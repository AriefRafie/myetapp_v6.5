package ekptg.model.htp.rekod;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import ekptg.helpers.DB;
import ekptg.helpers.Utils;
import ekptg.model.htp.FrmUtilData;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHtp;

//import org.apache.log4j.Logger;

public class FrmTanahDaftarRizabBean implements ITanahDaftar{	
	private Db db = null;
	//private static Logger myLog = Logger.getLogger(ekptg.model.htp.rekod.FrmTanahDaftarBean.class);	
	private Connection conn = null;
	private IHtp iHTP = null;  
	private String sql = "";

	@Override
	// KEMASKINI RIZAB BY ID
	//ekptg.model.htp.FrmRekodPendaftaranHakmilikRizabData
	//public static void updateRizabById(Hashtable data) throws Exception { 
	public void kemaskini(Hashtable<String, String> data) throws Exception {
		//Db dbHakmilik = null;
		//Connection conn1 = null;
		//Connection conn2 = null;
		sql = "";
		String sqlHakmilikPerihal = "";
	    try{
	    	db = new Db();
			conn = db.getConnection();
	    	conn.setAutoCommit(false);
	    	
	    	Statement stmtHakmilik = db.getStatement();
			  SQLRenderer rHakmilik = new SQLRenderer();
			  rHakmilik.update("ID_HAKMILIK", data.get("idHakmilik"));
			  
			  
	    	  //convert date before add
			  String tarikhTerima = data.get("txdTarikhTerima").toString();
			  String txdTarikhTerima = "to_date('" + tarikhTerima + "','dd/MM/yyyy')";	    	  
			  rHakmilik.add("TARIKH_TERIMA",rHakmilik.unquote(txdTarikhTerima));  	
			  String tarikhWarta= data.get("txdTarikhWarta").toString();
			  String txdTarikhWarta = "to_date('" + tarikhWarta + "','dd/MM/yyyy')";	 		  
			  rHakmilik.add("TARIKH_WARTA",rHakmilik.unquote(txdTarikhWarta));  	
			  rHakmilik.add("ID_LOT", "1");
			  rHakmilik.add("NO_WARTA", data.get("txtNoWarta"));
			  rHakmilik.add("NO_LOT", data.get("txtNoLotR"));
			  rHakmilik.add("ID_LUAS",data.get("socLuas"));
			  rHakmilik.add("LUAS",  data.get("txtLuasLama"));
			  
			  rHakmilik.add("ID_LUAS_BERSAMAAN","2");
			  rHakmilik.add("LUAS_BERSAMAAN",data.get("txtLuas")==""?"":Utils.RemoveComma(data.get("txtLuas").toString()));
			  
			  
			  rHakmilik.add("NO_PELAN", data.get("txtNoPelan"));
			  rHakmilik.add("NO_PU", data.get("txtNoPu"));
			  rHakmilik.add("NO_SYIT", data.get("txtNoSyit"));
			  rHakmilik.add("LOKASI", data.get("txtLokasi"));
			  rHakmilik.add("NO_FAIL_JOPA", data.get("txtNoFailJopa"));	 
			  if(!data.get("socStatus").equals("")){
				  rHakmilik.add("STATUS_SAH",data.get("socStatus"));
			  }
			  rHakmilik.add("KEGUNAAN_TANAH", data.get("txtKegunaanTanah"));
			  rHakmilik.add("CATATAN", data.get("catatan"));   
			  rHakmilik.add("ID_JENISRIZAB", data.get("socjenisrizab"));   			  
			  rHakmilik.add("TARIKH_KEMASKINI", rHakmilik.unquote("sysdate"));	  
			  rHakmilik.add("ID_KEMASKINI", data.get("idMasuk"));;			  

			  sql = rHakmilik.getSQLUpdate("TBLHTPHAKMILIK");
			  System.out.println(":::: sql : "+sql);
			  stmtHakmilik.executeUpdate(sql);
			  
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
			rHakmilikPerihal.add("KEGUNAAN_TANAH", data.get("txtKegunaanTanah"));
			rHakmilikPerihal.add("TARIKH_KEMASKINI", rHakmilikPerihal.unquote("sysdate"));	  
			rHakmilikPerihal.add("ID_KEMASKINI", data.get("idMasuk"));;			  
			sqlHakmilikPerihal = rHakmilikPerihal.getSQLUpdate("TBLHTPHAKMILIKPERIHAL");
			stmtHakmilikPerihal.executeUpdate(sqlHakmilikPerihal);
			conn.commit();
		
		}catch(Exception e1){
			try{
				conn.rollback();
			}catch(SQLException sqle){
				sqle.printStackTrace();
				throw new Exception(getIHTP().getErrorHTML("[MODUL HTP REKOD] RALAT KEMASKINI MAKLUMAT TANAH RIZAB (<i>ROLLBACK</i>), "+sqle.toString()));
			
			}
			e1.printStackTrace();
			throw new Exception(getIHTP().getErrorHTML("[MODUL HTP REKOD] RALAT KEMASKINI MAKLUMAT TANAH RIZAB , "+e1.toString()));
			
		}finally {
			if (db != null)db.close();
		}		  
		
	}

