package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblrujsuburusanstatus entity provides the base persistence definition
 * of the Tblrujsuburusanstatus entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujsuburusanstatus implements
		java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1173143986366388249L;
	private Long idSuburusanstatus;
	private Long idSuburusan;
	private Long idStatus;
	private String aktif;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set<?> tblrujsuburusanstatusfails = new HashSet(0);
	private int langkah;

	// Constructors

	/** default constructor */
	public AbstractTblrujsuburusanstatus() {
	}

	/** minimal constructor */
	public AbstractTblrujsuburusanstatus(Long idSuburusanstatus,
			Long idSuburusan, Long idStatus, Long idMasuk, Long idKemaskini) {
		this.idSuburusanstatus = idSuburusanstatus;
		this.idSuburusan = idSuburusan;
		this.idStatus = idStatus;
		this.idMasuk = idMasuk;
		this.idKemaskini = idKemaskini;
	}

	/** full constructor */
	public AbstractTblrujsuburusanstatus(Long idSuburusanstatus,
			Long idSuburusan, Long idStatus, String aktif, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Set<?> tblrujsuburusanstatusfails,int langkah) {
		this.idSuburusanstatus = idSuburusanstatus;
		this.idSuburusan = idSuburusan;
		this.idStatus = idStatus;
		this.aktif = aktif;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblrujsuburusanstatusfails = tblrujsuburusanstatusfails;
		this.langkah = langkah;
	}

	// Property accessors
	public int getLangkah() {
		return this.langkah;
	}

	public void setLangkah(int langkah) {
		this.langkah = langkah;
	}
	
	public Long getIdSuburusanstatus() {
		return this.idSuburusanstatus;
	}

	public void setIdSuburusanstatus(Long idSuburusanstatus) {
		this.idSuburusanstatus = idSuburusanstatus;
	}

	public Long getIdSuburusan() {
		return this.idSuburusan;
	}

	public void setIdSuburusan(Long idSuburusan) {
		this.idSuburusan = idSuburusan;
	}

	public Long getIdStatus() {
		return this.idStatus;
	}

	public void setIdStatus(Long idStatus) {
		this.idStatus = idStatus;
	}

	public String getAktif() {
		return this.aktif;
	}

	public void setAktif(String aktif) {
		this.aktif = aktif;
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

	public Set<?> getTblrujsuburusanstatusfails() {
		return this.tblrujsuburusanstatusfails;
	}

	public void setTblrujsuburusanstatusfails(Set<?> tblrujsuburusanstatusfails) {
		this.tblrujsuburusanstatusfails = tblrujsuburusanstatusfails;
	}

}