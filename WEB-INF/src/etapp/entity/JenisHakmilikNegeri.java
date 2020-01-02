package etapp.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ekptg.model.entities.Negeri;

@Entity
@Table(name = "TBLRUJJENISHAKMILIKNEGERI")
public class JenisHakmilikNegeri {

	// Fields
	@Id @Column(name="ID_JENISHAKMILIKNEGERI")
	private Long id;	
//	@ManyToMany(cascade=CascadeType.PERSIST)
//    @JoinTable(
//            name="division_employee",
//            joinColumns=@JoinColumn(name="division_id", referencedColumnName="division_id"),
//            inverseJoinColumns=@JoinColumn(name="employee_no", referencedColumnName="employee_no")
//        )

	@ManyToOne @JoinColumn(name="ID_NEGERI")
	private Negeri negeri;	
	@ManyToOne @JoinColumn(name="ID_JENISHAKMILIK")
	private JenisHakmilik jenisHakmilik;	
	
//	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="jenisHakmilik")
//	private List<HakmilikUrusan> listHakmilikUrusan;

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

	public JenisHakmilik getJenisHakmilik() {
		return this.jenisHakmilik;
	}

	public void setJenisHakmilik(JenisHakmilik jenisHakmilik) {
		this.jenisHakmilik = jenisHakmilik;
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
	
//	public List<HakmilikUrusan> getListHakmilikUrusan() {
//		return listHakmilikUrusan;
//	}
//	public void setListHakmilikUrusan(List<HakmilikUrusan> listHakmilikUrusan) {
//		this.listHakmilikUrusan = listHakmilikUrusan;
//	}
	
	public Negeri getNegeri() {
		if (this.negeri == null)negeri = null;
		return negeri;
	}
	public void setNegeri(Negeri negeri) {
		this.negeri = negeri;
	}


}