package ekptg.view.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.helpers.Paging2;

@SuppressWarnings("serial")
public class IntegrasiReg extends AjaxBasedModule {
	
	static Logger myLogger = Logger.getLogger(ekptg.view.admin.IntegrasiReg.class);
	String skrin_name = "app/admin/Integrasi/index.jsp";
	
	@Override
	public String doTemplate2() throws Exception {
		
		List list_TBLRUJNEGERI = null;
		List listIntegrasi = null;
		List listDokumen = null;
		List listTrans = null;
		
		Hashtable detailIntegrasi = null;
		
		List list_TBLRUJJENISPEJABAT = null;
		
		
		
		//initialize
		this.context.put("Type", "");
		this.context.put("FlagCari", "");
		this.context.put("list_TBLRUJNEGERI", "");
		this.context.put("listIntegrasi", "");
		this.context.put("detailIntegrasi", "");
		this.context.put("carianUmum", "");
		this.context.put("isComplete", "");
		
		HttpSession session = this.request.getSession();
		
		String command = getParam("command");
		String FlagCari = getParam("FlagCari");
		String Type = "";
		String carianUmum = "";
		myLogger.info("Integrasi command : "+command );
		
		if(command.equals("carianUtama"))
		{
			skrin_name = "app/admin/Integrasi/SenaraiUtama.jsp";
			
		}
		else if(command.equals("closeCarian"))
		{
			skrin_name = "app/admin/Integrasi/blank_Integrasi.jsp";
		}
		else if(command.equals("showCarianInteg") )
		{
			Type = getParam("Type");
			this.context.put("Type",Type);
			myLogger.info("Type -- "+Type);
			//String carianInteg = getParam("carianInteg");
			String CT_FLAGTEPERINCI_CARIAN = getParam("CT_FLAGTEPERINCI_CARIAN"+Type);
			this.context.put("CT_FLAGTEPERINCI_CARIAN",CT_FLAGTEPERINCI_CARIAN);
			
			String CT_OPENCLOSE_CARIAN = getParam("CT_OPENCLOSE_CARIAN"+Type);
			this.context.put("CT_OPENCLOSE_CARIAN",CT_OPENCLOSE_CARIAN);
			
			list_TBLRUJNEGERI = listTableRujukan(session,"TBLRUJNEGERI","");
			this.context.put("list_TBLRUJNEGERI",list_TBLRUJNEGERI);	
			
			skrin_name = "app/admin/Integrasi/CarianTerperinciInteg.jsp";

		}
		else if(command.equals("showSenaraiInteg1")){
			
			String action = getParam("action");
			
			/*Type = "1";
			this.context.put("Type", Type);*/
			
			this.context.put("SuccessMesejDeleteUser", "");
			
			if(getParam("FlagDelete").equals("Y")){
				
				String ID_INTEGRASI = getParam("ID_INTEGRASI");
				deleteIntegrasi (session, ID_INTEGRASI);
				this.context.put("SuccessMesejDeleteUser", "Maklumat Berjaya Dihapus");
			}
			
			carianUmum = getParam("carianUmum");
			this.context.put("carianUmum", carianUmum);
			myLogger.info("carianUmum -- "+carianUmum);
			
			FlagCari = getParam("FlagCari");
			myLogger.info("FlagCari -- "+FlagCari);
			this.context.put("FlagCari",FlagCari);
			
			if(getParam("FlagCetak").equals("Y"))
			{
				listIntegrasi = getListIntegrasi(session,"1",carianUmum);
				this.context.put("listIntegrasi",listIntegrasi);	
				skrin_name = "app/admin/Integrasi/SenaraiIntegrasiDalaman_Print.jsp";
			}
			else
			{
				listIntegrasi = getListIntegrasi(session,"1",carianUmum);
				//this.context.put("listIntegrasi",listIntegrasi);	
				setupPageList(session, action, listIntegrasi, "listIntegrasi",command,"div_ListInteg1");
				skrin_name = "app/admin/Integrasi/SenaraiIntegrasiDalaman.jsp";
			}
		
		}
		else if(command.equals("showSenaraiInteg2")){
			
			String action = getParam("action");
			
			/*Type = "2";
			this.context.put("Type", Type);*/
			
			this.context.put("SuccessMesejDeleteUser", "");
			
			if(getParam("FlagDelete").equals("Y")){
				
				String ID_INTEGRASI = getParam("ID_INTEGRASI");
				deleteIntegrasi (session, ID_INTEGRASI);
				this.context.put("SuccessMesejDeleteUser", "Maklumat Berjaya Dihapus");
			}
			
			carianUmum = getParam("carianUmum");
			this.context.put("carianUmum", carianUmum);
			myLogger.info("carianUmum -- "+carianUmum);
			
			FlagCari = getParam("FlagCari");
			myLogger.info("FlagCari -- "+FlagCari);
			this.context.put("FlagCari",FlagCari);
			
			if(getParam("FlagCetak").equals("Y"))
			{
				listIntegrasi = getListIntegrasi(session,"2",carianUmum);
				this.context.put("listIntegrasi",listIntegrasi);	
				skrin_name = "app/admin/Integrasi/SenaraiIntegrasiLuaran_Print.jsp";
			}
			else
			{
				listIntegrasi = getListIntegrasi(session,"2",carianUmum);
				//this.context.put("listIntegrasi",listIntegrasi);	
				setupPageList(session, action, listIntegrasi, "listIntegrasi",command,"div_ListInteg2");
				skrin_name = "app/admin/Integrasi/SenaraiIntegrasiLuaran.jsp";
			}
		
		}
		else if(command.equals("showSenaraiInteg3")){
			
			String action = getParam("action");
			
			/*Type = "3";
			this.context.put("Type", Type);*/
			
			this.context.put("SuccessMesejDeleteUser", "");
			
			if(getParam("FlagDelete").equals("Y")){
				
				String ID_INTEGRASI = getParam("ID_INTEGRASI");
				deleteIntegrasi (session, ID_INTEGRASI);
				this.context.put("SuccessMesejDeleteUser", "Maklumat Berjaya Dihapus");
			}
			
			carianUmum = getParam("carianUmum");
			this.context.put("carianUmum", carianUmum);
			myLogger.info("carianUmum -- "+carianUmum);
			
			FlagCari = getParam("FlagCari");
			myLogger.info("FlagCari -- "+FlagCari);
			this.context.put("FlagCari",FlagCari);
			
			if(getParam("FlagCetak").equals("Y"))
			{
				listIntegrasi = getListIntegrasi(session,"3",carianUmum);
				this.context.put("listIntegrasi",listIntegrasi);	
				skrin_name = "app/admin/Integrasi/SenaraiIntegrasiSistem_Print.jsp";
			}
			else
			{
				listIntegrasi = getListIntegrasi(session,"3",carianUmum);
				//this.context.put("listIntegrasi",listIntegrasi);	
				setupPageList(session, action, listIntegrasi, "listIntegrasi",command,"div_ListInteg3");
				skrin_name = "app/admin/Integrasi/SenaraiIntegrasiSistem.jsp";
			}
		
		}
		else if(command.equals("viewDetailsIntegrasi"))
		{
			Type = getParam("Type");
			this.context.put("Type",Type);
			
			String ID_INTEGRASI = getParam("ID_INTEGRASI");
			this.context.put("ID_INTEGRASI",ID_INTEGRASI);
			
			detailIntegrasi = getDetailIntegrasi(session,Type,ID_INTEGRASI);
			this.context.put("detailIntegrasi",detailIntegrasi);	
			
			listDokumen = getListDoc(session, ID_INTEGRASI);
			this.context.put("listDokumen",listDokumen);
			
			listTrans = getListTransaksi(session,ID_INTEGRASI);
			this.context.put("listTrans",listTrans);
			
			if(getParam("FlagCetak").equals("Y"))
			{
				skrin_name = "app/admin/Integrasi/viewDetailsIntegrasiPrint.jsp";	
			}
			else if(Type.equals("1"))
			{
			skrin_name = "app/admin/Integrasi/viewDetailsIntegrasi.jsp";
			}
			else if(Type.equals("2"))
			{
			skrin_name = "app/admin/Integrasi/viewDetailsIntegrasiLuaran.jsp";
			}
			else if(Type.equals("3"))
			{
			skrin_name = "app/admin/Integrasi/viewDetailsIntegrasiSistem.jsp";
			}
			else {
				
			}
			
		}
		/*else if(command.equals("popupUpload")){
			
			this.context.put("isComplete", false);
			
			String ID_INTEGRASI = getParam("ID_INTEGRASI");
			this.context.put("ID_INTEGRASI",ID_INTEGRASI);
			
			myLogger.info("ID_INTEGRASI -- "+ID_INTEGRASI);
			
			skrin_name = "app/admin/Integrasi/FrmTambahLampiran.jsp";
		}*/
		else if(command.equals("simpanDokumen")){
			
			String ID_INTEGRASI = getParam("ID_INTEGRASI");
			this.context.put("ID_INTEGRASI_AFTERUPLOAD",ID_INTEGRASI);
			myLogger.info("ID_INTEGRASI simpanDokumen -- "+ID_INTEGRASI);
			
			String returnDivUpload = getParam("returnDivUpload");
			this.context.put("returnDivUpload",returnDivUpload);
			
			this.context.put("commandDoc", "showDokumen");
			this.context.put("after_saveDOC", "Y");
			
			uploadFiles(ID_INTEGRASI);
	       	this.context.put("isComplete", true);
	       	this.context.put("COOR_UPLOAD",getParam("getPageCoor"));
			this.context.put("after_uploadLampiran","Y");			
			
			skrin_name = "app/admin/Integrasi/index.jsp";

		}
		else if(command.equals("showDokumen")){
			
			String FLAG_DELETE = getParam("FLAG_DELETE");
			
			if (FLAG_DELETE.equals("Y")){
				//delete dokumen
				String ID_DOKUMEN = getParam("ID_DOKUMEN");
				deleteDokumen(session, ID_DOKUMEN);	
				this.context.put("SuccessMesejDeleteUser", "Dokumen Berjaya Dihapus");
			}
			
			
			String ID_INTEGRASI = getParam("ID_INTEGRASI");
			this.context.put("ID_INTEGRASI",ID_INTEGRASI);
			
			listDokumen = getListDoc(session, ID_INTEGRASI);
			this.context.put("listDokumen",listDokumen);
					
			skrin_name = "app/admin/Integrasi/SenaraiLampiranIntegrasi.jsp";

		}
		else if(command.equals("addInteg"))
		{
			Type = getParam("Type");
			this.context.put("Type",Type);
			
			String ID_INTEGRASI = getParam("ID_INTEGRASI");
			this.context.put("ID_INTEGRASI",ID_INTEGRASI);
			
			detailIntegrasi = getDetailIntegrasi(session,Type,ID_INTEGRASI);
			this.context.put("detailIntegrasi",detailIntegrasi);	
			
			list_TBLRUJJENISPEJABAT = listTableRujukan(session,"TBLRUJJENISPEJABAT","");
			this.context.put("list_TBLRUJJENISPEJABAT",list_TBLRUJJENISPEJABAT);
			
			list_TBLRUJNEGERI = listTableRujukan(session,"TBLRUJNEGERI","");
			this.context.put("list_TBLRUJNEGERI",list_TBLRUJNEGERI);	
			
		 if(Type.equals("1"))
			{
			 	skrin_name = "app/admin/Integrasi/editDetailsIntegrasi.jsp";
			}
			else if(Type.equals("2"))
			{
				skrin_name = "app/admin/Integrasi/editDetailsIntegrasiLuaran.jsp";
			}
			else if(Type.equals("3"))
			{
				skrin_name = "app/admin/Integrasi/editDetailsIntegrasiSistem.jsp";
			}
			else {
				
			}
			
		}
		else if(command.equals("saveInteg"))
		{
			Type = getParam("Type");
			this.context.put("Type",Type);
			
			String ID_INTEGRASI = getParam("ID_INTEGRASI");
			this.context.put("ID_INTEGRASI",ID_INTEGRASI);
			
			//check kat tblrujintegrasi dahulu
			//boolean checkIdInt = checkTblIntegrasi(session,ID_INTEGRASI);
			
			//myLogger.info("result checkIdInt : "+checkIdInt);
			
			if(ID_INTEGRASI==null || ID_INTEGRASI.equals("") || ID_INTEGRASI.equals("null")){
				//insert
				System.out.println("ID_INTEGRASI insert? -- "+ID_INTEGRASI);
				ID_INTEGRASI = saveDetailIntegrasi(session,Type,ID_INTEGRASI);
				System.out.println("ID_INTEGRASI after insert? -- "+ID_INTEGRASI);
				
			}else {
				//update
				
			//	if (checkIdInt == true) {
				System.out.println("ID_INTEGRASI update? -- "+ID_INTEGRASI);
				ID_INTEGRASI = saveDetailIntegrasi(session,Type,ID_INTEGRASI);
				System.out.println("ID_INTEGRASI after update? -- "+ID_INTEGRASI);
				/*} else {
					String ID_PEJABAT = ID_INTEGRASI;
					//insert tblrujintegrasi
					ID_INTEGRASI = saveDetailIntegrasi(session,Type,ID_PEJABAT,checkIdInt);
					System.out.println("ID_INTEGRASI after insert? -- "+ID_INTEGRASI);
					ID_INTEGRASI = ID_PEJABAT; //SET ID PEJABAT UNTUK SHOW DETAILS
				}*/
			}
			
			detailIntegrasi = getDetailIntegrasi(session,Type,ID_INTEGRASI);
			this.context.put("detailIntegrasi",detailIntegrasi);	

			skrin_name = "app/admin/Integrasi/viewDetailsIntegrasi.jsp";
		}
		
		//buat cetak
		
		return skrin_name;	
	}
	
