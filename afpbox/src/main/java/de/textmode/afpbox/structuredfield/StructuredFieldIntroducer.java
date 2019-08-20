package de.textmode.afpbox.structuredfield;

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

import de.textmode.afpbox.AfpException;
import de.textmode.afpbox.common.ByteUtils;
import de.textmode.afpbox.io.Record;

/**
 * The {@link StructuredFieldIntroducer} contains the Structured Field Introducer that follows the 0x5A
 * control character of an AFP record.
 */
public final class StructuredFieldIntroducer {

    private static final int BIT0 = 0x80; // 1000 0000
    private static final int BIT2 = 0x20; // 0010 0000
    private static final int BIT4 = 0x08; // 0000 1000

    private static final int OFFSET_TO_FLAG_BYTE = 6;
    private static final int OFFSET_TO_SFID = 3;

    private static final int STRUCTURED_FIELD_INTRODUCER_LENGTH = 7;

    private final Record record;
    private final byte flagByte;
    private final int sfid;

    /**
     * Constructs a {@code StructuredFieldIntroducer} from a read {@link Record}.
     *
     * @param record   the read {@link Record}.
     */
    public StructuredFieldIntroducer(final Record record) {
        this.record = record;

        // It is very very likely that a method is called that requires the flag byte or the Structured Field
        // Identifier. So we determine them here to get a better performance and more readable code...
        final byte[] data = record.getData();

        // We assume that the raw record data starts with the control character 0x5A. Also note that
        // the backed raw record data may be longer than the structured field! This may happen under
        // z/OS if reading from a MVS Data Set with a fixed record format...
        assert data[0] == 0x5A;

        this.flagByte = data[OFFSET_TO_FLAG_BYTE];
        this.sfid = ByteUtils.toInteger(data, OFFSET_TO_SFID, 3);
    }

    /**
     * Returns the Structured Field Identifier (SFID). Constants for every known SFID can be found in the helper class
     * {@link de.textmode.afpbox.common.StructuredFieldIdentifier}.
     *
     * @return the Structured Field Identifier (SFID).
     */
    public int getStructuredFieldIdentifier() {
        return this.sfid;
    }

    /**
     * Returns <code>true</code> if the Structured Field Introducer has an extension. If an extension
     * is present, the Structured Field Introducer can be up to 255 bytes long. The actual length
     * can be get by invoking {@link #getStructuredFieldIntroducerLength}.
     *
     * @return <code>true</code> if the Structured Field Introducer has an Extension.
     */
    public boolean hasStructuredFieldIntroducerExtension() {
        return (this.flagByte & BIT0) != 0;
    }

    /**
     * Returns <code>true</code> if the Structured Field has been segmented in multiple Structured
     * Fields. Segmenting normally only occurs for those structured fields that contain OCA data.
     *
     * @return <code>true</code> if the Structured Field has been segmented.
     */
    public boolean isSegmented() {
        return (this.flagByte & BIT2) != 0;
    }

    /**
     * Returns <code>true</code> if the Structured Field contains padding data. Padding bytes may be
     * used by an application to extend the physical length of a structured field.
     *
     * @return <code>true</code> if the Structured Field contains padding data.
     */
    public boolean hasPaddingData() {
        return (this.flagByte & BIT4) != 0;
    }

    /**
     * Returns the length of the padding data. If no padding data is present zero is returned.
     *
     * @return length of the padding data of zero if no padding data is present.
     *
     * @throws AfpException if the record specifies an invalid number of padding bytes.
     */
    public int getPaddingDataLength() throws AfpException {
        if (!this.hasPaddingData()) {
            return 0;
        }

        // If the last byte of the record contains 0x00, then the two bytes before this 0x00 contain
        // the lengths of the padding bytes...
        final int paddingLength;

        final byte[] data = this.record.getData();
        final int fieldLength = this.getStructuredFieldLength();

        final int lastByte = ByteUtils.toInteger(data, fieldLength, 1);

        if (lastByte == 0x00) {
            paddingLength = ByteUtils.toInteger(data, fieldLength - 2, 2);

            if (paddingLength < 3 || paddingLength > 32759) {
                throw new AfpException(
                        "Record at offset " + this.record.getOffset()
                        + " specifies an invalid length of padding bytes: " + paddingLength);
            }
        } else {
            paddingLength = lastByte;
        }

        return paddingLength;
    }

    /**
     * Returns the length of the Structured Field Introducer. The regular length of the Structured Field
     * Introducer is seven bytes, but it may be longer (8 to 263 bytes) if an extension is present.
     *
     * @return length of the Structured Field Introducer.
     *
     * @throws AfpException if the record specifies an invalid extension length.
     */
    public int getStructuredFieldIntroducerLength() throws AfpException {
        if (!this.hasStructuredFieldIntroducerExtension()) {
            return STRUCTURED_FIELD_INTRODUCER_LENGTH;
        }

        final int extensionLength = ByteUtils.toInteger(this.record.getData(), 9, 1);
        if (extensionLength < 1) {
            throw new AfpException(
                    "Record at offset " + this.record.getOffset()
                    + " specifies an invalid extension length: " + extensionLength);
        }

        return STRUCTURED_FIELD_INTRODUCER_LENGTH + extensionLength;
    }

    /**
     * Returns the length of the Structured Field (this does not include the control character 0x5A).
     *
     * @return length of the Structured Field.
     *
     * @throws AfpException if the record specifies an invalid length.
     */
    public int getStructuredFieldLength() throws AfpException {
        final int length = ByteUtils.toInteger(this.record.getData(), 1, 2);
        if (length < 8 || length > 32767) {
            throw new AfpException(
                    "Record at offset " + this.record.getOffset() 
                    + " specifies an invalid structured field length: " + length);
        }

        return length;
    }
}
