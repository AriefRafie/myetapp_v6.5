package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblphpfailapbbertindih entity provides the base persistence
 * definition of the Tblphpfailapbbertindih entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblphpfailapbbertindih implements
		java.io.Serializable {

	// Fields

	private Long idFailapbbertindih;
	private Tblphppmohonnjdualpertama tblphppmohonnjdualpertama;
	private String bertindihDenganFail;
	private String namaSyarikatTindih;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblphpfailapbbertindih() {
	}

	/** minimal constructor */
	public AbstractTblphpfailapbbertindih(Long idFailapbbertindih) {
		this.idFailapbbertindih = idFailapbbertindih;
	}

	/** full constructor */
	public AbstractTblphpfailapbbertindih(Long idFailapbbertindih,
			Tblphppmohonnjdualpertama tblphppmohonnjdualpertama,
			String bertindihDenganFail, String namaSyarikatTindih,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		this.idFailapbbertindih = idFailapbbertindih;
		this.tblphppmohonnjdualpertama = tblphppmohonnjdualpertama;
		this.bertindihDenganFail = bertindihDenganFail;
		this.namaSyarikatTindih = namaSyarikatTindih;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdFailapbbertindih() {
		return this.idFailapbbertindih;
	}

	public void setIdFailapbbertindih(Long idFailapbbertindih) {
		this.idFailapbbertindih = idFailapbbertindih;
	}

	public Tblphppmohonnjdualpertama getTblphppmohonnjdualpertama() {
		return this.tblphppmohonnjdualpertama;
	}

	public void setTblphppmohonnjdualpertama(
			Tblphppmohonnjdualpertama tblphppmohonnjdualpertama) {
		this.tblphppmohonnjdualpertama = tblphppmohonnjdualpertama;
	}

	public String getBertindihDenganFail() {
		return this.bertindihDenganFail;
	}

	public void setBertindihDenganFail(String bertindihDenganFail) {
		this.bertindihDenganFail = bertindihDenganFail;
	}

	public String getNamaSyarikatTindih() {
		return this.namaSyarikatTindih;
	}

	public void setNamaSyarikatTindih(String namaSyarikatTindih) {
		this.namaSyarikatTindih = namaSyarikatTindih;
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