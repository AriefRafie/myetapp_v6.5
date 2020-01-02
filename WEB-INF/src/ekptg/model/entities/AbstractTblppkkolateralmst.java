package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblppkkolateralmst entity provides the base persistence definition of
 * the Tblppkkolateralmst entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblppkkolateralmst implements
		java.io.Serializable {

	// Fields

	private Long idKolateralmst;
	private Tblppkperbicaraan tblppkperbicaraan;
	private Date tarikhBicara;
	private String sebabPertelingkahan;
	private String catatan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblppkkolateraldtls = new HashSet(0);
	private Set tblppknotiskolaterals = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblppkkolateralmst() {
	}

	/** minimal constructor */
	public AbstractTblppkkolateralmst(Long idKolateralmst,
			Tblppkperbicaraan tblppkperbicaraan) {
		this.idKolateralmst = idKolateralmst;
		this.tblppkperbicaraan = tblppkperbicaraan;
	}

	/** full constructor */
	public AbstractTblppkkolateralmst(Long idKolateralmst,
			Tblppkperbicaraan tblppkperbicaraan, Date tarikhBicara,
			String sebabPertelingkahan, String catatan, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Set tblppkkolateraldtls, Set tblppknotiskolaterals) {
		this.idKolateralmst = idKolateralmst;
		this.tblppkperbicaraan = tblppkperbicaraan;
		this.tarikhBicara = tarikhBicara;
		this.sebabPertelingkahan = sebabPertelingkahan;
		this.catatan = catatan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblppkkolateraldtls = tblppkkolateraldtls;
		this.tblppknotiskolaterals = tblppknotiskolaterals;
	}

	// Property accessors

	public Long getIdKolateralmst() {
		return this.idKolateralmst;
	}

	public void setIdKolateralmst(Long idKolateralmst) {
		this.idKolateralmst = idKolateralmst;
	}

	public Tblppkperbicaraan getTblppkperbicaraan() {
		return this.tblppkperbicaraan;
	}

	public void setTblppkperbicaraan(Tblppkperbicaraan tblppkperbicaraan) {
		this.tblppkperbicaraan = tblppkperbicaraan;
	}

	public Date getTarikhBicara() {
		return this.tarikhBicara;
	}

	public void setTarikhBicara(Date tarikhBicara) {
		this.tarikhBicara = tarikhBicara;
	}

	public String getSebabPertelingkahan() {
		return this.sebabPertelingkahan;
	}

	public void setSebabPertelingkahan(String sebabPertelingkahan) {
		this.sebabPertelingkahan = sebabPertelingkahan;
	}

	public String getCatatan() {
		return this.catatan;
	}

	public void setCatatan(String catatan) {
		this.catatan = catatan;
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

	public Set getTblppkkolateraldtls() {
		return this.tblppkkolateraldtls;
	}

	public void setTblppkkolateraldtls(Set tblppkkolateraldtls) {
		this.tblppkkolateraldtls = tblppkkolateraldtls;
	}

	public Set getTblppknotiskolaterals() {
		return this.tblppknotiskolaterals;
	}

	public void setTblppknotiskolaterals(Set tblppknotiskolaterals) {
		this.tblppknotiskolaterals = tblppknotiskolaterals;
	}

}