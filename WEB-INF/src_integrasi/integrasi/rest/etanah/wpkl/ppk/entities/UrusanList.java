
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
    "NoPerserahan",
    "JenisUrusan",
    "TarikhDaftar"
})
public class UrusanList {

    @JsonProperty("NoPerserahan")
    private String noPerserahan;
    @JsonProperty("JenisUrusan")
    private String jenisUrusan;
    @JsonProperty("TarikhDaftar")
    private String tarikhDaftar;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("NoPerserahan")
    public String getNoPerserahan() {
        return noPerserahan;
    }

    @JsonProperty("NoPerserahan")
    public void setNoPerserahan(String noPerserahan) {
        this.noPerserahan = noPerserahan;
    }

    public UrusanList withNoPerserahan(String noPerserahan) {
        this.noPerserahan = noPerserahan;
        return this;
    }

    @JsonProperty("JenisUrusan")
    public String getJenisUrusan() {
        return jenisUrusan;
    }

    @JsonProperty("JenisUrusan")
    public void setJenisUrusan(String jenisUrusan) {
        this.jenisUrusan = jenisUrusan;
    }

    public UrusanList withJenisUrusan(String jenisUrusan) {
        this.jenisUrusan = jenisUrusan;
        return this;
    }

    @JsonProperty("TarikhDaftar")
    public String getTarikhDaftar() {
        return tarikhDaftar;
    }

    @JsonProperty("TarikhDaftar")
    public void setTarikhDaftar(String tarikhDaftar) {
        this.tarikhDaftar = tarikhDaftar;
    }

    public UrusanList withTarikhDaftar(String tarikhDaftar) {
        this.tarikhDaftar = tarikhDaftar;
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

    public UrusanList withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
