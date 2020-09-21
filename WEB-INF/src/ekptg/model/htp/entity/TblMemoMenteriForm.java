package ekptg.model.htp.entity;


public class TblMemoMenteriForm {
	private Long idTblHtpMemoMenteri;
	private String noMemo;
	private String noFailSeksyen;
	private String catatan;
	private String tarikh;
	private Long idMasuk;
	private String tarikhMasuk;
	private Long idKemaskini;
	private String tarikhKemaskini;
	private String status;
	private Long idKategori;
	private String namaPemohon;
	private String maklumatTanah;
	private String asasPertimbangan;
	private String kadarPajakan;
	private String kesimpulan;

	public Long getIdKategori() {
		return idKategori;
	}
	public void setIdKategori(Long idKategori) {
		this.idKategori = idKategori;
	}

	public Long getIdTblHtpMemoMenteri() {
		return idTblHtpMemoMenteri;
	}
	public void setIdTblHtpMemoMenteri(Long idTblHtpMemoMenteri) {
		this.idTblHtpMemoMenteri = idTblHtpMemoMenteri;
	}
	public String getNoMemo() {
		return noMemo;
	}
	public void setNoMemo(String noMemo) {
		this.noMemo = noMemo;
	}
	public String getNoFailSeksyen() {
		return noFailSeksyen;
	}
	public void setNoFailSeksyen(String noFailSeksyen) {
		this.noFailSeksyen = noFailSeksyen;
	}
	public String getCatatan() {
		return catatan;
	}
	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}
	public String getTarikh() {
		return tarikh;
	}
	public void setTarikh(String tarikh) {
		this.tarikh = tarikh;
	}
	public Long getIdMasuk() {
		return idMasuk;
	}
	public void setIdMasuk(Long idMasuk) {
		this.idMasuk = idMasuk;
	}
	public String getTarikhMasuk() {
		return tarikhMasuk;
	}
	public void setTarikhMasuk(String tarikhMasuk) {
		this.tarikhMasuk = tarikhMasuk;
	}
	public Long getIdKemaskini() {
		return idKemaskini;
	}
	public void setIdKemaskini(Long idKemaskini) {
		this.idKemaskini = idKemaskini;
	}
	public String getTarikhKemaskini() {
		return tarikhKemaskini;
	}
	public void setTarikhKemaskini(String tarikhKemaskini) {
		this.tarikhKemaskini = tarikhKemaskini;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNamaPemohon() {
		return namaPemohon;
	}
	public void setNamaPemohon(String namaPemohon) {
		this.namaPemohon = namaPemohon;
	}
	public String getMaklumatTanah() {
		return maklumatTanah;
	}
	public void setMaklumatTanah(String maklumatTanah) {
		this.maklumatTanah = maklumatTanah;
	}
	public String getAsasPertimbangan() {
		return asasPertimbangan;
	}
	public void setAsasPertimbangan(String asasPertimbangan) {
		this.asasPertimbangan = asasPertimbangan;
	}
	public String getKadarPajakan() {
		return kadarPajakan;
	}
	public void setKadarPajakan(String kadarPajakan) {
		this.kadarPajakan = kadarPajakan;
	}
	public String getKesimpulan() {
		return kesimpulan;
	}
	public void setKesimpulan(String kesimpulan) {
		this.kesimpulan = kesimpulan;
	}

}
