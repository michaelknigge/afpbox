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
import de.textmode.afpbox.common.ByteUtils;

/**
 * The Relative Move Baseline control sequence moves the baseline coordinate relative to
 * the current baseline coordinate position.
 */
public final class RelativeMoveBaseline extends PtocaControlSequence {

    /**
     * Constructs the {@link RelativeMoveBaseline}.
     *
     * @param functionType  the integer value of the PTOCA control sequence function type.
     * @param data          the raw data of the PTOCA control sequence.
     */
    RelativeMoveBaseline(final int functionType, final byte[] data) throws AfpException {
        super(functionType, data, "RMB", 4);
    }

    /**
     * Returns the increment in the B-direction from m the current baseline
     * coordinate position to a new baseline coordinate position.
     *
     * @return increment in the B-direction from m the current baseline
     *     coordinate position to a new baseline coordinate position.
     */
    public int getIncrement() {
        return ByteUtils.toInteger16(this.getData(), 2);
    }
}
