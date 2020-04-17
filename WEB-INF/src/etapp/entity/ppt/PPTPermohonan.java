package etapp.entity.ppt;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lebah.template.UID;
import etapp.entity.pfd.Fail;
import etapp.entity.rujukan.Agensi;
import etapp.entity.rujukan.Daerah;
import etapp.entity.rujukan.Mukim;
import etapp.entity.rujukan.Negeri;
import etapp.entity.rujukan.Status;


@Entity
@Table(name="TBLPPTPERMOHONAN")
public class PPTPermohonan {
	
	@Id
	@Column(name="ID_PERMOHONAN")
	private long id;
	@OneToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="ID_FAIL")
	private Fail fail;
	@Column(name="NO_PERMOHONAN")
	private String nomborPermohonan;
	@Column(name="NO_PERSERAHAN")
	private String nomborPerserahan;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="TARIKH_PERMOHONAN")
	private Date tarikhPermohonan;
	@ManyToOne
	@JoinColumn(name="ID_AGENSI")
	private Agensi agensi;
	@Column(name="FLAG_PERUNTUKAN")
	private String flagPeruntukan;
	@Column(name="FLAG_SEGERA")
	private String flagSegera;
	@Column(name="TUJUAN")
	private String tujuan;
	@Column(name="NO_RUJUKAN_SURAT")
	private String nomborRujukanSurat;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="TARIKH_SURAT")
	private Date tarikhSurat;
	@Temporal(TemporalType.DATE)
	@Column(name="TARIKH_KEHENDAKI")
	private Date tarikhKehendaki;
	@Column(name="ALAMAT1")
	private String alamat1;
	@Column(name="ALAMAT2")
	private String alamat2;
	@Column(name="ALAMAT3")
	private String alamat3;
	@ManyToOne
	@JoinColumn(name="ID_NEGERI")
	private Negeri negeri;
	@ManyToOne
	@JoinColumn(name="ID_MUKIM")
	private Mukim mukim;
	@ManyToOne
	@JoinColumn(name="ID_STATUS")
	private Status status;
	@ManyToOne
	@JoinColumn(name="ID_DAERAH")
	private Daerah daerah;
	@Temporal(TemporalType.DATE)
	@Column(name="TARIKH_BORANGA")
	private Date tarikhBorangA;
	@Temporal(TemporalType.DATE)
	@Column(name="TARIKH_BORANGB")
	private Date tarikhBorangB;
	@Temporal(TemporalType.DATE)
	@Column(name="TARIKH_BORANGC")
	private Date tarikhBorangC;
	@Temporal(TemporalType.DATE)
	@Column(name="TARIKH_BORANGD")
	private Date tarikhBorangD;
	@Column(name="PERIHAL_KAWASAN")
	private String perihalKawasan;
	@Temporal(TemporalType.DATE)
	@Column(name="TARIKH_TERIMA")
	private Date tarikhTerima;
	@Temporal(TemporalType.DATE)
	@Column(name="TARIKH_SEMAK")
	private Date tarikhSemak;
	@Temporal(TemporalType.DATE)
	@Column(name="TARIKH_SAHKAN")
	private Date tarikhSahkan;
	@Temporal(TemporalType.DATE)
	@Column(name="TARIKH_MASUK")
	private Date tarikhMasuk;
	@Temporal(TemporalType.DATE)
	@Column(name="TARIKH_KEMASKINI")
	private Date tarikhKemaskini;
	@Column(name="NO_RUJUKAN_PTG")
	private String noRujukanPTG;
	@Column(name="NO_RUJUKAN_PTD")
	private String noRujukanPTD;
	@Column(name="NO_RUJUKAN_UPT")
	private String noRujukanUPT;
	@Column(name="FLAG_JENISPERMOHONAN")
	private String flagJenisPermohonan;
	@Column(name="FLAG_JENIS_PROJEK")
	private String flagJenisProjek;
	@OneToMany(cascade=CascadeType.REMOVE, mappedBy="permohonan")
	private List<PPTHakmilik> listHakmilik;
	@OneToMany(cascade=CascadeType.REMOVE, mappedBy="permohonan")
	private List<PPTDokumen> listDokumen;
	@ManyToOne
	@JoinColumn(name="ID_JTS_STATUS")
	private PPTStatus jtsStatus;
	@ManyToOne
	@JoinColumn(name="ID_STATUS_UPDATE")
	private PPTStatusUpdate jtsStatusUpdateLog;
	
	
	public PPTPermohonan() {
		setId(UID.get());
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getNomborPermohonan() {
		return nomborPermohonan;
	}
	public void setNomborPermohononan(String nomborPermohonan) {
		this.nomborPermohonan = nomborPermohonan;
	}
	public String getNomborPerserahan() {
		return nomborPerserahan;
	}
	public void setNomborPerserahan(String nomborPerserahan) {
		this.nomborPerserahan = nomborPerserahan;
	}
	public Date getTarikhPermohonan() {
		return tarikhPermohonan;
	}
	public void setTarikhPermohonan(Date tarikhPermohonan) {
		this.tarikhPermohonan = tarikhPermohonan;
	}
	public Agensi getAgensi() {
		return agensi;
	}
	public void setAgensi(Agensi agensi) {
		this.agensi = agensi;
	}
	public String getFlagPeruntukan() {
		return flagPeruntukan;
	}
	public void setFlagPeruntukan(String flagPeruntukan) {
		this.flagPeruntukan = flagPeruntukan;
	}
	public String getFlagSegera() {
		return flagSegera;
	}
	public void setFlagSegera(String flagSegera) {
		this.flagSegera = flagSegera;
	}
	public String getTujuan() {
		return tujuan;
	}
	public void setTujuan(String tujuan) {
		this.tujuan = tujuan;
	}
	public String getNomborRujukanSurat() {
		return nomborRujukanSurat;
	}
	public void setNomborRujukanSurat(String nomborRujukanSurat) {
		this.nomborRujukanSurat = nomborRujukanSurat;
	}
	public Date getTarikhSurat() {
		return tarikhSurat;
	}
	public void setTarikhSurat(Date tarikhSurat) {
		this.tarikhSurat = tarikhSurat;
	}
	public Date getTarikhKehendaki() {
		return tarikhKehendaki;
	}
	public void setTarikhKehendaki(Date tarikhKehendaki) {
		this.tarikhKehendaki = tarikhKehendaki;
	}
	public String getAlamat1() {
		return alamat1;
	}
	public void setAlamat1(String alamat1) {
		this.alamat1 = alamat1;
	}
	public String getAlamat2() {
		return alamat2;
	}
	public void setAlamat2(String alamat2) {
		this.alamat2 = alamat2;
	}
	public String getAlamat3() {
		return alamat3;
	}
	public void setAlamat3(String alamat3) {
		this.alamat3 = alamat3;
	}
	public Negeri getNegeri() {
		return negeri;
	}
	public void setNegeri(Negeri negeri) {
		this.negeri = negeri;
	}
	public Mukim getMukim() {
		return mukim;
	}
	public void setMukim(Mukim mukim) {
		this.mukim = mukim;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Daerah getDaerah() {
		return daerah;
	}
	public void setDaerah(Daerah daerah) {
		this.daerah = daerah;
	}
	public Date getTarikhBorangA() {
		return tarikhBorangA;
	}
	public void setTarikhBorangA(Date tarikhBorangA) {
		this.tarikhBorangA = tarikhBorangA;
	}
	public Date getTarikhBorangB() {
		return tarikhBorangB;
	}
	public void setTarikhBorangB(Date tarikhBorangB) {
		this.tarikhBorangB = tarikhBorangB;
	}
	public Date getTarikhBorangC() {
		return tarikhBorangC;
	}
	public void setTarikhBorangC(Date tarikhBorangC) {
		this.tarikhBorangC = tarikhBorangC;
	}
	public Date getTarikhBorangD() {
		return tarikhBorangD;
	}
	public void setTarikhBorangD(Date tarikhBorangD) {
		this.tarikhBorangD = tarikhBorangD;
	}

	public Fail getFail() {
		return fail;
	}

	public void setFail(Fail fail) {
		this.fail = fail;
	}

	public void setNomborPermohonan(String nomborPermohonan) {
		this.nomborPermohonan = nomborPermohonan;
	}

	public String getPerihalKawasan() {
		return perihalKawasan;
	}

	public void setPerihalKawasan(String perihalKawasan) {
		this.perihalKawasan = perihalKawasan;
	}

	public Date getTarikhTerima() {
		return tarikhTerima;
	}

	public void setTarikhTerima(Date tarikhTerima) {
		this.tarikhTerima = tarikhTerima;
	}

	public Date getTarikhSemak() {
		return tarikhSemak;
	}

	public void setTarikhSemak(Date tarikhSemak) {
		this.tarikhSemak = tarikhSemak;
	}

	public Date getTarikhSahkan() {
		return tarikhSahkan;
	}

	public void setTarikhSahkan(Date tarikhSahkan) {
		this.tarikhSahkan = tarikhSahkan;
	}

	public Date getTarikhMasuk() {
		return tarikhMasuk;
	}

	public void setTarikhMasuk(Date tarikhMasuk) {
		this.tarikhMasuk = tarikhMasuk;
	}

	public Date getTarikhKemaskini() {
		return tarikhKemaskini;
	}

	public void setTarikhKemaskini(Date tarikhKemaskini) {
		this.tarikhKemaskini = tarikhKemaskini;
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

	public String getFlagJenisPermohonan() {
		return flagJenisPermohonan;
	}

	public void setFlagJenisPermohonan(String flagJenisPermohonan) {
		this.flagJenisPermohonan = flagJenisPermohonan;
	}

	public String getFlagJenisProjek() {
		return flagJenisProjek;
	}

	public void setFlagJenisProjek(String flagJenisProjek) {
		this.flagJenisProjek = flagJenisProjek;
	}

	public List<PPTHakmilik> getListHakmilik() {
		return listHakmilik;
	}

	public void setListHakmilik(List<PPTHakmilik> listHakmilik) {
		this.listHakmilik = listHakmilik;
	}

	public List<PPTDokumen> getListDokumen() {
		return listDokumen;
	}

	public void setListDokumen(List<PPTDokumen> listDokumen) {
		this.listDokumen = listDokumen;
	}

	public PPTStatus getJtsStatus() {
		return jtsStatus;
	}

	public void setJtsStatus(PPTStatus jtsStatus) {
		this.jtsStatus = jtsStatus;
	}

	public PPTStatusUpdate getJtsStatusUpdateLog() {
		return jtsStatusUpdateLog;
	}

	public void setJtsStatusUpdateLog(PPTStatusUpdate jtsStatusUpdateLog) {
		this.jtsStatusUpdateLog = jtsStatusUpdateLog;
	}
	
	
	
	
	

}
