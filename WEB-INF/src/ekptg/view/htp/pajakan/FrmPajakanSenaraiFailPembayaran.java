/**
 * 
 */
package ekptg.view.htp.pajakan;

import java.util.Date;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import lebah.util.Util;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.htp.FrmPajakanHeaderData;
import ekptg.model.htp.FrmPajakanSenaraiFailData;
import ekptg.model.htp.HakmilikUrusan;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHtp;
import ekptg.model.htp.entity.HtpPermohonan;
import ekptg.model.htp.entity.MaklumatBil;
import ekptg.model.htp.entity.Pajakan;
import ekptg.model.htp.entity.PajakanKadar;
import ekptg.model.htp.entity.Permohonan;
import ekptg.model.htp.entity.PfdFail;
import ekptg.model.htp.pajakan.IPajakanUtama;
import ekptg.model.htp.pajakan.PajakanUtamaBean;
import ekptg.model.htp.pembelian.IPembelian;
import ekptg.model.htp.pembelian.PembelianBean;

/**
 * 
 *
 */
public class FrmPajakanSenaraiFailPembayaran extends AjaxBasedModule {

	private final String PATH="app/htp/pajakan/";
	private final String JENISTANAH = "2,4";	//TR-TANAH RIZAB,TM-TANAH MILIK
	private static final long serialVersionUID = 1L;	
	FrmPajakanHeaderData logicHeader = new FrmPajakanHeaderData();
	FrmPajakanSenaraiFailData logic = new FrmPajakanSenaraiFailData();
	private Pajakan pajakan = null;
	private PajakanKadar pajakanKadar = null;
	private Permohonan permohonan = null;
	private PfdFail fail = null;
	private static Logger myLog = Logger.getLogger(ekptg.view.htp.pajakan.FrmPajakanSenaraiFailPembayaran.class);
	//private Permohonan permohonan = null;
	private HakmilikUrusan urusan = null;
	private IPembelian iPembelian = null;
	private HtpBean iHtp = null;
	private HtpPermohonan htpPermohonan = null;
	private IPajakanUtama iPajakanUtama = null;
	String flagAdvSearch = "";
	private String tahunSemasa = "";
	private String readonly = "disabled class = \"disabled\"";
	private Vector senaraiBayaran = null;
	private MaklumatBil mBil = null;
	@Override
	public String doTemplate2() throws Exception {

		HttpSession session = this.request.getSession();		
		Boolean postDB = false;
		String doPost =  session.getAttribute("doPost").toString();
	    if (doPost.equals("true")) {
	        postDB = true;
	    }
	    
	    //GET DEFAULT PARAM
	    String action = getParam("action"); //* ACTION NI HANYA UTK SETUP PAGING SHJ
	    String vm = ""; 
        String actionPajakan = getParam("actionPajakan");
        String submit = getParam("command");   
        String mode = getParam("mode");
        String hitButton = getParam("hitButton");
        
        //GET ID PARAM
        String idFail = getParam("idFail");
        String idPermohonan = getParam("idPermohonan");
        String idStatus = getParam("idStatus");
        String subUrusan = getParam("subUrusan");
        String idHakmilikUrusan = getParam("idHakmilikUrusan");
        
        //VECTOR
        Vector beanHeader = null;
        Vector list = null;
        Vector beanMaklumatPermohonan = null;
        Vector senaraiHakmilik = null;
        
        //GET DROPDOWN PARAM
        String idNegeri = getParam("socNegeri");
		if (idNegeri == null || idNegeri.trim().length() == 0){
			idNegeri = "99999";
		}
		String idKementerian = getParam("socKementerian");
		if (idKementerian == null || idKementerian.trim().length() == 0){
			idKementerian = "99999";
		}
		String idAgensi = getParam("socAgensi");
		if (idAgensi == null || idAgensi.trim().length() == 0){
			idAgensi = "99999";
		}
		String idSuburusan = getParam("socSuburusan");
		if (idSuburusan == null || idSuburusan.trim().length() == 0){
			idSuburusan = "99999";
		}
		String idStatusTanah = getParam("socStatusTanah");
		if (idStatusTanah == null || idStatusTanah.trim().length() == 0){
			idStatusTanah = "99999";
		}
		String idJenisFail = getParam("socJenisFail");
		if (idJenisFail == null || idJenisFail.trim().length() == 0){
			idJenisFail = "99999";
		}
		myLog.info("actionPajakan:"+actionPajakan);
		myLog.info("idFail:"+idFail);
		myLog.info("mode:"+mode);
		
		//18/08/2010
		flagAdvSearch = getParam("flagAdvSearch");
        //18/01/2011
		String selectedTab = getParam("selectedTab");
    	 if(selectedTab.equals("") || selectedTab.equals(null)){
         	selectedTab = "0";
      	 }

		//HITBUTTON
//		if (postDB){
//    		if ("simpan".equals(hitButton)){
//         		idFail = logic.simpan(idNegeri, idKementerian, idAgensi, idSuburusan, idStatusTanah, idJenisFail, getParam("txtNoFailKJP"), 
//         					getParam("tarikhSuratKJP"), getParam("txtNoFailLain"), getParam("tarikhAgihan"), getParam("txtTajuk"),getParam("tarikhSuratPemohon"), session);
//         		session.setAttribute("idFail", idFail);
//        	}
//        	if ("simpanbyswasta".equals(hitButton)){
//         		idFail = logic.simpan(idPermohonan,idNegeri, idKementerian, idAgensi, idSuburusan, idStatusTanah, idJenisFail, getParam("txtNoFailKJP"), 
//         					getParam("tarikhSuratKJP"), getParam("txtNoFailLain"), getParam("tarikhAgihan"), getParam("txtTajuk"),getParam("tarikhSuratPemohon"), session);
//         		session.setAttribute("idFail", idFail);
//        	}
//    	}
	       	beanHeader = new Vector();
	        logicHeader.setMaklumatPermohonan(idFail);
	        beanHeader = logicHeader.getBeanMaklumatPermohonan();
			this.context.put("BeanHeader", beanHeader);
	        Util util = new Util();
			this.context.put("lutil", util);

		
		if ("papar".equals(submit)){
        	vm = PATH+"bayaran/setup/frmPajakanTabAkaun.jsp";
        	
        	if (selectedTab.equals("0")){
        		
        		if(mode.equals("newmaklumatbayaran")){
        			myLog.info("newmaklumatbayaran:idPermohonan="+idPermohonan);    			
            		pajakan = getPU().getMaklumatPajakan(idPermohonan);
            		readonly = "";
        			senaraiBayaran =  (Vector)getPU().getPajakanKadar(idPermohonan) ;
        			if(!senaraiBayaran.isEmpty()){
        				pajakan = getPU().getMaklumatPajakanKadarPermohonan(idPermohonan);
        			}
            		//pajakan.getTarikhTamatPajakan()
            		//pajakan.getTempohPajakan()
//        			Vector senaraiPajakan = new Vector();
//        			logic.setListPajakan(idPermohonan);
//        			senaraiPajakan = logic.getSenaraiPajakan();
//        			this.context.put("SenaraiPajakan", senaraiPajakan);   		
//        			PajakanView(mode, idPajakan);
        		}else if(mode.equals("simpanMaklumatBayaran")){	
        			getMaklumatKadarValues(idPermohonan);	
        			pajakanKadar = getPU().saveMaklumatKadar(pajakanKadar, session);
        			//htpPermohonan = getIPembelian().simpanPermohonan(htpPermohonan);

        			senaraiBayaran =  (Vector)getPU().getPajakanKadar(idPermohonan) ;
        			mode = "viewmaklumatbayaran";
        			
        		}else if (mode.equalsIgnoreCase("viewmaklumatbayaran")){
        			String idPajakanKadar = getParam("idpajakankadar");
        			pajakanKadar = getPU().getPajakanKadarI(idPajakanKadar);
        			//myLog.info(""+ pajakanKadar.getPajakan().getIdPajakan());
        			pajakan = new Pajakan();
        			pajakan.setIdPajakan( pajakanKadar.getPajakan().getIdPajakan());
        			
        			//myLog.info(""+ pajakanKadar.getPajakan().getTarikhMulaPajakan());
    				pajakan.setTarikhMulaPajakan( pajakanKadar.getPajakan().getTarikhMulaPajakan());
    				pajakan.setTarikhTamatPajakan(pajakanKadar.getPajakan().getTarikhTamatPajakan());
    				pajakan.setTempohPajakan(pajakanKadar.getPajakan().getTempohPajakan());
    				pajakan.setKadarCukai(pajakanKadar.getPajakan().getKadarCukai());
    				pajakan.setKadarPajakan(pajakanKadar.getPajakan().getKadarPajakan());
        			context.put("pajakanKadar", pajakanKadar);
        			
        			senaraiBayaran =  (Vector)getPU().getPajakanKadar(idPermohonan) ;

            		
        		}else if (mode.equalsIgnoreCase("updatemaklumatbayaran")){
        			getMaklumatKadarValues(idPermohonan);	       			
        			senaraiBayaran =  (Vector)getPU().getPajakanKadar(idPermohonan) ;
            		readonly = "";
            		
        		}else if (mode.equalsIgnoreCase("saveupdatemaklumatbayaran")){
        			getMaklumatKadarValues(idPermohonan);	       			
        			pajakanKadar = getPU().saveUpdateMaklumatKadar(pajakanKadar, session);
        			senaraiBayaran =  (Vector)getPU().getPajakanKadar(idPermohonan) ;
            		readonly = "";
        			mode = "viewmaklumatbayaran";
        		
        		}else if (mode.equalsIgnoreCase("deletemaklumatbayaran")){
            			getMaklumatKadarValues(idPermohonan);	       			
            			pajakanKadar = getPU().deleteMaklumatKadar(pajakanKadar);
            			senaraiBayaran =  (Vector)getPU().getPajakanKadar(idPermohonan) ;
                		//readonly = "";
            			//mode = "viewmaklumatbayaran";
            			mode = "view";
               		
        		}else{
        			senaraiBayaran =  (Vector)getPU().getPajakanKadar(idPermohonan) ;
        				
        			//mode = "view";
       			
        		}
    			context.put("senaraiBayaran", senaraiBayaran);
    			context.put("pajakan", pajakan);
    			
			}else if (selectedTab.equals("1")){
       			myLog.info("selectedTab=1:idPermohonan="+idPermohonan);    			
        		pajakan = getPU().getMaklumatPajakan(idPermohonan);
				
        		if(mode.equalsIgnoreCase("newmaklumatbil")){
        			myLog.info("newmaklumatbil:idPermohonan="+idPermohonan);    			
//            		pajakan = getPU().getMaklumatPajakan(idPermohonan);
//        			senaraiBayaran =  (Vector)getPU().getPajakanKadar(idPermohonan) ;
//        			if(!senaraiBayaran.isEmpty()){
//        				pajakan = getPU().getMaklumatPajakanKadarPermohonan(idPermohonan);
//        			}
            		tahunSemasa = lebah.util.Util.getDateTime(new Date(), "yyyy");
            		pajakan = getPU().getMaklumatPajakan(idPermohonan);
            		String tahunMula = lebah.util.Util.getDateTime(pajakan.getTarikhMulaPajakan(), "yyyy");
            		readonly = "";
            		double baki =0.0;
            		double denda =0.0;
         			if (hitButton.contains("change")){
         				String tahunBil = getParam("Form_tahun");
        				myLog.info("tahunBil="+tahunBil);
        				myLog.info("Id Pajakan="+pajakan.getIdPajakan());
        				pajakanKadar = getPU().getPajakanKadarI(String.valueOf(pajakan.getIdPajakan()), tahunBil);
            			if(pajakanKadar!=null){
            				mBil = new MaklumatBil();
            				pajakan.setKadarCukai(pajakanKadar.getPajakan().getKadarCukai());
            				pajakan.setKadarPajakan(pajakanKadar.getPajakan().getKadarPajakan());
            				mBil.setPajakan(pajakan);
            				//mBil.setBaki(pajakanKadar.g);
            			//}else{
            			
            			}
        				//getMaklumatKadarValues(idPermohonan);	       			
                		//pajakanKadar = getPU().deleteMaklumatKadar(pajakanKadar);
                		//senaraiBayaran =  (Vector)getPU().getPajakanKadar(idPermohonan) ;
                		//mode = "view";
                		context.put("tahun_cukai", Integer.parseInt(tahunBil));
                		context.put("mbil", mBil);
        			}

            		context.put("tahunSemasa", Integer.parseInt(tahunSemasa));
        			context.put("pUtama", pajakan);
            		context.put("tahunMula", Integer.parseInt(tahunMula));
 
        		}else if(mode.equalsIgnoreCase("simpanmaklumatbil")){	
        			getMaklumatKadarValues(idPermohonan);	
        			pajakanKadar = getPU().saveMaklumatKadar(pajakanKadar, session);
 
        			senaraiBayaran =  (Vector)getPU().getPajakanKadar(idPermohonan) ;
        			mode = "viewmaklumatbayaran";
        			
        		}else if (mode.equalsIgnoreCase("viewmaklumatbil")){
        			String idPajakanKadar = getParam("idpajakankadar");
        			pajakanKadar = getPU().getPajakanKadarI(idPajakanKadar);
        			//myLog.info(""+ pajakanKadar.getPajakan().getIdPajakan());
        			pajakan = new Pajakan();
        			pajakan.setIdPajakan( pajakanKadar.getPajakan().getIdPajakan());
        			
        			//myLog.info(""+ pajakanKadar.getPajakan().getTarikhMulaPajakan());
    				pajakan.setTarikhMulaPajakan( pajakanKadar.getPajakan().getTarikhMulaPajakan());
    				pajakan.setTarikhTamatPajakan(pajakanKadar.getPajakan().getTarikhTamatPajakan());
    				pajakan.setTempohPajakan(pajakanKadar.getPajakan().getTempohPajakan());
    				pajakan.setKadarCukai(pajakanKadar.getPajakan().getKadarCukai());
    				pajakan.setKadarPajakan(pajakanKadar.getPajakan().getKadarPajakan());
        			context.put("pajakanKadar", pajakanKadar);
        			
        			senaraiBayaran =  (Vector)getPU().getPajakanKadar(idPermohonan) ;
           		
        		}else if (mode.equalsIgnoreCase("updatemaklumatbil")){
        			getMaklumatKadarValues(idPermohonan);	       			
        			senaraiBayaran =  (Vector)getPU().getPajakanKadar(idPermohonan) ;
            		readonly = "";
            		
        		}else if (mode.equalsIgnoreCase("saveupdatemaklumatbil")){
        			getMaklumatKadarValues(idPermohonan);	       			
        			pajakanKadar = getPU().saveUpdateMaklumatKadar(pajakanKadar, session);
        			senaraiBayaran =  (Vector)getPU().getPajakanKadar(idPermohonan) ;
            		readonly = "";
        			mode = "viewmaklumatbayaran";
        		
        		}else if (mode.equalsIgnoreCase("deletemaklumatbil")){
        			getMaklumatKadarValues(idPermohonan);	       			
            		pajakanKadar = getPU().deleteMaklumatKadar(pajakanKadar);
            		senaraiBayaran =  (Vector)getPU().getPajakanKadar(idPermohonan) ;
            		mode = "view";
               		
        		}else{
        			//senaraiBayaran =  (Vector)getPU().getPajakanKadar(idPermohonan) ;
   
        		}
    			context.put("senaraiBayaran", senaraiBayaran);
    			context.put("pajakan", pajakan);				
				
			}
        	
		}else if("daftar".equals(submit)){	
        	vm = PATH+"bayaran/setup/frmMaklumatBil.jsp";

    		tahunSemasa = lebah.util.Util.getDateTime(new Date(), "yyyy");
    		pajakan = getPU().getMaklumatPajakan(idPermohonan);
    		String tahunMula = lebah.util.Util.getDateTime(pajakan.getTarikhMulaPajakan(), "yyyy");

    		context.put("tahunSemasa", Integer.parseInt(tahunSemasa));
			context.put("pUtama", pajakan);
    		context.put("tahunMula", Integer.parseInt(tahunMula));

			//			//GO TO PAPAR PAJAKAN  
//			logicHeader.setMaklumatPermohonan(idFail);
//			beanMaklumatPermohonan = new Vector();
//
//    		beanMaklumatPermohonan = logicHeader.getBeanMaklumatPermohonan();
//			Hashtable hashHeader = (Hashtable) logicHeader.getBeanMaklumatPermohonan().get(0);
//			/*hashPermohonan.put("noFail", "");
//			hashPermohonan.put("noFailKJP", getParam("txtNoFailKJP") == null ? "" : getParam("txtNoFailKJP"));
//			hashPermohonan.put("tarikhSuratKJP", getParam("tarikhSuratKJP") == null ? "" : getParam("tarikhSuratKJP"));
//			hashPermohonan.put("noFailLain", getParam("txtNoFailLain") == null ? "" : getParam("txtNoFailLain"));
//			hashPermohonan.put("tarikhAgihan", getParam("tarikhAgihan") == null ? "" : getParam("tarikhAgihan"));
//			hashPermohonan.put("tajuk", getParam("txtTajuk") == null ? "" : getParam("txtTajuk"));
//			hashPermohonan.put("tarikhSuratPemohon", getParam("tarikhSuratPemohon") == null ? "" : getParam("tarikhSuratPemohon"));
//			beanMaklumatPermohonan.addElement(hashPermohonan);
//			this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);
//	*/		
//			this.context.put("selectNegeri",HTML.SelectNegeriByMahkamah("socNegeri", Long.parseLong(idNegeri), " disabled class=disabled", ""));
//			this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), " disabled class=disabled", " onChange=\"doChangeKementerian();\""));
//			this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian ,Long.parseLong(idAgensi), " disabled class=disabled", ""));
//			this.context.put("selectSuburusan",HTML.SelectSuburusanByIdUrusan("3", "socSuburusan" ,Long.parseLong(idSuburusan), " disabled class=disabled", ""));
//			this.context.put("selectStatusTanah",HTML.SelectJenisTanah("socStatusTanah" ,Long.parseLong(idStatusTanah), " disabled class=disabled", ""));
//			this.context.put("selectJenisFail",HTML.SelectTarafKeselamatan("socJenisFail" ,Long.parseLong(idJenisFail), " disabled class=disabled", ""));
//
//    		
//        	this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);
//        	MaklumatPermohonanView("view");
//        	this.context.put("mode", mode);
//	        	
//		} else if ("daftarBaru".equals(actionPajakan)){  //ACTION PAJAKAN     	
//		//if ("daftarBaru".equals(submit)){       	
//        	//GO TO DAFTAR BARU PAJAKAN   
//        	//vm = PATH+"frmPajakanPenerimaanPermohonanDaftar.jsp";
//        	vm = PATH+"frmDaftar.jsp";
//        	
//        	this.context.put("mode", "new");
//        	this.context.put("readonly", "");
//        	this.context.put("inputTextClass", "");
//        	
//        	//MAKLUMAT PERMOHONAN
//        	beanMaklumatPermohonan = new Vector();
//			Hashtable hashPermohonan = new Hashtable();
//			hashPermohonan.put("noFail", "");
//			hashPermohonan.put("noFailKJP", getParam("txtNoFailKJP") == null ? "" : getParam("txtNoFailKJP"));
//			hashPermohonan.put("tarikhSuratKJP", getParam("tarikhSuratKJP") == null ? "" : getParam("tarikhSuratKJP"));
//			hashPermohonan.put("noFailLain", getParam("txtNoFailLain") == null ? "" : getParam("txtNoFailLain"));
//			hashPermohonan.put("tarikhAgihan", getParam("tarikhAgihan") == null ? "" : getParam("tarikhAgihan"));
//			hashPermohonan.put("tajuk", getParam("txtTajuk") == null ? "" : getParam("txtTajuk"));
//			hashPermohonan.put("tarikhSuratPemohon", getParam("tarikhSuratPemohon") == null ? "" : getParam("tarikhSuratPemohon"));
//			beanMaklumatPermohonan.addElement(hashPermohonan);
//			this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);
//			
//			this.context.put("selectNegeri",HTML.SelectNegeriExcludePelbagaiNegeri("socNegeri", Long.parseLong(idNegeri), "", ""));
//			this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), "", " onChange=\"doChangeKementerian();\""));
//			this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian ,Long.parseLong(idAgensi), "", ""));
//			this.context.put("selectSuburusan",HTML.SelectSuburusanByIdUrusan("3", "socSuburusan" ,Long.parseLong(idSuburusan), "", ""));
//			//this.context.put("selectStatusTanah",HTML.SelectJenisTanah("socStatusTanah" ,Long.parseLong(idStatusTanah), "", ""));
//			this.context.put("selectStatusTanah",UtilHTML.SelectJenisTanah("socStatusTanah",Long.parseLong(idStatusTanah),"",JENISTANAH));
//			this.context.put("selectJenisFail",HTML.SelectTarafKeselamatan("socJenisFail" ,Long.parseLong(idJenisFail), "", ""));
//        	
//        }else if ("daftarBaruSwasta".equals(actionPajakan)){       	
//		//if ("daftarBaru".equals(submit)){       	
//        	//GO TO DAFTAR BARU PAJAKAN   
//        	vm = PATH+"frmPajakanPenerimaanPermohonanDaftarSwasta.jsp";
//        	
//        	this.context.put("mode", "new");
//        	this.context.put("readonly", "");
//        	this.context.put("inputTextClass", "");
//        	
//        	//MAKLUMAT PERMOHONAN
//        	// idHakmilik (parameter dari skrin)
//			urusan = getIPembelian().findByHakmilikUrusanId(getParam("idHakmilik"));
//			//idPermohonan = urusan.getPermohonan().getNoPermohonan(); 
//			htpPermohonan = getIPembelian().findPermohonan(urusan.getPermohonan().getNoPermohonan(), "");
//			fail = htpPermohonan.getPermohonan().getPfdFail();
//			
//        	beanMaklumatPermohonan = new Vector();
//			Hashtable hashPermohonan = new Hashtable();
//			hashPermohonan.put("noFail", "");
//			hashPermohonan.put("noFailKJP", getParam("txtNoFailKJP") == null ? "" : getParam("txtNoFailKJP"));
//			hashPermohonan.put("tarikhSuratKJP", getParam("tarikhSuratKJP") == null ? "" : getParam("tarikhSuratKJP"));
//			hashPermohonan.put("noFailLain", getParam("txtNoFailLain") == null ? "" : getParam("txtNoFailLain"));
//			hashPermohonan.put("tarikhAgihan", getParam("tarikhAgihan") == null ? "" : getParam("tarikhAgihan"));
//			hashPermohonan.put("tajuk", getParam("txtTajuk") == null ? "" : getParam("txtTajuk"));
//			hashPermohonan.put("tarikhSuratPemohon", getParam("tarikhSuratPemohon") == null ? "" : getParam("tarikhSuratPemohon"));
//			beanMaklumatPermohonan.addElement(hashPermohonan);
//			this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);
//			
//			this.context.put("selectNegeri",HTML.SelectNegeriExcludePelbagaiNegeri("socNegeri", Long.parseLong(urusan.getIdNegeri()), "", ""));
//			this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian",fail.getIdKementerian(), "", " onChange=\"doChangeKementerian();\""));
//			this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian ,htpPermohonan.getIdAgensi(), "", ""));
//			this.context.put("selectSuburusan",HTML.SelectSuburusanByIdUrusan("3", "socSuburusan" ,Long.parseLong(String.valueOf("18")), " disabled", ""));
//			this.context.put("selectStatusTanah",HTML.SelectJenisTanah("socStatusTanah" ,Long.parseLong(idStatusTanah), "", ""));
//			this.context.put("selectJenisFail",HTML.SelectTarafKeselamatan("socJenisFail" ,Long.parseLong(idJenisFail), "", ""));
//        
//        } else if("carian".equals(actionPajakan)){
//        	//vm = PATH+"frmPajakanPenerimaanPermohonanSenaraiFail.jsp";
//        	vm = PATH+"index.jsp";
//			this.context.put("socNegeri",HTML.SelectNegeri("socNegeri", Long.parseLong(idNegeri), "", ""));
//			this.context.put("socKementerian",HTML.SelectKementerian("socKementerian",Long.parseLong(idKementerian), "", " onChange=\"doChangeKementerian();\" style=\"width:400\""));
//			this.context.put("socAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian ,Long.parseLong(idAgensi), "", " style=\"width:400\""));
//			if(flagAdvSearch.equals("Y")){
//				list = getIHTP().carianFail(getParam("txtNoFail"), getParam("txtTajukFail"), getParam("txdTarikhTerima"), getParam("txtNamaPemohon"), idNegeri, idKementerian, idAgensi);
//	    	
//			}else{
//				//logic.carianFail(getParam("txtNoFail"), getParam("txdTarikhTerima"),getParam("txtTajukFail"),getParam("txtNamaPemohon"));
//				//list = logic.getSenaraiFail();
//				list = getIHTP().carianFail(getParam("txtNoFail"), getParam("txtTajukFail"), getParam("txdTarikhTerima"), getParam("txtNamaPemohon"), idNegeri, idKementerian, idAgensi);
//		    	
//		    }		
//			flagAdvSearch = "Y";
//			this.context.put("SenaraiFail", list);			
//			this.context.put("txtNoFail", getParam("txtNoFail"));
//			this.context.put("txdTarikhTerima", getParam("txdTarikhTerima"));			
//			setupPage(session,action,list);
			
        } else {       	
        	//GO TO LIST FAIL PAJAKAN        	
			list = new Vector();
        	//vm = PATH+"frmPajakanPenerimaanPermohonanSenaraiFail.jsp";
        	vm = PATH+"bayaran/setup/index.jsp";
			this.context.put("socNegeri",HTML.SelectNegeri("socNegeri", Long.parseLong(idNegeri), "", ""));
			this.context.put("socKementerian",HTML.SelectKementerian("socKementerian",Long.parseLong(idKementerian), "", " onChange=\"doChangeKementerian();\" style=\"width:400\""));
			this.context.put("socAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian ,Long.parseLong(idAgensi), "", " style=\"width:400\""));
			if(flagAdvSearch.equals("Y")){
				list = getIHTP().carianFail(getParam("txtNoFail"), getParam("txtTajukFail"), getParam("txdTarikhTerima"), getParam("txtNamaPemohon"), idNegeri, idKementerian, idAgensi,"3","9");
	    	
			}else{
				list = getIHTP().carianFail(getParam("txtNoFail"), getParam("txtTajukFail"), getParam("txdTarikhTerima"), getParam("txtNamaPemohon"), idNegeri, idKementerian, idAgensi,"3","9");

			}
			if (mode.indexOf("change") != -1) {
				list = getIHTP().carianFail(getParam("txtNoFail"), getParam("txtTajukFail"), getParam("txdTarikhTerima"), getParam("txtNamaPemohon"), idNegeri, idKementerian, idAgensi,"3","9");
		    	flagAdvSearch = "Y";
		    	
		    }

			this.context.put("SenaraiFail", list);
			
			this.context.put("txtNoFail", getParam("txtNoFail"));
			this.context.put("txdTarikhTerima", getParam("txdTarikhTerima"));
			
			setupPage(session,action,list);
        }
        
