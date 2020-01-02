package ekptg.model.entities;

/**
 * AbstractTblpengguna entity provides the base persistence definition of the
 * Tblpengguna entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpengguna implements java.io.Serializable {

	// Fields

	private Long idPengguna;
	private String kodPengguna;
	private String namaPengguna;
	private String noKp;
	private String katalaluan;
	private String katalaluanBandingan;
	private String kodStatusPengguna;
	private String kodJenisPengguna;

	// Constructors

	/** default constructor */
	public AbstractTblpengguna() {
	}

	/** minimal constructor */
	public AbstractTblpengguna(String kodStatusPengguna) {
		this.kodStatusPengguna = kodStatusPengguna;
	}

	/** full constructor */
	public AbstractTblpengguna(String kodPengguna, String namaPengguna,
			String noKp, String katalaluan, String katalaluanBandingan,
			String kodStatusPengguna, String kodJenisPengguna) {
		this.kodPengguna = kodPengguna;
		this.namaPengguna = namaPengguna;
		this.noKp = noKp;
		this.katalaluan = katalaluan;
		this.katalaluanBandingan = katalaluanBandingan;
		this.kodStatusPengguna = kodStatusPengguna;
		this.kodJenisPengguna = kodJenisPengguna;
	}

	// Property accessors

	public Long getIdPengguna() {
		return this.idPengguna;
	}

	public void setIdPengguna(Long idPengguna) {
		this.idPengguna = idPengguna;
	}

	public String getKodPengguna() {
		return this.kodPengguna;
	}

	public void setKodPengguna(String kodPengguna) {
		this.kodPengguna = kodPengguna;
	}

	public String getNamaPengguna() {
		return this.namaPengguna;
	}

	public void setNamaPengguna(String namaPengguna) {
		this.namaPengguna = namaPengguna;
	}

	public String getNoKp() {
		return this.noKp;
	}

	public void setNoKp(String noKp) {
		this.noKp = noKp;
	}

	public String getKatalaluan() {
		return this.katalaluan;
	}

	public void setKatalaluan(String katalaluan) {
		this.katalaluan = katalaluan;
	}

	public String getKatalaluanBandingan() {
		return this.katalaluanBandingan;
	}

	public void setKatalaluanBandingan(String katalaluanBandingan) {
		this.katalaluanBandingan = katalaluanBandingan;
	}

	public String getKodStatusPengguna() {
		return this.kodStatusPengguna;
	}

	public void setKodStatusPengguna(String kodStatusPengguna) {
		this.kodStatusPengguna = kodStatusPengguna;
	}

	public String getKodJenisPengguna() {
		return this.kodJenisPengguna;
	}

	public void setKodJenisPengguna(String kodJenisPengguna) {
		this.kodJenisPengguna = kodJenisPengguna;
	}

}