package main.java.com.bluesky.zhz.server.mybatisgen.impl;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.config.JDBCConnectionConfiguration;
import org.mybatis.generator.internal.JDBCConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * https://blog.csdn.net/w980994974/article/details/76165235
 * */

/**
 * 
 */

public class DbPlugin extends PluginAdapter {
 
	@Override
	public void initialized(IntrospectedTable introspectedTable) {
		//System.err.println(JsonUtils.ToJSON(introspectedTable));
		JDBCConnectionConfiguration jdbcConnectionConfiguration=  context.getJdbcConnectionConfiguration();
		JDBCConnectionFactory connectionFactory = new JDBCConnectionFactory(jdbcConnectionConfiguration);
		
		Connection  connection=null;
		boolean isSqlServer=false;
		Map<String, String> hashText = new HashMap<>();
		try {
			connection=connectionFactory.getConnection();
			 java.sql.DatabaseMetaData databaseMetaData= connection.getMetaData();
			 String driverName=databaseMetaData.getDriverName();
		
			
			String tablename=introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime();
			 if (driverName.indexOf("SQL Server")>-1) {
				 isSqlServer=true;
				 String sql = "SELECT\n" +
		                   "\t B.name,convert(varchar(1000), C.\n" +
		                   "VALUE)\n" +
		                   "\tAS REMARKS\n" +
		                   "FROM\n" +
		                   "\tsys.tables A\n" +
		                   "INNER JOIN sys.columns B ON B.object_id = A.object_id\n" +
		                   "LEFT JOIN sys.extended_properties C ON C.major_id = B.object_id\n" +
		                   "AND C.minor_id = B.column_id\n" +
		                   "WHERE\n" +
		                   "\tA.name = ? ";
				 PreparedStatement prepareStatement = connection.prepareStatement(sql);
				 prepareStatement.setString(1, tablename);
				 ResultSet  sqlServerResultSet= prepareStatement.executeQuery();
			     while (sqlServerResultSet.next()) {
			    	 //System.err.println(sqlServerResultSet.getString("name")+","+sqlServerResultSet.getString("REMARKS"));
			    	 hashText.put(sqlServerResultSet.getString("name"), sqlServerResultSet.getString("REMARKS"));
				}
			     sqlServerResultSet.close();
				 
	    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (isSqlServer) {
			List<IntrospectedColumn> lIntrospectedColumns=	introspectedTable.getAllColumns();
			if (lIntrospectedColumns != null) {
				for (IntrospectedColumn introspectedColumn : lIntrospectedColumns) {
					String actualColumnName = introspectedColumn.getActualColumnName();
					introspectedColumn.setRemarks("");
					if (hashText.containsKey(actualColumnName) && hashText.get(actualColumnName) != null) {
						introspectedColumn.setRemarks(hashText.get(actualColumnName));
					}
				}
			}
		}
		super.initialized(introspectedTable);
	}

	public boolean validate(List<String> warnings) {
		return true;
	}
}