package group12.data_access;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IsPhoneNewSQLOperation extends SQLOperationTemplate {

    @Override
    String makeSQL() {
        return "SELECT IsPhoneNew(?)";
    }

    @Override
    PreparedStatement addParameters(PreparedStatement ps) throws SQLException {
        String phone = (String) getParameters().get(0);
        ps.setString(1, phone);
        return ps;
    }

    @Override
    Object extractResultSet(ResultSet rs) throws SQLException {
        int numberOfPhones;
        rs.next();
        numberOfPhones = rs.getInt(1);
        return numberOfPhones;
    }

    @Override
    ResultSet execute(PreparedStatement ps) throws SQLException {
        return ps.executeQuery();
    }
}
