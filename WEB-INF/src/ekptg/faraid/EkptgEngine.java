
package ekptg.faraid;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;

import org.apache.log4j.Logger;

import Faraid.Library.Constant.GenderEnum;
import Faraid.Library.Constant.RelationEnum;
//import ekptg.faraid.EkptgEngine.Waris;

public class EkptgEngine {
	
	static Logger myLogger = Logger.getLogger(EkptgEngine.class);
	String sql;String sqlWhere;
    long lBase[] = {0,0};
    long lTashieh[] = {0,0};
	Hashtable<?, ?> parameters = null;
	Hashtable<?, ?> output = null;
	StringBuilder HTMLBuilder = new StringBuilder("");
	public String firstLayer_IDOB="";
	private Hashtable<String, String> firstLayer_IDOB2 = new Hashtable<String, String>();
	
	private static boolean debugmode = true;
 	//-- Testing 
	//--------------------
	public static void main (String args[]) throws Exception {
		try {
			long start = System.currentTimeMillis(); 
			//Faraid
			
			//EkptgEngine ekptgEngine = new EkptgEngine();
			//Simati sm = ekptgEngine.new Simati();
			//Vector hasilFaraid = new Vector();
			//77 - Abuya, 76 - Siti safiah ,66 - Embong Ahmad,55 - Mohammad Satar
			//YM - kes fail pindah 82
			//hasilFaraid = ekptgEngine.doAllFaraidProcessing("61","82",'0',ekptgEngine);
			//myLogger.info("record:"+hasilFaraid.size());
						
			//Akta 1958			
		    long elapsedTimeMillis = System.currentTimeMillis() - start; 
		    float elapsedTimeSecond = elapsedTimeMillis/1000f; 
		    myLogger.info("Time Taken = " + elapsedTimeSecond + " seconds"); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	// ****** DO ALL PROCESSING HERE
	public Vector<?> doAllFaraidProcessing(String id_simati,String id_permohonan,String id_permohonansimati,
		EkptgEngine ekptgEngine) 
		throws Exception {
		return doAllFaraidProcessing(id_simati,id_permohonan,id_permohonansimati,ekptgEngine,null);
	}
	
	// New
	public Vector<Waris> getWarisList(String id_simati,String id_permohonan, EkptgEngine ekptgEngine) throws Exception
	{
		Vector<Waris> listWaris = null;
		listWaris = ekptgEngine.getAllWarisData(id_simati, id_permohonan);
		return listWaris;
	}
	
	 public Vector<Waris> doAllFaraidProcessing(String id_simati,String id_permohonan,String id_permohonansimati,
		EkptgEngine ekptgEngine,Simati sm) 
		throws Exception
	 {
		 if (sm == null) {
			 sm = ekptgEngine.new Simati();
		 }
		 Vector<Waris> hasilFaraid = null;
		 try {
		 sm.setId_permohonan(id_permohonan);
		 sm.setId_simati(id_simati);
		 sm.setId_permohonansimati(id_permohonansimati);
		 ekptgEngine.getSimatiInfo(sm);
		 
		 int lapisan = ekptgEngine.getLapisan(id_simati,id_permohonan);
		 
		 Vector<Waris> listWaris = null;
		 Vector<Waris> hasilFaraidLapisan1 = null;
		 Vector<Waris> hasilFaraidBerikut = null;
		 hasilFaraid = new Vector<Waris>();
		 // Faraid Engine - Single layer
		 Faraid.Engine.Syafie oEngine = new Faraid.Engine.Syafie();
		 // Set Gender untuk Simati
		 if (debugmode) {
			 myLogger.info("Gender simati:"+sm.getJantina());
			 myLogger.info("Converted Gender simati:"+ConvertTextEnumJantina(sm.getJantina()));
		 }
		 
		try {
			oEngine.SetGender((GenderEnum)ConvertTextEnumJantina(sm.getJantina()));
		} catch (Exception x) {
			throw new Exception ("Simati="+sm.getNamaSimati()+" ( "+x.getMessage() + ")");
		}
		 ekptgEngine.setHTMLBuilder("** "+sm.getNamaSimati()+" (Meninggal)");
		 ekptgEngine.kosongkanBahagian(sm.getId_simati());
		 //Multi Layer Case
		 if (lapisan > 1) {
			 // Lapisan Pertama
		 	 Waris obInfo;
		 	 ekptgEngine.setHTMLBuilder("** Kes "+lapisan+" Lapis **");
			 //Multi Layer Init
		 	 Faraid.Engine.Layers oLayer = new Faraid.Engine.Layers();
			 int jumWaris = ekptgEngine.getJumWaris(id_simati,id_permohonan);
			 oLayer.Initialize(jumWaris, lapisan);
			 
			 //Masukkan semua waris dalam oLayers
			 listWaris = ekptgEngine.senaraiSemuaWaris(id_simati,id_permohonan);
			 for (int x=0;x<listWaris.size();x++) {
				obInfo = (Waris)listWaris.get(x);
				if (debugmode) {
					myLogger.info("InsertHeir :" +obInfo.getID()+":"+obInfo.getNama());
				}
				oLayer.InsertHeirDetail(obInfo.getID(),obInfo.getNama());
			 }
			 
			//Lapisan pertama
			hasilFaraidLapisan1 = ekptgEngine.doFirstLayer(ekptgEngine,oEngine,id_simati,id_permohonan);
			ekptgEngine.AssignLayerTashieh(oLayer,hasilFaraidLapisan1,1);
			
			//***** Lapisan-lapisan yang berikut
			Vector<Waris> waris_meninggal = ekptgEngine.getWarisMeninggal(hasilFaraidLapisan1);
			
			//change to dapatkan senarai waris by id permohonan - //todo
			
			String id_ob_yg_meninggal = "";
			for (int i=0;i<waris_meninggal.size();i++) {
				obInfo = (Waris)waris_meninggal.get(i);
				oLayer.AssignLayerHeader(obInfo.getID(),(i+2));
				id_ob_yg_meninggal = id_ob_yg_meninggal + obInfo.getID() + ","; //remember id_ob yg sudah meninggal
				
				//create new Faraid object - Single Layer
				oEngine = new Faraid.Engine.Syafie();
				if (debugmode) {
					 myLogger.info("Gender simati:"+obInfo.getJantina());
					 myLogger.info("Converted Gender simati:"+ConvertTextEnumJantina(obInfo.getJantina()));
				}
				try {
					oEngine.SetGender((GenderEnum)ConvertTextEnumJantina(obInfo.getJantina()));
				} catch (Exception x) {
					throw new Exception ("Simati="+obInfo.getNama()+" ( "+x.getMessage() + ")");
				}
				setHTMLBuilder("");
				ekptgEngine.setHTMLBuilder("** "+obInfo.getNama()+" (Meninggal)");
				
				// dapatkan senarai lapis n+1
				// getFirstLayer_IDOB2 = ID Semua Waris delimited by comma
				// getOneShorter = buang last delimited comma
				// sm = object simati
				hasilFaraidBerikut = ekptgEngine.doMultiLayer(ekptgEngine, oEngine,
						obInfo.getID(),
						getOneShorter(this.getFirstLayer_IDOB2()),
						getOneShorter(id_ob_yg_meninggal),
						sm.getJantina(), obInfo.getHubunganAsal());
				ekptgEngine.AssignLayerTashieh(oLayer,hasilFaraidBerikut,(i+2));
				hasilFaraidBerikut.clear();
			}
			
			
			//Final Result
			hasilFaraidBerikut.clear();
			hasilFaraid = ekptgEngine.getFinalResult(oLayer);
			ekptgEngine.setHTMLBuilder("");
			ekptgEngine.setHTMLBuilder("** Final Result **");
			ekptgEngine.displayResult(hasilFaraid,sm);	
			
		 } else { //Single Layer
				ekptgEngine.setHTMLBuilder("** Kes 1 Lapis **");
				hasilFaraid = ekptgEngine.doFirstLayer(ekptgEngine,oEngine,id_simati,id_permohonan);
				ekptgEngine.displayResult(hasilFaraid,sm);		 
		 }
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return hasilFaraid; 
	 
	 }
	// ******* END OF ALL PROCESSING
	public Vector<Waris> doFirstLayer(EkptgEngine ekptgEngine,Faraid.Engine.Syafie oEngine,
		String id_simati,String id_permohonan) 
		throws Exception{
		Vector<Waris> output = null;
		Vector<Waris> lists = null;
		try {
			output = new Vector<Waris>();
			lists = ekptgEngine.getFirstLayerData(id_simati,id_permohonan);
			ekptgEngine.addParamatersToFaraid(oEngine,lists);
			oEngine.Calculate();
			output = ekptgEngine.getOutputFromFaraid(oEngine,lists);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output;

	}	 
		//
		public Vector<Waris> doMultiLayer(EkptgEngine ekptgEngine,Faraid.Engine.Syafie oEngine,
				String id_ob,String id_ob_from_first_layer,
				String list_of_ids,
				String JantinaSimati,String HubunganWarisMati) 
				throws Exception{
			Vector<Waris> output = null;
			Vector<Waris> lists = null;
			try {
				output = new Vector<Waris>();
				lists = ekptgEngine.getMultiLayerData(id_ob, id_ob_from_first_layer, list_of_ids, JantinaSimati, HubunganWarisMati);
				ekptgEngine.addParamatersToFaraid(oEngine,lists);
				oEngine.Calculate();
				output = ekptgEngine.getOutputFromFaraid(oEngine,lists);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return output;

		}
		public Vector<Waris> getAllWarisData(String id_simati,String id_permohonan) 
				throws Exception {
			Db db = null;
			Vector<Waris> lists = null;
			Waris ob;
			try {
				db = new Db(); 
				lists = new Vector<Waris>();
				//Add Distinct - on 19/10/2009
				sql ="SELECT B.LAPIS,B.ID_OB,B.NAMA_OB,B.JANTINA,NVL(A.KETERANGAN,'Tiada Hubungan') KETERANGAN," +
				"B.STATUS_HIDUP,TO_CHAR(B.TARIKH_MATI,'DD/MM/YYYY') as TARIKH_MATI," +
				"(select count(*) from TBLPPKOB where ID_SIMATI=B.ID_SIMATI and lapis=B.LAPIS " +
				"and ID_SAUDARA=A.ID_SAUDARA) as Jumlah_Hubungan "+
				"FROM TBLPPKOB B LEFT OUTER JOIN TBLPPKPERMOHONANSIMATI C "+
				//"ON B.ID_PERMOHONANSIMATI = C.ID_PERMOHONANSIMATI "+
				"ON B.ID_SIMATI = C.ID_SIMATI "+
				"LEFT OUTER JOIN TBLPPKRUJSAUDARA A ON B.ID_SAUDARA = A.ID_SAUDARA "+
				"WHERE "+
				"B.id_tarafkptg in (1,8) " +
				"AND C.ID_SIMATI='"+id_simati+"' "+
				"AND C.ID_PERMOHONANSIMATI NOT IN (SELECT ID_PERMOHONANSIMATI FROM tblppkfailpindah)"+
				"AND C.ID_PERMOHONAN = '"+id_permohonan+"' "+
				"Order by KETERANGAN,LAPIS,B.tarikh_mati  ";
				
				if (debugmode) myLogger.info("getAllWarisData:"+sql);
				//get some data 
				ResultSet rs = db.getStatement().executeQuery(sql); 
				while (rs.next()){	
					ob = new EkptgEngine().new Waris();
					ob.setLapisan(rs.getString("LAPIS"));
					ob.setID(rs.getString("ID_OB"));
					ob.setNama(rs.getString("NAMA_OB"));
					ob.setJantina(rs.getString("JANTINA"));
					ob.setKeteranganJantina(rs.getString("KETERANGAN"));
					ob.setHubungan(rs.getString("KETERANGAN"));
					ob.setHubunganAsal(rs.getString("KETERANGAN"));
					ob.setStatus_Hidup(rs.getString("STATUS_HIDUP"));
					ob.setTarikhMati(rs.getString("TARIKH_MATI"));
					if (rs.getInt("Jumlah_Hubungan") == 0) {
						ob.setJumlahHubungan(1);
					}else {
						ob.setJumlahHubungan(rs.getInt("Jumlah_Hubungan"));
					}
					ob.setDirectWithSimatiAsal(true);
					//this.firstLayer_IDOB = this.firstLayer_IDOB + ob.getID() + ",";
					//firstLayer_IDOB2.put(ob.getID(),ob.getID());
					lists.add(ob);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if ( db != null ) db.close(); 
			}
		return lists;
		}
		// ******** LAYERS		
		public Vector<Waris> getFirstLayerData(String id_simati,String id_permohonan) 
		throws Exception {
			Db db = null;
			Vector<Waris> lists = null;
			Waris ob;
			try {
				db = new Db(); 
				lists = new Vector<Waris>();
				//Add Distinct - on 19/10/2009
				sql ="SELECT B.LAPIS,B.ID_OB,B.NAMA_OB,B.JANTINA,NVL(A.KETERANGAN,'Tiada Hubungan') KETERANGAN," +
				"B.STATUS_HIDUP,TO_CHAR(B.TARIKH_MATI,'DD/MM/YYYY') as TARIKH_MATI," +
				"(select count(*) from TBLPPKOB where ID_SIMATI=B.ID_SIMATI and lapis=B.LAPIS " +
				"and ID_SAUDARA=A.ID_SAUDARA) as Jumlah_Hubungan "+
				"FROM TBLPPKOB B LEFT OUTER JOIN TBLPPKPERMOHONANSIMATI C "+
				//"ON B.ID_PERMOHONANSIMATI = C.ID_PERMOHONANSIMATI "+
				"ON B.ID_SIMATI = C.ID_SIMATI "+
				"LEFT OUTER JOIN TBLPPKRUJSAUDARA A ON B.ID_SAUDARA = A.ID_SAUDARA "+
				"WHERE "+
				"B.LAPIS = 1 AND B.id_tarafkptg in (1,8) " +
				"AND C.ID_SIMATI='"+id_simati+"' "+
				"AND C.ID_PERMOHONANSIMATI NOT IN (SELECT ID_PERMOHONANSIMATI FROM tblppkfailpindah)"+
				"AND C.ID_PERMOHONAN = '"+id_permohonan+"' "+
				"Order by KETERANGAN,LAPIS,B.tarikh_mati  ";
				
				if (debugmode) myLogger.info("FIRST LAYER DATA:"+sql);
				//get some data 
				ResultSet rs = db.getStatement().executeQuery(sql); 
				while (rs.next()){	
					ob = new EkptgEngine().new Waris();
					ob.setLapisan(rs.getString("LAPIS"));
					ob.setID(rs.getString("ID_OB"));
					ob.setNama(rs.getString("NAMA_OB"));
					ob.setJantina(rs.getString("JANTINA"));
					ob.setKeteranganJantina(rs.getString("KETERANGAN"));
					ob.setHubungan(rs.getString("KETERANGAN"));
					ob.setHubunganAsal(rs.getString("KETERANGAN"));
					ob.setStatus_Hidup(rs.getString("STATUS_HIDUP"));
					ob.setTarikhMati(rs.getString("TARIKH_MATI"));
					if (rs.getInt("Jumlah_Hubungan") == 0) {
						ob.setJumlahHubungan(1);
					}else {
						ob.setJumlahHubungan(rs.getInt("Jumlah_Hubungan"));
					}
					ob.setDirectWithSimatiAsal(true);
					//this.firstLayer_IDOB = this.firstLayer_IDOB + ob.getID() + ",";
					firstLayer_IDOB2.put(ob.getID(),ob.getID());
					lists.add(ob);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if ( db != null ) db.close(); 
			}
		return lists;
		}
		//	 

		public Vector<Waris> getMultiLayerData(String id_ob,String id_ob_from_first_layer,
				String list_of_ids,
				String JantinaSimati,String HubunganWarisMati) 
		throws Exception {
			Db db = null;
			Connection conn = null;
			Vector<Waris> lists = null;
			Waris ob;
			firstLayer_IDOB = "";
			try {
				db = new Db(); 
				conn = db.getConnection();
		        conn.setAutoCommit(false);
		        Statement stmt = db.getStatement();
		        
		        //Prepare data first
		        //1.DELETE 
		        sql = "DELETE FROM TBL_FARAID_TMP WHERE ID='"+id_ob+"' ";
		        if (debugmode) myLogger.debug(sql);
		        stmt.executeUpdate(sql);
		        
		        //2.Insert
		        sql = "INSERT INTO TBL_FARAID_TMP " +
				"SELECT DISTINCT "+id_ob+",B.ID_OB,B.NAMA_OB,A.KETERANGAN AS Hubungan_Asal,CASE WHEN B.LAPIS = 1 THEN ( "+
				"Select NVL(upper(HBGN_BARU),'') AS HBGN_BARU From TBLRUJHUBUNGAN WHERE  "+
				"upper(JANTINA_SIMATI)='"+JantinaSimati.toUpperCase()+"' "+
				"AND upper(HBGN_WARIS_MATI)='"+HubunganWarisMati.toUpperCase()+"'  "+
				"AND upper(HBGN_WARIS_BERIKUT)=A.KETERANGAN AND ROWNUM<=1)  "+
				"ELSE (NVL(A.KETERANGAN,'Tiada Hubungan')) END  "+
				"AS Hubungan_Baru,0 as JUMLAH_HUBUNGAN  "+
				"FROM TBLPPKOB B LEFT OUTER JOIN TBLPPKPERMOHONANSIMATI C " +
				////"ON B.ID_PERMOHONANSIMATI = C.ID_PERMOHONANSIMATI "+ 
				"ON B.ID_SIMATI = C.ID_SIMATI "+ 
				"LEFT OUTER JOIN TBLPPKRUJSAUDARA A ON B.ID_SAUDARA = A.ID_SAUDARA ";
				sqlWhere = "WHERE id_tarafkptg in (1,8) AND B.ID_OB IN (select ID_OB from TBLPPKHUBUNGANOB where id_parentob='"+id_ob+"') ";
				if (id_ob_from_first_layer != null) {
					sqlWhere = sqlWhere + " OR ( " +
							"( B.ID_OB IN ("+id_ob_from_first_layer+") AND Status_Hidup=0 ) " +
						"OR " +
							 "( B.ID_OB IN ("+id_ob_from_first_layer+") AND Status_Hidup=1 AND " +
										  "B.ID_OB NOT IN ("+list_of_ids+") "+
						 ") "+
							")";
				}
		        
		        // Test - NEW QUERY PROVIDED BY RAZMAN
				 sql = "INSERT INTO TBL_FARAID_TMP SELECT " +
				"DISTINCT "+id_ob+", POB.ID_OB,POB.NAMA_OB,SAU.KETERANGAN AS Hubungan_Asal, " +
				"(CASE WHEN POB.LAPIS = 1 THEN TEMP_2.HBGN_BARU ELSE NVL(SAU.KETERANGAN,'Tiada Hubungan') END) AS Hubungan_Baru,0 as JUMLAH_HUBUNGAN " + 
				"FROM TBLPPKOB POB, TBLPPKPERMOHONANSIMATI PSM, TBLPPKRUJSAUDARA SAU, " +
				"(SELECT ID_OB FROM TBLPPKHUBUNGANOB WHERE ID_PARENTOB="+id_ob+") TEMP_1, " +
				"(SELECT NVL(UPPER(HBGN_BARU),'') AS HBGN_BARU,HBGN_WARIS_BERIKUT " +
				"FROM TBLRUJHUBUNGAN WHERE UPPER(JANTINA_SIMATI)='"+JantinaSimati.toUpperCase()+"' AND UPPER(HBGN_WARIS_MATI)='"+HubunganWarisMati.toUpperCase()+"' " +
				") TEMP_2 ";
				sqlWhere = "WHERE POB.ID_SIMATI = PSM.ID_SIMATI AND POB.ID_SAUDARA = SAU.ID_SAUDARA AND POB.ID_TARAFKPTG IN (1,8) " + 
						"AND UPPER(SAU.KETERANGAN) = UPPER(TEMP_2.HBGN_WARIS_BERIKUT(+)) ";
				if (id_ob_from_first_layer != null) {
					sqlWhere = sqlWhere + "AND ( " +
					"POB.ID_OB = TEMP_1.ID_OB " +
					"OR (CASE " + 
					"WHEN POB.STATUS_HIDUP=0 " +
					"AND (CASE WHEN POB.ID_OB IN ("+id_ob_from_first_layer+") THEN 1 END) = 1 " +
					"THEN 1 " +
					"WHEN POB.STATUS_HIDUP=1 " +
					"AND (CASE WHEN POB.ID_OB NOT IN ("+list_of_ids+") AND POB.ID_OB IN ("+id_ob_from_first_layer+") THEN 1 END) = 1 " +
					"THEN 1 " +
					"END) = 1) ";
				}
				
				if (debugmode) myLogger.debug(sql+sqlWhere);
				if (debugmode) myLogger.debug("sebelum");
		        stmt.executeUpdate(sql+sqlWhere);
		        if (debugmode) myLogger.debug("selepas");
		        
		        //3.Update
		        sql = "UPDATE TBL_FARAID_TMP A SET " +
		        	  "A.JUMLAH_HUBUNGAN = ( SELECT count(*) from TBL_FARAID_TMP WHERE HUBUNGAN_BARU = A.HUBUNGAN_BARU AND ID="+id_ob+") "+
		        	  "WHERE A.ID='"+id_ob+"'";
		        if (debugmode) myLogger.debug(sql);
		        stmt.executeUpdate(sql);
		        
		        
		        conn.commit();
		        
				lists = new Vector<Waris>();
		
				sql = "SELECT ID_OB,NAMA_OB,HUBUNGAN_ASAL,HUBUNGAN_BARU,JUMLAH_HUBUNGAN FROM " +
						"TBL_FARAID_TMP WHERE ID='"+id_ob+"' ";
				if (debugmode)myLogger.debug(sql);
				//get some data 
				ResultSet rs = db.getStatement().executeQuery(sql); 
				while (rs.next()){	
					ob = new EkptgEngine().new Waris();
					ob.setID(rs.getString("ID_OB"));
					ob.setNama(rs.getString("NAMA_OB"));
					ob.setHubungan(rs.getString("HUBUNGAN_BARU"));
					ob.setHubunganAsal(rs.getString("HUBUNGAN_ASAL"));
					ob.setJumlahHubungan(rs.getInt("JUMLAH_HUBUNGAN"));
					lists.add(ob);
				}
			}  catch (SQLException se) { 
				try {
		    		conn.rollback();
		    	} catch (SQLException se2) {
		    		throw new Exception("Rollback error:"+se2.getMessage());
		    	}
		    	throw new Exception("Ralat Jana Faraid:"+se.getMessage());
			} finally {
				if ( db != null ) db.close(); 
			}
			return lists;
		}
		//	
		
		public Vector<Waris> getWarisMeninggal(Vector<Waris> v) {
			Waris ob;
			Vector<Waris> lists = null;
			try {
				lists = new Vector<Waris>();
				for (int i=0;i<v.size();i++) {
					ob = (Waris)v.get(i);
					if ("1".equals(ob.getStatus_Hidup())) { 
						lists.add(ob);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return lists;
		}

		public Vector<Waris> getSenaraiWarisFromWarisMeninggal(Vector<?> v,String id_ob) {
			Waris ob;
			Vector<Waris> lists = null;
			try {
				lists = new Vector<Waris>();
				for (int i=0;i<v.size();i++) {
					ob = (Waris)v.get(i);
					if (id_ob.indexOf(ob.getID()+",") == -1) { // add jika waris belum meninggal
						lists.add(ob);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return lists;
		}
		
		//Mapping Hubungan diwaris yang kebawah		
		public String getConvertedWarisByIDOB(String id_ob) {
			
			if ("0".equals(id_ob)) return " - ";
			
			String output = "";
			Db db = null;
			try {
				db = new Db();
				sql = "select CASE WHEN C.JANTINA = '1' THEN 'LELAKI' WHEN C.JANTINA = '2' THEN 'PEREMPUAN' "+
					"ELSE 'X' END AS JANTINA_SIMATI, "+
					"(SELECT KETERANGAN FROM TBLPPKRUJSAUDARA  "+
					"WHERE ID_SAUDARA=A.ID_SAUDARA) AS HBGN_WARIS_MATI,  "+
					"( "+
					"SELECT KETERANGAN FROM TBLPPKRUJSAUDARA  WHERE  "+
					"ID_SAUDARA=(SELECT ID_SAUDARA FROM TBLPPKOB WHERE ID_OB=b.id_parentob)  "+
					") HBGN_WARIS_BERIKUT,A.LAPIS, "+
					"(SELECT KETERANGAN FROM TBLPPKRUJSAUDARA  "+
					"WHERE ID_SAUDARA=A.ID_SAUDARA) || ' KEPADA ' || "+
					"(SELECT NAMA_OB FROM TBLPPKOB WHERE ID_OB=B.ID_PARENTOB) AS  "+
					"HBGN_BARU  "+
					"from tblppkob A LEFT OUTER JOIN TBLPPKHUBUNGANOB B "+
					"ON A.ID_OB = B.ID_OB "+
					"LEFT OUTER JOIN tblppksimati C "+
					"ON A.ID_SIMATI = C.ID_SIMATI "+
					"where A.ID_OB='"+id_ob+"' ";
					myLogger.info("getConvertedWarisByIDOB: "+sql);
					ResultSet rs = db.getStatement().executeQuery(sql);
					if (rs.next()) {
						if ("1".equals(rs.getString("LAPIS"))) {
							output = rs.getString("HBGN_WARIS_MATI");
						} else {
							output = rs.getString("HBGN_BARU");
							//output = getHubunganDenganWarisAtas();
//						output = getConvertedWaris(rs.getString("JANTINA_SIMATI"),
//												   rs.getString("HBGN_WARIS_MATI"),
//												   rs.getString("HBGN_WARIS_BERIKUT"));
						}
						myLogger.info("getConvertedWarisByIDOB:" +output);
					}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if ( db != null ) db.close(); 
			}
			//myLogger.info("output from ID:"+output);
			return output;
		}
		
		public String getConvertedWaris(String JantinaSimati,String HubunganWarisMati,
				String HubunganWarisBerikut) {
			String output = "";
			Db db = null;
			try {
				db = new Db();
				sql = "Select NVL(upper(HBGN_BARU),'') AS HBGN_BARU From TBLRUJHUBUNGAN WHERE " +
						"upper(JANTINA_SIMATI)='"+JantinaSimati.toUpperCase()+"' " +
						"AND upper(HBGN_WARIS_MATI)='"+HubunganWarisMati.toUpperCase()+"' " +
						"AND upper(HBGN_WARIS_BERIKUT)='"+HubunganWarisBerikut.toUpperCase()+"' ";
				//myLogger.info(sql);
				ResultSet rs = db.getStatement().executeQuery(sql);
				if (rs.next()) {
					output = rs.getString("HBGN_BARU");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if ( db != null ) db.close(); 
			}
			return output;
		}

		//---
		
		public String getHubunganDenganWarisAtas(String id_ob) {
			String output = "";
			Db db = null;
			try {
				db = new Db();
				sql = "select (SELECT KETERANGAN FROM TBLPPKRUJSAUDARA "+
				"WHERE ID_SAUDARA=A.ID_SAUDARA) || ' ' || "+
				"(SELECT NAMA_OB FROM TBLPPKOB WHERE ID_OB=B.ID_PARENTOB) AS  "+
				"HBGN_BARU "+
				"from tblppkob A LEFT OUTER JOIN TBLPPKHUBUNGANOB B "+
				"ON A.ID_OB = B.ID_OB "+
				"where A.id_ob='"+id_ob+"' ";
				//myLogger.info(sql);
				ResultSet rs = db.getStatement().executeQuery(sql);
				if (rs.next()) {
					output = rs.getString("HBGN_BARU");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if ( db != null ) db.close(); 
			}
			return output;
		}
		// ******** COMMON	
		public void displayResult(Vector<Waris> v,Simati sm) {
			Waris ob;
			try {
				for (int i=0;i<v.size();i++) {
					ob = (Waris)v.get(i);
					setHTMLBuilder("id:"+ob.getID()+ ":" +ob.getNama()+"("+ob.getHubungan()+") = "+ob.getBahagian());
					//Jika Ada bahagian Utk BaitulMal, masukkan dalam table ppkob
					//Jika sudah ada
					if ("0".equals(ob.getID())) { // BaitulMal
						insertUpdateBaitulMal(sm,ob.getBahagianAtas(),ob.getBahagianBawah());
					}else {
						simpanBahagian(ob.getID(),ob.getBahagianAtas(),ob.getBahagianBawah());
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		////////////////////
		public Vector<Waris> getFinalResult(Faraid.Engine.Layers oLayer) throws Exception {
		    Vector<Waris> v=null;
			String sFinal[][];
	        int iRow ;
			if (oLayer.Validate()){
	            oLayer.Calculate();
	            sFinal = new String [oLayer.GetFinalResultRowSize()][oLayer.GetFinalResultColumnSize()];
	            //System.out.println("Final Result:");
	            oLayer.GetFinalResult(sFinal);
	            v =new Vector<Waris>();
	            Waris ob;
	            for (iRow = 0; iRow < sFinal.length; iRow++){
	            	ob = new EkptgEngine().new Waris();
	                if (sFinal[iRow][2].length() > 0) {
		                ob.setID(sFinal[iRow][0]);
		                ob.setNama(sFinal[iRow][1]);
		                ob.setBahagianAtas(Long.parseLong(sFinal[iRow][2]));
		                ob.setBahagianBawah(Long.parseLong(sFinal[iRow][3]));
		                ob.setHubungan(getConvertedWarisByIDOB(ob.getID()));
		                //ob.setHubungan("");
		                ob.setBahagian(ob.getBahagianAtas()+"/"+ob.getBahagianBawah());
	                v.add(ob);
	                }
	            }
	     	}			
			return v;
			
		}
		////////////////
		public void insertUpdateBaitulMal(Simati sm,long ba,long bb) {
			Db db = null;
			try {
				String ID_OB = getBaitulMalID(sm.getId_permohonansimati());
				//myLogger.info("ID_OB BAITULMAL:"+ID_OB+" ( "+ob.getBahagian() +") " +
				//		"atas:"+ob.getBahagianAtas()+ " bawah:"+ob.getBahagianBawah());
				if ("0".equals(ID_OB)) { // Id Belum Wujud.
					//Insert
					db = new Db();
					SQLRenderer r = new SQLRenderer();
					r.add("id_simati",sm.id_simati);
					//azam removed on 29/3/2010
					//due to error while deleting OB in pendaftaran
					//r.add("id_pemohon",sm.id_permohonan);
					r.add("id_permohonansimati",sm.getId_permohonansimati());
					r.add("nama_ob","BAITULMAL");
					r.add("status_hidup","0");
					r.add("id_tarafkptg","8");
					r.add("ba_faraid",ba);
					r.add("bb_faraid",bb);
					//r.add("id_masuk",userid);
					r.add("tarikh_masuk",r.unquote("sysdate")); 
					sql = r.getSQLInsert("tblppkob");
					myLogger.info("INSERT BAITULMAL:"+sql);
					db.getStatement().executeUpdate(sql);
				} else {
					//Update
					simpanBahagian(ID_OB,ba,bb);
				}
			} catch (Exception e) {
				
			} finally {
				if (db != null) db.close();
			}
			
		}		
		//
		public String getBaitulMalID(String id_permohonansimati) throws Exception{
			Db db = null;
			try {
				db = new Db();
				sql = "SELECT ID_OB from tblppkob where id_tarafkptg=8 and id_permohonansimati='"+id_permohonansimati+"' ";
				//myLogger.info(sql);
				ResultSet rs = db.getStatement().executeQuery(sql); 
				if (rs.next()) {
					return rs.getString("ID_OB");
				} else return "0";
			} catch (Exception e) {
				throw new Exception (e.getMessage());
			} finally {
				if (db != null) db.close();
			}
		}		
		
		public void addParamatersToFaraid(Faraid.Engine.Syafie oEngine,Vector<Waris> lists) {
			//myLogger.info("** addParamatersToFaraid **");
			Waris ob;
			try {
				for (int i=0;i<lists.size();i++) {
					ob = (Waris)lists.get(i);
					if (debugmode) myLogger.info("Set Relation Count:"+ ob.getNama()+"-"+ob.getHubungan()+" - "+ob.getJumlahHubungan());
					oEngine.SetRelationCount((RelationEnum)ConvertTextEnumHubungan(ob.getHubungan()),ob.getJumlahHubungan());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/////////////////		
		public void AssignLayerTashieh(Faraid.Engine.Layers oLayer,Vector<Waris> lists,int lapisan){
			setHTMLBuilder(" ** (Lapisan:"+lapisan+")**");
			Waris ob;
			try {
				for (int x=0;x<lists.size();x++) {
					ob = (Waris)lists.get(x);
					//myLogger.info("AssignLayerTashieh:"+"ID:"+ob.getID()+" - "+ob.getNama()+" ("+ob.getHubungan()+") "+ob.getBahagian());
					if (debugmode) {
						setHTMLBuilder("ID:"+ob.getID()+" - "+ob.getNama()+" ("+ob.getHubungan()+") "+ob.getBahagian());
						
					}
					if (ob.getBahagianAtas() > 0) {
						oLayer.AssignLayerTashieh(ob.getID(),lapisan,ob.getBahagianAtas(),ob.getBahagianBawah());
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		/////////////////
		public Vector<Waris> getOutputFromFaraid(Faraid.Engine.Syafie oEngine,Vector<Waris> lists) {
			Vector<Waris> FaraidOutput = null;
			Waris ob;
			try {
				//ekptgEngine.setHTMLBuilder("trying..");
				FaraidOutput = new Vector<Waris>();
				for (int i=0;i<lists.size();i++) {
				  ob = (Waris)lists.get(i);
				  //ekptgEngine.setHTMLBuilder(ob);
				  oEngine.GetRelationFraction((RelationEnum)ConvertTextEnumHubungan(ob.getHubungan()), lBase, lTashieh);
				  ob.setBahagian(lTashieh[0]/ob.getJumlahHubungan() + "/" + lTashieh[1]);
				  ob.setBahagianAtas(lTashieh[0]/ob.getJumlahHubungan());
				  ob.setBahagianBawah(lTashieh[1]);
				  FaraidOutput.add(ob);
				}
				oEngine.GetRelationFraction(RelationEnum.Bm, lBase, lTashieh);
				if (lTashieh[0] > 0){
					ob = new EkptgEngine().new Waris();
					ob.setID("0");
					ob.setNama("BAITULMAL");
					ob.setHubungan(" - ");
					ob.setJumlahHubungan(1);
					ob.setBahagian(lTashieh[0] + "/" + lTashieh[1]);
					ob.setBahagianAtas(lTashieh[0]);
					ob.setBahagianBawah(lTashieh[1]);
					FaraidOutput.add(ob);
				}
				//ekptgEngine.setHTMLBuilder("done trying..");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return FaraidOutput;
		}
		
		public void kosongkanBahagian(String id_simati)throws Exception {
		    Db db = null;
		    String sql = "";
		    try
		    {
				  db = new Db();
				  Statement stmt = db.getStatement();
				  SQLRenderer r = new SQLRenderer();
				  r.update("id_simati", id_simati);
				  r.add("ba_faraid",0);
				  r.add("bb_faraid",0);
				  sql = r.getSQLUpdate("tblppkob");
			      stmt.executeUpdate(sql);
			      
			      sql = "DELETE FROM tblppkob WHERE ID_SIMATI='"+id_simati+"' AND ID_TARAFKPTG=8";
			      myLogger.info(sql);
			      stmt.executeUpdate(sql);
			    }
			    finally {
			      if (db != null) db.close();
			    }
	}
		
		public void simpanBahagian(String id_ob,long ba_faraid, long bb_faraid)throws Exception {
			    Db db = null;
			    String sql = "";
			    try
			    {
					  db = new Db();
					  Statement stmt = db.getStatement();
					  SQLRenderer r = new SQLRenderer();
					  r.update("id_ob", id_ob);
					  r.add("ba_faraid", ba_faraid);
					  r.add("bb_faraid", bb_faraid);
					  
					  sql = r.getSQLUpdate("tblppkob");
				      stmt.executeUpdate(sql);
				    }
				    finally {
				      if (db != null) db.close();
				    }
		}

		public int checkIfAdaWaris(String id_ob) {
			int total = 0;
			Db db = null;
			try {
				db = new Db();
				sql = "select count(*) as total from TBLPPKHUBUNGANOB where id_parentob='"+id_ob+"'";
				ResultSet rs = db.getStatement().executeQuery(sql);
				if (rs.next()) {
					total = rs.getInt("total");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if ( db != null ) db.close(); 
			}
			
			return total;
		}
		//----------
		public int getJumWaris(String id_simati,String id_permohonan) {
			Db db = null;
			int jumWaris = 0;
			try {
				db = new Db(); 
				sql ="select count(*)+1 as jumWaris from TBLPPKOB where "+
					"ID_SIMATI='"+id_simati+"' AND "+
					"ID_PERMOHONANSIMATI NOT IN (SELECT ID_PERMOHONANSIMATI FROM tblppkfailpindah) ";
				if (debugmode) myLogger.info("getJumWaris sql+" + sql);
				//ekptgEngine.setHTMLBuilder(sql);
				ResultSet rs = db.getStatement().executeQuery(sql); 
				if ( rs.next() ) { 
					jumWaris = rs.getInt("jumWaris");
				} 
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if ( db != null ) db.close(); 
			}
			return jumWaris;
		}
		//-----------------------------------------
		public int getLapisan(String id_simati,String id_permohonan) {
			Db db = null;
			int lapisan = 0;
			try {
				db = new Db(); 
				sql ="select count(*)+1 as lapisan from TBLPPKOB where "+
					"ID_SIMATI = '"+id_simati+"' "+
					"AND ID_PERMOHONANSIMATI NOT IN (SELECT ID_PERMOHONANSIMATI FROM tblppkfailpindah) "+
					//"ID_PERMOHONANSIMATI=(SELECT ID_PERMOHONANSIMATI FROM TBLPPKPERMOHONANSIMATI WHERE " +
					//"ID_PERMOHONAN='"+id_permohonan+"') "+
					"AND lapis = 1 And status_hidup = 1";
				if (debugmode) myLogger.info(sql);
				//ekptgEngine.setHTMLBuilder(sql);
				ResultSet rs = db.getStatement().executeQuery(sql); 
				if ( rs.next() ) { 
					lapisan = rs.getInt("lapisan");
				} 
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if ( db != null ) db.close(); 
			}
			return lapisan;
		}
		
		//---------------------
		public void getSimatiInfo(Simati sm) throws Exception{
			Db db = null;
			//String jantina = null;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			try {
				db = new Db(); 
				sql ="Select a.id_simati,nama_simati," +
						"CASE WHEN NO_KP_BARU IS NOT NULL THEN  NO_KP_BARU "+
					    "WHEN NO_KP_BARU IS NULL AND NO_KP_LAMA IS NOT NULL THEN  NO_KP_LAMA "+
					    "WHEN NO_KP_BARU IS NULL AND NO_KP_LAMA IS NULL THEN  NO_KP_LAIN "+
					    "END AS NO_KP" +
						",tarikh_mati, " +
						"CASE WHEN a.jantina = '1' THEN 'Lelaki' WHEN a.jantina = '2' THEN 'Perempuan' " +
						"ELSE 'X' END AS Jantina,b.ID_PERMOHONANSIMATI " +
						"from tblppksimati a,Tblppkpermohonansimati b where a.ID_SIMATI = b.ID_SIMATI " +
						"AND a.id_simati='"+sm.id_simati+"' "+
						"AND id_permohonan='"+sm.id_permohonan+"' "; 
						//Removed this part since our record will be based on simati
						//"AND id_permohonan='"+sm.id_permohonan+"'"; 
				
				//setHTMLBuilder(sql);
				if (debugmode) myLogger.info(sql);
				ResultSet rs = db.getStatement().executeQuery(sql); 
				if ( rs.next() ) { 
					sm.setJantina(rs.getString("jantina"));
					sm.setNamaSimati(rs.getString("nama_simati"));
					sm.setId_simati(rs.getString("id_simati"));
					sm.setNoKp(rs.getString("NO_KP"));
					sm.setTarikhMati(sdf.format(rs.getDate("tarikh_mati")));
					sm.setId_permohonansimati(rs.getString("ID_PERMOHONANSIMATI"));
				} else {
					throw new Exception("Rekod Simati Tiada:id_permohonan="+sm.id_permohonan);
				}
				if (sm.getJantina() == null) throw new Exception("Jantina Simati Tiada");
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			} finally {
				if ( db != null ) db.close(); 
			}		
		}	
		///////////////////
		public Vector<Waris> senaraiSemuaWaris(String id_simati,String id_permohonan) 
		throws Exception{

			Db db = new Db();
			Vector<Waris> v = null;
			Waris ob;
			try{
			v=new Vector<Waris>();	
			sql = "SELECT B.LAPIS,B.ID_OB,B.NAMA_OB" +
				  " FROM TBLPPKRUJSAUDARA A , TBLPPKOB B, TBLPPKPERMOHONANSIMATI C" +
				  " WHERE B.ID_SAUDARA = A.ID_SAUDARA" +
				  " AND B.ID_SIMATI = C.ID_SIMATI" +
				  " AND B.ID_PERMOHONANSIMATI = C.ID_PERMOHONANSIMATI" +
				  //" AND C.ID_PERMOHONAN = '" + id_permohonan + "'"+
				  " AND B.ID_SIMATI = '" + id_simati + "'"+
				  " AND C.ID_PERMOHONANSIMATI NOT IN (select id_permohonansimati from tblppkfailpindah)"+ //Exclude from fail pindah cases
				  " UNION " +
				  " SELECT 1,0,'BaitulMal' FROM DUAL";
			
			ResultSet rs = db.getStatement().executeQuery(sql);
			if (debugmode) {
				myLogger.info("senaraiSemuaWaris:" + sql);
			}
			while (rs.next()) {
				//ob = new EkptgEngine.Waris();
				ob = new EkptgEngine().new Waris();
				ob.setID(rs.getString("ID_OB"));
				ob.setNama(rs.getString("NAMA_OB"));
				ob.setLapisan(rs.getString("LAPIS"));
				v.add(ob);
			}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if ( db != null ) db.close(); 
			}
			return v;

		}
		
	//------------ 
	 
	//GENERAL STUFF 
		
	public void setFirstLayer_IDOB(String txt) {
		this.firstLayer_IDOB = this.getFirstLayer_IDOB() + txt + ",";
	}
	
	public String getFirstLayer_IDOB() {
		return this.firstLayer_IDOB;
	}
	
	public String getFirstLayer_IDOB2() {
		
		String txt="";String name="";String value="";
		Enumeration<String> allparam = this.firstLayer_IDOB2.elements();
		for (; allparam.hasMoreElements(); ) {
	        name = (String)allparam.nextElement();
	        value = (String)this.firstLayer_IDOB2.get(name);
	        txt = txt + value + ",";
		}
		return txt;
	}
	public String splitString(String txt,int position) {
		String[] lookupValue = txt.split("/");
		return (String)lookupValue[position];
	}
		
	public void setHTMLBuilder(String txt) {
		myLogger.info(txt);
		HTMLBuilder.append(txt+"<br>");
	}
	
	public String getHTMLBuilder() {
		return HTMLBuilder.toString();
	}
	
	// 
	public String getOneShorter(String txt) {
		 //myLogger.debug("getOneShorter:"+txt);
		
		 if (txt == null || "".equals(txt)) return "";
		 return txt.substring(0,txt.length()-1);

	}
	
	//********************************
	//-- Ekptg to Faraid Engine Mapping
	
	 public static GenderEnum ConvertTextEnumJantina(String sGender) {
		 if ("1".equals(sGender)) return GenderEnum.Male;
		 else if ("2".equals(sGender)) return GenderEnum.Female;
		 else if ("L".equals(sGender)) return GenderEnum.Male;
		 else if ("P".equals(sGender)) return GenderEnum.Female;
		 else if ("Lelaki".equals(sGender)) return GenderEnum.Male;
		 else if ("Perempuan".equals(sGender)) return GenderEnum.Female;
		 else return GenderEnum.Unknown;
	 }
	 public static RelationEnum ConvertTextEnumHubungan(String sRelation) {
		 if ("ISTERI".equals(sRelation)) return RelationEnum.Wife;
		 else if ("ISTERI(X)".equals(sRelation)) return RelationEnum.Wife;
		 else if ("SUAMI".equals(sRelation)) return RelationEnum.Husband;
		 else if ("ANAK LELAKI".equals(sRelation)) return RelationEnum.Son;
		 else if ("ANAK PEREMPUAN".equals(sRelation)) return RelationEnum.Daughter;
		 else if ("IBU".equals(sRelation)) return RelationEnum.Mother;
		 else if ("BAPA".equals(sRelation)) return RelationEnum.Father;
		 else if ("CUCU LELAKI".equals(sRelation)) return RelationEnum.SonOfSon;
		 else if ("CUCU PEREMPUAN DARI ANAK LELAKI".equals(sRelation)) return RelationEnum.DaughterOfSon;
		 else if ("CUCU PEREMPUAN SEIBU SEBAPA".equals(sRelation)) return RelationEnum.DaughterOfSon;
		 else if ("SAUDARA LELAKI SEBAPA".equals(sRelation)) return RelationEnum.ConsanguineBrother;
		 else if ("SAUDARA LELAKI SEIBU".equals(sRelation)) return RelationEnum.UterineBrother;
		 else if ("SAUDARA LELAKI SEIBU SEBAPA".equals(sRelation)) return RelationEnum.FullBrother;
		 else if ("SAUDARA PEREMPUAN SEBAPA".equals(sRelation)) return RelationEnum.ConsanguineSister;
		 else if ("SAUDARA PEREMPUAN SEIBU".equals(sRelation)) return RelationEnum.UterineSister;
		 else if ("SAUDARA PEREMPUAN SEIBU SEBAPA".equals(sRelation)) return RelationEnum.FullSister;
		 else if ("ANAK LELAKI BAPA SAUDARA SEBAPA".equals(sRelation)) return RelationEnum.SonOfConsanguineBrotherOfFather;
		 else if ("ANAK LELAKI BAPA SAUDARA SEIBU SEBAPA".equals(sRelation)) return RelationEnum.SonOfFullBrother;
		 else if ("ANAK SAUDARA LELAKI SEBAPA".equals(sRelation)) return RelationEnum.SonOfConsanguineBrother;
		 else if ("ANAK SAUDARA LELAKI SEIBU SEBAPA".equals(sRelation)) return RelationEnum.SonOfFullBrother;
		 else if ("DATUK".equals(sRelation)) return RelationEnum.FatherOfFather;
		 else if ("BAPA SAUDARA SEBAPA".equals(sRelation)) return RelationEnum.FullBrotherOfFather;
		 else if ("BAPA SAUDARA SEIBU SEBAPA".equals(sRelation)) return RelationEnum.ConsanguineBrother;
		 else if ("NENEK PEREMPUAN SEBELAH BAPA (HINGGA ATAS)".equals(sRelation)) return RelationEnum.MotherOfFather;
		 else if ("NENEK PEREMPUAN SEBELAH IBU (HINGGA ATAS)".equals(sRelation)) return RelationEnum.MotherOfMother;
		 else if ("BAITUL MAL".equals(sRelation)) return RelationEnum.BaitulMal;
		 else return RelationEnum.NotDetermined;
	 }
	
	// *** INNER CLASSES
	
	 public class Simati {
		 
		private String Jantina;
		private String Nama;
		private String id_permohonan;
		private String id_simati;
		private String id_permohonansimati;
		private String id_pemohon;
		private String noKP;
		private String tarikhMati;
		private int lapisan;
		
		public int getLapisan() {
			return lapisan;
		}

		public void setLapisan(int lapisan) {
			this.lapisan = lapisan;
		}

		public Simati() {
			
		}
		
		public Simati(String id_permohonan) {
			this.id_permohonan = id_permohonan;
		}
		
		public String getId_simati() {
			return id_simati;
		}
		public void setId_simati(String id_simati) {
			this.id_simati = id_simati;
		}
		public String getJantina() {
			return Jantina;
		}
		public void setJantina(String jantina) {
			Jantina = jantina;
		}
		public String getNamaSimati() {
			return Nama;
		}
		public void setNamaSimati(String nama) {
			Nama = nama;
		}
		public String getId_permohonan() {
			return id_permohonan;
		}
		public void setId_permohonan(String id_permohonan) {
			this.id_permohonan = id_permohonan;
		}
		public String getNoKp() {
			return noKP;
		}
		public void setNoKp(String noKP) {
			this.noKP = noKP;
		}
		public String getTarikhMati() {
			return tarikhMati;
		}
		public void setTarikhMati(String tarikhMati) {
			this.tarikhMati = tarikhMati;
		}
		public String getId_permohonansimati() {
			return id_permohonansimati;
		}

		public void setId_permohonansimati(String id_permohonansimati) {
			this.id_permohonansimati = id_permohonansimati;
		}

		public String getId_pemohon() {
			return id_pemohon;
		}

		public void setId_pemohon(String id_pemohon) {
			this.id_pemohon = id_pemohon;
		}

		 
	 }
	 
	 //Inner class Waris
	 public class Waris {
		
		private String ID;
		private String Jantina;
		private String KeteranganJantina;
		private String Nama;
		private String Hubungan;
		private String HubunganAsal;
		private String Lapisan;
		private String Status_Hidup;
		private String TarikhMati;
		private String Bahagian;
		private String BahagianTashieh;
		private Long BahagianAtas;
		private Long BahagianBawah; 
		private Long BahagianAtasTashieh;
		private Long BahagianBawahTashieh; 
		private int JumlahHubungan;
		private boolean directWithSimatiAsal;

		public String getID() {
			return ID;
		}

		public void setID(String id) {
			ID = id;
		}
		
		public String getJantina() {
			return Jantina;
		}

		public void setJantina(String Jantina) {
			this.Jantina = Jantina;
		}

		public String getNama() {
			return Nama;
		}

		public void setNama(String nama) {
			Nama = nama;
		}

		public String getHubungan() {
			return Hubungan;
		}

		public void setHubungan(String hubungan) {
			Hubungan = hubungan;
		}

		public String getHubunganAsal() {
			return HubunganAsal;
		}

		public void setHubunganAsal(String hubunganAsal) {
			HubunganAsal = hubunganAsal;
		}

		public String getBahagianTashieh() {
			return BahagianTashieh;
		}

		public void setBahagianTashieh(String bahagianTashieh) {
			BahagianTashieh = bahagianTashieh;
		}
		
		public String getBahagian() {
			return Bahagian;
		}

		public void setBahagian(String bahagian) {
			Bahagian = bahagian;
		}

		public Long getBahagianAtas() {
			return BahagianAtas;
		}

		public void setBahagianAtas(long bahagianAtas) {
			BahagianAtas = bahagianAtas;
		}

		public Long getBahagianBawah() {
			return BahagianBawah;
		}

		public void setBahagianBawah(long bahagianBawah) {
			BahagianBawah = bahagianBawah;
		}

		public Long getBahagianAtasTashieh() {
			return BahagianAtasTashieh;
		}

		public void setBahagianAtasTashieh(Long bahagianAtasTashieh) {
			BahagianAtasTashieh = bahagianAtasTashieh;
		}

		public Long getBahagianBawahTashieh() {
			return BahagianBawahTashieh;
		}

		public void setBahagianBawahTashieh(Long bahagianBawahTashieh) {
			BahagianBawahTashieh = bahagianBawahTashieh;
		}
		
		public String getKeteranganJantina() {
			return KeteranganJantina;
		}

		public void setKeteranganJantina(String keteranganJantina) {
			KeteranganJantina = keteranganJantina;
		}

		public String getLapisan() {
			return Lapisan;
		}

		public void setLapisan(String lapisan) {
			Lapisan = lapisan;
		}

		public String getStatus_Hidup() {
			return Status_Hidup;
		}

		public void setStatus_Hidup(String status_Hidup) {
			Status_Hidup = status_Hidup;
		}

		public String getTarikhMati() {
			return TarikhMati;
		}

		public void setTarikhMati(String tarikhMati) {
			TarikhMati = tarikhMati;
		}

		public int getJumlahHubungan() {
			return JumlahHubungan;
		}

		public void setJumlahHubungan(int jumlahHubungan) {
			JumlahHubungan = jumlahHubungan;
		}

		public boolean isDirectWithSimatiAsal() {
			return directWithSimatiAsal;
		}

		public void setDirectWithSimatiAsal(boolean directWithSimatiAsal) {
			this.directWithSimatiAsal = directWithSimatiAsal;
		}
		 
	 }
	// *** END OF INNER CLASSES 

}
