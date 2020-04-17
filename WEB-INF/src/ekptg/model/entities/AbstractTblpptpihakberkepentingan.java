package ekptg.model.entities;

import java.util.Date;

/**
 * AbstractTblpptpihakberkepentingan entity provides the base persistence
 * definition of the Tblpptpihakberkepentingan entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractTblpptpihakberkepentingan implements
		java.io.Serializable {

	// Fields

	private Long idPihakberkepentingan;
	private Long idJenispb;
	private String noKpLama;
	private Long idJenisnopb;
	private String noPb;
	private Long jantina;
	private Long idBangsa;
	private Long idWarganegara;
	private String namaPb;
	private String namaKp;
	private String flagHidup;
	private String alamat1;
	private String alamat2;
	private String alamat3;
	private String poskod;
	private Long idNegeri;
	private String noTelRumah;
	private String noHp;
	private String noFax;
	private Long syerAtas;
	private Long syerBawah;
	private String flagBayarAward;
	private String flagBantahan;
	private String flagMahkamah;
	private String flagPermintaanUkur;
	private Double jumlahAward;
	private String flagBorangj;
	private String flagBayarPu;
	private String flagPembayaranAward;
	private String flagBayarBantahan;
	private String noAkaun;
	private Long idBank;
	private String jumlahAwardPu;
	private Double jumlahAwardBantahan;
	private Double jumlahFaedah;
	private String jenisLain2Pb;
	private String flagJenisDaftarpb;
	private Long idHakmilik;
	private Long idMasuk;
	private Date tarikhMasuk;
	private Long idKemaskini;
	private Date tarikhKemaskini;
	private Long idDb;

	// Constructors

	/** default constructor */
	public AbstractTblpptpihakberkepentingan() {
	}

	/** full constructor */
	public AbstractTblpptpihakberkepentingan(Long idJenispb, String noKpLama,
			Long idJenisnopb, String noPb, Long jantina, Long idBangsa,
			Long idWarganegara, String namaPb, String namaKp, String flagHidup,
			String alamat1, String alamat2, String alamat3, String poskod,
			Long idNegeri, String noTelRumah, String noHp, String noFax,
			Long syerAtas, Long syerBawah, String flagBayarAward,
			String flagBantahan, String flagMahkamah,
			String flagPermintaanUkur, Double jumlahAward, String flagBorangj,
			String flagBayarPu, String flagPembayaranAward,
			String flagBayarBantahan, String noAkaun, Long idBank,
			String jumlahAwardPu, Double jumlahAwardBantahan,
			Double jumlahFaedah, String jenisLain2Pb, String flagJenisDaftarpb,
			Long idHakmilik, Long idMasuk, Date tarikhMasuk, Long idKemaskini,
			Date tarikhKemaskini, Long idDb) {
		this.idJenispb = idJenispb;
		this.noKpLama = noKpLama;
		this.idJenisnopb = idJenisnopb;
		this.noPb = noPb;
		this.jantina = jantina;
		this.idBangsa = idBangsa;
		this.idWarganegara = idWarganegara;
		this.namaPb = namaPb;
		this.namaKp = namaKp;
		this.flagHidup = flagHidup;
		this.alamat1 = alamat1;
		this.alamat2 = alamat2;
		this.alamat3 = alamat3;
		this.poskod = poskod;
		this.idNegeri = idNegeri;
		this.noTelRumah = noTelRumah;
		this.noHp = noHp;
		this.noFax = noFax;
		this.syerAtas = syerAtas;
		this.syerBawah = syerBawah;
		this.flagBayarAward = flagBayarAward;
		this.flagBantahan = flagBantahan;
		this.flagMahkamah = flagMahkamah;
		this.flagPermintaanUkur = flagPermintaanUkur;
		this.jumlahAward = jumlahAward;
		this.flagBorangj = flagBorangj;
		this.flagBayarPu = flagBayarPu;
		this.flagPembayaranAward = flagPembayaranAward;
		this.flagBayarBantahan = flagBayarBantahan;
		this.noAkaun = noAkaun;
		this.idBank = idBank;
		this.jumlahAwardPu = jumlahAwardPu;
		this.jumlahAwardBantahan = jumlahAwardBantahan;
		this.jumlahFaedah = jumlahFaedah;
		this.jenisLain2Pb = jenisLain2Pb;
		this.flagJenisDaftarpb = flagJenisDaftarpb;
		this.idHakmilik = idHakmilik;
		this.idMasuk = idMasuk;
		this.tarikhMasuk = tarikhMasuk;
		this.idKemaskini = idKemaskini;
		this.tarikhKemaskini = tarikhKemaskini;
		this.idDb = idDb;
	}

	// Property accessors

	public Long getIdPihakberkepentingan() {
		return this.idPihakberkepentingan;
	}

	public void setIdPihakberkepentingan(Long idPihakberkepentingan) {
		this.idPihakberkepentingan = idPihakberkepentingan;
	}

	public Long getIdJenispb() {
		return this.idJenispb;
	}

	public void setIdJenispb(Long idJenispb) {
		this.idJenispb = idJenispb;
	}

	public String getNoKpLama() {
		return this.noKpLama;
	}

	public void setNoKpLama(String noKpLama) {
		this.noKpLama = noKpLama;
	}

	public Long getIdJenisnopb() {
		return this.idJenisnopb;
	}

	public void setIdJenisnopb(Long idJenisnopb) {
		this.idJenisnopb = idJenisnopb;
	}

	public String getNoPb() {
		return this.noPb;
	}

	public void setNoPb(String noPb) {
		this.noPb = noPb;
	}

	public Long getJantina() {
		return this.jantina;
	}

	public void setJantina(Long jantina) {
		this.jantina = jantina;
	}

	public Long getIdBangsa() {
		return this.idBangsa;
	}

	public void setIdBangsa(Long idBangsa) {
		this.idBangsa = idBangsa;
	}

	public Long getIdWarganegara() {
		return this.idWarganegara;
	}

	public void setIdWarganegara(Long idWarganegara) {
		this.idWarganegara = idWarganegara;
	}

	public String getNamaPb() {
		return this.namaPb;
	}

	public void setNamaPb(String namaPb) {
		this.namaPb = namaPb;
	}

	public String getNamaKp() {
		return this.namaKp;
	}

	public void setNamaKp(String namaKp) {
		this.namaKp = namaKp;
	}

	public String getFlagHidup() {
		return this.flagHidup;
	}

	public void setFlagHidup(String flagHidup) {
		this.flagHidup = flagHidup;
	}

	public String getAlamat1() {
		return this.alamat1;
	}

	public void setAlamat1(String alamat1) {
		this.alamat1 = alamat1;
	}

	public String getAlamat2() {
		return this.alamat2;
	}

	public void setAlamat2(String alamat2) {
		this.alamat2 = alamat2;
	}

	public String getAlamat3() {
		return this.alamat3;
	}

	public void setAlamat3(String alamat3) {
		this.alamat3 = alamat3;
	}

	public String getPoskod() {
		return this.poskod;
	}

	public void setPoskod(String poskod) {
		this.poskod = poskod;
	}

	public Long getIdNegeri() {
		return this.idNegeri;
	}

	public void setIdNegeri(Long idNegeri) {
		this.idNegeri = idNegeri;
	}

	public String getNoTelRumah() {
		return this.noTelRumah;
	}

	public void setNoTelRumah(String noTelRumah) {
		this.noTelRumah = noTelRumah;
	}

	public String getNoHp() {
		return this.noHp;
	}

	public void setNoHp(String noHp) {
		this.noHp = noHp;
	}

	public String getNoFax() {
		return this.noFax;
	}

	public void setNoFax(String noFax) {
		this.noFax = noFax;
	}

	public Long getSyerAtas() {
		return this.syerAtas;
	}

	public void setSyerAtas(Long syerAtas) {
		this.syerAtas = syerAtas;
	}

	public Long getSyerBawah() {
		return this.syerBawah;
	}

	public void setSyerBawah(Long syerBawah) {
		this.syerBawah = syerBawah;
	}

	public String getFlagBayarAward() {
		return this.flagBayarAward;
	}

	public void setFlagBayarAward(String flagBayarAward) {
		this.flagBayarAward = flagBayarAward;
	}

	public String getFlagBantahan() {
		return this.flagBantahan;
	}

	public void setFlagBantahan(String flagBantahan) {
		this.flagBantahan = flagBantahan;
	}

	public String getFlagMahkamah() {
		return this.flagMahkamah;
	}

	public void setFlagMahkamah(String flagMahkamah) {
		this.flagMahkamah = flagMahkamah;
	}

	public String getFlagPermintaanUkur() {
		return this.flagPermintaanUkur;
	}

	public void setFlagPermintaanUkur(String flagPermintaanUkur) {
		this.flagPermintaanUkur = flagPermintaanUkur;
	}

	public Double getJumlahAward() {
		return this.jumlahAward;
	}

	public void setJumlahAward(Double jumlahAward) {
		this.jumlahAward = jumlahAward;
	}

	public String getFlagBorangj() {
		return this.flagBorangj;
	}

	public void setFlagBorangj(String flagBorangj) {
		this.flagBorangj = flagBorangj;
	}

	public String getFlagBayarPu() {
		return this.flagBayarPu;
	}

	public void setFlagBayarPu(String flagBayarPu) {
		this.flagBayarPu = flagBayarPu;
	}

	public String getFlagPembayaranAward() {
		return this.flagPembayaranAward;
	}

	public void setFlagPembayaranAward(String flagPembayaranAward) {
		this.flagPembayaranAward = flagPembayaranAward;
	}

	public String getFlagBayarBantahan() {
		return this.flagBayarBantahan;
	}

	public void setFlagBayarBantahan(String flagBayarBantahan) {
		this.flagBayarBantahan = flagBayarBantahan;
	}

	public String getNoAkaun() {
		return this.noAkaun;
	}

	public void setNoAkaun(String noAkaun) {
		this.noAkaun = noAkaun;
	}

	public Long getIdBank() {
		return this.idBank;
	}

	public void setIdBank(Long idBank) {
		this.idBank = idBank;
	}

	public String getJumlahAwardPu() {
		return this.jumlahAwardPu;
	}

	public void setJumlahAwardPu(String jumlahAwardPu) {
		this.jumlahAwardPu = jumlahAwardPu;
	}

	public Double getJumlahAwardBantahan() {
		return this.jumlahAwardBantahan;
	}

	public void setJumlahAwardBantahan(Double jumlahAwardBantahan) {
		this.jumlahAwardBantahan = jumlahAwardBantahan;
	}

	public Double getJumlahFaedah() {
		return this.jumlahFaedah;
	}

	public void setJumlahFaedah(Double jumlahFaedah) {
		this.jumlahFaedah = jumlahFaedah;
	}

	public String getJenisLain2Pb() {
		return this.jenisLain2Pb;
	}

	public void setJenisLain2Pb(String jenisLain2Pb) {
		this.jenisLain2Pb = jenisLain2Pb;
	}

	public String getFlagJenisDaftarpb() {
		return this.flagJenisDaftarpb;
	}

	public void setFlagJenisDaftarpb(String flagJenisDaftarpb) {
		this.flagJenisDaftarpb = flagJenisDaftarpb;
	}

	public Long getIdHakmilik() {
		return this.idHakmilik;
	}

	public void setIdHakmilik(Long idHakmilik) {
		this.idHakmilik = idHakmilik;
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

	public Long getIdDb() {
		return this.idDb;
	}

	public void setIdDb(Long idDb) {
		this.idDb = idDb;
	}

}