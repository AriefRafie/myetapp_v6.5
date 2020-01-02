package ekptg.model.entities;

import java.util.Date;

/**
 * TblhtpperjanjianId entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class TblhtpperjanjianId extends AbstractTblhtpperjanjianId implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public TblhtpperjanjianId() {
	}

	/** minimal constructor */
	public TblhtpperjanjianId(String noRujukanSeksyen) {
		super(noRujukanSeksyen);
	}

	/** full constructor */
	public TblhtpperjanjianId(String noRujukanSeksyen, Date tarikhKeputusan,
			String pegawai, String noKontrak, Date tarikhJanji,
			Double nilaiTanah, Double hargaBeli, String bilHmBeli,
			String bilPtkbeli, String bilUnitBeli, Date tarikhPtpbeli,
			Date tarikh14a, String tempohPajak, Date tarikhMulaPjk,
			Date tarikhTamatpjk, Double kadarBayarPajak,
			Double kadarPjkBerikut, String kategoriCukai, Double kadarCukai,
			Double kadarCukaiBerikut, Date tarikhTerima15a, Date tarikhHntr15a,
			Date tarikhTandatangan15a, Double hargaTambahan, Date tarikhSerah,
			Date tarikhSerahSebenar, String namak, String alamatk1,
			String alamatk2, String alamatk3, String poskod, String negerik,
			String tempohBulan, Date tarikhTandatangan12a, Date tarikhHntr12a,
			Date tarikhDaf12a, Date tarikhMoa, Double nilaianProjek,
			String caraLaksanafee, Double nilaiBgn, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		super(noRujukanSeksyen, tarikhKeputusan, pegawai, noKontrak,
				tarikhJanji, nilaiTanah, hargaBeli, bilHmBeli, bilPtkbeli,
				bilUnitBeli, tarikhPtpbeli, tarikh14a, tempohPajak,
				tarikhMulaPjk, tarikhTamatpjk, kadarBayarPajak,
				kadarPjkBerikut, kategoriCukai, kadarCukai, kadarCukaiBerikut,
				tarikhTerima15a, tarikhHntr15a, tarikhTandatangan15a,
				hargaTambahan, tarikhSerah, tarikhSerahSebenar, namak,
				alamatk1, alamatk2, alamatk3, poskod, negerik, tempohBulan,
				tarikhTandatangan12a, tarikhHntr12a, tarikhDaf12a, tarikhMoa,
				nilaianProjek, caraLaksanafee, nilaiBgn, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini);
	}

}
