package ekptg.model.htp.entity;

import java.io.Serializable;

public class HtpPermohonan implements Serializable {
	private long idHtpPermohonan;
	private Permohonan permohonan;
	private long idAgensi;
	private long idJenisTanah;
	private long idPegawai;
	private String idDaerah;
	private int bil;
	private String noRujukanKJP;
	private String noRujukanLain;
	private String noRujukanUPT;
	private String noRujukanPTG;
	private String noRujukanPTD;
	private String tarikhAkhir;
	private String namaAgensi;
	private String statusPermohonan;
	public long getIdHtpPermohonan() {
		return idHtpPermohonan;
	}
	public void setIdHtpPermohonan(long idHtpPermohonan) {
		this.idHtpPermohonan = idHtpPermohonan;
	}
	public void setIdHtpPermohonan(String idHtpPermohonan) {
		if(idHtpPermohonan==""){
			idHtpPermohonan = "0";
		}
		this.idHtpPermohonan = Long.parseLong(idHtpPermohonan);
	}
	public Permohonan getPermohonan() {
		return permohonan;
	}
	public void setPermohonan(Permohonan permohonan) {
		this.permohonan = permohonan;
	}
	public long getIdAgensi() {
		return idAgensi;
	}
	public void setIdAgensi(long idAgensi) {
		this.idAgensi = idAgensi;
	}
	public void setIdAgensi(String idAgensi) {
		if(idAgensi == null || idAgensi.equals(""))
			idAgensi = "0";
		this.idAgensi = Long.parseLong(idAgensi);
	}
	public long getIdJenisTanah() {
		return idJenisTanah;
	}
	public void setIdJenisTanah(long idJenisTanah) {
		this.idJenisTanah = idJenisTanah;
	}
	public void setIdJenisTanah(String idJenisTanah) {
		if(idJenisTanah.equals(""))
			idJenisTanah = "0";
		this.idJenisTanah = Long.parseLong(idJenisTanah);
	}
	public long getIdPegawai() {
		return idPegawai;
	}
	public void setIdPegawai(long idPegawai) {
		this.idPegawai = idPegawai;
	}
	public String getNoRujukanKJP() {
		return noRujukanKJP;
	}
	public void setNoRujukanKJP(String noRujukanKJP) {
		this.noRujukanKJP = noRujukanKJP;
	}
	public String getNoRujukanLain() {
		return noRujukanLain;
	}
	public void setNoRujukanLain(String noRujukanLain) {
		this.noRujukanLain = noRujukanLain;
	}
	public String getTarikhAkhir() {
		return tarikhAkhir;
	}
	public void setTarikhAkhir(String tarikhAkhir) {
		this.tarikhAkhir = tarikhAkhir;
	}
	public String getNamaAgensi() {
		return namaAgensi;
	}
	public void setNamaAgensi(String namaAgensi) {
		this.namaAgensi = namaAgensi;
	}
	public String getStatusPermohonan() {
		return statusPermohonan;
	}
	public void setStatusPermohonan(String statusPermohonan) {
		this.statusPermohonan = statusPermohonan;
	}
	public String getIdDaerah() {
		if(idDaerah == null ||idDaerah.equals(""))
			idDaerah="0";
		return idDaerah;
	}
	public void setIdDaerah(String idDaerah) {
		this.idDaerah = idDaerah;
	}
	public String getNoRujukanUPT() {
		return noRujukanUPT;
	}
	public void setNoRujukanUPT(String noRujukanUPT) {
		this.noRujukanUPT = noRujukanUPT;
	}	
	public String getNoRujukanPTG() {
		return noRujukanPTG;
	}
	public void setNoRujukanPTG(String noRujukanPTG) {
		this.noRujukanPTG = noRujukanPTG;
	}
	public String getNoRujukanPTD() {
		return noRujukanPTD;
	}
	public void setNoRujukanPTD(String noRujukanPTD) {
		this.noRujukanPTD = noRujukanPTD;
	}
	public int getBil() {
		return bil;
	}
	public void setBil(int bil) {
		this.bil = bil;
	}	
	 
	
}
