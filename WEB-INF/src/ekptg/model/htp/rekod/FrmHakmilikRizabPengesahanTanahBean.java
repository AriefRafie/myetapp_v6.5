package ekptg.model.htp.rekod;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.Utils;
import ekptg.model.entities.Tblrujpejabatjkptg;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHtp;
import ekptg.model.htp.entity.HakMilik;
import ekptg.model.htp.entity.HtpPermohonan;
import ekptg.model.htp.entity.Permohonan;
import ekptg.model.htp.entity.PfdFail;

public class FrmHakmilikRizabPengesahanTanahBean implements IHakmilikRizabPengesahanTanah {
	
	private HakmilikInterface iHakmilik = null;
	private static Logger myLog = Logger.getLogger(ekptg.model.htp.rekod.FrmHakmilikRizabPengesahanTanahBean.class);
	private static Vector listCarianHakmilikDanRizab = null;
	private HakMilik hakmilik = null;
 	private IHtp iHTP = null;  
 	private FrmHakmilikRizabPengesahanBean iHakmilikRizabPengesahan = null;
 	private PfdFail pfdFail = null;
 	private Permohonan permohonan = null;
	private String sql = "";
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private	Db db = null;	
	HtpPermohonan htpPermohonan = null;


	//PAPAR CARIAN HAKMILIK DAN RIZAB
	@Override
	 public Hashtable<String, Comparable> getPerolehanInfo(String idHakmilik)throws Exception {
		 Db db = null;
		 String sql = "";
		 try {
			 db = new Db();
			 Statement stmt = db.getStatement();
			 @SuppressWarnings("unused")
			 SQLRenderer r = new SQLRenderer();
			 sql= " select n.nama_negeri,k.nama_kementerian,a.nama_agensi,s.nama_suburusan," +
			      " f.no_fail,to_char(f.tarikh_daftar_fail,'dd/mm/yyyy') tarikh_daftar_fail,h.no_rujukan_kjp,h.no_rujukan_lain," +
			      " to_char(p.tarikh_surat,'dd/mm/yyyy') tarikh_surat,to_char(p.tarikh_terima,'dd/mm/yyyy') tarikh_terima,p.tujuan,p.id_permohonan,f.id_fail," +
			      " k.id_kementerian,a.id_agensi,n.id_negeri,H.NO_RUJUKAN_PTD,H.NO_RUJUKAN_PTG,F.TAJUK_FAIL " +
			      " ,H.ID_DAERAH" +
			      " FROM " +
			      " tblrujnegeri n,Tblpfdfail f,tblrujkementerian k,tblpermohonan p,"+
			      " tblhtppermohonan h,tblrujagensi a,tblrujsuburusan s where "+
			      " f.ID_NEGERI=n.ID_NEGERI and f.id_kementerian=k.id_kementerian "+
			      " and p.ID_FAIL=f.ID_FAIL and p.id_permohonan=h.ID_PERMOHONAN  "+
			      " and h.ID_AGENSI=a.id_agensi and f.ID_SUBURUSAN=s.ID_SUBURUSAN  "+
			      " and p.id_permohonan = '"+idHakmilik+"'";

			 sql = getIHakmilik().getSQLHakmilik(idHakmilik);
			 //mylog.info("getPermohonanInfoV1:sql="+sql);
			      ResultSet rs = stmt.executeQuery(sql);
			      Hashtable h = new Hashtable();

			      while (rs.next()) {
		    		  if(rs.getString("ID_NEGERI")==null){
			    		  h.put("idnegeri","0");
			    	  }else{
			    		  h.put("idnegeri", rs.getString("ID_NEGERI"));	
			    	  }
			    	  h.put("negeri", Utils.isNull(rs.getString("NAMA_NEGERI"))); 
			    	  h.put("idkementerian", Utils.isNull(rs.getString("ID_KEMENTERIAN")));
			    	  h.put("kementerian", Utils.isNull(rs.getString("NAMA_KEMENTERIAN")));
		    		  h.put("idagensi", Utils.isNull(rs.getString("ID_AGENSI")));
		    		  h.put("agensi", Utils.isNull(rs.getString("NAMA_AGENSI")));
//	    		  		h.put("suburusan", Utils.isNull(rs.getString("nama_suburusan")));
		    		  h.put("fail", Utils.isNull(rs.getString("NO_FAIL")));
//			    	  if(rs.getString("tarikh_daftar_fail")==null){
//			    		  h.put("tdaftar",new Date());
//			    	  }else{
//			    		  h.put("tdaftar", rs.getString("tarikh_daftar_fail"));	
//			    		 }
		    		  h.put("rujukankjp", Utils.isNull(rs.getString("NO_FAIL_KJP")));
//		    		  h.put("rujukanlain", Utils.isNull(rs.getString("no_rujukan_lain")));
//			    	  if(rs.getString("tarikh_surat")==null){
//			    		  h.put("tsurat",new Date());
//			    	  }else{
//			    		  h.put("tsurat", rs.getString("tarikh_surat"));	
//			    	  }
//			    	  if(rs.getString("tarikh_terima")==null){
//			    		  h.put("rtterima",new Date());
//			    	  }else{
//			    		  h.put("rtterima", rs.getString("tarikh_terima"));	
//			    	  }
		    		  h.put("tujuan", Utils.isNull(rs.getString("TUJUAN")));
			    	  h.put("idpermohonan", rs.getString("ID_PERMOHONAN")==null ? "0" :rs.getString("ID_PERMOHONAN"));
			    	  h.put("idfail", rs.getString("ID_FAIL")==null ? "0" :rs.getString("ID_FAIL"));
		    		  h.put("noFailPTD", Utils.isNull(rs.getString("NO_FAIL_PTD")));
		    		  h.put("noFailPTG", Utils.isNull(rs.getString("NO_FAIL_PTG")));
			    	  h.put("idDaerah", rs.getString("ID_DAERAH")==null ? "0" :rs.getString("ID_DAERAH"));
		    		  h.put("tajukFail", Utils.isNull(rs.getString("TUJUAN")));
		    		  //
			    	  h.put("IdDaerahPermohonan", rs.getString("ID_DAERAH")==null ? "0" :rs.getString("ID_DAERAH"));
		    		  h.put("IdMukim",rs.getString("ID_MUKIM"));
		    		  h.put("IdLuas",rs.getString("ID_LUAS"));
		    		  h.put("idLuasLama",rs.getString("ID_LUAS"));
		    		  h.put("luasLama",rs.getString("LUAS"));
			    	  // FrmRekodPengesahanPerolehanTanah.viewHakmilikPengambilan 
		    		  h.put("noFailSeksyen", rs.getString("no_fail")==null ? "" :rs.getString("no_fail"));
			    	  //tajukFail
			    	  h.put("namaAgensi", rs.getString("NAMA_AGENSI")==null ? "" :rs.getString("NAMA_AGENSI"));
			    	  h.put("noFailKjp", rs.getString("NO_FAIL_KJP")==null ? "" :rs.getString("NO_FAIL_KJP"));
		    		  h.put("noFailPtg", Utils.isNull(rs.getString("NO_FAIL_PTG")));
			    	  h.put("idKementerian", rs.getString("ID_KEMENTERIAN")==null ? "" :rs.getString("ID_KEMENTERIAN"));
			    	  h.put("idAgensi", rs.getString("ID_AGENSI")==null ? "" :rs.getString("ID_AGENSI"));
			    	  h.put("idPermohonan", rs.getString("ID_PERMOHONAN")==null ? "" :rs.getString("ID_PERMOHONAN"));
		    		  h.put("IdNegeri", rs.getString("ID_NEGERI"));	
			    	  h.put("IdDaerah", rs.getString("ID_DAERAH")==null ? "0" :rs.getString("ID_DAERAH"));
			      }
			      return h;
			    } finally {
			      if (db != null) db.close();
			    }
	 }

