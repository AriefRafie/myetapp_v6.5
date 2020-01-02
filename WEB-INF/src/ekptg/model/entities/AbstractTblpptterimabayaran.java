package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblpptterimabayaran entity provides the base persistence definition
 * of the Tblpptterimabayaran entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpptterimabayaran implements
		java.io.Serializable {

	// Fields

	private Long idTerimabayaran;
	private Long idHakmilik;
	private String noRujukanSurat;
	private Date tarikhTerima;
	private Date tarikhSurat;
	private Long idBank;
	private Double amaunCek;
	private Date tarikhAmbilCek;
	private String masaAmbilCek;
	private String tempatAmbil;
	private Long jenisAward;
	private Date tarikhBorangk;
	private String noBorangk;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Long idDb;

	// Constructors

	/** default constructor */
	public AbstractTblpptterimabayaran() {
	}

	/** full constructor */
	public AbstractTblpptterimabayaran(Long idHakmilik, String noRujukanSurat,
			Date tarikhTerima, Date tarikhSurat, Long idBank, Double amaunCek,
			Date tarikhAmbilCek, String masaAmbilCek, String tempatAmbil,
			Long jenisAward, Date tarikhBorangk, String noBorangk,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Long idDb) {
		this.idHakmilik = idHakmilik;
		this.noRujukanSurat = noRujukanSurat;
		this.tarikhTerima = tarikhTerima;
		this.tarikhSurat = tarikhSurat;
		this.idBank = idBank;
		this.amaunCek = amaunCek;
		this.tarikhAmbilCek = tarikhAmbilCek;
		this.masaAmbilCek = masaAmbilCek;
		this.tempatAmbil = tempatAmbil;
		this.jenisAward = jenisAward;
		this.tarikhBorangk = tarikhBorangk;
		this.noBorangk = noBorangk;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.idDb = idDb;
	}

	// Property accessors

	public Long getIdTerimabayaran() {
		return this.idTerimabayaran;
	}

	public void setIdTerimabayaran(Long idTerimabayaran) {
		this.idTerimabayaran = idTerimabayaran;
	}

	public Long getIdHakmilik() {
		return this.idHakmilik;
	}

	public void setIdHakmilik(Long idHakmilik) {
		this.idHakmilik = idHakmilik;
	}

	public String getNoRujukanSurat() {
		return this.noRujukanSurat;
	}

	public void setNoRujukanSurat(String noRujukanSurat) {
		this.noRujukanSurat = noRujukanSurat;
	}

	public Date getTarikhTerima() {
		return this.tarikhTerima;
	}

	public void setTarikhTerima(Date tarikhTerima) {
		this.tarikhTerima = tarikhTerima;
	}

	public Date getTarikhSurat() {
		return this.tarikhSurat;
	}

	public void setTarikhSurat(Date tarikhSurat) {
		this.tarikhSurat = tarikhSurat;
	}

	public Long getIdBank() {
		return this.idBank;
	}

	public void setIdBank(Long idBank) {
		this.idBank = idBank;
	}

	public Double getAmaunCek() {
		return this.amaunCek;
	}

	public void setAmaunCek(Double amaunCek) {
		this.amaunCek = amaunCek;
	}

	public Date getTarikhAmbilCek() {
		return this.tarikhAmbilCek;
	}

	public void setTarikhAmbilCek(Date tarikhAmbilCek) {
		this.tarikhAmbilCek = tarikhAmbilCek;
	}

	public String getMasaAmbilCek() {
		return this.masaAmbilCek;
	}

	public void setMasaAmbilCek(String masaAmbilCek) {
		this.masaAmbilCek = masaAmbilCek;
	}

	public String getTempatAmbil() {
		return this.tempatAmbil;
	}

	public void setTempatAmbil(String tempatAmbil) {
		this.tempatAmbil = tempatAmbil;
	}

	public Long getJenisAward() {
		return this.jenisAward;
	}

	public void setJenisAward(Long jenisAward) {
		this.jenisAward = jenisAward;
	}

	public Date getTarikhBorangk() {
		return this.tarikhBorangk;
	}

	public void setTarikhBorangk(Date tarikhBorangk) {
		this.tarikhBorangk = tarikhBorangk;
	}

	public String getNoBorangk() {
		return this.noBorangk;
	}

	public void setNoBorangk(String noBorangk) {
		this.noBorangk = noBorangk;
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