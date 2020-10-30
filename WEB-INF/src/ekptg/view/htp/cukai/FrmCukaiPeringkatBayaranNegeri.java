package ekptg.view.htp.cukai;

import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.InternalUserUtil;
import ekptg.helpers.Utils;
import ekptg.model.entities.InternalUser;
import ekptg.model.htp.FrmCukaiBaucerData;
import ekptg.model.htp.FrmCukaiBayaranData;
import ekptg.model.htp.FrmCukaiPenyataData;
import ekptg.model.htp.FrmUtilData;
import ekptg.model.htp.UtilHTML;
import ekptg.model.htp.cukai.CukaiBean;
import ekptg.model.htp.cukai.CukaiPenyataBean;
import ekptg.model.htp.cukai.ICukai;
import ekptg.model.htp.cukai.ICukaiPenyata;
import ekptg.model.htp.cukai.entity.CukaiUtama;
import ekptg.model.htp.entity.HakMilik;

public class FrmCukaiPeringkatBayaranNegeri extends AjaxBasedModule{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String PATH="app/htp/cukai/";
	private InternalUser iu = null;
	private String result = new String();
	static Logger mylog = Logger.getLogger(ekptg.view.htp.cukai.FrmCukaiPeringkatBayaranNegeri.class);
	private String peringkatBayar = "";
	private String idPermohonan = "";
	private String idPeringkatBayaran = "";
	private String idNegeri = "";
	private ICukai iCukai = null;
	private ICukaiPenyata iCukaiPen = null;
	private HakMilik hakmilik = null;
	private UtilHTML utilHTML = new UtilHTML();
    private String idUser = "";
    private Vector vector = null;
    private Hashtable hPeringkatBayar = null;
    private boolean statusPeringkatBayar = false;
	private String year = "";
	private String tahunParam = "";
	private CukaiUtama cu = null;
		
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
	    //String submitAjax = getParam("command");
	    //String submit = getParam("command1");
		String action = getParam("action");
	    String submit = getParam("command");
	    this.context.put("command1", submit);
	    String pageMode = getParam("pagemode");
	    this.context.put("pagemode", pageMode);
		Vector list = new Vector();
		mylog.info(":command="+submit);
		mylog.info("action="+action);
		mylog.info("pageMode="+pageMode);
		//mylog.info(this.className+":fail::"+getParam("fail"));
		peringkatBayar = getParam("peringkat_bayaran");
		idPermohonan = getParam("idPermohonan");
		idPeringkatBayaran = getParam("idPeringkatbayaran");
		idNegeri= getParam("idNegeri");
		idUser = (String)session.getAttribute("_ekptg_user_id");		
		tahunParam = getParam("tahun_cukai");
		
