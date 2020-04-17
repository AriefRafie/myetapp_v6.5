package ekptg.model.htp.cukai;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.model.entities.Tblrujjenishakmilik;
import ekptg.model.entities.Tblrujlot;
import ekptg.model.entities.Tblrujmukim;
import ekptg.model.htp.FrmCukaiKemaskiniDataBaru;
import ekptg.model.htp.cukai.entity.CukaiTemp;
import ekptg.model.htp.entity.HakMilik;

public class FrmUploadBaruData {

	static Logger myLog = Logger.getLogger(ekptg.model.htp.cukai.FrmUploadBaruData.class);
	private ICukai iCukai = null;
	private HakMilik hakmilik = null;
	private static SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");

	public static Hashtable<String, String> getCukaiNegeriInfo(String idpermohonan)
			throws Exception {
		Db db = null;
		String sql2 = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql2 = "select Id_negeri, kod_negeri, Nama_negeri from tblrujnegeri";
			myLog.info("getPermohonanInfo(" + idpermohonan + ") : sql::"+ sql2);
			
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

			myLog.info("getPermohonanInfo(" + id + ") : sql::"+ sql);

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
	public static void deleteCukai(String idNegeri,String idDaerah,String year){
		Db db = null;
		try{
			db = new Db();
			String sql ="DELETE TBLHTPCUKAITEMP WHERE ID_NEGERI="+idNegeri+" " +
					"AND ID_DAERAH="+idDaerah+" AND TAHUN='"+year+"'";
			Statement stat = db.getStatement();
			stat.executeUpdate(sql);
			stat.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		 finally {
			 if (db != null)
				 db.close();
		}
		
	}
	public static Vector<CukaiTemp> getUploadList(String year,String idNegeri,String idDaerah){
		Db db = null;
		String sql = "";
		Vector<CukaiTemp> v = new Vector<CukaiTemp>();
		try{
			db = new Db();
			SQLRenderer r = new SQLRenderer();
			r.add("a.ID_CUKAITEMP");
			r.add("a.NO_HAKMILIK");
			r.add("NVL(a.NO_LOT,'0') NO_LOT");
			r.add("a.KEGUNAAN_TANAH");
			r.add("a.CUKAI_KENA_BAYAR");
			r.add("a.TUNGGAKAN");
			r.add("a.DENDA");
			r.add("a.PENGURANGAN");
			r.add("a.CUKAI_PERLU_BAYAR");
			r.add("a.NO_HAKMILIKUPLOAD");
			r.add("a.NO_LOTUPLOAD");
			r.add("b.KOD_JENIS_HAKMILIK");
			r.add("a.ID_NEGERI",r.unquote(idNegeri));
			r.add("a.ID_DAERAH",r.unquote(idDaerah));
			r.add("a.ID_NEGERI");
			r.add("a.ID_DAERAH");
			r.add("b.ID_JENISHAKMILIK",r.unquote("a.ID_JENISHAKMILIK"));
			r.add("b.ID_JENISHAKMILIK");
			r.add("b.KETERANGAN AS KETERANGAN_HAKMILIK");
			r.add("d.ID_LOT",r.unquote("a.ID_LOT"));
			r.add("d.KOD_LOT");
			r.add("d.KETERANGAN AS KETERANGAN_LOT");
			r.add("c.ID_MUKIM",r.unquote("a.ID_MUKIM"));
			r.add("C.NAMA_MUKIM");
			r.add("a.TAHUN",year);
			sql=r.getSQLSelect("TBLHTPCUKAITEMP a,Tblrujjenishakmilik b,Tblrujmukim c,Tblrujlot d");
			myLog.debug(sql);
			Statement stat = db.getStatement();
			ResultSet rs = stat.executeQuery(sql);
			while(rs.next()){
				CukaiTemp temp = new CukaiTemp();
				Tblrujjenishakmilik jenisHakmilik = new Tblrujjenishakmilik();
				Tblrujmukim rujMukim = new Tblrujmukim();
				Tblrujlot rujLot = new Tblrujlot();
				rujLot.setKeterangan(rs.getString("KETERANGAN_LOT"));
				rujLot.setKodLot(rs.getString("KOD_LOT"));
				rujMukim.setNamaMukim(rs.getString("NAMA_MUKIM"));
				jenisHakmilik.setKodJenisHakmilik(rs.getString("KOD_JENIS_HAKMILIK"));
				jenisHakmilik.setIdJenishakmilik(rs.getLong("ID_JENISHAKMILIK"));
				jenisHakmilik.setKeterangan(rs.getString("KETERANGAN_HAKMILIK"));
				temp.setIdCukaiTemp(rs.getLong("ID_CUKAITEMP"));
				temp.setKegunaanTanah(rs.getString("KEGUNAAN_TANAH"));
				temp.setNoHakmilik(rs.getString("NO_HAKMILIKUPLOAD"));
				String noLot = "";
				if(rs.getString("NO_LOT").contains(".0")){
					noLot = rs.getString("NO_LOT").substring(0,rs.getString("NO_LOT").indexOf(".0"));
					myLog.info("noLot:"+noLot);
				}else{
					noLot = rs.getString("NO_LOT");
				}	
				temp.setNoLot(noLot);
				temp.setRujJenisHakmilik(jenisHakmilik);
				temp.setRujMukim(rujMukim);
				temp.setRujLot(rujLot);
				v.addElement(temp);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			db.close();
		}
		return v;
	}
	
	public void SimpanFailUploadCukaiBaru(Hashtable data) throws Exception {
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
			Double Tunggakkan = 0.00;
    		Double Denda_Lewat = 0.00;
    		Double Kredit = 0.00;
    		Double Cukai_Kena_Bayar = 0.00;
    		String idNegeri = "";
    		String idDaerah = "";
    		String noHakmilik = (String)data.get("Hakmilik");
    		if(noHakmilik != null){
    			if(noHakmilik.indexOf('.') != -1){
    				noHakmilik = noHakmilik.substring(0,noHakmilik.indexOf('.'));
    			}
    		}
    		String NoHakmilikUpload = (String)data.get("Jenis_No")+noHakmilik;		
    		String noLot = (String)data.get("No_Lot");
    		if(noLot != null){
    			if(noLot.indexOf('.')!= -1){
    				 noLot = noLot.substring(0,noLot.indexOf('.'));
    			}
    		}
    		String NoLotUpload = (String)data.get("Jenis_Lot")+noLot;
    		    		
    		long ID_CUKAITEMP = ekptg.helpers.DB.getNextID("TBLHTPCUKAITEMP_SEQ");	   		  		
    		bil = (String)data.get("bil");
    		Mukim = (String)data.get("Mukim");
    		Jenis_No = (String)data.get("Jenis_No");
    		Hakmilik1 = (String)data.get("Hakmilik");
    		if(Hakmilik1!= null && Hakmilik1 !=""){
    			if(Hakmilik1.indexOf(".")!= -1){
    				Hakmilik1=Hakmilik1.substring(0,Hakmilik1.indexOf("."));
    			}
    		}
    		Jenis_Lot = (String)data.get("Jenis_Lot");
    		No_Lot = (String)data.get("No_Lot");
    		Kegunaan_Tanah = (String)data.get("Kegunaan_Tanah");
    		String tahun = (String)data.get("tahunCukai");
    		Cukai = Double.parseDouble((String)data.get("Cukai"));
    		Tunggakkan = Double.parseDouble((String)data.get("Tunggakkan"));
    		Denda_Lewat = Double.parseDouble((String)data.get("Denda_Lewat"));
    		Kredit = Double.parseDouble((String)data.get("Kredit"));
    		Cukai_Kena_Bayar = Double.parseDouble((String)data.get("Cukai_Kena_Bayar"));
    		idNegeri = (String)data.get("idNegeri");
    		idDaerah = (String)data.get("idDaerah");
    		String idMukim = (String)data.get("IdMukim");    
    		String idLot = (String)data.get("idLot");
    		String idHakmilik = (String)data.get("idHakmilik");
			
    		db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			r.add("ID_CUKAITEMP",ID_CUKAITEMP);
			r.add("TAHUN",tahun);
			r.add("ID_MUKIM",idMukim);
			r.add("NAMA_MUKIM",Mukim);
			r.add("NO_HAKMILIK",Hakmilik1);
			//r.add("NO_LOT",r.unquote(No_Lot));
			r.add("NO_LOT",No_Lot);
			r.add("KEGUNAAN_TANAH",Kegunaan_Tanah);
			r.add("CUKAI_KENA_BAYAR",Cukai);
			r.add("TUNGGAKAN",Tunggakkan);
			r.add("DENDA",Denda_Lewat);
			//r.add("PENGURANGAN",Kredit);
			r.add("PENGECUALIAN",Kredit);
			r.add("CUKAI_PERLU_BAYAR",Cukai_Kena_Bayar);
			r.add("ID_NEGERI",idNegeri);
			r.add("ID_DAERAH",idDaerah);
			r.add("NO_HAKMILIKUPLOAD",NoHakmilikUpload);
			r.add("NO_LOTUPLOAD",NoLotUpload);
			r.add("TARIKH_MASUK", r.unquote("sysdate"));
			r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));
			r.add("ID_LOT",r.unquote(idLot));
			r.add("ID_JENISHAKMILIK",r.unquote(idHakmilik));
			sql = r.getSQLInsert("TBLHTPCUKAITEMP");
			//myLog.debug(sql);
			stmt.executeUpdate(sql);
			
			hakmilik = getICukai().getHakmilikByMukimNohakmilikNolot(idMukim,idHakmilik,noHakmilik,idLot,noLot);
			if(hakmilik != null){
				// insert into tblhtpcukaiterperinci
				Hashtable ds = new Hashtable();
				ds.put("tahun", tahun);
				ds.put("tunggakan", data.get("Tunggakkan"));
				ds.put("denda", data.get("Denda_Lewat"));
				ds.put("pengurangan", data.get("Kredit"));
				ds.put("lebihan", data.get("Kredit"));
				ds.put("cukai_taliair", "0");
				ds.put("cukai_parit", "0");
				ds.put("cukai_kenabayar", data.get("Cukai"));
				ds.put("cukaiPerluBayar", data.get("Cukai_Kena_Bayar"));
				ds.put("senaraiNolot", NoLotUpload);
				ds.put("senaraiID_HAKMILIKCUKAI", String.valueOf(hakmilik.getHakmilikCukai().getIdHakmilikCukai()));
				ds.put("cukaiKenaBayarLama", data.get("Cukai"));
				ds.put("senaraiNO_HAKMILIKUPLOAD", NoHakmilikUpload);
				ds.put("senaraiNO_HAKMILIK", NoHakmilikUpload);
				//myLog.info("data is ="+ds);
				FrmCukaiKemaskiniDataBaru.kemaskiniData(ds);
			}else{
				data = new Hashtable();
				data.put("tahun", tahun);
				data.put("tunggakan", Tunggakkan);
				data.put("denda", Denda_Lewat);
				data.put("pengurangan", Kredit);
				data.put("lebihan", Tunggakkan);
				data.put("cukai_taliair", "0");
				data.put("cukai_parit", "0");
				data.put("cukai_kenabayar", Cukai);
				data.put("cukaiPerluBayar", Cukai_Kena_Bayar);
				data.put("senaraiNolot", NoLotUpload);
				data.put("senaraiID_HAKMILIKCUKAI", hakmilik.getHakmilikCukai().getIdHakmilikCukai());
				data.put("cukaiKenaBayarLama", Cukai);
				data.put("senaraiNO_HAKMILIKUPLOAD", NoHakmilikUpload);
				data.put("senaraiNO_HAKMILIK", NoHakmilikUpload);
				myLog.info("data XXX ="+data);
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			if (db != null)
				db.close();
		}

	}
	
	public static void SimpanFailUploadCukaiBaru07(Hashtable data) throws Exception {
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
			Double Tunggakkan = 0.00;
    		Double Denda_Lewat = 0.00;
    		Double Kredit = 0.00;
    		Double Cukai_Kena_Bayar = 0.00;
    		String idNegeri = "";
    		String idDaerah = "";
    		String noHakmilik = (String)data.get("Hakmilik");
    		/*if(noHakmilik != null){
    			if(noHakmilik.indexOf('.') != -1){
    				noHakmilik = noHakmilik.substring(0,noHakmilik.indexOf('.'));
    			}
    		}*/
    		String NoHakmilikUpload = (String)data.get("Jenis_No")+noHakmilik;
    		
    		String noLot = (String)data.get("No_Lot");
    		/*if(noLot != null){
    			if(noLot.indexOf('.')!= -1){
    				 noLot = noLot.substring(0,noLot.indexOf('.'));
    			}
    		}*/
    		String NoLotUpload = (String)data.get("Jenis_Lot")+noLot;
    		    		
    		long ID_CUKAITEMP = ekptg.helpers.DB.getNextID("TBLHTPCUKAITEMP_SEQ");	   		
    		
    		bil = (String)data.get("bil");
    		Mukim = (String)data.get("Mukim");
    		Jenis_No = (String)data.get("Jenis_No");
    		Hakmilik1 = (String)data.get("Hakmilik");
    		if(Hakmilik1!= null && Hakmilik1 !=""){
    			if(Hakmilik1.indexOf(".")!= -1){
    				Hakmilik1=Hakmilik1.substring(0,Hakmilik1.indexOf("."));
    			}
    		}
    		Jenis_Lot = (String)data.get("Jenis_Lot");
    		No_Lot = (String)data.get("No_Lot");
    		Kegunaan_Tanah = (String)data.get("Kegunaan_Tanah");
    		String tahun = (String)data.get("tahunCukai");
    		Cukai = Double.parseDouble((String)data.get("Cukai"));
    		Tunggakkan = Double.parseDouble((String)data.get("Tunggakkan"));
    		Denda_Lewat = Double.parseDouble((String)data.get("Denda_Lewat"));
    		Kredit = Double.parseDouble((String)data.get("Kredit"));
    		Cukai_Kena_Bayar = Double.parseDouble((String)data.get("Cukai_Kena_Bayar"));
    		idNegeri = (String)data.get("idNegeri");
    		idDaerah = (String)data.get("idDaerah");
    		String idMukim = (String)data.get("IdMukim");    
    		String idLot = (String)data.get("idLot");
    		String idHakmilik = (String)data.get("idHakmilik");
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			r.add("ID_CUKAITEMP",ID_CUKAITEMP);
			r.add("TAHUN",tahun);
			r.add("NAMA_MUKIM",Mukim);
			r.add("ID_MUKIM",idMukim);
			r.add("NO_HAKMILIK",Hakmilik1);
			//r.add("NO_LOT",r.unquote(No_Lot));
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
			//r.add("ID_LOT",r.unquote(idLot));
			//r.add("ID_JENISHAKMILIK",r.unquote(idHakmilik));
			
			sql = r.getSQLInsert("TBLHTPCUKAITEMP");
			
			myLog.debug(sql);
			
			stmt.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			if (db != null)
				db.close();
		}

	}

	private ICukai getICukai(){
		if(iCukai==null){
			iCukai = new CukaiBean();
		}
		return iCukai;
		
	}
	

}
