package de.textmode.afpbox.structuredfield;

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

/**
 * {@link StructuredFieldBase} is a {@link StructuredField} that contains only opaque data.
 */
public abstract class StructuredFieldBase implements StructuredField {

    private final byte[] data;

    /**
     * The constructor just stores the raw data of the Structured Field.
     *
     * @param data the raw data of the Structured Field
     */
    public StructuredFieldBase(final byte[] data) {
        this.data = data;
    }

    /**
     * Returns the data of the Structured Field (all bytes after the Structured Field Introducer).
     *
     * @return the data of the Structured Field
     */
    public final byte[] getData() {
        return this.data;
    }
}
