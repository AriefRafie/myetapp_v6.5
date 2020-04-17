package ekptg.view.admin.utils;

/**
 * 
 * @author Mohd Nazrul
 * @version 1.0
 *
 */

public class UsersInternalDetail {

	private String user_id;
	private String user_login;
	private String user_pass;
	private String user_name;
	private String user_role;
	private String user_type;
	private String id_seksyen;
	private String kod_skesyen;
	private String nama_seksyen;
	private String id_negeri;
	private String kod_negeri;
	private String nama_negeri;
	private String id_daerah;
	private String kod_daerah;
	private String nama_daerah;
	private String id_pejabat;
	private String nama_pejabat;
	private String p_alamat1;
	private String p_alamat2;
	private String p_alamat3;
	private String poskod;
	private String no_tel;
	private String no_fax;
	private String id_bandar;
	private String kod_bandar;
	private String b_keterangan;
	private String id_agama;
	private String kod_agama;
	private String a_keterangan;
	private String id_bangsa;
	private String kod_bangsa;
	private String bg_keterangan;
	private String id_jawatan;
	private String kod_jawatan;
	private String j_keterangan;
	private String css_title;
	private String css_name;
	private String emel;



	public UsersInternalDetail() {}
	
	public UsersInternalDetail(
			String user_id, String user_login, String user_pass,
			String user_name, String user_role, String user_type,
			String id_seksyen,String kod_skesyen, String nama_seksyen,
			String id_negeri, String kod_negeri ,String nama_negeri,
			String id_daerah, String kod_daerah, String nama_daerah,
			String id_pejabat, String nama_pejabat, String p_alamat1,
			String p_alamat2, String p_alamat3, String poskod,
		    String no_tel, String no_fax, String id_bandar,
			String kod_bandar, String b_keterangan, String id_agama,
			String kod_agama, String a_keterangan, String id_bangsa,
			String kod_bangsa, String bg_keterangan, String id_jawatan,
			String kod_jawatan, String j_keterangan, String css_title,
			String css_name,String emel
	){
		this.user_id = user_id;
		this.user_login = user_login;
		this.user_pass = user_pass;
		this.user_name = user_name;
		this.user_role = user_role;
		this.user_type = user_type;
		this.id_seksyen = id_seksyen;
		this.kod_skesyen = kod_skesyen;
		this.nama_seksyen = nama_seksyen;
		this.id_negeri = id_negeri;
		this.kod_negeri = kod_negeri; 
		this.nama_negeri = nama_negeri;
		this.id_daerah = id_daerah; 
		this.kod_daerah = kod_bangsa; 
		this.nama_daerah = nama_daerah;
		this.id_pejabat = id_pejabat;
		this.nama_pejabat = nama_pejabat; 
		this.p_alamat1 = p_alamat1;
		this.p_alamat2 = p_alamat2; 
		this.p_alamat3 = p_alamat3;
		this.poskod = poskod;
		this.no_tel = no_tel; 
		this.no_fax = no_fax; 
		this.id_bandar = id_bandar;
		this.kod_bandar = kod_bandar; 
		this.b_keterangan = b_keterangan; 
		this.id_agama = id_agama;
		this.kod_agama = kod_agama;
		this.a_keterangan = a_keterangan;
		this.id_bangsa = id_bangsa;
		this.kod_bangsa = kod_bangsa;
		this.bg_keterangan = bg_keterangan;
		this.id_jawatan = id_jawatan;
		this.kod_jawatan = kod_jawatan;
		this.j_keterangan = j_keterangan;
		this.css_title = css_title; 
		this.css_name = css_name;
		this.emel = emel;
	}
	
	public String getEmel() {
		return emel;
	}

