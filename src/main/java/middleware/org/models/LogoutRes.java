
package middleware.org.models;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "modelId",
    "transactionId",
    "channel",
    "errorCode",
    "responseCode",
    "logoutDateTime"
})
public class LogoutRes {

    @JsonProperty("modelId")
    private Integer modelId;
    @JsonProperty("transactionId")
    private String transactionId;
    @JsonProperty("channel")
    private String channel;
    @JsonProperty("errorCode")
    private Integer errorCode;
    @JsonProperty("responseCode")
    private Integer responseCode;
    @JsonProperty("logoutDateTime")
    private String logoutDateTime;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("modelId")
    public Integer getModelId() {
        return modelId;
    }

    @JsonProperty("modelId")
    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    @JsonProperty("transactionId")
    public String getTransactionId() {
        return transactionId;
    }

    @JsonProperty("transactionId")
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @JsonProperty("channel")
    public String getChannel() {
        return channel;
    }

    @JsonProperty("channel")
    public void setChannel(String channel) {
        this.channel = channel;
    }

    @JsonProperty("errorCode")
    public Integer getErrorCode() {
        return errorCode;
    }

    @JsonProperty("errorCode")
    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    @JsonProperty("responseCode")
    public Integer getResponseCode() {
        return responseCode;
    }

    @JsonProperty("responseCode")
    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    @JsonProperty("logoutDateTime")
    public String getLogoutDateTime() {
        return logoutDateTime;
    }

    @JsonProperty("logoutDateTime")
    public void setLogoutDateTime(String logoutDateTime) {
        this.logoutDateTime = logoutDateTime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other);
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
