package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblpdtjadual entity provides the base persistence definition of the
 * Tblpdtjadual entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpdtjadual implements java.io.Serializable {

	// Fields

	private Long idJadual;
	private Tblpdtenakmen tblpdtenakmen;
	private Tblpdtakta tblpdtakta;
	private String namaJadual;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblpdtjadualseksyens = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblpdtjadual() {
	}

	/** minimal constructor */
	public AbstractTblpdtjadual(Long idJadual) {
		this.idJadual = idJadual;
	}

	/** full constructor */
	public AbstractTblpdtjadual(Long idJadual, Tblpdtenakmen tblpdtenakmen,
			Tblpdtakta tblpdtakta, String namaJadual, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Set tblpdtjadualseksyens) {
		this.idJadual = idJadual;
		this.tblpdtenakmen = tblpdtenakmen;
		this.tblpdtakta = tblpdtakta;
		this.namaJadual = namaJadual;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblpdtjadualseksyens = tblpdtjadualseksyens;
	}

	// Property accessors

	public Long getIdJadual() {
		return this.idJadual;
	}

	public void setIdJadual(Long idJadual) {
		this.idJadual = idJadual;
	}

	public Tblpdtenakmen getTblpdtenakmen() {
		return this.tblpdtenakmen;
	}

	public void setTblpdtenakmen(Tblpdtenakmen tblpdtenakmen) {
		this.tblpdtenakmen = tblpdtenakmen;
	}

	public Tblpdtakta getTblpdtakta() {
		return this.tblpdtakta;
	}

	public void setTblpdtakta(Tblpdtakta tblpdtakta) {
		this.tblpdtakta = tblpdtakta;
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

	public Set getTblpdtjadualseksyens() {
		return this.tblpdtjadualseksyens;
	}

	public void setTblpdtjadualseksyens(Set tblpdtjadualseksyens) {
		this.tblpdtjadualseksyens = tblpdtjadualseksyens;
	}

}