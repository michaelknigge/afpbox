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
 * Unit-Tests for the class {@link DescriptorPositionTriplet}.
 */
public final class DescriptorPositionTripletTest extends TripletTest<DescriptorPositionTriplet> {

    /**
     * Checks if a faulty Triplet is determined.
     */
    public void testFaulty() throws Exception {
        this.parseAndExpectFailure("0243",
                "Triplet X'43' has invalid length of 2 bytes (expected 3 bytes).");

        this.parseAndExpectFailure("04430000",
                "Triplet X'43' has invalid length of 4 bytes (expected 3 bytes).");
    }

    /**
     * Checks some correct Triplets.
     */
    public void testHappyFlow() throws Exception {
        assertEquals(0, this.parseSingle("034300").getDescriptorPositionId());
        assertEquals(1, this.parseSingle("034301").getDescriptorPositionId());
        assertEquals(127, this.parseSingle("03437F").getDescriptorPositionId());
    }
}
