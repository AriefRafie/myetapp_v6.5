package latihan.sip;

import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.model.entities.Daerah;
import ekptg.model.htp.FrmCukaiBaucerData;
import ekptg.model.htp.FrmCukaiBayaranData;
import ekptg.model.htp.FrmCukaiPenyataData;
import ekptg.model.htp.FrmUtilData;
import ekptg.model.htp.UtilHTML;
import ekptg.model.htp.cukai.CukaiBaucerBean;
import ekptg.model.htp.cukai.CukaiBean;
import ekptg.model.htp.cukai.CukaiPenyataBean;
import ekptg.model.htp.cukai.ICukai;
import ekptg.model.htp.cukai.ICukaiBaucer;
import ekptg.model.htp.cukai.ICukaiPenyata;
import ekptg.model.htp.cukai.entity.BaucerCukai;
import ekptg.model.htp.cukai.entity.BayaranCukai;
import ekptg.model.htp.cukai.entity.CukaiPenyata;
import ekptg.model.htp.cukai.entity.CukaiUtama;
import ekptg.model.htp.entity.Bayaran;
import ekptg.model.htp.entity.HakMilik;
import ekptg.model.htp.entity.Resit;
import ekptg.model.utils.rujukan.DBPPT;

public class FrmCukai_20200228 extends AjaxBasedModule{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String PATH="app/tranningjava/202002/";
	static Logger myLog = Logger.getLogger(latihan.sip.FrmCukai.class);
	private String peringkatBayar = "";
	private String idPermohonan = "";
	private String idPeringkatBayaran = "";
	private String idNegeri = "";
	private ICukai iCukai = null;
	private ICukaiBaucer iCukaiBaucer = null;
	private ICukaiPenyata iCukaiPen = null;
	private HakMilik hakmilik = null;
	private UtilHTML utilHTML = new UtilHTML();
    private String idUser = "";
    private Vector vector = null;
    private Hashtable hPeringkatBayar = null;
    private boolean statusPeringkatBayar = false;
	//private String year = "";
	private String tahunParam = "";
	private CukaiUtama cu = null;
	private BaucerCukai bCukai= null;
	private CukaiPenyata cPenyata = null;
	private Daerah daerah = null;
	private Bayaran bayaran = null;
		
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
	    Vector senaraiFail = null;
		String action = getParam("action");
	    String submit = getParam("command");
	    this.context.put("command1", submit);
	    String pageMode = getParam("pagemode");
	    this.context.put("pagemode", pageMode);
		Vector list = new Vector();
		myLog.info("action="+action+",command="+submit+",pageMode="+pageMode);
		//myLog.info(this.className+":fail::"+getParam("fail"));
		peringkatBayar = getParam("peringkat_bayaran");
//		myLog.info("peringkatBayar="+peringkatBayar);
		idPermohonan = getParam("idPermohonan");
		idPeringkatBayaran = getParam("idPeringkatbayaran");
		idNegeri= getParam("idNegeri");
		idUser = (String)session.getAttribute("_ekptg_user_id");		
		tahunParam = (!getParam("tahun_cukai").equals("")?getParam("tahun_cukai"):lebah.util.Util.getDateTime(new Date(), "yyyy"));
		
		template_name= PATH+"index.jsp";
	    //myLog.info("command01="+request.getParameter("command01"));
//	    this.context.put("nama1", request.getParameter("command01"));
	    this.context.put("nama1",getParam("command01"));
	    
	    Vector vPPT = DBPPT.getJenisHakmiliks("");
		
