package de.textmode.afpbox.triplet;

/*
 * Copyright 2020 Michael Knigge
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
 * The Fully Qualified Name triplet enables the identification and referencing
 * of objects using Global Identifiers (GIDs).
 */
public final class FullyQualifiedNameTriplet extends Triplet {

    /**
     * Constructs the {@link FullyQualifiedNameTriplet}.
     *
     * @param data          the raw data of the Triplet.
     *
     * @throws AfpException if the triplet has not the expected length.
     */
    public FullyQualifiedNameTriplet(final byte[] data) throws AfpException {
        super(data, 5, 254);
    }

    /**
     * Returns the fully qualified name type.
     *
     * @return fully qualified name type.
     */
    public int getFullyQualifiedNameType() {
        return ByteUtils.toUnsignedByte(this.getData(),  2);
    }

    /**
     * Returns the fully qualified name format.
     *
     * @return fully qualified name format.
     */
    public int getFullyQualifiedNameFormat() {
        return ByteUtils.toUnsignedByte(this.getData(),  3);
    }

    /**
     * Returns the fully qualified name.
     *
     * @return fully qualified name.
     */
    public byte[] getFullyQualifiedName() {
        final byte[] data = this.getData();
        final byte[] result = new byte[data.length - 4];
        System.arraycopy(data, 4, result, 0, result.length);

        return result;
    }
}
