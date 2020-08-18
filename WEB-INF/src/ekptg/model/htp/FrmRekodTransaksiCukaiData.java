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
	private static Vector listCukai = null;
	
	// PAPAR TRANSAKSI CUKAI BY ID
	public static Vector getTransaksiCukaiById(int id) throws Exception {

		Db db = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			listCukai = new Vector();
			Statement stmt = db.getStatement();
			
			sql = "SELECT TBLHTPHAKMILIKCUKAI.ID_HAKMILIKCUKAI,TBLHTPHAKMILIKCUKAI.ID_HAKMILIK,TBLHTPHAKMILIKCUKAI.CUKAI_TERKINI, TBLHTPHAKMILIKCUKAI.TARIKH_MASUK "+
		       	  "FROM TBLHTPHAKMILIKCUKAI " +
		       	  "WHERE TBLHTPHAKMILIKCUKAI.ID_HAKMILIK = ' "+id +"' " +
		       	  "ORDER BY TBLHTPHAKMILIKCUKAI.TARIKH_MASUK DESC ";				  
		       			
			ResultSet rs = stmt.executeQuery(sql);
			log.info("transaksi cukai :"+sql);
			Hashtable h;

	      	int bil = 1;
	      	int count = 0;
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil+".");
				h.put("idHakmilik", rs.getString("ID_HAKMILIK")==null ? "" :rs.getString("ID_HAKMILIK"));
				h.put("idHakmilikCukai", rs.getString("ID_HAKMILIKCUKAI")==null ? "" :rs.getString("ID_HAKMILIKCUKAI"));
				h.put("cukaiTerkini", rs.getString("CUKAI_TERKINI")==null ? "" :Utils.format2Decimal(rs.getDouble("CUKAI_TERKINI")));
				h.put("tarikhTransaksi", rs.getString("TARIKH_MASUK")==null ? "" :sdf.format(rs.getDate("TARIKH_MASUK")));
				listCukai.addElement(h);
				bil++;
				count++;
			}
			if(count==0)
			{
				h = new Hashtable();
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
