package org.mybatis.generator.plugins.ext.generator;

import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.CompilationUnitVisitor;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.InnerClass;

import java.util.List;
import java.util.Set;

public class PoCompilationUnit extends InnerClass implements CompilationUnit {
    public PoCompilationUnit(FullyQualifiedJavaType type) {
        super(type);
    }

    public PoCompilationUnit(String type) {
        super(type);
    }

    @Override
    public Set<FullyQualifiedJavaType> getImportedTypes() {
        return null;
    }

    @Override
    public Set<String> getStaticImports() {
        return null;
    }

    @Override
    public FullyQualifiedJavaType getType() {
        return null;
    }

    @Override
    public void addImportedType(FullyQualifiedJavaType importedType) {

    }

    @Override
    public void addImportedTypes(Set<FullyQualifiedJavaType> importedTypes) {

    }

    @Override
    public void addStaticImport(String staticImport) {

    }

    @Override
    public void addStaticImports(Set<String> staticImports) {

    }

    @Override
    public void addFileCommentLine(String commentLine) {

    }

    @Override
    public List<String> getFileCommentLines() {
        return null;
    }

    @Override
    public <R> R accept(CompilationUnitVisitor<R> visitor) {
        return null;
    }
}
