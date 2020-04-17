package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblhtppajakan entity provides the base persistence definition of the
 * Tblhtppajakan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtppajakan implements java.io.Serializable {

	// Fields

	private Long idPajakan;
	private Tblhtppermohonan tblhtppermohonan;
	private String tujuan;
	private String tempohPajakan;
	private Date tarikhMulaPajakan;
	private Date tarikhTamatpajakan;
	private String caraBayar;
	private String kategoriCukai;
	private Double kadarCukai;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblhtppajakankadars = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblhtppajakan() {
	}

	/** minimal constructor */
	public AbstractTblhtppajakan(Long idPajakan,
			Tblhtppermohonan tblhtppermohonan) {
		this.idPajakan = idPajakan;
		this.tblhtppermohonan = tblhtppermohonan;
	}

	/** full constructor */
	public AbstractTblhtppajakan(Long idPajakan,
			Tblhtppermohonan tblhtppermohonan, String tujuan,
			String tempohPajakan, Date tarikhMulaPajakan,
			Date tarikhTamatpajakan, String caraBayar, String kategoriCukai,
			Double kadarCukai, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Set tblhtppajakankadars) {
		this.idPajakan = idPajakan;
		this.tblhtppermohonan = tblhtppermohonan;
		this.tujuan = tujuan;
		this.tempohPajakan = tempohPajakan;
		this.tarikhMulaPajakan = tarikhMulaPajakan;
		this.tarikhTamatpajakan = tarikhTamatpajakan;
		this.caraBayar = caraBayar;
		this.kategoriCukai = kategoriCukai;
		this.kadarCukai = kadarCukai;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblhtppajakankadars = tblhtppajakankadars;
	}

	// Property accessors

	public Long getIdPajakan() {
		return this.idPajakan;
	}

	public void setIdPajakan(Long idPajakan) {
		this.idPajakan = idPajakan;
	}

	public Tblhtppermohonan getTblhtppermohonan() {
		return this.tblhtppermohonan;
	}

	public void setTblhtppermohonan(Tblhtppermohonan tblhtppermohonan) {
		this.tblhtppermohonan = tblhtppermohonan;
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

	public void setTarikhMulaPajakan(Date tarikhMulaPajakan) {
		this.tarikhMulaPajakan = tarikhMulaPajakan;
	}

	public Date getTarikhTamatpajakan() {
		return this.tarikhTamatpajakan;
	}

	public void setTarikhTamatpajakan(Date tarikhTamatpajakan) {
		this.tarikhTamatpajakan = tarikhTamatpajakan;
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

	public void setKadarCukai(Double kadarCukai) {
		this.kadarCukai = kadarCukai;
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

}