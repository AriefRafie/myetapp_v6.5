package ekptg.view.htp;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.action.AjaxModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.htp.FrmCukaiKemaskiniDataBaru;
import ekptg.model.htp.FrmCukaiSenaraiFailExcelUpload;
import ekptg.model.htp.cukai.entity.CukaiTemp;

public class FrmCukaiKemasukkanModule extends AjaxModule {
	private final String PATH = "app/htp/cukai/";
	private String vm = PATH+"FrmCukaiSenaraiTahunFail.jsp";
	private String user_id = "";
	
	@Override
	public String doAction() throws Exception {
		String modepage = getParam("mode1");
		String command = getParam("command");
		//System.out.println(command);
		String action = getParam("action");
		String tahun = getParam("tahun");
		HttpSession session = request.getSession();
		user_id =(String)session.getAttribute("_ekptg_user_id");
		context.put("UTIL", new ekptg.helpers.Utils());
		
		if(command.equals("senaraiFail")){
			//System.out.println("Tahun fail ::" + tahun);
			getListItem();
			getSenaraiFail(session, action,tahun);
			this.context.put("Tahun", tahun);
			vm = PATH+"frmCukaiSenaraiFailUploadExcel.jsp";
			
		}	
		else if(command.equals("doChangeDaerah")){
			changeDaerah();
			getSenaraiFail(session, action,tahun);
			vm = PATH+"frmCukaiSenaraiFailUploadExcel.jsp";
					
		}
		else if(command.equals("searchHakmilik")){
			searchHakmilik(session, action,tahun);
			vm =PATH+"frmCukaiSenaraiFailUploadExcel.jsp";
			
		}
		else if(command.equals("createNew")){
			this.context.put("manualNegeri",HTML.SelectNegeri("manualNegeri", "onChange=\"doChangeDaerahManual()\"") );
    		this.context.put("manualDaerah", HTML.SelectDaerah("manualDaerah"));
    		this.context.put("manualMukim", HTML.SelectMukim("manualMukim"));
    		this.context.put("jenisLot",HTML.SelectLot("jenisLot"));
    		this.context.put("JenisHakmilik",HTML.SelectJenisHakmilik("JenisHakmilik"));
    		this.context.put("Tahun", tahun);
    		
			vm = PATH+"new.jsp";
			
		}
		else if(command.equals("doChangeDaerahManual")){			
			changeDaerahManual();
    		vm = PATH+"new.jsp";
    		
		}
		else if(command.equals("saveCukai")){
			saveManual(session,action);
			changeDaerah();
			String cukaiId = getParam("idcukai");
			//String cukaiId = getParam("idcukai"))==""?"0":getParam("manualNegeri");
			CukaiTemp cukai = FrmCukaiSenaraiFailExcelUpload.getCukaiTemp2();
			String lblNegeri3 = HTML.SelectNegeri("manualNegeri", cukai.getIdNegeri(), null, "onChange=\"doChangeDaerahManual()\" disabled=disabled ");
			this.context.put("manualNegeri", lblNegeri3);
			context.put("manualDaerah",HTML.SelectDaerahByNegeri(String.valueOf(cukai.getIdNegeri()), "manualDaerah", cukai.getIdDaerah(), null, "onChange=\"doChangeDaerahManual()\" disabled=disabled"));
			this.context.put("manualMukim", HTML.SelectMukimByDaerah(String.valueOf(cukai.getIdDaerah()), "manualMukim",cukai.getIdMukim(),"disabled=disabled"));
			this.context.put("jenisLot",HTML.SelectLot("jenisLot",cukai.getIdLot(),"disabled=disabled"));
    		this.context.put("JenisHakmilik",HTML.SelectJenisHakmilik("JenisHakmilik",cukai.getIdJenisHakmilik(),"disabled=disabled"));
			context.put("cukai", cukai);
			//vm ="app/htp/cukai/new.jsp";
			vm = PATH+"frmCukaiSenaraiFailUploadExcel.jsp";
		}
		else if(command.equals("viewCukaiDetail")){
			if(modepage.equalsIgnoreCase("1")){
			context.put("mode","kemaskini");
			}
			else{context.put("mode","simpan");
			}
			String cukaiId = getParam("idCukai");
			CukaiTemp cukai = FrmCukaiSenaraiFailExcelUpload.getCukaiTemp(cukaiId);
			String lblNegeri3 = HTML.SelectNegeri("manualNegeri", cukai.getIdNegeri(), null, "onChange=\"doChangeDaerahManual()\" disabled=disabled ");
			this.context.put("manualNegeri", lblNegeri3);
			context.put("manualDaerah",HTML.SelectDaerahByNegeri(String.valueOf(cukai.getIdNegeri()), "manualDaerah", cukai.getIdDaerah(), null, "onChange=\"doChangeDaerahManual()\" disabled=disabled"));
			this.context.put("manualMukim", HTML.SelectMukimByDaerah(String.valueOf(cukai.getIdDaerah()), "manualMukim",cukai.getIdMukim(),"disabled=disabled"));
			this.context.put("jenisLot",HTML.SelectLot("jenisLot",cukai.getIdLot(),"disabled=disabled"));
    		this.context.put("JenisHakmilik",HTML.SelectJenisHakmilik("JenisHakmilik",cukai.getIdJenisHakmilik(),"disabled=disabled"));
			
			context.put("cukai", cukai);
			vm = PATH+"editCukai.jsp";
		}
		else if(command.equals("updateCukai")){
			updateManual(session,action);
			context.put("mode","kemaskini");
		}
		else{
			getListItem();
			//getSenaraiFail(session, action);
			getSenaraiTahun(session, action);			
			vm = PATH+"FrmCukaiSenaraiTahunFail.jsp";
			
		}
		return vm;
		
	}
	
