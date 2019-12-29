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
 * Unit-Tests for the class {@link DrawBaselineAxisRule}.
 */
public final class DrawBaselineAxisRuleTest extends PtocaControlSequenceTest<DrawBaselineAxisRule> {

    /**
     * Checks if a faulty AMB is determined.
     */
    public void testFaulty() throws Exception {
        this.parseAndExpectFailure("2BD303E601",
                "PTOCA control sequence DBR has invalid length of 3 bytes (expected 4 or 7 bytes)");
    }

    /**
     * Checks some correct AMBs.
     */
    public void testHappyFlow() throws Exception {
        final DrawBaselineAxisRule dbr1 = this.parse("2BD304E60001");
        assertEquals(1, dbr1.getLength());
        assertEquals(0xFFFFFFFF, dbr1.getWidth());

        final DrawBaselineAxisRule dbr2 = this.parse("2BD304E68000");
        assertEquals(-32768, dbr2.getLength());
        assertEquals(0xFFFFFFFF, dbr2.getWidth());

        final DrawBaselineAxisRule dbr3 = this.parse("2BD307E77FFF010203");
        assertEquals(32767, dbr3.getLength());
        assertEquals(0x010203, dbr3.getWidth());
    }
}
