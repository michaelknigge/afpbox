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
 * The {@link CommentTriplet} is used to include comments for documentation
 * purposes within a structured field.
 */
public final class CommentTriplet extends Triplet {

    /**
     * Constructs the {@link CommentTriplet}.
     *
     * @param data          the raw data of the Triplet.
     *
     * @throws AfpException if the triplet has not the expected length.
     */
    public CommentTriplet(final byte[] data) throws AfpException {
        super(data);

        if (data.length < 3) {
            throw new AfpException(
                    "Triplet X'" + Integer.toHexString(data[1] & 0xFF) + "' has invalid length of "
                    + this.getData().length + " bytes (expected at least 3 bytes).");
        }
    }

    /**
     * Returns the text of the comment.
     *
     * @return text of the comment.
     */
    public byte[] getComment() {
        final byte[] data = this.getData();
        final byte[] result = new byte[data.length - 2];
        System.arraycopy(data, 2, result, 0, result.length);

        return result;
    }
}
