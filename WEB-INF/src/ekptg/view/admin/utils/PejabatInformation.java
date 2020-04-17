package ekptg.view.admin.utils;

public class PejabatInformation {

	private String id_pejabatjkptg;
	private String nama_pejabat;
	private String alamat1;
	private String alamat2;
	private String alamat3;
	private String poskod;
	private String no_tel;
	private String no_fax;
	private String id_daerah;
	private String nama_daerah;
	private String id_negeri;
	private String nama_negeri;
	private String id_negara;
	private String nama_negara;

	public PejabatInformation() {
	}

	public PejabatInformation(String id_pejabatjkptg, String nama_pejabat,
			String alamat1, String alamat2, String alamat3, String poskod,
			String no_tel, String no_fax, String id_daerah, String nama_daerah,
			String id_negeri, String nama_negeri, String id_negara,
			String nama_negara) {
		this.id_pejabatjkptg = id_pejabatjkptg;
		this.nama_pejabat = nama_pejabat;
		this.alamat1 = alamat1;
		this.alamat2 = alamat2;
		this.alamat3 = alamat3;
		this.poskod = poskod;
		this.no_tel = no_tel;
		this.no_fax = no_fax;
		this.id_daerah = id_daerah;
		this.nama_daerah = nama_daerah;
		this.id_negeri = id_negeri;
		this.nama_negeri = nama_negeri;
		this.id_negara = id_negara;
		this.nama_negara = nama_negara;

	}

	public String getId_pejabatjkptg() {
		return id_pejabatjkptg.toUpperCase();
	}

	public void setId_pejabatjkptg(String idPejabatjkptg) {
		id_pejabatjkptg = idPejabatjkptg;
	}

	public String getNama_pejabat() {
		return nama_pejabat.toUpperCase();
	}

	public void setNama_pejabat(String namaPejabat) {
		nama_pejabat = namaPejabat;
	}

	public String getAlamat1() {
		return alamat1.toUpperCase();
	}

	public void setAlamat1(String alamat1) {
		this.alamat1 = alamat1;
	}

	public String getAlamat2() {
		return alamat2.toUpperCase();
	}

	public void setAlamat2(String alamat2) {
		this.alamat2 = alamat2;
	}

	public String getAlamat3() {
		return alamat3.toUpperCase();
	}

	public void setAlamat3(String alamat3) {
		this.alamat3 = alamat3;
	}

	public String getPoskod() {
		return poskod.toUpperCase();
	}

	public void setPoskod(String poskod) {
		this.poskod = poskod;
	}

	public String getNo_tel() {
		return no_tel.toUpperCase();
	}

	public void setNo_tel(String noTel) {
		no_tel = noTel;
	}

	public String getNo_fax() {
		return no_fax.toUpperCase();
	}

	public void setNo_fax(String noFax) {
		no_fax = noFax;
	}

	public String getId_daerah() {
		return id_daerah.toUpperCase();
	}

	public void setId_daerah(String idDaerah) {
		id_daerah = idDaerah;
	}

	public String getNama_daerah() {
		return nama_daerah.toUpperCase();
	}

	public void setNama_daerah(String namaDaerah) {
		nama_daerah = namaDaerah;
	}

	public String getId_negeri() {
		return id_negeri.toUpperCase();
	}

	public void setId_negeri(String idNegeri) {
		id_negeri = idNegeri;
	}

	public String getNama_negeri() {
		return nama_negeri.toUpperCase();
	}

	public void setNama_negeri(String namaNegeri) {
		nama_negeri = namaNegeri;
	}

	public String getId_negara() {
		return id_negara.toUpperCase();
	}

	public void setId_negara(String idNegara) {
		id_negara = idNegara;
	}

	public String getNama_negara() {
		return nama_negara.toUpperCase();
	}

	public void setNama_negara(String namaNegara) {
		nama_negara = namaNegara;
	}

}
