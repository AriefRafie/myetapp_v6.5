package ekptg.object;

public class Person {


	private String Jantina;
	private String idfail;
	private String Nama;
	private String NamaPemohon;
	private String idFile;
	private String noFile;
	private String id_permohonan;
	private String id_simati;
	private String id_permohonansimati;
	private String id_pemohon;
	private String noKP;
	private String tarikhMati;
	private String StatusTerkini;
	private String NoPermohonanOnline;
	
	public Person() {
		
	}
	public void setidFail(String idFail){
	   this.idfail=idFail;
	}
	public String getidFail(){
	   return idfail;
	}
	public String getStatusTerkini() {
		return StatusTerkini;
	}

	public void setStatusTerkini(String statusTerkini) {
		StatusTerkini = statusTerkini;
	}
	public String getIdFile() {
		return idFile;
	}

	public void setIdFile(String idFile) {
		this.idFile = idFile;
	}
	
	public String getNoPermohonanOnline() {
		return NoPermohonanOnline;
	}

	public void setNoPermohonanOnline(String noPermohonanOnline) {
		NoPermohonanOnline = noPermohonanOnline;
	}

	public Person(String id_permohonan) {
		this.id_permohonan = id_permohonan;
	}
	
	public String getNamaPemohon() {
		return NamaPemohon;
	}

	public void setNamaPemohon(String namaPemohon) {
		NamaPemohon = namaPemohon;
	}	
	
	public String getNoFile() {
		return noFile;
	}

	public void setNoFile(String noFile) {
		this.noFile = noFile;
	}
	
	public String getId_simati() {
		return id_simati;
	}
	public void setId_simati(String id_simati) {
		this.id_simati = id_simati;
	}
	public String getJantina() {
		return Jantina;
	}
	public void setJantina(String jantina) {
		Jantina = jantina;
	}
	public String getNamaSimati() {
		return Nama;
	}
	public void setNamaSimati(String nama) {
		Nama = nama;
	}
	public String getId_permohonan() {
		return id_permohonan;
	}
	public void setId_permohonan(String id_permohonan) {
		this.id_permohonan = id_permohonan;
	}
	public String getNoKp() {
		return noKP;
	}
	public void setNoKp(String noKP) {
		this.noKP = noKP;
	}
	public String getTarikhMati() {
		return tarikhMati;
	}
	public void setTarikhMati(String tarikhMati) {
		this.tarikhMati = tarikhMati;
	}
	public String getId_permohonansimati() {
		return id_permohonansimati;
	}

	public void setId_permohonansimati(String id_permohonansimati) {
		this.id_permohonansimati = id_permohonansimati;
	}

	public String getId_pemohon() {
		return id_pemohon;
	}

	public void setId_pemohon(String id_pemohon) {
		this.id_pemohon = id_pemohon;
	}

	 
 }
