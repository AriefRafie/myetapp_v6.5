package ekptg.view.ppt;


import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.ppt.FrmPermohonanUPTData;
import ekptg.model.ppt.PPTHeader;

public class SkrinPopupAfidavit extends AjaxBasedModule {
	static Logger myLogger = Logger.getLogger(SkrinPopupAfidavit.class);
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	private final String PATH="app/ppt/PopupHakmilik/";
	private String vm = PATH +"SkrinPopupAfidavit.jsp";
	PPTHeader header = new PPTHeader();
	FrmPermohonanUPTData modelUPT = new FrmPermohonanUPTData();
	
	HttpSession session = null;
	String action = null;
	
	
	public String doTemplate2() throws Exception {		
		session = request.getSession();
		action = getParam("action");
		this.context.put("txtTujuan","");
		this.context.put("txtPerkara","");
		this.context.put("txtAlamat1","");
		this.context.put("txtAlamat2","");
		this.context.put("txtAlamat3","");
		this.context.put("txtPoskod","");
		this.context.put("id_afidavit","");
		context.put("selectNegeri","");
		context.put("selectBandar","");	
		context.put("getDataAfidevit","");	
		String command =  getParam("command");
		this.context.put("command",command);
		String id_hakmilik =  getParam("id_hakmilik");
		this.context.put("id_hakmilik",id_hakmilik);
		String id_hakmilikpb =  getParam("id_hakmilikpb");
		this.context.put("id_hakmilikpb",id_hakmilikpb);
		String id_award =  getParam("id_award");
		this.context.put("id_award",id_award);
		String id_daerah =  getParam("id_daerah");
		this.context.put("id_daerah",id_daerah);
		String id_negeri =  getParam("id_negeri");
		this.context.put("id_negeri",id_negeri);
		String flag_skrin = getParam("flag_skrin");
		this.context.put("flag_skrin",flag_skrin);
		this.context.put("NO_LOT","");
		this.context.put("NAMA_PB","");
		this.context.put("NO_PB","");
		myLogger.info("command :"+command);
		String bolehsimpan = "yes";		
		String id_user = (String) session.getAttribute("_ekptg_user_id");	
		String id_permohonan =  getParam("id_permohonan");
		this.context.put("id_permohonan",id_permohonan);
		
		
		Db db = null;
		try {
		db = new Db();	
		Hashtable dh = getPermohonan(id_permohonan,db);
		String id_negeriProjek = (String)dh.get("id_negeri");
		myLogger.info("id_negeriProjek :"+id_negeriProjek);
		if(id_negeriProjek!=""){
			context.put("selectMahkamah",HTML.SelectMahkamahByNegeri(Utils.parseLong(id_negeriProjek),"socMahkamah",null, "onChange=\"doChangeMahkamah('"+id_permohonan+"');\""));
		}
		
		Hashtable getDataAfidevit = getDataAfidevit(id_award,db);
		String id_afidavit = (String)getDataAfidevit.get("id_afidavit").toString();
		myLogger.info("getDataAfidevit :"+getDataAfidevit.size());
		myLogger.info("id_afidavit :"+id_afidavit);
		
		
		if(!id_afidavit.equals(""))
		{
			context.put("getDataAfidevit",getDataAfidevit);	
			dataAfidevit(id_award,id_negeriProjek,"enabled",db,id_permohonan);	
		}		
		
		
		
		
		
		if("doChangeMahkamah".equals(command)){	    	
	    	String id_pejabat = getParam("socMahkamah");	    	
	    	getAndSetAF(id_pejabat,id_negeriProjek,"",db,id_permohonan);
	    	this.context.put("txtTujuan",getParam("txtTujuan"));
	    	this.context.put("txtPerkara",getParam("txtPerkara"));
	    }
		else if("simpanAfidavit".equals(command)){	
			if(getParam("id_afidavit").equals(""))
			{		
				simpanAfidevitAdd(session,db);
				dataAfidevit(id_award,id_negeriProjek,"enabled",db,id_permohonan);	
			}
			else
			{	
				simpanAfidevitUpdate(session,db,getParam("id_afidavit"));
				dataAfidevit(id_award,id_negeriProjek,"enabled",db,id_permohonan);	
			}
	    	
	    }
			
		}finally {		
		if (db != null)
		db.close();
		}
		
		return vm;
	}
	
