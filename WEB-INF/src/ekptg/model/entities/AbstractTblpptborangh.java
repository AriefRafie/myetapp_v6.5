package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblpptborangh entity provides the base persistence definition of the
 * Tblpptborangh entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpptborangh implements java.io.Serializable {

	// Fields

	private Long idBorangh;
	private Long jenisBorangh;
	private Date tarikhBorangh;
	private Date tarikhCetak;
	private Long tarikhCetakSemula;
	private Date tarikhTerima;
	private Long keputusan;
	private Long idBorangg;
	private Long statusCek;
	private Date tarikhSerah;
	private Date tarikhAkhirBayaragensi;
	private Long idPihakberkepentingan;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Long idDb;

	// Constructors

	/** default constructor */
	public AbstractTblpptborangh() {
	}

	/** full constructor */
	public AbstractTblpptborangh(Long jenisBorangh, Date tarikhBorangh,
			Date tarikhCetak, Long tarikhCetakSemula, Date tarikhTerima,
			Long keputusan, Long idBorangg, Long statusCek, Date tarikhSerah,
			Date tarikhAkhirBayaragensi, Long idPihakberkepentingan,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Long idDb) {
		this.jenisBorangh = jenisBorangh;
		this.tarikhBorangh = tarikhBorangh;
		this.tarikhCetak = tarikhCetak;
		this.tarikhCetakSemula = tarikhCetakSemula;
		this.tarikhTerima = tarikhTerima;
		this.keputusan = keputusan;
		this.idBorangg = idBorangg;
		this.statusCek = statusCek;
		this.tarikhSerah = tarikhSerah;
		this.tarikhAkhirBayaragensi = tarikhAkhirBayaragensi;
		this.idPihakberkepentingan = idPihakberkepentingan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.idDb = idDb;
	}

	// Property accessors

	public Long getIdBorangh() {
		return this.idBorangh;
	}

	public void setIdBorangh(Long idBorangh) {
		this.idBorangh = idBorangh;
	}

	public Long getJenisBorangh() {
		return this.jenisBorangh;
	}

	public void setJenisBorangh(Long jenisBorangh) {
		this.jenisBorangh = jenisBorangh;
	}

	public Date getTarikhBorangh() {
		return this.tarikhBorangh;
	}

	public void setTarikhBorangh(Date tarikhBorangh) {
		this.tarikhBorangh = tarikhBorangh;
	}

	public Date getTarikhCetak() {
		return this.tarikhCetak;
	}

	public void setTarikhCetak(Date tarikhCetak) {
		this.tarikhCetak = tarikhCetak;
	}

	public Long getTarikhCetakSemula() {
		return this.tarikhCetakSemula;
	}

	public void setTarikhCetakSemula(Long tarikhCetakSemula) {
		this.tarikhCetakSemula = tarikhCetakSemula;
	}

	public Date getTarikhTerima() {
		return this.tarikhTerima;
	}

	public void setTarikhTerima(Date tarikhTerima) {
		this.tarikhTerima = tarikhTerima;
	}

	public Long getKeputusan() {
		return this.keputusan;
	}

	public void setKeputusan(Long keputusan) {
		this.keputusan = keputusan;
	}

	public Long getIdBorangg() {
		return this.idBorangg;
	}

	public void setIdBorangg(Long idBorangg) {
		this.idBorangg = idBorangg;
	}

	public Long getStatusCek() {
		return this.statusCek;
	}

	public void setStatusCek(Long statusCek) {
		this.statusCek = statusCek;
	}

	public Date getTarikhSerah() {
		return this.tarikhSerah;
	}

	public void setTarikhSerah(Date tarikhSerah) {
		this.tarikhSerah = tarikhSerah;
	}

	public Date getTarikhAkhirBayaragensi() {
		return this.tarikhAkhirBayaragensi;
	}

	public void setTarikhAkhirBayaragensi(Date tarikhAkhirBayaragensi) {
		this.tarikhAkhirBayaragensi = tarikhAkhirBayaragensi;
	}

	public Long getIdPihakberkepentingan() {
		return this.idPihakberkepentingan;
	}

	public void setIdPihakberkepentingan(Long idPihakberkepentingan) {
		this.idPihakberkepentingan = idPihakberkepentingan;
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