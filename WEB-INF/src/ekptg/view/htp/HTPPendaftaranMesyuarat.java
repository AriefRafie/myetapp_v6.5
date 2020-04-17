package ekptg.view.htp;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.File;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.model.entities.Tblrujsuburusanstatusfail;
import ekptg.model.htp.FrmDaftarMesyuaratData;
import ekptg.model.htp.FrmKemaskiniMesyuaratData;
import ekptg.model.htp.FrmSenaraiFailPajakanKecilData;
import ekptg.model.htp.FrmUtilData;

public class HTPPendaftaranMesyuarat extends AjaxBasedModule {	
	/**
	 * 
	 */
	private static final long serialVersionUID = -238633166372759433L;
	private final String PATH="app/htp/";
	static Logger myLog = Logger.getLogger(ekptg.view.htp.HTPPendaftaranMesyuarat.class);
		
	public String doTemplate2() throws Exception	{
    	HttpSession session = this.request.getSession();  // bila perlu untuk dapatkan maklumat daripada session(tiap x user login)	
    	String vm = "app/htp/frmPenswastaanSenaraiFail.jsp";
     	String command = getParam("command");
     	String action = getParam("action"); // ACTION UTK SETUP PAGING SHJ
     	Hashtable permohonan = null;
     	//myLog.info("button 123 :: "+command);
		 String disability1 = "";
		 String disability2 = "";
		 String readability1 = "";
		 String tajukMesyuarat = "";
		 String tarikhMesyuarat = "";
		 String seksyen = "0";
		 String lokasi = "0";
         String mode = getParam("mode");

//     	vm = "app/htp/pendaftaran_permohonan.jsp";
    	
		String usid = "";  
		
		this.context.put("NoFail", "");
		this.context.put("NamaFail", "");
		
		String id_negeri = getParam("socNegeri");
		String id_kementerian = getParam("sockementerian");
		String id_agensi = getParam("socAgensi");
		
     	String socKementerian = "";
     	String socAgensi = "";
//     	Vector list = null;
     	
    	
    	
     	myLog.info("submit:"+submit);
     	/*Senarai fail from DB*/
   		Vector list = FrmUtilData.getSenaraiFailByUrusan("4", "", "", 0L);
	
    	if ("tambah".equals(command)){
        	disability1 = "";
        	readability1 = "";
        	this.context.put("readOnly",readability1);
       	    this.context.put("disable",disability1);
       	    
        	vm = PATH+"frmMesyuaratKemaskini.jsp";
        	
        	FrmKemaskiniMesyuaratData.setData(0);
       	 	list = FrmKemaskiniMesyuaratData.getData();
       	 	this.context.put("Mesyuarat",list);
       	 
           	this.context.put("selectSeksyen",HTML.SelectSeksyen("socSeksyen"));
          	this.context.put("selectLokasi",HTML.SelectLokasiMesyuarat("socLokasi"));
          	this.context.put("selectStatus",HTML.SelectStatusFail("socStatus"));
          	this.context.put("selectPegawai",HTML.SelectPegawai("socPegawai"));
          	//this.context.put("selectFail",HTML.SelectFail("socFail"));
          	//String SelectFailByIdSeksyen(String idSeksyen,
        			//String selectName, Long selectedValue, String disability)
          	this.context.put("selectFail",HTML.SelectFailByIdSeksyen("3","socFail",null,""));
       	    this.context.put("mode","New");
	       	    
       	    FrmKemaskiniMesyuaratData.setListAhliMesyuarat(0);
       	    list = FrmKemaskiniMesyuaratData.getListAhliMesyuarat();
	  	    this.context.put("SenaraiAhliMesyuarat",list);
	  	     
    		FrmKemaskiniMesyuaratData.setListAgendaMesyuarat(0);
   		    list = FrmKemaskiniMesyuaratData.getListAgendaMesyuarat();
   		    this.context.put("SenaraiAgendaMesyuarat",list);
         
    	}else if ("papar".equals(command)){
       	 disability1 = "disabled";
       	 readability1 = "readonly";
       	 vm = "app/htp/frmKemaskiniMesyuarat.jsp";
       	 
       	 view_mesyuarat(session);
       	 list = FrmKemaskiniMesyuaratData.getData();
       	 this.context.put("Mesyuarat",list);
       	 this.context.put("readOnly",readability1);
       	 this.context.put("disable",disability1);
       	 this.context.put("mode","View");
       	 
       	 Hashtable h = (Hashtable) list.get(0);
       	 
        	 this.context.put("selectSeksyen",HTML.SelectNegeri("socSeksyen",Long.parseLong(h.get("id_Seksyen").toString()),disability1));
        	 this.context.put("selectLokasi",HTML.SelectSeksyen("socLokasi",Long.parseLong(h.get("id_Lokasi").toString()),disability1));
     	     this.context.put("selectStatus",HTML.SelectUrusan("socStatus",Long.parseLong(h.get("id_Status").toString()),disability1));
     	     this.context.put("selectPegawai",HTML.SelectSuburusan("socPegawai",Long.parseLong(h.get("id_Pegawai").toString()),disability1));
     	     this.context.put("selectFail",HTML.SelectFail("socFail",Long.parseLong(h.get("id_Fail").toString()),disability1));
     	     
     	     listAhliMesyuarat(session);
     	     list = FrmKemaskiniMesyuaratData.getListAhliMesyuarat();
     	     this.context.put("SenaraiAhliMesyuarat",list);
     	     
     	     listAgendaMesyuarat(session);
   	     list = FrmKemaskiniMesyuaratData.getListAgendaMesyuarat();
   	     this.context.put("SenaraiAgendaMesyuarat",list);
    	}else if ("kemaskini".equals(command)){
        	 
        	 disability1 = "";
        	 readability1 = "";
        	 vm = "app/htp/frmKemaskiniMesyuarat.jsp";
        	 
        	 view_mesyuarat(session);
        	 list = FrmKemaskiniMesyuaratData.getData();
        	 this.context.put("Mesyuarat",list);
        	 this.context.put("readOnly",readability1);
        	 this.context.put("disable",disability1);
        	 this.context.put("mode","Update");
        	 
        	 Hashtable h = (Hashtable) list.get(0);
        	 
         	 this.context.put("selectSeksyen",HTML.SelectNegeri("socSeksyen",Long.parseLong(h.get("id_Seksyen").toString()),disability1));
         	 this.context.put("selectLokasi",HTML.SelectSeksyen("socLokasi",Long.parseLong(h.get("id_Lokasi").toString()),disability1));
      	     this.context.put("selectStatus",HTML.SelectUrusan("socStatus",Long.parseLong(h.get("id_Status").toString()),disability1));
      	     this.context.put("selectPegawai",HTML.SelectSuburusan("socPegawai",Long.parseLong(h.get("id_Pegawai").toString()),disability1));
      	     this.context.put("selectFail",HTML.SelectFail("socFail",Long.parseLong(h.get("id_Fail").toString()),disability2));
      	     
      	     listAhliMesyuarat(session);
    	     list = FrmKemaskiniMesyuaratData.getListAhliMesyuarat();
    	     this.context.put("SenaraiAhliMesyuarat",list);
    	     
    	     listAgendaMesyuarat(session);
	  	     list = FrmKemaskiniMesyuaratData.getListAgendaMesyuarat();
	  	     this.context.put("SenaraiAgendaMesyuarat",list);
        	 
         }
         else if ("simpan".equals(command)){
        	 	if ("tambahBaru".equals(mode)){
            	 vm = "app/htp/frmKemaskiniMesyuarat.jsp";
            	 simpanMesyuarat(session);
            	 
            	 view_mesyuarat(session);
            	 list = FrmKemaskiniMesyuaratData.getData();
            	 this.context.put("Mesyuarat",list);
            	 this.context.put("readOnly","readonly");
            	 this.context.put("disable","disabled");
            	 this.context.put("mode","View");
            	 
            	 Hashtable h = (Hashtable) list.get(0);
            	 
             	 this.context.put("selectSeksyen",HTML.SelectNegeri("socSeksyen",Long.parseLong(h.get("id_Seksyen").toString()),"disabled"));
             	 this.context.put("selectLokasi",HTML.SelectSeksyen("socLokasi",Long.parseLong(h.get("id_Lokasi").toString()),"disabled"));
          	     this.context.put("selectStatus",HTML.SelectUrusan("socStatus",Long.parseLong(h.get("id_Status").toString()),"disabled"));
          	     this.context.put("selectPegawai",HTML.SelectSuburusan("socPegawai",Long.parseLong(h.get("id_Pegawai").toString()),"disabled"));
          	     this.context.put("selectFail",HTML.SelectFail("socFail",Long.parseLong(h.get("id_Fail").toString()),"disabled"));
          	         
            	 listAhliMesyuarat(session);
        	     list = FrmKemaskiniMesyuaratData.getListAhliMesyuarat();
        	     this.context.put("SenaraiAhliMesyuarat",list);
        	     
        	     listAgendaMesyuarat(session);
    	  	     list = FrmKemaskiniMesyuaratData.getListAgendaMesyuarat();
    	  	     this.context.put("SenaraiAgendaMesyuarat",list);
        	 	}
        	 	else if ("kemaskiniMesyuarat".equals(mode)){
        	 		 
	               	 vm = "app/htp/frmKemaskiniMesyuarat.jsp";
	               	 this.context.put("mode","Update");
	               	 simpanMesyuarat(session);
	               	 
	               	
	               	 view_mesyuarat(session);
	            	 list = FrmKemaskiniMesyuaratData.getData();
	            	 this.context.put("Mesyuarat",list);
	            	 this.context.put("readOnly","readonly");
	            	 this.context.put("disable","disabled");
	            	 this.context.put("mode","View");
	            	 
	            	 Hashtable h = (Hashtable) list.get(0);
	            	 
	             	 this.context.put("selectSeksyen",HTML.SelectNegeri("socSeksyen",Long.parseLong(h.get("id_Seksyen").toString()),"disabled"));
	             	 this.context.put("selectLokasi",HTML.SelectSeksyen("socLokasi",Long.parseLong(h.get("id_Lokasi").toString()),"disabled"));
	          	     this.context.put("selectStatus",HTML.SelectUrusan("socStatus",Long.parseLong(h.get("id_Status").toString()),"disabled"));
	          	     this.context.put("selectPegawai",HTML.SelectSuburusan("socPegawai",Long.parseLong(h.get("id_Pegawai").toString()),"disabled"));
	          	     this.context.put("selectFail",HTML.SelectFail("socFail",Long.parseLong(h.get("id_Fail").toString()),"disabled"));
	          	     
	               	 listAhliMesyuarat(session);
	           	     list = FrmKemaskiniMesyuaratData.getListAhliMesyuarat();
	           	     this.context.put("SenaraiAhliMesyuarat",list);
	           	     
	           	     listAgendaMesyuarat(session);
	       	  	     list = FrmKemaskiniMesyuaratData.getListAgendaMesyuarat();
	       	  	     this.context.put("SenaraiAgendaMesyuarat",list);
        	 	}
    
         }
         else if ("SenaraiMesyuarat".equals(command)){
        	 vm = "app/htp/frmMesyuaratDaftar.jsp";
        	 
        	 FrmDaftarMesyuaratData.setCarianMesyuarat(tajukMesyuarat, tarikhMesyuarat, seksyen, lokasi);
        	 list = FrmDaftarMesyuaratData.getList();
        	 session.setAttribute("SenaraiMesyuarat",list);
        	 prepareItemForDisplay(session,list,10,"first");
          	 this.context.put("selectSeksyen",HTML.SelectSeksyen("socSeksyen",Long.parseLong(seksyen),""));
          	 this.context.put("selectLokasi",HTML.SelectLokasiMesyuarat("socLokasi",Long.parseLong(lokasi),""));
          	 this.context.put("txtTajukMsyrt", tajukMesyuarat);
          	 this.context.put("txdTarikhMsyrt", tarikhMesyuarat);

         }
         else if (("next".equals(command)) || ("previous".equals(command))){
        	 vm = "app/htp/frmMesyuaratDaftar.jsp";
        	 
        	 if (!"".equals(getParam("txtTajukMsyrt")))
        		 tajukMesyuarat = getParam("txtTajukMsyrt");
 			 if (!"".equals(getParam("txdTarikhMsyrt")))
 				tarikhMesyuarat = getParam("txdTarikhMsyrt");
 			 if (!"".equals(getParam("socSeksyen")))
 				seksyen = getParam("socSeksyen");
 			 if (!"".equals(getParam("socLokasi")))
 				lokasi = getParam("socLokasi");
 			
 			 
        	 FrmDaftarMesyuaratData.setCarianMesyuarat(tajukMesyuarat, tarikhMesyuarat, seksyen, lokasi);
        	 list = FrmDaftarMesyuaratData.getList();
        	 session.setAttribute("SenaraiMesyuarat",list);
        	 prepareItemForDisplay(session,list,10,command);
          	 this.context.put("selectSeksyen",HTML.SelectSeksyen("socSeksyen",Long.parseLong(seksyen),""));
          	 this.context.put("selectLokasi",HTML.SelectLokasiMesyuarat("socLokasi",Long.parseLong(lokasi),""));
          	 this.context.put("txtTajukMsyrt", tajukMesyuarat);
          	 this.context.put("txdTarikhMsyrt", tarikhMesyuarat);
         
         }else if ("maklumatrundingan".equals(command)){
          	 this.context.put("pagemode", "baru");
          	 this.context.put("socLuas", "");
     
         	 vm = PATH+"frmPembelianMaklumatRundingan.jsp";
         	 //Table yang digunakan TBLHTPMAKLUMATMSYRT
         	 
         }else{
        	 vm = PATH+"frmMesyuaratDaftar.jsp";
    	 
        	 if (!"".equals(getParam("txtTajukMsyrt")))
        		 tajukMesyuarat = getParam("txtTajukMsyrt");
			 if (!"".equals(getParam("txdTarikhMsyrt")))
				 tarikhMesyuarat = getParam("txdTarikhMsyrt");
			 if (!"".equals(getParam("socSeksyen")))
				 seksyen = getParam("socSeksyen");
			 if (!"".equals(getParam("socLokasi")))
				 lokasi = getParam("socLokasi");
						 
			 FrmDaftarMesyuaratData.setCarianMesyuarat(tajukMesyuarat, tarikhMesyuarat, seksyen, lokasi);
			 list = FrmDaftarMesyuaratData.getList();
			 myLog.info(list.size());
			 session.setAttribute("Senarai",list);
			 this.context.put("senaraiFail",list);
			 setupPage(session,action,list);
			 //prepareItemForDisplay(session,list,10,"first");
			 this.context.put("selectSeksyen",HTML.SelectSeksyen("socSeksyen",Long.parseLong(seksyen),""));
			 this.context.put("selectLokasi",HTML.SelectLokasiMesyuarat("socLokasi",Long.parseLong(lokasi),""));
			 this.context.put("txtTajukMsyrt", tajukMesyuarat);
			 this.context.put("txdTarikhMsyrt", tarikhMesyuarat);
    	
         }
	   	//this.context.put("senaraiFail",list);
		//setupPage(session,action,list);
    	System.out.println(vm);
		return vm;
    	   	
}
	
private void view_mesyuarat(HttpSession session) throws Exception {
    int id = Integer.parseInt(getParam("idMesyuarat"));
    FrmKemaskiniMesyuaratData.setData(id);
	   
}

private int simpanMesyuarat(HttpSession session) throws Exception {
	int idMesyuarat;
	System.out.println(getParam("idMesyuarat"));
	if (getParam("idMesyuarat") == "" || Integer.parseInt(getParam("idMesyuarat").toString())== 0){
		
			Hashtable h = new Hashtable();
		    h.put("bil_Mesyuarat", getParam("txtBilMsyrt"));
		    h.put("tajuk_Mesyuarat", getParam("txtTajukMsyrt"));
		    h.put("kategori_Mesyuarat", getParam("socKategoriMsyrt"));
		    h.put("tarikh_Mesyuarat", getParam("txdTarikh"));
		    h.put("masa_Mesyuarat_Dari", getParam("txtDariJam"));
		    h.put("waktu_Mesyuarat_Dari", getParam("socWaktuDariJam"));
		    h.put("masa_Mesyuarat_Hingga", getParam("txtHinggaJam"));
		    h.put("waktu_Mesyuarat_Hingga", getParam("socWaktuHinggaJam"));
		    h.put("id_Seksyen",getParam("socSeksyen"));
		    h.put("id_Status",getParam("socStatus"));
			h.put("id_Pegawai", getParam("socPegawai"));
			h.put("id_Lokasi", getParam("socLokasi"));
			h.put("id_Fail", getParam("socFail"));
			h.put("catatan", getParam("txtCatatan"));
			idMesyuarat = FrmKemaskiniMesyuaratData.add(h);
		    
		    return idMesyuarat;
		}
		else{
			Hashtable h = new Hashtable();
			h.put("id_Mesyuarat", Integer.parseInt(getParam("idMesyuarat")));
			h.put("bil_Mesyuarat", getParam("txtBilMsyrt"));
		    h.put("tajuk_Mesyuarat", getParam("txtTajukMsyrt"));
		    h.put("kategori_Mesyuarat", getParam("socKategoriMsyrt"));
		    h.put("tarikh_Mesyuarat", getParam("txdTarikh"));
		    h.put("masa_Mesyuarat_Dari", getParam("txtDariJam"));
		    h.put("waktu_Mesyuarat_Dari", getParam("socWaktuDariJam"));
		    h.put("masa_Mesyuarat_Hingga", getParam("txtHinggaJam"));
		    h.put("waktu_Mesyuarat_Hingga", getParam("socWaktuHinggaJam"));
		    h.put("id_Seksyen",getParam("socSeksyen"));
		    h.put("id_Status",getParam("socStatus"));
			h.put("id_Pegawai", getParam("socPegawai"));
			h.put("id_Lokasi", getParam("socLokasi"));
			h.put("id_Fail", getParam("socFail"));
			h.put("catatan", getParam("txtCatatan"));
			idMesyuarat = FrmKemaskiniMesyuaratData.update(h);
				    
			return idMesyuarat;
		}
	   
	  }

