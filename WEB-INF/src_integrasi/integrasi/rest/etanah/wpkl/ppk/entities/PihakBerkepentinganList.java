
package integrasi.rest.etanah.wpkl.ppk.entities;

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
    "IdJenisPengenalanPB",
    "NoPengenalanPB",
    "NamaPB",
    "BA",
    "BB"
})
public class PihakBerkepentinganList {

    @JsonProperty("IdJenisPengenalanPB")
    private String idJenisPengenalanPB;
    @JsonProperty("NoPengenalanPB")
    private String noPengenalanPB;
    @JsonProperty("NamaPB")
    private String namaPB;
    @JsonProperty("BA")
    private String bA;
    @JsonProperty("BB")
    private String bB;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("IdJenisPengenalanPB")
    public String getIdJenisPengenalanPB() {
        return idJenisPengenalanPB;
    }

    @JsonProperty("IdJenisPengenalanPB")
    public void setIdJenisPengenalanPB(String idJenisPengenalanPB) {
        this.idJenisPengenalanPB = idJenisPengenalanPB;
    }

    public PihakBerkepentinganList withIdJenisPengenalanPB(String idJenisPengenalanPB) {
        this.idJenisPengenalanPB = idJenisPengenalanPB;
        return this;
    }

    @JsonProperty("NoPengenalanPB")
    public String getNoPengenalanPB() {
        return noPengenalanPB;
    }

    @JsonProperty("NoPengenalanPB")
    public void setNoPengenalanPB(String noPengenalanPB) {
        this.noPengenalanPB = noPengenalanPB;
    }

    public PihakBerkepentinganList withNoPengenalanPB(String noPengenalanPB) {
        this.noPengenalanPB = noPengenalanPB;
        return this;
    }

    @JsonProperty("NamaPB")
    public String getNamaPB() {
        return namaPB;
    }

    @JsonProperty("NamaPB")
    public void setNamaPB(String namaPB) {
        this.namaPB = namaPB;
    }

    public PihakBerkepentinganList withNamaPB(String namaPB) {
        this.namaPB = namaPB;
        return this;
    }

    @JsonProperty("BA")
    public String getBA() {
        return bA;
    }

    @JsonProperty("BA")
    public void setBA(String bA) {
        this.bA = bA;
    }

    public PihakBerkepentinganList withBA(String bA) {
        this.bA = bA;
        return this;
    }

    @JsonProperty("BB")
    public String getBB() {
        return bB;
    }

    @JsonProperty("BB")
    public void setBB(String bB) {
        this.bB = bB;
    }

    public PihakBerkepentinganList withBB(String bB) {
        this.bB = bB;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public PihakBerkepentinganList withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
