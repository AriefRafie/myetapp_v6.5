package ekptg.model.htp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.Utils;

public class FrmPajakanBayaranPajakanCukaiTanahData {
	
	private static Logger log = Logger.getLogger(FrmPajakanBayaranPajakanCukaiTanahData.class);
	private Vector<Hashtable <String,String>> senaraiFail = null;
	private Vector<Hashtable <String,String>> beanMaklumatBayaran = null;
	private Vector<Hashtable <String,String>> beanListMaklumatBayaran = null;
	//private Vector<Hashtable <String,String>> jenisBayaran = null;
	private Vector<Hashtable <String,String>> caraBayaran = null;
	private static Db db = null;
	private static Connection conn = null;
	private String sql = "";
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public void carianFail(String noFail, String tarikhTerima) throws Exception {
		try {
			senaraiFail = new Vector<Hashtable <String,String>>();
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_FAIL, B.ID_PERMOHONAN, B.ID_STATUS, A.NO_FAIL, B.TARIKH_TERIMA, C.KETERANGAN"
				+ " FROM TBLPFDFAIL A, TBLPERMOHONAN B, TBLRUJSTATUS C"
				+ " WHERE B.ID_FAIL = A.ID_FAIL AND B.ID_STATUS = C.ID_STATUS AND A.ID_SEKSYEN = '3' AND A.ID_URUSAN = '3' AND A.ID_SUBURUSAN IN (7, 17, 18) AND B.ID_STATUS IN (71)";

			//noFail
			if (noFail != null) {
				if (!noFail.trim().equals("")) {
					sql = sql + " AND UPPER(A.NO_FAIL) LIKE '%' ||'"
							+ noFail.trim().toUpperCase() + "'|| '%'";
				}
			}

			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yy");

			//tarikhTerima
			if (tarikhTerima != null) {
				if (!tarikhTerima.toString().trim().equals("")) {
					sql = sql + " AND TO_CHAR(B.TARIKH_TERIMA,'dd-MON-YY') = '" + sdf1.format(sdf.parse(tarikhTerima)).toUpperCase() +"'";
				}
			}

			sql = sql + " ORDER BY B.ID_PERMOHONAN DESC";
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable<String,String> h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("bil", String.valueOf(bil));
				h.put("idFail", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("noFail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL").toUpperCase());
				h.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA")));
				h.put("idStatus", rs.getString("ID_STATUS") == null ? "" : rs.getString("ID_STATUS"));
				h.put("status", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				senaraiFail.addElement(h);
				bil++;
			}

		} finally {
			if (db != null)
				db.close();
		}
	}
	
