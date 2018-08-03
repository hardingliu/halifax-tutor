package group12.tutorsetting.request;

public class UpdatePlanRequest {
    private String token;
    private int planNo;

    public void setToken(String token) {
        this.token = token;
    }

    public void setPlanNo(int planNo) {
        this.planNo = planNo;
    }

    public String getToken() {
        return token;
    }

    public int getPlanNo() {
        return planNo;
    }

    @Override
    public String toString() {
        return "token: " + token + " planNo: " + planNo;
    }
}
