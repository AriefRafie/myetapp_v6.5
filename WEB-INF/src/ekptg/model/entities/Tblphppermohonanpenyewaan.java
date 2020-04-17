package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblphppermohonanpenyewaan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblphppermohonanpenyewaan extends
		AbstractTblphppermohonanpenyewaan implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblphppermohonanpenyewaan() {
	}

	/** minimal constructor */
	public Tblphppermohonanpenyewaan(Long idPermohonanpenyewaan) {
		super(idPermohonanpenyewaan);
	}

	/** full constructor */
	public Tblphppermohonanpenyewaan(Long idPermohonanpenyewaan, Long idFail,
			String tujuan, Long idJenissewa, String keteranganAduan,
			Date tarikhSurat, String lokasi, String tajukPermohonan,
			Long idUnitluasmhn, Double luasMhn, Long idUnitluasbaki,
			Double luasBaki, String flagGuna, Date tarikhMulaMhn,
			Date tarikhTamatMhn, Long idStatus, String noPermohonanOnline,
			String flagLbhCeroboh, Long idJenisceroboh, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Long idUrusan, Set tblphpulasanteknikalpenyewaans,
			Set tblphppencerobohs, Set tblphppemohonpenyewaans,
			Set tblphpmohontanahpnywns, Set tblphpperjanjianpenyewaans,
			Set tblphpkertaskerjapenyewaans, Set tblphplawatantapakpenyewaans) {
		super(idPermohonanpenyewaan, idFail, tujuan, idJenissewa,
				keteranganAduan, tarikhSurat, lokasi, tajukPermohonan,
				idUnitluasmhn, luasMhn, idUnitluasbaki, luasBaki, flagGuna,
				tarikhMulaMhn, tarikhTamatMhn, idStatus, noPermohonanOnline,
				flagLbhCeroboh, idJenisceroboh, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini, idUrusan,
				tblphpulasanteknikalpenyewaans, tblphppencerobohs,
				tblphppemohonpenyewaans, tblphpmohontanahpnywns,
				tblphpperjanjianpenyewaans, tblphpkertaskerjapenyewaans,
				tblphplawatantapakpenyewaans);
	}

}
