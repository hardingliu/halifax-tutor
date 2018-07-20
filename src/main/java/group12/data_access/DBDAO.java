package group12.data_access;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import group12.registration.StudentSignupForm;
import group12.registration.TutorSignupForm;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

@Transactional
@Component
@ComponentScan
@ImportResource("classpath:spring.xml")
public class DBDAO implements IDataAccessObject {

    private DataSource dataSource;
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    Logger logger;

    public DBDAO() {
        logger = LogManager.getLogger("Logger DB");
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public ResultSet getResult(String query, String... parameters) {
        con = null;
        ps = null;
        rs = null;
        try {
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            for (int i = 0; i < parameters.length; i++) {
                ps.setString(i + 1, parameters[i]);
            }
            return ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Query:" + query + " Input:" + Arrays.toString(parameters) + " Error:" + e.getMessage());
        } finally {

        }
        return null;
    }

    public void closeConnections() throws SQLException {
        assert (con != null);
        con.close();
        assert (ps != null);
        ps.close();
        assert (rs != null);
        rs.close();
    }

    @Override
    public int countOfUserWithEmail(String email) {
        IsEmailNewSQLOperation isEmailNewSQLOperation = new IsEmailNewSQLOperation(email);
        int numberOfEmails = (int) isEmailNewSQLOperation.executeMysqlQuery();
        return numberOfEmails;
    }

    @Override
    public int countOfUserWithPhone(String phoneNumber) {
        IsPhoneNewSQLOperation isPhoneNewSQLOperation = new IsPhoneNewSQLOperation(phoneNumber);
        int numberOfPhones = (int) isPhoneNewSQLOperation.executeMysqlQuery();
        return numberOfPhones;
    }

    @Override
    public int countOfUserWithCreditCardNum(String creditCardNum) {
        IsCreditCardNewSQLOperation isCreditCardNewSQLOperation = new IsCreditCardNewSQLOperation(creditCardNum);
        int numberofCards = (int) isCreditCardNewSQLOperation.executeMysqlQuery();
        return numberofCards;
    }

    @Override
    public boolean saveStudent(Student student) {
        RegStudentSQLOperation regStudent = new RegStudentSQLOperation(student.getFirstName(), student.getLastName()
                , student.getEmail(), student.getPassword(), student.getSchool(), student.getPhoneNumber());
        int result = (int) regStudent.executeMysqlQuery();
        if (result == 1)
            return true;
        else
            return false;
    }

    @Override
    public Student getStudentByEmail(String email) {
        GetStudentByEmailSQLOperation getStudentByEmailSQLOperation = new GetStudentByEmailSQLOperation(email);
        Student student = (Student) getStudentByEmailSQLOperation.executeMysqlQuery();
        return student;
    }

    @Override
    public Tutor getTutorByEmail(String email) {
        GeTTutorEmailSQLOperation authorizeTutorSQLOperation = new GeTTutorEmailSQLOperation(email);
        Tutor tutor = (Tutor) authorizeTutorSQLOperation.executeMysqlQuery();
        return tutor;
    }


    @Override
    public boolean saveTutor(Tutor tutor) {
        RegTutorSQLOperation regTutorSQLOperation = new RegTutorSQLOperation(tutor.getFirstName(), tutor.getLastName()
                , tutor.getEmail(), tutor.getPassword(), tutor.getPhoneNumber(), tutor.getCreditCardHolder()
                , tutor.getCreditCardNum(), tutor.getExpiryDate(), tutor.getSecurityCode());
        int result = (int) regTutorSQLOperation.executeMysqlQuery();
        if (result == 1)
            return true;
        else return false;
    }

    @Override
    public int getStudentIDByEmail(String email) {
        GetStudentIdSQLOperation getStudentIdSQLOperation = new GetStudentIdSQLOperation(email);
        Student student = (Student) getStudentIdSQLOperation.executeMysqlQuery();
        return student.getStudentID();
    }

    @Override
    public int getTutorIDByEmail(String email) {
        GetTutorIdSQLOperation tutorIdSQLOperation = new GetTutorIdSQLOperation(email);
        Tutor tutor = (Tutor) tutorIdSQLOperation.executeMysqlQuery();
        return tutor.getTutorID();
    }

    @Override
    public boolean saveActivationCode(String code) {
        SaveActivationCodeSQLOperation saveActivationCodeSQLOperation = new SaveActivationCodeSQLOperation(code);
        int result = (int) saveActivationCodeSQLOperation.executeMysqlQuery();
        if (result == 1)
            return true;
        else
            return false;
    }

    @Override
    public boolean setStudentActivatedStatus(int id, String activateCode) {
        ActivateStudentSQLOperation activateStudentSQLOperation = new ActivateStudentSQLOperation(id);
        int result = (int) activateStudentSQLOperation.executeMysqlQuery();
        if (result == 1)
            return true;
        else return false;
    }

    @Override
    public boolean activateTutor(int id, String activateCode) {
        ActivateTutorSQLOperation activateTutorSQLOperation = new ActivateTutorSQLOperation(id);
        int result = (int) activateTutorSQLOperation.executeMysqlQuery();
        if (result == 1)
            return true;
        else
            return false;
    }

    @Override
    public boolean deleteStudent(int id) {
        DeleteStudentSQLOperation deleteStudentSQLOperation = new DeleteStudentSQLOperation(id);
        int result = (int) deleteStudentSQLOperation.executeMysqlQuery();
        if (result == 1)
            return true;
        else return false;
    }

    @Override
    public boolean deleteTutor(int id) {
        DeleteTutorSQLOperation deleteTutorSQLOperation = new DeleteTutorSQLOperation(id);
        int result = (int) deleteTutorSQLOperation.executeMysqlQuery();
        if (result == 1)
            return true;
        else return false;
    }

    @Override
    public boolean updateStudentPassword(String email, String newPassword) {
        UpdateStudentPasswordSQLOperation updateStudentPasswordSQLOperation = new UpdateStudentPasswordSQLOperation(email, newPassword);
        int result = (int) updateStudentPasswordSQLOperation.executeMysqlQuery();
        if (result == 1)
            return true;
        else return false;
    }

    @Override
    public boolean updateTutorPassword
            (String email, String newPassword) {
        UpdateTutorPasswordSQLOperation updateTutorPasswordSQLOperation =
                new UpdateTutorPasswordSQLOperation(email, newPassword);
        int result = (int) updateTutorPasswordSQLOperation.executeMysqlQuery();
        if (result == 1)
            return true;
        else return false;
    }


}
