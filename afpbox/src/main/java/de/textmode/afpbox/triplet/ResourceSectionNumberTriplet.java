package de.textmode.afpbox.triplet;

import de.textmode.afpbox.AfpException;

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

/**
 * The Resource Section Number triplet specifies a coded font section number.
 * It may be used to select a single section of a double-byte coded font if
 * less than the entire double-byte coded font is required for processing.
 */
public final class ResourceSectionNumberTriplet extends Triplet {

    /**
     * Constructs the {@link ResourceSectionNumberTriplet}.
     *
     * @param data          the raw data of the Triplet.
     *
     * @throws AfpException if the triplet has not the expected length.
     */
    public ResourceSectionNumberTriplet(final byte[] data) throws AfpException {
        super(data, 3);
    }

    /**
     * Returns the resource section number.
     *
     * @return resource section number.
     */
    public short getResourceSectionNumber() {
        return (short) (this.getData()[2] & 0xFF);
    }
}
