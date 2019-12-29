package de.textmode.afpbox.ptoca;

import de.textmode.afpbox.AfpException;
import de.textmode.afpbox.common.ByteUtils;

/**
 * The Repeat String control sequence contains a string of graphic character
 * code points that is repeated on the current line.
 */
public final class RepeatString extends PtocaControlSequence {

    /**
     * Constructs the {@link RepeatString}.
     *
     * @param functionType  the integer value of the PTOCA control sequence function type.
     * @param data          the raw data of the PTOCA control sequence.
     */
    RepeatString(final int functionType, final byte[] data) throws AfpException {
        super(functionType, data);

        if (this.getData().length < 4) {
            throw new AfpException("PTOCA control sequence RPS has invalid length of " + this.getData().length + " bytes (expected at least 4 bytes)");
        }
    }

    /**
     * Returns the length of the repeated string.
     *
     * @return the length of the repeated string.
     */
    public int getRepeatLength() {
        return ByteUtils.toUnsignedInteger16(this.getData(), 2);
    }

    /**
     * Returns the string (code ponits) to be repeated.
     *
     * @return the string (code ponits) to be repeated.
     */
    public byte[] getRepeatData() {
        if (this.getData().length == 4) {
            return ByteUtils.EMPTY_BYTE_ARRAY;
        }

        final int len = this.getLength() - 4;
        final byte[] result = new byte[len];

        System.arraycopy(this.getData(), 4, result, 0, len);

        return result;
    }
}
