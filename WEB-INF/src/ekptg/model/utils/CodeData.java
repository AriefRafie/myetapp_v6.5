package ekptg.model.utils;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

public class CodeData {

	static Logger myLogger = Logger.getLogger(CodeData.class);
	
	private String tablename;
	private String nicename;
	private String[] fields;
	private String[] nicefields;
	private String[] fieldSize;
	private String orderby;
	private String[] isLookup;
	private Vector v = null;
	private String ID = null;
	private String[] fieldLength;
	private Hashtable jantina;
	
	/////////////
	private String lookup_desc = null;
	private String lookup_tablename = null;
	
	public CodeData() {
		  
	}
	 
	public CodeData(String tablename,String nicename,String fields,String nicefields,
			String orderby,String isLookup,String fieldLength) {
		this.tablename = tablename;
		this.nicename = nicename;
		this.fields = toArray(fields);
		this.nicefields = toArray(nicefields);
		this.orderby = orderby;
		this.isLookup = toArray(isLookup);
		this.fieldLength = toArray(fieldLength);
	}
		
	//Setter & Getter
	
	
	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public String getNicename() {
		return nicename;
	}

	public void setNicename(String nicename) {
		this.nicename = nicename;
	}

	public String[] getFields() {
		return fields;
	}

	public void setFields(String[] fields) {
		this.fields = fields;
	}

	public String[] getNicefields() {
		return nicefields;
	}

	public void setNicefields(String[] nicefields) {
		this.nicefields = nicefields;
	}

	public String[] getFieldSize() {
		return fieldSize;
	}

	public void setFieldSize(String[] fieldSize) {
		this.fieldSize = fieldSize;
	}

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	public String[] getIsLookup() {
		return isLookup;
	}

	public void setIsLookup(String[] isLookup) {
		this.isLookup = isLookup;
	}

	public String[] getFieldLength() {
		return fieldLength;
	}

	public void setFieldLength(String[] fieldLength) {
		this.fieldLength = fieldLength;
	}

	public String getID() {
		return ID;
	}

	public void setID(String id) {
		ID = id;
	}

	////////////////
	public int getFieldCount() {
		 return this.fields == null ? 0 : this.fields.length - 1;
	}

	public int getRecordCount(){
	  if (v != null) return v.size();
	  else return 0;
	}
	  
	public Vector getList()
	    throws Exception
	  {
	    Db db = null;
	    String sql = "";
	    //String[] temp;
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      String[] temp = this.fields;
	      for (int i =0; i < temp.length ; i++) {
	    	  r.add(temp[i]);
	      }
	      sql = r.getSQLSelect(this.tablename);
	      if (this.ID != null) sql = sql + " WHERE "+temp[0]+ " = '" + this.ID + "'"; 
	      if (this.orderby != null)  sql = sql + " ORDER BY "+this.orderby;
	      //myLogger.info(sql);
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      v = new Vector();
	      while (rs.next()) {
	        Hashtable h = new Hashtable();
		      for (int i =0; i < temp.length ; i++) {
		    	  if (i == 0) { // reserve for first field - should be an ID
		    		  h.put(i,checkIsNull(rs.getString(temp[i]))); 
		    	  //}else if (temp[i].indexOf("id_") != -1 || "jantina".equals(temp[i])) {
		    	  } else if ( isLookupByFieldName(temp[i]) ) {
		    		  h.put(i,temp[i]+"@@"+checkIsNull(rs.getString(temp[i])));
		    	  } else {
		    		  h.put(i,checkIsNull(rs.getString(temp[i])));
		    	  }
		    	  //h.put(temp[i], checkIsNull(rs.getString(temp[i])));
//		    	  h.put(i, checkIsNull(rs.getString(temp[i])));
//		    	  if (temp[i].indexOf("id_") != -1 || "jantina".equals(temp[i])) {
//		    		  h.put("isLookup"+i,temp[i]);
//		    	  } else {
//		    		  h.put("isLookup"+i,"false");
//		    	  }
		      }
	        v.addElement(h);
	      }
	      return v;
	    } catch (Exception e) {
	    	myLogger.info(e.getMessage());
	    	return null;
	    }
	    
