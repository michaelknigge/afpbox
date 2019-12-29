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
 * Unit-Tests for the class {@link GlyphIdRun}.
 */
public final class GlyphIdRunTest extends PtocaControlSequenceTest<GlyphIdRun> {

    /**
     * Checks if a faulty GAR is determined.
     */
    public void testFaulty() throws Exception {
        this.parseAndExpectFailure("2BD3038B01",
                "PTOCA control sequence GIR has invalid length of 3 bytes (expected at least 4 bytes, only even values)");

        this.parseAndExpectFailure("2BD3058B010000",
                "PTOCA control sequence GIR has invalid length of 5 bytes (expected at least 4 bytes, only even values)");
    }

    /**
     * Checks some correct GARs.
     */
    public void testHappyFlow() throws Exception {
        final GlyphIdRun gir1 = this.parse("2BD3048B0000");
        assertEquals(0, gir1.getGlyphId().length);

        final GlyphIdRun gir2 = this.parse("2BD3068B00001122");
        assertEquals(1, gir2.getGlyphId().length);
        assertEquals(0x1122, gir2.getGlyphId()[0]);

        final GlyphIdRun gir3 = this.parse("2BD3088B00007FFF8000");
        assertEquals(2, gir3.getGlyphId().length);
        assertEquals(32767, gir3.getGlyphId()[0]);
        assertEquals(32768, gir3.getGlyphId()[1]);
    }
}
