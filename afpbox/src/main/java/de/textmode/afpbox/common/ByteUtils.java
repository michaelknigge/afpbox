package de.textmode.afpbox.common;

/**
 * The class {@link ByteUtils} contains various helper methods for dealing byte arrays.
 */
public final class ByteUtils {

    /**
     * Converts one bytes from a byte array to an unsigned integer.
     *
     * @return the unsigned 8-bit value of the converted byte.
     */
    public static int toUnsignedByte(final byte[] src, final int srcPos) {
        return src[srcPos] & 0xFF;
    }

    /**
     * Converts one bytes from a byte array to a signed integer.
     *
     * @return the signed 8-bit value of the converted byte.
     */
    public static int toByte(final byte[] src, final int srcPos) {
        return src[srcPos];
    }

    /**
     * Converts two bytes from a byte array to an unsigned integer (big endian format).
     *
     * @return the unsigned 16-bit value of the converted bytes.
     */
    public static int toUnsignedInteger16(final byte[] src, final int srcPos) {
        return ((src[srcPos] & 0xFF) << 8) | (src[srcPos + 1] & 0xFF);
    }

    /**
     * Converts two bytes from a byte array to a signed integer (big endian format).
     *
     * @return the signed 16-bit value of the converted bytes.
     */
    public static int toInteger16(final byte[] src, final int srcPos) {
        return (src[srcPos] << 8) | (src[srcPos + 1] & 0xFF);
    }

    /**
     * Converts three bytes from a byte array to an unsigned integer (big endian format).
     *
     * @return the unsigned 24-bit value of the converted bytes.
     */
    public static int toUnsignedInteger24(final byte[] src, final int srcPos) {
        return ((src[srcPos] & 0xFF) << 16) | ((src[srcPos + 1] & 0xFF) << 8) | (src[srcPos + 2] & 0xFF);
    }

    /**
     * Converts three bytes from a byte array to an signed integer (big endian format).
     *
     * @return the signed 24-bit value of the converted bytes.
     */
    public static int toInteger24(final byte[] src, final int srcPos) {
        return (src[srcPos] << 16) | ((src[srcPos + 1] & 0xFF) << 8) | (src[srcPos + 2] & 0xFF);
    }

    /**
     * Converts four bytes from a byte array to an unsigned long (big endian format).
     *
     * @return the unsigned 32-bit value of the converted bytes.
     */
    public static long toUnsignedInteger32(final byte[] src, final int srcPos) {
        return ((long) (src[srcPos] & 0xFF) << 24)
                | (long) (src[srcPos + 1] & 0xFF) << 16
                | (long) (src[srcPos + 2] & 0xFF) << 8
                | (long) src[srcPos + 3] & 0xFF;
    }

    /**
     * Converts four bytes from a byte array to an signed integer (big endian format).
     *
     * @return the signed 32-bit value of the converted bytes.
     */
    public static int toInteger32(final byte[] src, final int srcPos) {
        return (src[srcPos] << 24)
                | ((src[srcPos + 1] & 0xFF) << 16)
                | ((src[srcPos + 2] & 0xFF) << 8)
                | (src[srcPos + 3] & 0xFF);
    }
}