	//checking id integrasi
	public boolean checkTblIntegrasi(HttpSession session, String ID_INTEGRASI) throws Exception {
		
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		boolean check = false;
		try {
			db = new Db();
			stmt = db.getStatement();
			Hashtable h;
			h = new Hashtable();
			
				sql = " SELECT ID_INTEGRASI FROM TBLRUJINTEGRASI WHERE ID_INTEGRASI = "+ ID_INTEGRASI;
			
				myLogger.info("checkIdIntegrasi :" + sql.toUpperCase());
				rs = stmt.executeQuery(sql);
				
				String GET_USER_ID = "";
				while (rs.next()) {
					
					GET_USER_ID = rs.getString("ID_INTEGRASI") == null ? "" : rs.getString("ID_INTEGRASI");
					if(!GET_USER_ID.equals(""))
					{
						check = true;
					}
					
				}
			
			return check;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
	}
	
	//delete doc
	public void deleteDokumen(HttpSession session, String ID_DOKUMEN) throws Exception {
		Connection conn = null;
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			
			if (!ID_DOKUMEN.equals("")){
				r.add("ID_DOKUMEN", ID_DOKUMEN);
				sql = r.getSQLDelete("TBLINTEGRASIDOKUMEN");
				AuditTrail.logActivity(this,session,"DEL","DOKUMEN ["+ID_DOKUMEN+"] Deleted");
			}
			
			stmt.executeUpdate(sql);
			conn.commit();
			
		} 
		catch (SQLException se) { 
			myLogger.error(se);
	    	try {
	    		conn.rollback();
	    	} catch (SQLException se2) {
	    		throw new Exception("Rollback error:"+se2.getMessage());
	    	}
	    	throw new Exception("Ralat Delete DOKUMEN :"+se.getMessage());
		}
		catch (Exception re) {
			throw re;
		}finally {
			if (db != null)
				db.close();
		}
	}
	
