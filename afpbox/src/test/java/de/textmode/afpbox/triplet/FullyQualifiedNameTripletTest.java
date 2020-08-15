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
 * Unit-Tests for the class {@link FullyQualifiedNameTriplet}.
 */
public final class FullyQualifiedNameTripletTest extends TripletTest<FullyQualifiedNameTriplet> {

    /**
     * Checks if a faulty Triplet is determined.
     */
    public void testFaulty() throws Exception {
        this.parseAndExpectFailure("030240",
                "Triplet X'02' has invalid length of 3 bytes (expected 5 to 254 bytes).");
    }

    /**
     * Checks some correct Triplets.
     */
    public void testHappyFlow() throws Exception {
        final FullyQualifiedNameTriplet t1 = this.parseSingle("07028500010203");
        assertEquals(0x85, t1.getFullyQualifiedNameType());
        assertEquals(0x00, t1.getFullyQualifiedNameFormat());
        assertEquals(0x01, t1.getFullyQualifiedName()[0]);
        assertEquals(0x02, t1.getFullyQualifiedName()[1]);
        assertEquals(0x03, t1.getFullyQualifiedName()[2]);
    }
}
