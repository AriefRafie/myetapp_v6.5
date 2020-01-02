package etapp.entity.ppt;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lebah.template.UID;

@Entity
@Table(name = "TBLPPTBORANGK")

public class PPTBorangK {
	
	@Id
	@Column(name="ID_BORANGK")
	private long id;
	
	@ManyToOne @JoinColumn(name="ID_HAKMILIK")
	private PPTHakmilik hakmilik;
	
	@ManyToOne @JoinColumn(name="ID_PERMOHONAN")
	private PPTPermohonan permohonan;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="TARIKH_BORANGK")
	private Date tarikhBorangK;
	@Column(name="CATATAN")
	private String catatan;
	
	@Column(name="LS_80")
	private String LS80;
	@Column(name="PAMPASAN")
	private String pampasan;
	@Column(name="KOS_PEROLEHAN")
	private String kosPerolehan;
	@Column(name="KAEDAH_PEROLEHAN")
	private String kaedahPerolehan;
	
	public PPTBorangK() {
		setId(UID.get());
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public PPTPermohonan getPermohonan() {
		return permohonan;
	}
	public void setPermohonan(PPTPermohonan permohonan) {
		this.permohonan = permohonan;
	}
	public Date getTarikhBorangK() {
		return tarikhBorangK;
	}
	public void setTarikhBorangK(Date tarikhBorangK) {
		this.tarikhBorangK = tarikhBorangK;
	}
	public String getCatatan() {
		return catatan;
	}
	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}

	public PPTHakmilik getHakmilik() {
		return hakmilik;
	}

	public void setHakmilik(PPTHakmilik hakmilik) {
		this.hakmilik = hakmilik;
	}

	public String getLS80() {
		return LS80;
	}

	public void setLS80(String ls80) {
		LS80 = ls80;
	}

	public String getPampasan() {
		return pampasan;
	}

	public void setPampasan(String pampasan) {
		this.pampasan = pampasan;
	}

	public String getKosPerolehan() {
		return kosPerolehan;
	}

	public void setKosPerolehan(String kosPerolehan) {
		this.kosPerolehan = kosPerolehan;
	}

	public String getKaedahPerolehan() {
		return kaedahPerolehan;
	}

	public void setKaedahPerolehan(String kaedahPerolehan) {
		this.kaedahPerolehan = kaedahPerolehan;
	}
	
	
	
	
	
	

}
