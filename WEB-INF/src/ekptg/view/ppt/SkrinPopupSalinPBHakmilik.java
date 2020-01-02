package ekptg.view.ppt;


import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Paging2;
import ekptg.helpers.Utils;

public class SkrinPopupSalinPBHakmilik extends AjaxBasedModule {
	static Logger myLogger = Logger.getLogger(SkrinPopupSalinPBHakmilik.class);
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	private final String PATH="app/ppt/PopupHakmilik/";
	private String vm = PATH +"SkrinPopupCarianPB_salin.jsp";
	//private String vm = PATH +"";
	
	HttpSession session = null;
	String action = null;
	
	
	public String doTemplate2() throws Exception {		
		session = request.getSession();
		action = getParam("action");
		String command =  getParam("command");
		this.context.put("command",command);
		String id_hakmilik =  getParam("id_hakmilik");
		this.context.put("id_hakmilik",id_hakmilik);
		String id_hakmilikpb =  getParam("id_hakmilikpb");
		this.context.put("id_hakmilikpb",id_hakmilikpb);
		String id_daerah =  getParam("id_daerah");
		this.context.put("id_daerah",id_daerah);
		String id_negeri =  getParam("id_negeri");
		this.context.put("id_negeri",id_negeri);
		String flag_skrin = getParam("flag_skrin");
		
		this.context.put("flag_skrin",flag_skrin);
		String id_permohonan = getParam("id_permohonan");
		this.context.put("id_permohonan",id_permohonan);
		this.context.put("NO_LOT","");
		this.context.put("NAMA_PB","");
		this.context.put("NO_PB","");
		this.context.put("NO_HAKMILIK","");
		this.context.put("ID_MUKIM","");
		this.context.put("ID_DAERAH","");
		myLogger.info("command :"+command);
		String bolehsimpan = "yes";
		
		myLogger.info("flag_skrin ::::::::::::::::: :"+flag_skrin);
		Db db = null;
		try {
			
			if(flag_skrin.equals("skrin_salin_pb_PU") || flag_skrin.equals("skrin_salin_pb_siasatan") || flag_skrin.equals("skrin_salin_pb_sek8") || flag_skrin.equals("skrin_salin_pb_sementara"))
			{
				db = new Db();
				this.context.put("NAMA_PB",getParam("NAMA_PB"));
				this.context.put("NO_PB",getParam("NO_PB"));							
				displayPB(id_hakmilik,flag_skrin,action,getParam("NAMA_PB"),getParam("NO_PB"),db);
				vm = PATH +"SkrinPopupCarianPB_salin.jsp";
			}
			else if(flag_skrin.equals("skrin_salin_hakmilik_sek8") || flag_skrin.equals("skrin_hakmilik_sek8_KJP") || flag_skrin.equals("skrin_hakmilik_sek8") || flag_skrin.equals("skrin_hakmilik_sek4")  
					|| flag_skrin.equals("skrin_salin_hakmilik_sementara") || flag_skrin.equals("skrin_hakmilik_sementara") )
			{
				db = new Db();
				this.context.put("NO_HAKMILIK",getParam("NO_HAKMILIK"));
				this.context.put("NO_LOT",getParam("NO_LOT"));	
				this.context.put("ID_MUKIM",getParam("ID_MUKIM"));
				this.context.put("ID_DAERAH",getParam("ID_DAERAH"));				
				if(!getParam("ID_DAERAH").equals("") && getParam("ID_DAERAH")!= null){
					context.put("selectMukim",HTML.SelectMukimNoKodByDaerah(getParam("ID_DAERAH"),"socMukim",Utils.parseLong(getParam("ID_MUKIM")),"style=width:auto "));
				}else{
					context.put("selectMukim",HTML.SelectMukimNoKod("socMukim",Utils.parseLong(getParam("ID_MUKIM")),"style=width:auto "));
				}
				displayHakmilik(id_permohonan,flag_skrin,getParam("NO_LOT"), getParam("NO_HAKMILIK"),getParam("ID_MUKIM"),getParam("ID_DAERAH"),db);
				vm = PATH +"SkrinPopupCarianHakmilik_salin.jsp";
			}
		
		}finally {
		
		if (db != null)
		db.close();
		}
		
		
		return vm;
	}
	
	
	
	
	private void displayPB(String id_hakmilik,String flag_skrin,String action, String nama_pb,String no_pb,Db db) throws Exception{
		List<Hashtable>  list = null;	
		list = getPB(id_hakmilik,flag_skrin,nama_pb,no_pb,db);	
		context.put("SenaraiFail", list);
		setupPage(session,action,list);
	}
	
	
	private void displayHakmilik(String id_permohonan,String flag_skrin,String no_lot, String no_hakmilik,String id_mukim,String id_daerah,Db db) throws Exception{
		List<Hashtable>  list = null;	
		list = getHakmilik(id_permohonan,flag_skrin,no_lot,no_hakmilik,id_mukim,id_daerah,db);	
		context.put("SenaraiFail", list);
		setupPage(session,action,list);
	}
	
	
	
