package ekptg.view.ppk;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TBLINTMTPERMOHONAN")
public class MTPermohonan {

	@Id
	@Column(name = "PETISYENNO")
	private String id;
	@Column(name = "NAMASIMATI")
	private String namaSimati;
	@Column(name = "NAMASIMATILAIN")
	private String namaSimatiLain;
	@Column(name = "NOKPBARUSIMATI")
	private String noKPBaruSimati;
	@Column(name = "NOKPLAMASIMATI")
	private String noKPLamaSimati;
	@Column(name = "NOKPLAINSIMATI")
	private String noKPLainSimati;

	@Column(name = "TARIKHMATI")
	@Temporal(TemporalType.DATE)
	private Date tarikhMati;

	@Column(name = "NAMAPEMOHON")
	private String namaPemohon;

	@Column(name = "TARIKHJANABRGB")
	@Temporal(TemporalType.DATE)
	private Date tarikhJanaBrgB;

	@Column(name = "NOKPBARUPEMOHON")
	private String noKPBaruPemohon;
	@Column(name = "HUBUNGAN")
	private String hubungan;

	@Column(name = "TARIKHPERINTAH")
	@Temporal(TemporalType.DATE)
	private Date tarikhPerintah;

	@Column(name = "NAMAWARIS")
	private String namaWaris;
	@Column(name = "KODPEJABAT")
	private String kodPejabart;
	@Column(name = "JENISTRANSAKSI")
	private String jenisTransaksi;

	@Column(name = "TARIKHPROSESPERMOHONAN")
	@Temporal(TemporalType.DATE)
	private Date tarikhProsesPermohonan;
	@Column(name = "TARIKHPROSESPERINTAH")
	@Temporal(TemporalType.DATE)
	private Date tarikhProsesPerintah;

	@Column(name = "IDKADBIRU")
	private String idKadBiru;
	@Column(name = "FLAG_REP")
	private String flag_rep;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNamaSimati() {
		return namaSimati;
	}

	public void setNamaSimati(String namaSimati) {
		this.namaSimati = namaSimati;
	}

	public String getNamaSimatiLain() {
		return namaSimatiLain;
	}

	public void setNamaSimatiLain(String namaSimatiLain) {
		this.namaSimatiLain = namaSimatiLain;
	}

	public String getNoKPBaruSimati() {
		return noKPBaruSimati;
	}

	public void setNoKPBaruSimati(String noKPBaruSimati) {
		this.noKPBaruSimati = noKPBaruSimati;
	}

	public String getNoKPLamaSimati() {
		return noKPLamaSimati;
	}

	public void setNoKPLamaSimati(String noKPLamaSimati) {
		this.noKPLamaSimati = noKPLamaSimati;
	}

	public String getNoKPLainSimati() {
		return noKPLainSimati;
	}

	public void setNoKPLainSimati(String noKPLainSimati) {
		this.noKPLainSimati = noKPLainSimati;
	}

	public Date getTarikhMati() {
		return tarikhMati;
	}

	public void setTarikhMati(Date tarikhMati) {
		this.tarikhMati = tarikhMati;
	}

	public String getNamaPemohon() {
		return namaPemohon;
	}

	public void setNamaPemohon(String namaPemohon) {
		this.namaPemohon = namaPemohon;
	}

	public Date getTarikhJanaBrgB() {
		return tarikhJanaBrgB;
	}

	public void setTarikhJanaBrgB(Date tarikhJanaBrgB) {
		this.tarikhJanaBrgB = tarikhJanaBrgB;
	}

	public String getNoKPBaruPemohon() {
		return noKPBaruPemohon;
	}

	public void setNoKPBaruPemohon(String noKPBaruPemohon) {
		this.noKPBaruPemohon = noKPBaruPemohon;
	}

	public String getHubungan() {
		return hubungan;
	}

	public void setHubungan(String hubungan) {
		this.hubungan = hubungan;
	}

	public Date getTarikhPerintah() {
		return tarikhPerintah;
	}

	public void setTarikhPerintah(Date tarikhPerintah) {
		this.tarikhPerintah = tarikhPerintah;
	}

	public String getNamaWaris() {
		return namaWaris;
	}

	public void setNamaWaris(String namaWaris) {
		this.namaWaris = namaWaris;
	}

	public String getKodPejabart() {
		return kodPejabart;
	}

	public void setKodPejabart(String kodPejabart) {
		this.kodPejabart = kodPejabart;
	}

	public String getJenisTransaksi() {
		return jenisTransaksi;
	}

	public void setJenisTransaksi(String jenisTransaksi) {
		this.jenisTransaksi = jenisTransaksi;
	}

	public Date getTarikhProsesPermohonan() {
		return tarikhProsesPermohonan;
	}

	public void setTarikhProsesPermohonan(Date tarikhProsesPermohonan) {
		this.tarikhProsesPermohonan = tarikhProsesPermohonan;
	}

	public Date getTarikhProsesPerintah() {
		return tarikhProsesPerintah;
	}

	public void setTarikhProsesPerintah(Date tarikhProsesPerintah) {
		this.tarikhProsesPerintah = tarikhProsesPerintah;
	}

	public String getIdKadBiru() {
		return idKadBiru;
	}

	public void setIdKadBiru(String idKadBiru) {
		this.idKadBiru = idKadBiru;
	}

	public String getFlag_rep() {
		return flag_rep;
	}

	public void setFlag_rep(String flag_rep) {
		this.flag_rep = flag_rep;
	}
}