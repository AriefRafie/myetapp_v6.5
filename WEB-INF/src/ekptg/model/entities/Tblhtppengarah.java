package ekptg.model.entities;

import java.util.Date;

/**
 * Tblhtppengarah entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblhtppengarah extends AbstractTblhtppengarah implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblhtppengarah() {
	}

	/** minimal constructor */
	public Tblhtppengarah(Long idPengarah, Tblhtppemaju tblhtppemaju,
			Long idRujjenisopb, String idWarganegara, Long idUrusan,
			Long idMasuk) {
		super(idPengarah, tblhtppemaju, idRujjenisopb, idWarganegara, idUrusan,
				idMasuk);
	}

	/** full constructor */
	public Tblhtppengarah(Long idPengarah, Tblhtppemaju tblhtppemaju,
			Long idRujjenisopb, String noOpb, String nama,
			String idWarganegara, Long idUrusan, Long idMasuk, Date tarikhMasuk) {
		super(idPengarah, tblhtppemaju, idRujjenisopb, noOpb, nama,
				idWarganegara, idUrusan, idMasuk, tarikhMasuk);
	}

}
