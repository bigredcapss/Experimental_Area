package db.jdbc;

import db.Hus;
import db.HusDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HusDaoImpl implements HusDao{
	private DataSource datasource;
	@Override
	public void updateHus(Hus hus) {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			//从数据源中获取连接对象
			conn=datasource.getConnection();
			System.out.println("conn:"+conn);
			String sql = "select * from Hus";
			/*
			 * 指向sql操作
			 */
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				System.out.println(rs.getString(1));
				System.out.println(rs.getString(2));
			}
			//conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	public DataSource getDatasource() {
		return datasource;
	}
	public void setDatasource(DataSource datasource) {
		this.datasource = datasource;
	}

}
