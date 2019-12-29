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
 * The Set Intercharacter Adjustment control sequence specifies additional increment
 * or decrement between graphic characters. This is a modal control sequence.
 */
public final class SetIntercharacterAdjustment extends PtocaControlSequence {

    /**
     * Constructs the {@link SetIntercharacterAdjustment}.
     *
     * @param functionType  the integer value of the PTOCA control sequence function type.
     * @param data          the raw data of the PTOCA control sequence.
     */
    SetIntercharacterAdjustment(final int functionType, final byte[] data) throws AfpException {
        super(functionType, data);

        if (this.getData().length != 4 && this.getData().length != 5) {
            throw new AfpException(
                    "PTOCA control sequence SIA has invalid length of "
                            + this.getData().length + " bytes (expected 4 or 5 bytes)");
        }
    }

    /**
     * Returns a positive binary number expressed in measurement units. The range for
     * this parameter assumes a measurement unit of 1/1440 inch.
     *
     * @return a positive binary number expressed in measurement units.
     */
    public int getAdjustment() {
        return ByteUtils.toInteger16(this.getData(), 2);
    }

    /**
     * Returns the direction in which the intercharacter adjustment is to be applied.
     *
     * @return the direction in which the intercharacter adjustment is to be applied.
     */
    public int getDirection() {
        if (this.getData().length == 4) {
            return 0x00; // PTOCA default value for direction
        } else {
            return ByteUtils.toUnsignedByte(this.getData(), 4);
        }
    }
}
