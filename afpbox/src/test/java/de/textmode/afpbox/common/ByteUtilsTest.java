package de.textmode.afpbox.common;

/*
 * Copyright 2018 Michael Knigge
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

import junit.framework.TestCase;

/**
 * Unit-Tests for the class {@link ByteUtilsTest}.
 */
public final class ByteUtilsTest extends TestCase {

    /**
     * Checks the toInteger() method.
     */
    public void testToInteger() {
        final byte[] src = new byte[] { 0x00, 0x01, 0x02, 0x03, 0x04, (byte) 0x80, (byte)0xFF };

        assertEquals(ByteUtils.toInteger(src, 0, 0), 0);
        assertEquals(ByteUtils.toInteger(src, 0, 1), 0);
        assertEquals(ByteUtils.toInteger(src, 0, 2), 0x01);

        assertEquals(ByteUtils.toInteger(src, 1, 1), 0x01);
        assertEquals(ByteUtils.toInteger(src, 1, 2), 0x0102);

        assertEquals(ByteUtils.toInteger(src, 1, 3), 0x010203);
        assertEquals(ByteUtils.toInteger(src, 1, 4), 0x01020304);

        // Note... toInteger() is always UNSIGNED!
        assertEquals(ByteUtils.toInteger(src, 5, 1), 0x80);
        assertEquals(ByteUtils.toInteger(src, 6, 1), 0xFF);
    }

}
