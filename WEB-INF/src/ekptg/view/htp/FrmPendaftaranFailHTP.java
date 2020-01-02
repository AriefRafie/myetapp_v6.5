package ekptg.view.htp;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.action.AjaxModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHtp;
import ekptg.model.htp.UtilHTML;
import ekptg.model.htp.entity.HtpPermohonan;
import ekptg.model.htp.entity.Permohonan;
import ekptg.model.htp.entity.PfdFail;

public class FrmPendaftaranFailHTP extends AjaxModule {
	/**
	 * Module untuk pendaftaran fail lama
	 */
	private static final long serialVersionUID = -8380025942631767247L;
	private static final String PATH="app/htp/fail/";
	private final String IDURUSANPEMBERIMILKAN= "1";
	private final String IDURUSANPERIZAPAN = "10";
	private final String IDURUSANCUKAI= "11";
	private final String IDURUSANPAJAKAN = "3";
	private final String IDURUSANPKECIL= "309";
	private final String IDURUSANGADAIAN = "108";
	private final String IDURUSANPEMBELIAN= "2";
	private final String IDURUSANPERLETAKAHAN = "5";
	private final String IDURUSANPENSWASTAAN= "4";
	private final String IDSUBURUSANPERMOHONAN = "42";
	private final String IDSUBURUSANPEMBELIAN = "23,25";
	static Logger myLog = Logger.getLogger(ekptg.view.htp.FrmPendaftaranFailHTP.class);
	private String vm = PATH+"index.jsp";
	private String userID = null;
	private IHtp iHtp = null;
	private String readonly = " readonly=\"readonly\" class=\"disabled\"";
	private String readonlysoc = " disabled class=\"disabled\"";
	private String readonlysockemaskini = readonlysoc;
	private String idNegeri ="0";
	private String idStringUrusan = IDURUSANPEMBERIMILKAN+","+IDURUSANPERIZAPAN+","+IDURUSANCUKAI+
									","+IDURUSANPAJAKAN+","+IDURUSANPKECIL+","+IDURUSANGADAIAN+
									","+IDURUSANPEMBELIAN+","+IDURUSANPERLETAKAHAN+","+IDURUSANPENSWASTAAN;
	private String idurusan = "";
	private String idsuburusan = "";
	private String socDaerah = "";
	private String socNegeri = "";
	private String iddaerah = "";

	private String socUrusan = "";
	private String socSubUrusan = "";
//	private final String URUSAN_TANAH = "01";
	private final String URUSAN_BANGUNAN = "03";
	private final String ID_URUSANTANAH = "24";
	private final String ID_URUSANTANAH1 = "25";
	
	private PfdFail fail = null;
	private Permohonan permohonan = null;
	private HtpPermohonan htpPermohonan = null;
	
	@Override
	public String doAction() throws Exception {
		HttpSession ses = request.getSession();
		userID = (String)ses.getAttribute("_ekptg_user_id");
		String command = getParam("command");
		String action = getParam("action");
		String idPermohonan = getParam("idPermohonan");
		String idHtpPermohonan = getParam("idHtpPermohonan");
//		String idHakmilikUrusan =  getParam("idHakmilikUrusan");
		context.put("URUSAN_BANGUNAN",URUSAN_BANGUNAN);
		context.put("URUSAN_TANAH",ID_URUSANTANAH);
		context.put("URUSAN_TANAH1",ID_URUSANTANAH1);
		
	 	idurusan = getParam("socUrusan");
	 	if ("".equals(idurusan)) idurusan="-1";
	 	idsuburusan = getParam("socSubUrusan");
	 	myLog.info("command="+command+",action="+action);
		if(command.equals("tambahFail")){
			//getSenaraiSemakFail();
			String mode="";
			context.put("mode", mode);
			context.put("pageMode", "edit");
			//context.put("socNegeri",HTML.SelectNegeri("socNegeri"));
			socNegeri = HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri),""," onChange=\"doChangeKementerian()\" ");
			socDaerah = HTML.SelectDaerahByNegeri(idNegeri,"socDaerah",Utils.parseLong(iddaerah),"","");
			socUrusan = UtilHTML.SelectUrusanHTP("socUrusan",Utils.parseLong(idurusan),"onChange=\"doChangeKementerian()\" ",idStringUrusan);//disabled class=disabled
			//context.put("selectSuburusan", UtilHTML.SelectSuburusanByIdUrusan("2","23,25", "socSuburusan", null,""));
	 		socSubUrusan = HTML.SelectSuburusanByIdUrusan(idurusan,"socSubUrusan",Utils.parseLong(idsuburusan), "");
	 		if (idurusan.equals("1")){
	 	 		socSubUrusan = HTML.SelectSuburusanByIdUrusan(idurusan,"socSubUrusan",Utils.parseLong(IDSUBURUSANPERMOHONAN), "");	
	 		}else if (idurusan.equals("2")){
	 	 		socSubUrusan = UtilHTML.SelectSuburusanByIdUrusan(idurusan,IDSUBURUSANPEMBELIAN,"socSubUrusan",null, "");	
	 		}
			context.put("socNegeri", socNegeri);
			context.put("socDaerah", socDaerah);

