package group12.data_access;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetStudentSQLOperation extends SQLOperationTemplate {

    private static Logger logger = LogManager.getLogger(GetStudentSQLOperation.class);

    public GetStudentSQLOperation(Object... parameters){
        super(parameters);
    }

    @Override
    String makeSQL() {
        return "SELECT * FROM Student Where Email =?";
    }

    @Override
    PreparedStatement addParameters(PreparedStatement ps) throws SQLException{
        String email = (String) getParameters().get(0);
        ps.setString(1,email);
        return ps;
    }

    @Override
    Object extractResultSet(ResultSet rs) throws SQLException {
        Student student = new Student();
        student.setEmail(rs.getString("Email"));
        student.setPassword(rs.getString("Password"));
        student.setStudentID(rs.getInt("ID"));
        student.setFirstName(rs.getString("FirstName"));
        student.setLastName(rs.getString("LastName"));
        student.setActivated(rs.getBoolean("AccountActivation"));
        student.setBanned(rs.getBoolean("Banned"));
        student.setPhoneNumber(rs.getString("PhoneNumber"));
        student.setSchool(rs.getString("School"));
        return student;
    }

}