		Hashtable getPermohonan = null;
		public Hashtable getPermohonan(String id_permohonan,Db db) throws Exception {
			getPermohonan = new Hashtable();			
			//Db db = null;
			String sql = "";
			try {
				//db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				sql = " SELECT P.ID_PERMOHONAN,F.ID_NEGERI, F.ID_FAIL,P.ID_STATUS FROM  TBLPPTPERMOHONAN P, TBLPFDFAIL F "+
					"	WHERE P.ID_FAIL = F.ID_FAIL AND P.ID_PERMOHONAN = '"+ id_permohonan+"' ";				
				ResultSet rs = stmt.executeQuery(sql);				
			
				String id_fail = "";
				String id_status = "";
				String id_negeri = "";
				while (rs.next()){					
					id_permohonan = rs.getString("ID_PERMOHONAN")==null?"":rs.getString("ID_PERMOHONAN").toUpperCase();			
					id_fail = rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL").toUpperCase();	
					id_status = rs.getString("ID_STATUS")==null?"":rs.getString("ID_STATUS").toUpperCase();	
					id_negeri  = rs.getString("ID_NEGERI")==null?"":rs.getString("ID_NEGERI").toUpperCase();	
					getPermohonan.put("id_fail", id_fail);
					getPermohonan.put("id_status", id_status);
					getPermohonan.put("id_negeri", id_negeri);
					getPermohonan.put("id_permohonan", id_permohonan);
				}
				return getPermohonan;
			} finally {
				//if (db != null) db.close();
			}
		}
		
		public Hashtable getDataAfidevit(String id_award,Db db)throws Exception {			
				    String sql = "";				    
				    try {				    		   
				    		//db = new Db();
				    		Statement stmt = db.getStatement();				      
				    		sql = "SELECT A.id_afidavit, B.ID_PEJABAT, ";
				    		sql += " A.TUJUAN,A.PERKARA_RUJUKAN, B.ID_NEGERI, B.ALAMAT1, B.ALAMAT2, B.ALAMAT3, B.ID_BANDAR, B.POSKOD ";
				    		sql += " FROM TBLPPTAFIDAVIT A, TBLRUJPEJABAT B ";
				    		sql += " WHERE A.ID_MAHKAMAH = B.ID_PEJABAT(+) ";
				    		sql += " AND A.ID_AWARD = '"+id_award+"'";				      
				    		ResultSet rs = stmt.executeQuery(sql);				      
				    		Hashtable h = new Hashtable();
				    		while (rs.next()) {						    			
				    			h.put("id_afidavit", rs.getString("id_afidavit")== null?"":rs.getString("id_afidavit"));
				    			h.put("id_pejabat", rs.getString("ID_PEJABAT")== null?"":rs.getString("ID_PEJABAT"));
				    			h.put("tujuan", rs.getString("TUJUAN")== null?"":rs.getString("TUJUAN"));
				    			h.put("perkara_rujukan", rs.getString("PERKARA_RUJUKAN")== null?"":rs.getString("PERKARA_RUJUKAN"));
				    			h.put("id_negeri", rs.getString("ID_NEGERI")== null?"":rs.getString("ID_NEGERI"));
				    			h.put("alamat1", rs.getString("ALAMAT1")== null?"":rs.getString("ALAMAT1"));
				    			h.put("alamat2", rs.getString("ALAMAT2")== null?"":rs.getString("ALAMAT2"));
				    			h.put("alamat3", rs.getString("ALAMAT3")== null?"":rs.getString("ALAMAT3"));
				    			h.put("id_bandar", rs.getString("ID_BANDAR")== null?"":rs.getString("ID_BANDAR"));
				    			h.put("poskod", rs.getString("POSKOD")== null?"":rs.getString("POSKOD"));		
				      }//close while
				    		
				    		myLogger.info("h :"+h.size());
				    		
				    		if(h.size()==0)
				    		{
				    			h.put("id_afidavit", "");
				    			h.put("id_pejabat", "");
				    			h.put("tujuan", "");
				    			h.put("perkara_rujukan","");
				    			h.put("id_negeri", "");
				    			h.put("alamat1", "");
				    			h.put("alamat2", "");
				    			h.put("alamat3", "");
				    			h.put("id_bandar","");
				    			h.put("poskod", "");		
				    		}
				    		
				    		
				    		return h;
				    }// 
				    finally {
				     // if (db != null) db.close();
				    }
				    
		}//close setDataAfidevit
		
	
		private void dataAfidevit(String id_award,String id_negeriProjek,String disability,Db db,String id_permohonan) throws Exception{			
				//model for validation new / view
				String id_pejabat = "";
				String id_bandar = "";
				String id_negeri = "";
				String id_afidavit = "";
				String tujuan = "";
				String perkara_rujukan = "";
				Hashtable da = setDataAfidevit(id_award,db);
				context.put("dataAfidevit",da);
				
				
				
				
				if(da.size()!=0){
					id_afidavit = (String)da.get("id_afidavit");
					id_pejabat = (String)da.get("id_pejabat");
					id_negeri = (String)da.get("id_negeri");
					id_bandar = (String)da.get("id_bandar");
					tujuan = (String)da.get("tujuan");
					perkara_rujukan = (String)da.get("perkara_rujukan");					
					this.context.put("txtTujuan",tujuan);
					this.context.put("txtPerkara",perkara_rujukan);					
				}	
				
				
				
				String alamat1 = "";
				String alamat2 = "";
				String alamat3 = "";
				String poskod = "";
				Vector dataPejabat = setDataPejabat(id_pejabat,db);
				if(dataPejabat.size()!=0){
					Hashtable dp = (Hashtable)dataPejabat.get(0);
					alamat1 = (String)dp.get("alamat1");
					alamat2 = (String)dp.get("alamat2");
					alamat3 = (String)dp.get("alamat3");
					poskod = (String)dp.get("poskod");
					id_negeri = (String)dp.get("id_negeri");
					id_bandar = (String)dp.get("id_bandar");
				}				
				context.put("txtAlamat1",alamat1);
				context.put("txtAlamat2",alamat2);
				context.put("txtAlamat3",alamat3);
				context.put("txtPoskod",poskod);
				
				
				
				//id
				context.put("id_afidavit",id_afidavit);				
				String mode = "";				
				if(disability.equals("enabled")){
					mode = "";
				}else{
					mode = "disabled class=disabled";
				}				
				//dropdown
				if(id_negeriProjek!=""){
					context.put("selectMahkamah",HTML.SelectMahkamahByNegeri(Utils.parseLong(id_negeriProjek),"socMahkamah",Utils.parseLong(id_pejabat), " "+mode+" onChange=\"doChangeMahkamah('"+id_permohonan+"');\""));
				}				
				context.put("selectNegeri",HTML.SelectNegeriMampu("socNegeri",Utils.parseLong(id_negeri),null,"disabled class=disabled style=width:300px"));
				context.put("selectBandar",HTML.SelectBandar("socBandar",Utils.parseLong(id_bandar),"disabled class=disabled  style=width:300px"));				
			}//close dataAfidevit

