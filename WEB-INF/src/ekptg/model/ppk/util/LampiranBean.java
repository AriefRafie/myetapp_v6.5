package ekptg.model.ppk.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import ekptg.helpers.Utils;

public class LampiranBean {
	private static Logger myLog = Logger.getLogger(ekptg.model.ppk.util.LampiranBean.class);
	Connection conn = null;
	
	public String getLampiransHA(String idHarta) throws Exception {
		StringBuffer sb = new StringBuffer("");
		Vector<Hashtable<String, String>> dokumens = lampiranMengikutHarta(idHarta, null,true);
		for (int i = 0; i < dokumens.size(); i++) {
			Hashtable<String, String> mo = (Hashtable<String, String>) dokumens.get(i);			
			sb.append("<a class=\"style4\" href=\"javascript:lampiranHartaPapar("+mo.get("idDokumen")+")\"");
			sb.append(" onclick=\"lampiranHartaPapar("+mo.get("idDokumen")+"); return false;\"");
			sb.append(" onkeypress=\"window.open(this.href); return false;\">"); 
			//sb.append(" onclick=\"cetakImej("+mo.get("idDokumen")+"); return false;\""); 
			sb.append(mo.get("namaFail"));
			if(dokumens.size()==1 || (i == (dokumens.size()-1) && dokumens.size() != 1) )
				sb.append(" </a>");
			else
				sb.append(" </a>,");
			sb.append("<br>");

		}
		return sb.toString();
		
	}
	public String getLampirans(String idHarta) throws Exception {
		StringBuffer sb = new StringBuffer("");
		Vector<Hashtable<String, String>> dokumens = lampiranMengikutHarta(idHarta, null,false);
		for (int i = 0; i < dokumens.size(); i++) {
			Hashtable<String, String> mo = (Hashtable<String, String>) dokumens.get(i);			
			sb.append("<a class=\"style42\" href=\"javascript:lampiranHartaPapar("+mo.get("idDokumen")+")\"");
			sb.append(" onclick=\"lampiranHartaPapar("+mo.get("idDokumen")+"); return false;\"");
			sb.append(" onkeypress=\"window.open(this.href); return false;\">"); 
			//sb.append(" onclick=\"cetakImej("+mo.get("idDokumen")+"); return false;\""); 
			sb.append(mo.get("namaFail"));
			if(dokumens.size()==1 || (i == (dokumens.size()-1) && dokumens.size() != 1) )
				sb.append(" </a>");
			else
				sb.append(" </a>,");
//			<a class="opener" href="javascript:deleteDetailImej($!mo.idDokumen,$!mo.idLampiran)" 
//				onclick="deleteDetailImej($!mo.idDokumen,$!mo.idLampiran); return false;">
//				<img src="../img/online/x.gif" alt="hapus" width="20" height="15"/>
//			</a>
			sb.append("<br>");

		}
		return sb.toString();
		
	}

