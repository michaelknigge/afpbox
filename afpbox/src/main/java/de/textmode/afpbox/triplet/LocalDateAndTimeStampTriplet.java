package de.textmode.afpbox.triplet;

/*
 * Copyright 2020 Michael Knigge
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
 * The Local Date and Time Stamp triplet specifies a date and time stamp
 * to be associated with an object.
 */
public final class LocalDateAndTimeStampTriplet extends Triplet {

    /**
     * Constructs the {@link LocalDateAndTimeStampTriplet}.
     *
     * @param data          the raw data of the Triplet.
     *
     * @throws AfpException if the triplet has not the expected length.
     */
    public LocalDateAndTimeStampTriplet(final byte[] data) throws AfpException {
        super(data, 17);
    }

    /**
     * Returns the date and time stamp type (0x00: Creation, 0x01: Retired value, 0x03: Revision).
     *
     * @return date and time stamp type.
     */
    public int getStampType() {
        return ByteUtils.toUnsignedByte(this.getData(), 2);
    }

    /**
     * Returns the Hundreds position and implied thousands position of year (0x40: 19xx, 0xF0-0xF9: 20xx-29xx).
     *
     * @return Hundreds position and implied thousands position of year.
     */
    public int getYearHundreds() {
        return ByteUtils.toUnsignedByte(this.getData(), 3);
    }

    /**
     * Returns the Tens and units position of year.
     *
     * @return Tens and units position of year.
     */
    public int getYearTens() {
        return ByteUtils.toUnsignedInteger16(this.getData(), 4);
    }

    /**
     * Returns the Day of year.
     *
     * @return Day of year.
     */
    public int getDay() {
        return ByteUtils.toUnsignedInteger24(this.getData(), 6);
    }

    /**
     * Returns the Hour of day.
     *
     * @return Hour of day.
     */
    public int getHour() {
        return ByteUtils.toUnsignedInteger16(this.getData(), 9);
    }

    /**
     * Returns the Minute of hour.
     *
     * @return Minute of hour.
     */
    public int getMinute() {
        return ByteUtils.toUnsignedInteger16(this.getData(), 11);
    }

    /**
     * Returns the Second of minute.
     *
     * @return Second of minute.
     */
    public int getSecond() {
        return ByteUtils.toUnsignedInteger16(this.getData(), 13);
    }
    /**
     * Returns the Hundredth of second.
     *
     * @return Hundredth of second.
     */
    public int getHundredthOfSecond() {
        return ByteUtils.toUnsignedInteger16(this.getData(), 15);
    }
}