	public Hashtable setDataAfidevit(String id_award,Db db)throws Exception {			
				    String sql = "";				    
				 	Statement stmt = db.getStatement();				      
				    		sql = "SELECT A.id_afidavit, B.ID_PEJABAT, ";
				    		sql += " A.TUJUAN,A.PERKARA_RUJUKAN, B.ID_NEGERI, B.ALAMAT1, B.ALAMAT2, B.ALAMAT3, B.ID_BANDAR, B.POSKOD ";
				    		sql += " FROM TBLPPTAFIDAVIT A, TBLRUJPEJABAT B ";
				    		sql += " WHERE A.ID_MAHKAMAH = B.ID_PEJABAT(+) ";
				    		sql += " AND A.ID_AWARD = '"+id_award+"'";				      
				    		ResultSet rs = stmt.executeQuery(sql);				      
				    		Hashtable h = new Hashtable();				    	
				    		while (rs.next()) {				    			
				    			h.put("id_afidavit", rs.getString("id_afidavit")== null?"":rs.getString("id_afidavit"));
				    			h.put("id_pejabat", rs.getString("ID_PEJABAT")== null?"":rs.getString("ID_PEJABAT"));
				    			h.put("tujuan", rs.getString("TUJUAN")== null?"":rs.getString("TUJUAN"));
				    			h.put("perkara_rujukan", rs.getString("PERKARA_RUJUKAN")== null?"":rs.getString("PERKARA_RUJUKAN"));
				    			h.put("id_negeri", rs.getString("ID_NEGERI")== null?"":rs.getString("ID_NEGERI"));
				    			h.put("alamat1", rs.getString("ALAMAT1")== null?"":rs.getString("ALAMAT1"));
				    			h.put("alamat2", rs.getString("ALAMAT2")== null?"":rs.getString("ALAMAT2"));
				    			h.put("alamat3", rs.getString("ALAMAT3")== null?"":rs.getString("ALAMAT3"));
				    			h.put("id_bandar", rs.getString("ID_BANDAR")== null?"":rs.getString("ID_BANDAR"));
				    			h.put("poskod", rs.getString("POSKOD")== null?"":rs.getString("POSKOD"));
				    		}				     
				      return h;
				  
		}//close setDataAfidevit
		
