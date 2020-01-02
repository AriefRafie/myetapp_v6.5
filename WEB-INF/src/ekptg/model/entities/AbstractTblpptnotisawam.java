package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblpptnotisawam entity provides the base persistence definition of
 * the Tblpptnotisawam entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpptnotisawam implements java.io.Serializable {

	// Fields

	private Long idNotisawam;
	private String tempat;
	private Date tarikhTampal;
	private Long jenisTempatTampal;
	private Long idJenisDokumen;
	private String bilSurat;
	private String bilNotis;
	private Long idPermohonan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Long idDb;

	// Constructors

	/** default constructor */
	public AbstractTblpptnotisawam() {
	}

	/** full constructor */
	public AbstractTblpptnotisawam(String tempat, Date tarikhTampal,
			Long jenisTempatTampal, Long idJenisDokumen, String bilSurat,
			String bilNotis, Long idPermohonan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Long idDb) {
		this.tempat = tempat;
		this.tarikhTampal = tarikhTampal;
		this.jenisTempatTampal = jenisTempatTampal;
		this.idJenisDokumen = idJenisDokumen;
		this.bilSurat = bilSurat;
		this.bilNotis = bilNotis;
		this.idPermohonan = idPermohonan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.idDb = idDb;
	}

	// Property accessors

	public Long getIdNotisawam() {
		return this.idNotisawam;
	}

	public void setIdNotisawam(Long idNotisawam) {
		this.idNotisawam = idNotisawam;
	}

	public String getTempat() {
		return this.tempat;
	}

	public void setTempat(String tempat) {
		this.tempat = tempat;
	}

	public Date getTarikhTampal() {
		return this.tarikhTampal;
	}

	public void setTarikhTampal(Date tarikhTampal) {
		this.tarikhTampal = tarikhTampal;
	}

	public Long getJenisTempatTampal() {
		return this.jenisTempatTampal;
	}

	public void setJenisTempatTampal(Long jenisTempatTampal) {
		this.jenisTempatTampal = jenisTempatTampal;
	}

	public Long getIdJenisDokumen() {
		return this.idJenisDokumen;
	}

	public void setIdJenisDokumen(Long idJenisDokumen) {
		this.idJenisDokumen = idJenisDokumen;
	}

	public String getBilSurat() {
		return this.bilSurat;
	}

	public void setBilSurat(String bilSurat) {
		this.bilSurat = bilSurat;
	}

	public String getBilNotis() {
		return this.bilNotis;
	}

	public void setBilNotis(String bilNotis) {
		this.bilNotis = bilNotis;
	}

	public Long getIdPermohonan() {
		return this.idPermohonan;
	}

	public void setIdPermohonan(Long idPermohonan) {
		this.idPermohonan = idPermohonan;
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

	public Long getIdDb() {
		return this.idDb;
	}

	public void setIdDb(Long idDb) {
		this.idDb = idDb;
	}

}