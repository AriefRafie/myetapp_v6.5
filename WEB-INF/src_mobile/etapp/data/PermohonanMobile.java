package etapp.data;

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
@Table(name = "TBLPERMOHONAN")
public class PermohonanMobile {
	
	@Id @Column(name="ID_PERMOHONAN")
	private long id;
	
	@ManyToOne
	@JoinColumn(name="ID_FAIL")
	private PFDFailMobile fail;
	
	@Column(name="NO_PERMOHONAN")
	private String noPermohonan;
	@Column(name="TARIKH_TERIMA")
	@Temporal(TemporalType.DATE)
	private Date tarikhTerima;
	@Column(name="TARIKH_SURAT")
	@Temporal(TemporalType.DATE)
	private Date tarikhSurat;
	@Column(name="TUJUAN")
	private String tujuan;
	@Column(name="TARIKH_KEMASKINI")
	@Temporal(TemporalType.DATE)
	private Date tarikhKemaskini;
	
	@ManyToOne
	@JoinColumn(name="ID_PEMOHON")
	private PHPPemohonMobile pemohon;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public PFDFailMobile getFail() {
		return fail;
	}
	public void setFail(PFDFailMobile fail) {
		this.fail = fail;
	}
	public String getNoPermohonan() {
		return noPermohonan;
	}
	public void setNoPermohonan(String noPermohonan) {
		this.noPermohonan = noPermohonan;
	}
	public Date getTarikhTerima() {
		return tarikhTerima;
	}
	public void setTarikhTerima(Date tarikhTerima) {
		this.tarikhTerima = tarikhTerima;
	}
	public Date getTarikhSurat() {
		return tarikhSurat;
	}
	public void setTarikhSurat(Date tarikhSurat) {
		this.tarikhSurat = tarikhSurat;
	}
	public String getTujuan() {
		return tujuan;
	}
	public void setTujuan(String tujuan) {
		this.tujuan = tujuan;
	}
	public Date getTarikhKemaskini() {
		return tarikhKemaskini;
	}
	public void setTarikhKemaskini(Date tarikhKemaskini) {
		this.tarikhKemaskini = tarikhKemaskini;
	}
	public PHPPemohonMobile getPemohon() {
		return pemohon;
	}
	public void setPemohon(PHPPemohonMobile pemohon) {
		this.pemohon = pemohon;
	}
}