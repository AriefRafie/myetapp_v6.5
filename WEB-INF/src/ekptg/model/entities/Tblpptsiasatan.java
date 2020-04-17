package ekptg.model.entities;

import java.util.Date;

/**
 * Tblpptsiasatan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpptsiasatan extends AbstractTblpptsiasatan implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpptsiasatan() {
	}

	/** full constructor */
	public Tblpptsiasatan(String noKes, String noKesSebelum,
			String noKesBerikut, String noSiasatan, Long statusSiasatan,
			Date tarikhSiasatan, String masaSiasatan, String tempat,
			String alamat1, String alamat2, String alamat3, String poskod,
			Long idNegeri, String alasanTangguh, Double nilaianJpph,
			Long idUnitluas, String bantahanTuantnh, String bantahanAgensi,
			String bantahanLain, Long tempohMilikTanah, String caraMilik,
			Double hargaBeli, String jenisBangunan, String jenisTanaman,
			String flagPecahSempadan, String flagTukarSyarat,
			Date tarikhPecahSempadan, Date tarikhTukarSyarat,
			String statusSemasa, String bebanan, String keteranganTuanTanah,
			String keteranganAgensi, String keteranganJurunilai,
			String tuntutanTuantnh, String tuntutanPbBebanan,
			String tuntutanPbTdkdaftar, String tuntutanPbLain, Long idBorange,
			String perintah, Date tarikhAkhirTampal, String flagBantahan,
			Long idPegawaiSiasatan, Long idPenarikanbalik, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini, Long idDb) {
		super(noKes, noKesSebelum, noKesBerikut, noSiasatan, statusSiasatan,
				tarikhSiasatan, masaSiasatan, tempat, alamat1, alamat2,
				alamat3, poskod, idNegeri, alasanTangguh, nilaianJpph,
				idUnitluas, bantahanTuantnh, bantahanAgensi, bantahanLain,
				tempohMilikTanah, caraMilik, hargaBeli, jenisBangunan,
				jenisTanaman, flagPecahSempadan, flagTukarSyarat,
				tarikhPecahSempadan, tarikhTukarSyarat, statusSemasa, bebanan,
				keteranganTuanTanah, keteranganAgensi, keteranganJurunilai,
				tuntutanTuantnh, tuntutanPbBebanan, tuntutanPbTdkdaftar,
				tuntutanPbLain, idBorange, perintah, tarikhAkhirTampal,
				flagBantahan, idPegawaiSiasatan, idPenarikanbalik, idMasuk,
				tarikhMasuk, idKemaskini, tarikhKemaskini, idDb);
	}

}
