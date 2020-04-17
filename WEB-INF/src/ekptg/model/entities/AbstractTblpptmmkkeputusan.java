package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblpptmmkkeputusan entity provides the base persistence definition of
 * the Tblpptmmkkeputusan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpptmmkkeputusan implements
		java.io.Serializable {

	// Fields

	private Long idMmkkeputusan;
	private Long idMmk;
	private Date tarikhTerima;
	private Date tarikhKeputusan;
	private Long statusKeputusan;
	private String ulasan;
	private Date tarikhHantar;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Long idDb;

	// Constructors

	/** default constructor */
	public AbstractTblpptmmkkeputusan() {
	}

	/** full constructor */
	public AbstractTblpptmmkkeputusan(Long idMmk, Date tarikhTerima,
			Date tarikhKeputusan, Long statusKeputusan, String ulasan,
			Date tarikhHantar, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Long idDb) {
		this.idMmk = idMmk;
		this.tarikhTerima = tarikhTerima;
		this.tarikhKeputusan = tarikhKeputusan;
		this.statusKeputusan = statusKeputusan;
		this.ulasan = ulasan;
		this.tarikhHantar = tarikhHantar;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.idDb = idDb;
	}

	// Property accessors

	public Long getIdMmkkeputusan() {
		return this.idMmkkeputusan;
	}

	public void setIdMmkkeputusan(Long idMmkkeputusan) {
		this.idMmkkeputusan = idMmkkeputusan;
	}

	public Long getIdMmk() {
		return this.idMmk;
	}

	public void setIdMmk(Long idMmk) {
		this.idMmk = idMmk;
	}

	public Date getTarikhTerima() {
		return this.tarikhTerima;
	}

	public void setTarikhTerima(Date tarikhTerima) {
		this.tarikhTerima = tarikhTerima;
	}

	public Date getTarikhKeputusan() {
		return this.tarikhKeputusan;
	}

	public void setTarikhKeputusan(Date tarikhKeputusan) {
		this.tarikhKeputusan = tarikhKeputusan;
	}

	public Long getStatusKeputusan() {
		return this.statusKeputusan;
	}

	public void setStatusKeputusan(Long statusKeputusan) {
		this.statusKeputusan = statusKeputusan;
	}

	public String getUlasan() {
		return this.ulasan;
	}

	public void setUlasan(String ulasan) {
		this.ulasan = ulasan;
	}

	public Date getTarikhHantar() {
		return this.tarikhHantar;
	}

	public void setTarikhHantar(Date tarikhHantar) {
		this.tarikhHantar = tarikhHantar;
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

	public Long getIdDb() {
		return this.idDb;
	}

	public void setIdDb(Long idDb) {
		this.idDb = idDb;
	}

}