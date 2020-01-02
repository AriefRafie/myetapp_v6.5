package ekptg.model.integrasi;

//import java.sql.Blob;
import java.io.StringWriter;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.commons.fileupload.FileItem;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import ekptg.helpers.DB;
//----------------------------------
//XML
//----------------------------------

public class FrmModelEKadaster {
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public String getNamaUserByID(String ID_USER) throws Exception {
		String returnValue = "";
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(ID_USER)) {
				Statement stmt = db.getStatement();
				ResultSet rs = null;
				String sql = "";
				sql = "SELECT USER_NAME FROM USERS WHERE USER_ID = " + ID_USER;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					returnValue = rs.getString(1) == null ? "" : rs.getString(1);
				}
			}
		} finally {
			if (db != null) db.close();
		}
		return returnValue;
	}
	
	public String getMIMEType(String FileName) throws Exception {
		String returnValue = "";
		Db db = new Db();
		
		try {
			String sql = "";
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			SQLRenderer r = new SQLRenderer();
			
			r.add("CONTENT_MIME");
			r.add("CONTENT_NAME", FileName);
			sql = r.getSQLSelect("TBLINTETERIMAAN");
			r.clear();
			
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				returnValue = rs.getString(1) == null ? "" : rs.getString(1);
			}
		} finally {
			if (db != null) db.close();
		}
		return returnValue;
	}
	
	public String getUploadID(String FileName) throws Exception {
		String returnValue = "";
		Db db = new Db();
		
		try {
			String sql = "";
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			SQLRenderer r = new SQLRenderer();
			
			r.add("ID_ETERIMAAN");
			r.add("CONTENT_NAME", FileName);
			sql = r.getSQLSelect("TBLINTETERIMAAN");
			r.clear();
			
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				returnValue = rs.getString(1) == null ? "" : rs.getString(1);
			}
		} finally {
			if (db != null) db.close();
		}
		return returnValue;
	}
	
	@SuppressWarnings("unchecked")
	public Vector getListUploadedFile() throws Exception {
		Vector v = new Vector();
		Db db = new Db();
		
		try {
			String sql = "";
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			Hashtable h = null;
			SQLRenderer r = new SQLRenderer();
			int i = 1;
			String IDFail = "", NamaFail = "", NamaPegawai = "", TarikhMuatNaik = "", SHA1 = "", MIME = "";
			
			r.add("ID_EKADASTER");
			r.add("CONTENT_NAME");
			r.add("USER_ID");
			r.add("TARIKH_KEMASKINI");
			r.add("SHA1");
			r.add("CONTENT_MIME");
			sql = r.getSQLSelect("TBLINTEKADASTER", "TARIKH_KEMASKINI DESC");
			r.clear();

			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				IDFail = rs.getString(1) == null ? "" : rs.getString(1);
				NamaFail = rs.getString(2) == null ? "" : rs.getString(2);
				NamaPegawai = rs.getString(3) == null ? "" : rs.getString(3);
				TarikhMuatNaik = rs.getDate(4) == null ? "" : sdf.format(rs.getDate(4));
				SHA1 = rs.getString(5) == null ? "" : rs.getString(5);
				MIME = rs.getString(6) == null ? "" : rs.getString(6);
				
				NamaPegawai = getNamaUserByID(NamaPegawai);
					
				h = new Hashtable();
				h.put("No", i);
				h.put("IDFail", IDFail);
				h.put("NamaFail", NamaFail);
				h.put("NamaPegawai", NamaPegawai);
				h.put("TarikhMuatNaik", TarikhMuatNaik);
				h.put("SHA1", SHA1);
				h.put("MIME", MIME);
				
				v.add(h);
				i++;
			}
		} finally {
			if (db != null) db.close();
		}
		return v;
	}
	
	public Boolean doUpload(FileItem objItem, String userID) throws Exception {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			String SHA1_Value = "";
			MessageDigest md = MessageDigest.getInstance("SHA1");

			md.update(objItem.get());
		    
		    byte[] mdbytes = md.digest();
		 
		    //convert the byte to hex format
		    StringBuffer sb = new StringBuffer("");
		    for (int i = 0; i < mdbytes.length; i++) {
		    	sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
		    }
		    SHA1_Value = sb.toString();
		    
			java.util.Date today = new java.util.Date();
			java.sql.Date date = new java.sql.Date(today.getTime());
			long ID_DB = DB.getNextID("TBLINTEKADASTER_SEQ");
        	Connection con = db.getConnection();
        	con.setAutoCommit(false);
        	PreparedStatement ps = con.prepareStatement("INSERT INTO TBLINTEKADASTER " +
        			"(ID_EKADASTER, TARIKH_SIMPAN, TARIKH_KEMASKINI, USER_ID, CONTENT, CONTENT_NAME, CONTENT_MIME, SHA1) " +
        			"VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        	ps.setLong(1, ID_DB);
			ps.setDate(2, date);
			ps.setDate(3, date);
        	ps.setString(4, userID);
        	ps.setBinaryStream(5, objItem.getInputStream(), (int)objItem.getSize());
        	ps.setString(6, objItem.getName());
        	ps.setString(7, objItem.getContentType());
        	ps.setString(8, SHA1_Value);
        	ps.executeUpdate();
            con.commit();
            
            returnValue = true;
		} finally {
			if (db != null)
				db.close();
		}
		return returnValue;
	}
	
	public Boolean processFile(String IDFail) throws Exception {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(IDFail)) {
		        Connection con = db.getConnection();
		        PreparedStatement ps = con.prepareStatement("SELECT CONTENT, CONTENT_MIME FROM TBLINTEKADASTER WHERE ID_EKADASTER = ?");
		        ps.setString(1, IDFail);
		        ResultSet rs = ps.executeQuery();
		        if (rs.next()) {
		        	//Blob b = rs.getBlob("CONTENT");
			        //String s = new String(b.getBytes(1l, (int) b.length()));
			        //System.out.println(s);
			        ps.execute("UPDATE TBLINTEKADASTER SET STATUS_PROSES = 'SELESAI' WHERE ID_EKADASTER = " + IDFail);
			        returnValue = true;
		        }
			}
		} finally {
			if (db != null) db.close();
		}
        return returnValue;
	}
	
	public Boolean deleteFile(String IDFail) throws Exception {
		Boolean returnValue = false;
		Db db = new Db();
		
		try {
			if (!"".equalsIgnoreCase(IDFail)) {
				Statement stmt = db.getStatement();
				String sql = "";
				sql = "DELETE FROM TBLINTEKADASTER WHERE ID_EKADASTER = " + IDFail;
				stmt.execute(sql);
				returnValue = true;
			}
		} finally {
			if (db != null) db.close();
		}
        return returnValue;
	}
	
	public String generateXML(String IDFail) throws Exception {
		String returnValue = "";
		Db db = new Db();
		
		try {
			Statement stmt = db.getStatement();
			ResultSet rs = null;
			String sql = "";
			
			if (!"".equalsIgnoreCase(IDFail)) {
				String Tarikh = "", FileName = "", HashValue = "";
				sql = "SELECT TO_CHAR(SYSDATE, 'YYYY/MM/DD/HH24:MI:SS'), CONTENT_NAME, SHA1 FROM TBLINTEKADASTER " + 
					"WHERE ID_EKADASTER = " + IDFail;
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					Tarikh = rs.getString(1) == null ? "" : rs.getString(1);
					FileName = rs.getString(2) == null ? "" : rs.getString(2);
					HashValue = rs.getString(3) == null ? "" : rs.getString(3);
				}
				
				DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
	            DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
	            Document doc = docBuilder.newDocument();
	            	            
	            Element root = doc.createElement("MyTrustJupemPKI");
	            doc.appendChild(root);
	            
//	            Comment comment = doc.createComment("Just a thought");
//	            root.appendChild(comment);
	            
	            Element childDT = doc.createElement("DateTime");
//	            child.setAttribute("name", "value");
	            root.appendChild(childDT);	            
	            Text textDT = doc.createTextNode(Tarikh);
	            childDT.appendChild(textDT);

	            Element childFI = doc.createElement("FileInfo");
	            root.appendChild(childFI);
	            Element childFN = doc.createElement("Filename");
	            childFI.appendChild(childFN);
	            Text textFN = doc.createTextNode(FileName);
	            childFN.appendChild(textFN);
	            Element childHA = doc.createElement("HashAlgorithm");
	            childFI.appendChild(childHA);
	            Text textHA = doc.createTextNode("sha1");
	            childHA.appendChild(textHA);
	            Element childHV = doc.createElement("HashValue");
	            childFI.appendChild(childHV);
	            Text textHV = doc.createTextNode(HashValue);
	            childHV.appendChild(textHV);

	            TransformerFactory transfac = TransformerFactory.newInstance();
	            Transformer trans = transfac.newTransformer();
	            trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
	            trans.setOutputProperty(OutputKeys.INDENT, "yes");
	            
	            StringWriter sw = new StringWriter();
	            StreamResult result = new StreamResult(sw);
	            DOMSource source = new DOMSource(doc);
	            trans.transform(source, result);

	            returnValue = sw.toString();
			}
		} finally {
			if (db != null) db.close();
		}
		return returnValue;
	}
}