	//tambah dokumen
	private void uploadFiles(String ID_INTEGRASI) throws Exception {
	    DiskFileItemFactory factory = new DiskFileItemFactory();
	    ServletFileUpload upload = new ServletFileUpload(factory);
	    boolean isMultipart = ServletFileUpload.isMultipartContent(request);
	    if (isMultipart) {
		    List items = upload.parseRequest(this.request);
		    Iterator itr = items.iterator();
		    while (itr.hasNext()) {
		      FileItem item = (FileItem)itr.next();
		      if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
		    	 saveData(item,ID_INTEGRASI);
		      }
		    }
	    }
	  }

	private void saveData(FileItem item, String ID_INTEGRASI) throws Exception {
			Db db = null;
			
			HttpSession session = this.request.getSession();
			String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
			
	    try {
	    	db = new Db();
	    	long idLampiran = DB.getNextID("TBLINTEGRASIDOKUMEN_SEQ");
	    	Connection con = db.getConnection();
	    	con.setAutoCommit(false);
	    	PreparedStatement ps = con.prepareStatement("INSERT INTO TBLINTEGRASIDOKUMEN " +
	    			"(ID_DOKUMEN, ID_INTEGRASI, NAMA_DOKUMEN, JENIS_DOKUMEN, DOKUMEN, " +
	    			" JENISUP_DOKUMEN, KETERANGAN, TARIKH_MASUK, ID_MASUK) " +
	    			"values(?,?,?,?,?,?,?,SYSDATE,?)");
	    	ps.setLong(1,idLampiran);
	    	ps.setString(2, ID_INTEGRASI);
	    	ps.setString(3,item.getName());
	    	ps.setString(4,item.getContentType());
	    	ps.setBinaryStream(5,item.getInputStream(),(int)item.getSize());
	    	ps.setString(6,getParam("noRujukan"));
	    	ps.setString(7,getParam("keteranganDokumen"));
	    	ps.setString(8,USER_ID_SYSTEM);
	    	
	    	ps.executeUpdate();
	    	
	    	
	        con.commit();
	    } finally {
		      if (db != null) db.close();
	    }
	}

	
	//show transaction list
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List getListTransaksi(HttpSession session, String ID_INTEGRASI)throws Exception {
				
		Db db = null; 
		ResultSet rs = null;
		Statement stmt = null;
		SQLRenderer r = new SQLRenderer();
		List listDoc = null;
		String sql = "";
		
		try {
			db = new Db();
			stmt = db.getStatement();
						sql = 	" SELECT ID_DOKUMEN, ID_INTEGRASI, NAMA_DOKUMEN, JENIS_DOKUMEN, DOKUMEN, " +
								" TO_CHAR(TARIKH_MASUK,'DD/MM/YYYY') AS TARIKH_MASUK , ID_MASUK, " +
								" TARIKH_KEMASKINI, ID_KEMASKINI, KETERANGAN, JENISUP_DOKUMEN "+
								" FROM TBLLOGTRANSAKSIMTPPK "+
								" WHERE ID_INTEGRASI = "+ ID_INTEGRASI +
								" ORDER BY ID_DOKUMEN DESC ";
					
					myLogger.debug("SQL LIST DOC - "+sql.toUpperCase());
					 rs = stmt.executeQuery(sql);
					
					 listDoc = Collections.synchronizedList(new ArrayList());
					Map h = null;
					int bil = 0;
					while (rs.next()) {
						h = Collections.synchronizedMap(new HashMap());
						bil++;
						String rowCss = "";
						if ( (bil % 2) == 0 )
						{
							rowCss = "row2";
						}
				        else
				        {
				        	rowCss = "row1";
				        }
						h.put("rowCss",rowCss);
						h.put("BIL",bil);
						h.put("ID_DOKUMEN",checkIsNull(rs.getString("ID_DOKUMEN")));
						h.put("ID_INTEGRASI",checkIsNull(rs.getString("ID_INTEGRASI")));
						h.put("NAMA_DOKUMEN",checkIsNull(rs.getString("NAMA_DOKUMEN")));
						h.put("JENIS_DOKUMEN",checkIsNull(rs.getString("JENIS_DOKUMEN")));
						h.put("DOKUMEN",checkIsNull(rs.getString("DOKUMEN")));
						h.put("TARIKH_MASUK",checkIsNull(rs.getString("TARIKH_MASUK")));
						h.put("ID_MASUK",checkIsNull(rs.getString("ID_MASUK")));
						h.put("TARIKH_KEMASKINI",checkIsNull(rs.getString("TARIKH_KEMASKINI")));
						h.put("ID_KEMASKINI",checkIsNull(rs.getString("ID_KEMASKINI")));
						h.put("KETERANGAN",checkIsNull(rs.getString("KETERANGAN")));
						h.put("JENISUP_DOKUMEN",checkIsNull(rs.getString("JENISUP_DOKUMEN")));
						
						listDoc.add(h);
					}
						
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (db != null) db.close();
				}
				
				return listDoc;
				
			}
	//show doc
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List getListDoc(HttpSession session, String ID_INTEGRASI)throws Exception {
				
		Db db = null; 
		ResultSet rs = null;
		Statement stmt = null;
		SQLRenderer r = new SQLRenderer();
		List listDoc = null;
		String sql = "";
		
		try {
			db = new Db();
			stmt = db.getStatement();
						sql = 	" SELECT ID_DOKUMEN, ID_INTEGRASI, NAMA_DOKUMEN, JENIS_DOKUMEN, DOKUMEN, " +
								" TO_CHAR(TARIKH_MASUK,'DD/MM/YYYY') AS TARIKH_MASUK , ID_MASUK, " +
								" TARIKH_KEMASKINI, ID_KEMASKINI, KETERANGAN, JENISUP_DOKUMEN "+
								" FROM TBLINTEGRASIDOKUMEN "+
								" WHERE ID_INTEGRASI = "+ ID_INTEGRASI +
								" ORDER BY ID_DOKUMEN DESC ";
					
					myLogger.debug("SQL LIST DOC - "+sql.toUpperCase());
					 rs = stmt.executeQuery(sql);
					
					 listDoc = Collections.synchronizedList(new ArrayList());
					Map h = null;
					int bil = 0;
					while (rs.next()) {
						h = Collections.synchronizedMap(new HashMap());
						bil++;
						String rowCss = "";
						if ( (bil % 2) == 0 )
						{
							rowCss = "row2";
						}
				        else
				        {
				        	rowCss = "row1";
				        }
						h.put("rowCss",rowCss);
						h.put("BIL",bil);
						h.put("ID_DOKUMEN",checkIsNull(rs.getString("ID_DOKUMEN")));
						h.put("ID_INTEGRASI",checkIsNull(rs.getString("ID_INTEGRASI")));
						h.put("NAMA_DOKUMEN",checkIsNull(rs.getString("NAMA_DOKUMEN")));
						h.put("JENIS_DOKUMEN",checkIsNull(rs.getString("JENIS_DOKUMEN")));
						h.put("DOKUMEN",checkIsNull(rs.getString("DOKUMEN")));
						h.put("TARIKH_MASUK",checkIsNull(rs.getString("TARIKH_MASUK")));
						h.put("ID_MASUK",checkIsNull(rs.getString("ID_MASUK")));
						h.put("TARIKH_KEMASKINI",checkIsNull(rs.getString("TARIKH_KEMASKINI")));
						h.put("ID_KEMASKINI",checkIsNull(rs.getString("ID_KEMASKINI")));
						h.put("KETERANGAN",checkIsNull(rs.getString("KETERANGAN")));
						h.put("JENISUP_DOKUMEN",checkIsNull(rs.getString("JENISUP_DOKUMEN")));
						
						listDoc.add(h);
					}
						
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (db != null) db.close();
				}
				
				return listDoc;
				
			}
	
	//save integ
	public String saveDetailIntegrasi(HttpSession session, String Type, String ID_INTEGRASI) throws Exception {
		Connection conn = null;
		Db db = null;
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
		String sql = "";

		long ID_INTEGRASI_INSERT = 0;
		
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			
			String NAMA = getParam("NAMA_"+ID_INTEGRASI);
			String NAMA_LAIN = getParam("NAMA_LAIN_"+ID_INTEGRASI);
			String KOD_AGENSI = getParam("KOD_AGENSI_"+ID_INTEGRASI);
			
			String ALAMAT1 = getParam("ALAMAT1_"+ID_INTEGRASI);
			String ALAMAT2 = getParam("ALAMAT2_"+ID_INTEGRASI);
			String ALAMAT3 = getParam("ALAMAT3_"+ID_INTEGRASI);
			String POSKOD = getParam("POSKOD_"+ID_INTEGRASI);
			String ID_NEGERI = getParam("ID_NEGERI_"+ID_INTEGRASI);
			
			String EMEL = getParam("EMEL_"+ID_INTEGRASI);
			String CATATAN = getParam("CATATAN_"+ID_INTEGRASI);
			
			String FLAG_AKTIF = getParam("FLAG_AKTIF_"+ID_INTEGRASI);
			
			String JENIS_INTEGRASI = getParam("JENIS_INTEGRASI_"+ID_INTEGRASI);
			String KATEGORI = getParam("KATEGORI_"+ID_INTEGRASI);
			
			String JENIS_PU = getParam("JENIS_PU_"+ID_INTEGRASI);
			
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			
			r.add("NAMA", NAMA.toUpperCase());
			r.add("NAMA_LAIN", NAMA_LAIN.toUpperCase());
			r.add("KOD_AGENSI", KOD_AGENSI.toUpperCase());
			r.add("ALAMAT1", ALAMAT1.toUpperCase());
			r.add("ALAMAT2", ALAMAT2.toUpperCase());
			r.add("ALAMAT3", ALAMAT3.toUpperCase());
			r.add("POSKOD", POSKOD);
			r.add("ID_NEGERI", ID_NEGERI);
			r.add("FLAG_AKTIF", FLAG_AKTIF);
			r.add("ID_JENISINTEG", JENIS_INTEGRASI);
			r.add("ID_KATEGORI", KATEGORI);
			
			r.add("ID_JENISPEJABAT", JENIS_PU);
			
			r.add("EMEL", EMEL);
			r.add("CATATAN", CATATAN);
			
			if (Type.equals("2")){
				
				String NAMA_PEMILIK = getParam("NAMA_PEMILIK_"+ID_INTEGRASI);
				String NAMA_LAIN_PEMILIK = getParam("NAMA_LAIN_PEMILIK_"+ID_INTEGRASI);
				String KATEG_PEMILIK = getParam("KATEG_PEMILIK_"+ID_INTEGRASI);
				
				r.add("NAMA_PEMILIK", NAMA_PEMILIK);
				r.add("NAMA_LAIN_PEMILIK", NAMA_LAIN_PEMILIK);
				r.add("KATEG_PEMILIK", KATEG_PEMILIK);
				
			}
			
			
			if(ID_INTEGRASI.equals("")){
				
			myLogger.info("MASUK INSERT INTEGRASI : "+ID_INTEGRASI);
				
			ID_INTEGRASI_INSERT = DB.getNextID(db,"TBLRUJINTEGRASI_SEQ");
			ID_INTEGRASI = ID_INTEGRASI_INSERT+"";
		
			r.add("ID_INTEGRASI", ID_INTEGRASI);
			r.add("ID_MASUK", USER_ID_SYSTEM);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			
			sql = r.getSQLInsert("TBLRUJINTEGRASI");	
			
			/*}else if (!ID_INTEGRASI.equals("") && checkIdInt == false ){
				
			myLogger.info("MASUK INSERT INTEGRASI 2 : "+ID_INTEGRASI);
			
			ID_INTEGRASI_INSERT = DB.getNextID(db,"TBLRUJINTEGRASI_SEQ");
			ID_INTEGRASI = ID_INTEGRASI_INSERT+"";
		
			r.add("ID_INTEGRASI", ID_INTEGRASI);
			r.add("ID_MASUK", USER_ID_SYSTEM);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			
			sql = r.getSQLInsert("TBLRUJINTEGRASI");
			}*/
			}	
			else{
			r.update("ID_INTEGRASI", ID_INTEGRASI);	
			r.add("ID_KEMASKINI", USER_ID_SYSTEM);
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			
			sql = r.getSQLUpdate("TBLRUJINTEGRASI");	
			
			}
			
			myLogger.info("ACTION INTEGRASI : "+sql.toUpperCase());
			
			stmt.executeUpdate(sql);
			conn.commit();
			
		} 
		catch (SQLException se) { 
			myLogger.error(se);
	    	try {
	    		conn.rollback();
	    	} catch (SQLException se2) {
	    		throw new Exception("Rollback error:"+se2.getMessage());
	    	}
	    	throw new Exception("Ralat action save DAERAH :"+se.getMessage());
		}
		catch (Exception re) {
			throw re;
		}finally {
			if (db != null)
				db.close();
		}
		return ID_INTEGRASI;
	}
	
	//details integ
	
	@SuppressWarnings("unchecked")
	public Hashtable getDetailIntegrasiX (HttpSession session, String Type, String ID_INTEGRASI) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			db = new Db();
			stmt = db.getStatement();
			Hashtable h;
			h = new Hashtable();
			
			if(!ID_INTEGRASI.equals(""))
			{
				
				sql = 	" SELECT I.ID_INTEGRASI, I.ID_JENISINTEG, I.ID_KATEGORI, I.NAMA, I.NAMA_LAIN, I.KOD_AGENSI, " +
						" I.PENERANGAN, I.STATUS, I.NAMA_PEMILIK, I.ALAMAT1, I.ALAMAT2, I.ALAMAT3, I.POSKOD, I.ID_NEGERI, " +
						" I.NO_TEL, I.EMEL, I.CATATAN, I.FLAG_AKTIF, TO_CHAR(I.TARIKH_MASUK,'DD/MM/YYYY') TARIKH_MASUK," +
						" I.ID_MASUK, TO_CHAR(I.TARIKH_KEMASKINI,'DD/MM/YYYY') TARIKH_KEMASKINI, " +
						" I.ID_KEMASKINI, N.NAMA_NEGERI, U.USER_NAME, " +
						" I.NAMA_LAIN_PEMILIK, I.KATEG_PEMILIK "+
						" FROM TBLRUJINTEGRASI I, TBLRUJNEGERI N, USERS U " +
						" WHERE I.ID_INTEGRASI = "+ ID_INTEGRASI +
						" AND I.ID_NEGERI = N.ID_NEGERI " +
						" AND I.ID_MASUK = U.USER_ID " ;
				
				rs = stmt.executeQuery(sql);				
								
				while (rs.next()) {
					
					h.put("ID_INTEGRASI",rs.getString("ID_INTEGRASI") == null ? "" : rs.getString("ID_INTEGRASI"));
					h.put("KOD_AGENSI",rs.getString("KOD_AGENSI") == null ? "" : rs.getString("KOD_AGENSI"));
					h.put("NAMA",rs.getString("NAMA") == null ? "" : rs.getString("NAMA"));
					h.put("NAMA_LAIN",rs.getString("NAMA_LAIN") == null ? "" : rs.getString("NAMA_LAIN"));
					h.put("ID_NEGERI",rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
					h.put("ID_KATEGORI",rs.getString("ID_KATEGORI") == null ? "" : rs.getString("ID_KATEGORI"));
					h.put("FLAG_AKTIF",rs.getString("FLAG_AKTIF") == null ? "" : rs.getString("FLAG_AKTIF"));
					
					h.put("ID_JENISINTEG",rs.getString("ID_JENISINTEG") == null ? "" : rs.getString("ID_JENISINTEG"));
					h.put("PENERANGAN",rs.getString("PENERANGAN") == null ? "" : rs.getString("PENERANGAN"));
					h.put("STATUS",rs.getString("STATUS") == null ? "" : rs.getString("STATUS"));
					h.put("NAMA_PEMILIK",rs.getString("NAMA_PEMILIK") == null ? "" : rs.getString("NAMA_PEMILIK"));
					h.put("ALAMAT1",rs.getString("ALAMAT1") == null ? "" : rs.getString("ALAMAT1"));
					h.put("ALAMAT2",rs.getString("ALAMAT2") == null ? "" : rs.getString("ALAMAT2"));
					h.put("ALAMAT3",rs.getString("ALAMAT3") == null ? "" : rs.getString("ALAMAT3"));
					
					h.put("POSKOD",rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD"));
					h.put("ID_KEMASKINI",rs.getString("ID_KEMASKINI") == null ? "" : rs.getString("ID_KEMASKINI"));
					h.put("NO_TEL",rs.getString("NO_TEL") == null ? "" : rs.getString("NO_TEL"));
					h.put("EMEL",rs.getString("EMEL") == null ? "" : rs.getString("EMEL"));
					h.put("NAMA_NEGERI",rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI"));
					h.put("CATATAN",rs.getString("CATATAN") == null ? "-" : rs.getString("CATATAN"));
					
					h.put("TARIKH_MASUK",rs.getString("TARIKH_MASUK") == null ? "" : rs.getString("TARIKH_MASUK"));
					h.put("ID_MASUK",rs.getString("ID_MASUK") == null ? "" : rs.getString("ID_MASUK"));
					h.put("TARIKH_KEMASKINI",rs.getString("TARIKH_KEMASKINI") == null ? "" : rs.getString("TARIKH_KEMASKINI"));
					h.put("ID_KEMASKINI",rs.getString("ID_KEMASKINI") == null ? "" : rs.getString("ID_KEMASKINI"));
					
					h.put("USER_NAME",rs.getString("USER_NAME") == null ? "" : rs.getString("USER_NAME"));
					
					//h.put("NAMA_PEMILIK",rs.getString("NAMA_LAIN_PEMILIK") == null ? "" : rs.getString("NAMA_PEMILIK"));
					h.put("NAMA_LAIN_PEMILIK",rs.getString("NAMA_LAIN_PEMILIK") == null ? "" : rs.getString("NAMA_LAIN_PEMILIK"));
					h.put("KATEG_PEMILIK",rs.getString("KATEG_PEMILIK") == null ? "" : rs.getString("KATEG_PEMILIK"));
					
					}
			}
			else
			{
				h.put("ID_INTEGRASI","");
				h.put("KOD_AGENSI","");
				h.put("NAMA","");
				h.put("NAMA_LAIN","");
				h.put("ID_NEGERI","");
				h.put("ID_KATEGORI","");
				h.put("FLAG_AKTIF","");
				
				h.put("ID_JENISINTEG","");
				h.put("PENERANGAN","");
				h.put("STATUS","");
				h.put("NAMA_PEMILIK","");
				h.put("NAMA_LAIN_PEMILIK","");
				h.put("KATEG_PEMILIK","");
				h.put("ALAMAT1","");
				h.put("ALAMAT2","");
				h.put("ALAMAT3","");
				
				h.put("POSKOD","");
				h.put("NAMA_NEGERI","");
				h.put("NO_TEL","");
				h.put("EMEL","");
				h.put("CATATAN","");
				
				h.put("TARIKH_MASUK","");
				h.put("ID_MASUK","");
				h.put("TARIKH_KEMASKINI","");
				h.put("ID_KEMASKINI","");
				h.put("USER_NAME","");
			}
			
			myLogger.info(" SQL Details integrasi :" + sql.toUpperCase());
			
			return h;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public Hashtable getDetailIntegrasi (HttpSession session, String Type, String ID_INTEGRASI) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			db = new Db();
			stmt = db.getStatement();
			Hashtable h;
			h = new Hashtable();
			
			if(!ID_INTEGRASI.equals(""))
			{
				/*		if (Type.equals("1")){
					
				sql = 	" SELECT P.ID_PEJABAT AS ID_INTEGRASI, '' AS ID_JENISINTEG, '1' AS ID_KATEGORI, " +
						" UPPER(P.NAMA_PEJABAT) AS NAMA, '' AS NAMA_LAIN, P.KOD_PEJABAT AS KOD_AGENSI, " +
						" 'INTEGRASI DALAMAN' AS PENERANGAN, '' AS STATUS, '' AS NAMA_PEMILIK, " +
						" UPPER(P.ALAMAT1) AS ALAMAT1, UPPER(P.ALAMAT2) AS ALAMAT2, UPPER(P.ALAMAT3) AS ALAMAT3, " +
						" P.POSKOD, N.ID_NEGERI, N.NAMA_NEGERI, " +
						 " P.NO_TEL, P.NO_FAX, '-' AS EMEL, '-' AS CATATAN,  " +
						 " (CASE WHEN P.FLAG_AKTIF = 1 THEN 'AKTIF' " +
						 " WHEN P.FLAG_AKTIF = 2 THEN 'TIDAK AKTIF' " +
						 " ELSE 'AKTIF' END) AS FLAG_AKTIF, " +
						 " TO_CHAR(P.TARIKH_MASUK,'DD/MM/YYYY') TARIKH_MASUK," +
						 " P.ID_MASUK, TO_CHAR(P.TARIKH_KEMASKINI,'DD/MM/YYYY') TARIKH_KEMASKINI, " +
						 " P.ID_KEMASKINI, '' AS USER_NAME, " +
						 " '' AS NAMA_LAIN_PEMILIK, '' AS KATEG_PEMILIK, JP.ID_JENISPEJABAT, " +
						 " '1' AS ID_JENISINTEG_INT "+
						 " FROM  TBLRUJPEJABAT P, " +
						 " TBLRUJJENISPEJABAT JP, " +
						 " TBLRUJSEKSYEN S, " +
						 " TBLRUJDAERAH D, " +
						 " TBLRUJNEGERI N, " +
						 " TBLRUJBANDAR B " +
						 " WHERE " +
						 " P.ID_DAERAH = D.ID_DAERAH " +
						 " AND P.ID_NEGERI = N.ID_NEGERI " +
						 " AND P.ID_SEKSYEN = S.ID_SEKSYEN " +
						 " AND P.ID_JENISPEJABAT = JP.ID_JENISPEJABAT " +
						 " AND P.ID_BANDAR = B.ID_BANDAR " +
						 " AND P.ID_PEJABAT = "+ID_INTEGRASI;

				}
				else {*/
					
				sql = 	" SELECT I.ID_INTEGRASI, I.ID_JENISINTEG, I.ID_KATEGORI, UPPER(I.NAMA) AS NAMA, " +
						" UPPER(I.NAMA_LAIN) AS NAMA_LAIN, I.KOD_AGENSI, " +
						" I.PENERANGAN, I.STATUS, UPPER(I.NAMA_PEMILIK) AS NAMA_PEMILIK, UPPER(I.ALAMAT1) AS ALAMAT1, " +
						" UPPER(I.ALAMAT2) AS ALAMAT2, UPPER(I.ALAMAT3) AS ALAMAT3, I.POSKOD, I.ID_NEGERI, " +
						" I.NO_TEL, I.EMEL, I.CATATAN, I.FLAG_AKTIF, TO_CHAR(I.TARIKH_MASUK,'DD/MM/YYYY') TARIKH_MASUK," +
						" I.ID_MASUK, TO_CHAR(I.TARIKH_KEMASKINI,'DD/MM/YYYY') TARIKH_KEMASKINI, " +
						" I.ID_KEMASKINI, N.NAMA_NEGERI, U.USER_NAME, I.ID_JENISINTEG AS ID_JENISINTEG_INT, " +
						" UPPER(I.NAMA_LAIN_PEMILIK) AS NAMA_LAIN_PEMILIK, I.KATEG_PEMILIK, I.ID_JENISPEJABAT "+
						" FROM TBLRUJINTEGRASI I, TBLRUJNEGERI N, USERS U " +
						" WHERE I.ID_INTEGRASI = "+ ID_INTEGRASI +
						" AND I.ID_NEGERI = N.ID_NEGERI(+) " +
						" AND I.ID_KEMASKINI = U.USER_ID (+)" ;
				//}
				
				myLogger.info(" SQL Details integrasi :" + sql.toUpperCase());
				
				rs = stmt.executeQuery(sql);	
				
								
				while (rs.next()) {
					
					h.put("ID_INTEGRASI",rs.getString("ID_INTEGRASI") == null ? "" : rs.getString("ID_INTEGRASI"));
					h.put("KOD_AGENSI",rs.getString("KOD_AGENSI") == null ? "-" : rs.getString("KOD_AGENSI"));
					h.put("NAMA",rs.getString("NAMA") == null ? "-" : rs.getString("NAMA"));
					h.put("NAMA_LAIN",rs.getString("NAMA_LAIN") == null ? "-" : rs.getString("NAMA_LAIN"));
					h.put("ID_NEGERI",rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
					h.put("ID_KATEGORI",rs.getString("ID_KATEGORI") == null ? "" : rs.getString("ID_KATEGORI"));
					h.put("FLAG_AKTIF",rs.getString("FLAG_AKTIF") == null ? "" : rs.getString("FLAG_AKTIF"));
					
					h.put("ID_JENISINTEG",rs.getString("ID_JENISINTEG") == null ? "" : rs.getString("ID_JENISINTEG"));
					h.put("PENERANGAN",rs.getString("PENERANGAN") == null ? "-" : rs.getString("PENERANGAN"));
					h.put("STATUS",rs.getString("STATUS") == null ? "" : rs.getString("STATUS"));
					h.put("NAMA_PEMILIK",rs.getString("NAMA_PEMILIK") == null ? "-" : rs.getString("NAMA_PEMILIK"));
					h.put("ALAMAT1",rs.getString("ALAMAT1") == null ? "-" : rs.getString("ALAMAT1"));
					h.put("ALAMAT2",rs.getString("ALAMAT2") == null ? "-" : rs.getString("ALAMAT2"));
					h.put("ALAMAT3",rs.getString("ALAMAT3") == null ? "-" : rs.getString("ALAMAT3"));
					
					h.put("POSKOD",rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD"));
					h.put("ID_KEMASKINI",rs.getString("ID_KEMASKINI") == null ? "" : rs.getString("ID_KEMASKINI"));
					h.put("NO_TEL",rs.getString("NO_TEL") == null ? "-" : rs.getString("NO_TEL"));
					h.put("EMEL",rs.getString("EMEL") == null ? "-" : rs.getString("EMEL"));
					h.put("NAMA_NEGERI",rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI"));
					h.put("CATATAN",rs.getString("CATATAN") == null ? "-" : rs.getString("CATATAN"));
					
					h.put("TARIKH_MASUK",rs.getString("TARIKH_MASUK") == null ? "-" : rs.getString("TARIKH_MASUK"));
					h.put("ID_MASUK",rs.getString("ID_MASUK") == null ? "" : rs.getString("ID_MASUK"));
					h.put("TARIKH_KEMASKINI",rs.getString("TARIKH_KEMASKINI") == null ? "-" : rs.getString("TARIKH_KEMASKINI"));
					h.put("ID_KEMASKINI",rs.getString("ID_KEMASKINI") == null ? "" : rs.getString("ID_KEMASKINI"));
					
					h.put("USER_NAME",rs.getString("USER_NAME") == null ? "-" : rs.getString("USER_NAME"));
					
					//h.put("NAMA_PEMILIK",rs.getString("NAMA_LAIN_PEMILIK") == null ? "" : rs.getString("NAMA_PEMILIK"));
					h.put("NAMA_LAIN_PEMILIK",rs.getString("NAMA_LAIN_PEMILIK") == null ? "-" : rs.getString("NAMA_LAIN_PEMILIK"));
					h.put("KATEG_PEMILIK",rs.getString("KATEG_PEMILIK") == null ? "" : rs.getString("KATEG_PEMILIK"));
					h.put("ID_JENISINTEG_INT",rs.getString("ID_JENISINTEG_INT") == null ? "" : rs.getString("ID_JENISINTEG_INT"));
					h.put("ID_JENISPEJABAT",rs.getString("ID_JENISPEJABAT") == null ? "" : rs.getString("ID_JENISPEJABAT"));
					
					}
			}
			else
			{
				h.put("ID_INTEGRASI","");
				h.put("KOD_AGENSI","");
				h.put("NAMA","");
				h.put("NAMA_LAIN","");
				h.put("ID_NEGERI","");
				h.put("ID_KATEGORI","");
				h.put("FLAG_AKTIF","");
				
				h.put("ID_JENISINTEG","");
				h.put("PENERANGAN","");
				h.put("STATUS","");
				h.put("NAMA_PEMILIK","");
				h.put("NAMA_LAIN_PEMILIK","");
				h.put("KATEG_PEMILIK","");
				h.put("ALAMAT1","");
				h.put("ALAMAT2","");
				h.put("ALAMAT3","");
				
				h.put("POSKOD","");
				h.put("NAMA_NEGERI","");
				h.put("NO_TEL","");
				h.put("EMEL","");
				h.put("CATATAN","");
				
				h.put("TARIKH_MASUK","");
				h.put("ID_MASUK","");
				h.put("TARIKH_KEMASKINI","");
				h.put("ID_KEMASKINI","");
				h.put("USER_NAME","");
				h.put("ID_JENISINTEG_INT","");
				h.put("ID_JENISPEJABAT","");
			}
			
			
			
			return h;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
	}
	
	
	public void deleteIntegrasi(HttpSession session,String ID_INTEGRASI) throws Exception {
		Connection conn = null;
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			
			if (!ID_INTEGRASI.equals("")){
				r.add("ID_INTEGRASI", ID_INTEGRASI);
				sql = r.getSQLDelete("TBLRUJINTEGRASI");
				AuditTrail.logActivity(this,session,"DEL"," ["+ID_INTEGRASI+"] Deleted");
			}
			
			stmt.executeUpdate(sql);
			conn.commit();
			
		} 
		catch (SQLException se) { 
			myLogger.error(se);
	    	try {
	    		conn.rollback();
	    	} catch (SQLException se2) {
	    		throw new Exception("Rollback error:"+se2.getMessage());
	    	}
	    	throw new Exception("Ralat Delete INTEGRASI :"+se.getMessage());
		}
		catch (Exception re) {
			throw re;
		}finally {
			if (db != null)
				db.close();
		}
	}
	
	//list integ
	@SuppressWarnings("unchecked")
	public List getListIntegrasi(HttpSession session, String Type, String carianUmum)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listInteg = null;
		String sql = "";
		
		//open ct
		String CT_OPENCLOSE_CARIAN = getParam("CT_OPENCLOSE_CARIAN_"+Type);
		String CT_FLAGTEPERINCI_CARIAN = getParam("CT_FLAGTEPERINCI_CARIAN_"+Type);	
		
		String CT_NAMA = getParam("CT_NAMA_"+Type);
		String CT_ID_NEGERI = getParam("CT_ID_NEGERI_"+Type);
		
		String CT_FLAG_AKTIF = getParam("CT_FLAG_AKTIF_"+Type);
		String CT_KATEGORI = getParam("CT_KATEGORI_"+Type);
		
		
		this.context.put("CT_OPENCLOSE_CARIAN",CT_OPENCLOSE_CARIAN);
		this.context.put("CT_FLAGTEPERINCI_CARIAN", CT_FLAGTEPERINCI_CARIAN);
		
		this.context.put("CT_NAMA", CT_NAMA);
		this.context.put("CT_ID_NEGERI", CT_ID_NEGERI);
		
		this.context.put("CT_FLAG_AKTIF", CT_FLAG_AKTIF);
		this.context.put("CT_KATEGORI", CT_KATEGORI);
		this.context.put("Type",Type);
		//close ct
		myLogger.info(" Type ::: "+Type);
		
		
		try {
			db = new Db();
			stmt = db.getStatement();
			
			/*if(Type.equals("1"))
			{
			
			sql = 	" SELECT P.ID_PEJABAT, '' AS ID_INTEGRASI, P.KOD_PEJABAT AS KOD_AGENSI, " +
					" P.ID_JENISPEJABAT AS ID_JENISINTEG, UPPER (P.NAMA_PEJABAT) AS NAMA, " +
					" '' AS NAMA_LAIN, '1' AS ID_JENISINTEG_INT, '1' AS ID_KATEGORI, P.ID_NEGERI, " +
					" (CASE WHEN P.FLAG_AKTIF = 1 THEN 'AKTIF' WHEN P.FLAG_AKTIF = 2 THEN 'TIDAK AKTIF' " +
					" ELSE 'AKTIF' END) AS FLAG_AKTIF FROM   TBLRUJPEJABAT P, TBLRUJJENISPEJABAT JP, " +
					" TBLRUJSEKSYEN S, TBLRUJDAERAH D, TBLRUJNEGERI N, TBLRUJBANDAR B " +
					" WHERE P.ID_DAERAH = D.ID_DAERAH AND P.ID_NEGERI = N.ID_NEGERI " +
					" AND P.ID_JENISPEJABAT = JP.ID_JENISPEJABAT AND P.ID_BANDAR = B.ID_BANDAR " +
					" AND P.ID_SEKSYEN = S.ID_SEKSYEN " +
					" AND P.FLAG_INT = 1 ";
			
			sql +=	" UNION "; 
			
			sql += 	" SELECT P.ID_PEJABATJKPTG AS ID_PEJABAT, '' AS ID_INTEGRASI, P.KOD_JKPTG AS KOD_AGENSI, " +
					" P.ID_JENISPEJABAT AS ID_JENISINTEG, P.NAMA_PEJABAT AS NAMA, '' AS NAMA_LAIN, " +
					" '' AS ID_JENISINTEG_INT, '1' AS ID_KATEGORI, P.ID_NEGERI, (CASE WHEN P.FLAG_AKTIF = 1 " +
					" THEN 'AKTIF' WHEN P.FLAG_AKTIF = 2 THEN 'TIDAK AKTIF' ELSE 'AKTIF' END) AS FLAG_AKTIF "+
					" FROM TBLRUJPEJABATJKPTG P, TBLRUJSEKSYEN S, TBLRUJDAERAH D, TBLRUJNEGERI N, TBLRUJBANDAR B " +
					" WHERE P.ID_DAERAH = D.ID_DAERAH AND P.ID_NEGERI = N.ID_NEGERI AND P.ID_BANDAR = B.ID_BANDAR " +
					" AND P.ID_SEKSYEN = S.ID_SEKSYEN AND P.FLAG_INT = 1"; //AND P.id_jenispejabat = '1617162' " ;
			
			
			//checking terperinci
			
			
			}
			else {*/
			
			sql = 	" SELECT '' AS ID_PEJABAT, I.ID_INTEGRASI, I.ID_JENISINTEG, " +
					" I.KOD_AGENSI, UPPER(I.NAMA) AS NAMA, I.NAMA_LAIN, " +
					" I.FLAG_AKTIF, I.ID_NEGERI, I.ID_KATEGORI, " +
					" I.ID_JENISINTEG AS ID_JENISINTEG_INT " +
					" FROM TBLRUJINTEGRASI I ";
			
			sql+= " WHERE ID_JENISINTEG = "+ Type;
			

						if(!carianUmum.equals("") && carianUmum!=null)
						{
							sql += " AND (";
							sql += " UPPER(I.NAMA) LIKE '%"+carianUmum.trim().toUpperCase()+"%' ";					
							sql += " OR UPPER(I.NAMA_LAIN) LIKE '%"+carianUmum.trim().toUpperCase()+"%' ";	
							sql += " OR UPPER(I.KOD_AGENSI) LIKE '%"+carianUmum.trim().toUpperCase()+"%' ";	

						}
			
						
						if(!CT_NAMA.equals("") && CT_NAMA!=null)
						{
							sql += " AND (";						
							sql += " UPPER(I.NAMA) LIKE '%"+CT_NAMA.trim().toUpperCase()+"%' ";					
							sql += " OR UPPER(I.NAMA_LAIN) LIKE '%"+CT_NAMA.trim().toUpperCase()+"%' ";	
							sql += ") ";
						}						

						if(!CT_ID_NEGERI.equals("") && CT_ID_NEGERI!=null)
						{
							sql += " AND (";						
							sql += " I.ID_NEGERI = '"+CT_ID_NEGERI+"' ";					
							sql += ") ";
						}
						
						if(!CT_FLAG_AKTIF.equals("") && CT_FLAG_AKTIF!=null)
						{
							if(CT_FLAG_AKTIF.equals("1"))
							{
								sql += " AND (";						
								sql += " UPPER(I.FLAG_AKTIF) IS NULL OR UPPER(I.FLAG_AKTIF) = '1' OR UPPER(I.FLAG_AKTIF) = '') ";					
								//sql += ") ";
							}
							else if(CT_FLAG_AKTIF.equals("2"))
							{
								sql += " AND (";						
								sql += " UPPER(I.FLAG_AKTIF) = '2' ";					
								sql += ") ";
							}
						}
						
						if(!CT_KATEGORI.equals("") && CT_KATEGORI!=null)
						{
							sql += " AND (";						
							sql += " I.ID_KATEGORI = '"+CT_KATEGORI+"' ";					
							sql += ") ";
						}
						
					if(!carianUmum.equals("") && carianUmum!=null)
						{
							sql += " ) ";
						}
						
						sql += " ORDER BY I.ID_INTEGRASI ";
			//}						
					//}
					//close ct
	
			myLogger.info(" SQL listInteg :"+ sql);
			
			rs = stmt.executeQuery(sql);
			listInteg = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				bil++;
				String rowCss = "";
				if ( (bil % 2) == 0 )
				{
					rowCss = "row2";
				}
		        else
		        {
		        	rowCss = "row1";
		        }
		
				h.put("rowCss",rowCss);
				h.put("BIL",bil);
				h.put("ID_PEJABAT",rs.getString("ID_PEJABAT") == null ? "" : rs.getString("ID_PEJABAT"));
				h.put("ID_INTEGRASI",rs.getString("ID_INTEGRASI") == null ? "" : rs.getString("ID_INTEGRASI"));
				h.put("KOD_AGENSI",rs.getString("KOD_AGENSI") == null ? "" : rs.getString("KOD_AGENSI"));
				h.put("NAMA",rs.getString("NAMA") == null ? "" : rs.getString("NAMA"));
				h.put("NAMA_LAIN",rs.getString("NAMA_LAIN") == null ? "" : rs.getString("NAMA_LAIN"));
				h.put("ID_NEGERI",rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
				h.put("ID_KATEGORI",rs.getString("ID_KATEGORI") == null ? "" : rs.getString("ID_KATEGORI"));
				h.put("FLAG_AKTIF",rs.getString("FLAG_AKTIF") == null ? "" : rs.getString("FLAG_AKTIF"));
				h.put("ID_JENISINTEG",rs.getString("ID_JENISINTEG") == null ? "" : rs.getString("ID_JENISINTEG"));
				h.put("ID_JENISINTEG_INT",rs.getString("ID_JENISINTEG_INT") == null ? "" : rs.getString("ID_JENISINTEG_INT"));
				
				listInteg.add(h);
			}
			
			
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}

		return listInteg;

	}
	
	
	//LIST TABLE
	@SuppressWarnings("unchecked")
	public List listTableRujukan(HttpSession session, String tableRujukan, String ID )throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listTableRujukan = null;
		String sql = "";
		
		try {
			db = new Db();
			stmt = db.getStatement();
			
			
			if(tableRujukan.equals("TBLRUJJAWATAN"))
			{ 
				sql = " SELECT ID_JAWATAN AS ID, KOD_JAWATAN AS KOD, UPPER(KETERANGAN) AS KETERANGAN FROM TBLRUJJAWATAN WHERE KOD_JAWATAN != '00' ORDER BY KOD_JAWATAN";
			}
			
			else if(tableRujukan.equals("TBLINTRUJJENISPEJABAT"))
			{
				sql = " SELECT ID_JENISPEJABAT AS ID, UPPER(KOD_PEJABAT) AS KOD, UPPER(NAMA_PEJABAT) AS KETERANGAN FROM TBLINTRUJJENISPEJABAT ORDER BY KOD_PEJABAT";
			}
			else if(tableRujukan.equals("TBLRUJBANGSA"))
			{
				sql = " SELECT ID_BANGSA AS ID, KOD_BANGSA AS KOD, UPPER(KETERANGAN) AS KETERANGAN FROM TBLRUJBANGSA WHERE ID_BANGSA IN (1,2,3,7) ORDER BY ID_BANGSA";
			}
			else if(tableRujukan.equals("TBLRUJSEKSYEN"))
			{
				sql = " SELECT ID_SEKSYEN AS ID, KOD_SEKSYEN AS KOD, UPPER(NAMA_SEKSYEN) AS KETERANGAN FROM TBLRUJSEKSYEN ORDER BY ID_SEKSYEN";
			}
			else if(tableRujukan.equals("TBLRUJAGAMA"))
			{
				sql = " SELECT ID_AGAMA AS ID, KOD_AGAMA AS KOD, UPPER(KETERANGAN) AS KETERANGAN FROM TBLRUJAGAMA ORDER BY KOD_AGAMA";
			}
			else if(tableRujukan.equals("TBLRUJKEMENTERIAN"))
			{
				sql = " SELECT ID_KEMENTERIAN AS ID, KOD_KEMENTERIAN AS KOD, UPPER(NAMA_KEMENTERIAN) AS KETERANGAN  FROM TBLRUJKEMENTERIAN WHERE ID_KEMENTERIAN IS NOT NULL ORDER BY KOD_KEMENTERIAN ";
			}
			
			else if(tableRujukan.equals("TBLRUJNEGERI"))
			{
				sql = " SELECT ID_NEGERI AS ID, KOD_NEGERI AS KOD, UPPER(NAMA_NEGERI) AS KETERANGAN FROM TBLRUJNEGERI";
				sql += " WHERE ID_NEGERI IS NOT NULL ORDER BY KETERANGAN";
			
			}
			
			else if(tableRujukan.equals("TBLRUJBANDAR"))
			{
				sql = " SELECT ID_BANDAR AS ID, KOD_BANDAR AS KOD, UPPER(KETERANGAN) AS KETERANGAN FROM TBLRUJBANDAR";
				sql += " WHERE ID_BANDAR IS NOT NULL ORDER BY KETERANGAN, KOD_BANDAR ";
			
			}
			
			else if(tableRujukan.equals("TBLRUJDAERAH"))
			{
				sql = "  SELECT ID_DAERAH AS ID, KOD_DAERAH AS KOD, UPPER(NAMA_DAERAH) AS KETERANGAN FROM TBLRUJDAERAH " +
						" ORDER BY NAMA_DAERAH, KOD_DAERAH ";
			}
			else if(tableRujukan.equals("TBLRUJMUKIM"))
			{
				sql = "  SELECT ID_MUKIM AS ID, KOD_MUKIM AS KOD, UPPER(NAMA_MUKIM) AS KETERANGAN FROM TBLRUJMUKIM " +
						" ORDER BY NAMA_MUKIM, KOD_MUKIM ";
			}
			
			else if(tableRujukan.equals("TBLRUJJENISPEJABAT"))
			{
				sql = "  SELECT ID_JENISPEJABAT AS ID, KOD_JENIS_PEJABAT AS KOD, UPPER(KETERANGAN) AS KETERANGAN FROM TBLRUJJENISPEJABAT ORDER BY KETERANGAN ";
			}
			
			

		//	myLog.info(" V3: SQL listTableRujukanV3 ("+tableRujukan+") :"+ sql);
			rs = stmt.executeQuery(sql);
			listTableRujukan = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			while (rs.next()) {
				h = Collections.synchronizedMap(new HashMap());
				bil++;
				h.put("BIL",bil);
				h.put("ID",rs.getString("ID") == null ? "" : rs.getString("ID"));
				h.put("KOD",rs.getString("KOD") == null ? "" : rs.getString("KOD").toUpperCase());
				h.put("KETERANGAN",rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN").toUpperCase());
				
				listTableRujukan.add(h);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}

		return listTableRujukan;

	}
	
	public void setupPageList(HttpSession session, String action, List list, String namaList, String command, String div) {
		try {
			setupPageDefaultPut();
			String scrolPosition = getParam("scrolPosition"+command);
			//myLogger.info(" ------- scrolPosition :"+scrolPosition);
			this.context.put("scrolPosition", scrolPosition);
			this.context.put("namaList", namaList);
			this.context.put("command", command);
			this.context.put("div", div);
			this.context.put("totalRecords", list.size());
			int page = getParam("page") == "" ? 1 : getParamAsInteger("page");
			int page_mula = 1;
			
			int itemsPerPage;
			if (this.context.get("itemsPerPage"+command) == null
					|| this.context.get("itemsPerPage"+command) == "") {
				//myLogger.info(" itemsPerPage : "+getParam("itemsPerPage"+command));
				itemsPerPage = getParam("itemsPerPage"+command) == "" ? 10
						: getParamAsInteger("itemsPerPage"+command);
			} else {
				itemsPerPage = (Integer) this.context.get("itemsPerPage"+command);
			}
			
			if (("getNext").equals(action)) {
				page++;
			} else if (("getPrevious").equals(action)) {
				page--;
			} else if (("getPage").equals(action)) {
				page = getParamAsInteger("value");
			} else if (("doChangeItemPerPage").equals(action)) {
				itemsPerPage = getParamAsInteger("itemsPerPage"+command);
			}
			//myLogger.info(" itemsPerPage : "+itemsPerPage);
			
			Paging2 paging = new Paging2(session, list, itemsPerPage);

			if (page > paging.getTotalPages())
				page = 1;
			this.context.put(namaList, paging.getPage(page));
			this.context.put("page_mula", new Integer(page_mula));
			this.context.put("page", new Integer(page));
			this.context.put("itemsPerPage", new Integer(itemsPerPage));
			this.context.put("totalPages", new Integer(paging.getTotalPages()));
			this.context.put("startNumber", new Integer(paging.getTopNumber()));
			this.context.put("isFirstPage", new Boolean(paging.isFirstPage()));
			this.context.put("isLastPage", new Boolean(paging.isLastPage()));

			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			this.context.put("error", e.getMessage());
		}
	}
	
	public void setupPageDefaultPut()
	{
		this.context.put("page_mula", "");
		this.context.put("page", "");
		this.context.put("itemsPerPage", "");
		this.context.put("totalPages","");
		this.context.put("startNumber", "");
		this.context.put("isFirstPage", "");
		this.context.put("isLastPage", "");
		this.context.put("scrolPosition", "");
		this.context.put("namaList", "");
		this.context.put("command", "");
		this.context.put("div", "");
		this.context.put("totalRecords", "");
	}
	
	public String checkIsNull(String txt) {
		if (txt == null) return "";
		else return txt;
	}
}
