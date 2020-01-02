package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblphptanahgantiplpsn entity provides the base persistence definition
 * of the Tblphptanahgantiplpsn entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblphptanahgantiplpsn implements
		java.io.Serializable {

	// Fields

	private Long idTanahganti;
	private Tblphppermohonanpelepasan tblphppermohonanpelepasan;
	private Long idHakmilik;
	private Long idNegeri;
	private Long idDaerah;
	private Long idMukim;
	private Long idJenishm;
	private Long idSyaratnyata;
	private Long idSekatan;
	private String noHm;
	private Long idLot;
	private String noLot;
	private Double luas;
	private Long idUnitluas;
	private Long idKategori;
	private Double kadarNilaianJpph;
	private Double jumlahNilaianJpph;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private String flagDaftarHtp;
	private String flagHakmilikPersekutuan;

	// Constructors

	/** default constructor */
	public AbstractTblphptanahgantiplpsn() {
	}

	/** minimal constructor */
	public AbstractTblphptanahgantiplpsn(Long idTanahganti) {
		this.idTanahganti = idTanahganti;
	}

	/** full constructor */
	public AbstractTblphptanahgantiplpsn(Long idTanahganti,
			Tblphppermohonanpelepasan tblphppermohonanpelepasan,
			Long idHakmilik, Long idNegeri, Long idDaerah, Long idMukim,
			Long idJenishm, Long idSyaratnyata, Long idSekatan, String noHm,
			Long idLot, String noLot, Double luas, Long idUnitluas,
			Long idKategori, Double kadarNilaianJpph, Double jumlahNilaianJpph,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, String flagDaftarHtp,
			String flagHakmilikPersekutuan) {
		this.idTanahganti = idTanahganti;
		this.tblphppermohonanpelepasan = tblphppermohonanpelepasan;
		this.idHakmilik = idHakmilik;
		this.idNegeri = idNegeri;
		this.idDaerah = idDaerah;
		this.idMukim = idMukim;
		this.idJenishm = idJenishm;
		this.idSyaratnyata = idSyaratnyata;
		this.idSekatan = idSekatan;
		this.noHm = noHm;
		this.idLot = idLot;
		this.noLot = noLot;
		this.luas = luas;
		this.idUnitluas = idUnitluas;
		this.idKategori = idKategori;
		this.kadarNilaianJpph = kadarNilaianJpph;
		this.jumlahNilaianJpph = jumlahNilaianJpph;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.flagDaftarHtp = flagDaftarHtp;
		this.flagHakmilikPersekutuan = flagHakmilikPersekutuan;
	}

	// Property accessors

	public Long getIdTanahganti() {
		return this.idTanahganti;
	}

	public void setIdTanahganti(Long idTanahganti) {
		this.idTanahganti = idTanahganti;
	}

	public Tblphppermohonanpelepasan getTblphppermohonanpelepasan() {
		return this.tblphppermohonanpelepasan;
	}

	public void setTblphppermohonanpelepasan(
			Tblphppermohonanpelepasan tblphppermohonanpelepasan) {
		this.tblphppermohonanpelepasan = tblphppermohonanpelepasan;
	}

	public Long getIdHakmilik() {
		return this.idHakmilik;
	}

	public void setIdHakmilik(Long idHakmilik) {
		this.idHakmilik = idHakmilik;
	}

	public Long getIdNegeri() {
		return this.idNegeri;
	}

	public void setIdNegeri(Long idNegeri) {
		this.idNegeri = idNegeri;
	}

	public Long getIdDaerah() {
		return this.idDaerah;
	}

	public void setIdDaerah(Long idDaerah) {
		this.idDaerah = idDaerah;
	}

	public Long getIdMukim() {
		return this.idMukim;
	}

	public void setIdMukim(Long idMukim) {
		this.idMukim = idMukim;
	}

	public Long getIdJenishm() {
		return this.idJenishm;
	}

	public void setIdJenishm(Long idJenishm) {
		this.idJenishm = idJenishm;
	}

	public Long getIdSyaratnyata() {
		return this.idSyaratnyata;
	}

	public void setIdSyaratnyata(Long idSyaratnyata) {
		this.idSyaratnyata = idSyaratnyata;
	}

	public Long getIdSekatan() {
		return this.idSekatan;
	}

	public void setIdSekatan(Long idSekatan) {
		this.idSekatan = idSekatan;
	}

	public String getNoHm() {
		return this.noHm;
	}

	public void setNoHm(String noHm) {
		this.noHm = noHm;
	}

	public Long getIdLot() {
		return this.idLot;
	}

	public void setIdLot(Long idLot) {
		this.idLot = idLot;
	}

	public String getNoLot() {
		return this.noLot;
	}

	public void setNoLot(String noLot) {
		this.noLot = noLot;
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

	public Long getIdKategori() {
		return this.idKategori;
	}

	public void setIdKategori(Long idKategori) {
		this.idKategori = idKategori;
	}

	public Double getKadarNilaianJpph() {
		return this.kadarNilaianJpph;
	}

	public void setKadarNilaianJpph(Double kadarNilaianJpph) {
		this.kadarNilaianJpph = kadarNilaianJpph;
	}

	public Double getJumlahNilaianJpph() {
		return this.jumlahNilaianJpph;
	}

	public void setJumlahNilaianJpph(Double jumlahNilaianJpph) {
		this.jumlahNilaianJpph = jumlahNilaianJpph;
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

	public String getFlagDaftarHtp() {
		return this.flagDaftarHtp;
	}

	public void setFlagDaftarHtp(String flagDaftarHtp) {
		this.flagDaftarHtp = flagDaftarHtp;
	}

	public String getFlagHakmilikPersekutuan() {
		return this.flagHakmilikPersekutuan;
	}

	public void setFlagHakmilikPersekutuan(String flagHakmilikPersekutuan) {
		this.flagHakmilikPersekutuan = flagHakmilikPersekutuan;
	}

}