	//PAPAR CARIAN HAKMILIK DAN RIZAB
	@Override
	 public Hashtable<String, Comparable> getPerolehanInfo(String idHakmilik,String idHakmilik1)throws Exception {
		 Db db = null;
		 String sql = "";
		 try {
			 db = new Db();
			 Statement stmt = db.getStatement();
			 @SuppressWarnings("unused")
			 SQLRenderer r = new SQLRenderer();
			 sql= " select n.nama_negeri,k.nama_kementerian,a.nama_agensi,s.nama_suburusan," +
			      " f.no_fail,to_char(f.tarikh_daftar_fail,'dd/mm/yyyy') tarikh_daftar_fail,h.no_rujukan_kjp,h.no_rujukan_lain," +
			      " to_char(p.tarikh_surat,'dd/mm/yyyy') tarikh_surat,to_char(p.tarikh_terima,'dd/mm/yyyy') tarikh_terima,p.tujuan,p.id_permohonan,f.id_fail," +
			      " k.id_kementerian,a.id_agensi,n.id_negeri,H.NO_RUJUKAN_PTD,H.NO_RUJUKAN_PTG,F.TAJUK_FAIL " +
			      " ,H.ID_DAERAH" +
			      " FROM " +
			      " tblrujnegeri n,Tblpfdfail f,tblrujkementerian k,tblpermohonan p,"+
			      " tblhtppermohonan h,tblrujagensi a,tblrujsuburusan s where "+
			      " f.ID_NEGERI=n.ID_NEGERI and f.id_kementerian=k.id_kementerian "+
			      " and p.ID_FAIL=f.ID_FAIL and p.id_permohonan=h.ID_PERMOHONAN  "+
			      " and h.ID_AGENSI=a.id_agensi and f.ID_SUBURUSAN=s.ID_SUBURUSAN  "+
			      " and p.id_permohonan = '"+idHakmilik+"'";

			 //sql = getIHakmilik().getSQLHakmilik(idHakmilik);
			 
			 sql = getSQLPengambilan(idHakmilik1);
			 //mylog.info("getPermohonanInfoV1:sql="+sql);
			      ResultSet rs = stmt.executeQuery(sql);
			      Hashtable h = new Hashtable();

			      while (rs.next()) {
		    		  if(rs.getString("ID_NEGERI")==null){
			    		  h.put("idnegeri","0");
			    	  }else{
			    		  h.put("idnegeri", rs.getString("ID_NEGERI"));	
			    	  }
			    	  h.put("negeri", Utils.isNull(rs.getString("NAMA_NEGERI"))); 
			    	  h.put("idkementerian", Utils.isNull(rs.getString("ID_KEMENTERIAN")));
			    	  h.put("kementerian", Utils.isNull(rs.getString("NAMA_KEMENTERIAN")));
		    		  h.put("idagensi", Utils.isNull(rs.getString("ID_AGENSI")));
		    		  h.put("agensi", Utils.isNull(rs.getString("NAMA_AGENSI")));
		    		  h.put("fail", Utils.isNull(rs.getString("NO_FAIL")));
		    		  h.put("rujukankjp", Utils.isNull(rs.getString("NO_FAIL_KJP")));
		    		  h.put("tujuan", Utils.isNull(rs.getString("TUJUAN")));
			    	  h.put("idpermohonan", rs.getString("ID_PERMOHONAN")==null ? "0" :rs.getString("ID_PERMOHONAN"));
			    	  h.put("idfail", rs.getString("ID_FAIL")==null ? "0" :rs.getString("ID_FAIL"));
		    		  h.put("noFailPTD", Utils.isNull(rs.getString("NO_FAIL_PTD")));
		    		  h.put("noFailPTG", Utils.isNull(rs.getString("NO_FAIL_PTG")));
			    	  h.put("idDaerah", rs.getString("ID_DAERAH")==null ? "0" :rs.getString("ID_DAERAH"));
		    		  h.put("tajukFail", Utils.isNull(rs.getString("TUJUAN")));
		    		  //
			    	  h.put("IdDaerahPermohonan", rs.getString("ID_DAERAH")==null ? "0" :rs.getString("ID_DAERAH"));
		    		  h.put("IdMukim",rs.getString("ID_MUKIM"));
		    		  h.put("IdLuas",rs.getString("ID_LUAS"));
		    		  h.put("idLuasLama",rs.getString("ID_LUAS"));
		    		  h.put("luasLama",rs.getString("LUAS"));
			    	  
		    		  // FrmRekodPengesahanPerolehanTanah.viewHakmilikPengambilan 
		    		  h.put("noFailSeksyen", rs.getString("no_fail")==null ? "" :rs.getString("no_fail"));
			    	  //tajukFail
			    	  h.put("namaAgensi", rs.getString("NAMA_AGENSI")==null ? "" :rs.getString("NAMA_AGENSI"));
			    	  h.put("noFailKjp", rs.getString("NO_FAIL_KJP")==null ? "" :rs.getString("NO_FAIL_KJP"));
		    		  h.put("noFailPtg", Utils.isNull(rs.getString("NO_FAIL_PTG")));
			    	  h.put("idKementerian", rs.getString("ID_KEMENTERIAN")==null ? "" :rs.getString("ID_KEMENTERIAN"));
			    	  h.put("idAgensi", rs.getString("ID_AGENSI")==null ? "" :rs.getString("ID_AGENSI"));
			    	  h.put("idPermohonan", rs.getString("ID_PERMOHONAN")==null ? "" :rs.getString("ID_PERMOHONAN"));
		    		  h.put("IdNegeri", rs.getString("ID_NEGERI"));	
			    	  h.put("IdDaerah", rs.getString("ID_DAERAH")==null ? "0" :rs.getString("ID_DAERAH"));
			    	  //h.put("IdJenisHakmilik", rs.getString("JENIS_HAKMILIK")==null ? "0" :rs.getString("JENIS_HAKMILIK"));
			    	  h.put("IdJenisHakmilik", rs.getString("ID_JENISHAKMILIK")==null ? "0" :rs.getString("ID_JENISHAKMILIK"));
			    	  h.put("NoLot", rs.getString("NO_LOT")==null ? "0" :rs.getString("NO_LOT"));
			    	  

			      }
			      return h;
			    } finally {
			      if (db != null) db.close();
			    }
	 }
	
