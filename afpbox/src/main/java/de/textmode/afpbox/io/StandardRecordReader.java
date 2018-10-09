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
 * The {@link StandardRecordReader} is a {@link RecordReader} that assumes that every
 * record starts with x'5A'. Trailing data (i. e. CR LF) at the end of a record is
 * removed.
 */
public final class StandardRecordReader implements RecordReader {

    private final InputStream is;
    private long offset;

    /**
     * Constructor of the {@link StandardRecordReader} that takes an {@link InputStream}.
     *
     * @param is {@link InputStream} where data is read from. The {@link InputStream} is not closed
     *     after EOF.
     */
    public StandardRecordReader(final InputStream is) {
        this.is = is;
        this.offset = -1;
    }

    @Override
    public Record read() throws IOException {
        // PAY ATTENTION
        // PAY ATTENTION when we have to use "this.is.read()" and/or "this.readNextByte()".
        // PAY ATTENTION
        if (this.offset == -1) {
            final int read = this.is.read();
            if (read == -1) {
                return null;
            } else if (read != 0x5A) {
                throw new IOException("The first record does not start with X'5A'.");
            } else {
                this.offset = 0;
            }
        } else {
            if (this.skipToStartOfRecord() == -1) {
                return null;
            }
        }

        final int firstLenghByte = this.is.read();
        if (firstLenghByte == -1) {
            throw new IOException(
                    "Unexpected end of stream while reading the first length byte of "
                    + "record starting at offset " + this.offset);
        }

        final int secondLenghByte = this.is.read();
        if (secondLenghByte == -1) {
            throw new IOException(
                    "Unexpected end of stream while reading the second length byte of "
                    + "record starting at offset " + this.offset);
        }

        final int length = 1 + (firstLenghByte << 8) + secondLenghByte;
        if (length < 3) {
            throw new IOException("The length of record at offset " + this.offset + " is invalid.");
        }

        final byte[] record = new byte[length];

        record[0] = 0x5A;
        record[1] = (byte) (firstLenghByte & 0xFF);
        record[2] = (byte) (secondLenghByte & 0xFF);

        this.readIntoBuffer(record, 3, length - 3);

        final Record result = new Record(record, this.offset);

        this.offset += length;

        // TODO: support Structured Field Introducer Extension
        // TODO: support Structured Field Segmentation
        // TODO: support Structured Field Padding

        return result;
    }

    private int skipToStartOfRecord() throws IOException {
        while (true) {
            final int readByte = this.readNextByte();
            if (readByte == -1 || readByte == 0x5A) {
                return readByte;
            }
        }
    }

    private int readNextByte() throws IOException {
        final int readByte = this.is.read();
        if (readByte != -1) {
            ++this.offset;
        }

        return readByte;
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
