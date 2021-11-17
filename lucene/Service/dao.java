package lucene.Service;

import lucene.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
@Repository
public class dao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public User SelectRecruit(int id){
        final User re=new User();
        String sql= "SELECT * FROM   recruitmentrequirements WHERE id=? ";
        jdbcTemplate.query(sql, new Object[]{id}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                re.setOccupationalCategory(rs.getString(3));
                re.setRemainingPositions(rs.getString(4));
                re.setSalaryRequirements(rs.getString(5));
                re.setCompany(rs.getString(2));
                re.setNumber(rs.getString(6));
            }
        });
        return re;
    }

}
