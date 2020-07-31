package models;

public class UserAuthRequest {
    private String httpMethod;

    private UserAuth params;

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public UserAuth getParams() {
        return params;
    }

    public void setParams(UserAuth params) {
        this.params = params;
    }
}
