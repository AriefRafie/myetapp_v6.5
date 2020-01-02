package ekptg.model.htp.rekod;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.DB;
import ekptg.helpers.Utils;
import ekptg.model.htp.FrmUtilData;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHtp;

import org.apache.log4j.Logger;

public class FrmTanahDaftarBean implements ITanahDaftar{	
	private Db db = null;
	private static Logger myLog = Logger.getLogger(ekptg.model.htp.rekod.FrmTanahDaftarBean.class);	
	private Connection conn = null;
	private IHtp iHTP = null;  
	private ITanahKementerian iTanahKem = null;  
	private String sql = "";
	private SQLRenderer r = new SQLRenderer();

	/** KEMASKINI TANAH MILIK */
	@Override
	public void kemaskini(Hashtable<String, String> data) throws Exception {
		sql = "";
		//String sqlHakmilikPerihal = "";
	    try{
	    	myLog.info("data="+data);
	    	db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
	    	
	    	Statement stmtHakmilik = db.getStatement();
	    	SQLRenderer rHakmilik = new SQLRenderer();
	    	rHakmilik.update("ID_HAKMILIK", data.get("idHakmilik"));
	    	
	    	//2018/01/23:Kemaskini kementerian dan agensi
	    	rHakmilik.add("ID_KEMENTERIAN", data.get("socIdKementerian"));   			  
	    	rHakmilik.add("ID_AGENSI", data.get("socIdAgensi"));   			  
	    	//rHakmilik.add("NO_FAIL_PTG", data.get(("txtNoFailPTG")); 
	    	//rHakmilik.add("NO_FAIL_PTD", data.get(("txtNoFailPTD")); 
	    	
	    	rHakmilik.add("ID_NEGERI", data.get("socNegeriHR"));
	    	rHakmilik.add("ID_DAERAH", data.get("socDaerahHR"));
	    	rHakmilik.add("ID_MUKIM", data.get("socMukimHR"));
	    	//rHakmilik.add("ID_SEKSYEN", data.get("socSeksyenHR"));
	    	rHakmilik.add("ID_LOT", data.get("socLot"));
	    	rHakmilik.add("NO_LOT", data.get("txtNoLot"));

	    	//convert date before add
	    	String tarikhTerima = data.get("tarikhTerima").toString();
	    	String txdTarikhTerima = "to_date('" + tarikhTerima + "','dd/MM/yyyy')";	    	  
	    	rHakmilik.add("TARIKH_TERIMA",rHakmilik.unquote(txdTarikhTerima));  
	    	rHakmilik.add("NO_FAIL_JOPA", data.get("txtNoFailJopa"));	 
	    	if(!data.get("socStatus").equals("")){
	    		rHakmilik.add("STATUS_SAH",data.get("socStatus"));
	    	}
	    	rHakmilik.add("LOKASI", data.get("lokasi"));
	    	rHakmilik.add("KEGUNAAN_TANAH", data.get("kegunaanTanah"));
	    	rHakmilik.add("ID_LUAS",data.get("idLuas"));
	    	rHakmilik.add("LUAS",  data.get("luas"));			  
	    	rHakmilik.add("ID_LUAS_BERSAMAAN","2");
	    	rHakmilik.add("LUAS_BERSAMAAN",data.get("luasBersamaan"));
	    	
	    	rHakmilik.add("CATATAN", data.get("catatan"));   
	    	rHakmilik.add("NO_PELAN", data.get("noPelan"));
	    	rHakmilik.add("NO_SYIT", data.get("noSyit"));
	    	rHakmilik.add("NO_PU", data.get("noPU"));
	    	
	    	rHakmilik.add("TARIKH_KEMASKINI", rHakmilik.unquote("sysdate"));	  
	    	rHakmilik.add("ID_KEMASKINI", data.get("idMasuk"));;			  

	    	// Tanah Milik
	    	rHakmilik.add("ID_JENISHAKMILIK", data.get("socJenisHakmilik"));
	    	rHakmilik.add("NO_HAKMILIK", data.get("noHakmilik"));
	    	rHakmilik.add("NO_BANGUNAN", data.get("noBangunan"));
	    	rHakmilik.add("NO_TINGKAT", data.get("noTingkat"));
	    	rHakmilik.add("NO_PETAK", data.get("noPetak"));			
			//  
	    	String tarikhDaftar = (String)data.get("tarikhDaftar");
	    	String txdTarikhDaftar = "to_date('" + tarikhDaftar + "','dd/MM/yyyy')";	  			  			  
	    	rHakmilik.add("TARIKH_DAFTAR", rHakmilik.unquote(txdTarikhDaftar));
	    	rHakmilik.add("TARAF_HAKMILIK", data.get("tarafHakmilik"));
	    	rHakmilik.add("TEMPOH", data.get("tempoh"));
	    	//convert date before add
	    	if(data.get("tarikhLuput")!=""){
	    		  String tarikhLuput = (String)data.get("tarikhLuput");
	    		  String txdTarikhLuput = "to_date('" + tarikhLuput + "','dd/MM/yyyy')";	
	    		  rHakmilik.add("TARIKH_LUPUT", rHakmilik.unquote(txdTarikhLuput));
	    		  
	    	}	    	  
	    	rHakmilik.add("CUKAI_TERKINI", data.get("cukai"));
	 	   	rHakmilik.add("NO_RIZAB", data.get("noRizab"));
	 	   	String tarikhRizab = (String)(data.get("tarikhRizab"));
	 	   	String txdTarikhRizab = "to_date('" + tarikhRizab + "','dd/MM/yyyy')";
	 	   	rHakmilik.add("TARIKH_RIZAB", rHakmilik.unquote(txdTarikhRizab));
	 	   	rHakmilik.add("ID_RIZAB", data.get("idRizab"));   
	 	   	rHakmilik.add("KAWASAN_RIZAB", data.get("kawasanRizab"));
	 	   	
	 	   	rHakmilik.add("ID_KATEGORI", data.get("socKategori"));
	 	   	rHakmilik.add("SEKATAN", data.get("sekatan"));	  
	 	   	rHakmilik.add("SYARAT", data.get("syarat"));	  
	 	   	rHakmilik.add("ID_SEKSYENUPI", data.get("seksyenUPI"));	  
	 	   	    		    
	    	sql = rHakmilik.getSQLUpdate("TBLHTPHAKMILIK");
			myLog.info("kemaskini:sql="+sql);
			stmtHakmilik.executeUpdate(sql);
			
			//TBLHTPHAKMILIKCUKAI
			r = new SQLRenderer();
			String sqlCukai = "";
			r.update("ID_HAKMILIK",r.unquote(String.valueOf(data.get("idHakmilik")))); 
			r.update("STATUS","S"); 
			r.add("CUKAI_TERKINI",data.get("txtCukaiTerkini"));
			r.add("CUKAI",data.get("txtCukaiTahun"));
			r.add("ID_KEMASKINI",data.get("idMasuk"));
			r.add("TARIKH_KEMASKINI",r.unquote("SYSDATE"));
			sqlCukai = r.getSQLUpdate("TBLHTPHAKMILIKCUKAI");
			myLog.info("kemaskini:sqlCukai="+sqlCukai);
			stmtHakmilik.executeUpdate(sqlCukai);
			  
//		}catch(Exception e){
//			try{
//				conn1.rollback();
//			}catch(SQLException sqle){
//				sqle.printStackTrace();
//				throw new Exception(getIHTP().getErrorHTML(sqle.toString()));
//			
//			}
//			e.printStackTrace();
//			throw new Exception(getIHTP().getErrorHTML(e.toString()));
//	    
//		}finally {
//		    if (dbHakmilik != null) dbHakmilik.close();
//	    }
	    
//		Db dbHakmilikPerihal = new Db();
//		try{
//			conn2 = dbHakmilik.getConnection();
//	    	conn2.setAutoCommit(false);
	    	Statement stmtHakmilikPerihal = db.getStatement();
			SQLRenderer rHakmilikPerihal = new SQLRenderer();
			rHakmilikPerihal.update("ID_HAKMILIK", data.get("idHakmilik"));
			//rHakmilikPerihal.add("KEGUNAAN_TANAH", data.get("txtKegunaanTanah"));
			rHakmilikPerihal.add("KEGUNAAN_TANAH", data.get("kegunaanTanah"));
			rHakmilikPerihal.add("TARIKH_KEMASKINI", rHakmilikPerihal.unquote("sysdate"));	  
			rHakmilikPerihal.add("ID_KEMASKINI", data.get("idMasuk"));;			  
			String sqlHakmilikPerihal = rHakmilikPerihal.getSQLUpdate("TBLHTPHAKMILIKPERIHAL");
			myLog.info("kemaskini:sqlHakmilikPerihal="+sqlHakmilikPerihal);
			stmtHakmilikPerihal.executeUpdate(sqlHakmilikPerihal);
			
			conn.commit();
		
		}catch(Exception e1){
			try{
				conn.rollback();
			}catch(SQLException sqle){
				//sqle.printStackTrace();
				throw new Exception(getIHTP().getErrorHTML("[MODUL HTP REKOD] RALAT KEMASKINI MAKLUMAT TANAH MILIK (<i>ROLLBACK</i>), "+sqle.toString()));
			
			}
			//e1.printStackTrace();
			throw new Exception(getIHTP().getErrorHTML("[MODUL HTP REKOD] RALAT KEMASKINI MAKLUMAT TANAH MILIK , "+e1.toString()));
			
		}finally {
			if (db != null)db.close();
		}		  
		
	}

