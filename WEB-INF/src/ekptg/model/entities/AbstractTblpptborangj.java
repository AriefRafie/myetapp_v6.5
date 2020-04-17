package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblpptborangj entity provides the base persistence definition of the
 * Tblpptborangj entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpptborangj implements java.io.Serializable {

	// Fields

	private Long idBorangj;
	private Long idBangunan;
	private Date tarikhNotis;
	private Date tarikhTamatNotis;
	private Date tarikhBorangj;
	private Long idHakmilik;
	private Long idBorangi;
	private Long tempoh;
	private Long unitTempoh;
	private String tindakan;
	private String keputusan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Long idDb;

	// Constructors

	/** default constructor */
	public AbstractTblpptborangj() {
	}

	/** full constructor */
	public AbstractTblpptborangj(Long idBangunan, Date tarikhNotis,
			Date tarikhTamatNotis, Date tarikhBorangj, Long idHakmilik,
			Long idBorangi, Long tempoh, Long unitTempoh, String tindakan,
			String keputusan, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Long idDb) {
		this.idBangunan = idBangunan;
		this.tarikhNotis = tarikhNotis;
		this.tarikhTamatNotis = tarikhTamatNotis;
		this.tarikhBorangj = tarikhBorangj;
		this.idHakmilik = idHakmilik;
		this.idBorangi = idBorangi;
		this.tempoh = tempoh;
		this.unitTempoh = unitTempoh;
		this.tindakan = tindakan;
		this.keputusan = keputusan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.idDb = idDb;
	}

	// Property accessors

	public Long getIdBorangj() {
		return this.idBorangj;
	}

	public void setIdBorangj(Long idBorangj) {
		this.idBorangj = idBorangj;
	}

	public Long getIdBangunan() {
		return this.idBangunan;
	}

	public void setIdBangunan(Long idBangunan) {
		this.idBangunan = idBangunan;
	}

	public Date getTarikhNotis() {
		return this.tarikhNotis;
	}

	public void setTarikhNotis(Date tarikhNotis) {
		this.tarikhNotis = tarikhNotis;
	}

	public Date getTarikhTamatNotis() {
		return this.tarikhTamatNotis;
	}

	public void setTarikhTamatNotis(Date tarikhTamatNotis) {
		this.tarikhTamatNotis = tarikhTamatNotis;
	}

	public Date getTarikhBorangj() {
		return this.tarikhBorangj;
	}

	public void setTarikhBorangj(Date tarikhBorangj) {
		this.tarikhBorangj = tarikhBorangj;
	}

	public Long getIdHakmilik() {
		return this.idHakmilik;
	}

	public void setIdHakmilik(Long idHakmilik) {
		this.idHakmilik = idHakmilik;
	}

	public Long getIdBorangi() {
		return this.idBorangi;
	}

	public void setIdBorangi(Long idBorangi) {
		this.idBorangi = idBorangi;
	}

	public Long getTempoh() {
		return this.tempoh;
	}

	public void setTempoh(Long tempoh) {
		this.tempoh = tempoh;
	}

	public Long getUnitTempoh() {
		return this.unitTempoh;
	}

	public void setUnitTempoh(Long unitTempoh) {
		this.unitTempoh = unitTempoh;
	}

	public String getTindakan() {
		return this.tindakan;
	}

	public void setTindakan(String tindakan) {
		this.tindakan = tindakan;
	}

	public String getKeputusan() {
		return this.keputusan;
	}

	public void setKeputusan(String keputusan) {
		this.keputusan = keputusan;
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