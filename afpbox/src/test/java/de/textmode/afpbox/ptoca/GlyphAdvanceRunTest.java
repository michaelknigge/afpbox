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
 * Unit-Tests for the class {@link GlyphAdvanceRun}.
 */
public final class GlyphAdvanceRunTest extends PtocaControlSequenceTest<GlyphAdvanceRun> {

    /**
     * Checks if a faulty GAR is determined.
     */
    public void testFaulty() throws Exception {
        this.parseAndExpectFailure("2BD3038C01",
                "PTOCA control sequence GAR has invalid length of 3 bytes (expected at least 4 bytes, only even values)");

        this.parseAndExpectFailure("2BD3058D010000",
                "PTOCA control sequence GAR has invalid length of 5 bytes (expected at least 4 bytes, only even values)");
    }

    /**
     * Checks some correct GARs.
     */
    public void testHappyFlow() throws Exception {
        final GlyphAdvanceRun gar1 = this.parse("2BD3048C0000");
        assertEquals(0, gar1.getGlyphAdvance().length);

        final GlyphAdvanceRun gar2 = this.parse("2BD3068C00001122");
        assertEquals(1, gar2.getGlyphAdvance().length);
        assertEquals(0x1122, gar2.getGlyphAdvance()[0]);

        final GlyphAdvanceRun gar3 = this.parse("2BD3088D00007FFF8000");
        assertEquals(2, gar3.getGlyphAdvance().length);
        assertEquals(32767, gar3.getGlyphAdvance()[0]);
        assertEquals(32768, gar3.getGlyphAdvance()[1]);
    }
}
