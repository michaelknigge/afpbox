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
 * Unit-Tests for the class {@link SetTextOrientation}.
 */
public final class SetTextOrientationTest extends PtocaControlSequenceTest<SetTextOrientation> {

    /**
     * Checks if a faulty STO is determined.
     */
    public void testFaulty() throws Exception {
        this.parseAndExpectFailure("2BD305F6000000",
                "PTOCA control sequence STO has invalid length of 5 bytes (expected 6 bytes)");
    }

    /**
     * Checks some correct STOs.
     */
    public void testHappyFlow() throws Exception {
        final SetTextOrientation sto1 = this.parse("2BD306F601020304");
        assertEquals(0x0102, sto1.getInlineAxisOrientation());
        assertEquals(0x0304, sto1.getBaselineAxisOrientation());

        final SetTextOrientation sto2 = this.parse("2BD306F78899FFEE");
        assertEquals(0x8899, sto2.getInlineAxisOrientation());
        assertEquals(0xFFEE, sto2.getBaselineAxisOrientation());
    }
}
