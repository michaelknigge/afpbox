package de.textmode.afpbox.structuredfield;

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

import de.textmode.afpbox.io.AfpDataInputStream;

/**
 * The {@link StructuredField} NOP (No Operation).
 */
public final class NoOperation extends StructuredFieldBase {

    /**
     * Constructs the {@link NoOperation}.
     *
     * @param is the {@link AfpDataInputStream} the Structured Field data is read from.
     */
    public NoOperation(final AfpDataInputStream is) {
        super(is.readRemainingBytes());
    }
}
