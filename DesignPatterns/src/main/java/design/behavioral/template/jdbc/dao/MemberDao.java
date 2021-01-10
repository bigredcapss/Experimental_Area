package design.behavioral.template.jdbc.dao;


import design.behavioral.template.jdbc.entity.Member;
import design.behavioral.template.jdbc.framework.JdbcTemplate;
import design.behavioral.template.jdbc.framework.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.List;


public class MemberDao extends JdbcTemplate
{
    public MemberDao(DataSource dataSource) {
        super(dataSource);
    }

    /**
     * 钩子方法，该钩子方法是通过继承来实现的
     * @return
     */
    public List<?> selectAll(){
        String sql = "select * from t_member";
        return super.executeQuery(sql, new RowMapper<Member>() {
            public Member mapRow(ResultSet rs, int rowNum) throws Exception {
            Member member = new Member();
            //字段过多，原型模式
            member.setUsername(rs.getString("username"));
            member.setPassword(rs.getString("password"));
            member.setAge(rs.getInt("age"));
            member.setAddr(rs.getString("addr"));
            return member;
            }
        },null);
    }
}
