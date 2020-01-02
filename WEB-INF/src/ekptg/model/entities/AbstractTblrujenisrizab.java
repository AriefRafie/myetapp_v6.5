package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblrujenisrizab entity provides the base persistence definition of
 * the Tblrujenisrizab entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujenisrizab implements java.io.Serializable {

	// Fields

	private Long idRizab;
	private String kodRizab;
	private String keterangan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblrujenisrizab() {
	}

	/** minimal constructor */
	public AbstractTblrujenisrizab(Long idRizab) {
		this.idRizab = idRizab;
	}

	/** full constructor */
	public AbstractTblrujenisrizab(Long idRizab, String kodRizab,
			String keterangan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		this.idRizab = idRizab;
		this.kodRizab = kodRizab;
		this.keterangan = keterangan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdRizab() {
		return this.idRizab;
	}

	public void setIdRizab(Long idRizab) {
		this.idRizab = idRizab;
	}

	public String getKodRizab() {
		return this.kodRizab;
	}

	public void setKodRizab(String kodRizab) {
		this.kodRizab = kodRizab;
	}

	public String getKeterangan() {
		return this.keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
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

}