package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblpfdminitmesyuaratsubpara entity provides the base persistence
 * definition of the Tblpfdminitmesyuaratsubpara entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpfdminitmesyuaratsubpara implements
		java.io.Serializable {

	// Fields

	private Long idMinitmesyuaratsubpara;
	private Long idMinitmesyuaratpara;
	private String subPara;
	private String maklumbalas;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblpfdminitmesyuaratsubpara() {
	}

	/** minimal constructor */
	public AbstractTblpfdminitmesyuaratsubpara(Long idMinitmesyuaratsubpara) {
		this.idMinitmesyuaratsubpara = idMinitmesyuaratsubpara;
	}

	/** full constructor */
	public AbstractTblpfdminitmesyuaratsubpara(Long idMinitmesyuaratsubpara,
			Long idMinitmesyuaratpara, String subPara, String maklumbalas,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		this.idMinitmesyuaratsubpara = idMinitmesyuaratsubpara;
		this.idMinitmesyuaratpara = idMinitmesyuaratpara;
		this.subPara = subPara;
		this.maklumbalas = maklumbalas;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdMinitmesyuaratsubpara() {
		return this.idMinitmesyuaratsubpara;
	}

	public void setIdMinitmesyuaratsubpara(Long idMinitmesyuaratsubpara) {
		this.idMinitmesyuaratsubpara = idMinitmesyuaratsubpara;
	}

	public Long getIdMinitmesyuaratpara() {
		return this.idMinitmesyuaratpara;
	}

	public void setIdMinitmesyuaratpara(Long idMinitmesyuaratpara) {
		this.idMinitmesyuaratpara = idMinitmesyuaratpara;
	}

	public String getSubPara() {
		return this.subPara;
	}

	public void setSubPara(String subPara) {
		this.subPara = subPara;
	}

	public String getMaklumbalas() {
		return this.maklumbalas;
	}

	public void setMaklumbalas(String maklumbalas) {
		this.maklumbalas = maklumbalas;
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