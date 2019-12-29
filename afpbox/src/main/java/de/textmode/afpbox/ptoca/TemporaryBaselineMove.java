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
 * The Temporary Baseline Move control sequence changes the position of the baseline
 * without changing the established baseline.
 */
public final class TemporaryBaselineMove extends PtocaControlSequence {

    /**
     * Constructs the {@link TemporaryBaselineMove}.
     *
     * @param functionType  the integer value of the PTOCA control sequence function type.
     * @param data          the raw data of the PTOCA control sequence.
     */
    TemporaryBaselineMove(final int functionType, final byte[] data) throws AfpException {
        super(functionType, data);

        if (this.getData().length != 3 && this.getData().length != 4 && this.getData().length != 6) {
            throw new AfpException("PTOCA control sequence TBM has invalid length of " + this.getData().length + " bytes (expected 3, 4 or 6 bytes)");
        }
    }

    /**
     * Returns the direction of the change.
     *
     * @return the direction of the change.
     */
    public int getDirection() {
        return ByteUtils.toUnsignedByte(this.getData(), 2);
    }

    /**
     * Returns the method by which the receiver exhibits the change in the baseline coordinate.
     *
     * @return the method by which the receiver exhibits the change in the baseline coordinate.
     */
    public int getPrecision() {
        if (this.getData().length == 3) {
            return 0; // The PTOCA default value for PRECSION is zero.
        } else {
            return ByteUtils.toUnsignedByte(this.getData(), 3);
        }
    }

    /**
     * Returns a positive number expressed in measurement units. The range for this parameter
     * assumes a measurement unit of 1/1440 inch.
     *
     * @return a positive number expressed in measurement units. If this control sequence does not
     *     specify a temporary baseline increment the special value -1 is returned.
     */
    public int getTemporaryBaselineIncrement() {
        if (this.getData().length != 6) {
            return -1;
        } else {
            return ByteUtils.toInteger16(this.getData(), 4);
        }
    }
}
