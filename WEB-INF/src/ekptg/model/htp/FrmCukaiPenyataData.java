package ekptg.model.htp;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.util.Util;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.Utils;

public class FrmCukaiPenyataData {
	private static Vector list = new Vector();
	private static Vector list2 = new Vector();
	static Logger myLog = Logger.getLogger(FrmCukaiPenyataData.class);

	public static Vector getAmaunCukai(String id_daerah, int idPeringkatbayaran)throws Exception {
		Db db = null;
		list2.clear();
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
				sql = "SELECT A.JUMLAH_CUKAI,A.ID_DAERAH,A.ID_PERINGKATBAYARAN"
						+ " FROM TBLHTPCUKAIUTAMA A"
						+ " WHERE A.ID_DAERAH =" +id_daerah
						+ " AND A.ID_PERINGKATBAYARAN=" +idPeringkatbayaran;
			
			ResultSet rs = stmt.executeQuery(sql);
//			System.out.println("FrmCukaiPenyataData::getAmaunCukai::"+sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("jumlahCukai", rs.getString("JUMLAH_CUKAI") == null ? "" : Util.formatDecimal(rs.getDouble("JUMLAH_CUKAI")));
				h.put("idDaerah", rs.getString("ID_DAERAH") == null ? "" : rs.getString("ID_DAERAH"));
				h.put("idPeringkatbayaran", rs.getString("ID_PERINGKATBAYARAN") == null ? "" : rs.getString("ID_PERINGKATBAYARAN"));
				list2.addElement(h);
			}
			return list2;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public static Vector getListPenyata(int idNegeri)throws Exception {
		Db db = null;
		list.clear();
		String sql = "";
					
		try {
			db = new Db();
			      			      				
			sql ="SELECT TPH.ID_DAERAH,SUM(b.CUKAI_TERKINI) AS CUKAITERKINI, SUM(b.DENDA) AS DENDA,c.NAMA_DAERAH,"+
				" count(TPH.ID_DAERAH) as TOTALMUKIM,sum(TPCP.CUKAI_KENA_BAYAR) CUKAI_KENA_BAYAR "+
				" FROM TBLHTPHAKMILIK TPH,TBLHTPHAKMILIKCUKAI b,TBLRUJDAERAH C,TBLHTPCUKAITERPERINCI TPCP "+
				" WHERE TPH.ID_NEGERI='"+idNegeri+"' AND b.ID_HAKMILIK = TPH.ID_HAKMILIK "+
				" AND TPH.ID_DAERAH = c.ID_DAERAH "+
				" AND B.ID_HAKMILIKCUKAI = TPCP.ID_HAKMILIKCUKAI "+
				" GROUP BY TPH.ID_DAERAH,c.NAMA_DAERAH" +
				" ORDER BY TPH.ID_DAERAH ";			
			 System.out.println("FrmCukaiPenyataData::getListPenyata::"+sql);
			      
			 Statement stmt = db.getStatement();
			 ResultSet rs = stmt.executeQuery(sql);
			 Hashtable h;
			 while (rs.next()) {
				 h = new Hashtable();				
				 Double d = 0.00;
				 Double c = 0.00;
				 h.put("nama_daerah", rs.getString("NAMA_DAERAH"));
				 h.put("idDaerah", rs.getString("ID_DAERAH"));
				 h.put("sumDenda", rs.getDouble("DENDA"));
				 h.put("sumIdHakmilik", rs.getInt("TOTALMUKIM"));
				 h.put("sumCukai_", rs.getDouble("CUKAITERKINI"));
				 h.put("sumCukai", rs.getDouble("CUKAI_KENA_BAYAR"));
				 /*h.put("sumBayaran_lain", rs.getString("sumLain2") == null || "0".equals(rs.getString("sumLain2").toString()) ? "0.00":Util.formatDecimal(rs.getDouble("sumLain2")));
					h.put("tungakan", rs.getString("sumtungakan") == null || "0".equals(rs.getString("sumtungakan").toString()) ? "0.00":Util.formatDecimal(rs.getDouble("sumtungakan")));
			  	if((rs.getString("sumPengurangan") != null) || (rs.getString("sumPengecualian") != null)){
			    	Double a = Double.parseDouble(rs.getString("sumDenda"));
			    	Double b = Double.parseDouble(rs.getString("sumPerluBayar"));
			    	c = Double.parseDouble(rs.getString("sumPengurangan")==null?"0":rs.getString("sumPengurangan"));
			    	d = Double.parseDouble(rs.getString("sumPengecualian")==null?"0":rs.getString("sumPengecualian"));			    		  
			    	Double e = a+b;
			    	Double f = c+d;
			    	Double g = e-f;
			    	h.put("lebihan", g);
			    }else{
			    	h.put("lebihan", "0.00");
			    }
				  	h.put("sumIdHakmilik", rs.getString("sumIdDaerah")==null?"0":rs.getInt("sumIdDaerah"));
				  	h.put("sumAllHakmilik", rs.getString("sumIdDaerah")==null?"0":rs.getInt("sumIdDaerah"));
			    	h.put("sumCukai", rs.getString("sumPerluBayar")==null?"0":Util.formatDecimal(rs.getDouble("sumPerluBayar")));
			    	h.put("sumAllCukai", rs.getString("sumPerluBayar")==null?"0.00":rs.getDouble("sumPerluBayar"));*/
			    
				 list.addElement(h);
			 }

			return list;
			
		}finally {
			if (db != null) db.close();
		}
	}
	
