package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblpptborangq entity provides the base persistence definition of the
 * Tblpptborangq entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpptborangq implements java.io.Serializable {

	// Fields

	private Long idBorangq;
	private String noBorangq;
	private Date tarikhBorangq;
	private String flagNotis;
	private Date tarikhMula;
	private Date tarikhAkhir;
	private Long idUnitluas;
	private Long luasSewa;
	private Long idPermohonan;
	private Long tempohPendudukan;
	private Long unitTempoh;
	private Double tawaranPampasan;
	private String masa;
	private String tempat;
	private Date tarikhRundingan;
	private Long idHakmilik;
	private Date tarikhCetak;
	private Date tarikhCetakSemula;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Long idDb;

	// Constructors

	/** default constructor */
	public AbstractTblpptborangq() {
	}

	/** full constructor */
	public AbstractTblpptborangq(String noBorangq, Date tarikhBorangq,
			String flagNotis, Date tarikhMula, Date tarikhAkhir,
			Long idUnitluas, Long luasSewa, Long idPermohonan,
			Long tempohPendudukan, Long unitTempoh, Double tawaranPampasan,
			String masa, String tempat, Date tarikhRundingan, Long idHakmilik,
			Date tarikhCetak, Date tarikhCetakSemula, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini, Long idDb) {
		this.noBorangq = noBorangq;
		this.tarikhBorangq = tarikhBorangq;
		this.flagNotis = flagNotis;
		this.tarikhMula = tarikhMula;
		this.tarikhAkhir = tarikhAkhir;
		this.idUnitluas = idUnitluas;
		this.luasSewa = luasSewa;
		this.idPermohonan = idPermohonan;
		this.tempohPendudukan = tempohPendudukan;
		this.unitTempoh = unitTempoh;
		this.tawaranPampasan = tawaranPampasan;
		this.masa = masa;
		this.tempat = tempat;
		this.tarikhRundingan = tarikhRundingan;
		this.idHakmilik = idHakmilik;
		this.tarikhCetak = tarikhCetak;
		this.tarikhCetakSemula = tarikhCetakSemula;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.idDb = idDb;
	}

	// Property accessors

	public Long getIdBorangq() {
		return this.idBorangq;
	}

	public void setIdBorangq(Long idBorangq) {
		this.idBorangq = idBorangq;
	}

	public String getNoBorangq() {
		return this.noBorangq;
	}

	public void setNoBorangq(String noBorangq) {
		this.noBorangq = noBorangq;
	}

	public Date getTarikhBorangq() {
		return this.tarikhBorangq;
	}

	public void setTarikhBorangq(Date tarikhBorangq) {
		this.tarikhBorangq = tarikhBorangq;
	}

	public String getFlagNotis() {
		return this.flagNotis;
	}

	public void setFlagNotis(String flagNotis) {
		this.flagNotis = flagNotis;
	}

	public Date getTarikhMula() {
		return this.tarikhMula;
	}

	public void setTarikhMula(Date tarikhMula) {
		this.tarikhMula = tarikhMula;
	}

	public Date getTarikhAkhir() {
		return this.tarikhAkhir;
	}

	public void setTarikhAkhir(Date tarikhAkhir) {
		this.tarikhAkhir = tarikhAkhir;
	}

	public Long getIdUnitluas() {
		return this.idUnitluas;
	}

	public void setIdUnitluas(Long idUnitluas) {
		this.idUnitluas = idUnitluas;
	}

	public Long getLuasSewa() {
		return this.luasSewa;
	}

	public void setLuasSewa(Long luasSewa) {
		this.luasSewa = luasSewa;
	}

	public Long getIdPermohonan() {
		return this.idPermohonan;
	}

	public void setIdPermohonan(Long idPermohonan) {
		this.idPermohonan = idPermohonan;
	}

	public Long getTempohPendudukan() {
		return this.tempohPendudukan;
	}

	public void setTempohPendudukan(Long tempohPendudukan) {
		this.tempohPendudukan = tempohPendudukan;
	}

	public Long getUnitTempoh() {
		return this.unitTempoh;
	}

	public void setUnitTempoh(Long unitTempoh) {
		this.unitTempoh = unitTempoh;
	}

	public Double getTawaranPampasan() {
		return this.tawaranPampasan;
	}

	public void setTawaranPampasan(Double tawaranPampasan) {
		this.tawaranPampasan = tawaranPampasan;
	}

	public String getMasa() {
		return this.masa;
	}

	public void setMasa(String masa) {
		this.masa = masa;
	}

	public String getTempat() {
		return this.tempat;
	}

	public void setTempat(String tempat) {
		this.tempat = tempat;
	}

	public Date getTarikhRundingan() {
		return this.tarikhRundingan;
	}

	public void setTarikhRundingan(Date tarikhRundingan) {
		this.tarikhRundingan = tarikhRundingan;
	}

	public Long getIdHakmilik() {
		return this.idHakmilik;
	}

	public void setIdHakmilik(Long idHakmilik) {
		this.idHakmilik = idHakmilik;
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