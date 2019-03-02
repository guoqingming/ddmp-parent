package com.byit.plugin;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;

public class LombokAnnotationPlugin extends PluginAdapter {


  public boolean validate(List<String> list) {
    return false;
  }

  @Override
  public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
    topLevelClass.addAnnotation("@Data");
    topLevelClass.addImportedType(new FullyQualifiedJavaType("lombok.Data"));
    List<Method> methods = topLevelClass.getMethods();
    List<Method> remove = new ArrayList<Method>();
    for (Method method : methods) {
      if (method.getBodyLines().size() < 2) {
        remove.add(method);
      }
    }
    methods.removeAll(remove);
    return true;
  }

  @Override
  public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
    return false;
  }

    @Override
    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return false;
    }
}