		if("cukaiperingkatbayar".equals(submit)){
			mylog.info("cukaiperingkatbayar:"+pageMode);
		    template_name = PATH+"frmCukaiPenyata.jsp";
		    Hashtable permohonan = getICukai().getPermohonanInfo(idPermohonan);
		    int idNegeriInt = Integer.parseInt(getParam("idNegeri"));		
			String negeri = getParam("negeri");
			String Daerah = getParam("socDaerah")==""?"0":getParam("socDaerah");
			long idDaerah = Long.parseLong(Daerah);
			statusPeringkatBayar = false;

			vector = (Vector)FrmCukaiPenyataData.getList("11", "", (String)permohonan.get("fail"), Utils.parseLong((String)permohonan.get("idnegeri")));
			mylog.info("vector.isEmpty()="+vector.isEmpty());
			if(vector.isEmpty() == false){
				hPeringkatBayar = (Hashtable)vector.get(0);
				peringkatBayar = String.valueOf(hPeringkatBayar.get("peringkatBayaran"));
				idPeringkatBayaran = String.valueOf(hPeringkatBayar.get("idPeringkatBayaran"));
				if(idPeringkatBayaran != "0"){
					statusPeringkatBayar = true;
				}
			}
			mylog.info("peringkatBayar="+peringkatBayar);
			mylog.info("idPeringkatBayaran="+idPeringkatBayaran);
			mylog.info("statusPeringkatBayar="+statusPeringkatBayar);
//			String idBayaran = getParam("peringkat_bayaran");
//	    	if(!pen.get("peringkatBayaran").equals("TIADA")){ 	
//	    		idBayaran = (String)pen.get("peringkatBayaran");
//	    	}
    		
    		year = lebah.util.Util.getDateTime(new Date(), "yyyy");
    		
			this.context.put("info", permohonan);
    		this.context.put("idbayaran", peringkatBayar);
    		//this.context.put("idPeringkatbayaran", pen.get("idPeringkatBayaran"));  
    		this.context.put("tahun_cukai", year);
    		this.context.put("tahuncukai", Integer.parseInt(year));
    		this.context.put("pageMode", pageMode); 
    		this.context.put("statusPeringkatBayar", statusPeringkatBayar);
			//by rosli
//			this.context.put("senaraiLangkah", FrmUtilData.getLangkahByPermohonan("11","43",""+idPermohonan,(String)session.getAttribute("_portal_role")));
			//idPeringkatBayaran = String.valueOf(pen.get("idPeringkatBayaran"));
			//peringkatBayar = String.valueOf(pen.get("peringkatBayaran"));
			
			// button kemaskini penyata_______________________________________________________________________________________			   
			if("simpanPenyata".equals(pageMode)){
				mylog.info("simpanPenyata");
				selectedTab = "0";
				readability = "readonly";
				disability = "disabled";
				style2 = "none";
				//simpanPenyata(session, idPermohonan);
				vector = getICukaiPenyata().getSenaraiPenyata(String.valueOf(idNegeri), year);
				simpanPenyata(session,idPermohonan,vector);
			    DataPenyata(session,idNegeriInt,disability,readability,style1,style2);			    
	    		//System.out.println("simpanPenyata::peringkat_bayaran:::"+getParam("peringkat_bayaran"));
			    //System.out.println("simpanPenyata::socbayaran:::"+getParam("socbayaran"));				
			    this.context.put("peringkat_bayaran", getParam("peringkat_bayaran"));
	    		this.context.put("idbayaran", getParam("socbayaran"));
			    this.context.put("SimpanStatus", "success");
				this.context.put("ResultSimpan", result); 
				
			}else if("penyataview".equals(pageMode)){
				mylog.info("penyataview");
				selectedTab = "0";
			    readability = "readonly";
				disability = "disabled";
				style2 = "none";
				year = tahunParam==""?year:tahunParam; 
				DataPenyata(session,idNegeriInt,disability,readability,style1,style2);

				//System.out.println("penyataview::peringkat_bayaran:::"+getParam("peringkat_bayaran"));
				//System.out.println("penyataview::socbayaran:::"+getParam("socbayaran"));				
				
				//this.context.put("peringkat_bayaran", getParam("peringkat_bayaran"));
	    		//this.context.put("idbayaran", getParam("peringkat_bayaran"));
				//this.context.put("idPermohonan",idPermohonan);		
//				this.context.put("idNegeri", idNegeri);
//				this.context.put("negeri", negeri);
				mylog.info("penyataview:peringkatBayar="+peringkatBayar);
				int bilCukaiUtama=0;
				if(statusPeringkatBayar){
					peringkatBayar = getParam("socbayaran")==null?getParam("peringkat_bayaran"):getParam("socbayaran");
					cu = getICukai().getCukaiUtama(idPeringkatBayaran);
					if(cu!=null){
						mylog.info("bilHakmilik="+cu.getBilanganHakmilik()+",="+cu.getJumlahCukai());
						this.context.put("bilHakmilik", String.valueOf(cu.getBilanganHakmilik()));
						this.context.put("jumlahCukai", String.valueOf(cu.getJumlahCukai()));
						bilCukaiUtama = 1;
					}
					
				}else{
					if(vector.size() != 0){			    
						statusPeringkatBayar = true;
						//cu = null;
					}					
				}
				this.context.put("cukaiUtama",bilCukaiUtama);	
	    		this.context.put("tahun_cukai", year);
				mylog.info("statusPeringkatBayar: "+statusPeringkatBayar);
				
//				EkptgEmailSender email = EkptgEmailSender.getInstance();
//				email.FROM ="etapp_webmaster@ekptg.gov.my";
//				email.SUBJECT="EMAIL DARI CUKAI";
//				email.MESSAGE = setSign();
//				email.MULTIPLE_RECIEPIENT = new String[1];
//				email.MULTIPLE_RECIEPIENT[0] = "roslizakaria@gmail.com";
//				//email.MULTIPLE_RECIEPIENT[1]="wfaresh@gmail.com";
//				//email.MULTIPLE_RECIEPIENT[2]="cipon.it@gmail.com";				
//				email.TO_CC = new String[1];
//				email.TO_CC[0]  = "rosli@hla-group.com";
//				email.sendEmail();
				
			}else if("baucerview".equals(pageMode)){
				selectedTab = "0";
				if(statusPeringkatBayar){

				//idPeringkatBayaran = String.valueOf(pen.get("idPeringkatBayaran"));
				//peringkatBayar = String.valueOf(pen.get("peringkatBayaran"));
				selectedTab = "1";
			//System.out.println("===================="+pageMode);
				int peringkat_bayaran = Integer.parseInt(getParam("peringkat_bayaran"));
	    		//int idPeringkatbayaran = Integer.parseInt(getParam("idPeringkatbayaran"));
	    		//idNegeri = Integer.parseInt(getParam("idNegeri"));
				readability = "readonly";
				disability = "disabled";
				style2 = "none";
				viewBaucer(session,idNegeri,idPeringkatBayaran,peringkatBayar,disability,readability,style1,style2);
			
	    		this.context.put("idPermohonan",idPermohonan);		
				this.context.put("idNegeri", idNegeri);
				//this.context.put("peringkat_bayaran", peringkat_bayaran);
	    		this.context.put("idbayaran", getParam("socbayaran"));
	    		//this.context.put("idPeringkatbayaran", idPeringkatbayaran);
	    		
				}
				mylog.info("baucerview:selectedTab="+selectedTab);

			}else if("cukaiBayaran".equals(pageMode)){
				mylog.info("cukaiBayaran");
				selectedTab = "0";
				if(statusPeringkatBayar){
					selectedTab = "2";
					ViewBayaran(session,idNegeriInt,disability,readability,style1,style2);
			    	this.context.put("idbayaran", getParam("socbayaran"));   
					Vector<?> v = FrmUtilData.getListBaucer(idPeringkatBayaran);  
					mylog.info("bayaranStatus:size="+v.size());
					this.context.put("bayaranStatus", v.size());
				}
				mylog.info("cukaiBayaran:selectedTab="+selectedTab);
			
			}
			this.context.put("idNegeri", idNegeri);
			this.context.put("negeri", negeri);
			
		}else if("tambahBaucer".equals(submit)){
			selectedTab = "1";
			mylog.info("tambahBaucer = "+submit);

	    	template_name = PATH+"frmCukaiTambahBaucer.jsp";
	    	int idNegeri = Integer.parseInt(getParam("idNegeri"));	
		    int idPermohonan = Integer.parseInt(getParam("idPermohonan"));
			this.context.put("idNegeri", idNegeri);
			this.context.put("idPermohonan", idPermohonan);
			this.context.put("negeri", getParam("negeri"));
			this.context.put("peringkat_bayaran", getParam("peringkat_bayaran"));
			String idBaucer;
			int idDaerah;
		    int idPeringkatbayaran;
		    if (getParam("idPeringkatbayaran") != "0")
		    	idPeringkatbayaran = Integer.parseInt(getParam("idPeringkatbayaran"));
		    else
	    		idPeringkatbayaran = 0;
		    this.context.put("idPeringkatbayaran", idPeringkatbayaran);
		    
		    String id_daerah = getParam("socDaerah");
			if (id_daerah == null || id_daerah.trim().length() == 0){
				id_daerah = "0";
			}

			if("simpanB".equals(pageMode)){
				//System.out.println("CukaiProcess::Baucer::simpan1");
				mylog.info("tambahBaucer : pageMode:simpanB="+pageMode);
				readability = "readonly";
			    disability = "disabled";
			    style2 = "none";
			    idBaucer = SimpanTBaucer(session,idPeringkatbayaran);
			    this.context.put("idBaucer", idBaucer);
			    //System.out.println("CukaiProcess::simpan:::idBaucer"+idBaucer);			    
			    if(getParam("socDaerah") != ""){
			    	idDaerah = Integer.parseInt(getParam("socDaerah"));
			    	//System.out.println("CukaiProcess::Baucer::simpanB::idDaerah::"+idDaerah);
			    }else{
			    	idDaerah = Integer.parseInt(getParam("idDaerah"));
			    	//System.out.println("CukaiProcess::Baucer::simpanB::idDaerah::"+idDaerah);
			    }
			    
				DataTBaucer(session,idNegeri,idBaucer,idPeringkatbayaran,disability,readability,style1,style2);
				this.context.put("idPeringkatbayaran", idPeringkatbayaran);
				this.context.put("idDaerah", idDaerah);
				this.context.put("SimpanStatus", "success");
				this.context.put("ResultSimpan", result);				
				//System.out.println("CukaiProcess::Baucer::simpan");
				
			}else if("baru".equals(pageMode)){
				//mylog.info("tambahBaucer : baru");
				mylog.info("tambahBaucer : baru:peringkat_bayaran="+getParam("peringkat_bayaran"));
				
				style1 = "none";
				DataTBaucerBaru(session,idNegeri,disability,readability,style1,style2);
				if(peringkatBayar.equals("1")){
					this.context.put("selectDaerah",utilHTML.SelectNegeriByCukai(String.valueOf(idPeringkatbayaran),"socDaerah",Long.parseLong(id_daerah),"onChange=\"doChangeDaerah();\"", null));				
				}else{
					this.context.put("selectDaerah",UtilHTML.SelectDaerahByCukai(idPeringkatbayaran,"socDaerah",Long.parseLong(id_daerah),"onChange=\"doChangeDaerah();\"", null));
				}
				list = FrmCukaiPenyataData.getAmaunCukai(id_daerah,idPeringkatbayaran);			
					
				if (list.size() == 0){
					Hashtable hash = new Hashtable();
					hash.put("jumlahCukai", "");
					hash.put("idDaerah", "");
					hash.put("idPeringkatbayaran", "");
					list.addElement(hash);
				}			
				//this.context.put("idPeringkatbayaran", idPeringkatbayaran);
				this.context.put("AmaunCukai", list);

			}else if("view".equals(pageMode)){
				mylog.info("tambahBaucer : pageMode="+pageMode);
				idBaucer = getParam("idBaucer");
				this.context.put("idPeringkatbayaran", idPeringkatbayaran);
				this.context.put("idBaucer", idBaucer);
				this.context.put("idDaerah", getParam("idDaerah"));
				style2 = "none";
				DataTBaucer(session,idNegeri,idBaucer,idPeringkatbayaran,disability,readability,style1,style2);

			}
			
		}else if("cukaiBayaran".equals(submit)){

			selectedTab = "2";
			//template_name = "app/htp/frmCukaiBayaran.jsp";
			template_name = PATH+"frmCukaiPenyata.jsp";
			//int idPermohonan = Integer.parseInt(getParam("idPermohonan"));
			int idNegeri = Integer.parseInt(getParam("idNegeri"));
			ViewBayaran(session,idNegeri,disability,readability,style1,style2);
			    
			//this.context.put("idPermohonan", idPermohonan);
			this.context.put("idNegeri", idNegeri);
	    	this.context.put("negeri", getParam("negeri"));
			//this.context.put("selectedTab",selectedTab);
			//this.context.put("peringkat_bayaran", getParam("peringkat_bayaran"));
	    	this.context.put("idbayaran", getParam("socbayaran"));   
			Vector<?> v = FrmUtilData.getListBaucer(idPeringkatBayaran);  
			mylog.info("bayaranStatus:size="+v.size());
			this.context.put("bayaranStatus", v.size());
			
		}else if("tambahBayaran".equals(submit)){
		   	template_name = PATH+"frmCukaiTambahBayaran.jsp";
		   	//idPermohonan = getParam("idPermohonan");
		   	//int idNegeri = Integer.parseInt(getParam("idNegeri"));
		   	//int peringkat_bayaran = Integer.parseInt(getParam("peringkat_bayaran"));
			this.context.put("idNegeri", idNegeri);
	    	this.context.put("negeri", getParam("negeri"));
			//this.context.put("selectedTab",selectedTab);
	    	this.context.put("idbayaran", getParam("socbayaran")); 
			
			int idBayaranCukai;
			String idBaucer;
		    //int idPeringkatbayaran;
//		    if(getParam("idPeringkatbayaran") != "0")
//		    	idPeringkatBayaran = Integer.parseInt(getParam("idPeringkatbayaran"));
//		    else
//	    		idPeringkatbayaran = 0;
			    
		    //
			Vector<?> v = FrmUtilData.getListBaucer(idPeringkatBayaran);  
			mylog.info("bayaranStatus:size="+v.size());
			this.context.put("bayaranStatus", v.size());
		    //System.out.println("cukaiBayaran::tambahBayaran::idPeringkatbayaran:::"+idPeringkatbayaran);			    
		    String id_baucer = getParam("socBaucer");
			if (id_baucer == null || id_baucer.trim().length() == 0){
	    		id_baucer = "0";
			}
	    	
			if("simpanBay".equals(pageMode)){
				mylog.info("CukaiProcess::Bayaran::simpanBayaran");
				readability = "readonly";
				disability = "disabled";
				style2 = "none";
				String idBayaran = simpanBayaran(session,idPeringkatBayaran);
				//System.out.println("CukaiProcess::Bayaran::simpanBay::socBaucer:xxxxxxxxx:"+getParam("socBaucer"));
				if(getParam("socBaucer") != ""){
					idBaucer = getParam("socBaucer");
				    //System.out.println("CukaiProcess::Bayaran::simpanBay::idBaucer::"+idBaucer);
				}else{
				  	idBaucer = getParam("idBaucer");
			    	//System.out.println("CukaiProcess::Bayaran::simpanBay::idBaucer::"+idBaucer);
				}
				bayaranView(session,String.valueOf(idNegeri),idBayaran,idPeringkatBayaran,disability,readability,style1,style2);
				this.context.put("idBayaranCukai", idBayaran);
				//this.context.put("peringkat_bayaran", peringkat_bayaran);
				//this.context.put("idPeringkatbayaran", idPeringkatbayaran);
				this.context.put("idBaucer", idBaucer);
				this.context.put("SimpanStatus", "success");
				this.context.put("ResultSimpan", result);
				//System.out.println("CukaiProcess::Bayaran::Simpan");
			
			}else if("baruBay".equals(pageMode)){
				mylog.info("CukaiProcess::Bayaran::Baru");
				//this.context.put("peringkat_bayaran", peringkat_bayaran);
				//this.context.put("idPeringkatbayaran", idPeringkatbayaran);
				viewBayaranBaru(session,idNegeri,disability,readability,style1,style2);
				//this.context.put("selectBaucer",UtilHTML.SelectBaucer(idPeringkatbayaran,"socBaucer",Long.parseLong(id_baucer),"onChange=\"doChangeBaucer();\"",null));
				this.context.put("selectBaucer",UtilHTML.SelectBaucer(idPeringkatBayaran,"socBaucer",Long.parseLong(id_baucer),"",null));
				list = FrmCukaiBayaranData.getAmaunBaucer(id_baucer);			
				if (list.size() == 0){
					Hashtable hash = new Hashtable();
					hash.put("amaun", "");
					hash.put("idBaucer", "");
					list.addElement(hash);
				}
				this.context.put("AmaunBaucer", list);
				//System.out.println("CukaiProcess::Bayaran::baru::list.size()::"+list.size());
				//System.out.println("CukaiProcess::Bayaran::baru::idBaucer::"+id_baucer);
				//System.out.println("CukaiProcess::Bayaran::baru::idNegeri::"+idNegeri);
			
			}else if("viewBay".equals(pageMode)){
				mylog.info("CukaiProcess::Bayaran::BayaranView");
				idBayaranCukai = Integer.parseInt(getParam("idBayaranCukai"));
				String idBayaran = getParam("idBayaranCukai");
				idBaucer = getParam("idBaucer");
				//this.context.put("idPeringkatbayaran", idPeringkatbayaran);
				this.context.put("idBayaranCukai", idBayaranCukai);
				this.context.put("idBaucer", idBaucer);
				style2 = "none";
				bayaranView(session,idNegeri,idBayaran,idPeringkatBayaran,disability,readability,style1,style2);
				//System.out.println("CukaiProcess::Bayaran::view::idBayaranCukai:::"+idBayaranCukai);
				//System.out.println("CukaiProcess::Bayaran::view::idBaucer:::"+idBaucer);
				
			} else if("hapusbayaran".equals(pageMode)){
			
			} 	
				
		}else{ 
			mylog.info("Default:Senarai Fail");
			template_name = PATH+"frmCukaiPeringkatBayar.jsp";	        
			iu = InternalUserUtil.getSeksyenId(idUser);
			idNegeri = iu.getIdNegeri();
			senaraiFail = FrmCukaiPenyataData.getList("11","","",Long.parseLong(String.valueOf(idNegeri)));
			if (action.indexOf("doChange") != -1) {
				senaraiFail = FrmCukaiPenyataData.getList("11","","",Long.parseLong(String.valueOf(idNegeri)));
				
		    }
			setupPage(session,action,senaraiFail);

		}
		
