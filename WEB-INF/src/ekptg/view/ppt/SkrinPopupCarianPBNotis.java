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

import ekptg.engine.StateLookup;
import ekptg.helpers.DB;
import ekptg.helpers.Paging2;
import ekptg.model.ppt.FrmPermohonanUPTData;

public class SkrinPopupCarianPBNotis extends AjaxBasedModule {
	static Logger myLogger = Logger.getLogger(SkrinPopupCarianPBNotis.class);
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	private final String PATH="app/ppt/PopupHakmilik/";
	private String vm = PATH +"SkrinPopupCarianPBNotis.jsp";
	FrmPermohonanUPTData modelUPT = new FrmPermohonanUPTData();
	
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
		String id_borangg = getParam("id_borangg");
		this.context.put("id_borangg",id_borangg);
		String id_user = (String) session.getAttribute("_ekptg_user_id");
		
		
		String flagJenisBukti = "";
		
		
		if(flag_skrin.equals("skrin_pb_notis") )
		{
			flagJenisBukti = "1";
			//vm = PATH +"SkrinPopupCarianPBNotis.jsp";
		}		
		else if(flag_skrin.equals("skrin_pb_notis_borangK"))
		{
		flagJenisBukti = "3";
			//vm = PATH +"SkrinPopupCarianPBNotis.jsp";
		}
		
		
		
		
		
		
		
		if(flag_skrin.equals("skrin_kemasukan_notis_secara_pukal") || flag_skrin.equals("skrin_kemasukan_notisK_secara_pukal"))
		{context.put("hideItem", "no");
			
			context.put("txtNamaHantar", getParam("txtNamaHantar"));
			context.put("socJenisSerah", getParam("socJenisSerah"));
			context.put("socStatusSerah", getParam("socStatusSerah"));
			context.put("txdTarikhSerah", getParam("txdTarikhSerah"));
			
			if("checkStatus".equals(command)){
				String statusSerah = getParam("socStatusSerah");
				if(statusSerah.equals("2")){
					context.put("hideItem", "yes");
				}else{
		    		context.put("hideItem", "no");
				}				
			}	
			if(command.equals("simpanMaklumatNotis"))
			{			
	        		String[] checkN = this.request.getParameterValues("checkN");
	        		
	        		Db db = null;
	        		try {
	        				db = new Db();
	        				String txtNamaHantar = getParam("txtNamaHantar");
	        				String socJenisSerah = getParam("socJenisSerah");
	        				String socStatusSerah = getParam("socStatusSerah");
	        				String txdTarikhSerah = getParam("txdTarikhSerah");
	        				String txtMasaTampal = getParam("txtMasaTampal");
	        				String socJenisWaktu = getParam("socJenisWaktu");	        				
	        				
	        				Hashtable dh = getPermohonan(id_hakmilik,db);
	        				String id_permohonan = (String)dh.get("id_permohonan");
	        				String id_fail = (String)dh.get("id_fail");
	        				String id_status = (String)dh.get("id_status");	        				
	        				
	        				String id_suburusanstatusfailppt = "";
	        				Vector listStat = setGetIdSuburusanstatusfail(id_permohonan,db);
	        				if(listStat.size()!=0){
	        					Hashtable idsb = (Hashtable)listStat.get(0);
	        					id_suburusanstatusfailppt = (String)idsb.get("id_suburusanstatusfailppt");
	        				}	        				
	        				
	        		if (checkN != null) {	        			
	        			for (int i = 0; i < checkN.length; i++) {	
	        				
	        				myLogger.info(" checkN :::::: "+checkN[i]);
	        				
	        				Hashtable h = new Hashtable();
	        				
	        				h.put("id_hakmilik", id_hakmilik);
	        				h.put("txtNamaHantar", txtNamaHantar);
	        				h.put("socJenisSerah", socJenisSerah);
	        				h.put("socStatusSerah", socStatusSerah);
	        				h.put("txdTarikhSerah", txdTarikhSerah);
	        				h.put("socJenisWaktu", socJenisWaktu);
	        				h.put("txtMasaTampal", txtMasaTampal);
	        				
	        				String id_pihakberkepentingan = checkN[i];
	        				String txtNamaTerima = checkN[i] +"txtNamaTerima";
	        				String socJenisNoKP = checkN[i] +"socJnsKP";
	        				String txtNoKP = checkN[i] +"no_kp";
	        				String txtHubungan = checkN[i] +"txtHubungan";
	        				String txtCatatan = checkN[i] +"txtCatatan";
	        				String txtTempatTampal = checkN[i] +"txtTempatTampal";	        				
	        				
	        				h.put("id_pihakberkepentingan", id_pihakberkepentingan);	 
	        				h.put("txtNamaTerima", getParam(txtNamaTerima));
	        				h.put("txtNoKP", getParam(txtNoKP));
	        				h.put("socJenisNoKP", getParam(socJenisNoKP));
	        				h.put("txtHubungan", getParam(txtHubungan));
	        				h.put("txtTempatTampal", getParam(txtTempatTampal));
	        				h.put("txtCatatan", getParam(txtCatatan));
	        				h.put("id_user",id_user);	        				
	        				
	        				hapusBuktiPenyampaian(id_pihakberkepentingan, flagJenisBukti,db);
	        				long idbuktipenyampaian = getNextID("TBLPPTBUKTIPENYAMPAIAN_SEQ",db);	        				
	        				simpanPenyampaianNotis(h,idbuktipenyampaian,flagJenisBukti,db);        				
	            			if(id_status.equals("52") || id_status.equals("74")){
	            				updateStatus(id_permohonan,id_status,id_user,db);
	            				updateSuburusanStatusFailPPT(id_user,id_fail,id_permohonan,id_suburusanstatusfailppt,"1479",db);
	            			}        				
	        			}
	        		 }	
	        		}finally {
	        			
	        			if (db != null)
	        			db.close();
	        		}
			}
			
			
			
			
			
			
			
			
			
			
			
			
			context.put("selectJenisNoKP", getRujJenisNoPB());
		}
		
		
		Db db = null;
		try {
				db = new Db();
				this.context.put("NAMA_PB",getParam("NAMA_PB"));
				this.context.put("NO_PB",getParam("NO_PB"));				
				displayPB(id_hakmilik,flag_skrin,action,getParam("NAMA_PB"),getParam("NO_PB"),db,flagJenisBukti,id_borangg);
		
		}finally {
		
		if (db != null)
		db.close();
		}
		
