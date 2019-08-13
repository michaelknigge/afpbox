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

import java.security.InvalidParameterException;

/**
 * The class {@link ByteUtils} contains various helper methods for dealing byte arrays.
 */
public final class ByteUtils {

    /**
     * Converts up to four bytes from a byte array to an integer (big endian format).
     *
     * @param src      the source array
     * @param srcPos   starting position in the source array
     * @param length   the number of array elements to be converted
     *
     * @return the integer value of the converted bytes.
     */
    public static int toInteger(final byte[] src, final int srcPos, final int length) {
        switch (length) {
        case 0:
            return 0;
        case 1:
            return src[srcPos] & 0xFF;
        case 2:
            return ((src[srcPos] & 0xFF) << 8)
                   + (src[srcPos + 1] & 0xFF);
        case 3:
            return ((src[srcPos] & 0xFF) << 16)
                   + ((src[srcPos + 1] & 0xFF) << 8)
                   + (src[srcPos + 2] & 0xFF);
        case 4:
            return ((src[srcPos] & 0xFF) << 24)
                   + ((src[srcPos + 1] & 0xFF) << 16)
                   + ((src[srcPos + 2] & 0xFF) << 8)
                   + (src[srcPos + 3] & 0xFF);
        default:
            throw new InvalidParameterException("Invalid length for toInteger(): " + length); //$NON-NLS-1$
        }
    }
}
