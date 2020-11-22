package ekptg.model.htp;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;

import org.apache.log4j.Logger;

import ekptg.helpers.Utils;

public class FrmRekodTransaksiCukaiData {
	private static Logger log = Logger.getLogger(FrmRekodTransaksiCukaiData.class);
	private static Vector<Hashtable<String,String>> listCukai = null;
	
	// PAPAR TRANSAKSI CUKAI BY ID
	public static Vector<Hashtable<String,String>> getTransaksiCukaiById(String id) throws Exception {
		Db db = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			listCukai = new Vector<Hashtable<String,String>>();
			Statement stmt = db.getStatement();
			
			sql = "SELECT TC.ID_HAKMILIKCUKAI,TC.ID_HAKMILIK,TC.CUKAI_TERKINI, TC.TARIKH_MASUK "+
		       	  " ,U.USER_NAME "+
		       	  " FROM TBLHTPHAKMILIKCUKAI TC, USERS U " +
		       	  " WHERE TC.ID_KEMASKINI = U.ID_KEMASKINI(+) "+
		       	  " AND TC.ID_HAKMILIK = ' "+id +"' " +
		       	  " ORDER BY TC.TARIKH_MASUK DESC ";				  
		       			
			ResultSet rs = stmt.executeQuery(sql);
			log.info("transaksi cukai :"+sql);
			Hashtable<String,String> h;

	      	int bil = 1;
	      	int count = 0;
			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("bil", bil+".");
				h.put("idHakmilik", rs.getString("ID_HAKMILIK")==null ? "" :rs.getString("ID_HAKMILIK"));
				h.put("idHakmilikCukai", rs.getString("ID_HAKMILIKCUKAI")==null ? "" :rs.getString("ID_HAKMILIKCUKAI"));
				h.put("cukaiTerkini", rs.getString("CUKAI_TERKINI")==null ? "" :Utils.format2Decimal(rs.getDouble("CUKAI_TERKINI")));
				h.put("tarikhTransaksi", rs.getString("TARIKH_MASUK")==null ? "" :sdf.format(rs.getDate("TARIKH_MASUK")));
				h.put("pengguna", rs.getString("USER_NAME")==null ? "" :rs.getString("USER_NAME"));
				listCukai.addElement(h);
				bil++;
				count++;
				
			}
			if(count==0){
				h = new Hashtable<String,String>();
				h.put("bil","");
				h.put("idHakmilik","");
				h.put("idHakmilikCukai","");
				h.put("cukaiTerkini", "Tiada Rekod.");
				h.put("tarikhTransaksi","");
				listCukai.addElement(h);
				
			}
			
		} finally {
			if (db != null)
				db.close();
		}
		return listCukai;
		
	}
	
	

}
