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

import java.nio.charset.Charset;
import java.util.Arrays;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import de.textmode.afpbox.AfpException;
import junit.framework.TestCase;

/**
 * Unit-Tests for the class {@link AfpDataInputStreamTest}.
 */
public final class AfpDataInputStreamTest extends TestCase {

    /**
     * Helper Method for creating a {@link AfpDataInputStream}.
     */
    private static AfpDataInputStream buildAfpDataInputStream(final String hexString) throws DecoderException {
        return new AfpDataInputStream(Hex.decodeHex(hexString.toCharArray()), 0, 0);
    }

    /**
     * Checks reading unsigned 8 bit values.
     */
    public void testReadUnsignedByte() throws Exception {
        assertEquals(0, buildAfpDataInputStream("00").readUnsignedByte());
        assertEquals(1, buildAfpDataInputStream("01").readUnsignedByte());
        assertEquals(128, buildAfpDataInputStream("80").readUnsignedByte());
        assertEquals(255, buildAfpDataInputStream("FF").readUnsignedByte());
    }

    /**
     * Checks reading signed 8 bit values.
     */
    public void testReadSignedByte() throws Exception {
        assertEquals(0, buildAfpDataInputStream("00").readByte());
        assertEquals(1, buildAfpDataInputStream("01").readByte());
        assertEquals(-128, buildAfpDataInputStream("80").readByte());
        assertEquals(-1, buildAfpDataInputStream("FF").readByte());
    }

    /**
     * Checks reading unsigned 16 bit values.
     */
    public void testReadUnsignedInteger16() throws Exception {
        assertEquals(0, buildAfpDataInputStream("0000").readUnsignedInteger16());
        assertEquals(1, buildAfpDataInputStream("0001").readUnsignedInteger16());
        assertEquals(512, buildAfpDataInputStream("0200").readUnsignedInteger16());
        assertEquals(50176, buildAfpDataInputStream("C400").readUnsignedInteger16());
        assertEquals(65535, buildAfpDataInputStream("FFFF").readUnsignedInteger16());
    }

    /**
     * Checks reading signed 16 bit values.
     */
    public void testReadSignedInteger16() throws Exception {
        assertEquals(12345, buildAfpDataInputStream("3039").readInteger16());
        assertEquals(12288, buildAfpDataInputStream("3000").readInteger16());
        assertEquals(1024, buildAfpDataInputStream("0400").readInteger16());
        assertEquals(512, buildAfpDataInputStream("0200").readInteger16());
        assertEquals(1, buildAfpDataInputStream("0001").readInteger16());
        assertEquals(0, buildAfpDataInputStream("0000").readInteger16());
        assertEquals(-1, buildAfpDataInputStream("FFFF").readInteger16());
        assertEquals(-512, buildAfpDataInputStream("FE00").readInteger16());
        assertEquals(-1024, buildAfpDataInputStream("FC00").readInteger16());
        assertEquals(-12288, buildAfpDataInputStream("D000").readInteger16());
        assertEquals(-12345, buildAfpDataInputStream("CFC7").readInteger16());
    }

    /**
     * Checks reading unsigned 24 bit values.
     */
    public void testReadUnsignedInteger24() throws Exception {
        assertEquals(0, buildAfpDataInputStream("000000").readUnsignedInteger24());
        assertEquals(1, buildAfpDataInputStream("000001").readUnsignedInteger24());
        assertEquals(512, buildAfpDataInputStream("000200").readUnsignedInteger24());
        assertEquals(12845056, buildAfpDataInputStream("C40000").readUnsignedInteger24());
        assertEquals(16777215, buildAfpDataInputStream("FFFFFF").readUnsignedInteger24());
    }

    /**
     * Checks reading signed 24 bit values.
     */
    public void testReadSignedInteger24() throws Exception {
        assertEquals(0, buildAfpDataInputStream("000000").readInteger24());
        assertEquals(1, buildAfpDataInputStream("000001").readInteger24());
        assertEquals(512, buildAfpDataInputStream("000200").readInteger24());
        assertEquals(-3932160, buildAfpDataInputStream("C40000").readInteger24());
        assertEquals(-1, buildAfpDataInputStream("FFFFFF").readInteger24());
    }

