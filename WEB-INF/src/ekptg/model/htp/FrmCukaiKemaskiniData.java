package ekptg.model.htp;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.util.Util;
import ekptg.helpers.DB;
import ekptg.helpers.Utils;

public class FrmCukaiKemaskiniData {
	
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	
	public static Vector getListKCukaiView(int idHakmilikCukai, Long noHakmilik, Long idJenisHakmilik, Long idKementerian, Long idNegeri, Long idDaerah, Long idMukim)throws Exception {
 	    Db db = null;
 	    String sql = "";
 	    
 	    if (idHakmilikCukai == 0){
 	    	idHakmilikCukai = 0;
	    }
 	    if (noHakmilik == 0){
	    	noHakmilik = null;
	    }
	    if (idJenisHakmilik == 0){
	    	idJenisHakmilik = null;
	    }
	    if (idNegeri == 0){
	    	idNegeri = null;
	    }
	    if (idDaerah == 0){
	    	idDaerah = null;
	    }
	    if (idMukim == 0){
	    	idMukim = null;
	    }
	    
 	    try {
 	      db = new Db();
 	      Statement stmt = db.getStatement();
 	      	sql = " SELECT thh.id_negeri, thh.id_daerah, thh.id_mukim, thh.id_jenishakmilik, thh.no_hakmilik, ";
 	      	sql +=	" tpf.no_fail,  thh.no_fail_ptg, thh.tarikh_daftar, thh.id_luas, thh.id_lot, thh.luas, ";
 	      	sql +=	" thhc.tarikh_masuk, thhc.cukai_terkini, thhc.cukai_taliair, thhc.cukai_parit,  thhc.denda,thhc.bayaran_lain, ";
 	      	sql +=	" thh.no_lot, thhc.id_hakmilikcukai, thh.id_hakmilik, thhc.status,  thhc.cukai, ";
 	      	sql +=	" thhc.pengecualian, thhc.pengurangan, thhp.kegunaan_tanah, tru.nama_urusan, thpajak.kategori_cukai ";
 	      	
 	      	sql +=" FROM tblhtphakmilik thh, tblhtphakmilikcukai thhc, tblhtphakmilikperihal thhp, ";
 	      	sql +=	" tblpermohonan tp, tblpfdfail tpf, tblrujurusan tru, tblhtppajakan thpajak ";
 	      	
 	      	sql +=" WHERE  thh.id_hakmilik = thhc.id_hakmilik ";
 	      	sql +=	" AND thhc.id_hakmilik = thhp.id_hakmilik ";
 	      	sql +=	" AND tpf.id_urusan = tru.id_urusan ";
 	      	sql +=	" AND tpf.id_fail = tp.id_fail ";
 	      	sql +=	" AND thh.id_permohonan = tp.id_permohonan ";
 	      	sql +=	" AND thh.id_permohonan = thpajak.id_permohonan(+)";
 	      	
 	      	
 	      	if(idHakmilikCukai != 0){
 	      		sql +=	" AND thhc.id_hakmilikcukai = "+idHakmilikCukai;
	 	     }
 	      	if(noHakmilik != null){
	 	    	sql +=" AND thh.no_hakmilik = "+noHakmilik;
	 	     }
	 	     if(idJenisHakmilik != null){
	 	    	sql += " AND thh.id_jenishakmilik = "+idJenisHakmilik;
	 	     }
	 	     if(idNegeri != null){
	 	    	sql += " AND thh.id_negeri = "+idNegeri;
	 	     }
	 	     if(idDaerah != null){
	 	    	sql += " AND thh.id_daerah = "+idDaerah;
	 	     }
	 	     if(idMukim != null){
	 	    	sql += " AND thh.id_mukim = "+idMukim;
	 	     }
	 	     sql += " ORDER BY thhc.tarikh_masuk DESC";
 	    	
 	     System.out.println("FrmCukaiKemaskiniData::getListKCukaiView::sql::"+sql);	
 	     ResultSet rs = stmt.executeQuery(sql);
 	      
 	     Vector list = new Vector();
 	     Hashtable h;
 	     int bil = 1;
 	      
 	     while (rs.next()) {
 	    	  h = new Hashtable();
 	    	  h.put("bil", bil);
 	    	  
 	    	  if(rs.getString("id_negeri") != null){
	    		  h.put("idNegeri", rs.getString("id_negeri"));
	    	  }else{
	    		  h.put("idNegeri", "0");
	    	  }
 	    	  if(rs.getString("id_daerah") != null){
	    		  h.put("idDaerah", rs.getString("id_daerah"));
	    	  }else{
	    		  h.put("idDaerah", "0");
	    	  }
 	    	  if(rs.getString("id_mukim") != null){
	    		  h.put("idMukim", rs.getString("id_mukim"));
	    	  }else{
	    		  h.put("idMukim", "0");
	    	  }
 	    	  if(rs.getString("id_luas") != null){
	    		  h.put("idLuas", rs.getString("id_luas"));
	    	  }else{
	    		  h.put("idLuas", "0");
	    	  }
 	    	  if(rs.getString("id_hakmilikcukai") != null){
	    		  h.put("idHakmilikCukai", rs.getString("id_hakmilikcukai"));
	    	  }else{
	    		  h.put("idHakmilikCukai", "0");
	    	  }
 	    	  if(rs.getString("id_hakmilik") != null){
	    		  h.put("idHakmilik", rs.getString("id_hakmilik"));
	    	  }else{
	    		  h.put("idHakmilik", "0");
	    	  }
 	    	  if(rs.getString("id_jenishakmilik") != null){
	    		  h.put("idJenisHakmilik", rs.getString("id_jenishakmilik"));
	    	  }else{
	    		  h.put("idJenisHakmilik", "0");
	    	  }
 	    	  if(rs.getString("no_hakmilik") != null){
	    		  h.put("noHakmilik", rs.getString("no_hakmilik"));
	    	  }else{
	    		  h.put("noHakmilik", "0");
	    	  }
 	    	  if(rs.getString("no_fail") != null){
	    		  h.put("noFail", rs.getString("no_fail"));
	    	  }else{
	    		  h.put("noFail", "TIADA");
	    	  }
 	    	  if(rs.getString("status") != null){
	    		  h.put("statusBayaran", rs.getString("status"));
	    	  }else{
	    		  h.put("statusBayaran", "TIADA");
	    	  }
 	    	  if(rs.getString("no_fail_ptg") != null){
	    		  h.put("noFailPTG", rs.getString("no_fail_ptg"));
	    	  }else{
	    		  h.put("noFailPTG", "TIADA");
	    	  }
 	    	 if(rs.getString("no_fail_ptg") != null){
	    		  h.put("noFailPTD", rs.getString("no_fail_ptg"));
	    	  }else{
	    		  h.put("noFailPTD", "TIADA");
	    	  }
	    	  if(rs.getString("tarikh_daftar") != null){
	    		  h.put("tarikhDaftar", Format.format(rs.getDate("tarikh_daftar")));
	    	  }else{
	    		  h.put("tarikhDaftar", "");
	    	  }
	    	  if(rs.getString("luas") != null){
	    		  h.put("Luas", Utils.formatLuas((Double)rs.getDouble("luas")));
	    	  }else{
	    		  h.put("Luas", "0");
	    	  }
	    	  if(rs.getString("nama_urusan") != null){
	    		  h.put("caraPerolehi", rs.getString("nama_urusan"));
	    	  }else{
	    		  h.put("caraPerolehi", "TIADA");
	    	  }
	    	  if(rs.getString("id_lot") != null){
	    		  h.put("idLot", rs.getString("id_lot"));
	    	  }else{
	    		  h.put("idLot", "0");
	    	  }
	    	  if(rs.getString("no_lot") != null){
	    		  h.put("NoLot", rs.getString("no_lot"));
	    	  }else{
	    		  h.put("NoLot", "0");
	    	  }
	    	  if(rs.getString("tarikh_masuk") != null){
	    		  h.put("tMasuk", rs.getString("tarikh_masuk"));
	    	  }else{
	    		  h.put("tMasuk", "0");
	    	  }

	    	  h.put("cukaiTerkini", rs.getString("cukai_terkini") == null || "0".equals(rs.getString("cukai_terkini").toString()) ? "0.00":Util.formatDecimal(rs.getDouble("cukai_terkini")));
	    	  h.put("cukaiTaliAir", rs.getString("cukai_taliair") == null || "0".equals(rs.getString("cukai_taliair").toString()) ? "0.00":Util.formatDecimal(rs.getDouble("cukai_taliair")));
	    	  h.put("cukaiParit", rs.getString("cukai_parit") == null || "0".equals(rs.getString("cukai_parit").toString()) ? "0.00":Util.formatDecimal(rs.getDouble("cukai_parit")));
	    	  h.put("Denda", rs.getString("denda") == null || "0".equals(rs.getString("denda").toString()) ? "0.00":Util.formatDecimal(rs.getDouble("denda")));
	    	  h.put("bayaranLain", rs.getString("bayaran_lain") == null || "0".equals(rs.getString("bayaran_lain").toString()) ? "0.00":Util.formatDecimal(rs.getDouble("bayaran_lain")));
	    	  h.put("Cukai", rs.getString("cukai") == null || "0".equals(rs.getString("cukai").toString()) ? "0.00":Util.formatDecimal(rs.getDouble("cukai")));
	    	  h.put("lebihan", rs.getString("pengecualian") == null || "0".equals(rs.getString("pengecualian").toString()) ? "0.00":Util.formatDecimal(rs.getDouble("pengecualian")));
	    	  h.put("tunggakkan", rs.getString("pengurangan") == null || "0".equals(rs.getString("pengurangan").toString()) ? "0.00":Util.formatDecimal(rs.getDouble("pengurangan")));
 	    	  h.put("pengecualian", rs.getString("pengecualian") == null || "0".equals(rs.getString("pengecualian").toString()) ? "0.00":Util.formatDecimal(rs.getDouble("pengecualian")));	    	  
	    	  h.put("pengurangan", rs.getString("pengurangan") == null || "0".equals(rs.getString("pengurangan").toString()) ? "0.00":Util.formatDecimal(rs.getDouble("pengurangan")));

	    	  if(rs.getString("kegunaan_tanah") != null){
	    		  h.put("kegunaanTanah", rs.getString("kegunaan_tanah"));
	    	  }else{
	    		  h.put("kegunaanTanah", "TIADA");
	    	  }
	    	  if(rs.getString("kategori_cukai") != null){
	    		  h.put("pembayarCukai", rs.getString("kategori_cukai"));
	    	  }else{
	    		  h.put("pembayarCukai", "TIADA");
	    	  }
 	    	
 	    	  list.addElement(h);
 	    	  bil++;
 	      }
 	     return list;
 	    } finally {
 	      if (db != null) db.close();
 	    }
 	}
	
