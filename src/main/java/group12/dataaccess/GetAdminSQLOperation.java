package group12.dataaccess;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetAdminSQLOperation extends SQLOperationTemplate{

    private static Logger logger = LogManager.getLogger(GetAdminSQLOperation.class);

    public GetAdminSQLOperation(Object... parameters){
        super(parameters);
    }

    //don't change this for now.
    @Override
    protected String makeSQL() {
        return "SELECT * FROM Admin Where Email =?";
    }

    @Override
    protected PreparedStatement addParameters(PreparedStatement ps) throws SQLException {
        String email = (String) getParameters().get(0);
        ps.setString(1,email);
        return ps;
    }

    @Override
    protected ResultSet execute(PreparedStatement ps) throws SQLException{
        return ps.executeQuery();
    }

    @Override
    protected Object extractResultSet(ResultSet rs) throws SQLException {
        Admin admin = new Admin();
        admin.setEmail(rs.getString("Email"));
        admin.setPassword(rs.getString("Password"));
        return admin;
    }
}