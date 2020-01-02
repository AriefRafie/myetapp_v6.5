package etapp.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "TBLPHPPEMOHON")
public class PHPPemohon {
	
	@Id @Column(name="ID_PEMOHON")
	private String id;
	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="pemohon")
	private List<Permohonan> listPermohonan;
	
	@Column(name="NAMA")
	private String nama;
	@Column(name="NO_PENGENALAN")
	private String nomborPengenalan;
	@Column(name="ALAMAT1_TETAP")
	private String alamatTetap1;
	@Column(name="ALAMAT1_TETAP")
	private String alamatTetap2;
	@Column(name="ALAMAT1_TETAP")
	private String alamatTetap3;
	@Column(name="POSKOD_TETAP")
	private String poskodTetap;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getNomborPengenalan() {
		return nomborPengenalan;
	}
	public void setNomborPengenalan(String nomborPengenalan) {
		this.nomborPengenalan = nomborPengenalan;
	}
	public String getAlamatTetap1() {
		return alamatTetap1;
	}
	public void setAlamatTetap1(String alamatTetap1) {
		this.alamatTetap1 = alamatTetap1;
	}
	public String getAlamatTetap2() {
		return alamatTetap2;
	}
	public void setAlamatTetap2(String alamatTetap2) {
		this.alamatTetap2 = alamatTetap2;
	}
	public String getAlamatTetap3() {
		return alamatTetap3;
	}
	public void setAlamatTetap3(String alamatTetap3) {
		this.alamatTetap3 = alamatTetap3;
	}
	public String getPoskodTetap() {
		return poskodTetap;
	}
	public void setPoskodTetap(String poskodTetap) {
		this.poskodTetap = poskodTetap;
	}
	public List<Permohonan> getListPermohonan() {
		return listPermohonan;
	}
	public void setListPermohonan(List<Permohonan> listPermohonan) {
		this.listPermohonan = listPermohonan;
	}
	
	

}
