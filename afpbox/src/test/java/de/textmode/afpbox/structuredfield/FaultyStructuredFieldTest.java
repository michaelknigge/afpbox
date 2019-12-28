package de.textmode.afpbox.structuredfield;

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
 * Unit-Tests for the class {@link FaultyStructuredField}.
 */
public class FaultyStructuredFieldTest extends StructuredFieldTest<FaultyStructuredField> {

    public void testFaultyStructuredField() throws Exception {
        final String sfi = "5A0010D3A8A5FF0000";
        final String data = "F0F0F0F0F0F0F0F1";
        final FaultyStructuredField sf = this.parse(sfi + data);
        assertEquals(data, Hex.encodeHexString(sf.getData()).toUpperCase());
    }
}
