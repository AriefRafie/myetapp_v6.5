package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblhtpajakanbil entity provides the base persistence definition of
 * the Tblhtpajakanbil entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtpajakanbil implements java.io.Serializable {

	// Fields

	private Long idBil;
	private Tblhtppajakankadar tblhtppajakankadar;
	private Long idBayaran;
	private Double bayaranTertunggak;
	private Date tarikhBil;
	private Date tarikhBayar;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Long tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblhtpajakanbil() {
	}

	/** minimal constructor */
	public AbstractTblhtpajakanbil(Long idBil,
			Tblhtppajakankadar tblhtppajakankadar, Long idBayaran, Long idMasuk) {
		this.idBil = idBil;
		this.tblhtppajakankadar = tblhtppajakankadar;
		this.idBayaran = idBayaran;
		this.idMasuk = idMasuk;
	}

	/** full constructor */
	public AbstractTblhtpajakanbil(Long idBil,
			Tblhtppajakankadar tblhtppajakankadar, Long idBayaran,
			Double bayaranTertunggak, Date tarikhBil, Date tarikhBayar,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Long tarikhKemaskini) {
		this.idBil = idBil;
		this.tblhtppajakankadar = tblhtppajakankadar;
		this.idBayaran = idBayaran;
		this.bayaranTertunggak = bayaranTertunggak;
		this.tarikhBil = tarikhBil;
		this.tarikhBayar = tarikhBayar;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdBil() {
		return this.idBil;
	}

	public void setIdBil(Long idBil) {
		this.idBil = idBil;
	}

	public Tblhtppajakankadar getTblhtppajakankadar() {
		return this.tblhtppajakankadar;
	}

	public void setTblhtppajakankadar(Tblhtppajakankadar tblhtppajakankadar) {
		this.tblhtppajakankadar = tblhtppajakankadar;
	}

	public Long getIdBayaran() {
		return this.idBayaran;
	}

	public void setIdBayaran(Long idBayaran) {
		this.idBayaran = idBayaran;
	}

	public Double getBayaranTertunggak() {
		return this.bayaranTertunggak;
	}

	public void setBayaranTertunggak(Double bayaranTertunggak) {
		this.bayaranTertunggak = bayaranTertunggak;
	}

	public Date getTarikhBil() {
		return this.tarikhBil;
	}

	public void setTarikhBil(Date tarikhBil) {
		this.tarikhBil = tarikhBil;
	}

	public Date getTarikhBayar() {
		return this.tarikhBayar;
	}

	public void setTarikhBayar(Date tarikhBayar) {
		this.tarikhBayar = tarikhBayar;
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

	public Long getTarikhKemaskini() {
		return this.tarikhKemaskini;
	}

	public void setTarikhKemaskini(Long tarikhKemaskini) {
		this.tarikhKemaskini = tarikhKemaskini;
	}

}