	public static Vector getListKCukai(int idHakmilikCukai, Long noHakmilik, Long idJenisHakmilik, Long idKementerian, Long idNegeri, Long idDaerah, Long idMukim)throws Exception {
 	    Db db = null;
 	    String sql = "";
 	    
 	    if (noHakmilik == 0){
	    	noHakmilik = null;
	    }
	    if (idJenisHakmilik == 0){
	    	idJenisHakmilik = null;
	    }
	    if (idNegeri == 0){
	    	idNegeri = null;
	    }
	    if (idDaerah == 0){
	    	idDaerah = null;
	    }
	    if (idMukim == 0){
	    	idMukim = null;
	    }
	    
 	    try {
 	      db = new Db();
 	      Statement stmt = db.getStatement();
 	      	sql = " SELECT thh.id_negeri, thh.id_daerah, thh.id_mukim, thh.id_jenishakmilik, thh.no_hakmilik, ";
 	      	sql +=	" tpf.no_fail,  thh.no_fail_ptg, thh.tarikh_daftar, thh.id_luas, thh.id_lot, thh.luas, ";
 	      	sql +=	" thhc.tarikh_masuk, thhc.cukai_terkini, thhc.cukai_taliair, thhc.cukai_parit,  thhc.denda,thhc.bayaran_lain, ";
 	      	sql +=	" thh.no_lot, thhc.id_hakmilikcukai, thh.id_hakmilik, thhc.status,  thhc.cukai, ";
 	      	sql +=	" thhc.pengecualian, thhc.pengurangan, thhp.kegunaan_tanah, tru.nama_urusan, thpajak.kategori_cukai ";
 	      	sql +=" FROM tblhtphakmilik thh, tblhtphakmilikcukai thhc, tblhtphakmilikperihal thhp, ";
 	      	sql +=	" tblpermohonan tp, tblpfdfail tpf, tblrujurusan tru, tblhtppajakan thpajak ";
 	      	sql +=" WHERE  thh.id_hakmilik = thhc.id_hakmilik ";
 	      	sql +=	" AND thhc.id_hakmilik = thhp.id_hakmilik ";
 	      	sql +=	" AND tpf.id_urusan = tru.id_urusan ";
 	      	sql +=	" AND tpf.id_fail = tp.id_fail ";
 	      	sql +=	" AND thh.id_permohonan = tp.id_permohonan ";
 	      	sql +=	" AND thh.id_permohonan = thpajak.id_permohonan(+)";
 	      	sql +=	" AND thhc.id_hakmilikcukai = "+idHakmilikCukai;
 	      	 	      	
 	      	if(noHakmilik != null){
 	      		sql +=" AND thh.no_hakmilik = "+noHakmilik;
	 	    }
	 	    if(idJenisHakmilik != null){
	 	    	sql += " AND thh.id_jenishakmilik = "+idJenisHakmilik;
	 	    }
	 	    if(idNegeri != null){
	 	    	sql += " AND thh.id_negeri = "+idNegeri;
	 	    }
	 	    if(idDaerah != null){
	 	    	sql += " AND thh.id_daerah = "+idDaerah;
	 	    }
	 	    if(idMukim != null){
	 	    	sql += " AND thh.id_mukim = "+idMukim;
	 	    }
	 	     
	 	    sql += " ORDER BY thhc.tarikh_masuk DESC";
 	    	
 	     System.out.println("FrmCukaiKemaskiniData::getListKCukai::sql::"+sql);	
 	     ResultSet rs = stmt.executeQuery(sql);
 	      
 	     Vector list = new Vector();
 	     Hashtable h;
 	     int bil = 1;
 	      
 	    while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  
	    	  if(rs.getString("id_negeri") != null){
	    		  h.put("idNegeri", rs.getString("id_negeri"));
	    	  }else{
	    		  h.put("idNegeri", "0");
	    	  }
	    	  if(rs.getString("id_daerah") != null){
	    		  h.put("idDaerah", rs.getString("id_daerah"));
	    	  }else{
	    		  h.put("idDaerah", "0");
	    	  }
	    	  if(rs.getString("id_mukim") != null){
	    		  h.put("idMukim", rs.getString("id_mukim"));
	    	  }else{
	    		  h.put("idMukim", "0");
	    	  }
	    	  if(rs.getString("id_luas") != null){
	    		  h.put("idLuas", rs.getString("id_luas"));
	    	  }else{
	    		  h.put("idLuas", "0");
	    	  }
	    	  if(rs.getString("id_hakmilikcukai") != null){
	    		  h.put("idHakmilikCukai", rs.getString("id_hakmilikcukai"));
	    	  }else{
	    		  h.put("idHakmilikCukai", "0");
	    	  }
	    	  if(rs.getString("id_hakmilik") != null){
	    		  h.put("idHakmilik", rs.getString("id_hakmilik"));
	    	  }else{
	    		  h.put("idHakmilik", "0");
	    	  }
	    	  if(rs.getString("id_jenishakmilik") != null){
	    		  h.put("idJenisHakmilik", rs.getString("id_jenishakmilik"));
	    	  }else{
	    		  h.put("idJenisHakmilik", "0");
	    	  }
	    	  if(rs.getString("no_hakmilik") != null){
	    		  h.put("noHakmilik", rs.getString("no_hakmilik"));
	    	  }else{
	    		  h.put("noHakmilik", "0");
	    	  }
	    	  if(rs.getString("no_fail") != null){
	    		  h.put("noFail", rs.getString("no_fail"));
	    	  }else{
	    		  h.put("noFail", "TIADA");
	    	  }
	    	  if(rs.getString("status") != null){
	    		  h.put("statusBayaran", rs.getString("status"));
	    	  }else{
	    		  h.put("statusBayaran", "TIADA");
	    	  }
	    	  if(rs.getString("no_fail_ptg") != null){
	    		  h.put("noFailPTG", rs.getString("no_fail_ptg"));
	    	  }else{
	    		  h.put("noFailPTG", "TIADA");
	    	  }
	    	 if(rs.getString("no_fail_ptg") != null){
	    		  h.put("noFailPTD", rs.getString("no_fail_ptg"));
	    	  }else{
	    		  h.put("noFailPTD", "TIADA");
	    	  }
	    	  if(rs.getString("tarikh_daftar") != null){
	    		  h.put("tarikhDaftar", Format.format(rs.getDate("tarikh_daftar")));
	    	  }else{
	    		  h.put("tarikhDaftar", "");
	    	  }
	    	  if(rs.getString("luas") != null){
	    		  h.put("Luas", Utils.formatLuas((Double)rs.getDouble("luas")));
	    	  }else{
	    		  h.put("Luas", "0");
	    	  }
	    	  if(rs.getString("nama_urusan") != null){
	    		  h.put("caraPerolehi", rs.getString("nama_urusan"));
	    	  }else{
	    		  h.put("caraPerolehi", "TIADA");
	    	  }
	    	  if(rs.getString("id_lot") != null){
	    		  h.put("idLot", rs.getString("id_lot"));
	    	  }else{
	    		  h.put("idLot", "0");
	    	  }
	    	  if(rs.getString("no_lot") != null){
	    		  h.put("NoLot", rs.getString("no_lot"));
	    	  }else{
	    		  h.put("NoLot", "0");
	    	  }
	    	  if(rs.getString("tarikh_masuk") != null){
	    		  h.put("tMasuk", rs.getString("tarikh_masuk"));
	    	  }else{
	    		  h.put("tMasuk", "0");
	    	  }

	    	  h.put("cukaiTerkini", rs.getString("cukai_terkini") == null || "0".equals(rs.getString("cukai_terkini").toString()) ? "0.00":Utils.RemoveSymbol(Util.formatDecimal(rs.getDouble("cukai_terkini"))));
	    	  h.put("cukaiTaliAir", rs.getString("cukai_taliair") == null || "0".equals(rs.getString("cukai_taliair").toString()) ? "0.00":Utils.RemoveSymbol(Util.formatDecimal(rs.getDouble("cukai_taliair"))));
	    	  h.put("cukaiParit", rs.getString("cukai_parit") == null || "0".equals(rs.getString("cukai_parit").toString()) ? "0.00":Utils.RemoveSymbol(Util.formatDecimal(rs.getDouble("cukai_parit"))));
	    	  h.put("Denda", rs.getString("denda") == null || "0".equals(rs.getString("denda").toString()) ? "0.00":Utils.RemoveSymbol(Util.formatDecimal(rs.getDouble("denda"))));
	    	  h.put("bayaranLain", rs.getString("bayaran_lain") == null || "0".equals(rs.getString("bayaran_lain").toString()) ? "0.00":Utils.RemoveSymbol(Util.formatDecimal(rs.getDouble("bayaran_lain"))));
	    	  h.put("Cukai", rs.getString("cukai") == null || "0".equals(rs.getString("cukai").toString()) ? "0.00":Utils.RemoveSymbol(Util.formatDecimal(rs.getDouble("cukai"))));
	    	  h.put("lebihan", rs.getString("pengecualian") == null || "0".equals(rs.getString("pengecualian").toString()) ? "0.00":Utils.RemoveSymbol(Util.formatDecimal(rs.getDouble("pengecualian"))));
	    	  h.put("tunggakkan", rs.getString("pengurangan") == null || "0".equals(rs.getString("pengurangan").toString()) ? "0.00":Utils.RemoveSymbol(Util.formatDecimal(rs.getDouble("pengurangan"))));
	    	  h.put("pengecualian", rs.getString("pengecualian") == null || "0".equals(rs.getString("pengecualian").toString()) ? "0.00":Utils.RemoveSymbol(Util.formatDecimal(rs.getDouble("pengecualian"))));	    	  
	    	  h.put("pengurangan", rs.getString("pengurangan") == null || "0".equals(rs.getString("pengurangan").toString()) ? "0.00":Utils.RemoveSymbol(Util.formatDecimal(rs.getDouble("pengurangan"))));

	    	  if(rs.getString("kegunaan_tanah") != null){
	    		  h.put("kegunaanTanah", rs.getString("kegunaan_tanah"));
	    	  }else{
	    		  h.put("kegunaanTanah", "TIADA");
	    	  }
	    	  if(rs.getString("kategori_cukai") != null){
	    		  h.put("pembayarCukai", rs.getString("kategori_cukai"));
	    	  }else{
	    		  h.put("pembayarCukai", "TIADA");
	    	  }
	    	
	    	  list.addElement(h);
	    	  bil++;
	      }
 	     return list;
 	    } finally {
 	      if (db != null) db.close();
 	    }
 	}
	
	//*** insert data in database
	public static int insert(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		try
		{
			long idHakmilikCukai = DB.getNextID("TBLHTPHAKMILIKCUKAI_SEQ");
		    int idHakmilik = (Integer)data.get("idHakmilik");
		    double cukaiTerkini = Double.parseDouble(Utils.RemoveSymbol(data.get("cukaiTerkini").toString()));
		    double cukaiTaliAir = Double.parseDouble(Utils.RemoveSymbol(data.get("cukaiTaliAir").toString()));
		    double cukaiParit = Double.parseDouble(Utils.RemoveSymbol(data.get("cukaiParit").toString()));
		    double Denda = Double.parseDouble(Utils.RemoveSymbol(data.get("Denda").toString()));
		    double bayaranLain = Double.parseDouble(Utils.RemoveSymbol(data.get("bayaranLain").toString()));
		    double Cukai = Double.parseDouble(Utils.RemoveSymbol(data.get("Cukai").toString()));
		    double pengecualian = Double.parseDouble(Utils.RemoveSymbol(data.get("pengecualian").toString()));
		    double pengurangan = Double.parseDouble(Utils.RemoveSymbol(data.get("pengurangan").toString()));
		    String statusBayaran = (String)data.get("statusBayaran");
		      
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("id_hakmilikcukai", idHakmilikCukai);
			r.add("id_hakmilik", idHakmilik);
			r.add("cukai_terkini", cukaiTerkini);
			r.add("cukai_taliair", cukaiTaliAir);
			r.add("cukai_parit", cukaiParit);
			r.add("denda", Denda);
			r.add("bayaran_lain", bayaranLain);
			r.add("cukai", Cukai);
			r.add("pengecualian",pengecualian);
			r.add("pengurangan", pengurangan);
			r.add("status", statusBayaran);
			  
		    sql = r.getSQLInsert("Tblhtphakmilikcukai");
		    System.out.println("FrmCukaiKemaskiniData::Update::Tblhtphakmilikcukai = "+sql);
		    stmt.executeUpdate(sql);
		    return (int)idHakmilikCukai;
		}
		finally {
			if (db != null) db.close();
		}
	}
	  
	//*** update data in database
	public static int update(Hashtable data) throws Exception {
		Db db = null;
		Db db2 = null;
		String sql = "";
		try
		{
			int idHC = (Integer)data.get("idHakmilikCukai");
				if(idHC==0){
					int idHC_old = (Integer)data.get("idHC");
					db2 = new Db();
					Statement stmt2 = db2.getStatement();
					SQLRenderer rStat = new SQLRenderer();
					rStat.update("id_hakmilikcukai", idHC_old);
					rStat.add("status_terkini", "N");
					sql = rStat.getSQLUpdate("Tblhtphakmilikcukai");
					stmt2.executeUpdate(sql);
					
					long idHakmilikCukai = DB.getNextID("TBLHTPHAKMILIKCUKAI_SEQ");
					int idHakmilik = (Integer)data.get("idHakmilik");
				    double cukaiTerkini = Double.parseDouble(Utils.RemoveSymbol(data.get("cukaiTerkini").toString()));
				    double cukaiTaliAir = Double.parseDouble(Utils.RemoveSymbol(data.get("cukaiTaliAir").toString()));
				    double cukaiParit = Double.parseDouble(Utils.RemoveSymbol(data.get("cukaiParit").toString()));
				    double Denda = Double.parseDouble(Utils.RemoveSymbol(data.get("Denda").toString()));
				    double bayaranLain = Double.parseDouble(Utils.RemoveSymbol(data.get("bayaranLain").toString()));
				    double Cukai = Double.parseDouble(Utils.RemoveSymbol(data.get("Cukai").toString()));
				    double pengecualian = Double.parseDouble(Utils.RemoveSymbol(data.get("pengecualian").toString()));
				    double pengurangan = Double.parseDouble(Utils.RemoveSymbol(data.get("pengurangan").toString()));
				    String statusBayaran = (String)data.get("statusBayaran");
				    Date now = new Date();
				    SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy h:MM:ss a");
				    String datenow = "to_date('" + formatter.format(now) + "','dd/MM/yyyy HH:MI:SS AM')";
				      
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					r.add("id_hakmilikcukai", idHakmilikCukai);
					r.add("id_hakmilik", idHakmilik);
					r.add("cukai_terkini", cukaiTerkini);
					r.add("cukai_taliair", cukaiTaliAir);
					r.add("cukai_parit", cukaiParit);
					r.add("denda", Denda);
					r.add("bayaran_lain", bayaranLain);
					r.add("cukai", Cukai);
					r.add("pengecualian",pengecualian);
					r.add("pengurangan", pengurangan);
					r.add("status", statusBayaran);
					r.add("tarikh_masuk", r.unquote(datenow));
					r.add("status_terkini", "Y");
					
					sql = r.getSQLInsert("Tblhtphakmilikcukai");
				    System.out.println("FrmCukaiKemaskiniData::Insert::Tblhtphakmilikcukai = "+sql);
				    stmt.executeUpdate(sql);
				    return (int)idHakmilikCukai;
				}else{
					int idHakmilikCukai = (Integer)data.get("idHakmilikCukai");
					int idHakmilik = (Integer)data.get("idHakmilik");
				    double cukaiTerkini = Double.parseDouble(Utils.RemoveSymbol(data.get("cukaiTerkini").toString()));
				    double cukaiTaliAir = Double.parseDouble(Utils.RemoveSymbol(data.get("cukaiTaliAir").toString()));
				    double cukaiParit = Double.parseDouble(Utils.RemoveSymbol(data.get("cukaiParit").toString()));
				    double Denda = Double.parseDouble(Utils.RemoveSymbol(data.get("Denda").toString()));
				    double bayaranLain = Double.parseDouble(Utils.RemoveSymbol(data.get("bayaranLain").toString()));
				    double Cukai = Double.parseDouble(Utils.RemoveSymbol(data.get("Cukai").toString()));
				    double pengecualian = Double.parseDouble(Utils.RemoveSymbol(data.get("pengecualian").toString()));
				    double pengurangan = Double.parseDouble(Utils.RemoveSymbol(data.get("pengurangan").toString()));
				    String statusBayaran = (String)data.get("statusBayaran");
				      
					db = new Db();
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					r.update("id_hakmilikcukai", idHakmilikCukai);
					r.update("id_hakmilik", idHakmilik);
					r.add("cukai_terkini", cukaiTerkini);
					r.add("cukai_taliair", cukaiTaliAir);
					r.add("cukai_parit", cukaiParit);
					r.add("denda", Denda);
					r.add("bayaran_lain", bayaranLain);
					r.add("cukai", Cukai);
					r.add("pengecualian",pengecualian);
					r.add("pengurangan", pengurangan);
					r.add("status", statusBayaran);
					r.add("status_terkini", "Y");
					
					sql = r.getSQLUpdate("Tblhtphakmilikcukai");
				    System.out.println("FrmCukaiKemaskiniData::Update::Tblhtphakmilikcukai = "+sql);
				    stmt.executeUpdate(sql);
				    return (int)idHakmilikCukai;
				}  
		}
		finally {
			if (db != null) db.close();
		}
	}
	
}