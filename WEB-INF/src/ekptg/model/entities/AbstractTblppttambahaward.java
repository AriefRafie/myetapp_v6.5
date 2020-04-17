package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblppttambahaward entity provides the base persistence definition of
 * the Tblppttambahaward entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblppttambahaward implements java.io.Serializable {

	// Fields

	private Long idTambahaward;
	private Long idAward;
	private Double nilaiAward;
	private String keterangan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Long idDb;

	// Constructors

	/** default constructor */
	public AbstractTblppttambahaward() {
	}

	/** full constructor */
	public AbstractTblppttambahaward(Long idAward, Double nilaiAward,
			String keterangan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Long idDb) {
		this.idAward = idAward;
		this.nilaiAward = nilaiAward;
		this.keterangan = keterangan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.idDb = idDb;
	}

	// Property accessors

	public Long getIdTambahaward() {
		return this.idTambahaward;
	}

	public void setIdTambahaward(Long idTambahaward) {
		this.idTambahaward = idTambahaward;
	}

	public Long getIdAward() {
		return this.idAward;
	}

	public void setIdAward(Long idAward) {
		this.idAward = idAward;
	}

	public Double getNilaiAward() {
		return this.nilaiAward;
	}

	public void setNilaiAward(Double nilaiAward) {
		this.nilaiAward = nilaiAward;
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

	public Long getIdDb() {
		return this.idDb;
	}

	public void setIdDb(Long idDb) {
		this.idDb = idDb;
	}

}