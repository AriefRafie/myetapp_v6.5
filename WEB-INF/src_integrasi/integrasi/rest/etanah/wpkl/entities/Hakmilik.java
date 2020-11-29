
package integrasi.rest.etanah.wpkl.entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "IdHakmilik",
    "IdJenisHakmilik",
    "NoHakmilik",
    "KodNoLot",
    "NoPTNoLot",
    "Seksyen",
    "IdKategoriTanah",
    "IdNegeri",
    "IdDaerah",
    "IdMukim",
    "UnitLuas",
    "Luas",
    "SyaratNyata",
    "Sekatan",
    "TarafPegangan",
    "TarikhDaftarHakmilik",
    "StatusHakmilik",
    "PihakBerkepentinganList",
    "UrusanList",
    "KodAgensi",
    "KodCawangan",
    "IDPengguna",
    "KodTransaksi",
    "TarikhMasaMohon",
    "TarikhMasaBalas",
    "Token",
    "Catatan"
})
public class Hakmilik {

    @JsonProperty("IdHakmilik")
    private String idHakmilik;
    @JsonProperty("IdJenisHakmilik")
    private String idJenisHakmilik;
    @JsonProperty("NoHakmilik")
    private String noHakmilik;
    @JsonProperty("KodNoLot")
    private String kodNoLot;
    @JsonProperty("NoPTNoLot")
    private String noPTNoLot;
    @JsonProperty("Seksyen")
    private String seksyen;
    @JsonProperty("IdKategoriTanah")
    private String idKategoriTanah;
    @JsonProperty("IdNegeri")
    private String idNegeri;
    @JsonProperty("IdDaerah")
    private String idDaerah;
    @JsonProperty("IdMukim")
    private String idMukim;
    @JsonProperty("UnitLuas")
    private String unitLuas;
    @JsonProperty("Luas")
    private String luas;
    @JsonProperty("SyaratNyata")
    private String syaratNyata;
    @JsonProperty("Sekatan")
    private String sekatan;
    @JsonProperty("TarafPegangan")
    private String tarafPegangan;
    @JsonProperty("TarikhDaftarHakmilik")
    private String tarikhDaftarHakmilik;
    @JsonProperty("StatusHakmilik")
    private String statusHakmilik;
    @JsonProperty("PihakBerkepentinganList")
    private List<PihakBerkepentinganList> pihakBerkepentinganList = null;
    @JsonProperty("UrusanList")
    private List<UrusanList> urusanList = null;
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
    @JsonProperty("Catatan")
    private String catatan;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    

