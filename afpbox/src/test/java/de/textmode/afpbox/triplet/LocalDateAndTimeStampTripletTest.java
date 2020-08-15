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
 * Unit-Tests for the class {@link LocalDateAndTimeStampTriplet}.
 */
public final class LocalDateAndTimeStampTripletTest extends TripletTest<LocalDateAndTimeStampTriplet> {

    /**
     * Checks if a faulty Triplet is determined.
     */
    public void testFaulty() throws Exception {
        this.parseAndExpectFailure("036240",
                "Triplet X'62' has invalid length of 3 bytes (expected 17 bytes).");
    }

    /**
     * Checks some correct Triplets.
     */
    public void testHappyFlow() throws Exception {
        final LocalDateAndTimeStampTriplet t1 = this.parseSingle("116203F0F2F0F1F2F3F2F3F5F9F4F5F1F2");
        assertEquals(0x03, t1.getStampType());
        assertEquals(0xF0, t1.getYearHundreds());
        assertEquals(0xF2F0, t1.getYearTens());
        assertEquals(0xF1F2F3, t1.getDay());
        assertEquals(0xF2F3, t1.getHour());
        assertEquals(0xF5F9, t1.getMinute());
        assertEquals(0xF4F5, t1.getSecond());
        assertEquals(0xF1F2, t1.getHundredthOfSecond());
    }
}