	public static Vector<Hashtable<String,String>> getList(String idUrusan, String carian, String noFail, Long idNegeri) throws Exception {
		Db db = null;
		list.clear();
		String sql = "";
		if (idNegeri == 20)
			idNegeri = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT distinct f.id_fail, f.no_fail, f.tajuk_fail, s.keterangan, n.nama_negeri," +
				" n.kod_mampu,p.id_permohonan, f.id_negeri"+
				",'0' id_peringkatbayaran,'-1' peringkat_bayaran " +
//				",NVL(pb.id_peringkatbayaran,0) id_peringkatbayaran,pb.peringkat_bayaran " +
				" FROM " +
				" tblpfdfail f, tblpermohonan p, tblrujsuburusanstatusfail sf, tblrujsuburusanstatus ss, " +
				" tblrujstatus s, tblrujnegeri n, tblhtpperingkatbayaran pb "+
				" WHERE f.id_fail = p.id_fail AND f.id_status <> 999 AND p.id_permohonan = sf.id_permohonan AND n.id_negeri = f.id_negeri "+
				" AND sf.id_suburusanstatus = ss.id_suburusanstatus AND ss.id_status = s.id_status "+
				" AND pb.id_permohonan(+) = p.id_permohonan "+
				" AND sf.aktif = '1' AND f.id_urusan = " + idUrusan +
				" AND f.tajuk_fail LIKE '%" + carian + "%' "+
				" AND f.no_fail LIKE '%" + noFail + "%' ";
			if (idNegeri != null)
				sql += "AND f.id_negeri = " + idNegeri;
			sql += " ORDER BY n.kod_mampu";
		
			ResultSet rs = stmt.executeQuery(sql);
//			mylog.info("getList:sql="+sql);
			Vector<Hashtable<String,String>> list = new Vector<Hashtable<String,String>>();
			Hashtable<String,String> h;
//			int bil = 1;
			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("idPermohonan", rs.getString("id_Permohonan"));
				h.put("idFail", rs.getString("id_Fail"));
				h.put("idNegeri", rs.getString("id_Negeri"));
				h.put("noFail", rs.getString("no_Fail"));
				h.put("tajuk", rs.getString("tajuk_Fail"));
				h.put("negeri", rs.getString("nama_Negeri"));
				h.put("keterangan", rs.getString("keterangan"));
				h.put("kodMampu", rs.getString("kod_Mampu"));
				h.put("peringkatBayaran", rs.getString("peringkat_bayaran")== null? "-1":rs.getString("peringkat_bayaran"));
				h.put("idPeringkatBayaran", rs.getString("id_peringkatbayaran")== null? "0":rs.getString("id_peringkatbayaran"));
				//h.put("jumlahHakmilik", rs.getString("JUMLAH_HAKMILIK")== null? "0":rs.getString("JUMLAH_HAKMILIK"));
				//h.put("jumlahCukai", rs.getString("JUMLAH_CUKAI")== null? "0.0":rs.getDouble("JUMLAH_CUKAI"));
				list.addElement(h);
//				bil++;
				
			}
			return list;
			
		} finally {
			if (db != null)	db.close();
			
		}
		
	}
	
	public static Vector getListDefault(String idUrusan, String carian, String noFail, Long idNegeri) throws Exception {
		Db db = null;
		list.clear();
		String sql = "";
		if (idNegeri == 20)
			idNegeri = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT distinct f.id_Fail, f.no_Fail, f.tajuk_Fail, s.keterangan, n.nama_Negeri," +
				" n.kod_Mampu,p.id_Permohonan, f.id_Negeri,pb.id_peringkatbayaran,pb.peringkat_bayaran "+
				" FROM " +
				" Tblpfdfail f, Tblpermohonan p, Tblrujsuburusanstatusfail sf, Tblrujsuburusanstatus ss, " +
				" Tblrujstatus s, Tblrujnegeri n,tblhtpperingkatbayaran pb "+
				" WHERE f.id_Fail = p.id_Fail AND F.ID_STATUS <> 999 AND p.id_Permohonan = sf.id_Permohonan AND n.id_Negeri = f.id_Negeri "+
				" AND sf.id_Suburusanstatus = ss.id_Suburusanstatus AND ss.id_Status = s.id_Status "+
				" AND pb.ID_PERMOHONAN(+) = p.ID_PERMOHONAN " +
				" AND SS.LANGKAH<>99 "+
				" AND sf.aktif = '1' AND f.id_Urusan = " + idUrusan +
				" AND f.tajuk_Fail LIKE '%" + carian + "%' "+
				" AND f.no_Fail LIKE '%" + noFail + "%' ";
			if (idNegeri != null)
				sql += "AND f.id_Negeri = " + idNegeri;
			sql += " ORDER BY n.kod_Mampu";
		
			ResultSet rs = stmt.executeQuery(sql);
			myLog.info("FrmCukaiPenyataData::getList::"+sql);

			Vector list = new Vector();
			Hashtable h;
			int bil = 1;

			while (rs.next()) {
				h = new Hashtable();
				h.put("idPermohonan", rs.getString("id_Permohonan"));
				h.put("idFail", rs.getString("id_Fail"));
				h.put("idNegeri", rs.getString("id_Negeri"));
				h.put("noFail", rs.getString("no_Fail"));
				h.put("tajuk", rs.getString("tajuk_Fail"));
				h.put("negeri", rs.getString("nama_Negeri"));
				h.put("keterangan", rs.getString("keterangan"));
				h.put("kodMampu", rs.getString("kod_Mampu"));
				h.put("peringkatBayaran", rs.getString("peringkat_bayaran")== null? "TIADA":rs.getString("peringkat_bayaran"));
				h.put("idPeringkatBayaran", rs.getString("id_peringkatbayaran")== null? "0":rs.getString("id_peringkatbayaran"));
				list.addElement(h);
				bil++;
			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public static Hashtable getPermohonanInfo(int idpermohonan)throws Exception {
	    Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      sql= " select n.nama_negeri,k.nama_kementerian,a.nama_agensi,s.nama_suburusan," +
	      " f.no_fail,f.tarikh_daftar_fail,h.no_rujukan_kjp,h.no_rujukan_lain," +
	      " p.tarikh_surat,p.tarikh_terima,p.tujuan,p.id_permohonan,f.id_fail," +
	      " k.id_kementerian,a.id_agensi,n.id_negeri FROM " +
	      " tblrujnegeri n,Tblpfdfail f,tblrujkementerian k,tblpermohonan p,"+
	      " tblhtppermohonan h,tblrujagensi a,tblrujsuburusan s where "+
	      " f.ID_NEGERI=n.ID_NEGERI and f.id_kementerian=k.id_kementerian "+
	      " and p.ID_FAIL=f.ID_FAIL and p.id_permohonan=h.ID_PERMOHONAN  "+
	      " and h.ID_AGENSI=a.id_agensi and f.ID_SUBURUSAN=s.ID_SUBURUSAN  "+
	      " and p.id_permohonan = "+idpermohonan;
	      ResultSet rs = stmt.executeQuery(sql);
	      Hashtable h = new Hashtable();

	      while (rs.next()) {
	    	  if(rs.getString("nama_negeri")==null){
	    		  h.put("negeri", "");
	      		}else{
	      			h.put("negeri", rs.getString("nama_negeri")); }
	    	  if(rs.getString("nama_kementerian")==null){
	    		  h.put("kementerian", "");
	    	  }else{
	    		  h.put("kementerian", rs.getString("nama_kementerian"));}
	    	  if(rs.getString("nama_agensi")==null){
	    		  h.put("agensi", "");
	    	  }else {
	    		  h.put("agensi", rs.getString("nama_agensi"));
	    	  }
	    	  if(rs.getString("nama_suburusan")==null){
	    		  h.put("suburusan", "");
	    	  }else{
	    		  h.put("suburusan", rs.getString("nama_suburusan"));
	    	  }
	    	  if(rs.getString("no_fail")==null){
	    		  h.put("fail", "");
	    	  }else{
	    		  h.put("fail", rs.getString("no_fail"));
	    	  }
	    	  if(rs.getDate("tarikh_daftar_fail")==null){
	    		  h.put("tdaftar",new Date());
	    	  }else{
	    		  h.put("tdaftar", rs.getDate("tarikh_daftar_fail"));
	    	  }
	    	  if(rs.getString("no_rujukan_kjp")==null){
	    		  h.put("rujukankjp", "");
	    	  }else{
	    		  h.put("rujukankjp", rs.getString("no_rujukan_kjp"));
	    	  }
	    	  if(rs.getString("no_rujukan_lain")==null){
	    		  h.put("rujukanlain", "");
	    	  }else{
	    		  h.put("rujukanlain", rs.getString("no_rujukan_lain"));
	    	  }
	    	  if(rs.getDate("tarikh_surat")==null){
	    		  h.put("tsurat",new Date());
	    	  }else{
	    		  h.put("tsurat", rs.getDate("tarikh_surat"));
	    	  }
	    	  if(rs.getDate("tarikh_terima")==null){
	    		  h.put("rtterima",new Date());
	    	  }else{
	    		  h.put("rtterima", rs.getDate("tarikh_terima"));
	    	  }
	    	  if(rs.getString("tujuan")==null){
	    		  h.put("tujuan", "");
	    	  }else{
	    		  h.put("tujuan", rs.getString("tujuan"));
	    	  }
	    	  if(rs.getString("id_permohonan")==null){
	    		  h.put("idpermohonan", "");
	    	  }else{
	    		  h.put("idpermohonan", rs.getString("id_permohonan"));
	    	  }
	    	  if(rs.getString("id_fail")==null){
	    		  h.put("idfail", "");
	    	  }else{
	    		  h.put("idfail", rs.getString("id_fail"));
	    	  }	 
    		  h.put("idagensi", rs.getString("id_agensi"));
    		  h.put("idkementerian", rs.getString("id_kementerian"));
    		  h.put("idnegeri", rs.getString("id_negeri"));
	      }
	      return h;
	    } finally {
	      if (db != null) db.close();
	    }
	  }
	
	public static String simpanPeringkatBayarInteger(Hashtable data) throws Exception {
		Db db = null;
	    String sql = "";
	    try{
	    	db = new Db();
	    	long idPeringkatbayaran = DB.getNextID("TBLHTPPERINGKATBAYARAN_SEQ");	     
	    	int idNegeri = (Integer)data.get("idNegeri");
	    	int tahun_cukai = (Integer)data.get("tahun_cukai");
	    	String peringkat_bayaran = (String)data.get("peringkat_bayaran");
	    	//int idPermohonan = (Integer)data.get("idpermohonan");
	    	String idPermohonan = String.valueOf(data.get("idpermohonan"));
	    	String idMasuk = String.valueOf(data.get("idMasuk"));

	    	Statement stmtPeringkatBayar = db.getStatement();
	    	SQLRenderer rPB = new SQLRenderer();
	    	rPB.add("id_peringkatbayaran", idPeringkatbayaran);
	    	rPB.add("id_permohonan", idPermohonan);
	    	rPB.add("id_negeri", idNegeri);
	    	rPB.add("tahun_cukai", tahun_cukai);
	    	rPB.add("peringkat_bayaran", peringkat_bayaran);
	    	rPB.add("ID_MASUK", idMasuk);
	    	rPB.add("TARIKH_MASUK", rPB.unquote("sysdate"));
	    	rPB.add("ID_KEMASKINI", idMasuk);
	    	rPB.add("TARIKH_KEMASKINI", rPB.unquote("sysdate"));
	    	
	    	sql = rPB.getSQLInsert("tblhtpperingkatbayaran");
	    	myLog.info("FrmCukaiPenyataData:simpanPeringkatBayar::sql ::: "+sql);
	    	stmtPeringkatBayar.executeUpdate(sql);	 

	    	return String.valueOf(idPeringkatbayaran);
	    
	    }finally {
	      if (db != null) db.close();
	    }
	  
	}
	public static String simpanPeringkatBayar(Hashtable<String,String> data) throws Exception {
		Db db = null;
	    String sql = "";
	    try{
	    	db = new Db();
	    	long idPeringkatbayaran = DB.getNextID("TBLHTPPERINGKATBAYARAN_SEQ");	     
	    	int idNegeri = Integer.parseInt(String.valueOf(data.get("idNegeri")));
	    	int tahun_cukai = Integer.parseInt(String.valueOf(data.get("tahun_cukai")));
	    	String peringkat_bayaran = String.valueOf(data.get("peringkat_bayaran"));
	    	//int idPermohonan = (Integer)data.get("idpermohonan");
	    	String idPermohonan = String.valueOf(data.get("idpermohonan"));
	    	String idMasuk = String.valueOf(data.get("idMasuk"));

	    	Statement stmtPeringkatBayar = db.getStatement();
	    	SQLRenderer rPB = new SQLRenderer();
	    	rPB.add("id_peringkatbayaran", idPeringkatbayaran);
	    	rPB.add("id_permohonan", idPermohonan);
	    	rPB.add("id_negeri", idNegeri);
	    	rPB.add("tahun_cukai", tahun_cukai);
	    	rPB.add("peringkat_bayaran", peringkat_bayaran);
	    	rPB.add("ID_MASUK", idMasuk);
	    	rPB.add("TARIKH_MASUK", rPB.unquote("sysdate"));
	    	rPB.add("ID_KEMASKINI", idMasuk);
	    	rPB.add("TARIKH_KEMASKINI", rPB.unquote("sysdate"));
	    	
	    	sql = rPB.getSQLInsert("tblhtpperingkatbayaran");
	    	myLog.info("FrmCukaiPenyataData:simpanPeringkatBayar::sql ::: "+sql);
	    	stmtPeringkatBayar.executeUpdate(sql);	 

	    	return String.valueOf(idPeringkatbayaran);
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }

	public static int simpanCukaiUtamaInteger(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		try
		{
			db = new Db();     
			Double jum_cukai = Double.parseDouble(Utils.RemoveSymbol(data.get("jum_cukai").toString()));
			int jum_hakmilik = (Integer)data.get("jum_hakmilik");
			String idPeringkat = String.valueOf(data.get("idPeringkat"));
			int idCukaiUtama = (Integer)(data.get("idCukaiUtama"));
			//String idCukaiUtama = String.valueOf(data.get("idCukaiUtama"));
			int tahun = (Integer)data.get("tahun");
			int idNegeri = (Integer)data.get("idNegeri");
 			String idDaerah = (String)data.get("idDaerah");
	    	long id_CukaiUtama = DB.getNextID("TBLHTPCUKAIUTAMA_SEQ");	     
	    	String idMasuk = String.valueOf(data.get("idMasuk"));

			Statement stmtCukaiUtama = db.getStatement();
			SQLRenderer rCU = new SQLRenderer();
			rCU.add("id_cukaiutama", id_CukaiUtama);
			rCU.add("jumlah_cukai", jum_cukai);
			rCU.add("jumlah_hakmilik", jum_hakmilik);
			rCU.add("id_peringkatbayaran", idPeringkat);
			rCU.add("tahun", tahun);		
			rCU.add("id_negeri", idNegeri);
			rCU.add("id_daerah", idDaerah);
			rCU.add("ID_MASUK", idMasuk);
			rCU.add("TARIKH_MASUK", rCU.unquote("sysdate"));
			rCU.add("ID_KEMASKINI", idMasuk);
			rCU.add("TARIKH_KEMASKINI", rCU.unquote("sysdate"));
	    	
			sql = rCU.getSQLInsert("Tblhtpcukaiutama");
			System.out.println("FrmCukaiPenyataData::Insert::Tblhtpcukaiutama = "+sql);
			stmtCukaiUtama.executeUpdate(sql);	
    
      	return (int)idCukaiUtama;
		}
		finally {
			if (db != null) db.close();
		}
	}
	
	public static String simpanCukaiUtama(Hashtable<String,String> data) throws Exception {
		Db db = null;
		String sql = "";
		try{
			db = new Db();     
			Double jum_cukai = Double.parseDouble(Utils.RemoveSymbol(data.get("jum_cukai").toString()));
			int jum_hakmilik = Integer.parseInt( String.valueOf(data.get("jum_hakmilik")));
			String idPeringkat = String.valueOf(data.get("idPeringkat"));
			//String idCukaiUtama = String.valueOf(data.get("idCukaiUtama"));
			int tahun = Integer.parseInt( String.valueOf(data.get("tahun")));
			int idNegeri = Integer.parseInt( String.valueOf(data.get("idNegeri")));
	    	long id_CukaiUtama = DB.getNextID("TBLHTPCUKAIUTAMA_SEQ");	     
 			String idDaerah = String.valueOf(data.get("idDaerah"));
	    	String idMasuk = String.valueOf(data.get("idMasuk"));

			Statement stmtCukaiUtama = db.getStatement();
			SQLRenderer rCU = new SQLRenderer();
			rCU.add("id_cukaiutama", id_CukaiUtama);
			rCU.add("jumlah_cukai", jum_cukai);
			rCU.add("jumlah_hakmilik", jum_hakmilik);
			rCU.add("id_peringkatbayaran", idPeringkat);
			rCU.add("tahun", tahun);		
			rCU.add("id_negeri", idNegeri);
			rCU.add("id_daerah", idDaerah);
			rCU.add("ID_MASUK", idMasuk);
			rCU.add("TARIKH_MASUK", rCU.unquote("sysdate"));
			rCU.add("ID_KEMASKINI", idMasuk);
			rCU.add("TARIKH_KEMASKINI", rCU.unquote("sysdate"));	    	
			sql = rCU.getSQLInsert("tblhtpcukaiutama");
			myLog.info("FrmCukaiPenyataData::Insert::Tblhtpcukaiutama = "+sql);
			stmtCukaiUtama.executeUpdate(sql);	    
			return String.valueOf(id_CukaiUtama);
      	
		}finally {
			if (db != null) db.close();
			
		}
	}

	public static String updatePeringkatBayarInteger(Hashtable data) throws Exception {
		Db db = null;
	    String sql = "";
	    try{
	    	db = new Db();
	    	String idPeringkatbayaran = String.valueOf(data.get("idPeringkatbayaran"));
	    	int idNegeri = (Integer)data.get("idNegeri");
	    	int tahun_cukai = (Integer)data.get("tahun_cukai");
	    	String peringkat_bayaran = (String)data.get("peringkat_bayaran");
	    	String idPermohonan = String.valueOf(data.get("idpermohonan"));
	    	String idKemaskini = String.valueOf(data.get("idKemaskini"));

	    	Statement stmtPeringkatBayar = db.getStatement();
	    	SQLRenderer rPB = new SQLRenderer();
	    	rPB.update("id_peringkatbayaran", idPeringkatbayaran);
	    	rPB.add("id_permohonan", idPermohonan);
	    	rPB.add("id_negeri", idNegeri);
	    	rPB.add("tahun_cukai", tahun_cukai);
	    	rPB.add("peringkat_bayaran", peringkat_bayaran);
	    	rPB.add("ID_KEMASKINI", idKemaskini);
	    	rPB.add("TARIKH_KEMASKINI", rPB.unquote("sysdate"));
	    	
	    	sql = rPB.getSQLUpdate("Tblhtpperingkatbayaran");
	    	System.out.println("FrmCukaiPenyataData:updatePeringkatBayar::sql="+sql);
	    	stmtPeringkatBayar.executeUpdate(sql);	 

	    	return idPeringkatbayaran;
	    	
	    }finally {
	      if (db != null) db.close();
	    }
	    
	    
	  }
	
	public static String updatePeringkatBayar(Hashtable data) throws Exception {
		Db db = null;
	    String sql = "";
	    try{
	    	db = new Db();
	    	String idPeringkatbayaran = String.valueOf(data.get("idPeringkatbayaran"));
	    	int idNegeri = (Integer)data.get("idNegeri");
	    	int tahun_cukai = (Integer)data.get("tahun_cukai");
	    	String peringkat_bayaran = (String)data.get("peringkat_bayaran");
	    	String idPermohonan = String.valueOf(data.get("idpermohonan"));
	    	String idKemaskini = String.valueOf(data.get("idKemaskini"));

	    	Statement stmtPeringkatBayar = db.getStatement();
	    	SQLRenderer rPB = new SQLRenderer();
	    	rPB.update("id_peringkatbayaran", rPB.unquote(idPeringkatbayaran));
	    	rPB.add("id_permohonan", idPermohonan);
	    	rPB.add("id_negeri", idNegeri);
	    	rPB.add("tahun_cukai", tahun_cukai);
	    	rPB.add("peringkat_bayaran", peringkat_bayaran);
	    	rPB.add("ID_KEMASKINI", idKemaskini);
	    	rPB.add("TARIKH_KEMASKINI", rPB.unquote("sysdate"));
	    	
	    	sql = rPB.getSQLUpdate("Tblhtpperingkatbayaran");
	    	//System.out.println("FrmCukaiPenyataData:updatePeringkatBayar::sql="+sql);
	    	stmtPeringkatBayar.executeUpdate(sql);	 

	    	return String.valueOf(idPeringkatbayaran);
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }
	

	public static String updateCukaiUtama(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		try{
			db = new Db();
			Double jum_cukai = Double.parseDouble(Utils.RemoveSymbol(data.get("jum_cukai").toString()));
			int jum_hakmilik = (Integer)data.get("jum_hakmilik");
			String idPeringkat = String.valueOf(data.get("idPeringkat"));
			//String idCukaiUtama = String.valueOf(data.get("idCukaiUtama"));
			int tahun = (Integer)data.get("tahun");
			int idNegeri = (Integer)data.get("idNegeri");
 			String idDaerah = (String)data.get("idDaerah");
	    	String idKemaskini = String.valueOf(data.get("idKemaskini"));

			Statement stmtCukaiUtama = db.getStatement();
			SQLRenderer rCU = new SQLRenderer();
			//rCU.update("id_cukaiutama", idCukaiUtama);
			rCU.update("id_peringkatbayaran", idPeringkat);
			rCU.update("tahun", tahun);
			rCU.update("id_daerah", idDaerah);
			rCU.add("jumlah_cukai", jum_cukai);
			rCU.add("jumlah_hakmilik", jum_hakmilik);
			//rCU.add("id_peringkatbayaran", idPeringkat);
			rCU.add("tahun", tahun);		
			rCU.add("id_negeri", idNegeri);
			//rCU.add("id_daerah", idDaerah);
			rCU.add("ID_KEMASKINI", idKemaskini);
			rCU.add("TARIKH_KEMASKINI", rCU.unquote("sysdate"));
			sql = rCU.getSQLUpdate("Tblhtpcukaiutama");
			myLog.info("FrmCukaiPenyataData::UPDATE::Tblhtpcukaiutama = "+sql);
			stmtCukaiUtama.executeUpdate(sql);	
    
			//return idCukaiUtama;
			return idPeringkat;
		
		}finally {
			if (db != null) db.close();
		}
		
	}
	
	
}


