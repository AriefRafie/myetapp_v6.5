package ekptg.model.entities;

import java.util.Date;

/**
 * Tblrujdokumen entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblrujdokumen extends AbstractDokumen implements java.io.Serializable {

	// Constructors
	/** default constructor */
	public Tblrujdokumen() {
	}

	/** full constructor */
	public Tblrujdokumen(String idDokumen, String namaDokumen
			,String catatan, String dokumen,String idJenis,String kandungan
			,Long idMasuk,Date tarikhMasuk,Long idKemaskini,Date tarikhKemaskini) {
		super(idDokumen, namaDokumen
			, catatan, dokumen, idJenis,kandungan
			, idMasuk,tarikhMasuk,idKemaskini,tarikhKemaskini);
	}
	

}
