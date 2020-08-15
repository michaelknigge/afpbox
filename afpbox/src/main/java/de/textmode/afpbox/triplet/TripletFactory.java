package de.textmode.afpbox.triplet;

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

import de.textmode.afpbox.AfpException;
import de.textmode.afpbox.common.TripletIdentifier;

/**
 * The {@link TripletFactory} creates the concrete {@link Triplet} objects.
 */
public final class TripletFactory {

    /**
     * Creates a concrete {@link Triplet} from the given byte[].
     *
     * @param tripletId     the ID of the Triplet
     * @param data          the raw data of the Triplet
     *
     * @return the built {@link Triplet}.
     *
     * @throws AfpException if an error occurs while parsing the {@link Triplet}.
     */
    public static Triplet createFor(
            final int tripletId,
            final byte[] data) throws AfpException {

        // Yes.... this code looks ugly and could be done nicer using a HashMap. But remember that
        // Java does not support HashMaps for native data types, so we would have to use a HashMap
        // with an Integer as Key. But then for every lookup an Integer would have to be created
        // from an int... overkill... So we just use a switch-case which is pretty fast at runtime...

        switch (tripletId) {
        case TripletIdentifier.CODED_GRAPHIC_CHARACTER_SET_GLOBAL_IDENTIFIER:
            return new CodedGraphicCharacterSetGlobalIdentifierTriplet(data);
//        case TripletIdentifier.FULLY_QUALIFIED_NAME:
//        case TripletIdentifier.MAPPING_OPTION:
//        case TripletIdentifier.OBJECT_CLASSIFICATION:
//        case TripletIdentifier.MODCA_INTERCHANGE_SET:
//        case TripletIdentifier.FONT_DESCRIPTOR_SPECIFICATION:
//        case TripletIdentifier.FONT_CODED_GRAPHIC_CHARACTER_SET_GLOBAL_IDENTIFIER:
//        case TripletIdentifier.OBJECT_FUNCTION_SET_SPECIFICATION:
//        case TripletIdentifier.EXTENDED_RESOURCE_LOCAL_IDENTIFIER:
//        case TripletIdentifier.RESOURCE_LOCAL_IDENTIFIER:
        case TripletIdentifier.RESOURCE_SECTION_NUMBER:
            return new ResourceSectionNumberTriplet(data);
//        case TripletIdentifier.CHARACTER_ROTATION:
//        case TripletIdentifier.OBJECT_BYTE_OFFSET:
//        case TripletIdentifier.ATTRIBUTE_VALUE:
        case TripletIdentifier.DESCRIPTOR_POSITION:
            return new DescriptorPositionTriplet(data);
//        case TripletIdentifier.MEDIA_EJECT_CONTROL:
//        case TripletIdentifier.PAGE_OVERLAY_CONDITIONAL_PROCESSING:
//        case TripletIdentifier.RESOURCE_USAGE_ATTRIBUTE:
//        case TripletIdentifier.MEASUREMENT_UNITS:
//        case TripletIdentifier.OBJECT_AREA_SIZE:
//        case TripletIdentifier.AREA_DEFINITION:
//        case TripletIdentifier.COLOR_SPECIFICATION:
//        case TripletIdentifier.ENCODING_SCHEME_ID:
//        case TripletIdentifier.MEDIUM_MAP_PAGE_NUMBER:
//        case TripletIdentifier.OBJECT_BYTE_EXTENT:
//        case TripletIdentifier.OBJECT_STRUCTURED_FIELD_OFFSET:
//        case TripletIdentifier.OBJECT_STRUCTURED_FIELD_EXTENT:
//        case TripletIdentifier.OBJECT_OFFSET:
//        case TripletIdentifier.FONT_HORIZONTAL_SCALE_FACTOR:
//        case TripletIdentifier.OBJECT_COUNT:
//        case TripletIdentifier.OBJECT_DATE_AND_TIME_STAMP:
        case TripletIdentifier.COMMENT:
            return new CommentTriplet(data);
//        case TripletIdentifier.MEDIUM_ORIENTATION:
//        case TripletIdentifier.RESOURCE_OBJECT_INCLUDE:
//        case TripletIdentifier.PRESENTATION_SPACE_RESET_MIXING:
//        case TripletIdentifier.PRESENTATION_SPACE_MIXING_RULE:
        case TripletIdentifier.UNIVERSAL_DATE_AND_TIME_STAMP:
            return new UniversalDateAndTimeStampTriplet(data);
//        case TripletIdentifier.TONER_SAVER:
//        case TripletIdentifier.COLOR_FIDELITY:
//        case TripletIdentifier.FONT_FIDELITY:
//        case TripletIdentifier.ATTRIBUTE_QUALIFIER:
//        case TripletIdentifier.PAGE_POSITION_INFORMATION:
//        case TripletIdentifier.PARAMETER_VALUE:
//        case TripletIdentifier.PRESENTATION_CONTROL:
//        case TripletIdentifier.FONT_RESOLUTION_AND_METRIC_TECHNOLOGY:
//        case TripletIdentifier.FINISHING_OPERATION:
//        case TripletIdentifier.TEXT_FIDELITY:
//        case TripletIdentifier.MEDIA_FIDELITY:
//        case TripletIdentifier.FINISHING_FIDELITY:
//        case TripletIdentifier.DATA_OBJECT_FONT_DESCRIPTOR:
//        case TripletIdentifier.LOCALE_SELECTOR:
//        case TripletIdentifier.UP3I_FINISHING_OPERATION:
//        case TripletIdentifier.MODCA_FUNCTION_SET:
//        case TripletIdentifier.COLOR_MANAGEMENT_RESOURCE_DESCRIPTOR:
//        case TripletIdentifier.RENDERING_INTENT:
//        case TripletIdentifier.CMR_TAG_FIDELITY:
//        case TripletIdentifier.DEVICE_APPEARANCE:
//        case TripletIdentifier.IMAGE_RESOLUTION:
//        case TripletIdentifier.OBJECT_CONTAINER_PRESENTATION_SPACE_SIZE:
//        case TripletIdentifier.KEEP_GROUP_TOGETHER:
//        case TripletIdentifier.TRIPLET_EXTENDER:

        default:
            return new UnknownTriplet(data);
        }
    }
}
