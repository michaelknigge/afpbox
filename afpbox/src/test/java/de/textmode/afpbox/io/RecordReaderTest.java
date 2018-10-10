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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

abstract class RecordReaderTest extends TestCase {

    protected String readAndExpectFailure(
            final Class<? extends RecordReader> clazz,
            final byte[]... inputBytes) throws Exception {

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        for (final byte[] bytes : inputBytes) {
            baos.write(bytes);
        }

        final ByteArrayInputStream is = new ByteArrayInputStream(baos.toByteArray());
        final Constructor<? extends RecordReader> ctor = clazz.getConstructor(InputStream.class);

        final RecordReader reader = ctor.newInstance(is);

        try {
            while (reader.read() != null) {
                continue;
            }
            fail("This unit test should fail...");
            return null;
        } catch (final IOException e) {
            return e.getMessage();
        }
    }

    protected List<Record> readAndExpectSuccess(
            final Class<? extends RecordReader> clazz,
            final byte[]... inputBytes) throws Exception {

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        for (final byte[] bytes : inputBytes) {
            baos.write(bytes);
        }

        final ByteArrayInputStream is = new ByteArrayInputStream(baos.toByteArray());
        final Constructor<? extends RecordReader> ctor = clazz.getConstructor(InputStream.class);
        final RecordReader reader = ctor.newInstance(is);
        final List<Record> result = new ArrayList<>();
        Record record;

        while ((record = reader.read()) != null) {
            result.add(record);
        }

        return result;
    }
}
