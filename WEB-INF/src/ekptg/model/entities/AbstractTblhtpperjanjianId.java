package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblhtpperjanjianId entity provides the base persistence definition of
 * the TblhtpperjanjianId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtpperjanjianId implements
		java.io.Serializable {

	// Fields

	private String noRujukanSeksyen;
	private Date tarikhKeputusan;
	private String pegawai;
	private String noKontrak;
	private Date tarikhJanji;
	private Double nilaiTanah;
	private Double hargaBeli;
	private String bilHmBeli;
	private String bilPtkbeli;
	private String bilUnitBeli;
	private Date tarikhPtpbeli;
	private Date tarikh14a;
	private String tempohPajak;
	private Date tarikhMulaPjk;
	private Date tarikhTamatpjk;
	private Double kadarBayarPajak;
	private Double kadarPjkBerikut;
	private String kategoriCukai;
	private Double kadarCukai;
	private Double kadarCukaiBerikut;
	private Date tarikhTerima15a;
	private Date tarikhHntr15a;
	private Date tarikhTandatangan15a;
	private Double hargaTambahan;
	private Date tarikhSerah;
	private Date tarikhSerahSebenar;
	private String namak;
	private String alamatk1;
	private String alamatk2;
	private String alamatk3;
	private String poskod;
	private String negerik;
	private String tempohBulan;
	private Date tarikhTandatangan12a;
	private Date tarikhHntr12a;
	private Date tarikhDaf12a;
	private Date tarikhMoa;
	private Double nilaianProjek;
	private String caraLaksanafee;
	private Double nilaiBgn;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblhtpperjanjianId() {
	}

	/** minimal constructor */
	public AbstractTblhtpperjanjianId(String noRujukanSeksyen) {
		this.noRujukanSeksyen = noRujukanSeksyen;
	}

	/** full constructor */
	public AbstractTblhtpperjanjianId(String noRujukanSeksyen,
			Date tarikhKeputusan, String pegawai, String noKontrak,
			Date tarikhJanji, Double nilaiTanah, Double hargaBeli,
			String bilHmBeli, String bilPtkbeli, String bilUnitBeli,
			Date tarikhPtpbeli, Date tarikh14a, String tempohPajak,
			Date tarikhMulaPjk, Date tarikhTamatpjk, Double kadarBayarPajak,
			Double kadarPjkBerikut, String kategoriCukai, Double kadarCukai,
			Double kadarCukaiBerikut, Date tarikhTerima15a, Date tarikhHntr15a,
			Date tarikhTandatangan15a, Double hargaTambahan, Date tarikhSerah,
			Date tarikhSerahSebenar, String namak, String alamatk1,
			String alamatk2, String alamatk3, String poskod, String negerik,
			String tempohBulan, Date tarikhTandatangan12a, Date tarikhHntr12a,
			Date tarikhDaf12a, Date tarikhMoa, Double nilaianProjek,
			String caraLaksanafee, Double nilaiBgn, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		this.noRujukanSeksyen = noRujukanSeksyen;
		this.tarikhKeputusan = tarikhKeputusan;
		this.pegawai = pegawai;
		this.noKontrak = noKontrak;
		this.tarikhJanji = tarikhJanji;
		this.nilaiTanah = nilaiTanah;
		this.hargaBeli = hargaBeli;
		this.bilHmBeli = bilHmBeli;
		this.bilPtkbeli = bilPtkbeli;
		this.bilUnitBeli = bilUnitBeli;
		this.tarikhPtpbeli = tarikhPtpbeli;
		this.tarikh14a = tarikh14a;
		this.tempohPajak = tempohPajak;
		this.tarikhMulaPjk = tarikhMulaPjk;
		this.tarikhTamatpjk = tarikhTamatpjk;
		this.kadarBayarPajak = kadarBayarPajak;
		this.kadarPjkBerikut = kadarPjkBerikut;
		this.kategoriCukai = kategoriCukai;
		this.kadarCukai = kadarCukai;
		this.kadarCukaiBerikut = kadarCukaiBerikut;
		this.tarikhTerima15a = tarikhTerima15a;
		this.tarikhHntr15a = tarikhHntr15a;
		this.tarikhTandatangan15a = tarikhTandatangan15a;
		this.hargaTambahan = hargaTambahan;
		this.tarikhSerah = tarikhSerah;
		this.tarikhSerahSebenar = tarikhSerahSebenar;
		this.namak = namak;
		this.alamatk1 = alamatk1;
		this.alamatk2 = alamatk2;
		this.alamatk3 = alamatk3;
		this.poskod = poskod;
		this.negerik = negerik;
		this.tempohBulan = tempohBulan;
		this.tarikhTandatangan12a = tarikhTandatangan12a;
		this.tarikhHntr12a = tarikhHntr12a;
		this.tarikhDaf12a = tarikhDaf12a;
		this.tarikhMoa = tarikhMoa;
		this.nilaianProjek = nilaianProjek;
		this.caraLaksanafee = caraLaksanafee;
		this.nilaiBgn = nilaiBgn;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public String getNoRujukanSeksyen() {
		return this.noRujukanSeksyen;
	}

	public void setNoRujukanSeksyen(String noRujukanSeksyen) {
		this.noRujukanSeksyen = noRujukanSeksyen;
	}

	public Date getTarikhKeputusan() {
		return this.tarikhKeputusan;
	}

	public void setTarikhKeputusan(Date tarikhKeputusan) {
		this.tarikhKeputusan = tarikhKeputusan;
	}

	public String getPegawai() {
		return this.pegawai;
	}

	public void setPegawai(String pegawai) {
		this.pegawai = pegawai;
	}

	public String getNoKontrak() {
		return this.noKontrak;
	}

	public void setNoKontrak(String noKontrak) {
		this.noKontrak = noKontrak;
	}

	public Date getTarikhJanji() {
		return this.tarikhJanji;
	}

	public void setTarikhJanji(Date tarikhJanji) {
		this.tarikhJanji = tarikhJanji;
	}

	public Double getNilaiTanah() {
		return this.nilaiTanah;
	}

	public void setNilaiTanah(Double nilaiTanah) {
		this.nilaiTanah = nilaiTanah;
	}

	public Double getHargaBeli() {
		return this.hargaBeli;
	}

	public void setHargaBeli(Double hargaBeli) {
		this.hargaBeli = hargaBeli;
	}

	public String getBilHmBeli() {
		return this.bilHmBeli;
	}

	public void setBilHmBeli(String bilHmBeli) {
		this.bilHmBeli = bilHmBeli;
	}

	public String getBilPtkbeli() {
		return this.bilPtkbeli;
	}

	public void setBilPtkbeli(String bilPtkbeli) {
		this.bilPtkbeli = bilPtkbeli;
	}

	public String getBilUnitBeli() {
		return this.bilUnitBeli;
	}

	public void setBilUnitBeli(String bilUnitBeli) {
		this.bilUnitBeli = bilUnitBeli;
	}

	public Date getTarikhPtpbeli() {
		return this.tarikhPtpbeli;
	}

	public void setTarikhPtpbeli(Date tarikhPtpbeli) {
		this.tarikhPtpbeli = tarikhPtpbeli;
	}

	public Date getTarikh14a() {
		return this.tarikh14a;
	}

	public void setTarikh14a(Date tarikh14a) {
		this.tarikh14a = tarikh14a;
	}

	public String getTempohPajak() {
		return this.tempohPajak;
	}

	public void setTempohPajak(String tempohPajak) {
		this.tempohPajak = tempohPajak;
	}

	public Date getTarikhMulaPjk() {
		return this.tarikhMulaPjk;
	}

	public void setTarikhMulaPjk(Date tarikhMulaPjk) {
		this.tarikhMulaPjk = tarikhMulaPjk;
	}

	public Date getTarikhTamatpjk() {
		return this.tarikhTamatpjk;
	}

	public void setTarikhTamatpjk(Date tarikhTamatpjk) {
		this.tarikhTamatpjk = tarikhTamatpjk;
	}

	public Double getKadarBayarPajak() {
		return this.kadarBayarPajak;
	}

	public void setKadarBayarPajak(Double kadarBayarPajak) {
		this.kadarBayarPajak = kadarBayarPajak;
	}

	public Double getKadarPjkBerikut() {
		return this.kadarPjkBerikut;
	}

	public void setKadarPjkBerikut(Double kadarPjkBerikut) {
		this.kadarPjkBerikut = kadarPjkBerikut;
	}

	public String getKategoriCukai() {
		return this.kategoriCukai;
	}

	public void setKategoriCukai(String kategoriCukai) {
		this.kategoriCukai = kategoriCukai;
	}

	public Double getKadarCukai() {
		return this.kadarCukai;
	}

	public void setKadarCukai(Double kadarCukai) {
		this.kadarCukai = kadarCukai;
	}

	public Double getKadarCukaiBerikut() {
		return this.kadarCukaiBerikut;
	}

	public void setKadarCukaiBerikut(Double kadarCukaiBerikut) {
		this.kadarCukaiBerikut = kadarCukaiBerikut;
	}

	public Date getTarikhTerima15a() {
		return this.tarikhTerima15a;
	}

	public void setTarikhTerima15a(Date tarikhTerima15a) {
		this.tarikhTerima15a = tarikhTerima15a;
	}

	public Date getTarikhHntr15a() {
		return this.tarikhHntr15a;
	}

	public void setTarikhHntr15a(Date tarikhHntr15a) {
		this.tarikhHntr15a = tarikhHntr15a;
	}

	public Date getTarikhTandatangan15a() {
		return this.tarikhTandatangan15a;
	}

	public void setTarikhTandatangan15a(Date tarikhTandatangan15a) {
		this.tarikhTandatangan15a = tarikhTandatangan15a;
	}

	public Double getHargaTambahan() {
		return this.hargaTambahan;
	}

	public void setHargaTambahan(Double hargaTambahan) {
		this.hargaTambahan = hargaTambahan;
	}

	public Date getTarikhSerah() {
		return this.tarikhSerah;
	}

	public void setTarikhSerah(Date tarikhSerah) {
		this.tarikhSerah = tarikhSerah;
	}

	public Date getTarikhSerahSebenar() {
		return this.tarikhSerahSebenar;
	}

	public void setTarikhSerahSebenar(Date tarikhSerahSebenar) {
		this.tarikhSerahSebenar = tarikhSerahSebenar;
	}

	public String getNamak() {
		return this.namak;
	}

	public void setNamak(String namak) {
		this.namak = namak;
	}

	public String getAlamatk1() {
		return this.alamatk1;
	}

	public void setAlamatk1(String alamatk1) {
		this.alamatk1 = alamatk1;
	}

	public String getAlamatk2() {
		return this.alamatk2;
	}

	public void setAlamatk2(String alamatk2) {
		this.alamatk2 = alamatk2;
	}

	public String getAlamatk3() {
		return this.alamatk3;
	}

	public void setAlamatk3(String alamatk3) {
		this.alamatk3 = alamatk3;
	}

	public String getPoskod() {
		return this.poskod;
	}

	public void setPoskod(String poskod) {
		this.poskod = poskod;
	}

	public String getNegerik() {
		return this.negerik;
	}

	public void setNegerik(String negerik) {
		this.negerik = negerik;
	}

	public String getTempohBulan() {
		return this.tempohBulan;
	}

	public void setTempohBulan(String tempohBulan) {
		this.tempohBulan = tempohBulan;
	}

	public Date getTarikhTandatangan12a() {
		return this.tarikhTandatangan12a;
	}

	public void setTarikhTandatangan12a(Date tarikhTandatangan12a) {
		this.tarikhTandatangan12a = tarikhTandatangan12a;
	}

	public Date getTarikhHntr12a() {
		return this.tarikhHntr12a;
	}

	public void setTarikhHntr12a(Date tarikhHntr12a) {
		this.tarikhHntr12a = tarikhHntr12a;
	}

	public Date getTarikhDaf12a() {
		return this.tarikhDaf12a;
	}

	public void setTarikhDaf12a(Date tarikhDaf12a) {
		this.tarikhDaf12a = tarikhDaf12a;
	}

	public Date getTarikhMoa() {
		return this.tarikhMoa;
	}

	public void setTarikhMoa(Date tarikhMoa) {
		this.tarikhMoa = tarikhMoa;
	}

	public Double getNilaianProjek() {
		return this.nilaianProjek;
	}

	public void setNilaianProjek(Double nilaianProjek) {
		this.nilaianProjek = nilaianProjek;
	}

	public String getCaraLaksanafee() {
		return this.caraLaksanafee;
	}

	public void setCaraLaksanafee(String caraLaksanafee) {
		this.caraLaksanafee = caraLaksanafee;
	}

	public Double getNilaiBgn() {
		return this.nilaiBgn;
	}

	public void setNilaiBgn(Double nilaiBgn) {
		this.nilaiBgn = nilaiBgn;
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractTblhtpperjanjianId))
			return false;
		AbstractTblhtpperjanjianId castOther = (AbstractTblhtpperjanjianId) other;

		return ((this.getNoRujukanSeksyen() == castOther.getNoRujukanSeksyen()) || (this
				.getNoRujukanSeksyen() != null
				&& castOther.getNoRujukanSeksyen() != null && this
				.getNoRujukanSeksyen().equals(castOther.getNoRujukanSeksyen())))
				&& ((this.getTarikhKeputusan() == castOther
						.getTarikhKeputusan()) || (this.getTarikhKeputusan() != null
						&& castOther.getTarikhKeputusan() != null && this
						.getTarikhKeputusan().equals(
								castOther.getTarikhKeputusan())))
				&& ((this.getPegawai() == castOther.getPegawai()) || (this
						.getPegawai() != null
						&& castOther.getPegawai() != null && this.getPegawai()
						.equals(castOther.getPegawai())))
				&& ((this.getNoKontrak() == castOther.getNoKontrak()) || (this
						.getNoKontrak() != null
						&& castOther.getNoKontrak() != null && this
						.getNoKontrak().equals(castOther.getNoKontrak())))
				&& ((this.getTarikhJanji() == castOther.getTarikhJanji()) || (this
						.getTarikhJanji() != null
						&& castOther.getTarikhJanji() != null && this
						.getTarikhJanji().equals(castOther.getTarikhJanji())))
				&& ((this.getNilaiTanah() == castOther.getNilaiTanah()) || (this
						.getNilaiTanah() != null
						&& castOther.getNilaiTanah() != null && this
						.getNilaiTanah().equals(castOther.getNilaiTanah())))
				&& ((this.getHargaBeli() == castOther.getHargaBeli()) || (this
						.getHargaBeli() != null
						&& castOther.getHargaBeli() != null && this
						.getHargaBeli().equals(castOther.getHargaBeli())))
				&& ((this.getBilHmBeli() == castOther.getBilHmBeli()) || (this
						.getBilHmBeli() != null
						&& castOther.getBilHmBeli() != null && this
						.getBilHmBeli().equals(castOther.getBilHmBeli())))
				&& ((this.getBilPtkbeli() == castOther.getBilPtkbeli()) || (this
						.getBilPtkbeli() != null
						&& castOther.getBilPtkbeli() != null && this
						.getBilPtkbeli().equals(castOther.getBilPtkbeli())))
				&& ((this.getBilUnitBeli() == castOther.getBilUnitBeli()) || (this
						.getBilUnitBeli() != null
						&& castOther.getBilUnitBeli() != null && this
						.getBilUnitBeli().equals(castOther.getBilUnitBeli())))
				&& ((this.getTarikhPtpbeli() == castOther.getTarikhPtpbeli()) || (this
						.getTarikhPtpbeli() != null
						&& castOther.getTarikhPtpbeli() != null && this
						.getTarikhPtpbeli()
						.equals(castOther.getTarikhPtpbeli())))
				&& ((this.getTarikh14a() == castOther.getTarikh14a()) || (this
						.getTarikh14a() != null
						&& castOther.getTarikh14a() != null && this
						.getTarikh14a().equals(castOther.getTarikh14a())))
				&& ((this.getTempohPajak() == castOther.getTempohPajak()) || (this
						.getTempohPajak() != null
						&& castOther.getTempohPajak() != null && this
						.getTempohPajak().equals(castOther.getTempohPajak())))
				&& ((this.getTarikhMulaPjk() == castOther.getTarikhMulaPjk()) || (this
						.getTarikhMulaPjk() != null
						&& castOther.getTarikhMulaPjk() != null && this
						.getTarikhMulaPjk()
						.equals(castOther.getTarikhMulaPjk())))
				&& ((this.getTarikhTamatpjk() == castOther.getTarikhTamatpjk()) || (this
						.getTarikhTamatpjk() != null
						&& castOther.getTarikhTamatpjk() != null && this
						.getTarikhTamatpjk().equals(
								castOther.getTarikhTamatpjk())))
				&& ((this.getKadarBayarPajak() == castOther
						.getKadarBayarPajak()) || (this.getKadarBayarPajak() != null
						&& castOther.getKadarBayarPajak() != null && this
						.getKadarBayarPajak().equals(
								castOther.getKadarBayarPajak())))
				&& ((this.getKadarPjkBerikut() == castOther
						.getKadarPjkBerikut()) || (this.getKadarPjkBerikut() != null
						&& castOther.getKadarPjkBerikut() != null && this
						.getKadarPjkBerikut().equals(
								castOther.getKadarPjkBerikut())))
				&& ((this.getKategoriCukai() == castOther.getKategoriCukai()) || (this
						.getKategoriCukai() != null
						&& castOther.getKategoriCukai() != null && this
						.getKategoriCukai()
						.equals(castOther.getKategoriCukai())))
				&& ((this.getKadarCukai() == castOther.getKadarCukai()) || (this
						.getKadarCukai() != null
						&& castOther.getKadarCukai() != null && this
						.getKadarCukai().equals(castOther.getKadarCukai())))
				&& ((this.getKadarCukaiBerikut() == castOther
						.getKadarCukaiBerikut()) || (this
						.getKadarCukaiBerikut() != null
						&& castOther.getKadarCukaiBerikut() != null && this
						.getKadarCukaiBerikut().equals(
								castOther.getKadarCukaiBerikut())))
				&& ((this.getTarikhTerima15a() == castOther
						.getTarikhTerima15a()) || (this.getTarikhTerima15a() != null
						&& castOther.getTarikhTerima15a() != null && this
						.getTarikhTerima15a().equals(
								castOther.getTarikhTerima15a())))
				&& ((this.getTarikhHntr15a() == castOther.getTarikhHntr15a()) || (this
						.getTarikhHntr15a() != null
						&& castOther.getTarikhHntr15a() != null && this
						.getTarikhHntr15a()
						.equals(castOther.getTarikhHntr15a())))
				&& ((this.getTarikhTandatangan15a() == castOther
						.getTarikhTandatangan15a()) || (this
						.getTarikhTandatangan15a() != null
						&& castOther.getTarikhTandatangan15a() != null && this
						.getTarikhTandatangan15a().equals(
								castOther.getTarikhTandatangan15a())))
				&& ((this.getHargaTambahan() == castOther.getHargaTambahan()) || (this
						.getHargaTambahan() != null
						&& castOther.getHargaTambahan() != null && this
						.getHargaTambahan()
						.equals(castOther.getHargaTambahan())))
				&& ((this.getTarikhSerah() == castOther.getTarikhSerah()) || (this
						.getTarikhSerah() != null
						&& castOther.getTarikhSerah() != null && this
						.getTarikhSerah().equals(castOther.getTarikhSerah())))
				&& ((this.getTarikhSerahSebenar() == castOther
						.getTarikhSerahSebenar()) || (this
						.getTarikhSerahSebenar() != null
						&& castOther.getTarikhSerahSebenar() != null && this
						.getTarikhSerahSebenar().equals(
								castOther.getTarikhSerahSebenar())))
				&& ((this.getNamak() == castOther.getNamak()) || (this
						.getNamak() != null
						&& castOther.getNamak() != null && this.getNamak()
						.equals(castOther.getNamak())))
				&& ((this.getAlamatk1() == castOther.getAlamatk1()) || (this
						.getAlamatk1() != null
						&& castOther.getAlamatk1() != null && this
						.getAlamatk1().equals(castOther.getAlamatk1())))
				&& ((this.getAlamatk2() == castOther.getAlamatk2()) || (this
						.getAlamatk2() != null
						&& castOther.getAlamatk2() != null && this
						.getAlamatk2().equals(castOther.getAlamatk2())))
				&& ((this.getAlamatk3() == castOther.getAlamatk3()) || (this
						.getAlamatk3() != null
						&& castOther.getAlamatk3() != null && this
						.getAlamatk3().equals(castOther.getAlamatk3())))
				&& ((this.getPoskod() == castOther.getPoskod()) || (this
						.getPoskod() != null
						&& castOther.getPoskod() != null && this.getPoskod()
						.equals(castOther.getPoskod())))
				&& ((this.getNegerik() == castOther.getNegerik()) || (this
						.getNegerik() != null
						&& castOther.getNegerik() != null && this.getNegerik()
						.equals(castOther.getNegerik())))
				&& ((this.getTempohBulan() == castOther.getTempohBulan()) || (this
						.getTempohBulan() != null
						&& castOther.getTempohBulan() != null && this
						.getTempohBulan().equals(castOther.getTempohBulan())))
				&& ((this.getTarikhTandatangan12a() == castOther
						.getTarikhTandatangan12a()) || (this
						.getTarikhTandatangan12a() != null
						&& castOther.getTarikhTandatangan12a() != null && this
						.getTarikhTandatangan12a().equals(
								castOther.getTarikhTandatangan12a())))
				&& ((this.getTarikhHntr12a() == castOther.getTarikhHntr12a()) || (this
						.getTarikhHntr12a() != null
						&& castOther.getTarikhHntr12a() != null && this
						.getTarikhHntr12a()
						.equals(castOther.getTarikhHntr12a())))
				&& ((this.getTarikhDaf12a() == castOther.getTarikhDaf12a()) || (this
						.getTarikhDaf12a() != null
						&& castOther.getTarikhDaf12a() != null && this
						.getTarikhDaf12a().equals(castOther.getTarikhDaf12a())))
				&& ((this.getTarikhMoa() == castOther.getTarikhMoa()) || (this
						.getTarikhMoa() != null
						&& castOther.getTarikhMoa() != null && this
						.getTarikhMoa().equals(castOther.getTarikhMoa())))
				&& ((this.getNilaianProjek() == castOther.getNilaianProjek()) || (this
						.getNilaianProjek() != null
						&& castOther.getNilaianProjek() != null && this
						.getNilaianProjek()
						.equals(castOther.getNilaianProjek())))
				&& ((this.getCaraLaksanafee() == castOther.getCaraLaksanafee()) || (this
						.getCaraLaksanafee() != null
						&& castOther.getCaraLaksanafee() != null && this
						.getCaraLaksanafee().equals(
								castOther.getCaraLaksanafee())))
				&& ((this.getNilaiBgn() == castOther.getNilaiBgn()) || (this
						.getNilaiBgn() != null
						&& castOther.getNilaiBgn() != null && this
						.getNilaiBgn().equals(castOther.getNilaiBgn())))
				&& ((this.getIdMasuk() == castOther.getIdMasuk()) || (this
						.getIdMasuk() != null
						&& castOther.getIdMasuk() != null && this.getIdMasuk()
						.equals(castOther.getIdMasuk())))
				&& ((this.getTarikhMasuk() == castOther.getTarikhMasuk()) || (this
						.getTarikhMasuk() != null
						&& castOther.getTarikhMasuk() != null && this
						.getTarikhMasuk().equals(castOther.getTarikhMasuk())))
				&& ((this.getIdKemaskini() == castOther.getIdKemaskini()) || (this
						.getIdKemaskini() != null
						&& castOther.getIdKemaskini() != null && this
						.getIdKemaskini().equals(castOther.getIdKemaskini())))
				&& ((this.getTarikhKemaskini() == castOther
						.getTarikhKemaskini()) || (this.getTarikhKemaskini() != null
						&& castOther.getTarikhKemaskini() != null && this
						.getTarikhKemaskini().equals(
								castOther.getTarikhKemaskini())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getNoRujukanSeksyen() == null ? 0 : this
						.getNoRujukanSeksyen().hashCode());
		result = 37
				* result
				+ (getTarikhKeputusan() == null ? 0 : this.getTarikhKeputusan()
						.hashCode());
		result = 37 * result
				+ (getPegawai() == null ? 0 : this.getPegawai().hashCode());
		result = 37 * result
				+ (getNoKontrak() == null ? 0 : this.getNoKontrak().hashCode());
		result = 37
				* result
				+ (getTarikhJanji() == null ? 0 : this.getTarikhJanji()
						.hashCode());
		result = 37
				* result
				+ (getNilaiTanah() == null ? 0 : this.getNilaiTanah()
						.hashCode());
		result = 37 * result
				+ (getHargaBeli() == null ? 0 : this.getHargaBeli().hashCode());
		result = 37 * result
				+ (getBilHmBeli() == null ? 0 : this.getBilHmBeli().hashCode());
		result = 37
				* result
				+ (getBilPtkbeli() == null ? 0 : this.getBilPtkbeli()
						.hashCode());
		result = 37
				* result
				+ (getBilUnitBeli() == null ? 0 : this.getBilUnitBeli()
						.hashCode());
		result = 37
				* result
				+ (getTarikhPtpbeli() == null ? 0 : this.getTarikhPtpbeli()
						.hashCode());
		result = 37 * result
				+ (getTarikh14a() == null ? 0 : this.getTarikh14a().hashCode());
		result = 37
				* result
				+ (getTempohPajak() == null ? 0 : this.getTempohPajak()
						.hashCode());
		result = 37
				* result
				+ (getTarikhMulaPjk() == null ? 0 : this.getTarikhMulaPjk()
						.hashCode());
		result = 37
				* result
				+ (getTarikhTamatpjk() == null ? 0 : this.getTarikhTamatpjk()
						.hashCode());
		result = 37
				* result
				+ (getKadarBayarPajak() == null ? 0 : this.getKadarBayarPajak()
						.hashCode());
		result = 37
				* result
				+ (getKadarPjkBerikut() == null ? 0 : this.getKadarPjkBerikut()
						.hashCode());
		result = 37
				* result
				+ (getKategoriCukai() == null ? 0 : this.getKategoriCukai()
						.hashCode());
		result = 37
				* result
				+ (getKadarCukai() == null ? 0 : this.getKadarCukai()
						.hashCode());
		result = 37
				* result
				+ (getKadarCukaiBerikut() == null ? 0 : this
						.getKadarCukaiBerikut().hashCode());
		result = 37
				* result
				+ (getTarikhTerima15a() == null ? 0 : this.getTarikhTerima15a()
						.hashCode());
		result = 37
				* result
				+ (getTarikhHntr15a() == null ? 0 : this.getTarikhHntr15a()
						.hashCode());
		result = 37
				* result
				+ (getTarikhTandatangan15a() == null ? 0 : this
						.getTarikhTandatangan15a().hashCode());
		result = 37
				* result
				+ (getHargaTambahan() == null ? 0 : this.getHargaTambahan()
						.hashCode());
		result = 37
				* result
				+ (getTarikhSerah() == null ? 0 : this.getTarikhSerah()
						.hashCode());
		result = 37
				* result
				+ (getTarikhSerahSebenar() == null ? 0 : this
						.getTarikhSerahSebenar().hashCode());
		result = 37 * result
				+ (getNamak() == null ? 0 : this.getNamak().hashCode());
		result = 37 * result
				+ (getAlamatk1() == null ? 0 : this.getAlamatk1().hashCode());
		result = 37 * result
				+ (getAlamatk2() == null ? 0 : this.getAlamatk2().hashCode());
		result = 37 * result
				+ (getAlamatk3() == null ? 0 : this.getAlamatk3().hashCode());
		result = 37 * result
				+ (getPoskod() == null ? 0 : this.getPoskod().hashCode());
		result = 37 * result
				+ (getNegerik() == null ? 0 : this.getNegerik().hashCode());
		result = 37
				* result
				+ (getTempohBulan() == null ? 0 : this.getTempohBulan()
						.hashCode());
		result = 37
				* result
				+ (getTarikhTandatangan12a() == null ? 0 : this
						.getTarikhTandatangan12a().hashCode());
		result = 37
				* result
				+ (getTarikhHntr12a() == null ? 0 : this.getTarikhHntr12a()
						.hashCode());
		result = 37
				* result
				+ (getTarikhDaf12a() == null ? 0 : this.getTarikhDaf12a()
						.hashCode());
		result = 37 * result
				+ (getTarikhMoa() == null ? 0 : this.getTarikhMoa().hashCode());
		result = 37
				* result
				+ (getNilaianProjek() == null ? 0 : this.getNilaianProjek()
						.hashCode());
		result = 37
				* result
				+ (getCaraLaksanafee() == null ? 0 : this.getCaraLaksanafee()
						.hashCode());
		result = 37 * result
				+ (getNilaiBgn() == null ? 0 : this.getNilaiBgn().hashCode());
		result = 37 * result
				+ (getIdMasuk() == null ? 0 : this.getIdMasuk().hashCode());
		result = 37
				* result
				+ (getTarikhMasuk() == null ? 0 : this.getTarikhMasuk()
						.hashCode());
		result = 37
				* result
				+ (getIdKemaskini() == null ? 0 : this.getIdKemaskini()
						.hashCode());
		result = 37
				* result
				+ (getTarikhKemaskini() == null ? 0 : this.getTarikhKemaskini()
						.hashCode());
		return result;
	}

}