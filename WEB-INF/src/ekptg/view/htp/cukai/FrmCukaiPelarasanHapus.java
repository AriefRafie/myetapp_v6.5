package ekptg.view.htp.cukai;

import java.util.Date;
import java.util.Hashtable;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.htp.FrmCukaiSenaraiFailExcelUpload;
import ekptg.model.htp.cukai.CukaiBean;
import ekptg.model.htp.cukai.FrmCukaiPenyataBean;
import ekptg.model.htp.cukai.ICukai;

public class FrmCukaiPelarasanHapus extends AjaxBasedModule{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3613060385456172653L;
	private final String PATHTP="app/htp/";
	private final String PATHVER = PATHTP+ResourceBundle.getBundle("file").getString("ver_htp")+"/";
	private final String PATH = PATHVER+"cukai/";

	static Logger myLog = Logger.getLogger(ekptg.view.htp.cukai.FrmCukaiPelarasanHapus.class);
	String socNegeri = "";
	String socDaerah = "";
	String socMukim = "";
	String idnegeri = "";
 	String iddaerah = "";
 	String idmukim = "";
    String isCarian = "tidak";
	private ICukai iCukai = null;
	private ICukai iCukaiPenyata = null;
	private String year = "";
	private String socTahun = "";
	private String bil ="1";
	private String noHakmilik = "";

