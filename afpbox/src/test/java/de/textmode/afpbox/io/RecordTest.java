package de.textmode.afpbox.io;

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

import java.util.Arrays;

import org.apache.commons.codec.binary.Hex;

import junit.framework.TestCase;

/**
 * Unit-Tests for the class {@link Record}.
 */
public final class RecordTest extends TestCase {

    /**
     * Checks all getters of {@link Record}.
     */
    public void testGetters() throws Exception {
        final byte[] beginPrintFile = Hex.decodeHex("5A0008D3A8A5000000".toCharArray());
        final Record record = new Record(beginPrintFile, 123);

        assertEquals(123, record.getOffset());
        assertTrue(Arrays.equals(beginPrintFile, record.getData()));
    }

    /**
     * Checks method hashCode().
     */
    public void testHashCode() throws Exception {
        final byte[] beginPrintFile = Hex.decodeHex("5A0008D3A8A5000000".toCharArray());
        final byte[] beginPage = Hex.decodeHex("5A0010D3A8A5000000F0F0F0F0F0F0F0F1".toCharArray());

        assertEquals(new Record(beginPrintFile, 0).hashCode(), new Record(beginPrintFile, 0).hashCode());
        assertTrue(new Record(beginPrintFile, 0).hashCode() != new Record(beginPrintFile, 1).hashCode());
        assertTrue(new Record(beginPrintFile, 0).hashCode() != new Record(beginPage, 0).hashCode());
    }

    /**
     * Checks method equals().
     */
    public void testEquals() throws Exception {
        final byte[] beginPrintFile = Hex.decodeHex("5A0008D3A8A5000000".toCharArray());
        final byte[] beginPage = Hex.decodeHex("5A0010D3A8A5000000F0F0F0F0F0F0F0F1".toCharArray());

        assertFalse(new Record(beginPrintFile, 0).equals(null));
        assertFalse(new Record(beginPrintFile, 0).equals(new Record(beginPrintFile, 1)));
        assertFalse(new Record(beginPrintFile, 0).equals(new Record(beginPage, 0)));
        assertTrue(new Record(beginPrintFile, 0).equals(new Record(beginPrintFile, 0)));
    }
}
