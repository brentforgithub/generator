package org.mybatis.generator.plugins.ext.generator;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.JavaFormatter;
import org.mybatis.generator.api.dom.java.CompilationUnit;

public class PoGeneratedJavaFile extends GeneratedJavaFile {


    public PoGeneratedJavaFile(CompilationUnit compilationUnit, String targetProject, String fileEncoding, JavaFormatter javaFormatter) {
        super(compilationUnit, targetProject, fileEncoding, javaFormatter);
    }

    public PoGeneratedJavaFile(CompilationUnit compilationUnit, String targetProject, JavaFormatter javaFormatter) {
        super(compilationUnit, targetProject, javaFormatter);
    }
}
