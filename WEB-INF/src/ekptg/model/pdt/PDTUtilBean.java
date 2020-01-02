package ekptg.model.pdt;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.Utils;
import ekptg.model.entities.Tblpdtakta;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHtp;
import ekptg.model.htp.entity.HtpPermohonan;
import ekptg.model.htp.entity.Permohonan;
import ekptg.model.htp.entity.PfdFail;

public class PDTUtilBean implements IPDTUtil{
	
 	private IHtp iHTP = null;  
	private static Logger myLog = Logger.getLogger(ekptg.model.pdt.PDTPerundanganBean.class);
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private Vector senarai= new Vector();
	
    String sql = "";
	PfdFail fail = null;
	Permohonan permohonan = null;
	HtpPermohonan htpPermohonan = null;
	public static String idTag = "";

	@Override
	public String getAktaPilihan(String idAkta, String selectName,
		String selectedValue, String disability, String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=\"\">SILA PILIH</option>\n");
			if (jsFunction != null){
				sb.append(jsFunction);	}
			Vector<Tblpdtakta> v = getAkta(idAkta);
			Tblpdtakta f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblpdtakta) v.get(i);
				if (String.valueOf(f.getIdAkta()).equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdAkta() + ">"
				+ f.getNoAkta() + " - " + f.getNamaAkta()
				+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	
	}
		
	 public String tambah(Hashtable data) throws Exception{
		 Db db = null;
		 String sql = "";  
		 Date now = new Date();
		 try {	 
		    	  long idDokumen = DB.getNextID("TBLPDTDOKUMEN_SEQ");
		    	  String noRujDokumen = (String)data.get("no_Rujukan_Dokumen");
		    	  String tajukDokumen = (String)data.get("tajuk_Dokumen");
		    	  String idJenisDokumen = (String)data.get("id_Jenisdokumen");
		    	  String tarikhDokumen = (String)data.get("tarikh_Dokumen");
		    	  String tkhDokumen = "to_date('" + tarikhDokumen + "','dd/MM/yyyy')";
			      String idSeksyen = (String)data.get("id_Seksyen");
			      String idFail = (String)data.get("id_Fail");
			      String catatan = (String)data.get("catatan");
			      
			      db = new Db();
			      Statement stmt = db.getStatement();
			      SQLRenderer r = new SQLRenderer();			      
			      r.add("id_Dokumen",idDokumen);
			      r.add("no_Dokumen",noRujDokumen);
			      r.add("tajuk_Dokumen", tajukDokumen);
			      r.add("id_Jenisdokumen", idJenisDokumen);
			      r.add("tarikh_Dokumen", r.unquote(tkhDokumen));
			      r.add("id_Seksyen", idSeksyen);
			      r.add("id_Fail", idFail);
			      r.add("catatan", catatan);
			      r.add("tarikh_Daftar",r.unquote("sysdate"));
			      r.add("tarikh_masuk",r.unquote("sysdate"));
			      sql = r.getSQLInsert("tblpdtdokumen");			      				     
			      stmt.executeUpdate(sql);
			      
					String tagDokumen = (String)data.get("tagDokumen");
					String idTagDokumen = (String)data.get("idTagDokumen");
					if(!tagDokumen.equals("")){
						db = new Db();
						stmt = db.getStatement();
					    r = new SQLRenderer();			      
						if(!idTagDokumen.equals("0")){
							r.update("ID_RUJTAG", data.get("idTagDokumen"));
							r.add("ID_KEMASKINI", data.get("idMasuk"));
							r.add("TAG_DOKUMEN",tagDokumen);
							r.add("TARIKH_KEMASKINI",r.unquote("sysdate"));
							sql = r.getSQLUpdate("TBLRUJTAGDOKUMEN");								
							idTag = String.valueOf(idTagDokumen);
						
						}else{
							long idTagDokumenBaru = DB.getNextID("TBLRUJTAGDOKUMEN_SEQ");
							r.add("ID_RUJTAG",idTagDokumenBaru);
							r.add("ID_DOKUMEN",idDokumen);
							r.add("TAG_DOKUMEN",tagDokumen);
							r.add("TARIKH_MASUK",r.unquote("sysdate")); 
							r.add("ID_MASUK",data.get("idMasuk"));
							sql = r.getSQLInsert("TBLRUJTAGDOKUMEN");  
							idTag = String.valueOf(idTagDokumenBaru);
						
						}
					    stmt.executeUpdate(sql);

					}

			      
			      return String.valueOf(idDokumen);
		    }catch (Exception re) {
			 myLog.error("Error: ", re);
				 throw re;
				 }
			      finally {
		    	if (db != null) db.close();
		    }
		    
	 }
	 
	 //Tblpdtakta
	 private Vector<Tblpdtakta> getAkta(String idAkta) throws Exception {
		 Db db = null;
		 String sql = "Select id_akta,no_akta,nama_akta " 
			+" from "
			+" tblpdtakta ";
			if(!idAkta.equals("")){
				sql += " where id_akta = " + idAkta ; 
			}
			sql +=" order by id_akta";
			try {
				db = new Db();
				Statement stmt = db.getStatement();
				ResultSet rs = stmt.executeQuery(sql);
				Vector<Tblpdtakta> v = new Vector<Tblpdtakta>();
				Tblpdtakta u = null;
				while (rs.next()) {
					u = new Tblpdtakta();
					u.setIdAkta(rs.getLong("id_akta"));
					u.setNoAkta(rs.getString("no_akta"));
					u.setNamaAkta(rs.getString("nama_akta"));
					v.addElement(u);
				}
				return v;
				
			} catch (Exception re) {
			 myLog.error("Error: ", re);
				 throw re;
				 }
			     finally {
				if (db != null)
					db.close();
			}
			
		}
	 
		@Override
		public Vector getMaklumat(String id,String no, String nama,String tag, String sumber)
			throws Exception {		
			 Db db = null;
			 senarai.clear();
			 String sql = "";	 
			 try {
			      db = new Db();
			      Statement stmt = db.getStatement();
			      //SQLRenderer r = new SQLRenderer();
			      sql = ""+
			      " (select ID_AKTA ID, NO_AKTA NO,NAMA_AKTA NAMA,'AKTA' MAKLUMAT " +
			      "		from tblpdtakta WHERE ID_AKTA IN "+
			      " 		    (SELECT ID_DOKUMEN FROM TBLRUJTAGDOKUMEN WHERE SUMBER='AKTA'  "+
			      " 		    AND TAG_DOKUMEN LIKE '%"+tag+"%' "+
			      " 		    ) "+ 
			      " 		) "+
			      " 		UNION "+ 
//			      " (select DTAP.ID_AKTAPINDA ID, DTAP.NO_AKTA_PINDAAN NO,DTA.NAMA_AKTA NAMA,'AKTAPINDA' MAKLUMAT " +
			      " (select DTAP.ID_AKTAPINDA ID, DTA.NO_AKTA NO,DTA.NAMA_AKTA NAMA,'AKTAPINDA' MAKLUMAT " +
			      "		from tblpdtaktapinda DTAP,tblpdtakta DTA " +
			      "		WHERE DTAP.ID_AKTA = DTA.ID_AKTA(+) AND DTAP.ID_AKTAPINDA IN "+
			      " 		    (SELECT ID_DOKUMEN FROM TBLRUJTAGDOKUMEN WHERE SUMBER='AKTAP'  "+
			      " 		    AND TAG_DOKUMEN LIKE '%"+tag+"%' "+
			      " 		    ) "+
			      " 		) "+
			      " 		UNION "+
			      " (select ID_ENAKMEN ID, NO_ENAKMEN NO,NAMA_ENAKMEN NAMA,'ENAKMEN' MAKLUMAT " +
			      "		from tblpdtENAKMEN WHERE ID_ENAKMEN IN "+
			      " 		    (SELECT ID_DOKUMEN FROM TBLRUJTAGDOKUMEN WHERE SUMBER='ENAKMEN'  "+
			      " 		    AND TAG_DOKUMEN LIKE '%"+tag+"%' "+
			      " 		    ) "+
			      " 		) "+
			      " 		UNION "+
			      " (select ID_ENAKMENPINDA ID, NO_ENAKMEN_PINDAAN NO,NAMA_ENAKMEN_PINDAAN NAMA,'ENAKMENPINDA' MAKLUMAT from tblpdtENAKMENpinda WHERE ID_ENAKMENPINDA IN "+
			      " 		    (SELECT ID_DOKUMEN FROM TBLRUJTAGDOKUMEN WHERE SUMBER='ENAKMENP'  "+
			      " 		    AND TAG_DOKUMEN LIKE '%"+tag+"%' "+
			      " 		    ) "+
			      " 		) "+
			      " 		UNION "+
			      " (select ID_PEKELILING ID, BIL_PEKELILING NO,TAJUK_PEKELILING NAMA,'PEKELILING' MAKLUMAT from tblpdtpekeliling WHERE ID_PEKELILING IN "+
			      " 		    (SELECT ID_DOKUMEN FROM TBLRUJTAGDOKUMEN WHERE SUMBER='PEKELILING'  "+
			      " 		    AND TAG_DOKUMEN LIKE '%"+tag+"%' "+
			      " 		    ) "+
			      " 		) "+
			      " 		UNION "+
			      " (select ID_DOKUMEN ID, NO_DOKUMEN NO,TAJUK_DOKUMEN NAMA,'DOKUMEN' MAKLUMAT from tblpdtdokumen WHERE ID_DOKUMEN IN "+
			      " 		    (SELECT ID_DOKUMEN FROM TBLRUJTAGDOKUMEN WHERE SUMBER='DOKUMEN'  "+
			      " 		    AND TAG_DOKUMEN LIKE '%"+tag+"%' "+
			      " 		    ) "+
			      " 		) "+
			      " 		UNION "+
			      " (select ID_PERUNDANGAN ID, KETERANGAN_PERUNDANGAN NO,NVL(KETERANGAN_KEPUTUSAN,'TIADA') NAMA,'PERUNDANGAN' MAKLUMAT from tblpdtperundangan WHERE ID_PERUNDANGAN IN "+
			      " 		    (SELECT ID_DOKUMEN FROM TBLRUJTAGDOKUMEN WHERE SUMBER='PERUNDANGAN'  "+
			      " 		    AND TAG_DOKUMEN LIKE '%"+tag+"%' "+
			      " 		    ) "+
			      " 		) "+
			      "";
			      myLog.info(sql);
			      ResultSet rs = stmt.executeQuery(sql);
			      Hashtable h = new Hashtable();
			      int bil = 1;
			      int count = 0;
			      while (rs.next()) {
			    	  h = new Hashtable();
			    	  h.put("bil", bil);
			    	  h.put("id",rs.getString("ID"));
			    	  h.put("kod",Utils.isNull(rs.getString("NO")));
			    	  h.put("keterangan",Utils.isNull(rs.getString("NAMA")));
			    	  h.put("maklumat",rs.getString("MAKLUMAT"));
			    	  senarai.addElement(h); 
			    	  bil++;
			    	  count++;
			      }
			      
			 }catch (Exception re) {
			 myLog.error("Error: ", re);
				 throw re;
				 }
			     finally {
				 if (db != null) db.close();
			 }  
			 return senarai;
		}	 
	 


	  
	private IHtp getIHTP(){
		if(iHTP== null)
			iHTP = new HtpBean();
		return iHTP;
	}	
	
	
}
