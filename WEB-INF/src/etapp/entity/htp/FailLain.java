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

import etapp.entity.pfd.Fail;

@Entity
@Table(name = "TBLHTPFAILLAIN")
public class FailLain {
	@Id @Column(name="ID_HTPFAILLAIN")
	private long id;
	@ManyToOne @JoinColumn(name="ID_FAIL")
	private Fail fail;
	@ManyToOne @JoinColumn(name="ID_FAILBARU")
	private Fail failBaru;
	
	@Column(name="NO_FAIL_LAIN")
	private String noFailLain;
	@Column(name="ID_MASUK")
	private Long idMasuk;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_MASUK")
	private Date tarikhMasuk;
	@Column(name="ID_KEMASKINI")
	private Long idKemaskini;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_KEMASKINI")
	private Date tarikhKemaskini;
	//
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public Fail getFail() {
		return fail;
	}
	public void setFail(Fail fail) {
		this.fail = fail;
	}
	public Fail getFailBaru() {
		return failBaru;
	}
	public void setFailBaru(Fail failBaru) {
		this.failBaru = failBaru;
	}
	public String getNoFailLain() {
		return noFailLain;
	}
	public void setNoFailLain(String noFailLain) {
		this.noFailLain = noFailLain;
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
