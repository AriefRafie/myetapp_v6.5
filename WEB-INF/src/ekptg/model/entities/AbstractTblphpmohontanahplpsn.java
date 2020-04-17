package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblphpmohontanahplpsn entity provides the base persistence definition
 * of the Tblphpmohontanahplpsn entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblphpmohontanahplpsn implements
		java.io.Serializable {

	// Fields

	private Long idMohontanahplpsn;
	private Long idPermohonanpelepasan;
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
	private String noWarta;
	private Date tarikhWarta;
	private Long idMenteri;
	private Long idAgensi;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private String flagDaftarHtp;

	// Constructors

	/** default constructor */
	public AbstractTblphpmohontanahplpsn() {
	}

	/** minimal constructor */
	public AbstractTblphpmohontanahplpsn(Long idMohontanahplpsn) {
		this.idMohontanahplpsn = idMohontanahplpsn;
	}

	/** full constructor */
	public AbstractTblphpmohontanahplpsn(Long idMohontanahplpsn,
			Long idPermohonanpelepasan, Long idHakmilik, Long idNegeri,
			Long idDaerah, Long idMukim, Long idJenishm, Long idSyaratnyata,
			Long idSekatan, String noHm, Long idLot, String noLot, Double luas,
			Long idUnitluas, Long idKategori, String noWarta, Date tarikhWarta,
			Long idMenteri, Long idAgensi, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, String flagDaftarHtp) {
		this.idMohontanahplpsn = idMohontanahplpsn;
		this.idPermohonanpelepasan = idPermohonanpelepasan;
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
		this.noWarta = noWarta;
		this.tarikhWarta = tarikhWarta;
		this.idMenteri = idMenteri;
		this.idAgensi = idAgensi;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.flagDaftarHtp = flagDaftarHtp;
	}

	// Property accessors

	public Long getIdMohontanahplpsn() {
		return this.idMohontanahplpsn;
	}

	public void setIdMohontanahplpsn(Long idMohontanahplpsn) {
		this.idMohontanahplpsn = idMohontanahplpsn;
	}

	public Long getIdPermohonanpelepasan() {
		return this.idPermohonanpelepasan;
	}

	public void setIdPermohonanpelepasan(Long idPermohonanpelepasan) {
		this.idPermohonanpelepasan = idPermohonanpelepasan;
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

	public String getNoWarta() {
		return this.noWarta;
	}

	public void setNoWarta(String noWarta) {
		this.noWarta = noWarta;
	}

	public Date getTarikhWarta() {
		return this.tarikhWarta;
	}

	public void setTarikhWarta(Date tarikhWarta) {
		this.tarikhWarta = tarikhWarta;
	}

	public Long getIdMenteri() {
		return this.idMenteri;
	}

	public void setIdMenteri(Long idMenteri) {
		this.idMenteri = idMenteri;
	}

	public Long getIdAgensi() {
		return this.idAgensi;
	}

	public void setIdAgensi(Long idAgensi) {
		this.idAgensi = idAgensi;
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

}