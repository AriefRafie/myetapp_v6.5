package ekptg.view.htp.negeri.cukai;

import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.InternalUserUtil;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.entities.InternalUser;
import ekptg.model.entities.Tblrujmukim;
import ekptg.model.entities.Tblrujsuburusanstatusfail;
import ekptg.model.htp.FrmCukaiKemaskiniDataBaru;
import ekptg.model.htp.FrmCukaiPenyataData;
import ekptg.model.htp.FrmCukaiSenaraiFailExcelUpload;
import ekptg.model.htp.FrmUtilData;
import ekptg.model.htp.HTPStatusBean;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHTPStatus;
import ekptg.model.htp.IHtp;
import ekptg.model.htp.cukai.CukaiBean;
import ekptg.model.htp.cukai.FrmCukaiPenyataBean;
import ekptg.model.htp.cukai.ICukai;
import ekptg.model.htp.cukai.entity.CukaiTemp;
import ekptg.model.htp.entity.HakMilik;
import ekptg.model.htp.entity.HakmilikCukai;
import ekptg.model.htp.entity.HtpPermohonan;
import ekptg.model.htp.entity.Permohonan;
import ekptg.model.htp.entity.PfdFail;
import ekptg.model.htp.online.IOnline;
import ekptg.model.htp.online.OnlineBean;
import ekptg.model.htp.utiliti.HTPRoleBean;
import ekptg.model.htp.utiliti.IHTPRole;

@SuppressWarnings("serial")
public class FrmCukaiPelarasanNegeri extends AjaxBasedModule{
	
	private final String PATH="app/htp/negeri/cukai/";
	private final String PATHCUKAI="app/htp/cukai/";
	private String DISABILITY = " disabled class=disabled ";
	static Logger myLog = Logger.getLogger(ekptg.view.htp.negeri.cukai.FrmCukaiPelarasanNegeri.class);
	String socNegeri = "";
	String socDaerah = "";
	String socMukim = "";
	String idnegeri = "";
 	String iddaerah = "";
 	String idmukim = "";
    String isCarian = "tidak";
	String userId = "";
	private IHtp iHTP = null;  
	private InternalUser iu = null;
	private ICukai iCukai = null;
	private IOnline iOnline = null;
	private IHTPStatus iStatus = null;
	private IHTPRole iRole= null;  
	private String year = "";
	private String socTahun = "";
	private String bil ="1";
	private HakMilik hakmilik = null;
	private String noHakmilik = "";
	private Tblrujsuburusanstatusfail subUrusanStatusFail = null;

