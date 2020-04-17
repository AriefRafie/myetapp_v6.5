package ekptg.model.entities;

import java.util.Date;

/**
 * TblhtpmaklumatmsyrtId entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class TblhtpmaklumatmsyrtId extends AbstractTblhtpmaklumatmsyrtId
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public TblhtpmaklumatmsyrtId() {
	}

	/** minimal constructor */
	public TblhtpmaklumatmsyrtId(Long idTblhtpmaklumatmsyrt, Long idMesyuarat) {
		super(idTblhtpmaklumatmsyrt, idMesyuarat);
	}

	/** full constructor */
	public TblhtpmaklumatmsyrtId(Long idTblhtpmaklumatmsyrt, Long idMesyuarat,
			String ulasanPegawaiSeksyen, String noRujukanUpe,
			Date tarikhSuratUpe, String ulasanUpe, Double hargaBeli,
			Double nilaiTnh, Double hargaTambah, String tempohSerah,
			Date tarikhSerah, String noRujukanSurat, Date tarikhKelulusan,
			String keputusan, Date tarikhTerimaSurat, Double hargaSetuju,
			String unitBersamaan, Double hargaBersamaan, Double nilaiBgn,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idTblhtpmaklumatmsyrt, idMesyuarat, ulasanPegawaiSeksyen,
				noRujukanUpe, tarikhSuratUpe, ulasanUpe, hargaBeli, nilaiTnh,
				hargaTambah, tempohSerah, tarikhSerah, noRujukanSurat,
				tarikhKelulusan, keputusan, tarikhTerimaSurat, hargaSetuju,
				unitBersamaan, hargaBersamaan, nilaiBgn, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini);
	}

}
