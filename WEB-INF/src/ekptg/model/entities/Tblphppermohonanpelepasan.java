package ekptg.model.entities;

import java.util.Date;

/**
 * Tblphppermohonanpelepasan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblphppermohonanpelepasan extends
		AbstractTblphppermohonanpelepasan implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblphppermohonanpelepasan() {
	}

	/** full constructor */
	public Tblphppermohonanpelepasan(Long idPermohonan, String keterangan,
			String noRujukanSurat, String lokasi, String namaProjek,
			String flagJenisProjek, String tajukPermohonan, Long idUnitluasmhn,
			Double luasMhn, Long idUnitluasbaki, Double luasBaki,
			String kemajuanTanah, String flagGuna, Long idUnitluasdiluluskan,
			Double luasDiluluskan, Long idUnitluasbakidiluluskan,
			Double luasBakiDiluluskan, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Long idDb) {
		super(idPermohonan, keterangan, noRujukanSurat, lokasi, namaProjek,
				flagJenisProjek, tajukPermohonan, idUnitluasmhn, luasMhn,
				idUnitluasbaki, luasBaki, kemajuanTanah, flagGuna,
				idUnitluasdiluluskan, luasDiluluskan, idUnitluasbakidiluluskan,
				luasBakiDiluluskan, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini, idDb);
	}

}
