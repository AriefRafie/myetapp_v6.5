package ekptg.model.entities;

/**
 * Tblunitorganisasi entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class Tblunitorganisasi extends AbstractTblunitorganisasi implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblunitorganisasi() {
	}

	/** full constructor */
	public Tblunitorganisasi(String kodUnitOrganisasi,
			String namaUnitOrganisasi, String alamat1, String alamat2,
			String alamat3, String poskod, String bandar, String kodMpb,
			String kodNegeri, String kodNegara, String noTelefon, String noFak,
			String email, String kodStatus) {
		super(kodUnitOrganisasi, namaUnitOrganisasi, alamat1, alamat2, alamat3,
				poskod, bandar, kodMpb, kodNegeri, kodNegara, noTelefon, noFak,
				email, kodStatus);
	}

}