	private void listAhliMesyuarat(HttpSession session) throws Exception {
		
		int id = Integer.parseInt(getParam("idMesyuarat"));
    	FrmKemaskiniMesyuaratData.setListAhliMesyuarat(id);

  }
private void listAgendaMesyuarat(HttpSession session) throws Exception {
		int id = Integer.parseInt(getParam("idMesyuarat"));
    	FrmKemaskiniMesyuaratData.setListAgendaMesyuarat(id);

  }

	private void paparMaklumatPermohonan(String id_permohonan)throws Exception  {
		// untuk papar maklumat pemohon
		
		  Db db = null;
		    String sql = "";
		    
		    try {
		      
		    Vector list =null;
		    list =new Vector();
		    db = new Db();
		    Statement stmt = db.getStatement();
		     		   
		    sql= " select n.nama_negeri,k.nama_kementerian,a.nama_agensi,s.nama_suburusan," +
		      " f.no_fail,f.tarikh_daftar_fail,h.no_rujukan_kjp,h.no_rujukan_lain," +
		      " p.tarikh_surat,p.tarikh_terima,p.tujuan," +
		      " FROM tblrujnegeri n,Tblpfdfail f,tblrujkementerian k,tblpermohonan p,"+
		      " tblhtppermohonan h,tblrujagensi a,tblrujsuburusan s where "+
		      " f.ID_NEGERI=n.ID_NEGERI and f.id_kementerian=k.id_kementerian "+
		      " and p.ID_FAIL=f.ID_FAIL and p.id_permohonan=h.ID_PERMOHONAN  "+
		      " and h.ID_AGENSI=a.id_agensi and f.ID_SUBURUSAN=s.ID_SUBURUSAN  "+
		      " and p.id_permohonan = '"+id_permohonan+"'";
		    
		    ResultSet rs = stmt.executeQuery(sql);
		    Hashtable h;
		    int bil = 1;
		    int count = 0;
		    while (rs.next()){
		    	
		    	h = new Hashtable ();
		    	h.put("nama_negeri",rs.getString("nama_negeri"));
		    	h.put("nama_kementerian",rs.getString("nama_kementerian"));
		    	h.put("nama_agensi",rs.getString("nama_agensi"));
		    	h.put("nama_suburusan",rs.getString("nama_suburusan"));
		    	h.put("no_fail",rs.getString("no_fail"));
		    	h.put("tarikh_daftar_fail",rs.getString("tarikh_daftar_fail"));
		    	h.put("no_rujukan_kjp",rs.getString("no_rujukan_kjp"));
		    	h.put("no_rujukan_lain",rs.getString("no_rujukan_lain"));
		    	h.put("tarikh_surat",rs.getString("tarikh_surat"));
		    	h.put("tarikh_terima",rs.getString("tarikh_terima"));
		    	h.put("tujuan",rs.getString("tujuan"));
		    	
		    	list.addElement(h);
		    }
		    
		    }finally{
		    	if (db != null) db.close();
		    }
	}
     
