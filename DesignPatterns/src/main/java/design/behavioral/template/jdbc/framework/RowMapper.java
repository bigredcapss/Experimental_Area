package design.behavioral.template.jdbc.framework;

import java.sql.ResultSet;

public interface RowMapper<T> {

    T mapRow (ResultSet rs, int rowNum) throws Exception;

}
