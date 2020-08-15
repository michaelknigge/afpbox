package de.textmode.afpbox.triplet;

/*
 * Copyright 2020 Michael Knigge
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
 * Unit-Tests for the class {@link UnknownTriplet}.
 */
public final class UnknownTripletTest  extends TripletTest<UnknownTriplet> {

    /**
     * Checks if an unknwon triplet is returned as an {@link UnknownTriplet}.
     */
    public void testHappyFlow() throws Exception {
        final UnknownTriplet t1 = this.parseSingle("030040");
        assertEquals(3, t1.getData().length);
        assertEquals(0x03, t1.getData()[0]);
        assertEquals(0x00, t1.getData()[1]);
        assertEquals(0x40, t1.getData()[2]);
    }
}
