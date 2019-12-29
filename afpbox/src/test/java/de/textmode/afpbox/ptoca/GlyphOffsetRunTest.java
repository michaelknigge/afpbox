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
 * Unit-Tests for the class {@link GlyphOffsetRun}.
 */
public final class GlyphOffsetRunTest extends PtocaControlSequenceTest<GlyphOffsetRun> {

    /**
     * Checks if a faulty GOR is determined.
     */
    public void testFaulty() throws Exception {
        this.parseAndExpectFailure("2BD3038E01",
                "PTOCA control sequence GOR has invalid length of 3 bytes (expected at least 4 bytes, only even values)");

        this.parseAndExpectFailure("2BD3058F010000",
                "PTOCA control sequence GOR has invalid length of 5 bytes (expected at least 4 bytes, only even values)");
    }

    /**
     * Checks some correct GORs.
     */
    public void testHappyFlow() throws Exception {
        final GlyphOffsetRun gor1 = this.parse("2BD3048E0000");
        assertEquals(0, gor1.getGlyphOffset().length);

        final GlyphOffsetRun gor2 = this.parse("2BD3068E00001122");
        assertEquals(1, gor2.getGlyphOffset().length);
        assertEquals(0x1122, gor2.getGlyphOffset()[0]);

        final GlyphOffsetRun gor3 = this.parse("2BD3088F00007FFF8000");
        assertEquals(2, gor3.getGlyphOffset().length);
        assertEquals(32767, gor3.getGlyphOffset()[0]);
        assertEquals(-32768, gor3.getGlyphOffset()[1]);
    }
}
