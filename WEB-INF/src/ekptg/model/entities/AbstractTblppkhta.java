package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblppkhta entity provides the base persistence definition of the
 * Tblppkhta entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblppkhta implements java.io.Serializable {

	// Fields

	private Long idHta;
//	private Tblppksimati tblppksimati;
        private Long idSimati;
	private String noHakmilik;
	private String noPt;
	private Double nilaiHtaTarikhmohon;
	private Double nilaiHtaTarikhmati;
	private Long idKategori;
        private Long idJenishm;
	private Long idJenispb;
	private Long idNegeri;
	private Long idDaerah;
	private Long idMukim;
	private Long idLuas;
	private String luas;
	private String luasHmp;
	private String noCagaran;
	private String noPajakan;
	private String jenisTnh;
	private String alamatHta1;
	private String alamatHta2;
	private String alamatHta3;
	private String bandarHta;
	private String poskodHta;
	private Date tarikhPerjanjian;
	private String namaPemaju;
	private String alamatPemaju1;
	private String alamatPemaju2;
	private String alamatPemaju3;
	private String bandarPemaju;
	private String poskodPemaju;
	private Long idNegeripemaju;
	private String catatan;
	private Long baSimati;
	private Long bbSimati;
	private String noBangunan;
	private String noTingkat;
	private String noPetak;
	private String noStrata;
	private String maklumatTanah;
	private String noPerjanjian;
	private String jenisHta;
	private String statusPemilikan;
	private String tanggungan;
	private String noPerserahan;
	private String namaRancangan;
	private String noRoh;
	private String noLotId;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
        private String flagKategoriHta;
	private Set tblppkperintahhtaobmsts = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblppkhta() {
	}

	/** minimal constructor */
	public AbstractTblppkhta(Long idHta) {
		this.idHta = idHta;
	//	this.tblppksimati = tblppksimati;
	}

	/** full constructor */
	public AbstractTblppkhta(Long idHta,Long idSimati,
			String noHakmilik, String noPt, Double nilaiHtaTarikhmohon,
			Double nilaiHtaTarikhmati, Long idKategori, Long idJenishm,
			Long idJenispb, Long idNegeri, Long idDaerah, Long idMukim,
			Long idLuas, String luas, String luasHmp, String noCagaran,
			String noPajakan, String jenisTnh, String alamatHta1,
			String alamatHta2, String alamatHta3, String bandarHta,
			String poskodHta, Date tarikhPerjanjian, String namaPemaju,
			String alamatPemaju1, String alamatPemaju2, String alamatPemaju3,
			String bandarPemaju, String poskodPemaju, Long idNegeripemaju,
			String catatan, Long baSimati, Long bbSimati, String noBangunan,
			String noTingkat, String noPetak, String noStrata,
			String maklumatTanah, String noPerjanjian, String jenisHta,
			String statusPemilikan, String tanggungan, String noPerserahan,
			String namaRancangan, String noRoh, String noLotId, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			String flagKategoriHta,Set tblppkperintahhtaobmsts) {
		this.idHta = idHta;
	//	this.tblppksimati = tblppksimati;
                this.idSimati = idSimati;
		this.noHakmilik = noHakmilik;
		this.noPt = noPt;
		this.nilaiHtaTarikhmohon = nilaiHtaTarikhmohon;
		this.nilaiHtaTarikhmati = nilaiHtaTarikhmati;
		this.idKategori = idKategori;
		this.idJenishm = idJenishm;
		this.idJenispb = idJenispb;
		this.idNegeri = idNegeri;
		this.idDaerah = idDaerah;
		this.idMukim = idMukim;
		this.idLuas = idLuas;
		this.luas = luas;
		this.luasHmp = luasHmp;
		this.noCagaran = noCagaran;
		this.noPajakan = noPajakan;
		this.jenisTnh = jenisTnh;
		this.alamatHta1 = alamatHta1;
		this.alamatHta2 = alamatHta2;
		this.alamatHta3 = alamatHta3;
		this.bandarHta = bandarHta;
		this.poskodHta = poskodHta;
		this.tarikhPerjanjian = tarikhPerjanjian;
		this.namaPemaju = namaPemaju;
		this.alamatPemaju1 = alamatPemaju1;
		this.alamatPemaju2 = alamatPemaju2;
		this.alamatPemaju3 = alamatPemaju3;
		this.bandarPemaju = bandarPemaju;
		this.poskodPemaju = poskodPemaju;
		this.idNegeripemaju = idNegeripemaju;
		this.catatan = catatan;
		this.baSimati = baSimati;
		this.bbSimati = bbSimati;
		this.noBangunan = noBangunan;
		this.noTingkat = noTingkat;
		this.noPetak = noPetak;
		this.noStrata = noStrata;
		this.maklumatTanah = maklumatTanah;
		this.noPerjanjian = noPerjanjian;
		this.jenisHta = jenisHta;
		this.statusPemilikan = statusPemilikan;
		this.tanggungan = tanggungan;
		this.noPerserahan = noPerserahan;
		this.namaRancangan = namaRancangan;
		this.noRoh = noRoh;
		this.noLotId = noLotId;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
                this.flagKategoriHta = flagKategoriHta;
		this.tblppkperintahhtaobmsts = tblppkperintahhtaobmsts;
	}

	// Property accessors

	public Long getIdHta() {
		return this.idHta;
	}

	public void setIdHta(Long idHta) {
		this.idHta = idHta;
	}

/*	public Tblppksimati getTblppksimati() {
		return this.tblppksimati;
	}

	public void setTblppksimati(Tblppksimati tblppksimati) {
		this.tblppksimati = tblppksimati;
	}
*/
	public String getNoHakmilik() {
		return this.noHakmilik;
	}

	public void setNoHakmilik(String noHakmilik) {
		this.noHakmilik = noHakmilik;
	}

	public String getNoPt() {
		return this.noPt;
	}

	public void setNoPt(String noPt) {
		this.noPt = noPt;
	}

	public Double getNilaiHtaTarikhmohon() {
		return this.nilaiHtaTarikhmohon;
	}

	public void setNilaiHtaTarikhmohon(Double nilaiHtaTarikhmohon) {
		this.nilaiHtaTarikhmohon = nilaiHtaTarikhmohon;
	}

	public Double getNilaiHtaTarikhmati() {
		return this.nilaiHtaTarikhmati;
	}

	public void setNilaiHtaTarikhmati(Double nilaiHtaTarikhmati) {
		this.nilaiHtaTarikhmati = nilaiHtaTarikhmati;
	}

	public Long getIdKategori() {
		return this.idKategori;
	}

	public void setIdKategori(Long idKategori) {
		this.idKategori = idKategori;
	}

	public Long getIdJenispb() {
		return this.idJenispb;
	}

	public void setIdJenispb(Long idJenispb) {
		this.idJenispb = idJenispb;
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

	public Long getIdLuas() {
		return this.idLuas;
	}

	public void setIdLuas(Long idLuas) {
		this.idLuas = idLuas;
	}

	public String getLuas() {
		return this.luas;
	}

	public void setLuas(String luas) {
		this.luas = luas;
	}

	public String getLuasHmp() {
		return this.luasHmp;
	}

	public void setLuasHmp(String luasHmp) {
		this.luasHmp = luasHmp;
	}

	public String getNoCagaran() {
		return this.noCagaran;
	}

	public void setNoCagaran(String noCagaran) {
		this.noCagaran = noCagaran;
	}

	public String getNoPajakan() {
		return this.noPajakan;
	}

	public void setNoPajakan(String noPajakan) {
		this.noPajakan = noPajakan;
	}

	public String getJenisTnh() {
		return this.jenisTnh;
	}

	public void setJenisTnh(String jenisTnh) {
		this.jenisTnh = jenisTnh;
	}

	public String getAlamatHta1() {
		return this.alamatHta1;
	}

	public void setAlamatHta1(String alamatHta1) {
		this.alamatHta1 = alamatHta1;
	}

	public String getAlamatHta2() {
		return this.alamatHta2;
	}

	public void setAlamatHta2(String alamatHta2) {
		this.alamatHta2 = alamatHta2;
	}

	public String getAlamatHta3() {
		return this.alamatHta3;
	}

	public void setAlamatHta3(String alamatHta3) {
		this.alamatHta3 = alamatHta3;
	}

	public String getBandarHta() {
		return this.bandarHta;
	}

	public void setBandarHta(String bandarHta) {
		this.bandarHta = bandarHta;
	}

	public String getPoskodHta() {
		return this.poskodHta;
	}

	public void setPoskodHta(String poskodHta) {
		this.poskodHta = poskodHta;
	}

	public Date getTarikhPerjanjian() {
		return this.tarikhPerjanjian;
	}

	public void setTarikhPerjanjian(Date tarikhPerjanjian) {
		this.tarikhPerjanjian = tarikhPerjanjian;
	}

	public String getNamaPemaju() {
		return this.namaPemaju;
	}

	public void setNamaPemaju(String namaPemaju) {
		this.namaPemaju = namaPemaju;
	}

	public String getAlamatPemaju1() {
		return this.alamatPemaju1;
	}

	public void setAlamatPemaju1(String alamatPemaju1) {
		this.alamatPemaju1 = alamatPemaju1;
	}

	public String getAlamatPemaju2() {
		return this.alamatPemaju2;
	}

	public void setAlamatPemaju2(String alamatPemaju2) {
		this.alamatPemaju2 = alamatPemaju2;
	}

	public String getAlamatPemaju3() {
		return this.alamatPemaju3;
	}

	public void setAlamatPemaju3(String alamatPemaju3) {
		this.alamatPemaju3 = alamatPemaju3;
	}

	public String getBandarPemaju() {
		return this.bandarPemaju;
	}

	public void setBandarPemaju(String bandarPemaju) {
		this.bandarPemaju = bandarPemaju;
	}

	public String getPoskodPemaju() {
		return this.poskodPemaju;
	}

	public void setPoskodPemaju(String poskodPemaju) {
		this.poskodPemaju = poskodPemaju;
	}

	public Long getIdNegeripemaju() {
		return this.idNegeripemaju;
	}

	public void setIdNegeripemaju(Long idNegeripemaju) {
		this.idNegeripemaju = idNegeripemaju;
	}

	public String getCatatan() {
		return this.catatan;
	}

	public void setCatatan(String catatan) {
		this.catatan = catatan;
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

	public String getNoBangunan() {
		return this.noBangunan;
	}

	public void setNoBangunan(String noBangunan) {
		this.noBangunan = noBangunan;
	}

	public String getNoTingkat() {
		return this.noTingkat;
	}

	public void setNoTingkat(String noTingkat) {
		this.noTingkat = noTingkat;
	}

	public String getNoPetak() {
		return this.noPetak;
	}

	public void setNoPetak(String noPetak) {
		this.noPetak = noPetak;
	}

	public String getNoStrata() {
		return this.noStrata;
	}

	public void setNoStrata(String noStrata) {
		this.noStrata = noStrata;
	}

	public String getMaklumatTanah() {
		return this.maklumatTanah;
	}

	public void setMaklumatTanah(String maklumatTanah) {
		this.maklumatTanah = maklumatTanah;
	}

	public String getNoPerjanjian() {
		return this.noPerjanjian;
	}

	public void setNoPerjanjian(String noPerjanjian) {
		this.noPerjanjian = noPerjanjian;
	}

	public String getJenisHta() {
		return this.jenisHta;
	}

	public void setJenisHta(String jenisHta) {
		this.jenisHta = jenisHta;
	}

	public String getStatusPemilikan() {
		return this.statusPemilikan;
	}

	public void setStatusPemilikan(String statusPemilikan) {
		this.statusPemilikan = statusPemilikan;
	}

	public String getTanggungan() {
		return this.tanggungan;
	}

	public void setTanggungan(String tanggungan) {
		this.tanggungan = tanggungan;
	}

	public String getNoPerserahan() {
		return this.noPerserahan;
	}

	public void setNoPerserahan(String noPerserahan) {
		this.noPerserahan = noPerserahan;
	}

	public String getNamaRancangan() {
		return this.namaRancangan;
	}

	public void setNamaRancangan(String namaRancangan) {
		this.namaRancangan = namaRancangan;
	}

	public String getNoRoh() {
		return this.noRoh;
	}

	public void setNoRoh(String noRoh) {
		this.noRoh = noRoh;
	}

	public String getNoLotId() {
		return this.noLotId;
	}

	public void setNoLotId(String noLotId) {
		this.noLotId = noLotId;
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

	public Set getTblppkperintahhtaobmsts() {
		return this.tblppkperintahhtaobmsts;
	}

	public void setTblppkperintahhtaobmsts(Set tblppkperintahhtaobmsts) {
		this.tblppkperintahhtaobmsts = tblppkperintahhtaobmsts;
	}

    public void setIdSimati(Long idSimati) {
        this.idSimati = idSimati;
    }

    public Long getIdSimati() {
        return idSimati;
    }

    public void setIdJenishm(Long idJenishm) {
        this.idJenishm = idJenishm;
    }

    public Long getIdJenishm() {
        return idJenishm;
    }
    public String getFlagKategoriHta() {
            return this.flagKategoriHta;
    }

    public void setFlagKategoriHta(String flagKategoriHta) {
            this.flagKategoriHta = flagKategoriHta;
    }
}
