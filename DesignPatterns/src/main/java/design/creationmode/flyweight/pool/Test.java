package design.creationmode.flyweight.pool;

import java.sql.Connection;

/**
 * 连接池测试
 */
public class Test {
    public static void main(String[] args) {
        ConnectionPool pool = new ConnectionPool();
        Connection conn = pool.getConnection();
        System.out.println(conn);
    }
}
