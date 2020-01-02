package ekptg.model.htp;

import java.util.Hashtable;
import java.util.Vector;

import org.apache.log4j.Logger;

import ekptg.model.htp.entity.HtpPermohonan;
import ekptg.model.htp.entity.Permohonan;
import ekptg.model.htp.entity.PfdFail;

public class HTPPermohonanTanahBean extends HTPPermohonanBean {
	HtpPermohonan htpPermohonan = null;
	Permohonan permohonan = null;	
	PfdFail fail = null;	
	static Logger myLog = Logger.getLogger(ekptg.model.htp.HTPPermohonanTanahBean.class);
	FrmSenaraiFailTerimaPohonData fData = FrmSenaraiFailTerimaPohonData.getInstance();
	private Hashtable<String, String> hashDataInput = null;

	public void maklumatAsas(org.apache.velocity.VelocityContext context_, Vector<Hashtable <String,String>> tanahVector
		,String idPermohonan) throws Exception {
		try{
			tanahVector = fData.getMaklumatAsasTanahInfo(idPermohonan);		    			
			//untuk ppt
			if(tanahVector.size()>0 ){
				//tabmode = "3_1";
			}	    			
			context_.put("bilBorangK", tanahVector.size());
			context_.put("MAT", tanahVector);
			
		}catch(Exception e){
			myLog.info("maklumatAsas()");
		}
		
	}
	
	public void maklumatLuas(org.apache.velocity.VelocityContext context_
		,String socLuas, String socLuasParam,String LuasLama) throws Exception {
				
		String luas = "0";
		String luas1 = "0";
		String luas2 = "0";
		if(socLuas.equals("1") 
			|| socLuas.equals("2") 
			|| socLuas.equals("3") 
			|| socLuasParam.equals("5") 
			|| socLuasParam.equals("6")){
			if(socLuas.equals("1")){
				if(LuasLama.contains("KM"))
					luas = LuasLama.substring(0, (LuasLama.length()-2));
				else
					luas = LuasLama;
				
			}else if(socLuas.equals("2")){
				if(LuasLama.contains("H"))
					luas = LuasLama.substring(0, (LuasLama.length()-1));
				else
					luas = LuasLama;
			
			}else if(socLuas.equals("3")){
				if(LuasLama.contains("MP"))
					luas = LuasLama.substring(0, (LuasLama.length()-2));
				else{
					if(LuasLama.contains("M"))
						luas = LuasLama.substring(0, (LuasLama.length()-1));
					else
						luas = LuasLama;
				}
			
			}else if(socLuas.equals("5")){
				if(LuasLama.contains("KP"))
					luas = LuasLama.substring(0, (LuasLama.length()-2));
				else{
					if(LuasLama.contains("K"))
						luas = LuasLama.substring(0, (LuasLama.length()-1));
					else
						luas = LuasLama;
				}
				
			}else if(socLuas.equals("6")){
				if(LuasLama.contains("P"))
					luas = LuasLama.substring(0, (LuasLama.length()-1));
				else
					luas = LuasLama;
			}else if(socLuas.equals("4")){
				if(LuasLama.contains("E,") && LuasLama.contains("R,")){
					luas = LuasLama.substring(0,LuasLama.indexOf("E,"));
					luas1 = LuasLama.substring(LuasLama.indexOf("E,")+2,LuasLama.indexOf("R,"));
					luas2 = LuasLama.substring(LuasLama.indexOf("R,")+2,(LuasLama.length()-1));
		
				}
				
			}else if(socLuas.equals("7")){
				//log.info("Belum viewMaklumatRizab:submit="+btnSubmit+":4="+getParam("txtLuasGabung"));
				if(LuasLama.contains("E,") && LuasLama.contains("D")){
					luas = LuasLama.substring(0,LuasLama.indexOf("E,"));
					luas1 = LuasLama.substring(LuasLama.indexOf("E,")+2,LuasLama.indexOf("D"));
				}
				
			}else if(socLuas.equals("8")){
				//log.info("Belum viewMaklumatRizab:submit="+btnSubmit+":4="+getParam("txtLuasGabung"));
				if(LuasLama.contains("R,") && LuasLama.contains("J,")){
					luas = LuasLama.substring(0,LuasLama.indexOf("R,"));
					luas1 = LuasLama.substring(LuasLama.indexOf("R,")+2,LuasLama.indexOf("J"));
					luas2 = LuasLama.substring(LuasLama.indexOf("J,")+2,(LuasLama.length()-1));
				}
			
			}else{ //7||9 (TIADA SAMPLE DATA)
				luas = LuasLama;							
			}
			context_.put("txtLuasLama1", luas1.trim());	
			context_.put("txtLuasLama2", luas2.trim());	
			context_.put("txtLuasLama", luas);					
	
		}
	
	}
	/**
	 * Fungsi simpan dan kemaskini maklumat charting
	 * value parameter tindakan = insert/update
	 */
	public void chartingSimpanKemaskini(String userId, String idPermohonan,String idHakmilik, String flag 
		,String bayaran, String ulasan, String imejDesc, String noFail
		,String tindakan) throws Exception {
		hashDataInput = new Hashtable<String, String>();
		hashDataInput.put("idUser", userId);
		hashDataInput.put("idpermohonan", idPermohonan);
		hashDataInput.put("idhakmilikurusan", idHakmilik);
		hashDataInput.put("Flagcharting", flag);
		hashDataInput.put("JumBayaranPelan", bayaran);
		hashDataInput.put("ulasan", ulasan);
		hashDataInput.put("keteranganImej", imejDesc);
		hashDataInput.put("nofail",noFail);
		FrmTerimaPohonData.LakaranPelan(hashDataInput,tindakan);
	
	}
	
