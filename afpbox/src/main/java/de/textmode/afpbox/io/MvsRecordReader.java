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

import java.io.IOException;
import java.io.InputStream;

/**
 * The {@link MvsRecordReader} is a {@link RecordReader} that assumes that every
 * record starts with a so called RDW (Record Descriptor Word). The first two bytes
 * specify the length of the record (including the four bytes of the RDW) and the
 * next two bytes should be x'0000' (they are used for so called "spanned records"
 * under MVS).
 *
 * <p>This record format is used under MVS (aka OS/390 aka z/OS). You can find the
 * documentation here:
 *
 * <p>https://www.ibm.com/support/knowledgecenter/en/SSLTBW_2.2.0/com.ibm.zos.v2r2.idad400/d4356.htm
 */
public final class MvsRecordReader implements RecordReader {

    private final InputStream is;
    private long offset;

    /**
     * Constructor of the {@link MvsRecordReader} that takes an {@link InputStream}.
     *
     * @param is {@link InputStream} where data is read from. The {@link InputStream} is not closed
     *     after EOF.
     */
    public MvsRecordReader(final InputStream is) {
        this.is = is;
        this.offset = 0;
    }

    @Override
    public Record read() throws IOException {
        final int firstLenghByte = this.is.read();
        if (firstLenghByte == -1) {
            return null;
        }

        final int secondLenghByte = this.is.read();
        if (secondLenghByte == -1) {
            throw new IOException(
                    "Unexpected end of stream while reading the second length byte of "
                    + "record starting at offset " + this.offset);
        }

        final int length = (firstLenghByte << 8) + secondLenghByte;
        if (length < 4 || length > 32760) {
            throw new IOException("The length of record at offset " + this.offset + " is invalid.");
        }

        // Skip 3rd byte of the RDW....
        if (this.is.read() == -1) {
            throw new IOException(
                    "Unexpected end of stream while reading the RDW (byte 3) of "
                    + "record starting at offset " + this.offset);
        }

        // Skip 4th byte of the RDW....
        if (this.is.read() == -1) {
            throw new IOException(
                    "Unexpected end of stream while reading the RDW (byte 4) of "
                    + "record starting at offset " + this.offset);
        }

        final byte[] record = new byte[length - 4];

        this.readIntoBuffer(record, 0, length - 4);

        final Record result = new Record(record, this.offset);

        this.offset += length;

        return result;
    }

    private void readIntoBuffer(final byte[] buffer, final int offset, final int length) throws IOException {
        int bytesLeft = length;
        int bytesRead = 0;

        while (bytesLeft > 0) {
            final int read = this.is.read(buffer, offset + bytesRead, bytesLeft);
            if (read == -1) {
                throw new IOException(
                        "Unexpected end of stream while reading record starting at offset " + this.offset);
            }

            bytesLeft -= read;
            bytesRead += read;
        }
    }
}
