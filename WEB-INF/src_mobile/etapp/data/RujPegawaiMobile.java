package etapp.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TBLRUJPEGAWAI")
public class RujPegawaiMobile {

	@Id @Column(name="ID_PEGAWAI")
	private Long id;
	@Column(name="NAMA_PEGAWAI")
	private String namaPegawai;
	@Column(name="JAWATAN")
	private String jawatan;
	
	@ManyToOne
	@JoinColumn(name="ID_NEGERI")
	private RujNegeriMobile negeri;
	
	@ManyToOne
	@JoinColumn(name="ID_SEKSYEN")
	private RujJenisAduanMobile seksyen;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNamaPegawai() {
		return namaPegawai;
	}
	public void setNamaPegawai(String namaPegawai) {
		this.namaPegawai = namaPegawai;
	}
	public String getJawatan() {
		return jawatan;
	}
	public void setJawatan(String jawatan) {
		this.jawatan = jawatan;
	}
	public RujNegeriMobile getNegeri() {
		return negeri;
	}
	public void setNegeri(RujNegeriMobile negeri) {
		this.negeri = negeri;
	}
	public RujJenisAduanMobile getSeksyen() {
		return seksyen;
	}
	public void setSeksyen(RujJenisAduanMobile seksyen) {
		this.seksyen = seksyen;
	}
	
}