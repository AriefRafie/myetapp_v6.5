package ekptg.view.htp;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.action.AjaxModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.htp.FrmSemakan;
import ekptg.model.htp.HakmilikUrusan;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHtp;
import ekptg.model.htp.PihakBerkepentingan;
import ekptg.model.htp.UtilHTML;
import ekptg.model.htp.entity.Bangunan;
import ekptg.model.htp.entity.HtpPermohonan;
import ekptg.model.htp.entity.Pemohon;
import ekptg.model.htp.entity.Permohonan;
import ekptg.model.htp.entity.PfdFail;
import ekptg.model.htp.pembelian.IPembelian;
import ekptg.model.htp.pembelian.IPemilik;
import ekptg.model.htp.pembelian.PembelianBean;
import ekptg.model.htp.pembelian.PemilikBean;

public class FrmPendaftaranFail extends AjaxModule {

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
	static Logger myLog = Logger.getLogger(ekptg.view.htp.FrmPendaftaranFail.class);
	private String vm = PATH+"index.jsp";
	private String userID = null;
	private IHtp iHtp = null;
	private IPembelian iPembelian = null;
	private IPemilik iPemilik = null;
	private String readonly = " readonly=\"readonly\" class=\"disabled\"";
	private String readonlysoc = " disabled class=\"disabled\"";
	private String readonlysockemaskini = readonlysoc;
	private String idNegeri ="0";
	private String idStringUrusan = "";
	private String idurusan = "";
	private String idsuburusan = "";
	private String socDaerah = "";
	private String socNegeri = "";
	//private String idnegeri = "";
	private String iddaerah = "";

	private String socUrusan = "";
	private String socSubUrusan = "";
	private final String URUSAN_TANAH = "01";
	private final String URUSAN_BANGUNAN = "03";
	private final String ID_URUSANTANAH = "24";
	private final String ID_URUSANTANAH1 = "25";
	
	private PfdFail fail = null;
	private Permohonan permohonan = null;
	private HtpPermohonan htpPermohonan = null;
	private HakmilikUrusan urusan = null;
	private PihakBerkepentingan pemilik = null;
	private Pemohon pemohon;
	private Bangunan bangunan = null;
	//private FrmSenaraiFailTerimaPohonData senaraiFail = null;
	
	@Override
	public String doAction() throws Exception {
		HttpSession ses = request.getSession();
		userID = (String)ses.getAttribute("_ekptg_user_id");
		String command = getParam("command");
		String action = getParam("action");
		String idPermohonan = getParam("idPermohonan");
		String idHtpPermohonan = getParam("idHtpPermohonan");
		String idHakmilikUrusan =  getParam("idHakmilikUrusan");
		context.put("URUSAN_BANGUNAN",URUSAN_BANGUNAN);
		context.put("URUSAN_TANAH",ID_URUSANTANAH);
		context.put("URUSAN_TANAH1",ID_URUSANTANAH1);
		
	 	idurusan = getParam("socUrusan");
	 	if ("".equals(idurusan)) idurusan="-1";
	 	idsuburusan = getParam("socSubUrusan");

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
			String noFail = getParam("noFail")==""?null:getParam("noFail");
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
			if(action.contains("getPage"))
				v = getIHTP().getSenaraiFailMengikutUrusanDanPengguna(idStringUrusan, carian, noFail,idNegeri);
			
			context.put("socNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri)));
			setupPage(ses,action, v);
			vm = PATH+"index.jsp";
			
		}
				
		return vm;
		
	}
	
