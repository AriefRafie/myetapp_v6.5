package ekptg.model.entities;



import java.sql.Blob;
import java.util.Date;

/**
 * Tblpfdrujlampiran entity. @author MyEclipse Persistence Tools
 */
public class Tblpfdrujlampiran extends AbstractTblpfdrujlampiran implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblpfdrujlampiran() {
	}

	/** full constructor */
	public Tblpfdrujlampiran(Long idDokumen, Blob content,
			String namaFail, String jenisMime, Long idMasuk,
			Date tarikhMasuk, Long idKemaskini, Date tarikhKemaskini) {
		super(idDokumen, content, namaFail, jenisMime, idMasuk, tarikhMasuk,
				idKemaskini, tarikhKemaskini);
	}

}
