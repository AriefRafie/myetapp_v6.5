package integrasi.rest.etanah.wpkl.ppt.entities;

import java.util.List;

public class PermohonanForm {

	private String kodAgensi;
	private String kodCawangan;
	private String idPengguna;
	private String kodTransaksi;
	private String tarikhMasaMohon;
	private String token;
	private String idTransaksi;
	private String noFailPTG;
	private String namaKementerian;
	private String namaProjek;
	private String noWarta;
	private String tarikhWarta;
	private List<HakmilikPermohonan> hakmilikPermohonanList = null;
	private List<Fail> failList = null;

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

	public String getIdTransaksi() {
		return idTransaksi;
	}

	public void setIdTransaksi(String idTransaksi) {
		this.idTransaksi = idTransaksi;
	}

	public String getNoFailPTG() {
		return noFailPTG;
	}

	public void setNoFailPTG(String noFailPTG) {
		this.noFailPTG = noFailPTG;
	}

	public String getNamaKementerian() {
		return namaKementerian;
	}

	public void setNamaKementerian(String namaKementerian) {
		this.namaKementerian = namaKementerian;
	}

	public String getNamaProjek() {
		return namaProjek;
	}

	public void setNamaProjek(String namaProjek) {
		this.namaProjek = namaProjek;
	}

	public String getNoWarta() {
		return noWarta;
	}

	public void setNoWarta(String noWarta) {
		this.noWarta = noWarta;
	}

	public String getTarikhWarta() {
		return tarikhWarta;
	}

	public void setTarikhWarta(String tarikhWarta) {
		this.tarikhWarta = tarikhWarta;
	}

	public List<HakmilikPermohonan> getHakmilikPermohonanList() {
		return hakmilikPermohonanList;
	}

	public void setHakmilikPermohonanList(
			List<HakmilikPermohonan> hakmilikPermohonanList) {
		this.hakmilikPermohonanList = hakmilikPermohonanList;
	}

	public List<Fail> getFailList() {
		return failList;
	}

	public void setFailList(List<Fail> failList) {
		this.failList = failList;
	}

}
