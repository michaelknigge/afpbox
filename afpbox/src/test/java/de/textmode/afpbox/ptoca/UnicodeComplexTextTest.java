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

/**
 * Unit-Tests for the class {@link UnicodeComplexText}.
 */
public final class UnicodeComplexTextTest extends PtocaControlSequenceTest<UnicodeComplexText> {

    /**
     * Checks if a faulty UCT is determined.
     */
    public void testFaulty() throws Exception {
        this.parseAndExpectFailure("2BD3036A01",
                "PTOCA control sequence UCT has invalid length of 3 bytes (expected 16 bytes)");
    }

    /**
     * Checks some correct UCTs.
     */
    public void testHappyFlow() throws Exception {
        final UnicodeComplexText uct1 = this.parse("2BD3106A01001122AB001220000000007FFF");
        assertEquals(1, uct1.getVersionLevel());
        assertEquals(0x1122, uct1.getComplexTextLength());
        assertEquals(0xAB, uct1.getComplexTextProcessingControlFlags());
        assertEquals(0x12, uct1.getBidiLayoutProcessingControlCode());
        assertEquals(0x20, uct1.getGlyphProcessingControlCode());
        assertEquals(32767, uct1.getAlternateCurrentInlinePosition());

        final UnicodeComplexText uct2 = this.parse("2BD3106A01001122AB001220000000008000");
        assertEquals(1, uct2.getVersionLevel());
        assertEquals(0x1122, uct2.getComplexTextLength());
        assertEquals(0xAB, uct2.getComplexTextProcessingControlFlags());
        assertEquals(0x12, uct2.getBidiLayoutProcessingControlCode());
        assertEquals(0x20, uct2.getGlyphProcessingControlCode());
        assertEquals(-32768, uct2.getAlternateCurrentInlinePosition());
    }
}
