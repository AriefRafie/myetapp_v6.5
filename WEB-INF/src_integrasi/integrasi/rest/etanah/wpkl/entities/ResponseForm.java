
package integrasi.rest.etanah.wpkl.entities;

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
    "KodAgensi",
    "KodCawangan",
    "IDPengguna",
    "KodTransaksi",
    "TarikhMasaMohon",
    "TarikhMasaBalas",
    "Token",
    "KodRalat",
    "Catatan"
})
public class ResponseForm {

    @JsonProperty("KodAgensi")
    private String kodAgensi;
    @JsonProperty("KodCawangan")
    private String kodCawangan;
    @JsonProperty("IDPengguna")
    private String iDPengguna;
    @JsonProperty("KodTransaksi")
    private String kodTransaksi;
    @JsonProperty("TarikhMasaMohon")
    private String tarikhMasaMohon;
    @JsonProperty("TarikhMasaBalas")
    private String tarikhMasaBalas;
    @JsonProperty("Token")
    private String token;
    @JsonProperty("KodRalat")
    private String kodRalat;
    @JsonProperty("Catatan")
    private String catatan;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("KodAgensi")
    public String getKodAgensi() {
        return kodAgensi;
    }

    @JsonProperty("KodAgensi")
    public void setKodAgensi(String kodAgensi) {
        this.kodAgensi = kodAgensi;
    }

    public ResponseForm withKodAgensi(String kodAgensi) {
        this.kodAgensi = kodAgensi;
        return this;
    }

    @JsonProperty("KodCawangan")
    public String getKodCawangan() {
        return kodCawangan;
    }

    @JsonProperty("KodCawangan")
    public void setKodCawangan(String kodCawangan) {
        this.kodCawangan = kodCawangan;
    }

    public ResponseForm withKodCawangan(String kodCawangan) {
        this.kodCawangan = kodCawangan;
        return this;
    }

    @JsonProperty("IDPengguna")
    public String getIDPengguna() {
        return iDPengguna;
    }

    @JsonProperty("IDPengguna")
    public void setIDPengguna(String iDPengguna) {
        this.iDPengguna = iDPengguna;
    }

    public ResponseForm withIDPengguna(String iDPengguna) {
        this.iDPengguna = iDPengguna;
        return this;
    }

    @JsonProperty("KodTransaksi")
    public String getKodTransaksi() {
        return kodTransaksi;
    }

    @JsonProperty("KodTransaksi")
    public void setKodTransaksi(String kodTransaksi) {
        this.kodTransaksi = kodTransaksi;
    }

    public ResponseForm withKodTransaksi(String kodTransaksi) {
        this.kodTransaksi = kodTransaksi;
        return this;
    }

    @JsonProperty("TarikhMasaMohon")
    public String getTarikhMasaMohon() {
        return tarikhMasaMohon;
    }

    @JsonProperty("TarikhMasaMohon")
    public void setTarikhMasaMohon(String tarikhMasaMohon) {
        this.tarikhMasaMohon = tarikhMasaMohon;
    }

    public ResponseForm withTarikhMasaMohon(String tarikhMasaMohon) {
        this.tarikhMasaMohon = tarikhMasaMohon;
        return this;
    }

    @JsonProperty("TarikhMasaBalas")
    public String getTarikhMasaBalas() {
        return tarikhMasaBalas;
    }

    @JsonProperty("TarikhMasaBalas")
    public void setTarikhMasaBalas(String tarikhMasaBalas) {
        this.tarikhMasaBalas = tarikhMasaBalas;
    }

    public ResponseForm withTarikhMasaBalas(String tarikhMasaBalas) {
        this.tarikhMasaBalas = tarikhMasaBalas;
        return this;
    }

    @JsonProperty("Token")
    public String getToken() {
        return token;
    }

    @JsonProperty("Token")
    public void setToken(String token) {
        this.token = token;
    }

    public ResponseForm withToken(String token) {
        this.token = token;
        return this;
    }
    
    @JsonProperty("KodRalat")
    public String getKodRalat() {
        return kodRalat;
    }

    @JsonProperty("KodRalat")
    public void setKodRalat(String kodRalat) {
        this.kodRalat = kodRalat;
    }

    public ResponseForm withKodRalat(String kodRalat) {
        this.kodRalat = kodRalat;
        return this;
    }

    @JsonProperty("Catatan")
    public String getCatatan() {
        return catatan;
    }

    @JsonProperty("Catatan")
    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public ResponseForm withCatatan(String catatan) {
        this.catatan = catatan;
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

    public ResponseForm withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }
}
