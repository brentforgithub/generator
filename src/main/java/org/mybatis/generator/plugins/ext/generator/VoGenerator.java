package org.mybatis.generator.plugins.ext.generator;

import org.mybatis.generator.api.FullyQualifiedTable;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.Plugin;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.codegen.RootClassInfo;
import org.mybatis.generator.codegen.mybatis3.model.BaseRecordGenerator;

import java.util.ArrayList;
import java.util.List;

import static org.mybatis.generator.internal.util.JavaBeansUtil.*;
import static org.mybatis.generator.internal.util.messages.Messages.getString;

public class VoGenerator extends BaseRecordGenerator {

    private String targetPackage;

    public VoGenerator(String project) {
        super(project);
    }

    /**
     * 参考BaseRecordGenerator
     * @return
     */
    @Override
    public List<CompilationUnit> getCompilationUnits() {
        FullyQualifiedTable table = introspectedTable.getFullyQualifiedTable();
//        progressCallback.startTask(getString(
//                "Progress.19", table.toString())); //$NON-NLS-1$
        Plugin plugins = context.getPlugins();
        VoCommentGenerator commentGenerator = new VoCommentGenerator();

        FullyQualifiedJavaType type = new FullyQualifiedJavaType(
                targetPackage + "." + introspectedTable.getFullyQualifiedTable().getDomainObjectName() + "Vo");
        TopLevelClass topLevelClass = new TopLevelClass(type);
        topLevelClass.setVisibility(JavaVisibility.PUBLIC);
        commentGenerator.addJavaFileComment(topLevelClass);

        FullyQualifiedJavaType superClass = getSuperClass();
        if (superClass != null) {
            topLevelClass.setSuperClass(superClass);
            topLevelClass.addImportedType(superClass);
        }
        topLevelClass.addImportedType("lombok.Data");
        topLevelClass.addAnnotation("@Data");
        commentGenerator.addModelClassComment(topLevelClass, introspectedTable);

        List<IntrospectedColumn> introspectedColumns = getColumnsInThisClass();

//        if (introspectedTable.isConstructorBased()) {
//            addParameterizedConstructor(topLevelClass, introspectedTable.getNonBLOBColumns());
//
//            if (includeBLOBColumns()) {
//                addParameterizedConstructor(topLevelClass, introspectedTable.getAllColumns());
//            }
//
//            if (!introspectedTable.isImmutable()) {
//                addDefaultConstructor(topLevelClass);
//            }
//        }

        String rootClass = getRootClass();
        context.setCommentGeneratorConfiguration();
        for (IntrospectedColumn introspectedColumn : introspectedColumns) {
            if (RootClassInfo.getInstance(rootClass, warnings)
                    .containsProperty(introspectedColumn)) {
                continue;
            }

            Field field = getJavaBeansField(introspectedColumn, context, introspectedTable);
            if (plugins.modelFieldGenerated(field, topLevelClass,
                    introspectedColumn, introspectedTable,
                    Plugin.ModelClassType.BASE_RECORD)) {
                topLevelClass.addField(field);
                topLevelClass.addImportedType(field.getType());
            }

//            Method method = getJavaBeansGetter(introspectedColumn, context, introspectedTable);
//            if (plugins.modelGetterMethodGenerated(method, topLevelClass,
//                    introspectedColumn, introspectedTable,
//                    Plugin.ModelClassType.BASE_RECORD)) {
//                topLevelClass.addMethod(method);
//            }
//
//            if (!introspectedTable.isImmutable()) {
//                method = getJavaBeansSetter(introspectedColumn, context, introspectedTable);
//                if (plugins.modelSetterMethodGenerated(method, topLevelClass,
//                        introspectedColumn, introspectedTable,
//                        Plugin.ModelClassType.BASE_RECORD)) {
//                    topLevelClass.addMethod(method);
//                }
//            }
        }

        List<CompilationUnit> answer = new ArrayList<>();
        if (context.getPlugins().modelBaseRecordClassGenerated(
                topLevelClass, introspectedTable)) {
            answer.add(topLevelClass);
        }
        return answer;
    }

    private List<IntrospectedColumn> getColumnsInThisClass() {
        List<IntrospectedColumn> introspectedColumns;
        if (includePrimaryKeyColumns()) {
            introspectedColumns = introspectedTable.getAllColumns();
        } else {
            introspectedColumns = introspectedTable
                    .getNonPrimaryKeyColumns();
        }

        return introspectedColumns;
    }

    private boolean includePrimaryKeyColumns() {
        return !introspectedTable.getRules().generatePrimaryKeyClass()
                && introspectedTable.hasPrimaryKeyColumns();
    }
    private FullyQualifiedJavaType getSuperClass() {
        FullyQualifiedJavaType superClass;
        if (introspectedTable.getRules().generatePrimaryKeyClass()) {
            superClass = new FullyQualifiedJavaType(introspectedTable
                    .getPrimaryKeyType());
        } else {
            String rootClass = getRootClass();
            if (rootClass != null) {
                superClass = new FullyQualifiedJavaType(rootClass);
            } else {
                superClass = null;
            }
        }

        return superClass;
    }

    public String getTargetPackage() {
        return targetPackage;
    }

    public void setTargetPackage(String targetPackage) {
        this.targetPackage = targetPackage;
    }
}
