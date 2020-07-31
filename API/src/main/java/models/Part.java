package models;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "valuePeriod"
})
public class Part {

    @JsonProperty("name")
    private String name;
    @JsonProperty("valuePeriod")
    private ValuePeriod valuePeriod;
    @JsonProperty("valueBoolean")
    private boolean valueBoolean;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("valuePeriod")
    public ValuePeriod getValuePeriod() {
        return valuePeriod;
    }

    @JsonProperty("valuePeriod")
    public void setValuePeriod(ValuePeriod valuePeriod) {
        this.valuePeriod = valuePeriod;
    }

    @JsonProperty("valueBoolean")
    public boolean getValueBoolean() {
        return valueBoolean;
    }

    @JsonProperty("valueBoolean")
    public void setValueBoolean(boolean valueBoolean) {
        this.valueBoolean = valueBoolean;
    }


    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