		public void simpanPendaftaranPermohonan(String no_fail_seksyen) throws Exception {
		Db db = null;
		String sql = "";
		try {
			sql = "insert into table1 ";
			
		} catch (Exception e) {
		}
		finally {
			 if (db != null) db.close();
		}
	}
	
	public Vector cariFail(String nofail,String namafail,int idNegeri) throws Exception {
		
		//myLog.info("testing");
		
		    Db db = null;
		    String sql = "";
		    try {
		      db = new Db();
		      Statement stmt = db.getStatement();
		      String chkNoFail = nofail.trim();
			  String chkNamaFail = namafail.trim();
//			  int chkIdNegeri = idNegeri.trim();
			  
		      sql = "SELECT A.NO_FAIL,A.TAJUK_FAIL,C.NAMA_NEGERI,D.KETERANGAN,C.ID_NEGERI"+

		      	    " FROM TBLPFDFAIL A,"+
		      	    " TBLPERMOHONAN B,"+
		      	    " TBLRUJNEGERI C,"+
		      	    " TBLRUJSTATUS D"+

		      	    " WHERE A.ID_FAIL = B.ID_FAIL"+
		      	    " AND C.ID_NEGERI = A.ID_NEGERI"+
		      	    " AND D.ID_STATUS = A.ID_STATUS";
//		      "and no_fail like '%"+NoFail+"%'";
		      boolean setLimit = true;
		      if (nofail != null) {
					if (!nofail.trim().equals("")) {
						setLimit = false;
						sql = sql + " AND ( UPPER(A.NO_FAIL) LIKE '%' ||'"
								+ chkNoFail.toUpperCase() + "'|| '%' ) " ;
					}
				}
				
		      //namafail
				if (namafail != null) {
					if (!namafail.trim().equals("")) {
						setLimit = false;
						sql = sql + " AND UPPER(A.TAJUK_FAIL) LIKE '%' ||'"
								+ chkNamaFail.toUpperCase() + "'|| '%' ";
					}
				}
				
			
				if (idNegeri != 0) {
						setLimit = false;
						sql = sql + " AND UPPER(C.ID_NEGERI) LIKE '%' ||'" +
			            idNegeri + "'|| '%'  ";
					
				}
		      
				myLog.info("CARIFAIL 11111:: "+sql);
		      ResultSet rs = stmt.executeQuery(sql);
		      Vector v = new Vector();
		     int bil = 1;
		      while (rs.next()) {
		        Hashtable h = new Hashtable();
		        h.put("bil",bil);
		        h.put("no_fail", rs.getString("NO_FAIL"));
		        h.put("nama_negeri", rs.getString("NAMA_NEGERI"));
		        h.put("tajuk_fail", rs.getString("TAJUK_FAIL"));
		        h.put("keterangan", rs.getString("KETERANGAN"));
		        v.addElement(h);
		        bil++;
		      }
		      return v;
		    }
		    finally {
		      if (db != null) db.close();
		    }
	}
	
	
	public void setupPage(HttpSession session,String action,Vector list) {
		try {
		
		this.context.put("totalRecords",list.size());
		int page = getParam("page") == "" ? 1:getParamAsInteger("page");
		
		int itemsPerPage;
		if (this.context.get("itemsPerPage") == null || this.context.get("itemsPerPage") == "") {
			itemsPerPage = getParam("itemsPerPage") == "" ? 10:getParamAsInteger("itemsPerPage");
		} else {
			itemsPerPage = (Integer)this.context.get("itemsPerPage");
		}
	    
	    if ("getNext".equals(action)) {
	    	page++;
	    } else if ("getPrevious".equals(action)) {
	    	page--;
	    } else if ("getPage".equals(action)) {
	    	page = getParamAsInteger("value");
	    } else if ("doChangeItemPerPage".equals(action)) {
	       itemsPerPage = getParamAsInteger("itemsPerPage");
	    }
	    	
	    Paging paging = new Paging(session,list,itemsPerPage);
		
		if (page > paging.getTotalPages()) page = 1; //reset page number
		this.context.put("senaraiFail",paging.getPage(page));
	    this.context.put("page", new Integer(page));
	    this.context.put("itemsPerPage", new Integer(itemsPerPage));
	    this.context.put("totalPages", new Integer(paging.getTotalPages()));
	    this.context.put("startNumber", new Integer(paging.getTopNumber()));
	    this.context.put("isFirstPage",new Boolean(paging.isFirstPage()));
	    this.context.put("isLastPage", new Boolean(paging.isLastPage()));
	        
		} catch (Exception e) {
			e.printStackTrace();
			this.context.put("error",e.getMessage());
		}	
	}
	
