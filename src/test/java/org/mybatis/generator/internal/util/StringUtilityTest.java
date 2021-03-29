/*
 *    Copyright 2006-2021 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.mybatis.generator.internal.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StringUtilityTest {

    @Test
    void testNoCatalog() {
        String answer = StringUtility.composeFullyQualifiedTableName(null, "schema", "table", '.');
        assertEquals("schema.table", answer);
    }

    @Test
    void testNoSchema() {
        String answer = StringUtility.composeFullyQualifiedTableName("catalog", null, "table", '.');
        assertEquals("catalog..table", answer);
    }

    @Test
    void testAllPresent() {
        String answer = StringUtility.composeFullyQualifiedTableName("catalog", "schema", "table", '.');
        assertEquals("catalog.schema.table", answer);
    }

    @Test
    void testTableOnly() {
        String answer = StringUtility.composeFullyQualifiedTableName(null, null, "table", '.');
        assertEquals("table", answer);
    }
}
