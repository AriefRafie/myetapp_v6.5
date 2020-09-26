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

import ekptg.helpers.Paging2;
import ekptg.model.ppt.FrmSek8SiasatanData;

public class SkrinPopupCarianPB extends AjaxBasedModule {
	static Logger myLogger = Logger.getLogger(SkrinPopupCarianPB.class);
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	private final String PATH="app/ppt/PopupHakmilik/";
	private String vm = PATH +"SkrinPopupCarianPB.jsp";
	FrmSek8SiasatanData logic = new FrmSek8SiasatanData();
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
		this.context.put("NO_LOT","");
		this.context.put("NAMA_PB","");
		this.context.put("NO_PB","");
		myLogger.info("command :"+command);
		String bolehsimpan = "yes";
		String id_borangf = getParam("id_borangf");
		this.context.put("id_borangf",id_borangf);
		String id_permohonan =  getParam("id_permohonan");
		this.context.put("id_permohonan",id_permohonan);
		String flag_tuntutan = getParam("flag_tuntutan");
		Vector senarai_pihak_penting_bahagian = null;
		if(!flag_tuntutan.equals("Y"))
		{
			flag_tuntutan = "";
		}		
		this.context.put("flag_tuntutan",flag_tuntutan);
		
		senarai_pihak_penting_bahagian = logic.senarai_pihak_penting_bahagian(id_hakmilik);
		this.context.put("senarai_pihak_penting_bahagian",senarai_pihak_penting_bahagian);	
		
