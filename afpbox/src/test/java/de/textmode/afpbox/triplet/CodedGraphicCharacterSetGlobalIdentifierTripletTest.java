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
 * Unit-Tests for the class {@link CodedGraphicCharacterSetGlobalIdentifierTriplet}.
 */
public final class CodedGraphicCharacterSetGlobalIdentifierTripletTest
    extends TripletTest<CodedGraphicCharacterSetGlobalIdentifierTriplet> {

    /**
     * Checks if a faulty Triplet is determined.
     */
    public void testFaulty() throws Exception {
        this.parseAndExpectFailure("0501020304",
                "Triplet X'01' has invalid length of 5 bytes (expected 6 bytes).");

        this.parseAndExpectFailure("07010203040506",
                "Triplet X'01' has invalid length of 7 bytes (expected 6 bytes).");
    }

    /**
     * Checks some correct Triplets.
     */
    public void testHappyFlow() throws Exception {
        final CodedGraphicCharacterSetGlobalIdentifierTriplet t1 = this.parseSingle("06010000FFFF");
        assertEquals(0, t1.getGraphicCharacterSetGlobalIdentifier());
        assertEquals(0xFFFF, t1.getCodedCharacterSetIdentifier());

        final CodedGraphicCharacterSetGlobalIdentifierTriplet t2 = this.parseSingle("0601000000FF");
        assertEquals(0, t2.getGraphicCharacterSetGlobalIdentifier());
        assertEquals(0x00FF, t2.getCodedCharacterSetIdentifier());

        final CodedGraphicCharacterSetGlobalIdentifierTriplet t3 = this.parseSingle("0601FFFE00FF");
        assertEquals(0xFFFE, t3.getGraphicCharacterSetGlobalIdentifier());
        assertEquals(0x00FF, t3.getCodePageGlobalIdentifier());
    }
}
