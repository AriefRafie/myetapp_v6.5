package etapp.entity.htp.cukai;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import etapp.entity.Permohonan;
import etapp.entity.rujukan.Negeri;

@Entity
@Table(name = "TBLHTPPERINGKATBAYARAN")
public class PeringkatBayaran {
	@Id @Column(name="ID_PERINGKATBAYARAN")
	private long id;
	@ManyToOne @JoinColumn(name="ID_PERMOHONAN")
	private Permohonan permohonan;
	@ManyToOne @JoinColumn(name="ID_NEGERI")
	private Negeri negeri;
//	@ManyToOne @JoinColumn(name="ID_DAERAH")
//	private Daerah daerah;
//	@ManyToOne @JoinColumn(name="ID_MUKIM")
//	private Mukim mukim;
//	@ManyToOne @JoinColumn(name="ID_PEGAWAI")
//	private Pegawai pegawai;
	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="peringkatBayaran")
	private List<CukaiUtama> listCukaiUtama;
	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="peringkatBayaran")
	private List<Baucer> listBaucer;

	@Column(name="PERINGKAT_BAYARAN")
	private String peringkatBayaran;
	@Column(name="TAHUN_CUKAI")
	private String tahun;
	@Column(name="ID_MASUK")
	private Long idMasuk;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_MASUK")
	private Date tarikhMasuk;
	@Column(name="ID_KEMASKINI")
	private Long idKemaskini;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="TARIKH_KEMASKINI")
	private Date tarikhKemaskini;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Negeri getNegeri() {
		return negeri;
	}
	public void setNegeri(Negeri negeri) {
		this.negeri = negeri;
	}
	public String getPeringkatBayaran() {
		return peringkatBayaran;
	}
	public void setPeringkatBayaran(String peringkatBayaran) {
		this.peringkatBayaran = peringkatBayaran;
	}
	public String getTahun() {
		return tahun;
	}
	public void setTahun(String tahun) {
		this.tahun = tahun;
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
	public Permohonan getPermohonan() {
		return permohonan;
	}
	public void setPermohonan(Permohonan permohonan) {
		this.permohonan = permohonan;
	}
	public List<CukaiUtama> getListCukaiUtama() {
		return listCukaiUtama;
	}
	public void setListCukaiUtama(List<CukaiUtama> listCukaiUtama) {
		this.listCukaiUtama = listCukaiUtama;
	}
	public List<Baucer> getListBaucer() {
		return listBaucer;
	}
	public void setListBaucer(List<Baucer> listBaucer) {
		this.listBaucer = listBaucer;
	}

	//@Transient /** tak perlu ada column di DB*/
	//private String tempohTanah;

}