	Vector list_pb = null;
	public Vector getPB(String id_hakmilik,String flag_skrin,String nama_pb,String no_pb,Db db) throws Exception {
		list_pb = new Vector();
		list_pb.clear();
		String sql = "";
		try {
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = " SELECT HMPB.ID_PA1, PNGAMANAH.NAMA_PB AS NAMA_PA1, NVL(PB.UMUR,'0')AS UMUR, BPB.KETERANGAN_SYER,BPB.ID_BAHAGIANPB,BPB.SYER_ATAS,BPB.SYER_BAWAH,HMPB.FLAG_BORANGC,HMPB.FLAG_BORANGE,HMPB.FLAG_BORANGG,HMPB.FLAG_BORANGK,HMPB.KETERANGAN_JENIS_PB,HMPB.CATATAN,HMPB.NO_HANDPHONE,HMPB.NO_TEL_RUMAH,HM.ID_HAKMILIK," +
					//	"S.ID_SIASATAN," +
						"PBALIK.ID_PERMOHONAN,"
						+
						// "H.FLAG_HADIR," +
						"HMPB.ID_PIHAKBERKEPENTINGAN,HMPB.ID_HAKMILIKPB,PB.NAMA_PB,"
						+ "PB.NO_PB,PB.NAMA_KP,HMPB.ID_JENISPB,JPB.KETERANGAN AS JENISPB, "
						+ " HMPB.ALAMAT1,HMPB.ALAMAT2,HMPB.ALAMAT3,HMPB.POSKOD,HMPB.ID_NEGERI,HMPB.ID_BANDAR,N.NAMA_NEGERI, "
						+ " B.KETERANGAN AS NAMA_BANDAR,PB.ID_JENISNOPB,JNOPB.KETERANGAN AS JENISNOPB,HMPB.NO_AKAUN,HMPB.ID_BANK,RB.NAMA_BANK, JH.KOD_JENIS_HAKMILIK, HM.NO_HAKMILIK,HM.NO_LOT, "
						
						
						+" CASE  WHEN HM.NO_LOT IS NOT NULL AND HM.NO_PT IS NULL THEN HM.NO_LOT  WHEN HM.NO_LOT IS NULL AND  "
						+" HM.NO_PT IS NULL THEN LT.KETERANGAN || HM.NO_PT  WHEN HM.NO_LOT IS NULL AND HM.NO_PT IS NOT NULL THEN  LT.KETERANGAN || HM.NO_PT   "
						+" WHEN HM.NO_LOT IS NOT NULL AND HM.NO_PT IS NOT NULL THEN LT.KETERANGAN || HM.NO_PT || CHR(32) || CHR(40) || HM.NO_LOT || CHR(41)  ELSE 'TIADA'  END AS NO_LOTPT,UPPER(PBALIK.TUJUAN) AS PROJEK  "
						
						+ " FROM TBLPPTHAKMILIK HM,TBLPPTPERMOHONAN PBALIK," +
							//	"TBLPPTSIASATAN S," +
								"TBLPPTHAKMILIKPB HMPB, "
						+
						
						"TBLPPTPIHAKBERKEPENTINGAN PB,TBLRUJLOT LT,TBLRUJJENISPB JPB,TBLRUJNEGERI N,TBLRUJBANDAR B, "
						+ " TBLRUJJENISNOPB JNOPB,TBLRUJBANK RB,TBLPPTBAHAGIANPB BPB, TBLRUJJENISHAKMILIK JH,TBLPPTPIHAKBERKEPENTINGAN PNGAMANAH  "
						+ " WHERE " 
						
					//	+" PH.ID_HAKMILIK = HM.ID_HAKMILIK "
						+" HMPB.ID_BAHAGIANPB = BPB.ID_BAHAGIANPB(+) AND  HM.ID_PERMOHONAN = PBALIK.ID_PERMOHONAN AND HM.ID_LOT = LT.ID_LOT(+) AND HM.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) "
						
					
					
					//	" AND  S.ID_HAKMILIK(+) = HM.ID_HAKMILIK "
						+ " AND HMPB.ID_HAKMILIK = HM.ID_HAKMILIK"
						+ " AND PNGAMANAH.ID_PIHAKBERKEPENTINGAN(+) = HMPB.ID_PA1 "
						+ " AND HMPB.ID_PIHAKBERKEPENTINGAN = PB.ID_PIHAKBERKEPENTINGAN "
						+ " AND HMPB.ID_JENISPB = JPB.ID_JENISPB(+) "
						+ " AND HMPB.ID_NEGERI = N.ID_NEGERI(+) "
						+ " AND HMPB.ID_BANDAR = B.ID_BANDAR(+) "
						+ " AND PB.ID_JENISNOPB = JNOPB.ID_JENISNOPB(+) "
						+ " AND HMPB.ID_BANK = RB.ID_BANK(+) "
						+ " AND NVL(HMPB.ID_JENISPB,0) NOT IN ('40','41','42') ";
						

						//sql = sql + " AND HM.ID_HAKMILIK = '"+ id_hakmilik+ "' ";
						sql = sql + " AND ROWNUM < 100  ";
						
						if(!no_pb.equals("") && !no_pb.equals(null))
						{
							
							if (!no_pb.trim().equals("")) {
								sql = sql + " AND UPPER(PB.NO_PB) LIKE '%"
										+ no_pb.toUpperCase().trim() + "%'";								
							}
							
						}					
						if(!nama_pb.equals("") && !nama_pb.equals(null))
						{
							
							if (!nama_pb.trim().equals("")) {
								sql = sql + " AND  UPPER(PB.NAMA_PB) LIKE '%"
								+ nama_pb.toUpperCase().trim() + "%'";
							}
							
						}
						
						
						
					sql += " ORDER BY ID_BAHAGIANPB,NAMA_PB ASC" + "";
					
					
					myLogger.info("LIST PB BARU :"+sql.toUpperCase());
			
			
			myLogger.info("LIST HAKMILIK :"+sql.toUpperCase());
			
			stmt.setFetchSize(10);
			ResultSet rs = stmt.executeQuery(sql);
			int bil = 0;
			String temp = "";
			while (rs.next()) {
				bil = bil + 1;

				Hashtable h = new Hashtable();
				h.put("BIL", bil);
				temp = bil + "k";
				h.put("BILTEMP", temp);
				
				h.put("no_lotpt", rs.getString("NO_LOTPT")==null?"":rs.getString("NO_LOTPT"));
				h.put("kod_jenis_hakmilik", rs.getString("kod_jenis_hakmilik")==null?"":rs.getString("kod_jenis_hakmilik"));
				h.put("no_hakmilik", rs.getString("no_hakmilik")==null?"":rs.getString("no_hakmilik"));
				h.put("no_lot", rs.getString("no_lot")==null?"":rs.getString("no_lot"));
				h.put("projek", rs.getString("PROJEK")==null?"":rs.getString("PROJEK"));

				
				h.put("UMUR", rs.getInt("UMUR") == 0 ? 0: rs.getInt("UMUR"));	
				h.put("ID_PA1", rs.getString("ID_PA1") == null ? "" : rs.getString("ID_PA1"));	
				h.put("NAMA_PA1", rs.getString("NAMA_PA1") == null ? "" : rs.getString("NAMA_PA1"));	
				
				h.put("ID_BAHAGIANPB", rs.getString("ID_BAHAGIANPB") == null ? ""
						: rs.getString("ID_BAHAGIANPB"));					
				h.put("SYER_ATAS", rs.getString("SYER_ATAS") == null ? ""
						: rs.getString("SYER_ATAS"));
				h.put("SYER_BAWAH", rs.getString("SYER_BAWAH") == null ? ""
						: rs.getString("SYER_BAWAH"));
				
				h.put("FLAG_BORANGC", rs.getString("FLAG_BORANGC") == null ? ""
						: rs.getString("FLAG_BORANGC"));
				h.put("FLAG_BORANGE", rs.getString("FLAG_BORANGE") == null ? ""
						: rs.getString("FLAG_BORANGE"));
				h.put("FLAG_BORANGG", rs.getString("FLAG_BORANGG") == null ? ""
						: rs.getString("FLAG_BORANGG"));
				h.put("FLAG_BORANGK", rs.getString("FLAG_BORANGK") == null ? ""
						: rs.getString("FLAG_BORANGK"));
				
				h.put("CATATAN", rs.getString("CATATAN") == null ? ""
						: rs.getString("CATATAN"));
				h.put("KETERANGAN_JENIS_PB", rs.getString("KETERANGAN_SYER") == null ? ""
						: rs.getString("KETERANGAN_SYER"));
				h.put("NO_HANDPHONE", rs.getString("NO_HANDPHONE") == null ? ""
						: rs.getString("NO_HANDPHONE"));
				h.put("NO_TEL_RUMAH", rs.getString("NO_TEL_RUMAH") == null ? ""
						: rs.getString("NO_TEL_RUMAH"));
				h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? ""
						: rs.getString("ID_HAKMILIK"));
		//		h.put("ID_SIASATAN", rs.getString("ID_SIASATAN") == null ? ""
			//			: rs.getString("ID_SIASATAN"));
				h.put("ID_PENARIKANBALIK",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				// h.put("FLAG_HADIR",rs.getString("FLAG_HADIR") == null ? "" :
				// rs.getString("FLAG_HADIR"));
				h.put("ID_PIHAKBERKEPENTINGAN", rs
						.getString("ID_PIHAKBERKEPENTINGAN") == null ? "" : rs
						.getString("ID_PIHAKBERKEPENTINGAN"));
				h.put("ID_HAKMILIKPB",
						rs.getString("ID_HAKMILIKPB") == null ? "" : rs
								.getString("ID_HAKMILIKPB"));
				h.put("NAMA_PB", rs.getString("NAMA_PB") == null ? "" : rs
						.getString("NAMA_PB").toUpperCase());
				h.put("NO_PB", rs.getString("NO_PB") == null ? "" : rs
						.getString("NO_PB").toUpperCase());
				h.put("NAMA_KP", rs.getString("NAMA_KP") == null ? "" : rs
						.getString("NAMA_KP").toUpperCase());
				h.put("ID_JENISPB", rs.getString("ID_JENISPB") == null ? ""
						: rs.getString("ID_JENISPB"));
				// h.put("KETERANGAN",rs.getString("KETERANGAN") == null ? "" :
				// rs.getString("KETERANGAN"));
				h.put("JENISPB", rs.getString("JENISPB") == null ? "" : rs
						.getString("JENISPB").toUpperCase());
				h.put("ALAMAT1", rs.getString("ALAMAT1") == null ? "" : rs
						.getString("ALAMAT1").toUpperCase());
				h.put("ALAMAT2", rs.getString("ALAMAT2") == null ? "" : rs
						.getString("ALAMAT2").toUpperCase());
				h.put("ALAMAT3", rs.getString("ALAMAT3") == null ? "" : rs
						.getString("ALAMAT3").toUpperCase());
				h.put("POSKOD", rs.getString("POSKOD") == null ? "" : rs
						.getString("POSKOD"));
				h.put("ID_NEGERI", rs.getString("ID_NEGERI") == null ? "" : rs
						.getString("ID_NEGERI"));
				h.put("ID_BANDAR", rs.getString("ID_BANDAR") == null ? "" : rs
						.getString("ID_BANDAR"));
				h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI") == null ? ""
						: rs.getString("NAMA_NEGERI").toUpperCase());
				h.put("NAMA_BANDAR", rs.getString("NAMA_BANDAR") == null ? ""
						: rs.getString("NAMA_BANDAR").toUpperCase());
				h.put("ID_JENISNOPB", rs.getString("ID_JENISNOPB") == null ? ""
						: rs.getString("ID_JENISNOPB"));
				h.put("JENISNOPB", rs.getString("JENISNOPB") == null ? "" : rs
						.getString("JENISNOPB").toUpperCase());
				h.put("NO_AKAUN", rs.getString("NO_AKAUN") == null ? "" : rs
						.getString("NO_AKAUN").toUpperCase());
				h.put("ID_BANK", rs.getString("ID_BANK") == null ? "" : rs
						.getString("ID_BANK"));
				h.put("NAMA_BANK", rs.getString("NAMA_BANK") == null ? "" : rs
						.getString("NAMA_BANK").toUpperCase());

				list_pb.addElement(h);
			}
			return list_pb;
		} finally {
			//if (db != null)
			//db.close();
		}
	}
	
	
	Vector list_hakmilik = null;
	public Vector getHakmilik(String id_permohonan,String flag_skrin,String no_lot, String no_hakmilik,String id_mukim,String id_daerah,Db db) throws Exception {
		String noLOT = no_lot.trim();
		String nama2Mukim = "";
		String listLOT = "";
		String listLOTHM = "";
		double totalSize = 0;
		list_hakmilik = new Vector();
		list_hakmilik.clear();
		String sql = "";
		try {
		//	db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			sql = " " +
					//"SELECT *    FROM ( "+
					" SELECT DISTINCT ROW_NUMBER () OVER (ORDER BY MK.NAMA_MUKIM ASC, LPAD (M.NO_LOT, 20) ASC, LPAD (M.NO_PT, 20) ASC, LPAD (M.NO_SUBJAKET, 20) ASC) AS RN, "+
					" M.FLAG_SEGERA_SEBAHAGIAN, P.NO_RUJUKAN_PTG, P.ID_STATUS, F.NO_FAIL, M.CATATAN, P.ID_PERMOHONAN, LS.KETERANGAN AS UNIT1, LT.KETERANGAN AS UNIT2, M.ID_HAKMILIK, M.ID_NEGERI,  "+
					" N.NAMA_NEGERI,  (SELECT COUNT(A.ID_HAKMILIKPB) FROM TBLPPTHAKMILIKPB A, TBLPPTPIHAKBERKEPENTINGAN B  WHERE A.ID_PIHAKBERKEPENTINGAN = B.ID_PIHAKBERKEPENTINGAN AND A.ID_HAKMILIK(+) = M.ID_HAKMILIK  "+
					" AND A.ID_JENISPB NOT IN (40,41,42))AS TOTALPB,  M.ID_JENISHAKMILIK, M.ID_DAERAH, M.ID_MUKIM, MK.NAMA_MUKIM, M.ID_UNITLUASLOT, M.LUAS_AMBIL,   "+
					" M.NO_HAKMILIK, M.NO_LOT, M.LUAS_LOT, M.NO_PT, M.TARIKH_DAFTAR, M.TARIKH_LUPUT, M.TEMPOH_LUPUT, JH.KOD_JENIS_HAKMILIK,  M.LOKASI,M.SYARAT_NYATA,M.SYARAT_KHAS, "+
					" M.SEKATAN_HAK,M.SEKATAN_KEPENTINGAN,M.NO_SYIT, JH.KETERANGAN AS JENIS_HAKMILIK, M.ID_KATEGORITANAH,  M.FLAG_AMBIL_SEGERA,D.NAMA_DAERAH,M.FLAG_BORANGL, M.STATUS_BORANGL,  "+
					" M.TARIKH_TERIMA_HM, U.USER_NAME AS NAMA_PEGAWAI, JH.STATUS_HAKMILIK,  M.FLAG_JENIS_RIZAB, M.NAMA_LAIN_RIZAB, M.NO_WARTA_RIZAB, M.TARIKH_WARTA_RIZAB, M.NO_SUBJAKET,  "+
					" M.ID_PEGAWAI, M.ID_UNITLUASAMBIL_CONVERT, M.ID_UNITLUASLOT_CONVERT,  CASE  WHEN M.NO_LOT IS NOT NULL AND M.NO_PT IS NULL THEN M.NO_LOT  WHEN M.NO_LOT IS NULL AND  "+
					" M.NO_PT IS NULL THEN LT.KETERANGAN || M.NO_PT  WHEN M.NO_LOT IS NULL AND M.NO_PT IS NOT NULL THEN  LT.KETERANGAN || M.NO_PT   "+
					" WHEN M.NO_LOT IS NOT NULL AND M.NO_PT IS NOT NULL THEN LT.KETERANGAN || M.NO_PT || CHR(32) || CHR(40) || M.NO_LOT || CHR(41)  ELSE 'TIADA'  END AS NO_LOTPT, M.SEKSYEN   "+
					" FROM TBLPFDFAIL F, TBLPPTPERMOHONAN P, TBLRUJLOT LT, TBLRUJLUAS LS, TBLRUJMUKIM MK, TBLRUJNEGERI N, TBLPPTHAKMILIK M, TBLRUJJENISHAKMILIK JH,  TBLRUJDAERAH D, USERS U  "+
					" WHERE M.ID_PERMOHONAN = P.ID_PERMOHONAN(+)  AND M.ID_NEGERI = N.ID_NEGERI  AND P.ID_FAIL = F.ID_FAIL  AND M.ID_DAERAH = D.ID_DAERAH(+) AND LS.ID_LUAS(+) = M.ID_UNITLUASLOT   "+
					" AND M.ID_PEGAWAI = U.USER_ID(+) AND M.ID_JENISHAKMILIK = JH.ID_JENISHAKMILIK(+) AND M.ID_LOT = LT.ID_LOT(+)  AND M.ID_MUKIM = MK.ID_MUKIM(+)  AND NVL(M.FLAG_PEMBATALAN_KESELURUHAN,0) <> 'Y'   "+
					" AND NVL(M.FLAG_PENARIKAN_KESELURUHAN,0) <> 'Y' AND ROWNUM < 100 AND M.ID_DAERAH = '"+id_daerah+"' ";
					//sql +=" AND P.ID_PERMOHONAN = '"+id_permohonan+"'  ";
					
					 if (no_lot != "" && no_lot != null) {
					     if (!noLOT.equals("")) {
					            sql += " AND (" +
					            		"UPPER(m.no_lot) LIKE '%" + noLOT.toUpperCase() + "%' " +
					                      " OR  UPPER(m.no_pt) LIKE '%" + noLOT.toUpperCase() + "%' " +
					                      " OR UPPER(lt.keterangan) LIKE '%" + noLOT.toUpperCase() + "%' "+
					                      " ) ";
					     }
					 }

					 
					 if (no_hakmilik != "" && no_hakmilik != null) {
					     if (!no_hakmilik.equals("")) {
					            sql += " AND (" +
					            		"UPPER(m.no_hakmilik) LIKE '%" + no_hakmilik.toUpperCase() + "%') ";
					     }
					 }
					 
					 
					 if (id_mukim != "" && id_mukim != null) {
					     if (!id_mukim.equals("")) {
					            sql += " AND " +
					            		"UPPER(m.id_mukim) = '" + id_mukim + "' ";
					     }
					 }
					
					
					sql += " ORDER BY MK.NAMA_MUKIM ASC, LPAD(M.NO_LOT,20) ASC, LPAD(M.NO_PT,20) ASC, LPAD(NO_LOTPT,20) ASC, LPAD(M.NO_SUBJAKET,20) ASC" +
					"";
			
			
			myLogger.info("LIST HAKMILIK :"+sql.toUpperCase());
			
			stmt.setFetchSize(10);
			ResultSet rs = stmt.executeQuery(sql);
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				Hashtable h = new Hashtable();
				h.put("BIL", bil);
				h.put("rn", rs.getString("rn")==null?"":rs.getString("rn"));
				h.put("flag_segera_sebahagian", rs.getString("flag_segera_sebahagian")==null?"":rs.getString("flag_segera_sebahagian"));
				h.put("seksyen", rs.getString("seksyen")==null?"":rs.getString("seksyen"));
				h.put("listLOT", listLOT.toUpperCase());
				h.put("listLOTHM", listLOTHM.toUpperCase());
				h.put("nama2Mukim", nama2Mukim.toUpperCase());
				h.put("nama2MukimInit", nama2Mukim);
				h.put("bil", bil);
				h.put("totalPB", rs.getString("totalPB")==null?"":rs.getString("totalPB"));
				h.put("kod_jenis_hakmilik", rs.getString("kod_jenis_hakmilik")==null?"":rs.getString("kod_jenis_hakmilik"));
				h.put("no_rujukan_ptg", rs.getString("no_rujukan_ptg")==null?"":rs.getString("no_rujukan_ptg"));
				h.put("id_status", rs.getString("id_status")==null?"":rs.getString("id_status"));
				h.put("no_lotpt", rs.getString("NO_LOTPT")==null?"":rs.getString("NO_LOTPT"));
				h.put("no_fail", rs.getString("no_fail")==null?"":rs.getString("no_fail"));
				h.put("no_subjaket", rs.getString("no_subjaket")==null?"":rs.getString("no_subjaket"));
				h.put("id_pegawai", rs.getString("id_pegawai")==null?"":rs.getString("id_pegawai"));
				h.put("id_permohonan", rs.getString("id_Permohonan")==null?"":rs.getString("id_Permohonan"));
				h.put("nama_pegawai", rs.getString("nama_pegawai")==null?"":rs.getString("nama_pegawai"));
				h.put("kod_lot", rs.getString("unit2")==null?"":rs.getString("unit2"));
				h.put("unitluaslot", rs.getString("unit1")==null?"":rs.getString("unit1"));			
				h.put("id_hakmilik", rs.getString("id_hakmilik")==null?"":rs.getString("id_hakmilik"));
				h.put("id_negeri", rs.getString("id_negeri")==null?"":rs.getString("id_negeri"));
				h.put("nama_negeri", rs.getString("nama_negeri")==null?"":rs.getString("nama_negeri"));
				h.put("id_jenishakmilik", rs.getString("id_jenishakmilik")==null?"":rs.getString("id_jenishakmilik"));
				h.put("id_daerah", rs.getString("id_daerah")==null?"":rs.getString("id_daerah"));
				h.put("id_mukim", rs.getString("id_mukim")==null?"":rs.getString("id_mukim"));
				h.put("nama_mukim", rs.getString("nama_mukim")==null?"":rs.getString("nama_mukim"));
				h.put("id_unitluaslot", rs.getString("id_unitluaslot")==null?"":rs.getString("id_unitluaslot"));
				h.put("flag_ambil_segera", rs.getString("flag_ambil_segera")==null?"":rs.getString("flag_ambil_segera"));
				h.put("nama_daerah", rs.getString("nama_daerah")==null?"":rs.getString("nama_daerah"));
				h.put("status_borangl", rs.getString("status_borangl")==null?"":rs.getString("status_borangl"));
				h.put("tarikh_terima_hm", rs.getDate("tarikh_terima_hm")==null?"":Format.format(rs.getDate("tarikh_terima_hm")));
				h.put("status_hakmilik", rs.getString("status_hakmilik")==null?"":rs.getString("status_hakmilik"));
				
				h.put("flag_jenis_rizab", rs.getString("flag_jenis_rizab")==null?"":rs.getString("flag_jenis_rizab"));
				h.put("nama_lain_rizab", rs.getString("nama_lain_rizab")==null?"":rs.getString("nama_lain_rizab"));
				h.put("tarikh_warta_rizab", rs.getDate("tarikh_warta_rizab")==null?"":Format.format(rs.getDate("tarikh_warta_rizab")));
				h.put("no_warta_rizab", rs.getString("no_warta_rizab")==null?"":rs.getString("no_warta_rizab"));
				
				if(rs.getString("luas_ambil") != null && rs.getString("luas_ambil") != ""){
					
					double luasAmbil = rs.getDouble("luas_ambil");
					String LA = Utils.formatLuasHM(luasAmbil);
					h.put("luas_ambil",LA);
							
				}else{
					h.put("luas_ambil","0");
				}
				
				
				//validation for PU
				if(rs.getString("luas_ambil") != null && rs.getString("luas_lot") != null){
					
					double luasAmbil = 0;
					if(rs.getString("id_unitluasambil_convert") != null && rs.getString("id_unitluasambil_convert").equals("2")){
						luasAmbil = rs.getDouble("luas_ambil"); //meter persegi
					}else{
						luasAmbil = rs.getDouble("luas_ambil") * 10000; //hektar
					}
					
					double luasLot = 0;
					if(rs.getString("id_unitluaslot_convert") != null && rs.getString("id_unitluaslot_convert").equals("2")){
						luasLot = rs.getDouble("luas_lot"); //meter persegi
					}else{
						luasLot = rs.getDouble("luas_lot") * 10000; //hektar
					}						
					
					double baki = luasLot - luasAmbil;
					
					if(baki>0){
						h.put("flagPU","yes");
					}else{
						h.put("flagPU","no");
					}
			
				}else{
					h.put("flagPU","no");
				}
				
				if(rs.getString("id_unitluasambil_convert") != null){
					
					if(rs.getString("id_unitluasambil_convert").equals("1")){
						h.put("unitByKategori", "Hektar");
					}else{
						h.put("unitByKategori", "Meter Persegi");
					}			
				}else{
					h.put("unitByKategori", "");
				}

				
				if(rs.getString("id_unitluasambil_convert") != null){	
					
					if(rs.getString("id_unitluasambil_convert").equals("1")){	
						
						if(rs.getString("luas_ambil") != null){
							double luasAmbil = rs.getDouble("luas_ambil");
							String LAH = Utils.formatLuasHM(luasAmbil);
							h.put("nilaiTanah",LAH);
						}else{
							h.put("nilaiTanah","0");
						}
						
					}else{						
						if(rs.getString("luas_ambil") != null){
							double luasAmbil = rs.getDouble("luas_ambil") / 10000;
							String LAM = Utils.formatLuasHM(luasAmbil);
							h.put("nilaiTanah",LAM);
						}else{
							h.put("nilaiTanah","0");
						}						
					}	
					
				}else{
					h.put("nilaiTanah","0");
				}
				
				
				//get total luas ambil in hectar
				if(rs.getString("luas_ambil") != null){	
					if(rs.getString("id_unitluasambil_convert") != null && rs.getString("id_unitluasambil_convert").equals("1")){
						totalSize += rs.getDouble("luas_ambil");
					}else{
						totalSize += (rs.getDouble("luas_ambil") / 10000);
					}
				}
				// ADDED BY ELLY
				//myLogger.info("TOTAL SIZE LUAS >> "+totalSize);
				h.put("luas_keseluruhan", totalSize);					
				
				h.put("id_kategoritanah", rs.getString("id_kategoritanah")==null?"":rs.getString("id_kategoritanah"));
				h.put("no_hakmilik", rs.getString("no_hakmilik")==null?"":rs.getString("no_hakmilik"));
				h.put("no_lot", rs.getString("no_lot")==null?"":rs.getString("no_lot"));
				h.put("luas_lot", rs.getString("luas_lot")==null?"":rs.getDouble("luas_lot"));
				h.put("no_pt", rs.getString("no_pt")==null?"":rs.getString("no_pt"));
				h.put("catatan", rs.getString("catatan")==null?"":rs.getString("catatan"));
				
				h.put("tarikh_daftar", rs.getDate("tarikh_daftar")==null?"":Format.format(rs.getDate("tarikh_daftar")));
				h.put("tarikh_luput", rs.getDate("tarikh_luput")==null?"":Format.format(rs.getDate("tarikh_luput")));
				h.put("tempoh_luput", rs.getString("tempoh_luput")==null?"":rs.getString("tempoh_luput"));
				
				h.put("lokasi", rs.getString("lokasi")==null?"":rs.getString("lokasi"));
				h.put("syarat_nyata", rs.getString("syarat_nyata")==null?"":rs.getString("syarat_nyata"));
				h.put("syarat_khas", rs.getString("syarat_khas")==null?"":rs.getString("syarat_khas"));
				
				h.put("sekatan_hak", rs.getString("sekatan_hak")==null?"":rs.getString("sekatan_hak"));
				h.put("sekatan_kepentingan", rs.getString("sekatan_kepentingan")==null?"":rs.getString("sekatan_kepentingan"));
				h.put("no_syit", rs.getString("no_syit")==null?"":rs.getString("no_syit"));
				h.put("jenis_hakmilik", rs.getString("jenis_hakmilik")==null?"":rs.getString("jenis_hakmilik"));
				h.put("flag_borangl", rs.getString("flag_borangl")==null?"":rs.getString("flag_borangl"));

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
	
	 
	 
}
