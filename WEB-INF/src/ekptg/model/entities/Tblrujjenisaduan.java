package ekptg.model.entities;


import java.util.Set;

/**
 * Tblrujjenisaduan entity. @author MyEclipse Persistence Tools
 */
public class Tblrujjenisaduan extends AbstractTblrujjenisaduan implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tblrujjenisaduan() {
	}

	/** minimal constructor */
	public Tblrujjenisaduan(Long idJenisaduan) {
		super(idJenisaduan);
	}

	/** full constructor */
	public Tblrujjenisaduan(Long idJenisaduan, String kodJenisAduan,
			String jenisAduan, Set tblonlineaduans) {
		super(idJenisaduan, kodJenisAduan, jenisAduan, tblonlineaduans);
	}

}
