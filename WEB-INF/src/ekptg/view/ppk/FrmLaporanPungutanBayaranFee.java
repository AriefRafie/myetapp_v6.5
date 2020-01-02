package ekptg.view.ppk;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.velocity.VTemplate;

import org.apache.log4j.Logger;
import org.apache.velocity.Template;

import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.ppk.FrmModelLaporanPungutanBayaranFee;

public class FrmLaporanPungutanBayaranFee extends VTemplate {
	
	static Logger myLogger = Logger.getLogger(FrmSenaraiNotis.class);
	FrmModelLaporanPungutanBayaranFee model = new FrmModelLaporanPungutanBayaranFee();
	Vector negeriLogin = null;
	public Template doTemplate() throws Exception {
		
		HttpSession session = request.getSession();
		
		String vm = "";
		
		Vector dataUnitJkptg = new Vector();
		Vector dataSemak = new Vector();
		Vector negeri = null;
		dataUnitJkptg.clear();
		dataSemak.clear();
		
		context.put("Util", new lebah.util.Util());
		
		String usid="";  
   		usid=(String)session.getAttribute("_ekptg_user_id");	

   		String submit = getParam("command");
    	myLogger.info("[submit] :: " +submit);
    	
    	String tahun = getParam("socTahun");
		if (tahun == null || tahun.trim().length() == 0){
			tahun = "0";
		}
		
		String id_negeri = getParam("socNegeri");
		String id_unit = getParam("socUnit");
		String id_daerah = "";
       
    	if ("doChangeUnit".equals(submit)){
    		
    		//reset data to zero
			context.put("pengeluaran_perintah", "");
			context.put("salinan_perintah", "");
			context.put("fee_rayuan", "");
			context.put("salinan_dokumen", "");
			context.put("duti", "");
			context.put("baitulmal", "");
			context.put("iddaerah", "");
			context.put("bulan", "");
			context.put("lain2", "");
			
    		
    		
    		//dropdown
    		if(id_negeri!=""){
    			context.put("selectTahun",HTML.SelectTahun("socTahun", tahun, "", " style=width:100"));
    			context.put("selectUnit",HTML.SelectTempatBicaraByNegeri(id_negeri,"socUnit",null,"style=width:340"));
//    			context.put("selectUnit",HTML.SelectUnitJKPTGByIdNegeri("socUnit",null,null,"style=width:340",id_negeri));
    			context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(id_negeri),null,"style=width:340 onChange=\"doChangeUnit();\" "));
        	}else{
        		context.put("selectUnit",HTML.SelectTempatBicara("socUnit",null,null,"style=width:340"));
    			context.put("selectNegeri",HTML.SelectNegeri("socNegeri",null,null,"style=width:340 onChange=\"doChangeUnit();\" "));
    			context.put("selectTahun",HTML.SelectTahun("socTahun", tahun, "", " style=width:100"));

        	}
    		
    		vm = "app/ppk/frmLaporanPungutanBayaranFee.jsp";
    		
    	}//close doChangeTempatBicara
    	
    	else if ("semak".equals(submit)){
    		
    		
    		
    		String month = getParam("bulan");
    		
    		System.out.println("id_kptg-->"+id_unit);
    		
    		//dropdown
    		context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(id_negeri),null,"style=width:340 onChange=\"doChangeUnit();\" "));
    		context.put("selectUnit",HTML.SelectTempatBicaraByNegeri(id_negeri,"socUnit",Long.parseLong(id_unit),"style=width:340"));
			context.put("selectTahun",HTML.SelectTahun("socTahun", tahun, "", " style=width:100"));

    		
    		Double pengeluaran_perintah = 0.00;
    		Double salinan_perintah = 0.00;
    		Double fee_rayuan = 0.00;
    		Double salinan_dokumen = 0.00;
    		Double duti = 0.00;
    		Double baitulmal = 0.00;
    		Double lain2 = 0.00;
    		
    		
    		
    		model.setUnitJkptg(id_unit);
    		dataUnitJkptg = model.getUnitJkptg();
    		if(dataUnitJkptg.size()!=0){
    			Hashtable x = (Hashtable) dataUnitJkptg.get(0);   			
    			id_daerah = x.get("id_daerah").toString();
    		}
    		
    		model.setDataSemak(id_negeri,id_daerah,model.getTahun(Integer.parseInt(month),Integer.parseInt(tahun)),model.getBulan(Integer.parseInt(month)),id_unit);
    		dataSemak = model.getDataSemak();
    		if(dataSemak.size()!=0){
    			Hashtable z = (Hashtable) dataSemak.get(0); 
    			pengeluaran_perintah = Double.parseDouble(z.get("pengeluaran_perintah").toString());
        		salinan_perintah = Double.parseDouble(z.get("salinan_perintah").toString());
        		fee_rayuan = Double.parseDouble(z.get("fee_rayuan").toString());
        		salinan_dokumen = Double.parseDouble(z.get("salinan_dokumen").toString());
        		duti = Double.parseDouble(z.get("duti").toString());
        		baitulmal = Double.parseDouble(z.get("baitulmal").toString());	
        	}
    		
    			//set data
    			context.put("pengeluaran_perintah", pengeluaran_perintah);
    			context.put("salinan_perintah", salinan_perintah);
    			context.put("fee_rayuan", fee_rayuan);
    			context.put("salinan_dokumen", salinan_dokumen);
    			context.put("duti", duti);
    			context.put("baitulmal", baitulmal);
    			context.put("lain2", lain2);
    			context.put("iddaerah", id_daerah);
    			context.put("bulan", month);
    			
    			
    		vm = "app/ppk/frmLaporanPungutanBayaranFee.jsp";
    		
    	}//close semak
    	
    	else{			
       		
    		
    		//reset data to zero
			context.put("pengeluaran_perintah", "");
			context.put("salinan_perintah", "");
			context.put("fee_rayuan", "");
			context.put("salinan_dokumen", "");
			context.put("duti", "");
			context.put("baitulmal", "");
			context.put("iddaerah", "");
			context.put("bulan", "");
			context.put("lain2", "");
			
    		//dropdown
			negeri = getIdNegeriByLogin(usid);
			Hashtable hN = (Hashtable)negeri.get(0);
    		context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(hN.get("ID_NEGERI").toString()),"style=width:340 onChange=\"doChangeUnit()\" "));
    		context.put("selectUnit",HTML.SelectUnitByNegeri(hN.get("ID_NEGERI").toString(),"socUnit",null,"style=width:340"));
			context.put("selectTahun",HTML.SelectTahun("socTahun", tahun, "", " style=width:100"));

    		vm = "app/ppk/frmLaporanPungutanBayaranFee.jsp";
       		
   		}//close else
       		
    	   Template template = engine.getTemplate(vm);
           return template;
           
	}//close template	
	
	public Vector getIdNegeriByLogin (String userid)throws Exception{
		 Db db = null;
		 String sql = "";
		 
		 try{
			 negeriLogin = new Vector();
		     db = new Db();
		     Statement stmt = db.getStatement();
		     
		     sql = "SELECT ID_NEGERI FROM USERS_INTERNAL WHERE USER_ID = '"+userid+"'";
		     ResultSet rs = stmt.executeQuery(sql);
		     Hashtable h = null;
		     while (rs.next()) {
		    	  
		    	  h = new Hashtable();
		    	  h.put("ID_NEGERI",rs.getString("ID_NEGERI"));
		    	  negeriLogin.addElement(h);
		    	  
		     }
		     
		     return negeriLogin;
			 
		 }
		 finally {
				if(db != null) db.close();
				
			}
		 
	 }
	
	
}//close class

