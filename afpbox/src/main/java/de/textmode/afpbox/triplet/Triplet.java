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

/**
 * A {@link Triplet} triplet is a self-identifying parameter that contains
 * three components: the length of the triplet, an ID identifying the triplet,
 * and the associated parameters.
 */
public abstract class Triplet {

    private final byte[] data;

    /**
     * The constructor just stores the raw data of the Triplet.
     *
     * @param data the raw data of the Structured Field
     */
    public Triplet(final byte[] data) {
        this.data = data;
    }

    /**
     * The constructor just stores the raw data of the Triplet.
     *
     * @param data the raw data of the Structured Field.
     * @param expectedLength complete expected length of the Triplet.
     *
     * @throws AfpException if the raw data has not the expected length.
     */
    public Triplet(final byte[] data, final int expectedLength) throws AfpException {
        this.data = data;

        if (data.length != expectedLength) {
            throw new AfpException(
                    "Triplet X'" + Integer.toHexString(data[1] & 0xFF) + "' has invalid length of "
                    + this.getData().length + " bytes (expected " + expectedLength + " bytes).");
        }
    }

    /**
     * Returns the raw data of the Triplet.
     *
     * @return the data of the Triplet
     */
    public final byte[] getData() {
        return this.data;
    }
}
