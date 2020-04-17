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
@Table(name = "TBLINTMTBRGCKAVEAT")
public class MTKaveat {

	@Id
	@Column(name = "IDBRGCADAKAVEAT")
	private String id;

	@ManyToOne
	@JoinColumn(name = "IDKEPUTUSAN")
	private MTKeputusan keputusan;

	@Column(name = "NOKAVEAT")
	private String noKaveat;

	@Column(name = "TARIKHKAVEAT")
	@Temporal(TemporalType.DATE)
	private Date tarikhKaveat;

	@Column(name = "NAMAPENGKAVEAT")
	private String namaPengkaveat;
	@Column(name = "NAMAFIRMA")
	private String namaFirma;
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

	public String getNoKaveat() {
		return noKaveat;
	}

	public void setNoKaveat(String noKaveat) {
		this.noKaveat = noKaveat;
	}

	public Date getTarikhKaveat() {
		return tarikhKaveat;
	}

	public void setTarikhKaveat(Date tarikhKaveat) {
		this.tarikhKaveat = tarikhKaveat;
	}

	public String getNamaPengkaveat() {
		return namaPengkaveat;
	}

	public void setNamaPengkaveat(String namaPengkaveat) {
		this.namaPengkaveat = namaPengkaveat;
	}

	public String getNamaFirma() {
		return namaFirma;
	}

	public void setNamaFirma(String namaFirma) {
		this.namaFirma = namaFirma;
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