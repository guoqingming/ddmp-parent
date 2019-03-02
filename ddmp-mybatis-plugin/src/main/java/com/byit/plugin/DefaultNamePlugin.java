package com.byit.plugin;

import java.util.List;

import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.TableConfiguration;

public class DefaultNamePlugin extends PluginAdapter {

  @Override
  public boolean validate(List<String> warnings) {
    return true;
  }

  private void defaultRename(IntrospectedTable introspectedTable) {
    introspectedTable.setSelectByPrimaryKeyStatementId("getById");
    introspectedTable.setDeleteByPrimaryKeyStatementId("deleteById");
    introspectedTable.setUpdateByPrimaryKeyStatementId("updateById");
    introspectedTable.setUpdateByPrimaryKeySelectiveStatementId("updateByIdSelective");
  }

  /**
   * int deleteByPrimaryKey(Long id);
   *
   * int insert(UserRegister record);
   *
   * int insertSelective(UserRegister record);
   *
   * UserRegister selectByPrimaryKey(Long id);
   *
   * int updateByPrimaryKeySelective(UserRegister record);
   *
   * int updateByPrimaryKey(UserRegister record);
   */
  @Override
  public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass,
      IntrospectedTable introspectedTable) {

    defaultRename(introspectedTable);


    for (Method method : interfaze.getMethods()) {
      String methodName = method.getName();

      if (methodName.contains("PrimaryKey")) {
        method.setName(methodName.replace("PrimaryKey", "Id"));
      }
      methodName = method.getName();
      if (methodName.contains("select")) {
        method.setName(methodName.replace("select", "get"));
      }
    }
    return true;

  }

  @Override
  public boolean sqlMapGenerated(GeneratedXmlFile sqlMap, IntrospectedTable introspectedTable) {

    defaultRename(introspectedTable);

    return true;
  }

  @Override
  public void setContext(Context context) {

    List<TableConfiguration> list = context.getTableConfigurations();


    for (TableConfiguration tableConfiguration : list) {
      tableConfiguration.setCountByExampleStatementEnabled(false);
      tableConfiguration.setDeleteByExampleStatementEnabled(false);
      tableConfiguration.setSelectByExampleStatementEnabled(false);
      tableConfiguration.setUpdateByExampleStatementEnabled(false);
    }
    super.setContext(context);
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

