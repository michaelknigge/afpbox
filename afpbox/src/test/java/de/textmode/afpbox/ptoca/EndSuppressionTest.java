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

public final class EndSuppressionTest extends PtocaControlSequenceTest<EndSuppression> {

    /**
     * Checks if a faulty ESU is determined.
     */
    public void testFaulty() throws Exception {
        this.parseAndExpectFailure("2BD302F4",
                "PTOCA control sequence ESU has invalid length of 2 bytes (expected 3 bytes)");
    }

    /**
     * Checks some correct ESUs.
     */
    public void testHappyFlow() throws Exception {
        assertEquals(0, this.parse("2BD303F400").getSuppressionIdentifier());
        assertEquals(1, this.parse("2BD303F401").getSuppressionIdentifier());

        assertEquals(128, this.parse("2BD303F580").getSuppressionIdentifier());
        assertEquals(255, this.parse("2BD303F5FF").getSuppressionIdentifier());
    }
}