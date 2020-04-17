package ekptg.model.htp.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import ekptg.helpers.Utils;


public class Penyewaan implements java.io.Serializable {

	// Fields

	private Long id;
	private Permohonan permohonan;
	private String tujuan;
	private String tempoh;
	private Date tarikhMula = new Date("01/01/1900");
	private Date tarikhTamat = new Date("01/01/1900");
	private String caraBayar;
	private String kategoriCukai;
	private Double kadarCukai;
	private Double kadar;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblhtppajakankadars = new HashSet(0);
	private HtpPerjanjian htpPerjanjian;
	private Pemohon pemohon;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	// Constructors

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long idPajakan) {
		this.id = idPajakan;
	}

	public Permohonan getPermohonan() {
		return permohonan;
	}
	public void setPermohonan(Permohonan permohonan) {
		this.permohonan = permohonan;
	}

	public String getTujuan() {
		return this.tujuan;
	}

	public void setTujuan(String tujuan) {
		this.tujuan = tujuan;
	}

	public String getTempoh() {
		return this.tempoh;
	}

	public void setTempoh(String tempohPajakan) {
		this.tempoh = tempohPajakan;
	}

	public Date getTarikhMula() {
		return this.tarikhMula;
	}
	
	public String getTarikhMulaString() {
		return sdf.format(this.tarikhMula);
	}

	public void setTarikhMula(Date tarikhMulaPajakan) {
		this.tarikhMula = tarikhMulaPajakan;
	}

	public Date getTarikhTamat() {
		return this.tarikhTamat;
	}

	public String getTarikhTamatString() {
		return sdf.format(this.tarikhTamat);
	}

	public void setTarikhTamat(Date tarikhTamatpajakan) {
		this.tarikhTamat = tarikhTamatpajakan;
	}

	public String getCaraBayar() {
		return this.caraBayar;
	}

	public void setCaraBayar(String caraBayar) {
		this.caraBayar = caraBayar;
	}

	public String getKategoriCukai() {
		return this.kategoriCukai;
	}

	public void setKategoriCukai(String kategoriCukai) {
		this.kategoriCukai = kategoriCukai;
	}

	public Double getKadarCukai() {
		return this.kadarCukai;
	}

	public String getKadarCukaiString() {
		return Utils.format2Decimal(this.kadarCukai);
	}
	
	public void setKadarCukai(Double kadarCukai) {
		this.kadarCukai = kadarCukai;
	}

	public Double getKadar() {
		return this.kadar;
	}
	
	public String getKadarString() {
		return Utils.format2Decimal(this.kadar);
	}
	
	public void setKadar(Double kadarPajakan) {
		this.kadar= kadarPajakan;
	}
	
	public Long getIdMasuk() {
		return this.idMasuk;
	}

	public void setIdMasuk(Long idMasuk) {
		this.idMasuk = idMasuk;
	}

	public Date getTarikhMasuk() {
		return this.tarikhMasuk;
	}

	public void setTarikhMasuk(Date tarikhMasuk) {
		this.tarikhMasuk = tarikhMasuk;
	}

	public Long getIdKemaskini() {
		return this.idKemaskini;
	}

	public void setIdKemaskini(Long idKemaskini) {
		this.idKemaskini = idKemaskini;
	}

	public Date getTarikhKemaskini() {
		return this.tarikhKemaskini;
	}

	public void setTarikhKemaskini(Date tarikhKemaskini) {
		this.tarikhKemaskini = tarikhKemaskini;
	}

	public Set getTblhtppajakankadars() {
		return this.tblhtppajakankadars;
	}

	public void setTblhtppajakankadars(Set tblhtppajakankadars) {
		this.tblhtppajakankadars = tblhtppajakankadars;
	}
	
	public HtpPerjanjian getHtpPerjanjian() {
		return this.htpPerjanjian;
	}

	public void setHtpPerjanjian(HtpPerjanjian htpPerjanjian) {
		this.htpPerjanjian = htpPerjanjian;
	}
	
	public Pemohon getPemohon() {
		return this.pemohon;
	}

	public void setPemohon(Pemohon pemohon) {
		this.pemohon = pemohon;
	}
	

}