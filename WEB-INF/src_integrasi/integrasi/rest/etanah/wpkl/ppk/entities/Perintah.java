package integrasi.rest.etanah.wpkl.ppk.entities;

import java.util.List;

public class Perintah {

	private String kodAgensi;
	private String kodCawangan;
	private String idPengguna;
	private String kodTransaksi;
	private String tarikhMasaMohon;
	private String token;
	
	private String noFailPTG;
	private String namaSiMati;
	private String noKpSiMati;
	private String tarikhMati;
	private String noSijilMati;
	private String namaPemohon;
	private String noKPPemohon;
	private String alamatPemohon1;
	private String alamatPemohon2;
	private String alamatPemohon3;
	private String poskodPemohon;
	private String bandarPemohon;
	private String negeriPemohon;
	private String tempatBicara;
	private String alamatBicara1;
	private String alamatBicara2;
	private String alamatBicara3;
	private String poskodBicara;
	private String bandarBicara;
	private String negeriBicara;
	private String pegawaiBicara;
	private String tarikhPerintah;
	private List<HakmilikPerintah> hakmilikPerintahList = null;

	public String getKodAgensi() {
		return kodAgensi;
	}

	public void setKodAgensi(String kodAgensi) {
		this.kodAgensi = kodAgensi;
	}

	public String getKodCawangan() {
		return kodCawangan;
	}

	public void setKodCawangan(String kodCawangan) {
		this.kodCawangan = kodCawangan;
	}

	public String getIdPengguna() {
		return idPengguna;
	}

	public void setIdPengguna(String idPengguna) {
		this.idPengguna = idPengguna;
	}

	public String getKodTransaksi() {
		return kodTransaksi;
	}

	public void setKodTransaksi(String kodTransaksi) {
		this.kodTransaksi = kodTransaksi;
	}

	public String getTarikhMasaMohon() {
		return tarikhMasaMohon;
	}

	public void setTarikhMasaMohon(String tarikhMasaMohon) {
		this.tarikhMasaMohon = tarikhMasaMohon;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getNoFailPTG() {
		return noFailPTG;
	}

	public void setNoFailPTG(String noFailPTG) {
		this.noFailPTG = noFailPTG;
	}

	public String getNamaSiMati() {
		return namaSiMati;
	}

	public void setNamaSiMati(String namaSiMati) {
		this.namaSiMati = namaSiMati;
	}

	public String getNoKpSiMati() {
		return noKpSiMati;
	}

	public void setNoKpSiMati(String noKpSiMati) {
		this.noKpSiMati = noKpSiMati;
	}

	public String getTarikhMati() {
		return tarikhMati;
	}

	public void setTarikhMati(String tarikhMati) {
		this.tarikhMati = tarikhMati;
	}

	public String getNoSijilMati() {
		return noSijilMati;
	}

	public void setNoSijilMati(String noSijilMati) {
		this.noSijilMati = noSijilMati;
	}

	public String getNamaPemohon() {
		return namaPemohon;
	}

	public void setNamaPemohon(String namaPemohon) {
		this.namaPemohon = namaPemohon;
	}

	public String getNoKPPemohon() {
		return noKPPemohon;
	}

	public void setNoKPPemohon(String noKPPemohon) {
		this.noKPPemohon = noKPPemohon;
	}

	public String getAlamatPemohon1() {
		return alamatPemohon1;
	}

	public void setAlamatPemohon1(String alamatPemohon1) {
		this.alamatPemohon1 = alamatPemohon1;
	}

	public String getAlamatPemohon2() {
		return alamatPemohon2;
	}

	public void setAlamatPemohon2(String alamatPemohon2) {
		this.alamatPemohon2 = alamatPemohon2;
	}

	public String getAlamatPemohon3() {
		return alamatPemohon3;
	}

	public void setAlamatPemohon3(String alamatPemohon3) {
		this.alamatPemohon3 = alamatPemohon3;
	}

	public String getPoskodPemohon() {
		return poskodPemohon;
	}

	public void setPoskodPemohon(String poskodPemohon) {
		this.poskodPemohon = poskodPemohon;
	}

	public String getBandarPemohon() {
		return bandarPemohon;
	}

	public void setBandarPemohon(String bandarPemohon) {
		this.bandarPemohon = bandarPemohon;
	}

	public String getNegeriPemohon() {
		return negeriPemohon;
	}

	public void setNegeriPemohon(String negeriPemohon) {
		this.negeriPemohon = negeriPemohon;
	}

	public String getTempatBicara() {
		return tempatBicara;
	}

	public void setTempatBicara(String tempatBicara) {
		this.tempatBicara = tempatBicara;
	}

	public String getAlamatBicara1() {
		return alamatBicara1;
	}

	public void setAlamatBicara1(String alamatBicara1) {
		this.alamatBicara1 = alamatBicara1;
	}

	public String getAlamatBicara2() {
		return alamatBicara2;
	}

	public void setAlamatBicara2(String alamatBicara2) {
		this.alamatBicara2 = alamatBicara2;
	}

	public String getAlamatBicara3() {
		return alamatBicara3;
	}

	public void setAlamatBicara3(String alamatBicara3) {
		this.alamatBicara3 = alamatBicara3;
	}

	public String getPoskodBicara() {
		return poskodBicara;
	}

	public void setPoskodBicara(String poskodBicara) {
		this.poskodBicara = poskodBicara;
	}

	public String getBandarBicara() {
		return bandarBicara;
	}

	public void setBandarBicara(String bandarBicara) {
		this.bandarBicara = bandarBicara;
	}

	public String getNegeriBicara() {
		return negeriBicara;
	}

	public void setNegeriBicara(String negeriBicara) {
		this.negeriBicara = negeriBicara;
	}

	public String getPegawaiBicara() {
		return pegawaiBicara;
	}

	public void setPegawaiBicara(String pegawaiBicara) {
		this.pegawaiBicara = pegawaiBicara;
	}

	public String getTarikhPerintah() {
		return tarikhPerintah;
	}

	public void setTarikhPerintah(String tarikhPerintah) {
		this.tarikhPerintah = tarikhPerintah;
	}

	public List<HakmilikPerintah> getHakmilikPerintahList() {
		return hakmilikPerintahList;
	}

	public void setHakmilikPerintahList(
			List<HakmilikPerintah> hakmilikPerintahList) {
		this.hakmilikPerintahList = hakmilikPerintahList;
	}
}
