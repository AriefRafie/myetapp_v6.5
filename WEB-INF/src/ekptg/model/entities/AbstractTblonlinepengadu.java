package ekptg.model.entities;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblonlinepengadu entity provides the base persistence definition of
 * the Tblonlinepengadu entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblonlinepengadu implements java.io.Serializable {

	// Fields

	private Long idPengadu;
	private Long idPengguna;
	private String namaPengadu;
	private String noTelRumah;
	private String noTelBimbit;
	private String emel;
	private Long idDb;
	private Set tblonlinepengaduans = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblonlinepengadu() {
	}

	/** minimal constructor */
	public AbstractTblonlinepengadu(Long idPengadu) {
		this.idPengadu = idPengadu;
	}

	/** full constructor */
	public AbstractTblonlinepengadu(Long idPengadu,
			Long idPengguna, String namaPengadu, String noTelRumah,
			String noTelBimbit, String emel, Long idDb,
			Set tblonlinepengaduans) {
		this.idPengadu = idPengadu;
		this.idPengguna = idPengguna;
		this.namaPengadu = namaPengadu;
		this.noTelRumah = noTelRumah;
		this.noTelBimbit = noTelBimbit;
		this.emel = emel;
		this.idDb = idDb;
		this.tblonlinepengaduans = tblonlinepengaduans;
	}

	// Property accessors

	public Long getIdPengadu() {
		return this.idPengadu;
	}

	public void setIdPengadu(Long idPengadu) {
		this.idPengadu = idPengadu;
	}

	public Long getIdPengguna() {
		return this.idPengguna;
	}

	public void setIdPengguna(Long idPengguna) {
		this.idPengguna = idPengguna;
	}

	public String getNamaPengadu() {
		return this.namaPengadu;
	}

	public void setNamaPengadu(String namaPengadu) {
		this.namaPengadu = namaPengadu;
	}

	public String getNoTelRumah() {
		return this.noTelRumah;
	}

	public void setNoTelRumah(String noTelRumah) {
		this.noTelRumah = noTelRumah;
	}

	public String getNoTelBimbit() {
		return this.noTelBimbit;
	}

	public void setNoTelBimbit(String noTelBimbit) {
		this.noTelBimbit = noTelBimbit;
	}

	public String getEmel() {
		return this.emel;
	}

	public void setEmel(String emel) {
		this.emel = emel;
	}

	public Long getIdDb() {
		return this.idDb;
	}

	public void setIdDb(Long idDb) {
		this.idDb = idDb;
	}

	public Set getTblonlinepengaduans() {
		return this.tblonlinepengaduans;
	}

	public void setTblonlinepengaduans(Set tblonlinepengaduans) {
		this.tblonlinepengaduans = tblonlinepengaduans;
	}

}