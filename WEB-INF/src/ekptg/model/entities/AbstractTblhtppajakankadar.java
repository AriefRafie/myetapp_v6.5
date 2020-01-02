package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblhtppajakankadar entity provides the base persistence definition of
 * the Tblhtppajakankadar entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtppajakankadar implements
		java.io.Serializable {

	// Fields

	private Long idPajakankadar;
	private Tblhtppajakan tblhtppajakan;
	private Tblhtphakmilikcukai tblhtphakmilikcukai;
	private Date tarikhMulaPajak;
	private Date tarikhTamatpajak;
	private Double kadarBayarPajak;
	private Double kadarCukai;
	private Date tarikhSemakan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblhtpajakanbils = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblhtppajakankadar() {
	}

	/** minimal constructor */
	public AbstractTblhtppajakankadar(Long idPajakankadar,
			Tblhtppajakan tblhtppajakan, Tblhtphakmilikcukai tblhtphakmilikcukai) {
		this.idPajakankadar = idPajakankadar;
		this.tblhtppajakan = tblhtppajakan;
		this.tblhtphakmilikcukai = tblhtphakmilikcukai;
	}

	/** full constructor */
	public AbstractTblhtppajakankadar(Long idPajakankadar,
			Tblhtppajakan tblhtppajakan,
			Tblhtphakmilikcukai tblhtphakmilikcukai, Date tarikhMulaPajak,
			Date tarikhTamatpajak, Double kadarBayarPajak, Double kadarCukai,
			Date tarikhSemakan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Set tblhtpajakanbils) {
		this.idPajakankadar = idPajakankadar;
		this.tblhtppajakan = tblhtppajakan;
		this.tblhtphakmilikcukai = tblhtphakmilikcukai;
		this.tarikhMulaPajak = tarikhMulaPajak;
		this.tarikhTamatpajak = tarikhTamatpajak;
		this.kadarBayarPajak = kadarBayarPajak;
		this.kadarCukai = kadarCukai;
		this.tarikhSemakan = tarikhSemakan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblhtpajakanbils = tblhtpajakanbils;
	}

	// Property accessors

	public Long getIdPajakankadar() {
		return this.idPajakankadar;
	}

	public void setIdPajakankadar(Long idPajakankadar) {
		this.idPajakankadar = idPajakankadar;
	}

	public Tblhtppajakan getTblhtppajakan() {
		return this.tblhtppajakan;
	}

	public void setTblhtppajakan(Tblhtppajakan tblhtppajakan) {
		this.tblhtppajakan = tblhtppajakan;
	}

	public Tblhtphakmilikcukai getTblhtphakmilikcukai() {
		return this.tblhtphakmilikcukai;
	}

	public void setTblhtphakmilikcukai(Tblhtphakmilikcukai tblhtphakmilikcukai) {
		this.tblhtphakmilikcukai = tblhtphakmilikcukai;
	}

	public Date getTarikhMulaPajak() {
		return this.tarikhMulaPajak;
	}

	public void setTarikhMulaPajak(Date tarikhMulaPajak) {
		this.tarikhMulaPajak = tarikhMulaPajak;
	}

	public Date getTarikhTamatpajak() {
		return this.tarikhTamatpajak;
	}

	public void setTarikhTamatpajak(Date tarikhTamatpajak) {
		this.tarikhTamatpajak = tarikhTamatpajak;
	}

	public Double getKadarBayarPajak() {
		return this.kadarBayarPajak;
	}

	public void setKadarBayarPajak(Double kadarBayarPajak) {
		this.kadarBayarPajak = kadarBayarPajak;
	}

	public Double getKadarCukai() {
		return this.kadarCukai;
	}

	public void setKadarCukai(Double kadarCukai) {
		this.kadarCukai = kadarCukai;
	}

	public Date getTarikhSemakan() {
		return this.tarikhSemakan;
	}

	public void setTarikhSemakan(Date tarikhSemakan) {
		this.tarikhSemakan = tarikhSemakan;
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

	public Set getTblhtpajakanbils() {
		return this.tblhtpajakanbils;
	}

	public void setTblhtpajakanbils(Set tblhtpajakanbils) {
		this.tblhtpajakanbils = tblhtpajakanbils;
	}

}