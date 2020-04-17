package ekptg.faraid;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import Faraid.Library.Constant.GenderEnum;
import Faraid.Library.Constant.RelationEnum;

public class Engine{
	
	private static Vector listWarisSatuLapisan = new Vector();
	private static Vector listWarisBerlapis = new Vector();
	private static Vector paparSiMati = new Vector();

	public static void paparSiMati (int id_permohonan)throws Exception{
		//Papar Maklumat SiMati
		Db db = new Db();
		paparSiMati.clear();
		try{
		String sqlSiMati = "SELECT C.NAMA_SIMATI, C.NO_KP_BARU,C.NO_KP_LAMA, C.TARIKH_MATI" +
				" FROM TBLPPKPERMOHONANSIMATI A, TBLPPKPERMOHONAN B, TBLPPKSIMATI C" +
				" WHERE B.ID_PERMOHONAN = A.ID_PERMOHONAN" +
				" AND A.ID_SIMATI = C.ID_SIMATI" +
				" AND A.ID_PERMOHONAN = " +id_permohonan;
  
		Statement stmt = db.getStatement();
		ResultSet rsSiMati = stmt.executeQuery(sqlSiMati);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Hashtable hSiMati;
		while (rsSiMati.next()){
			hSiMati = new Hashtable();	
			hSiMati.put("namaSiMati",rsSiMati.getString("NAMA_SIMATI")== null?"":rsSiMati.getString("NAMA_SIMATI"));
			
			if(rsSiMati.getString("NO_KP_BARU")!= null && rsSiMati.getString("NO_KP_BARU") != ""){
				hSiMati.put("noKp",rsSiMati.getString("NO_KP_BARU"));
			}
			else if (rsSiMati.getString("NO_KP_BARU")== null && rsSiMati.getString("NO_KP_BARU") == ""){
				hSiMati.put("noKp",rsSiMati.getString("NO_KP_LAMA"));
			}
			else{
				hSiMati.put("noKp","");
			}
			hSiMati.put("tarikhMati",rsSiMati.getDate("TARIKH_MATI")== null?"":sdf.format(rsSiMati.getDate("TARIKH_MATI")));
			paparSiMati.addElement(hSiMati);
		  }
		}
		 finally {
		      if (db != null) db.close();
		    }  
		
	}
	public static Vector getSiMati(){
		 
		  return paparSiMati;
	  } 
	public static void senaraiWarisSatuLapisan(int id_permohonan)throws Exception{
		 	//Senarai Waris dan Bahagian
		 	
			System.out.println ("Waris Single Layer");
			Db db = new Db(); 	
		 	long lBase[] = {0,0};
		    long lTashieh[] = {0,0};
		    Faraid.Engine.Syafie oEngine;
			String id_simati="",jantina="",relation="";
			String sqlJantinaSiMati="";
			String sqlWaris = "";
			String sqlRelation = "";
			String nama="",no_kp="",lapisan = "", statusHidup = "";
			String countRelation[][]= new String[0][0];
			listWarisSatuLapisan.clear();
	       
			try { 
			// create Faraid Engine
			oEngine = new Faraid.Engine.Syafie();
			
			sqlJantinaSiMati ="SELECT ID_PERMOHONAN,ID_SIMATI," +
					"(SELECT JANTINA FROM TBLPPKSIMATI WHERE ID_SIMATI=A.ID_SIMATI ) AS JANTINA " +
					"FROM TBLPPKPERMOHONANSIMATI A WHERE " +
					"ID_PERMOHONAN="+id_permohonan; 
			//get some data 
			System.out.println("sqlJantinaSimati : " +sqlJantinaSiMati);
			ResultSet rsJantinaSiMati = db.getStatement().executeQuery(sqlJantinaSiMati); 
			if ( rsJantinaSiMati.next() ) { 
				id_simati = rsJantinaSiMati.getString("ID_SIMATI");
				jantina = rsJantinaSiMati.getString("JANTINA");
			} 
			
				oEngine.SetGender((GenderEnum)ConvertTextEnumJantina(jantina));
			// count relation
			 	sqlRelation = "SELECT A.KETERANGAN, COUNT(*) AS COUNT_RELATION" +
				" FROM TBLPPKRUJSAUDARA A,TBLPPKOB B, TBLPPKPERMOHONANSIMATI C" +
				" WHERE B.ID_SAUDARA = A.ID_SAUDARA" +
				" AND B.ID_SIMATI = C.ID_SIMATI" +
				" AND C.ID_PERMOHONAN = " +id_permohonan +
				" GROUP BY KETERANGAN";
			 	
			 	System.out.println("Count relation");
			 	System.out.println("sqlRelation : " +sqlRelation);
			   	ResultSet rsJumHbgn = db.getStatement().executeQuery(sqlRelation); 	
			   	Hashtable parameters = new Hashtable();
				while (rsJumHbgn.next()){					
					if (rsJumHbgn.getString("KETERANGAN") != null && rsJumHbgn.getString("KETERANGAN") != ""){
						relation = rsJumHbgn.getString("KETERANGAN");
						oEngine.SetRelationCount((RelationEnum)ConvertTextEnumHubungan(relation),rsJumHbgn.getInt("COUNT_RELATION"));
						parameters.put(relation,Integer.parseInt(rsJumHbgn.getString("COUNT_RELATION")));
					}
					else{
						relation = "";
					}
				}
				// calculate fraction
				  oEngine.Calculate();
				  String hubungan="";
				  int countHbgn = 0;
				  Hashtable simpanlTashiehAtas = new Hashtable();
				  Hashtable simpanlTashiehBwh = new Hashtable();
				  try{
					  for (Enumeration e = parameters.keys(); e.hasMoreElements();)
					   {
						  hubungan = (String)e.nextElement();
						  countHbgn = (Integer)parameters.get(hubungan);
						  
						  if (countHbgn != 1){
							oEngine.GetRelationFraction((RelationEnum)ConvertTextEnumHubungan(hubungan), lBase, lTashieh);
							 if (lTashieh[0] > 0){
								 simpanlTashiehAtas.put(hubungan,lTashieh[0]/countHbgn);
								 simpanlTashiehBwh.put(hubungan,lTashieh[1]);
								 System.out.println("lTashieh : " +lTashieh[0]/countHbgn + "/" +lTashieh[1]);
							 }
						  }
						  else{
							  oEngine.GetRelationFraction((RelationEnum)ConvertTextEnumHubungan(hubungan), lBase, lTashieh);
								 if (lTashieh[0] > 0){
									 simpanlTashiehAtas.put(hubungan,lTashieh[0]);
									 simpanlTashiehBwh.put(hubungan,lTashieh[1]);
									 System.out.println("lTashieh : " +lTashieh[0] + "/" +lTashieh[1]);

								 }
						  }
							
					   } 
				  } catch (Exception e) {e.printStackTrace();}

			// get the details waris
			sqlWaris = "SELECT ID_OB,NAMA_OB,NO_KP_BARU,(SELECT KETERANGAN FROM TBLPPKRUJSAUDARA " +
					"WHERE ID_SAUDARA=A.ID_SAUDARA) " +
					"AS RELATION FROM TBLPPKOB A WHERE ID_SIMATI="+
				  "(SELECT ID_SIMATI FROM TBLPPKPERMOHONANSIMATI WHERE ID_PERMOHONAN='"+id_permohonan+"')";
			
			System.out.println("SQL WARIS : " +sqlWaris);
			ResultSet rsWaris = db.getStatement().executeQuery(sqlWaris); 
			
			Hashtable hWaris;
			int bil = 1;
		    int count = 0;
		    String hubunganAtas = "";
		    String hubunganBwh = "";
		    long lTashiehAtas = 0;
		    long lTashiehBwh = 0;
		    
			while (rsWaris.next()) { 
				hWaris = new Hashtable();
				hWaris.put("idOb",rsWaris.getString("ID_OB"));
				hWaris.put("bil",bil);
				hWaris.put("namaWaris",rsWaris.getString("NAMA_OB"));
				hWaris.put("hubungan",rsWaris.getString("RELATION")== null?"":rsWaris.getString("RELATION"));
				
				 try{
					  for (Enumeration e1 = simpanlTashiehAtas.keys(); e1.hasMoreElements();)
					   {
						  hubunganAtas = (String)e1.nextElement();
						  lTashiehAtas = (Long)simpanlTashiehAtas.get(hubunganAtas);
						  
						  for (Enumeration e2 = simpanlTashiehBwh.keys(); e2.hasMoreElements();){
							  
							  hubunganBwh = (String)e2.nextElement();
							  lTashiehBwh = (Long)simpanlTashiehBwh.get(hubunganBwh);
							  
							  if ((hubunganBwh.equals(rsWaris.getString("RELATION")) && (hubunganAtas.equals(rsWaris.getString("RELATION"))))){
								  hWaris.put("lTashieh",lTashiehAtas + "/" +lTashiehBwh);
//								  hWaris.put("lTashieh[0]",lTashiehAtas);
//								  hWaris.put("lTashieh[1]",lTashiehBwh);
							  }
							  
						  }
						  
						 
							
					   } 
				  } catch (Exception e) {e.printStackTrace();}
	
				listWarisSatuLapisan.addElement(hWaris);
		        bil++;
		    	count++;
		    	
			} 
	            
			   if (count == 0){
		    	  hWaris = new Hashtable();
		    	  hWaris.put("idOb","");
		    	  hWaris.put("bil","");
		    	  hWaris.put("namaWaris","Tiada rekod.");
		    	  hWaris.put("hubungan","");
		    	  hWaris.put("lTashieh","");
		    	  listWarisSatuLapisan.addElement(hWaris);
		            
			 }
			  
			   
			} catch (Exception e) { } 
			finally { 
			//Close the database connection 
			if ( db != null ) db.close(); 
			} 
	 }
	 public static Vector getSenaraiWarisSatuLapisan(){
		 
		  return listWarisSatuLapisan;
	  }
	 public static void updateBhgnWarisSatuLapis (Hashtable data)throws Exception{
		  Db db = null;
		    String sql = "";
		    try
		    {
		    	  int idOb = (Integer)data.get("id_Ob");
			      String baFaraid = (String)data.get("ba_Faraid");
				  String bbFaraid = (String)data.get("bb_Faraid");
				  String idKemaskini = (String)data.get("id_Kemaskini");
				  
				  db = new Db();
				  Statement stmt = db.getStatement();
				  SQLRenderer r = new SQLRenderer();
				  r.update("id_Fail", idOb);
				  r.add("ba_Faraid", baFaraid);
				  r.add("bb_Faraid", bbFaraid);
				  r.add("id_Kemaskini",idKemaskini);
				  r.add("tarikh_Kemaskini",r.unquote("sysdate")); 
				
				  sql = r.getSQLUpdate("tblppkob");
			      stmt.executeUpdate(sql);
		    	
		    } finally {
			      if (db != null) db.close();
		    }
	 }
	 public static void senaraiWarisBerlapis(int id_permohonan,int jumWaris, int jumLapis)throws Exception{
		 	//Senarai Waris dan Bahagian
		 
			Db db = new Db(); 	
		 	long lBase[] = {0,0};
		    long lTashieh[] = {0,0};
		    Faraid.Engine.Syafie oEngine;
			Faraid.Engine.Layers oLayer;
			String id_simati="",jantina="",relation="";
			String sqlJantinaSiMati="";
			String sqlWaris = "";
			String sqlRelation = "";
			String nama="",no_kp="",id_ob="";
			String countRelation[][]= new String[0][0];
	        Hashtable fraction;
	        String sFinal[][];
	        
			try { 
			// create Faraid Engine
			oEngine = new Faraid.Engine.Syafie();
			oLayer = new Faraid.Engine.Layers();
			
			//initialize waris
			oLayer.Initialize(jumWaris, jumLapis);
			
			//insert heir detail		
			sqlJantinaSiMati ="SELECT ID_PERMOHONAN,ID_SIMATI," +
							  "(SELECT JANTINA FROM TBLPPKSIMATI WHERE ID_SIMATI=A.ID_SIMATI ) AS JANTINA " +
							  "FROM TBLPPKPERMOHONANSIMATI A WHERE " +
							  "ID_PERMOHONAN="+id_permohonan; 
					
			ResultSet rsJantinaSiMati = db.getStatement().executeQuery(sqlJantinaSiMati); 
				if ( rsJantinaSiMati.next() ) { 
							id_simati = rsJantinaSiMati.getString("ID_SIMATI");
							jantina = rsJantinaSiMati.getString("JANTINA");
				} 
						
			oEngine.SetGender((GenderEnum)ConvertTextEnumJantina(jantina));

			// get the details waris
			sqlWaris = "SELECT ID_OB,NAMA_OB,NO_KP_BARU,STATUS_HIDUP,TARIKH_MATI(SELECT KETERANGAN FROM TBLPPKRUJSAUDARA " +
						"WHERE ID_SAUDARA=A.ID_SAUDARA) " +
						"AS RELATION FROM TBLPPKOB A WHERE ID_SIMATI="+
					  "(SELECT ID_SIMATI FROM TBLPPKPERMOHONANSIMATI WHERE ID_PERMOHONAN='"+id_permohonan+"')" +
					  	"ORDER BY TARIKH_MATI ASC";
				
			ResultSet rsWaris = db.getStatement().executeQuery(sqlWaris); 
			Hashtable hWaris = new Hashtable();
			
			while (rsWaris.next()) {
				id_ob = rsWaris.getString("ID_OB");
				oLayer.InsertHeirDetail(id_ob, rsWaris.getString("NAMA_OB"));
				hWaris.put(rsWaris.getString("ID_OB"),rsWaris.getDate("TARIKH_MATI"));
			} 
			
			//assign layer header
			String idOb = "";
			String tarikhMati = "";
			int j = 1;
			try{
				
				for (Enumeration e = hWaris.keys(); e.hasMoreElements();){
					
					idOb = (String)e.nextElement();
					tarikhMati = (String)hWaris.get(idOb);
					
					if (!"".equals(tarikhMati)){
						
						oLayer.AssignLayerHeader(idOb, j + 1);
					}
					
				}
			}catch (Exception e){e.printStackTrace();}
			
			
			
			
			
			
					if(oLayer.Validate()){
						oLayer.Calculate();
						sFinal = new String [oLayer.GetFinalResultRowSize()][oLayer.GetFinalResultColumnSize()];
						oLayer.GetFinalResult(sFinal);
						
//						for (int iRow = 0; iRow < sFinal.length; iRow++){
//		                    for (int iColumn = 0; iColumn < sFinal[iRow].length; iColumn++){
//		                    	hWaris.put("lTashieh",( sFinal[iRow][iColumn]));
//		                    }
//		                    System.out.println("");
//		                }
						
					}
//					 if (count == 0){
//				    	  hWaris = new Hashtable();
//				    	  hWaris.put("idOb","");
//				    	  hWaris.put("bil","");
//				    	  hWaris.put("namaWaris","Tiada rekod.");
//				    	  hWaris.put("hubungan","");
//				    	  hWaris.put("lTashieh","");
//				    	  listWarisSatuLapisan.addElement(hWaris);
//				            
//					 }

			} catch (Exception e) { } 
			finally { 
			//Close the database connection 
			if ( db != null ) db.close(); 
			} 
						
			
	 }
	 public static Vector getSenaraiWarisBerlapis(){
		 
		  return listWarisBerlapis;
	  }
	 public static GenderEnum ConvertTextEnumJantina(String sGender) {
		 if ("1".equals(sGender)) return GenderEnum.Male;
		 else if ("2".equals(sGender)) return GenderEnum.Female;
		 else if ("L".equals(sGender)) return GenderEnum.Male;
		 else if ("P".equals(sGender)) return GenderEnum.Female;
		 else return GenderEnum.Unknown;
	 }
	 public static RelationEnum ConvertTextEnumHubungan(String sRelation) {
		 if ("ISTERI".equals(sRelation)) return RelationEnum.Wife;
		 else if ("SUAMI".equals(sRelation)) return RelationEnum.Husband;
		 else if ("ANAK LELAKI".equals(sRelation)) return RelationEnum.Son;
		 else if ("ANAK PEREMPUAN".equals(sRelation)) return RelationEnum.Daughter;
		 else if ("IBU".equals(sRelation)) return RelationEnum.Mother;
		 else if ("BAPA".equals(sRelation)) return RelationEnum.Father;
		 else if ("CUCU LELAKI".equals(sRelation)) return RelationEnum.SonOfSon;
		 else if ("CUCU PEREMPUAN DARI ANAK LELAKI".equals(sRelation)) return RelationEnum.DaughterOfSon;
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

}
