package ekptg.view.ppk;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TBLINTMTKEPUTUSAN")
public class MTKeputusan {

	@Id
	@Column(name = "IDKEPUTUSAN")
	private String id;
	@Column(name = "KEPUTUSANBORANGC")
	private String kepBrgC;

	@Column(name = "TARIKHJANABORANGC")
	@Temporal(TemporalType.DATE)
	private Date tarikhJanaBrgC;

	@Column(name = "IDKADBIRU")
	private String idKadBiru;
	@Column(name = "JENISTRANSAKSI")
	private String jenisTransaksi;

	@Column(name = "TARIKHPROSES")
	@Temporal(TemporalType.DATE)
	private Date tarikhProses;

	@Column(name = "BORANGCEXTRACTKOD")
	private String brgCExtractKod;
	@Column(name = "FLAG_REP")
	private String flagRep;

	@Column(name = "TARIKH_REP")
	@Temporal(TemporalType.DATE)
	private Date tarikhRep;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKepBrgC() {
		return kepBrgC;
	}

	public void setKepBrgC(String kepBrgC) {
		this.kepBrgC = kepBrgC;
	}

	public Date getTarikhJanaBrgC() {
		return tarikhJanaBrgC;
	}

	public void setTarikhJanaBrgC(Date tarikhJanaBrgC) {
		this.tarikhJanaBrgC = tarikhJanaBrgC;
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

	public String getBrgCExtractKod() {
		return brgCExtractKod;
	}

	public void setBrgCExtractKod(String brgCExtractKod) {
		this.brgCExtractKod = brgCExtractKod;
	}

	public String getFlagRep() {
		return flagRep;
	}

	public void setFlagRep(String flagRep) {
		this.flagRep = flagRep;
	}

	public Date getTarikhRep() {
		return tarikhRep;
	}

	public void setTarikhRep(Date tarikhRep) {
		this.tarikhRep = tarikhRep;
	}
}