package ekptg.view.ppt;


import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.Paging2;

public class SkrinPopupHakmilikKJP extends AjaxBasedModule {
	static Logger myLogger = Logger.getLogger(SkrinPopupHakmilikKJP.class);
	private final String PATH="app/ppt/PopupHakmilik/";
	private String vm = PATH +"SkrinPopupHakmilikKJP.jsp";
	
	HttpSession session = null;
	String action = null;
	
	
	public String doTemplate2() throws Exception {		
		session = request.getSession();
		action = getParam("action");
		String command =  getParam("command");
		this.context.put("command",command);
		String id_permohonan =  getParam("id_permohonan");
		this.context.put("id_permohonan",id_permohonan);
		String id_daerah =  getParam("id_daerah");
		this.context.put("id_daerah",id_daerah);
		String id_negeri =  getParam("id_negeri");
		this.context.put("id_negeri",id_negeri);	
		this.context.put("hash_maklumat_permohonan","");
		this.context.put("id_mohon_selected","");
		String id_hakmilik = getParam("id_hakmilik");
		this.context.put("id_hakmilik",id_hakmilik);
		this.context.put("tutup_skrin_popup","");
		String jenis_seksyen =  getParam("jenis_seksyen");
		this.context.put("jenis_seksyen",jenis_seksyen);
		
		myLogger.info("command :"+command);
		myLogger.info("id_mohon_selected :"+getParam("id_mohon_selected"));
		myLogger.info("id_hakmilik :"+id_hakmilik);
		
		
		Boolean doPost_pop = false;
		String prev_token = session.getAttribute("form_token_pop") == null ? ""	: (String) session.getAttribute("form_token_pop");
		myLogger.info("prev_token !!!!!!!!!!!!!!!!!!!!!!!!!!!! :"+prev_token);
		String form_token_pop = getParam("form_token_pop") == null ? "empty": getParam("form_token_pop");
		myLogger.info("form_token !!!!!!!!!!!!!!!!!!!!!!!!!!!! :"+form_token_pop);
		if (prev_token.equals(form_token_pop)) {
			doPost_pop = true;
			//session.setAttribute("isPost", new Boolean(true));
		} else if ("empty".equals(form_token_pop)) {
			doPost_pop = true;
			//session.setAttribute("isPost", new Boolean(false));
		} else {
			doPost_pop = false;
			//session.setAttribute("isPost", new Boolean(false));
		}
		form_token_pop = Long.toString(System.currentTimeMillis());
		session.setAttribute("form_token_pop", form_token_pop);		
		myLogger.info("doPost_pop !!!!!!!!!!!!!!!!!!!!!!!!!!!! :"+doPost_pop);
		
		Db db = null;
		try {
		db = new Db();
		
				if(command.equals("paparHakmilik"))
				{
					this.context.put("hash_maklumat_permohonan",maklumatPermohonan(getParam("id_mohon_selected"),db));
					listHakmilik(getParam("id_mohon_selected"),db) ;
					this.context.put("id_mohon_selected",getParam("id_mohon_selected"));
				}
				else if(command.equals("transfer"))
				{
					if (doPost_pop == true) {	
					transferData(session,getParam("id_mohon_selected"),getParam("id_permohonan"),db);
					}
					this.context.put("tutup_skrin_popup","yes");
				}
				else if(command.equals("transferHakmilik"))
				{
					if (doPost_pop == true) {	
					transferDataHakmilik(session,getParam("id_hakmilik"),getParam("id_permohonan"),db);
					}
					this.context.put("tutup_skrin_popup","yes");
					this.context.put("id_hakmilik",getParam("id_hakmilik"));
				}
				
				this.context.put("id_hakmilik", getParam("id_hakmilik"));
				this.context.put("NO_FAIL",getParam("NO_FAIL"));
				this.context.put("NAMA_PROJEK",getParam("NAMA_PROJEK"));
				displayFail(getParam("NO_FAIL"),getParam("NAMA_PROJEK"),id_negeri,id_daerah,db,jenis_seksyen);
				myLogger.info("id_hakmilik========="+id_hakmilik);
		
		}finally {
		
		if (db != null)
		db.close();
		}
		
		
		return vm;
	}
	
	private void displayFail(String NO_FAIL,String NAMA_PROJEK,String ID_NEGERI,String ID_DAERAH,Db db,String jenis_seksyen) throws Exception{
		List<Hashtable>  list = null;	
		list = getFail(NO_FAIL,NAMA_PROJEK,ID_NEGERI,ID_DAERAH,db,jenis_seksyen);	
		context.put("SenaraiFail", list);	
		setupPage(session,action,list);
	}
	
	private void listHakmilik(String id_permohonan,Db db) throws Exception{
		String id_hakmilik = getParam("id_hakmilik");
		List<Hashtable>  listhakmilik = null;	
		listhakmilik = getHakmilik(id_permohonan,db);	
		context.put("listhakmilik", listhakmilik);	
		context.put("id_hakmilik", id_hakmilik);
	}
	
	
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
			sql = " SELECT (J.KOD_JENIS_HAKMILIK || ' ' || H.NO_HAKMILIK) AS NO_HAKMILIK, H.NO_LOT,UPPER(M.NAMA_MUKIM) AS NAMA_MUKIM,H.ID_HAKMILIK, H.ID_PERMOHONAN "+
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
				if (rs.getString("ID_HAKMILIK") == null) {	h.put("ID_HAKMILIK", ""); } else { h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK").toUpperCase()); }
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
	 
	 public void transferDataHakmilik(HttpSession session,String id_hakmilik,String id_permohonan,Db db) throws Exception {
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
						 " FROM TBLPPTHAKMILIK T WHERE ID_HAKMILIK = '"+id_hakmilik+"' ";
				myLogger.info("transferDataHakmilik :"+sql.toUpperCase());
				stmt.executeUpdate(sql);
			
				
			} finally {
				//if (db != null)
				//	db.close();
			}
		}
	
}
