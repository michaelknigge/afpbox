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
 * The Set Baseline Increment control sequence specifies the increment to be added to the
 * current baseline coordinate when a Begin Line control sequence is executed. This is a
 * modal control sequence.
 */
public final class SetBaselineIncrement extends PtocaControlSequence {

    /**
     * Constructs the {@link SetBaselineIncrement}.
     *
     * @param functionType  the integer value of the PTOCA control sequence function type.
     * @param data          the raw data of the PTOCA control sequence.
     */
    SetBaselineIncrement(final int functionType, final byte[] data) throws AfpException {
        super(functionType, data, "SBI", 4);
    }

    /**
     * Returns a positive binary number expressed in measurement units. The range for this
     * parameter assumes a measurement unit of 1/1440 inch.
     *
     * @return positive binary number expressed in measurement units.
     */
    public int getIncrement() {
        return ByteUtils.toInteger16(this.getData(), 2);
    }
}
