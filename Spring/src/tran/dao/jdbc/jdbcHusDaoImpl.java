package tran.dao.jdbc;

import db.Hus;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.datasource.DataSourceUtils;
import tran.dao.HusDao1;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class jdbcHusDaoImpl implements HusDao1{
	private DataSource datasource;
	@Override
	public void saveHus(Hus hus) {
			/*把dataSource注入dao层来直接使用,则需要注意下面几个点
			一定不要这样拿conn 因为我们要保证service开始事务
			和提交事务用的conn和dao层用到的conn是同一个对象
			Connection conn = datasource.getConnection();
			一定要这样去拿conn,因为DataSourceUtils是spring提供的工具类
			Connection conn = DataSourceUtils.getConnection(datasource);
		*/
		Connection conn =null;
		PreparedStatement ps=null;
		try {
			conn= DataSourceUtils.getConnection(datasource);
			String sql="insert into hus values(?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setLong(1, hus.getId());
			ps.setString(2, hus.getName());
			ps.setInt(3, hus.getAge());
			ps.execute();
		} catch (CannotGetJdbcConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public DataSource getDatasource() {
		return datasource;
	}
	public void setDatasource(DataSource datasource) {
		this.datasource = datasource;
	}

}
