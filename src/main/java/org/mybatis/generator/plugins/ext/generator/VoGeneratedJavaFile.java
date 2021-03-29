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
package org.mybatis.generator.plugins.ext.generator;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.JavaFormatter;
import org.mybatis.generator.api.dom.java.CompilationUnit;

public class VoGeneratedJavaFile extends GeneratedJavaFile {


    public VoGeneratedJavaFile(CompilationUnit compilationUnit, String targetProject, String fileEncoding, JavaFormatter javaFormatter) {
        super(compilationUnit, targetProject, fileEncoding, javaFormatter);
    }

    public VoGeneratedJavaFile(CompilationUnit compilationUnit, String targetProject, JavaFormatter javaFormatter) {
        super(compilationUnit, targetProject, javaFormatter);
    }
}
