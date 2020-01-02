package ekptg.view.ppk;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.ppk.FrmLaporanRingkasanKesBelumSelesaiData;

public class FrmLaporanRingkasanKesBelumSelesai extends AjaxBasedModule {
	
	FrmLaporanRingkasanKesBelumSelesaiData logiconline =  new FrmLaporanRingkasanKesBelumSelesaiData();
	Vector negeriLogin = null;
	Vector unitLogin = null;
	public String doTemplate2() throws Exception {

		HttpSession session = this.request.getSession();

		String vm = "app/ppk/frmLaporanRingkasanKesBelumSelesai.jsp";
		 
        String action = getParam("action");
		String submit = getParam("command");
		
		
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    Date date = new Date();
	    String currentDate = dateFormat.format(date);
	    Vector negeri = null;
		Vector unit = null;
		String usid="";  
   		usid=(String)session.getAttribute("_ekptg_user_id");
		String idNegeri = getParam("socNegeri");
		if (idNegeri == null || idNegeri.trim().length() == 0){
			idNegeri = "0";
		}
		this.context.put("idNegeri", idNegeri);
		
		String idUnit = getParam("socUnit");
		if (idUnit == null || idUnit.trim().length() == 0){
			idUnit = "0";
		}
//		this.context.put("idUnit", idUnit);
		
		String tahun = getParam("socTahun");
		if (tahun == null || tahun.trim().length() == 0){
			tahun = "0";
		}
//		this.context.put("tahun", tahun);
		
		String bulan = getParam("socBulan");
		if (bulan == null || bulan.trim().length() == 0){
			bulan = "0";
		}
//		this.context.put("bulan", bulan);

	
		
		if (submit.equals("doChangeNegeri")){
			negeri = getIdNegeriByLogin(usid);
			Hashtable hN = (Hashtable)negeri.get(0);
			context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(hN.get("ID_NEGERI").toString()),"style=width:340"));
			
			unit = getIdUnitByLogin(usid);
			Hashtable hU = (Hashtable)unit.get(0);
		    context.put("selectUnit",HTML.SelectUnitById_Pjbt(hN.get("ID_NEGERI").toString(),"socUnit",Utils.parseLong(hU.get("ID_PEJABATJKPTG").toString()),"style=width:400 onChange=\"doChangeUnit();\"",""));
							
			this.context.put("selectTahun",HTML.SelectTahun("socTahun", tahun, "", " style=width:100"));
			this.context.put("bulan", bulan);
			
			this.context.put("A", "");
			this.context.put("B", "");
			this.context.put("C", "");
			this.context.put("D", "");
			this.context.put("E", "");
			this.context.put("F", "");
			this.context.put("G", "");
			this.context.put("H", "");
			this.context.put("I", "");
			this.context.put("J", "");
			this.context.put("K", "");
			this.context.put("L", "");
			this.context.put("M", "");
			this.context.put("N", "");
			
		} 
		else if (submit.equals("retrieve_info")){
			//this.context.put("idUnit", idUnit);
			//this.context.put("tahun", tahun);
			//this.context.put("bulan", bulan);
			
			this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri", Long.parseLong(idNegeri), null, " onChange=\"doChangeNegeri()\""));
			this.context.put("selectUnit",HTML.SelectTempatBicaraByNegeri(idNegeri,"socUnit",Long.parseLong(idUnit),"style=width:340"));
//			Vector listUnit = FrmLaporanRingkasanKesBelumSelesaiData.getAddressSelectedDaerah(Integer.parseInt(idNegeri),Integer.parseInt(idUnit));
//			this.context.put("selectUnit", listUnit);
//			this.context.put("showUnit","yes");
			this.context.put("selectTahun",HTML.SelectTahun("socTahun", tahun, "", " style=width:100"));
			
					
			this.context.put("idUnit", idUnit);
			this.context.put("tahun", tahun);
			this.context.put("bulan", bulan);
			
			int A = logiconline.getA(idNegeri, idUnit, tahun, bulan);
			this.context.put("A", A);
			
			int B = logiconline.getB(idNegeri, idUnit, tahun, bulan);
			this.context.put("B", B);
			
			int C = logiconline.getC(idNegeri, idUnit, tahun, bulan);
			this.context.put("C", C);
			
			int D = logiconline.getD(idNegeri, idUnit, tahun, bulan);
			this.context.put("D", D);
			
			int E = logiconline.getE(idNegeri, idUnit, tahun, bulan);
			this.context.put("E", E);
			
			int F = logiconline.getF(idNegeri, idUnit, tahun, bulan);
			this.context.put("F", F);
			
			int G = logiconline.getG(idNegeri, idUnit, tahun, bulan);
			this.context.put("G", G);
			
			int H = logiconline.getH(idNegeri, idUnit, tahun, bulan);
			this.context.put("H", H);
			
			int I = logiconline.getI(idNegeri, idUnit, tahun, bulan);
			this.context.put("I",I);
			
			int J = A + B + C + D + E + F + G + H;
			this.context.put("J", J);
			
			int K = logiconline.getK(idNegeri, idUnit, tahun, bulan);
			this.context.put("K", K);
			
			int L = J - K;
			
			if(L < 0){
				this.context.put("L", "0");
			}
			else{this.context.put("L", L);}
			
			this.context.put("M", currentDate);
			this.context.put("N", "0");
		}
		else {
			negeri = getIdNegeriByLogin(usid);
			Hashtable hN = (Hashtable)negeri.get(0);
			context.put("selectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(hN.get("ID_NEGERI").toString()),"style=width:340"));
			
			unit = getIdUnitByLogin(usid);
			Hashtable hU = (Hashtable)unit.get(0);
		    context.put("selectUnit",HTML.SelectUnitById_Pjbt(hN.get("ID_NEGERI").toString(),"socUnit",Utils.parseLong(hU.get("ID_PEJABATJKPTG").toString()),"style=width:400 onChange=\"doChangeUnit();\"",""));
			this.context.put("selectTahun",HTML.SelectTahun("socTahun", tahun, "", " style=width:100"));
			this.context.put("bulan", bulan);
			
			
			
		}
		return vm;
	}
	
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
	public Vector getIdUnitByLogin (String userid)throws Exception{
		 Db db = null;
		 String sql = "";
		 
		 try{
			 unitLogin = new Vector();
		     db = new Db();
		     Statement stmt = db.getStatement();
		     
		     sql = "SELECT ID_PEJABATJKPTG FROM USERS_INTERNAL WHERE USER_ID = '"+userid+"'";
		     ResultSet rs = stmt.executeQuery(sql);
		     Hashtable h = null;
		     while (rs.next()) {
		    	  
		    	  h = new Hashtable();
		    	  h.put("ID_PEJABATJKPTG",rs.getString("ID_PEJABATJKPTG"));
		    	  unitLogin.addElement(h);
		    	  
		     }
		     
		     return unitLogin;
			 
		 }
		 finally {
				if(db != null) db.close();
				
			}
		 
	 }

}
