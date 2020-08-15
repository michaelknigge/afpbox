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

import java.util.ArrayList;
import java.util.List;

import de.textmode.afpbox.AfpException;
import de.textmode.afpbox.io.AfpDataInputStream;

/**
 * The {@link TripletParser} reads {@link Triplet Triplet(s)} from a byte array.
 */
public final class TripletParser {

    private TripletParser() {
    }

    /**
     * Parses the given {@link Triplet Triplet(s)}.
     *
     * @param data     the Triplets
     *
     * @throws AfpException if a Triplet is corrupt / truncated.
     */
    public static List<Triplet> parse(final byte[] data) throws AfpException {

        final List<Triplet> triplets = new ArrayList<>();
        final AfpDataInputStream reader = new AfpDataInputStream(data);

        while (reader.bytesAvailable() > 0) {
            // TODO: We should not throw an exception. We should create a
            // "FaultyTriplet" with the rest of the remaining data. So the caller
            // can at least get the previous triplets from the returned list...
            final int length = reader.readUnsignedByte();
            if (length < 2) {
                throw new AfpException("A length value " + length + " was read for a triplet.");
            }

            final int tripletId = reader.readUnsignedByte();
            final byte[] triplet = reader.readBytes(reader.tell() - 2, length);

            // TODO: If a exception is thrown, create an FaultyTriplet - same as in PtocaParser
            triplets.add(TripletFactory.createFor(tripletId, triplet));
        }

        return triplets;
    }
}
