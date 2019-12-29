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
 * Unit-Tests for the class {@link BeginLine}.
 */
public final class BeginLineTest extends PtocaControlSequenceTest<BeginLine> {

    /**
     * Checks if a faulty RMB is determined.
     */
    public void testFaulty() throws Exception {
        this.parseAndExpectFailure("2BD303D801",
                "PTOCA control sequence BLN has invalid length of 3 bytes (expected 2 bytes)");
    }

    /**
     * Checks correct BLN.
     */
    public void testHappyFlow() throws Exception {
        this.parse("2BD302D8");
        this.parse("2BD302D9");
    }
}