        //SET DEFAULT PARAM
		this.context.put("actionPajakan", actionPajakan);
		
		//SET DEFAULT ID PARAM
		this.context.put("idFail", idFail);
		this.context.put("idPermohonan", idPermohonan);
		this.context.put("idStatus", idStatus);
		this.context.put("subUrusan", subUrusan);
	    // 19/08/2010
    	this.context.put("flagAdvSearch",flagAdvSearch);	
		myLog.info("mode : "+mode);
    	this.context.put("mode",mode);
		myLog.info("selectedTab : "+selectedTab);
        this.context.put("selectedTab", selectedTab);
        this.context.put("readOnly", readonly);

    	return vm;

	}

	public void setupPage(HttpSession session,String action,Vector list) {
		
		try {
		
			this.context.put("totalRecords",list.size());
			int page = getParam("page") == "" ? 1:getParamAsInteger("page");
			
			int itemsPerPage;
			if (this.context.get("itemsPerPage") == null || this.context.get("itemsPerPage") == "") {
				itemsPerPage = getParam("itemsPerPage") == "" ? 10:getParamAsInteger("itemsPerPage");
			} else {
				itemsPerPage = (Integer)this.context.get("itemsPerPage");
			}
		    
		    if ("getNext".equals(action)) {
		    	page++;
		    } else if ("getPrevious".equals(action)) {
		    	page--;
		    } else if ("getPage".equals(action)) {
		    	page = getParamAsInteger("value");
		    } else if ("doChangeItemPerPage".equals(action)) {
		       itemsPerPage = getParamAsInteger("itemsPerPage");
		    }
		    	
		    Paging paging = new Paging(session,list,itemsPerPage);
			
			if (page > paging.getTotalPages()) page = 1; //reset page number
				this.context.put("SenaraiFail",paging.getPage(page));
			    this.context.put("page", new Integer(page));
			    this.context.put("itemsPerPage", new Integer(itemsPerPage));
			    this.context.put("totalPages", new Integer(paging.getTotalPages()));
			    this.context.put("startNumber", new Integer(paging.getTopNumber()));
			    this.context.put("isFirstPage",new Boolean(paging.isFirstPage()));
			    this.context.put("isLastPage", new Boolean(paging.isLastPage()));
	        
		} catch (Exception e) {
			e.printStackTrace();
			this.context.put("error",e.getMessage());
		}	
	}
	
	private IPembelian getIPembelian(){
		if (iPembelian==null){
			iPembelian=new PembelianBean();
		}
		return iPembelian;
	} 
	
	private IHtp getIHTP(){
		if(iHtp == null)
			iHtp = new HtpBean();
		return iHtp;
	}	

	private IPajakanUtama getPU(){
		if(iPajakanUtama == null)
			iPajakanUtama = new PajakanUtamaBean();
		return iPajakanUtama;
	}		
	  public void MaklumatPermohonanView(String mode) throws Exception{

	    	try {
	    		
	    		if (mode.equalsIgnoreCase("view")){    			

	        		this.context.put("readOnly", "readOnly");
	        		this.context.put("classDis", "disabled");
	        		this.context.put("inputTextClass", "disabled");
	        		
	        	}
	    		//mode = update
	    		else if(mode.equalsIgnoreCase("update")){	    			

	    			this.context.put("readOnly", "");
	        		this.context.put("classDis", "");
	    		}

	    	} catch (Exception e){
	    		e.printStackTrace();
	    	}
	    }
	  
		private void getMaklumatKadarValues(String idPermohonan){

			String tarikhMula = getParam("txdTarikhMulaPajakan");
			String tarikhTamat = getParam("txdTarikhTamatPajakan");
			String tempoh = getParam("txtTempoh");
			String kadarCukai = getParam("txtKadarCukai");			
			String kadarPajakan = getParam("txtKadarPajakan");	
			String idPajakan= getParam("txtidpajakan");	
			String idPajakanKadar= getParam("txtidpajakankadar");	
			pajakanKadar = new PajakanKadar();
			pajakan = new Pajakan();
			
			pajakanKadar.setIdPajakanKadar(Long.parseLong(idPajakanKadar==null||idPajakanKadar.equals("")?"0":idPajakanKadar));
			pajakanKadar.setTarikhMulaBayaran(tarikhMula);
			pajakanKadar.setTarikhTamatBayaran(tarikhTamat);
			pajakan.setTarikhMulaPajakan(new Date(tarikhMula));
			pajakan.setTarikhTamatPajakan(new Date(tarikhTamat));
			pajakan.setTempohPajakan(tempoh);
			pajakan.setKadarCukai(Double.valueOf(Utils.RemoveComma(kadarCukai)));
			pajakan.setKadarPajakan(Double.valueOf(Utils.RemoveComma(kadarPajakan)));
			pajakan.setIdPajakan(Long.parseLong(idPajakan));
			permohonan = new Permohonan();
			permohonan.setIdPermohonan(idPermohonan);
			
			pajakan.setPermohonan(permohonan);
			pajakanKadar.setPajakan(pajakan);
			context.put("pajakan", pajakan);
			context.put("pajakanKadar", pajakanKadar);
			
		}

	
	
}
