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
@Table(name = "TBLPPKPERMOHONAN")
public class PPKPermohonanMobile {

	@Id
	@Column(name = "ID_PERMOHONAN")
	private long id;
	@Column(name = "NO_PERMOHONAN_ONLINE")
	private String noPermohonan;

	@ManyToOne
	@JoinColumn(name = "ID_PEMOHON")
	private PPKPemohonMobile pemohon;

	@Column(name = "TARIKH_MOHON_ONLINE")
	@Temporal(TemporalType.DATE)
	private Date tarikhMohon;

	@ManyToOne
	@JoinColumn(name = "ID_STATUS")
	private RujStatusMobile status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNoPermohonan() {
		return noPermohonan;
	}

	public void setNoPermohonan(String noPermohonan) {
		this.noPermohonan = noPermohonan;
	}

	public PPKPemohonMobile getPemohon() {
		return pemohon;
	}

	public void setPemohon(PPKPemohonMobile pemohon) {
		this.pemohon = pemohon;
	}

	public Date getTarikhMohon() {
		return tarikhMohon;
	}

	public void setTarikhMohon(Date tarikhMohon) {
		this.tarikhMohon = tarikhMohon;
	}

	public RujStatusMobile getStatus() {
		return status;
	}

	public void setStatus(RujStatusMobile status) {
		this.status = status;
	}
}