	public void setEmel(String emel) {
		this.emel = emel;
	}
	
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String userId) {
		user_id = userId.toUpperCase();
	}

	public String getUser_login() {
		return user_login;
	}

	public void setUser_login(String userLogin) {
		user_login = userLogin.toUpperCase();
	}

	public String getUser_pass() {
		return user_pass;
	}

	public void setUser_pass(String userPass) {
		user_pass = userPass;
	}

	public String getUser_name() {
		return user_name.toUpperCase();
	}

	public void setUser_name(String userName) {
		user_name = userName;
	}

	public String getUser_role() {
		//return user_role.toUpperCase();
		return user_role;
	}

	public void setUser_role(String userRole) {
		user_role = userRole;
	}

	public String getUser_type() {
		return user_type.toUpperCase();
	}

	public void setUser_type(String userType) {
		user_type = userType;
	}

	public String getId_seksyen() {
		return id_seksyen.toUpperCase();
	}

	public void setId_seksyen(String idSeksyen) {
		id_seksyen = idSeksyen;
	}

	public String getKod_skesyen() {
		return kod_skesyen.toUpperCase();
	}

	public void setKod_skesyen(String kodSkesyen) {
		kod_skesyen = kodSkesyen;
	}

	public String getNama_seksyen() {
		return nama_seksyen.toUpperCase();
	}

	public void setNama_seksyen(String namaSeksyen) {
		nama_seksyen = namaSeksyen;
	}

	public String getId_negeri() {
		return id_negeri.toUpperCase();
	}

	public void setId_negeri(String idNegeri) {
		id_negeri = idNegeri;
	}

	public String getKod_negeri() {
		return kod_negeri.toUpperCase();
	}

	public void setKod_negeri(String kodNegeri) {
		kod_negeri = kodNegeri;
	}

	public String getNama_negeri() {
		return nama_negeri.toUpperCase();
	}

	public void setNama_negeri(String namaNegeri) {
		nama_negeri = namaNegeri;
	}

	public String getId_daerah() {
		return id_daerah.toUpperCase();
	}

	public void setId_daerah(String idDaerah) {
		id_daerah = idDaerah;
	}

	public String getKod_daerah() {
		return kod_daerah.toUpperCase();
	}

	public void setKod_daerah(String kodDaerah) {
		kod_daerah = kodDaerah;
	}

	public String getNama_daerah() {
		return nama_daerah.toUpperCase();
	}

	public void setNama_daerah(String namaDaerah) {
		nama_daerah = namaDaerah;
	}

	public String getId_pejabat() {
		return id_pejabat.toUpperCase();
	}

	public void setId_pejabat(String idPejabat) {
		id_pejabat = idPejabat;
	}

	public String getNama_pejabat() {
		return nama_pejabat.toUpperCase();
	}

	public void setNama_pejabat(String namaPejabat) {
		nama_pejabat = namaPejabat;
	}

	public String getP_alamat1() {
		return p_alamat1.toUpperCase();
	}

	public void setP_alamat1(String pAlamat1) {
		p_alamat1 = pAlamat1;
	}

	public String getP_alamat2() {
		return p_alamat2.toUpperCase();
	}

	public void setP_alamat2(String pAlamat2) {
		p_alamat2 = pAlamat2;
	}

	public String getP_alamat3() {
		return p_alamat3.toUpperCase();
	}

	public void setP_alamat3(String pAlamat3) {
		p_alamat3 = pAlamat3;
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

	public String getId_bandar() {
		return id_bandar.toUpperCase();
	}

	public void setId_bandar(String idBandar) {
		id_bandar = idBandar;
	}

	public String getKod_bandar() {
		return kod_bandar.toUpperCase();
	}

	public void setKod_bandar(String kodBandar) {
		kod_bandar = kodBandar;
	}

	public String getB_keterangan() {
		return b_keterangan.toUpperCase();
	}

	public void setB_keterangan(String bKeterangan) {
		b_keterangan = bKeterangan;
	}

	public String getId_agama() {
		return id_agama.toUpperCase();
	}

	public void setId_agama(String idAgama) {
		id_agama = idAgama;
	}

	public String getKod_agama() {
		return kod_agama.toUpperCase();
	}

	public void setKod_agama(String kodAgama) {
		kod_agama = kodAgama;
	}

	public String getA_keterangan() {
		return a_keterangan.toUpperCase();
	}

	public void setA_keterangan(String aKeterangan) {
		a_keterangan = aKeterangan;
	}

	public String getId_bangsa() {
		return id_bangsa.toUpperCase();
	}

	public void setId_bangsa(String idBangsa) {
		id_bangsa = idBangsa;
	}

	public String getKod_bangsa() {
		return kod_bangsa.toUpperCase();
	}

	public void setKod_bangsa(String kodBangsa) {
		kod_bangsa = kodBangsa;
	}

	public String getBg_keterangan() {
		return bg_keterangan.toUpperCase();
	}

	public void setBg_keterangan(String bgKeterangan) {
		bg_keterangan = bgKeterangan;
	}

	public String getId_jawatan() {
		return id_jawatan.toUpperCase();
	}

	public void setId_jawatan(String idJawatan) {
		id_jawatan = idJawatan;
	}

	public String getKod_jawatan() {
		return kod_jawatan.toUpperCase();
	}

	public void setKod_jawatan(String kodJawatan) {
		kod_jawatan = kodJawatan;
	}

	public String getJ_keterangan() {
		return j_keterangan.toUpperCase();
	}

	public void setJ_keterangan(String jKeterangan) {
		j_keterangan = jKeterangan;
	}

	public String getCss_title() {
		return css_title.toUpperCase();
	}
	
	public String getCss_title2() {
		return css_title;
	}

	public void setCss_title(String cssTitle) {
		css_title = cssTitle;
	}

	public String getCss_name() {
		return css_name.toUpperCase();
	}
	
	public String getCss_name2() {
		return css_name;
	}

	public void setCss_name(String cssName) {
		css_name = cssName;
	}

}
