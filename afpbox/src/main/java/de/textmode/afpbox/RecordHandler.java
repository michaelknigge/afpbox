package de.textmode.afpbox;

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

import de.textmode.afpbox.io.Record;

/**
 * The {@link RecordHandler} is responsible for processing the read records.
 */
public interface RecordHandler {

    /**
     * This method gets called if a line record has been read.
     *
     * @param record   the read {@link Record}
     */
    public void handleLineRecord(final Record record);

    /**
     * This method gets called if an AFP record has been read. The {@link StructuredFieldIntroducer} contains some base
     * information about the structured field so the concrete implementation of the {@link RecordHandler} can decide if
     * the rest of the structured field should be parsed or not.
     *
     * @param sfi   the read {@link StructuredFieldIntroducer} created from the read {@link Record}.
     *
     * @return true if the complete record should be parsed. After the record has been parsed the
     *     method {@link #handleStructuredFieldIntroducer} gets invoked.
     */
    public boolean handleStructuredFieldIntroducer(final StructuredFieldIntroducer sfi);

    /**
     * This method gets called if an AFP record has been read and {@link #handleStructuredFieldIntroducer}
     * returned <code>true</code>.
     *
     * @param sf   the read and parsed {@link StructuredField}.
     */
    public void handleStructuredField(final StructuredField sf);

}
