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
 * Unit-Tests for the class {@link Overstrike}.
 */
public final class OverstrikeTest extends PtocaControlSequenceTest<Overstrike> {

    /**
     * Checks if a faulty OVS is determined.
     */
    public void testFaulty() throws Exception {
        this.parseAndExpectFailure("2BD304720001",
                "PTOCA control sequence OVS has invalid length of 4 bytes (expected 5 bytes)");
    }

    /**
     * Checks some correct OVSs.
     */
    public void testHappyFlow() throws Exception {
        final Overstrike ovs1 = this.parse("2BD30572000000");
        assertEquals(0, ovs1.getBypassIdentifiers());
        assertEquals(0, ovs1.getOverstrikeIdentifiers());

        final Overstrike ovs2 = this.parse("2BD30572010203");
        assertEquals(0x01, ovs2.getBypassIdentifiers());
        assertEquals(0x0203, ovs2.getOverstrikeIdentifiers());
    }
}
