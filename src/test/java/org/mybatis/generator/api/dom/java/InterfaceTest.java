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
package org.mybatis.generator.api.dom.java;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.mybatis.generator.api.dom.java.render.TopLevelInterfaceRenderer;

class InterfaceTest {

    @Test
    void testConstructor() {
        Interface interfaze = new Interface("com.foo.UserInterface");
        assertNotNull(interfaze);
    }

    @Test
    void testAddImportedType() {
        Interface interfaze = new Interface("com.foo.UserInterface");
        FullyQualifiedJavaType arrayList = FullyQualifiedJavaType.getNewArrayListInstance();
        interfaze.addImportedType(arrayList);

        assertNotNull(interfaze.getImportedTypes());
        assertEquals(1, interfaze.getImportedTypes().size());
        assertTrue(interfaze.getImportedTypes().contains(arrayList));
    }

    @Test
    void testAddImportedTypes() {
        Interface interfaze = new Interface("com.foo.UserInterface");
        Set<FullyQualifiedJavaType> importedTypes = new HashSet<>();

        FullyQualifiedJavaType arrayList = FullyQualifiedJavaType.getNewArrayListInstance();
        FullyQualifiedJavaType hashMap = FullyQualifiedJavaType.getNewHashMapInstance();

        importedTypes.add(arrayList);
        importedTypes.add(hashMap);
        interfaze.addImportedTypes(importedTypes);

        assertNotNull(interfaze.getImportedTypes());
        assertEquals(2, interfaze.getImportedTypes().size());
        assertTrue(interfaze.getImportedTypes().contains(arrayList));
        assertTrue(interfaze.getImportedTypes().contains(hashMap));
    }

    @Test
    void testAddFileCommentLine() {
        Interface interfaze = new Interface("com.foo.UserInterface");
        interfaze.addFileCommentLine("test");

        assertNotNull(interfaze.getFileCommentLines());
        assertEquals(1, interfaze.getFileCommentLines().size());
        assertEquals("test", interfaze.getFileCommentLines().get(0));
    }

    @Test
    void testAddStaticImport() {
        Interface interfaze = new Interface("com.foo.UserInterface");
        interfaze.addStaticImport("com.foo.StaticUtil");

        assertNotNull(interfaze.getStaticImports());
        assertEquals(1, interfaze.getStaticImports().size());
        assertTrue(interfaze.getStaticImports().contains("com.foo.StaticUtil"));
    }

    @Test
    void testAddStaticImports() {
        Interface interfaze = new Interface("com.foo.UserInterface");
        Set<String> staticImports = new HashSet<>();
        staticImports.add("com.foo.StaticUtil1");
        staticImports.add("com.foo.StaticUtil2");
        interfaze.addStaticImports(staticImports);

        assertNotNull(interfaze.getStaticImports());
        assertEquals(2, interfaze.getStaticImports().size());
        assertTrue(interfaze.getStaticImports().contains("com.foo.StaticUtil1"));
        assertTrue(interfaze.getStaticImports().contains("com.foo.StaticUtil2"));
    }

    @Test
    void testInterfaceFields() {
        Interface interfaze = new Interface("foo.Bar");
        interfaze.setVisibility(JavaVisibility.PUBLIC);

        Field field = new Field("EMPTY_STRING", FullyQualifiedJavaType.getStringInstance());
        field.setInitializationString("\"\"");
        interfaze.addField(field);

        field = new Field("ONE", FullyQualifiedJavaType.getStringInstance());
        field.setInitializationString("\"one\"");
        interfaze.addField(field);

        String expected = "package foo;" + System.getProperty("line.separator")
            + System.getProperty("line.separator")
            + "public interface Bar {" + System.getProperty("line.separator")
            + "    String EMPTY_STRING = \"\";" + System.getProperty("line.separator") + System.getProperty("line.separator")
            + "    String ONE = \"one\";" + System.getProperty("line.separator")
            + "}";

        TopLevelInterfaceRenderer renderer = new TopLevelInterfaceRenderer();
        assertThat(renderer.render(interfaze)).isEqualTo(expected);
    }
}
