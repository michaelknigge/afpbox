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

/**
 * The {@link UnknownTriplet} is created for all Triplets we don't know (or
 * currently don't support).
 */
public final class UnknownTriplet extends Triplet {

    /**
     * Constructs the {@link UnknownTriplet}.
     *
     * @param data          the raw data of the Triplet.
     */
    public UnknownTriplet(final byte[] data) {
        super(data);
    }
}
