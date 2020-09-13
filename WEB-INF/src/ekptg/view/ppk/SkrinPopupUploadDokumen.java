package ekptg.view.ppk;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import ekptg.engine.StateLookup;
import ekptg.helpers.DB;
import ekptg.helpers.Paging2;
import ekptg.model.ppk.FrmPrmhnnSek8DaftarSek8InternalData;

public class SkrinPopupUploadDokumen extends AjaxBasedModule {
	static Logger myLogger = Logger.getLogger(SkrinPopupUploadDokumen.class);
	private final String PATH="app/ppk/";
	private String vm = PATH +"SkrinPopupDokumen.jsp"; 
	
	HttpSession session = null;
	String action = null;
	Vector listSupportingDoc = null;
	
	FrmPrmhnnSek8DaftarSek8InternalData logic_A = null;
	
	public String doTemplate2() throws Exception {		
		logic_A = new FrmPrmhnnSek8DaftarSek8InternalData();
		String jenisDoc = getParam("id_jenisDoc");
		session = request.getSession();
		String usid = (String) session.getAttribute("_ekptg_user_id");
		action = getParam("action");
		String submit =  getParam("command");
		String id_jenisDoc =  getParam("id_jenisDoc");
		myLogger.info("id_jenisDoc= "+id_jenisDoc);
		
		this.context.put("submit",submit);
		String id_Permohonan =  getParam("id_Permohonan");
		myLogger.info("id_Permohonan= "+id_Permohonan);
		String IdSimati =  getParam("IdSimati");
		this.context.put("success","");
		this.context.put("disable", "");
		this.context.put("id_jenisDoc", id_jenisDoc);
		//check dah upload atau belum
		
		if (checkDahUpload(IdSimati) == true) {
			this.context.put("success","fail");
			this.context.put("disable", "disable");
			
		}
		
		this.context.put("id_Permohonan",id_Permohonan);
		this.context.put("usid",usid);
		this.context.put("IdSimati",IdSimati);
		
		
		if ("uploadSupDoc".equals(submit)) {
			nakUpload(id_Permohonan,session,usid, IdSimati,id_jenisDoc);
			this.context.put("success","success");
			myLogger.info("READ HERE1");
			myLogger.info("id_Permohonan= "+id_Permohonan);
			logic_A.setSupportingDoc(id_Permohonan, jenisDoc);
			listSupportingDoc = logic_A.setSupportingDoc(id_Permohonan, jenisDoc);
			myLogger.info("READ HERE2");
			
			this.context.put("ViewSupportingDoc", listSupportingDoc);
			this.context.put("disable", "disable");
		}
		
		
		return vm;
	}
	
	
	
	//TODO
	
	
	public boolean checkDahUpload(String IdSimati) throws Exception {

		

		Db db = null;
		boolean a = false;
		
		String sql3 = "";
		
		try {
			db = new Db();

			Statement stmt4 = db.getStatement();
			sql3 = "SELECT * FROM TBLPPKDOKUMENSIMATI WHERE ID_SIMATI="+IdSimati;
			myLogger.info("SQL DAH ADA KE BELUM SUPPORTING DOKUMEN" + sql3);
			ResultSet rs4 = stmt4.executeQuery(sql3);

			
			while (rs4.next()) {
				a = true;
			}
		} finally {
			if (db != null)
				db.close();

		}
		// return false;
		myLogger.info("A = "+a);
		return a;
		
	}
			
			
	public void nakUpload(String id_permohonan,HttpSession session, String id_Masuk, String id_Simati, String id_jenisDoc)
			throws Exception{
			    Db db = null;
			    Connection conn = null;
			    String getPemohon = "";
			    myLogger.info("id_jenisDoc:"+id_jenisDoc);  
			    try {
			    	
				      db = new Db();
			          conn = db.getConnection();
			          conn.setAutoCommit(false);
				      Statement stmt = db.getStatement();
				      
				      
				      //getPemohon = " SELECT ID_PEMOHON FROM TBLPPKPERMOHONAN WHERE ID_PERMOHONAN = '"+id_permohonan+"' ";
				      getPemohon = " SELECT P.ID_PEMOHON,PM.NAMA_PEMOHON,PM.NO_KP_BARU,PM.NO_KP_LAMA,PM.NO_KP_LAIN,PM.JENIS_KP  " +
				      		" FROM TBLPPKPERMOHONAN P,TBLPPKPEMOHON PM WHERE P.ID_PERMOHONAN = '"+id_permohonan+"' " +
				      		" AND P.ID_PEMOHON = PM.ID_PEMOHON" ;
				      myLogger.info("-------1:"+getPemohon);  
				      ResultSet rs1 = stmt.executeQuery(getPemohon);
				        
						while (rs1.next()) {
							
							
							uploadFiles(db,conn,id_Simati, id_Masuk, id_jenisDoc );
						}
				     
						
				      

				      
								 		

										
				  
				  
					    
			    conn.commit();
			}
			catch (SQLException se) {
				try {
					conn.rollback();
				} catch (SQLException se2) {
					throw new Exception("Rollback error:"+se2.getMessage());
				}
				throw new Exception("Ralat Muat naik Dokumen:"+se.getMessage());
			}
			finally {
			  if (db != null) db.close();
			}
			
			
			}

