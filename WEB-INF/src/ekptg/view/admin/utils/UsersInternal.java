package ekptg.view.admin.utils;

/**
 * 
 * @author Mohd Nazrul
 * @version 1.0
 * 
 */

public class UsersInternal {

	private String user_id;
	private String user_login;
	private String user_name;
	private String user_role;
	private String user_type;
	private String id_seksyen;
	private String nama_seksyen;
	private String id_negeri;
	private String nama_negeri;
	private String id_daerah;
	private String nama_daerah;
	private String id_pejabatjkptg;
	private String nama_pejabat;
	private String id_jawatan;
	private String keterangan;

	public UsersInternal() {
	}

	public UsersInternal(String user_id, String user_login, String user_name,
			String user_role, String user_type, String id_seksyen,
			String nama_seksyen, String id_negeri, String nama_negeri,
			String id_daerah, String nama_daerah, String id_pejabatjkptg,
			String nama_pejabat, String id_jawatan, String keterangan) {

		this.user_id = user_id;
		this.user_login = user_login;
		this.user_name = user_name;
		this.user_role = user_role;
		this.user_type = user_type;
		this.id_seksyen = id_seksyen;
		this.nama_seksyen = nama_seksyen;
		this.id_negeri = id_negeri;
		this.nama_negeri = nama_negeri;
		this.id_daerah = id_daerah;
		this.nama_daerah = nama_daerah;
		this.id_pejabatjkptg = id_pejabatjkptg;
		this.nama_pejabat = nama_pejabat;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_login() {
		return user_login;
	}

	public void setUser_login(String user_login) {
		this.user_login = user_login;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_role() {
		return user_role;
	}

	public void setUser_role(String user_role) {
		this.user_role = user_role;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	public String getId_seksyen() {
		return id_seksyen;
	}

	public void setId_seksyen(String id_seksyen) {
		this.id_seksyen = id_seksyen;
	}

	public String getNama_seksyen() {
		return nama_seksyen;
	}

	public void setNama_seksyen(String nama_seksyen) {
		this.nama_seksyen = nama_seksyen;
	}

	public String getId_negeri() {
		return id_negeri;
	}

	public void setId_negeri(String id_negeri) {
		this.id_negeri = id_negeri;
	}

	public String getNama_negeri() {
		return nama_negeri;
	}

	public void setNama_negeri(String nama_negeri) {
		this.nama_negeri = nama_negeri;
	}

	public String getId_daerah() {
		return id_daerah;
	}

	public void setId_daerah(String id_daerah) {
		this.id_daerah = id_daerah;
	}

	public String getNama_daerah() {
		return nama_daerah;
	}

	public void setNama_daerah(String nama_daerah) {
		this.nama_daerah = nama_daerah;
	}

	public String getId_pejabatjkptg() {
		return id_pejabatjkptg;
	}

	public void setId_pejabatjkptg(String id_pejabatjkptg) {
		this.id_pejabatjkptg = id_pejabatjkptg;
	}

	public String getNama_pejabat() {
		return nama_pejabat;
	}

	public void setNama_pejabat(String nama_pejabat) {
		this.nama_pejabat = nama_pejabat;
	}

	public String getId_jawatan() {
		return id_jawatan;
	}

	public void setId_jawatan(String id_jawatan) {
		this.id_jawatan = id_jawatan;
	}

	public String getKeterangan() {
		return keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

}
