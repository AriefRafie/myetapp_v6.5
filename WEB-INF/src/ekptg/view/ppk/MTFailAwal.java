package ekptg.view.ppk;

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
@Table(name = "TBLINTMTBRGCFAIL")
public class MTFailAwal {

	@Id
	@Column(name = "IDBRGCFAIL")
	private String id;

	@ManyToOne
	@JoinColumn(name = "IDKEPUTUSAN")
	private MTKeputusan keputusan;

	@Column(name = "NOFAILAWAL")
	private String noFail;
	@Column(name = "NAMAPEMOHONAWAL")
	private String namaPemohonAwal;
	@Column(name = "NAMAPEJAGENSI")
	private String namaPejAgensi;

	@Column(name = "TARIKHPERMOHONANAWAL")
	@Temporal(TemporalType.DATE)
	private Date tarikhPermohonanAwal;

	@Column(name = "IDKADBIRU")
	private String idKadBiru;
	@Column(name = "JENISTRANSAKSI")
	private String jenisTransaksi;

	@Column(name = "TARIKHPROSES")
	@Temporal(TemporalType.DATE)
	private Date tarikhProses;

	@Column(name = "FLAG_REP")
	private String flag_rep;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public MTKeputusan getKeputusan() {
		return keputusan;
	}

	public void setKeputusan(MTKeputusan keputusan) {
		this.keputusan = keputusan;
	}

	public String getNoFail() {
		return noFail;
	}

	public void setNoFail(String noFail) {
		this.noFail = noFail;
	}

	public String getNamaPemohonAwal() {
		return namaPemohonAwal;
	}

	public void setNamaPemohonAwal(String namaPemohonAwal) {
		this.namaPemohonAwal = namaPemohonAwal;
	}

	public String getNamaPejAgensi() {
		return namaPejAgensi;
	}

	public void setNamaPejAgensi(String namaPejAgensi) {
		this.namaPejAgensi = namaPejAgensi;
	}

	public Date getTarikhPermohonanAwal() {
		return tarikhPermohonanAwal;
	}

	public void setTarikhPermohonanAwal(Date tarikhPermohonanAwal) {
		this.tarikhPermohonanAwal = tarikhPermohonanAwal;
	}

	public String getIdKadBiru() {
		return idKadBiru;
	}

	public void setIdKadBiru(String idKadBiru) {
		this.idKadBiru = idKadBiru;
	}

	public String getJenisTransaksi() {
		return jenisTransaksi;
	}

	public void setJenisTransaksi(String jenisTransaksi) {
		this.jenisTransaksi = jenisTransaksi;
	}

	public Date getTarikhProses() {
		return tarikhProses;
	}

	public void setTarikhProses(Date tarikhProses) {
		this.tarikhProses = tarikhProses;
	}

	public String getFlag_rep() {
		return flag_rep;
	}

	public void setFlag_rep(String flag_rep) {
		this.flag_rep = flag_rep;
	}
}