package ekptg.model.htp.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import ekptg.helpers.Utils;


public class Pajakan implements java.io.Serializable {

	// Fields

	private Long idPajakan;
	private Permohonan permohonan;
	private String tujuan;
	private String tempohPajakan;
	private Date tarikhMulaPajakan;
	private Date tarikhTamatPajakan;
	private String caraBayar;
	private String kategoriCukai;
	private String tarikhBayar;
	private Double kadarCukai;
	private Double kadarPajakan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblhtppajakankadars = new HashSet(0);
	private HtpPerjanjian htpPerjanjian;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private Pemohon pemohon;
// Constructors

	// Property accessors

	public Long getIdPajakan() {
		return this.idPajakan;
	}

	public void setIdPajakan(Long idPajakan) {
		this.idPajakan = idPajakan;
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

	public String getTempohPajakan() {
		return this.tempohPajakan;
	}

	public void setTempohPajakan(String tempohPajakan) {
		this.tempohPajakan = tempohPajakan;
	}

	public Date getTarikhMulaPajakan() {
		return this.tarikhMulaPajakan;
	}
	
	public String getTarikhMulaPajakanString() {
		return this.tarikhMulaPajakan==null?"01/01/1900":sdf.format(this.tarikhMulaPajakan);
	}

	public void setTarikhMulaPajakan(Date tarikhMulaPajakan) {
		this.tarikhMulaPajakan = tarikhMulaPajakan;
	}

	public Date getTarikhTamatPajakan() {
		return this.tarikhTamatPajakan;
	}

	public String getTarikhTamatPajakanString() {
		return this.tarikhTamatPajakan==null?"01/01/1900":sdf.format(this.tarikhTamatPajakan);
	}

	public void setTarikhTamatPajakan(Date tarikhTamatpajakan) {
		this.tarikhTamatPajakan = tarikhTamatpajakan;
	}

	public String getTarikhBayarString() {
		return this.tarikhBayar==null?"01/01/1900":sdf.format(this.tarikhBayar);
	}

	public void setTarikhBayarString(String tarikhBayar) {
		this.tarikhBayar = tarikhBayar;
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

	public Double getKadarPajakan() {
		return this.kadarPajakan;
	}
	
	public String getKadarPajakanString() {
		return Utils.format2Decimal(this.kadarPajakan);
	}
	
	public void setKadarPajakan(Double kadarPajakan) {
		this.kadarPajakan = kadarPajakan;
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