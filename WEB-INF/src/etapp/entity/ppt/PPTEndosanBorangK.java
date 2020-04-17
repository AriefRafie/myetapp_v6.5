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
@Table(name = "TBLPPTENDOSANBORANGK")

public class PPTEndosanBorangK {
	
	@Id
	@Column(name="ID_ENDOSANBORANGK")
	private long id;
	@ManyToOne @JoinColumn(name="ID_BORANGK")
	private PPTBorangK borangK;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="TARIKH_CATATAN")
	private Date tarikhCatatan;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="TARIKH_TERIMA")
	private Date tarikhTerima;
	
	@Column(name="MASA_CATATAN")
	private String masaCatatan;
	@Column(name="JENIS_MASA")
	private String jenisMasa;
	@Column(name="NO_PERSERAHAN")
	private String noPerserahan;
	
	public PPTEndosanBorangK() {
		setId(UID.get());
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public PPTBorangK getBorangK() {
		return borangK;
	}
	public void setBorangK(PPTBorangK borangK) {
		this.borangK = borangK;
	}
	public Date getTarikhCatatan() {
		return tarikhCatatan;
	}
	public void setTarikhCatatan(Date tarikhCatatan) {
		this.tarikhCatatan = tarikhCatatan;
	}
	public String getMasaCatatan() {
		return masaCatatan;
	}
	public void setMasaCatatan(String masaCatatan) {
		this.masaCatatan = masaCatatan;
	}
	public String getJenisMasa() {
		return jenisMasa;
	}
	public void setJenisMasa(String jenisMasa) {
		this.jenisMasa = jenisMasa;
	}

	public String getNoPerserahan() {
		return noPerserahan;
	}

	public void setNoPerserahan(String noPerserahan) {
		this.noPerserahan = noPerserahan;
	}

	public Date getTarikhTerima() {
		return tarikhTerima;
	}

	public void setTarikhTerima(Date tarikhTerima) {
		this.tarikhTerima = tarikhTerima;
	}
	


}