		if("cukaiperingkatbayar".equals(submit)){

		
		}else if("penyata".equals(submit)){
			selectedTab = "0";
//			myLog.info("baucerview:selectedTab="+selectedTab);
			
		    template_name = PATH+"frmCukaiPenyata.jsp";
		    Hashtable permohonan = getICukai().getPermohonanInfo(idPermohonan);
		    int idNegeriInt = Integer.parseInt(getParam("idNegeri"));		
			String negeri = getParam("negeri");
			String Daerah = getParam("socDaerah")==""?"0":getParam("socDaerah");
			long idDaerah = Long.parseLong(Daerah);
			statusPeringkatBayar = false;

			vector = getICukaiPenyata().getPenyata("11", "",String.valueOf(permohonan.get("fail")), String.valueOf(permohonan.get("idnegeri")),null);
			CukaiPenyata cPenyata = null;
			if(vector.isEmpty() == false){
				cPenyata = (CukaiPenyata)vector.get(0);
				idPeringkatBayaran = String.valueOf(cPenyata.getId());
				myLog.info("idPeringkatBayaran="+idPeringkatBayaran);
				if(!idPeringkatBayaran.equals("0")){
					peringkatBayar = cPenyata.getNoRujukan();
					statusPeringkatBayar = true;
				}
			}
			myLog.info("peringkatBayar="+peringkatBayar);
			this.context.put("info", permohonan);
    		this.context.put("idbayaran", peringkatBayar);
    		this.context.put("idNegeri", Integer.parseInt(String.valueOf(permohonan.get("idnegeri"))));
    		this.context.put("tahun_cukai", tahunParam);
    		this.context.put("tahuncukai", Integer.parseInt(tahunParam));
    		this.context.put("pageMode", pageMode); 
    		this.context.put("statusPeringkatBayar", statusPeringkatBayar);
    		
    		if("penyataview".equals(pageMode)){
				myLog.info("penyataview");
				selectedTab = "0";
			    readability = "readonly";
				disability = "disabled";
				style2 = "none";
				DataPenyata(session,idNegeriInt,disability,readability,style1,style2);
				myLog.info("penyataview:peringkatBayar="+peringkatBayar);
				int bilCukaiUtama=0;
				if(statusPeringkatBayar){
					cu = getICukai().getCukaiUtama(idPeringkatBayaran);
					if(cu!=null){
						myLog.info("bilHakmilik="+cu.getBilanganHakmilik()+",="+cu.getJumlahCukai());
						this.context.put("bilHakmilik", String.valueOf(cu.getBilanganHakmilik()));
						this.context.put("jumlahCukai", String.valueOf(cu.getJumlahCukai()));
						bilCukaiUtama = 1;
					}
					
				}else{
					if(vector.size() != 0){			    
						statusPeringkatBayar = true;
					}					
				}
				this.context.put("cukaiUtama",bilCukaiUtama);	
				myLog.info("statusPeringkatBayar: "+statusPeringkatBayar);
				
			}else if("simpanPenyata".equals(pageMode)){
				myLog.info("simpanPenyata");
				selectedTab = "0";
				readability = "readonly";
				disability = "disabled";
				style2 = "none";
				vector = getICukaiPenyata().getSenaraiPenyata(String.valueOf(idNegeri), tahunParam);
				simpanPenyata(session,idPermohonan,vector);
			    DataPenyata(session,idNegeriInt,disability,readability,style1,style2);			    
			    this.context.put("peringkat_bayaran", peringkatBayar);
			    this.context.put("SimpanStatus", "success");
				//this.context.put("ResultSimpan", result); 
			
			}

    		
		}else if("baucer".equals(submit)){
    			myLog.info("cukaiperingkatbayar:"+pageMode);
    		    template_name = PATH+"frmCukaiPenyata.jsp";
    		    Hashtable permohonan = getICukai().getPermohonanInfo(idPermohonan);
    		    int idNegeriInt = Integer.parseInt(getParam("idNegeri"));		
    			String negeri = getParam("negeri");
    			String Daerah = getParam("socDaerah")==""?"0":getParam("socDaerah");
    			long idDaerah = Long.parseLong(Daerah);
    			statusPeringkatBayar = false;
    			String idBaucer = "0";
				BaucerCukai bCukai = null;
    			vector = getICukaiPenyata().getPenyata("11", "",String.valueOf(permohonan.get("fail")), String.valueOf(permohonan.get("idnegeri")),null);
    			//myLog.info("vector.isEmpty()="+vector.isEmpty());
    			CukaiPenyata cPenyata = null;
    			if(vector.isEmpty() == false){
    				cPenyata = (CukaiPenyata)vector.get(0);
    				idPeringkatBayaran = String.valueOf(cPenyata.getId());
    				//myLog.info("idPeringkatBayaran="+idPeringkatBayaran);
    				if(!idPeringkatBayaran.equals("0")){
    					peringkatBayar = cPenyata.getNoRujukan();
    					statusPeringkatBayar = true;
    				}
    			}
    			this.context.put("info", permohonan);
        		this.context.put("idbayaran", peringkatBayar);
        		this.context.put("tahun_cukai", tahunParam);
        		this.context.put("tahuncukai", Integer.parseInt(tahunParam));
        		this.context.put("pageMode", pageMode); 
        		this.context.put("statusPeringkatBayar", statusPeringkatBayar);
        		
        		if("baucerview".equals(pageMode)){
    				selectedTab = "0";
    				if(statusPeringkatBayar){
    					selectedTab = "1";
    					int peringkat_bayaran = Integer.parseInt(getParam("peringkat_bayaran"));
    					readability = "readonly";
    					disability = "disabled";
    					style2 = "none";
    					viewBaucer(session,idNegeri,idPeringkatBayaran,peringkatBayar,disability,readability,style1,style2);
    				
    		    		this.context.put("idPermohonan",idPermohonan);		
    					this.context.put("idNegeri", idNegeri);
    		    		this.context.put("idbayaran", getParam("socbayaran"));
    				}
    			}else if("baru".equals(pageMode)){
    				selectedTab = "1";
    				this.context.put("idNegeri", idNegeriInt);
    				this.context.put("negeri", getParam("negeri"));
     				getBaucerValues();
    				if(peringkatBayar.equals("1")){
    					this.context.put("selectDaerah",utilHTML.SelectNegeriByCukai(tahunParam,idPeringkatBayaran,"socDaerah",idDaerah,"onChange=\"doChangeDaerah();\" style=\"width:200\"",null));				
    				}else{
    					this.context.put("selectDaerah",utilHTML.SelectDaerahByCukai(tahunParam,idPeringkatBayaran,"socDaerah",idDaerah,"onChange=\"doChangeDaerah();\" style=\"width:200\"",null));
    				}
    		  		this.context.put("tahun_cukai", tahunParam);
    	    		this.context.put("tahuncukai", Integer.parseInt(tahunParam));
    				this.context.put("mode", "");
    	 
    			}else if("simpanB".equals(pageMode)){
    				myLog.info("tambahBaucer : pageMode:simpanB="+pageMode);
    				readability = "readonly";
    			    disability = "disabled";
    			    style2 = "none";
    			    idBaucer = SimpanTBaucer(session,Integer.parseInt(idPeringkatBayaran));
    			    this.context.put("idbaucer", idBaucer);
    				this.context.put("idDaerah", idDaerah);
    				this.context.put("SimpanStatus", "success");
    				//this.context.put("ResultSimpan", result);	
    				bCukai = getICukaiBaucer().getSenaraiBaucer(negeri, null, peringkatBayar,null, String.valueOf(idBaucer));
    				this.context.put("bcukai", bCukai);
    				this.context.put("mode", readability);
    			    this.context.put("pagemode", "view");
    			
    			}else if("view".equals(pageMode)){
    				myLog.info("baucer : pageMode="+pageMode);
    				idBaucer = getParam("idBaucer");
    				this.context.put("idbaucer", idBaucer);
    				this.context.put("idDaerah", getParam("idDaerah"));
    				style2 = "none";
    				bCukai = getICukaiBaucer().getSenaraiBaucer(negeri, null, peringkatBayar,null, getParam("idBaucer"));
    				this.context.put("bcukai", bCukai);
    				this.context.put("mode", "readonly");

    			}else if("kemaskinibaucer".equals(pageMode)){
    				myLog.info("Baucer::kemaskinibaucer");
    				idBaucer = getParam("idBaucer");
       				this.context.put("idbaucer", idBaucer);
    				this.context.put("idDaerah", getParam("idDaerah"));
    				style2 = "none";
    				bCukai = getICukaiBaucer().getSenaraiBaucer(negeri, null, peringkatBayar,null, getParam("idBaucer"));
     				this.context.put("bcukai", bCukai);
    		  		this.context.put("tahun_cukai", tahunParam);
    	    		this.context.put("tahuncukai", Integer.parseInt(tahunParam));
    				this.context.put("mode", "");
    				
    			}else if("hapusbaucer".equals(pageMode)){
    				getICukaiBaucer().hapusBaucerCukai(getParam("idBaucer"));
					viewBaucer(session,idNegeri,idPeringkatBayaran,peringkatBayar,disability,readability,style1,style2);
            		this.context.put("pageMode", "baucerview"); 
     				
    			} 

		}else if("bayaran".equals(submit)){
			myLog.info("bayaran:"+pageMode);
        	template_name = PATH+"frmCukaiPenyata.jsp";
        	Hashtable permohonan = getICukai().getPermohonanInfo(idPermohonan);
        	int idNegeriInt = Integer.parseInt(getParam("idNegeri"));		
        	String negeri = getParam("negeri");
        	String Daerah = getParam("socDaerah")==""?"0":getParam("socDaerah");
        	long idDaerah = Long.parseLong(Daerah);
        	int idBaucer = 0;
        	int idBayaranCukai = 0;
        	statusPeringkatBayar = false;

   			vector = getICukaiPenyata().getPenyata("11", "",String.valueOf(permohonan.get("fail")), String.valueOf(permohonan.get("idnegeri")),null);
//   		myLog.info("vector.isEmpty()="+vector.isEmpty());
   			CukaiPenyata cPenyata = null;
   			this.context.put("info", permohonan);
    		this.context.put("idbayaran", peringkatBayar);
    		this.context.put("tahun_cukai", tahunParam);
    		this.context.put("tahuncukai", Integer.parseInt(tahunParam));
    		this.context.put("pageMode", pageMode); 
    		this.context.put("statusPeringkatBayar", statusPeringkatBayar);
    		
    		if(vector.isEmpty() == false){
   				cPenyata = (CukaiPenyata)vector.get(0);
   				//idPeringkatBayaran = String.valueOf(hPeringkatBayar.get("idPeringkatBayaran"));
   				idPeringkatBayaran = String.valueOf(cPenyata.getId());
   				//myLog.info("idPeringkatBayaran="+idPeringkatBayaran);
   				if(!idPeringkatBayaran.equals("0")){
   					//peringkatBayar = String.valueOf(hPeringkatBayar.get("peringkatBayaran"));
   					peringkatBayar = cPenyata.getNoRujukan();
   					statusPeringkatBayar = true;
   				}
   			}

      		if("bayaranview".equals(pageMode)){
      			//myLog.info("cukaiBayaran");
        		selectedTab = "0";
        		if(statusPeringkatBayar){
        			selectedTab = "2";
        			ViewBayaran(session,idNegeriInt,disability,readability,style1,style2);
        		   	this.context.put("idbayaran", getParam("socbayaran"));   
        			Vector<?> v = FrmUtilData.getListBaucer(idPeringkatBayaran);  
        			this.context.put("bayaranStatus", v.size());
        		
        		}
        		
      		}else if("baru".equals(pageMode)){
				myLog.info("CukaiProcess::Bayaran::Baru");
		    	this.context.put("idbayaran", getParam("socbayaran")); 			
				//int idBayaranCukai;
				Vector<?> v = FrmUtilData.getListBaucer(idPeringkatBayaran);  
				this.context.put("bayaranStatus", v.size());
			    String id_baucer = getParam("socBaucer");
				if (id_baucer == null || id_baucer.trim().length() == 0){
		    		id_baucer = "0";
				}
				
				viewBayaranBaru(session,idNegeri,disability,readability,style1,style2);
				this.context.put("selectBaucer",utilHTML.selectBaucer(peringkatBayar,idPeringkatBayaran,"socBaucer",Long.parseLong(id_baucer)," style=\"width:200\"",null));
				list = FrmCukaiBayaranData.getAmaunBaucer(id_baucer);			
				if (list.size() == 0){
					Hashtable hash = new Hashtable();
					hash.put("amaun", "");
					hash.put("idBaucer", "");
					list.addElement(hash);
				}
				this.context.put("AmaunBaucer", list);
				this.context.put("mode", "");
				
        	}else if("simpanBay".equals(pageMode)){
				myLog.info("CukaiProcess::Bayaran::simpanBayaran");
				readability = "readonly";
				disability = "disabled";
				style2 = "none";
				String idBayaran = simpanBayaran(session,idPeringkatBayaran);
				if(getParam("socBaucer") != ""){
					idBaucer = Integer.parseInt(getParam("socBaucer"));
				}else{
				  	idBaucer = Integer.parseInt(getParam("idBaucer"));
				}
				BayaranCukai bCukai = null;
				bCukai = getICukai().bayaranView(idBayaran);
				this.context.put("bcukai", bCukai);
				this.context.put("idBayaranCukai", idBayaran);
				this.context.put("idBaucer", idBaucer);
				this.context.put("SimpanStatus", "success");
				//this.context.put("ResultSimpan", result);
	    		this.context.put("pagemode", "viewBay"); 
		
			}else if("viewBay".equals(pageMode)){
				myLog.info("Bayaran::viewBay");
				idBayaranCukai = Integer.parseInt(getParam("idBayaranCukai"));
				String idBayaran = getParam("idBayaranCukai");
				idBaucer = Integer.parseInt(getParam("idBaucer"));
				this.context.put("idBayaranCukai", idBayaranCukai);
				this.context.put("idBaucer", idBaucer);
				BayaranCukai bCukai = null;
				bCukai = getICukai().bayaranView(idBayaran);
				this.context.put("bcukai", bCukai);
				
			}else if("kemaskinibayaran".equals(pageMode)){
				myLog.info("CukaiProcess::Bayaran::kemaskinibayaran");
				String idBayaran = getParam("idBayaranCukai");
				style2 = "none";
				BayaranCukai bCukai = null;
				bCukai = getICukai().bayaranView(idBayaran);
				this.context.put("bcukai", bCukai);
		  		this.context.put("tahun_cukai", tahunParam);
	    		this.context.put("tahuncukai", Integer.parseInt(tahunParam));
				
			}else if("kemaskinisimpanbayaran".equals(pageMode)){
				myLog.info("CukaiProcess::Bayaran::BayaranView");
				idBayaranCukai = Integer.parseInt(getParam("idBayaranCukai"));
				String idBayaran = getParam("idBayaranCukai");
				idBaucer = Integer.parseInt(getParam("idBaucer"));
				this.context.put("idBayaranCukai", idBayaranCukai);
				this.context.put("idBaucer", idBaucer);
				style2 = "none";
				simpanBayaran(session,idPeringkatBayaran);

				BayaranCukai bCukai = null;
				bCukai = getICukai().bayaranView(idBayaran);
				this.context.put("bcukai", bCukai);
	    		this.context.put("pagemode", "viewBay"); 
		
					
			} else if("hapusbayaran".equals(pageMode)){
				getICukai().hapusBayaranCukai(getParam("idBayaranCukai"));
    			ViewBayaran(session,idNegeriInt,disability,readability,style1,style2);
    		   	this.context.put("idbayaran", getParam("socbayaran"));   
    			Vector<?> v = FrmUtilData.getListBaucer(idPeringkatBayaran);  
    			this.context.put("bayaranStatus", v.size());

				this.context.put("command1", "bayaran");
	    		this.context.put("pagemode", "bayaranview"); 
				
			} 	
      		
		
      		this.context.put("idNegeri", idNegeri);
        	this.context.put("negeri", negeri);
//	  		this.context.put("tahun_cukai", tahunParam);
//    		this.context.put("tahuncukai", Integer.parseInt(tahunParam));
	
		}else{ 
//			myLog.info("Default: Senarai Fail ");
			//template_name = PATH+"frmCukaiPeringkatBayar.jsp";	        
			senaraiFail = FrmCukaiPenyataData.getList("11","","",Long.parseLong("20"));
			//senaraiFail = getICukaiPenyata().getList("11","","",Long.parseLong("20"),tahunParam);
			if (action.indexOf("doChange") != -1) {
				senaraiFail = FrmCukaiPenyataData.getList("11","","",Long.parseLong("20"));
				//senaraiFail = getICukaiPenyata().getList("11","","",Long.parseLong("20"),tahunParam);
				
		    }
			setupPage(session,action,senaraiFail);

		}
		
