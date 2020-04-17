package ekptg.model.htp.entity;

import java.io.Serializable;

import ekptg.model.entities.Kementerian;

public class PfdFail implements Serializable {
	private long idFail;
	private long idTarafKeselamatan;
	private long idSeksyen;
	private long idUrusan;
	private long idSubUrusan;
	private String tarikhDaftarFail;
	private String flagFail;
	private String noFail;
	private String noFailRoot;
	private long idLokasiFail;
	private long idNegeri;
	private long idStatus;
	private long idKementerian;
	private long idFarahasat;
	private long idMasuk;
	private long idKemaskini;
	private String tarikhKemaskini;
	private String namaKementerian;
	private String namaUrusan;
	private String namaSuburusan;
	private String kodSuburusan;
	private String tajukFail;
	private Kementerian kementerian; 
	private int bilFail; 

	
	public long getIdFail() {
		return idFail;
	}
	public void setIdFail(long idFail) {
		this.idFail = idFail;
	}
	public long getIdTarafKeselamatan() {
		return idTarafKeselamatan;
	}
	public void setIdTarafKeselamatan(long idTarafKeselamatan) {
		this.idTarafKeselamatan = idTarafKeselamatan;
	}
	public void setIdTarafKeselamatan(String idTarafKeselamatan) {
		if(idTarafKeselamatan.equals(""))
			idTarafKeselamatan="0";
		this.idTarafKeselamatan = Long.parseLong(idTarafKeselamatan);
	}
	public long getIdSeksyen() {
		return idSeksyen;
	}
	public void setIdSeksyen(long idSeksyen) {
		this.idSeksyen = idSeksyen;
	}
	public long getIdUrusan() {
		return idUrusan;
	}
	public void setIdUrusan(long idUrusan) {
		this.idUrusan = idUrusan;
	}
	public long getIdSubUrusan() {
		return idSubUrusan;
	}
	public void setIdSubUrusan(long idSubUrusan) {
		this.idSubUrusan = idSubUrusan;
	}
	public void setIdSubUrusan(String idSubUrusan) {
		if(idSubUrusan.equals(""))
			idSubUrusan ="0";
		this.idSubUrusan =  Long.parseLong(idSubUrusan);
	}
	public String getTarikhDaftarFail() {
		return tarikhDaftarFail;
	}
	public void setTarikhDaftarFail(String tarikhDaftarFail) {
		this.tarikhDaftarFail = tarikhDaftarFail;
	}
	public String getFlagFail() {
		return flagFail;
	}
	public void setFlagFail(String flagFail) {
		this.flagFail = flagFail;
	}
	public String getNoFail() {
		return noFail;
	}
	public void setNoFail(String noFail) {
		this.noFail = noFail;
	}
	public String getNoFailRoot() {
		return noFailRoot;
	}
	public void setNoFailRoot(String noFailRoot) {
		this.noFailRoot = noFailRoot;
	}
	public long getIdLokasiFail() {
		return idLokasiFail;
	}
	public void setIdLokasiFail(long idLokasiFail) {
		this.idLokasiFail = idLokasiFail;
	}
	public long getIdNegeri() {
		return idNegeri;
	}
	public void setIdNegeri(long idNegeri) {
		this.idNegeri = idNegeri;
	}
	public void setIdNegeri(String idNegeri) {
		if(idNegeri == null || idNegeri.equals(""))
			idNegeri = "0";
		this.idNegeri = Long.parseLong(idNegeri);
	}
	public long getIdStatus() {
		return idStatus;
	}
	public void setIdStatus(long idStatus) {
		this.idStatus = idStatus;
	}
	public long getIdKementerian() {
		return idKementerian;
	}
	public void setIdKementerian(long idKementerian) {
		this.idKementerian = idKementerian;
	}
	public void setIdKementerian(String idKementerian) {
		if(idKementerian.equals(""))
			idKementerian ="0";
		this.idKementerian = Long.parseLong(idKementerian);
	}
	public long getIdFarahasat() {
		return idFarahasat;
	}
	public void setIdFarahasat(long idFarahasat) {
		this.idFarahasat = idFarahasat;
	}
	public long getIdMasuk() {
		return idMasuk;
	}
	public void setIdMasuk(long idMasuk) {
		this.idMasuk = idMasuk;
	}
	public long getIdKemaskini() {
		return idKemaskini;
	}
	public void setIdKemaskini(long idKemaskini) {
		this.idKemaskini = idKemaskini;
	}
	public String getTarikhKemaskini() {
		return tarikhKemaskini;
	}
	public void setTarikhKemaskini(String tarikhKemaskini) {
		this.tarikhKemaskini = tarikhKemaskini;
	}
	public String getNamaKementerian() {
		return namaKementerian;
	}
	public void setNamaKementerian(String namaKementerian) {
		this.namaKementerian = namaKementerian;
	}
	
	public String getNamaUrusan() {
		return namaUrusan;
	}
	public void setNamaUrusan(String namaUrusan) {
		this.namaUrusan = namaUrusan;
	}
	
	public String getNamaSuburusan() {
		return namaSuburusan;
	}
	public void setNamaSuburusan(String namaSuburusan) {
		this.namaSuburusan = namaSuburusan;
	}
	public String getKodSuburusan() {
		return kodSuburusan;
	}
	public void setKodSuburusan(String kodSuburusan) {
		this.kodSuburusan = kodSuburusan;
	}
	public Kementerian getKementerian() {
		return this.kementerian;
	}

	public void setKementerian(Kementerian kementerian) {
		this.kementerian = kementerian;
	}

	public String getTajukFail() {
		return tajukFail;
	}
	public void setTajukFail(String tajukFail) {
		this.tajukFail = tajukFail;
	}
	
	public int getBil() {
		return bilFail;
	}
	public void setBil(int bilFail) {
		this.bilFail = bilFail;
	}
	
}
