package etapp.entity.rujukan;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TBLRUJNEGERI")
public class Negeri{

	// Fields
	@Id
	@Column(name="ID_NEGERI")
	private Long id;
	@ManyToOne
	@JoinColumn(name="ID_NEGARA")
	private Negara negara;

	@Column(name="KOD_NEGERI")
	private String kodNegeri;
	@Column(name="NAMA_NEGERI")
	private String namaNegeri;
	@Column(name="KOD_MAMPU")
	private String kodMampu;
	@Column(name="ABBREV")
	private String abbrev;
	@Column(name="NAMA_NEGERI_PENUH")
	private String namaNegeriPenuh;
	@Column(name="ID_MASUK")
	private Long idMasuk;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_MASUK")
	private Date tarikhMasuk;
	@Column(name="ID_KEMASKINI")
	private Long idKemaskini;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_KEMASKINI")
	private Date tarikhKemaskini;

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKodNegeri() {
		return this.kodNegeri;
	}

	public void setKodNegeri(String kodNegeri) {
		this.kodNegeri = kodNegeri;
	}

	public String getNamaNegeri() {
		return this.namaNegeri;
	}

	public void setNamaNegeri(String namaNegeri) {
		this.namaNegeri = namaNegeri;
	}

	public String getKodMampu() {
		return this.kodMampu;
	}

	public void setKodMampu(String kodMampu) {
		this.kodMampu = kodMampu;
	}

	public String getAbbrev() {
		return this.abbrev;
	}

	public void setAbbrev(String abbrev) {
		this.abbrev = abbrev;
	}

	public Long getIdMasuk() {
		return this.idMasuk;
	}

	public void setIdMasuk(Long idMasuk) {
		this.idMasuk = idMasuk;
	}

	public Date getTarikhMasuk() {
		return this.tarikhMasuk;
	}

	public void setTarikhMasuk(Date tarikhMasuk) {
		this.tarikhMasuk = tarikhMasuk;
	}

	public Long getIdKemaskini() {
		return this.idKemaskini;
	}

	public void setIdKemaskini(Long idKemaskini) {
		this.idKemaskini = idKemaskini;
	}

	public Date getTarikhKemaskini() {
		return this.tarikhKemaskini;
	}

	public void setTarikhKemaskini(Date tarikhKemaskini) {
		this.tarikhKemaskini = tarikhKemaskini;
	}

	public Negara getNegara() {
		return negara;
	}

	public void setNegara(Negara negara) {
		this.negara = negara;
	}

	public String getNamaNegeriPenuh() {
		return namaNegeriPenuh;
	}

	public void setNamaNegeriPenuh(String namaNegeriPenuh) {
		this.namaNegeriPenuh = namaNegeriPenuh;
	}
	

}