	public String getSQLPengambilan(String idHakmilik){
		sql = ""+
		"SELECT F.ID_FAIL" +
		",F.NO_FAIL" +
		",F.ID_KEMENTERIAN" +
		" ,( SELECT NAMA_KEMENTERIAN FROM TBLRUJKEMENTERIAN RK WHERE ID_KEMENTERIAN =F.ID_KEMENTERIAN) NAMA_KEMENTERIAN " +
		" , PTP.ID_AGENSI "+
		" ,( SELECT NAMA_AGENSI FROM TBLRUJAGENSI RA WHERE ID_AGENSI = PTP.ID_AGENSI) NAMA_AGENSI" +
		",PTP.ID_PERMOHONAN,PTP.NO_RUJUKAN_SURAT NO_FAIL_KJP "+
		" ,RN.ID_NEGERI,RN.NAMA_NEGERI,RD.Id_Daerah,RD.NAMA_DAERAH,RM.Id_Mukim,RM.NAMA_MUKIM "+
		" ,RJH.Id_Jenishakmilik,RJH.Keterangan as HakKeterangan,RJH.KOD_JENIS_HAKMILIK JENIS_HAKMILIK "+
		" ,PTH.NO_HAKMILIK,PTH.ID_HAKMILIK "+
		" ,Case  "+
		"     WHEN PTH.NO_PT=null then PTH.NO_LOT "+
		"     WHEN PTH.NO_LOT=null then PTH.NO_PT "+
		"     else 'TIADA MAKLUMAT' "+
		" END NO_LOT "+
		" ,PTHA.ID_LOT " +
		" ,( SELECT KETERANGAN FROM TBLRUJLOT WHERE ID_LOT= PTHA.ID_LOT) JENIS_LOT "+
		//" --,lot.Keterangan as LotKeterangan
		" ,PTHA.NAMA_LUAS_ASAL LUAS "+
		" ,PTHA.ID_UNITLUASAMBIL ID_LUAS "+
		//--,PTHA.ID_UNITLUASLOT_CONVERT ID_LUAS
		" ,'PENGAMBILAN' SUMBER,'' NO_WARTA,'TIADA' STATUS_HAKMILIK" +
		" , PTP.TUJUAN,PTP.TUJUAN KEGUNAAN_TANAH, 'PENGAMBILAN' NAMA_URUSAN " +
		" ,'' NO_FAIL_PTG,'' NO_FAIL_PTD" +
		" FROM  "+
		" TBLPPTTEMPHAKMILIK PTH,TBLPPTHAKMILIK PTHA,TBLPPTPERMOHONAN PTP "+
		" ,TBLPFDFAIL F "+
		" ,TBLRUJNEGERI RN,TBLRUJDAERAH RD,TBLRUJMUKIM RM "+
		" ,TBLRUJJENISHAKMILIK RJH "+
		//--,TBLRUJLOT LOT
		//--,TBLRUJLUAS LUAS
		" WHERE  "+
		" PTH.ID_HAKMILIK_ASAL = PTHA.ID_HAKMILIK "+
		" AND PTP.ID_PERMOHONAN = PTHA.ID_PERMOHONAN "+
		" AND PTP.ID_FAIL = F.ID_FAIL "+
		" AND RN.ID_NEGERI= PTHA.ID_NEGERI  "+
		" AND RD.ID_DAERAH=PTHA.ID_DAERAH "+
		" AND RM.ID_MUKIM=PTHA.ID_MUKIM  "+
		" AND RJH.Id_Jenishakmilik=PTH.Id_Jenishakmilik " +
		"" ;
		if(idHakmilik.equals("")){
			sql +=" AND PTHA.ID_HAKMILIK = "+idHakmilik+ "";
		}
		sql+= "";
		return sql;
		
	}
	
