package ekptg.model.entities;

import java.util.Date;

/**
 * Tblphpmaklumatpakarapb entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblphpmaklumatpakarapb extends AbstractTblphpmaklumatpakarapb
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblphpmaklumatpakarapb() {
	}

	/** minimal constructor */
	public Tblphpmaklumatpakarapb(Long idMaklumatpakarapb) {
		super(idMaklumatpakarapb);
	}

	/** full constructor */
	public Tblphpmaklumatpakarapb(Long idMaklumatpakarapb,
			Tblphppemohonlesenapb tblphppemohonlesenapb, String nama,
			String kelayakan, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini) {
		super(idMaklumatpakarapb, tblphppemohonlesenapb, nama, kelayakan,
				idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini);
	}

}
