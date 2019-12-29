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
 * Unit-Tests for the class {@link SetTextColor}.
 */
public final class SetTextColorTest extends PtocaControlSequenceTest<SetTextColor> {

    /**
     * Checks if a faulty STC is determined.
     */
    public void testFaulty() throws Exception {
        this.parseAndExpectFailure("2BD3067400000000",
                "PTOCA control sequence STC has invalid length of 6 bytes (expected 4 or 5 bytes)");
    }

    /**
     * Checks some correct STCs.
     */
    public void testHappyFlow() throws Exception {
        final SetTextColor stc1 = this.parse("2BD30474FF01");
        assertEquals(0xFF01, stc1.getForegroundColor());
        assertEquals(0x00, stc1.getPrecision());

        final SetTextColor stc2 = this.parse("2BD30575010201");
        assertEquals(0x0102, stc2.getForegroundColor());
        assertEquals(0x01, stc2.getPrecision());
    }
}