	    finally {
	      if (db != null) db.close();
	    }
	  }
	  
	  
	  public void update(String tablename,Hashtable parameters,
			  String id_fieldname,String id) {
		  
		  String sql="";
		  String name="";
		  String value="";
		  if (id != null) {
			  sql = "Update "+tablename+ " SET ";
			  int x = 1;
			  for (Enumeration e = parameters.keys(); e.hasMoreElements();x++)
			   {
				  name = (String)e.nextElement();
				  value = (String)parameters.get(name);
				  sql = sql + name.replace("Form_","") + "='"+ value + "'" + (x<parameters.size()?",":"") ;
			   }
			 // r.add("tarikh_kemaskini",r.unquote("SYSDATE"));
			  sql = sql + ",tarikh_kemaskini=SYSDATE ";
			  sql = sql + " WHERE "+id_fieldname+" = '" +id+ "' ";
			  
			  Db db = null;
			  try {
				  db = new Db();
				  Statement stmt = db.getStatement();
				  stmt.executeUpdate(sql);
			  } catch (Exception e) { 
				  e.printStackTrace();
			  }
			  finally {
				  if (db != null) db.close();
			  }
			  
			  //myLogger.info(sql);
		  }
	  }

	public void delete(String table, String id_fieldname,String id) 
	throws Exception{
		Db db = null;
		try {
			  db = new Db();
			  Statement stmt = db.getStatement();
			  String sql = "DELETE FROM "+table+" WHERE "+id_fieldname+" ='"+ id +"' ";
			  stmt.executeUpdate(sql);
			  //myLogger.info(sql);
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	   finally {
		  if (db != null) db.close();
	   }
		
	}

	public void insert(String table, Hashtable parameters,
			String id_fieldname) throws Exception{

		  String sql = "";
		  String name = "";
		  String value ="";
		  Db db = null;
		  try {
			  db = new Db();
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
			  int x = 1;
			  for (Enumeration e = parameters.keys(); e.hasMoreElements();x++) {
				  name = (String)e.nextElement();
				  value = (String)parameters.get(name);
				  r.add(name.replace("Form_",""),value);
			  }
			  r.add("tarikh_masuk",r.unquote("SYSDATE"));
			  r.add("tarikh_kemaskini",r.unquote("SYSDATE"));
			  sql = r.getSQLInsert(table);
			  stmt.executeUpdate(sql);
		  }
		  finally {
			  if (db != null) db.close();
		  }
		  //myLogger.info("sql:"+sql);
		
	}
	
	public String checkIsNull(String x) {
		if (x != null) return x;
		else return "";
	}

	public String getLookup(String field,String id) throws Exception{
		String lookupString = null;
		setIdDesc(field);
		if ("jantina".equals(field)) {
			if (this.jantina != null) {
				lookupString = (String)this.jantina.get(id);
			}
		}else {
			Db db = null;
			try {
				db = new Db();
				String sql = "Select "+this.lookup_desc+" FROM "+this.lookup_tablename+ " WHERE "+field+"='"+id+"'";
				Statement stmt = db.getStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					lookupString = rs.getString(1);
				}		
			} catch (Exception e) {
				;
			}finally {
				if (db != null) db.close();
			}
			if (lookupString == null) lookupString = "";
		}
		return lookupString;
	}
	
	public String getSelectOptionValue(String field,String selectedValue) {
		setIdDesc(field);
		StringBuffer sb = new StringBuffer();
		String selected = "";
		if (selectedValue == null)selectedValue="";
		if ("jantina".equals(field)) {
			String name = "";
			String value="";
			 for (Enumeration e = this.jantina.keys(); e.hasMoreElements();) {
				  name = (String)e.nextElement();
				  value = (String)this.jantina.get(name);
					if (selectedValue.equals(name)) selected = "selected";
					else selected = "";
					sb.append("<option " + selected + " value=" + name + ">"
							+ value + "</option>\n");
			 }
			//return sb.toString();	
		} else {
		Db db = null;
		try {
			db = new Db();
			String sql = "Select "+field+","+this.lookup_desc+" FROM "+this.lookup_tablename;
			Statement stmt = db.getStatement();
			myLogger.info(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				if (selectedValue.equals(rs.getString(1))) selected = "selected";
				else selected = "";
				sb.append("<option " + selected + " value=" + rs.getString(1) + ">"
						+ rs.getString(2) + "</option>\n");
							}		
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (db != null) db.close();
		}
		}
		return sb.toString();	
	}
	
	public void setIdDesc(String field) {
		if ("id_negeri".equals(field)) {
			this.lookup_desc = "NAMA_NEGERI";
			this.lookup_tablename = "TBLRUJNEGERI";
		}
		else if ("id_negara".equals(field)) {
			this.lookup_desc = "NAMA_NEGARA";
			this.lookup_tablename = "TBLRUJNEGARA";
		}
		else if ("id_seksyen".equals(field)) {
			this.lookup_desc = "NAMA_SEKSYEN";
			this.lookup_tablename = "TBLRUJSEKSYEN";
		}
		else if ("id_daerah".equals(field)) {
			this.lookup_desc = "NAMA_DAERAH";
			this.lookup_tablename = "TBLRUJDAERAH";
		} 
		else if ("id_kementerian".equals(field)) {
			this.lookup_desc = "NAMA_KEMENTERIAN";
			this.lookup_tablename = "TBLRUJKEMENTERIAN";			
		} else if ("id_jenispejabat".equals(field)) {
			this.lookup_desc = "KETERANGAN";
			this.lookup_tablename = "TBLRUJJENISPEJABAT";			
		}
		else if ("jantina".equals(field)) {
			this.jantina = new Hashtable();
			this.jantina.put("03"," ");
			this.jantina.put("01","LELAKI");
			this.jantina.put("02","PEREMPUAN");
		}

	}
	
	
	public String[] toArray(String str ) {
		return str.split(",");
	}
	
	public int getConvertedFieldSize(String size) {
		if ("".equals(size)) return 0;
		else if (Integer.parseInt(size) >= 60) {
			return 60;
		} else {
			return Integer.parseInt(size);
		}
	}
	
	public void printObject(Object c)
	{
		myLogger.info(c);
	}
	
	public String getValue(String txt) throws Exception {
		if (txt == null) return "";
		else if (isLookup(txt)) {
			String[] lookupValue = txt.split("@@");
			String field = (String)lookupValue[0];
			String value = (String)lookupValue[1];
			return this.getLookup(field,value);
		}else return txt;
	}
	
	public boolean isLookup(String txt) {
		if (txt == null) return false;
		else if (txt.indexOf("@@") != -1) return true;
		else return false;
	}
	
	public boolean isLookupByFieldName(String fieldname) {
		if (fieldname.indexOf("id_") != -1 || "jantina".equals(fieldname)) {
			return true;
		} else return false;
	}
		  
}