	//public void setListMaklumatBayaran(String idPermohonan) throws Exception{
	public void setListMaklumatBayaran(String idFail) throws Exception{		
		String sql = "";		
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			beanListMaklumatBayaran = new Vector<Hashtable <String,String>>();
			
//			sql = "SELECT A.ID_BAYARAN, A.ID_PERMOHONAN, B.KETERANGAN AS TUJUANBAYAR,  " ;
//			sql += "C.KETERANGAN AS CARABAYAR, A.NAMA_BANK, A.NO_BAYARAN, A.TARIKH_TERIMA,  ";
//			sql += "A.JUMLAH_BAYARAN, A.NO_RESIT, A.TARIKH_RESIT, A.TARIKH_HANTAR_RESIT ";
//			sql += "FROM TBLHTPBAYARAN A, TBLRUJJENISBAYARAN B,  TBLRUJCARABAYAR C ";
//			sql += "WHERE  A.TUJUAN_BAYARAN = B.ID_JENISBAYARAN  ";
//			sql += "AND A.ID_CARABAYAR = C.ID_CARABAYAR  ";
//			sql += "AND A.ID_PERMOHONAN = '" + idPermohonan + "'";
			
			sql = "SELECT A.ID_BAYARAN, A.ID_PERMOHONAN, B.KETERANGAN AS TUJUANBAYAR,  " ;
			sql += "C.KETERANGAN AS CARABAYAR, A.NAMA_BANK, A.NO_BAYARAN, A.TARIKH_TERIMA,  ";
			sql += "A.JUMLAH_BAYARAN, A.NO_RESIT, A.TARIKH_RESIT, A.TARIKH_HANTAR_RESIT ";
			sql += "FROM TBLHTPBAYARAN A, TBLRUJJENISBAYARAN B,  TBLRUJCARABAYAR C, TBLPERMOHONAN D ";
			//sql += "WHERE  A.TUJUAN_BAYARAN = B.ID_JENISBAYARAN AND B.ID_JENISBAYARAN IN (21,22)  ";
			sql += "WHERE  A.TUJUAN_BAYARAN = B.ID_JENISBAYARAN ";
			sql += "AND A.ID_CARABAYAR = C.ID_CARABAYAR  ";
			sql += "AND A.ID_PERMOHONAN = D.ID_PERMOHONAN ";
			sql += "AND D.ID_FAIL = '" + idFail + "'" +
					" order by A.TARIKH_TERIMA " +
					"";	
			log.info("sql list bayaran : " + sql);
			
			ResultSet rs = stmt.executeQuery(sql);
			int bil = 1;
			Hashtable <String,String> hashBayaran;
			while(rs.next()){
				hashBayaran = new Hashtable <String,String>();
				hashBayaran.put("bil", String.valueOf(bil));
				hashBayaran.put("namaBank", rs.getString("NAMA_BANK") == null ? "" : rs.getString("NAMA_BANK"));
    			hashBayaran.put("NoBayaran", rs.getString("NO_BAYARAN") == null ? "" : rs.getString("NO_BAYARAN"));
    			hashBayaran.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA")));
    			hashBayaran.put("jumlahBayaran", Utils.format2Decimal(Double.parseDouble(rs.getString("JUMLAH_BAYARAN") == "" ? "0" : rs.getString("JUMLAH_BAYARAN"))));
    			hashBayaran.put("NoResit", rs.getString("NO_RESIT") == null ? "" : rs.getString("NO_RESIT"));
    			hashBayaran.put("tarikhResit", rs.getDate("TARIKH_RESIT") == null ? "" : sdf.format(rs.getDate("TARIKH_RESIT")));
    			hashBayaran.put("tarikhHantarResit", rs.getDate("TARIKH_HANTAR_RESIT") == null ? "" : sdf.format(rs.getDate("TARIKH_HANTAR_RESIT")));
    			hashBayaran.put("tujuan", rs.getString("TUJUANBAYAR") == null ? "" : rs.getString("TUJUANBAYAR"));
    			hashBayaran.put("caraBayar", rs.getString("CARABAYAR") == null ? "" : rs.getString("CARABAYAR"));
    			hashBayaran.put("idBayaran", rs.getString("ID_BAYARAN") == null ? "" : rs.getString("ID_BAYARAN"));
    			hashBayaran.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));