	@Override
	public String simpanTransaction(Hashtable<String,String> hashData) throws Exception {
		String idHakmilik = "";
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			idHakmilik = insertMilik(db,hashData);
			FrmUtilData.insertHakmilikCukai(db
					,Utils.RemoveComma(String.valueOf(hashData.get("txtCukaiTahun")))
					,String.valueOf(hashData.get("idMasuk"))
					,idHakmilik);
	
			//FrmUtilData.insertHakmilikAgensi(db,hashData,idHakmilik);
			// 2017/11/15
			getTanahKem().insertTanahAgensi(db,hashData,idHakmilik);
			conn.commit();

	    }catch (SQLException se) {
	    	try {
	    		conn.rollback();
			} catch (SQLException se2) {
				throw new Exception(getIHTP().getErrorHTML("[MODUL HTP REKOD] RALAT KEMASUKAN MAKLUMAT TANAH RIZAB (<i>ROLLBACK</i>), "+se2.toString()));
			}
			//se.printStackTrace();
			throw new Exception(getIHTP().getErrorHTML("[MODUL HTP REKOD] RALAT KEMASUKAN MAKLUMAT TANAH RIZAB (SIMPAN), "+se.toString()));
				
		}finally {
				if (conn != null) conn.close();
		}	
		return idHakmilik;
			
	}
	
	// INSERT TBLHTPHAKMILIK
	public String insertMilik(Db db,Hashtable<String,String> data) throws Exception {
		String idHakmilik = "";
	
		 try{
	    	  idHakmilik = String.valueOf(DB.getNextID(db,"TBLHTPHAKMILIK_SEQ"));
			  Statement stmt = db.getStatement();
			  SQLRenderer r = new SQLRenderer();
	    	  r.add("ID_HAKMILIK", r.unquote(idHakmilik));
	    	  r.add("ID_PERMOHONAN", data.get("idPermohonan"));
	    	  
	    	  r.add("ID_NEGERI", data.get("socNegeriHR"));
	    	  r.add("ID_DAERAH", data.get("socDaerahHR"));
	    	  r.add("ID_MUKIM", data.get("socMukimHR"));
	    	  r.add("ID_SEKSYENUPI", data.get("socSeksyenUPI"));
	    	  
	    	  r.add("NO_FAIL", data.get("txtNoFail"));
	    	  r.add("NO_FAIL_PTG", data.get("txtFailPTG"));
	    	  r.add("NO_FAIL_PTD", data.get("txtFailPTD"));			
	    	  r.add("NO_FAIL_KJP", data.get("txtFailKJP"));

	    	  //convert date before add
			  String tarikhTerima = (String)(data.get("txdTarikhTerima"));
			  String txdTarikhTerima = "to_date('" + tarikhTerima + "','dd/MM/yyyy')";	    	  
			  r.add("TARIKH_TERIMA",r.unquote(txdTarikhTerima));  	
			  //convert date before add
			  String tarikhDaftar = (String)data.get("txdTarikhWarta");
			  String txdTarikhDaftar = "to_date('" + tarikhDaftar + "','dd/MM/yyyy')";	  			  			  
			  r.add("TARIKH_DAFTAR", r.unquote(txdTarikhDaftar));
			  
			  r.add("ID_JENISHAKMILIK", data.get("socJenisHakmilikHR"));
				//add by rosli format 0000000
				//r.add("NO_HAKMILIK", data.get("txtNoHakmilik"));
				//String ph = ""+data.get("socNegeriHR")+data.get("socDaerahHR")+data.get("socMukimHR")+data.get("socKodJenisHakmilikHR")+data.get("txtNoHakmilik");
				//comment on 07/06/2010
				//r.add("NO_HAKMILIK",String.format("%08d",Integer.parseInt(String.valueOf(data.get("txtNoHakmilik")))));
			  r.add("NO_HAKMILIK",data.get("txtNoHakmilik"));
			  String ph = FrmUtilData.getKodNegeri(String.valueOf(data.get("socNegeriHR")));
			  ph += FrmUtilData.getKodDaerah(String.valueOf(data.get("socDaerahHR")));
			  ph += FrmUtilData.getKodMukim(String.valueOf(data.get("socMukimHR")));
			  ph += FrmUtilData.getKodJenisHakmilik(String.valueOf(data.get("socJenisHakmilikHR")));
				//comment on 07/06/2010
				//ph += String.format("%08d",Integer.parseInt(String.valueOf(data.get("txtNoHakmilik"))));
			  ph += data.get("txtNoHakmilik");
			  if(!data.get("noBangunan").equals("") && !data.get("noTingkat").equals("") && !data.get("noPetak").equals("")){	
				ph += String.valueOf(data.get("noBangunan"))
						+String.valueOf(data.get("noTingkat"))
						+String.valueOf(data.get("noPetak"));
			  }			
		
			  r.add("PEGANGAN_HAKMILIK", ph);
			  r.add("ID_LOT", data.get("socLotHR"));
			  r.add("NO_LOT", data.get("txtNoLot"));
			  //tambah pada 20100709 - hakmilik strata
			  r.add("NO_BANGUNAN", data.get("noBangunan"));				
			  r.add("NO_TINGKAT", data.get("noTingkat"));				
			  r.add("NO_PETAK", data.get("noPetak"));		
				
			  r.add("ID_LUAS", data.get("socLuas"));
			  r.add("LUAS", data.get("txtLuasGabung"));
			  r.add("ID_LUAS_BERSAMAAN","2");//Default utk hektar
			  r.add("LUAS_BERSAMAAN", Utils.RemoveComma((String)data.get("txtLuas")));
						
			  r.add("TARAF_HAKMILIK", data.get("socTaraf"));
			  r.add("TEMPOH", data.get("txtTempoh"));
			  //convert date before add
			  String tarikhLuput = (String)data.get("txdTarikhLuput");
			  String txdTarikhLuput = "to_date('" + tarikhLuput + "','dd/MM/yyyy')";	  			  			 
			  r.add("TARIKH_LUPUT", r.unquote(txdTarikhLuput));
				
			  r.add("CUKAI", Utils.RemoveComma((String)data.get("txtCukaiTahun")));
			  //r.add("CUKAI_TERKINI", Utils.RemoveComma((String)data.get("txtCukaiTerkini")));
			  r.add("CUKAI_TERKINI", Utils.RemoveComma((String)data.get("txtCukaiTahun")));
	    	  
			  r.add("LOKASI", data.get("txtLokasi"));
			  r.add("KEGUNAAN_TANAH", data.get("txtKegunaanTanah"));

			  r.add("ID_KATEGORI", data.get("socKategori"));
			  r.add("ID_SUBKATEGORI",FrmUtilData.getSubCategory((String)data.get("socKategori")));
			  
			  r.add("NO_RIZAB", data.get("txtNoWarta"));
			  //convert date before add
			  String tarikhWarta = (String)data.get("txdTarikhWarta");
			  String txdTarikhWarta = "to_date('" + tarikhWarta + "','dd/MM/yyyy')";		    	  
			  r.add("TARIKH_RIZAB", r.unquote(txdTarikhWarta));
			  r.add("ID_RIZAB", data.get("socJenisRizab"));    
			  r.add("KAWASAN_RIZAB", data.get("txtKawasanRizab"));
			  
	    	  r.add("NO_PU", data.get("txtNoPu"));
	    	  r.add("NO_SYIT", data.get("txtNoSyit"));
	    	  r.add("NO_PELAN", data.get("txtNoPelan"));
	    	  
	    	  r.add("SYARAT", data.get("txtSyarat"));	  
	    	  r.add("SEKATAN", data.get("txtSekatan"));	  
	    	  r.add("CATATAN", data.get("catatan"));		    	  
	    	  r.add("STATUS_SAH", data.get("socStatus"));
	    	  
	    	  r.add("ID_KEMENTERIAN", data.get("idKementerian"));
	    	  r.add("ID_AGENSI", data.get("idAgensi"));
				
	    	  r.add("TARIKH_MASUK", r.unquote("sysdate"));
			  r.add("ID_MASUK", r.unquote(String.valueOf(data.get("idMasuk"))));	
			  r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));	  
			  r.add("ID_KEMASKINI", r.unquote(String.valueOf(data.get("idMasuk"))));;			    
			
			  sql = r.getSQLInsert("TBLHTPHAKMILIK");
			  //mylog.info("sql insert TBLHTPHAKMILIK (TANAH RIZAB) :"+sql);
		      stmt.executeUpdate(sql);
		    
	    } catch (Exception e) {
	    	throw new Exception(getIHTP().getErrorHTML("[MODUL HTP REKOD] RALAT KEMASUKAN MAKLUMAT TANAH RIZAB (SIMPAN), "+e.toString()));
	    }
	    return idHakmilik;
    
	}
	
	private IHtp getIHTP(){
		if(iHTP== null)
			iHTP = new HtpBean();
		return iHTP;
	}		  
	private ITanahKementerian getTanahKem(){
		if(iTanahKem== null)
			iTanahKem = new FrmTanahKementerianBean();
		return iTanahKem;
	}	
	
}
