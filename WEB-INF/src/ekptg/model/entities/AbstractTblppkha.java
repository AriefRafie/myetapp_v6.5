package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblppkha entity provides the base persistence definition of the
 * Tblppkha entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblppkha implements java.io.Serializable {

	// Fields

	private Long idHa;
//	private Tblrujppkjenisha tblrujppkjenisha;
//	private Tblppksimati tblppksimati;
        private Long idJenisha;
        private Long idSimati;
	private Long bil;
	private Long idNegeri;
	private Long idDaerah;
	private String jenama;
	private String noDaftar;
	private String noSijil;
	private Double bilUnit;
	private Date tarikhHarta;
	private String alamatHa1;
	private String alamatHa2;
	private String alamatHa3;
	private String poskod;
	private Double nilaiHaTarikhmohon;
	private Double nilaiHaTarikhmati;
	private Long baSimati;
	private Long bbSimati;
	private String catatan;
	private Double nilaiSeunit;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblppkha() {
	}

	/** minimal constructor */
	public AbstractTblppkha(Long idHa) {
		this.idHa = idHa;
		//this.tblppksimati = tblppksimati;
	}

	/** full constructor */
	public AbstractTblppkha(Long idHa, Long idJenisha,
			Long idSimati, Long bil, Long idNegeri, Long idDaerah,
			String jenama, String noDaftar, String noSijil, Double bilUnit,
			Date tarikhHarta, String alamatHa1, String alamatHa2,
			String alamatHa3, String poskod, Double nilaiHaTarikhmohon,
			Double nilaiHaTarikhmati, Long baSimati, Long bbSimati,
			String catatan, Double nilaiSeunit, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		this.idHa = idHa;
	//	this.tblrujppkjenisha = tblrujppkjenisha;
	//	this.tblppksimati = tblppksimati;
                this.idSimati = idSimati;
                this.idJenisha = idJenisha;
		this.bil = bil;
		this.idNegeri = idNegeri;
		this.idDaerah = idDaerah;
		this.jenama = jenama;
		this.noDaftar = noDaftar;
		this.noSijil = noSijil;
		this.bilUnit = bilUnit;
		this.tarikhHarta = tarikhHarta;
		this.alamatHa1 = alamatHa1;
		this.alamatHa2 = alamatHa2;
		this.alamatHa3 = alamatHa3;
		this.poskod = poskod;
		this.nilaiHaTarikhmohon = nilaiHaTarikhmohon;
		this.nilaiHaTarikhmati = nilaiHaTarikhmati;
		this.baSimati = baSimati;
		this.bbSimati = bbSimati;
		this.catatan = catatan;
		this.nilaiSeunit = nilaiSeunit;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdHa() {
		return this.idHa;
	}

	public void setIdHa(Long idHa) {
		this.idHa = idHa;
	}

/*	public Tblrujppkjenisha getTblrujppkjenisha() {
		return this.tblrujppkjenisha;
	}

	public void setTblrujppkjenisha(Tblrujppkjenisha tblrujppkjenisha) {
		this.tblrujppkjenisha = tblrujppkjenisha;
	}

	public Tblppksimati getTblppksimati() {
		return this.tblppksimati;
	}

	public void setTblppksimati(Tblppksimati tblppksimati) {
		this.tblppksimati = tblppksimati;
	}
 */
	public Long getBil() {
		return this.bil;
	}

	public void setBil(Long bil) {
		this.bil = bil;
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

	public String getJenama() {
		return this.jenama;
	}

	public void setJenama(String jenama) {
		this.jenama = jenama;
	}

	public String getNoDaftar() {
		return this.noDaftar;
	}

	public void setNoDaftar(String noDaftar) {
		this.noDaftar = noDaftar;
	}

	public String getNoSijil() {
		return this.noSijil;
	}

	public void setNoSijil(String noSijil) {
		this.noSijil = noSijil;
	}

	public Double getBilUnit() {
		return this.bilUnit;
	}

	public void setBilUnit(Double bilUnit) {
		this.bilUnit = bilUnit;
	}

	public Date getTarikhHarta() {
		return this.tarikhHarta;
	}

	public void setTarikhHarta(Date tarikhHarta) {
		this.tarikhHarta = tarikhHarta;
	}

	public String getAlamatHa1() {
		return this.alamatHa1;
	}

	public void setAlamatHa1(String alamatHa1) {
		this.alamatHa1 = alamatHa1;
	}

	public String getAlamatHa2() {
		return this.alamatHa2;
	}

	public void setAlamatHa2(String alamatHa2) {
		this.alamatHa2 = alamatHa2;
	}

	public String getAlamatHa3() {
		return this.alamatHa3;
	}

	public void setAlamatHa3(String alamatHa3) {
		this.alamatHa3 = alamatHa3;
	}

	public String getPoskod() {
		return this.poskod;
	}

	public void setPoskod(String poskod) {
		this.poskod = poskod;
	}

	public Double getNilaiHaTarikhmohon() {
		return this.nilaiHaTarikhmohon;
	}

	public void setNilaiHaTarikhmohon(Double nilaiHaTarikhmohon) {
		this.nilaiHaTarikhmohon = nilaiHaTarikhmohon;
	}

	public Double getNilaiHaTarikhmati() {
		return this.nilaiHaTarikhmati;
	}

	public void setNilaiHaTarikhmati(Double nilaiHaTarikhmati) {
		this.nilaiHaTarikhmati = nilaiHaTarikhmati;
	}

	public Long getBaSimati() {
		return this.baSimati;
	}

	public void setBaSimati(Long baSimati) {
		this.baSimati = baSimati;
	}

	public Long getBbSimati() {
		return this.bbSimati;
	}

	public void setBbSimati(Long bbSimati) {
		this.bbSimati = bbSimati;
	}

	public String getCatatan() {
		return this.catatan;
	}

	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}

	public Double getNilaiSeunit() {
		return this.nilaiSeunit;
	}

	public void setNilaiSeunit(Double nilaiSeunit) {
		this.nilaiSeunit = nilaiSeunit;
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

    public void setIdSimati(Long idSimati) {
        this.idSimati = idSimati;
    }

    public Long getIdSimati() {
        return idSimati;
    }

    public void setIdJenisha(Long idJenisha) {
        this.idJenisha = idJenisha;
    }

    public Long getIdJenisha() {
        return idJenisha;
    }
}
