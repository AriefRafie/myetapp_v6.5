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

public class SkrinPopupBangunan extends AjaxBasedModule {
	static Logger myLogger = Logger.getLogger(SkrinPopupBangunan.class);
	private final String PATH="app/ppt/PopupHakmilik/";
	private String vm = PATH +"SkrinPopupCarianBangunan.jsp";
	
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
		String id_hakmilik =  getParam("id_hakmilik");
		this.context.put("id_hakmilik",id_hakmilik);
		this.context.put("hash_maklumat_permohonan","");
		this.context.put("id_mohon_selected","");
		this.context.put("tutup_skrin_popup","");
		this.context.put("NO_BANGUNAN","");
		this.context.put("NAMA_PB","");
		this.context.put("NO_PB","");
		this.context.put("ID_BANGUNAN_SELECT","");
		String flag_skrin = getParam("flag_skrin");
		this.context.put("flag_skrin",flag_skrin);
		
		myLogger.info("command :"+command);
		myLogger.info("id_mohon_selected :"+getParam("id_mohon_selected"));
		
		Db db = null;
		try {
		db = new Db();
		
								
				this.context.put("NO_BANGUNAN",getParam("NO_BANGUNAN"));
				this.context.put("NAMA_PB",getParam("NAMA_PB"));
				this.context.put("NO_PB",getParam("NO_PB"));
				this.context.put("ID_BANGUNAN_SELECT",getParam("ID_BANGUNAN_SELECT"));
				displayFail(getParam("id_hakmilik"),getParam("NO_BANGUNAN"),getParam("NAMA_PB"),getParam("NO_PB"),getParam("ID_BANGUNAN_SELECT"),db);
		
		}finally {
		
		if (db != null)
		db.close();
		}
		
		
		return vm;
	}
	
	private void displayFail(String id_hakmilik,String NO_BANGUNAN,String NAMA_PB,String NO_PB,String ID_BANGUNAN_SELECT,Db db) throws Exception{
		
		 Vector list_temp = getFail(id_hakmilik,NO_BANGUNAN,NAMA_PB,NO_PB,ID_BANGUNAN_SELECT,db);
		 for (int i = 0; i < list_temp.size(); i++) {		
			 Hashtable getBangunan = (Hashtable) list_temp.get(i);
			 String id_bg =  getBangunan.get("id_bangunan").toString() == null ? "" : getBangunan.get("id_bangunan").toString();
			 String id_hakmilik_b =  getBangunan.get("id_hakmilik").toString() == null ? "" : getBangunan.get("id_hakmilik").toString();
			myLogger.info("id_bg :"+id_bg);
			myLogger.info("TOTAL PB :"+getBangunan_pb(id_bg,id_hakmilik_b,db).size());
			int total_pb = getBangunan_pb(id_bg,id_hakmilik_b,db).size();
			 if(total_pb>0)
	    	 {
				 getBangunan.put("list_pb", getBangunan_pb(id_bg,id_hakmilik_b,db));
				 //getBangunan.put("list_pb", "XXXXXXXXXXXXXXXXXXX");
	    	 }
	    	 else
	    	 {	    			
	    		getBangunan.put("list_pb","");	
	    	 }
			 //getBangunan.put("list_pb","XXXXXXXXXX");
			// list_temp.addElement(getBangunan);
			 
		 }
		 List<Hashtable>  list = null;	
		 list = list_temp;	
		
		context.put("SenaraiFail", list);	
		setupPage(session,action,list);
		
	}
	
	
	
	Vector list_fail = null;
	public Vector getFail(String id_hakmilik,String NO_BANGUNAN,String NAMA_PB,String NO_PB,String ID_BANGUNAN_SELECT,Db db) throws Exception {
		
		
		myLogger.info("id_hakmilik :"+id_hakmilik);
		myLogger.info("NO_BANGUNAN :"+NO_BANGUNAN);
		myLogger.info("NAMA_PB :"+NAMA_PB);
		myLogger.info("NO_PB :"+NO_PB);
		
			
			
		list_fail = new Vector();
		list_fail.clear();
		//Db db = null;
		String sql = "";
		try {
			//db = new Db();
			
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			sql = "SELECT DISTINCT TRIM(NVL(TO_CHAR(a.nilaian_jpph,'999999990.99'),'')) AS nilaian_jpph,a.id_hakmilik," +
	    			" a.id_bangunan,a.id_hakmilik,a.no_bangunan,CASE A.JENIS_BANGUNAN WHEN '1'  THEN 'Kekal-Bangunan konkrit dan mendapat kelulusan CF'" +
	    			"WHEN '2' Then 'Separuh Kekal-Bangunan separuh konkrit dan separuh papan'" +
	    			"WHEN '3' Then 'Sementara Kekal-Binaan bangunan dan papan dan kayu '" +
	    			"WHEN '4' Then 'Lain-lain'" +
	    			"ELSE '' END AS jenis,a.saiz_bangunan, ";
	    	sql += " a.nilai_bangunan,a.alamat1,a.alamat2,a.alamat3,a.poskod,a.id_negeri,a.id_bandar, ";
	    	sql += " a.ulasan,a.lain_perkara, b.nama_negeri, c.keterangan as nama_bandar ";
	    	sql += " FROM TBLPPTBANGUNAN a, TBLRUJNEGERI b, TBLRUJBANDAR c ";
	    	sql += " WHERE a.id_negeri = b.id_negeri(+)";
	    	sql += " AND a.id_bandar = c.id_bandar(+)";
	    	sql += " AND a.id_hakmilik = '"+id_hakmilik+"'";
			   
	    	if (NO_BANGUNAN != null) {
				if (!NO_BANGUNAN.equals("")) {
				
					sql += " AND a.no_bangunan like '%" + NO_BANGUNAN + "%' ";
				}
			}
			
			if (ID_BANGUNAN_SELECT != null) {
				if (!ID_BANGUNAN_SELECT.equals("")) {
				
					sql += " AND a.id_bangunan = '" + ID_BANGUNAN_SELECT + "' ";
				}
			}
			
			if (NAMA_PB != null || NO_PB != null) {
				if (!NAMA_PB.equals("") || !NO_PB.equals("")) {
				
					sql += " AND a.id_bangunan in (SELECT B.ID_BANGUNAN AS ID_BANGUNAN FROM TBLPPTBANGUNAN B,TBLPPTBANGUNANPB BPB,TBLPPTHAKMILIKPB HPB,TBLPPTPIHAKBERKEPENTINGAN PB "+
							" WHERE BPB.ID_BANGUNAN IN (SELECT B.ID_BANGUNAN FROM TBLPPTBANGUNAN B " +
							"WHERE B.ID_BANGUNAN IS NOT NULL ";
					sql +=	"AND ID_HAKMILIK = '"+id_hakmilik+"'";
					sql +=		") ";
					sql +=		" AND PB.ID_PIHAKBERKEPENTINGAN = HPB.ID_PIHAKBERKEPENTINGAN  AND B.ID_BANGUNAN = BPB.ID_BANGUNAN  ";
					if (NAMA_PB != null) {
						if (!NAMA_PB.equals("")) {
					sql +=	" AND PB.NAMA_PB LIKE '%"+NAMA_PB+"%' ";
						}
					}
					if (NO_PB != null) {
						if (!NO_PB.equals("")) {
					sql +=	" AND PB.NO_PB LIKE '%"+NO_PB+"%' ";
						}
					}
					sql +=	" AND BPB.ID_HAKMILIKPB = HPB.ID_HAKMILIKPB) ";
				}
			}
			
			
			
			
			sql += "   ORDER BY a.no_bangunan ASC ";
		
			
			
			
			myLogger.info("LIST FAIL :"+sql.toUpperCase());
			
			
			
			stmt.setFetchSize(10);
			ResultSet rs = stmt.executeQuery(sql);
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				Hashtable h = new Hashtable();				
	    		h.put("bil", bil);		    			
	    		h.put("nama_negeri", rs.getString("nama_negeri")== null?"":rs.getString("nama_negeri"));
	    		h.put("nama_bandar", rs.getString("nama_bandar")== null?"":rs.getString("nama_bandar"));
	    		h.put("id_bangunan", rs.getString("id_bangunan")== null?"":rs.getString("id_bangunan"));
	    		h.put("no_bangunan", rs.getString("no_bangunan")== null?"":rs.getString("no_bangunan"));
	    		h.put("jenis", rs.getString("jenis")== null?"":rs.getString("jenis"));//perubahan yati
	    		h.put("saiz_bangunan", rs.getString("saiz_bangunan")== null?"":rs.getString("saiz_bangunan"));
	    		h.put("nilai_bangunan", rs.getString("nilai_bangunan")== null?"":rs.getString("nilai_bangunan"));
	    		h.put("alamat1", rs.getString("alamat1")== null?"":rs.getString("alamat1"));
	    		h.put("alamat2", rs.getString("alamat2")== null?"":rs.getString("alamat2"));
	    		h.put("alamat3", rs.getString("alamat3")== null?"":rs.getString("alamat3"));
	    		h.put("poskod", rs.getString("poskod")== null?"":rs.getString("poskod"));
	    		h.put("id_negeri", rs.getString("id_negeri")== null?"":rs.getString("id_negeri"));
	    		h.put("id_bandar", rs.getString("id_bandar")== null?"":rs.getString("id_bandar"));
	    		h.put("ulasan", rs.getString("ulasan")== null?"":rs.getString("ulasan"));
	    		h.put("lain_perkara", rs.getString("lain_perkara")== null?"":rs.getString("lain_perkara"));
	    		h.put("nilaian_jpph", rs.getString("nilaian_jpph")== null?"":rs.getDouble("nilaian_jpph"));	
	    		h.put("id_hakmilik", rs.getString("id_hakmilik")== null?"":rs.getString("id_hakmilik"));	
	    		
	    		
				list_fail.addElement(h);
				bil++;
			}
			return list_fail;
		} finally {
			//if (db != null)
			//db.close();
		}
	}
	
	
	
	Vector list_bangunan_pb = null;
	public Vector getBangunan_pb(String idBangunan,String id_hakmilik,Db db) throws Exception {
		
		list_bangunan_pb = new Vector();
		list_bangunan_pb.clear();
	    
		//Db db = null;
		String sql = "";
		try {
			//db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "SELECT DISTINCT a.id_bangunanpb,a.id_bangunan,e.id_hakmilik, ";
	    	sql += " a.no_unit_bangunan,a.kadar_sewa,a.kegunaan_bangunan,a.saiz_bangunan, ";
	    	sql += " b.nama_pb, f.keterangan as jenis_pb ";
	    	sql += " FROM TBLPPTBANGUNANPB a, TBLPPTPIHAKBERKEPENTINGAN b, TBLPPTBANGUNAN c, TBLPPTHAKMILIKPB e, ";
	    	sql += " TBLRUJJENISPB f";
	    	sql += " WHERE a.id_hakmilikpb = e.id_hakmilikpb ";
			sql += " AND a.id_bangunan = c.id_bangunan(+) ";
			sql += " AND e.id_pihakberkepentingan = b.id_pihakberkepentingan(+) ";
			sql += " AND e.id_jenispb = f.id_jenispb(+)";
			sql += " AND a.id_bangunan = '"+idBangunan+"'";
			sql += " AND e.id_hakmilik = '"+id_hakmilik+"' order by b.nama_pb ";
			myLogger.info("LIST bangunan pb :"+sql.toUpperCase());
			
			stmt.setFetchSize(10);
			ResultSet rsx = stmt.executeQuery(sql);
			int bil = 0;
			while (rsx.next()) {
				bil = bil + 1;
				Hashtable h = new Hashtable();
				h.put("BIL", bil);
				h.put("id_bangunan", rsx.getString("id_bangunan")== null?"":rsx.getString("id_bangunan"));
	    		h.put("id_bangunanpb", rsx.getString("id_bangunanpb")== null?"":rsx.getString("id_bangunanpb"));
	    		h.put("no_unit_bangunan", rsx.getString("no_unit_bangunan")== null?"":rsx.getString("no_unit_bangunan").toUpperCase());
	    		h.put("nama_pb", rsx.getString("nama_pb")== null?"":rsx.getString("nama_pb").toUpperCase());
	    		h.put("jenis_pb", rsx.getString("jenis_pb")== null?"":rsx.getString("jenis_pb").toUpperCase());
				list_bangunan_pb.addElement(h);
			}
			return list_bangunan_pb;
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
				
				myLogger.info("transfer :"+sql.toUpperCase());
				stmt.executeUpdate(sql);
			
				
			} finally {
				//if (db != null)
				//	db.close();
			}
		}
	
}
