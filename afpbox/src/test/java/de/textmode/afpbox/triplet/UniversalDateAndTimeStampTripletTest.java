package de.textmode.afpbox.triplet;

/*
 * Copyright 2020 Michael Knigge
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Unit-Tests for the class {@link UniversalDateAndTimeStampTriplet}.
 */
public class UniversalDateAndTimeStampTripletTest extends TripletTest<UniversalDateAndTimeStampTriplet> {

    /**
     * Checks if a faulty Triplet is determined.
     */
    public void testFaulty() throws Exception {
        this.parseAndExpectFailure("037240",
                "Triplet X'72' has invalid length of 3 bytes (expected 13 bytes).");
    }

    /**
     * Checks some correct Triplets.
     */
    public void testHappyFlow() throws Exception {
        final UniversalDateAndTimeStampTriplet t1 = this.parseSingle("0D720007E40405010203000102");
        assertEquals(2020, t1.getYear());
        assertEquals(4, t1.getMonth());
        assertEquals(5, t1.getDay());
        assertEquals(1, t1.getHour());
        assertEquals(2, t1.getMinute());
        assertEquals(3, t1.getSecond());
        assertEquals(0, t1.getTimeZone());
        assertEquals(1, t1.getUtcDifferenceHours());
        assertEquals(2, t1.getUtcDifferenceMinutes());
    }
}