		mylog.info("idPermohonan="+idPermohonan);
		mylog.info("idPeringkatBayaran="+idPeringkatBayaran);
		mylog.info("peringkatBayar="+peringkatBayar);
		this.context.put("idPermohonan", idPermohonan);
	    this.context.put("idPeringkatbayaran", idPeringkatBayaran);
		this.context.put("peringkat_bayaran",peringkatBayar);
		this.context.put("peringkatbayar", peringkatBayar);		   
   		this.context.put("selectedTab",selectedTab);
		mylog.info("selectedTab = "+selectedTab);
   		this.context.put("statusPeringkatBayar",statusPeringkatBayar);
		
  		
		return template_name;
		
	}
	
	//_________create list penyata ______________________________________________________________________________________________________
	  private void DataPenyata(HttpSession session, int idNegeri, String disability, 
			  String readability, String style1, String style2) throws Exception{
		  vector = new Vector();
		  try{
			  //vector = FrmCukaiPenyataData.getListPenyata(idNegeri);
			  vector = getICukaiPenyata().getSenaraiPenyata(String.valueOf(idNegeri), year);
			  //int idPermohonan = Integer.parseInt(getParam("idPermohonan"));
			  //String negeri = getParam("negeri");
			  //System.out.println("CukaiProcess::DataPenyata::list.size():::"+list.size());
			  //System.out.println("penyataview::DataPenyata::idpermohonan:::"+idPermohonan);
			  //System.out.println("CukaiProcess::DataPenyata::negeri:::"+negeri);
			  //this.context.put("negeri", negeri);
			  this.context.put("ResultSimpan", "");
			  //____LIST PENYATA____________________________________________________________________________________________________________________________________________					
			  if(vector.size() != 0){			    
				  mylog.info("CukaiProcess::DataPenyata::list::"+vector);
				  this.context.put("Penyata", vector);
				  //this.context.put("idPermohonan", idPermohonan);
				  //this.context.put("negeri", negeri);
				  this.context.put("Style1", "");
				  this.context.put("Style2", style2);
				  
			  }else{
				  this.context.put("Penyata", "");
				  style1 = "none";
				  this.context.put("Style1", style1);
				  this.context.put("Style2", "");
				  
			  }
			  //this.context.put("negeri", negeri);
			
		  }catch(Exception ex){
				//System.out.println("CukaiProcess::DataPenyata::Exception:: = "+ex);
				ex.printStackTrace();
		  
		  }
	  }	  
	  
	  @SuppressWarnings("unchecked")
	  private void SimpanPenyata(HttpSession session , int idPermohonan) throws Exception {
		  	Vector list = new Vector();
			list.clear();
			int idPeringkatbayaran = Integer.parseInt(getParam("idPeringkatbayaran"));
			int idNegeri = Integer.parseInt(getParam("idNegeri"));	
			String negeri = getParam("negeri");
			System.out.println("CukaiProcess::DataPenyata::negeri:::"+negeri);
			this.context.put("negeri", negeri);
	    	int tahun_cukai = Integer.parseInt(getParam("tahun_cukai"));
	    	String idBayaran = getParam("socbayaran");
	    	list = FrmCukaiPenyataData.getListPenyata(idNegeri);
			System.out.println("CukaiProcess::SimpanPenyata::list.size()::: "+list.size());
				
			if(idPeringkatbayaran == 0){
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
				result = "baru";
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
				result = "kemaskini";
			}
		}
	  
		private void simpanPenyata(HttpSession session ,String idPermohonan) throws Exception {
		  	vector = new Vector();
			//list.clear();
			//int idPeringkatbayaran = Integer.parseInt(getParam("idPeringkatbayaran"));
			//int idNegeri = Integer.parseInt(getParam("idNegeri"));	
			int idPeringkatbayaran = idPeringkatBayaran==""?0:Integer.parseInt(idPeringkatBayaran);
		    int idNegeriInt = Integer.parseInt(idNegeri);		

			//String negeri = getParam("negeri");
			//this.context.put("negeri", negeri);
	    	int tahun_cukai = Integer.parseInt(getParam("tahun_cukai"));
	    	String idBayaran = getParam("socbayaran");
	    	vector = FrmCukaiPenyataData.getListPenyata(idNegeriInt);
				
			if(idPeringkatbayaran == 0){
				Hashtable h = new Hashtable();	
				h.put("idpermohonan", idPermohonan);						
				h.put("idNegeri", idNegeriInt);
				h.put("tahun_cukai", tahun_cukai);						
				h.put("peringkat_bayaran", idBayaran);		    
				h.put("idMasuk", idUser);
				idPeringkatbayaran = FrmCukaiPenyataData.simpanPeringkatBayarInteger(h);						
				//list = FrmCukaiPenyataData.getListPenyata(idNegeri);
				mylog.info("CukaiProcess::simpanPeringkatBayar::idNegeri::"+idNegeri);						
				mylog.info("CukaiProcess::simpanPeringkatBayar::tahun_cukai::"+tahun_cukai);
				mylog.info("CukaiProcess::simpanPeringkatBayar::peringkat_bayaran::"+idBayaran);						
				mylog.info("CukaiProcess::simpanPeringkatBayar:: h = "+h);
				mylog.info("CukaiProcess::SimpanPenyata::idPeringkatbayaran::"+idPeringkatbayaran);						
						
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
					mylog.info("CukaiProcess::simpanCukaiUtama::idCukaiUtama::"+idPeringkatbayaran);
					mylog.info("CukaiProcess::simpanCukaiUtama::idPeringkat::"+idPeringkatbayaran);
					mylog.info("CukaiProcess::simpanCukaiUtama::tahun::"+tahun_cukai);						
					mylog.info("CukaiProcess::simpanCukaiUtama::idNegeri::"+idNegeri);
					mylog.info("CukaiProcess::simpanCukaiUtama:: hash = "+hash);
					FrmCukaiPenyataData.simpanCukaiUtama(hash);
					
				}
				result = "baru";
				
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
				mylog.info("CukaiProcess::kemaskiniPeringkatBayar::idNegeri::"+idNegeri);
				mylog.info("CukaiProcess::kemaskiniPeringkatBayar::tahun_cukai::"+tahun_cukai);
				mylog.info("CukaiProcess::kemaskiniPeringkatBayar::peringkat_bayaran::"+idBayaran);						
				mylog.info("CukaiProcess::kemaskiniPeringkatBayar:: h = "+h);
				mylog.info("CukaiProcess::KemaskiniPenyata::idPeringkatbayaran::"+idPeringkatbayaran);
												
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
					mylog.info("CukaiProcess::simpanCukaiUtama::idCukaiUtama::"+idPeringkatbayaran);
					mylog.info("CukaiProcess::simpanCukaiUtama::jum_cukai::"+sumCukai);
					mylog.info("CukaiProcess::simpanCukaiUtama::jum_hakmilik::"+sumIdHakmilik);
					mylog.info("CukaiProcess::simpanCukaiUtama::idPeringkat::"+idPeringkatbayaran);						
					mylog.info("CukaiProcess::simpanCukaiUtama::tahun::"+tahun_cukai);
					mylog.info("CukaiProcess::simpanCukaiUtama::idNegeri::"+idNegeri);	
					mylog.info("CukaiProcess::kemaskiniCukaiUtama:: hash = "+hash);
					FrmCukaiPenyataData.updateCukaiUtama(hash);
					
				}				
				result = "kemaskini";
			
			}
			
		}	 
		
		//27/10/2010
		private void simpanPenyata(HttpSession session,String idPermohonan,Vector vector) throws Exception {
		  	//vector = new Vector();
			//list.clear();
			//int idPeringkatbayaran = Integer.parseInt(getParam("idPeringkatbayaran"));
			//int idNegeri = Integer.parseInt(getParam("idNegeri"));	
			int idPeringkatbayaran = idPeringkatBayaran==""?0:Integer.parseInt(idPeringkatBayaran);
		    int idNegeriInt = Integer.parseInt(idNegeri);		

			//String negeri = getParam("negeri");
			//this.context.put("negeri", negeri);
	    	int tahun_cukai = Integer.parseInt(getParam("tahun_cukai"));
	    	String idBayaran = getParam("socbayaran");
	    	//vector = FrmCukaiPenyataData.getListPenyata(idNegeriInt);
				
			if(idPeringkatbayaran == 0){
				Hashtable h = new Hashtable();	
				h.put("idpermohonan", idPermohonan);						
				h.put("idNegeri", idNegeriInt);
				h.put("tahun_cukai", tahun_cukai);						
				h.put("peringkat_bayaran", idBayaran);		    
				h.put("idMasuk", idUser);
				idPeringkatbayaran = FrmCukaiPenyataData.simpanPeringkatBayarInteger(h);						
				//list = FrmCukaiPenyataData.getListPenyata(idNegeri);
				mylog.info("CukaiProcess::simpanPeringkatBayar::idNegeri::"+idNegeri);						
				mylog.info("CukaiProcess::simpanPeringkatBayar::tahun_cukai::"+tahun_cukai);
				mylog.info("CukaiProcess::simpanPeringkatBayar::peringkat_bayaran::"+idBayaran);						
				mylog.info("CukaiProcess::simpanPeringkatBayar:: h = "+h);
				mylog.info("CukaiProcess::SimpanPenyata::idPeringkatbayaran::"+idPeringkatbayaran);						
						
				for(int i=0; i<vector.size(); i++){							
					Hashtable input = (Hashtable)vector.get(i);
					Hashtable hash = new Hashtable();
					hash.put("idCukaiUtama",idPeringkatbayaran);					
					hash.put("jum_cukai", input.get("sumCukai"));						
					hash.put("jum_hakmilik",(Integer)input.get("sumIdHakmilik"));							
					hash.put("idPeringkat",idPeringkatbayaran);							
					hash.put("tahun",tahun_cukai);
					hash.put("idNegeri",idNegeriInt);					
					hash.put("idDaerah",(String)input.get("idDaerah"));							
					hash.put("idMasuk", idUser);
					mylog.info("CukaiProcess::simpanCukaiUtama::idCukaiUtama::"+idPeringkatbayaran);
					mylog.info("CukaiProcess::simpanCukaiUtama::idPeringkat::"+idPeringkatbayaran);
					mylog.info("CukaiProcess::simpanCukaiUtama::tahun::"+tahun_cukai);						
					mylog.info("CukaiProcess::simpanCukaiUtama::idNegeri::"+idNegeri);
					mylog.info("CukaiProcess::simpanCukaiUtama:: hash = "+hash);
					FrmCukaiPenyataData.simpanCukaiUtama(hash);
					
				}
				result = "baru";
				
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
				mylog.info("CukaiProcess::kemaskiniPeringkatBayar::idNegeri::"+idNegeri);
				mylog.info("CukaiProcess::kemaskiniPeringkatBayar::tahun_cukai::"+tahun_cukai);
				mylog.info("CukaiProcess::kemaskiniPeringkatBayar::peringkat_bayaran::"+idBayaran);						
				mylog.info("CukaiProcess::kemaskiniPeringkatBayar:: h = "+h);
				mylog.info("CukaiProcess::KemaskiniPenyata::idPeringkatbayaran::"+idPeringkatbayaran);
												
				for(int i=0; i<vector.size(); i++){							
					Hashtable input = (Hashtable)vector.get(0);
					Hashtable hash = new Hashtable();				
					hash.put("idCukaiUtama",idPeringkatbayaran);					
					hash.put("jum_cukai", input.get("sumCukai"));
					hash.put("jum_hakmilik", Integer.parseInt(getParam("sumIdHakmilik")));						
					hash.put("idPeringkat",idPeringkatbayaran);							
					hash.put("tahun",tahun_cukai);							
					hash.put("idNegeri",idNegeriInt);				
					hash.put("idDaerah",(String)input.get("idDaerah"));							
					hash.put("idKemaskini", idUser);
					mylog.info("CukaiProcess::simpanCukaiUtama::idCukaiUtama::"+idPeringkatbayaran);
					mylog.info("CukaiProcess::simpanCukaiUtama::jum_cukai::"+sumCukai);
					mylog.info("CukaiProcess::simpanCukaiUtama::jum_hakmilik::"+sumIdHakmilik);
					mylog.info("CukaiProcess::simpanCukaiUtama::idPeringkat::"+idPeringkatbayaran);						
					mylog.info("CukaiProcess::simpanCukaiUtama::tahun::"+tahun_cukai);
					mylog.info("CukaiProcess::simpanCukaiUtama::idNegeri::"+idNegeri);	
					mylog.info("CukaiProcess::kemaskiniCukaiUtama:: hash = "+hash);
					FrmCukaiPenyataData.updateCukaiUtama(hash);
					
				}				
				result = "kemaskini";
			
			}
			
		}	  
		
	  
		//Baucer
	  
	  private void ViewBaucer(HttpSession session, int idNegeri, int idPeringkatbayaran, int peringkat_bayaran, String disability, String readability, String style1, String style2) throws Exception
		{
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

	  private void viewBaucer(HttpSession session, String idNegeri, String idPeringkatbayaran, String peringkat_bayaran, String disability, String readability, String style1, String style2) throws Exception
		{
		  	Vector list = new Vector();
			list.clear();
//___list baucer_________________________________________________________________________________________________________________					    
			try{
				//list = FrmCukaiBaucerData.getListBaucer(idNegeri,idPeringkatbayaran,peringkat_bayaran);
				list = getICukai().getSenaraiBaucer(idNegeri, idPeringkatbayaran, peringkat_bayaran);
				System.out.println("CukaiProcess::ViewBaucer::list.size():::"+list.size());
				this.context.put("ResultSimpan", "");
				
				if(list.size() != 0){			    
					this.context.put("Baucer", list);
					System.out.println("CukaiProcess::ViewBaucer::list::"+list);
					Hashtable bau = (Hashtable) list.get(0);
					this.context.put("idPeringkatbayaran", bau.get("idPeringkatbayaran"));
					this.context.put("idDaerah", bau.get("idDaerah"));
					this.context.put("selectDaerah",utilHTML.SelectDaerahByCukai(idPeringkatBayaran,"socDaerah",null,"disabled",null));
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
	  
	  private String SimpanTBaucer(HttpSession session,int idPeringkatbayaran) throws Exception {
		  String idBaucer = "0";
		  //System.out.println("CukaiProcess::SimpanTBaucer:::idBaucer::"+getParam("idBaucer"));
		  if(getParam("idBaucer") == ""){
			  //baucer baru
			  //System.out.println("CukaiProcess::SimpanTBaucer::baru");
			  Hashtable h = new Hashtable();
			  h.put("idPeringkatbayaran", idPeringkatbayaran);
			  h.put("socDaerah", Integer.parseInt(getParam("socDaerah")));
			  //System.out.println(Integer.parseInt(getParam("socDaerah")));
			  h.put("tkh_baucer", getParam("txdTarikhBaucer"));
			  //System.out.println("tarikh baucer " + getParam("txdTarikhBaucer"));
			  h.put("no_resit", getParam("txtNoCek"));
			  h.put("no_baucer", getParam("txtNoBaucer"));
			  //System.out.println("No baucer " + getParam("txtNoBaucer"));
			  h.put("tkh_resit", getParam("txdTarikhCek"));
			  h.put("amaun_baucer", getParam("txtAmaunBaucer"));
			  //System.out.println("Amaun : " + getParam("txtAmaunBaucer"));
			  h.put("tkh_terima", getParam("txdTarikhTerima"));
			  h.put("idNegeri", Integer.parseInt(getParam("idNegeri")));
			  //System.out.println("CukaiTambahBaucer::SimpanBaucer:: h = "+h);
			  idBaucer = FrmCukaiBaucerData.simpanTBaucer(h);
			  result = "baru";
			  //System.out.println("CukaiTambahBaucer::SimpanBaucer:: h = "+h);
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
			FrmCukaiBaucerData.updateTBaucer(h);
			result = "kemaskini";
			//System.out.println("CukaiTambahBaucer::KemaskiniBaucer:: h = "+h);
			return idBaucer;
		}
	}
		
	private void DataTBaucer(HttpSession session,int idNegeri, String idBaucer, int idPeringkatbayaran, String disability, String readability, String style1, String style2) throws Exception {
	  	Vector list = new Vector();
		list.clear();					    
		try{
			list = FrmCukaiBaucerData.getListTBaucer(idNegeri,idBaucer,idPeringkatbayaran);
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
			mylog.info("CukaiProcess::DataTBaucer::Exception:: = "+ex);
			ex.printStackTrace();

		}

	}
		
	private void DataTBaucerBaru(HttpSession session, int idNegeri, String disability, String readability, String style1, String style2) throws Exception {
		Vector list = new Vector();
		list.clear();
		
		try{
			this.context.put("ResultSimpan", "");
			this.context.put("Baucer", "");
			//System.out.println("CukaiProcess::DataTBaucerBaru::idNegeri:::"+idNegeri);
			//System.out.println("CukaiProcess::DataTBaucerBaru::list.size() = "+list.size());
			style1 = "none";
			this.context.put("Style1", style1);
			this.context.put("Style2", "");

		}catch(Exception ex){
				mylog.info("FrmCukai::DataTBaucerBaru::Exception:: = "+ex);
				ex.printStackTrace();
		
		}
		
	}
		
	private void ViewBayaran(HttpSession session, int idNegeri,String disability, String readability, String style1, String style2) throws Exception {
		Vector list = new Vector();
		list.clear();
		
		try{

			list = FrmCukaiBayaranData.getListBayaran(idNegeri);
//			System.out.println("=================ViewBayaran");
//			System.out.println("CukaiProcess::ViewBayaran::list.size():::"+list.size());
			this.context.put("ResultSimpan", "");
				
			if(list.size() != 0){			    
				this.context.put("Bayaran", list);
				//System.out.println("CukaiProcess::ViewBayaran::list::"+list);
				Hashtable bay = (Hashtable) list.get(0);
				this.context.put("idPeringkatbayaran", bay.get("idPeringkatbayaran"));
				this.context.put("idDaerah", bay.get("idDaerah"));
				this.context.put("id_bayarancukai", bay.get("id_bayarancukai"));
				this.context.put("selectBaucer",UtilHTML.SelectBaucer(Integer.parseInt(bay.get("idPeringkatbayaran").toString()),"socBaucer",null,"disabled",null));
//					System.out.println("ViewBayaran::idPeringkatbayaran:::"+bay.get("idPeringkatbayaran"));
//					System.out.println("ViewBayaran::idDaerah:::"+bay.get("idDaerah"));
//					System.out.println("ViewBayaran::id_bayarancukai:::"+bay.get("id_bayarancukai"));
				this.context.put("Style1", style1);
				this.context.put("Style2", style2);
			}else{			
				this.context.put("Bayaran", "");
			    style1 = "none";
			    this.context.put("Style1", style1);
			    this.context.put("Style2", "");
			}
		}catch(Exception ex){
			mylog.info("FrmCukai::ViewBayaran::Exception:: = "+ex);
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
			result = "baru";
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
			result = "kemaskini";
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
			result = "baru";
			//System.out.println("CukaiProcess::SimpanBayaran::idBayaranCukai::"+idBayaranCukai);
			//System.out.println("CukaiTambahBayaran::SimpanBayaran:: h = "+h);
			return String.valueOf(idBayaranCukai);
		}else{
			//kemaskini bayaran
			//System.out.println("CukaiProcess::SimpanTBayaran:::kemaskini::idBayaranCukai::"+getParam("idBayaranCukai"));
			//System.out.println("CukaiProcess::SimpanTBayaran::kemaskini");
			Hashtable h = new Hashtable();
			idBayaranCukai = Integer.parseInt(getParam("idBayaranCukai"));
			h.put("idBayaranCukai", idBayaranCukai);
			h.put("idPeringkatbayaran", idPeringkatbayaran);
			h.put("idBaucer", getParam("idBaucer"));
			h.put("tkh_bayaran", getParam("txdTarikhBayaran"));
			h.put("nama_bank", getParam("txtNamaBank"));
			h.put("amaunBayaran", getParam("txtAmaun"));
			h.put("noRujBayaran", getParam("txtNoRujBayaran"));
			//h.put("noCek", getParam("txtNoCek"));
			h.put("noResit", getParam("txtNoResit"));
			h.put("tkh_resit", getParam("txdTarikhResit"));
			FrmCukaiBayaranData.updateTBayaran(h);
			result = "kemaskini";
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
			mylog.info("FrmCukai::DataTBayaran::Exception:: = "+ex);
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
			mylog.info("FrmCukai::DataTBayaran::Exception:: = "+ex);
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
			mylog.info("FrmCukai::DataTBayaranBaru::Exception = "+ex);
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
			mylog.info("FrmCukai::DataTBayaranBaru::Exception = "+ex);
			
		}
	}
	
	private ICukai getICukai(){
		if(iCukai==null){
			iCukai = new CukaiBean();
		}
		return iCukai;
		
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
	
	private ICukaiPenyata getICukaiPenyata(){
		if(iCukaiPen==null){
			iCukaiPen = new CukaiPenyataBean();
		}
		return iCukaiPen;
		
	}
		
}
