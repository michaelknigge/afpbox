package de.textmode.afpbox.io;

/*
 * Copyright 2018 Michael Knigge
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

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

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

/**
 * The {@link AfpDataInputStream} is not really an DataInputStream, but it follows the
 * concept of the DataInputStream. It provides methods for reading native data types and
 * data types specific for AFP.
 */
public final class AfpDataInputStream {

    private static final Charset EBCDIC_INTERNATIONAL = Charset.forName("ibm-500");

    private final byte[] data;
    private int offset;
    private int bytesLeft;


    /**
     * Constructs an {@link AfpDataInputStream}.
     *
     * @param data the underlying byte array
     * @param startOffset the start offset (usually the first byte after the Structured field introducer)
     * @param paddingBytesToIgnore Padding bytes that shall be ignored
     */
    public AfpDataInputStream(final byte[] data, final int startOffset, final int paddingBytesToIgnore) {
        this.data = data;
        this.offset = startOffset;
        // TODO: Hmmmmmm... will this work under z/OS for RECFM=FB???? Maybe better to pass off + len!?
        this.bytesLeft = data.length - startOffset - paddingBytesToIgnore;
    }

    /**
     * Checks if enough bytes are available to read. If not an {@link AfpException} is thrown with
     * information that may help the user to validate the AFP structured field.
     *
     * @param length the required amount of bytes
     *
     * @throws AfpException if there are less then <code>length</code> bytes left to read.
     */
    private void checkForAvailableBytes(final int length) throws AfpException {
        if (length > this.bytesLeft) {
            throw new AfpException(
                    "Tried to parse " + length
                    + " bytes at offset " + this.offset
                    + " but there are only " + this.bytesLeft
                    + " bytes left to parse. Maybe the structured field is truncated.");
        }
    }

    /**
     * Reads and returns some bytes.
     *
     * @param length how many bytes should be read and returned
     *
     * @return the requested amount of bytes read.
     *
     * @throws AfpException if there are not enough bytes left to read.
     */
    public byte[] readBytes(final int length) throws AfpException {
        this.checkForAvailableBytes(length);

        final byte[] result = new byte[length];
        System.arraycopy(this.data, this.offset, result, 0, length);

        this.offset += length;
        this.bytesLeft -= length;

        return result;
    }

    /**
     * Reads and returns all remaining bytes.
     *
     * @return the remaining bytes. If no more bytes are available an empty byte array will be returned.
     */
    public byte[] readRemainingBytes() {

        final byte[] result = new byte[this.bytesLeft];
        System.arraycopy(this.data, this.offset, result, 0, this.bytesLeft);

        this.offset += this.bytesLeft;
        this.bytesLeft = 0;

        return result;
    }

    /**
     * Reads and returns some bytes and treads the bytes as an EBCDIC-International encoded String.
     *
     * @param length length of the string
     *
     * @return the String decoded from the read bytes.
     *
     * @throws AfpException if there are not enough bytes left to read.
     */
    public String readEbcdicString(final int length) throws AfpException {
        this.checkForAvailableBytes(length);

        final String result = EBCDIC_INTERNATIONAL.decode(ByteBuffer.wrap(this.data, this.offset, length)).toString();

        this.offset += length;
        this.bytesLeft -= length;

        return result;
    }

    /**
     * Reads and returns some bytes and treads the bytes as an encoded single byte character String.
     *
     * @param length length of the string
     * @param charset charset to be used for decoding the bytes
     *
     * @return the String decoded from the read bytes.
     *
     * @throws AfpException if there are not enough bytes left to read.
     */
    public String readString(final int length, final Charset charset) throws AfpException {
        this.checkForAvailableBytes(length);

        final String result = charset.decode(ByteBuffer.wrap(this.data, this.offset, length)).toString();

        this.offset += length;
        this.bytesLeft -= length;

        return result;
    }

    /**
     * Reads and returns one input byte. The byte is treated as an unsigned value.
     *
     * @return the unsigned 8-bit value read.
     *
     * @throws AfpException if there are not enough bytes left to parse.
     */
    public int readUnsignedByte() throws AfpException {
        this.checkForAvailableBytes(1);

        final int result = this.data[this.offset] & 0xFF;

        ++this.offset;
        --this.bytesLeft;

        return result;
    }

