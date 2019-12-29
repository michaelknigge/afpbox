package de.textmode.afpbox.ptoca;

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

import org.apache.commons.codec.binary.Hex;

/**
 * Unit-Tests for the class {@link TransparentData}.
 */
public final class TransparentDataTest extends PtocaControlSequenceTest<TransparentData> {

    /**
     * Checks if a faulty TRN is determined.
     */
    public void testFaulty() throws Exception {
        this.parseAndExpectFailure("2BD301DA",
                "A length value 1 was read for an PTOCA control sequence.");
    }

    /**
     * Checks some correct TRNs.
     */
    public void testHappyFlow() throws Exception {
        assertEquals("", Hex.encodeHexString(this.parse("2BD302DA").getTransparentData()).toUpperCase());

        assertEquals("00", Hex.encodeHexString(this.parse("2BD303DA00").getTransparentData()).toUpperCase());
        assertEquals("000102", Hex.encodeHexString(this.parse("2BD305DA000102").getTransparentData()).toUpperCase());

        assertEquals("FF", Hex.encodeHexString(this.parse("2BD303DBFF").getTransparentData()).toUpperCase());
        assertEquals("FF00FF", Hex.encodeHexString(this.parse("2BD305DBFF00FF").getTransparentData()).toUpperCase());
    }
}