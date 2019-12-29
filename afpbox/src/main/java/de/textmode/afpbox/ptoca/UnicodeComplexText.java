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
 * The Unicode Complex Text (UCT) control sequence marks the start of a sequence of Unicode code points.
 */
public final class UnicodeComplexText extends PtocaControlSequence {

    /**
     * Constructs the {@link UnicodeComplexText}.
     *
     * @param functionType  the integer value of the PTOCA control sequence function type.
     * @param data          the raw data of the PTOCA control sequence.
     */
    UnicodeComplexText(final int functionType, final byte[] data) throws AfpException {
        super(functionType, data, "UCT", 16);
    }

    /**
     * Returns the UCT version level.
     *
     * @return UCT version level.
     */
    public int getVersionLevel() {
        return ByteUtils.toUnsignedByte(this.getData(), 2);
    }

    /**
     * Returns the length of complex text data that follows this control sequence.
     *
     * @return length of complex text data that follows this control sequence.
     */
    public int getComplexTextLength() {
        return ByteUtils.toUnsignedInteger16(this.getData(), 4);
    }

    /**
     * Returns the complext text processing control flags.
     *
     * @return complext text processing control flags.
     */
    public int getComplexTextProcessingControlFlags() {
        return ByteUtils.toUnsignedByte(this.getData(), 6);
    }

    /**
     * Returns the bidi layout processing control code.
     *
     * @return Bidi layout processing control code.
     */
    public int getBidiLayoutProcessingControlCode() {
        return ByteUtils.toUnsignedByte(this.getData(), 8);
    }

    /**
     * Returns the glyph processing control code.
     *
     * @return glyph processing control code.
     */
    public int getGlyphProcessingControlCode() {
        return ByteUtils.toUnsignedByte(this.getData(), 9);
    }

    /**
     * Returns the alternate current inline position.
     *
     * @return alternate current inline position.
     */
    public int getAlternateCurrentInlinePosition() {
        return ByteUtils.toInteger16(this.getData(), 14);
    }
}