	private void simpanFail(HttpSession session, Long idfail) throws Exception {
		  Hashtable h = null;
		  h = new Hashtable();
		  String kodNegeriMampu = "";
		  String kodKementerianMampu = "";
		  int idNegeri = 0;
		  int idUrusan = 0;
		  int idKementerian = 0;
		  String idSeksyen ="";
		  int idTarafkeselamatan = 1; /** TERBUKA*/
		  int idSubUrusan = 8; /** TERBUKA*/
		  int idLokasi = 1;
		  int idFaharasat = 1;
		  String idCatatan = "TIADA";
		  String idFailRoot = "TIADA";
		  String tarikhMasuk = getParam ("txttarikhbukafail");
		  
		  Vector vecFail = new Vector();
		  
//		  idNegeri = Integer.parseInt(getParam("socNegeri"));
		  idNegeri = getParamAsInteger("socNegeri");
		  idUrusan = Integer.parseInt("4");
		  idKementerian = Integer.parseInt(getParam("sockementerian"));
		  idSeksyen = FrmUtilData.getSeksyenMengikutPengguna((String)session.getAttribute("_ekptg_user_id"));
		  
		  vecFail = FrmSenaraiFailPajakanKecilData.getFileCount(idNegeri,idUrusan);
		  kodNegeriMampu = FrmSenaraiFailPajakanKecilData.getNegeriByMampu(idNegeri);
		  kodKementerianMampu = FrmSenaraiFailPajakanKecilData.getKementerianByMampu(idNegeri);
		  //biFail = vecFail.size();
		  int fileSeq = 0;
		  
		  fileSeq = File.getSeqNo(Integer.parseInt( idSeksyen) , idUrusan, idKementerian, idNegeri);
		  System.out.println("FrmPajakanKecil:simpanFail::fileSeq:::"+fileSeq);

		  String noFail = "JKPTG/101/906/";
		  noFail += kodKementerianMampu+"/"+kodNegeriMampu+"-"+fileSeq;
   
		  int idmasuk = Integer.parseInt((String)session.getAttribute("_ekptg_user_id"));
		  h.put("id_Fail", idfail);
		  h.put("id_Tarafkeselamatan", idTarafkeselamatan);
		  h.put("id_Seksyen", Integer.parseInt( idSeksyen));
		  h.put("id_Urusan", idUrusan);
		  h.put("id_Suburusan", idSubUrusan);
		  h.put("tajuk_Fail", getParam("txttajuk"));
		  h.put("no_Fail", noFail);
		  h.put("id_Negeri", idNegeri);
		  h.put("id_Kementerian",idKementerian);
		  h.put("flag_Fail","1");
		  h.put("id_Status", 7);
		  h.put("id_Masuk", idmasuk);
		  h.put("tarikh_Bukafail", tarikhMasuk);
		  h.put("tarikhMasuk", tarikhMasuk);
		  h.put("id_Lokasi", idLokasi);
		  h.put("id_Faharasat", idFaharasat);
		  h.put("catatan", idCatatan);
		  h.put("no_Failroot", idFailRoot);
		  FrmUtilData.simpanFail(h);
		  

	  }

