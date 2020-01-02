package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblhtppajakanbil entity provides the base persistence definition of
 * the Tblhtppajakanbil entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtppajakanbil implements java.io.Serializable {

	// Fields

	private Long idPajakanbil;
	private Long idKadar;
	private Long idBayaran;
	private Double bayaranTertunggak;
	private Date tarikhBil;
	private Date tarikhBayar;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblhtppajakanbil() {
	}

	/** minimal constructor */
	public AbstractTblhtppajakanbil(Long idPajakanbil, Long idKadar,
			Long idBayaran) {
		this.idPajakanbil = idPajakanbil;
		this.idKadar = idKadar;
		this.idBayaran = idBayaran;
	}

	/** full constructor */
	public AbstractTblhtppajakanbil(Long idPajakanbil, Long idKadar,
			Long idBayaran, Double bayaranTertunggak, Date tarikhBil,
			Date tarikhBayar, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		this.idPajakanbil = idPajakanbil;
		this.idKadar = idKadar;
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

	public Long getIdPajakanbil() {
		return this.idPajakanbil;
	}

	public void setIdPajakanbil(Long idPajakanbil) {
		this.idPajakanbil = idPajakanbil;
	}

	public Long getIdKadar() {
		return this.idKadar;
	}

	public void setIdKadar(Long idKadar) {
		this.idKadar = idKadar;
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

	public Date getTarikhKemaskini() {
		return this.tarikhKemaskini;
	}

	public void setTarikhKemaskini(Date tarikhKemaskini) {
		this.tarikhKemaskini = tarikhKemaskini;
	}

}