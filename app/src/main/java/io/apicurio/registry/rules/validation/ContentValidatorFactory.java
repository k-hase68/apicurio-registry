/*
 * Copyright 2019 Red Hat
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.apicurio.registry.rules.validation;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import io.apicurio.registry.types.ArtifactType;

/**
 * Factory for creating validators for the different supported content types.
 * @author eric.wittmann@gmail.com
 */
@ApplicationScoped
public class ContentValidatorFactory {
    
    @Inject
    AvroContentValidator avroValidator;
    @Inject
    ProtobuffContentValidator protoValidator;
    @Inject
    JsonSchemaContentValidator jsonValidator;
    @Inject
    OpenApiContentValidator openapiValidator;
    @Inject
    AsyncApiContentValidator asyncValidator;
    
    public ContentValidator createValidator(ArtifactType artifactType) {
        switch (artifactType) {
            case ASYNCAPI:
                return asyncValidator;
            case AVRO:
                return avroValidator;
            case JSON:
                return jsonValidator;
            case OPENAPI:
                return openapiValidator;
            case PROTOBUFF:
                return protoValidator;
            default:
                break;
        }
        throw new UnsupportedOperationException("No content validator available for: " + artifactType);
    }

}
