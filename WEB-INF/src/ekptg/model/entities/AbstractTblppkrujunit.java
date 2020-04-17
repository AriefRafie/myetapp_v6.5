package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblppkrujunit entity provides the base persistence definition of the
 * Tblppkrujunit entity.
 * 
 * @author by Elly (080709)
 */

public abstract class AbstractTblppkrujunit implements java.io.Serializable {

	// Fields

	private Long idunitpk;
	private String kod;
	private Long idNegeri;
	private Long idJkptg;
	private String namapejabat;
	private String keteranganunitpsk;
	private String namapegawai;
	private String jawatan;
	private String statuspeg;
	private String alamat1;
	private String alamat2;
	private String alamat3;
	private String poskod;
	private String notel;
	private String notelsambungan;
	private String catatan;
	private Long idMasuk;	
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Long idDB;

	// Constructors

	/** default constructor */
	public AbstractTblppkrujunit() {
	}

	/** full constructor */
	public AbstractTblppkrujunit(String kod,Long idNegeri,Long idJkptg,String namapejabat,String keteranganunitpsk,String namapegawai,String jawatan,String statuspeg,
	String alamat1,String alamat2,String alamat3,String poskod,String notel,String notelsambungan,String catatan,Long idMasuk,Date tarikhMasuk,Long idKemaskini,
	Long idDB) {
		this.kod = kod;
		this.idNegeri = idNegeri;
		this.idJkptg = idJkptg;
		this.namapejabat = namapejabat;
		this.keteranganunitpsk = keteranganunitpsk;
		this.namapegawai = namapegawai;
		this.jawatan = jawatan;
		this.statuspeg = statuspeg;
		this.alamat1 = alamat1;
		this.alamat2 = alamat2;
		this.alamat3 = alamat3;
		this.poskod = poskod;
		this.notel = notel;
		this.notelsambungan = notelsambungan;
		this.catatan = catatan;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.idDB = idDB;
	}

	// Property accessors

	public Long getidunitpk() {
		return this.idunitpk;
	}
	
	public void setidunitpk(Long idunitpk) {
		this.idunitpk = idunitpk;
	}
	
	public String getkod() {
		return this.kod;
	}
	
	public void setkod(String kod) {
		this.kod = kod;
	}
	
	public Long getidNegeri() {
		return this.idNegeri;
	}
	
	public void setidNegeri(Long idNegeri) {
		this.idNegeri = idNegeri;
	}

	public Long getidJkptg() {
		return this.idJkptg;
	}
	
	public void setidJkptg(Long idJkptg) {
		this.idJkptg = idJkptg;
	}
	
	public String getnamapejabat() {
		return this.namapejabat;
	}
	
	public void setnamapejabat(String namapejabat) {
		this.namapejabat = namapejabat;
	}
	
	public String getketeranganunitpsk() {
		return this.keteranganunitpsk;
	}

	public void setketeranganunitpsk(String keteranganunitpsk) {
		this.keteranganunitpsk = keteranganunitpsk;
	}
	
	public String getnamapegawai() {
		return this.namapegawai;
	}

	public void setnamapegawai(String namapegawai) {
		this.namapegawai = namapegawai;
	}
	
	public String getjawatan() {
		return this.jawatan;
	}
	
	public void setjawatan(String jawatan) {
		this.jawatan = jawatan;
	}

	public String getstatuspeg() {
		return this.statuspeg;
	}
	
	public void setstatuspeg(String statuspeg) {
		this.statuspeg = statuspeg;
	}

	public String getalamat1() {
		return this.alamat1;
	}
	
	public void setalamat1(String alamat1) {
		this.alamat1 = alamat1;
	}
	
	public String getalamat2() {
		return this.alamat2;
	}
	
	public void setalamat2(String alamat2) {
		this.alamat2 = alamat2;
	}
	
	public String getalamat3() {
		return this.alamat3;
	}
	
	public void setalamat3(String alamat3) {
		this.alamat3 = alamat3;
	}
	
	public String getposkod() {
		return this.poskod;
	}
	
	public void setposkod(String poskod) {
		this.poskod = poskod;
	}
	
	public String getnotel() {
		return this.notel;
	}
	
	public void setnotel(String notel) {
		this.notel = notel;
	}
	
	public String getnotelsambungan() {
		return this.notelsambungan;
	}
	
	public void setnotelsambungan(String notelsambungan) {
		this.notelsambungan = notelsambungan;
	}
	
	public String getcatatan() {
		return this.catatan;
	}
	
	public void setcatatan(String catatan) {
		this.catatan = catatan;
	}	
	
	public Long getIdMasuk() {
		return this.idMasuk;
	}
	
	public void setidMasuk(Long idMasuk) {
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
	
	public void setidKemaskini(Long idKemaskini) {
		this.idKemaskini = idKemaskini;
	}
	
	public Long getidDB() {
		return this.idDB;
	}
	
	public void setidDB(Long idDB) {
		this.idDB = idDB;
	}

}