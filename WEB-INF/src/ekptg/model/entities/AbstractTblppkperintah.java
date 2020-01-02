package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblppkperintah entity provides the base persistence definition of the
 * Tblppkperintah entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblppkperintah implements java.io.Serializable {

	// Fields

	private Long idPerintah;
	private Tblrujppkjenisperintah tblrujppkjenisperintah;
	private Tblppkperbicaraan tblppkperbicaraan;
	private Tblrujppkunit tblrujppkunit;
	private Date tarikhPerintah;
	private Long idNegeri;
	private String notisGeran;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblppkperintahhtaobmsts = new HashSet(0);
	private Set tblppkperintahhaobmsts = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblppkperintah() {
	}

	/** minimal constructor */
	public AbstractTblppkperintah(Long idPerintah,
			Tblppkperbicaraan tblppkperbicaraan) {
		this.idPerintah = idPerintah;
		this.tblppkperbicaraan = tblppkperbicaraan;
	}

	/** full constructor */
	public AbstractTblppkperintah(Long idPerintah,
			Tblrujppkjenisperintah tblrujppkjenisperintah,
			Tblppkperbicaraan tblppkperbicaraan, Tblrujppkunit tblrujppkunit,
			Date tarikhPerintah, Long idNegeri, String notisGeran,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblppkperintahhtaobmsts,
			Set tblppkperintahhaobmsts) {
		this.idPerintah = idPerintah;
		this.tblrujppkjenisperintah = tblrujppkjenisperintah;
		this.tblppkperbicaraan = tblppkperbicaraan;
		this.tblrujppkunit = tblrujppkunit;
		this.tarikhPerintah = tarikhPerintah;
		this.idNegeri = idNegeri;
		this.notisGeran = notisGeran;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblppkperintahhtaobmsts = tblppkperintahhtaobmsts;
		this.tblppkperintahhaobmsts = tblppkperintahhaobmsts;
	}

	// Property accessors

	public Long getIdPerintah() {
		return this.idPerintah;
	}

	public void setIdPerintah(Long idPerintah) {
		this.idPerintah = idPerintah;
	}

	public Tblrujppkjenisperintah getTblrujppkjenisperintah() {
		return this.tblrujppkjenisperintah;
	}

	public void setTblrujppkjenisperintah(
			Tblrujppkjenisperintah tblrujppkjenisperintah) {
		this.tblrujppkjenisperintah = tblrujppkjenisperintah;
	}

	public Tblppkperbicaraan getTblppkperbicaraan() {
		return this.tblppkperbicaraan;
	}

	public void setTblppkperbicaraan(Tblppkperbicaraan tblppkperbicaraan) {
		this.tblppkperbicaraan = tblppkperbicaraan;
	}

	public Tblrujppkunit getTblrujppkunit() {
		return this.tblrujppkunit;
	}

	public void setTblrujppkunit(Tblrujppkunit tblrujppkunit) {
		this.tblrujppkunit = tblrujppkunit;
	}

	public Date getTarikhPerintah() {
		return this.tarikhPerintah;
	}

	public void setTarikhPerintah(Date tarikhPerintah) {
		this.tarikhPerintah = tarikhPerintah;
	}

	public Long getIdNegeri() {
		return this.idNegeri;
	}

	public void setIdNegeri(Long idNegeri) {
		this.idNegeri = idNegeri;
	}

	public String getNotisGeran() {
		return this.notisGeran;
	}

	public void setNotisGeran(String notisGeran) {
		this.notisGeran = notisGeran;
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

	public Set getTblppkperintahhtaobmsts() {
		return this.tblppkperintahhtaobmsts;
	}

	public void setTblppkperintahhtaobmsts(Set tblppkperintahhtaobmsts) {
		this.tblppkperintahhtaobmsts = tblppkperintahhtaobmsts;
	}

	public Set getTblppkperintahhaobmsts() {
		return this.tblppkperintahhaobmsts;
	}

	public void setTblppkperintahhaobmsts(Set tblppkperintahhaobmsts) {
		this.tblppkperintahhaobmsts = tblppkperintahhaobmsts;
	}

}