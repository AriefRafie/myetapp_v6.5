package ekptg.faraid;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import Faraid.Library.Constant.GenderEnum;
import Faraid.Library.Constant.RelationEnum;

public class Test extends AjaxBasedModule{
	static Logger myLogger = Logger.getLogger(Test.class);
	private static Vector listWaris = new Vector();
	private static Vector paparSiMati = new Vector();
	public String doTemplate2() throws Exception
    {
		Db db = new Db(); 
		String vm = "app/ppk/frmFaraid.jsp";
		String id_permohonan = getParam("id_permohonan");
		Vector waris = new Vector();
		Vector simati = new Vector();
        HttpSession session = this.request.getSession();
        String action = getParam("action");
        this.context.put("action",action); 
        
        paparSiMati(Integer.parseInt(id_permohonan));
	    simati = getSiMati();
	    	
	    Hashtable paparSimati = (Hashtable)simati.get(0);
	    this.context.put("namaSiMati",paparSimati.get("namaSiMati"));
	    this.context.put("noKp",paparSimati.get("noKp"));
	    this.context.put("tarikhMati",paparSimati.get("tarikhMati"));
	    	
	    senaraiWaris(Integer.parseInt(id_permohonan));
		waris = getSenaraiWaris();
		prepareItemForDisplay(session, waris,10,"first");
		
  	    if (("next".equals(action)) || ("previous".equals(action))){
  	    	senaraiWaris(Integer.parseInt(id_permohonan));
  			waris = getSenaraiWaris();
  			prepareItemForDisplay(session, waris,10,action);
  	    }
  	  

		return vm;
	}
	public void paparSiMati (int id_permohonan)throws Exception{
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
	public void senaraiWaris(int id_permohonan)throws Exception{
		 	//Senarai Waris dan Bahagian
		 
			Db db = new Db(); 	
		 	long lBase[] = {0,0};
		    long lTashieh[] = {0,0};
		    Faraid.Engine.Syafie oEngine;
			String id_simati="",jantina="",relation="";
			String sqlJantinaSiMati="";
			String sqlWaris = "";
			String sqlRelation = "";
			String nama="",no_kp="";
			String countRelation[][]= new String[0][0];
			listWaris.clear();
	       
			try { 
			// create Faraid Engine
			oEngine = new Faraid.Engine.Syafie();
			
			//Open the database connection 
			sqlJantinaSiMati ="SELECT ID_PERMOHONAN,ID_SIMATI," +
					"(SELECT JANTINA FROM TBLPPKSIMATI WHERE ID_SIMATI=A.ID_SIMATI ) AS JANTINA " +
					"FROM TBLPPKPERMOHONANSIMATI A WHERE " +
					"ID_PERMOHONAN="+id_permohonan; 
			//get some data 
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
			 	myLogger.info(sqlRelation);
			 	
			   	ResultSet rsJumHbgn = db.getStatement().executeQuery(sqlRelation); 	
			   	Hashtable parameters = new Hashtable();
				while (rsJumHbgn.next()){					
					if (rsJumHbgn.getString("KETERANGAN") != null && rsJumHbgn.getString("KETERANGAN") != ""){
						relation = rsJumHbgn.getString("KETERANGAN");
						myLogger.info("Relation :" + relation);
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
				  Hashtable simpanlTashieh = new Hashtable();
				  myLogger.info("hashtable :"+parameters.toString());
				  myLogger.info("size:"+parameters.size());
				  
				  try{
					  for (Enumeration e = parameters.keys(); e.hasMoreElements();)
					   {
						  hubungan = (String)e.nextElement();
						  countHbgn = (Integer)parameters.get(hubungan);
						  
						  if (countHbgn != 1){
							oEngine.GetRelationFraction((RelationEnum)ConvertTextEnumHubungan(hubungan), lBase, lTashieh);
							 if (lTashieh[0] > 0){
								 simpanlTashieh.put(hubungan,lTashieh[0]/countHbgn + "/" + lTashieh[1]);
								 int kira = (int)lTashieh[0]/countHbgn;
								 System.out.println("lTashieh[0] : " +lTashieh[0]);
								 System.out.println("lTashieh[0]/countHbgn :"+kira);
								 System.out.println("Hubungan : " + hubungan + " lTashieh : " + lTashieh[0]/countHbgn + "/" + lTashieh[1]);
							 }
						  }
						  else{
							  oEngine.GetRelationFraction((RelationEnum)ConvertTextEnumHubungan(hubungan), lBase, lTashieh);
								 if (lTashieh[0] > 0){
									 simpanlTashieh.put(hubungan,lTashieh[0] + "/" + lTashieh[1]);
									 System.out.println("Hubungan : " + hubungan + " lTashieh : " +lTashieh[0] + "/" + lTashieh[1]);
								 }
						  }
							
					   } 
				  } catch (Exception e) {e.printStackTrace();}

			// get the details waris
			sqlWaris = "SELECT ID_OB,NAMA_OB,NO_KP_BARU,(SELECT KETERANGAN FROM TBLPPKRUJSAUDARA " +
					"WHERE ID_SAUDARA=A.ID_SAUDARA) " +
					"AS RELATION FROM TBLPPKOB A WHERE ID_SIMATI="+
				  "(SELECT ID_SIMATI FROM TBLPPKPERMOHONANSIMATI WHERE ID_PERMOHONAN='"+id_permohonan+"')";
			
			ResultSet rsWaris = db.getStatement().executeQuery(sqlWaris); 
			Hashtable hWaris;
			int bil = 1;
		    int count = 0;
		    String key = "";
		    String value = "";
			while (rsWaris.next()) { 
				hWaris = new Hashtable();
				hWaris.put("idOb",rsWaris.getString("ID_OB"));
				hWaris.put("bil",bil);
				hWaris.put("namaWaris",rsWaris.getString("NAMA_OB"));
				hWaris.put("hubungan",rsWaris.getString("RELATION")== null?"":rsWaris.getString("RELATION"));
				
				try{
					 for (Enumeration e = simpanlTashieh.keys(); e.hasMoreElements();){
						 key = (String)e.nextElement();
						 
						 if (key.equals(rsWaris.getString("RELATION"))){
							 value = (String)simpanlTashieh.get(key);
							 System.out.println("key : " +key);
							 System.out.println("value : " +value);
							 hWaris.put("lTashieh",value == null?"":value);
						 } 
					 }
					
				}catch (Exception e){e.printStackTrace();}
					
		        listWaris.addElement(hWaris);
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
		    	  listWaris.addElement(hWaris);
		            
			 }
			  
			   
			} catch (Exception e) { } 
			finally { 
			//Close the database connection 
			if ( db != null ) db.close(); 
			} 
	 }
	 public static Vector getSenaraiWaris(){
		 
		  return listWaris;
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
		 else if ("ANAK SAUDARA LELAKI SEBAPA".equals(sRelation)) return RelationEnum.NotRelated;
		 else if ("ANAK SAUDARA LELAKI SEIBU SEBAPA".equals(sRelation)) return RelationEnum.NotRelated;
		 else if ("DATUK".equals(sRelation)) return RelationEnum.FatherOfFather;
		 else if ("BAPA SAUDARA SEBAPA".equals(sRelation)) return RelationEnum.ConsanguineBrotherOfFather;
		 else if ("BAPA SAUDARA SEIBU SEBAPA".equals(sRelation)) return RelationEnum.ConsanguineBrother;
		 else if ("NENEK PEREMPUAN SEBELAH BAPA (HINGGA ATAS)".equals(sRelation)) return RelationEnum.MotherOfFather;
		 else if ("NENEK PEREMPUAN SEBELAH IBU (HINGGA ATAS)".equals(sRelation)) return RelationEnum.MotherOfMother;
		 else if ("PETUAN HAMBA SAHAYA".equals(sRelation)) return RelationEnum.NotRelated;
		 else if ("PETUAN PEREMPUAN HAMBA SAHAYA".equals(sRelation)) return RelationEnum.NotRelated;
		 else if ("CUCU PEREMPUAN DARI ANAK PEREMPUAN".equals(sRelation)) return RelationEnum.NotRelated;
		 else if ("MENANTU".equals(sRelation)) return RelationEnum.NotRelated;
		 else if ("BAPA TIRI".equals(sRelation)) return RelationEnum.NotRelated;
		 else if ("IBU TIRI".equals(sRelation)) return RelationEnum.NotRelated;
		 else if ("ANAK TIRI".equals(sRelation)) return RelationEnum.NotRelated;
		 else if ("AMANAH RAYA BERHAD".equals(sRelation)) return RelationEnum.BaitulMal;
		 else if ("ANAK SAUDARA PEREMPUAN SEBAPA".equals(sRelation)) return RelationEnum.NotRelated;
		 else if ("ANAK SAUDARA PEREMPUAN SEIBU SEBAPA".equals(sRelation)) return RelationEnum.NotRelated;
		 else if ("ANAK ANGKAT LELAKI".equals(sRelation)) return RelationEnum.NotRelated;
		 else if ("ANAK ANGKAT PEREMPUAN".equals(sRelation)) return RelationEnum.NotRelated;
		 else return RelationEnum.NotDetermined;
		 
	 }
	 private void prepareItemForDisplay(HttpSession session, Vector objVector, int cntItemPage, String action)
	  {
	    int x;
	    int startno = 0;
	    if (action == null) action = "first";
	    if (session.getAttribute("_portal_startno") != null) startno = ((Integer)session.getAttribute("_portal_startno")).intValue();
	    if (action.equals("previous"))
	      if (startno == objVector.size()) {
	        x = startno % cntItemPage;
	        if (x > 0) {
	          startno -= x;
	          startno -= cntItemPage;
	        } else {
	          startno -= cntItemPage * 2;
	          if (startno < 0) startno = 0;
	        }
	      } else {
	        startno -= cntItemPage * 2;
	        if (startno < 0) startno = 0;
	      }
	    else if (action.equals("first"))
	      startno = 0;
	    	
	    else if (action.equals("last"))
	      x = cntItemPage;
	    else if (action.equals("back"))
	      if (startno == objVector.size()) {
	        x = startno % cntItemPage;
	        if (x == 0) x = cntItemPage;
	        startno -= x;
	      } else {
	        startno -= cntItemPage;
	        if (startno < 0) startno = 0;

	      }
	    
	    Vector moduleVector = new Vector();
	    int i = 0; int cnt = 0;
	    if (objVector.size() > 0) {
	      cnt = 0; for (i = startno; (cnt < cntItemPage) && (i < objVector.size()); ) {
	        moduleVector.addElement(objVector.elementAt(i));

	        ++i; ++cnt;
	      }

	    }

	    session.setAttribute("_portal_moduleVector", moduleVector);
	   
	    this.context.put("startnoInt", new Integer(startno));
	    this.context.put("totalInt", new Integer(objVector.size()));
	    
	   
	    startno = i;
	   
	    session.setAttribute("_portal_startno", new Integer(startno));
	    
	  }
	 
}
