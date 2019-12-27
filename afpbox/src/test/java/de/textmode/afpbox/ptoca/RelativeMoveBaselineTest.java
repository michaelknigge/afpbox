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
 * Unit-Tests for the class {@link RelativeMoveBaseline}.
 */
public final class RelativeMoveBaselineTest extends PtocaControlSequenceTest<RelativeMoveBaseline> {

    /**
     * Checks if a faulty RMB is determined.
     */
    public void testFaulty() throws Exception {
        this.parseAndExpectFailure("2BD303D401",
                "PTOCA control sequence RMB has invalid length of 3 bytes (expected 4 bytes)");
    }

    /**
     * Checks some correct RMBs.
     */
    public void testHappyFlow() throws Exception {
        assertEquals(1, this.parse("2BD304D40001").getIncrement());
        assertEquals(0, this.parse("2BD304D40000").getIncrement());
        assertEquals(32767, this.parse("2BD304D57FFF").getIncrement());
        assertEquals(-32768, this.parse("2BD304D58000").getIncrement());
        assertEquals(-1024, this.parse("2BD304D5FC00").getIncrement());
    }
}
