package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblpdtjadualpinda entity provides the base persistence definition of
 * the Tblpdtjadualpinda entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpdtjadualpinda implements java.io.Serializable {

	// Fields

	private Long idJadualpinda;
	private Tblpdtenakmenpinda tblpdtenakmenpinda;
	private Tblpdtaktapinda tblpdtaktapinda;
	private String namaJadual;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblpdtjadualpindaseksyens = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblpdtjadualpinda() {
	}

	/** minimal constructor */
	public AbstractTblpdtjadualpinda(Long idJadualpinda,
			Tblpdtaktapinda tblpdtaktapinda) {
		this.idJadualpinda = idJadualpinda;
		this.tblpdtaktapinda = tblpdtaktapinda;
	}

	/** full constructor */
	public AbstractTblpdtjadualpinda(Long idJadualpinda,
			Tblpdtenakmenpinda tblpdtenakmenpinda,
			Tblpdtaktapinda tblpdtaktapinda, String namaJadual, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Set tblpdtjadualpindaseksyens) {
		this.idJadualpinda = idJadualpinda;
		this.tblpdtenakmenpinda = tblpdtenakmenpinda;
		this.tblpdtaktapinda = tblpdtaktapinda;
		this.namaJadual = namaJadual;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblpdtjadualpindaseksyens = tblpdtjadualpindaseksyens;
	}

	// Property accessors

	public Long getIdJadualpinda() {
		return this.idJadualpinda;
	}

	public void setIdJadualpinda(Long idJadualpinda) {
		this.idJadualpinda = idJadualpinda;
	}

	public Tblpdtenakmenpinda getTblpdtenakmenpinda() {
		return this.tblpdtenakmenpinda;
	}

	public void setTblpdtenakmenpinda(Tblpdtenakmenpinda tblpdtenakmenpinda) {
		this.tblpdtenakmenpinda = tblpdtenakmenpinda;
	}

	public Tblpdtaktapinda getTblpdtaktapinda() {
		return this.tblpdtaktapinda;
	}

	public void setTblpdtaktapinda(Tblpdtaktapinda tblpdtaktapinda) {
		this.tblpdtaktapinda = tblpdtaktapinda;
	}

	public String getNamaJadual() {
		return this.namaJadual;
	}

	public void setNamaJadual(String namaJadual) {
		this.namaJadual = namaJadual;
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

	public Set getTblpdtjadualpindaseksyens() {
		return this.tblpdtjadualpindaseksyens;
	}

	public void setTblpdtjadualpindaseksyens(Set tblpdtjadualpindaseksyens) {
		this.tblpdtjadualpindaseksyens = tblpdtjadualpindaseksyens;
	}

}