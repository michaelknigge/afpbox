package de.textmode.afpbox;

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

import java.io.EOFException;
import java.io.IOException;

import de.textmode.afpbox.io.AfpDataInputStream;
import de.textmode.afpbox.ptoca.PtocaControlSequenceFactory;

/**
 * The {@link PtocaParser} reads PTOCA control sequences from a byte array.
 */
public final class PtocaParser {

    private static final int CONTROL_SEQUENCE_PREFIX = 0x2B;
    private static final int CONTROL_SEQUENCE_CLASS = 0xD3;


    private PtocaParser() {
    }

    /**
     * Parses the given PTOCA control sequences. For each read control sequence
     * the {@link PtocaControlSequenceHandler} is invoked.
     *
     * @param data     the PTOCA control sequences
     * @param handler  the {@link RecordHandler} that will be invoked for the read records.
     *
     * @throws EOFException if the end of the stream was reached unexpectedly.
     * @throws IOException if an I/O error occurs.
     * @throws AfpException if the PTOCA data is corrupt / truncated.
     */
    public static void parse(
            final byte[] data,
            final PtocaControlSequenceHandler handler) throws IOException, AfpException {

        final AfpDataInputStream reader = new AfpDataInputStream(data);
        int codePointsOffset = -1;
        int codePointsLength = 0;

        while (reader.bytesAvailable() > 0) {

            // The Control Sequence Prefix marks the beginning of an unchained control sequence.
            // This parameter causes a change in the mode of interpretation of a presentation text stream.
            // When a Control Sequence Prefix is encountered, the bytes immediately following are interpreted
            // as a control sequence rather than as code points. This mode of interpretation continues
            // until the control sequence or control sequence chain is terminated.
            final int readByte = reader.readUnsignedByte();
            if (readByte == CONTROL_SEQUENCE_PREFIX) {
                if (codePointsLength > 0) {
                    handler.handleCodePoints(data, codePointsOffset, codePointsLength);
                    codePointsOffset = -1;
                    codePointsLength = 0;
                }
                if (reader.bytesAvailable() == 0) {
                    throw new EOFException(
                            "The PTOCA stream unexpectedly ends after a control sequence prefix byte.");
                }
                if (reader.readUnsignedByte() == CONTROL_SEQUENCE_CLASS) {
                    if (reader.bytesAvailable() < 2) {
                        throw new EOFException(
                                "The PTOCA stream seems to be truncated after a control sequence class byte.");
                    }
                    parseControlSequence(data, reader, handler);
                } else {
                    throw new AfpException(
                            "A control sequence class byte (value 0xD3) was "
                            + "expected but a byte with value " + readByte + " (decimal) was read.");
                }
            } else {
                if (codePointsOffset == -1) {
                    codePointsOffset = reader.tell() - 1;
                }
                ++codePointsLength;
            }
        }

        if (codePointsLength > 0) {
            handler.handleCodePoints(data, codePointsOffset, codePointsLength);
        }
    }

    private static void parseControlSequence(
            final byte[] ptocaData,
            final AfpDataInputStream reader,
            final PtocaControlSequenceHandler handler) throws IOException, AfpException {

        boolean chainedSequence = true;
        while (chainedSequence && reader.bytesAvailable() > 0) {
            final int length = reader.readUnsignedByte();
            if (length < 2) {
                throw new AfpException("A length value " + length + " was read for an PTOCA control sequence.");
            }

            final int functionType = reader.readUnsignedByte();
            final byte[] data = reader.readBytes(reader.tell() - 2, length);

            chainedSequence = (data[1] & 0x01) == 1;

            if (handler.handleControSequence(functionType, ptocaData, reader.tell() - length)) {
                handler.handleControSequence(PtocaControlSequenceFactory.createFor(functionType, data));
            }
        }
    }
}
