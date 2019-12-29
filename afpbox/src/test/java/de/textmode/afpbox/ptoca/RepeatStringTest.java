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

import org.apache.commons.codec.binary.Hex;

/**
 * Unit-Tests for the class {@link RepeatString}.
 */
public final class RepeatStringTest extends PtocaControlSequenceTest<RepeatString> {

    /**
     * Checks if a faulty RPS is determined.
     */
    public void testFaulty() throws Exception {
        this.parseAndExpectFailure("2BD303EE01",
                "PTOCA control sequence RPS has invalid length of 3 bytes (expected at least 4 bytes)");
    }

    /**
     * Checks some correct RPSs.
     */
    public void testHappyFlow() throws Exception {
        final RepeatString rps1 = this.parse("2BD304EE0000");
        assertEquals(0, rps1.getRepeatLength());
        assertEquals(0, rps1.getRepeatData().length);

        final RepeatString rps2 = this.parse("2BD305EE000102");
        assertEquals(1, rps2.getRepeatLength());
        assertEquals("02", Hex.encodeHexString(rps2.getRepeatData()).toUpperCase());

        final RepeatString rps3 = this.parse("2BD307EF0006010203");
        assertEquals(6, rps3.getRepeatLength());
        assertEquals("010203", Hex.encodeHexString(rps3.getRepeatData()).toUpperCase());
    }
}