		@SuppressWarnings({ "static-access", "unchecked" })
		private void getAndSetAF(String id_pejabat,String id_negeriProjek,String mode,Db db,String id_permohonan) throws Exception{
			
		
			if(id_negeriProjek!=""){
					context.put("selectMahkamah",HTML.SelectMahkamahByNegeri(Utils.parseLong(id_negeriProjek),"socMahkamah",Utils.parseLong(id_pejabat), "onChange=\"doChangeMahkamah('"+id_permohonan+"');\""));
			}		
			
			String alamat1 = "";
			String alamat2 = "";
			String alamat3 = "";
			String poskod = "";
			String id_negeri = "";
			String id_bandar = "";
			Vector dataPejabat = setDataPejabat(id_pejabat,db);
			if(dataPejabat.size()!=0){
				Hashtable dp = (Hashtable)dataPejabat.get(0);
				alamat1 = (String)dp.get("alamat1");
				alamat2 = (String)dp.get("alamat2");
				alamat3 = (String)dp.get("alamat3");
				poskod = (String)dp.get("poskod");
				id_negeri = (String)dp.get("id_negeri");
				id_bandar = (String)dp.get("id_bandar");
			}
			
			context.put("selectNegeri",HTML.SelectNegeriMampu("socNegeri",Utils.parseLong(id_negeri),null,"disabled class=disabled style=width:300px"));
			context.put("selectBandar",HTML.SelectBandar("socBandar",Utils.parseLong(id_bandar),"disabled class=disabled  style=width:300px"));
			
			context.put("txtAlamat1",alamat1);
			context.put("txtAlamat2",alamat2);
			context.put("txtAlamat3",alamat3);
			context.put("txtPoskod",poskod);
			context.put("txtTujuan",getParam("txtTujuan"));
			context.put("txtPerkara",getParam("txtPerkara"));
			
		}//close getAndSetAF
		
		Vector dataPejabat = null;
		public Vector setDataPejabat(String id_pejabat,Db db)throws Exception {
				
				dataPejabat = new Vector();
					
				  //  Db db = null;
				    dataPejabat.clear();
				    String sql = "";				    
				    try {				    	
				    		//db = new Db();
				    		Statement stmt = db.getStatement();				      
				    		sql = "SELECT ALAMAT1,ALAMAT2,ALAMAT3,POSKOD,ID_NEGERI,ID_BANDAR FROM TBLRUJPEJABAT WHERE ID_PEJABAT = '"+id_pejabat+"'";
				    		ResultSet rs = stmt.executeQuery(sql);				      
				    		Hashtable h;				    		
				    		while (rs.next()) {
				    			h = new Hashtable();	    		
				    			h.put("alamat1", rs.getString("ALAMAT1")== null?"":rs.getString("ALAMAT1"));
				    			h.put("alamat2", rs.getString("ALAMAT2")== null?"":rs.getString("ALAMAT2"));
				    			h.put("alamat3", rs.getString("ALAMAT3")== null?"":rs.getString("ALAMAT3"));
				    			h.put("poskod", rs.getString("POSKOD")== null?"":rs.getString("POSKOD"));
				    			h.put("id_negeri", rs.getString("ID_NEGERI")== null?"":rs.getString("ID_NEGERI"));
				    			h.put("id_bandar", rs.getString("ID_BANDAR")== null?"":rs.getString("ID_BANDAR"));				    			
				    			dataPejabat.addElement(h);
				      }//close while
				      return dataPejabat;
				    }
				    finally {
				    //  if (db != null) db.close();
				    }
				    
		}//close setSizeExistPB
		
	
		@SuppressWarnings({ "unchecked" })
		private void simpanAfidevitAdd(HttpSession session,Db db) throws Exception{
			
			myLogger.info("simpanAfidevitAdd >>>>>>>>>>DB>>>>>>>>>");
			
			Hashtable h = new Hashtable();		
			//id
			h.put("id_award", getParam("id_award"));
			h.put("id_hakmilikpb", getParam("id_hakmilikpb"));		
			h.put("txtTujuan", getParam("txtTujuan"));
			h.put("txtPerkara", getParam("txtPerkara"));
			h.put("socMahkamah", getParam("socMahkamah"));		
			h.put("id_user", session.getAttribute("_ekptg_user_id"));		
			simpanAfidevit(h,db);  
		}//close simpanAfidevit
		
