package etapp.entity.htp;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import etapp.entity.rujukan.JenisPB;
import etapp.entity.rujukan.Urusan;
import etapp.entity.rujukan.Warganegara;

@Entity
@Table(name = "TBLHTPPENGARAH")
public class Pengarah{
	// Fields
	@Id @Column(name="ID_PENGARAH")
	private Long id;	
	@ManyToOne @JoinColumn(name="ID_PEMAJU")
	private Pemaju pemaju;
	@ManyToOne @JoinColumn(name="ID_JENISPB")
	private JenisPB jenisPB;
	@ManyToOne @JoinColumn(name="ID_URUSAN")
	private Urusan urusan;
	@ManyToOne @JoinColumn(name="ID_WARGANEGARA")
	private Warganegara warganegara;
	
	@Column(name="NO_OPB")
	private String noRujukan; //no syarikat
	@Column(name="NAMA")
	private String nama;
	@Column(name="ID_MASUK")
	private Long idMasuk;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_MASUK")
	private Date tarikhMasuk;
	@Column(name="ID_KEMASKINI")
	private Long idKemaskini;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_KEMASKINI")
	private Date tarikhKemaskini;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Pemaju getPemaju() {
		return pemaju;
	}
	public void setPemaju(Pemaju pemaju) {
		this.pemaju = pemaju;
	}
	public JenisPB getJenisPB() {
		return jenisPB;
	}
	public void setJenisPB(JenisPB jenisPB) {
		this.jenisPB = jenisPB;
	}
	public Urusan getUrusan() {
		return urusan;
	}
	public void setUrusan(Urusan urusan) {
		this.urusan = urusan;
	}
	public Warganegara getWarganegara() {
		return warganegara;
	}
	public void setWarganegara(Warganegara warganegara) {
		this.warganegara = warganegara;
	}
	public String getNoRujukan() {
		return noRujukan;
	}
	public void setNoRujukan(String noRujukan) {
		this.noRujukan = noRujukan;
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