	private void getSenaraiTahun(HttpSession session, String action) throws Exception {
		Vector senaraiTahun = FrmCukaiSenaraiFailExcelUpload.CukaigetSenaraiTahunFail();
		//System.out.println("senrai tahun ::" + senaraiTahun);
		this.context.put("SenaraiTahun", senaraiTahun);	
		setupPage(session,action,senaraiTahun);	
		
	}
	
	private void changeDaerahManual()throws Exception{
		String lblNegeri3 = "";
		String idNegeri3 = getParam("manualNegeri");
		String idDaerah3= getParam("manualDaerah");
		String idMukim3= getParam("manualMukim");
		
   		lblNegeri3 = HTML.SelectNegeri("manualNegeri", Utils.parseLong(idNegeri3), null, "onChange=\"doChangeDaerahManual()\" ");
		this.context.put("manualNegeri", lblNegeri3);
		
		if(idNegeri3 != ""){
			//context.put("lblDaerah2",HTML.SelectDaerahByNegeri(idNegeri2,"lblDaerah",null,""));
			context.put("manualDaerah",HTML.SelectDaerahByNegeri(idNegeri3, "manualDaerah", Utils.parseLong(idDaerah3), null, "onChange=\"doChangeDaerahManual()\""));
			
		}else {
			//context.put("lblDaerah2",HTML.SelectDaerahByNegeri(idNegeri2,"lblDaerah2",null,"onChange=\"doChangeDaerah1()\""));
			//context.put("lblDaerah2",HTML.SelectDaerahByNegeri(idNegeri2, "lblDaerah2", Utils.parseLong(idDaerah2), null, "onChange=\"doChangeDaerah1()\""));
			context.put("manualDaerah", HTML.SelectDaerah("manualDaerah",null,null,""));
		}
		
		/*if(idNegeri3 != "" && idDaerah3 != ""){
					
			this.context.put("manualMukim", HTML.SelectMukimByDaerah(idDaerah3, "manualMukim", Utils.parseLong(idMukim3), null, "onChange=\"doChangeDaerahManual()\""));
			
		}else{*/
			
			this.context.put("manualMukim", HTML.SelectMukimByDaerah(idDaerah3, "manualMukim"));
			
		//}
		
		this.context.put("jenisLot",HTML.SelectLot("jenisLot"));			
		this.context.put("JenisHakmilik",HTML.SelectJenisHakmilik("JenisHakmilik"));
	}
	private void saveManual(HttpSession session,String action)throws Exception{
		String idNegeri2 = "";
		String idDaerah2 = "";
		String idMukim2 = "";
		String noHakmilik = "";
		String Luas = "";
		String noLot = "";
		String Tahunan = "";
		String CukaiLain = "";
		String Tungakan = "";
		String Denda = "";
		String Lebihan = "";
		String JumBayaran = "";
		String KegunaanTanah = "";
		String JenisHakmilik ="";
		String JenisLot = "";
		String tahun = "";
		
		idNegeri2 = getParam("manualNegeri")==""?"0":getParam("manualNegeri");
		idDaerah2 = getParam("manualDaerah")==""?"0":getParam("manualDaerah");
		idMukim2 = getParam("manualMukim")==""?"0":getParam("manualMukim");
		JenisHakmilik = getParam("JenisHakmilik")==""?"0":getParam("JenisHakmilik");
		noHakmilik = getParam("txtNoHakmilik")==""?"0":getParam("txtNoHakmilik");
		Luas = getParam("txtLuas")==""?"0":getParam("txtLuas");
		JenisLot = getParam("jenisLot")==""?"0":getParam("jenisLot");
		noLot = getParam("txtNoLot")==""?"0":getParam("txtNoLot");
		Tahunan = getParam("txtTahunan")==""?"0":getParam("txtTahunan");
		CukaiLain = getParam("txtCukaiLain")==""?"0":getParam("txtCukaiLain");
		Tungakan = getParam("txtTungakan")==""?"0":getParam("txtTungakan");
		Denda = getParam("txtDenda")==""?"0":getParam("txtDenda");
		Lebihan = getParam("txtLebihan")==""?"0":getParam("txtLebihan");
		JumBayaran = getParam("txtJumBayaran")==""?"0":getParam("txtJumBayaran"); 
		KegunaanTanah = getParam("txtKegunaanTanah")==""?"0":getParam("txtKegunaanTanah");
		tahun = getParam("tahun")==""?"0":getParam("tahun");
		
		Hashtable h = new Hashtable();
		h.put("idNegeri2", idNegeri2);
		h.put("idDaerah2", idDaerah2);
		h.put("idMukim2", idMukim2);
		h.put("JenisHakmilik", JenisHakmilik);
		h.put("noHakmilik", noHakmilik);
		h.put("Luas", Luas);
		h.put("JenisLot", JenisLot);
		h.put("noLot", noLot);
		h.put("Tahunan", Tahunan);
		h.put("CukaiLain", CukaiLain);
		h.put("Tungakan", Tungakan);
		h.put("Denda", Denda);
		h.put("Lebihan", Lebihan);
		h.put("JumBayaran", JumBayaran);
		h.put("KegunaanTanah", KegunaanTanah);   
		h.put("idmasuk", user_id);
		h.put("Tahun", tahun);
		String idCukai = FrmCukaiKemaskiniDataBaru.SimpanDataManual(h);
		CukaiTemp cukai = FrmCukaiSenaraiFailExcelUpload.getCukaiTemp(idCukai);
		context.put("cukai", cukai);
		//Vector list = FrmCukaiSenaraiFailExcelUpload.CukaigetSenaraiFailExcel(output,1);
		//setupPage(session,action,list);
	}
	private void updateManual(HttpSession session,String action)throws Exception{
		String idCukai=getParam("idCukai");
		String idNegeri2 = "";
		String idDaerah2 = "";
		String idMukim2 = "";
		String noHakmilik = "";
		String Luas = "";
		String noLot = "";
		String Tahunan = "";
		String CukaiLain = "";
		String Tungakan = "";
		String Denda = "";
		String Lebihan = "";
		String JumBayaran = "";
		String KegunaanTanah = "";
		String JenisHakmilik ="";
		String JenisLot = "";
		String tahun = "";
		
		idNegeri2 = getParam("manualNegeri")==""?"0":getParam("manualNegeri");
		idDaerah2 = getParam("manualDaerah")==""?"0":getParam("manualDaerah");
		idMukim2 = getParam("manualMukim")==""?"0":getParam("manualMukim");
		JenisHakmilik = getParam("JenisHakmilik")==""?"0":getParam("JenisHakmilik");
		noHakmilik = getParam("txtNoHakmilik")==""?"0":getParam("txtNoHakmilik");
		Luas = getParam("txtLuas")==""?"0":getParam("txtLuas");
		JenisLot = getParam("jenisLot")==""?"0":getParam("jenisLot");
		noLot = getParam("txtNoLot")==""?"0":getParam("txtNoLot");
		Tahunan = getParam("txtTahunan")==""?"0":getParam("txtTahunan");
		CukaiLain = getParam("txtCukaiLain")==""?"0":getParam("txtCukaiLain");
		Tungakan = getParam("txtTungakan")==""?"0":getParam("txtTungakan");
		Denda = getParam("txtDenda")==""?"0":getParam("txtDenda");
		Lebihan = getParam("txtLebihan")==""?"0":getParam("txtLebihan");
		JumBayaran = getParam("txtJumBayaran")==""?"0":getParam("txtJumBayaran"); 
		KegunaanTanah = getParam("txtKegunaanTanah")==""?"0":getParam("txtKegunaanTanah");
		tahun = getParam("tahun")==""?"0":getParam("tahun");
	
		CukaiTemp temp = new CukaiTemp();
		temp.setLebihan(Double.valueOf(Utils.RemoveComma(Lebihan)));
		temp.setTunggakkan(Double.valueOf(Utils.RemoveComma(Tungakan)));
		temp.setBayaranLain(Double.valueOf(Utils.RemoveComma(CukaiLain)));
		temp.setIdCukaiTemp(Long.parseLong(idCukai));
		temp.setKegunaanTanah(KegunaanTanah);
		temp.setCukaiKenaBayar(Double.parseDouble(Utils.RemoveComma(Tahunan)));
		temp.setDenda(Double.parseDouble(Utils.RemoveComma(Denda)));
		temp.setIdMasuk(Long.parseLong(user_id));
		temp.setIdKemaskini(user_id);
		temp.setTahun(tahun);
		FrmCukaiKemaskiniDataBaru.updateDataManual(temp);
		CukaiTemp cukai = FrmCukaiSenaraiFailExcelUpload.getCukaiTemp(idCukai);
		
		System.out.println("cukai lebihan:"+temp.getLebihan());
		
		context.put("cukai", cukai);
		//Vector list = FrmCukaiSenaraiFailExcelUpload.CukaigetSenaraiFailExcel(String.valueOf(temp.getIdCukaiTemp()),1);
		//setupPage(session,action,list);
	}
	
