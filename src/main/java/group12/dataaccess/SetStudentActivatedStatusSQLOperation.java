package group12.dataaccess;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SetStudentActivatedStatusSQLOperation extends SQLOperationTemplate {
    public SetStudentActivatedStatusSQLOperation(Object... parameters) {
        super(parameters);
    }

    @Override
    protected String makeSQL() {
        return "SELECT SetStudentActivatedStatus(?,?)";
    }

    @Override
    protected PreparedStatement addParameters(PreparedStatement ps) throws SQLException {
        int id = (int) getParameters().get(0);
        boolean activationStatus = (boolean) getParameters().get(1);
        ps.setInt(1, id);
        ps.setBoolean(2, activationStatus);
        return ps;
    }

    @Override
    protected Object extractResultSet(ResultSet rs) throws SQLException {
        return rs.getBoolean(1);
    }

    @Override
    protected ResultSet execute(PreparedStatement ps) throws SQLException {
        return ps.executeQuery();
    }
}
