package de.textmode.afpbox.common;

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
 * The class {@link ByteUtils} contains various helper methods for dealing byte arrays.
 */
public final class ByteUtils {

    /**
     * An empty byte array.
     */
    public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];

    /**
     * An empty int array.
     */
    public static final int[] EMPTY_INT_ARRAY = new int[0];

    /**
     * An empty String.
     */
    public static final String EMPTY_STRING = "";

    /**
     * Converts one bytes from a byte array to an unsigned integer.
     *
     * @param src     the source array.
     * @param srcPos  starting position in the source array.
     *
     * @return the unsigned 8-bit value of the converted byte.
     */
    public static int toUnsignedByte(final byte[] src, final int srcPos) {
        return src[srcPos] & 0xFF;
    }

    /**
     * Converts one bytes from a byte array to a signed integer.
     *
     * @param src     the source array.
     * @param srcPos  starting position in the source array.
     *
     * @return the signed 8-bit value of the converted byte.
     */
    public static int toByte(final byte[] src, final int srcPos) {
        return src[srcPos];
    }

    /**
     * Converts two bytes from a byte array to an unsigned integer (big endian format).
     *
     * @param src     the source array.
     * @param srcPos  starting position in the source array.
     *
     * @return the unsigned 16-bit value of the converted bytes.
     */
    public static int toUnsignedInteger16(final byte[] src, final int srcPos) {
        return ((src[srcPos] & 0xFF) << 8) | (src[srcPos + 1] & 0xFF);
    }

    /**
     * Converts two bytes from a byte array to a signed integer (big endian format).
     *
     * @param src     the source array.
     * @param srcPos  starting position in the source array.
     *
     * @return the signed 16-bit value of the converted bytes.
     */
    public static int toInteger16(final byte[] src, final int srcPos) {
        return (src[srcPos] << 8) | (src[srcPos + 1] & 0xFF);
    }

    /**
     * Converts three bytes from a byte array to an unsigned integer (big endian format).
     *
     * @param src     the source array.
     * @param srcPos  starting position in the source array.
     *
     * @return the unsigned 24-bit value of the converted bytes.
     */
    public static int toUnsignedInteger24(final byte[] src, final int srcPos) {
        return ((src[srcPos] & 0xFF) << 16) | ((src[srcPos + 1] & 0xFF) << 8) | (src[srcPos + 2] & 0xFF);
    }

    /**
     * Converts three bytes from a byte array to an signed integer (big endian format).
     *
     * @param src     the source array.
     * @param srcPos  starting position in the source array.
     *
     * @return the signed 24-bit value of the converted bytes.
     */
    public static int toInteger24(final byte[] src, final int srcPos) {
        return (src[srcPos] << 16) | ((src[srcPos + 1] & 0xFF) << 8) | (src[srcPos + 2] & 0xFF);
    }

    /**
     * Converts four bytes from a byte array to an unsigned long (big endian format).
     *
     * @param src     the source array.
     * @param srcPos  starting position in the source array.
     *
     * @return the unsigned 32-bit value of the converted bytes.
     */
    public static long toUnsignedInteger32(final byte[] src, final int srcPos) {
        return ((long) (src[srcPos] & 0xFF) << 24)
                | (long) (src[srcPos + 1] & 0xFF) << 16
                | (long) (src[srcPos + 2] & 0xFF) << 8
                | (long) src[srcPos + 3] & 0xFF;
    }

    /**
     * Converts four bytes from a byte array to an signed integer (big endian format).
     *
     * @param src     the source array.
     * @param srcPos  starting position in the source array.
     *
     * @return the signed 32-bit value of the converted bytes.
     */
    public static int toInteger32(final byte[] src, final int srcPos) {
        return (src[srcPos] << 24)
                | ((src[srcPos + 1] & 0xFF) << 16)
                | ((src[srcPos + 2] & 0xFF) << 8)
                | (src[srcPos + 3] & 0xFF);
    }
}
