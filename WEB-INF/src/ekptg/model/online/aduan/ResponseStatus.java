package ekptg.model.online.aduan;

public enum ResponseStatus {
	SELESAI("SELESAI"),
	DRAF("DRAF"),
	TIDAK_LENGKAP("TIDAK LENGKAP"),
	TIDAK_RELEVAN("TIDAK RELEVAN"),
	BARU("BARU");
	
	private String desc;
	private ResponseStatus(String desc){
		this.desc = desc;
	}
	public String getDesc() {
		return desc;
	}
	public static ResponseStatus getResponseStatus(String code){
		for(ResponseStatus s : ResponseStatus.values()){
			if(code.equals(s.toString())){
				return s;
				
			}
		}
		return null;
	}
}
