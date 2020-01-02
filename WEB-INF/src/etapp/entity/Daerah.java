package etapp.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import etapp.entity.htp.HakmilikUrusan;

@Entity
@Table(name = "TBLRUJDAERAH")
public class Daerah{

	// Fields
	@Id @Column(name="ID_DAERAH")
	private Long id;
	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="daerah")
	private List<HakmilikUrusan> listHakmilikUrusan;

	@Column(name="KOD_DAERAH")
	private String kodDaerah;
	@Column(name="NAMA_DAERAH")
	private String namaDaerah;
	@Column(name="ID_NEGERI")
	private Long idNegeri;
	@Column(name="ID_MASUK")
	private Long idMasuk;
	@Column(name="TARIKH_MASUK") 
	private Date tarikhMasuk;
	@Column(name="ID_KEMASKINI")
	private Long idKemaskini;
	@Column(name="TARIKH_KEMASKINI") 
	private Date tarikhKemaskini;

	// Property accessors
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKodDaerah() {
		return this.kodDaerah;
	}

	public void setKodDaerah(String kodDaerah) {
		this.kodDaerah = kodDaerah;
	}

	public String getNamaDaerah() {
		return this.namaDaerah;
	}

	public void setNamaDaerah(String namaDaerah) {
		this.namaDaerah = namaDaerah;
	}

	public Long getIdNegeri() {
		return this.idNegeri;
	}

	public void setIdNegeri(Long idNegeri) {
		this.idNegeri = idNegeri;
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
	public List<HakmilikUrusan> getListHakmilikUrusan() {
		return listHakmilikUrusan;
	}
	public void setListHakmilikUrusan(List<HakmilikUrusan> listHakmilikUrusan) {
		this.listHakmilikUrusan = listHakmilikUrusan;
	}

}