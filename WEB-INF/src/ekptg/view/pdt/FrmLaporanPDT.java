package ekptg.view.pdt;
import java.util.Calendar;

import lebah.portal.AjaxBasedModule;

public class FrmLaporanPDT  extends AjaxBasedModule{
	
    public String  doTemplate2() throws Exception

    {	
    	String tahun = getParam("sbcTahun");
   		String tahun1 = getParam("sbcTahun1");
		String tahun2 = getParam("sbcTahun2");
		String tahun3 = getParam("sbcTahun3");

		String dariBulan = getParam("socDariBulan");
		String dariHingga = getParam("socKeBulan");
		String action = getParam("action");
		String BulanDariNo = null;
		String BulanDariNama = null;
		String BulanHinggaNo = null;
		String BulanHinggaNama = null;
		String stbdb = "selecteddb";
		String stbhb = "selectedhb";
		String sttnn1 = "selectedthn1";
		String sttnn2 = "selectedthn2";
		String sttnn3 = "selectedthn3";
		String stahun1 = "00";
		
		
		if ("1".equalsIgnoreCase(tahun1)){
			stahun1="19";
		}else if("2".equalsIgnoreCase(tahun1)){
			stahun1="20";
		}
		
	   Calendar cal=Calendar.getInstance();
	   int year=cal.get(Calendar.YEAR);
	   this.context.put("currentYear", year);
	   this.context.put("selectedYear", tahun);
        if ("jana".equals(action)){
        

    		//String tahun = stahun1 + tahun2 + tahun3 ;
    		
        	if (dariBulan != null && dariBulan !="0"){
        		//main operation
        			String[] break_dariBulan = dariBulan.split ("/");
        			BulanDariNo = break_dariBulan[0];
        			BulanDariNama = break_dariBulan[1];
        			String[] break_hinggaBulan = dariHingga.split ("/");
        			BulanHinggaNo = break_hinggaBulan[0];
        			BulanHinggaNama = break_hinggaBulan[1];
        		//	
        			if (Integer.parseInt(BulanDariNo) > Integer.parseInt(BulanHinggaNo)){
        	    		this.context.put("view","error");        				
        			}else{
        	    		this.context.put("view","view");
        			}
        			this.context.put("BulanDariNo",BulanDariNo);
        			this.context.put("BulanDariNama",BulanDariNama.toUpperCase());
        			this.context.put("BulanHinggaNo",BulanHinggaNo);
        			this.context.put("BulanHinggaNama",BulanHinggaNama.toUpperCase());
        			this.context.put("tahun",tahun);
        			
        			stbdb = stbdb + BulanDariNo ;
        			stbhb = stbhb + BulanHinggaNo ;
        			sttnn1 = sttnn1 + tahun1 ;
        			sttnn2 = sttnn2 + tahun2.replace("0", "");
        			sttnn3 = sttnn3 + tahun3.replace("0", "");

        			}
        }else{

			this.context.put("view","");
        
        }
        
        
        	this.context.put(stbdb,"selected");
    		this.context.put(stbhb,"selected");
    		this.context.put(sttnn1,"selected");
    		this.context.put(sttnn2,"selected");
    		this.context.put(sttnn3,"selected");
    	
            String vm = "app/pdt/frmLaporanPDT.jsp";

            return vm;

    }
}
