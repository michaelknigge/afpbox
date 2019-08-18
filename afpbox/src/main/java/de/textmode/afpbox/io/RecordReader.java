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

import java.io.IOException;

/**
 * The {@link RecordReader} is responsible for reading data records that contain
 * AFP or line data.
 */
public interface RecordReader {

    /**
     * Reads and returns the next data record. If no more records are available <code>null</code>
     * is returned. The read record will start with the control character (x'5A' for AFP), so leading
     * length bytes are removed.
     *
     * @return the read record or <code>null</code> if no more record is available.
     *
     * @throws IOException if an I/O error occurs.
     */
    public abstract Record read() throws IOException;
}
