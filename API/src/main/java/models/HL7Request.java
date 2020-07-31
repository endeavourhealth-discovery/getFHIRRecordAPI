package models;

public class HL7Request {
    private String httpMethod;

    private HL7Params params;

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public HL7Params getParams() {
        return params;
    }

    public void setParams(HL7Params params) {
        this.params = params;
    }
}
