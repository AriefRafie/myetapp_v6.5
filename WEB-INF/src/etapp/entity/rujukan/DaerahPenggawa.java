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
@Table(name = "TBLRUJDAERAHPENGGAWA")
public class DaerahPenggawa{

	// Fields
	@Id @Column(name="ID_DAERAHPENGGAWA")
	private Long id;
	@ManyToOne @JoinColumn(name="ID_DAERAH")
	private Daerah daerah;

	@Column(name="KOD_DAERAHPENGGAWA")
	private String kod;
	@Column(name="NAMA_DAERAHPENGGAWA")
	private String nama;
	@Column(name="KOD_MAMPU")
	private String kodMampu;
	@Column(name="ABBREV")
	private String abbrev;
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

	public String getKod() {
		return this.kod;
	}

	public void setKod(String kodDaerah) {
		this.kod = kodDaerah;
	}

	public String getNama() {
		return this.nama;
	}

	public void setNama(String namaDaerah) {
		this.nama = namaDaerah;
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

	public Daerah getDaerah() {
		return daerah;
	}

	public void setDaerah(Daerah daerah) {
		this.daerah = daerah;
	}

	public String getKodMampu() {
		return kodMampu;
	}

	public void setKodMampu(String kodMampu) {
		this.kodMampu = kodMampu;
	}

	public String getAbbrev() {
		return abbrev;
	}

	public void setAbbrev(String abbrev) {
		this.abbrev = abbrev;
	}


	
}