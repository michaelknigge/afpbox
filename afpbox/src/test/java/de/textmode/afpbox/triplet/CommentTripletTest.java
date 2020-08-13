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
 * Unit-Tests for the class {@link CommentTriplet}.
 */
public final class CommentTripletTest extends TripletTest<CommentTriplet> {

    /**
     * Checks if a faulty Triplet is determined.
     */
    public void testFaulty() throws Exception {
        this.parseAndExpectFailure("0265",
                "Triplet X'65' has invalid length of 2 bytes (expected at least 3 bytes).");
    }

    /**
     * Checks some correct Triplets.
     */
    public void testHappyFlow() throws Exception {
        final byte[] t1 = this.parseSingle("036540").getComment();
        assertEquals(1, t1.length);
        assertEquals(0x40, t1[0]);

        final byte[] t2 = this.parseSingle("0465F1F2").getComment();
        assertEquals(2, t2.length);
        assertEquals(0xF1, t2[0] & 0xFF);
        assertEquals(0xF2, t2[1] & 0xFF);
    }
}
