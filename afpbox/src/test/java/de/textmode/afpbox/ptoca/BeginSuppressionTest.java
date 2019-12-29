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

public final class BeginSuppressionTest extends PtocaControlSequenceTest<BeginSuppression> {

    /**
     * Checks if a faulty BSU is determined.
     */
    public void testFaulty() throws Exception {
        this.parseAndExpectFailure("2BD302F2",
                "PTOCA control sequence BSU has invalid length of 2 bytes (expected 3 bytes)");
    }

    /**
     * Checks some correct BSUs.
     */
    public void testHappyFlow() throws Exception {
        assertEquals(0, this.parse("2BD303F200").getSuppressionIdentifier());
        assertEquals(1, this.parse("2BD303F201").getSuppressionIdentifier());

        assertEquals(128, this.parse("2BD303F380").getSuppressionIdentifier());
        assertEquals(255, this.parse("2BD303F3FF").getSuppressionIdentifier());
    }
}