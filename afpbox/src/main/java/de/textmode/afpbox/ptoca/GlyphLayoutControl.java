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

import java.nio.charset.StandardCharsets;

import de.textmode.afpbox.AfpException;
import de.textmode.afpbox.common.ByteUtils;

/**
 * The Glyph Layout Control control sequence marks the start of one or more glyph run
 * groupings that together render text using arrays of glyph identifiers and positions.
 */
public final class GlyphLayoutControl extends PtocaControlSequence {

    /**
     * Constructs the {@link GlyphLayoutControl}.
     *
     * @param functionType  the integer value of the PTOCA control sequence function type.
     * @param data          the raw data of the PTOCA control sequence.
     */
    GlyphLayoutControl(final int functionType, final byte[] data) throws AfpException {
        super(functionType, data);

        if (this.getData().length < 10) {
            throw new AfpException(
                    "PTOCA control sequence GLC has invalid length of "
                            + this.getData().length + " bytes (expected at least 10 bytes)");
        }
    }

    /**
     * Returns the advance along the current baseline after processing this GLC chain.
     *
     * @return advance along the current baseline.
     */
    public int getAdvance() {
        return ByteUtils.toInteger16(this.getData(), 2);
    }

    /**
     * Returns the Length of FONTOID (font object identifier) parameter.
     *
     * @return length of FONTOID (font object identifier) parameter.
     */
    public int getFontObjectIdentifierLength() {
        return ByteUtils.toUnsignedByte(this.getData(), 4);
    }

    /**
     * Returns the Length of FFONTNME (font name) parameter.
     *
     * @return length of FFONTNME (font name) parameter.
     */
    public int getFontNameLength() {
        return ByteUtils.toUnsignedByte(this.getData(), 5);
    }

    /**
     * Returns the font object identifier.
     *
     * @return the font object identifier.
     */
    public byte[] getFontObjectIdentifier() {
        if (this.getFontObjectIdentifierLength() == 0) {
            return ByteUtils.EMPTY_BYTE_ARRAY;
        }

        final int len = this.getFontObjectIdentifierLength();
        final byte[] result = new byte[len];

        System.arraycopy(this.getData(), 10, result, 0, len);

        return result;
    }

    /**
     * Returns the font name.
     *
     * @return the font name.
     */
    public String getFontName() {
        if (this.getFontNameLength() == 0) {
            return ByteUtils.EMPTY_STRING;
        }

        return new String(
                this.getData(),
                this.getFontObjectIdentifierLength() + 10,
                this.getFontNameLength(),
                StandardCharsets.UTF_16BE);
    }
}
