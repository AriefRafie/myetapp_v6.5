package ekptg.model.entities;

import java.util.Date;

/**
 * Tblphppenceroboh entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblphppenceroboh extends AbstractTblphppenceroboh implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblphppenceroboh() {
	}

	/** minimal constructor */
	public Tblphppenceroboh(Long idPenceroboh) {
		super(idPenceroboh);
	}

	/** full constructor */
	public Tblphppenceroboh(Long idPenceroboh,
			Tblphppermohonanpenyewaan tblphppermohonanpenyewaan, String nama,
			Long idJenispengenalan, String noKp, String noKpLama,
			Long idWarnakp, String alamat1, String alamat2, String alamat3,
			String poskod, Long idBandar, Long idNegeri, String noTelRumah,
			String noTelPejabat, String extTel, String noFax,
			String noTelBimbit, String emel, Date tarikhLahir, Long umur,
			Long idKeturunan, Long idWarganegara, String pekerjaan,
			Double pendapatan, Long bilAhliKeluarga, Long bilRumah,
			Double luas, Long idUnitluas, String tempatDaftar,
			String statusSyarikat, Double modalDibenarkan, Double modalJelas,
			String undangUndangDiperbadankan, String jenisPendaftaran,
			Long idJantina, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idPenceroboh, tblphppermohonanpenyewaan, nama, idJenispengenalan,
				noKp, noKpLama, idWarnakp, alamat1, alamat2, alamat3, poskod,
				idBandar, idNegeri, noTelRumah, noTelPejabat, extTel, noFax,
				noTelBimbit, emel, tarikhLahir, umur, idKeturunan,
				idWarganegara, pekerjaan, pendapatan, bilAhliKeluarga,
				bilRumah, luas, idUnitluas, tempatDaftar, statusSyarikat,
				modalDibenarkan, modalJelas, undangUndangDiperbadankan,
				jenisPendaftaran, idJantina, idMasuk, tarikhMasuk, idKemaskini,
				tarikhKemaskini);
	}

}
