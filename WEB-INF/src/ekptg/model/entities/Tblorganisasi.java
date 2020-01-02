package ekptg.model.entities;

/**
 * Tblorganisasi entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblorganisasi extends AbstractTblorganisasi implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblorganisasi() {
	}

	/** full constructor */
	public Tblorganisasi(String kodOrganisasi, String namaOrganisasi,
			String alamat1, String alamat2, String alamat3, String poskod,
			String bandar, String kodMpb, String kodNegeri, String kodNegara,
			String kodStatus, String noTelefon, String noFak, String alamatWeb,
			String email) {
		super(kodOrganisasi, namaOrganisasi, alamat1, alamat2, alamat3, poskod,
				bandar, kodMpb, kodNegeri, kodNegara, kodStatus, noTelefon,
				noFak, alamatWeb, email);
	}

}
