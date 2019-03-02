package com.byit.plugin;

import static org.mybatis.generator.internal.util.StringUtility.isTrue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.MergeConstants;
import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.internal.util.StringUtility;

public class NormalCommentGenerator extends PluginAdapter implements CommentGenerator {
  public boolean validate(List<String> warnings) {
    return false;
  }

  /** The properties. */
  private Properties properties;

  /** The suppress date. */
  private boolean suppressDate;

  /** The suppress all comments. */
  private boolean suppressAllComments;

  /**
   * The addition of table remark's comments.
   * If suppressAllComments is true, this option is ignored
   */
  private boolean addRemarkComments;

  private SimpleDateFormat dateFormat;

  /**
   * Instantiates a new default comment generator.
   */
  public NormalCommentGenerator() {
    super();
    properties = new Properties();
    suppressDate = false;
    suppressAllComments = false;
    addRemarkComments = false;
  }

  /* (non-Javadoc)
   * @see org.mybatis.generator.api.CommentGenerator#addJavaFileComment(org.mybatis.generator.api.dom.java.CompilationUnit)
   */
  public void addJavaFileComment(CompilationUnit compilationUnit) {
    // add no file level comments by default
  }

  /**
   * Adds a suitable comment to warn users that the element was generated, and
   * when it was generated.
   * xml格式生成
   *
   * @param xmlElement
   *            the xml element
   */
  public void addComment(XmlElement xmlElement) {
    if (suppressAllComments) {
      return;
    }

    StringBuilder sb = new StringBuilder();
    sb.append("<!-- generated "); //$NON-NLS-1$
    sb.append(MergeConstants.NEW_ELEMENT_TAG);
    String s = getDateString();
    if (s != null) {
      sb.append(" date: "); //$NON-NLS-1$
      sb.append(s);
    }

    sb.append(" -->");

    xmlElement.addElement(new TextElement(sb.toString()));
  }

  /* (non-Javadoc)
   * @see org.mybatis.generator.api.CommentGenerator#addRootComment(org.mybatis.generator.api.dom.xml.XmlElement)
   */
  public void addRootComment(XmlElement rootElement) {
    // add no document level comments by default
  }