		private void simpanAfidevitUpdate(HttpSession session,Db db,String id_afidavit) throws Exception{
			Hashtable h = new Hashtable();		
			//id
			h.put("id_afidavit", id_afidavit);
			h.put("id_award", getParam("id_award"));
			h.put("id_hakmilikpb", getParam("id_hakmilikpb"));		
			h.put("txtTujuan", getParam("txtTujuan"));
			h.put("txtPerkara", getParam("txtPerkara"));
			h.put("socMahkamah", getParam("socMahkamah"));		
			h.put("id_user", session.getAttribute("_ekptg_user_id"));		
			simpanAfidevitUpdate(h,db,id_afidavit);  
		}//close simpanAfidevit
		
		
		public static void simpanAfidevit(Hashtable data,Db db) throws Exception
		  {			
		  //  Db db = null;
		    String sql = "";		    
		    try{		      
		    	//	db = new Db();
		    		Statement stmt = db.getStatement();		    		
		    		String id_user = (String)data.get("id_user");	    	
		    		String id_award = (String)data.get("id_award");
		    		String id_hakmilikpb = (String)data.get("id_hakmilikpb");		    		
		    		String txtTujuan = (String)data.get("txtTujuan");
		    		String txtPerkara = (String)data.get("txtPerkara");
		    		String socMahkamah = (String)data.get("socMahkamah");		    		
		    		//insert
		    		SQLRenderer r = new SQLRenderer();
		    		r.add("id_award", id_award);	
		    		r.add("id_hakmilikpb", id_hakmilikpb);
		    		r.add("tujuan", txtTujuan);	
		    		r.add("perkara_rujukan", txtPerkara);	
		    		r.add("id_mahkamah", socMahkamah);	
		    		r.add("tarikh_masuk",r.unquote("sysdate"));
		    		r.add("id_masuk",id_user);    		
		    		sql = r.getSQLInsert("TBLPPTAFIDAVIT");
		    		stmt.executeUpdate(sql);	    		
		    }//close try 
		    finally {
		     // if (db != null) db.close();
		    }//close finally
		   
		}//close simpanAfidevit
		
		
		public static void simpanAfidevitUpdate(Hashtable data,Db db,String id_afidavit) throws Exception
		  {			
		  //  Db db = null;
		    String sql = "";		    
		    try{		      
		    	//	db = new Db();
		    		Statement stmt = db.getStatement();		    		
		    		String id_user = (String)data.get("id_user");	    	
		    		String id_award = (String)data.get("id_award");
		    		String id_hakmilikpb = (String)data.get("id_hakmilikpb");		    		
		    		String txtTujuan = (String)data.get("txtTujuan");
		    		String txtPerkara = (String)data.get("txtPerkara");
		    		String socMahkamah = (String)data.get("socMahkamah");		    		
		    		//insert
		    		SQLRenderer r = new SQLRenderer();
		    		r.update("id_afidavit", id_afidavit);
		    		r.add("id_award", id_award);	
		    		r.add("id_hakmilikpb", id_hakmilikpb);
		    		r.add("tujuan", txtTujuan);	
		    		r.add("perkara_rujukan", txtPerkara);	
		    		r.add("id_mahkamah", socMahkamah);	
		    		r.add("tarikh_masuk",r.unquote("sysdate"));
		    		r.add("id_masuk",id_user);    		
		    		sql = r.getSQLUpdate("TBLPPTAFIDAVIT");
		    		stmt.executeUpdate(sql);	    		
		    }//close try 
		    finally {
		     // if (db != null) db.close();
		    }//close finally
		   
		}//close simpanAfidevit
	
		
		
}
