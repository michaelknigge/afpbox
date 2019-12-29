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

import de.textmode.afpbox.AfpException;
import de.textmode.afpbox.common.ByteUtils;

/**
 * The Set Text Color control sequence specifies a color attribute for the
 * foreground areas of the text presentation space.
 */
public final class SetExtendedTextColor extends PtocaControlSequence {

    /**
     * Constructs the {@link SetExtendedTextColor}.
     *
     * @param functionType  the integer value of the PTOCA control sequence function type.
     * @param data          the raw data of the PTOCA control sequence.
     */
    SetExtendedTextColor(final int functionType, final byte[] data) throws AfpException {
        super(functionType, data);

        if (this.getData().length < 14 || this.getData().length > 16) {
            throw new AfpException("PTOCA control sequence SEC has invalid length of " + this.getData().length + " bytes (expected 14-16 bytes)");
        }
    }

    /**
     * Returns the color space (0x01 == RGB, 0x02 == CMYK, 0x06 == Highlight,
     * 0x08 == CILAB, 0x40 == Standard OCA color space).
     *
     * @return the color space.
     */
    public int getColorSpace() {
        return ByteUtils.toUnsignedByte(this.getData(), 3);
    }

    /**
     * Returns the number of bits in color component 1.
     *
     * @return the number of bits in color component 1.
     */
    public int getNumberOfBitsInComponent1() {
        return ByteUtils.toUnsignedByte(this.getData(), 8);
    }

    /**
     * Returns the number of bits in color component 2.
     *
     * @return the number of bits in color component 2.
     */
    public int getNumberOfBitsInComponent2() {
        return ByteUtils.toUnsignedByte(this.getData(), 9);
    }

    /**
     * Returns the number of bits in color component 3.
     *
     * @return the number of bits in color component 3.
     */
    public int getNumberOfBitsInComponent3() {
        return ByteUtils.toUnsignedByte(this.getData(), 10);
    }

    /**
     * Returns the number of bits in color component 4.
     *
     * @return the number of bits in color component 4.
     */
    public int getNumberOfBitsInComponent4() {
        return ByteUtils.toUnsignedByte(this.getData(), 11);
    }

    /**
     * Returns the color value in the specified color space.
     *
     * @return the color value.
     */
    public byte[] getColorSpecifications() {
        final int len = this.getData().length - 12;
        final byte[] result = new byte[len];

        System.arraycopy(this.getData(), 12, result, 0, len);

        return result;
    }
}
