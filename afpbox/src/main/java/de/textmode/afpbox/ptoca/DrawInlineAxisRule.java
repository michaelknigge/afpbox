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
 * The Draw I-axis Rule control sequence draws a rule in the I-direction.
 */
public final class DrawInlineAxisRule extends PtocaControlSequence {

    /**
     * Constructs the {@link DrawInlineAxisRule}.
     *
     * @param functionType  the integer value of the PTOCA control sequence function type.
     * @param data          the raw data of the PTOCA control sequence.
     */
    DrawInlineAxisRule(final int functionType, final byte[] data) throws AfpException {
        super(functionType, data);

        if (this.getData().length != 4 && this.getData().length != 7) {
            throw new AfpException("PTOCA control sequence DIR has invalid length of " + this.getData().length + " bytes (expected 4 or 7 bytes)");
        }
    }

    /**
     * Returns the length of the rule.
     *
     * @return the length of the rule.
     */
    @Override
    public int getLength() {
        return ByteUtils.toInteger16(this.getData(), 2);
    }

    /**
     * Returns the width of the rule. If the control sequence does not specify a width the
     * special value X'FFFFFFFF' is returned.
     *
     * @return the width of the rule or X'FFFFFFFF'.
     */
    public int getWidth() {
        if (this.getData().length == 4) {
            return 0xFFFFFFFF;
        } else {
            return ByteUtils.toInteger24(this.getData(), 4);
        }
    }
}
