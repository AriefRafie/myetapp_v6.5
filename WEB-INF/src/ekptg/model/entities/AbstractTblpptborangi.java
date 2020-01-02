package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblpptborangi entity provides the base persistence definition of the
 * Tblpptborangi entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpptborangi implements java.io.Serializable {

	// Fields

	private Long idBorangi;
	private Date tarikhBorangi;
	private Date tarikhCetak;
	private Date tarikhCetakSemula;
	private Long jenisAmbilsegera;
	private String noRujukanSurat;
	private Date tarikhSurat;
	private Date tarikhTerima;
	private Long idPermohonan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Long idDb;

	// Constructors

	/** default constructor */
	public AbstractTblpptborangi() {
	}

	/** full constructor */
	public AbstractTblpptborangi(Date tarikhBorangi, Date tarikhCetak,
			Date tarikhCetakSemula, Long jenisAmbilsegera,
			String noRujukanSurat, Date tarikhSurat, Date tarikhTerima,
			Long idPermohonan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Long idDb) {
		this.tarikhBorangi = tarikhBorangi;
		this.tarikhCetak = tarikhCetak;
		this.tarikhCetakSemula = tarikhCetakSemula;
		this.jenisAmbilsegera = jenisAmbilsegera;
		this.noRujukanSurat = noRujukanSurat;
		this.tarikhSurat = tarikhSurat;
		this.tarikhTerima = tarikhTerima;
		this.idPermohonan = idPermohonan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.idDb = idDb;
	}

	// Property accessors

	public Long getIdBorangi() {
		return this.idBorangi;
	}

	public void setIdBorangi(Long idBorangi) {
		this.idBorangi = idBorangi;
	}

	public Date getTarikhBorangi() {
		return this.tarikhBorangi;
	}

	public void setTarikhBorangi(Date tarikhBorangi) {
		this.tarikhBorangi = tarikhBorangi;
	}

	public Date getTarikhCetak() {
		return this.tarikhCetak;
	}

	public void setTarikhCetak(Date tarikhCetak) {
		this.tarikhCetak = tarikhCetak;
	}

	public Date getTarikhCetakSemula() {
		return this.tarikhCetakSemula;
	}

	public void setTarikhCetakSemula(Date tarikhCetakSemula) {
		this.tarikhCetakSemula = tarikhCetakSemula;
	}

	public Long getJenisAmbilsegera() {
		return this.jenisAmbilsegera;
	}

	public void setJenisAmbilsegera(Long jenisAmbilsegera) {
		this.jenisAmbilsegera = jenisAmbilsegera;
	}

	public String getNoRujukanSurat() {
		return this.noRujukanSurat;
	}

	public void setNoRujukanSurat(String noRujukanSurat) {
		this.noRujukanSurat = noRujukanSurat;
	}

	public Date getTarikhSurat() {
		return this.tarikhSurat;
	}

	public void setTarikhSurat(Date tarikhSurat) {
		this.tarikhSurat = tarikhSurat;
	}

	public Date getTarikhTerima() {
		return this.tarikhTerima;
	}

	public void setTarikhTerima(Date tarikhTerima) {
		this.tarikhTerima = tarikhTerima;
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