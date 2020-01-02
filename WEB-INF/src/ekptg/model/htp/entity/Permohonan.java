package ekptg.model.htp.entity;

import java.io.Serializable;

public class Permohonan implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 126688974001671012L;
	private long idPermohonan;
	private PfdFail pfdFail;
	private String noPermohonan;
	private String noPerserahan;
	private String tarikhSurat;
	private String tarikhTerima;
	private String tujuan;
	private long idMasuk;
	private String idNegeri;
	private String namaNegeri;
	private long idAgensi;
	private String namaAgensi;
	private String keterangan;

	//Penambahbaikan. Syaz. 01122014. Untuk function pengesahan (2 layer)
	/**
	 * N = BARU SAVE
	 * H = HANTAR
	 * S = SAH (TELAH DISAHKAN)
	 * 
	 * note : Hanya fail selepas penambahbaikan sahaja akan ada flag ini. Yang lama dikira telah disahkan dan boleh proceed seperti biasa.
	 */
	private String flagMohonFail;
	
	public String getNamaNegeri() {
		return namaNegeri;
	}
	public void setNamaNegeri(String namaNegeri) {
		this.namaNegeri = namaNegeri;
	}
	public String getIdNegeri() {
		return idNegeri;
	}
	public void setIdNegeri(String idNegeri) {
		this.idNegeri = idNegeri;
	}
	public long getIdPermohonan() {
		return idPermohonan;
	}
	public void setIdPermohonan(long idPermohonan) {
		this.idPermohonan = idPermohonan;
	}
	public void setIdPermohonan(String idPermohonan) {
		if(idPermohonan==""){
			idPermohonan = "0";
		}
		this.idPermohonan = Long.parseLong(idPermohonan);
		
	}
	public PfdFail getPfdFail() {
		return pfdFail;
	}
	public void setPfdFail(PfdFail pfdFail) {
		this.pfdFail = pfdFail;
	}
	public String getNoPermohonan() {
		return noPermohonan;
	}
	public void setNoPermohonan(String noPermohonan) {
		this.noPermohonan = noPermohonan;
	}
	public String getNoPerserahan() {
		return noPerserahan;
	}
	public void setNoPerserahan(String noPerserahan) {
		this.noPerserahan = noPerserahan;
	}
	public String getTarikhSurat() {
		return tarikhSurat;
	}
	public void setTarikhSurat(String tarikhSurat) {
		this.tarikhSurat = tarikhSurat;
	}
	public String getTarikhTerima() {
		return tarikhTerima;
	}
	public void setTarikhTerima(String tarikhTerima) {
		this.tarikhTerima = tarikhTerima;
	}
	public String getTujuan() {
		return tujuan;
	}
	public void setTujuan(String tujuan) {
		this.tujuan = tujuan;
	}
	public long getIdMasuk() {
		return idMasuk;
	}
	public void setIdMasuk(long idMasuk) {
		this.idMasuk = idMasuk;
	}
	
	public String getNamaAgensi() {
		return namaAgensi;
	}
	public void setNamaAgensi(String namaAgensi) {
		this.namaAgensi = namaAgensi;
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
	
	public String getKeterangan() {
		return keterangan;
	}
	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}
	public String getFlagMohonFail() {
		return flagMohonFail;
	}
	public void setFlagMohonFail(String flagMohonFail) {
		this.flagMohonFail = flagMohonFail;
	}
	
}