	private void searchHakmilik(HttpSession session,String action, String tahun)throws Exception{
		String lblNegericari = getParam("lblNegeri2");
		String lblDaerahcari = getParam("lblDaerah2");
		String lblMukimcari = getParam("lblMukim2");
		String noHakmilikcari = getParam("txtNoHakmilik").trim();
		Vector list = null;
//		if(!noHakmilikcari.equals(""))
//			list = FrmCukaiSenaraiFailExcelUpload.CukaigetSenaraiFailExcel(noHakmilikcari,1);
//		else if(!lblNegericari.equals("")){
//			list = FrmCukaiSenaraiFailExcelUpload.CukaigetSenaraiFailExcel(lblNegericari,2);
//		}
//		else
		list = FrmCukaiSenaraiFailExcelUpload.CukaigetSenaraiFailExcel2(lblNegericari,lblDaerahcari,lblMukimcari,noHakmilikcari,tahun);
		setupPage(session,action,list);
		changeDaerah();
	}
	
	private void getSenaraiFail(HttpSession session,String action, String tahun)throws Exception{
		Vector senaraiFailbyTahun = FrmCukaiSenaraiFailExcelUpload.getCukaiTempByTahun(tahun);
		this.context.put("SenaraiFail", senaraiFailbyTahun);		
		setupPage(session,action,senaraiFailbyTahun);
		
	}
	