		this.context.put("idPermohonan", idPermohonan);
	    this.context.put("idPeringkatbayaran", idPeringkatBayaran);
		this.context.put("peringkat_bayaran",peringkatBayar);
		this.context.put("peringkatbayar", peringkatBayar);		   
   		this.context.put("selectedTab",selectedTab);
   		this.context.put("statusPeringkatBayar",statusPeringkatBayar);
   		//this.context.put("command1",statusPeringkatBayar);
   		//this.context.put("pagemode",pagemode);
		myLog.info("idPermohonan="+idPermohonan);
		myLog.info("idPeringkatBayaran="+idPeringkatBayaran);
		myLog.info("peringkatBayar="+peringkatBayar);
		myLog.info("selectedTab = "+selectedTab);
		
  		
		return template_name;
		
	}
	
	private void DataPenyata(HttpSession session, int idNegeri, String disability, 
			String readability, String style1, String style2) throws Exception{
		vector = new Vector();
		try{
			  vector = getICukaiPenyata().getSenaraiPenyata(String.valueOf(idNegeri), tahunParam);
			  this.context.put("ResultSimpan", "");
			  if(vector.size() != 0){			    
				  myLog.info("CukaiProcess::DataPenyata::list::"+vector);
				  this.context.put("Penyata", vector);
				  this.context.put("Style1", "");
				  this.context.put("Style2", style2);
				  
			  }else{
				  this.context.put("Penyata", "");
				  style1 = "none";
				  this.context.put("Style1", style1);
				  this.context.put("Style2", "");
				  
			  }
			
		}catch(Exception ex){
			//System.out.println("CukaiProcess::DataPenyata::Exception:: = "+ex);
			ex.printStackTrace();
		  
		}
	
	}	  
	  
	  @SuppressWarnings("unchecked")
	  private void SimpanPenyata(HttpSession session , int idPermohonan) throws Exception {
		  	Vector list = new Vector();
			list.clear();
			String idPeringkatbayaran = getParam("idPeringkatbayaran");
			int idNegeri = Integer.parseInt(getParam("idNegeri"));	
			String negeri = getParam("negeri");
			System.out.println("CukaiProcess::DataPenyata::negeri:::"+negeri);
			this.context.put("negeri", negeri);
	    	int tahun_cukai = Integer.parseInt(getParam("tahun_cukai"));
	    	String idBayaran = getParam("socbayaran");
	    	list = FrmCukaiPenyataData.getListPenyata(idNegeri);
			System.out.println("CukaiProcess::SimpanPenyata::list.size()::: "+list.size());
				
			if(idPeringkatbayaran == "0"){
				Hashtable h = new Hashtable();	
				h.put("idpermohonan", idPermohonan);
						
				h.put("idNegeri", Integer.parseInt(getParam("idNegeri")));
				System.out.println("CukaiProcess::simpanPeringkatBayar::idNegeri::"+idNegeri);
						
				h.put("tahun_cukai", Integer.parseInt(getParam("tahun_cukai")));
				System.out.println("CukaiProcess::simpanPeringkatBayar::tahun_cukai::"+tahun_cukai);
						
				h.put("peringkat_bayaran", idBayaran);						
		    	System.out.println("CukaiProcess::simpanPeringkatBayar::peringkat_bayaran::"+idBayaran);
						
				System.out.println("CukaiProcess::simpanPeringkatBayar:: h = "+h);
						
				idPeringkatbayaran = FrmCukaiPenyataData.simpanPeringkatBayarInteger(h);
				System.out.println("CukaiProcess::SimpanPenyata::idPeringkatbayaran::"+idPeringkatbayaran);
						
				list = FrmCukaiPenyataData.getListPenyata(idNegeri);
						
				for(int i=0; i<list.size(); i++){
							
					Hashtable input = (Hashtable)list.get(i);
					Hashtable hash = new Hashtable();
//					Double sumCukai = Double.parseDouble(input.get("sumCukai").toString());	
					
					hash.put("idCukaiUtama",idPeringkatbayaran);
					System.out.println("CukaiProcess::simpanCukaiUtama::idCukaiUtama::"+idPeringkatbayaran);
					
					hash.put("jum_cukai", input.get("sumCukai"));
						
					hash.put("jum_hakmilik",(Integer)input.get("sumIdHakmilik"));
							
					hash.put("idPeringkat",idPeringkatbayaran);
					System.out.println("CukaiProcess::simpanCukaiUtama::idPeringkat::"+idPeringkatbayaran);
							
					hash.put("tahun",Integer.parseInt(getParam("tahun_cukai")));
					System.out.println("CukaiProcess::simpanCukaiUtama::tahun::"+tahun_cukai);
							
					hash.put("idNegeri",idNegeri);
					System.out.println("CukaiProcess::simpanCukaiUtama::idNegeri::"+idNegeri);
						
					hash.put("idDaerah",(String)input.get("idDaerah"));
							
					FrmCukaiPenyataData.simpanCukaiUtama(hash);
					System.out.println("CukaiProcess::simpanCukaiUtama:: hash = "+hash);
				}
				//result = "baru";
			}else{
//				kemaskini fail
				Hashtable h = new Hashtable();	
				h.put("idpermohonan", idPermohonan);
				
				h.put("idPeringkatbayaran", idPeringkatbayaran);
						
				h.put("idNegeri", Integer.parseInt(getParam("idNegeri")));
				System.out.println("CukaiProcess::kemaskiniPeringkatBayar::idNegeri::"+idNegeri);
						
				h.put("tahun_cukai", Integer.parseInt(getParam("tahun_cukai")));
				System.out.println("CukaiProcess::kemaskiniPeringkatBayar::tahun_cukai::"+tahun_cukai);
						
				h.put("peringkat_bayaran", idBayaran);						
		    	System.out.println("CukaiProcess::kemaskiniPeringkatBayar::peringkat_bayaran::"+idBayaran);
						
				System.out.println("CukaiProcess::kemaskiniPeringkatBayar:: h = "+h);
							
				idPeringkatbayaran = FrmCukaiPenyataData.updatePeringkatBayarInteger(h);
				System.out.println("CukaiProcess::KemaskiniPenyata::idPeringkatbayaran::"+idPeringkatbayaran);
							
				list = FrmCukaiPenyataData.getListPenyata(idNegeri);
							
				String sumCukai = getParam("sumCukai");
				int sumIdHakmilik = Integer.parseInt(getParam("sumIdHakmilik"));
							
				for(int i=0; i<list.size(); i++){
								
					Hashtable input = (Hashtable)list.get(0);
					Hashtable hash = new Hashtable();
					
					hash.put("idCukaiUtama",idPeringkatbayaran);
					System.out.println("CukaiProcess::simpanCukaiUtama::idCukaiUtama::"+idPeringkatbayaran);
					
					hash.put("jum_cukai", input.get("sumCukai"));
					System.out.println("CukaiProcess::simpanCukaiUtama::jum_cukai::"+sumCukai);
						
					hash.put("jum_hakmilik", Integer.parseInt(getParam("sumIdHakmilik")));
					System.out.println("CukaiProcess::simpanCukaiUtama::jum_hakmilik::"+sumIdHakmilik);
							
					hash.put("idPeringkat",idPeringkatbayaran);
					System.out.println("CukaiProcess::simpanCukaiUtama::idPeringkat::"+idPeringkatbayaran);
							
					hash.put("tahun",Integer.parseInt(getParam("tahun_cukai")));
					System.out.println("CukaiProcess::simpanCukaiUtama::tahun::"+tahun_cukai);
							
					hash.put("idNegeri",idNegeri);
					System.out.println("CukaiProcess::simpanCukaiUtama::idNegeri::"+idNegeri);
						
					hash.put("idDaerah",(String)input.get("idDaerah"));
								
					FrmCukaiPenyataData.updateCukaiUtama(hash);
					System.out.println("CukaiProcess::kemaskiniCukaiUtama:: hash = "+hash);
				}
				//result = "kemaskini";
			}
		}
	  
		private void simpanPenyata(HttpSession session ,String idPermohonan) throws Exception {
		  	vector = new Vector();
			//list.clear();
			//int idPeringkatbayaran = Integer.parseInt(getParam("idPeringkatbayaran"));
			//int idNegeri = Integer.parseInt(getParam("idNegeri"));	
			String idPeringkatbayaran = idPeringkatBayaran==""?"0":idPeringkatBayaran;
		    int idNegeriInt = Integer.parseInt(idNegeri);		

			//String negeri = getParam("negeri");
			//this.context.put("negeri", negeri);
	    	int tahun_cukai = Integer.parseInt(getParam("tahun_cukai"));
	    	String idBayaran = getParam("socbayaran");
	    	vector = FrmCukaiPenyataData.getListPenyata(idNegeriInt);
				
			if(idPeringkatbayaran == "0"){
				Hashtable h = new Hashtable();	
				h.put("idpermohonan", idPermohonan);						
				h.put("idNegeri", idNegeriInt);
				h.put("tahun_cukai", tahun_cukai);						
				h.put("peringkat_bayaran", idBayaran);		    
				h.put("idMasuk", idUser);
				idPeringkatbayaran = FrmCukaiPenyataData.simpanPeringkatBayarInteger(h);						
				//list = FrmCukaiPenyataData.getListPenyata(idNegeri);
				myLog.info("CukaiProcess::simpanPeringkatBayar::idNegeri::"+idNegeri);						
				myLog.info("CukaiProcess::simpanPeringkatBayar::tahun_cukai::"+tahun_cukai);
				myLog.info("CukaiProcess::simpanPeringkatBayar::peringkat_bayaran::"+idBayaran);						
				myLog.info("CukaiProcess::simpanPeringkatBayar:: h = "+h);
				myLog.info("CukaiProcess::SimpanPenyata::idPeringkatbayaran::"+idPeringkatbayaran);						
						
				for(int i=0; i<vector.size(); i++){							
					Hashtable input = (Hashtable)vector.get(i);
					Hashtable hash = new Hashtable();
					hash.put("idCukaiUtama",idPeringkatbayaran);					
					hash.put("jum_cukai", input.get("sumCukai"));						
					hash.put("jum_hakmilik",(Integer)input.get("sumIdHakmilik"));							
					hash.put("idPeringkat",idPeringkatbayaran);							
					hash.put("tahun",tahun_cukai);
					hash.put("idNegeri",idNegeri);					
					hash.put("idDaerah",(String)input.get("idDaerah"));							
					hash.put("idMasuk", idUser);
					myLog.info("CukaiProcess::simpanCukaiUtama::idCukaiUtama::"+idPeringkatbayaran);
					myLog.info("CukaiProcess::simpanCukaiUtama::idPeringkat::"+idPeringkatbayaran);
					myLog.info("CukaiProcess::simpanCukaiUtama::tahun::"+tahun_cukai);						
					myLog.info("CukaiProcess::simpanCukaiUtama::idNegeri::"+idNegeri);
					myLog.info("CukaiProcess::simpanCukaiUtama:: hash = "+hash);
					FrmCukaiPenyataData.simpanCukaiUtama(hash);
					
				}
				//result = "baru";
				
			}else{
				//	Kemaskini Peringkat Bayaran
				Hashtable h = new Hashtable();	
				h.put("idpermohonan", idPermohonan);			
				h.put("idPeringkatbayaran", idPeringkatbayaran);						
				h.put("idNegeri", idNegeriInt);
				h.put("tahun_cukai", tahun_cukai);							
				h.put("peringkat_bayaran", idBayaran);						
				h.put("idKemaskini", idUser);
				idPeringkatbayaran = FrmCukaiPenyataData.updatePeringkatBayarInteger(h);							
				//list = FrmCukaiPenyataData.getListPenyata(idNegeri);						
				String sumCukai = getParam("sumCukai");
				int sumIdHakmilik = Integer.parseInt(getParam("sumIdHakmilik"));
				myLog.info("CukaiProcess::kemaskiniPeringkatBayar::idNegeri::"+idNegeri);
				myLog.info("CukaiProcess::kemaskiniPeringkatBayar::tahun_cukai::"+tahun_cukai);
				myLog.info("CukaiProcess::kemaskiniPeringkatBayar::peringkat_bayaran::"+idBayaran);						
				myLog.info("CukaiProcess::kemaskiniPeringkatBayar:: h = "+h);
				myLog.info("CukaiProcess::KemaskiniPenyata::idPeringkatbayaran::"+idPeringkatbayaran);
												
				for(int i=0; i<vector.size(); i++){							
					Hashtable input = (Hashtable)vector.get(0);
					Hashtable hash = new Hashtable();				
					hash.put("idCukaiUtama",idPeringkatbayaran);					
					hash.put("jum_cukai", input.get("sumCukai"));
					hash.put("jum_hakmilik", Integer.parseInt(getParam("sumIdHakmilik")));						
					hash.put("idPeringkat",idPeringkatbayaran);							
					hash.put("tahun",tahun_cukai);							
					hash.put("idNegeri",idNegeri);				
					hash.put("idDaerah",(String)input.get("idDaerah"));							
					hash.put("idKemaskini", idUser);
					myLog.info("CukaiProcess::simpanCukaiUtama::idCukaiUtama::"+idPeringkatbayaran);
					myLog.info("CukaiProcess::simpanCukaiUtama::jum_cukai::"+sumCukai);
					myLog.info("CukaiProcess::simpanCukaiUtama::jum_hakmilik::"+sumIdHakmilik);
					myLog.info("CukaiProcess::simpanCukaiUtama::idPeringkat::"+idPeringkatbayaran);						
					myLog.info("CukaiProcess::simpanCukaiUtama::tahun::"+tahun_cukai);
					myLog.info("CukaiProcess::simpanCukaiUtama::idNegeri::"+idNegeri);	
					myLog.info("CukaiProcess::kemaskiniCukaiUtama:: hash = "+hash);
					FrmCukaiPenyataData.updateCukaiUtama(hash);
					
				}				
				//result = "kemaskini";
			
			}
			
		}	 
		
		//27/10/2010
		private void simpanPenyata(HttpSession session,String idPermohonan,Vector vector) throws Exception {
			//int idPeringkatbayaran = idPeringkatBayaran==""?0:Integer.parseInt(idPeringkatBayaran);
		    int idNegeriInt = Integer.parseInt(idNegeri);		
	    	int tahun_cukai = Integer.parseInt(tahunParam);
	    	//String idBayaran = getParam("socbayaran");
	    	String idCukaiUtama = "";
				
			if(idPeringkatBayaran.equals("0")){
				Hashtable h = new Hashtable();	
				h.put("idpermohonan", idPermohonan);						
				h.put("idNegeri", idNegeriInt);
				h.put("tahun_cukai", tahun_cukai);						
				h.put("peringkat_bayaran", peringkatBayar);		    
				h.put("idMasuk", idUser);
				idPeringkatBayaran = FrmCukaiPenyataData.simpanPeringkatBayar(h);						
				//list = FrmCukaiPenyataData.getListPenyata(idNegeri);
				myLog.info("CukaiProcess::simpanPeringkatBayar::idNegeri::"+idNegeri);						
				myLog.info("CukaiProcess::simpanPeringkatBayar::tahun_cukai::"+tahun_cukai);
				//myLog.info("CukaiProcess::simpanPeringkatBayar::peringkat_bayaran::"+idBayaran);						
				myLog.info("CukaiProcess::simpanPeringkatBayar:: h = "+h);
				//myLog.info("CukaiProcess::SimpanPenyata::idPeringkatbayaran::"+idPeringkatbayaran);						
						
				for(int i=0; i<vector.size(); i++){							
					Hashtable input = (Hashtable)vector.get(i);
					Hashtable hash = new Hashtable();
//					hash.put("idCukaiUtama",idPeringkatBayaran);					
					hash.put("jum_cukai", input.get("sumCukai"));						
					hash.put("jum_hakmilik",(Integer)input.get("sumIdHakmilik"));							
					hash.put("idPeringkat",idPeringkatBayaran);							
					hash.put("tahun",tahun_cukai);
					hash.put("idNegeri",idNegeriInt);					
					hash.put("idDaerah",(String)input.get("idDaerah"));							
					hash.put("idMasuk", idUser);
//					myLog.info("CukaiProcess::simpanCukaiUtama::idCukaiUtama::"+idPeringkatbayaran);
//					myLog.info("CukaiProcess::simpanCukaiUtama::idPeringkat::"+idPeringkatbayaran);
//					myLog.info("CukaiProcess::simpanCukaiUtama::tahun::"+tahun_cukai);						
//					myLog.info("CukaiProcess::simpanCukaiUtama::idNegeri::"+idNegeri);
					myLog.info("CukaiProcess::simpanCukaiUtama:: hash = "+hash);
					FrmCukaiPenyataData.simpanCukaiUtama(hash);
					
				}
				//result = "baru";
				
			}else{
				//	Kemaskini Peringkat Bayaran
				Hashtable h = new Hashtable();	
				h.put("idpermohonan", idPermohonan);			
				h.put("idPeringkatbayaran", idPeringkatBayaran);						
				h.put("idNegeri", idNegeriInt);
				h.put("tahun_cukai", tahun_cukai);							
				h.put("peringkat_bayaran", peringkatBayar);						
				h.put("idMasuk", idUser);
				h.put("idKemaskini", idUser);
				//if(getICukai().isCukaiPeringkatBayaran(idPermohonan,tahunParam))
				idPeringkatBayaran = FrmCukaiPenyataData.updatePeringkatBayar(h);	
				//else
				//	idPeringkatBayaran = FrmCukaiPenyataData.simpanPeringkatBayar(h);						

				//list = FrmCukaiPenyataData.getListPenyata(idNegeri);						
				String sumCukai = getParam("sumCukai");
				int sumIdHakmilik = Integer.parseInt(getParam("sumIdHakmilik"));		
				myLog.info("CukaiProcess::kemaskiniPeringkatBayar:: h = "+h);
												
				for(int i=0; i<vector.size(); i++){							
					Hashtable input = (Hashtable)vector.get(i);
					Hashtable hash = new Hashtable();				
					//hash.put("idCukaiUtama",idPeringkatBayaran);					
					hash.put("jum_cukai", input.get("sumCukai"));
//					hash.put("jum_hakmilik", Integer.parseInt(getParam("sumIdHakmilik")));						
					hash.put("jum_hakmilik", Integer.parseInt(String.valueOf(input.get("sumIdHakmilik"))));						
					hash.put("idPeringkat",idPeringkatBayaran);							
					hash.put("tahun",tahun_cukai);							
					hash.put("idNegeri",idNegeriInt);				
					hash.put("idDaerah",String.valueOf(input.get("idDaerah")));							
					hash.put("idMasuk", idUser);
					hash.put("idKemaskini", idUser);
////					myLog.info("CukaiProcess::simpanCukaiUtama::idCukaiUtama::"+idPeringkatbayaran);
//					myLog.info("CukaiProcess::simpanCukaiUtama::jum_cukai::"+sumCukai);
//					myLog.info("CukaiProcess::simpanCukaiUtama::jum_hakmilik::"+sumIdHakmilik);
////					myLog.info("CukaiProcess::simpanCukaiUtama::idPeringkat::"+idPeringkatbayaran);						
//					myLog.info("CukaiProcess::simpanCukaiUtama::tahun::"+tahun_cukai);
//					myLog.info("CukaiProcess::simpanCukaiUtama::idNegeri::"+idNegeri);	
					myLog.info("CukaiProcess::kemaskiniCukaiUtama:: hash = "+hash);
					if(getICukai().isCukaiUtama(idPeringkatBayaran,String.valueOf(input.get("idDaerah")),tahunParam))
						FrmCukaiPenyataData.updateCukaiUtama(hash);
					else
						FrmCukaiPenyataData.simpanCukaiUtama(hash);

				}				
				//result = "kemaskini";
			
			}
			
		}	  
			  
		//Baucer Lama	  
	  private void XViewBaucer(HttpSession session, int idNegeri, int idPeringkatbayaran
			  , int peringkat_bayaran, String disability, String readability
			  , String style1, String style2) throws Exception {
		  	Vector list = new Vector();
			list.clear();
//___list baucer_________________________________________________________________________________________________________________					    
			try{
				list = FrmCukaiBaucerData.getListBaucer(idNegeri,idPeringkatbayaran,peringkat_bayaran);
				System.out.println("CukaiProcess::ViewBaucer::list.size():::"+list.size());
				this.context.put("ResultSimpan", "");
				
				if(list.size() != 0){			    
					this.context.put("Baucer", list);
					System.out.println("CukaiProcess::ViewBaucer::list::"+list);
					Hashtable bau = (Hashtable) list.get(0);
					this.context.put("idPeringkatbayaran", bau.get("idPeringkatbayaran"));
					this.context.put("idDaerah", bau.get("idDaerah"));
					this.context.put("selectDaerah",UtilHTML.SelectDaerahByCukai(idPeringkatbayaran,"socDaerah",null,"disabled",null));
					System.out.println("ViewBaucer::idPeringkatbayaran:::"+bau.get("idPeringkatbayaran"));
					System.out.println("ViewBaucer::idDaerah:::"+bau.get("idDaerah"));
					this.context.put("Style1", style1);
				    this.context.put("Style2", style2);
				}else{
					this.context.put("Baucer", "");
				    style1 = "none";
				    this.context.put("Style1", style1);
				    this.context.put("Style2", "");
				}
			}catch(Exception ex){
				System.out.println("CukaiProcess::ViewBaucer::Exception:: = "+ex);
				ex.printStackTrace();
			}
		}

	  private void viewBaucer(HttpSession session, String idNegeri, String idPeringkatbayaran
			  , String peringkat_bayaran, String disability, String readability
			  , String style1, String style2) throws Exception{
		  Vector list = new Vector();
		  list.clear();
		  try{
			  list = getICukai().getSenaraiBaucer(idNegeri, idPeringkatbayaran, peringkat_bayaran,tahunParam);
			  this.context.put("ResultSimpan", "");				
			  if(list.size() != 0){			    
				  this.context.put("Baucer", list);
				  //System.out.println("CukaiProcess::ViewBaucer::list::"+list);
				  Hashtable bau = (Hashtable) list.get(0);
				  this.context.put("idPeringkatbayaran", bau.get("idPeringkatbayaran"));
				  this.context.put("idDaerah", bau.get("idDaerah"));
				  this.context.put("selectDaerah",utilHTML.SelectDaerahByCukai(idPeringkatBayaran,"socDaerah",null,"disabled",null));
				  //System.out.println("ViewBaucer::idPeringkatbayaran:::"+bau.get("idPeringkatbayaran"));
				  //System.out.println("ViewBaucer::idDaerah:::"+bau.get("idDaerah"));
				  this.context.put("Style1", style1);
				  this.context.put("Style2", style2);
			  }else{
				  this.context.put("Baucer", "");
				  style1 = "none";
				  this.context.put("Style1", style1);
				  this.context.put("Style2", "");
			  }
			  
		  }catch(Exception ex){
//			System.out.println("CukaiProcess::ViewBaucer::Exception:: = "+ex);
			  ex.printStackTrace();
		  }
		  
	  }
	  
	  private String SimpanTBaucer(HttpSession session,int idPeringkatbayaran) throws Exception {
		  String idBaucer = "0";
		  if(getParam("idBaucer") == ""){
			  //baucer baru
			  Hashtable h = new Hashtable();
			  h.put("idPeringkatbayaran", idPeringkatbayaran);
			  h.put("socDaerah", Integer.parseInt(getParam("socDaerah")));
			  h.put("tkh_baucer", getParam("txdTarikhBaucer"));
			  h.put("no_resit", getParam("txtNoCek"));
			  h.put("no_baucer", getParam("txtNoBaucer"));
			  h.put("tkh_resit", getParam("txdTarikhCek"));
			  h.put("amaun_baucer", getParam("txtAmaunBaucer"));
			  h.put("tkh_terima", getParam("txdTarikhTerima"));
			  h.put("idNegeri", Integer.parseInt(getParam("idNegeri")));
			  h.put("tahun", tahunParam);
			  idBaucer = FrmCukaiBaucerData.simpanTBaucer(h);
			  //result = "baru";
			  return idBaucer;
			  
		}else{
			//kemaskini baucer
			//System.out.println("CukaiProcess::SimpanTBaucer::kemaskini");
			Hashtable h = new Hashtable();
			idBaucer = getParam("idBaucer");
			h.put("idBaucer", idBaucer);
			h.put("idPeringkatbayaran", idPeringkatbayaran);
			h.put("tkh_baucer", getParam("txdTarikhBaucer"));
			h.put("no_resit", getParam("txtNoCek"));
			h.put("no_baucer", getParam("txtNoBaucer"));
			h.put("tkh_resit", getParam("txdTarikhCek"));
			h.put("amaun_baucer", getParam("txtAmaunBaucer"));
			h.put("tkh_terima", getParam("txdTarikhTerima"));
			h.put("idNegeri", Integer.parseInt(getParam("idNegeri")));
			h.put("tahun", tahunParam);
			if(getICukaiBaucer().isBaucer(getParam("idBaucer"))){
				FrmCukaiBaucerData.updateTBaucer(h);
				//result = "kemaskini";
			} else{
				idBaucer = FrmCukaiBaucerData.simpanTBaucer(h);
				//result = "baru";
			}
			//System.out.println("CukaiTambahBaucer::KemaskiniBaucer:: h = "+h);
			return idBaucer;
		}
		  
	}
		
	private void DataTBaucer(HttpSession session,int idNegeri, int idBaucer, int idPeringkatbayaran, String disability, String readability, String style1, String style2) throws Exception {
	  	Vector list = new Vector();
		list.clear();					    
		try{
			list = FrmCukaiBaucerData.getListTBaucer(idNegeri,String.valueOf(idBaucer),idPeringkatbayaran);
			this.context.put("ResultSimpan", "");
			
			if(list.size() != 0){			    
				this.context.put("Baucer", list);
				Hashtable bau = (Hashtable) list.get(0);
				this.context.put("idPeringkatbayaran", bau.get("idPeringkatbayaran"));
				if(getParam("idDaerah")!=""){
					this.context.put("selectDaerah",UtilHTML.SelectDaerahByCukai(idPeringkatbayaran,"socDaerah",Long.parseLong(getParam("idDaerah").toString()), "disabled", null));
				}else{
					this.context.put("selectDaerah",UtilHTML.SelectDaerahByCukai(idPeringkatbayaran,"socDaerah",Long.parseLong(bau.get("idDaerah").toString()), "disabled", null));	
				}
				this.context.put("Style1", style1);
			    this.context.put("Style2", style2);
			
			}else{
				this.context.put("Baucer", "");
				style1 = "none";
				this.context.put("Style1", style1);
				this.context.put("Style2", "");
			}

		}catch(Exception ex){
			myLog.info("CukaiProcess::DataTBaucer::Exception:: = "+ex);
			ex.printStackTrace();

		}

	}
		
	private void DataTBaucerBaru(HttpSession session, int idNegeri, String disability, String readability, String style1, String style2) throws Exception {
		Vector list = new Vector();
		list.clear();		
		try{
			this.context.put("ResultSimpan", "");
			this.context.put("Baucer", "");
			style1 = "none";
			this.context.put("Style1", style1);
			this.context.put("Style2", "");

		}catch(Exception ex){
			myLog.info("FrmCukai::DataTBaucerBaru::Exception:: = "+ex);
			ex.printStackTrace();
		}
		
	}
		
	private void ViewBayaran(HttpSession session, int idNegeri,String disability, String readability, String style1, String style2) throws Exception {
		Vector list = new Vector();
		list.clear();		
		try{
			//list = FrmCukaiBayaranData.getListBayaran(idNegeri);
			list = FrmCukaiBayaranData.getListBayaran(String.valueOf(idNegeri),peringkatBayar);
			this.context.put("ResultSimpan", "");			
			if(list.size() != 0){			    
				this.context.put("Bayaran", list);
				Hashtable bay = (Hashtable) list.get(0);
				this.context.put("idPeringkatbayaran", bay.get("idPeringkatbayaran"));
				this.context.put("idDaerah", bay.get("idDaerah"));
				this.context.put("id_bayarancukai", bay.get("id_bayarancukai"));
				this.context.put("selectBaucer",UtilHTML.SelectBaucer(Integer.parseInt(bay.get("idPeringkatbayaran").toString()),"socBaucer",null,"disabled",null));
				this.context.put("Style1", style1);
				this.context.put("Style2", style2);
				
			}else{			
				this.context.put("Bayaran", "");
			    style1 = "none";
			    this.context.put("Style1", style1);
			    this.context.put("Style2", "");
			
			}
		}catch(Exception ex){
			myLog.info("FrmCukai::ViewBayaran::Exception:: = "+ex);
			ex.printStackTrace();
	
		}
	
	}
		
	private int SimpanTBayaran(HttpSession session,String idPeringkatbayaran) throws Exception	{
		int idBayaranCukai = 0;
		if(getParam("idBayaranCukai") == ""){
			//bayaran baru
			//System.out.println("CukaiProcess::SimpanTBayaran::baru");
			Hashtable h = new Hashtable();
			h.put("idPeringkatbayaran", idPeringkatbayaran);
			h.put("socBaucer", Integer.parseInt(getParam("socBaucer")));
			h.put("tkh_bayaran", getParam("txdTarikhBayaran"));
			h.put("nama_bank", getParam("txtNamaBank"));
			h.put("amaunBayaran", getParam("txtAmaun"));
			h.put("noRujBayaran", getParam("txtNoRujBayaran"));
//			h.put("noCek", getParam("txtNoCek"));
			if(getParam("txtNoResit") != ""){
				h.put("noResit", getParam("txtNoResit"));
			}else{
				h.put("noResit", "");
			}
			h.put("tkh_resit", getParam("txdTarikhResit"));
			idBayaranCukai = FrmCukaiBayaranData.simpanTBayaran(h);
			//result = "baru";
			//System.out.println("CukaiProcess::SimpanBayaran::idBayaranCukai::"+idBayaranCukai);
			//System.out.println("CukaiTambahBayaran::SimpanBayaran:: h = "+h);
			return idBayaranCukai;
			
		}else{
			//kemaskini bayaran
			//System.out.println("CukaiProcess::SimpanTBayaran:::kemaskini::idBayaranCukai::"+getParam("idBayaranCukai"));
			//System.out.println("CukaiProcess::SimpanTBayaran::kemaskini");
			Hashtable h = new Hashtable();
			idBayaranCukai = Integer.parseInt(getParam("idBayaranCukai"));
			h.put("idBayaranCukai", idBayaranCukai);
			h.put("idPeringkatbayaran", idPeringkatbayaran);
			h.put("idBaucer", Integer.parseInt(getParam("idBaucer")));
			h.put("tkh_bayaran", getParam("txdTarikhBayaran"));
			h.put("nama_bank", getParam("txtNamaBank"));
			h.put("amaunBayaran", getParam("txtAmaun"));
			h.put("noRujBayaran", getParam("txtNoRujBayaran"));
			//h.put("noCek", getParam("txtNoCek"));
			h.put("noResit", getParam("txtNoResit"));
			h.put("tkh_resit", getParam("txdTarikhResit"));
			FrmCukaiBayaranData.updateTBayaran(h);
			//result = "kemaskini";
			//System.out.println("CukaiTambahBayaran::KemaskiniBayaran:: h = "+h);
			return idBayaranCukai;
		
		}
	
	}

	private String simpanBayaran(HttpSession session,String idPeringkatbayaran) throws Exception	{
		int idBayaranCukai = 0;
		if(getParam("idBayaranCukai") == ""){
			//bayaran baru
			//System.out.println("CukaiProcess::SimpanTBayaran::baru");
			Hashtable h = new Hashtable();
			h.put("idPeringkatbayaran", idPeringkatbayaran);
			h.put("socBaucer", Integer.parseInt(getParam("socBaucer")));
			h.put("tkh_bayaran", getParam("txdTarikhBayaran"));
			h.put("nama_bank", getParam("txtNamaBank"));
			h.put("amaunBayaran", getParam("txtAmaun"));
			h.put("noRujBayaran", getParam("txtNoRujBayaran"));
//			h.put("noCek", getParam("txtNoCek"));
			if(getParam("txtNoResit") != ""){
				h.put("noResit", getParam("txtNoResit"));
			}else{
				h.put("noResit", "");
			}
			h.put("tkh_resit", getParam("txdTarikhResit"));
			idBayaranCukai = FrmCukaiBayaranData.simpanTBayaran(h);
			//result = "baru";
			//System.out.println("CukaiProcess::SimpanBayaran::idBayaranCukai::"+idBayaranCukai);
			//System.out.println("CukaiTambahBayaran::SimpanBayaran:: h = "+h);
			return String.valueOf(idBayaranCukai);
		}else{
			//kemaskini bayaran
			Hashtable h = new Hashtable();
			idBayaranCukai = Integer.parseInt(getParam("idBayaranCukai"));
			h.put("idBayaranCukai", idBayaranCukai);
			h.put("idPeringkatbayaran", idPeringkatbayaran);
			h.put("idBaucer", Integer.parseInt(getParam("idBaucer")));
			h.put("tkh_bayaran", getParam("txdTarikhBayaran"));
			h.put("nama_bank", getParam("txtNamaBank"));
			h.put("amaunBayaran", getParam("txtAmaun"));
			h.put("noRujBayaran", getParam("txtNoRujBayaran"));
			//h.put("noCek", getParam("txtNoCek"));
			h.put("noResit", getParam("txtNoResit"));
			h.put("tkh_resit", getParam("txdTarikhResit"));
			FrmCukaiBayaranData.updateTBayaran(h);
			//result = "kemaskini";
			//System.out.println("CukaiTambahBayaran::KemaskiniBayaran:: h = "+h);
			return String.valueOf(idBayaranCukai);
		
		}
	
	}	
	
	private void DataTBayaran(HttpSession session,int idNegeri, int idBayaranCukai, int idPeringkatbayaran, String disability, String readability, String style1, String style2) throws Exception {
	  	Vector list = new Vector();
		list.clear();
				    
		try{
			list = FrmCukaiBayaranData.getListTBayaran(idNegeri,idBayaranCukai);
			//System.out.println("CukaiProcess::DataTBayaran::list.size():::"+list.size());
			this.context.put("ResultSimpan", "");
			if(list.size() != 0){			    
				this.context.put("Bayaran", list);
				//System.out.println("CukaiProcess::DataTBayaran::list::"+list);
				Hashtable bau = (Hashtable) list.get(0);
				this.context.put("idNegeri", bau.get("idNegeri"));
				//System.out.println("CukaiProcess::DataTBayaran::socBaucer:::"+getParam("socBaucer"));
				if(getParam("idBaucer")!=""){
					this.context.put("selectBaucer",UtilHTML.SelectBaucer(idPeringkatbayaran,"socBaucer", Long.parseLong(getParam("idBaucer")),"disabled",null));	
				}else{
					this.context.put("selectBaucer",UtilHTML.SelectBaucer(idPeringkatbayaran,"socBaucer", Long.parseLong(bau.get("idBaucer").toString()),"disabled",null));	
				}
				
//				System.out.println("CukaiProcess::DataTBayaran::idNegeri:::"+bau.get("idNegeri"));
//				System.out.println("CukaiProcess::DataTBayaran::idBaucer:::"+bau.get("idBaucer"));
//				System.out.println("CukaiProcess::DataTBayaran::idBayaranCukai:::"+bau.get("idBayaranCukai"));
				this.context.put("Style1", style1);
				this.context.put("Style2", style2);
			}else{
				this.context.put("Bayaran", "");
				style1 = "none";
				this.context.put("Style1", style1);
				this.context.put("Style2", "");
			}
		}catch(Exception ex){
			myLog.info("FrmCukai::DataTBayaran::Exception:: = "+ex);
			ex.printStackTrace();
		}
		
	}
	
	private void bayaranView(HttpSession session,String idNegeri, String idBayaranCukai, String idPeringkatbayaran, String disability, String readability, String style1, String style2) throws Exception {
	  	Vector list = new Vector();
		list.clear();				    
		try{
			list = FrmCukaiBayaranData.getListTBayaran(idNegeri,idBayaranCukai);
			//System.out.println("CukaiProcess::DataTBayaran::list.size():::"+list.size());
			this.context.put("ResultSimpan", "");
			if(list.size() != 0){			    
				this.context.put("Bayaran", list);
				//System.out.println("CukaiProcess::DataTBayaran::list::"+list);
				Hashtable bau = (Hashtable) list.get(0);
				this.context.put("idNegeri", bau.get("idNegeri"));
				//System.out.println("CukaiProcess::DataTBayaran::socBaucer:::"+getParam("socBaucer"));
				if(getParam("idBaucer")!=""){
					this.context.put("selectBaucer",UtilHTML.SelectBaucer(idPeringkatbayaran,"socBaucer", Long.parseLong(getParam("idBaucer")),"disabled",null));	
				}else{
					this.context.put("selectBaucer",UtilHTML.SelectBaucer(idPeringkatbayaran,"socBaucer", Long.parseLong(bau.get("idBaucer").toString()),"disabled",null));	
				}				
//				System.out.println("CukaiProcess::DataTBayaran::idNegeri:::"+bau.get("idNegeri"));
//				System.out.println("CukaiProcess::DataTBayaran::idBaucer:::"+bau.get("idBaucer"));
//				System.out.println("CukaiProcess::DataTBayaran::idBayaranCukai:::"+bau.get("idBayaranCukai"));
				this.context.put("Style1", style1);
				this.context.put("Style2", style2);
			}else{
				this.context.put("Bayaran", "");
				style1 = "none";
				this.context.put("Style1", style1);
				this.context.put("Style2", "");
			}
		}catch(Exception ex){
			myLog.info("FrmCukai::DataTBayaran::Exception:: = "+ex);
			ex.printStackTrace();
		}
		
	}
		
	private void DataTBayaranBaru(HttpSession session, int idNegeri, String disability, String readability, String style1, String style2) throws Exception {
		Vector list = new Vector();
		list.clear();
		try{
			this.context.put("ResultSimpan", "");
			this.context.put("Bayaran", "");
			//System.out.println("CukaiProcess::DataTBayaranBaru::idNegeri:::"+idNegeri);
			//System.out.println("CukaiProcess::DataTBayaranBaru::list.size() = "+list.size());
		    style1 = "none";
		    this.context.put("Style1", style1);
		    this.context.put("Style2", "");
		}catch(Exception ex){
			myLog.info("FrmCukai::DataTBayaranBaru::Exception = "+ex);
		}
	}
	
	private void viewBayaranBaru(HttpSession session, String idNegeri, String disability, String readability, String style1, String style2) throws Exception {
		Vector list = new Vector();
		list.clear();
		try{
			this.context.put("ResultSimpan", "");
			this.context.put("Bayaran", "");
		    style1 = "none";
		    this.context.put("Style1", style1);
		    this.context.put("Style2", "");
		    
		}catch(Exception ex){
			myLog.info("FrmCukai::DataTBayaranBaru::Exception = "+ex);
			
		}
	}
	
	private ICukai getICukai(){
		if(iCukai==null){
			iCukai = new CukaiBean();
		}
		return iCukai;
		
	}
	
	private ICukaiPenyata getICukaiPenyata(){
		if(iCukaiPen==null){
			iCukaiPen = new CukaiPenyataBean();
		}
		return iCukaiPen;
		
	}
	
	private ICukaiBaucer getICukaiBaucer(){
		if(iCukaiBaucer==null){
			iCukaiBaucer = new CukaiBaucerBean();
		}
		return iCukaiBaucer;
		
	}
	
	public static String setSign(){
		
		StringBuffer bff = new StringBuffer();
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("<p>");
		bff.append("Tuan/Puan,");
		bff.append("<br/>");
		bff.append("<br/>");		  
		bff.append("Permohonan Tuan/Puan telah diterima dan nombor fail adalah seperti berikut <nombor fail>.");
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("Sila <i>login</i> masuk ke www.etapp.gov.my untuk semakan serta pengesahan dari pihak tuan/puan.");
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("Sekian, Terima Kasih.");
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("etapp_webmaster@ekptg.gov.my");
		bff.append("<br/>");
		bff.append("<br/>");
		bff.append("Nota : Email ini adalah dijana oleh sistem eTapp dan tidak perlu dibalas.");
		bff.append("</p>");
		return bff.toString();
	}
	
	private void getBaucerValues(){
		String idDaerah = getParam("socDaerah")==""?"0":getParam("socDaerah");
		String tarikhBaucer = getParam("txdTarikhBaucer")==""?"01/01/1900":getParam("txdTarikhBaucer");
		String noBaucer = getParam("txtNoBaucer");
		String tarikhCek = getParam("txdTarikhCek")==""?"01/01/1900":getParam("txdTarikhCek");
		String noRujukan = getParam("txtNoCek");
		String tarikhTerima = getParam("txdTarikhTerima")==""?"01/01/1900":getParam("txdTarikhTerima");
		String jumlahBaucer = getParam("txtAmaunBaucer")==""?"0.00":getParam("txtAmaunBaucer");
		
		bCukai = new BaucerCukai();
		daerah = new Daerah();
		Resit resit = new Resit();
		//Baucer baucer = new Baucer();	
		
		daerah.setIdDaerah(Long.parseLong(idDaerah));
		//daerah.setNamaDaerah(rs.getString("nama_daerah"));
		bCukai.setDaerah(daerah);
		//bCukai.setId( rs.getLong("id_baucer"));
		bCukai.setNoRujukan(noBaucer);
		//bCukai.setIdPeringkat(rs.getLong("id_peringkatbayaran"));
		bCukai.setTarikh(new Date(tarikhBaucer));
		resit.setNoRujukan(noRujukan);
		resit.setTarikh(new Date(tarikhCek));
		bCukai.setResit(resit);
		bCukai.setJumlah(Double.parseDouble(jumlahBaucer));
		bCukai.setTarikhTerima(new Date(tarikhTerima));
		//baucer.setBil(rs.getInt("bil_bayaran"));
	    //bCukai.setBaucer(baucer);
		
		context.put("bcukai", bCukai);
		context.put("idNegeri", Integer.parseInt(idNegeri));
		context.put("negeri", idNegeri);
	}
		
}
