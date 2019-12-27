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

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import de.textmode.afpbox.io.AfpDataInputStream;
import junit.framework.TestCase;

/**
 * Unit-Tests for the class {@link ByteUtilsTest}.
 */
public final class ByteUtilsTest extends TestCase {
    /**
     * Helper Method for creating a {@link AfpDataInputStream}.
     */
    private static byte[] toByteArray(final String hexString) throws DecoderException {
        return Hex.decodeHex(hexString.toCharArray());
    }

    /**
     * Checks reading unsigned 8 bit values.
     */
    public void testReadUnsignedByte() throws Exception {
        assertEquals(0, ByteUtils.toUnsignedByte(toByteArray("00"), 0));
        assertEquals(1, ByteUtils.toUnsignedByte(toByteArray("01"), 0));
        assertEquals(128, ByteUtils.toUnsignedByte(toByteArray("80"), 0));
        assertEquals(255, ByteUtils.toUnsignedByte(toByteArray("FF"), 0));

        assertEquals(1, ByteUtils.toUnsignedByte(toByteArray("0001"), 1));
        assertEquals(255, ByteUtils.toUnsignedByte(toByteArray("00FF"), 1));
    }

    /**
     * Checks reading signed 8 bit values.
     */
    public void testReadSignedByte() throws Exception {
        assertEquals(0, ByteUtils.toByte(toByteArray("00"), 0));
        assertEquals(1, ByteUtils.toByte(toByteArray("01"), 0));
        assertEquals(-128, ByteUtils.toByte(toByteArray("80"), 0));
        assertEquals(-1, ByteUtils.toByte(toByteArray("FF"), 0));

        assertEquals(1, ByteUtils.toByte(toByteArray("0001"), 1));
        assertEquals(-1, ByteUtils.toByte(toByteArray("00FF"), 1));
    }

    /**
     * Checks reading unsigned 16 bit values.
     */
    public void testReadUnsignedInteger16() throws Exception {
        assertEquals(0, ByteUtils.toUnsignedInteger16(toByteArray("0000"), 0));
        assertEquals(1, ByteUtils.toUnsignedInteger16(toByteArray("0001"), 0));
        assertEquals(512, ByteUtils.toUnsignedInteger16(toByteArray("0200"), 0));
        assertEquals(50176, ByteUtils.toUnsignedInteger16(toByteArray("C400"), 0));
        assertEquals(65535, ByteUtils.toUnsignedInteger16(toByteArray("FFFF"), 0));

        assertEquals(1, ByteUtils.toUnsignedInteger16(toByteArray("000001"), 1));
        assertEquals(50176, ByteUtils.toUnsignedInteger16(toByteArray("00C400"), 1));
    }

    /**
     * Checks reading signed 16 bit values.
     */
    public void testReadSignedInteger16() throws Exception {
        assertEquals(12345, ByteUtils.toInteger16(toByteArray("3039"), 0));
        assertEquals(12288, ByteUtils.toInteger16(toByteArray("3000"), 0));
        assertEquals(1024, ByteUtils.toInteger16(toByteArray("0400"), 0));
        assertEquals(512, ByteUtils.toInteger16(toByteArray("0200"), 0));
        assertEquals(1, ByteUtils.toInteger16(toByteArray("0001"), 0));
        assertEquals(0, ByteUtils.toInteger16(toByteArray("0000"), 0));
        assertEquals(-1, ByteUtils.toInteger16(toByteArray("FFFF"), 0));
        assertEquals(-512, ByteUtils.toInteger16(toByteArray("FE00"), 0));
        assertEquals(-1024, ByteUtils.toInteger16(toByteArray("FC00"), 0));
        assertEquals(-12288, ByteUtils.toInteger16(toByteArray("D000"), 0));
        assertEquals(-12345, ByteUtils.toInteger16(toByteArray("CFC7"), 0));

        assertEquals(1024, ByteUtils.toInteger16(toByteArray("000400"), 1));
        assertEquals(-1, ByteUtils.toInteger16(toByteArray("00FFFF"), 1));
        assertEquals(-12288, ByteUtils.toInteger16(toByteArray("00D000"), 1));
    }