    /**
     * Reads and returns one input byte. The byte is treated as an signed value.
     *
     * @return the signed 8-bit value read.
     *
     * @throws AfpException if there are not enough bytes left to parse.
     */
    public int readByte() throws AfpException {
        this.checkForAvailableBytes(1);

        final int result = this.data[this.offset];

        ++this.offset;
        --this.bytesLeft;

        return result;
    }

    /**
     * Reads two input bytes and returns a integer value. The value is treated as an unsigned value.
     *
     * @return the unsigned 16-bit value read.
     *
     * @throws AfpException if there are not enough bytes left to parse.
     */
    public int readUnsignedInteger16() throws AfpException {
        this.checkForAvailableBytes(2);

        final int result = ((this.data[this.offset] & 0xFF) << 8) | (this.data[this.offset + 1] & 0xFF);

        this.offset += 2;
        this.bytesLeft -= 2;

        return result;
    }

    /**
     * Reads two input bytes and returns a integer value. The value is treated as an signed value.
     *
     * @return the signed 16-bit value read.
     *
     * @throws AfpException if there are not enough bytes left to parse.
     */
    public int readInteger16() throws AfpException {
        this.checkForAvailableBytes(2);

        final int result = (this.data[this.offset] << 8) | (this.data[this.offset + 1] & 0xFF);

        this.offset += 2;
        this.bytesLeft -= 2;

        return result;
    }

    /**
     * Reads three input bytes and returns a integer value. The value is treated as an unsigned value.
     *
     * @return the unsigned 24-bit value read.
     *
     * @throws AfpException if there are not enough bytes left to parse.
     */
    public int readUnsignedInteger24() throws AfpException {
        this.checkForAvailableBytes(3);

        final int result =
                ((this.data[this.offset] & 0xFF) << 16)
                | ((this.data[this.offset + 1] & 0xFF) << 8)
                | (this.data[this.offset + 2] & 0xFF);

        this.offset += 3;
        this.bytesLeft -= 3;

        return result;
    }

    /**
     * Reads three input bytes and returns a integer value. The value is treated as an signed value.
     *
     * @return the signed 24-bit value read.
     *
     * @throws AfpException if there are not enough bytes left to parse.
     */
    public int readInteger24() throws AfpException {
        this.checkForAvailableBytes(3);

        final int result =
                (this.data[this.offset] << 16)
                | ((this.data[this.offset + 1] & 0xFF) << 8)
                | (this.data[this.offset + 2] & 0xFF);

        this.offset += 3;
        this.bytesLeft -= 3;

        return result;
    }

    /**
     * Reads four input bytes and returns a long value. The value is treated as an unsigned value.
     *
     * @return the unsigned 32-bit value read.
     *
     * @throws AfpException if there are not enough bytes left to parse.
     */
    public long readUnsignedInteger32() throws AfpException {
        this.checkForAvailableBytes(4);

        final long result =
                ((long) (this.data[this.offset] & 0xFF) << 24)
                | (long) (this.data[this.offset + 1] & 0xFF) << 16
                | (long) (this.data[this.offset + 2] & 0xFF) << 8
                | (long) this.data[this.offset + 3] & 0xFF;

        this.offset += 4;
        this.bytesLeft -= 4;

        return result;
    }

    /**
     * Reads four input bytes and returns a integer value. The value is treated as an signed value.
     *
     * @return the signed 32-bit value read.
     *
     * @throws AfpException if there are not enough bytes left to parse.
     */
    public int readInteger32() throws AfpException {
        this.checkForAvailableBytes(4);

        final int result =
                (this.data[this.offset] << 24)
                | ((this.data[this.offset + 1] & 0xFF) << 16)
                | ((this.data[this.offset + 2] & 0xFF) << 8)
                | (this.data[this.offset + 3] & 0xFF);

        this.offset += 4;
        this.bytesLeft -= 4;

        return result;
    }
}
