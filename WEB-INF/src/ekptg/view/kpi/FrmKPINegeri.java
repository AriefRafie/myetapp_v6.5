package ekptg.view.kpi;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.htp.FrmCukaiPenyataData;
import ekptg.model.kpi.FrmKPIData;
import ekptg.model.kpi.FrmKPIHTML;

public class FrmKPINegeri extends AjaxBasedModule{
	
	private String result = new String();
	static Logger mylog = Logger.getLogger(FrmKPINegeri.class);

	public String doTemplate2()throws Exception {
		 
	    HttpSession session = this.request.getSession();
	    String template_name = "";
      	String disability = "disabled";
    	String readability = "";
    	String style1 = "";
		String style2 = "";

	    Vector senaraiFail = null;
	    String submit = getParam("command");
	    this.context.put("command", submit);
	    String pageMode = getParam("pagemode");
	    this.context.put("pagemode", pageMode);
		Vector list = new Vector();
    	//System.out.println(this.className+":command-"+submit+",pageMode-"+pageMode);
    	//System.out.println(this.className+":fail::"+getParam("fail"));
		String tempIdUrusan = getParam("socUrusan")==null?"382":getParam("socUrusan");
		String tempIdSubUrusan= getParam("socSuburusan")==""?"59":getParam("socSuburusan");

		if("bydaerah".equals(submit)){
			template_name = "app/kpi/frmKPIMengikutDaerah.jsp";
			String tempIdNegeri = getParam("idnegeri")==null?"0":getParam("idnegeri");
			String tempTarikhMula = getParam("tarikhmula")==""?"0":getParam("tarikhmula");
			String tempTarikhTamat = getParam("tarikhtamat")==""?"0":getParam("tarikhtamat");
			Vector<?> senaraiDesc = null;
			senaraiDesc = FrmKPIData.getKeteranganUntukUtama(tempIdUrusan,tempIdSubUrusan);
			this.context.put("senaraidesc", senaraiDesc);  
			
			String strNegeri = FrmKPIHTML.getKPIMengikutPejabat("", null,tempIdUrusan,tempIdSubUrusan,tempIdNegeri,tempTarikhMula,tempTarikhTamat);
			this.context.put("mengikutnegeri", strNegeri);  
		    		
		}else if("negerimengikuttarikh".equals(submit)){
			template_name = "app/kpi/frmKPIMengikutNegeri.jsp";
			String tempIdNegeri = getParam("idnegeri")==null?"0":getParam("idnegeri");
			String tempTarikhMula = getParam("txdMula")==""?"0":getParam("txdMula");
			String tempTarikhTamat = getParam("txdAkhir")==""?"0":getParam("txdAkhir");
			Vector<?> senaraiDesc = null;
			senaraiDesc = FrmKPIData.getKeteranganUntukUtama(tempIdUrusan,tempIdSubUrusan);
			this.context.put("senaraidesc", senaraiDesc);  
			
			String strNegeri = FrmKPIHTML.getKPIMengikutNegeri("", null,tempIdUrusan,tempIdSubUrusan,"0",tempTarikhMula,tempTarikhTamat);
			this.context.put("mengikutnegeri", strNegeri);  
		    		
		}else{ 
			
		   mylog.info(this.className+"!=submit::else:::user_id="+session.getAttribute("_ekptg_user_id"));
		   template_name = "app/kpi/frmKPIMengikutNegeri.jsp";
   	    
		   Vector<?> senaraiDesc = null;
		   //senaraiDesc = FrmKPIData.getKeteranganTerperinci("382","59");
		   senaraiDesc = FrmKPIData.getKeteranganUntukUtama("382","59");
		   this.context.put("senaraidesc", senaraiDesc);  
		   
		   /*String terperinci = FrmKPIData.getKPIGiliranTerperinciHTML("", null,"382","59");
		   this.context.put("terperinci", terperinci);  

		   Vector senaraiKeberkesanan = null;
		   senaraiKeberkesanan = FrmKPIData.getKPIKeberkesanan("382", "59");
		   this.context.put("kesans", senaraiKeberkesanan);  
		   */	   

		   String strNegeri = FrmKPIHTML.getKPIMengikutNegeri("", null,"382","59","0","0","0");
		   this.context.put("mengikutnegeri", strNegeri);  

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
				//System.out.println("CukaiProcess::DataPenyata::list.size():::"+list.size());
				int idPermohonan = Integer.parseInt(getParam("idPermohonan"));
			    //System.out.println("penyataview::DataPenyata::idpermohonan:::"+idPermohonan);
				negeri = getParam("Negeri");
				//System.out.println("CukaiProcess::DataPenyata::negeri:::"+negeri);
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
