package ekptg.model.ppt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;

public class FrmHakmilikSementaraSenaraiJPPHData {
	
	Vector list = null;
	Vector papar_JPPH = null;
	SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
	
	static Logger myLogger = Logger.getLogger(FrmHakmilikSementaraSenaraiJPPHData.class);
	
	public void  setCarianFail(String noFail,String tarikh,String status, String noRujJKPTG)throws Exception {
		Db db = null;
	    String sql = "";
	    String chkData = noFail.trim();
	    //
	    try {
	      list = new Vector();
	      db = new Db();
	      Statement stmt = db.getStatement();
	      
	      sql = "SELECT distinct s.id_status, p.id_permohonan, p.no_permohonan, f.id_fail, c.nama_negeri, p.no_rujukan_ptd, p.no_rujukan_ptg, p.no_rujukan_upt, f.no_fail, p.tarikh_permohonan, k.nama_kementerian, su.nama_suburusan, s.keterangan ";
	      sql +="FROM Tblpptpermohonan p, Tblpfdfail f, Tblrujsuburusan su, Tblrujnegeri c, Tblrujstatus s, Tblrujkementerian k ";
	      sql +="WHERE f.id_fail = p.id_fail AND c.id_negeri = p.id_negeri AND f.id_suburusan = su.id_suburusan AND f.id_kementerian = k.id_kementerian ";
	      sql +="AND p.id_status = s.id_status AND s.kod_status NOT IN ('32','27') AND s.id_seksyen = 1 AND f.id_suburusan = 53";
	      
	      if(noFail != null){
	    	  sql = sql + " AND UPPER(f.no_fail) LIKE '%'||'" + noFail.toUpperCase() + "'||'%'";  
	      }
	     
	      if(tarikh != null){
	    	  if (!tarikh.toString().trim().equals("")){
	    	  SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");
	    	  sql +="AND p.tarikh_permohonan = '" + sdf.format(Format.parse(tarikh)).toUpperCase()+ "' ";
	    	  }
	      }
	      if(status != null){
	    	  if (!status.trim().equals("")){
	    		  if (!status.trim().equals("0")){
	    			  sql +="AND s.id_status = '" + status + "' ";
	    		  }
	    	  }	  
	      }
	      if(noRujJKPTG != null){
	    	  if (!noRujJKPTG.trim().equals("")){
	    		  if (!noRujJKPTG.trim().equals("0")){
	    			  sql +="AND UPPER(p.no_rujukan_upt) LIKE '%'||'" + noRujJKPTG.toUpperCase() + "'||'%'";  
	    		  }
	    	  }	  
	      }
	      sql +="ORDER by p.tarikh_permohonan desc, f.no_fail desc ";
	      
	      ResultSet rs = stmt.executeQuery(sql);  
	      Hashtable h;
	      int bil = 1;
	      int count = 0;
	      while (rs.next()) {
	    	  h = new Hashtable();
	    	  h.put("bil", bil);
	    	  h.put("id_fail", rs.getString("id_fail"));
	    	  h.put("id_status", rs.getString("id_status"));
	    	  h.put("id_permohonan", rs.getString("id_permohonan"));
	    	  h.put("no_permohonan", rs.getString("no_permohonan"));
	    	  h.put("no_rujukan_ptd", rs.getString("no_rujukan_ptd")==null?"":rs.getString("no_rujukan_ptd"));
	    	  h.put("no_rujukan_ptg", rs.getString("no_rujukan_ptg")==null?"":rs.getString("no_rujukan_ptg"));
	    	  h.put("no_rujukan_upt", rs.getString("no_rujukan_upt")==null?"":rs.getString("no_rujukan_upt"));
	    	  h.put("tarikh_permohonan", rs.getDate("tarikh_permohonan")==null?"":Format.format(rs.getDate("tarikh_permohonan")));
	    	  h.put("nama_suburusan", rs.getString("nama_suburusan")==null?"":rs.getString("nama_suburusan"));
	    	 // h.put("nama_agensi", rs.getString("nama_agensi")==null?"":rs.getString("nama_agensi"));
	    	  h.put("nama_negeri", rs.getString("nama_negeri")==null?"":rs.getString("nama_negeri"));
	    	  h.put("nama_kementerian", rs.getString("nama_kementerian")==null?"":rs.getString("nama_kementerian"));
	    	  h.put("keterangan", rs.getString("keterangan")==null?"":rs.getString("keterangan"));
	    	  if(rs.getString("no_fail") == null){
		    		h.put("no_fail","Belum Diluluskan");
		    	}else{
		    		h.put("no_fail",rs.getString("no_fail"));
		    	}
	    	  list.addElement(h);
	    	  bil++;
	    	  count++;
	      }
	      
	      if(count == 0){
	    	  h = new Hashtable();
	    	  h.put("bil", "");
	    	  h.put("id_fail","");
	    	  h.put("id_status", "");
	    	  h.put("id_permohonan", "");
	    	  h.put("no_permohonan", "");
	    	  h.put("no_rujukan_ptd", "");
	    	  h.put("no_rujukan_ptg", "");
	    	  h.put("no_rujukan_upt", "");
	    	  h.put("tarikh_permohonan", "");
	    	  h.put("nama_suburusan", "");
	    	 // h.put("nama_agensi", rs.getString("nama_agensi")==null?"":rs.getString("nama_agensi"));
	    	  h.put("nama_negeri", "");
	    	  h.put("nama_kementerian", "");
	    	  h.put("keterangan", "");
	    	  h.put("no_fail","Tiada rekod.");
	    	  list.addElement(h);
	      }
	      //return list;
	    } finally {
	      if (db != null) db.close();
	    }
	}
	
