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
 * The Underscore control sequence identifies text fields that are to be underscored.
 */
public final class Underscore extends PtocaControlSequence {

    /**
     * Constructs the {@link Underscore}.
     *
     * @param functionType  the integer value of the PTOCA control sequence function type.
     * @param data          the raw data of the PTOCA control sequence.
     */
    Underscore(final int functionType, final byte[] data) throws AfpException {
        super(functionType, data, "USC", 3);
    }

    /**
     * Returns which controlled inline white space within the underscore field is to be underscored.
     *
     * @return which controlled inline white space within the underscore field is to be underscored.
     */
    public int getBypassIdentifiers() {
        return ByteUtils.toUnsignedByte(this.getData(), 2);
    }
}
