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
 * Unit-Tests for the class {@link SetInlineMargin}.
 */
public final class SetInlineMarginTest extends PtocaControlSequenceTest<SetInlineMargin> {

    /**
     * Checks if a faulty SIA is determined.
     */
    public void testFaulty() throws Exception {
        this.parseAndExpectFailure("2BD303C001",
                "PTOCA control sequence SIM has invalid length of 3 bytes (expected 4 bytes)");
    }

    /**
     * Checks some correct SIAs.
     */
    public void testHappyFlow() throws Exception {
        assertEquals(1, this.parse("2BD304C00001").getDisplacement());
        assertEquals(0, this.parse("2BD304C00000").getDisplacement());
        assertEquals(32767, this.parse("2BD304C17FFF").getDisplacement());
    }
}
