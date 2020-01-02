package ekptg.view.admin.utils;


public class UsersOnlineDetail {

	// A.USER_ID, A.USER_LOGIN, A.USER_NAME, A.USER_ROLE, A.USER_TYPE,
	// B.ALAMAT1, B.ALAMAT2, " +
	// "B.ALAMAT3, B.ID_NEGERI, C.NAMA_NEGERI , B.POSKOD, B.EMEL, B.NO_HP, B.NO_TEL, B.NO_FAX, B.NO_KP_BARU, "
	// +
	// "B.UMUR, B.JANTINA, B.TARAF_PERKAHWINAN, B.TARIKH_LAHIR

	private String user_id;
	private String user_login;
	private String password;
	private String user_name;
	private String user_role;
	private String user_type;
	private String alamat1;
	private String alamat2;
	private String alamat3;
	private String id_negeri;
	private String nama_negeri;
	private String poskod;
	private String emel;
	private String no_hp;
	private String no_tel;
	private String no_fax;
	private String no_kp_baru;
	private String umur;
	private String jantina;
	private String taraf_perkahwinan;
	private String jantina_id;
	private String taraf_perkahwinan_id;
	private String tarikh_lahir;
	private String kp1;
	private String kp2;
	private String kp3;

	public UsersOnlineDetail() {
	}

	public UsersOnlineDetail(String user_id, String user_login, String password,
			String user_name, String user_role, String user_type,
			String alamat1, String alamat2, String alamat3, String id_negeri,
			String nama_negeri, String poskod, String emel, String no_hp,
			String no_tel, String no_fax, String no_kp_baru, String umur,
			String jantina, String taraf_perkahwinan, String tarikh_lahir) {

		this.user_id = user_id;
		this.user_login = user_login;
		this.password = password;
		this.user_name = user_name;
		this.user_role = user_role;
		this.user_type = user_type;
		this.alamat1 = alamat1;
		this.alamat2 = alamat2;
		this.alamat3 = alamat3;
		this.id_negeri = id_negeri;
		this.nama_negeri = nama_negeri;
		this.poskod = poskod;
		this.emel = emel;
		this.no_hp = no_hp;
		this.no_tel = no_tel;
		this.no_fax = no_fax;
		this.no_kp_baru = no_kp_baru;
		this.umur = umur;
		this.tarikh_lahir = tarikh_lahir;
		
		if(jantina.equals("L")){ this.jantina = "LELAKI"; this.jantina_id = jantina; }
		else if (jantina.equals("P")){ this.jantina = "PEREMPUAN"; this.jantina_id = jantina; }
		else this.jantina = this.jantina_id = "";
		
		if(taraf_perkahwinan.equals("B")){ this.taraf_perkahwinan = "BUJANG"; this.taraf_perkahwinan_id = taraf_perkahwinan; }
		else if(taraf_perkahwinan.equals("K")){ this.taraf_perkahwinan = "KAHWIN"; this.taraf_perkahwinan_id = taraf_perkahwinan; }
		else this.taraf_perkahwinan = this.taraf_perkahwinan_id = "";
		
		this.kp1 = no_kp_baru == null ? "" : no_kp_baru.substring(0, 6);
		this.kp2 = no_kp_baru == null ? "" : no_kp_baru.substring(6, 8);
		this.kp3 = no_kp_baru == null ? "" : no_kp_baru.substring(8, 12);

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
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUser_name() {
		return user_name.toUpperCase();
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_role() {
		return user_role.toUpperCase();
	}

	public void setUser_role(String user_role) {
		this.user_role = user_role;
	}

	public String getUser_type() {
		return user_type.toUpperCase();
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
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

	public String getId_negeri() {
		return id_negeri;
	}

	public void setId_negeri(String id_negeri) {
		this.id_negeri = id_negeri;
	}

	public String getNama_negeri() {
		return nama_negeri.toUpperCase();
	}

	public void setNama_negeri(String nama_negeri) {
		this.nama_negeri = nama_negeri;
	}

	public String getPoskod() {
		return poskod;
	}

	public void setPoskod(String poskod) {
		this.poskod = poskod;
	}

	public String getEmel() {
		return emel;
	}

	public void setEmel(String emel) {
		this.emel = emel;
	}

	public String getNo_hp() {
		return no_hp;
	}

	public void setNo_hp(String no_hp) {
		this.no_hp = no_hp;
	}

	public String getNo_fax() {
		return no_fax;
	}

	public void setNo_fax(String no_fax) {
		this.no_fax = no_fax;
	}

	public String getNo_kp_baru() {
		return no_kp_baru;
	}

	public void setNo_kp_baru(String no_kp_baru) {
		this.no_kp_baru = no_kp_baru;
	}

	public String getUmur() {
		return umur;
	}

	public void setUmur(String umur) {
		this.umur = umur;
	}

	public String getJantina() {
		return jantina.toUpperCase();
	}

	public void setJantina(String jantina) {
		this.jantina = jantina;
	}

	public String getTaraf_perkahwinan() {
		return taraf_perkahwinan.toUpperCase();
	}

	public void setTaraf_perkahwinan(String taraf_perkahwinan) {
		this.taraf_perkahwinan = taraf_perkahwinan;
	}

	public String getTarikh_lahir() throws Exception {
		return tarikh_lahir;
	}

	public void setTarikh_lahir(String tarikh_lahir) {
		this.tarikh_lahir = tarikh_lahir;
	}

	public String getNo_tel() {
		return no_tel;
	}

	public void setNo_tel(String no_tel) {
		this.no_tel = no_tel;
	}

	public String getKp1() {
		return kp1;
	}

	public void setKp1(String kp1) {
		this.kp1 = kp1;
	}

	public String getKp2() {
		return kp2;
	}

	public void setKp2(String kp2) {
		this.kp2 = kp2;
	}

	public String getKp3() {
		return kp3;
	}

	public void setKp3(String kp3) {
		this.kp3 = kp3;
	}

	public String getJantina_id() {
		return jantina_id;
	}

	public void setJantina_id(String jantinaId) {
		jantina_id = jantinaId;
	}

	public String getTaraf_perkahwinan_id() {
		return taraf_perkahwinan_id;
	}

	public void setTaraf_perkahwinan_id(String tarafPerkahwinanId) {
		taraf_perkahwinan_id = tarafPerkahwinanId;
	}
	
	
	
	

}
