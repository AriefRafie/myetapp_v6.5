package ekptg.model.php2.utiliti;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.Ostermiller.util.Base64;

import ekptg.helpers.DB;
import ekptg.helpers.Utils;
import ekptg.model.entities.Tblrujdokumen;
import ekptg.model.utils.Fungsi;
import ekptg.model.utils.lampiran.ILampiran;

public class LampiranBean implements ILampiran{
	private static Logger myLog = Logger.getLogger(ekptg.model.php2.utiliti.LampiranBean.class);
	Connection conn = null;
	Vector<Hashtable<String, String>> dokumens = null;
	Vector<Tblrujdokumen> lampiran = null;
	public StringBuffer sb = new StringBuffer("");

	public String getLampirans(String idRujukan,String jenisDokumen,String js) throws Exception {
		sb = new StringBuffer("");
		lampiran = getLampirans(idRujukan,jenisDokumen);
		for (int i = 0; i < lampiran.size(); i++) {
			Tblrujdokumen mo = lampiran.get(i);	
			if(!js.equals(""))
				sb.append("<a href=\"javascript:"+js+"("+mo.getIdDokumen()+")\"");
			else
				sb.append("<a href=\"javascript:paparLampiran("+mo.getIdDokumen()+")\"");

			sb.append(" onkeypress=\"window.open(this.href); return false;\">"); 
			sb.append("<div class=\"pautan\">"+mo.getNamaDokumen()+"</div>");

			if(lampiran.size()==1 || (i == (lampiran.size()-1) && lampiran.size() != 1) )
				sb.append(" </a>");
			else
				sb.append(" </a>,");
			sb.append("<br>");

		}
		return sb.toString();
		
	}
	
