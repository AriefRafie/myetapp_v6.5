package ekptg.view.admin.utils;

/**
 * 
 * @author Mohd Nazrul
 * @version 1.0
 * 
 */

public class UsersOnline {

	private String user_id;
	private String user_login;
	private String user_name;
	private String user_role;
	private String user_type;
	private String id_negeri;
	private String nama_negeri;

	public UsersOnline() {
	}

	public UsersOnline(String user_id, String user_login, String user_name,
			String user_role, String user_type, String id_negeri,
			String nama_negeri) {

		this.user_id = user_id;
		this.user_login = user_login;
		this.user_name = user_name;
		this.user_role = user_role;
		this.user_type = user_type;
		this.id_negeri = id_negeri;
		this.nama_negeri = nama_negeri;

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

}
