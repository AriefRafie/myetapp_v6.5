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
@Table(name = "TBLRUJMUKIM")
public class Mukim{

	// Fields
	@Id @Column(name="ID_MUKIM")
	private Long id;
	@ManyToOne @JoinColumn(name="ID_DAERAHPENGGAWA")
	private DaerahPenggawa daerahPenggawa;
	@ManyToOne @JoinColumn(name="ID_DAERAH")
	private Daerah daerah;
	
	@Column(name="KOD_MUKIM")
	private String kod;
	@Column(name="NAMA_MUKIM")
	private String nama;
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

	public DaerahPenggawa getDaerahPenggawa() {
		return daerahPenggawa;
	}

	public void setDaerahPenggawa(DaerahPenggawa daerahPenggawa) {
		this.daerahPenggawa = daerahPenggawa;
	}

	public Daerah getDaerah() {
		return daerah;
	}

	public void setDaerah(Daerah daerah) {
		this.daerah = daerah;
	}

	public String getKod() {
		return kod;
	}

	public void setKod(String kod) {
		this.kod = kod;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public Long getIdMasuk() {
		return idMasuk;
	}

	public void setIdMasuk(Long idMasuk) {
		this.idMasuk = idMasuk;
	}

	public Date getTarikhMasuk() {
		return tarikhMasuk;
	}

	public void setTarikhMasuk(Date tarikhMasuk) {
		this.tarikhMasuk = tarikhMasuk;
	}

	public Long getIdKemaskini() {
		return idKemaskini;
	}

	public void setIdKemaskini(Long idKemaskini) {
		this.idKemaskini = idKemaskini;
	}

	public Date getTarikhKemaskini() {
		return tarikhKemaskini;
	}

	public void setTarikhKemaskini(Date tarikhKemaskini) {
		this.tarikhKemaskini = tarikhKemaskini;
	}


}