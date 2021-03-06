package group12.login;

import group12.dataaccess.Tutor;
import group12.dataaccess.User;
import group12.tokenauth.IAccessToken;
import group12.tokenauth.JWTAccessToken;

public class TutorAuthStrategy implements IAuthenticationStrategy{

    private IAuthDAO authDAO;

    private String bannedTutorGoTo;
    private String inactiveTutorGoTo;
    private String activeAndUnbannedTutorGoTo;

    public void setAuthDAO(IAuthDAO authDAO){
        this.authDAO = authDAO;
    }

    public void setBannedTutorGoTo(String bannedTutorGoTo) {
        this.bannedTutorGoTo = bannedTutorGoTo;
    }

    public void setInactiveTutorGoTo(String inactiveTutorGoTo) {
        this.inactiveTutorGoTo = inactiveTutorGoTo;
    }

    public void setActiveAndUnbannedTutorGoTo(String activeAndUnbannedTutorGoTo) {
        this.activeAndUnbannedTutorGoTo = activeAndUnbannedTutorGoTo;
    }

    @Override
    public void authenticate(User tutor) {
        Tutor validTutor = authDAO.getTutorByEmail(tutor.getEmail());
        if(validTutor == null){
            tutor.setLoginResponse(new LoginResponse(AuthenticationResult.FAILURE,"Wrong Email"));
        }
        else if(validTutor.getPassword().equals(tutor.getPassword())){
            AuthenticationResult result = AuthenticationResult.SUCCESS;
            String message = "Welcome Back " + validTutor.getFirstName();
            String url = makeUrl(validTutor.isActivated(),validTutor.isBanned());
            String token = makeToken(tutor.getEmail());
            tutor.setLoginResponse(new LoginResponse(result, message, url, token));
        }
        else{
            tutor.setLoginResponse(new LoginResponse(AuthenticationResult.FAILURE,"Wrong Password"));
        }
    }

    private String makeToken(String email){
        IAccessToken tokenMaker = JWTAccessToken.getInstance();
        return tokenMaker.generateToken(email);
    }

    private String makeUrl(boolean isActivated, boolean isBanned){
        if(isBanned){
            return bannedTutorGoTo;
        }
        if(isActivated){
            return activeAndUnbannedTutorGoTo;
        }
        return inactiveTutorGoTo;
    }
}
