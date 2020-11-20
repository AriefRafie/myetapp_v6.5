package ekptg.model.htp.cukai;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

import org.apache.log4j.Logger;

import ekptg.helpers.Utils;
import ekptg.model.htp.cukai.entity.CukaiPenyata;

public class CukaiPenyataBean implements ICukaiPenyata {
	
	static Logger myLog = Logger.getLogger(ekptg.model.htp.cukai.CukaiPenyataBean.class);
	//private static SimpleDateFormat format =  new SimpleDateFormat("dd/MM/yyyy");
	private String sql = "";
	//private CukaiPenyata cPenyata = null;
	private Vector<Hashtable<String,String>> list = null;
	
	public Vector<CukaiPenyata> getPenyata(String idUrusan, String carian, String noFail, String idNegeri,String tahun) 
		throws Exception {
		Db db = null;
		CukaiPenyata cPenyata = null;
		if (idNegeri.equals("20"))
			idNegeri = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = new String();
			sql = "SELECT distinct f.id_Fail, f.no_Fail, f.tajuk_Fail, s.keterangan, n.nama_Negeri,n.kod_Mampu" +
				",p.id_permohonan, f.id_Negeri"+
				",NVL(pb.id_peringkatbayaran,-1) id_peringkatbayaran,pb.peringkat_bayaran  " +
				" " +
				" FROM " +
				" Tblpfdfail f, Tblpermohonan p, Tblrujsuburusanstatusfail sf, Tblrujsuburusanstatus ss, " +
				" Tblrujstatus s, Tblrujnegeri n,tblhtpperingkatbayaran pb "+
				" WHERE f.id_Fail = p.id_Fail AND F.ID_STATUS <> 999 AND p.id_Permohonan = sf.id_Permohonan AND n.id_Negeri = f.id_Negeri "+
				" AND sf.id_Suburusanstatus = ss.id_Suburusanstatus AND ss.id_Status = s.id_Status "+
				" AND pb.id_permohonan(+) = p.id_permohonan "+
				" AND sf.aktif = '1' AND f.id_urusan = " + idUrusan +
				" AND f.tajuk_fail LIKE '%" + carian + "%' "+
				" AND f.no_fail LIKE '%" + noFail + "%' " +
				"  " +
				"";
			if (tahun!=null)	sql += " AND PB.tahun_cukai ='"+tahun+"' ";
			if (idNegeri != null)	sql += "AND f.id_negeri = " + idNegeri;
			sql += " ORDER BY n.kod_mampu";		
			ResultSet rs = stmt.executeQuery(sql);
			myLog.info("getPenyata:sql="+sql);
			Vector list = new Vector();
			while (rs.next()) {
				cPenyata = new CukaiPenyata();
//				h.put("idPermohonan", rs.getString("id_Permohonan"));
//				h.put("idFail", rs.getString("id_Fail"));
//				h.put("idNegeri", rs.getString("id_Negeri"));
//				h.put("noFail", rs.getString("no_Fail"));
//				h.put("tajuk", rs.getString("tajuk_Fail"));
//				h.put("negeri", rs.getString("nama_Negeri"));
//				h.put("keterangan", rs.getString("keterangan"));
//				h.put("kodMampu", rs.getString("kod_Mampu"));
				cPenyata.setId(rs.getLong("id_peringkatbayaran"));
				cPenyata.setNoRujukan(rs.getString("peringkat_bayaran")== null? "TIADA":rs.getString("peringkat_bayaran"));
				list.addElement(cPenyata);
				
			}
			return list;
		
		} finally {
			if (db != null)	db.close();
			
		}
		//return cPenyata;
	}
	
	public Vector getList(String idUrusan, String carian, String noFail, Long idNegeri,String tahun) 
		throws Exception {
		Db db = null;
		//list.clear();
		String sql = "";
		if (idNegeri == 20)
			idNegeri = null;
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT distinct f.id_Fail, f.no_Fail, f.tajuk_Fail, s.keterangan, n.nama_Negeri," +
				" n.kod_Mampu,p.id_Permohonan, f.id_Negeri,NVL(pb.id_peringkatbayaran,0) id_peringkatbayaran,pb.peringkat_bayaran " +
				" FROM " +
				" Tblpfdfail f, Tblpermohonan p, Tblrujsuburusanstatusfail sf, Tblrujsuburusanstatus ss, " +
				" Tblrujstatus s, Tblrujnegeri n,tblhtpperingkatbayaran pb "+
				" WHERE f.id_Fail = p.id_Fail AND F.ID_STATUS <> 999 AND p.id_Permohonan = sf.id_Permohonan AND n.id_Negeri = f.id_Negeri "+
				" AND sf.id_Suburusanstatus = ss.id_Suburusanstatus AND ss.id_Status = s.id_Status "+
				" AND pb.ID_PERMOHONAN(+) = p.ID_PERMOHONAN "+
				" AND sf.aktif = '1' AND f.id_Urusan = " + idUrusan +
				" AND f.tajuk_Fail LIKE '%" + carian + "%' "+
				" AND f.no_Fail LIKE '%" + noFail + "%' "+
				" AND PB.TAHUN_CUKAI ='"+tahun+"' ";
			if (idNegeri != null)
				sql += "AND f.id_Negeri = " + idNegeri;
			sql += " ORDER BY n.kod_Mampu";
		
			ResultSet rs = stmt.executeQuery(sql);
//			mylog.info("getList:sql="+sql);
			list = new Vector<Hashtable<String,String>>();
			Hashtable<String,String> h = null;
			//int bil = 1;
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
				h.put("peringkatBayaran", rs.getString("peringkat_bayaran")== null? "TIADA":rs.getString("peringkat_bayaran"));
				h.put("idPeringkatBayaran", rs.getString("id_peringkatbayaran")== null? "0":rs.getString("id_peringkatbayaran"));
				list.addElement(h);
				//bil++;
				
			}
			return list;
			
		} finally {
			if (db != null)	db.close();
			
		}
		
	}
	
	public Vector<Hashtable<String,String>> getSenaraiPenyata(String idNegeri,String tahun)throws Exception {
		Db db = null;
		String sql = "";			
		Vector<Hashtable<String,String>> list = new Vector<Hashtable<String,String>>();
		try {
			db = new Db();
			sql ="SELECT RD.ID_DAERAH,RD.NAMA_DAERAH, COUNT(*) TOTAL_MUKIM "+
				" ,SUM(TPCT.CUKAI_KENA_BAYAR) AS CUKAI_TERKINI, SUM(TPCT.DENDA) DENDA " +
				" ,SUM(TPCT.CUKAI_PERLU_BAYAR) CUKAI_KENA_BAYAR "+
				" FROM " +
				//" TBLHTPHAKMILIK TPH," +
				" TBLHTPCUKAITEMP TPCT,TBLRUJDAERAH RD "+
				" WHERE TPCT.ID_NEGERI='"+idNegeri+"' "+
				//" AND TPH.ID_MUKIM = TPCT.ID_MUKIM AND TPH.ID_DAERAH = TPCT.ID_DAERAH AND TPH.ID_NEGERI = TPCT.ID_NEGERI "+
				//" AND TPH.ID_JENISHAKMILIK = TPCT.ID_JENISHAKMILIK AND LTRIM(TPH.NO_HAKMILIK,0) = LTRIM(TPCT.NO_HAKMILIK,0) " +
				" AND TPCT.ID_DAERAH = RD.ID_DAERAH AND TPCT.TAHUN ='"+tahun+"' "+
				" GROUP BY RD.ID_DAERAH,RD.NAMA_DAERAH" +
				" ORDER BY RD.ID_DAERAH ";			
			myLog.info("getSenaraiPenyata("+idNegeri+","+tahun+")::sql="+sql);
			      
			 Statement stmt = db.getStatement();
			 ResultSet rs = stmt.executeQuery(sql);
			 Hashtable<String,String> h;
			 while (rs.next()) {
				 h = new Hashtable<String,String>();				
//				 Double d = 0.00;
//				 Double c = 0.00;
				 h.put("nama_daerah", rs.getString("NAMA_DAERAH"));
				 h.put("idDaerah", rs.getString("ID_DAERAH"));
				 h.put("sumDenda", Utils.format2Decimal(rs.getDouble("DENDA")));
				 h.put("sumIdHakmilik", String.valueOf(rs.getInt("TOTAL_MUKIM")));
				 h.put("sumCukai_", Utils.format2Decimal(rs.getDouble("CUKAI_TERKINI")));
				 h.put("sumCukai", Utils.format2Decimal(rs.getDouble("CUKAI_KENA_BAYAR")));			    
				 h.put("sumCukaiFormat", Utils.format2Decimal(rs.getDouble("CUKAI_KENA_BAYAR")));			    
				 list.addElement(h);
				 
			 }
			 return list;
			
		}finally {
			if (db != null) db.close();
			
		}
	}
	
	public Vector getSenaraiFailXPenyata(String idUrusan, String carian, String noFail, String idNegeris) 
		throws Exception {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "" +
				" SELECT distinct f.id_Fail, f.no_Fail, NVL(f.tajuk_Fail,'TIADA MAKLUMAT') tajuk_Fail, s.keterangan, n.nama_Negeri," +
				" n.kod_Mampu,p.id_Permohonan, f.id_Negeri,NVL(pb.id_peringkatbayaran,0) id_peringkatbayaran,pb.peringkat_bayaran " +
				" FROM " +
				" Tblpfdfail f, Tblpermohonan p, Tblrujsuburusanstatusfail sf, Tblrujsuburusanstatus ss, " +
				" Tblrujstatus s, Tblrujnegeri n,tblhtpperingkatbayaran pb "+
				" WHERE f.id_Fail = p.id_Fail AND F.ID_STATUS <> 999 AND p.id_Permohonan = sf.id_Permohonan AND n.id_Negeri = f.id_Negeri "+
				" AND sf.id_Suburusanstatus = ss.id_Suburusanstatus AND ss.id_Status = s.id_Status "+
				" AND pb.ID_PERMOHONAN(+) = p.ID_PERMOHONAN "+
				" AND sf.aktif = '1' AND f.id_Urusan = " + idUrusan +
				" AND f.id_Negeri IN (" + idNegeris +")";
			sql += " ORDER BY n.kod_Mampu";	
			ResultSet rs = stmt.executeQuery(sql);
			//
			myLog.info("getSenaraiFailXPenyata:sql="+sql);
			Vector list = new Vector();
			Hashtable h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable();
				h.put("idPermohonan", rs.getString("id_Permohonan"));
				h.put("idFail", rs.getString("id_Fail"));
				h.put("idNegeri", rs.getString("id_Negeri"));
				h.put("noFail", rs.getString("no_Fail"));
				h.put("tajuk", Utils.isNull(rs.getString("tajuk_Fail")));
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
			if (db != null)	db.close();
			
		}
		
	}
	

}

