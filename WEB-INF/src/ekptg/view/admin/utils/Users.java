package ekptg.view.admin.utils;

/**
 * <b> Users.java </b<
 * 
 * @author Mohd Nazrul
 * @version 1.0
 * 
 */

public class Users {

	private String user_id;
	private String user_login;
	private String user_password;
	private String user_name;
	private String user_role;
	private String date_registered;
	private String user_type;
	private String id_masuk;
	private String tarikh_masuk;
	private String id_kesmakini;
	private String tarikh_kemaskini;

	public Users() {
	}

	public Users(String userId, String user_login, String user_password,
			String user_name, String user_role, String date_registered,
			String user_type, String id_masuk, String tarikh_masuk,
			String id_kemaskini, String tarikh_kemaskini) {
		
		this.user_id = userId; 
		this.user_login = user_login;
		this.user_password = user_password;
		this.user_name = user_name;
		this.user_role = user_role;
		this.date_registered = date_registered;
		this.user_type = user_type;
		this.id_masuk = id_masuk;
		this.tarikh_masuk = tarikh_masuk;
		this.id_kesmakini = id_kemaskini;
		this.tarikh_kemaskini = tarikh_kemaskini;

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

	public String getDate_registered() {
		return date_registered;
	}

	public void setDate_registered(String date_registered) {
		this.date_registered = date_registered;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	public String getId_masuk() {
		return id_masuk;
	}

	public void setId_masuk(String id_masuk) {
		this.id_masuk = id_masuk;
	}

	public String getTarikh_masuk() {
		return tarikh_masuk;
	}

	public void setTarikh_masuk(String tarikh_masuk) {
		this.tarikh_masuk = tarikh_masuk;
	}

	public String getId_kesmakini() {
		return id_kesmakini;
	}

	public void setId_kesmakini(String id_kesmakini) {
		this.id_kesmakini = id_kesmakini;
	}

	public String getKemaskini() {
		return tarikh_kemaskini;
	}

	public void setTarikh_kemaskini(String tarikh_kemaskini) {
		this.tarikh_kemaskini = tarikh_kemaskini;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

}