	 		context.put("selectUrusan", socUrusan);
			context.put("selectSuburusan", socSubUrusan);
			context.put("statusTanah", HTML.SelectJenisTanah("socStatusTanah", null, ""));
			context.put("socTarafKeselamatan", HTML.SelectTarafKeselamatan("socTarafKeselamatan", null, "") );
			context.remove("htpPermohonan");
			getKementerianDetail("", "edit");
			context.put("inputstyleread", null );
			vm= PATH+"file.jsp";
			
			context.put("page","1");
			
		}else if(command.equals("doChangeKementerian")){
			//getSenaraiSemakFail();
			getValues();
			idNegeri = getParam("socNegeri");
			String idKementerian = getParam("socKementerian");
			String socKementerian = HTML.SelectKementerian("socKementerian", (idKementerian == "") ? null : Long.parseLong(idKementerian),"", " onChange=\"doChangeKementerian()\" ");
			String socAgensi = HTML.SelectAgensiByKementerian("socAgensi",idKementerian,Long.parseLong("1"),"");
			String socNegeri = HTML.SelectNegeri("socNegeri",(idNegeri == "") ? null : Long.parseLong(idNegeri)," onChange=\"doChangeKementerian()\" ");
			socDaerah = HTML.SelectDaerahByNegeri(idNegeri,"socDaerah",Utils.parseLong(htpPermohonan.getIdDaerah()),"","");
		    context.put("selectKementerian",socKementerian);
			context.put("selectAgensi",socAgensi);
			context.put("socNegeri",socNegeri);
			context.put("socDaerah", socDaerah);
			//context.put("selectSuburusan", UtilHTML.SelectSuburusanByIdUrusan("2","23,25", "socSuburusan", null,""));
			socUrusan = UtilHTML.SelectUrusanHTP("socUrusan",Utils.parseLong(idurusan),"onChange=\"doChangeKementerian()\" ",idStringUrusan);//disabled class=disabled
			//context.put("selectSuburusan", UtilHTML.SelectSuburusanByIdUrusan("2","23,25", "socSuburusan", null,""));
	 		socSubUrusan = HTML.SelectSuburusanByIdUrusan(idurusan,"socSubUrusan",fail.getIdSubUrusan(), "");
	 		if (idurusan.equals("1")){
	 	 		socSubUrusan = HTML.SelectSuburusanByIdUrusan(idurusan,"socSubUrusan",Utils.parseLong(IDSUBURUSANPERMOHONAN), "");	
	 		}else if (idurusan.equals("2")){
	 	 		socSubUrusan = UtilHTML.SelectSuburusanByIdUrusan(idurusan,IDSUBURUSANPEMBELIAN,"socSubUrusan",null, "");	
	 		}
	 		context.put("selectUrusan", socUrusan);
			context.put("selectSuburusan", socSubUrusan);
			context.put("statusTanah", HTML.SelectJenisTanah("socStatusTanah", htpPermohonan.getIdJenisTanah(), ""));
			context.put("socTarafKeselamatan", HTML.SelectTarafKeselamatan("socTarafKeselamatan", fail.getIdTarafKeselamatan(), "") );
			//context.put("idPermohonan", permohonan.getIdPermohonan());
			context.put("tajuk", permohonan.getTujuan());
			context.put("noFailKJP", htpPermohonan.getNoRujukanKJP());
			context.put("noFailLain", htpPermohonan.getNoRujukanLain());
			context.put("noFailUPT", htpPermohonan.getNoRujukanUPT());
			context.put("noFailPTG", htpPermohonan.getNoRujukanPTG());
			context.put("noFailPTD", htpPermohonan.getNoRujukanPTD());
			
			String mode="";
			context.put("inputstyleread", null );
			context.put("mode", mode);
			context.put("pageMode", "edit");
			vm= PATH+"file.jsp";
			
		}else if(command.equals("doChangeKementerianKemaskini")){
			//getSenaraiSemakFail();
			getValues();
			idNegeri = getParam("socNegeri");
			String idKementerian = getParam("socKementerian");
			String socKementerian = HTML.SelectKementerian("socKementerian", (idKementerian == "") ? null : Long.parseLong(idKementerian),"", " onChange=\"doChangeKementerianKemaskini()\" ");
			String socAgensi = HTML.SelectAgensiByKementerian("socAgensi",idKementerian,Long.parseLong("1"),"");
			String socNegeri = HTML.SelectNegeri("socNegeri",(idNegeri == "") ? null : Long.parseLong(idNegeri)," onChange=\"doChangeKementerianKemaskini()\" ");
			socDaerah = HTML.SelectDaerahByNegeri(idNegeri,"socDaerah",Utils.parseLong(htpPermohonan.getIdDaerah()),"","");
			context.put("selectKementerian",socKementerian);
			context.put("selectAgensi",socAgensi);
			context.put("socNegeri",socNegeri);
			context.put("socDaerah", socDaerah);
				//context.put("selectSuburusan", UtilHTML.SelectSuburusanByIdUrusan("2","23,25", "socSuburusan", null,""));
			socUrusan = UtilHTML.SelectUrusanHTP("socUrusan",Utils.parseLong(idurusan),"onChange=\"doChangeKementerianKemaskini()\" ",idStringUrusan);//disabled class=disabled
				//context.put("selectSuburusan", UtilHTML.SelectSuburusanByIdUrusan("2","23,25", "socSuburusan", null,""));
			socSubUrusan = HTML.SelectSuburusanByIdUrusan(idurusan,"socSubUrusan",fail.getIdSubUrusan(), "");
			if (idurusan.equals("1")){
		 	 	socSubUrusan = HTML.SelectSuburusanByIdUrusan(idurusan,"socSubUrusan",Utils.parseLong(IDSUBURUSANPERMOHONAN), "");	
		 	}else if (idurusan.equals("2")){
		 	 	socSubUrusan = UtilHTML.SelectSuburusanByIdUrusan(idurusan,IDSUBURUSANPEMBELIAN,"socSubUrusan",null, "");	
		 	}
		 	context.put("selectUrusan", socUrusan);
			context.put("selectSuburusan", socSubUrusan);
			context.put("statusTanah", HTML.SelectJenisTanah("socStatusTanah", htpPermohonan.getIdJenisTanah(), ""));
			context.put("socTarafKeselamatan", HTML.SelectTarafKeselamatan("socTarafKeselamatan", fail.getIdTarafKeselamatan(), "") );
				//context.put("idPermohonan", permohonan.getIdPermohonan());
			context.put("tajuk", permohonan.getTujuan());
			context.put("noFailKJP", htpPermohonan.getNoRujukanKJP());
			context.put("noFailLain", htpPermohonan.getNoRujukanLain());
			context.put("noFailUPT", htpPermohonan.getNoRujukanUPT());
			context.put("noFailPTG", htpPermohonan.getNoRujukanPTG());
			context.put("noFailPTD", htpPermohonan.getNoRujukanPTD());
				
			String mode="";
			context.put("inputstyleread", null );
			context.put("mode", mode);
			context.put("pageMode", "kemas");
			vm= PATH+"file.jsp";
				
		}else if(command.equalsIgnoreCase("simpan")){
			myLog.info("====Simpan====");
			getValues();
			//viewDetail2();
			//getSenaraiSemakFail();
			htpPermohonan = getIHTP().simpanPermohonan(htpPermohonan);
			//myLog.info("====Simpan_View====");		
			htpPermohonan = getIHTP().findPermohonan(String.valueOf(permohonan.getIdPermohonan()),String.valueOf(htpPermohonan.getIdHtpPermohonan()));			
			viewDetail2();
			//simpanSenaraiSemakFail(String.valueOf(htpPermohonan.getPermohonan().getIdPermohonan()));
			context.put("htpPermohonan", htpPermohonan);
			context.put("pageMode", "update");
			context.put("inputstyleread", readonly );
			vm= PATH+"file.jsp";
			
		}else if(command.equalsIgnoreCase("update")){
			getValues();
			myLog.info("===Kemaskini====");
			htpPermohonan = getIHTP().findPermohonan(String.valueOf(permohonan.getIdPermohonan()),String.valueOf(htpPermohonan.getIdHtpPermohonan()));
			
			readonlysockemaskini = "";
			viewDetail2();
			getKementerianDetailExist(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdKementerian()),String.valueOf(htpPermohonan.getIdAgensi()),"edit");
