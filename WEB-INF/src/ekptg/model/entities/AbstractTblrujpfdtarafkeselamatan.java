package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblrujpfdtarafkeselamatan entity provides the base persistence
 * definition of the Tblrujpfdtarafkeselamatan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujpfdtarafkeselamatan implements
		java.io.Serializable {

	// Fields

	private Long idTarafKeselamatan;
	private String kodTarafKeselamatan;
	private String tarafKeselamatan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblpfdfails = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblrujpfdtarafkeselamatan() {
	}

	/** minimal constructor */
	public AbstractTblrujpfdtarafkeselamatan(Long idTarafKeselamatan) {
		this.idTarafKeselamatan = idTarafKeselamatan;
	}

	/** full constructor */
	public AbstractTblrujpfdtarafkeselamatan(Long idTarafKeselamatan,
			String kodTarafKeselamatan, String tarafKeselamatan, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Set tblpfdfails) {
		this.idTarafKeselamatan = idTarafKeselamatan;
		this.kodTarafKeselamatan = kodTarafKeselamatan;
		this.tarafKeselamatan = tarafKeselamatan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblpfdfails = tblpfdfails;
	}

	// Property accessors

	public Long getIdTarafKeselamatan() {
		return this.idTarafKeselamatan;
	}

	public void setIdTarafKeselamatan(Long idTarafKeselamatan) {
		this.idTarafKeselamatan = idTarafKeselamatan;
	}

	public String getKodTarafKeselamatan() {
		return this.kodTarafKeselamatan;
	}

	public void setKodTarafKeselamatan(String kodTarafKeselamatan) {
		this.kodTarafKeselamatan = kodTarafKeselamatan;
	}

	public String getTarafKeselamatan() {
		return this.tarafKeselamatan;
	}

	public void setTarafKeselamatan(String tarafKeselamatan) {
		this.tarafKeselamatan = tarafKeselamatan;
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

	public Set getTblpfdfails() {
		return this.tblpfdfails;
	}

	public void setTblpfdfails(Set tblpfdfails) {
		this.tblpfdfails = tblpfdfails;
	}

}