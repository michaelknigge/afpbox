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
 * The Coded Graphic Character Set Global Identifier (CGCSGID) triplet is used to establish
 * the values of the code page and character set for interpretation of all structured field
 * parameters having a CHAR data type, such as name parameters, except where such parameters
 * define a fixed encoding.
 */
public final class CodedGraphicCharacterSetGlobalIdentifierTriplet extends Triplet {

    /**
     * Constructs the {@link CodedGraphicCharacterSetGlobalIdentifierTriplet}.
     *
     * @param data          the raw data of the Triplet.
     *
     * @throws AfpException if the triplet has not the expected length.
     */
    public CodedGraphicCharacterSetGlobalIdentifierTriplet(final byte[] data) throws AfpException {
        super(data, 6);
    }

    /**
     * Returns the Graphic Character Set Global Identifier.
     *
     * @return Graphic Character Set Global Identifier.
     */
    public int getGraphicCharacterSetGlobalIdentifier() {
        return ByteUtils.toUnsignedInteger16(this.getData(), 2);
    }

    /**
     * Returns the Code Page Global Identifier. Note that the returned value is
     * valid only if {@link #getGraphicCharacterSetGlobalIdentifier()} returns
     * a non-zero value.
     *
     * @return Code Page Global Identifier.
     */
    public int getCodePageGlobalIdentifier() {
        return ByteUtils.toUnsignedInteger16(this.getData(), 4);
    }

    /**
     * Returns the Coded Character Set Identifier. Note that the returned value is
     * valid only if {@link #getGraphicCharacterSetGlobalIdentifier()} returns zero.
     *
     * @return Coded Character Set Identifier.
     */
    public int getCodedCharacterSetIdentifier() {
        return ByteUtils.toUnsignedInteger16(this.getData(), 4);
    }
}
