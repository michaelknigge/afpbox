package de.textmode.afpbox.ptoca;

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
 * {@link PtocaControlSequence} is the super class for all PTOCA control sequences.
 */
public abstract class PtocaControlSequence {

    private final int functionType;
    private final byte[] data;

    /**
     * Constructs the {@link PtocaControlSequence}.
     *
     * @param functionType the integer value of the PTOCA control sequence function type.
     * @param data the raw data of the PTOCA control sequence.
     */
    PtocaControlSequence(final int functionType, final byte[] data) {
        this.functionType = functionType;
        this.data = data;
    }

    /**
     * Constructs the {@link PtocaControlSequence}.
     *
     * @param functionType the integer value of the PTOCA control sequence function type.
     * @param data the raw data of the PTOCA control sequence.
     * @param name the name (abbreviation like "AMB" or "SCFL") of the PTOCA control sequence.
     * @param expectedLength complete expected length of the PTOCA control sequence.
     *
     * @throws AfpException
     */
    PtocaControlSequence(
            final int functionType,
            final byte[] data,
            final String name,
            final int expectedLength) throws AfpException {

        this.functionType = functionType;
        this.data = data;

        if (this.getData().length + 2 != expectedLength) {
            throw new AfpException("PTOCA control sequence " + name + " has invalid length of " + this.getData().length + " bytes (expected " + expectedLength + " bytes)");
        }
    }

    /**
     * Returns the length of the complete PTOCA control sequence (including the length and function type byte).
     *
     * @return the length of the complete PTOCA control sequence (including the length and function type byte).
     */
    public int getLength() {
        return this.data.length;
    }

    /**
     * Returns the function type of the PTOCA control sequence.
     *
     * @return the length of the complete PTOCA control sequence.
     */
    public int getFunctionType() {
        return this.functionType;
    }

    /**
     * Returns the data of the PTOCA control sequence (all bytes after the length and function type byte.
     *
     * @return the data of the PTOCA control sequence
     */
    protected byte[] getData() {
        return this.data;
    }
}
