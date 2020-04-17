package etapp.entity.htp.pajakan;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import etapp.entity.Permohonan;

@Entity
@Table(name = "TBLHTPULASANSPHP")
public class UlasanSPHP{
	@Id @Column(name="ID_ULASANSPHP")
	private long id;
	@ManyToOne @JoinColumn(name="ID_PERMOHONAN")
	private Permohonan permohonan;

	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_HANTAR")
	private Date tarikhHantar;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_TERIMA")
	private Date tarikhTerima;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_SURAT_KEPUTUSAN")
	private Date tarikhSuratKeputusan;	
	@Column(name="NO_RUJUKAN")
	private String noRujukan;
	@Column(name="ULASAN")
	private String ulasan;
	@Column(name="STATUS_KEPUTUSAN")
	private String status;
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
	public Permohonan getPermohonan() {
		return permohonan;
	}
	public void setPermohonan(Permohonan permohonan) {
		this.permohonan = permohonan;
	}
	public Date getTarikhHantar() {
		return tarikhHantar;
	}
	public void setTarikhHantar(Date tarikhHantar) {
		this.tarikhHantar = tarikhHantar;
	}
	public Date getTarikhTerima() {
		return tarikhTerima;
	}
	public void setTarikhTerima(Date tarikhTerima) {
		this.tarikhTerima = tarikhTerima;
	}
	public Date getTarikhSuratKeputusan() {
		return tarikhSuratKeputusan;
	}
	public void setTarikhSuratKeputusan(Date tarikhSuratKeputusan) {
		this.tarikhSuratKeputusan = tarikhSuratKeputusan;
	}
	public String getNoRujukan() {
		return noRujukan;
	}
	public void setNoRujukan(String noRujukan) {
		this.noRujukan = noRujukan;
	}
	public String getUlasan() {
		return ulasan;
	}
	public void setUlasan(String ulasan) {
		this.ulasan = ulasan;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
