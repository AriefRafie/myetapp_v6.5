package ekptg.model.entities;

import java.util.Date;

/**
 * Tblhtpperjanjianswasta entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblhtpperjanjianswasta extends AbstractTblhtpperjanjianswasta
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblhtpperjanjianswasta() {
	}

	/** minimal constructor */
	public Tblhtpperjanjianswasta(Long idPerjanjianswasta,
			Tblhtppermohonan tblhtppermohonan, Long idPerjanjian) {
		super(idPerjanjianswasta, tblhtppermohonan, idPerjanjian);
	}

	/** full constructor */
	public Tblhtpperjanjianswasta(Long idPerjanjianswasta,
			Tblhtppermohonan tblhtppermohonan, Long idPerjanjian,
			Date tarikhKeputusan, Double nilaiTanah, Double nilaianProjek,
			String caraLaksanafee, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini) {
		super(idPerjanjianswasta, tblhtppermohonan, idPerjanjian,
				tarikhKeputusan, nilaiTanah, nilaianProjek, caraLaksanafee,
				idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