	public Vector getList(){
		return list;
	}
	
	public String addJPPH(Hashtable data) throws Exception {
			
			String sql = "";
			long idUlasanTeknikal = DB.getNextID("TBLPPTULASANTEKNIKAL_SEQ");
//			String txtKodJPPH = (String)data.get("txtKodJPPH");
			String txtBilSurat = (String)data.get("txtBilSurat");
			String txdTkhSurat = (String)data.get("txdTkhSurat");
			String txdTkhAkhirJwpnDiterima = (String)data.get("txdTkhAkhirJwpnDiterima");
			String txtNoRujSuratJPPH = (String)data.get("txtNoRujSuratJPPH");
			String txdTkhTerima = (String)data.get("txdTkhTerima");
			String txdTkhSuratJPPH = (String)data.get("txdTkhSuratJPPH");
			String id_permohonan = (String)data.get("id_permohonan");
			String id_Masuk = (String)data.get("id_Masuk");
			
			Db db = null;
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				r.add("ID_ULASANTEKNIKAL",idUlasanTeknikal);
				r.add("ID_JABATANTEKNIKAL","13");
//				r.add("KOD_PEJABAT_JPPH", txtKodJPPH);
				r.add("BIL_SURAT", txtBilSurat);
				r.add("TARIKH_SURAT", r.unquote("to_date('" + txdTkhSurat + "','dd/MM/yyyy')"));
				r.add("TARIKH_AKHIR", r.unquote("to_date('" + txdTkhAkhirJwpnDiterima + "','dd/MM/yyyy')"));
				r.add("NO_RUJUKANSURATJT", txtNoRujSuratJPPH);
				r.add("TARIKH_TERIMAJT", r.unquote("to_date('" + txdTkhTerima + "','dd/MM/yyyy')"));
				r.add("TARIKH_SURATJT", r.unquote("to_date('" + txdTkhSuratJPPH + "','dd/MM/yyyy')"));
				r.add("ID_PERMOHONAN", id_permohonan);
				r.add("ID_MASUK", id_Masuk);
			    r.add("TARIKH_MASUK", r.unquote("sysdate"));
			    
			    sql = r.getSQLInsert("TBLPPTULASANTEKNIKAL");
			    	
			    stmt.executeUpdate(sql);
			    
			    Statement stmtQ = db.getStatement();
				SQLRenderer rQ = new SQLRenderer();
				rQ.update("ID_PERMOHONAN", id_permohonan);
				rQ.add("ID_STATUS", "43");
			    sql = rQ.getSQLUpdate("TBLPPTPERMOHONAN");	
			    stmtQ.executeUpdate(sql);
			    
			    return ""+idUlasanTeknikal;
			    
			}
			