	public Vector<Tblrujdokumen> getLampirans(String idRujukan,String jenisDokumen) throws Exception {				
	    Db db = null;
	    String sql = "";			  	    
	    Vector<Tblrujdokumen> listDokumen_bantahan = new Vector<Tblrujdokumen>();
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      //   "(id_dokumen,id_permohonan,nama_fail,jenis_mime,content,nama_dokumen) " +
	      sql = " SELECT "+
	      		" A.ID_PERMOHONAN,A.ID_DOKUMEN,A.NAMA_DOKUMEN JENIS_DOKUMEN,A.NAMA_FAIL,A.JENIS_MIME"+ 
				" ,A. CATATAN KETERANGAN,A.CONTENT "+
				" FROM TBLPHPDOKUMEN A"+
				" WHERE A.ID_PERMOHONAN = '"+idRujukan+"' "+
	    		" AND A.NAMA_DOKUMEN = '"+jenisDokumen+"' "+
				"";
//	      myLog.info("getLampirans :sql="+sql);
	      ResultSet rs = stmt.executeQuery(sql);   
	      Tblrujdokumen h = null;
	      int bil = 0;
	    
	      while (rs.next()) {    	
	    	  bil = bil + 1;
	    	  h = new Tblrujdokumen();
	    	  h.setIdDokumen(rs.getString("ID_DOKUMEN")== null?"":rs.getString("ID_DOKUMEN"));
	    	  h.setNamaDokumen(rs.getString("NAMA_FAIL")== null?"":rs.getString("NAMA_FAIL"));
	    	  h.setDokumen("");
	    	  h.setCatatan(rs.getString("KETERANGAN")== null?"":rs.getString("KETERANGAN"));
	    	  
	       	  listDokumen_bantahan.addElement(h);
	    	  
	      }		      
	      
	    } finally {
	      if (db != null) db.close();
	    }	
	    return listDokumen_bantahan;
	    
	}
	
	public void simpan(FileItem item,HttpServletRequest request) throws Exception {
  		Db db = null;
        try {
        	db = new Db();
        	long id_Dokumen = DB.getNextID("TBLPHPDOKUMEN_SEQ");
        	Connection con = db.getConnection();
        	con.setAutoCommit(false);
//        	ID_DOKUMEN" NUMBER NOT NULL ENABLE, 
//        	"ID_PERMOHONAN" NUMBER, 
//        	"NAMA_FAIL" VARCHAR2(200), 
//        	"JENIS_MIME" VARCHAR2(100), 
//        	"CONTENT" BLOB,        	
//        	"NAMA_DOKUMEN" VARCHAR2(200), 

//        	"CATATAN" VARCHAR2(500), 
//        	"ID_LAPORANTANAH" NUMBER, 
//        	"TARIKH_SURAT" VARCHAR2(10), 
//        	"TARIKH_TERIMA" VARCHAR2(10), 
//        	"NO_RUJ_SURAT" VARCHAR2(10), 
//        	"ID_ULASANTEKNIKAL" NUMBER, 
//        	"FLAG_DOKUMEN" VARCHAR2(2), 
//        	"ID_MESYUARAT" NUMBER, 
//        	"ID_PENAWARANKJP" NUMBER(16,0), 
//        	"ID_PHPHAKMILIK" NUMBER(16,0), 
        	PreparedStatement ps = con.prepareStatement("insert into tblphpdokumen " +
        			"(id_dokumen,id_permohonan,nama_fail,jenis_mime,content,nama_dokumen) " +
        			"values(?,?,?,?,?,?)");
        	ps.setLong(1, id_Dokumen);
        	ps.setString(2, request.getParameter("rujukan"));
           	myLog.info("simpan:sql="+ps.toString());
           	ps.setString(3,item.getName());
        	ps.setString(4,item.getContentType());
        	ps.setBinaryStream(5,item.getInputStream(),(int)item.getSize());
        	ps.setString(6, request.getParameter("jenisdokumen"));  
        	myLog.info("simpan:sql="+ps.toString());
        	ps.executeUpdate();
            con.commit();
            
	    }catch (SQLException se) { 
	    	throw new Exception("Ralat : Masalah muatnaik fail="+se.getMessage());
	    }finally {
		      if (db != null) db.close();
	    }
        
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
	public void simpanDokumenInt(Tblrujdokumen data) throws Exception {		
		Db db = null;
		String sql="";
		try {
				db = new Db();
				sql = "INSERT INTO TBLINTMTPENDAFTARANDOK "
						+ " (ID_RUJUKAN,ID_DOKUMEN,ID_MASUK,TARIKH_MASUK) " 
						+ "VALUES(?,?,?,SYSDATE)";
				Connection con = db.getConnection();
				con.setAutoCommit(false);
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, data.getDokumen());
				ps.setString(2, data.getIdDokumen());
				ps.setLong(3, data.getIdMasuk());
				//myLog.info("saveData:sql="+ps.toString());
				ps.executeUpdate();

				con.commit();

		} finally {
			if (db != null)
				db.close();
		}
				
	}
	
	@Override
	public Tblrujdokumen getLampiran(String iDokumen) throws Exception {
		return getLampiran(iDokumen
				,null,null,null
				,null);
	}
	
	@Override
	public Tblrujdokumen getLampiran(String iDokumen
		,String colNama,String colJenis,String colCon
		,String table) throws Exception {
			Db db = null;
			String sql = "";
			Tblrujdokumen dokumen = null;
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				SQLRenderer r = new SQLRenderer();
				r.add("ID_DOKUMEN");
				if(colNama==null)
					r.add("NAMA_FAIL");
				else
					r.add(colNama);
				
				if(colJenis==null)
					r.add("JENIS_MIME");
				else
					r.add(colJenis);
				
				if(colCon==null)
					r.add("CONTENT");
				else
					r.add(colCon);
				r.add("ID_DOKUMEN",iDokumen);
				
				if(table==null)
					sql = r.getSQLSelect("TBLPPTDOKUMEN");
				else
					sql = r.getSQLSelect(table);

				//myLog.info(sql);
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					dokumen = new Tblrujdokumen();
					dokumen.setIdDokumen(iDokumen);
					if(colNama==null)
						dokumen.setNamaDokumen(rs.getString("NAMA_FAIL"));
					else
						dokumen.setNamaDokumen(rs.getString(colNama));
					if(colJenis==null)
						dokumen.setNamaDokumen(rs.getString("JENIS_MIME"));
					else
						dokumen.setNamaDokumen(rs.getString(colJenis));
					
					Blob  b = null;
					if(colCon==null){
						b = rs.getBlob("CONTENT");
						InputStream is = b.getBinaryStream();
						byte [] b2 = IOUtils.toByteArray(is);
						dokumen.setKandungan(Base64.encodeToString(b2));
						
					}else{
						b = rs.getBlob(colCon);
						InputStream is = b.getBinaryStream();
						byte [] b2 = IOUtils.toByteArray(is);
						dokumen.setKandungan(Base64.encodeToString(b2));
					
					}
			      
				}

			} finally {
				if (db != null) db.close();
			}
			return dokumen;
			    
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

	public void hapus(String idDokumen) throws Exception {
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
			sql = r.getSQLDelete("TBLPHPDOKUMEN");
			
			myLog.info("hapusLampiran:tblphpdokumen::sql="+sql);
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
	
	public String javascriptUpload(String jsUpload,String jsPapar
			,String idDokumen,HttpSession session,String skrin) throws Exception {
			Fungsi.setWin800600();
			
			sb = new StringBuffer("");
			sb.append("<script>");
			if(jsUpload.equals(""))
				sb.append("function paparLampiran(idDokumen){");
			else
				sb.append("function "+jsPapar+"(idDokumen){");

			//sb.append("function "+jsPapar+"(){");
			sb.append("var url = '../servlet/ekptg.model.utils.DisplayBlob?id='+idDokumen+'&tablename=tblphpdokumen';");
			sb.append("var hWnd=window.open(url,'Cetak','width="+Fungsi.lebar+",height="+Fungsi.tinggi+", resizable=yes,scrollbars=yes,menubar=1');");
			sb.append("if ((document.window != null) && (!hWnd.opener))");
			sb.append("hWnd.opener=document.window;");
			sb.append("if (hWnd.focus != null) hWnd.focus();");
			sb.append("}");
			
			Fungsi.setWin400300();
			if(jsUpload.equals(""))
				sb.append("function onlineAttach(idPermohonan,idSenarai,idJenisDokumen) {");
			else
				sb.append("function "+jsUpload+"(idPermohonan,idSenarai,idJenisDokumen) {");
		
			sb.append("param = 'actionrefresh=php"+skrin+"&actionPopup=papar&idPermohonan=&flagOnline=$!flagOnline';");
//			sb.append("param = 'actionrefresh=phpapb&actionPopup=papar&idPermohonan=&flagOnline=$!flagOnline';");
			sb.append("param += '&rujukan='+idPermohonan+'&jenisdokumen='+idJenisDokumen+'&idsenarai='+idSenarai;");
			sb.append("var url = '../x/"+session.getAttribute("securityToken")+"/ekptg.view.online.UploadDokumenSemak?'+param;");
			sb.append("var hWnd = window.open(url,'printuser','width="+Fungsi.lebar+",height="+Fungsi.tinggi+", resizable=yes,scrollbars=yes');");
			sb.append("if ((document.window != null) && (!hWnd.opener))");
			sb.append("hWnd.opener=document.window;");
			sb.append("if (hWnd.focus != null) hWnd.focus();");
			sb.append("}");
			//sb.append(javascriptUpload(sb));
			sb.append("</script>");
			return sb.toString();
		 
	}
	
	public String javascriptUpload(String jsUpload,String jsPapar
		,String idDokumen,HttpSession session) throws Exception {
		Fungsi.setWin800600();
		
		sb = new StringBuffer("");
		sb.append("<script>");
		if(jsUpload.equals(""))
			sb.append("function paparLampiran(idDokumen){");
		else
			sb.append("function "+jsPapar+"(idDokumen){");

		sb.append("var url = '../servlet/ekptg.model.utils.DisplayBlob?id='+idDokumen+'&tablename=tblphpdokumen';");
		sb.append("var hWnd=window.open(url,'Cetak','width="+Fungsi.lebar+",height="+Fungsi.tinggi+", resizable=yes,scrollbars=yes,menubar=1');");
		sb.append("if ((document.window != null) && (!hWnd.opener))");
		sb.append("hWnd.opener=document.window;");
		sb.append("if (hWnd.focus != null) hWnd.focus();");
		sb.append("}");
		Fungsi.setWin400300();

		sb.append("function onlineAttach(idPermohonan,idSenarai,idJenisDokumen) {");
		sb.append("param = 'actionrefresh=phpapb&actionPopup=papar&idPermohonan=&flagOnline=$!flagOnline';");
//		sb.append("param = 'actionrefresh=phpapb&actionPopup=papar&idPermohonan=&flagOnline=$!flagOnline';");
		sb.append("param += '&rujukan='+idPermohonan+'&jenisdokumen='+idJenisDokumen+'&idsenarai='+idSenarai;");
		sb.append("var url = '../x/"+session.getAttribute("securityToken")+"/ekptg.view.online.UploadDokumenSemak?'+param;");
		sb.append("var hWnd = window.open(url,'printuser','width="+Fungsi.lebar+",height="+Fungsi.tinggi+", resizable=yes,scrollbars=yes');");
		sb.append("if ((document.window != null) && (!hWnd.opener))");
		sb.append("hWnd.opener=document.window;");
		sb.append("if (hWnd.focus != null) hWnd.focus();");
		sb.append("}");
		//sb.append(javascriptUpload(sb));
		sb.append("</script>");
		return sb.toString();
	 
	}
	public String javascriptUpload(StringBuffer sb) {
		//sb = new StringBuffer("");
		sb.append("if ((document.window != null) && (!hWnd.opener))");
		sb.append("hWnd.opener=document.window;");
		sb.append("if (hWnd.focus != null) hWnd.focus();");
		sb.append("}");
		return sb.toString();
	}
	
	
}
//20200822