		Db db = null;
		try {
				db = new Db();
				this.context.put("NAMA_PB",getParam("NAMA_PB"));
				this.context.put("NO_PB",getParam("NO_PB"));
				
				
				
				if(command.equals("Simpan_Borang"))
				{
					
								
		        		String[] ids1 = this.request.getParameterValues("ids1");
		        		String[] idPB = this.request.getParameterValues("idPB");				
		        		String[] borangE = this.request.getParameterValues("borangE");
		        		String[] catatan = this.request.getParameterValues("catatan");
		        		String[] borangG = this.request.getParameterValues("borangG");
		        		String[] borangK = this.request.getParameterValues("borangK");
		        		
		        		if (idPB != null) {
		        			
		        			for (int i = 0; i < idPB.length; i++) {						
		        					if (bolehsimpan.equals("yes")) 
		        					{
		        						updateBorang(idPB[i],(String) session.getAttribute("_ekptg_user_id"),"clear",db);  									
		        					}
		        			}
		        		 }	
		        		
		        		if (ids1 != null) {
		        			
		        			
		        			for (int i = 0; i < ids1.length; i++) {						
		        					if (bolehsimpan.equals("yes")) 
		        					{
		        						updateBorang(ids1[i],(String) session.getAttribute("_ekptg_user_id"),"borangC",db);  									
		        					}
		        			}
		        		 }
		        		
		        		
		        		if (catatan != null) {
		        			
		        			String[] idPB_catatan = this.request.getParameterValues("idPB");	
		        			for (int i = 0; i < catatan.length; i++) {						
		        					if (bolehsimpan.equals("yes")) 
		        					{
		        						//System.out.println(" idPB_catatan[i] :"+idPB_catatan[i]+" catatan[i] :"+catatan[i]);
		        						updateBorangCatatan(idPB_catatan[i],catatan[i],(String) session.getAttribute("_ekptg_user_id"),"catatan",db);  									
		        					}
		        			}
		        		 }	
		        		
		        		
		        		if (borangE != null) {
		        			
		        			
		        			for (int i = 0; i < borangE.length; i++) {						
		        					if (bolehsimpan.equals("yes")) 
		        					{
		        						updateBorang(borangE[i],(String) session.getAttribute("_ekptg_user_id"),"borangE",db);  									
		        					}
		        			}
		        		 }	
		        		
		        		if (borangG != null) {
		        			
		        			
		        			for (int i = 0; i < borangG.length; i++) {						
		        					if (bolehsimpan.equals("yes")) 
		        					{
		        						updateBorang(borangG[i],(String) session.getAttribute("_ekptg_user_id"),"borangG",db);  									
		        					}
		        			}
		        		 }	
		        		
		        		if (borangK != null) {
		        			
		        			
		        			for (int i = 0; i < borangK.length; i++) {						
		        					if (bolehsimpan.equals("yes")) 
		        					{
		        						updateBorang(borangK[i],(String) session.getAttribute("_ekptg_user_id"),"borangK",db);  									
		        					}
		        			}
		        		 }	
		        		
		        						
		        		
					
				}
				
				
				
				
				displayPB(id_hakmilik,flag_skrin,action,getParam("NAMA_PB"),getParam("NO_PB"),db,id_borangf,id_permohonan);
		
		}finally {
		
		if (db != null)
		db.close();
		}
		
		
		return vm;
	}
	
	
	public void updateBorang(String id_hakmilikpb,
			String id_Masuk,String type,Db db) throws Exception {
	//	Db db = null;
		String sql = "";
		String sql1 = "";

		try {
		//	db = new Db();

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("ID_HAKMILIKPB", id_hakmilikpb);
			
			if(type.equals("borangC"))
			{
			r.add("FLAG_BORANGC", 1);
			}
			
			if(type.equals("borangE"))
			{
			r.add("FLAG_BORANGE", 1);
			}
			
			if(type.equals("borangG"))
			{
			r.add("FLAG_BORANGG", 1);
			}
			
			if(type.equals("borangK"))
			{
			r.add("FLAG_BORANGK", 1);
			}
			
			if(type.equals("catatan"))
			{
				r.add("catatan2", 1);
			}
			
			if(type.equals("clear"))
			{
			r.add("FLAG_BORANGC", "");
			r.add("FLAG_BORANGE", "");
			r.add("FLAG_BORANGG", "");
			r.add("FLAG_BORANGK", "");
			}
			
			
			r.add("ID_KEMASKINI", id_Masuk);
			r.add("ID_MASUK", id_Masuk);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLUpdate("tblppthakmilikpb");
			myLogger.info("SQL INSERT KEHADIRAN :" + sql.toUpperCase());
			stmt.executeUpdate(sql);

		} finally {
		//	if (db != null)
		//		db.close();
		}

	}
	
	
	public void updateBorangCatatan(String id_hakmilikpb,String catatan,
			String id_Masuk,String type,Db db) throws Exception {
	//	Db db = null;
		String sql = "";
		String sql1 = "";

		try {
		//	db = new Db();

			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.update("ID_HAKMILIKPB", id_hakmilikpb);
			
			r.add("catatan2", catatan);
			
			
			r.add("ID_KEMASKINI", id_Masuk);
			r.add("ID_MASUK", id_Masuk);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			sql = r.getSQLUpdate("tblppthakmilikpb");
			myLogger.info("SQL INSERT catatan tuntutan :" + sql.toUpperCase());
			stmt.executeUpdate(sql);

		} finally {
		//	if (db != null)
		//		db.close();
		}

	}
	
	
	private void displayPB(String id_hakmilik,String flag_skrin,String action, String nama_pb,String no_pb,Db db,String id_borangF,String id_permohonan) throws Exception{
		List<Hashtable>  list = null;	
		list = getPB(id_hakmilik,flag_skrin,nama_pb,no_pb,db,id_borangF,id_permohonan);	
		context.put("SenaraiFail", list);
		//setupPage(session,action,list);
	}
	
	
	
	Vector list_pb = null;
	public Vector getPB(String id_hakmilik,String flag_skrin,String nama_pb,String no_pb,Db db,String id_borangF,String id_permohonan) throws Exception {
		list_pb = new Vector();
		list_pb.clear();
		String sql = "";
		try {
			
			/*
			
					sql = "SELECT DISTINCT a.id_buktipenyampaian,a.nama_penerima,a.nama_penghantar,a.catatan,a.jenis_serahan,a.no_kp_penerima, ";
				sql += " a.tarikh_hantar,a.id_jenisnopb,a.flag_serah,a.flag_jenis_bukti, b.nama_pb, b.id_jenisnopb as id_kodnopb, b.no_pb ";
				sql += " FROM tblpptbuktipenyampaian a, tblpptpihakberkepentingan b ";
				sql += " WHERE a.id_pihakberkepentingan = b.id_pihakberkepentingan ";
				sql += " AND a.id_hakmilik = '"+idHakmilik+"'";
				sql += " AND NVL(a.flag_jenis_bukti,0) = '"+flagJenisBukti+"'";
				
				ResultSet rs = stmt.executeQuery(sql);	
				Hashtable h;
				int bil = 1;

				while (rs.next()){
					h = new Hashtable();
					h.put("bil", bil);
					h.put("id_buktipenyampaian", rs.getString("id_buktipenyampaian")==null?"":rs.getString("id_buktipenyampaian"));
					h.put("nama_penerima", rs.getString("nama_penerima")==null?"<b>Tidak Dapat Diserahkan / Bukan Melalui Serahan Tangan</b>":rs.getString("nama_penerima"));
					h.put("nama_penghantar", rs.getString("nama_penghantar")==null?"":rs.getString("nama_penghantar"));			
					h.put("tarikh_hantar", rs.getDate("tarikh_hantar")==null?"":Format.format(rs.getDate("tarikh_hantar")));
					h.put("nama_pb", rs.getString("nama_pb")==null?"":rs.getString("nama_pb"));		
					h.put("id_kodnopb", rs.getString("id_kodnopb")==null?"":rs.getString("id_kodnopb"));		
					h.put("no_pb", rs.getString("no_pb")==null?"":rs.getString("no_pb"));		
					listPenyampaianNotis.addElement(h);
					bil++;	
				}
			
			*/
			
			
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = " SELECT DISTINCT HMPB.ID_PA1, PNGAMANAH.NAMA_PB AS NAMA_PA1, ";			
					sql += " NVL(PB.UMUR,'0')AS UMUR, BPB.KETERANGAN_SYER,BPB.ID_BAHAGIANPB,BPB.SYER_ATAS,BPB.SYER_BAWAH,HMPB.FLAG_BORANGC,HMPB.FLAG_BORANGE,HMPB.FLAG_BORANGG,HMPB.FLAG_BORANGK,HMPB.KETERANGAN_JENIS_PB,HMPB.CATATAN,HMPB.NO_HANDPHONE,HMPB.NO_TEL_RUMAH,HM.ID_HAKMILIK," +
					//	"S.ID_SIASATAN," +
						"PBALIK.ID_PERMOHONAN,";
						
						// "H.FLAG_HADIR," +
						
						if(!id_permohonan.equals("") && !id_permohonan.equals(null) && flag_skrin.equals("bantahan_mahkamah"))
						{
						sql += " CASE ";
						sql += " WHEN HM.NO_LOT IS NOT NULL AND HM.NO_PT IS NULL THEN HM.NO_LOT ";
						sql += " WHEN HM.NO_LOT IS NULL AND HM.NO_PT IS NULL THEN lt.keterangan || HM.NO_PT ";
						sql += " WHEN HM.NO_LOT IS NULL AND HM.NO_PT IS NULL THEN lt.keterangan || HM.NO_PT ";
						sql += " WHEN HM.NO_LOT IS NULL AND HM.NO_PT IS NOT NULL THEN lt.keterangan || HM.NO_PT ";
						sql += " WHEN HM.NO_LOT IS NOT NULL AND HM.NO_PT IS NOT NULL THEN lt.keterangan || HM.NO_PT || CHR(32) || CHR(40) || HM.NO_LOT || CHR(41) ";
						sql += " ELSE ''  ";
						sql += " END AS NO_LOTPT,BAN.FLAG_ONLINE,BAN.STATUS_BANTAHAN,SB.KETERANGAN,BAN.NO_BANTAHAN,";
						}
						else
						{
							sql += " '' AS NO_LOTPT,'' AS FLAG_ONLINE,'' AS STATUS_BANTAHAN,'' AS KETERANGAN,'' AS NO_BANTAHAN,";
						}
						
						if(!id_permohonan.equals("") && !id_permohonan.equals(null) && flag_skrin.equals("bantahan_mahkamah"))
							sql += "NVL('NO.KES : '||IP.NO_KES,'') AS NO_KES, IP.NO_KES AS KES,";
							
						
						sql += "HMPB.ID_PIHAKBERKEPENTINGAN,HMPB.CATATAN2,HMPB.ID_HAKMILIKPB,PB.NAMA_PB,"
						+ "PB.NO_PB,PB.NAMA_KP,HMPB.ID_JENISPB,JPB.KETERANGAN AS JENISPB, "
						+ " HMPB.ALAMAT1,HMPB.ALAMAT2,HMPB.ALAMAT3,HMPB.POSKOD,HMPB.ID_NEGERI,HMPB.ID_BANDAR,N.NAMA_NEGERI, "
						+ " B.KETERANGAN AS NAMA_BANDAR,PB.ID_JENISNOPB,JNOPB.KETERANGAN AS JENISNOPB,HMPB.NO_AKAUN,HMPB.ID_BANK,RB.NAMA_BANK "
						+ " FROM TBLPPTHAKMILIK HM,TBLPPTPERMOHONAN PBALIK," +
							//	"TBLPPTSIASATAN S," +
								"TBLPPTHAKMILIKPB HMPB, ";
						if(!id_borangF.equals("") && !id_borangF.equals(null))
						{							
							sql +=  " TBLPPTBORANGFHAKMILIKPB FA, TBLPPTBORANGF BF,";
						}
						else if(!id_permohonan.equals("") && !id_permohonan.equals(null) && flag_skrin.equals("bantahan_mahkamah"))
						{
							sql += " TBLPPTBORANGH HH,TBLPPTBORANGG GG,TBLPPTBANTAHAN BAN,TBLRUJSTATUS SB,Tblrujlot LT, "
								+ " TBLINTMTPENDAFTARAN IP,";
						}
							
						
						
						
						
			sql += "TBLPPTPIHAKBERKEPENTINGAN PB,TBLRUJJENISPB JPB,TBLRUJNEGERI N,TBLRUJBANDAR B, "
						+ " TBLRUJJENISNOPB JNOPB,TBLRUJBANK RB,TBLPPTBAHAGIANPB BPB, TBLPPTPIHAKBERKEPENTINGAN PNGAMANAH  "
						+ " WHERE " 
					//	+" PH.ID_HAKMILIK = HM.ID_HAKMILIK "
						+" HMPB.ID_BAHAGIANPB = BPB.ID_BAHAGIANPB(+) AND  HM.ID_PERMOHONAN = PBALIK.ID_PERMOHONAN "
					//	" AND  S.ID_HAKMILIK(+) = HM.ID_HAKMILIK "
						+ " AND HMPB.ID_HAKMILIK = HM.ID_HAKMILIK"
						+ " AND PNGAMANAH.ID_PIHAKBERKEPENTINGAN(+) = HMPB.ID_PA1 "
						+ " AND HMPB.ID_PIHAKBERKEPENTINGAN = PB.ID_PIHAKBERKEPENTINGAN "
						+ " AND HMPB.ID_JENISPB = JPB.ID_JENISPB(+) "
						+ " AND HMPB.ID_NEGERI = N.ID_NEGERI(+) "
						+ " AND HMPB.ID_BANDAR = B.ID_BANDAR(+) ";
			
			
			
					
						if(!id_borangF.equals("") && !id_borangF.equals(null))
						{
							
							sql +=  " AND FA.ID_BORANGF = BF.ID_BORANGF AND FA.ID_HAKMILIKPB = HMPB.ID_HAKMILIKPB " +
									" AND FA.ID_BORANGF = '"+id_borangF+"' ";
						}
						if(!id_permohonan.equals("") && !id_permohonan.equals(null) && flag_skrin.equals("bantahan_mahkamah"))
						{
							sql += "  AND HH.ID_BORANGG = GG.ID_BORANGG AND BAN.ID_HAKMILIK IS NULL AND HH.ID_HAKMILIKPB = HMPB.ID_HAKMILIKPB AND HMPB.ID_HAKMILIKPB = BAN.ID_HAKMILIKPB(+) " +
									" AND BAN.STATUS_BANTAHAN = SB.ID_STATUS(+)  AND PBALIK.ID_PERMOHONAN = '"+ id_permohonan+ "'  AND HM.ID_LOT = LT.id_lot(+) ";
						}
						
						else if(!flag_skrin.equals("senarai_borangF_inbulk"))
						{
							sql = sql + " AND HM.ID_HAKMILIK = '"+ id_hakmilik+ "' ";
						}
			
			
						sql += " AND PB.ID_JENISNOPB = JNOPB.ID_JENISNOPB(+) "
						+ " AND HMPB.ID_BANK = RB.ID_BANK(+) ";
						
						
						sql += " AND NVL(HMPB.ID_JENISPB,0) NOT IN ('40','41','42') ";
						
						if(!id_permohonan.equals("") && !id_permohonan.equals(null) && flag_skrin.equals("bantahan_mahkamah"))
							sql += " AND BAN.ID_BANTAHAN=IP.ID_RUJUKAN(+) "; //AND IP.NO_KES IS NOT NULL"; 
						
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
						
						
						
					//sql += " ORDER BY ID_BAHAGIANPB,NAMA_PB ASC" + "";
						
						sql += " ORDER BY (CASE WHEN ID_JENISPB = 1 THEN 1 "+
					            "  WHEN   ID_JENISPB = 2 THEN 2 "+
					            "  ELSE 3 END) ASC, "+
					            "  (CASE WHEN (TO_NUMBER(SYER_ATAS) > 0) AND (TO_NUMBER(SYER_BAWAH) > 0) THEN (TO_NUMBER(SYER_ATAS)/TO_NUMBER(SYER_BAWAH)) "+
					            "  ELSE 0 END) DESC, TRIM(NAMA_PB) ASC ";
					
					
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
				
				h.put("CATATAN2", rs.getString("CATATAN2") == null ? ""
						: rs.getString("CATATAN2"));
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
				
				h.put("status_bantahan", rs.getString("STATUS_BANTAHAN")==null?"":rs.getString("STATUS_BANTAHAN"));
		    	h.put("keteranganStatusBantahan", rs.getString("KETERANGAN")==null?"":rs.getString("KETERANGAN"));
		    	h.put("no_bantahan", rs.getString("NO_BANTAHAN")==null?"":rs.getString("NO_BANTAHAN"));
		    	h.put("no_lotpt", rs.getString("NO_LOTPT")==null?"":rs.getString("NO_LOTPT"));
		    	h.put("flag_online", rs.getString("FLAG_ONLINE")==null?"":rs.getString("FLAG_ONLINE"));
		    	//mt
		    	if(flag_skrin.equals("bantahan_mahkamah")) {
		    	h.put("noKes", rs.getString("NO_KES")==null?"":rs.getString("NO_KES"));
		    	h.put("kes", rs.getString("KES")==null?"":rs.getString("KES"));
		    	}
				list_pb.addElement(h);
			}
			return list_pb;
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
	            
	            myLogger.info("PAGE :"+context.get("itemsPerPage"));
	            
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