    /**
     * Checks reading unsigned 32 bit values.
     */
    public void testReadUnsignedInteger32() throws Exception {
        assertEquals(12345, buildAfpDataInputStream("00003039").readUnsignedInteger32());
        assertEquals(12288, buildAfpDataInputStream("00003000").readUnsignedInteger32());
        assertEquals(1024, buildAfpDataInputStream("00000400").readUnsignedInteger32());
        assertEquals(512, buildAfpDataInputStream("00000200").readUnsignedInteger32());
        assertEquals(1, buildAfpDataInputStream("00000001").readUnsignedInteger32());
        assertEquals(0, buildAfpDataInputStream("00000000").readUnsignedInteger32());
        assertEquals(4294967295L, buildAfpDataInputStream("FFFFFFFF").readUnsignedInteger32());
        assertEquals(4294966784L, buildAfpDataInputStream("FFFFFE00").readUnsignedInteger32());
        assertEquals(4294966272L, buildAfpDataInputStream("FFFFFC00").readUnsignedInteger32());
    }

    /**
     * Checks reading signed 32 bit values.
     */
    public void testReadSignedInteger32() throws Exception {
        assertEquals(12345, buildAfpDataInputStream("00003039").readInteger32());
        assertEquals(12288, buildAfpDataInputStream("00003000").readInteger32());
        assertEquals(1024, buildAfpDataInputStream("00000400").readInteger32());
        assertEquals(512, buildAfpDataInputStream("00000200").readInteger32());
        assertEquals(1, buildAfpDataInputStream("00000001").readInteger32());
        assertEquals(0, buildAfpDataInputStream("00000000").readInteger32());
        assertEquals(-1, buildAfpDataInputStream("FFFFFFFF").readInteger32());
        assertEquals(-512, buildAfpDataInputStream("FFFFFE00").readInteger32());
        assertEquals(-1024, buildAfpDataInputStream("FFFFFC00").readInteger32());
        assertEquals(-12288, buildAfpDataInputStream("FFFFD000").readInteger32());
        assertEquals(-12345, buildAfpDataInputStream("FFFFCFC7").readInteger32());
    }

    /**
     * Checks reading multiple values
     */
    public void testReadMultipleValues() throws Exception {
        final AfpDataInputStream is = new AfpDataInputStream(
                Hex.decodeHex("01000100000100000001F4F5F663FCF1F2F3010203".toCharArray()), 0, 0);

        assertEquals(1, is.readUnsignedByte());
        assertEquals(1, is.readUnsignedInteger16());
        assertEquals(1, is.readUnsignedInteger24());
        assertEquals(1, is.readUnsignedInteger32());
        assertEquals("456", is.readEbcdicString(3));
        assertEquals("[]", is.readString(2, Charset.forName("ibm-273")));
        assertTrue(Arrays.equals(Hex.decodeHex("F1F2F3".toCharArray()), is.readBytes(3)));

        assertTrue(Arrays.equals(Hex.decodeHex("010203".toCharArray()), is.readRemainingBytes()));
        assertEquals(0, is.readRemainingBytes().length); // will return an empty array if no bytes left to read!

        // All bytes are consumed - reading even one more byte should throw an exception....
        try {
            is.readUnsignedByte();
            fail("read after the end of the stream...");
        } catch (final AfpException e) {
            assertTrue(e.getMessage().contains("0 bytes left to parse."));
        }
    }

    /**
     * Checks reading multiple values (staring at non-zero offset and some padding bytes at the end)
     */
    public void testReadMultipleValuesWithPaddingBytesAndNonZeroOffset() throws Exception {
        final AfpDataInputStream is = new AfpDataInputStream(Hex.decodeHex(
                "FFFFFF01000100000100000001FFFF".toCharArray()), 3, 2);

        assertEquals(1, is.readUnsignedByte());
        assertEquals(1, is.readUnsignedInteger16());
        assertEquals(1, is.readUnsignedInteger24());
        assertEquals(1, is.readUnsignedInteger32());

        // All bytes are consumed - reading even one more byte should throw an exception....
        try {
            is.readUnsignedByte();
            fail("read after the end of the stream...");
        } catch (final AfpException e) {
            assertTrue(e.getMessage().contains("0 bytes left to parse."));
        }
    }

}
