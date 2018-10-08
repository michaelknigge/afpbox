package de.textmode.afpbox;

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

/**
 * Exception for errors during parsing of AFP data streams.
 */
public final class AfpException extends Exception {

    private static final long serialVersionUID = 2597440977023784140L;

    /**
     * Constructs an {@code AfpException} with the specified detail message.
     *
     * @param message   the detail message.
     */
    public AfpException(final String message) {
        super(message);
    }

}