	public void lokasiTanahSimpanKemaskini(String userId,String idHakmilik,String bandar, String bandarDesc
		, String lebuhraya, String lebuhrayaDesc, String jlnKeretapi,  String jlnKeretapiDesc, String pantai, String pantaiDesc
		,String utara, String selatan, String timur, String barat,String keteranganLain, String lokasi, String zon, String tindakan) 
		throws Exception {
		hashDataInput = new Hashtable<String, String>();
		hashDataInput.put("idUser",userId);
		hashDataInput.put("idhakmilikurusan", idHakmilik);
		
		hashDataInput.put("txtbandar", bandar);
		hashDataInput.put("txtbandarperihal", bandarDesc);
		hashDataInput.put("txtLebuhRaya", lebuhraya);
		hashDataInput.put("txtLebuhRayaperihal", lebuhrayaDesc);
		hashDataInput.put("txtJkeretapi", jlnKeretapi);
		hashDataInput.put("txtJkeretapiperihal", jlnKeretapiDesc);
		hashDataInput.put("txtSgPntai", pantai);	
		hashDataInput.put("txtSgPntaiperihal", pantaiDesc);
		hashDataInput.put("txtSempadanUtara", utara);
		hashDataInput.put("txtSempadanSelatan", selatan);
		hashDataInput.put("txtSempadanTimur", timur);
		hashDataInput.put("txtSempadanBarat", barat);
		hashDataInput.put("txtSempadanKeteranganLain", keteranganLain);
		hashDataInput.put("txtPerihalLokasi", lokasi);
		hashDataInput.put("txtZone", zon);
		
		if(tindakan.equals("insert"))
			FrmTerimaPohonData.simpanLokasiTanah(hashDataInput);
		else
			FrmTerimaPohonData.kemaskiniLokasiTanah(hashDataInput);

	}
	public static String getKeputusanPermohonan(int i){
		//myLog.info("getKeputusanPermohonan:i="+i);
		String m="";
		String[]names = {"BELUM ADA KEPUTUSAN", "DILULUSKAN", "TIDAK DILULUSKAN","DIBATALKAN/DITARIKBALIKR","TIADA MAKLUMAT"};
   		m = names[i];
		return m;
		
	}
	
	
}