	@Override
	public String doTemplate2() throws Exception {
		Hashtable data = null;
		HttpSession session = this.request.getSession();
		String action = getParam("action");
		String template_name = PATH+"frmCukaiPelarasanHapusSenarai.jsp";		
		String submit = getParam("command");
		myLog.info("action="+action+",submit="+submit+",carian="+ getParam("carian"));
		Vector<Hashtable<String, String>> SenaraiFailOrig = null;		
		idnegeri = getParam("socNegeri");
	 	iddaerah = getParam("socDaerah");
	 	idmukim = getParam("socMukim");
	 	socTahun = getParam("Form_tahun")==null||getParam("Form_tahun")==""?lebah.util.Util.getDateTime(new Date(), "yyyy"):getParam("Form_tahun");
	 	noHakmilik = getParam("txtnohakmilik");
		context.remove("SenaraiFailOrig");
		context.put("UTIL", new ekptg.helpers.Utils());

		if("CukaiKemaskini".equals(submit)){
			data = new Hashtable();
			String idCukaiTemp ="";
			String idCukaiTerperinci = "";
			bil = getParam("bil");
			idCukaiTerperinci = getParam("id_cukaiterperinci"+bil);
			idCukaiTemp = getParam("idcukaitemp"+bil);

			getICukaiPenyata().cukaiTempHapus(idCukaiTemp);
			getICukaiPenyata().cukaiTerperinciHapus(idCukaiTerperinci);
			
			SenaraiFailOrig = (Vector<Hashtable<String, String>>)getICukaiPenyata().senaraiHakmilik(idnegeri,iddaerah,idmukim,socTahun);
			this.context.put("SenaraiFailOrig", SenaraiFailOrig);
			
		}else if(submit.equals("hakmilik")){
//			myLog.info("hakmilik="+ idnegeri +"|"+iddaerah+"|"+idmukim+"|"+socTahun);
			
			SenaraiFailOrig = (Vector<Hashtable<String, String>>)getICukaiBase().senaraiPenyataCukaiTemp(idnegeri,iddaerah,idmukim,socTahun);

			Vector<Hashtable<String,String>> senaraiHakmilikB = getICukaiPenyata().senaraiHakmilik(idnegeri,iddaerah,idmukim,socTahun);
//			myLog.info("senaraiHakmilikB="+ senaraiHakmilikB.size());

			Vector<Hashtable<String,String>> senaraiHakmilikX = getICukaiBase().senaraiHakmilik(idnegeri,iddaerah,idmukim,socTahun);
			myLog.info("senaraiHakmilikX="+ senaraiHakmilikX.size());

			this.context.put("SenaraiFailOrig", SenaraiFailOrig);
			this.context.put("senaraiHakmilikB", senaraiHakmilikB);
			this.context.put("senaraiHakmilikX", senaraiHakmilikX);

			template_name = PATH+"frmCukaiSenarai.jsp";
			
		}else if("CetakSenaraiKemaskini".equals(submit)){
     		Vector senaraikemaskini = FrmCukaiSenaraiFailExcelUpload.getCukaiKemaskiniList();
    		this.context.put("senaraikemaskini", senaraikemaskini);
     		template_name = "app/htp/frmCukaiKemaskiniDataExcel2.jsp";
    		
		}else if("carian".equals(submit)){
			isCarian = "ya";		
		    String noLot = getParam("txtNoLot")==""?"":getParam("txtNoLot");
			try {
				SenaraiFailOrig = getICukaiPenyata().senaraiHakmilik(idnegeri,iddaerah,idmukim,socTahun);
				if(!noHakmilik.equals("")){
					SenaraiFailOrig = getICukaiPenyata().senaraiHakmilik(idnegeri
					,iddaerah,idmukim,socTahun,noHakmilik);
				}else if(!noLot.equals("")){
					SenaraiFailOrig = getICukaiPenyata().senaraiHakmilik(idnegeri
							,iddaerah,idmukim,socTahun,noHakmilik,"",noLot);					
				}else if(!noHakmilik.equals("") && !noLot.equals("")){
					SenaraiFailOrig = getICukaiPenyata().senaraiHakmilik(idnegeri
							,iddaerah,idmukim,socTahun,noHakmilik,"",noLot);				
				}
				this.context.put("SenaraiFailOrig", SenaraiFailOrig);
		     
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("[2 HTP CUKAI PELARASAN HAPUS] TIADA MAKLUMAT CUKAI");
			}
			
		}else{
			isCarian = getParam("carian");
		    String noLot = getParam("txtNoLot")==""?"":getParam("txtNoLot");
			if(isCarian.equals("ya")){
				try {
					SenaraiFailOrig = getICukaiPenyata().senaraiHakmilik(idnegeri,iddaerah,idmukim,socTahun);
					isCarian = "ya";		
				     
				} catch (Exception e) {
					e.printStackTrace();
					throw new Exception("[1 HTP CUKAI PELARASAN HAPUS] TIADA MAKLUMAT CUKAI");
				}
				//myLog.info(" 1st SenaraiFailOrig.size()="+SenaraiFailOrig)	;
			}
		}
		socNegeri = HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri),null,"onChange=doChangeNegeriX();");
		socDaerah = HTML.SelectDaerahByNegeri(idnegeri, "socDaerah",Utils.parseLong(iddaerah),null, "onChange=\"doChangeDaerahX()\"");
		socMukim = HTML.SelectMukimByDaerah(iddaerah, "socMukim", Utils.parseLong(idmukim) , "");
	    
		this.context.put("socDaerah", socDaerah);
		this.context.put("socNegeri", socNegeri);
		this.context.put("socMukim", socMukim);		
		setupPage(session,action,SenaraiFailOrig);
		this.context.put("iscarian", isCarian);
		year = lebah.util.Util.getDateTime(new Date(), "yyyy");
		this.context.put("tahuncukai", Integer.parseInt(year));
		this.context.put("tahuncukain", Integer.parseInt(year)+1);
		this.context.put("tahunparam", Integer.parseInt(socTahun));
		this.context.put("carianNoHakmilik", noHakmilik);

		return template_name;		
		
	}

	private ICukai getICukaiPenyata(){
		if(iCukaiPenyata==null){
			iCukaiPenyata = new FrmCukaiPenyataBean();
		}
		return iCukaiPenyata;
		
	}
	
	private ICukai getICukaiBase(){
		if(iCukai==null){
			iCukai = new CukaiBean();
		}
		return iCukai;
		
	}
	
	
}
