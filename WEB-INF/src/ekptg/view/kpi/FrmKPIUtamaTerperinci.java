package ekptg.view.kpi;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.htp.FrmCukaiPenyataData;
import ekptg.model.kpi.FrmKPIData;


public class FrmKPIUtamaTerperinci extends AjaxBasedModule{
	
	private static final long serialVersionUID = 1L;
	private String result = new String();
	static Logger mylog = Logger.getLogger(FrmKPIUtamaTerperinci.class);

	public String doTemplate2()throws Exception {
		 
	    HttpSession session = this.request.getSession();
	    String template_name = "";
      	String disability = "disabled";
    	String readability = "";
    	String style1 = "";
		String style2 = "";

		this.context.put("Util", new lebah.util.Util());

		String selectedTab = new String();
    	selectedTab = getParam("tabId").toString();
    	if (selectedTab == null || "".equals(selectedTab) ) {
    		selectedTab="0";
    	}

   		this.context.put("selectedTab",selectedTab);
   		//System.out.println("CukaiProcess:::selectedTab::"+selectedTab);
   		
	    Vector senaraiFail = null;
	    String submit = getParam("command1");
	    this.context.put("command1", submit);
	    String pageMode = getParam("pagemode");
	    this.context.put("pagemode", pageMode);
		Vector list = new Vector();
		mylog.info(this.className+":pageMode::"+pageMode);
		mylog.info(this.className+":fail::"+getParam("fail"));
    	
		if("terperinci".equals(submit)){
			   template_name = "app/kpi/frmKPIUtamaTerperinci.jsp";
	
		}else{ 
			
			mylog.info(this.className+"!=submit::else:::");
			template_name = "app/kpi/frmKPIUtamaTerperinci.jsp";
   	    
		   /*Vector<?> senaraiDesc = null;
		   senaraiDesc = FrmKPIData.getKeteranganTerperinci("382","59");
		   this.context.put("senaraidesc", senaraiDesc);  
		   
		   String terperinci = FrmKPIHTML.getKPIGiliranTerperinciHTML("", null,"382","59");
		   this.context.put("terperinci", terperinci);  

		   Vector<?> senaraiKeberkesanan = null;
		   senaraiKeberkesanan = FrmKPIData.getKPIKeberkesanan("382", "59");
		   this.context.put("kesans", senaraiKeberkesanan);  
		   */
			String id = getParam("idsuburusanstatus");
		   String hari = getParam("hari");
		   String tempIdNegeri = getParam("idnegeri");//=="0"?"0":getParam("idnegeri");
		   String tempTarikhMula = getParam("txdMula");//=="0"?"0":getParam("txdMula");
		   String tempTarikhAkhir = getParam("txdAkhir");//=="0"?"0":getParam("txdAkhir");

		   Vector<?>  senaraiKeberkesanan1 = FrmKPIData.getKPIGiliranTerperinciSenarai(id, hari,tempIdNegeri,tempTarikhMula,tempTarikhAkhir);
		   this.context.put("senaraifail", senaraiKeberkesanan1);  
		}
		
	    return template_name;
	  }

	  private void DataPenyata(HttpSession session, int idNegeri, String disability, String readability, String style1, String style2) throws Exception
		{
		  	String negeri = "";
			Vector list = new Vector();
			list.clear();
			try{
				list = FrmCukaiPenyataData.getListPenyata(idNegeri);
				System.out.println("CukaiProcess::DataPenyata::list.size():::"+list.size());
				int idPermohonan = Integer.parseInt(getParam("idPermohonan"));
			    System.out.println("penyataview::DataPenyata::idpermohonan:::"+idPermohonan);
				negeri = getParam("Negeri");
				System.out.println("CukaiProcess::DataPenyata::negeri:::"+negeri);
				this.context.put("negeri", "");
								
				if(list.size() != 0){			    
					System.out.println("CukaiProcess::DataPenyata::list::"+list);
					this.context.put("Penyata", list);
					this.context.put("idPermohonan", idPermohonan);
				    this.context.put("Style1", style1);
				    this.context.put("Style2", style2);
				}else{
					this.context.put("Penyata", "");
				    style1 = "none";
				    this.context.put("Style1", style1);
				    this.context.put("Style2", "");
				}
				this.context.put("negeri", negeri);
			}catch(Exception ex){
				System.out.println("CukaiProcess::DataPenyata::Exception:: = "+ex);
				ex.printStackTrace();
			}
		}	  	  

	  //Baucer	  
	  private void DataBaucer(HttpSession session, String disability, String readability, String style1, String style2) throws Exception
		{
		  	Vector list = new Vector();
			list.clear();
//			int idCukaiUtama = Integer.parseInt(getParam("idCukaiUtama"));
//		    System.out.println("penyataview::DataBaucer::idCukaiUtama:::"+idCukaiUtama);
		    
			try{
				//list = FrmCukaiBaucerData.getListBaucer();
				System.out.println("CukaiProcess::DataBaucer::list.size():::"+list.size());
								
				if(list.size() != 0){			    
					System.out.println("CukaiProcess::DataBaucer::list::"+list);
					this.context.put("Baucer", list);
					this.context.put("idCukaiUtama", list);
				    this.context.put("Style1", style1);
				    this.context.put("Style2", style2);
				}else{
					this.context.put("Baucer", "");
				    style1 = "none";
				    this.context.put("Style1", style1);
				    this.context.put("Style2", "");
				}
			}catch(Exception ex){
				System.out.println("CukaiProcess::DataBaucer::Exception:: = "+ex);
				ex.printStackTrace();
			}
		}
}
