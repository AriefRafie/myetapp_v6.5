package ekptg.view.utils;
/**
 * 
 * @author Mohd Nazrul
 *
 */
public class PejabatInformation {
	private String id_pejabatjkptg;
	private String nama_pejabat;
	private String alamat1;
	private String alamat2;
	private String alamat3;
	private String poskod;
	private String id_bandar;
	private String id_negeri;
	private String nama_negeri;
	
	private String id_jenis_pejabat;
	private String id_daerah;
	private String nama_daerah;
	private String id_seksyen;
	private String nama_seksyen;

	public PejabatInformation() {
	}

	public PejabatInformation(String id_pejabatjkptg, String nama_pejabat,
			String alamat1, String alamat2, String alamat3, String poskod,
			String id_bandar, String id_negeri, String nama_negeri) {
		this.id_pejabatjkptg = id_pejabatjkptg;
		this.nama_pejabat = nama_pejabat;
		this.alamat1 = alamat1;
		this.alamat2 = alamat2;
		this.alamat3 = alamat3;
		this.poskod = poskod;
		this.id_bandar = id_bandar;
		this.id_negeri = id_negeri;
		this.nama_negeri = nama_negeri;

	}

	public PejabatInformation(String id_jenis_pejabat, String id_pejabat_jkptg,
			String nama_pejabat, String alamat1, String alamat2,
			String alamat3, String poskod, String id_negeri,
			String nama_negeri, String id_daerah, String nama_daerah,
			String id_seksyen, String nama_seksyen) {
		this.id_jenis_pejabat = id_jenis_pejabat;
		this.id_pejabatjkptg = id_pejabat_jkptg;
		this.nama_pejabat = nama_pejabat;
		this.alamat1 =alamat1;
		this.alamat2 = alamat2;
		this.alamat3 = alamat3;
		this.poskod = poskod;
		this.id_negeri = id_negeri;
		this.nama_negeri = nama_negeri;
		this.id_daerah = id_daerah;
		this.nama_daerah = nama_daerah;
		this.id_seksyen = id_seksyen;
		this.nama_seksyen = nama_seksyen;

	}

	public String getId_pejabatjkptg() {
		return id_pejabatjkptg;
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
		return poskod;
	}

	public void setPoskod(String poskod) {
		this.poskod = poskod;
	}

	public String getId_bandar() {
		return id_bandar;
	}

	public void setId_bandar(String idBandar) {
		id_bandar = idBandar;
	}

	public String getId_negeri() {
		return id_negeri;
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

	public String getId_jenis_pejabat() {
		return id_jenis_pejabat;
	}

	public void setId_jenis_pejabat(String idJenisPejabat) {
		id_jenis_pejabat = idJenisPejabat;
	}

	public String getId_daerah() {
		return id_daerah;
	}

	public void setId_daerah(String idDaerah) {
		id_daerah = idDaerah;
	}

	public String getNama_daerah() {
		return nama_daerah;
	}

	public void setNama_daerah(String namaDaerah) {
		nama_daerah = namaDaerah;
	}

	public String getId_seksyen() {
		return id_seksyen;
	}

	public void setId_seksyen(String idSeksyen) {
		id_seksyen = idSeksyen;
	}

	public String getNama_seksyen() {
		return nama_seksyen;
	}

	public void setNama_seksyen(String namaSeksyen) {
		nama_seksyen = namaSeksyen;
	}

}
