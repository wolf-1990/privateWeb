package main.java.com.bluesky.zhz.server.mybatisgen.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.InnerEnum;
import org.mybatis.generator.api.dom.java.JavaElement;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.internal.util.StringUtility;
import org.springframework.util.StringUtils;

/**
 * 
 * @author 康晓璞
 *
 */
public class MyGenerator implements CommentGenerator {
	private Properties properties;
	private boolean suppressDate;
	private boolean suppressAllComments;
	private boolean AddJPA;
	private String currentDateStr;

	public static Properties getKeyProperty() {
		Properties properties = new Properties();
		try {
			InputStream inputStream = MyGenerator.class.getClassLoader().getResourceAsStream("key.properties");
			if (inputStream != null) {
				InputStreamReader in = new InputStreamReader(inputStream, "utf-8");
				properties.load(in);
				inputStream.close();
				in.close();
			} else {
				System.err.println("key.properties为空");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException io) {
			io.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return properties;
	}

	public MyGenerator() {
		this.properties = getKeyProperty();
		this.suppressDate = false;
		this.suppressAllComments = false;
		this.currentDateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
	@Override
	public void addJavaFileComment(CompilationUnit compilationUnit) {
	}
	@Override
	public void addComment(XmlElement xmlElement) {
	}
	@Override
	public void addRootComment(XmlElement rootElement) {
	}
	@Override
	public void addConfigurationProperties(Properties properties) {
		this.properties.putAll(properties);

		if (properties.containsKey("AddJPA")) {
			String objAddJPA = (String) properties.get("AddJPA");
			if (objAddJPA.equals("true")) {
				this.AddJPA = true;
			}
		}
		this.suppressDate = StringUtility.isTrue(properties.getProperty("suppressDate"));

		this.suppressAllComments = StringUtility.isTrue(properties.getProperty("suppressAllComments"));
	}

	private String getAuthor(String author) {
		Properties properties = System.getProperties();
		assert(properties.get("user.name") != null);
		String computerName = properties.get("user.name").toString();
		return StringUtils.isEmpty(author) ? computerName : author;
	}
	
	protected void addJavadocTag(JavaElement javaElement, boolean markAsDoNotDelete) {
		javaElement.addJavaDocLine(" *");
		StringBuilder sb = new StringBuilder();
		sb.append(" * ");

		sb.append("@author" + getAuthor(this.properties.getProperty("author")));

		if (markAsDoNotDelete) {
			sb.append(" do_not_delete_during_merge");
		}
		String s = getDateString();
		if (s != null) {
			sb.append(' ');
			sb.append(s);
		}
		javaElement.addJavaDocLine(sb.toString());
	}

	protected String getDateString() {
		String result = null;
		if (!this.suppressDate) {
			result = this.currentDateStr;
		}
		return result;
	}
	@Override
	public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
		if (this.suppressAllComments) {
			return;
		}
//		StringBuilder sb = new StringBuilder();
//		innerClass.addJavaDocLine("/**");
//		sb.append(" * ");
//		sb.append(introspectedTable.getFullyQualifiedTable());
//		sb.append(" ");
//		sb.append(getDateString());
//		innerClass.addJavaDocLine(sb.toString().replace("\n", " "));
//		innerClass.addJavaDocLine(" */");
	}
	@Override
	public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {
//		if (this.suppressAllComments) {
//			return;
//		}
//		StringBuilder sb = new StringBuilder();
//		innerEnum.addJavaDocLine("/**");
//		sb.append(" * ");
//		sb.append(introspectedTable.getFullyQualifiedTable());
//		innerEnum.addJavaDocLine(sb.toString().replace("\n", " "));
//		innerEnum.addJavaDocLine(" */");
	}
	@Override
	public void addFieldComment(Field field, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		if (this.suppressAllComments) {
			return;
		}
		StringBuilder sb = new StringBuilder();
		field.addJavaDocLine("/**");
		sb.append(" * ");
		sb.append(introspectedColumn.getRemarks());
		field.addJavaDocLine(sb.toString().replace("\n", " "));
		field.addJavaDocLine(" */");
		if (this.AddJPA) {
			List<IntrospectedColumn> primaryKeyColumns = introspectedTable.getPrimaryKeyColumns();
			for (IntrospectedColumn col : primaryKeyColumns) {
				if (col.getActualColumnName().equals(introspectedColumn.getActualColumnName())) {
					field.addAnnotation("@Id");
				}
			}
			field.addAnnotation("@Column(name = \"" + introspectedColumn.getActualColumnName() + "\")");
		}
	}
	@Override
	public void addFieldComment(Field field, IntrospectedTable introspectedTable) {
		if (this.suppressAllComments) {
			return;
		}
//		StringBuilder sb = new StringBuilder();
//		field.addJavaDocLine("/**");
//		sb.append(" * ");
//		sb.append(introspectedTable.getFullyQualifiedTable());
//		field.addJavaDocLine(sb.toString().replace("\n", " "));
//		field.addJavaDocLine(" */");
	}
	@Override
	public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
		if (this.suppressAllComments) {
			return;
		}
//		method.addJavaDocLine("/**");
//		addJavadocTag(method, false);
//		method.addJavaDocLine(" */");
	}
	@Override
	public void addGetterComment(Method method, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		if (this.suppressAllComments) {
			return;
		}
		method.addJavaDocLine("/**");
		StringBuilder sb = new StringBuilder();
		sb.append(" * ");
		sb.append(introspectedColumn.getRemarks());
		method.addJavaDocLine(sb.toString().replace("\n", " "));
		sb.setLength(0);
		sb.append(" * @return ");
		sb.append(introspectedColumn.getActualColumnName());
		sb.append(" ");
		sb.append(introspectedColumn.getRemarks());
		method.addJavaDocLine(sb.toString().replace("\n", " "));
		method.addJavaDocLine(" */");
	}
	@Override
	public void addSetterComment(Method method, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		if (this.suppressAllComments) {
			return;
		}
		method.addJavaDocLine("/**");
		StringBuilder sb = new StringBuilder();
		sb.append(" * ");
		sb.append(introspectedColumn.getRemarks());
		method.addJavaDocLine(sb.toString().replace("\n", " "));
		Parameter parm = (Parameter) method.getParameters().get(0);
		sb.setLength(0);
		sb.append(" * @param ");
		sb.append(parm.getName());
		sb.append(" ");
		sb.append(introspectedColumn.getRemarks());
		method.addJavaDocLine(sb.toString().replace("\n", " "));
		method.addJavaDocLine(" */");
	}
	@Override
	public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
		if (this.suppressAllComments) {
			return;
		}
		StringBuilder sb = new StringBuilder();
		innerClass.addJavaDocLine("/**");
		sb.append(" * ");
		sb.append(introspectedTable.getFullyQualifiedTable());
		innerClass.addJavaDocLine(sb.toString().replace("\n", " "));
		sb.setLength(0);
		sb.append(" * @author addClassComment ");
		sb.append(getAuthor(this.properties.getProperty("author")));
		sb.append(" ");
		sb.append(this.currentDateStr);
		innerClass.addJavaDocLine(sb.toString());
		innerClass.addJavaDocLine(" */");
	}
	@Override
	public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		if (this.AddJPA) {
			topLevelClass.addImportedType("javax.persistence.Table");
			topLevelClass.addImportedType("javax.persistence.Id");
			topLevelClass.addImportedType("javax.persistence.Column");
			topLevelClass.addImportedType("javax.persistence.Transient");
		}

