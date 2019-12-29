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
 * The Repeat String control sequence contains a string of graphic character
 * code points that is repeated on the current line.
 */
public final class RepeatString extends PtocaControlSequence {

    /**
     * Constructs the {@link RepeatString}.
     *
     * @param functionType  the integer value of the PTOCA control sequence function type.
     * @param data          the raw data of the PTOCA control sequence.
     */
    RepeatString(final int functionType, final byte[] data) throws AfpException {
        super(functionType, data);

        if (this.getData().length < 4) {
            throw new AfpException("PTOCA control sequence RPS has invalid length of " + this.getData().length + " bytes (expected at least 4 bytes)");
        }
    }

    /**
     * Returns the length of the repeated string.
     *
     * @return the length of the repeated string.
     */
    public int getRepeatLength() {
        return ByteUtils.toUnsignedInteger16(this.getData(), 2);
    }

    /**
     * Returns the string (code ponits) to be repeated.
     *
     * @return the string (code ponits) to be repeated.
     */
    public byte[] getRepeatData() {
        if (this.getData().length == 4) {
            return ByteUtils.EMPTY_BYTE_ARRAY;
        }

        final int len = this.getLength() - 4;
        final byte[] result = new byte[len];

        System.arraycopy(this.getData(), 4, result, 0, len);

        return result;
    }
}