	private void changeDaerah() throws Exception{
		String idNegeri2 = getParam("lblNegeri2");
		String idDaerah2= getParam("lblDaerah2");
		String idMukim2= getParam("lblMukim2");
		String tahun = getParam("tahun");
				
		String lblNegeri2 = HTML.SelectNegeri("lblNegeri2", Utils.parseLong(idNegeri2), null, "onChange=\"doChangeDaerah1()\" ");
		this.context.put("lblNegeri2", lblNegeri2);
		
		if(idNegeri2 != ""){
			//context.put("lblDaerah2",HTML.SelectDaerahByNegeri(idNegeri2,"lblDaerah",null,""));
			context.put("lblDaerah2",HTML.SelectDaerahByNegeri(idNegeri2, "lblDaerah2", Utils.parseLong(idDaerah2), null, "onChange=\"doChangeDaerah1()\""));
			
		}else {
			//context.put("lblDaerah2",HTML.SelectDaerahByNegeri(idNegeri2,"lblDaerah2",null,"onChange=\"doChangeDaerah1()\""));
			//context.put("lblDaerah2",HTML.SelectDaerahByNegeri(idNegeri2, "lblDaerah2", Utils.parseLong(idDaerah2), null, "onChange=\"doChangeDaerah1()\""));
			context.put("lblDaerah2", HTML.SelectDaerah("lblDaerah2",null,null,""));
		}
		
		if(idNegeri2 != "" && idDaerah2 != ""){
					
			this.context.put("lblMukim2", HTML.SelectMukimByDaerah(idDaerah2, "lblMukim2", Utils.parseLong(idMukim2), null, "onChange=\"doChangeDaerah1()\""));
			
		}else{
			
			this.context.put("lblMukim2", HTML.SelectMukimByDaerah(idDaerah2, "lblMukim2"));
			
		}
	}
	private void getListItem()throws Exception{
		String lblNegeri = HTML.SelectNegeri("lblNegeri", "onChange=\"doChangeDaerah()\"");
		String lblDaerah = HTML.SelectDaerah("lblDaerah");
		context.put("lblNegeri", lblNegeri);
		context.put("lblDaerah", lblDaerah);
		
		this.context.put("lblNegeri2", HTML.SelectNegeri("lblNegeri2", "onChange=\"doChangeDaerah1()\""));
		this.context.put("lblDaerah2", HTML.SelectDaerah("lblDaerah2"));
		this.context.put("lblMukim2", HTML.SelectMukim("lblMukim2"));
	}

}