	@Override
	public HtpPermohonan findPermohonan(String idPermohonan) throws Exception {
		Db db = null;
		Connection conn = null;
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			sql = "SELECT P.ID_PERMOHONAN FROM TBLPERMOHONAN P WHERE P.ID_FAIL=" +idPermohonan;
//	myLog.info("Permohonan:::findPermohonan::sql::"+sql);
											
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);												    
			if(rs.next()){
				//pfdFail = new PfdFail();
				permohonan = new Permohonan();
				htpPermohonan = new HtpPermohonan();
				permohonan.setIdPermohonan(rs.getString("ID_PERMOHONAN"));
				htpPermohonan.setPermohonan(permohonan);
				conn.commit();
			}
										    	
		 } finally {
		      if (db != null) db.close();
		    }
		
		return htpPermohonan;
	}
	
	  public String simpanPermohonanHTP(Hashtable<?, ?> data) throws Exception {
		    Db db = null;
		    String sql = "";
		    try{
		    			/*ID_PERMOHONAN	1	1	N	NUMBER		Yes		
		    			ID_FAIL	2		N	NUMBER		Yes		
		    			ID_PEJABATJKPTG	3		N	NUMBER		Yes		
		    			NO_PERMOHONAN	4		Y	VARCHAR2 (30 Byte)		Yes		
		    			NO_PERSERAHAN	5		Y	VARCHAR2 (50 Byte)		Yes		
		    			TARIKH_SURAT	6		Y	DATE		Yes		
		    			TARIKH_TERIMA	7		Y	DATE		Yes		
		    			TUJUAN	8		Y	VARCHAR2 (300 Byte)		Yes		
		    			ID_MASUK	9		Y	NUMBER		Yes		
		    			TARIKH_MASUK	10		Y	DATE		Yes		
		    			ID_KEMASKINI	11		Y	NUMBER		Yes		
		    			TARIKH_KEMASKINI	12		Y	DATE		Yes		
		    			 */
		    	Tblrujpejabatjkptg pejabat = null;
		    	Hashtable<String, String> userInfo = null;

		    	long idPermohonan = DB.getNextID("TBLPERMOHONAN_SEQ");
		    	String idFail = (String)data.get("id_Fail");		    	
			    String idMasuk = (String)data.get("id_Masuk");
		    	//userInfo = getUserInternalInfo(idMasuk);
		    	//pejabat = DB.getPejabatJKPTG(userInfo.get("IdSeksyen"),userInfo.get("IdNegeri"),userInfo.get("IdDaerah"));		      
		    	Long idJKPTG = 3L;
		    	String noPermohonan = (String)data.get("no_Permohonan");
		    	String noPerserahan = (String)data.get("no_Perserahan");    	
		    	String tarikhSuratKJP = "to_date('" + (String)data.get("tarikh_SuratKJP") + "','dd/MM/yyyy')";
		    	String tarikhTerima = "to_date('" + (String)data.get("tarikh_Terima") + "','dd/MM/yyyy')";
	          	String tujuan = (String)data.get("tajuk");
	          	//idMasuk dah declare di atas     	
	          	String tarikhMasuk = "to_date('" + (String)data.get("tarikh_Masuk") + "','dd/MM/yyyy')";
	          	
	          	// TBLHTPPERMOHONAN
	          	/*	ID_HTPPERMOHONAN	1	1	N	NUMBER		Yes		
	          			ID_PERMOHONAN	2		Y	NUMBER		Yes		
	          			ID_AGENSI	3		Y	NUMBER		Yes		
	          			ID_JENISTANAH	4		Y	VARCHAR2 (1000 Byte)		Yes		
	          			ID_PEGAWAI	5		N	NUMBER		Yes		
	          			NO_RUJUKAN_KJP	6		Y	VARCHAR2 (50 Byte)		Yes		
	          			NO_RUJUKAN_LAIN	7		Y	VARCHAR2 (50 Byte)		Yes		
	          			TARIKH_AGIHAN	8		Y	DATE		Yes		
	          			ID_MASUK	9		Y	NUMBER		Yes		
	          			TARIKH_MASUK	10		Y	DATE		Yes		
	          			ID_KEMASKINI	11		Y	NUMBER		Yes		
	          			TARIKH_KEMASKINI	12		Y	DATE		Yes		
	          	 */
	          	long idHtppermohonan = DB.getNextID("TBLHTPPERMOHONAN_SEQ");
	          	int idAgensi = (Integer)data.get("id_Agensi");
	          	int idJenistanah = (Integer)data.get("id_Jenistanah");;
	          	//int idPegawai = (Integer)data.get("id_Pegawai");
	          
	          	String noFailkjp = (String)data.get("no_Failkjp");
	          	String noFaillain = (String)data.get("no_Faillain");
	          	String tarikhAgihan = "to_date('" + (String)data.get("tarikh_Agihan") + "','dd/MM/yyyy')";
	          	//id_masuk guna maklumat di atas
	          	//tarikh_masuk guna maklumat di atas
	          	//id_kemaskini guna maklumat di atas
	          	//tarikh_kemaskini guna maklumat di atas

	          	db = new Db();
	          	Statement stmt = db.getStatement();
	          	SQLRenderer r = new SQLRenderer();
	          	r.add("id_Permohonan", r.unquote(""+idPermohonan));
	          	r.add("id_Fail",idFail);
	          	r.add("id_Jkptg",idJKPTG);
	          	r.add("no_Permohonan",noPermohonan);
	          	r.add("no_Perserahan",noPerserahan);
	          	r.add("tarikh_Surat", r.unquote(tarikhSuratKJP));
	          	//tarikh_Terima juga utk tarikh permohonan
	          	r.add("tarikh_Terima", r.unquote(tarikhTerima));
	          	r.add("tujuan",tujuan);
	          	r.add("id_Masuk",idMasuk);
	          	r.add("tarikh_Masuk",r.unquote("SYSDATE"));
	          	r.add("id_Kemaskini",idMasuk);
	          	r.add("tarikh_Kemaskini",r.unquote("SYSDATE"));
	          	sql = r.getSQLInsert("Tblpermohonan");
	          	myLog.info("FrmUtilData::simpanPermohonanHTP::TBLPERMOHONAN = "+sql);
	          	stmt.executeUpdate(sql);
		      
	          	Statement stmtHtp = db.getStatement();
	          	SQLRenderer rHtp = new SQLRenderer();
	          	rHtp.add("id_Htppermohonan",idHtppermohonan);
	          	rHtp.add("id_Permohonan", idPermohonan);
	          	rHtp.add("id_Agensi", idAgensi);
	          	rHtp.add("id_Jenistanah", idJenistanah);
	          	rHtp.add("id_Pegawai", idMasuk);
	          	rHtp.add("no_Rujukan_Kjp", noFailkjp);
	          	rHtp.add("no_Rujukan_Lain", noFaillain);
	          	rHtp.add("tarikh_Agihan", rHtp.unquote(tarikhAgihan));
	          	rHtp.add("id_Masuk",idMasuk);
	          	rHtp.add("tarikh_Masuk",rHtp.unquote("SYSDATE"));
	          	rHtp.add("id_Kemaskini",idMasuk);
	          	rHtp.add("tarikh_Kemaskini",rHtp.unquote("SYSDATE"));
	          	sql = rHtp.getSQLInsert("Tblhtppermohonan");
	          	myLog.info("FrmUtilData::simpanPermohonanHTP::TBLHTPPERMOHONAN = "+sql);
	          	//stmtHtp.executeUpdate(sql);
		      
		      //StatusChange_Action(idPermohonan, idSuburusan);
		      
		      return ""+idPermohonan;
		    }
		    finally {
		      if (db != null) db.close();
		    }
		  }
	
	private IHtp getIHTP(){
		if(iHTP== null)
			iHTP = new HtpBean();
		return iHTP;
	}
	
	private HakmilikInterface getIHakmilik(){
		if (iHakmilik==null){
			iHakmilik=new HakmilikBean();
		}
		return iHakmilik;
	}		
	
	private IHakmilikRizab getIHakmilikRizabPengesahan(){
		if (iHakmilikRizabPengesahan == null){
			iHakmilikRizabPengesahan = new FrmHakmilikRizabPengesahanBean();
		}
		return iHakmilikRizabPengesahan;
	}
	

	
}
