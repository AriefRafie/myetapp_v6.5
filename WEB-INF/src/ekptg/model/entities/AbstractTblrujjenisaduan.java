package ekptg.model.entities;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblrujjenisaduan entity provides the base persistence definition of
 * the Tblrujjenisaduan entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblrujjenisaduan implements java.io.Serializable {

	// Fields

	private Long idJenisaduan;
	private String kodJenisAduan;
	private String jenisAduan;
	private Set tblonlineaduans = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblrujjenisaduan() {
	}

	/** minimal constructor */
	public AbstractTblrujjenisaduan(Long idJenisaduan) {
		this.idJenisaduan = idJenisaduan;
	}

	/** full constructor */
	public AbstractTblrujjenisaduan(Long idJenisaduan,
			String kodJenisAduan, String jenisAduan, Set tblonlineaduans) {
		this.idJenisaduan = idJenisaduan;
		this.kodJenisAduan = kodJenisAduan;
		this.jenisAduan = jenisAduan;
		this.tblonlineaduans = tblonlineaduans;
	}

	// Property accessors

	public Long getIdJenisaduan() {
		return this.idJenisaduan;
	}

	public void setIdJenisaduan(Long idJenisaduan) {
		this.idJenisaduan = idJenisaduan;
	}

	public String getKodJenisAduan() {
		return this.kodJenisAduan;
	}

	public void setKodJenisAduan(String kodJenisAduan) {
		this.kodJenisAduan = kodJenisAduan;
	}

	public String getJenisAduan() {
		return this.jenisAduan;
	}

	public void setJenisAduan(String jenisAduan) {
		this.jenisAduan = jenisAduan;
	}

	public Set getTblonlineaduans() {
		return this.tblonlineaduans;
	}

	public void setTblonlineaduans(Set tblonlineaduans) {
		this.tblonlineaduans = tblonlineaduans;
	}

}