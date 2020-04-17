package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblppknotabicara entity provides the base persistence definition of
 * the Tblppknotabicara entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblppknotabicara implements java.io.Serializable {

	// Fields

	private Long idNotabicara;
	private Tblppkperbicaraan tblppkperbicaraan;
	private String noJilid;
	private String dir;
	private Long bil;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblppknotabicara() {
	}

	/** minimal constructor */
	public AbstractTblppknotabicara(Long idNotabicara,
			Tblppkperbicaraan tblppkperbicaraan) {
		this.idNotabicara = idNotabicara;
		this.tblppkperbicaraan = tblppkperbicaraan;
	}

	/** full constructor */
	public AbstractTblppknotabicara(Long idNotabicara,
			Tblppkperbicaraan tblppkperbicaraan, String noJilid, String dir,
			Long bil, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		this.idNotabicara = idNotabicara;
		this.tblppkperbicaraan = tblppkperbicaraan;
		this.noJilid = noJilid;
		this.dir = dir;
		this.bil = bil;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdNotabicara() {
		return this.idNotabicara;
	}

	public void setIdNotabicara(Long idNotabicara) {
		this.idNotabicara = idNotabicara;
	}

	public Tblppkperbicaraan getTblppkperbicaraan() {
		return this.tblppkperbicaraan;
	}

	public void setTblppkperbicaraan(Tblppkperbicaraan tblppkperbicaraan) {
		this.tblppkperbicaraan = tblppkperbicaraan;
	}

	public String getNoJilid() {
		return this.noJilid;
	}

	public void setNoJilid(String noJilid) {
		this.noJilid = noJilid;
	}

	public String getDir() {
		return this.dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public Long getBil() {
		return this.bil;
	}

	public void setBil(Long bil) {
		this.bil = bil;
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