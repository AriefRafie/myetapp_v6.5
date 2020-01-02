package ekptg.model.htp;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

public class FrmCukaiUploadList {

	static Logger mylog = Logger
			.getLogger(ekptg.model.htp.FrmCukaiUploadList.class);
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");

	public static Hashtable getCukaiNegeriInfo(String idpermohonan)
			throws Exception {

		Db db = null;

		String sql2 = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql2 = "select Id_negeri, kod_negeri, Nama_negeri from tblrujnegeri";

			mylog
					.info("getPermohonanInfo(" + idpermohonan + ") : sql::"
							+ sql2);

			ResultSet rs1 = stmt.executeQuery(sql2);
			Hashtable h = new Hashtable();

			while (rs1.next()) {

				if (rs1.getString("ID_NEGERI") == null) {
					h.put("lblNegeri", "");
				} else {
					h.put("lblIDNegeri", rs1.getString("ID_NEGERI"));
				}
				if (rs1.getString("kod_negeri") == null) {
					h.put("lblKodNegeri", "");
				} else {
					h.put("lblKodNegeri", rs1.getString("kod_negeri"));
				}
				if (rs1.getString("Nama_negeri") == null) {
					h.put("lblNamaNegeri", "");
				} else {
					h.put("lblNamaNegeri", rs1.getString("Nama_negeri"));
				}

			}
			return h;
		} finally {
			if (db != null)
				db.close();
		}
	}
	
	public static void GetKeteranganByID(int id) throws Exception{
		
		Db db = null;

		String sql = "";

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql = "select keterangan from tblrujLot where id_lot = " + id;

			mylog.info("getPermohonanInfo(" + id + ") : sql::"+ sql);

			ResultSet rs1 = stmt.executeQuery(sql);
			Hashtable h = new Hashtable();

			while (rs1.next()) {

				if (rs1.getString("keterangan") == null) {
					h.put("lotketerangan", "");
				} else {
					h.put("lotketerangan", rs1.getString("keterangan"));
				}
				

			}
		} finally {
			if (db != null)
				db.close();
		}
	}
	


	public static void SimpanFailUploadCukai(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		Date now = new Date();
		try {

			String bil = "";
			String Mukim = "";
			String Jenis_No = "";
			String Hakmilik1 = "";
			String Jenis_Lot = "";
			String No_Lot = "";
			String Kegunaan_Tanah = "";
			Double Cukai = 0.00;
			String Cukai2 = "";    	
			Long Cukai3 = 0L;  
			Double Tunggakkan = 0.00;
    		Double Denda_Lewat = 0.00;
    		Double Kredit = 0.00;
    		Double Cukai_Kena_Bayar = 0.00;
    		String idNegeri = "";
    		String idDaerah = "";
    		String NoHakmilikUpload = (String)data.get("Jenis_No")+(String)data.get("Hakmilik");
    		String NoLotUpload = (String)data.get("Jenis_Lot")+(String)data.get("No_Lot");
    		    		
    		long ID_CUKAITEMP = ekptg.helpers.DB.getNextID("TBLHTPCUKAITEMP_SEQ");	   		
    		bil = (String)data.get("bil");
    		Mukim = (String)data.get("Mukim");
    		Jenis_No = (String)data.get("Jenis_No");
    		Hakmilik1 = (String)data.get("Hakmilik");
    		Jenis_Lot = (String)data.get("Jenis_Lot");
    		No_Lot = (String)data.get("No_Lot");
    		Kegunaan_Tanah = (String)data.get("Kegunaan_Tanah");
    		
    		Cukai = (Double)data.get("Cukai");
    		//Cukai = Double.parseDouble((String)data.get("Cukai"));
    		Tunggakkan = (Double)data.get("Tunggakkan");
    		//Tunggakkan = Double.parseDouble((String)data.get("Tunggakkan"));
    		Denda_Lewat = (Double)data.get("Denda_Lewat");
    		//Denda_Lewat = Double.parseDouble((String)data.get("Denda_Lewat"));
    		Kredit = (Double)data.get("Kredit");
    		//Kredit = Double.parseDouble((String)data.get("Kredit"));
    		Cukai_Kena_Bayar = (Double)data.get("Cukai_Kena_Bayar");
    		//Cukai_Kena_Bayar = Double.parseDouble((String)data.get("Cukai_Kena_Bayar"));
    		idNegeri = (String)data.get("idNegeri");
    		idDaerah = (String)data.get("idDaerah");
    		//System.out.println("ID_CUKAITEMP" + ID_CUKAITEMP);
    		//System.out.println("Cukai" + Cukai);
    		
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			r.add("ID_CUKAITEMP",ID_CUKAITEMP);
			//r.add("",bil);
			r.add("NAMA_MUKIM",Mukim);
			//r.add("ID_JENISHAKMILIK",Jenis_No);
			r.add("NO_HAKMILIK",r.unquote(Hakmilik1));
			//r.add("ID_LOT",Jenis_Lot);
			r.add("NO_LOT",r.unquote(No_Lot));
			r.add("KEGUNAAN_TANAH",Kegunaan_Tanah);
			r.add("CUKAI_KENA_BAYAR",Cukai);
			r.add("TUNGGAKAN",Tunggakkan);
			r.add("DENDA",Denda_Lewat);
			r.add("PENGURANGAN",Kredit);
			r.add("CUKAI_PERLU_BAYAR",Cukai_Kena_Bayar);
			r.add("ID_NEGERI",idNegeri);
			r.add("ID_DAERAH",idDaerah);
			r.add("NO_HAKMILIKUPLOAD",NoHakmilikUpload);
			r.add("NO_LOTUPLOAD",NoLotUpload);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			
			sql = r.getSQLInsert("TBLHTPCUKAITEMP");
			//System.out.println("FrmSenaraiFailPajakanKecilData:janaFail::sql:::"+ sql);
			stmt.executeUpdate(sql);
		} finally {
			if (db != null)
				db.close();
		}

	}

	public static void SimpanFailUploadCukaiBaru(Hashtable data) throws Exception {
		Db db = null;
		String sql = "";
		Date now = new Date();
		try {
			String bil = "";
			String Mukim = "";
			String Jenis_No = "";
			String Hakmilik1 = "";
			String Jenis_Lot = "";
			String No_Lot = "";
			String Kegunaan_Tanah = "";
			Double Cukai = 0.00;
			String Cukai2 = "";    	
			Long Cukai3 = 0L;  
			Double Tunggakkan = 0.00;
    		Double Denda_Lewat = 0.00;
    		Double Kredit = 0.00;
    		Double Cukai_Kena_Bayar = 0.00;
    		String idNegeri = "";
    		String idDaerah = "";
    		String NoHakmilikUpload = (String)data.get("Jenis_No")+(String)data.get("Hakmilik");
    		String NoLotUpload = (String)data.get("Jenis_Lot")+(String)data.get("No_Lot");
    		    		
    		long ID_CUKAITEMP = ekptg.helpers.DB.getNextID("TBLHTPCUKAITEMP_SEQ");	   		
    		//System.out.println("MODEL : SimpanFailUploadCukai :: NoHakmilikUpload ::" + NoHakmilikUpload);
    		//System.out.println("MODEL : SimpanFailUploadCukai :: NoLotUpload ::" + NoLotUpload);
    		
    		bil = (String)data.get("bil");
    		Mukim = (String)data.get("Mukim");
    		Jenis_No = (String)data.get("Jenis_No");
    		Hakmilik1 = (String)data.get("Hakmilik");
    		Jenis_Lot = (String)data.get("Jenis_Lot");
    		No_Lot = (String)data.get("No_Lot");
    		Kegunaan_Tanah = (String)data.get("Kegunaan_Tanah");
    		
    		//asal	Cukai = (Double)data.get("Cukai");
    		Cukai = Double.parseDouble((String)data.get("Cukai"));
    		//Tunggakkan = (Double)data.get("Tunggakkan");
    		Tunggakkan = Double.parseDouble((String)data.get("Tunggakkan"));
    		//Denda_Lewat = (Double)data.get("Denda_Lewat");
    		Denda_Lewat = Double.parseDouble((String)data.get("Denda_Lewat"));
    		//Kredit = (Double)data.get("Kredit");
    		Kredit = Double.parseDouble((String)data.get("Kredit"));
    		//Cukai_Kena_Bayar = (Double)data.get("Cukai_Kena_Bayar");
    		Cukai_Kena_Bayar = Double.parseDouble((String)data.get("Cukai_Kena_Bayar"));
    		idNegeri = (String)data.get("idNegeri");
    		idDaerah = (String)data.get("idDaerah");
    		//System.out.println("ID_CUKAITEMP" + ID_CUKAITEMP);
    		//System.out.println("Cukai" + Cukai);
    		
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			r.add("ID_CUKAITEMP",ID_CUKAITEMP);
			//r.add("",bil);
			r.add("NAMA_MUKIM",Mukim);
			//r.add("ID_JENISHAKMILIK",Jenis_No);
			r.add("NO_HAKMILIK",Hakmilik1);
			//r.add("ID_LOT",Jenis_Lot);
			r.add("NO_LOT",No_Lot);
			r.add("KEGUNAAN_TANAH",Kegunaan_Tanah);
			r.add("CUKAI_KENA_BAYAR",Cukai);
			r.add("TUNGGAKAN",Tunggakkan);
			r.add("DENDA",Denda_Lewat);
			r.add("PENGURANGAN",Kredit);
			r.add("CUKAI_PERLU_BAYAR",Cukai_Kena_Bayar);
			r.add("ID_NEGERI",idNegeri);
			r.add("ID_DAERAH",idDaerah);
			r.add("NO_HAKMILIKUPLOAD",NoHakmilikUpload);
			r.add("NO_LOTUPLOAD",NoLotUpload);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			
			sql = r.getSQLInsert("TBLHTPCUKAITEMP");
			//System.out.println("FrmSenaraiFailPajakanKecilData:janaFail::sql:::"+ sql);
			stmt.executeUpdate(sql);
		} finally {
			if (db != null)
				db.close();
		}

	}
	
	

}
