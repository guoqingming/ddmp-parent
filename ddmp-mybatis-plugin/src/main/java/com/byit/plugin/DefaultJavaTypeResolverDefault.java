package com.byit.plugin;

import java.sql.Types;
import java.util.Properties;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl;

public class DefaultJavaTypeResolverDefault extends JavaTypeResolverDefaultImpl {

  public DefaultJavaTypeResolverDefault() {

    super();
    super.typeMap.put(Types.SMALLINT, new JdbcTypeInformation("SMALLINT", //$NON-NLS-1$
        new FullyQualifiedJavaType(Integer.class.getName())));
    super.typeMap.put(Types.TINYINT, new JdbcTypeInformation("TINYINT", //$NON-NLS-1$
        new FullyQualifiedJavaType(Integer.class.getName())));
    typeMap.put(Types.BIT, new JdbcTypeInformation("BIT", //$NON-NLS-1$
        new FullyQualifiedJavaType(Integer.class.getName())));

  }

  @Override
  public void addConfigurationProperties(Properties properties) {

    super.addConfigurationProperties(properties);

  }



}
