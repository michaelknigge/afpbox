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
public final class SetTextColor extends PtocaControlSequence {

    /**
     * Constructs the {@link SetTextColor}.
     *
     * @param functionType  the integer value of the PTOCA control sequence function type.
     * @param data          the raw data of the PTOCA control sequence.
     */
    SetTextColor(final int functionType, final byte[] data) throws AfpException {
        super(functionType, data);

        if (this.getData().length != 4 && this.getData().length != 5) {
            throw new AfpException("PTOCA control sequence STC has invalid length of " + this.getData().length + " bytes (expected 4 or 5 bytes)");
        }
    }

    /**
     * Returns the foreground color.
     *
     * @return the foreground color.
     */
    public int getForegroundColor() {
        return ByteUtils.toUnsignedInteger16(this.getData(), 2);
    }

    /**
     * Returns how the receiver should process colors that are syntactically valid but
     * not supported by the receiver. This parameter is retired.
     *
     * @return the value 0x00 or 0x01.
     */
    public int getPrecision() {
        if (this.getData().length == 4) {
            return 0x00;
        } else {
            return ByteUtils.toUnsignedByte(this.getData(), 4);
        }
    }
}
