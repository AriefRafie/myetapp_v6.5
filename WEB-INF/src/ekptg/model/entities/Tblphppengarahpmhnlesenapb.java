package ekptg.model.entities;

import java.util.Date;

/**
 * Tblphppengarahpmhnlesenapb entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblphppengarahpmhnlesenapb extends
		AbstractTblphppengarahpmhnlesenapb implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblphppengarahpmhnlesenapb() {
	}

	/** minimal constructor */
	public Tblphppengarahpmhnlesenapb(Long idTblpengarahpmhnlesenapb) {
		super(idTblpengarahpmhnlesenapb);
	}

	/** full constructor */
	public Tblphppengarahpmhnlesenapb(Long idTblpengarahpmhnlesenapb,
			Tblphppemohonlesenapb tblphppemohonlesenapb, String nama,
			Long idJenispengenalan, String noKp, Long idWarganegara,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idTblpengarahpmhnlesenapb, tblphppemohonlesenapb, nama,
				idJenispengenalan, noKp, idWarganegara, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini);
	}

}
