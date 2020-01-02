package ekptg.model.entities;

import java.util.Set;

/**
 * Tblpdtpindaanjadualpara entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpdtpindaanjadualpara extends AbstractTblpdtpindaanjadualpara
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtpindaanjadualpara() {
	}

	/** minimal constructor */
	public Tblpdtpindaanjadualpara(Long idPindaanjadualpara) {
		super(idPindaanjadualpara);
	}

	/** full constructor */
	public Tblpdtpindaanjadualpara(Long idPindaanjadualpara,
			Tblpdtjadualperenggan tblpdtjadualperenggan,
			Tblpdtjadualpindaperenggan tblpdtjadualpindaperenggan,
			Set tblpdtpindaanaktas, Set tblpdtpindaanenakmens) {
		super(idPindaanjadualpara, tblpdtjadualperenggan,
				tblpdtjadualpindaperenggan, tblpdtpindaanaktas,
				tblpdtpindaanenakmens);
	}

}