  /* (non-Javadoc)
   * @see org.mybatis.generator.api.CommentGenerator#addConfigurationProperties(java.util.Properties)
   */
  public void addConfigurationProperties(Properties properties) {
    this.properties.putAll(properties);

    suppressDate = isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_DATE));

    suppressAllComments = isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_ALL_COMMENTS));

    addRemarkComments = isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_ADD_REMARK_COMMENTS));

    String dateFormatString = properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_DATE_FORMAT);
    if (StringUtility.stringHasValue(dateFormatString)) {
      dateFormat = new SimpleDateFormat(dateFormatString);
    }
  }

  /**
   * This method adds the custom javadoc tag for. You may do nothing if you do
   * not wish to include the Javadoc tag -
   * however, if you do not include the Javadoc tag then the Java merge
   * capability of the eclipse plugin will break.
   *
   * @param javaElement
   *            the java element
   * @param markAsDoNotDelete
   *            the mark as do not delete
   */
  protected void addJavadocTag(JavaElement javaElement, boolean markAsDoNotDelete) {
    javaElement.addJavaDocLine(" *"); //$NON-NLS-1$
    StringBuilder sb = new StringBuilder();
    sb.append(" * "); //$NON-NLS-1$
    sb.append(MergeConstants.NEW_ELEMENT_TAG);
    if (markAsDoNotDelete) {
      sb.append(" for merge"); //$NON-NLS-1$
    }
    String s = getDateString();
    if (s != null) {
      sb.append(' ');
      sb.append(s);
    }
    javaElement.addJavaDocLine(sb.toString());
  }

  /**
   * This method returns a formated date string to include in the Javadoc tag
   * and XML comments. You may return null if you do not want the date in
   * these documentation elements.
   *
   * @return a string representing the current timestamp, or null
   */
  protected String getDateString() {
    if (suppressDate) {
      return null;
    } else if (dateFormat != null) {
      return dateFormat.format(new Date());
    } else {
      return new Date().toString();
    }
  }

  /* (non-Javadoc)
   * @see org.mybatis.generator.api.CommentGenerator#addClassComment(org.mybatis.generator.api.dom.java.InnerClass, org.mybatis.generator.api.IntrospectedTable)
   */
  public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
    if (suppressAllComments) {
      return;
    }

    innerClass.addJavaDocLine("/**"); //$NON-NLS-1$
    innerClass.addJavaDocLine(" * "); //$NON-NLS-1$
    //addJavadocTag(innerClass, false);
    innerClass.addJavaDocLine(" */"); //$NON-NLS-1$
  }

  /* (non-Javadoc)
   * @see org.mybatis.generator.api.CommentGenerator#addTopLevelClassComment(org.mybatis.generator.api.dom.java.TopLevelClass, org.mybatis.generator.api.IntrospectedTable)
   */

  public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
    if (suppressAllComments || !addRemarkComments) {
      return;
    }


    StringBuilder sb = new StringBuilder();

    topLevelClass.addJavaDocLine("/**"); //$NON-NLS-1$
    String remarks = introspectedTable.getRemarks();
    if (addRemarkComments && StringUtility.stringHasValue(remarks)) {
      String[] remarkLines = remarks.split(System.getProperty("line.separator")); //$NON-NLS-1$
      for (String remarkLine : remarkLines) {
        topLevelClass.addJavaDocLine(" *   " + remarkLine); //$NON-NLS-1$
      }
    }
    sb.append(" *  "); //$NON-NLS-1$
    topLevelClass.addJavaDocLine(sb.toString());
    //addJavadocTag(topLevelClass, true);
    topLevelClass.addJavaDocLine(" */"); //$NON-NLS-1$
    topLevelClass.addAnnotation("@ApiModel");
    topLevelClass.addImportedType(new FullyQualifiedJavaType("io.swagger.annotations.ApiModel"));
    topLevelClass.addImportedType(new FullyQualifiedJavaType("io.swagger.annotations.ApiModelProperty"));
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
  }

  /* (non-Javadoc)
   * @see org.mybatis.generator.api.CommentGenerator#addEnumComment(org.mybatis.generator.api.dom.java.InnerEnum, org.mybatis.generator.api.IntrospectedTable)
   */
  public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {
    if (suppressAllComments) {
      return;
    }

    StringBuilder sb = new StringBuilder();

    innerEnum.addJavaDocLine("/**"); //$NON-NLS-1$
    innerEnum.addJavaDocLine(" * This enum was generated by MyBatis Generator."); //$NON-NLS-1$

    sb.append(" * This enum corresponds to the database table "); //$NON-NLS-1$
    sb.append(introspectedTable.getFullyQualifiedTable());
    innerEnum.addJavaDocLine(sb.toString());

    //addJavadocTag(innerEnum, false);

    innerEnum.addJavaDocLine(" */"); //$NON-NLS-1$
  }

  /* (non-Javadoc)
   * @see org.mybatis.generator.api.CommentGenerator#addFieldComment(org.mybatis.generator.api.dom.java.Field, org.mybatis.generator.api.IntrospectedTable, org.mybatis.generator.api.IntrospectedColumn)
   */
  public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
    if (suppressAllComments) {
      return;
    }

    String remarks = introspectedColumn.getRemarks();
    field.addJavaDocLine("/**"); //$NON-NLS-1$

    if (addRemarkComments && StringUtility.stringHasValue(remarks)) {
      String[] remarkLines = remarks.split(System.getProperty("line.separator")); //$NON-NLS-1$
      for (String remarkLine : remarkLines) {
        field.addJavaDocLine(" * " + remarkLine); //$NON-NLS-1$
      }
    }
    //addJavadocTag(field, false);

    field.addJavaDocLine(" */"); //$NON-NLS-1$
    String annotation = "@ApiModelProperty(\""+remarks+ "\")";
    if(annotation != null && !"".equals(annotation)){
      field.addAnnotation(annotation);
    }
  }

  /* (non-Javadoc)
   * @see org.mybatis.generator.api.CommentGenerator#addFieldComment(org.mybatis.generator.api.dom.java.Field, org.mybatis.generator.api.IntrospectedTable)
   */
  public void addFieldComment(Field field, IntrospectedTable introspectedTable) {
    if (suppressAllComments) {
      return;
    }

    field.addJavaDocLine("/**"); //$NON-NLS-1$

    //addJavadocTag(field, false);

    field.addJavaDocLine(" */"); //$NON-NLS-1$
  }

  /* (non-Javadoc)
   * @see org.mybatis.generator.api.CommentGenerator#addGeneralMethodComment(org.mybatis.generator.api.dom.java.Method, org.mybatis.generator.api.IntrospectedTable)
   */
  public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
    if (suppressAllComments) {
      return;
    }

    //		method.addJavaDocLine("/**"); //$NON-NLS-1$
    //		addJavadocTag(method, false);
    //
    //		method.addJavaDocLine(" */"); //$NON-NLS-1$
  }

  /* (non-Javadoc)
   * @see org.mybatis.generator.api.CommentGenerator#addGetterComment(org.mybatis.generator.api.dom.java.Method, org.mybatis.generator.api.IntrospectedTable, org.mybatis.generator.api.IntrospectedColumn)
   */
  public void addGetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
    if (suppressAllComments) {
      return;
    }

    //		method.addJavaDocLine("/**"); //$NON-NLS-1$
    //
    //		addJavadocTag(method, false);
    //
    //		method.addJavaDocLine(" */"); //$NON-NLS-1$
  }

  /* (non-Javadoc)
   * @see org.mybatis.generator.api.CommentGenerator#addSetterComment(org.mybatis.generator.api.dom.java.Method, org.mybatis.generator.api.IntrospectedTable, org.mybatis.generator.api.IntrospectedColumn)
   */
  public void addSetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
    if (suppressAllComments) {

      return;
    }

    //		method.addJavaDocLine("/**"); //$NON-NLS-1$
    //
    //		addJavadocTag(method, false);
    //
    //		method.addJavaDocLine(" */"); //$NON-NLS-1$
  }

  /* (non-Javadoc)
   * @see org.mybatis.generator.api.CommentGenerator#addClassComment(org.mybatis.generator.api.dom.java.InnerClass, org.mybatis.generator.api.IntrospectedTable, boolean)
   */
  public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
    if (suppressAllComments) {
      return;
    }

    StringBuilder sb = new StringBuilder();

    innerClass.addJavaDocLine("/**"); //$NON-NLS-1$
    //innerClass.addJavaDocLine(" * "); //$NON-NLS-1$

    //addJavadocTag(innerClass, markAsDoNotDelete);

    innerClass.addJavaDocLine(" */"); //$NON-NLS-1$
  }

  @Override
  public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

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
