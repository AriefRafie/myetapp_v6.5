package ekptg.model.entities;

import java.util.Date;

/**
 * Tblpdtpekeliling entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpdtpekeliling extends AbstractTblpdtpekeliling implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpdtpekeliling() {
	}

	/** minimal constructor */
	public Tblpdtpekeliling(Long idPekeliling) {
		super(idPekeliling);
	}

 	/** full constructor */
	public Tblpdtpekeliling(Long idPekeliling,
			String kategoriPekeliling, String bilPekeliling,
                        String tajukPekeliling, Date tarikhPekeliling,			
			Date tarikhKuatkuasa, Date tarikhDaftar, Long idFail,
			String seksyenUrusetia, String status, String catatan,
			Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Long idDokumenPekeliling) {
		super(idPekeliling, kategoriPekeliling, bilPekeliling, 
                        tajukPekeliling, tarikhPekeliling, tarikhKuatkuasa, 
                        tarikhDaftar, idFail, seksyenUrusetia, status,
                        catatan, idMasuk, tarikhMasuk, idKemaskini, 
                        tarikhKemaskini, idDokumenPekeliling);
	}

}