	  private void updatePermohonan(HttpSession session)throws Exception{
		  String id = getParam("id_kemaskini");
		    //String kod_cara_bayar = getParam("kod_cara_bayar");
		    //String keterangan = getParam("keterangan");
		    //String id_kemaskini = getParam("id_kemaskini");
		    //Date tarikh_kemaskini = getParam("tarikh_kemaskini");
		    //Date tarikh_kemaskini = new Date();
		    //CaraBayarData.update(Long.parseLong(id_carabayar), kod_cara_bayar,
		    	//	keterangan,Long.parseLong(id_kemaskini),tarikh_kemaskini);
	  }

	
	  
	  private String simpanPermohonan(HttpSession session,String idFail, int flag)throws Exception {
		  Hashtable data = null;
		  data = new Hashtable();
		  String txdBukafail = "";
		  int idNegeri = 0;
		  int idAgensi = 0;
		  int idFaharasat = 1;
		  int idStatus = 7;/**AKTIF*/
		  String tajuk = "TIADA";
		  String strTiada = "TIADA";
		  int idMasuk = 1;

		  txdBukafail = getParam("txttarikhbukafail"); 
		  tajuk = getParam("txttajuk");
		  idMasuk = Integer.parseInt((String)session.getAttribute("_ekptg_user_id"));
		  idAgensi = Integer.parseInt(getParam("socAgensi"));
		  
		  if(flag==0){
			  //data.put("IdFail", fail);
			  //else
			  data.put("id_Fail",idFail);
			  //id_PejabatJKPTG /** auto simpan*/
			  data.put("no_Permohonan",strTiada);
			  data.put("no_Perserahan",strTiada); 
			  data.put("tarikh_SuratKJP", txdBukafail);
			  data.put("tarikh_Terima", txdBukafail);
			  data.put("tajuk",tajuk);
			  data.put("tarikh_Masuk", txdBukafail);
	    	  data.put("id_Agensi", idAgensi);
	    	  data.put("id_Jenistanah", 1);
	    	  data.put("id_Pegawai", idMasuk);
	    	  data.put("no_Failkjp", strTiada);
	    	  data.put("no_Faillain", strTiada);
	    	  data.put("tarikh_Agihan", txdBukafail);

	    	  data.put("id_Masuk", idMasuk);
		  }
		  
		  return FrmUtilData.simpanPermohonanHTP(data);
	  }
	  public void simpanStatus(HttpSession session,Long idPermohonan,Long idFail){
		  Long idMasuk = Long.parseLong((String)session.getAttribute("_ekptg_user_id"));

		  Tblrujsuburusanstatusfail s = new Tblrujsuburusanstatusfail();
		  s.setIdPermohonan(idPermohonan);
		  s.setIdSuburusanstatus(Long.parseLong("2461"));
		  s.setIdFail(idFail);
		  s.setAktif("1");
		  s.setIdMasuk(idMasuk);
		  FrmUtilData.simpanStatusPermohonan(s);
	  } 
	  
