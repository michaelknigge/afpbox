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
 * Unit-Tests for the class {@link DrawInlineAxisRule}.
 */
public final class DrawInlineAxisRuleTest extends PtocaControlSequenceTest<DrawInlineAxisRule> {

    /**
     * Checks if a faulty AMI is determined.
     */
    public void testFaulty() throws Exception {
        this.parseAndExpectFailure("2BD303E401",
                "PTOCA control sequence DIR has invalid length of 3 bytes (expected 4 or 7 bytes)");
    }

    /**
     * Checks some correct AMIs.
     */
    public void testHappyFlow() throws Exception {
        final DrawInlineAxisRule dbr1 = this.parse("2BD304E40001");
        assertEquals(1, dbr1.getLength());
        assertEquals(0xFFFFFFFF, dbr1.getWidth());

        final DrawInlineAxisRule dbr2 = this.parse("2BD304E48000");
        assertEquals(-32768, dbr2.getLength());
        assertEquals(0xFFFFFFFF, dbr2.getWidth());

        final DrawInlineAxisRule dbr3 = this.parse("2BD307E57FFF010203");
        assertEquals(32767, dbr3.getLength());
        assertEquals(0x010203, dbr3.getWidth());
    }
}
