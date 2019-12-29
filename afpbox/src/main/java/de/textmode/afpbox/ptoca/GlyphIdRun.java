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
 * The Glyph ID Run control sequence specifies an array of glyph IDs from the current TrueType/OpenType font.
 */
public final class GlyphIdRun extends PtocaControlSequence {

    /**
     * Constructs the {@link GlyphIdRun}.
     *
     * @param functionType  the integer value of the PTOCA control sequence function type.
     * @param data          the raw data of the PTOCA control sequence.
     */
    GlyphIdRun(final int functionType, final byte[] data) throws AfpException {
        super(functionType, data);

        if (this.getData().length < 4 || (this.getData().length & 0x01) == 1) {
            throw new AfpException(
                    "PTOCA control sequence GIR has invalid length of "
                            + this.getData().length + " bytes (expected at least 4 bytes, only even values)");
        }
    }

    /**
     * Returns a sequence of glyph ids in the current font or in a font linked to the current font.
     *
     * @return a sequence of glyph ids in the current font or in a font linked to the current font.
     */
    public int[] getGlyphId() {

        if (this.getData().length == 4) {
            return ByteUtils.EMPTY_INT_ARRAY;
        }

        final int max = (this.getData().length - 4) / 2;
        final int[] result = new int[max];

        for (int ix = 0, off = 4; ix < max; ++ix, off += 2) {
            result[ix] = ByteUtils.toUnsignedInteger16(this.getData(), off);
        }

        return result;
    }
}
