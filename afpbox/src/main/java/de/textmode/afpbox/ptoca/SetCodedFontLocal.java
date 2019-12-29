package de.textmode.afpbox.ptoca;

import de.textmode.afpbox.AfpException;
import de.textmode.afpbox.common.ByteUtils;

/**
 * The Set Coded Font Local control sequence activates a coded font and specifies
 * the character attributes to be used. This is a modal control sequence.
 */
public final class SetCodedFontLocal extends PtocaControlSequence {

    /**
     * Constructs the {@link SetCodedFontLocal}.
     *
     * @param functionType  the integer value of the PTOCA control sequence function type.
     * @param data          the raw data of the PTOCA control sequence.
     */
    SetCodedFontLocal(final int functionType, final byte[] data) throws AfpException {
        super(functionType, data, "SCFL", 3);
    }

    /**
     * Returns the local identifier.
     *
     * @return the local identifier.
     */
    public int getLocalIdentifier() {
        return ByteUtils.toUnsignedByte(this.getData(), 2);
    }
}