	    private void label(Hashtable permohonan){
	    	
			String labelNegeri = (String)permohonan.get("negeri");
			String labelKementerian = (String)permohonan.get("kementerian");
			String labelAgensi = (String)permohonan.get("agensi");
			String labelTajuk = (String)permohonan.get("tujuan");
			String labelNoFail = (String)permohonan.get("fail");
			String labelNoFailLain = (String)permohonan.get("rujukankjp");
			String labelTarikhSuratKJP = (String)permohonan.get("rtterima");
			String labelTarikhBukaFail = (String)permohonan.get("tdaftar");
		
			
			this.context.put("labelNegeri", labelNegeri);
			this.context.put("labelKementerian", labelKementerian);
			this.context.put("labelAgensi", labelAgensi);
			this.context.put("labelTajuk", labelTajuk);
			this.context.put("labelNoFail", labelNoFail);
			this.context.put("labelNoFailLain", labelNoFailLain);
			this.context.put("labelTarikhSuratKJP", labelTarikhSuratKJP);
			this.context.put("labelTarikhBukaFail", labelTarikhBukaFail);

	    }

		private void prepareItemForDisplay(HttpSession session, Vector objVector, int cntItemPage, String command)
		  {
		    int x;
		    int startno = 0;
		    if (command == null) command = "first";
		    if (session.getAttribute("_portal_startno") != null) startno = ((Integer)session.getAttribute("_portal_startno")).intValue();
		    if (command.equals("previous"))
		      if (startno == objVector.size()) {
		        x = startno % cntItemPage;
		        if (x > 0) {
		          startno -= x;
		          startno -= cntItemPage;
		        } else {
		          startno -= cntItemPage * 2;
		          if (startno < 0) startno = 0;
		        }
		      } else {
		        startno -= cntItemPage * 2;
		        if (startno < 0) startno = 0;
		      }
		    else if (command.equals("first"))
		      startno = 0;
		    	
		    else if (command.equals("last"))
		      x = cntItemPage;
		    else if (command.equals("back"))
		      if (startno == objVector.size()) {
		        x = startno % cntItemPage;
		        if (x == 0) x = cntItemPage;
		        startno -= x;
		      } else {
		        startno -= cntItemPage;
		        if (startno < 0) startno = 0;

		      }
		    
		    Vector moduleVector = new Vector();
		    int i = 0; int cnt = 0;
		    if (objVector.size() > 0) {
		      cnt = 0; for (i = startno; (cnt < cntItemPage) && (i < objVector.size()); ) {
		        moduleVector.addElement(objVector.elementAt(i));

		        ++i; ++cnt;
		      }

		    }

		    session.setAttribute("_portal_moduleVector", moduleVector);
		   
		    this.context.put("startnoInt", new Integer(startno));
		    this.context.put("totalInt", new Integer(objVector.size()));
		    
		   
		    startno = i;
		   
		    session.setAttribute("_portal_startno", new Integer(startno));
		    
		  }

	    
}

