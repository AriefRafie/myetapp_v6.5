package etapp.entity.htp;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ekptg.model.htp.entity.Permohonan;
import etapp.entity.Pegawai;
import etapp.entity.rujukan.Agensi;
import etapp.entity.rujukan.Daerah;
import etapp.entity.rujukan.JenisTanah;

@Entity
@Table(name = "TBLHTPPERMOHONAN")
public class HTPPermohonan {
	
	@Id @Column(name="ID_HTPPERMOHONAN")
	private String id;
	@ManyToOne @JoinColumn(name="ID_PERMOHONAN")
	private Permohonan permohonan;
	@ManyToOne @JoinColumn(name="ID_AGENSI")
	private Agensi agensi;
	@ManyToOne @JoinColumn(name="ID_DAERAH")
	private Daerah daerah;
	@ManyToOne @JoinColumn(name="ID_JENISTANAH")
	private JenisTanah jenisTanah;
	@ManyToOne @JoinColumn(name="ID_PEGAWAI")
	private Pegawai pegawai;

	@Temporal(TemporalType.DATE) @Column(name="TARIKH_AGIHAN")
	private Date tarikhAgihan;
	@Column(name="NO_RUJUKAN_KJP")
	private String noRujukanKJP;
	@Column(name="NO_RUJUKAN_LAIN")
	private String noRujukanLain;
	@Temporal(TemporalType.DATE) @Column(name="TARIKH_SURAT_RUJUKAN_LAIN")
	private Date tarikhSuratRujukanLain;
	@Column(name="NO_RUJUKAN_PTG")
	private String noRujukanPTG;
	@Column(name="NO_RUJUKAN_PTD")
	private String noRujukanPTD;
	@Column(name="NO_RUJUKAN_UPT")
	private String noRujukanUPT;
	@Column(name="ID_MASUK")
	private Long idMasuk;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_MASUK")
	private Date tarikhMasuk;
	@Column(name="ID_KEMASKINI")
	private Long idKemaskini;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_KEMASKINI")
	private Date tarikhKemaskini;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Permohonan getPermohonan() {
		return permohonan;
	}
	public void setPermohonan(Permohonan permohonan) {
		this.permohonan = permohonan;
	}
	public Agensi getAgensi() {
		return agensi;
	}
	public void setAgensi(Agensi agensi) {
		this.agensi = agensi;
	}
	public Daerah getDaerah() {
		return daerah;
	}
	public void setDaerah(Daerah daerah) {
		this.daerah = daerah;
	}
	public JenisTanah getJenisTanah() {
		return jenisTanah;
	}
	public void setJenisTanah(JenisTanah jenisTanah) {
		this.jenisTanah = jenisTanah;
	}
	public Pegawai getPegawai() {
		return pegawai;
	}
	public void setPegawai(Pegawai pegawai) {
		this.pegawai = pegawai;
	}
	public Date getTarikhAgihan() {
		return tarikhAgihan;
	}
	public void setTarikhAgihan(Date tarikhAgihan) {
		this.tarikhAgihan = tarikhAgihan;
	}
	public String getNoRujukanKJP() {
		return noRujukanKJP;
	}
	public void setNoRujukanKJP(String noRujukanKJP) {
		this.noRujukanKJP = noRujukanKJP;
	}
	public String getNoRujukanLain() {
		return noRujukanLain;
	}
	public void setNoRujukanLain(String noRujukanLain) {
		this.noRujukanLain = noRujukanLain;
	}
	public Date getTarikhSuratRujukanLain() {
		return tarikhSuratRujukanLain;
	}
	public void setTarikhSuratRujukanLain(Date tarikhSuratRujukanLain) {
		this.tarikhSuratRujukanLain = tarikhSuratRujukanLain;
	}
	public String getNoRujukanPTG() {
		return noRujukanPTG;
	}
	public void setNoRujukanPTG(String noRujukanPTG) {
		this.noRujukanPTG = noRujukanPTG;
	}
	public String getNoRujukanPTD() {
		return noRujukanPTD;
	}
	public void setNoRujukanPTD(String noRujukanPTD) {
		this.noRujukanPTD = noRujukanPTD;
	}
	public String getNoRujukanUPT() {
		return noRujukanUPT;
	}
	public void setNoRujukanUPT(String noRujukanUPT) {
		this.noRujukanUPT = noRujukanUPT;
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
