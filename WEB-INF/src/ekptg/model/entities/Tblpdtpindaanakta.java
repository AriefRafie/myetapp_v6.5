package ekptg.model.entities;

/**
 * Tblpdtpindaanakta entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpdtpindaanakta extends AbstractTblpdtpindaanakta implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtpindaanakta() {
	}

	/** minimal constructor */
	public Tblpdtpindaanakta(Long idPindaanakta, Tblpdtakta tblpdtakta,
			Tblpdtaktapinda tblpdtaktapinda) {
		super(idPindaanakta, tblpdtakta, tblpdtaktapinda);
	}

	/** full constructor */
	public Tblpdtpindaanakta(Long idPindaanakta,
			Tblpdtpindaanaktabab tblpdtpindaanaktabab,
			Tblpdtpindaanaktasubpara tblpdtpindaanaktasubpara,
			Tblpdtpindaanaktabahagian tblpdtpindaanaktabahagian,
			Tblpdtpindaanjadualpara tblpdtpindaanjadualpara,
			Tblpdtpindaanaktapara tblpdtpindaanaktapara,
			Tblpdtpindaanaktaseksyen tblpdtpindaanaktaseksyen,
			Tblpdtakta tblpdtakta,
			Tblpdtpindaanjadualsubpara tblpdtpindaanjadualsubpara,
			Tblpdtpindaanaktapenggal tblpdtpindaanaktapenggal,
			Tblpdtaktapinda tblpdtaktapinda) {
		super(idPindaanakta, tblpdtpindaanaktabab, tblpdtpindaanaktasubpara,
				tblpdtpindaanaktabahagian, tblpdtpindaanjadualpara,
				tblpdtpindaanaktapara, tblpdtpindaanaktaseksyen, tblpdtakta,
				tblpdtpindaanjadualsubpara, tblpdtpindaanaktapenggal,
				tblpdtaktapinda);
	}

}
