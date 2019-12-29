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
 * The Set Inline Margin control sequence specifies the position of an inline margin.
 * This is a modal control sequence.
 */
public final class SetInlineMargin extends PtocaControlSequence {

    /**
     * Constructs the {@link SetInlineMargin}.
     *
     * @param functionType  the integer value of the PTOCA control sequence function type.
     * @param data          the raw data of the PTOCA control sequence.
     */
    SetInlineMargin(final int functionType, final byte[] data) throws AfpException {
        super(functionType, data, "SIM", 4);
    }

    /**
     * Returns the displacement from the B-axis in the I-direction that is to be applied when
     * a Begin Line control sequence is processed.
     *
     * @return the displacement from the B-axis in the I-direction.
     */
    public int getDisplacement() {
        return ByteUtils.toUnsignedInteger16(this.getData(), 2);
    }
}