    @JsonProperty("IdHakmilik")
    public String getIdHakmilik() {
        return idHakmilik;
    }
    @JsonProperty("IdHakmilik")
    public void setIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
    }

    public Hakmilik withIdHakmilik(String idHakmilik) {
        this.idHakmilik = idHakmilik;
        return this;
    }

    @JsonProperty("IdJenisHakmilik")
    public String getIdJenisHakmilik() {
        return idJenisHakmilik;
    }
    @JsonProperty("IdJenisHakmilik")
    public void setIdJenisHakmilik(String idJenisHakmilik) {
        this.idJenisHakmilik = idJenisHakmilik;
    }

    public Hakmilik withIdJenisHakmilik(String idJenisHakmilik) {
        this.idJenisHakmilik = idJenisHakmilik;
        return this;
    }

    @JsonProperty("NoHakmilik")
    public String getNoHakmilik() {
        return noHakmilik;
    }

    @JsonProperty("NoHakmilik")
    public void setNoHakmilik(String noHakmilik) {
        this.noHakmilik = noHakmilik;
    }

    public Hakmilik withNoHakmilik(String noHakmilik) {
        this.noHakmilik = noHakmilik;
        return this;
    }

    @JsonProperty("KodNoLot")
    public String getKodNoLot() {
        return kodNoLot;
    }

    @JsonProperty("KodNoLot")
    public void setKodNoLot(String kodNoLot) {
        this.kodNoLot = kodNoLot;
    }

    public Hakmilik withKodNoLot(String kodNoLot) {
        this.kodNoLot = kodNoLot;
        return this;
    }

    @JsonProperty("NoPTNoLot")
    public String getNoPTNoLot() {
        return noPTNoLot;
    }

    @JsonProperty("NoPTNoLot")
    public void setNoPTNoLot(String noPTNoLot) {
        this.noPTNoLot = noPTNoLot;
    }

    public Hakmilik withNoPTNoLot(String noPTNoLot) {
        this.noPTNoLot = noPTNoLot;
        return this;
    }

    @JsonProperty("Seksyen")
    public String getSeksyen() {
        return seksyen;
    }

    @JsonProperty("Seksyen")
    public void setSeksyen(String seksyen) {
        this.seksyen = seksyen;
    }

    public Hakmilik withSeksyen(String seksyen) {
        this.seksyen = seksyen;
        return this;
    }

    @JsonProperty("IdKategoriTanah")
    public String getIdKategoriTanah() {
        return idKategoriTanah;
    }

    @JsonProperty("IdKategoriTanah")
    public void setIdKategoriTanah(String idKategoriTanah) {
        this.idKategoriTanah = idKategoriTanah;
    }

    public Hakmilik withIdKategoriTanah(String idKategoriTanah) {
        this.idKategoriTanah = idKategoriTanah;
        return this;
    }

    @JsonProperty("IdNegeri")
    public String getIdNegeri() {
        return idNegeri;
    }

    @JsonProperty("IdNegeri")
    public void setIdNegeri(String idNegeri) {
        this.idNegeri = idNegeri;
    }

    public Hakmilik withIdNegeri(String idNegeri) {
        this.idNegeri = idNegeri;
        return this;
    }

    @JsonProperty("IdDaerah")
    public String getIdDaerah() {
        return idDaerah;
    }

    @JsonProperty("IdDaerah")
    public void setIdDaerah(String idDaerah) {
        this.idDaerah = idDaerah;
    }

    public Hakmilik withIdDaerah(String idDaerah) {
        this.idDaerah = idDaerah;
        return this;
    }

    @JsonProperty("IdMukim")
    public String getIdMukim() {
        return idMukim;
    }

    @JsonProperty("IdMukim")
    public void setIdMukim(String idMukim) {
        this.idMukim = idMukim;
    }

    public Hakmilik withIdMukim(String idMukim) {
        this.idMukim = idMukim;
        return this;
    }

    @JsonProperty("UnitLuas")
    public String getUnitLuas() {
        return unitLuas;
    }

    @JsonProperty("UnitLuas")
    public void setUnitLuas(String unitLuas) {
        this.unitLuas = unitLuas;
    }

    public Hakmilik withUnitLuas(String unitLuas) {
        this.unitLuas = unitLuas;
        return this;
    }

    @JsonProperty("Luas")
    public String getLuas() {
        return luas;
    }

    @JsonProperty("Luas")
    public void setLuas(String luas) {
        this.luas = luas;
    }

    public Hakmilik withLuas(String luas) {
        this.luas = luas;
        return this;
    }

    @JsonProperty("SyaratNyata")
    public String getSyaratNyata() {
        return syaratNyata;
    }

    @JsonProperty("SyaratNyata")
    public void setSyaratNyata(String syaratNyata) {
        this.syaratNyata = syaratNyata;
    }

    public Hakmilik withSyaratNyata(String syaratNyata) {
        this.syaratNyata = syaratNyata;
        return this;
    }

    @JsonProperty("Sekatan")
    public String getSekatan() {
        return sekatan;
    }

    @JsonProperty("Sekatan")
    public void setSekatan(String sekatan) {
        this.sekatan = sekatan;
    }

    public Hakmilik withSekatan(String sekatan) {
        this.sekatan = sekatan;
        return this;
    }

    @JsonProperty("TarafPegangan")
    public String getTarafPegangan() {
        return tarafPegangan;
    }

    @JsonProperty("TarafPegangan")
    public void setTarafPegangan(String tarafPegangan) {
        this.tarafPegangan = tarafPegangan;
    }

    public Hakmilik withTarafPegangan(String tarafPegangan) {
        this.tarafPegangan = tarafPegangan;
        return this;
    }

    @JsonProperty("TarikhDaftarHakmilik")
    public String getTarikhDaftarHakmilik() {
        return tarikhDaftarHakmilik;
    }

    @JsonProperty("TarikhDaftarHakmilik")
    public void setTarikhDaftarHakmilik(String tarikhDaftarHakmilik) {
        this.tarikhDaftarHakmilik = tarikhDaftarHakmilik;
    }

    public Hakmilik withTarikhDaftarHakmilik(String tarikhDaftarHakmilik) {
        this.tarikhDaftarHakmilik = tarikhDaftarHakmilik;
        return this;
    }
    
    @JsonProperty("StatusHakmilik")
    public String getStatusHakmilik() {
        return statusHakmilik;
    }

    @JsonProperty("StatusHakmilik")
    public void setStatusHakmilik(String statusHakmilik) {
        this.statusHakmilik = statusHakmilik;
    }

    public Hakmilik withStatusHakmilik(String statusHakmilik) {
        this.statusHakmilik = statusHakmilik;
        return this;
    }

    @JsonProperty("PihakBerkepentinganList")
    public List<PihakBerkepentinganList> getPihakBerkepentinganList() {
        return pihakBerkepentinganList;
    }

    @JsonProperty("PihakBerkepentinganList")
    public void setPihakBerkepentinganList(List<PihakBerkepentinganList> pihakBerkepentinganList) {
        this.pihakBerkepentinganList = pihakBerkepentinganList;
    }

    public Hakmilik withPihakBerkepentinganList(List<PihakBerkepentinganList> pihakBerkepentinganList) {
        this.pihakBerkepentinganList = pihakBerkepentinganList;
        return this;
    }

    @JsonProperty("UrusanList")
    public List<UrusanList> getUrusanList() {
        return urusanList;
    }

    @JsonProperty("UrusanList")
    public void setUrusanList(List<UrusanList> urusanList) {
        this.urusanList = urusanList;
    }

    public Hakmilik withUrusanList(List<UrusanList> urusanList) {
        this.urusanList = urusanList;
        return this;
    }

    @JsonProperty("KodAgensi")
    public String getKodAgensi() {
        return kodAgensi;
    }

    @JsonProperty("KodAgensi")
    public void setKodAgensi(String kodAgensi) {
        this.kodAgensi = kodAgensi;
    }

    public Hakmilik withKodAgensi(String kodAgensi) {
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

    public Hakmilik withKodCawangan(String kodCawangan) {
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

    public Hakmilik withIDPengguna(String iDPengguna) {
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

    public Hakmilik withKodTransaksi(String kodTransaksi) {
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

    public Hakmilik withTarikhMasaMohon(String tarikhMasaMohon) {
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

    public Hakmilik withTarikhMasaBalas(String tarikhMasaBalas) {
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

    public Hakmilik withToken(String token) {
        this.token = token;
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

    public Hakmilik withCatatan(String catatan) {
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

    public Hakmilik withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
//20201124