package ekptg.model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * AbstractTblrujsuburusanstatusfail entity provides the base persistence
 * definition of the Tblrujsuburusanstatusfail entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujsuburusanstatusfail implements
		java.io.Serializable {

	// Fields
	private Long idSuburusanstatusfail;
	private Long idPermohonan;
	private Long idSuburusanstatus;
	private String url;
	private String aktif;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Long idFail;
	private static SimpleDateFormat sdf =  new SimpleDateFormat("dd/MM/yyyy");
	private String tarikhMasukStr;
	private String tarikhKemaskiniStr;

	// Constructors
	/** default constructor */
	public AbstractTblrujsuburusanstatusfail() {
	}

	/** minimal constructor */
	public AbstractTblrujsuburusanstatusfail(Long idSuburusanstatusfail,
			Long idPermohonan, Long idSuburusanstatus, Long idMasuk,
			Long idKemaskini,Long idFail) {
		this.idSuburusanstatusfail = idSuburusanstatusfail;
		this.idPermohonan = idPermohonan;
		this.idSuburusanstatus = idSuburusanstatus;
		this.idMasuk = idMasuk;
		this.idKemaskini = idKemaskini;
		this.idFail = idFail;
	}

	/** full constructor */
	public AbstractTblrujsuburusanstatusfail(Long idSuburusanstatusfail
			,Long idPermohonan, Long idSuburusanstatus, String url
			,String aktif, Long idMasuk, Date tarikhMasuk, String tarikhMasukStr
			,Long idKemaskini, Date tarikhKemaskini, String tarikhKemaskiniStr
			,Long idFail) {
		this.idSuburusanstatusfail = idSuburusanstatusfail;
		this.idPermohonan = idPermohonan;
		this.idSuburusanstatus = idSuburusanstatus;
		this.url = url;
		this.aktif = aktif;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.idFail = idFail;
	}

	// Property accessors

	public Long getIdSuburusanstatusfail() {
		return this.idSuburusanstatusfail;
	}

	public void setIdSuburusanstatusfail(Long idSuburusanstatusfail) {
		this.idSuburusanstatusfail = idSuburusanstatusfail;
	}

	public Long getIdPermohonan() {
		return this.idPermohonan;
	}

	public void setIdPermohonan(Long idPermohonan) {
		this.idPermohonan = idPermohonan;
	}

	public Long getIdSuburusanstatus() {
		return this.idSuburusanstatus;
	}

	public void setIdSuburusanstatus(Long idSuburusanstatus) {
		this.idSuburusanstatus = idSuburusanstatus;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
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
	public String getTarikhMasukFormat() {
		if(sdf.format(tarikhMasuk).equals("01/01/1900"))
			return "";
		else
			return sdf.format(tarikhMasuk);
	}
	public void setTarikhMasuk(Date tarikhMasuk) {
		this.tarikhMasuk = tarikhMasuk;
	}
	
	public void setTarikhMasuk(String tarikhMasukStr) {
		this.tarikhMasukStr = tarikhMasukStr;
	}
	public String getTarikhMasukStr() {
		return this.tarikhMasukStr;
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
	
	public String getTarikhKemaskiniFormat() {
		if(sdf.format(tarikhKemaskiniStr).equals("01/01/1900"))
			return "";
		else
			return sdf.format(tarikhKemaskiniStr);
	}
	
	public void setTarikhKemaskini(String tarikhKemaskiniStr) {
		this.tarikhKemaskiniStr = tarikhKemaskiniStr;
	}
	
	public String getTarikhKemaskiniStr() {
		return this.tarikhKemaskiniStr;
	}

	public Long getIdFail() {
		return this.idFail;
	}

	public void setIdFail(Long idFail) {
		this.idFail = idFail;
	}

}