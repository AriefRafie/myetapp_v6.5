package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblphpjadualkedualesenapb entity provides the base persistence
 * definition of the Tblphpjadualkedualesenapb entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblphpjadualkedualesenapb implements
		java.io.Serializable {

	// Fields

	private Long idJadualkedualesenapb;
	private Tblphppmohonnjdualpertama tblphppmohonnjdualpertama;
	private Long idFail;
	private String noSiriLesen;
	private Date tarikhKeluarlesen;
	private String lokasi;
	private String tempoh;
	private Long idTempoh;
	private Date tarikhMulaLesen;
	private Date tarikhTamatlesen;
	private Double luas;
	private Long idUnitluas;
	private String statusLesen;
	private Long idJenissewa;
	private String jenisLesen;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblphppemeganglesenapbs = new HashSet(0);
	private Set tblphpborangas = new HashSet(0);
	private Set tblphpversikoordinats = new HashSet(0);
	private Set tblphplaporanpasirs = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblphpjadualkedualesenapb() {
	}

	/** minimal constructor */
	public AbstractTblphpjadualkedualesenapb(Long idJadualkedualesenapb) {
		this.idJadualkedualesenapb = idJadualkedualesenapb;
	}

	/** full constructor */
	public AbstractTblphpjadualkedualesenapb(Long idJadualkedualesenapb,
			Tblphppmohonnjdualpertama tblphppmohonnjdualpertama, Long idFail,
			String noSiriLesen, Date tarikhKeluarlesen, String lokasi,
			String tempoh, Long idTempoh, Date tarikhMulaLesen,
			Date tarikhTamatlesen, Double luas, Long idUnitluas,
			String statusLesen, Long idJenissewa, String jenisLesen,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Set tblphppemeganglesenapbs,
			Set tblphpborangas, Set tblphpversikoordinats,
			Set tblphplaporanpasirs) {
		this.idJadualkedualesenapb = idJadualkedualesenapb;
		this.tblphppmohonnjdualpertama = tblphppmohonnjdualpertama;
		this.idFail = idFail;
		this.noSiriLesen = noSiriLesen;
		this.tarikhKeluarlesen = tarikhKeluarlesen;
		this.lokasi = lokasi;
		this.tempoh = tempoh;
		this.idTempoh = idTempoh;
		this.tarikhMulaLesen = tarikhMulaLesen;
		this.tarikhTamatlesen = tarikhTamatlesen;
		this.luas = luas;
		this.idUnitluas = idUnitluas;
		this.statusLesen = statusLesen;
		this.idJenissewa = idJenissewa;
		this.jenisLesen = jenisLesen;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblphppemeganglesenapbs = tblphppemeganglesenapbs;
		this.tblphpborangas = tblphpborangas;
		this.tblphpversikoordinats = tblphpversikoordinats;
		this.tblphplaporanpasirs = tblphplaporanpasirs;
	}

	// Property accessors

	public Long getIdJadualkedualesenapb() {
		return this.idJadualkedualesenapb;
	}

	public void setIdJadualkedualesenapb(Long idJadualkedualesenapb) {
		this.idJadualkedualesenapb = idJadualkedualesenapb;
	}

	public Tblphppmohonnjdualpertama getTblphppmohonnjdualpertama() {
		return this.tblphppmohonnjdualpertama;
	}

	public void setTblphppmohonnjdualpertama(
			Tblphppmohonnjdualpertama tblphppmohonnjdualpertama) {
		this.tblphppmohonnjdualpertama = tblphppmohonnjdualpertama;
	}

	public Long getIdFail() {
		return this.idFail;
	}

	public void setIdFail(Long idFail) {
		this.idFail = idFail;
	}

	public String getNoSiriLesen() {
		return this.noSiriLesen;
	}

	public void setNoSiriLesen(String noSiriLesen) {
		this.noSiriLesen = noSiriLesen;
	}

	public Date getTarikhKeluarlesen() {
		return this.tarikhKeluarlesen;
	}

	public void setTarikhKeluarlesen(Date tarikhKeluarlesen) {
		this.tarikhKeluarlesen = tarikhKeluarlesen;
	}

	public String getLokasi() {
		return this.lokasi;
	}

	public void setLokasi(String lokasi) {
		this.lokasi = lokasi;
	}

	public String getTempoh() {
		return this.tempoh;
	}

	public void setTempoh(String tempoh) {
		this.tempoh = tempoh;
	}

	public Long getIdTempoh() {
		return this.idTempoh;
	}

	public void setIdTempoh(Long idTempoh) {
		this.idTempoh = idTempoh;
	}

	public Date getTarikhMulaLesen() {
		return this.tarikhMulaLesen;
	}

	public void setTarikhMulaLesen(Date tarikhMulaLesen) {
		this.tarikhMulaLesen = tarikhMulaLesen;
	}

	public Date getTarikhTamatlesen() {
		return this.tarikhTamatlesen;
	}

	public void setTarikhTamatlesen(Date tarikhTamatlesen) {
		this.tarikhTamatlesen = tarikhTamatlesen;
	}

	public Double getLuas() {
		return this.luas;
	}

	public void setLuas(Double luas) {
		this.luas = luas;
	}

	public Long getIdUnitluas() {
		return this.idUnitluas;
	}

	public void setIdUnitluas(Long idUnitluas) {
		this.idUnitluas = idUnitluas;
	}

	public String getStatusLesen() {
		return this.statusLesen;
	}

	public void setStatusLesen(String statusLesen) {
		this.statusLesen = statusLesen;
	}

	public Long getIdJenissewa() {
		return this.idJenissewa;
	}

	public void setIdJenissewa(Long idJenissewa) {
		this.idJenissewa = idJenissewa;
	}

	public String getJenisLesen() {
		return this.jenisLesen;
	}

	public void setJenisLesen(String jenisLesen) {
		this.jenisLesen = jenisLesen;
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

	public Set getTblphppemeganglesenapbs() {
		return this.tblphppemeganglesenapbs;
	}

	public void setTblphppemeganglesenapbs(Set tblphppemeganglesenapbs) {
		this.tblphppemeganglesenapbs = tblphppemeganglesenapbs;
	}

	public Set getTblphpborangas() {
		return this.tblphpborangas;
	}

	public void setTblphpborangas(Set tblphpborangas) {
		this.tblphpborangas = tblphpborangas;
	}

	public Set getTblphpversikoordinats() {
		return this.tblphpversikoordinats;
	}

	public void setTblphpversikoordinats(Set tblphpversikoordinats) {
		this.tblphpversikoordinats = tblphpversikoordinats;
	}

	public Set getTblphplaporanpasirs() {
		return this.tblphplaporanpasirs;
	}

	public void setTblphplaporanpasirs(Set tblphplaporanpasirs) {
		this.tblphplaporanpasirs = tblphplaporanpasirs;
	}

}