		if (this.suppressAllComments) {
			return;
		}
		StringBuilder sb = new StringBuilder();
		topLevelClass.addJavaDocLine("/**");
		sb.append(" * ");
		sb.append(introspectedTable.getFullyQualifiedTable());
		topLevelClass.addJavaDocLine(sb.toString().replace("\n", " "));
		sb.setLength(0);
		sb.append(" * @author ");
		sb.append(getAuthor(this.properties.getProperty("author")));
		sb.append(" ");
		sb.append(this.currentDateStr);
		topLevelClass.addJavaDocLine(sb.toString());
		topLevelClass.addJavaDocLine(" */");
		if (this.AddJPA)
			topLevelClass.addAnnotation("@Table(name = \"" + introspectedTable.getFullyQualifiedTable() + "\")");
	}

	public static String toLowerCaseFirstOne(String s) {
		if (Character.isLowerCase(s.charAt(0))) {
			return s;
		}
		return Character.toLowerCase(s.charAt(0)) + s.substring(1);
	}

	@Override
	public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable,
			Set<FullyQualifiedJavaType> imports) {
	}

	@Override
	public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> imports) {
	}

	@Override
	public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable,
			Set<FullyQualifiedJavaType> imports) {
	}

	@Override
	public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> imports) {
	}

	@Override
	public void addClassAnnotation(InnerClass innerClass, IntrospectedTable introspectedTable,
			Set<FullyQualifiedJavaType> imports) {
	}
}
