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
 * The Set Text Orientation control sequence establishes the I-direction and B-direction
 * for the subsequent text. This is a modal control sequence.
 */
public final class SetTextOrientation extends PtocaControlSequence {

    /**
     * Constructs the {@link SetTextOrientation}.
     *
     * @param functionType  the integer value of the PTOCA control sequence function type.
     * @param data          the raw data of the PTOCA control sequence.
     */
    SetTextOrientation(final int functionType, final byte[] data) throws AfpException {
        super(functionType, data, "STO", 6);
    }

    /**
     * Returns the I-Axis orientation.
     *
     * @return the I-Axis orientation.
     */
    public int getInlineAxisOrientation() {
        return ByteUtils.toUnsignedInteger16(this.getData(), 2);
    }

    /**
     * Returns the B-Axis orientation.
     *
     * @return the B-Axis orientation.
     */
    public int getBaselineAxisOrientation() {
        return ByteUtils.toUnsignedInteger16(this.getData(), 4);
    }
}
