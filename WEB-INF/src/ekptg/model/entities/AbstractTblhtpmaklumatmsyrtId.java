package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblhtpmaklumatmsyrtId entity provides the base persistence definition
 * of the TblhtpmaklumatmsyrtId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblhtpmaklumatmsyrtId implements
		java.io.Serializable {

	// Fields

	private Long idTblhtpmaklumatmsyrt;
	private Long idMesyuarat;
	private String ulasanPegawaiSeksyen;
	private String noRujukanUpe;
	private Date tarikhSuratUpe;
	private String ulasanUpe;
	private Double hargaBeli;
	private Double nilaiTnh;
	private Double hargaTambah;
	private String tempohSerah;
	private Date tarikhSerah;
	private String noRujukanSurat;
	private Date tarikhKelulusan;
	private String keputusan;
	private Date tarikhTerimaSurat;
	private Double hargaSetuju;
	private String unitBersamaan;
	private Double hargaBersamaan;
	private Double nilaiBgn;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;

	// Constructors

	/** default constructor */
	public AbstractTblhtpmaklumatmsyrtId() {
	}

	/** minimal constructor */
	public AbstractTblhtpmaklumatmsyrtId(Long idTblhtpmaklumatmsyrt,
			Long idMesyuarat) {
		this.idTblhtpmaklumatmsyrt = idTblhtpmaklumatmsyrt;
		this.idMesyuarat = idMesyuarat;
	}

	/** full constructor */
	public AbstractTblhtpmaklumatmsyrtId(Long idTblhtpmaklumatmsyrt,
			Long idMesyuarat, String ulasanPegawaiSeksyen, String noRujukanUpe,
			Date tarikhSuratUpe, String ulasanUpe, Double hargaBeli,
			Double nilaiTnh, Double hargaTambah, String tempohSerah,
			Date tarikhSerah, String noRujukanSurat, Date tarikhKelulusan,
			String keputusan, Date tarikhTerimaSurat, Double hargaSetuju,
			String unitBersamaan, Double hargaBersamaan, Double nilaiBgn,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		this.idTblhtpmaklumatmsyrt = idTblhtpmaklumatmsyrt;
		this.idMesyuarat = idMesyuarat;
		this.ulasanPegawaiSeksyen = ulasanPegawaiSeksyen;
		this.noRujukanUpe = noRujukanUpe;
		this.tarikhSuratUpe = tarikhSuratUpe;
		this.ulasanUpe = ulasanUpe;
		this.hargaBeli = hargaBeli;
		this.nilaiTnh = nilaiTnh;
		this.hargaTambah = hargaTambah;
		this.tempohSerah = tempohSerah;
		this.tarikhSerah = tarikhSerah;
		this.noRujukanSurat = noRujukanSurat;
		this.tarikhKelulusan = tarikhKelulusan;
		this.keputusan = keputusan;
		this.tarikhTerimaSurat = tarikhTerimaSurat;
		this.hargaSetuju = hargaSetuju;
		this.unitBersamaan = unitBersamaan;
		this.hargaBersamaan = hargaBersamaan;
		this.nilaiBgn = nilaiBgn;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
	}

	// Property accessors

	public Long getIdTblhtpmaklumatmsyrt() {
		return this.idTblhtpmaklumatmsyrt;
	}

	public void setIdTblhtpmaklumatmsyrt(Long idTblhtpmaklumatmsyrt) {
		this.idTblhtpmaklumatmsyrt = idTblhtpmaklumatmsyrt;
	}

	public Long getIdMesyuarat() {
		return this.idMesyuarat;
	}

	public void setIdMesyuarat(Long idMesyuarat) {
		this.idMesyuarat = idMesyuarat;
	}

	public String getUlasanPegawaiSeksyen() {
		return this.ulasanPegawaiSeksyen;
	}

	public void setUlasanPegawaiSeksyen(String ulasanPegawaiSeksyen) {
		this.ulasanPegawaiSeksyen = ulasanPegawaiSeksyen;
	}

	public String getNoRujukanUpe() {
		return this.noRujukanUpe;
	}

	public void setNoRujukanUpe(String noRujukanUpe) {
		this.noRujukanUpe = noRujukanUpe;
	}

	public Date getTarikhSuratUpe() {
		return this.tarikhSuratUpe;
	}

	public void setTarikhSuratUpe(Date tarikhSuratUpe) {
		this.tarikhSuratUpe = tarikhSuratUpe;
	}

	public String getUlasanUpe() {
		return this.ulasanUpe;
	}

	public void setUlasanUpe(String ulasanUpe) {
		this.ulasanUpe = ulasanUpe;
	}

	public Double getHargaBeli() {
		return this.hargaBeli;
	}

	public void setHargaBeli(Double hargaBeli) {
		this.hargaBeli = hargaBeli;
	}

	public Double getNilaiTnh() {
		return this.nilaiTnh;
	}

	public void setNilaiTnh(Double nilaiTnh) {
		this.nilaiTnh = nilaiTnh;
	}

	public Double getHargaTambah() {
		return this.hargaTambah;
	}

	public void setHargaTambah(Double hargaTambah) {
		this.hargaTambah = hargaTambah;
	}

	public String getTempohSerah() {
		return this.tempohSerah;
	}

	public void setTempohSerah(String tempohSerah) {
		this.tempohSerah = tempohSerah;
	}

	public Date getTarikhSerah() {
		return this.tarikhSerah;
	}

	public void setTarikhSerah(Date tarikhSerah) {
		this.tarikhSerah = tarikhSerah;
	}

	public String getNoRujukanSurat() {
		return this.noRujukanSurat;
	}

	public void setNoRujukanSurat(String noRujukanSurat) {
		this.noRujukanSurat = noRujukanSurat;
	}

	public Date getTarikhKelulusan() {
		return this.tarikhKelulusan;
	}

	public void setTarikhKelulusan(Date tarikhKelulusan) {
		this.tarikhKelulusan = tarikhKelulusan;
	}

	public String getKeputusan() {
		return this.keputusan;
	}

	public void setKeputusan(String keputusan) {
		this.keputusan = keputusan;
	}

	public Date getTarikhTerimaSurat() {
		return this.tarikhTerimaSurat;
	}

	public void setTarikhTerimaSurat(Date tarikhTerimaSurat) {
		this.tarikhTerimaSurat = tarikhTerimaSurat;
	}

	public Double getHargaSetuju() {
		return this.hargaSetuju;
	}

	public void setHargaSetuju(Double hargaSetuju) {
		this.hargaSetuju = hargaSetuju;
	}

	public String getUnitBersamaan() {
		return this.unitBersamaan;
	}

	public void setUnitBersamaan(String unitBersamaan) {
		this.unitBersamaan = unitBersamaan;
	}

	public Double getHargaBersamaan() {
		return this.hargaBersamaan;
	}

	public void setHargaBersamaan(Double hargaBersamaan) {
		this.hargaBersamaan = hargaBersamaan;
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
		if (!(other instanceof AbstractTblhtpmaklumatmsyrtId))
			return false;
		AbstractTblhtpmaklumatmsyrtId castOther = (AbstractTblhtpmaklumatmsyrtId) other;

		return ((this.getIdTblhtpmaklumatmsyrt() == castOther
				.getIdTblhtpmaklumatmsyrt()) || (this
				.getIdTblhtpmaklumatmsyrt() != null
				&& castOther.getIdTblhtpmaklumatmsyrt() != null && this
				.getIdTblhtpmaklumatmsyrt().equals(
						castOther.getIdTblhtpmaklumatmsyrt())))
				&& ((this.getIdMesyuarat() == castOther.getIdMesyuarat()) || (this
						.getIdMesyuarat() != null
						&& castOther.getIdMesyuarat() != null && this
						.getIdMesyuarat().equals(castOther.getIdMesyuarat())))
				&& ((this.getUlasanPegawaiSeksyen() == castOther
						.getUlasanPegawaiSeksyen()) || (this
						.getUlasanPegawaiSeksyen() != null
						&& castOther.getUlasanPegawaiSeksyen() != null && this
						.getUlasanPegawaiSeksyen().equals(
								castOther.getUlasanPegawaiSeksyen())))
				&& ((this.getNoRujukanUpe() == castOther.getNoRujukanUpe()) || (this
						.getNoRujukanUpe() != null
						&& castOther.getNoRujukanUpe() != null && this
						.getNoRujukanUpe().equals(castOther.getNoRujukanUpe())))
				&& ((this.getTarikhSuratUpe() == castOther.getTarikhSuratUpe()) || (this
						.getTarikhSuratUpe() != null
						&& castOther.getTarikhSuratUpe() != null && this
						.getTarikhSuratUpe().equals(
								castOther.getTarikhSuratUpe())))
				&& ((this.getUlasanUpe() == castOther.getUlasanUpe()) || (this
						.getUlasanUpe() != null
						&& castOther.getUlasanUpe() != null && this
						.getUlasanUpe().equals(castOther.getUlasanUpe())))
				&& ((this.getHargaBeli() == castOther.getHargaBeli()) || (this
						.getHargaBeli() != null
						&& castOther.getHargaBeli() != null && this
						.getHargaBeli().equals(castOther.getHargaBeli())))
				&& ((this.getNilaiTnh() == castOther.getNilaiTnh()) || (this
						.getNilaiTnh() != null
						&& castOther.getNilaiTnh() != null && this
						.getNilaiTnh().equals(castOther.getNilaiTnh())))
				&& ((this.getHargaTambah() == castOther.getHargaTambah()) || (this
						.getHargaTambah() != null
						&& castOther.getHargaTambah() != null && this
						.getHargaTambah().equals(castOther.getHargaTambah())))
				&& ((this.getTempohSerah() == castOther.getTempohSerah()) || (this
						.getTempohSerah() != null
						&& castOther.getTempohSerah() != null && this
						.getTempohSerah().equals(castOther.getTempohSerah())))
				&& ((this.getTarikhSerah() == castOther.getTarikhSerah()) || (this
						.getTarikhSerah() != null
						&& castOther.getTarikhSerah() != null && this
						.getTarikhSerah().equals(castOther.getTarikhSerah())))
				&& ((this.getNoRujukanSurat() == castOther.getNoRujukanSurat()) || (this
						.getNoRujukanSurat() != null
						&& castOther.getNoRujukanSurat() != null && this
						.getNoRujukanSurat().equals(
								castOther.getNoRujukanSurat())))
				&& ((this.getTarikhKelulusan() == castOther
						.getTarikhKelulusan()) || (this.getTarikhKelulusan() != null
						&& castOther.getTarikhKelulusan() != null && this
						.getTarikhKelulusan().equals(
								castOther.getTarikhKelulusan())))
				&& ((this.getKeputusan() == castOther.getKeputusan()) || (this
						.getKeputusan() != null
						&& castOther.getKeputusan() != null && this
						.getKeputusan().equals(castOther.getKeputusan())))
				&& ((this.getTarikhTerimaSurat() == castOther
						.getTarikhTerimaSurat()) || (this
						.getTarikhTerimaSurat() != null
						&& castOther.getTarikhTerimaSurat() != null && this
						.getTarikhTerimaSurat().equals(
								castOther.getTarikhTerimaSurat())))
				&& ((this.getHargaSetuju() == castOther.getHargaSetuju()) || (this
						.getHargaSetuju() != null
						&& castOther.getHargaSetuju() != null && this
						.getHargaSetuju().equals(castOther.getHargaSetuju())))
				&& ((this.getUnitBersamaan() == castOther.getUnitBersamaan()) || (this
						.getUnitBersamaan() != null
						&& castOther.getUnitBersamaan() != null && this
						.getUnitBersamaan()
						.equals(castOther.getUnitBersamaan())))
				&& ((this.getHargaBersamaan() == castOther.getHargaBersamaan()) || (this
						.getHargaBersamaan() != null
						&& castOther.getHargaBersamaan() != null && this
						.getHargaBersamaan().equals(
								castOther.getHargaBersamaan())))
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
				+ (getIdTblhtpmaklumatmsyrt() == null ? 0 : this
						.getIdTblhtpmaklumatmsyrt().hashCode());
		result = 37
				* result
				+ (getIdMesyuarat() == null ? 0 : this.getIdMesyuarat()
						.hashCode());
		result = 37
				* result
				+ (getUlasanPegawaiSeksyen() == null ? 0 : this
						.getUlasanPegawaiSeksyen().hashCode());
		result = 37
				* result
				+ (getNoRujukanUpe() == null ? 0 : this.getNoRujukanUpe()
						.hashCode());
		result = 37
				* result
				+ (getTarikhSuratUpe() == null ? 0 : this.getTarikhSuratUpe()
						.hashCode());
		result = 37 * result
				+ (getUlasanUpe() == null ? 0 : this.getUlasanUpe().hashCode());
		result = 37 * result
				+ (getHargaBeli() == null ? 0 : this.getHargaBeli().hashCode());
		result = 37 * result
				+ (getNilaiTnh() == null ? 0 : this.getNilaiTnh().hashCode());
		result = 37
				* result
				+ (getHargaTambah() == null ? 0 : this.getHargaTambah()
						.hashCode());
		result = 37
				* result
				+ (getTempohSerah() == null ? 0 : this.getTempohSerah()
						.hashCode());
		result = 37
				* result
				+ (getTarikhSerah() == null ? 0 : this.getTarikhSerah()
						.hashCode());
		result = 37
				* result
				+ (getNoRujukanSurat() == null ? 0 : this.getNoRujukanSurat()
						.hashCode());
		result = 37
				* result
				+ (getTarikhKelulusan() == null ? 0 : this.getTarikhKelulusan()
						.hashCode());
		result = 37 * result
				+ (getKeputusan() == null ? 0 : this.getKeputusan().hashCode());
		result = 37
				* result
				+ (getTarikhTerimaSurat() == null ? 0 : this
						.getTarikhTerimaSurat().hashCode());
		result = 37
				* result
				+ (getHargaSetuju() == null ? 0 : this.getHargaSetuju()
						.hashCode());
		result = 37
				* result
				+ (getUnitBersamaan() == null ? 0 : this.getUnitBersamaan()
						.hashCode());
		result = 37
				* result
				+ (getHargaBersamaan() == null ? 0 : this.getHargaBersamaan()
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