			finally {
				if (db != null)
					db.close();
			}
		}
	
	
	
	public Vector papar_JPPH(String id_permohonan) throws Exception {
		
		String sql = "";
		Db db = null;
			
			try {
				papar_JPPH = new Vector();
				db = new Db();
				Statement stmt = db.getStatement();
				
				sql = "SELECT ID_ULASANTEKNIKAL,ID_PERMOHONAN, KOD_PEJABAT_JPPH, BIL_SURAT, TARIKH_SURAT, TARIKH_AKHIR, " +
						"NO_RUJUKANSURATJT, TARIKH_TERIMAJT, TARIKH_SURATJT " +
						"FROM TBLPPTULASANTEKNIKAL " +
						"WHERE ID_JABATANTEKNIKAL = 13 " +
						"AND ID_PERMOHONAN = '"+id_permohonan+"'" ;
						
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
			
				while (rs.next()) {
					h = new Hashtable();	
					h.put("ID_ULASANTEKNIKAL", rs.getString("ID_ULASANTEKNIKAL")==null?"":rs.getString("ID_ULASANTEKNIKAL"));
					h.put("ID_PERMOHONAN", rs.getString("ID_PERMOHONAN")==null?"":rs.getString("ID_PERMOHONAN"));
					h.put("KOD_PEJABAT_JPPH", rs.getString("KOD_PEJABAT_JPPH")==null?"":rs.getString("KOD_PEJABAT_JPPH"));
					h.put("BIL_SURAT", rs.getString("BIL_SURAT")==null?"":rs.getString("BIL_SURAT"));
					h.put("TARIKH_SURAT", rs.getString("TARIKH_SURAT")==null?"":Format.format(rs.getDate("TARIKH_SURAT")));
					h.put("TARIKH_AKHIR", rs.getString("TARIKH_AKHIR")==null?"":Format.format(rs.getDate("TARIKH_AKHIR")));				
					h.put("NO_RUJUKANSURATJT", rs.getString("NO_RUJUKANSURATJT")==null?"":rs.getString("NO_RUJUKANSURATJT"));
					h.put("TARIKH_TERIMAJT", rs.getString("TARIKH_TERIMAJT")==null?"":Format.format(rs.getDate("TARIKH_TERIMAJT")));
					h.put("TARIKH_SURATJT", rs.getString("TARIKH_SURATJT")==null?"":Format.format(rs.getDate("TARIKH_SURATJT")));
					papar_JPPH.addElement(h);								
				}
			}
			finally {
				if (db != null)
					db.close();
			}	
		return papar_JPPH;
	}

	public void updateJPPH(Hashtable data) throws Exception {
			
			String sql = "";
			String idUlasanTeknikal = (String)data.get("idUlasanTeknikal");
//			String txtKodJPPH = (String)data.get("txtKodJPPH");
			String txtBilSurat = (String)data.get("txtBilSurat");
			
			String txdTkhSurat = (String)data.get("txdTkhSurat");
			String txdTkhAkhirJwpnDiterima = (String)data.get("txdTkhAkhirJwpnDiterima");
			String txtNoRujSuratJPPH = (String)data.get("txtNoRujSuratJPPH");
			String txdTkhTerima = (String)data.get("txdTkhTerima");
			String txdTkhSuratJPPH = (String)data.get("txdTkhSuratJPPH");
			String idKemaskini = (String)data.get("id_Kemaskini");
			
			Db db = null;
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				
				r.update("ID_ULASANTEKNIKAL", idUlasanTeknikal);
//				r.add("KOD_PEJABAT_JPPH", txtKodJPPH);
				r.add("BIL_SURAT", txtBilSurat);
				r.add("TARIKH_SURAT", r.unquote("to_date('" + txdTkhSurat + "','dd/MM/yyyy')"));
				r.add("TARIKH_AKHIR", r.unquote("to_date('" + txdTkhAkhirJwpnDiterima + "','dd/MM/yyyy')"));
				r.add("NO_RUJUKANSURATJT", txtNoRujSuratJPPH);
			    r.add("TARIKH_TERIMAJT", r.unquote("to_date('" + txdTkhTerima + "','dd/MM/yyyy')"));
			    r.add("TARIKH_SURATJT", r.unquote("to_date('" + txdTkhSuratJPPH + "','dd/MM/yyyy')"));
			    r.add("ID_KEMASKINI", idKemaskini);
			    r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			    
			    sql = r.getSQLUpdate("TBLPPTULASANTEKNIKAL");
			    stmt.executeUpdate(sql);
			    
			}
			
			finally {
				if (db != null)
					db.close();
			}
		}
	
	public void hapus_JPPH(String idUlasanTeknikal)throws Exception{
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();						
			sql = "DELETE FROM TBLPPTULASANTEKNIKAL WHERE ID_ULASANTEKNIKAL= '"+idUlasanTeknikal+"'";
			stmt.executeUpdate(sql);			
		}
		finally {
			if(db != null)
				db.close();
		}
	}
}
