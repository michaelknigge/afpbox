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
 * Unit-Tests for the class {@link TemporaryBaselineMove}.
 */
public final class TemporaryBaselineMoveTest extends PtocaControlSequenceTest<TemporaryBaselineMove> {

    /**
     * Checks if a faulty TBM is determined.
     */
    public void testFaulty() throws Exception {
        this.parseAndExpectFailure("2BD30578000000",
                "PTOCA control sequence TBM has invalid length of 5 bytes (expected 3, 4 or 6 bytes)");
    }

    /**
     * Checks some correct TBMs.
     */
    public void testHappyFlow() throws Exception {
        final TemporaryBaselineMove tbm1 = this.parse("2BD3037801");
        assertEquals(1, tbm1.getDirection());
        assertEquals(0, tbm1.getPrecision());
        assertEquals(-1, tbm1.getTemporaryBaselineIncrement());

        final TemporaryBaselineMove tbm2 = this.parse("2BD3037903");
        assertEquals(3, tbm2.getDirection());
        assertEquals(0, tbm2.getPrecision());
        assertEquals(-1, tbm2.getTemporaryBaselineIncrement());

        final TemporaryBaselineMove tbm3 = this.parse("2BD304790201");
        assertEquals(2, tbm3.getDirection());
        assertEquals(1, tbm3.getPrecision());
        assertEquals(-1, tbm3.getTemporaryBaselineIncrement());

        final TemporaryBaselineMove tbm4 = this.parse("2BD3067902010000");
        assertEquals(2, tbm4.getDirection());
        assertEquals(1, tbm4.getPrecision());
        assertEquals(0, tbm4.getTemporaryBaselineIncrement());

        final TemporaryBaselineMove tbm5 = this.parse("2BD3067903007FFF");
        assertEquals(3, tbm5.getDirection());
        assertEquals(0, tbm5.getPrecision());
        assertEquals(32767, tbm5.getTemporaryBaselineIncrement());
    }
}