	private void uploadFiles(Db db,Connection conn, String idSimati, String id_Masuk, String id_jenisDoc) throws Exception {
		myLogger.info("Baca uploadFiles: id_jenisDoc = "+id_jenisDoc); 
		myLogger.info("Baca uploadFiles:--------------"); 
		String idSimati1 = idSimati;
		DiskFileItemFactory factory = new DiskFileItemFactory();
	    ServletFileUpload upload = new ServletFileUpload(factory);
	    myLogger.info("Baca uploadFiles2:--------------"); 
	    List items = upload.parseRequest(this.request);
	    Iterator itr = items.iterator();
	    myLogger.info("Baca uploadFiles3:--------------"); 
	    while (itr.hasNext()) {    	
	      FileItem item = (FileItem)itr.next();
	      if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
	    	  System.out.println("item.getName = "+ item.getName());
	    	  saveData(item,db,conn,idSimati1,id_Masuk, id_jenisDoc);
	      }
	    }
	  }
	
	private void saveData(FileItem item,Db db,Connection conn, String idSimati, String id_Masuk, String id_jenisDoc) throws Exception {
		//Db db = null;
	
    try {
    	db = new Db();
    	SQLRenderer r = new SQLRenderer();
    	Connection con = db.getConnection();
    	con.setAutoCommit(false);
    	
    	long idDokumen = DB.getNextID(db, "TBLPPKDOKUMENSIMATI_SEQ");
    	String idSimati1 = idSimati;
    	myLogger.info("idSimati1********* = "+idSimati1);
    	myLogger.info("id_Masuk********* = "+id_Masuk);
    	myLogger.info("id_jenisDoc********* = "+id_jenisDoc);
    	
    	//String id_permohonansimati = getParam("id_permohonansimati_atheader");
    	PreparedStatement ps = con.prepareStatement("INSERT INTO TBLPPKDOKUMENSIMATI (ID_DOKUMEN, ID_SIMATI,ID_JENISDOKUMEN,NAMA_DOKUMEN, FORMAT, KANDUNGAN, ID_MASUK, TARIKH_MASUK) VALUES (?,?,?,?,?,?,?,"+r.unquote("sysdate")+")");	
    	ps.setLong(1,idDokumen);
    	ps.setString(2,idSimati1);
    	ps.setString(3,id_jenisDoc);
    	ps.setString(4,item.getName());
    	ps.setString(5,item.getContentType());
    	ps.setBinaryStream(6,item.getInputStream(),(int)item.getSize());
    		
    	ps.setString(7,id_Masuk);
    	//ps.setString(4,getParam("id_permohonansimati_atheader"));
    	myLogger.info("Baca SaveData:---------------"); 
    	ps.executeUpdate();	
    	myLogger.info("Baca SaveData 2:---------------"); 
        con.commit();
        myLogger.info("Baca SaveData 3:---------------"); 
    } finally {
	      if (db != null) db.close();
    }
}
	
	
	
	private void displayDokumen(String id_permohonan,String id_pembatalan,String id_penarikanbalik,String id_bantahan,String id_permintaanukur,String id_award,String id_hakmilik,String flag_skrin,String jenis_doc,Db db,String nama_dokumen,String tajuk,String id_tanah,String id_notisawam,String id_buktipenyampaian,String id_borangh) throws Exception{
		List<Hashtable>  list = null;	
		list = setListDokumen(id_permohonan,id_pembatalan,id_penarikanbalik,id_bantahan,id_permintaanukur,id_award,id_hakmilik,flag_skrin,jenis_doc,db,nama_dokumen,tajuk,id_tanah,id_notisawam,id_buktipenyampaian,id_borangh);
		context.put("SenaraiFail", list);	
		setupPage(session,action,list);
	}
	
	private void displayFail(String NO_FAIL,String NAMA_PROJEK,String ID_NEGERI,String ID_DAERAH,Db db,String jenis_seksyen) throws Exception{
		List<Hashtable>  list = null;	
		list = getFail(NO_FAIL,NAMA_PROJEK,ID_NEGERI,ID_DAERAH,db,jenis_seksyen);	
		context.put("SenaraiFail", list);	
		setupPage(session,action,list);
	}
	
	private void listHakmilik(String id_permohonan,Db db) throws Exception{
		List<Hashtable>  listhakmilik = null;	
		listhakmilik = getHakmilik(id_permohonan,db);	
		context.put("listhakmilik", listhakmilik);	
	}
	
	
	
	 public static void simpanKeteranganDoc(String id_Dokumen,String keterangan,String id_user,Db db) throws Exception{
			
			//	  Db db = null;
				  String sql = "";

				  try{
					  	
					  //	db = new Db();
			    		Statement stmt = db.getStatement();			    		
			    		SQLRenderer r = new SQLRenderer();
			    		r.update("id_Dokumen", id_Dokumen);		    				
			    		r.add("tajuk", keterangan);
			    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
			    		r.add("id_kemaskini",id_user);    		
			    		sql = r.getSQLUpdate("Tblpptdokumen");
			    		stmt.executeUpdate(sql);
			    		
			    		
			    		
			    }//close try 
			    finally {
			      //if (db != null) db.close();
			    }//close finally
			   
			  }//close simpanSj
	
	
	Hashtable maklumatPermohonan = null;
	public Hashtable maklumatPermohonan(String id_permohonan,Db db) throws Exception {
		maklumatPermohonan = new Hashtable();
		maklumatPermohonan.clear();
	//	Db db = null;
		String sql = "";
		try {
		//	db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = " SELECT P.ID_PERMOHONAN,F.NO_FAIL,P.NO_RUJUKAN_PTD,P.NO_RUJUKAN_PTG,P.NO_RUJUKAN_UPT,F.ID_FAIL,UPPER(P.TUJUAN) AS PROJEK,D.NAMA_DAERAH AS NAMA_DAERAH,D.ID_DAERAH, N.NAMA_NEGERI AS NAMA_NEGERI, N.ID_NEGERI,F.ID_SUBURUSAN, "+
					" TO_CHAR(P.TARIKH_PERMOHONAN,'DD/MM/YYYY')  AS TARIKH_MOHON, (SELECT COUNT(*) FROM TBLPPTHAKMILIK H WHERE H.ID_PERMOHONAN = P.ID_PERMOHONAN) AS TOTAL_HAKMILIK "+
					" FROM TBLPPTPERMOHONAN P, TBLPFDFAIL F,TBLRUJDAERAH D,TBLRUJNEGERI N "+
					" WHERE P.ID_FAIL = F.ID_FAIL " +
					" AND P.ID_DAERAH = D.ID_DAERAH  AND F.ID_NEGERI = N.ID_NEGERI AND P.ID_PERMOHONAN = '"+id_permohonan+"' " ;
				
			myLogger.info("SELECT maklumatPermohonan :"+sql);	
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
							
				if (rs.getString("ID_PERMOHONAN") == null) {	h.put("ID_PERMOHONAN", ""); } else { h.put("ID_PERMOHONAN", rs.getString("ID_PERMOHONAN").toUpperCase()); }
				if (rs.getString("NO_FAIL") == null) {	h.put("NO_FAIL", ""); } else { h.put("NO_FAIL", rs.getString("NO_FAIL").toUpperCase()); }	
				if (rs.getString("NO_RUJUKAN_PTD") == null) {	h.put("NO_RUJUKAN_PTD", ""); } else { h.put("NO_RUJUKAN_PTD", rs.getString("NO_RUJUKAN_PTD").toUpperCase()); }
				if (rs.getString("NO_RUJUKAN_UPT") == null) {	h.put("NO_RUJUKAN_UPT", ""); } else { h.put("NO_RUJUKAN_UPT", rs.getString("NO_RUJUKAN_UPT").toUpperCase()); }
				if (rs.getString("NO_RUJUKAN_PTG") == null) {	h.put("NO_RUJUKAN_PTG", ""); } else { h.put("NO_RUJUKAN_PTG", rs.getString("NO_RUJUKAN_PTG").toUpperCase()); }
				if (rs.getString("PROJEK") == null) {	h.put("PROJEK", ""); } else { h.put("PROJEK", rs.getString("PROJEK").toUpperCase()); }
				if (rs.getString("NAMA_DAERAH") == null) {	h.put("NAMA_DAERAH", ""); } else { h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH").toUpperCase()); }
				if (rs.getString("NAMA_NEGERI") == null) {	h.put("NAMA_NEGERI", ""); } else { h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI").toUpperCase()); }
				if (rs.getString("TARIKH_MOHON") == null) {	h.put("TARIKH_MOHON", ""); } else { h.put("TARIKH_MOHON", rs.getString("TARIKH_MOHON").toUpperCase()); }
				if (rs.getString("TOTAL_HAKMILIK") == null) {	h.put("TOTAL_HAKMILIK", ""); } else { h.put("TOTAL_HAKMILIK", rs.getString("TOTAL_HAKMILIK").toUpperCase()); }
				
			}
			return h;
		} finally {
		//	if (db != null)
		//		db.close();
		}
	}
	
	Vector listDokumen = null;
	public Vector setListDokumen(String id_permohonan,String id_pembatalan,String id_penarikanbalik,String id_bantahan,String id_permintaanukur,String id_award,String id_hakmilik,String flag_skrin,String jenis_dokumen,Db db,String nama_dokumen,String tajuk,String id_tanah,String id_notisawam,String id_buktipenyampaian,String id_borangh) throws Exception {
	   //Db db = null;
	    //listDokumen.clear();
	    String sql = "";
	    
	    try {
	     // db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      sql = " SELECT a.id_permohonan, a.id_Dokumen, a.nama_Fail, a.jenis_Mime, a.tajuk, a.keterangan, a.content, a.jenis_dokumen,a.id_hakmilik  FROM Tblpptdokumen a " +
	      		" WHERE a.id_Dokumen is not null ";
	      
	      
	      
	      
	      if(!id_borangh.equals(""))
	      {
	    	  sql += " and a.id_borangh = '"+id_borangh+"' ";
	      }
	      
	      if(!id_buktipenyampaian.equals(""))
	      {
	    	  sql += " and a.id_buktipenyampaian = '"+id_buktipenyampaian+"' ";
	      }
	      
	      if(!id_notisawam.equals(""))
	      {
	    	  sql += " and a.id_notisawam = '"+id_notisawam+"' ";
	      }
	      
	      if(!id_permohonan.equals(""))
	      {
	    	  sql += " and a.id_permohonan = '"+id_permohonan+"' ";
	      }     
	      
	      if(!id_pembatalan.equals(""))
	      {
	    	  sql += " and a.id_pembatalan = '"+id_pembatalan+"' ";
	      }
	      
	      if(!id_penarikanbalik.equals(""))
	      {
	    	  sql += " and a.id_penarikanbalik = '"+id_penarikanbalik+"' ";    	 
	      }
	      
	      if(!id_bantahan.equals(""))
	      {
	    	  sql += " and a.id_bantahan = '"+id_bantahan+"' ";
	      }
	      
	      if(!id_permintaanukur.equals(""))
	      {
	    	  sql += " and a.id_permintaanukur = '"+id_permintaanukur+"' ";
	      }
	      
	      if(!id_award.equals(""))
	      {
	    	  sql += " and a.id_award = '"+id_award+"' ";
	      }
	      
	      if(!id_hakmilik.equals(""))
	      {
	    	  sql += " and a.id_hakmilik = '"+id_hakmilik+"' ";
	      }
	      
	      if(!jenis_dokumen.equals(""))
	      {
	    	  sql += " and a.jenis_dokumen = '"+jenis_dokumen+"' ";
	      }
	      
	      
	      if(!id_tanah.equals(""))
	      {
	    	  sql += " and a.id_tanah = '"+id_tanah+"' ";
	      }
	      
	      
	      
	      if (nama_dokumen != null) {
				if (!nama_dokumen.equals("")) {
				
					sql += " AND a.nama_Fail LIKE '%" + nama_dokumen + "%' ";
				}
			}
	      
	      if (tajuk != null) {
				if (!tajuk.equals("")) {
				
					sql += " AND (upper(a.tajuk) LIKE '%" + tajuk + "%' OR a.tajuk LIKE '%" +tajuk+ "%' )";
				}
			}
	      
	      
	      if(flag_skrin.equals("warta"))
	      {
	    	  sql += " and (a.jenis_dokumen = 'BorangD' or a.jenis_dokumen = 'warta' or a.jenis_dokumen = 'warta_sek8') ";
	      }
	      else if(flag_skrin.equals("BorangK"))
	      {
	    	  sql += " and a.jenis_dokumen = 'BorangK' ";
	      }
	      else if(flag_skrin.equals("notis_borange"))
	      {
	    	  sql += " and a.jenis_dokumen = 'notis_borange' ";	   
	      }	  
	      else if(flag_skrin.equals("notis_awam_sek8"))
	      {
	    	  sql += " and a.jenis_dokumen = 'notis_awam_sek8' ";	   
	      }	
	      else if(flag_skrin.equals("notis_borangh"))
	      {
	    	  sql += " and a.jenis_dokumen = 'notis_borangh' ";	 			
	      }
		  else if(flag_skrin.equals("notis_borangk"))
		  {
			  sql += " and a.jenis_dokumen = 'notis_borangk' ";	 			
		  }
		  else if(flag_skrin.equals("notis_awam_sek4"))
		  {
			  sql += " and a.jenis_dokumen = 'notis_awam_sek4' ";	 			
		  }
		  else if(flag_skrin.equals("notis_awam_sementara"))
		  {
			  sql += " and a.jenis_dokumen = 'notis_awam_sementara' ";	 			
		  }      
	      else if(flag_skrin.equals("LaporanTanahTerperinciImej"))
		  {
			  sql += " and a.jenis_dokumen = 'laporanTanahTerperinciImejan' ";			
		  }
	      else if(flag_skrin.equals("warta_penarikan"))
		  {
			  sql += " and a.jenis_dokumen = 'warta_penarikanbalik' ";			
		  }
	      else if(flag_skrin.equals("daftar_sek8")) //penambahbaikan yati
		  {
			  sql += " and a.jenis_dokumen = 'daftar_sek8' ";	
			  sql += " and a.id_jenisdokumen = '1526' ";
		  }
	      else if(flag_skrin.equals("BorangD")) //penambahbaikan yati
		  {
			  sql += " and a.jenis_dokumen = 'BorangD' ";	
			  sql += " and a.id_jenisdokumen = '1527' ";
		  }
	      else if(flag_skrin.equals("dokumen_pu")) //penambahbaikan yati
		  {
			  sql += " and a.jenis_dokumen = 'dokumen_pu' ";	
			  sql += " and a.id_jenisdokumen = '1528' ";
		  }
	      else if(flag_skrin.equals("LaporanTanahTerperinci")) //penambahbaikan yati
		  {
			  sql += " and a.jenis_dokumen = 'LaporanTanahTerperinci' ";
		  }
	      else if(flag_skrin.equals("penarikan_balik")) //penambahbaikan yati
		  {
			  sql += " and a.jenis_dokumen = 'penarikan_balik' ";	
		  }
	      else if(flag_skrin.equals("pembatalan")) //penambahbaikan yati
		  {
			  sql += " and a.jenis_dokumen = 'pembatalan' ";	
		  }
	      else
	      {
	    	  sql += " and (a.jenis_dokumen is null or a.jenis_dokumen = '')  ";	
	      }
	      
	      
	      myLogger.info(" SQL DOKUMEN :"+sql);
	      ResultSet rs = stmt.executeQuery(sql);
	     
	     
	      Hashtable h;
	      int bil = 1;
	      listDokumen = new Vector();
	      while (rs.next()) {
	    	  
	    	  h = new Hashtable();
	    	 
	    	  h.put("bil", bil);
	    	  h.put("jenis_dokumen", rs.getString("jenis_dokumen")== null?"":rs.getString("jenis_dokumen"));
	    	  h.put("id_hakmilik", rs.getString("id_hakmilik")== null?"":rs.getString("id_hakmilik"));
	    	  h.put("id_permohonan", rs.getString("id_permohonan")== null?"":rs.getString("id_permohonan"));
	    	  h.put("id_Dokumen", rs.getString("id_Dokumen")== null?"":rs.getString("id_Dokumen"));
	    	  h.put("nama_Fail", rs.getString("nama_Fail")== null?"":rs.getString("nama_Fail"));
	    	  h.put("jenis_Mime",rs.getString("jenis_Mime")== null?"":rs.getString("jenis_Mime"));
	    	  h.put("tajuk",rs.getString("tajuk")== null?"":rs.getString("tajuk"));
	    	  h.put("keterangan",rs.getString("keterangan")== null?"":rs.getString("keterangan"));
	          
	    	  listDokumen.addElement(h);
	    	  bil++;	    	
	      }			    
	      return listDokumen;
	    } finally {		    	
	   // if (db != null) db.close();
	    }
	}
	
	@SuppressWarnings("unchecked")
	private void uploadFiles(Db db,Connection conn,String flag_skrin) throws Exception {
		    DiskFileItemFactory factory = new DiskFileItemFactory();
		    ServletFileUpload upload = new ServletFileUpload(factory);
		    List items = upload.parseRequest(request);
		    Iterator itr = items.iterator();
		    while (itr.hasNext()) {
		      FileItem item = (FileItem)itr.next();
		      if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
		    	  saveData(item,db,conn,flag_skrin);
		      }
		    }
		  }
	
	public static void hapusDokumen(String id_dokumen, Db db) throws Exception {
		   
	//	Db db = null;
	    String sql = "";
	   
	    try{
	    	
	    	 	//db = new Db();
	    	 	Statement stmt = db.getStatement();			  
	    		//String iddokumen = (String)data.get("id_dokumen");	    		
	    		sql = "DELETE FROM tblpptdokumen where id_dokumen = '"+id_dokumen+"'";
	    		stmt.executeUpdate(sql);
	    }
	    finally {
	   // (db != null) db.close();
	    }
	  }//close hapus
	
	//penambahbaikan yati
	 private void saveData(FileItem item,Db db,Connection conn,String flag_skrin) throws Exception {
	  		
	        	long id_Dokumen = getNextID("TBLPPTDOKUMEN_SEQ",db);
	        	//String userId = (String) session.getAttribute("_ekptg_user_id");
	        	
	        	PreparedStatement ps = conn.prepareStatement("insert into TBLPPTDOKUMEN " +
	        			"(id_Dokumen,id_permohonan,id_pembatalan,id_penarikanbalik,id_bantahan,id_permintaanukur,id_award,id_hakmilik,nama_Fail,jenis_Mime,content,tajuk,keterangan,jenis_dokumen,id_tanah,id_notisawam,id_buktipenyampaian,id_borangh,id_jenisdokumen,tarikh_masuk) " +
	        			"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE)");
	        	ps.setLong(1, id_Dokumen);
	        	ps.setString(2, getParam("id_permohonan"));
	        	ps.setString(3, getParam("id_pembatalan"));
	        	ps.setString(4, getParam("id_penarikanbalik"));
	        	ps.setString(5, getParam("id_bantahan"));
	        	ps.setString(6, getParam("id_permintaanukur"));
	        	ps.setString(7, getParam("id_award"));
	        	ps.setString(8, getParam("id_hakmilik"));
	        	
	        	
	        	
	        	ps.setString(9,item.getName());
	        	ps.setString(10,item.getContentType());
	        	ps.setBinaryStream(11,item.getInputStream(),(int)item.getSize());
	        	ps.setString(12, getParam("nama_dokumen"));
	        	ps.setString(13, getParam("keterangan"));
	        	
	        	ps.setString(14, getParam("jenis_dokumen"));
	        	
	        	if(flag_skrin.equals("warta"))
		  	    {
	        		ps.setString(14, "warta");
	        		ps.setString(19, "");
		  	    }
	        	else if(flag_skrin.equals("BorangK"))
		  	    {
	        		ps.setString(14, "BorangK");
	        		ps.setString(19, "");
		  	    }
	        	else if(flag_skrin.equals("LaporanTanahTerperinciImej"))
	   		  	{
		   			ps.setString(14, "laporanTanahTerperinciImejan");
		   			ps.setString(19, "");
	   		  	}
	        	else if(flag_skrin.equals("warta_penarikan"))
		  		{
	        		ps.setString(14, "warta_penarikanbalik");
	        		ps.setString(19, "");
		  	    }
	        	else if(flag_skrin.equals("notis_borange"))
		  		{
	        		ps.setString(14, "notis_borange");
	        		ps.setString(19, "");
		  	    }
	        	else if(flag_skrin.equals("notis_awam_sek8"))
		  		{
	        		ps.setString(14, "notis_awam_sek8");
	        		ps.setString(19, "");
		  	    }	        	
	    		else if(flag_skrin.equals("notis_borangh"))
	    		{
	    			ps.setString(14, "notis_borangh");
	    			ps.setString(19, "");
	    		}
	    		else if(flag_skrin.equals("notis_borangk"))
	    		{
	    			ps.setString(14, "notis_borangk");
	    			ps.setString(19, "");
	    		}
	    		else if(flag_skrin.equals("notis_awam_sek4"))
	    		{
	    			ps.setString(14, "notis_awam_sek4");
	    			ps.setString(19, "");
	    		} 
	    		else if(flag_skrin.equals("notis_awam_sementara"))
	    		{
	    			ps.setString(14, "notis_awam_sementara");
	    			ps.setString(19, "");
	    		} 
	    		else if(flag_skrin.equals("daftar_sek8"))
	    		{
	    			ps.setString(14, "daftar_sek8");
	    			ps.setString(19, "1526");	    			
	    		} 
	    		else if(flag_skrin.equals("BorangD"))
		  	    {
	        		ps.setString(14, "BorangD");
	        		ps.setString(19, "1527");
		  	    }
	    		else if(flag_skrin.equals("dokumen_pu"))
		  	    {
	        		ps.setString(14, "dokumen_pu");
	        		ps.setString(19, "1528");
		  	    }
	    		else if(flag_skrin.equals("LaporanTanahTerperinci"))
	   		  	{	
		   			ps.setString(14, "LaporanTanahTerperinci");
		   			ps.setString(19, "");
	   		  	}
	    		else if(flag_skrin.equals("penarikan_balik"))
	   		  	{	
		   			ps.setString(14, "penarikan_balik");
		   			ps.setString(19, "");
	   		  	}
	    		else if(flag_skrin.equals("pembatalan"))
	   		  	{	
		   			ps.setString(14, "pembatalan");
		   			ps.setString(19, "");
	   		  	}
	        	else
	        	{
	        		ps.setString(14, "");
	        		ps.setString(19, "");
	        	}
	        	
	        	
	        	ps.setString(15,getParam("id_tanah"));
	        	ps.setString(16,getParam("id_notisawam"));
	        	ps.setString(17,getParam("id_buktipenyampaian"));
	        	ps.setString(18,getParam("id_borangh"));
	       
	        	
	        	ps.executeUpdate();	   
	  }
	 
	 public synchronized static long getNextID(String seqName,Db db) throws Exception {
			String statecode=StateLookup.getInstance().getTitle("StateCode");
			String sql = "select " + statecode + " || to_char(sysdate,'YY') || " +seqName + ".NEXTVAL  FROM DUAL ";
			try {
				Statement stmt = db.getStatement();
				ResultSet rs = stmt.executeQuery(sql);
				rs.next();
				return rs.getLong(1);
			} catch (Exception ex) {
				ex.printStackTrace();
				throw ex;
			} finally {
				//if (db != null) db.close();
			}
		}
	
	Vector list_fail = null;
	public Vector getFail(String NO_FAIL,String NAMA_PROJEK,String ID_NEGERI,String ID_DAERAH,Db db,String jenis_seksyen) throws Exception {
		
		
		myLogger.info("NO_FAIL :"+NO_FAIL);
		myLogger.info("NAMA_PROJEK :"+NAMA_PROJEK);
		myLogger.info("ID_NEGERI :"+ID_NEGERI);
		myLogger.info("ID_DAERAH :"+ID_DAERAH);
		
	
		list_fail = new Vector();
		list_fail.clear();
		//Db db = null;
		String sql = "";
		try {
			//db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			
			
			sql = " SELECT P.ID_PERMOHONAN,F.NO_FAIL,P.NO_RUJUKAN_PTD,P.NO_RUJUKAN_PTG,P.NO_RUJUKAN_UPT,F.ID_FAIL,UPPER(P.TUJUAN) AS PROJEK,D.NAMA_DAERAH AS NAMA_DAERAH,D.ID_DAERAH, N.NAMA_NEGERI AS NAMA_NEGERI, N.ID_NEGERI,F.ID_SUBURUSAN, "+
					" TO_CHAR(P.TARIKH_PERMOHONAN,'DD/MM/YYYY')  AS TARIKH_MOHON, (SELECT COUNT(*) FROM TBLPPTHAKMILIK H WHERE H.ID_PERMOHONAN = P.ID_PERMOHONAN) AS TOTAL_HAKMILIK "+
					" FROM TBLPPTPERMOHONAN P, TBLPFDFAIL F,TBLRUJDAERAH D,TBLRUJNEGERI N "+
					" WHERE P.ID_FAIL = F.ID_FAIL " +
					" AND P.ID_DAERAH = D.ID_DAERAH  AND F.ID_NEGERI = N.ID_NEGERI ";
			
			if(jenis_seksyen.equals("8"))
			{
					sql += " AND F.ID_SUBURUSAN = '51'";
			}
			else if(jenis_seksyen.equals("4"))
			{
					sql += " AND F.ID_SUBURUSAN = '52'";
			}
			
			
			
			
			
					sql += " ";
			   
			
			if (ID_NEGERI != null) {
				if (!ID_NEGERI.equals("")) {
				
					sql += " AND F.ID_NEGERI = '" + ID_NEGERI + "' ";
				}
			}
			
			if (ID_DAERAH != null) {
				if (!ID_DAERAH.equals("")) {
				
					sql += " AND P.ID_DAERAH = '" + ID_DAERAH + "' ";
				}
			}
			 
			
			if (NO_FAIL != null) {
				if (!NO_FAIL.trim().equals("")) {
				
					sql += " AND (UPPER(F.NO_FAIL) LIKE '%" + NO_FAIL.toUpperCase().trim() + "%'  OR UPPER(P.NO_RUJUKAN_PTD) LIKE '%" + NO_FAIL.toUpperCase().trim() + "%' OR UPPER(P.NO_RUJUKAN_PTG) LIKE '%" + NO_FAIL.toUpperCase().trim() + "%' OR UPPER(P.NO_RUJUKAN_UPT) LIKE '%" + NO_FAIL.toUpperCase().trim() + "%') ";
				}
			}
			
			if (NAMA_PROJEK != null) {
				if (!NAMA_PROJEK.trim().equals("")) {
				
					sql += " AND UPPER(P.TUJUAN) LIKE '%" + NAMA_PROJEK.toUpperCase().trim() + "%'";
					
				}
			}
			
						
			
			
			sql += "  AND  ROWNUM < 50 ORDER BY TO_DATE(TO_CHAR(P.TARIKH_PERMOHONAN,'DD/MM/YYYY'),'DD/MM/YYYY') DESC ";
		
			
			
			
			myLogger.info("LIST FAIL :"+sql.toUpperCase());
			
			
			
			stmt.setFetchSize(10);
			ResultSet rs = stmt.executeQuery(sql);
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				Hashtable h = new Hashtable();
				h.put("BIL", bil);
				if (rs.getString("ID_PERMOHONAN") == null) {	h.put("ID_PERMOHONAN", ""); } else { h.put("ID_PERMOHONAN", rs.getString("ID_PERMOHONAN").toUpperCase()); }
				if (rs.getString("NO_FAIL") == null) {	h.put("NO_FAIL", ""); } else { h.put("NO_FAIL", rs.getString("NO_FAIL").toUpperCase()); }	
				if (rs.getString("NO_RUJUKAN_PTD") == null) {	h.put("NO_RUJUKAN_PTD", ""); } else { h.put("NO_RUJUKAN_PTD", rs.getString("NO_RUJUKAN_PTD").toUpperCase()); }
				if (rs.getString("NO_RUJUKAN_UPT") == null) {	h.put("NO_RUJUKAN_UPT", ""); } else { h.put("NO_RUJUKAN_UPT", rs.getString("NO_RUJUKAN_UPT").toUpperCase()); }
				if (rs.getString("NO_RUJUKAN_PTG") == null) {	h.put("NO_RUJUKAN_PTG", ""); } else { h.put("NO_RUJUKAN_PTG", rs.getString("NO_RUJUKAN_PTG").toUpperCase()); }
				if (rs.getString("PROJEK") == null) {	h.put("PROJEK", ""); } else { h.put("PROJEK", rs.getString("PROJEK").toUpperCase()); }
				if (rs.getString("NAMA_DAERAH") == null) {	h.put("NAMA_DAERAH", ""); } else { h.put("NAMA_DAERAH", rs.getString("NAMA_DAERAH").toUpperCase()); }
				if (rs.getString("NAMA_NEGERI") == null) {	h.put("NAMA_NEGERI", ""); } else { h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI").toUpperCase()); }
				if (rs.getString("TARIKH_MOHON") == null) {	h.put("TARIKH_MOHON", ""); } else { h.put("TARIKH_MOHON", rs.getString("TARIKH_MOHON").toUpperCase()); }
				if (rs.getString("TOTAL_HAKMILIK") == null) {	h.put("TOTAL_HAKMILIK", ""); } else { h.put("TOTAL_HAKMILIK", rs.getString("TOTAL_HAKMILIK").toUpperCase()); }
				list_fail.addElement(h);
			}
			return list_fail;
		} finally {
			//if (db != null)
			//db.close();
		}
	}
	
	
	
	Vector list_hakmilik = null;
	public Vector getHakmilik(String id_permohonan,Db db) throws Exception {
		
		list_hakmilik = new Vector();
		list_hakmilik.clear();
		//Db db = null;
		String sql = "";
		try {
		//	db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = " SELECT (J.KOD_JENIS_HAKMILIK || ' ' || H.NO_HAKMILIK) AS NO_HAKMILIK, H.NO_LOT,UPPER(M.NAMA_MUKIM) AS NAMA_MUKIM, H.ID_PERMOHONAN "+
					" FROM TBLPPTHAKMILIK H, TBLRUJJENISHAKMILIK J,TBLRUJMUKIM M "+
					" WHERE H.ID_JENISHAKMILIK = J.ID_JENISHAKMILIK(+) AND H.ID_PERMOHONAN = '"+id_permohonan+"' "+
					" AND H.ID_MUKIM = M.ID_MUKIM ORDER BY NAMA_MUKIM,NO_LOT ";
			myLogger.info("LIST HAKMILIK :"+sql.toUpperCase());
			
			stmt.setFetchSize(10);
			ResultSet rs = stmt.executeQuery(sql);
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				Hashtable h = new Hashtable();
				h.put("BIL", bil);
				if (rs.getString("NO_HAKMILIK") == null) {	h.put("NO_HAKMILIK", ""); } else { h.put("NO_HAKMILIK", rs.getString("NO_HAKMILIK").toUpperCase()); }
				if (rs.getString("NO_LOT") == null) {	h.put("NO_LOT", ""); } else { h.put("NO_LOT", rs.getString("NO_LOT").toUpperCase()); }	
				if (rs.getString("NAMA_MUKIM") == null) {	h.put("NAMA_MUKIM", ""); } else { h.put("NAMA_MUKIM", rs.getString("NAMA_MUKIM").toUpperCase()); }
				list_hakmilik.addElement(h);
			}
			return list_hakmilik;
		} finally {
			//if (db != null)
			//db.close();
		}
	}
	
	
	
	 protected void setupPage(HttpSession session, String action, List lists)
	    {
	        if(lists == null) {
	            context.put("totalRecords", Integer.valueOf(0));
	            context.put("SenaraiFail", "");
	            context.put("page", Integer.valueOf(0));
	            context.put("itemsPerPage", Integer.valueOf(0));
	            context.put("totalPages", Integer.valueOf(0));
	            context.put("startNumber", Integer.valueOf(0));
	            context.put("isFirstPage", Boolean.valueOf(true));
	            context.put("isLastPage", Boolean.valueOf(true));
	        } else {
	            context.put("totalRecords", Integer.valueOf(lists.size()));
	            int page = getParam("page") != "" ? getParamAsInteger("page") : 1;
	            int itemsPerPage;
	            if(context.get("itemsPerPage") == null || "".equals(context.get("itemsPerPage")) || "0".equals(context.get("itemsPerPage")))
	                itemsPerPage = getParam("itemsPerPage") != "" ? getParamAsInteger("itemsPerPage") : 10;
	                else
		            {
		            	  myLogger.info("PAGE 2 :"+context.get("itemsPerPage"));
		               // itemsPerPage = ((Integer)context.get("itemsPerPage")).intValue();
		            itemsPerPage = Integer.parseInt(context.get("itemsPerPage").toString());
		            myLogger.info("PAGE 3 :"+context.get("itemsPerPage"));
		            }
	            if("getNext".equals(action))
	                page++;
	            else
	            if("getPrevious".equals(action))
	                page--;
	            else
	            if("getPage".equals(action))
	                page = getParamAsInteger("value");
	            else
	            if("doChangeItemPerPage".equals(action))
	                itemsPerPage = getParamAsInteger("itemsPerPage");
	            if(itemsPerPage == 0)
	                itemsPerPage = 10;
	            Paging2 paging = new Paging2(session, lists, itemsPerPage);
	            if(page > paging.getTotalPages())
	                page = 1;
	            context.put("SenaraiFail", paging.getPage(page));
	            context.put("page", new Integer(page));
	            context.put("itemsPerPage", new Integer(itemsPerPage));
	            context.put("totalPages", new Integer(paging.getTotalPages()));
	            context.put("startNumber", new Integer(paging.getTopNumber()));
	            context.put("isFirstPage", new Boolean(paging.isFirstPage()));
	            context.put("isLastPage", new Boolean(paging.isLastPage()));
	        }
	    }
	
	 
	 public void transferData(HttpSession session,String id_mohon_selected,String id_permohonan,Db db) throws Exception {
			//Db db = null;
//			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String sql = "";			
			String user_id = (String) session.getAttribute("_ekptg_user_id");
			String role = (String) session.getAttribute("myrole");
			try {
				//db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				/*
				 sql = " INSERT INTO TBLPPTHAKMILIK "+
					 " (ID_NEGERI, ID_DAERAH,  "+
					 " ID_MUKIM, ID_JENISHAKMILIK, NO_HAKMILIK,  "+
					 " ID_SUBJAKET, TARIKH_TERIMA, TARIKH_DAFTAR,  "+
					 " FLAG_REZAB, FLAG_GSA, TEMPOH_LUPUT,  "+
					 " TARIKH_LUPUT, NO_PT, ID_UNITLUASPT,  "+
					 " LUAS_PT, NO_LOT, ID_UNITLUASLOT,  "+
					 " LUAS_LOT, ID_UNITLUASAMBIL, LUAS_AMBIL,  "+
					 " ID_UNITLUASBARU, LUAS_BARU, NO_PELAN,  "+
					 " NO_SYIT, LOKASI, ID_KATEGORITANAH,  "+
					 " SYARAT_NYATA, SYARAT_KHAS, SEKATAN_KEPENTINGAN, "+ 
					 " SEKATAN_HAK, JENIS_MILIK, ULASAN_PENTADBIR,  "+
					 " JUMLAH_AWARD, UNIT_AWARD, TARIKH_HANTAR_DHD,  "+
					 " TARIKH_TERIMA_DHD, FLAG_AMBIL_SEGERA, FLAG_PEMBATALAN, "+ 
					 " FLAG_PENARIKAN_BALIK, FLAG_LAPORAN_TANAH, FLAG_HANTAR_DHD, "+ 
					 " FLAG_TERIMA_DHD, FLAG_SIASATAN, FLAG_BORANGL,  "+
					 " FLAG_PERMINTAAN_UKUR, ID_SIASATAN, ID_BORANGK,  "+
					 " ID_BORANGG, ID_BORANGE, ID_BORANGI,  "+
					 " ID_BORANGL, ID_PENARIKANBALIK, ID_PEMBATALAN, "+ 
					 " FLAG_UBAH, NO_BANGUNAN, NO_TINGKAT,  "+
					 " NO_PETAK, TARIKH_BORANGK, TARIKH_BORANGK_SEGERA, "+ 
					 " ID_PERMOHONAN, TARIKH_TERIMA_HM, NO_KELULUSAN,  "+
					 " NO_DAFTAR, ID_LOT,  "+
					 " ID_DB, SEKSYEN, CATATAN, "+ 
					 " FLAG_ENDOSAN, LUAS_LOT_TARIK, ID_DAERAHPENGGAWA,  "+
					 " TARIKH_SURAT_PTG, TARIKH_HANTAR_JUPEM, TARIKH_BORANG_PU,  "+
					 " STATUS_BORANGL, ID_PEGAWAI, ID_BANTAHAN,  "+
					 " FLAG_BANTAHAN, NO_SUBJAKET, FLAG_JENIS_RIZAB,  "+
					 " NAMA_LAIN_RIZAB, NO_WARTA_RIZAB, TARIKH_WARTA_RIZAB,  "+
					 " NO_PERMINTAANUKUR, FLAG_ENDOSAN_BORANGK, FLAG_PENARIKAN_KESELURUHAN,  "+
					 " FLAG_PEMBATALAN_KESELURUHAN, ID_UNITLUASLOT_CONVERT, ID_UNITLUASAMBIL_CONVERT,  "+
					 " NAMA_LUAS_ASAL, NAMA_LUAS_AMBIL, FLAG_BAYAR_BANTAHAN,  "+
					 " FLAG_SEBAHAGIAN, PGNHM, FLAG_HANTAR_HTP,  "+
					 " TARIKH_HANTAR_HTP, FLAG_SEGERA_SEBAHAGIAN, CATATAN_STOP_SIASATAN,  "+
					 " FLAG_STOP_SIASATAN, TARIKH_STOP_SIASATAN,ID_MASUK,  "+
					 " TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI)    "+
					 " SELECT  T.ID_NEGERI, T.ID_DAERAH,  "+
					 " T.ID_MUKIM, T.ID_JENISHAKMILIK, T.NO_HAKMILIK,  "+
					 " T.ID_SUBJAKET, T.TARIKH_TERIMA, T.TARIKH_DAFTAR,  "+
					 " T.FLAG_REZAB, T.FLAG_GSA, T.TEMPOH_LUPUT,  "+
					 " T.TARIKH_LUPUT, T.NO_PT, T.ID_UNITLUASPT,  "+
					 " T.LUAS_PT, T.NO_LOT, T.ID_UNITLUASLOT,  "+
					 " T.LUAS_LOT, T.ID_UNITLUASAMBIL, T.LUAS_AMBIL,  "+
					 " T.ID_UNITLUASBARU, T.LUAS_BARU, T.NO_PELAN,  "+
					 " T.NO_SYIT, T.LOKASI, T.ID_KATEGORITANAH,  "+
					 " T.SYARAT_NYATA, T.SYARAT_KHAS, T.SEKATAN_KEPENTINGAN,  "+
					 " T.SEKATAN_HAK, T.JENIS_MILIK, T.ULASAN_PENTADBIR,  "+
					 " T.JUMLAH_AWARD, T.UNIT_AWARD, T.TARIKH_HANTAR_DHD,  "+
					 " T.TARIKH_TERIMA_DHD, T.FLAG_AMBIL_SEGERA, T.FLAG_PEMBATALAN,  "+
					 " T.FLAG_PENARIKAN_BALIK, T.FLAG_LAPORAN_TANAH, T.FLAG_HANTAR_DHD,  "+
					 " T.FLAG_TERIMA_DHD, T.FLAG_SIASATAN, T.FLAG_BORANGL,  "+
					 " T.FLAG_PERMINTAAN_UKUR, T.ID_SIASATAN, T.ID_BORANGK,  "+
					 " T.ID_BORANGG, T.ID_BORANGE, T.ID_BORANGI,  "+
					 " T.ID_BORANGL, '', '',  "+
					 " T.FLAG_UBAH, T.NO_BANGUNAN, T.NO_TINGKAT,  "+
					 " T.NO_PETAK, T.TARIKH_BORANGK, T.TARIKH_BORANGK_SEGERA,  "+ 
					 " "+id_permohonan+", T.TARIKH_TERIMA_HM, T.NO_KELULUSAN,  "+
					 " T.NO_DAFTAR, T.ID_LOT,  "+
					 " T.ID_DB, T.SEKSYEN, T.CATATAN, "+ 
					 " T.FLAG_ENDOSAN, T.LUAS_LOT_TARIK, T.ID_DAERAHPENGGAWA, "+ 
					 " T.TARIKH_SURAT_PTG, T.TARIKH_HANTAR_JUPEM, T.TARIKH_BORANG_PU, "+ 
					 " T.STATUS_BORANGL, T.ID_PEGAWAI, T.ID_BANTAHAN,  "+
					 " T.FLAG_BANTAHAN, T.NO_SUBJAKET, T.FLAG_JENIS_RIZAB, "+ 
					 " T.NAMA_LAIN_RIZAB, T.NO_WARTA_RIZAB, T.TARIKH_WARTA_RIZAB, "+ 
					 " T.NO_PERMINTAANUKUR, T.FLAG_ENDOSAN_BORANGK, T.FLAG_PENARIKAN_KESELURUHAN, "+ 
					 " T.FLAG_PEMBATALAN_KESELURUHAN, T.ID_UNITLUASLOT_CONVERT, T.ID_UNITLUASAMBIL_CONVERT, "+ 
					 " T.NAMA_LUAS_ASAL, T.NAMA_LUAS_AMBIL, T.FLAG_BAYAR_BANTAHAN,  "+
					 " T.FLAG_SEBAHAGIAN, T.PGNHM, T.FLAG_HANTAR_HTP,  "+
					 " T.TARIKH_HANTAR_HTP, T.FLAG_SEGERA_SEBAHAGIAN, T.CATATAN_STOP_SIASATAN, "+ 
					 " T.FLAG_STOP_SIASATAN, T.TARIKH_STOP_SIASATAN, "+
					 " "+user_id+",SYSDATE AS TARIKH_MASUK,  "+user_id+", SYSDATE AS TARIKH_KEMASKINI "+
					 " FROM TBLPPTHAKMILIK T WHERE ID_PERMOHONAN = '"+id_mohon_selected+"' ";
				*/
				
				sql = " INSERT INTO TBLPPTHAKMILIK "+
						 " (ID_NEGERI, ID_DAERAH,  "+
						 " ID_MUKIM, ID_JENISHAKMILIK, NO_HAKMILIK,  "+
						 " ID_SUBJAKET, TARIKH_TERIMA, TARIKH_DAFTAR,  "+
						 " FLAG_REZAB, FLAG_GSA, TEMPOH_LUPUT,  "+
						 " TARIKH_LUPUT, NO_PT, ID_UNITLUASPT,  "+
						 " LUAS_PT, NO_LOT, ID_UNITLUASLOT,  "+
						 " LUAS_LOT, ID_UNITLUASAMBIL, LUAS_AMBIL,  "+
						 " ID_UNITLUASBARU, LUAS_BARU, NO_PELAN,  "+
						 " NO_SYIT, LOKASI, ID_KATEGORITANAH,  "+
						 " SYARAT_NYATA, SYARAT_KHAS, SEKATAN_KEPENTINGAN, "+ 
						 " SEKATAN_HAK, JENIS_MILIK, ULASAN_PENTADBIR,  "+
						 " JUMLAH_AWARD, UNIT_AWARD, TARIKH_HANTAR_DHD,  "+
						 " TARIKH_TERIMA_DHD, FLAG_AMBIL_SEGERA, FLAG_PEMBATALAN, "+ 
						 " FLAG_PENARIKAN_BALIK, FLAG_LAPORAN_TANAH, FLAG_HANTAR_DHD, "+ 
						 " FLAG_TERIMA_DHD, FLAG_SIASATAN, FLAG_BORANGL,  "+
						 " FLAG_PERMINTAAN_UKUR, ID_SIASATAN, ID_BORANGK,  "+
						 " ID_BORANGG, ID_BORANGE, ID_BORANGI,  "+
						 " ID_BORANGL, ID_PENARIKANBALIK, ID_PEMBATALAN, "+ 
						 " FLAG_UBAH, NO_BANGUNAN, NO_TINGKAT,  "+
						 " NO_PETAK, TARIKH_BORANGK, TARIKH_BORANGK_SEGERA, "+ 
						 " ID_PERMOHONAN, TARIKH_TERIMA_HM, NO_KELULUSAN,  "+
						 " NO_DAFTAR, ID_LOT,  "+
						 " ID_DB, SEKSYEN, CATATAN, "+ 
						 " FLAG_ENDOSAN, LUAS_LOT_TARIK, ID_DAERAHPENGGAWA,  "+
						 " TARIKH_SURAT_PTG, TARIKH_HANTAR_JUPEM, TARIKH_BORANG_PU,  "+
						 " STATUS_BORANGL, ID_PEGAWAI, ID_BANTAHAN,  "+
						 " FLAG_BANTAHAN, NO_SUBJAKET, FLAG_JENIS_RIZAB,  "+
						 " NAMA_LAIN_RIZAB, NO_WARTA_RIZAB, TARIKH_WARTA_RIZAB,  "+
						 " NO_PERMINTAANUKUR, FLAG_ENDOSAN_BORANGK, FLAG_PENARIKAN_KESELURUHAN,  "+
						 " FLAG_PEMBATALAN_KESELURUHAN, ID_UNITLUASLOT_CONVERT, ID_UNITLUASAMBIL_CONVERT,  "+
						 " NAMA_LUAS_ASAL, NAMA_LUAS_AMBIL, FLAG_BAYAR_BANTAHAN,  "+
						 " FLAG_SEBAHAGIAN, PGNHM, FLAG_HANTAR_HTP,  "+
						 " TARIKH_HANTAR_HTP, FLAG_SEGERA_SEBAHAGIAN, CATATAN_STOP_SIASATAN,  "+
						 " FLAG_STOP_SIASATAN, TARIKH_STOP_SIASATAN,ID_MASUK,  "+
						 " TARIKH_MASUK, ID_KEMASKINI, TARIKH_KEMASKINI)    "+
						 " SELECT  T.ID_NEGERI, T.ID_DAERAH,  "+
						 " T.ID_MUKIM, T.ID_JENISHAKMILIK, T.NO_HAKMILIK,  "+
						 " T.ID_SUBJAKET, T.TARIKH_TERIMA, T.TARIKH_DAFTAR,  "+
						 " T.FLAG_REZAB, T.FLAG_GSA, T.TEMPOH_LUPUT,  "+
						 " T.TARIKH_LUPUT, T.NO_PT, '',  "+
						 " T.LUAS_PT, T.NO_LOT, '',  "+
						 " '', '', '',  "+
						 " '', '', T.NO_PELAN,  "+
						 " T.NO_SYIT, T.LOKASI, T.ID_KATEGORITANAH,  "+
						 " T.SYARAT_NYATA, T.SYARAT_KHAS, T.SEKATAN_KEPENTINGAN,  "+
						 " T.SEKATAN_HAK, T.JENIS_MILIK, '',  "+
						 " '', '', '',  "+
						 " '','','',  "+
						 " '','', '',  "+
						 " '', '', '',  "+
						 " '', '', '',  "+
						 " '', '', '',  "+
						 " '', '', '',  "+
						 " '', '', '',  "+
						 " '', '', '',  "+ 
						 " "+id_permohonan+", T.TARIKH_TERIMA_HM, T.NO_KELULUSAN,  "+
						 " T.NO_DAFTAR, T.ID_LOT,  "+
						 " T.ID_DB, T.SEKSYEN, T.CATATAN, "+ 
						 " '','', T.ID_DAERAHPENGGAWA, "+ 
						 " '', '','', "+ 
						 " '', '','',  "+
						 " '', '', T.FLAG_JENIS_RIZAB, "+ 
						 " T.NAMA_LAIN_RIZAB, T.NO_WARTA_RIZAB, T.TARIKH_WARTA_RIZAB, "+ 
						 " '', '', '', "+ 
						 " '', '', '', "+ 
						 " '', '', '',  "+
						 " '', T.PGNHM, '',  "+
						 " '', '', '', "+ 
						 " '', '', "+
						 " "+user_id+",SYSDATE AS TARIKH_MASUK,  "+user_id+", SYSDATE AS TARIKH_KEMASKINI "+
						 " FROM TBLPPTHAKMILIK T WHERE ID_PERMOHONAN = '"+id_mohon_selected+"' ";
				myLogger.info("transfer :"+sql.toUpperCase());
				stmt.executeUpdate(sql);
			
				
			} finally {
				//if (db != null)
				//	db.close();
			}
		}
	
}
