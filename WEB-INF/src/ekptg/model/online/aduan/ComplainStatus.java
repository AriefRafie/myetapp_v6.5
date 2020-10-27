package ekptg.model.online.aduan;

import java.util.Vector;

public enum ComplainStatus {
	DALAM_PROSES("DALAM PROSES"),
	SEMAKKAN_SEKSYEN("DALAM SEMAKAN SEKSYEN"),
	SELESAI("SELESAI"),
	ABAIKAN("ABAIKAN"),
	PALSU("PALSU"),
	BACA("TELAH DIBACA")
	;
	
	private String desc;
	
	private ComplainStatus(String desc){
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}
	
	public static Vector<String> getList(){
		Vector<String> v = new Vector<String>();
		for(ComplainStatus status : ComplainStatus.values()){
			v.add(status.getDesc());
		}
		return v;
	}
	
	public static ComplainStatus getDescription(String code){
//		System.out.println("input code:"+code);
		for(ComplainStatus status : ComplainStatus.values()){
//			System.out.println("status aduan:" + status.getDesc());
			if(code.equals(status.getDesc())){
				return status;			
			}
		}		
		return null;
	}	
}