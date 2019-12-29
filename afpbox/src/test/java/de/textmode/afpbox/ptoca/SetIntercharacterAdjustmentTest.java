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
 * Unit-Tests for the class {@link SetIntercharacterAdjustment}.
 */
public final class SetIntercharacterAdjustmentTest extends PtocaControlSequenceTest<SetIntercharacterAdjustment> {

    /**
     * Checks if a faulty SIA is determined.
     */
    public void testFaulty() throws Exception {
        this.parseAndExpectFailure("2BD303C201",
                "PTOCA control sequence SIA has invalid length of 3 bytes (expected 4 or 5 bytes)");

        this.parseAndExpectFailure("2BD306C201000000",
                "PTOCA control sequence SIA has invalid length of 6 bytes (expected 4 or 5 bytes)");
    }

    /**
     * Checks some correct SIAs.
     */
    public void testHappyFlow() throws Exception {
        final SetIntercharacterAdjustment sia1 = this.parse("2BD304C20001");
        assertEquals(1, sia1.getAdjustment());
        assertEquals(0, sia1.getDirection());

        final SetIntercharacterAdjustment sia2 = this.parse("2BD305C2000001");
        assertEquals(0, sia2.getAdjustment());
        assertEquals(1, sia2.getDirection());

        final SetIntercharacterAdjustment sia3 = this.parse("2BD305C27FFF01");
        assertEquals(32767, sia3.getAdjustment());
        assertEquals(1, sia3.getDirection());

        final SetIntercharacterAdjustment sia4 = this.parse("2BD305C3800000");
        assertEquals(-32768, sia4.getAdjustment());
        assertEquals(0, sia4.getDirection());

        final SetIntercharacterAdjustment sia5 = this.parse("2BD304C3FC00");
        assertEquals(-1024, sia5.getAdjustment());
        assertEquals(0, sia5.getDirection());
    }
}