	@Override
	public String doTemplate2() throws Exception {
		
		String template_name = PATH+"index.jsp";	
		HttpSession session = this.request.getSession();
		String action = getParam("action");
		String modepage = getParam("mode1");
		String submit = getParam("command");
		Vector SenaraiFailTemp = null;
		Vector SenaraiFailOrig = null;
		context.put("UTIL", new ekptg.helpers.Utils());
		
		userId = (String)session.getAttribute("_ekptg_user_id");
		iu = InternalUserUtil.getSeksyenId(userId);
		idnegeri = iu.getIdNegeri();
		context.put("idNegeri", idnegeri);
	 	iddaerah = getParam("socDaerah");
	 	idmukim = getParam("socMukim");
	 	socTahun = getParam("Form_tahun")==null||getParam("Form_tahun")==""?lebah.util.Util.getDateTime(new Date(), "yyyy"):getParam("Form_tahun");
		context.remove("SenaraiFailOrig");
		String tahun = getParam("tahun");
	 	noHakmilik = getParam("txtnohakmilik");
		myLog.info("action="+action);
		myLog.info("action="+modepage);
		myLog.info("action="+submit);

		if(submit.equals("CukaiKemaskini")){
			String idCukaiTemp ="";
			String idCukaiTerperinci = "";
			bil = getParam("bil");
			idCukaiTerperinci = getParam("id_cukaiterperinci"+bil);
			idCukaiTemp = getParam("idcukaitemp"+bil);

			getICukaiPenyata().cukaiTempHapus(idCukaiTemp);
			getICukaiPenyata().cukaiTerperinciHapus(idCukaiTerperinci);
			SenaraiFailOrig = (Vector<?>)getICukaiPenyata().senaraiHakmilik(idnegeri,iddaerah,idmukim,socTahun);
			this.context.put("SenaraiFailOrig", SenaraiFailOrig);
			socNegeri = HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri)," disabled=\"disabled\" ","onChange=doChangeNegeriX();");
			socDaerah = HTML.SelectDaerahByNegeri(idnegeri, "socDaerah",Utils.parseLong(iddaerah),null, "onChange=\"doChangeDaerahX()\"");
			socMukim = HTML.SelectMukimByDaerah(iddaerah, "socMukim", Utils.parseLong(idmukim) , "");
			
		}else if(submit.equals("simpanpengesahan")){
			myLog.info("simpanpengesahan");
			PfdFail fail = null;
			Permohonan permohonan = null;
			HtpPermohonan htpPermohonan = null;
			String semakMode="";
			String userLevel = "0";
		/*
			 * Langkah 1  untuk status - 
			 * Langkah 2 untuk status - MAKLUMAT CUKAI TANAH (NEGERI)
			 * Langkah 3 untuk status - TINDAKAN PEGAWAI (NEGERI)
			 * Langkah 4 untuk status - TINDAKAN PENGARAH (NEGERI) 
			 * Langkah 5 untuk status - MAKLUMAT CUKAI TANAH
			*/
			String langkah = getParam("statussemasa");
			myLog.info("simpanpengesahan langkah="+langkah);
			//(HTP)PenggunaNegeri 
			if(getRole().isUserRoleUsers(userId,"%Pengguna%%Negeri%")) {
				userLevel = "1";
			
			// (HTP)PegawaiNegeri
			}else if(getRole().isUserRoleUsers(userId,"%Pegawai%%Negeri%")){ 
				userLevel = "2";
			
			// (HTP)PengarahNegeri
			}else if(getRole().isUserRoleUsers(userId,"%Pengarah%%Negeri%")){
				userLevel = "3";
			}

			Vector senaraiFail = FrmCukaiPenyataData.getList("11","","",Long.parseLong(String.valueOf(idnegeri)));
			Hashtable hashFail = (Hashtable) senaraiFail.get(0);
			
			String idPermohonan = String.valueOf(hashFail.get("idPermohonan"));
			Long idFail = Long.parseLong(String.valueOf(hashFail.get("idFail")));				
	    	fail = new PfdFail();
	 		permohonan = new Permohonan();
	 		htpPermohonan = new HtpPermohonan();
 			permohonan.setIdPermohonan(idPermohonan);
 			fail.setIdFail(idFail);
 			fail.setIdSubUrusan("43");
 			permohonan.setPfdFail(fail);
	 		htpPermohonan.setPermohonan(permohonan);
			context.put("htpPermohonan", htpPermohonan);

			Tblrujsuburusanstatusfail rsusf = new Tblrujsuburusanstatusfail();
			rsusf.setIdPermohonan(htpPermohonan.getPermohonan().getIdPermohonan());
			rsusf.setIdFail(htpPermohonan.getPermohonan().getPfdFail().getIdFail());
			rsusf.setIdSuburusanstatusfail(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan());
			rsusf.setUrl("-");
			simpanPengesahan(rsusf,langkah);

			SenaraiFailOrig = (Vector<?>)getICukaiPenyata().senaraiHakmilik(idnegeri,iddaerah,idmukim,socTahun);
			this.context.put("SenaraiFailOrig", SenaraiFailOrig);
			socNegeri = HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri)," disabled=\"disabled\" ","onChange=doChangeNegeriX();");
			socDaerah = HTML.SelectDaerahByNegeri(idnegeri, "socDaerah",Utils.parseLong(iddaerah),null, "onChange=\"doChangeDaerahX()\"");
			socMukim = HTML.SelectMukimByDaerah(iddaerah, "socMukim", Utils.parseLong(idmukim) , "");
			context.put("userLevel", userLevel);
						
		}else if("XCetakSenaraiKemaskini".equals(submit)){
     		template_name = "app/htp/frmCukaiKemaskiniDataExcel2.jsp";
     		Vector senaraikemaskini = FrmCukaiSenaraiFailExcelUpload.getCukaiKemaskiniList();
    		this.context.put("senaraikemaskini", senaraikemaskini);
		
		}else if(submit.equals("tambahakmilik")){
     		template_name = PATH+"senaraiHakmilik.jsp";
			myLog.info("default");
			isCarian = getParam("carian");
		    String noLot = getParam("txtNoLot")==""?"":getParam("txtNoLot");
			if(isCarian.equals("ya")){
				myLog.info("default:isCarian");
				SenaraiFailOrig = getICukai().senaraiHakmilik(idnegeri,iddaerah,idmukim,socTahun,noHakmilik,"",noLot);
				isCarian = "ya";	
				
			}
			socNegeri = HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri),DISABILITY,"onChange=doChangeNegeriX();");
			socDaerah = HTML.SelectDaerahByNegeri(idnegeri, "socDaerah",Utils.parseLong(iddaerah),null, "onChange=\"cmdChangeDaerahHakmilik()\"");
			socMukim = HTML.SelectMukimByDaerah(iddaerah, "socMukim", Utils.parseLong(idmukim) , "");
			
			//setupPage(session,action,senaraiHakmilik);
			this.context.put("carianNoLot", noLot);
			this.context.put("count", SenaraiFailOrig);
			this.context.put("carianNoHakmilik", noHakmilik);
			
		}else if("CukaiSimpan".equals(submit)){	
			template_name = PATH+"senaraiHakmilik.jsp";
				Hashtable data = null;
				data = new Hashtable();
				String perluBayar ="";
				String tunggakan = ""; 
				String denda = ""; 
				String taliAir = ""; 
				String parit  = "";
				String pengurangan = "";
				String lebihan = "";
				String pengecualian = "";
				//String cukaiKenaBayarLama = "";
				String cukaiJumlah = "";			
				bil = getParam("bil");
				//cukai_perlubayar = getParam("cukaiperlubayar"+bil);
				perluBayar = getParam("cukaiperlubayar");
				//taliAir = getParam("cukaitaliair"+bil);
				taliAir = getParam("cukaitaliair");				
				//parit = getParam("cukaiparit"+bil);
				parit = getParam("cukaiparit");
				//tunggakan = getParam("cukaitunggakan"+bil);
				tunggakan = getParam("cukaitunggakan");
				//denda = getParam("cukaidenda"+bil);
				denda = getParam("cukaidenda");
				//pengurangan = getParam("cukaipengurangan"+bil);
				pengurangan = getParam("cukaipengurangan");
				//pengecualian = getParam("cukaipengecualian"+bil);
				pengecualian = getParam("cukaipengecualian");
				//cukaiKenaBayarLama = getParam("cukaikenabayar");
				lebihan = getParam("cukailebihan");
				//cukaiJumlah = getParam("cukaijumlah"+bil);
				cukaiJumlah = getParam("cukaijumlah");
				String senaraiID_HAKMILIKCUKAI = getParam("idhakmilikcukai"+bil);
				
				HakMilik hakmilikBaru = new HakMilik();
				hakmilik = getICukai().getCukai(senaraiID_HAKMILIKCUKAI);		//TBLHTPCUKAITEMP		
				hakmilikBaru.setIdPermohonan(hakmilik.getIdPermohonan());		//TBLHTPCUKAITEMP
				hakmilikBaru.setIdNegeri(hakmilik.getNegeri().getIdNegeri());	//TBLHTPCUKAITEMP
				hakmilikBaru.setIdDaerah(hakmilik.getDaerah().getIdDaerah());	//TBLHTPCUKAITEMP
				hakmilikBaru.setIdMukim(hakmilik.getMukim().getIdMukim());		//TBLHTPCUKAITEMP
				CukaiTemp cukai = new CukaiTemp();
				Tblrujmukim mukim = new Tblrujmukim();
				mukim.setNamaMukim(hakmilik.getMukim().getNamaMukim());			//TBLHTPCUKAITEMP
				cukai.setRujMukim(mukim);
				hakmilikBaru.setKegunaan(hakmilik.getKegunaan());				//TBLHTPCUKAITEMP
				hakmilikBaru.setIdJenisLot(hakmilik.getIdJenisLot());			//TBLHTPCUKAITEMP
				hakmilikBaru.setNoLot(hakmilik.getNoLot());						//TBLHTPCUKAITEMP
				cukai.setNoLot(hakmilik.getRujLot().getKeterangan()+hakmilik.getNoLot());
//				hakmilikBaru.setNoFailPTD(hakmilik.getRujLot().getKeterangan()+hakmilik.getNoLot()); //TBLHTPCUKAITEMP
				hakmilikBaru.setIdJenisHakmilik(hakmilik.getRujJenisHakmilik().getIdJenishakmilik()); //TBLHTPCUKAITEMP
				hakmilikBaru.setNoHakmilik(hakmilik.getNoHakmilik());			//TBLHTPCUKAITEMP
				cukai.setNoHakmilik(hakmilik.getRujJenisHakmilik().getKodJenisHakmilik()+hakmilik.getNoHakmilik()); //TBLHTPCUKAITEMP
				cukai.setCukaiPerluBayar(Double.parseDouble(Utils.RemoveComma(perluBayar))); //TBLHTPCUKAITEMP
				cukai.setCukaiTaliAir(Double.parseDouble(Utils.RemoveComma(taliAir)));				//TBLHTPCUKAITEMP
				cukai.setCukaiParit(Double.parseDouble(Utils.RemoveComma(parit)));					//TBLHTPCUKAITEMP
				cukai.setCukailain(0.00);										//TBLHTPCUKAITEMP
				cukai.setTunggakkan(Double.parseDouble(Utils.RemoveComma(tunggakan)));				//TBLHTPCUKAITEMP
				cukai.setDenda(Double.parseDouble(Utils.RemoveComma(denda)));						//TBLHTPCUKAITEMP
				cukai.setPengurangan(Double.parseDouble(Utils.RemoveComma(pengurangan)));			//TBLHTPCUKAITEMP
				cukai.setPengecualian(Double.parseDouble(Utils.RemoveComma(pengecualian)));		//TB\\\\\\\\\\				cukai.setLebihan(Double.parseDouble(Utils.RemoveComma(lebihan)));											//TBLHTPCUKAITEMP
				cukai.setTotalcukai(Double.parseDouble(Utils.RemoveComma(cukaiJumlah)));			//TBLHTPCUKAITEMP
				cukai.setCukaiKenaBayar(Double.parseDouble(Utils.RemoveComma(cukaiJumlah)));			//TBLHTPCUKAITEMP
				cukai.setTahun(socTahun);										//TBLHTPCUKAITEMP
				hakmilikBaru.setHakmilikCTemp(cukai);
				hakmilikBaru.setIdMasuk(Long.parseLong(userId));				//TBLHTPCUKAITEMP
				
				//TBLHTPCUKAITERPERINCI
				HakmilikCukai hcukai = new HakmilikCukai();
				hcukai.setKodBayaranCukai(socTahun);
				hcukai.setCukaiTerkini(Double.parseDouble(Utils.RemoveComma(perluBayar)));
				hcukai.setTunggakan(Double.parseDouble(Utils.RemoveComma(tunggakan)));
				hcukai.setDenda(Double.parseDouble(Utils.RemoveComma(denda)));
				hcukai.setCukaiTaliAir(Double.parseDouble(Utils.RemoveComma(taliAir)));				
				hcukai.setCukaiParit(Double.parseDouble(Utils.RemoveComma(parit)));					
				hcukai.setPengurangan(Double.parseDouble(Utils.RemoveComma(pengurangan)));			
				hcukai.setPengecualian(Double.parseDouble(Utils.RemoveComma(pengecualian)));
				hcukai.setJumlah(Double.parseDouble(Utils.RemoveComma(cukaiJumlah)));
				hcukai.setIdHakmilikCukai(Long.parseLong(senaraiID_HAKMILIKCUKAI));
		    	hakmilikBaru.setHakmilikCukai(hcukai);

				getICukai().simpanCukaiHakmilikTemp(hakmilikBaru);
				getICukai().simpanCukaiHakmilikTerperinci(hakmilikBaru);

				//getICukai().salinanCukaiBaru(hakmilikBaru);
				SenaraiFailOrig = getICukai().senaraiHakmilik(idnegeri,iddaerah,idmukim,socTahun);
				socNegeri = HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri)," disabled=\"disabled\" ","onChange=doChangeNegeriX();");
				socDaerah = HTML.SelectDaerahByNegeri(idnegeri, "socDaerah",Utils.parseLong(iddaerah),null, "onChange=\"doChangeDaerahX()\"");
				socMukim = HTML.SelectMukimByDaerah(iddaerah, "socMukim", Utils.parseLong(idmukim) , "");

		}else if("cukaikemaskinisemua".equals(submit)){	
     		template_name = PATH+"senaraiHakmilik.jsp";
			myLog.info("cukaikemaskinisemua="+submit);
			Hashtable data = null;
			data = new Hashtable();
			String lebihan ="";
			String denda =""; 
			String tunggakan =""; 
			String taliair2 =""; 
			String taliparit2  ="";
			String cukai_kenabayar ="";
			
			String[] cukais = this.request.getParameterValues("cukaiperlubayar");
			String[] taliair2s = this.request.getParameterValues("cukaitaliair");
			String[] taliparit2s = this.request.getParameterValues("cukaiparit");
			String[] tunggakans = this.request.getParameterValues("cukaitunggakan");
			String[] dendas = this.request.getParameterValues("cukaidenda");
			String[] pengurangans = this.request.getParameterValues("cukaipengurangan");			
			String[] lebihans = this.request.getParameterValues("cukailebihan");
			String[] pengecualians = this.request.getParameterValues("cukaipengecualian");				
			//String[] cukaiKenaBayars = this.request.getParameterValues("cukaikenabayar");				
			String[] cukaiJumlahs = this.request.getParameterValues("cukaijumlah");			
			String[] senaraiNoLots = this.request.getParameterValues("senaraiNolot");
			String[] senaraiNoHakmilikUploads = this.request.getParameterValues("senaraiNO_HAKMILIKUPLOAD");			
			String[] senaraiIdHakmilikCukais = this.request.getParameterValues("senaraiID_HAKMILIKCUKAI");			
			String[] senaraiNoHakmiliks = this.request.getParameterValues("senaraiNO_HAKMILIK");	
			String[] cbsemaks = this.request.getParameterValues("cb");
			String[] cbsemaks_ = this.request.getParameterValues("cb_");
			bil = getParam("bil");
 			myLog.info("cukaikemaskinisemua:bil="+bil);
 			myLog.info("cukaikemaskinisemua:length="+cbsemaks_.length);
          	if(cbsemaks_!=null){
        		for (int i = 0; i < cbsemaks_.length; i++) { 
        			if(cbsemaks_[i].equals("true")){
        				HakMilik hakmilikBaru = new HakMilik();
        				hakmilik = getICukai().getCukai(getParam("senaraiID_HAKMILIKCUKAI"+(i+1)));		//TBLHTPCUKAITEMP		
        				hakmilikBaru.setIdPermohonan(hakmilik.getIdPermohonan());		//TBLHTPCUKAITEMP
        				hakmilikBaru.setIdNegeri(hakmilik.getNegeri().getIdNegeri());	//TBLHTPCUKAITEMP
        				hakmilikBaru.setIdDaerah(hakmilik.getDaerah().getIdDaerah());	//TBLHTPCUKAITEMP
        				hakmilikBaru.setIdMukim(hakmilik.getMukim().getIdMukim());		//TBLHTPCUKAITEMP
        				CukaiTemp cukai = new CukaiTemp();
        				Tblrujmukim mukim = new Tblrujmukim();
        				mukim.setNamaMukim(hakmilik.getMukim().getNamaMukim());			//TBLHTPCUKAITEMP
        				cukai.setRujMukim(mukim);
        				hakmilikBaru.setKegunaan(hakmilik.getKegunaan());				//TBLHTPCUKAITEMP
        				hakmilikBaru.setIdJenisLot(hakmilik.getIdJenisLot());			//TBLHTPCUKAITEMP
        				hakmilikBaru.setNoLot(hakmilik.getNoLot());						//TBLHTPCUKAITEMP
        				cukai.setNoLot(hakmilik.getRujLot().getKeterangan()+hakmilik.getNoLot());
//        				hakmilikBaru.setNoFailPTD(hakmilik.getRujLot().getKeterangan()+hakmilik.getNoLot()); //TBLHTPCUKAITEMP
        				hakmilikBaru.setIdJenisHakmilik(hakmilik.getRujJenisHakmilik().getIdJenishakmilik()); //TBLHTPCUKAITEMP
        				hakmilikBaru.setNoHakmilik(hakmilik.getNoHakmilik());			//TBLHTPCUKAITEMP
        				cukai.setNoHakmilik(hakmilik.getRujJenisHakmilik().getKodJenisHakmilik()+hakmilik.getNoHakmilik()); //TBLHTPCUKAITEMP
        				myLog.info("cukais[i]="+cukais[i]);
        				cukai.setCukaiPerluBayar(Double.parseDouble(Utils.RemoveComma(cukais[i])));
//        				cukai.setCukaiTaliAir(Double.parseDouble(Utils.RemoveComma(getParam("cukaitaliair"+(i+1)))));				//TBLHTPCUKAITEMP
        				cukai.setCukaiTaliAir(Double.parseDouble(Utils.RemoveComma(taliair2s[i])));				//TBLHTPCUKAITEMP
//        				cukai.setCukaiParit(Double.parseDouble(Utils.RemoveComma(getParam("cukaiparit"+(i+1)))));					//TBLHTPCUKAITEMP
        				cukai.setCukaiParit(Double.parseDouble(Utils.RemoveComma(taliparit2s[i])));					//TBLHTPCUKAITEMP
//        				cukai.setCukaiKenaBayar(Double.parseDouble(Utils.RemoveComma(getParam("cukaikenabayarlama"+(i+1))))); //TBLHTPCUKAITEMP
        				cukai.setCukaiKenaBayar(Double.parseDouble(Utils.RemoveComma(cukaiJumlahs[i]))); //TBLHTPCUKAITEMP
//        				cukai.setCukailain(0.00);										//TBLHTPCUKAITEMP
        				cukai.setCukailain(0.00);										//TBLHTPCUKAITEMP
//        				cukai.setTunggakkan(Double.parseDouble(Utils.RemoveComma(getParam("cukaitunggakan"+(i+1)))));				//TBLHTPCUKAITEMP
        				cukai.setTunggakkan(Double.parseDouble(Utils.RemoveComma(tunggakans[i])));				//TBLHTPCUKAITEMP
//        				cukai.setDenda(Double.parseDouble(Utils.RemoveComma(getParam("cukaidenda"+(i+1)))));						//TBLHTPCUKAITEMP
        				cukai.setDenda(Double.parseDouble(Utils.RemoveComma(dendas[i])));						//TBLHTPCUKAITEMP
//        				cukai.setPengurangan(Double.parseDouble(Utils.RemoveComma(getParam("cukaipengurangan"+(i+1)))));			//TBLHTPCUKAITEMP
        				cukai.setPengurangan(Double.parseDouble(Utils.RemoveComma(pengurangans[i])));			//TBLHTPCUKAITEMP
//        				cukai.setPengecualian(Double.parseDouble(Utils.RemoveComma(getParam("cukaipengecualian"+(i+1)))));		//TBLHTPCUKAITEMP
        				cukai.setPengecualian(Double.parseDouble(Utils.RemoveComma(pengecualians[i])));		//TBLHTPCUKAITEMP
//        				cukai.setLebihan(0.00);											//TBLHTPCUKAITEMP
        				cukai.setLebihan(Double.parseDouble(Utils.RemoveComma(lebihans[i])));											//TBLHTPCUKAITEMP
//        				cukai.setTotalcukai(Double.parseDouble(Utils.RemoveComma(getParam("cukaijumlah"+(i+1)))));			//TBLHTPCUKAITEMP
        				cukai.setTotalcukai(Double.parseDouble(Utils.RemoveComma(cukaiJumlahs[i])));			//TBLHTPCUKAITEMP
        				cukai.setTahun(socTahun);										//TBLHTPCUKAITEMP
        				hakmilikBaru.setHakmilikCTemp(cukai);
        				hakmilikBaru.setIdMasuk(Long.parseLong(userId));				//TBLHTPCUKAITEMP
        				
        				//TBLHTPCUKAITERPERINCI
        				HakmilikCukai hcukai = new HakmilikCukai();
        				hcukai.setKodBayaranCukai(socTahun);
        				hcukai.setCukaiTerkini(Double.parseDouble(Utils.RemoveComma(cukais[i])));
        				hcukai.setTunggakan(Double.parseDouble(Utils.RemoveComma(tunggakans[i])));
        				hcukai.setDenda(Double.parseDouble(Utils.RemoveComma(dendas[i])));
        				hcukai.setCukaiTaliAir(Double.parseDouble(Utils.RemoveComma(taliair2s[i])));				
        				hcukai.setCukaiParit(Double.parseDouble(Utils.RemoveComma(taliparit2s[i])));					
        				hcukai.setPengurangan(Double.parseDouble(Utils.RemoveComma(pengurangans[i])));			
        				hcukai.setPengecualian(Double.parseDouble(Utils.RemoveComma(pengecualians[i])));
        				hcukai.setJumlah(Double.parseDouble(Utils.RemoveComma(cukaiJumlahs[i])));
        				hcukai.setIdHakmilikCukai(Long.parseLong(getParam("senaraiID_HAKMILIKCUKAI"+(i+1))));
        		    	hakmilikBaru.setHakmilikCukai(hcukai);	 
        		    	
        				getICukai().simpanCukaiHakmilikTemp(hakmilikBaru);
        				getICukai().simpanCukaiHakmilikTerperinci(hakmilikBaru);
        				
        			}
        		}
        	} 
			SenaraiFailOrig = getICukai().senaraiHakmilik(idnegeri,iddaerah,idmukim,socTahun);
			socNegeri = HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri)," disabled=\"disabled\" ","onChange=doChangeNegeriX();");
			socDaerah = HTML.SelectDaerahByNegeri(idnegeri, "socDaerah",Utils.parseLong(iddaerah),null, "onChange=\"doChangeDaerahX()\"");
			socMukim = HTML.SelectMukimByDaerah(iddaerah, "socMukim", Utils.parseLong(idmukim) , "");
		
		}else if("salincukai".equals(submit)){	
     		template_name = PATH+"senaraiHakmilik.jsp";
			salinCukai(idnegeri,iddaerah,idmukim,socTahun);
			SenaraiFailOrig = getICukai().CukaiSenaraiKemaskiniFail(idnegeri,iddaerah,idmukim,socTahun);
			socNegeri = HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri)," disabled=\"disabled\" ","onChange=doChangeNegeriX();");
			socDaerah = HTML.SelectDaerahByNegeri(idnegeri, "socDaerah",Utils.parseLong(iddaerah),null, "onChange=\"doChangeDaerahX()\"");
			socMukim = HTML.SelectMukimByDaerah(iddaerah, "socMukim", Utils.parseLong(idmukim) , "");
			
    		// KEMASUKAN MANUAL CUKAI 
		}else if(submit.equals("XXmanual")){	
			getListItem();
			getSenaraiTahun(session, action);			
			template_name = PATH+"FrmCukaiSenaraiTahunFail.jsp";

		}else if(submit.equals("createNew")){
	    	template_name = PATH+"new.jsp";
			tahun = socTahun;
			//this.context.put("manualNegeri",HTML.SelectNegeri("manualNegeri", "onChange=\"doChangeDaerahManual()\"") );
	    	//this.context.put("manualDaerah", HTML.SelectDaerah("manualDaerah"));
			this.context.put("manualNegeri",HTML.SelectNegeri("manualNegeri", Utils.parseLong(idnegeri), " disabled class=\"disabled\" ", "onChange=\"doChangeDaerahManual()\"") );
	    	this.context.put("manualDaerah", HTML.SelectDaerahByNegeri(idnegeri, "manualDaerah", Utils.parseLong(iddaerah), null, "onChange=\"doChangeDaerahManual()\""));			

	    	this.context.put("manualMukim", HTML.SelectMukim("manualMukim"));
	    	this.context.put("jenisLot",HTML.SelectLot("jenisLot"));
	    	this.context.put("JenisHakmilik",HTML.SelectJenisHakmilik("JenisHakmilik"));
	    	this.context.put("Tahun", tahun);	    		
		
		}else if(submit.equals("doChangeDaerahManual")){			
			template_name = PATH+"new.jsp";
			changeDaerahManual();
		
		}else if(submit.equals("saveCukai")){
     		template_name = PATH+"senaraiHakmilik.jsp";
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
			//template_name = PATH+"frmCukaiSenaraiFailUploadExcel.jsp";
		
		}else if(submit.equals("senaraiFail")){
			template_name = PATH+"senaraiHakmilikManual.jsp";
			myLog.info("senaraiFail");
			tahun = socTahun;
			getListItem();
			getSenaraiFail(session, action,tahun);
			this.context.put("Tahun", tahun);
		
		}else if(submit.equals("doChangeDaerah")){
			template_name = PATH+"senaraiHakmilikManual.jsp";
			changeDaerahManualCarian();
			getSenaraiFail(session, action,tahun);
		
		}else if(submit.equals("viewCukaiDetail")){
			template_name = PATH+"frmEditCukai.jsp";
			if(modepage.equalsIgnoreCase("1")){
				context.put("mode","kemaskini");
			}else{
				context.put("mode","simpan");
			}
			String cukaiId = getParam("idCukai");
			CukaiTemp cukai = FrmCukaiSenaraiFailExcelUpload.getCukaiTemp(cukaiId);
			String lblNegeri3 = HTML.SelectNegeri("manualNegeri", cukai.getIdNegeri(), DISABILITY, "onChange=\"doChangeDaerahManual()\" ");
			context.put("manualNegeri", lblNegeri3);
			context.put("manualDaerah",HTML.SelectDaerahByNegeri(String.valueOf(cukai.getIdNegeri()), "manualDaerah", cukai.getIdDaerah(), DISABILITY, " onChange=\"doChangeDaerahManual()\" "));
			context.put("manualMukim", HTML.SelectMukimByDaerah(String.valueOf(cukai.getIdDaerah()), "manualMukim",cukai.getIdMukim(),DISABILITY));
			context.put("jenisLot",HTML.SelectLot("jenisLot",cukai.getIdLot(),DISABILITY));
    		context.put("JenisHakmilik",HTML.SelectJenisHakmilik("JenisHakmilik",cukai.getIdJenisHakmilik(),DISABILITY));			
			context.put("cukai", cukai);
		
		}else if(submit.equals("updateCukai")){
			template_name = PATH+"frmEditCukai.jsp";
			updateManual(session,action);
			context.put("mode","kemaskini");
		
		}else if(submit.equals("searchHakmilik")){
			template_name = PATH+"senaraiHakmilikManual.jsp";
			//tahun = socTahun;
			SenaraiFailOrig = searchHakmilik(session, action,tahun);
				 		
		}else if("carian".equals(submit)){
			isCarian = "ya";		
		    String noLot = getParam("txtNoLot")==""?"":getParam("txtNoLot");
			String semakMode="";
			String statusSemasa="2";
			PfdFail fail = null;
			Permohonan permohonan = null;
			HtpPermohonan htpPermohonan = null;
			String userLevel = "0";
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
				//this.context.put("SenaraiFailOrig", SenaraiFailOrig);
				
				Vector senaraiFail = FrmCukaiPenyataData.getList("11","","",Long.parseLong(String.valueOf(idnegeri)));
				Hashtable hashFail = (Hashtable) senaraiFail.get(0);
				
				String idPermohonan = String.valueOf(hashFail.get("idPermohonan"));
				Long idFail = Long.parseLong(String.valueOf(hashFail.get("idFail")));				
				//String idHtpPermohonan = getParam("txtidHtpPermohonan");
		    	fail = new PfdFail();
		 		permohonan = new Permohonan();
		 		htpPermohonan = new HtpPermohonan();
	 			//htpPermohonan.setIdHtpPermohonan(rs.getString("ID_HTPPERMOHONAN"));
	 			permohonan.setIdPermohonan(idPermohonan);
	 			fail.setIdFail(idFail);
	 			fail.setIdSubUrusan("43");
	 			permohonan.setPfdFail(fail);
		 		htpPermohonan.setPermohonan(permohonan);
				context.put("htpPermohonan", htpPermohonan);
				if(getRole().isUserRoleUsers(userId,"%Pengguna%%Negeri%")) {
					userLevel = "1";
				
				// (HTP)PegawaiNegeri
				}else if(getRole().isUserRoleUsers(userId,"%Pegawai%%Negeri%")){ 
					userLevel = "2";
				
				// (HTP)PengarahNegeri
				}else if(getRole().isUserRoleUsers(userId,"%Pengarah%%Negeri%")){
					userLevel = "3";
				}
				
				if(getIOnline().isHantarAktif(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan()
						,htpPermohonan.getPermohonan().getIdPermohonan()
						,htpPermohonan.getPermohonan().getPfdFail().getIdFail(),"2")){
					statusSemasa = "2";	
 					
				}else if(getIOnline().isHantarAktif(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan()
						,htpPermohonan.getPermohonan().getIdPermohonan()
						,htpPermohonan.getPermohonan().getPfdFail().getIdFail(),"3")){
					statusSemasa = "3";

				}else if(getIOnline().isHantarAktif(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan()
						,htpPermohonan.getPermohonan().getIdPermohonan()
						,htpPermohonan.getPermohonan().getPfdFail().getIdFail(),"4")){
					statusSemasa = "4";	

				}else if(getIOnline().isHantarAktif(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan()
						,htpPermohonan.getPermohonan().getIdPermohonan()
						,htpPermohonan.getPermohonan().getPfdFail().getIdFail(),"5")){
					statusSemasa = "5";	
				}

			     
		     
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("[2 HTP CUKAI PELARASAN HAPUS] TIADA MAKLUMAT CUKAI");
			}
			socNegeri = HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri),DISABILITY,"onChange=doChangeNegeriX();");
			socDaerah = HTML.SelectDaerahByNegeri(idnegeri, "socDaerah",Utils.parseLong(iddaerah),null, "onChange=\"doChangeDaerahX()\"");
			socMukim = HTML.SelectMukimByDaerah(iddaerah, "socMukim", Utils.parseLong(idmukim) , "");
			//setupPage(session,action,SenaraiFailOrig);
			context.put("statussemasa", statusSemasa);
			myLog.info("statusSemasa="+statusSemasa);
			context.put("userLevel", userLevel);
			myLog.info("userLevel="+userLevel);
		
		}else{
			isCarian = getParam("carian");
		    String noLot = getParam("txtNoLot")==""?"":getParam("txtNoLot");
			String semakMode="";
			String statusSemasa="2";
			PfdFail fail = null;
			Permohonan permohonan = null;
			HtpPermohonan htpPermohonan = null;
			String userLevel = "0";
			myLog.info("isCarian="+isCarian);
			if(isCarian.equals("ya")){
				try {
					SenaraiFailOrig = getICukaiPenyata().senaraiHakmilik(idnegeri,iddaerah,idmukim,socTahun);
					isCarian = "ya";
					
					Vector senaraiFail = FrmCukaiPenyataData.getList("11","","",Long.parseLong(String.valueOf(idnegeri)));
					Hashtable hashFail = (Hashtable) senaraiFail.get(0);
					
					String idPermohonan = String.valueOf(hashFail.get("idPermohonan"));
					Long idFail = Long.parseLong(String.valueOf(hashFail.get("idFail")));				
					//String idHtpPermohonan = getParam("txtidHtpPermohonan");
			    	fail = new PfdFail();
			 		permohonan = new Permohonan();
			 		htpPermohonan = new HtpPermohonan();
		 			//htpPermohonan.setIdHtpPermohonan(rs.getString("ID_HTPPERMOHONAN"));
		 			permohonan.setIdPermohonan(idPermohonan);
		 			fail.setIdFail(idFail);
		 			fail.setIdSubUrusan("43");
		 			permohonan.setPfdFail(fail);
			 		htpPermohonan.setPermohonan(permohonan);
					context.put("htpPermohonan", htpPermohonan);
//					if(FrmUtilData.isUserRole(userId,"(HTP)PeggunaNegeri")) 
//						userLevel = "1";
//					else if(FrmUtilData.isUserRole(userId,"(HTP)PegawaiNegeri")) 
//						userLevel = "2";
//					else if(FrmUtilData.isUserRole(userId,"(HTP)PengarahNegeri")) 
//						userLevel = "3";
//
//					if(getIOnline().isHantarAktif(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan()
//							,htpPermohonan.getPermohonan().getIdPermohonan()
//							,htpPermohonan.getPermohonan().getPfdFail().getIdFail(),"2")){
//						statusSemasa = "2";	
//	 					
//					}else if(getIOnline().isHantarAktif(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan()
//							,htpPermohonan.getPermohonan().getIdPermohonan()
//							,htpPermohonan.getPermohonan().getPfdFail().getIdFail(),"3")){
//						statusSemasa = "3";
//
//					}else if(getIOnline().isHantarAktif(htpPermohonan.getPermohonan().getPfdFail().getIdSubUrusan()
//							,htpPermohonan.getPermohonan().getIdPermohonan()
//							,htpPermohonan.getPermohonan().getPfdFail().getIdFail(),"4")){
//						statusSemasa = "4";	
//
//					}

				     
				} catch (Exception e) {
					e.printStackTrace();
					throw new Exception("[1 HTP CUKAI PELARASAN HAPUS] TIADA MAKLUMAT CUKAI");
				}
				//myLog.info(" 1st SenaraiFailOrig.size()="+SenaraiFailOrig)	;
			}
			socNegeri = HTML.SelectNegeri("socNegeri",Utils.parseLong(idnegeri),DISABILITY,"onChange=doChangeNegeriX();");
			socDaerah = HTML.SelectDaerahByNegeri(idnegeri, "socDaerah",Utils.parseLong(iddaerah),null, "onChange=\"doChangeDaerahX()\"");
			socMukim = HTML.SelectMukimByDaerah(iddaerah, "socMukim", Utils.parseLong(idmukim) , "");
			//setupPage(session,action,SenaraiFailOrig);
			context.put("semakMode", semakMode);
			//context.put("statussemasa", statusSemasa);
			myLog.info("statusSemasa="+statusSemasa);
			context.put("userLevel", userLevel);
			myLog.info("userLevel="+userLevel);
		
		}
	    
		setupPage(session,action,SenaraiFailOrig);
		this.context.put("socDaerah", socDaerah);
		this.context.put("socNegeri", socNegeri);
		this.context.put("socMukim", socMukim);		
		this.context.put("iscarian", isCarian);
		year = lebah.util.Util.getDateTime(new Date(), "yyyy");
		this.context.put("tahuncukai", Integer.parseInt(year));
		this.context.put("tahunparam", Integer.parseInt(socTahun));

		return template_name;		
		
	}
	
	//KEMASUKAN MANUAL
	private void getListItem()throws Exception{		
		socNegeri = HTML.SelectNegeri("lblNegeri2",Utils.parseLong(idnegeri)," disabled class=\"disabled\" ","onChange=doChangeNegeriX();");
		socDaerah = HTML.SelectDaerahByNegeri(idnegeri, "lblDaerah2",Utils.parseLong(iddaerah),null, "onChange=\"cmdChangeDaerahManualCarian()\"");
		context.put("lblNegeri2", socNegeri);
		context.put("lblDaerah2", socDaerah);
		this.context.put("lblMukim2", HTML.SelectMukim("lblMukim2"));
	
	}
	
	//KEMASUKAN MANUAL
	private void getSenaraiTahun(HttpSession session, String action) throws Exception {
		Vector senaraiTahun = FrmCukaiSenaraiFailExcelUpload.CukaigetSenaraiTahunFail();
		this.context.put("SenaraiTahun", senaraiTahun);	
		setupPage(session,action,senaraiTahun);	
		
	}
	
	//KEMASUKAN MANUAL
	private void changeDaerahManual()throws Exception{
		String lblNegeri3 = "";
		String idNegeri3 = getParam("manualNegeri");
		String idDaerah3= getParam("manualDaerah");
		String idMukim3= getParam("manualMukim");		
   		lblNegeri3 = HTML.SelectNegeri("manualNegeri", Utils.parseLong(idNegeri3), " disabled=\"disabled\" ", "onChange=\"doChangeDaerahManual()\" ");
		this.context.put("manualNegeri", lblNegeri3);
		
		if(idNegeri3 != ""){
			context.put("manualDaerah",HTML.SelectDaerahByNegeri(idNegeri3, "manualDaerah", Utils.parseLong(idDaerah3), null, "onChange=\"doChangeDaerahManual()\""));			
		}else {
			context.put("manualDaerah", HTML.SelectDaerah("manualDaerah",null,null,""));
		}
		this.context.put("manualMukim", HTML.SelectMukimByDaerah(idDaerah3, "manualMukim"));
		this.context.put("jenisLot",HTML.SelectLot("jenisLot"));			
		this.context.put("JenisHakmilik",HTML.SelectJenisHakmilik("JenisHakmilik"));
	
	}
	
	//KEMASUKAN MANUAL
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
		h.put("idmasuk", userId);
		h.put("Tahun", tahun);
		String idCukai = FrmCukaiKemaskiniDataBaru.SimpanDataManual(h);
		CukaiTemp cukai = FrmCukaiSenaraiFailExcelUpload.getCukaiTemp(idCukai);
		context.put("cukai", cukai);

	}
	
	//KEMASUKAN MANUAL
	private void changeDaerah() throws Exception{
		String idNegeri2 = getParam("lblNegeri2");
		String idDaerah2= getParam("lblDaerah2");
		String idMukim2= getParam("lblMukim2");
		String tahun = getParam("tahun");				
		String lblNegeri2 = HTML.SelectNegeri("lblNegeri2", Utils.parseLong(idNegeri2), " disabled=\"disabled\"", "onChange=\"doChangeDaerah1()\" ");
		this.context.put("lblNegeri2", lblNegeri2);
		
		if(idNegeri2 != ""){
			context.put("lblDaerah2",HTML.SelectDaerahByNegeri(idNegeri2, "lblDaerah2", Utils.parseLong(idDaerah2), null, "onChange=\"doChangeDaerah1()\""));			
		}else {
			context.put("lblDaerah2", HTML.SelectDaerah("lblDaerah2",null,null,""));
		}		
		if(idNegeri2 != "" && idDaerah2 != ""){			
			this.context.put("lblMukim2", HTML.SelectMukimByDaerah(idDaerah2, "lblMukim2", Utils.parseLong(idMukim2), null, "onChange=\"doChangeDaerah1()\""));
		}else{			
			this.context.put("lblMukim2", HTML.SelectMukimByDaerah(idDaerah2, "lblMukim2"));
		}
		
	}
	
	//KEMASUKAN MANUAL
	private void getSenaraiFail(HttpSession session,String action, String tahun)throws Exception{
		Vector senaraiFailbyTahun = FrmCukaiSenaraiFailExcelUpload.getCukaiTempByTahun(tahun);
		this.context.put("SenaraiFail", senaraiFailbyTahun);		
		setupPage(session,action,senaraiFailbyTahun);
		
	}	
	
	//KEMASUKAN MANUAL
	private Vector searchHakmilik(HttpSession session,String action, String tahun)throws Exception{
		String lblNegericari = getParam("lblNegeri2");
		String lblDaerahcari = getParam("lblDaerah2");
		String lblMukimcari = getParam("lblMukim2");
		String noHakmilikcari = getParam("txtNoHakmilik").trim();
		Vector list = null;

		list = FrmCukaiSenaraiFailExcelUpload.CukaigetSenaraiFailExcel2(lblNegericari,lblDaerahcari,lblMukimcari,noHakmilikcari,tahun);
		//setupPage2(session,action,list);
		changeDaerahManualCarian();
		return list;
	}
	
	//KEMASUKAN MANUAL CARIAN
	private void changeDaerahManualCarian() throws Exception{
		String idNegeri2 = getParam("lblNegeri2");
		String idDaerah2= getParam("lblDaerah2");
		String idMukim2= getParam("lblMukim2");
		String tahun = getParam("tahun");				
		String lblNegeri2 = HTML.SelectNegeri("lblNegeri2", Utils.parseLong(idNegeri2),DISABILITY, "onChange=\"doChangeDaerah1()\" ");
		this.context.put("lblNegeri2", lblNegeri2);
		
		if(idNegeri2 != ""){
			context.put("lblDaerah2",HTML.SelectDaerahByNegeri(idNegeri2, "lblDaerah2", Utils.parseLong(idDaerah2), null, "onChange=\"cmdChangeDaerahManualCarian()\""));			
		}else {
			context.put("lblDaerah2", HTML.SelectDaerah("lblDaerah2",null,null,""));
		}		
		if(idNegeri2 != "" && idDaerah2 != ""){			
			this.context.put("lblMukim2", HTML.SelectMukimByDaerah(idDaerah2, "lblMukim2", Utils.parseLong(idMukim2), null, "onChange=\"cmdChangeDaerahManualCarian()\""));
		}else{			
			this.context.put("lblMukim2", HTML.SelectMukimByDaerah(idDaerah2, "lblMukim2"));
		}
		
	}
	
	
	public void setupPage2(HttpSession session,String action,Vector list) {
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
		this.context.put("SenaraiFailTemp",paging.getPage(page));
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
	
	private void salinCukai(String idnegeri,String iddaerah,String idmukim,String socTahun){
		myLog.info(idnegeri+","+iddaerah+","+ idmukim+","+socTahun);
		getICukai().salinCukai(idnegeri,iddaerah,idmukim,socTahun);
		
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
		temp.setIdMasuk(Long.parseLong(userId));
		temp.setIdKemaskini(userId);
		temp.setTahun(tahun);
		FrmCukaiKemaskiniDataBaru.updateDataManual(temp);
		CukaiTemp cukai = FrmCukaiSenaraiFailExcelUpload.getCukaiTemp(idCukai);
		
		myLog.info("cukai lebihan:"+temp.getLebihan());		
		context.put("cukai", cukai);
		//Vector list = FrmCukaiSenaraiFailExcelUpload.CukaigetSenaraiFailExcel(String.valueOf(temp.getIdCukaiTemp()),1);
		//setupPage(session,action,list);
	
	}
	
	private void simpanPengesahan(Tblrujsuburusanstatusfail rsusf,String langkah)throws Exception {
		Tblrujsuburusanstatusfail subUrusanStatusFailN = new Tblrujsuburusanstatusfail();
		try {
			subUrusanStatusFail = new Tblrujsuburusanstatusfail();
			subUrusanStatusFail.setIdPermohonan(rsusf.getIdPermohonan());
			subUrusanStatusFail.setIdFail(rsusf.getIdFail());
			subUrusanStatusFail.setAktif("0");
			
			subUrusanStatusFailN.setIdPermohonan(rsusf.getIdPermohonan());
			subUrusanStatusFailN.setIdFail(rsusf.getIdFail());
			long setIdSuburusanstatus = FrmUtilData.getIdSuburusanStatusByLangkah(langkah
					, String.valueOf(rsusf.getIdSuburusanstatusfail()),"=");
			subUrusanStatusFailN.setIdSuburusanstatus(setIdSuburusanstatus);
			subUrusanStatusFailN.setAktif("1");
			subUrusanStatusFailN.setUrl(Utils.isNull(rsusf.getUrl()));
			subUrusanStatusFailN.setIdMasuk(Long.parseLong(userId));
			subUrusanStatusFailN.setIdKemaskini(Long.parseLong(userId));
			Tblrujsuburusanstatusfail terbaru = getStatus().kemaskiniSimpanStatusPermohonanAktif(subUrusanStatusFail
					,subUrusanStatusFailN,socTahun);
			 getStatus().kemaskiniSimpanStatusCukai(subUrusanStatusFailN, terbaru, socTahun);

			 context.put("statussemasa", langkah);

		} catch (Exception e) {
			//throw new Exception("Ralat FrmGadaian[554]:"+e.getCause());
			getIHTP().getErrorHTML(e.toString());

		}
	}

	
	private ICukai getICukaiPenyata(){
		if(iCukai==null){
			iCukai = new FrmCukaiPenyataBean();
		}
		return iCukai;
		
	}	
	
	private ICukai getICukai(){
		if(iCukai==null){
			iCukai = new CukaiBean();
		}
		return iCukai;
		
	}	
	
	private IOnline getIOnline(){
		if(iOnline==null){
			iOnline = new OnlineBean();
		}
		return iOnline;
			
	}
	
	private IHtp getIHTP(){
		if(iHTP== null)
			iHTP = new HtpBean();
		return iHTP;
	}

	private IHTPStatus getStatus(){
		if(iStatus==null){
			iStatus = new HTPStatusBean();
		}
		return iStatus;
			
	}

	private IHTPRole getRole(){
		if(iRole==null){
			iRole = new HTPRoleBean();
		}
		return iRole;
			
	}
	
}
