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
@Table(name = "TBLPPTPERMOHONAN")
public class PPTPermohonanMobile {

	@Id @Column(name="ID_PERMOHONAN")
	private long id;
	@Column(name="NO_PERMOHONAN")
	private String noPermohonan;
	@Column(name="TUJUAN")
	private String tujuan;
	@Column(name="NO_PERMOHONAN_ONLINE")
	private String noPermohonanOnline;
	@Column(name="TARIKH_PERMOHONAN")
	@Temporal(TemporalType.DATE)
	private Date tarikhPermohonan;
	@Column(name="TARIKH_KEMASKINI")
	@Temporal(TemporalType.DATE)
	private Date tarikhKemaskini;
	@Column(name="NO_RUJUKAN_PTG")
	private String noRujPTG;
	@Column(name="NO_RUJUKAN_PTD")
	private String noRujPTD;
	@Column(name="NO_RUJUKAN_UPT")
	private String noRujUPT;
	@Column(name="NO_RUJUKAN_SURAT")
	private String noRujSurat;
	
	@ManyToOne
	@JoinColumn(name="ID_AGENSI")
	private RujAgensiMobile agensi;
	@ManyToOne
	@JoinColumn(name="ID_FAIL")
	private PFDFailMobile fail;	
	@ManyToOne
	@JoinColumn(name="ID_MASUK")
	private UsersMobile idUser;
	@ManyToOne
	@JoinColumn(name="ID_STATUS")
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
	public String getTujuan() {
		return tujuan;
	}
	public void setTujuan(String tujuan) {
		this.tujuan = tujuan;
	}
	public String getNoPermohonanOnline() {
		return noPermohonanOnline;
	}
	public void setNoPermohonanOnline(String noPermohonanOnline) {
		this.noPermohonanOnline = noPermohonanOnline;
	}
	public Date getTarikhPermohonan() {
		return tarikhPermohonan;
	}
	public void setTarikhPermohonan(Date tarikhPermohonan) {
		this.tarikhPermohonan = tarikhPermohonan;
	}
	public Date getTarikhKemaskini() {
		return tarikhKemaskini;
	}
	public void setTarikhKemaskini(Date tarikhKemaskini) {
		this.tarikhKemaskini = tarikhKemaskini;
	}
	public String getNoRujPTG() {
		return noRujPTG;
	}
	public void setNoRujPTG(String noRujPTG) {
		this.noRujPTG = noRujPTG;
	}
	public String getNoRujPTD() {
		return noRujPTD;
	}
	public void setNoRujPTD(String noRujPTD) {
		this.noRujPTD = noRujPTD;
	}
	public String getNoRujUPT() {
		return noRujUPT;
	}
	public void setNoRujUPT(String noRujUPT) {
		this.noRujUPT = noRujUPT;
	}
	public String getNoRujSurat() {
		return noRujSurat;
	}
	public void setNoRujSurat(String noRujSurat) {
		this.noRujSurat = noRujSurat;
	}
	public RujAgensiMobile getAgensi() {
		return agensi;
	}
	public void setAgensi(RujAgensiMobile agensi) {
		this.agensi = agensi;
	}
	public PFDFailMobile getFail() {
		return fail;
	}
	public void setFail(PFDFailMobile fail) {
		this.fail = fail;
	}
	public UsersMobile getIdUser() {
		return idUser;
	}
	public void setIdUser(UsersMobile idUser) {
		this.idUser = idUser;
	}
	public RujStatusMobile getStatus() {
		return status;
	}
	public void setStatus(RujStatusMobile status) {
		this.status = status;
	}
}