		return vm;
	}
	
	Vector list_statusfail = null;
	@SuppressWarnings("unchecked")
	public Vector setGetIdSuburusanstatusfail(String id_permohonan,Db db) throws Exception {
		
		list_statusfail = new Vector();		
		//Db db = null;
		list_statusfail.clear();
		String sql = "";		
		try{
			//	db = new Db();
				Statement stmt = db.getStatement();				
				sql = "SELECT DISTINCT ID_SUBURUSANSTATUSFAILPPT,ID_SUBURUSANSTATUS,AKTIF ";
				sql += " FROM TBLRUJSUBURUSANSTATUSFAILPPT ";
				sql += " WHERE ID_PERMOHONAN = '"+id_permohonan+"'";
				sql += " AND AKTIF = '1' ";				
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;		
				while(rs.next()) {
					h = new Hashtable();
					h.put("id_suburusanstatusfailppt", rs.getString("ID_SUBURUSANSTATUSFAILPPT")==null?"":rs.getString("ID_SUBURUSANSTATUSFAILPPT"));
					h.put("id_suburusanstatus", rs.getString("ID_SUBURUSANSTATUS")==null?"":rs.getString("ID_SUBURUSANSTATUS"));
					list_statusfail.addElement(h);
				}
				return list_statusfail;
		}
		finally {
		//	if(db != null) db.close();
		}		
	}//close setGetIdSuburusanstatusfail
	

	public  Vector getRujJenisNoPB() throws Exception {
	    Db db = null;
	    String sql = " SELECT ID_JENISNOPB, KOD_JENIS_NOPB, KETERANGAN FROM  TBLRUJJENISNOPB WHERE ID_JENISNOPB <> '9' ORDER BY LPAD(ID_JENISNOPB,10) ";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      ResultSet rs = stmt.executeQuery(sql);
	      Vector v = new Vector();
	  //    Tblrujjenisnopb u = null;
	      while (rs.next()) {
	    	  Hashtable h = new Hashtable();
	    //	  u = new Tblrujjenisnopb();
	    	  h.put("ID_JENISNOPB", rs.getString("ID_JENISNOPB")== null?"":rs.getString("ID_JENISNOPB"));
	    	  h.put("KOD_JENIS_NOPB", rs.getString("KOD_JENIS_NOPB")== null?"":rs.getString("KOD_JENIS_NOPB"));
	    	  h.put("KETERANGAN", rs.getString("KETERANGAN")== null?"":rs.getString("KETERANGAN"));
	    	  v.addElement(h);
	      }
	      return v;
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
	
	private void displayPB(String id_hakmilik,String flag_skrin,String action, String nama_pb,String no_pb,Db db,String flagJenisBukti,String id_borangg) throws Exception{
		List<Hashtable>  list = null;	
		list = getPB(id_hakmilik,flag_skrin,nama_pb,no_pb,db,flagJenisBukti,id_borangg);	
		context.put("SenaraiFail", list);
		setupPage(session,action,list);
	}
	
	
	
	Vector list_pb = null;
	public Vector getPB(String id_hakmilik,String flag_skrin,String nama_pb,String no_pb,Db db,String flagJenisBukti,String id_borangg) throws Exception {
		list_pb = new Vector();
		list_pb.clear();
		String sql = "";
		try {
			
			
			
			
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			
			if(flag_skrin.equals("skrin_penerima_H"))
			{			
			sql = "SELECT DISTINCT D.ID_HAKMILIKPB, B.ID_BORANGH, B.TARIKH_SERAH, B.TARIKH_TERIMA, B.TARIKH_AKHIR_BAYARAGENSI, B.KEPUTUSAN, ";
    		sql += " D.ID_PIHAKBERKEPENTINGAN, C.NAMA_PB,C.no_pb, "; 		    
    		sql += " B.HUBUNGAN, B.NAMA_PENGHANTAR, B.MASA_TAMPAL, B.JENIS_WAKTU, ";
    		sql += " B.TEMPAT_TAMPAL, B.JENIS_SERAHAN, B.FLAG_SERAH, B.NAMA_PENERIMA, B.NO_KP_PENERIMA, B.CATATAN, ";
    		sql += " B.ID_JENISNOPB, B.TARIKH_HANTAR ";		    		
    		sql += " FROM TBLPPTBORANGG A, TBLPPTBORANGH B, TBLPPTPIHAKBERKEPENTINGAN C, TBLPPTHAKMILIKPB D ";
    		sql += " WHERE B.ID_BORANGG = A.ID_BORANGG ";
    		sql += " AND B.ID_HAKMILIKPB = D.ID_HAKMILIKPB ";
    		sql += " AND D.ID_PIHAKBERKEPENTINGAN = C.ID_PIHAKBERKEPENTINGAN(+)";
    		sql += " AND A.ID_BORANGG = '"+id_borangg+"'";
    		sql += " ORDER BY NAMA_PB ";
			}/*		
			else if(flag_skrin.equals("skrin_kemasukan_notis_secara_pukal") || flag_skrin.equals("skrin_kemasukan_notisK_secara_pukal"))
			{			
				sql = " SELECT HMPB.ID_PA1, PNGAMANAH.NAMA_PB AS NAMA_PA1, ";			
				sql += " NVL(PB.UMUR,'0')AS UMUR, BPB.KETERANGAN_SYER,BPB.ID_BAHAGIANPB,BPB.SYER_ATAS,BPB.SYER_BAWAH,HMPB.FLAG_BORANGC,HMPB.FLAG_BORANGE,HMPB.FLAG_BORANGG,HMPB.FLAG_BORANGK,HMPB.KETERANGAN_JENIS_PB,HMPB.CATATAN,HMPB.NO_HANDPHONE,HMPB.NO_TEL_RUMAH,HM.ID_HAKMILIK," +
				//	"S.ID_SIASATAN," +
					"PBALIK.ID_PERMOHONAN,"
					+
					// "H.FLAG_HADIR," +
					"HMPB.ID_PIHAKBERKEPENTINGAN,HMPB.ID_HAKMILIKPB,PB.NAMA_PB,"
					+ "PB.NO_PB,PB.NAMA_KP,HMPB.ID_JENISPB,JPB.KETERANGAN AS JENISPB, "
					+ " HMPB.ALAMAT1,HMPB.ALAMAT2,HMPB.ALAMAT3,HMPB.POSKOD,HMPB.ID_NEGERI,HMPB.ID_BANDAR,N.NAMA_NEGERI, "
					+ " B.KETERANGAN AS NAMA_BANDAR,PB.ID_JENISNOPB,JNOPB.KETERANGAN AS JENISNOPB,HMPB.NO_AKAUN,HMPB.ID_BANK,RB.NAMA_BANK, "
					+ " (SELECT COUNT(PY.ID_BUKTIPENYAMPAIAN) AS CHECK_BUKTI FROM TBLPPTBUKTIPENYAMPAIAN PY WHERE PY.ID_PIHAKBERKEPENTINGAN = PB.ID_PIHAKBERKEPENTINGAN AND NVL(PY.FLAG_JENIS_BUKTI,0) = '"+flagJenisBukti+"') AS CHECK_BUKTI, "
					+ " (SELECT PY.ID_BUKTIPENYAMPAIAN FROM TBLPPTBUKTIPENYAMPAIAN PY WHERE PY.ID_PIHAKBERKEPENTINGAN = PB.ID_PIHAKBERKEPENTINGAN AND NVL (PY.FLAG_JENIS_BUKTI, 0) = '"+flagJenisBukti+"') AS ID_BUKTIPENYAMPAIAN, "
					+ " (SELECT PY.NAMA_PENGHANTAR FROM TBLPPTBUKTIPENYAMPAIAN PY WHERE PY.ID_PIHAKBERKEPENTINGAN = PB.ID_PIHAKBERKEPENTINGAN AND NVL (PY.FLAG_JENIS_BUKTI, 0) = '"+flagJenisBukti+"') AS NAMA_PENGHANTAR, "
					+ " (SELECT PY.MASA_TAMPAL FROM TBLPPTBUKTIPENYAMPAIAN PY WHERE PY.ID_PIHAKBERKEPENTINGAN = PB.ID_PIHAKBERKEPENTINGAN AND NVL (PY.FLAG_JENIS_BUKTI, 0) = '"+flagJenisBukti+"') AS MASA_TAMPAL, "
					+ " (SELECT PY.JENIS_WAKTU FROM TBLPPTBUKTIPENYAMPAIAN PY WHERE PY.ID_PIHAKBERKEPENTINGAN = PB.ID_PIHAKBERKEPENTINGAN AND NVL (PY.FLAG_JENIS_BUKTI, 0) = '"+flagJenisBukti+"') AS JENIS_WAKTU, "
					+ " (SELECT PY.TEMPAT_TAMPAL FROM TBLPPTBUKTIPENYAMPAIAN PY WHERE PY.ID_PIHAKBERKEPENTINGAN = PB.ID_PIHAKBERKEPENTINGAN AND NVL (PY.FLAG_JENIS_BUKTI, 0) = '"+flagJenisBukti+"') AS TEMPAT_TAMPAL, "
					+ " (SELECT PY.JENIS_SERAHAN FROM TBLPPTBUKTIPENYAMPAIAN PY WHERE PY.ID_PIHAKBERKEPENTINGAN = PB.ID_PIHAKBERKEPENTINGAN AND NVL (PY.FLAG_JENIS_BUKTI, 0) = '"+flagJenisBukti+"') AS JENIS_SERAHAN, "
					+ " (SELECT PY.FLAG_JENIS_BUKTI FROM TBLPPTBUKTIPENYAMPAIAN PY WHERE PY.ID_PIHAKBERKEPENTINGAN = PB.ID_PIHAKBERKEPENTINGAN AND NVL (PY.FLAG_JENIS_BUKTI, 0) = '"+flagJenisBukti+"') AS FLAG_JENIS_BUKTI, "
					+ " (SELECT PY.FLAG_SERAH FROM TBLPPTBUKTIPENYAMPAIAN PY WHERE PY.ID_PIHAKBERKEPENTINGAN = PB.ID_PIHAKBERKEPENTINGAN AND NVL (PY.FLAG_JENIS_BUKTI, 0) = '"+flagJenisBukti+"') AS FLAG_SERAH, "
					+ " (SELECT PY.NAMA_PENERIMA FROM TBLPPTBUKTIPENYAMPAIAN PY WHERE PY.ID_PIHAKBERKEPENTINGAN = PB.ID_PIHAKBERKEPENTINGAN AND NVL (PY.FLAG_JENIS_BUKTI, 0) = '"+flagJenisBukti+"') AS NAMA_PENERIMA, "
					+ " (SELECT PY.NO_KP_PENERIMA FROM TBLPPTBUKTIPENYAMPAIAN PY WHERE PY.ID_PIHAKBERKEPENTINGAN = PB.ID_PIHAKBERKEPENTINGAN AND NVL (PY.FLAG_JENIS_BUKTI, 0) = '"+flagJenisBukti+"') AS NO_KP_PENERIMA, "
					+ " (SELECT PY.CATATAN FROM TBLPPTBUKTIPENYAMPAIAN PY WHERE PY.ID_PIHAKBERKEPENTINGAN = PB.ID_PIHAKBERKEPENTINGAN AND NVL (PY.FLAG_JENIS_BUKTI, 0) = '"+flagJenisBukti+"') AS CATATAN_BUKTI, "
					+ " (SELECT PY.HUBUNGAN FROM TBLPPTBUKTIPENYAMPAIAN PY WHERE PY.ID_PIHAKBERKEPENTINGAN = PB.ID_PIHAKBERKEPENTINGAN AND NVL (PY.FLAG_JENIS_BUKTI, 0) = '"+flagJenisBukti+"') AS HUBUNGAN, "
					+ " (SELECT PY.ID_JENISNOPB FROM TBLPPTBUKTIPENYAMPAIAN PY WHERE PY.ID_PIHAKBERKEPENTINGAN = PB.ID_PIHAKBERKEPENTINGAN AND NVL (PY.FLAG_JENIS_BUKTI, 0) = '"+flagJenisBukti+"') AS ID_JENISNOPB_BUKTI "
					+ " FROM TBLPPTHAKMILIK HM,TBLPPTPERMOHONAN PBALIK," +
					"TBLPPTHAKMILIKPB HMPB, ";
		sql += "TBLPPTPIHAKBERKEPENTINGAN PB,TBLRUJJENISPB JPB,TBLRUJNEGERI N,TBLRUJBANDAR B, "
					+ " TBLRUJJENISNOPB JNOPB,TBLRUJBANK RB,TBLPPTBAHAGIANPB BPB, TBLPPTPIHAKBERKEPENTINGAN PNGAMANAH  "
					+ " WHERE " 
					+" HMPB.ID_BAHAGIANPB = BPB.ID_BAHAGIANPB(+) AND  HM.ID_PERMOHONAN = PBALIK.ID_PERMOHONAN "
					+ " AND HMPB.ID_HAKMILIK = HM.ID_HAKMILIK"
					+ " AND PNGAMANAH.ID_PIHAKBERKEPENTINGAN(+) = HMPB.ID_PA1 "
					+ " AND HMPB.ID_PIHAKBERKEPENTINGAN = PB.ID_PIHAKBERKEPENTINGAN "
					+ " AND HMPB.ID_JENISPB = JPB.ID_JENISPB(+) "
					+ " AND HMPB.ID_NEGERI = N.ID_NEGERI(+) "
					+ " AND HMPB.ID_BANDAR = B.ID_BANDAR(+) ";
					sql = sql + " AND HM.ID_HAKMILIK = '"+ id_hakmilik+ "' ";				
					sql += " AND PB.ID_JENISNOPB = JNOPB.ID_JENISNOPB(+) "
					+ " AND HMPB.ID_BANK = RB.ID_BANK(+) ";				
					sql += " AND NVL(HMPB.ID_JENISPB,0) NOT IN ('40','41','42') ";					
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
					sql += " ORDER BY (CASE "+
							"  WHEN CHECK_BUKTI > 0 "+
							"  THEN 1 "+
							"  WHEN CHECK_BUKTI = 0 "+
							"  THEN 2 "+
							"  ELSE 3 "+
							"  END "+
							"  ) ASC,(CASE WHEN ID_JENISPB = 1 THEN 1 "+
				            "  WHEN   ID_JENISPB = 2 THEN 2 "+
				            "  ELSE 3 END) ASC, "+
				            "  (CASE WHEN (TO_NUMBER(SYER_ATAS) > 0) AND (TO_NUMBER(SYER_BAWAH) > 0) THEN (TO_NUMBER(SYER_ATAS)/TO_NUMBER(SYER_BAWAH)) "+
				            "  ELSE 0 END) DESC, TRIM(NAMA_PB) ASC ";
			}	*/	
			else
			{
			sql = "SELECT DISTINCT a.id_buktipenyampaian,a.nama_penerima,a.nama_penghantar,a.catatan,a.jenis_serahan,a.no_kp_penerima, ";
			sql += " a.tarikh_hantar,a.id_jenisnopb,a.flag_serah,a.flag_jenis_bukti, b.nama_pb, b.id_jenisnopb as id_kodnopb, b.no_pb ";
			sql += " FROM tblpptbuktipenyampaian a, tblpptpihakberkepentingan b ";
			sql += " WHERE a.id_pihakberkepentingan = b.id_pihakberkepentingan ";
			sql += " AND a.id_hakmilik = '"+id_hakmilik+"'";
			sql += " AND NVL(a.flag_jenis_bukti,0) = '"+flagJenisBukti+"'";
			if(!no_pb.equals("") && !no_pb.equals(null))
			{				
				if (!no_pb.trim().equals("")) {
					sql = sql + " AND UPPER(b.NO_PB) LIKE '%"
							+ no_pb.toUpperCase().trim() + "%'";	
				}
				
			}
			if(!nama_pb.equals("") && !nama_pb.equals(null))
			{				
				if (!nama_pb.trim().equals("")) {
					sql = sql + " AND  UPPER(b.NAMA_PB) LIKE '%"
					+ nama_pb.toUpperCase().trim() + "%'";
				}
				
			}
			
			sql += " ORDER BY b.nama_pb ";
			}
		
				
			myLogger.info("LIST pb :"+sql.toUpperCase());
			
			stmt.setFetchSize(10);
			ResultSet rs = stmt.executeQuery(sql);
			int bil = 0;
			String temp = "";
			while (rs.next()) {
				bil = bil + 1;
				Hashtable h = new Hashtable();				
				h.put("bil", bil);
				if(flag_skrin.equals("skrin_penerima_H"))
				{
					h.put("id_hakmilikpb", rs.getString("ID_HAKMILIKPB")== null?"":rs.getString("ID_HAKMILIKPB"));
	    			h.put("id_borangh", rs.getString("ID_BORANGH")== null?"":rs.getString("ID_BORANGH"));
	    			h.put("id_pihakberkepentingan", rs.getString("ID_PIHAKBERKEPENTINGAN")== null?"":rs.getString("ID_PIHAKBERKEPENTINGAN"));
	    			h.put("nama_pb", rs.getString("NAMA_PB")== null?"":rs.getString("NAMA_PB"));
	    			h.put("keputusan", rs.getString("KEPUTUSAN")== null?"":rs.getString("KEPUTUSAN"));
	    			
	    			if(rs.getString("KEPUTUSAN") != null && rs.getString("KEPUTUSAN") != ""){
	    				if(rs.getString("KEPUTUSAN").equals("1")){
	    					h.put("jenis_keputusan","MENERIMA TAWARAN");
	    				}else if(rs.getString("KEPUTUSAN").equals("2")){
	    					h.put("jenis_keputusan","MENERIMA TAWARAN DENGAN BANTAHAN");
	    				}else if(rs.getString("KEPUTUSAN").equals("3")){
	    					h.put("jenis_keputusan","TIDAK MENERIMA TAWARAN");
	    				}else if(rs.getString("KEPUTUSAN").equals("4")){
	    					h.put("jenis_keputusan","TIDAK BERKENAAN");
	    				}else{
	    					h.put("jenis_keputusan","");
	    				}
	    			}else{
	    				h.put("jenis_keputusan","");
	    			}
	    			
	    			h.put("tarikh_serah", rs.getDate("TARIKH_SERAH")==null?"":Format.format(rs.getDate("TARIKH_SERAH")));
					h.put("tarikh_terima", rs.getDate("TARIKH_TERIMA")==null?"":Format.format(rs.getDate("TARIKH_TERIMA")));
					h.put("tarikh_akhir_bayar", rs.getDate("TARIKH_AKHIR_BAYARAGENSI")==null?"":Format.format(rs.getDate("TARIKH_AKHIR_BAYARAGENSI")));
					
					h.put("tarikh_hantar", rs.getDate("TARIKH_HANTAR")==null?"":Format.format(rs.getDate("TARIKH_HANTAR")));
					h.put("hubungan", rs.getString("HUBUNGAN")== null?"":rs.getString("HUBUNGAN"));
					h.put("nama_penghantar", rs.getString("NAMA_PENGHANTAR")== null?"":rs.getString("NAMA_PENGHANTAR"));
					h.put("masa_tampal", rs.getString("MASA_TAMPAL")== null?"":rs.getString("MASA_TAMPAL"));
					h.put("jenis_waktu", rs.getString("JENIS_WAKTU")== null?"":rs.getString("JENIS_WAKTU"));
					h.put("tempat_tampal", rs.getString("TEMPAT_TAMPAL")== null?"":rs.getString("TEMPAT_TAMPAL"));
					h.put("jenis_serahan", rs.getString("JENIS_SERAHAN")== null?"":rs.getString("JENIS_SERAHAN"));
					h.put("flag_serah", rs.getString("FLAG_SERAH")== null?"":rs.getString("FLAG_SERAH"));
					h.put("nama_penerima", rs.getString("NAMA_PENERIMA")== null?"<b>Tidak Dapat Diserahkan</b>":rs.getString("NAMA_PENERIMA"));
					h.put("no_kp_penerima", rs.getString("NO_KP_PENERIMA")== null?"":rs.getString("NO_KP_PENERIMA"));
					h.put("catatan", rs.getString("CATATAN")== null?"":rs.getString("CATATAN"));
					h.put("id_jenisnopb", rs.getString("ID_JENISNOPB")== null?"":rs.getString("ID_JENISNOPB"));
					h.put("no_pb", rs.getString("no_pb")==null?"":rs.getString("no_pb").toUpperCase());	
					
					
				}/*
				else if(flag_skrin.equals("skrin_kemasukan_notis_secara_pukal") || flag_skrin.equals("skrin_kemasukan_notisK_secara_pukal"))
				{
					h.put("bil", bil);
					h.put("UMUR", rs.getInt("UMUR") == 0 ? 0: rs.getInt("UMUR"));	
					h.put("ID_PA1", rs.getString("ID_PA1") == null ? "" : rs.getString("ID_PA1"));	
					h.put("NAMA_PA1", rs.getString("NAMA_PA1") == null ? "" : rs.getString("NAMA_PA1"));					
					h.put("ID_BAHAGIANPB", rs.getString("ID_BAHAGIANPB") == null ? "": rs.getString("ID_BAHAGIANPB"));					
					h.put("SYER_ATAS", rs.getString("SYER_ATAS") == null ? "": rs.getString("SYER_ATAS"));
					h.put("SYER_BAWAH", rs.getString("SYER_BAWAH") == null ? ""	: rs.getString("SYER_BAWAH"));					
					h.put("FLAG_BORANGC", rs.getString("FLAG_BORANGC") == null ? ""	: rs.getString("FLAG_BORANGC"));
					h.put("FLAG_BORANGE", rs.getString("FLAG_BORANGE") == null ? ""	: rs.getString("FLAG_BORANGE"));
					h.put("FLAG_BORANGG", rs.getString("FLAG_BORANGG") == null ? ""	: rs.getString("FLAG_BORANGG"));
					h.put("FLAG_BORANGK", rs.getString("FLAG_BORANGK") == null ? ""	: rs.getString("FLAG_BORANGK"));					
					h.put("CATATAN", rs.getString("CATATAN") == null ? "": rs.getString("CATATAN"));
					h.put("KETERANGAN_JENIS_PB", rs.getString("KETERANGAN_SYER") == null ? "": rs.getString("KETERANGAN_SYER"));
					h.put("NO_HANDPHONE", rs.getString("NO_HANDPHONE") == null ? ""	: rs.getString("NO_HANDPHONE"));
					h.put("NO_TEL_RUMAH", rs.getString("NO_TEL_RUMAH") == null ? ""	: rs.getString("NO_TEL_RUMAH"));
					h.put("ID_HAKMILIK", rs.getString("ID_HAKMILIK") == null ? "": rs.getString("ID_HAKMILIK"));
					h.put("ID_PENARIKANBALIK",rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
					h.put("ID_PIHAKBERKEPENTINGAN", rs.getString("ID_PIHAKBERKEPENTINGAN") == null ? "" : rs.getString("ID_PIHAKBERKEPENTINGAN"));
					h.put("ID_HAKMILIKPB",rs.getString("ID_HAKMILIKPB") == null ? "" : rs.getString("ID_HAKMILIKPB"));
					h.put("NAMA_PB", rs.getString("NAMA_PB") == null ? "" : rs.getString("NAMA_PB").toUpperCase());
					h.put("NO_PB", rs.getString("NO_PB") == null ? "" : rs.getString("NO_PB").toUpperCase());
					h.put("NAMA_KP", rs.getString("NAMA_KP") == null ? "" : rs.getString("NAMA_KP").toUpperCase());
					h.put("ID_JENISPB", rs.getString("ID_JENISPB") == null ? "": rs.getString("ID_JENISPB"));
					h.put("JENISPB", rs.getString("JENISPB") == null ? "" : rs.getString("JENISPB").toUpperCase());
					h.put("ALAMAT1", rs.getString("ALAMAT1") == null ? "" : rs.getString("ALAMAT1").toUpperCase());
					h.put("ALAMAT2", rs.getString("ALAMAT2") == null ? "" : rs.getString("ALAMAT2").toUpperCase());
					h.put("ALAMAT3", rs.getString("ALAMAT3") == null ? "" : rs.getString("ALAMAT3").toUpperCase());
					h.put("POSKOD", rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD"));
					h.put("ID_NEGERI", rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));
					h.put("ID_BANDAR", rs.getString("ID_BANDAR") == null ? "" : rs.getString("ID_BANDAR"));
					h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI") == null ? "": rs.getString("NAMA_NEGERI").toUpperCase());
					h.put("NAMA_BANDAR", rs.getString("NAMA_BANDAR") == null ? "": rs.getString("NAMA_BANDAR").toUpperCase());
					h.put("ID_JENISNOPB", rs.getString("ID_JENISNOPB") == null ? "": rs.getString("ID_JENISNOPB"));
					h.put("JENISNOPB", rs.getString("JENISNOPB") == null ? "" : rs.getString("JENISNOPB").toUpperCase());
					h.put("NO_AKAUN", rs.getString("NO_AKAUN") == null ? "" : rs.getString("NO_AKAUN").toUpperCase());
					h.put("ID_BANK", rs.getString("ID_BANK") == null ? "" : rs.getString("ID_BANK"));
					h.put("NAMA_BANK", rs.getString("NAMA_BANK") == null ? "" : rs.getString("NAMA_BANK").toUpperCase());
					h.put("CHECK_BUKTI", rs.getInt("CHECK_BUKTI"));
					
					
					
						h.put("ID_BUKTIPENYAMPAIAN", rs.getString("ID_BUKTIPENYAMPAIAN") == null ? "" : rs.getString("ID_BUKTIPENYAMPAIAN").toUpperCase());
						h.put("NAMA_PENGHANTAR", rs.getString("NAMA_PENGHANTAR") == null ? "" : rs.getString("NAMA_PENGHANTAR").toUpperCase());
						h.put("MASA_TAMPAL",rs.getString("MASA_TAMPAL") == null ? "" : rs.getString("MASA_TAMPAL").toUpperCase());
						h.put("JENIS_WAKTU",rs.getString("JENIS_WAKTU") == null ? "" : rs.getString("JENIS_WAKTU").toUpperCase());
						h.put("TEMPAT_TAMPAL", rs.getString("TEMPAT_TAMPAL") == null ? "" : rs.getString("TEMPAT_TAMPAL").toUpperCase());
						h.put("JENIS_SERAHAN", rs.getString("JENIS_SERAHAN") == null ? "" : rs.getString("JENIS_SERAHAN").toUpperCase());
						h.put("FLAG_JENIS_BUKTI", rs.getString("FLAG_JENIS_BUKTI") == null ? "" : rs.getString("FLAG_JENIS_BUKTI").toUpperCase());
						h.put("FLAG_SERAH",rs.getString("FLAG_SERAH") == null ? "" : rs.getString("FLAG_SERAH").toUpperCase());
						h.put("NAMA_PENERIMA", rs.getString("NAMA_PENERIMA") == null ? "" : rs.getString("NAMA_PENERIMA").toUpperCase());
						h.put("NO_KP_PENERIMA", rs.getString("NO_KP_PENERIMA") == null ? "" : rs.getString("NO_KP_PENERIMA").toUpperCase());
						h.put("CATATAN_BUKTI", rs.getString("CATATAN_BUKTI") == null ? "" : rs.getString("CATATAN_BUKTI").toUpperCase());
						h.put("ID_JENISNOPB_BUKTI",rs.getString("ID_JENISNOPB_BUKTI") == null ? "" : rs.getString("ID_JENISNOPB_BUKTI").toUpperCase());
						h.put("HUBUNGAN",rs.getString("HUBUNGAN") == null ? "" : rs.getString("HUBUNGAN").toUpperCase());
					
					
				}*/
				else
				{
				h.put("id_buktipenyampaian", rs.getString("id_buktipenyampaian")==null?"":rs.getString("id_buktipenyampaian").toUpperCase());
				h.put("nama_penerima", rs.getString("nama_penerima")==null?"<b>Tidak Dapat Diserahkan / Bukan Melalui Serahan Tangan</b>":rs.getString("nama_penerima").toUpperCase());
				h.put("nama_penghantar", rs.getString("nama_penghantar")==null?"":rs.getString("nama_penghantar").toUpperCase());			
				h.put("tarikh_hantar", rs.getDate("tarikh_hantar")==null?"":Format.format(rs.getDate("tarikh_hantar")));
				h.put("nama_pb", rs.getString("nama_pb")==null?"":rs.getString("nama_pb").toUpperCase());		
				h.put("id_kodnopb", rs.getString("id_kodnopb")==null?"":rs.getString("id_kodnopb").toUpperCase());		
				h.put("no_pb", rs.getString("no_pb")==null?"":rs.getString("no_pb").toUpperCase());
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
	
	 
	 
	 Hashtable getPermohonan = null;
		public Hashtable getPermohonan(String id_hakmilik,Db db) throws Exception {
			getPermohonan = new Hashtable();			
			//Db db = null;
			String sql = "";
			try {
				//db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				sql = " SELECT P.ID_PERMOHONAN, F.ID_FAIL,P.ID_STATUS FROM TBLPPTHAKMILIK H, TBLPPTPERMOHONAN P, TBLPFDFAIL F "+
					"	WHERE P.ID_FAIL = F.ID_FAIL AND P.ID_PERMOHONAN = H.ID_PERMOHONAN AND  H.ID_HAKMILIK = '"+ id_hakmilik+"' ";				
				ResultSet rs = stmt.executeQuery(sql);				
				String id_permohonan = "";
				String id_fail = "";
				String id_status = "";
				while (rs.next()){					
					id_permohonan = rs.getString("ID_PERMOHONAN")==null?"":rs.getString("ID_PERMOHONAN").toUpperCase();			
					id_fail = rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL").toUpperCase();	
					id_status = rs.getString("ID_STATUS")==null?"":rs.getString("ID_STATUS").toUpperCase();	
					getPermohonan.put("id_permohonan", id_permohonan);
					getPermohonan.put("id_fail", id_fail);
					getPermohonan.put("id_status", id_status);
				}
				return getPermohonan;
			} finally {
				//if (db != null) db.close();
			}
		}
		
		
		@SuppressWarnings("unchecked")
		public static void updateSuburusanStatusFailPPT(String id_user,String id_fail,String id_permohonan,String currentSuburusanstatusfailppt,String newSuburusanStatus,Db db) throws Exception
		  {
			
		   // Db db = null;
		    String sql = "";
		    
		    try{
		      
		    	//	db = new Db();
		    		Statement stmt = db.getStatement();
		    		
		    		//update n add tblrujsuburusanstatus
				    SQLRenderer r = new SQLRenderer();
					r.update("ID_SUBURUSANSTATUSFAILPPT", currentSuburusanstatusfailppt);	
					r.add("AKTIF",0);
					r.add("ID_KEMASKINI",id_user);
					r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
					sql = r.getSQLUpdate("tblrujsuburusanstatusfailppt");
					stmt.executeUpdate(sql);	 
					  
					r.clear();
					
					r.add("ID_SUBURUSANSTATUSFAILPPT",DB.getNextID("TBLRUJSUBURUSANSTATUSPPT_SEQ"));
					r.add("ID_PERMOHONAN",id_permohonan);
					r.add("ID_FAIL",id_fail);
					r.add("ID_SUBURUSANSTATUS",newSuburusanStatus);
					r.add("AKTIF",1);
					r.add("ID_MASUK",id_user);
					r.add("ID_KEMASKINI",id_user);
					r.add("TARIKH_MASUK",r.unquote("sysdate"));
					r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
					sql = r.getSQLInsert("tblrujsuburusanstatusfailppt");
					stmt.executeUpdate(sql);
		    	
		    }//close try 
		    finally {
		   // if (db != null) db.close();
		    }//close finally
		   
		  }//close updateSuburusanStatusFailPP
		
		
		@SuppressWarnings("unchecked")
		public static void updateStatus(String id_permohonan,String id_status,String user_id,Db db) throws Exception{
				
			//  Db db = null;
			  String sql = "";
		    
			  try{
		      
				  //	db = new Db();
		    		Statement stmt = db.getStatement();
		    		//status penyampaian notis
		    		String status = "58";		    		
		    		SQLRenderer r = new SQLRenderer();
		    		r.update("id_permohonan", id_permohonan);	   
		    		r.add("id_status",status);  
		    		r.add("tarikh_kemaskini",r.unquote("sysdate"));
		    		r.add("id_kemaskini",user_id);    		
		    		sql = r.getSQLUpdate("tblpptpermohonan");
		    		stmt.executeUpdate(sql);
	    	
		    }//close try 
		    finally {
		     // if (db != null) db.close();
		    }//close finally
		   
		  }//close updateStatus
		
		@SuppressWarnings("unchecked")
		public static void simpanPenyampaianNotis(Hashtable data,long id_buktipenyampaian,String flagJenisBukti,Db db) throws Exception
		  {
			
		 //   Db db = null;
		    String sql = "";
		    
		    try{
		      
		    		//db = new Db();
		    		Statement stmt = db.getStatement();
		    		
		    		String id_user = (String)data.get("id_user");
		    		String id_hakmilik = (String)data.get("id_hakmilik");
		    		String id_pihakberkepentingan = (String)data.get("id_pihakberkepentingan");
		    		
		    		String txtNamaHantar = (String)data.get("txtNamaHantar");
		    		String socKodDokumen = (String)data.get("socKodDokumen");
		    		String socJenisSerah = (String)data.get("socJenisSerah");
		    		String socStatusSerah = (String)data.get("socStatusSerah");
		    		String txdTarikhSerah = (String)data.get("txdTarikhSerah");
		    		
		    		//data penerima
		    		String txtNamaTerima = (String)data.get("txtNamaTerima");
		    		String txtNoKP = (String)data.get("txtNoKP");
		    		String socJenisNoKP = (String)data.get("socJenisNoKP");
		    		String txtHubungan = (String)data.get("txtHubungan");
		    		
		    		//data tampal
		    		String txtMasaTampal = (String)data.get("txtMasaTampal");
		    		String socJenisWaktu = (String)data.get("socJenisWaktu");
		    		String txtTempatTampal = (String)data.get("txtTempatTampal");

		    		String txtCatatan = (String)data.get("txtCatatan");
		    		
		    		String TS = "to_date('" + txdTarikhSerah + "','dd/MM/yyyy')";
	  		
		    		SQLRenderer r = new SQLRenderer();
		    		r.add("id_buktipenyampaian",id_buktipenyampaian);
		    		r.add("id_hakmilik", id_hakmilik);	  
		    		r.add("hubungan", txtHubungan);	
		    		r.add("id_pihakberkepentingan", id_pihakberkepentingan);	
		    		r.add("nama_penghantar", txtNamaHantar);
		    		r.add("masa_tampal", txtMasaTampal);	
		    		r.add("jenis_waktu", socJenisWaktu);	
		    		r.add("tempat_tampal", txtTempatTampal);	
		    		r.add("flag_jenis_bukti", flagJenisBukti);
		    		//r.add("id_jenisdokumen", socKodDokumen);
		    		r.add("jenis_serahan", socJenisSerah);
		    		r.add("flag_serah", socStatusSerah);
		    		r.add("nama_penerima", txtNamaTerima);
		    		r.add("no_kp_penerima", txtNoKP);
		    		r.add("catatan", txtCatatan);
		    		r.add("id_jenisnopb", socJenisNoKP);	    		
		    		r.add("tarikh_hantar",r.unquote(TS));	    			    		
		    		r.add("tarikh_masuk",r.unquote("sysdate"));
		    		r.add("id_masuk",id_user);    		
		    		sql = r.getSQLInsert("tblpptbuktipenyampaian");
		    		
		    		myLogger.info("INSERT NOTIS :"+sql);
		    		stmt.executeUpdate(sql);
	    	
		    }//close try 
		    finally {
		      //if (db != null) db.close();
		    }//close finally
		   
		}//close simpanPenyampaianNotis
		
		
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
				
			}
		}
		
		public static void hapusBuktiPenyampaian(String id_pihakberkepentingan, String flag_jenis_bukti,Db db) throws Exception {
			   
		    String sql = "";		   
		    try{
		    		Statement stmt = db.getStatement();				  
		    		sql = "DELETE FROM TBLPPTBUKTIPENYAMPAIAN where id_pihakberkepentingan = '"+id_pihakberkepentingan+"'  and flag_jenis_bukti = '"+flag_jenis_bukti+"'";
		    		stmt.executeUpdate(sql);
		    }
		    finally {		   
		    }
		  }//close hapus
		
		
		
		 Hashtable getBUKTI = null;
			public Hashtable getBUKTI(String id_pihakberkepentingan,String FLAG_JENIS_BUKTI,Db db) throws Exception {
				getBUKTI = new Hashtable();			
				//Db db = null;
				String sql = "";
				try {
					//db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					
					sql = " SELECT PX.ID_BUKTIPENYAMPAIAN,PX.NAMA_PENGHANTAR,PX.MASA_TAMPAL,PX.JENIS_WAKTU,PX.TEMPAT_TAMPAL, "+
						  " PX.FLAG_JENIS_BUKTI,PX.JENIS_SERAHAN,PX.FLAG_SERAH,PX.NAMA_PENERIMA,PX.NO_KP_PENERIMA,PX.CATATAN,PX.ID_JENISNOPB "+
						  " FROM TBLPPTBUKTIPENYAMPAIAN PX WHERE PX.ID_PIHAKBERKEPENTINGAN = '"+id_pihakberkepentingan+"' AND PX.FLAG_JENIS_BUKTI = '"+FLAG_JENIS_BUKTI+"' ";				
					ResultSet rs = stmt.executeQuery(sql);				
					Hashtable h;
					while (rs.next()){	
						h = new Hashtable();
						h.put("ID_BUKTIPENYAMPAIAN", rs.getString("ID_BUKTIPENYAMPAIAN")== null?"":rs.getString("ID_BUKTIPENYAMPAIAN"));
						h.put("NAMA_PENGHANTAR", rs.getString("NAMA_PENGHANTAR")== null?"":rs.getString("NAMA_PENGHANTAR"));
						h.put("MASA_TAMPAL", rs.getString("MASA_TAMPAL")== null?"":rs.getString("MASA_TAMPAL"));
						h.put("JENIS_WAKTU", rs.getString("JENIS_WAKTU")== null?"":rs.getString("JENIS_WAKTU"));
						h.put("TEMPAT_TAMPAL", rs.getString("TEMPAT_TAMPAL")== null?"":rs.getString("TEMPAT_TAMPAL"));
						h.put("FLAG_JENIS_BUKTI", rs.getString("FLAG_JENIS_BUKTI")== null?"":rs.getString("FLAG_JENIS_BUKTI"));
						h.put("JENIS_SERAHAN", rs.getString("JENIS_SERAHAN")== null?"":rs.getString("JENIS_SERAHAN"));
						h.put("FLAG_SERAH", rs.getString("FLAG_SERAH")== null?"":rs.getString("FLAG_SERAH"));
						h.put("NAMA_PENERIMA", rs.getString("NAMA_PENERIMA")== null?"":rs.getString("NAMA_PENERIMA"));
						h.put("NO_KP_PENERIMA", rs.getString("NO_KP_PENERIMA")== null?"":rs.getString("NO_KP_PENERIMA"));
						h.put("CATATAN", rs.getString("CATATAN")== null?"":rs.getString("CATATAN"));
						h.put("ID_JENISNOPB", rs.getString("ID_JENISNOPB")== null?"":rs.getString("ID_JENISNOPB"));
					}
					return getBUKTI;
				} finally {
					//if (db != null) db.close();
				}
			}
		
}
