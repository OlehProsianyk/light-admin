/*
 * Copyright 2012-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.lightadmin.core.rest.binary;

import org.springframework.data.mapping.PersistentProperty;

import static java.lang.String.format;
import static java.lang.String.valueOf;

public class FileStorageUtils {

    public static String relativePathToStoreBinaryAttrValue(String domainTypeName, Object idValue, PersistentProperty attrMeta) {
        return relativePathToDomainStorageAttributeDirectory(domainTypeName, idValue, attrMeta) + "/" + "file.bin";
    }

    public static String relativePathToDomainStorageAttributeDirectory(String domainTypeName, Object idValue, PersistentProperty attrMeta) {
        return relativePathToDomainStorageDirectory(domainTypeName, idValue) + "/" + attrMeta.getName();
    }

    public static String relativePathToDomainStorageDirectory(String domainTypeName, Object idValue) {
        return format("/domain/%s/%s",
                domainTypeName,
                valueOf(idValue)
        );
    }
}