	@Override
	//ekptg.model.htp.FrmUtilData
	//public static String insertHTPRizabTransaction(Hashtable data,String userId) throws Exception {
	public String simpan(Hashtable<String, String> hashData) throws Exception {
		String idHakmilik = "";
		//FrmRekodUtilData frmRekodUtilData = null;
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			//frmRekodUtilData = FrmRekodUtilData.getInstance();
			//idHakmilik = frmRekodUtilData.insertHTPRizab(db,hashData);
			idHakmilik = insertHTPRizab(db,hashData);
			//Kegunaan tanah tidak disimpan disini
			//FrmUtilData.insertHakmilikPerihal(db,data,idHakmilik);
			FrmUtilData.insertHakmilikAgensi(db,hashData,idHakmilik);
			conn.commit();

	    }catch (SQLException se) {
	    	try {
	    		conn.rollback();
			} catch (SQLException se2) {
				throw new Exception(getIHTP().getErrorHTML("[MODUL HTP REKOD] RALAT KEMASUKAN MAKLUMAT TANAH RIZAB (<i>ROLLBACK</i>), "+se2.toString()));
			}
			se.printStackTrace();
			throw new Exception(getIHTP().getErrorHTML("[MODUL HTP REKOD] RALAT KEMASUKAN MAKLUMAT TANAH RIZAB (SIMPAN), "+se.toString()));
				
		}finally {
				if (conn != null) conn.close();
		}	
		return idHakmilik;
			
	}
	
	// INSERT TBLHTPHAKMILIK
	public String insertHTPRizab(Db db,Hashtable data) throws Exception {
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
	    	  r.add("NO_WARTA", data.get("txtNoWarta"));
	    	  r.add("NO_FAIL", data.get("txtNoFail"));
	    	  r.add("NO_FAIL_KJP", data.get("txtFailKJP"));
	    	  r.add("NO_FAIL_PTG", data.get("txtFailPTG"));
	    	  r.add("NO_FAIL_PTD", data.get("txtFailPTD"));			    	  

	    	  //convert date before add
			  String tarikhTerima = (String)(data.get("txdTarikhTerima"));
			  String txdTarikhTerima = "to_date('" + tarikhTerima + "','dd/MM/yyyy')";	    	  
			  r.add("TARIKH_TERIMA",r.unquote(txdTarikhTerima));  	
			  //convert date before add
			  String tarikhDaftar = (String)data.get("txdTarikhWarta");
			  String txdTarikhDaftar = "to_date('" + tarikhDaftar + "','dd/MM/yyyy')";	  			  			  
	    	  r.add("TARIKH_WARTA", r.unquote(txdTarikhDaftar));
	    	  r.add("LOKASI", data.get("txtLokasi"));
	    	  r.add("ID_LUAS", data.get("socLuas"));
	    	  //r.add("LUAS", data.get("txtLuas"));
	    	  //24/09/2010
	    	  r.add("LUAS", data.get("txtLuasLama"));
	    	  r.add("ID_LUAS_BERSAMAAN", 2);
	    	  r.add("LUAS_BERSAMAAN", data.get("txtLuasBersamaan"));
	    	  r.add("NO_SYIT", data.get("txtNoSyit"));
	    	  r.add("NO_PELAN", data.get("txtNoPelan"));
	    	  r.add("NO_PU", data.get("txtNoPu"));
	    	  r.add("STATUS_SAH", data.get("socStatus"));
		      r.add("ID_KEMENTERIAN", data.get("idKementerian"));
	    	  r.add("ID_AGENSI", data.get("idAgensi"));
	    	  //kena ubah di controller dulu
	    	  r.add("ID_LOT", data.get("socLot"));
	    	  //azam change to txtNoLotR
	    	  //r.add("NO_LOT", data.get("txtNoLot"));
	    	  r.add("NO_LOT", data.get("txtNoLotR"));
	    	  
	    	  r.add("ID_JENISHAKMILIK", 0); //0=TIADA MAKLUMAT
	    	  //comment on 07/06/2010
	    	  //String ph = ""+data.get("socNegeriHR")+data.get("socDaerahHR")+data.get("socMukimHR")+data.get("txtNoWarta");
	    	  String ph = FrmUtilData.getKodNegeri(String.valueOf(data.get("socNegeriHR")));
	    	  ph += FrmUtilData.getKodDaerah(String.valueOf(data.get("socDaerahHR")));
	    	  ph += FrmUtilData.getKodMukim(String.valueOf(data.get("socMukimHR")));
	    	  ph += data.get("txtNoWarta");						    
	    	  r.add("PEGANGAN_HAKMILIK", ph);			    	  
	    	  r.add("CUKAI", r.unquote(String.valueOf("0.00")));
	    	  //r.add("TARAF_HAKMILIK", data.get("socTaraf")); P=Pajakan,F=Pegangan Bebas
	    	  //r.add("TEMPOH", data.get("txtTempoh"));
	    	  r.add("ID_KATEGORI", 1); //Kategori 1=TIADA MAKLUMAT	
	    	  r.add("ID_SUBKATEGORI",109); //Sub Kategori 109=TIADA MAKLUMAT					    	  
	    	  r.add("CUKAI_TERKINI", r.unquote(String.valueOf("0.00")));
	    	  r.add("ID_RIZAB", 0);//0=TIADA MAKLUMAT    
		      r.add("STATUS_RIZAB", "T");
	    	  r.add("TARIKH_MASUK", r.unquote("sysdate"));
			  r.add("ID_MASUK", r.unquote(String.valueOf(data.get("idMasuk"))));	
			  r.add("TARIKH_KEMASKINI", r.unquote("sysdate"));	  
			  r.add("ID_KEMASKINI", r.unquote(String.valueOf(data.get("idMasuk"))));;			    
			  r.add("KEGUNAAN_TANAH", data.get("txtKegunaanTanah"));
			  r.add("CATATAN", data.get("txtCatatan"));
			  r.add("ID_JENISRIZAB", data.get("socjenisrizab"));   			  
	  
			  sql = r.getSQLInsert("TBLHTPHAKMILIK");
			  //mylog.info("sql insert TBLHTPHAKMILIK (TANAH RIZAB) :"+sql);
		      stmt.executeUpdate(sql);
		    
	    } catch (Exception e) {
	    	throw new Exception(getIHTP().getErrorHTML("[MODUL HTP REKOD] RALAT KEMASUKAN MAKLUMAT TANAH RIZAB (SIMPAN), "+e.toString()));
	    }
	    return idHakmilik;
    
	}
	
	@Override
	public Hashtable<String,String> getMaklumat(String idRujukan) throws Exception{
		Hashtable<String,String> h =null;
		return h;
	}
	
	@Override
	public Vector <Hashtable<String,String>> getSenaraiMaklumat(String idRujukan) throws Exception{
		Vector <Hashtable<String,String>> vec =null;
		return vec;
	}
	
	@Override
	public void hapus(String idRujukan) throws Exception{
	}
	
	private IHtp getIHTP(){
		if(iHTP== null)
			iHTP = new HtpBean();
		return iHTP;
	}		  
	
	
}
