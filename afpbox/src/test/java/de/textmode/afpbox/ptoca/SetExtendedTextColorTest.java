package de.textmode.afpbox.ptoca;

import org.apache.commons.codec.binary.Hex;

/*
 * Copyright 2019 Michael Knigge
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
 * Unit-Tests for the class {@link SetExtendedTextColor}.
 */
public final class SetExtendedTextColorTest extends PtocaControlSequenceTest<SetExtendedTextColor> {

    /**
     * Checks if a faulty SEC is determined.
     */
    public void testFaulty() throws Exception {
        this.parseAndExpectFailure("2BD3068000000000",
                "PTOCA control sequence SEC has invalid length of 6 bytes (expected 14-16 bytes)");
    }

    /**
     * Checks some correct SECs.
     */
    public void testHappyFlow() throws Exception {
        final SetExtendedTextColor sec1 = this.parse("2BD30E80000400000000112233445566");
        assertEquals(0x04, sec1.getColorSpace());
        assertEquals(0x11, sec1.getNumberOfBitsInComponent1());
        assertEquals(0x22, sec1.getNumberOfBitsInComponent2());
        assertEquals(0x33, sec1.getNumberOfBitsInComponent3());
        assertEquals(0x44, sec1.getNumberOfBitsInComponent4());
        assertEquals("5566", Hex.encodeHexString(sec1.getColorSpecifications()));

        final SetExtendedTextColor sec2 = this.parse("2BD310800004000000001122334455667788");
        assertEquals(0x04, sec2.getColorSpace());
        assertEquals(0x11, sec2.getNumberOfBitsInComponent1());
        assertEquals(0x22, sec2.getNumberOfBitsInComponent2());
        assertEquals(0x33, sec2.getNumberOfBitsInComponent3());
        assertEquals(0x44, sec2.getNumberOfBitsInComponent4());
        assertEquals("55667788", Hex.encodeHexString(sec2.getColorSpecifications()));
    }
}