	private void getSenaraiSemakan(){
		
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
	
	private void getListItem()throws Exception{
		context.put("selectKementerian",HTML.SelectKementerian("socKementerian"));
		context.put("selectAgensi",HTML.SelectAgensi("socAgensi"));
		//HTML.SelectStatus(selectName, selectedValue, disability)
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
	
	private void getValuesMaklumatTanah(){
		
		String idPermohonan = getParam("txtidPermohonan");
		String idNegeri = getParam("socNegeri");
		String idDaerah = getParam("socDaerah");
		String idMukim = getParam("socMukim");
		String idHakmilik = getParam("socJenisHakmilik");
		String idKodLot = getParam("socLot");
		String noLot = getParam("txtNoLot");
		String idRizab = getParam("socRizab");
		String tarikhMula = getParam("txdTarikhTerima");
		String tarikhLuput = getParam("txdTarikhLuput");
		String luasTanah = getParam("txtLuas");		
		String noPelanAkui = getParam("txtNoPelan");
		String noHakMilik = getParam("txtNoHakmilik");
		String unitLuas = getParam("socLuas");
		String kategoriTanah = getParam("socKategori");
		String idSubkategoriTanah = getParam("");
		String peganganHakmilik = getParam("");
		String noBangunan = getParam("noBangunan");
		String noTingkat = getParam("noTingkat");
		String noPetak = getParam("noPetak");	
		String idHakmilikUrusan = getParam("txtIdHakmilikUrusan");
		String statusTanah = getParam("socTaraf");
		String tempohBaki = getParam("txtTempoh");
		String tempohBakiTanah = getParam("txtTempohbaki");
		
		String ddownHakmilik = getParam("ddownHakmilik");
		String NamaPemaju = getParam("txtNamaPemaju");
		String NoRuj = getParam("txtNoRuj");
		String Alamat1 = getParam("txtAlamat1");
		String Alamat2 = getParam("txtAlamat2");
		String Alamat3 = getParam("txtAlamat3");
		String Poskod = getParam("txtPoskod");
		String NoTelefon = getParam("txtNoTelefon");
		String NoFax = getParam("txtNoFax");

		urusan = new HakmilikUrusan();
		permohonan = new Permohonan();
		
		//urusan.setDdownHakmilik(ddownHakmilik);
		urusan.setIdhakmilikurusan(idHakmilikUrusan);
		urusan.setIdNegeri(idNegeri);
		urusan.setIdDaerah(idDaerah);
		urusan.setIdMukim(idMukim);
		urusan.setIdHakmilik(idHakmilik);
		urusan.setIdLot(idKodLot);
		urusan.setNolot(noLot);
		urusan.setIdJenisRizab(idRizab);
		urusan.setTarikhMula(tarikhMula);
		urusan.setTarikhMula(tarikhMula);
		urusan.setTarikhLuput(tarikhLuput);
		urusan.setLuas(luasTanah);
		urusan.setNoPlan(noPelanAkui);
		urusan.setNohakmilik(noHakMilik);
		urusan.setIdLuas(unitLuas);
		urusan.setIdKategoriTanah(kategoriTanah);
		urusan.setIdSubKetegoriTanah(idSubkategoriTanah);
		urusan.setNoBangunan(noBangunan);
		urusan.setNoTingkat(noTingkat);
		urusan.setNoPetak(noPetak);
		urusan.setPeganganHakmilik(peganganHakmilik);
		urusan.setNoBangunan(noBangunan);
		urusan.setNoTingkat(noTingkat);
		urusan.setNoPetak(noPetak);
		urusan.setStatusTanah(statusTanah);
		urusan.setTempohTanah(tempohBaki);
		urusan.setTempohBakiTanah(tempohBakiTanah);

		permohonan.setIdPermohonan(idPermohonan);
		
		urusan.setPermohonan(permohonan);
		context.put("urusan", urusan);
		
	}
	
	private void getValuesPemilik(){
		String idNegeri = getParam("socNegeri");
		String idDaerah = getParam("socDaerah");
		String selectJenisNoPB = getParam("selectJenisNoPB");
		String idHakmilikUrusan = getParam("txtIdHakmilikUrusan");
		String ddownHakmilik = getParam("ddownHakmilik");
		String namaPemaju = getParam("txtNamaPemaju");
		String noRuj = getParam("txtNoRuj");
		String alamat1 = getParam("txtAlamat1");
		String alamat2 = getParam("txtAlamat2");
		String alamat3 = getParam("txtAlamat3");
		String poskod = getParam("txtPoskod");
		String noTelefon = getParam("txtNoTelefon");
		String noFax = getParam("txtNoFax");
		String Idpihakberkepentingan =getParam("Idpihakberkepentingan");
	
		pemilik = new PihakBerkepentingan();
		
		pemilik.setIdpihakberkepentingan(Idpihakberkepentingan);
		pemilik.setIdHakmilikUrusan(ddownHakmilik);
		pemilik.setJenisPB(selectJenisNoPB);
		pemilik.setDdownHakmilik(ddownHakmilik);
		pemilik.setIdNegeri(idNegeri);
		pemilik.setIdDaerah(idDaerah);
		pemilik.setIdHakmilikurusanPB(idHakmilikUrusan);
		pemilik.setNama(namaPemaju);
		pemilik.setAlamat1(alamat1);
		pemilik.setAlamat2(alamat2);
		pemilik.setAlamat3(alamat3);
		pemilik.setNoRujukan(noRuj);
		pemilik.setPoskod(poskod);
		pemilik.setTel(noTelefon);
		pemilik.setFax(noFax);

		context.put("pemilik", pemilik);
		
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

	private void tambahTanahDetail() throws  Exception{
		String idPermohonan = getParam("txtidPermohonan")==""?"0":getParam("txtidPermohonan");
		String idHtpPermohonan = getParam("txtidHtpPermohonan")==""?"0":getParam("txtidHtpPermohonan");
		String socNegeri = getParam("socNegeri")==""?"0":getParam("socNegeri");
		String socDaerah = getParam("socDaerah") ==""?"0":getParam("socDaerah");
		
		String socLot = getParam("socLot") ==""?"0":getParam("socLot");
		htpPermohonan = getIPembelian().findPermohonan(idPermohonan, idHtpPermohonan);
		context.put("socNegeri",HTML.SelectNegeri("socNegeri",htpPermohonan.getPermohonan().getPfdFail().getIdNegeri(),"disabled"));
		context.put("socDaerah", HTML.SelectDaerahByNegeri(String.valueOf(htpPermohonan.getPermohonan().getPfdFail().getIdNegeri()), "socDaerah",Long.parseLong(socDaerah),null," onChange=\"doChangeDaerah()\" "));
		context.put("socMukim",HTML.SelectMukimByDaerah(socDaerah, "socMukim"));
		context.put("socJenisHakmilik" , HTML.SelectJenisHakmilik("socJenisHakmilik"));
		context.put("socLot", HTML.SelectLot("socLot", Long.parseLong(socLot), ""));
		context.put("socRizab", HTML.SelectRizab("socRizab"));
		context.put("socLuas", HTML.SelectLuas("socLuas"));
		context.put("socKategori", HTML.SelectKategori("socKategori"));
		context.put("htpPermohonan", htpPermohonan);
	
	}
	
	private void tambahTanahDetail2() throws  Exception{
		String idPermohonan = getParam("txtidPermohonan")==""?"0":getParam("txtidPermohonan");
		String idHtpPermohonan = getParam("txtidHtpPermohonan")==""?"0":getParam("txtidHtpPermohonan");
		String socNegeri = getParam("socNegeri")==""?"0":getParam("socNegeri");
		String socDaerah = getParam("socDaerah") ==""?"0":getParam("socDaerah");
		String idHakmilikUrusan = getParam("idHakmilikUrusan") ==""?"0":getParam("idHakmilikUrusan");
		htpPermohonan = getIPembelian().findPermohonan(idPermohonan, idHtpPermohonan);
		//urusan = getIPembelian().findByHakmilikUrusanId(idHakmilikUrusan);
		context.put("socNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(urusan.getIdNegeri()),"disabled"));
		//context.put("socDaerah", HTML.SelectDaerahby("socDaerah", Long.parseLong(urusan.getIdDaerah()), ""));
		context.put("socDaerah", HTML.SelectDaerahByNegeri(urusan.getIdNegeri(), "socDaerah",Long.parseLong(urusan.getIdDaerah()),null," onChange=\"doChangeDaerah()\" "));
		context.put("socMukim",HTML.SelectMukimByDaerah(urusan.getIdDaerah(), "socMukim", Long.parseLong(urusan.getIdMukim()), ""));
		context.put("socJenisHakmilik" , HTML.SelectJenisHakmilik("socJenisHakmilik", Long.parseLong(urusan.getIdHakmilik()), ""));
		context.put("socLot", HTML.SelectLot("socLot", Long.parseLong(urusan.getIdLot()), ""));
		context.put("socRizab", HTML.SelectRizab("socRizab", Long.parseLong(urusan.getIdJenisRizab()), ""));
		context.put("socLuas", HTML.SelectLuas("socLuas", Long.parseLong(urusan.getIdLuas()), ""));
		context.put("socKategori", HTML.SelectKategori("socKategori", Long.parseLong(urusan.getIdKategoriTanah()), ""));
		context.put("htpPermohonan", htpPermohonan);
		
	}
	
	private void tambahTanahDetail3() throws  Exception{
		String idPermohonan = getParam("txtidPermohonan")==""?"0":getParam("txtidPermohonan");
		String idHtpPermohonan = getParam("txtidHtpPermohonan")==""?"0":getParam("txtidHtpPermohonan");
		String socNegeri = getParam("socNegeri")==""?"0":getParam("socNegeri");
		String socDaerah = getParam("socDaerah") ==""?"0":getParam("socDaerah");
		String idHakmilikUrusan = getParam("idHakmilikUrusan") ==""?"0":getParam("idHakmilikUrusan");
		String dd = getParam("ddownHakmilik")==""?"0":getParam("ddownHakmilik");
		Long selectJenisNoPB = Long.parseLong(getParam("selectJenisNoPB")==""?"0":getParam("selectJenisNoPB"));
		
		htpPermohonan = getIPembelian().findPermohonan(idPermohonan, idHtpPermohonan);

		context.put("socNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(socNegeri),""," onChange=\"doChangeDaerah2()\" "));
		context.put("socDaerah", HTML.SelectDaerahByNegeri(socNegeri, "socDaerah"));
		context.put("selectJenisNoPB", HTML.SelectJenisNoPb("selectJenisNoPB", selectJenisNoPB, ""));
		context.put("htpPermohonan", htpPermohonan);
	
	}
	
	private IPembelian getIPembelian(){
		if (iPembelian==null){
			iPembelian=new PembelianBean();
		}
		return iPembelian;
	
	} 
	
	private IPemilik getIPemilik(){
		if(iPemilik==null){
			iPemilik = new PemilikBean();
		}
		return iPemilik;
		
	}
	
	private IHtp getIHTP(){
		if(iHtp==null){
			iHtp = new HtpBean();
		}
		return iHtp;
		
	}
	
	private void getPermohonanInfo()throws Exception{
		String idPermohonan = getParam("txtidPermohonan");
		String idHtpPermohonan = getParam("txtidHtpPermohonan");
		htpPermohonan = getIPembelian().findPermohonan(idPermohonan,idHtpPermohonan);
		context.put("htpPermohonan", htpPermohonan);
	}
	
	private void getPenjualDetails(){
		String idPenjual = getParam("idPenjual");
		String idPermohonan = getParam("txtidPermohonan");
		String flag = getParam("PenjualSama");
		String nama = getParam("txtNamaPenjual");
		String ic =getParam("txtKodPenjual");
		String alamat1 = getParam("txtAlamat1");
		String alamat2 = getParam("txtAlamat2");
		String alamat3 = getParam("txtAlamat3");
		String poskod = getParam("txtPoskod");
		String idNegeri = getParam("selectNegeriP");
		String idDaerah = getParam("selectDaerahP");
		String tel = getParam("txtNoTelefon");
		String fax = getParam("txtNoFax");
		String noPA = getParam("txtNoPA");
		
		permohonan = new Permohonan();
		permohonan.setIdPermohonan(idPermohonan);
		pemohon = new Pemohon();
		pemohon.setIdPemohon(idPenjual);
		pemohon.setFlagPemilik(flag);
		pemohon.setNama(nama);
		pemohon.setNoPemohon(ic);
		pemohon.setAlamat1(alamat1);
		pemohon.setAlamat2(alamat2);
		pemohon.setAlamat3(alamat3);
		pemohon.setPermohonan(permohonan);
		pemohon.setPoskod(poskod);
		pemohon.setIdNegeri(idNegeri);
		pemohon.setIdDaerah(idDaerah);
		pemohon.setTel(tel);
		pemohon.setFax(fax);
		pemohon.setNoPA(noPA);
		
		context.put("pemohon", pemohon);
	
	}
	
	private void getBangunanValues(){
		String idBangunan = getParam("idBangunan");
		String idHakmilikUrusan = getParam("idHakmilikUrusan");
		String noPetak= getParam("noPetak");
		String noBangunan = getParam("noBangunan");
		String noTingkat = getParam("noTingkat");
		
		bangunan = new Bangunan();
		urusan = new HakmilikUrusan();
		urusan.setIdhakmilikurusan(idHakmilikUrusan);
		bangunan.setIdBangunan(idBangunan);
		bangunan.setHakmilikUrusan(urusan);
		bangunan.setNoPetak(noPetak);
		bangunan.setNoBangunan(noBangunan);
		bangunan.setNoTingkat(noTingkat);
		
		context.put("bangunan", bangunan);
	}
	
	private void getSenaraiSemakFail()throws Exception{
		context.put("senaraiSemakan", FrmSemakan.getSenaraiSemakan("frmPembelianSemakNew"));
		context.put("semakclass", new FrmSemakan());
	
	}
	
	private void getSemakanPerakuanPembelian()throws Exception{
		context.put("semakclass", new FrmSemakan());
		Vector semakList = FrmSemakan.getSenaraiSemakan("frmBelianAkuan");
		context.put("perakuanPembelian", semakList);
	
	}
	
	private void getdoChange() throws Exception {
		String selectNegeriP = getParam("selectNegeriP");
		String selectDaerahP = getParam("selectDaerahP")==""?"0":getParam("selectDaerahP");
		context.put("selectNegeriP", HTML.SelectNegeri("selectNegeriP", Long.parseLong(selectNegeriP),""," onChange=\"doChangePenjualNegeri()\" "));
		context.put("selectDaerahP", HTML.SelectDaerahByNegeri(selectNegeriP, "selectDaerahP",Long.parseLong(selectDaerahP),""));
		
	}
	
	private void simpanSenaraiSemakFail(String idPermohonan)throws Exception{
		String[] cbsemaks = this.request.getParameterValues("cbsemaks");
		FrmSemakan frmSemak = new FrmSemakan();
		frmSemak.semakanHapusByPermohonan(String.valueOf(idPermohonan));
		if (cbsemaks != null) {
			for (int i = 0; i < cbsemaks.length; i++) {
				frmSemak = new FrmSemakan();
				frmSemak.semakanTambah(cbsemaks[i], String.valueOf(idPermohonan));
	
			}
		}
		
		
	}
	
	private void simpanSenaraiSemakAkuanPembelian(String idPermohonan)throws Exception{
		String[] cbsemaks = this.request.getParameterValues("akuans");
		FrmSemakan frmSemak = new FrmSemakan();
		frmSemak.semakanHapusByPermohonan(String.valueOf(idPermohonan));
		if (cbsemaks != null) {
			for (int i = 0; i < cbsemaks.length; i++) {
				frmSemak = new FrmSemakan();
				frmSemak.semakanTambah(cbsemaks[i], String.valueOf(idPermohonan));
			}
		}
		
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
		this.context.put("lists",paging.getPage(page));
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
	
}


