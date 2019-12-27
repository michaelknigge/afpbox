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
 * The Absolute Move Inline control sequence moves the inline coordinate relative to the B-axis.
 */
public final class AbsoluteMoveInline extends PtocaControlSequence {

    /**
     * Constructs the {@link AbsoluteMoveInline}.
     *
     * @param functionType  the integer value of the PTOCA control sequence function type.
     * @param data          the raw data of the PTOCA control sequence.
     */
    AbsoluteMoveInline(final int functionType, final byte[] data) throws AfpException {
        super(functionType, data, "AMI", 4);
    }

    /**
     * Returns the displacement in the I-direction from the B-axis of the object.
     *
     * @return the displacement in the I-direction from the B-axis of the object.
     */
    public int getDisplacement() {
        return ByteUtils.toUnsignedInteger16(this.getData(), 2);
    }
}