	public Vector<Hashtable<String, String>> lampiranMengikutHarta(String id, String iDokumen,boolean isHA) 
		throws Exception {
		Db db = null;
		String sql = "";
		Vector<Hashtable<String, String>> listLampiran = new Vector<Hashtable<String, String>>();
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("D.ID_DOKUMEN");
			r.add("D.NAMA_DOKUMEN");
			r.add("D.FORMAT");
			if(isHA){
				r.add("D.ID_HA");
				r.add("D.ID_HA",id);
			}else{
				r.add("D.ID_HTA");
				r.add("D.ID_HTA",id);
			}
			if(iDokumen != null){
				r.add("D.DOKUMEN",iDokumen);
			}
			if(isHA)
				sql = r.getSQLSelect("TBLPPKDOKUMENHA D");
			else
				sql = r.getSQLSelect("TBLPPKDOKUMENHTA D");
			
			//myLog.info(sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable<String, String> h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable<String, String>();
				h.put("bil",String.valueOf(bil));
				h.put("idDokumen",rs.getString("id_dokumen"));
				h.put("namaFail", Utils.isNull(rs.getString("nama_dokumen")));
				h.put("jenisMime", Utils.isNull(rs.getString("format")));
				listLampiran.addElement(h);
				bil++;
		      
			}

		} finally {
			if (db != null) db.close();
		}
		return listLampiran;
		    
	 }	 

	public void hapusLampiran(String idDokumen,String idLampiran,boolean isHA) throws Exception {
		//myLog.info("hapusLampiran");
		Db db = null;
		Connection conn = null;
		String sql = "";
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();	
			//Tblpfdrujlampiran a,Tblpfddokumen			
			//TBLHTPGAMBAR
			r.add("ID_DOKUMEN", idDokumen);
			if(isHA)
				sql = r.getSQLDelete("TBLPPKDOKUMENHA");
			else
				sql = r.getSQLDelete("TBLPPKDOKUMENHTA");
			
			myLog.info("hapusLampiran:Tblpfddokumen::sql="+sql);
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
	
	public void saveData(String idHarta,String idUser,FileItem item,boolean isHA) throws Exception {
		Db db = null;
		String sql="";
		try {
			db = new Db();
			// TBLPPKDOKUMENHTA
			if(isHA){
				sql = "INSERT INTO TBLPPKDOKUMENHA "
						+ "(ID_HA,NAMA_DOKUMEN,ID_MASUK,TARIKH_MASUK,KANDUNGAN,FORMAT) " + "VALUES(?,?,?,SYSDATE,?,?)";
			}else{
				sql = "INSERT INTO TBLPPKDOKUMENHTA "
						+ "(ID_HTA,NAMA_DOKUMEN,ID_MASUK,TARIKH_MASUK,KANDUNGAN,FORMAT) " + "VALUES(?,?,?,SYSDATE,?,?)";
					
			}
			Connection con = db.getConnection();
			con.setAutoCommit(false);
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, idHarta);
			ps.setString(2, item.getName());
			ps.setString(3, idUser);
			ps.setBinaryStream(4, item.getInputStream(), (int) item.getSize());
			ps.setString(5, item.getContentType());
			//myLog.info("saveData:sql="+ps.toString());
			ps.executeUpdate();

			con.commit();

		} finally {
			if (db != null)
				db.close();
		}
		
	}
	
	public void lampiranSimati(HttpServletRequest request
		,String idRujukan
		,String jenisDok
		,String idUser) throws Exception {
	    DiskFileItemFactory factory = new DiskFileItemFactory();
	    ServletFileUpload upload = new ServletFileUpload(factory);
	    List items = upload.parseRequest(request);
	    Iterator itr = items.iterator();	   
	    while (itr.hasNext()) {    	
	      FileItem item = (FileItem)itr.next();
	      //myLog.info("item.getName()="+item.getName());
	      if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {	    	  
	    	  simpanLampiranSimati("TBLPPKDOKUMENSIMATI",idRujukan,item,jenisDok,idUser);
	      }
	    }
	  }
	
	public void simpanLampiranSimati(String namaTable
		,String idRujukan
		,FileItem item
		,String jenisDok
		,String idUser) throws Exception {
		Db db = null;
		String sql="";
		try {
			db = new Db();
			sql = "INSERT INTO "+namaTable
					+ " (NO_RUJUKAN,ID_JENISDOKUMEN,NAMA_DOKUMEN,FORMAT,SAIZ,KANDUNGAN,ID_MASUK,TARIKH_MASUK) " 
					+ "VALUES(?,"+jenisDok+",?,?,?,?,?,SYSDATE)";
			Connection con = db.getConnection();
			con.setAutoCommit(false);
			PreparedStatement ps = con.prepareStatement(sql);
			myLog.info("simpanLampiranSimati:sql="+ps.toString());
		ps.setString(1, idRujukan);
		myLog.info("simpanLampiranSimati2:sql="+ps.toString());
		ps.setString(2, item.getName());
			ps.setString(3, item.getContentType());
			myLog.info("simpanLampiranSimati3:sql="+ps.toString());
		ps.setLong(4, item.getSize());
			ps.setBinaryStream(5, item.getInputStream(), (int) item.getSize());
			myLog.info("simpanLampiranSimati4:sql="+ps.toString());
	ps.setString(6, idUser);
			//
			myLog.info("simpanLampiranSimati5:sql="+ps.toString());
			ps.executeUpdate();

			con.commit();

		} finally {
			if (db != null)
				db.close();
		}
		
	}
	
	public Vector<Hashtable<String, String>> getLampiranSimati(String id, String iDokumen,String jenisDokumen) 
		throws Exception {
		Db db = null;
		String sql = "";
		Vector<Hashtable<String, String>> listLampiran = new Vector<Hashtable<String, String>>();
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("D.ID_DOKUMEN");
			r.add("D.NAMA_DOKUMEN");
			r.add("D.FORMAT");
			r.add("D.NO_RUJUKAN",id);
			r.add("D.ID_JENISDOKUMEN",jenisDokumen);
			if(iDokumen != null){
				r.add("D.ID_DOKUMEN",iDokumen);
			}
			sql = r.getSQLSelect("TBLPPKDOKUMENSIMATI D");
			//myLog.info(sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable<String, String> h;
			int bil = 1;
			while (rs.next()) {
				h = new Hashtable<String, String>();
				h.put("bil",String.valueOf(bil));
				h.put("idDokumen",rs.getString("id_dokumen"));
				h.put("namaFail", Utils.isNull(rs.getString("nama_dokumen")));
				h.put("jenisMime", Utils.isNull(rs.getString("format")));
				listLampiran.addElement(h);
				bil++;
			      
			}

		} finally {
			if (db != null) db.close();
		}
		return listLampiran;
			    
	}	
	
	public Vector<Hashtable<String, String>> getLampiranSimatii(String idSimati, String iDokumen,String jenisDokumen) 
			throws Exception {
			Db db = null;
			String sql = "";
			Vector<Hashtable<String, String>> listLampiran = new Vector<Hashtable<String, String>>();
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				r.add("D.ID_DOKUMEN");
				r.add("D.NAMA_DOKUMEN");
				r.add("D.FORMAT");
				r.add("D.NO_RUJUKAN");
				r.add("D.ID_SIMATI",idSimati);
				r.add("D.ID_JENISDOKUMEN",jenisDokumen);
				if(iDokumen != null){
					r.add("D.ID_DOKUMEN",iDokumen);
				}
				sql = r.getSQLSelect("TBLPPKDOKUMENSIMATI D");
				//myLog.info(sql);
				ResultSet rs = stmt.executeQuery(sql);
				Hashtable<String, String> h;
				int bil = 1;
				while (rs.next()) {
					h = new Hashtable<String, String>();
					h.put("bil",String.valueOf(bil));
					h.put("idDokumen",rs.getString("id_dokumen"));
					h.put("namaFail", Utils.isNull(rs.getString("nama_dokumen")));
					h.put("jenisMime", Utils.isNull(rs.getString("format")));
					listLampiran.addElement(h);
					bil++;
				      
				}

			} finally {
				if (db != null) db.close();
			}
			return listLampiran;
				    
		}
	
	public void hapusLampiranSimati(String idDokumen,String idLampiran,boolean isHA) throws Exception {
		//myLog.info("hapusLampiran");
		Db db = null;
		Connection conn = null;
		String sql = "";
		try {
			db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();	
			//Tblpfdrujlampiran a,Tblpfddokumen			
			//TBLHTPGAMBAR
			r.add("ID_DOKUMEN", idDokumen);
//			if(isHA)
//				sql = r.getSQLDelete("TBLPPKDOKUMENHA");
//			else
				sql = r.getSQLDelete("TBLPPKDOKUMENSIMATI");
			
			myLog.info("hapusLampiran:TBLPPKDOKUMENSIMATI::sql="+sql);
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
	
	public String getLampiranSimatiPapar(String idRujukan,String idJenis) throws Exception {
		StringBuffer sb = new StringBuffer("");
		Vector<Hashtable<String, String>> dokumens = getLampiranSimati(idRujukan,null,idJenis);
		for (int i = 0; i < dokumens.size(); i++) {
			Hashtable<String, String> mo = (Hashtable<String, String>) dokumens.get(i);			
			sb.append("<a class=\"style4\" href=\"javascript:paparLampiran("+mo.get("idDokumen")+")\"");
			sb.append(" onclick=\"paparLampiran("+mo.get("idDokumen")+"); return false;\"");
			sb.append(" onkeypress=\"window.open(this.href); return false;\">"); 
			//sb.append(" onclick=\"cetakImej("+mo.get("idDokumen")+"); return false;\""); 
			sb.append("<div class=\"pautan\">"+mo.get("namaFail")+"</div>");
			if(dokumens.size()==1 || (i == (dokumens.size()-1) && dokumens.size() != 1) )
				sb.append(" </a>");
			else
				sb.append(" </a>,");
			sb.append("<br>");

		}
		return sb.toString();
		
	}
	
	public String getLampiranSimatiPapari(String idRujukan,String idJenis) throws Exception {
		StringBuffer sb = new StringBuffer("");
		Vector<Hashtable<String, String>> dokumens = getLampiranSimatii(idRujukan,null,idJenis);
		for (int i = 0; i < dokumens.size(); i++) {
			Hashtable<String, String> mo = (Hashtable<String, String>) dokumens.get(i);			
			sb.append("<a class=\"style4\" href=\"javascript:paparLampiran("+mo.get("idDokumen")+")\"");
			sb.append(" onclick=\"paparLampiran("+mo.get("idDokumen")+"); return false;\"");
			sb.append(" onkeypress=\"window.open(this.href); return false;\">"); 
			//sb.append(" onclick=\"cetakImej("+mo.get("idDokumen")+"); return false;\""); 
			sb.append(mo.get("namaFail"));
			if(dokumens.size()==1 || (i == (dokumens.size()-1) && dokumens.size() != 1) )
				sb.append(" </a>");
			else
				sb.append(" </a>,");
			sb.append("<br>");

		}
		return sb.toString();
		
	}


	
}