    /**
     * Checks reading unsigned 24 bit values.
     */
    public void testReadUnsignedInteger24() throws Exception {
        assertEquals(0, ByteUtils.toUnsignedInteger24(toByteArray("000000"), 0));
        assertEquals(1, ByteUtils.toUnsignedInteger24(toByteArray("000001"), 0));
        assertEquals(512, ByteUtils.toUnsignedInteger24(toByteArray("000200"), 0));
        assertEquals(12845056, ByteUtils.toUnsignedInteger24(toByteArray("C40000"), 0));
        assertEquals(16777215, ByteUtils.toUnsignedInteger24(toByteArray("FFFFFF"), 0));

        assertEquals(1, ByteUtils.toUnsignedInteger24(toByteArray("00000001"), 1));
        assertEquals(12845056, ByteUtils.toUnsignedInteger24(toByteArray("00C40000"), 1));
    }

    /**
     * Checks reading signed 24 bit values.
     */
    public void testReadSignedInteger24() throws Exception {
        assertEquals(0, ByteUtils.toInteger24(toByteArray("000000"), 0));
        assertEquals(1, ByteUtils.toInteger24(toByteArray("000001"), 0));
        assertEquals(512, ByteUtils.toInteger24(toByteArray("000200"), 0));
        assertEquals(-3932160, ByteUtils.toInteger24(toByteArray("C40000"), 0));
        assertEquals(-1, ByteUtils.toInteger24(toByteArray("FFFFFF"), 0));

        assertEquals(1, ByteUtils.toInteger24(toByteArray("00000001"), 1));
        assertEquals(-3932160, ByteUtils.toInteger24(toByteArray("00C40000"), 1));
    }

    /**
     * Checks reading unsigned 32 bit values.
     */
    public void testReadUnsignedInteger32() throws Exception {
        assertEquals(12345, ByteUtils.toUnsignedInteger32(toByteArray("00003039"), 0));
        assertEquals(12288, ByteUtils.toUnsignedInteger32(toByteArray("00003000"), 0));
        assertEquals(1024, ByteUtils.toUnsignedInteger32(toByteArray("00000400"), 0));
        assertEquals(512, ByteUtils.toUnsignedInteger32(toByteArray("00000200"), 0));
        assertEquals(1, ByteUtils.toUnsignedInteger32(toByteArray("00000001"), 0));
        assertEquals(0, ByteUtils.toUnsignedInteger32(toByteArray("00000000"), 0));
        assertEquals(4294967295L, ByteUtils.toUnsignedInteger32(toByteArray("FFFFFFFF"), 0));
        assertEquals(4294966784L, ByteUtils.toUnsignedInteger32(toByteArray("FFFFFE00"), 0));
        assertEquals(4294966272L, ByteUtils.toUnsignedInteger32(toByteArray("FFFFFC00"), 0));

        assertEquals(1024, ByteUtils.toUnsignedInteger32(toByteArray("0000000400"), 1));
        assertEquals(4294966784L, ByteUtils.toUnsignedInteger32(toByteArray("00FFFFFE00"), 1));
    }

    /**
     * Checks reading signed 32 bit values.
     */
    public void testReadSignedInteger32() throws Exception {
        assertEquals(12345, ByteUtils.toInteger32(toByteArray("00003039"), 0));
        assertEquals(12288, ByteUtils.toInteger32(toByteArray("00003000"), 0));
        assertEquals(1024, ByteUtils.toInteger32(toByteArray("00000400"), 0));
        assertEquals(512, ByteUtils.toInteger32(toByteArray("00000200"), 0));
        assertEquals(1, ByteUtils.toInteger32(toByteArray("00000001"), 0));
        assertEquals(0, ByteUtils.toInteger32(toByteArray("00000000"), 0));
        assertEquals(-1, ByteUtils.toInteger32(toByteArray("FFFFFFFF"), 0));
        assertEquals(-512, ByteUtils.toInteger32(toByteArray("FFFFFE00"), 0));
        assertEquals(-1024, ByteUtils.toInteger32(toByteArray("FFFFFC00"), 0));
        assertEquals(-12288, ByteUtils.toInteger32(toByteArray("FFFFD000"), 0));
        assertEquals(-12345, ByteUtils.toInteger32(toByteArray("FFFFCFC7"), 0));

        assertEquals(512, ByteUtils.toInteger32(toByteArray("0000000200"), 1));
        assertEquals(-1024, ByteUtils.toInteger32(toByteArray("00FFFFFC00"), 1));
    }
}
