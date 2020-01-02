package ekptg.view.admin.utils;

public class UsersKJP {
	
	private String user_id;
	private String user_login;
	private String user_name;
	private String id_agensi;
	private String id_kementerian;
	private String nama_kementerian;
	private String nama_agensi;
	private String nama_jawatan;
		
	public String getUser_type() {
		return "KJP";
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
	public String getId_agensi() {
		return id_agensi;
	}
	public void setId_agensi(String id_agensi) {
		this.id_agensi = id_agensi;
	}
	public String getId_kementerian() {
		return id_kementerian;
	}
	public void setId_kementerian(String id_kementerian) {
		this.id_kementerian = id_kementerian;
	}
	public String getNama_kementerian() {
		return nama_kementerian;
	}
	public void setNama_kementerian(String nama_kementerian) {
		this.nama_kementerian = nama_kementerian;
	}
	public String getNama_agensi() {
		return nama_agensi;
	}
	public void setNama_agensi(String nama_agensi) {
		this.nama_agensi = nama_agensi;
	}
	public String getNama_jawatan() {
		return nama_jawatan;
	}
	public void setNama_jawatan(String nama_jawatan) {
		this.nama_jawatan = nama_jawatan;
	}
	
}
