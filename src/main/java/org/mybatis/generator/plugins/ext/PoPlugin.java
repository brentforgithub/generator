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
package org.mybatis.generator.plugins.ext;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.Plugin;
import org.mybatis.generator.api.dom.DefaultJavaFormatter;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.plugins.ext.generator.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PoPlugin  implements Plugin {
    private Context context;

    private PoCommentGenerator poCommentGenerator = new PoCommentGenerator();

    private Properties properties;
    private List<String> warnings;



    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
        List<GeneratedJavaFile> answer = new ArrayList<>();
        PoGenerator po = new PoGenerator(properties.getProperty("targetProject"));
        po.setIntrospectedTable(introspectedTable);
        po.setWarnings(warnings);
        po.setContext(context);
        po.setTargetPackage(properties.getProperty("targetPackage"));

        poCommentGenerator.addConfigurationProperties(context.getCommentGeneratorConfiguration().getProperties());
        po.setCommentGenerator(poCommentGenerator);
        DefaultJavaFormatter defaultJavaFormatter = new DefaultJavaFormatter();
        defaultJavaFormatter.setContext(context);

        PoGeneratedJavaFile poGeneratedJavaFile = new PoGeneratedJavaFile(
                po.getCompilationUnits().get(0),
                properties.getProperty("targetProject"), defaultJavaFormatter);


        List<GeneratedJavaFile> voList = new ArrayList<>();
        voList.add(poGeneratedJavaFile);
        return voList;
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    /**
     * 验证插件是否可用
     * @param warnings
     *            add strings to this list to specify warnings. For example, if
     *            the plugin is invalid, you should specify why. Warnings are
     *            reported to users after the completion of the run.
     * @return
     */
    @Override
    public boolean validate(List<String> warnings) {
        this.warnings = warnings;
        return true;
    }
}
