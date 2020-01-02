package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblppkhta entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblppkhta extends AbstractTblppkhta implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblppkhta() {
	}

	/** minimal constructor */
	public Tblppkhta(Long idHta) {
		super(idHta);
	}

	/** full constructor */
	public Tblppkhta(Long idHta,Long idSimati, String noHakmilik,
			String noPt, Double nilaiHtaTarikhmohon, Double nilaiHtaTarikhmati,
			Long idKategori, Long idJenishm, Long idJenispb, Long idNegeri,
			Long idDaerah, Long idMukim, Long idLuas, String luas,
			String luasHmp, String noCagaran, String noPajakan,
			String jenisTnh, String alamatHta1, String alamatHta2,
			String alamatHta3, String bandarHta, String poskodHta,
			Date tarikhPerjanjian, String namaPemaju, String alamatPemaju1,
			String alamatPemaju2, String alamatPemaju3, String bandarPemaju,
			String poskodPemaju, Long idNegeripemaju, String catatan,
			Long baSimati, Long bbSimati, String noBangunan, String noTingkat,
			String noPetak, String noStrata, String maklumatTanah,
			String noPerjanjian, String jenisHta, String statusPemilikan,
			String tanggungan, String noPerserahan, String namaRancangan,
			String noRoh, String noLotId, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, String flagKategoriHta,Set tblppkperintahhtaobmsts) {
		super(idHta, idSimati, noHakmilik, noPt, nilaiHtaTarikhmohon,
				nilaiHtaTarikhmati, idKategori, idJenishm, idJenispb, idNegeri,
				idDaerah, idMukim, idLuas, luas, luasHmp, noCagaran, noPajakan,
				jenisTnh, alamatHta1, alamatHta2, alamatHta3, bandarHta,
				poskodHta, tarikhPerjanjian, namaPemaju, alamatPemaju1,
				alamatPemaju2, alamatPemaju3, bandarPemaju, poskodPemaju,
				idNegeripemaju, catatan, baSimati, bbSimati, noBangunan,
				noTingkat, noPetak, noStrata, maklumatTanah, noPerjanjian,
				jenisHta, statusPemilikan, tanggungan, noPerserahan,
				namaRancangan, noRoh, noLotId, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini, flagKategoriHta, tblppkperintahhtaobmsts);
	}

}
