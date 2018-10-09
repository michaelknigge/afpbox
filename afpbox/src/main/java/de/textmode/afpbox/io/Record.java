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

import java.util.Arrays;

/**
 * The {@link Record} is a container for the read raw data (record) and the offset within the
 * stream the data was read from.
 */
public final class Record {

    private final byte[] record;
    private final long offset;

    /**
     * Constructor of a {@link Record}.
     *
     * @param record the raw data of the data record.
     * @param offset the offset measured from the beginning of the stream.
     */
    Record(final byte[] record, final long offset) {
        this.record = record;
        this.offset = offset;
    }

    /**
     * Gets the raw record.
     *
     * @return the raw record.
     */
    byte[] getRecord() {
        return this.record;
    }

    /**
     * Gets the offset measured from the beginning of the stream this {@link Record} was read from.
     *
     * @return the offset measured from the beginning of the stream.
     */
    long getOffset() {
        return this.offset;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(this.offset) ^ Arrays.hashCode(this.record);
    }

    @Override
    public boolean equals(final Object o) {
        if (o instanceof Record) {
            final Record other = (Record) o;
            return other.offset == this.offset && Arrays.equals(other.record, this.record);
        } else {
            return false;
        }
    }
}
