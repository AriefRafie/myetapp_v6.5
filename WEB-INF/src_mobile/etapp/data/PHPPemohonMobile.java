package etapp.data;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TBLPHPPEMOHON")
public class PHPPemohonMobile {

	@Id @Column(name="ID_PEMOHON")
	private long id;
	@Column(name="NAMA")
	private String nama;
	@Column(name="NO_PENGENALAN")
	private String noPengenalan;
	@Column(name="ID_MASUK")
	private long idMasuk;
	
	@OneToMany(mappedBy="pemohon")
	List<PermohonanMobile> permohonanMobileList;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getNoPengenalan() {
		return noPengenalan;
	}
	public void setNoPengenalan(String noPengenalan) {
		this.noPengenalan = noPengenalan;
	}
	public long getIdMasuk() {
		return idMasuk;
	}
	public void setIdMasuk(long idMasuk) {
		this.idMasuk = idMasuk;
	}
	public List<PermohonanMobile> getPermohonanMobileList() {
		return permohonanMobileList;
	}
	public void setPermohonanMobileList(List<PermohonanMobile> permohonanMobileList) {
		this.permohonanMobileList = permohonanMobileList;
	}
}