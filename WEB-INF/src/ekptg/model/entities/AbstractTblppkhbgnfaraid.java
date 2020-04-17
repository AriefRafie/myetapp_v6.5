package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblppkhbgnfaraid entity provides the base persistence definition of
 * the Tblppkhbgnfaraid entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblppkhbgnfaraid implements java.io.Serializable {

	// Fields

	private Long idHbgnfaraid;
	private Tblrujppktarafkptg tblrujppktarafkptg;
	private Tblrujppksaudara tblrujppksaudara;
	private Long kodFaraid;
	private String keterangan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblppkobfaraids = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblppkhbgnfaraid() {
	}

	/** minimal constructor */
	public AbstractTblppkhbgnfaraid(Long idHbgnfaraid, Long kodFaraid) {
		this.idHbgnfaraid = idHbgnfaraid;
		this.kodFaraid = kodFaraid;
	}

	/** full constructor */
	public AbstractTblppkhbgnfaraid(Long idHbgnfaraid,
			Tblrujppktarafkptg tblrujppktarafkptg,
			Tblrujppksaudara tblrujppksaudara, Long kodFaraid,
			String keterangan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Set tblppkobfaraids) {
		this.idHbgnfaraid = idHbgnfaraid;
		this.tblrujppktarafkptg = tblrujppktarafkptg;
		this.tblrujppksaudara = tblrujppksaudara;
		this.kodFaraid = kodFaraid;
		this.keterangan = keterangan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblppkobfaraids = tblppkobfaraids;
	}

	// Property accessors

	public Long getIdHbgnfaraid() {
		return this.idHbgnfaraid;
	}

	public void setIdHbgnfaraid(Long idHbgnfaraid) {
		this.idHbgnfaraid = idHbgnfaraid;
	}

	public Tblrujppktarafkptg getTblrujppktarafkptg() {
		return this.tblrujppktarafkptg;
	}

	public void setTblrujppktarafkptg(Tblrujppktarafkptg tblrujppktarafkptg) {
		this.tblrujppktarafkptg = tblrujppktarafkptg;
	}

	public Tblrujppksaudara getTblrujppksaudara() {
		return this.tblrujppksaudara;
	}

	public void setTblrujppksaudara(Tblrujppksaudara tblrujppksaudara) {
		this.tblrujppksaudara = tblrujppksaudara;
	}

	public Long getKodFaraid() {
		return this.kodFaraid;
	}

	public void setKodFaraid(Long kodFaraid) {
		this.kodFaraid = kodFaraid;
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

	public Set getTblppkobfaraids() {
		return this.tblppkobfaraids;
	}

	public void setTblppkobfaraids(Set tblppkobfaraids) {
		this.tblppkobfaraids = tblppkobfaraids;
	}

}