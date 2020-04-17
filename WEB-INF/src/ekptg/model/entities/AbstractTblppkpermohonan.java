package ekptg.model.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractTblppkpermohonan entity provides the base persistence definition of
 * the Tblppkpermohonan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblppkpermohonan implements java.io.Serializable {

	// Fields

	private Long idPermohonan;
	private Tblrujppkbuktimati tblrujppkbuktimati;
	private Tblrujppkunit tblrujppkunit;
	private Tblrujppktarafkptg tblrujppktarafkptg;
	private Long idFail;
	private Date tarikhMohon;
	private Long bilBicara;
	private Double jumlahHtaTarikhmohon;
	private Double jumlahHtaTarikhmati;
	private Double jumlahHaTarikhmohon;
	private Double jumlahHaTarikhmati;
	private Double jumlahHartaTarikhmohon;
	private Double jumlahHartaTarikhmati;
	private String bidangKuasa;
	private String flagJenisBorangc;
	private String keputusan;
	private String catatan;
	private Long idNegerimhn;
	private Long idDaerahmhn;
	private Long idStatus;
	private String seksyen;
	private Long idNegeriawal;
	private Long idDaerahawal;
	private Long idPejawal;
	private String noFailAwal;
	private String batalKuasaPentadbir;
	private String lantikPentadbir;
	private String batalPAmanah;
	private String lantikPAmanah;
	private String hartaTinggal;
	private String namaPemohonAwal;
	private String flagStatusPeguam;
	private String jenisFail;
	private Double nilaiTerdahulu;
	private String flagJenisPermohonan;
	private String noPermohonanOnline;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Set tblppkborangps = new HashSet(0);
	private Set tblppkborangas = new HashSet(0);
	private Set tblppkpemohons = new HashSet(0);
	private Set tblppkbayarans = new HashSet(0);
	private Set tblppkbkes = new HashSet(0);
	private Set tblppksimatis = new HashSet(0);
	private Set tblppkrayuans = new HashSet(0);
	private Set tblppkkeputusanpermohonans = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractTblppkpermohonan() {
	}

	/** minimal constructor */
	public AbstractTblppkpermohonan(Long idPermohonan, Long idFail) {
		this.idPermohonan = idPermohonan;
		this.idFail = idFail;
	}

	/** full constructor */
	public AbstractTblppkpermohonan(Long idPermohonan,
			Tblrujppkbuktimati tblrujppkbuktimati, Tblrujppkunit tblrujppkunit,
			Tblrujppktarafkptg tblrujppktarafkptg, Long idFail,
			Date tarikhMohon, Long bilBicara, Double jumlahHtaTarikhmohon,
			Double jumlahHtaTarikhmati, Double jumlahHaTarikhmohon,
			Double jumlahHaTarikhmati, Double jumlahHartaTarikhmohon,
			Double jumlahHartaTarikhmati, String bidangKuasa,
			String flagJenisBorangc, String keputusan, String catatan,
			Long idNegerimhn, Long idDaerahmhn, Long idStatus, String seksyen,
			Long idNegeriawal, Long idDaerahawal, Long idPejawal,
			String noFailAwal, String batalKuasaPentadbir,
			String lantikPentadbir, String batalPAmanah, String lantikPAmanah,
			String hartaTinggal, String namaPemohonAwal, String flagStatusPeguam,
			String jenisFail, Double nilaiTerdahulu, String flagJenisPermohonan,
			String noPermohonanOnline, Long idMasuk, Date tarikhMasuk,
			Long idKemaskini, Date tarikhKemaskini, Set tblppkborangps,
			Set tblppkborangas, Set tblppkpemohons, Set tblppkbayarans,
			Set tblppkbkes, Set tblppksimatis, Set tblppkrayuans,
			Set tblppkkeputusanpermohonans) {
		this.idPermohonan = idPermohonan;
		this.tblrujppkbuktimati = tblrujppkbuktimati;
		this.tblrujppkunit = tblrujppkunit;
		this.tblrujppktarafkptg = tblrujppktarafkptg;
		this.idFail = idFail;
		this.tarikhMohon = tarikhMohon;
		this.bilBicara = bilBicara;
		this.jumlahHtaTarikhmohon = jumlahHtaTarikhmohon;
		this.jumlahHtaTarikhmati = jumlahHtaTarikhmati;
		this.jumlahHaTarikhmohon = jumlahHaTarikhmohon;
		this.jumlahHaTarikhmati = jumlahHaTarikhmati;
		this.jumlahHartaTarikhmohon = jumlahHartaTarikhmohon;
		this.jumlahHartaTarikhmati = jumlahHartaTarikhmati;
		this.bidangKuasa = bidangKuasa;
		this.flagJenisBorangc = flagJenisBorangc;
		this.keputusan = keputusan;
		this.catatan = catatan;
		this.idNegerimhn = idNegerimhn;
		this.idDaerahmhn = idDaerahmhn;
		this.idStatus = idStatus;
		this.seksyen = seksyen;
		this.idNegeriawal = idNegeriawal;
		this.idDaerahawal = idDaerahawal;
		this.idPejawal = idPejawal;
		this.noFailAwal = noFailAwal;
		this.batalKuasaPentadbir = batalKuasaPentadbir;
		this.lantikPentadbir = lantikPentadbir;
		this.batalPAmanah = batalPAmanah;
		this.lantikPAmanah = lantikPAmanah;
		this.hartaTinggal = hartaTinggal;
		this.namaPemohonAwal = namaPemohonAwal;
		this.flagStatusPeguam = flagStatusPeguam;
		this.jenisFail = jenisFail;
		this.nilaiTerdahulu = nilaiTerdahulu;
		this.flagJenisPermohonan = flagJenisPermohonan;
		this.noPermohonanOnline = noPermohonanOnline;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.tblppkborangps = tblppkborangps;
		this.tblppkborangas = tblppkborangas;
		this.tblppkpemohons = tblppkpemohons;
		this.tblppkbayarans = tblppkbayarans;
		this.tblppkbkes = tblppkbkes;
		this.tblppksimatis = tblppksimatis;
		this.tblppkrayuans = tblppkrayuans;
		this.tblppkkeputusanpermohonans = tblppkkeputusanpermohonans;
	}

	// Property accessors

	public Long getIdPermohonan() {
		return this.idPermohonan;
	}

	public void setIdPermohonan(Long idPermohonan) {
		this.idPermohonan = idPermohonan;
	}

	public Tblrujppkbuktimati getTblrujppkbuktimati() {
		return this.tblrujppkbuktimati;
	}

	public void setTblrujppkbuktimati(Tblrujppkbuktimati tblrujppkbuktimati) {
		this.tblrujppkbuktimati = tblrujppkbuktimati;
	}

	public Tblrujppkunit getTblrujppkunit() {
		return this.tblrujppkunit;
	}

	public void setTblrujppkunit(Tblrujppkunit tblrujppkunit) {
		this.tblrujppkunit = tblrujppkunit;
	}

	public Tblrujppktarafkptg getTblrujppktarafkptg() {
		return this.tblrujppktarafkptg;
	}

	public void setTblrujppktarafkptg(Tblrujppktarafkptg tblrujppktarafkptg) {
		this.tblrujppktarafkptg = tblrujppktarafkptg;
	}

	public Long getIdFail() {
		return this.idFail;
	}

	public void setIdFail(Long idFail) {
		this.idFail = idFail;
	}

	public Date getTarikhMohon() {
		return this.tarikhMohon;
	}

	public void setTarikhMohon(Date tarikhMohon) {
		this.tarikhMohon = tarikhMohon;
	}

	public Long getBilBicara() {
		return this.bilBicara;
	}

	public void setBilBicara(Long bilBicara) {
		this.bilBicara = bilBicara;
	}

	public Double getJumlahHtaTarikhmohon() {
		return this.jumlahHtaTarikhmohon;
	}

	public void setJumlahHtaTarikhmohon(Double jumlahHtaTarikhmohon) {
		this.jumlahHtaTarikhmohon = jumlahHtaTarikhmohon;
	}

	public Double getJumlahHtaTarikhmati() {
		return this.jumlahHtaTarikhmati;
	}

	public void setJumlahHtaTarikhmati(Double jumlahHtaTarikhmati) {
		this.jumlahHtaTarikhmati = jumlahHtaTarikhmati;
	}

	public Double getJumlahHaTarikhmohon() {
		return this.jumlahHaTarikhmohon;
	}

	public void setJumlahHaTarikhmohon(Double jumlahHaTarikhmohon) {
		this.jumlahHaTarikhmohon = jumlahHaTarikhmohon;
	}

	public Double getJumlahHaTarikhmati() {
		return this.jumlahHaTarikhmati;
	}

	public void setJumlahHaTarikhmati(Double jumlahHaTarikhmati) {
		this.jumlahHaTarikhmati = jumlahHaTarikhmati;
	}

	public Double getJumlahHartaTarikhmohon() {
		return this.jumlahHartaTarikhmohon;
	}

	public void setJumlahHartaTarikhmohon(Double jumlahHartaTarikhmohon) {
		this.jumlahHartaTarikhmohon = jumlahHartaTarikhmohon;
	}

	public Double getJumlahHartaTarikhmati() {
		return this.jumlahHartaTarikhmati;
	}

	public void setJumlahHartaTarikhmati(Double jumlahHartaTarikhmati) {
		this.jumlahHartaTarikhmati = jumlahHartaTarikhmati;
	}

	public String getBidangKuasa() {
		return this.bidangKuasa;
	}

	public void setBidangKuasa(String bidangKuasa) {
		this.bidangKuasa = bidangKuasa;
	}

	public String getFlagJenisBorangc() {
		return this.flagJenisBorangc;
	}

	public void setFlagJenisBorangc(String flagJenisBorangc) {
		this.flagJenisBorangc = flagJenisBorangc;
	}

	public String getKeputusan() {
		return this.keputusan;
	}

	public void setKeputusan(String keputusan) {
		this.keputusan = keputusan;
	}

	public String getCatatan() {
		return this.catatan;
	}

	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}

	public Long getIdNegerimhn() {
		return this.idNegerimhn;
	}

	public void setIdNegerimhn(Long idNegerimhn) {
		this.idNegerimhn = idNegerimhn;
	}

	public Long getIdDaerahmhn() {
		return this.idDaerahmhn;
	}

	public void setIdDaerahmhn(Long idDaerahmhn) {
		this.idDaerahmhn = idDaerahmhn;
	}

	public Long getIdStatus() {
		return this.idStatus;
	}

	public void setIdStatus(Long idStatus) {
		this.idStatus = idStatus;
	}

	public String getSeksyen() {
		return this.seksyen;
	}

	public void setSeksyen(String seksyen) {
		this.seksyen = seksyen;
	}

	public Long getIdNegeriawal() {
		return this.idNegeriawal;
	}

	public void setIdNegeriawal(Long idNegeriawal) {
		this.idNegeriawal = idNegeriawal;
	}

	public Long getIdDaerahawal() {
		return this.idDaerahawal;
	}

	public void setIdDaerahawal(Long idDaerahawal) {
		this.idDaerahawal = idDaerahawal;
	}

	public Long getIdPejawal() {
		return this.idPejawal;
	}

	public void setIdPejawal(Long idPejawal) {
		this.idPejawal = idPejawal;
	}

	public String getNoFailAwal() {
		return this.noFailAwal;
	}

	public void setNoFailAwal(String noFailAwal) {
		this.noFailAwal = noFailAwal;
	}

	public String getBatalKuasaPentadbir() {
		return this.batalKuasaPentadbir;
	}

	public void setBatalKuasaPentadbir(String batalKuasaPentadbir) {
		this.batalKuasaPentadbir = batalKuasaPentadbir;
	}

	public String getLantikPentadbir() {
		return this.lantikPentadbir;
	}

	public void setLantikPentadbir(String lantikPentadbir) {
		this.lantikPentadbir = lantikPentadbir;
	}

	public String getBatalPAmanah() {
		return this.batalPAmanah;
	}

	public void setBatalPAmanah(String batalPAmanah) {
		this.batalPAmanah = batalPAmanah;
	}

	public String getLantikPAmanah() {
		return this.lantikPAmanah;
	}

	public void setLantikPAmanah(String lantikPAmanah) {
		this.lantikPAmanah = lantikPAmanah;
	}

	public String getHartaTinggal() {
		return this.hartaTinggal;
	}

	public void setHartaTinggal(String hartaTinggal) {
		this.hartaTinggal = hartaTinggal;
	}

	public String getNamaPemohonAwal() {
		return this.namaPemohonAwal;
	}

	public void setNamaPemohonAwal(String namaPemohonAwal) {
		this.namaPemohonAwal = namaPemohonAwal;
	}

	public String getFlagStatusPeguam() {
		return this.flagStatusPeguam;
	}

	public void setFlagStatusPeguam(String flagStatusPeguam) {
		this.flagStatusPeguam = flagStatusPeguam;
	}

	public String getJenisFail() {
		return this.jenisFail;
	}

	public void setJenisFail(String jenisFail) {
		this.jenisFail = jenisFail;
	}

	public Double getNilaiTerdahulu() {
		return this.nilaiTerdahulu;
	}

	public void setNilaiTerdahulu(Double nilaiTerdahulu) {
		this.nilaiTerdahulu = nilaiTerdahulu;
	}

	public String getFlagJenisPermohonan() {
		return this.flagJenisPermohonan;
	}

	public void setFlagJenisPermohonan(String flagJenisPermohonan) {
		this.flagJenisPermohonan = flagJenisPermohonan;
	}

	public String getNoPermohonanOnline() {
		return this.noPermohonanOnline;
	}

	public void setNoPermohonanOnline(String noPermohonanOnline) {
		this.noPermohonanOnline = noPermohonanOnline;
	}

	public Long getIdMasuk() {
		return this.idMasuk;
	}

	public void setIdMasuk(Long idMasuk) {
		this.idMasuk = idMasuk;
	}

	public Date getTarikhMasuk() {
		return this.tarikhMasuk;
	}

	public void setTarikhMasuk(Date tarikhMasuk) {
		this.tarikhMasuk = tarikhMasuk;
	}

	public Long getIdKemaskini() {
		return this.idKemaskini;
	}

	public void setIdKemaskini(Long idKemaskini) {
		this.idKemaskini = idKemaskini;
	}

	public Date getTarikhKemaskini() {
		return this.tarikhKemaskini;
	}

	public void setTarikhKemaskini(Date tarikhKemaskini) {
		this.tarikhKemaskini = tarikhKemaskini;
	}

	public Set getTblppkborangps() {
		return this.tblppkborangps;
	}

	public void setTblppkborangps(Set tblppkborangps) {
		this.tblppkborangps = tblppkborangps;
	}

	public Set getTblppkborangas() {
		return this.tblppkborangas;
	}

	public void setTblppkborangas(Set tblppkborangas) {
		this.tblppkborangas = tblppkborangas;
	}

	public Set getTblppkpemohons() {
		return this.tblppkpemohons;
	}

	public void setTblppkpemohons(Set tblppkpemohons) {
		this.tblppkpemohons = tblppkpemohons;
	}

	public Set getTblppkbayarans() {
		return this.tblppkbayarans;
	}

	public void setTblppkbayarans(Set tblppkbayarans) {
		this.tblppkbayarans = tblppkbayarans;
	}

	public Set getTblppkbkes() {
		return this.tblppkbkes;
	}

	public void setTblppkbkes(Set tblppkbkes) {
		this.tblppkbkes = tblppkbkes;
	}

	public Set getTblppksimatis() {
		return this.tblppksimatis;
	}

	public void setTblppksimatis(Set tblppksimatis) {
		this.tblppksimatis = tblppksimatis;
	}

	public Set getTblppkrayuans() {
		return this.tblppkrayuans;
	}

	public void setTblppkrayuans(Set tblppkrayuans) {
		this.tblppkrayuans = tblppkrayuans;
	}

	public Set getTblppkkeputusanpermohonans() {
		return this.tblppkkeputusanpermohonans;
	}

	public void setTblppkkeputusanpermohonans(Set tblppkkeputusanpermohonans) {
		this.tblppkkeputusanpermohonans = tblppkkeputusanpermohonans;
	}

}