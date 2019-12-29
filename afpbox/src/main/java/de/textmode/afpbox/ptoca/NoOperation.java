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
 * The No Operation control sequence has no effect on presentation.
 */
public final class NoOperation extends PtocaControlSequence {

    /**
     * Constructs the {@link NoOperation}.
     *
     * @param functionType  the integer value of the PTOCA control sequence function type.
     * @param data          the raw data of the PTOCA control sequence.
     */
    NoOperation(final int functionType, final byte[] data) throws AfpException {
        super(functionType, data);
    }

    /**
     * Returns the data that has no effect on presentation.
     *
     * @return the data that has no effect on presentation.
     */
    public byte[] getIgnoredData() {
        if (this.getLength() == 2) {
            return ByteUtils.EMPTY_BYTE_ARRAY;
        }

        final int len = this.getLength() - 2;
        final byte[] result = new byte[len];

        System.arraycopy(this.getData(), 2, result, 0, len);

        return result;
    }
}
