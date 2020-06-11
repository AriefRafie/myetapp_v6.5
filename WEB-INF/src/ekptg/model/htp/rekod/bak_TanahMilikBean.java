package ekptg.model.htp.rekod;

import java.sql.Statement;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHtp;
import ekptg.model.htp.entity.HakMilik;
import ekptg.model.htp.entity.Permohonan;
import ekptg.model.htp.entity.PfdFail;

public class bak_TanahMilikBean implements bak_ITanah{

	private static Logger myLog = Logger.getLogger(ekptg.model.htp.rekod.bak_TanahMilikBean.class);
	private HakMilik hakmilik = null;
 	private IHtp iHTP = null;
  	private IHTPUtilitiRekod iUtilRekod = null;
	private PfdFail pfdFail = null;
 	private Permohonan permohonan = null;

	public HakMilik kemaskini(HakMilik hakmilikBaru) throws Exception {
	    Db dbHakmilik = null;
	    String sql = "";
	    try {
	    	  dbHakmilik = new Db();
			  Statement stmtHakmilik = dbHakmilik.getStatement();
			  SQLRenderer rHakmilik = new SQLRenderer();
			  rHakmilik.update("ID_HAKMILIK",hakmilikBaru.getIdHakmilik());
	    	  //convert date before add
			  String tarikhTerima = hakmilikBaru.getTarikhTerimaStr();
			  String txdTarikhTerima = "to_date('" + tarikhTerima + "','dd/MM/yyyy')";
			  rHakmilik.add("TARIKH_TERIMA",rHakmilik.unquote(txdTarikhTerima));
			  String tarikhWarta= hakmilikBaru.getTarikhWartaStr();
			  String txdTarikhWarta = "to_date('" + tarikhWarta + "','dd/MM/yyyy')";
			  rHakmilik.add("TARIKH_WARTA",rHakmilik.unquote(txdTarikhWarta));
			  rHakmilik.add("NO_WARTA",hakmilikBaru.getNoWarta());
			  rHakmilik.add("ID_LOT", "1");
			  rHakmilik.add("NO_LOT", hakmilikBaru.getNoLot());
			  rHakmilik.add("ID_LUAS", hakmilikBaru.getIdLuas());
			  rHakmilik.add("LUAS", hakmilikBaru.getLuasString());

			  rHakmilik.add("ID_LUAS_BERSAMAAN","2");
			  rHakmilik.add("LUAS_BERSAMAAN", hakmilikBaru.getLuasBersamaan());

			  rHakmilik.add("NO_PELAN", hakmilikBaru.getNoPelan());
			  rHakmilik.add("NO_PU", hakmilikBaru.getNoPU());
			  rHakmilik.add("NO_SYIT", hakmilikBaru.getNoSyit());
			  rHakmilik.add("LOKASI", hakmilikBaru.getLokasi());
			  rHakmilik.add("NO_FAIL_JOPA", hakmilikBaru.getNoFailJOPA());
			  rHakmilik.add("STATUS_SAH", hakmilikBaru.getStatusSah());
			  rHakmilik.add("KEGUNAAN_TANAH", hakmilikBaru.getKegunaan());
			  rHakmilik.add("CATATAN", hakmilikBaru.getCatatan());
			  if (hakmilikBaru.getTarikhKemaskiniStr().equals(""))
				  rHakmilik.add("TARIKH_KEMASKINI", rHakmilik.unquote("sysdate"));
			  else
				  rHakmilik.add("TARIKH_KEMASKINI",hakmilikBaru.getTarikhKemaskiniStr());
			  
	    	  //convert date before add	  
			  rHakmilik.add("ID_KEMASKINI", hakmilikBaru.getIdMasuk());
			  sql = rHakmilik.getSQLUpdate("TBLHTPHAKMILIK");
			  myLog.info(sql);
			  stmtHakmilik.executeUpdate(sql);

	    }finally {
		    if (dbHakmilik != null) dbHakmilik.close();
	    }
		Db dbHakmilikPerihal = new Db();
		String sqlHakmilikPerihal = "";
		try{
		     Statement stmtHakmilikPerihal = dbHakmilikPerihal.getStatement();
			 SQLRenderer rHakmilikPerihal = new SQLRenderer();
			 rHakmilikPerihal.update("ID_HAKMILIK", hakmilikBaru.getIdHakmilik());
			 rHakmilikPerihal.add("KEGUNAAN_TANAH", hakmilikBaru.getKegunaan());
			 sqlHakmilikPerihal = rHakmilikPerihal.getSQLUpdate("TBLHTPHAKMILIKPERIHAL");
			 stmtHakmilikPerihal.executeUpdate(sqlHakmilikPerihal);

		}finally {
			if (dbHakmilikPerihal != null)dbHakmilikPerihal.close();
		}
		return getIUtilRekod().getTanah(String.valueOf(hakmilikBaru.getIdHakmilik()));

	}
	
	public String getLuas(String jenisLuas,String luas
		,String luas2,String luas3,String luas4
		,String luas5,String luas6){
		String luasAsal = ""; 
		if(jenisLuas.equals("1")){
			luasAsal = luas+"KM";
		}else if(jenisLuas.equals("2")){
			luasAsal = luas+"H";
		 }else if(jenisLuas.equals("3")){
			luasAsal = luas+"M";
		 }else if(jenisLuas.equals("4")){
			 luasAsal = luas2+"E"+luas3+"R"+luas4+"P";
		 }else if(jenisLuas.equals("5")){
			luasAsal = luas+"K";
		 }else if(jenisLuas.equals("7")){
			 luasAsal = luas5+"E"+luas6+"D";
		 }else if(jenisLuas.equals("8")){
			 luasAsal = luas2+"R"+luas3+"J"+luas4+"K";
		 }
		return luasAsal;
		
	}

//	private IHtp getIHTP(){
//		if(iHTP== null)
//			iHTP = new HtpBean();
//		return iHTP;
//
//	}

	private IHTPUtilitiRekod getIUtilRekod(){
		if (iUtilRekod == null){
			iUtilRekod = new HTPUtilitiRekodBean();
		}
		return iUtilRekod;

	}


}
