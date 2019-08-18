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

import de.textmode.afpbox.common.StructuredFieldIdentifier;
import de.textmode.afpbox.io.AfpDataInputStream;

public final class StructuredFieldFactory {

    public static StructuredField createFor(final int sfid, final AfpDataInputStream is) {

        // Yes.... this code looks ugly and could be done nicer using a HashMap. But remember that
        // Java does not support HashMaps for native data types, so we would have to use a HashMap
        // with an Integer as Key. But then for every lookup an Integer would have to be created
        // from an int - overkill... So we just use a switch-case which is pretty fast at runtime...

        switch (sfid) {
        case StructuredFieldIdentifier.NOP:
            return new NoOperation(is);
        default:
            return new UnknownStructuredField(is);
        }
    }
}
