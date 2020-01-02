package etapp.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import etapp.entity.htp.HakmilikUrusan;

@Entity
@Table(name="TBLPERMOHONAN")
public class Permohonan {
	
	@Id
	@Column(name="ID_PERMOHONAN")
	private long id;
	@OneToMany(mappedBy="permohonan", cascade=CascadeType.PERSIST)
	List<HakmilikUrusan> hakmilikUrusan;
	
	@Column(name="NO_PERMOHONAN")
	private String nomborPermohonan;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="TARIKH_SURAT")
	private Date tarikhSurat;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNomborPermohonan() {
		return nomborPermohonan;
	}
	public void setNomborPermohonan(String nomborPermohonan) {
		this.nomborPermohonan = nomborPermohonan;
	}
	public Date getTarikhSurat() {
		return tarikhSurat;
	}
	public void setTarikhSurat(Date tarikhSurat) {
		this.tarikhSurat = tarikhSurat;
	}
	public List<HakmilikUrusan> getHakmilikUrusan() {
		return hakmilikUrusan;
	}
	public void setHakmilikUrusan(List<HakmilikUrusan> hakMilikUrusans) {
		this.hakmilikUrusan = hakMilikUrusans;
	}
	
	

}
