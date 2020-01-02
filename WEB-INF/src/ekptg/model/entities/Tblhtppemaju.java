package ekptg.model.entities;

import java.util.Date;
import java.util.Set;

/**
 * Tblhtppemaju entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblhtppemaju extends AbstractTblhtppemaju implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblhtppemaju() {
	}

	/** minimal constructor */
	public Tblhtppemaju(Long idPemaju, Tblhtppermohonan tblhtppermohonan,
			Long idRujjenisopb, Long idDaerah, Long idNegeri) {
		super(idPemaju, tblhtppermohonan, idRujjenisopb, idDaerah, idNegeri);
	}

	/** full constructor */
	public Tblhtppemaju(Long idPemaju, Tblhtppermohonan tblhtppermohonan,
			Long idRujjenisopb, String noOpb, String noRujukanPemaju,
			String namaPemaju, String alamatPemaju1, String alamatPemaju2,
			String alamatPemaju3, String poskodPemaju, Long idDaerah,
			Long idNegeri, String noTel, String noFax, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini,
			Set tblhtppengarahs) {
		super(idPemaju, tblhtppermohonan, idRujjenisopb, noOpb,
				noRujukanPemaju, namaPemaju, alamatPemaju1, alamatPemaju2,
				alamatPemaju3, poskodPemaju, idDaerah, idNegeri, noTel, noFax,
				idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini,
				tblhtppengarahs);
	}

}
