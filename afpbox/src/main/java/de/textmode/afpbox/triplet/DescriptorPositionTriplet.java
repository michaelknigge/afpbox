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
 * The Descriptor Position triplet is used to associate an Object Area Position structured field
 * with an Object Area Descriptor structured field.
 */
public final class DescriptorPositionTriplet extends Triplet {

    /**
     * Constructs the {@link DescriptorPositionTriplet}.
     *
     * @param data          the raw data of the Triplet.
     *
     * @throws AfpException if the triplet has not the expected length.
     */
    public DescriptorPositionTriplet(final byte[] data) throws AfpException {
        super(data, 3);
    }

    /**
     * Returns the identifier of the Object Area Position structured field.
     *
     * @return identifier of the Object Area Position structured field.
     */
    public short getDescriptorPositionId() {
        return this.getData()[2];
    }
}