    			beanListMaklumatBayaran.addElement(hashBayaran);
    			bil++;
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	
		
	}

	public Vector<Hashtable <String,String>> getListMaklumatBayaran(){
		return beanListMaklumatBayaran;
	}

	public void setMaklumatBayaran(String idBayaran) throws Exception{
		try{
			db = new Db();
			Statement stmt = db.getStatement();
			beanMaklumatBayaran = new Vector<Hashtable <String,String>>();
			
			sql = "SELECT A.ID_BAYARAN, A.ID_PERMOHONAN, A.TUJUAN_BAYARAN, " ;
			sql += "A.ID_CARABAYAR, A.NAMA_BANK, A.NO_BAYARAN, A.TARIKH_TERIMA,  ";
			sql += "A.JUMLAH_BAYARAN, A.NO_RESIT, A.TARIKH_RESIT, A.TARIKH_HANTAR_RESIT, A.TARIKH_CEK  ";
			sql += "FROM TBLHTPBAYARAN A  ";
			sql += "WHERE A.ID_BAYARAN = '" + idBayaran + "'";			
			ResultSet rs = stmt.executeQuery(sql);
			
			Hashtable<String,String> hashBayaran;
			while(rs.next()){
				hashBayaran = new Hashtable<String,String>();
				hashBayaran.put("namaBank", rs.getString("NAMA_BANK") == null ? "" : rs.getString("NAMA_BANK"));
    			hashBayaran.put("NoBayaran", rs.getString("NO_BAYARAN") == null ? "" : rs.getString("NO_BAYARAN"));
    			hashBayaran.put("tarikhTerima", rs.getDate("TARIKH_TERIMA") == null ? "" : sdf.format(rs.getDate("TARIKH_TERIMA")));
    			hashBayaran.put("jumlahBayaran", rs.getString("JUMLAH_BAYARAN") == "" ? "0" : rs.getString("JUMLAH_BAYARAN"));
    			hashBayaran.put("NoResit", rs.getString("NO_RESIT") == null ? "" : rs.getString("NO_RESIT"));
    			hashBayaran.put("tarikhResit", rs.getDate("TARIKH_RESIT") == null ? "" : sdf.format(rs.getDate("TARIKH_RESIT")));
    			hashBayaran.put("tarikhHantarResit", rs.getDate("TARIKH_HANTAR_RESIT") == null ? "" : sdf.format(rs.getDate("TARIKH_HANTAR_RESIT")));
    			hashBayaran.put("tujuan", rs.getString("TUJUAN_BAYARAN") == null ? "" : rs.getString("TUJUAN_BAYARAN"));
    			hashBayaran.put("caraBayar", rs.getString("ID_CARABAYAR") == null ? "" : rs.getString("ID_CARABAYAR"));
    			hashBayaran.put("idBayaran", rs.getString("ID_BAYARAN") == null ? "" : rs.getString("ID_BAYARAN"));
    			hashBayaran.put("idPermohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
    			hashBayaran.put("tarikhCek", rs.getDate("TARIKH_CEK") == null ? "" : sdf.format(rs.getDate("TARIKH_CEK")));

    			beanMaklumatBayaran.addElement(hashBayaran);		
			}
			
		} finally {
			if (db != null)
				db.close();
		}
	
		
	}
	
	public void saveUpdateBayaran(String idBayaran, Hashtable<String,String> hash, HttpSession session) throws Exception {
		String userId = session.getAttribute("_ekptg_user_id").toString();
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();	
			
			//TBLHTPBAYARAN
			r.update("ID_BAYARAN", idBayaran);				
			if (!"".equals(hash.get("tarikhTerima"))){
				r.add("TARIKH_TERIMA", r.unquote("to_date('" + hash.get("tarikhTerima") + "','dd/MM/yyyy')"));
			}
			if (!"".equals(hash.get("tarikhResit"))){
				r.add("TARIKH_RESIT", r.unquote("to_date('" + hash.get("tarikhResit") + "','dd/MM/yyyy')"));
			}
			if (!"".equals(hash.get("tarikhHantarResit"))){
				r.add("TARIKH_HANTAR_RESIT", r.unquote("to_date('" + hash.get("tarikhHantarResit") + "','dd/MM/yyyy')"));
			}
			if (!"".equals(hash.get("tarikhCek"))){
				r.add("TARIKH_CEK", r.unquote("to_date('" + hash.get("tarikhCek") + "','dd/MM/yyyy')"));
			}
			
			r.add("TUJUAN_BAYARAN", hash.get("tujuan"));
			r.add("ID_CARABAYAR",hash.get("caraBayar"));	
			r.add("NAMA_BANK", hash.get("namaBank"));
			r.add("NO_BAYARAN", hash.get("NoBayaran"));
			r.add("JUMLAH_BAYARAN", hash.get("jumlahBayaran"));
			r.add("NO_RESIT", hash.get("NoResit"));

			r.add("ID_KEMASKINI", userId);
			r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));

			sql = r.getSQLUpdate("TBLHTPBAYARAN");
			stmt.executeUpdate(sql);

			conn.commit();
			
		} catch (SQLException ex) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception("Rollback error : " + e.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());
	    	
	    } finally {
			if (db != null)
				db.close();
		}
	}
	
	public void hapusBayaran(String idBayaran) throws Exception {

		Db db = null;
		Connection conn = null;
		String sql = "";

		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();	
			
			//TBLHTPBAYARAN
			r.add("ID_BAYARAN", idBayaran);

			sql = r.getSQLDelete("TBLHTPBAYARAN");
			stmt.executeUpdate(sql);
			
			conn.commit();
			
		} catch (SQLException ex) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception("Rollback error : " + e.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah menghapus data " + ex.getMessage());
	    	
	    } finally {
			if (db != null)
				db.close();
		}	
	}
	
	
	
	public Vector<Hashtable <String,String>> getBeanMaklumatBayaran() {
		return beanMaklumatBayaran;
	}
	
	public Vector<Hashtable <String,String>> getSenaraiFail() {
		return senaraiFail;
	}

	public String selectCaraBayaranPajakan(String selectName,Long selectedValue
		, String disability, String jsFunction ) throws Exception{		
		StringBuffer sb = null;		
		try{
			sb = new StringBuffer();
			Vector<Hashtable <String,String>> jCBayar = new Vector<Hashtable <String,String>>();
			Hashtable<String,String> hCBayar = null;
			String selected = "";			
			sb.append("<select name ='"+ selectName +"' "  );
			
			if(!disability.equals(null) && !disability.equals("")){
				sb.append(disability);
			}			
			if(!jsFunction.equals(null) && !jsFunction.equals("")){
				sb.append(jsFunction);
			}
			
			sb.append(" > ");
			sb.append("<option value = ''> SILA PILIH </option>");
						
			setCaraBayaran();
			jCBayar = getCaraBayaran();
			for(int i = 0; i < jCBayar.size(); i++){
				hCBayar = new Hashtable<String,String>();
				hCBayar = (Hashtable<String,String>)jCBayar.get(i);
				Long idCaraBayaran = Long.parseLong(hCBayar.get("idCaraBayar").toString());
				if(idCaraBayaran.equals(selectedValue)){
					selected = "selected";
				}
				sb.append("<option value = '" + idCaraBayaran + "' " + selected
						+ " > " + hCBayar.get("kod").toString() + " - "
						+ hCBayar.get("keterangan").toString() + " </option>\n" );
			}

			sb.append("</select>");
			
		}catch(Exception e){
			e.printStackTrace();
		
		}				
		return sb.toString();
	
	}
	
	public void setCaraBayaran() throws Exception{
		try{
			db = new Db();
			Statement stmt = db.getStatement();

			sql = "SELECT A.ID_CARABAYAR, A.KOD_CARABAYAR, A.KETERANGAN ";
			sql += "FROM TBLRUJCARABAYAR A ORDER BY A.KETERANGAN ASC";

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable<String,String> hashCaraBayaran = null;
			caraBayaran = new Vector<Hashtable <String,String>>();
			while(rs.next()){
				hashCaraBayaran = new Hashtable<String,String>();
				hashCaraBayaran.put("idCaraBayar", rs.getString("ID_CARABAYAR") == null ? "" : rs.getString("ID_CARABAYAR"));
				hashCaraBayaran.put("kod", rs.getString("KOD_CARABAYAR") == null ? "" : rs.getString("KOD_CARABAYAR"));
				hashCaraBayaran.put("keterangan", rs.getString("KETERANGAN") == null ? "" : rs.getString("KETERANGAN"));
				caraBayaran.addElement(hashCaraBayaran);
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			db.close();
		}
		
	}
	
	public Vector<Hashtable <String,String>> getCaraBayaran(){
		return caraBayaran;
	}
	
	public void saveBayaran(String idPermohonan, Hashtable<String,String> hash, HttpSession session) throws Exception {
		String userId = session.getAttribute("_ekptg_user_id").toString();
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();	
			
			//TBLHTPBAYARAN
			long idBayaran = DB.getNextID("TBLHTPBAYARAN_SEQ");
			r.add("ID_BAYARAN", idBayaran);
			r.add("ID_PERMOHONAN", idPermohonan);	
			
			if (!"".equals(hash.get("tarikhTerima"))){
				r.add("TARIKH_TERIMA", r.unquote("to_date('" + hash.get("tarikhTerima") + "','dd/MM/yyyy')"));
			}
			if (!"".equals(hash.get("tarikhResit"))){
				r.add("TARIKH_RESIT", r.unquote("to_date('" + hash.get("tarikhResit") + "','dd/MM/yyyy')"));
			}
			if (!"".equals(hash.get("tarikhHantarResit"))){
				r.add("TARIKH_HANTAR_RESIT", r.unquote("to_date('" + hash.get("tarikhHantarResit") + "','dd/MM/yyyy')"));
			}
			if (!"".equals(hash.get("tarikhCek"))){
				r.add("TARIKH_CEK", r.unquote("to_date('" + hash.get("tarikhCek") + "','dd/MM/yyyy')"));
			}
			
			r.add("TUJUAN_BAYARAN", hash.get("tujuan"));
			r.add("ID_CARABAYAR",hash.get("caraBayar"));	
			r.add("NAMA_BANK", hash.get("namaBank"));
			r.add("NO_BAYARAN", hash.get("NoBayaran"));
			r.add("JUMLAH_BAYARAN", hash.get("jumlahBayaran"));
			r.add("NO_RESIT", hash.get("NoResit"));
			
			//fix bug. also save tujuan into other field
			r.add("ID_JENISBAYARAN", hash.get("tujuan"));
			
			r.add("ID_MASUK", userId);
			r.add("TARIKH_MASUK", r.unquote("SYSDATE"));

			sql = r.getSQLInsert("TBLHTPBAYARAN");
			log.info(sql);
			stmt.executeUpdate(sql);
			
			conn.commit();
			
		} catch (SQLException ex) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception("Rollback error : " + e.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());
	    	
	    } finally {
			if (db != null)
				db.close();
		}
		
	}
	public void simpanKemaskiniBayaran(String idPermohonan, Hashtable<String,String> hash) throws Exception {
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();	

			//TBLHTPBAYARAN			
			String idBayaran = String.valueOf(hash.get("idBayaran")); //default value -1
			log.info("idBayaran="+idBayaran);
			if(idBayaran.equals("-1")){
				r.add("ID_BAYARAN", String.valueOf(DB.getNextID("TBLHTPBAYARAN_SEQ")));
			}else
				r.update("ID_BAYARAN", idBayaran);
				
			log.info("idBayaran 1="+idBayaran);
			r.add("ID_PERMOHONAN", idPermohonan);	
			
			if (!"".equals(hash.get("tarikhTerima"))){
				r.add("TARIKH_TERIMA", r.unquote("to_date('" + hash.get("tarikhTerima") + "','dd/MM/yyyy')"));
			}
			if (!"".equals(hash.get("tarikhResit"))){
				r.add("TARIKH_RESIT", r.unquote("to_date('" + hash.get("tarikhResit") + "','dd/MM/yyyy')"));
			}
			if (!"".equals(hash.get("tarikhHantarResit"))){
				r.add("TARIKH_HANTAR_RESIT", r.unquote("to_date('" + hash.get("tarikhHantarResit") + "','dd/MM/yyyy')"));
			}
			if (!"".equals(hash.get("tarikhCek"))){
				r.add("TARIKH_CEK", r.unquote("to_date('" + hash.get("tarikhCek") + "','dd/MM/yyyy')"));
			}
			
			r.add("TUJUAN_BAYARAN", hash.get("tujuan"));
			r.add("ID_CARABAYAR",hash.get("caraBayar"));	
			r.add("NAMA_BANK", hash.get("namaBank"));
			r.add("NO_BAYARAN", hash.get("NoBayaran"));
			r.add("JUMLAH_BAYARAN", hash.get("jumlahBayaran"));
			r.add("NO_RESIT", hash.get("NoResit"));		
			//fix bug. also save tujuan into other field
			r.add("ID_JENISBAYARAN", hash.get("tujuan"));		

			if(idBayaran.equals("-1")){
				r.add("ID_MASUK", hash.get("userId"));
				r.add("TARIKH_MASUK", r.unquote("SYSDATE"));
				sql = r.getSQLInsert("TBLHTPBAYARAN");
				log.info(":"+sql);
				
			}else{
				r.add("ID_KEMASKINI", hash.get("userId"));
				r.add("TARIKH_KEMASKINI", r.unquote("SYSDATE"));
				sql = r.getSQLUpdate("TBLHTPBAYARAN");
				log.info("sql:"+sql);
				
			}	
			log.info("simpanKemaskiniBayaran:"+sql);
			stmt.executeUpdate(sql);		
			conn.commit();
			
		} catch (SQLException ex) { 
	    	try {
	    		conn.rollback();
	    	} catch (SQLException e) {
	    		throw new Exception("Rollback error : " + e.getMessage());
	    	}
	    	throw new Exception("Ralat : Masalah penyimpanan data " + ex.getMessage());
	    	
	    } finally {
			if (db != null)
				db.close();
		}
	}
	//
	public static String SelectTujuanBayar(String selectName, Long selectedValue, String disability, String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' ");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(">");

			sb.append("<option value=\"\">SILA PILIH</option>\n");
//			Vector v = DB.getTujuanBayar();
			Vector<Hashtable <String,String>> v = getTujuanBayar();
			Hashtable<String,String> h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Hashtable<String,String>();
				h = (Hashtable<String,String>) v.get(i);
				if (h.get("idJenisBayar").equals(String.valueOf(selectedValue))) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("idJenisBayar") + ">"
						+ h.get("kodJenisBayar") + " - " + h.get("keterangan") + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

		return sb.toString();
	}
 
	public static Vector<Hashtable <String,String>> getTujuanBayar() throws Exception {
//		String key = "DB.getTujuanBayar";
//		Element cachedObject = myCache.get(key);
//		if (cachedObject != null) {
//		  return (Vector)cachedObject.getObjectValue();
//		} else {
			Db db = null;		
			String sql = "SELECT A.ID_JENISBAYARAN, A.KOD_JENIS_BAYARAN, A.KETERANGAN ";
			sql += "FROM TBLRUJJENISBAYARAN A ";
			sql += "WHERE A.ID_SEKSYEN = 3 ORDER BY A.KOD_JENIS_BAYARAN ASC ";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Vector<Hashtable <String,String>> v = new Vector<Hashtable <String,String>>();
			Hashtable <String,String> h;
			while (rs.next()) {
				h = new Hashtable <String,String>();
				h.put("idJenisBayar",String.valueOf(rs.getLong("ID_JENISBAYARAN")));
				h.put("kodJenisBayar",String.valueOf(rs.getLong("KOD_JENIS_BAYARAN")));
				h.put("keterangan",rs.getString("KETERANGAN"));
				v.addElement(h);
			}
//			myCache.put(new Element(key, v));
			return v;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	
}