//			socUrusan = UtilHTML.SelectUrusanHTP("socUrusan",Utils.parseLong(idurusan),"onChange=\"doChangeKementerian()\" ",idStringUrusan);//disabled class=disabled
//	 		socSubUrusan = HTML.SelectSuburusanByIdUrusan(idurusan,"socSubUrusan",fail.getIdSubUrusan(), "");
//	 		if (idurusan.equals("1")){
//	 	 		socSubUrusan = HTML.SelectSuburusanByIdUrusan(idurusan,"socSubUrusan",Utils.parseLong(IDSUBURUSANPERMOHONAN), "");	
//	 		}else if (idurusan.equals("2")){
//	 	 		socSubUrusan = UtilHTML.SelectSuburusanByIdUrusan(idurusan,IDSUBURUSANPEMBELIAN,"socSubUrusan",null, "");	
//	 		}

			context.put("htpPermohonan", htpPermohonan);
			context.put("pageMode", "kemas");
			context.put("inputstyleread", "");
			context.put("inputstylereadnofail",readonly);
			//getSenaraiSemakFail();
			vm= PATH+"file.jsp";
			
		}else if(command.equalsIgnoreCase("detail")){
			myLog.info("===detail====");
			getValues();
			htpPermohonan = getIHTP().findPermohonanKutipan(idPermohonan,idHtpPermohonan);
			viewDetail2();
			context.put("htpPermohonan", htpPermohonan);
			context.put("pageMode", "update");
			context.put("page","1");
			context.put("inputstyleread", readonly);
			//getSenaraiSemakFail();			
			vm= PATH+"file.jsp";
			
		}else if(command.equalsIgnoreCase("simpanupdate")){
			myLog.info("===simpanKemaskini====");		
			getValues();
			viewDetail2();
			htpPermohonan = getIHTP().kemaskiniPermohonanKutipan(htpPermohonan,String.valueOf(permohonan.getIdPermohonan()),String.valueOf(htpPermohonan.getIdHtpPermohonan()));
			//simpanSenaraiSemakFail(getParam("txtidPermohonan"));
			context.put("htpPermohonan", htpPermohonan);
			context.put("pageMode", "update");
			context.put("inputstyleread", readonly);
			//getSenaraiSemakFail();
			vm= PATH+"file.jsp";
		
		}else if(command.equals("searchFail") || command.equals("indexPage")){
			String noFail = getParam("noFail")==""?null:getParam("noFail").trim();
			String carian = getParam("namaFail")==""?null:getParam("namaFail");
			idNegeri = getParam("socNegeri") == "" ? "0" : getParam("socNegeri");
			Vector<?> v = getIHTP().getSenaraiFailMengikutUrusanDanPengguna(idStringUrusan, carian, noFail,idNegeri);
			context.put("socNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri)));
			context.put("carianNoFail", noFail);
			context.put("carian", carian);
			setupPage(ses,action, v);
			vm = PATH+"index.jsp";
			
		}else{
			String noFail = getParam("noFail")==""?null:getParam("noFail");
			String carian = getParam("namaFail")==""?null:getParam("namaFail");
			idNegeri = getParam("socNegeri") == "" ? "0" : getParam("socNegeri");			
			Vector<?> v = getIHTP().getSenaraiFailMengikutUrusanDanPengguna(idStringUrusan, carian, noFail,idNegeri,userID);
			//myLog.info(action);
			if(action.contains("get"))
				v = getIHTP().getSenaraiFailMengikutUrusanDanPengguna(idStringUrusan, carian, noFail,idNegeri);
			
			context.put("socNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri)));
			setupPage(ses,action, v);
			vm = PATH+"index.jsp";
			
		}				
		return vm;
		
	}
	
	private void getKementerianDetail(String idKementerian,String mode)throws Exception{
		String disabled ="disabled";
		if(mode=="edit"){
			disabled ="";
		}
		
		String socKementerian = HTML.SelectKementerian("socKementerian", (idKementerian == "") ? null : Long.parseLong(idKementerian),disabled, " onChange=\"doChangeKementerian()\" ");
	    String socAgensi = HTML.SelectAgensiByKementerian("socAgensi","",Long.parseLong("1"),"");
	    context.put("selectKementerian",socKementerian);
		context.put("selectAgensi",socAgensi);
		
	}
	
	private void getKementerianDetailExist(String idKementerian,String idAgensi,String mode)throws Exception{
		String disabled ="disabled";
		if(mode=="edit"){
			disabled ="";
		}
		
		String socKementerian = HTML.SelectKementerian("socKementerian", (idKementerian == "") ? null : Long.parseLong(idKementerian),disabled, " onChange=\"doChangeKementerianKemaskini()\" ");
	    String socAgensi = HTML.SelectAgensiByKementerian("socAgensi",idKementerian,idAgensi==""?Long.parseLong("1"):Long.parseLong(idAgensi),"");
	    context.put("selectKementerian",socKementerian);
		context.put("selectAgensi",socAgensi);
			
	}

	private void getValues(){
		String idNegeri = getParam("socNegeri");
		String idDaerah = getParam("socDaerah");
		String idKementerian = getParam("socKementerian");
		String idUrusan = getParam("socUrusan")==""?"0":getParam("socUrusan");
		String idSubUrusan = getParam("socSubUrusan");
		String idAgensi = getParam("socAgensi");			
		String tajuk = getParam("txtTajuk");		
		String failKJP = getParam("txtNoFailKJP");
		String tarikhSuratKJP = getParam("txdTarikhSurKJP");
		String noFailLain = getParam("txtNoFailLain");
		String tarikhPermohonan = getParam("txdTarikhPermohonan");
		String idJenisTanah = getParam("socStatusTanah");
		String idKeselamatan = getParam("socTarafKeselamatan");
		String noFail = getParam("txtNoFailSek");
		String idPermohonan = getParam("txtidPermohonan");
		String idHtpPermohonan = getParam("txtidHtpPermohonan");
		String noFailUPT = getParam("txtnofailupt");
		String noFailPTG = getParam("txtnofailptg");
		String noFailPTD = getParam("txtnofailptd");
		String tarikhDaftarFail = getParam("txdtarikhbukafail");
		
		fail = new PfdFail();
		permohonan = new Permohonan();
		htpPermohonan = new HtpPermohonan();
		
		fail.setIdNegeri(idNegeri);
		fail.setIdKementerian(idKementerian);
		fail.setIdUrusan(Long.parseLong(String.valueOf(idUrusan)));
		fail.setIdSubUrusan(idSubUrusan);
		fail.setIdTarafKeselamatan(idKeselamatan);
		fail.setNoFail(noFail);
		fail.setTarikhDaftarFail(tarikhDaftarFail);
		//senaraiFail = new FrmSenaraiFailTerimaPohonData();
		//senaraiFail.getKementerianByMampu(Integer.parseInt(idKementerian));
		
		htpPermohonan.setIdHtpPermohonan(idHtpPermohonan);
		htpPermohonan.setIdDaerah(idDaerah);
		htpPermohonan.setIdAgensi(idAgensi);
		permohonan.setTujuan(tajuk);
		htpPermohonan.setNoRujukanKJP(failKJP);
		permohonan.setTarikhSurat(tarikhSuratKJP);
		htpPermohonan.setNoRujukanLain(noFailLain);
		permohonan.setTarikhTerima(tarikhPermohonan);
		htpPermohonan.setIdJenisTanah(idJenisTanah);
		permohonan.setIdPermohonan(idPermohonan);
		permohonan.setIdMasuk(Long.parseLong(userID));
		htpPermohonan.setNoRujukanUPT(noFailUPT);
		htpPermohonan.setNoRujukanPTG(noFailPTG);
		htpPermohonan.setNoRujukanPTD(noFailPTD);
				
		permohonan.setPfdFail(fail);
		htpPermohonan.setPermohonan(permohonan);

		context.put("permohonan", htpPermohonan);
		
	}

	// Keadaan kemaskini
	private void viewDetail2() throws  Exception{	
		//String socNegeri = HTML.SelectNegeri("socNegeri",htpPermohonan.getPermohonan().getPfdFail().getIdNegeri(),readonlysoc+ "alt=\""+htpPermohonan.getPermohonan().getPfdFail().getIdNegeri()+readonlysoc);
		// baru
		String socNegeri = HTML.SelectNegeri("socNegeri",htpPermohonan.getPermohonan().getPfdFail().getIdNegeri(), "alt=\""+htpPermohonan.getPermohonan().getPfdFail().getIdNegeri()+"\" "+readonlysockemaskini,"onChange=\"doChangeKementerianKemaskini()\"");
		socDaerah = HTML.SelectDaerahByNegeri(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdNegeri()),"socDaerah",Utils.parseLong(htpPermohonan.getIdDaerah()),readonlysockemaskini," ");
		String socKementerian = HTML.SelectKementerian("socKementerian", htpPermohonan.getPermohonan().getPfdFail().getIdKementerian()," disabled class=\"disabled\"");
		//// baru 		String socKementerian = HTML.SelectKementerian("socKementerian", htpPermohonan.getPermohonan().getPfdFail().getIdKementerian()," ");

		String socAgensi = HTML.SelectAgensiByKementerian("socAgensi",String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdKementerian()),htpPermohonan.getIdAgensi(),readonlysockemaskini);
		//socUrusan = UtilHTML.SelectUrusanHTP("socUrusan",htpPermohonan.getPermohonan().getPfdFail().getIdUrusan(),"onChange=\"doChangeKementerian()\""+readonlysoc,idStringUrusan);//disabled class=disabled
		// baru 
		socUrusan = UtilHTML.SelectUrusanHTP("socUrusan",htpPermohonan.getPermohonan().getPfdFail().getIdUrusan(),"onChange=\"doChangeKementerianKemaskini()\""+readonlysockemaskini,idStringUrusan);//disabled class=disabled
 		
		context.put("selectUrusan", socUrusan);
		context.put("selectKementerian",socKementerian);
		context.put("selectAgensi",socAgensi);
		context.put("socDaerah", socDaerah);
		context.put("socNegeri",socNegeri);
		//context.put("selectSuburusan", HTML.SelectSuburusanByIdUrusan(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdUrusan()), "socSubUrusan",htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan(),readonlysoc));
		context.put("selectSuburusan", HTML.SelectSuburusanByIdUrusan(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdUrusan()), "socSubUrusan",htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan(),readonlysockemaskini));
		context.put("statusTanah", HTML.SelectJenisTanah("socStatusTanah", htpPermohonan.getIdJenisTanah(), readonlysockemaskini));
		context.put("socTarafKeselamatan", HTML.SelectTarafKeselamatan("socTarafKeselamatan", htpPermohonan.getPermohonan().getPfdFail().getIdTarafKeselamatan(), readonlysockemaskini) );
		//context.put("selectSuburusan", HTML.SelectSuburusanByIdUrusan(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdUrusan()), "socSubUrusan",htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan(),""));
		//context.put("statusTanah", HTML.SelectJenisTanah("socStatusTanah", htpPermohonan.getIdJenisTanah(), ""));
		//context.put("socTarafKeselamatan", HTML.SelectTarafKeselamatan("socTarafKeselamatan", htpPermohonan.getPermohonan().getPfdFail().getIdTarafKeselamatan(), "") );
	
	}
	
	private IHtp getIHTP(){
		if(iHtp==null){
			iHtp = new HtpBean();
		}
		return iHtp;
		
	}
	

}


