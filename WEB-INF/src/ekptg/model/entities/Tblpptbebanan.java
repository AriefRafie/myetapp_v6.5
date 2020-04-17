package ekptg.model.entities;

import java.util.Date;

/**
 * Tblpptbebanan entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblpptbebanan extends AbstractTblpptbebanan implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpptbebanan() {
	}

	/** full constructor */
	public Tblpptbebanan(String jilid, String folio, Long idKodbebanan,
			Long idJenisnopb, String noPb, String noKpLama, String nama,
			String alamat1, String alamat2, String alamat3, String poskod,
			Long idNegeri, Long idHakmilik, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Long idDb) {
		super(jilid, folio, idKodbebanan, idJenisnopb, noPb, noKpLama, nama,
				alamat1, alamat2, alamat3, poskod, idNegeri, idHakmilik,
				idMasuk, tarikhMasuk, idKemaskini, tarikhKemaskini, idDb);
	}

}
