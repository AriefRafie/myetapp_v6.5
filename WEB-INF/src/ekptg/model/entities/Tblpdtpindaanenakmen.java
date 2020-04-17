package ekptg.model.entities;

/**
 * Tblpdtpindaanenakmen entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpdtpindaanenakmen extends AbstractTblpdtpindaanenakmen
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtpindaanenakmen() {
	}

	/** minimal constructor */
	public Tblpdtpindaanenakmen(Long idPindaanenakmen,
			Tblpdtenakmenpinda tblpdtenakmenpinda, Tblpdtenakmen tblpdtenakmen) {
		super(idPindaanenakmen, tblpdtenakmenpinda, tblpdtenakmen);
	}

	/** full constructor */
	public Tblpdtpindaanenakmen(Long idPindaanenakmen,
			Tblpdtpindaanenakmenpenggal tblpdtpindaanenakmenpenggal,
			Tblpdtenakmenpinda tblpdtenakmenpinda, Tblpdtenakmen tblpdtenakmen,
			Tblpdtpindaanenakmensubpara tblpdtpindaanenakmensubpara,
			Tblpdtpindaanenakmenbab tblpdtpindaanenakmenbab,
			Tblpdtpindaanjadualpara tblpdtpindaanjadualpara,
			Tblpdtpindaanenakmenbahagian tblpdtpindaanenakmenbahagian,
			Tblpdtpindaanjadualsubpara tblpdtpindaanjadualsubpara,
			Tblpdtpindaanenakmenseksyen tblpdtpindaanenakmenseksyen,
			Tblpdtpindaanenakmenpara tblpdtpindaanenakmenpara) {
		super(idPindaanenakmen, tblpdtpindaanenakmenpenggal,
				tblpdtenakmenpinda, tblpdtenakmen, tblpdtpindaanenakmensubpara,
				tblpdtpindaanenakmenbab, tblpdtpindaanjadualpara,
				tblpdtpindaanenakmenbahagian, tblpdtpindaanjadualsubpara,
				tblpdtpindaanenakmenseksyen, tblpdtpindaanenakmenpara);
	}

}
