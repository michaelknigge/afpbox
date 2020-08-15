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
 * The Universal Date and Time Stamp triplet specifies a date and time
 * in accordance with the format defined in ISO 8601:1988 (E).
 */
public final class UniversalDateAndTimeStampTriplet extends Triplet {

    /**
     * Constructs the {@link UniversalDateAndTimeStampTriplet}.
     *
     * @param data          the raw data of the Triplet.
     *
     * @throws AfpException if the triplet has not the expected length.
     */
    public UniversalDateAndTimeStampTriplet(final byte[] data) throws AfpException {
        super(data, 13);
    }

    /**
     * Returns the Year AD using Gregorian calendar.
     *
     * @return Year AD using Gregorian calendar.
     */
    public int getYear() {
        return ByteUtils.toUnsignedInteger16(this.getData(), 3);
    }

    /**
     * Returns the Month of the year.
     *
     * @return Month of the year
     */
    public int getMonth() {
        return ByteUtils.toUnsignedByte(this.getData(), 5);
    }

    /**
     * Returns the Day of the year.
     *
     * @return Day of the year
     */
    public int getDay() {
        return ByteUtils.toUnsignedByte(this.getData(), 6);
    }

    /**
     * Returns the Hour of the day in 24-hour format.
     *
     * @return Hour of the day in 24-hour format.
     */
    public int getHour() {
        return ByteUtils.toUnsignedByte(this.getData(), 7);
    }

    /**
     * Returns the Minute of the hour.
     *
     * @return Minute of the hour.
     */
    public int getMinute() {
        return ByteUtils.toUnsignedByte(this.getData(), 8);
    }

    /**
     * Returns the Second of the minute.
     *
     * @return Second of the minute.
     */
    public int getSecond() {
        return ByteUtils.toUnsignedByte(this.getData(), 9);
    }

    /**
     * Returns the Relationship of time to UTC (0x00: UTC, 0x01: Ahead of UTC, 0x0s: Behind UTC).
     *
     * @return Relationship of time to UTC.
     */
    public int getTimeZone() {
        return ByteUtils.toUnsignedByte(this.getData(), 10);
    }

    /**
     * Returns the Hours ahead of or behind UTC.
     *
     * @return Hours ahead of or behind UTC.
     */
    public int getUtcDifferenceHours() {
        return ByteUtils.toUnsignedByte(this.getData(), 11);
    }

    /**
     * Returns the Minutes ahead of or behind UTC.
     *
     * @return Minutes ahead of or behind UTC.
     */
    public int getUtcDifferenceMinutes() {
        return ByteUtils.toUnsignedByte(this.getData(), 12);
    }
}
