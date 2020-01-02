package ekptg.model.entities;

import java.util.Date;

/**
 * Tblhtpnotis5aId entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblhtpnotis5aId extends AbstractTblhtpnotis5aId implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblhtpnotis5aId() {
	}

	/** minimal constructor */
	public Tblhtpnotis5aId(Long idNotis5a, Tblhtppermohonan tblhtppermohonan) {
		super(idNotis5a, tblhtppermohonan);
	}

	/** full constructor */
	public Tblhtpnotis5aId(Long idNotis5a, Tblhtppermohonan tblhtppermohonan,
			Date tarikhNotis5a, Date tarikhTerima, Double kadarCukai,
			Double bayarNotis, Double bayarDaftarHakmilik, Double bayarUkur,
			Double bayarPremium, Double bayarPerenggan, Double bayarSempadan,
			Double bayaranLain, Double jumlahBayaran, String noRujukanMop,
			Date tarikhLuput1, Date tarikhLuput2, Double rayuanPremium,
			Date tarikhTerimaGeran, Date tarikhHantarGeran,
			Double bayarSediahakmilik, Long bayarWarta, Long rayuanLain,
			String perihalRayuan, Double cukaiTertunggak,
			Double bayarTertunggak, Double bayarMohonLa, String drafTitle,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idNotis5a, tblhtppermohonan, tarikhNotis5a, tarikhTerima,
				kadarCukai, bayarNotis, bayarDaftarHakmilik, bayarUkur,
				bayarPremium, bayarPerenggan, bayarSempadan, bayaranLain,
				jumlahBayaran, noRujukanMop, tarikhLuput1, tarikhLuput2,
				rayuanPremium, tarikhTerimaGeran, tarikhHantarGeran,
				bayarSediahakmilik, bayarWarta, rayuanLain, perihalRayuan,
				cukaiTertunggak, bayarTertunggak, bayarMohonLa, drafTitle,
				idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
