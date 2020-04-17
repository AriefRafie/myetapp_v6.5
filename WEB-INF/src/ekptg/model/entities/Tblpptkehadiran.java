package ekptg.model.entities;

import java.util.Date;

/**
 * Tblpptkehadiran entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpptkehadiran extends AbstractTblpptkehadiran implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpptkehadiran() {
	}

	/** full constructor */
	public Tblpptkehadiran(Long idSiasatan, Long idPihakberkepentingan,
			String nama, String namaKp, String alamat1, String alamat2,
			String alamat3, String poskod, Long idNegeri, String noAkaun,
			Long idBank, String noKp, String flagHadir, Long idJenispb,
			Long idJenisnopb, String noKpLama, String perihalJenisLainpb,
			Long jenisLainpb, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Long idDb) {
		super(idSiasatan, idPihakberkepentingan, nama, namaKp, alamat1,
				alamat2, alamat3, poskod, idNegeri, noAkaun, idBank, noKp,
				flagHadir, idJenispb, idJenisnopb, noKpLama,
				perihalJenisLainpb, jenisLainpb, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini, idDb);
	}

}
