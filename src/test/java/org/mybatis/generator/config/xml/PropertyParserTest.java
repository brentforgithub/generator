/*
 *    Copyright ${license.git.copyrightYears} the original author or authors.
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
package org.mybatis.generator.config.xml;

import static org.assertj.core.api.Assertions.*;

import java.util.Properties;

import org.junit.jupiter.api.Test;

class PropertyParserTest {

    @Test
    void testNoMatches() {
        MyBatisGeneratorConfigurationParser parser = new MyBatisGeneratorConfigurationParser(null);

        String result = parser.parsePropertyTokens("${gen.code.package}.${gen.code.mapper}.${gen.code.subpackage}");

        assertThat(result).isEqualTo("${gen.code.package}.${gen.code.mapper}.${gen.code.subpackage}");
    }

    @Test
    void testNoMarkers() {
        MyBatisGeneratorConfigurationParser parser = new MyBatisGeneratorConfigurationParser(null);

        assertThat(parser.parsePropertyTokens("someValue")).isEqualTo("someValue");
    }

    @Test
    void testBadFormat() {
        MyBatisGeneratorConfigurationParser parser = new MyBatisGeneratorConfigurationParser(null);

        assertThat(parser.parsePropertyTokens("${someValue")).isEqualTo("${someValue");
    }

    @Test
    void testSingleValue() {
        Properties properties = new Properties();
        properties.setProperty("gen.code.package", "value1");
        properties.setProperty("gen.code.mapper", "value2");
        properties.setProperty("gen.code.subpackage", "value3");

        MyBatisGeneratorConfigurationParser parser = new MyBatisGeneratorConfigurationParser(properties);

        assertThat(parser.parsePropertyTokens("${gen.code.package}")).isEqualTo("value1");
        assertThat(parser.parsePropertyTokens("${gen.code.mapper}")).isEqualTo("value2");
        assertThat(parser.parsePropertyTokens("${gen.code.subpackage}")).isEqualTo("value3");
    }

    @Test
    void testSingleValueWithTrailingValue() {
        Properties properties = new Properties();
        properties.setProperty("gen.code.package", "value1");
        properties.setProperty("gen.code.mapper", "value2");
        properties.setProperty("gen.code.subpackage", "value3");

        MyBatisGeneratorConfigurationParser parser = new MyBatisGeneratorConfigurationParser(properties);

        assertThat(parser.parsePropertyTokens("${gen.code.package}.pg")).isEqualTo("value1.pg");
    }

    @Test
    void testTwoValues() {
        Properties properties = new Properties();
        properties.setProperty("gen.code.package", "value1");
        properties.setProperty("gen.code.mapper", "value2");
        properties.setProperty("gen.code.subpackage", "value3");

        MyBatisGeneratorConfigurationParser parser = new MyBatisGeneratorConfigurationParser(properties);

        assertThat(parser.parsePropertyTokens("${gen.code.package}.${gen.code.mapper}")).isEqualTo("value1.value2");
    }

    @Test
    void testTwoValuesOnly() {
        Properties properties = new Properties();
        properties.setProperty("gen.code.package", "value1");
        properties.setProperty("gen.code.mapper", "value2");
        properties.setProperty("gen.code.subpackage", "value3");

        MyBatisGeneratorConfigurationParser parser = new MyBatisGeneratorConfigurationParser(properties);

        assertThat(parser.parsePropertyTokens("${gen.code.package}${gen.code.mapper}")).isEqualTo("value1value2");
    }

    @Test
    void testTwoValuesWithTrailingValue() {
        Properties properties = new Properties();
        properties.setProperty("gen.code.package", "value1");
        properties.setProperty("gen.code.mapper", "value2");
        properties.setProperty("gen.code.subpackage", "value3");

        MyBatisGeneratorConfigurationParser parser = new MyBatisGeneratorConfigurationParser(properties);

        String result = parser.parsePropertyTokens("${gen.code.package}.${gen.code.mapper}.pg");

        assertThat(result).isEqualTo("value1.value2.pg");
    }

    @Test
    void testTwoValuesWithSingleCharacterTrailingValue() {
        Properties properties = new Properties();
        properties.setProperty("gen.code.package", "value1");
        properties.setProperty("gen.code.mapper", "value2");
        properties.setProperty("gen.code.subpackage", "value3");

        MyBatisGeneratorConfigurationParser parser = new MyBatisGeneratorConfigurationParser(properties);

        String result = parser.parsePropertyTokens("${gen.code.package}.${gen.code.mapper}.");

        assertThat(result).isEqualTo("value1.value2.");
    }

    @Test
    void testThreeValues() {
        Properties properties = new Properties();
        properties.setProperty("gen.code.package", "value1");
        properties.setProperty("gen.code.mapper", "value2");
        properties.setProperty("gen.code.subpackage", "value3");

        MyBatisGeneratorConfigurationParser parser = new MyBatisGeneratorConfigurationParser(properties);

        String result = parser.parsePropertyTokens("${gen.code.package}.${gen.code.mapper}.${gen.code.subpackage}");

        assertThat(result).isEqualTo("value1.value2.value3");
    }

	@Test
	void testNestedValues() {
		Properties properties = new Properties();
		properties.put("domain", "foo");
		properties.put("project.foo", "pfoo");
		properties.put("addr", "localhost");
		properties.put("env.localhost", "dev");
		properties.put("jdbc.pfoo.dev.url", "mysql");

		MyBatisGeneratorConfigurationParser parser = new MyBatisGeneratorConfigurationParser(properties);

		String result = parser.parsePropertyTokens("${jdbc.${project.${domain}}.${env.${addr}}.url}");

		assertThat(result).isEqualTo("mysql");
	}
}
