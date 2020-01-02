package ekptg.view.htp;

import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.entities.Tblrujmukim;
import ekptg.model.htp.FrmCukaiKemaskiniDataBaru;
import ekptg.model.htp.FrmCukaiSenaraiFailExcelUpload;
import ekptg.model.htp.HtpBean;
import ekptg.model.htp.IHtp;
import ekptg.model.htp.cukai.CukaiBean;
import ekptg.model.htp.cukai.ICukai;
import ekptg.model.htp.cukai.entity.CukaiTemp;
import ekptg.model.htp.entity.HakMilik;
import ekptg.model.htp.entity.HakmilikCukai;

@SuppressWarnings("serial")
public class FrmCukaiKemaskiniDataExcel extends AjaxBasedModule{
	
	private final String PATH="app/htp/cukai/";
	private ICukai iCukai = null;
 	private IHtp iHTP = null;  
	static Logger myLog = Logger.getLogger(ekptg.view.htp.FrmCukaiKemaskiniDataExcel.class);
	String socNegeri = "";
	String socDaerah = "";
	String socMukim = "";
	String idnegeri = "";
 	String iddaerah = "";
 	String idmukim = "";
    String isCarian = "tidak";
	private String year = "";
	private String socTahun = "";
	private String bil ="1";
	private HakMilik hakmilik = null;
	private String userId = "";
	private String noHakmilik = "";

	@Override
	public String doTemplate2() throws Exception {
		String template_name = PATH+"frmCukaiKemaskiniDataExcel2.jsp";
		
		try{
			
			HttpSession session = this.request.getSession();
			String action = getParam("action");
			String submit = getParam("command");
			Vector SenaraiFailTemp = null;
			Vector SenaraiFailOrig = null;
			context.put("UTIL", new ekptg.helpers.Utils());
			
			idnegeri = getParam("socNegeri");
		 	iddaerah = getParam("socDaerah");
		 	idmukim = getParam("socMukim");
		 	socTahun = getParam("Form_tahun")==null||getParam("Form_tahun")==""?lebah.util.Util.getDateTime(new Date(), "yyyy"):getParam("Form_tahun");
		 	userId =(String)session.getAttribute("_ekptg_user_id");
		 	noHakmilik = getParam("txtnohakmilik");
			String tahun = getParam("tahun");
			/**
			 * Simpan kemaskini data compare antara data Excel dng data Oracle			
			 */		
			if("CukaiKemaskini".equals(submit)){	
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
				cukai.setPengecualian(Double.parseDouble(Utils.RemoveComma(pengecualian)));		//TBLHTPCUKAITEMP
				cukai.setLebihan(Double.parseDouble(Utils.RemoveComma(lebihan)));											//TBLHTPCUKAITEMP
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

//				SenaraiFailOrig = getICukai().CukaiSenaraiKemaskiniFail(idnegeri,iddaerah,idmukim,socTahun);
				SenaraiFailOrig = getICukai().senaraiHakmilik(idnegeri,iddaerah,idmukim,socTahun);
				template_name = PATH+"frmCukaiKemaskiniDataExcel2.jsp";
			
			}else if("CukaiKemaskiniTBLHTPHAKMILIKCUKAITERPERINCI".equals(submit)){	//TBLHTPHAKMILIKCUKAITERPERINCI
					Hashtable data = null;
					data = new Hashtable();
					String tunggakan =""; 
					String denda =""; 
					String taliair2 =""; 
					String taliparit2  ="";
					String lebihan ="";
					String cukai_perlubayar ="";
					String pengurangan ="";
					String cukaiKenaBayarLama ="";
					String cukaiJumlah ="";
				
					bil = getParam("bil");
					tunggakan = getParam("cukaitunggakan"+bil);
					denda = getParam("cukaidenda"+bil);
					//taliair2 = getParam("txtcukaitaliair2");
					//taliparit2 = getParam("txtcukaiparit2");
					//cukai_kenabayar = getParam("txtJumBayaran2");
					taliair2 = getParam("cukaitaliair"+bil);
					taliparit2 = getParam("cukaiparit"+bil);
					pengurangan = getParam("cukaipengurangan"+bil);
					cukai_perlubayar = getParam("cukaiperlubayar"+bil);
					cukaiKenaBayarLama = getParam("cukaikenabayarlama"+bil);
					lebihan = getParam("cukaipengecualian"+bil);
					cukaiJumlah = getParam("cukaijumlah"+bil);
					//String senaraiID_HAKMILIKCUKAI = getParam("senaraiID_HAKMILIKCUKAI"+bil);
					String senaraiID_HAKMILIKCUKAI = getParam("idhakmilikcukai"+bil);
					String senaraiNolot = getParam("senaraiNolot");
					String senaraiNO_HAKMILIKUPLOAD = getParam("senaraiNO_HAKMILIKUPLOAD");
					String senaraiNO_HAKMILIK = getParam("senaraiNO_HAKMILIK");
					//String year = getParam("tahun_upload");
					myLog.info("bil:"+bil);
					myLog.info("cukai_kenabayar:"+cukai_perlubayar);
					myLog.info("senaraiID_HAKMILIKCUKAI:"+senaraiID_HAKMILIKCUKAI);
					
					data.put("tahun", socTahun);
					data.put("tunggakan", Utils.RemoveComma(tunggakan));
					data.put("denda", Utils.RemoveComma(denda));
					data.put("pengurangan", Utils.RemoveComma(pengurangan));
					data.put("lebihan", Utils.RemoveComma(lebihan));
					data.put("cukai_taliair", Utils.RemoveComma(taliair2));
					data.put("cukai_parit", Utils.RemoveComma(taliparit2));
					data.put("cukai_kenabayar", Utils.RemoveComma(cukaiJumlah));
					data.put("cukaiPerluBayar", Utils.RemoveComma(cukai_perlubayar));
					data.put("senaraiNolot", senaraiNolot);
					data.put("senaraiID_HAKMILIKCUKAI", senaraiID_HAKMILIKCUKAI);
					data.put("cukaiKenaBayarLama", cukaiKenaBayarLama);
					data.put("senaraiNO_HAKMILIKUPLOAD", senaraiNO_HAKMILIKUPLOAD);
					data.put("senaraiNO_HAKMILIK", senaraiNO_HAKMILIK);
					
					FrmCukaiKemaskiniDataBaru.kemaskiniData(data);				
					//SenaraiFailOrig = FrmCukaiSenaraiFailExcelUpload.CukaiSenaraiKemaskiniFail();
					//SenaraiFailOrig = FrmCukaiSenaraiFailExcelUpload.CukaiSenaraiKemaskiniFailV1(idnegeri,iddaerah,idmukim);
					//this.context.put("SenaraiFailOrig", SenaraiFailOrig);
					SenaraiFailOrig = getICukai().CukaiSenaraiKemaskiniFail(idnegeri,iddaerah,idmukim,socTahun);
					template_name = PATH+"frmCukaiKemaskiniDataExcel2.jsp";
				
			}else if("CetakSenaraiKemaskini".equals(submit)){
				context.remove("SenaraiFailOrig");
	    		//template_name = "app/htp/frmCukaiSenaraiTerperinci.jsp";
	//    		this.context.put("pagemode",0);
	    		Vector senaraikemaskini = FrmCukaiSenaraiFailExcelUpload.getCukaiKemaskiniList();
	    		this.context.put("senaraikemaskini", senaraikemaskini);
	    		
	    		//___page temp view kemaskini_________________________________________________________________________________________________________
	//    		if("tempKemaskini".equalsIgnoreCase(submit)){
	//    			this.context.put("senaraikemaskini", senaraikemaskini);
	//    			this.context.put("pagemode",1);
	//    		}
	    		template_name = PATH+"frmCukaiKemaskiniDataExcel2.jsp";
	    		
			}else if("carian".equals(submit)){
				myLog.info("carian");
				isCarian = "ya";		
			    String noLot = getParam("txtNoLot")==""?"":getParam("txtNoLot");
				if(!noHakmilik.equals("")){
					SenaraiFailOrig = getICukai().senaraiHakmilik(idnegeri
					,iddaerah,idmukim,socTahun,noHakmilik);
				}else if(!noLot.equals("")){
					SenaraiFailOrig = getICukai().senaraiHakmilik(idnegeri
							,iddaerah,idmukim,socTahun,noHakmilik,"",noLot);					
				}else if(!noHakmilik.equals("") && !noLot.equals("")){
					SenaraiFailOrig = getICukai().senaraiHakmilik(idnegeri
							,iddaerah,idmukim,socTahun,noHakmilik,"",noLot);				
				}else{
					SenaraiFailOrig = getICukai().senaraiHakmilik(idnegeri,iddaerah,idmukim,socTahun);
					
				}
		    	this.context.put("carianNoLot", noLot);
				this.context.put("SenaraiFailOrig", SenaraiFailOrig);
			
			}else if("carianTBLHTPHAKMILIKCUKAITERPERINCI".equals(submit)){	//TBLHTPHAKMILIKCUKAITERPERINCI
				isCarian = "ya";		
				SenaraiFailOrig = getICukai().CukaiSenaraiKemaskiniFail(idnegeri,iddaerah,idmukim,socTahun);
				this.context.put("SenaraiFailOrig", SenaraiFailOrig);
				
			}else if("salincukai".equals(submit)){	
				salinCukai(idnegeri,iddaerah,idmukim,socTahun);
				SenaraiFailOrig = getICukai().CukaiSenaraiKemaskiniFail(idnegeri,iddaerah,idmukim,socTahun);
				
				
			}else if("cukaikemaskinisemua".equals(submit)){	
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
//	        				data.put("tahun", socTahun);
//	        				data.put("tunggakan", Utils.RemoveComma(getParam("cukaitunggakan"+(i+1))));
//	        				data.put("denda", Utils.RemoveComma(getParam("cukaidenda"+(i+1))));
//	        				data.put("pengurangan", Utils.RemoveComma(getParam("cukaipengurangan"+(i+1))));
//	        				data.put("lebihan", Utils.RemoveComma(getParam("cukaipengecualian"+(i+1))));
//	        				data.put("cukai_taliair", Utils.RemoveComma(getParam("cukaitaliair"+(i+1))));
//	        				data.put("cukai_parit", Utils.RemoveComma(getParam("cukaiparit"+(i+1))));
//	        				data.put("cukai_kenabayar", Utils.RemoveComma(getParam("cukaijumlah"+(i+1))));
//	        				data.put("cukaiPerluBayar", Utils.RemoveComma(getParam("cukaiperlubayar"+(i+1))));
//	        				data.put("senaraiNolot", senaraiNoLots[i]);
//	        				data.put("senaraiID_HAKMILIKCUKAI", Utils.RemoveComma(getParam("senaraiID_HAKMILIKCUKAI"+(i+1))));
//	        				data.put("cukaiKenaBayarLama", Utils.RemoveComma(getParam("cukaikenabayarlama"+(i+1))));
//	        				data.put("senaraiNO_HAKMILIKUPLOAD", senaraiNoHakmilikUploads[i]);
//	        				data.put("senaraiNO_HAKMILIK", senaraiNoHakmiliks[i]);
//	        				myLog.info("data="+data);
//	        				FrmCukaiKemaskiniDataBaru.kemaskiniData(data); 
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
//	        				hakmilikBaru.setNoFailPTD(hakmilik.getRujLot().getKeterangan()+hakmilik.getNoLot()); //TBLHTPCUKAITEMP
	        				hakmilikBaru.setIdJenisHakmilik(hakmilik.getRujJenisHakmilik().getIdJenishakmilik()); //TBLHTPCUKAITEMP
	        				hakmilikBaru.setNoHakmilik(hakmilik.getNoHakmilik());			//TBLHTPCUKAITEMP
	        				cukai.setNoHakmilik(hakmilik.getRujJenisHakmilik().getKodJenisHakmilik()+hakmilik.getNoHakmilik()); //TBLHTPCUKAITEMP
	        				myLog.info("cukais[i]="+cukais[i]);
	        				cukai.setCukaiPerluBayar(Double.parseDouble(Utils.RemoveComma(cukais[i])));
//	        				cukai.setCukaiTaliAir(Double.parseDouble(Utils.RemoveComma(getParam("cukaitaliair"+(i+1)))));				//TBLHTPCUKAITEMP
	        				cukai.setCukaiTaliAir(Double.parseDouble(Utils.RemoveComma(taliair2s[i])));				//TBLHTPCUKAITEMP
//	        				cukai.setCukaiParit(Double.parseDouble(Utils.RemoveComma(getParam("cukaiparit"+(i+1)))));					//TBLHTPCUKAITEMP
	        				cukai.setCukaiParit(Double.parseDouble(Utils.RemoveComma(taliparit2s[i])));					//TBLHTPCUKAITEMP
//	        				cukai.setCukaiKenaBayar(Double.parseDouble(Utils.RemoveComma(getParam("cukaikenabayarlama"+(i+1))))); //TBLHTPCUKAITEMP
	        				cukai.setCukaiKenaBayar(Double.parseDouble(Utils.RemoveComma(cukaiJumlahs[i]))); //TBLHTPCUKAITEMP
//	        				cukai.setCukailain(0.00);										//TBLHTPCUKAITEMP
	        				cukai.setCukailain(0.00);										//TBLHTPCUKAITEMP
//	        				cukai.setTunggakkan(Double.parseDouble(Utils.RemoveComma(getParam("cukaitunggakan"+(i+1)))));				//TBLHTPCUKAITEMP
	        				cukai.setTunggakkan(Double.parseDouble(Utils.RemoveComma(tunggakans[i])));				//TBLHTPCUKAITEMP
//	        				cukai.setDenda(Double.parseDouble(Utils.RemoveComma(getParam("cukaidenda"+(i+1)))));						//TBLHTPCUKAITEMP
	        				cukai.setDenda(Double.parseDouble(Utils.RemoveComma(dendas[i])));						//TBLHTPCUKAITEMP
//	        				cukai.setPengurangan(Double.parseDouble(Utils.RemoveComma(getParam("cukaipengurangan"+(i+1)))));			//TBLHTPCUKAITEMP
	        				cukai.setPengurangan(Double.parseDouble(Utils.RemoveComma(pengurangans[i])));			//TBLHTPCUKAITEMP
//	        				cukai.setPengecualian(Double.parseDouble(Utils.RemoveComma(getParam("cukaipengecualian"+(i+1)))));		//TBLHTPCUKAITEMP
	        				cukai.setPengecualian(Double.parseDouble(Utils.RemoveComma(pengecualians[i])));		//TBLHTPCUKAITEMP
//	        				cukai.setLebihan(0.00);											//TBLHTPCUKAITEMP
	        				cukai.setLebihan(Double.parseDouble(Utils.RemoveComma(lebihans[i])));											//TBLHTPCUKAITEMP
//	        				cukai.setTotalcukai(Double.parseDouble(Utils.RemoveComma(getParam("cukaijumlah"+(i+1)))));			//TBLHTPCUKAITEMP
	        				cukai.setTotalcukai(Double.parseDouble(Utils.RemoveComma(cukaiJumlahs[i])));			//TBLHTPCUKAITEMP
	        				cukai.setTahun(socTahun);										//TBLHTPCUKAITEMP
	        				hakmilikBaru.setHakmilikCTemp(cukai);
	        				hakmilikBaru.setIdMasuk(Long.parseLong(userId));				//TBLHTPCUKAITEMP
	        				
	        				//TBLHTPCUKAITERPERINCI
	        				HakmilikCukai hcukai = new HakmilikCukai();
	        				hcukai.setKodBayaranCukai(socTahun);
//	        				hcukai.setCukaiTerkini(Double.parseDouble(Utils.RemoveComma(getParam("cukaikenabayarlama"+(i+1)))));
//	        				hcukai.setTunggakan(Double.parseDouble(Utils.RemoveComma(getParam("cukaitunggakan"+(i+1)))));
//	        				hcukai.setDenda(Double.parseDouble(Utils.RemoveComma(getParam("cukaidenda"+(i+1)))));
//	        				hcukai.setCukaiTaliAir(Double.parseDouble(Utils.RemoveComma(getParam("cukaitaliair"+(i+1)))));				
//	        				hcukai.setCukaiParit(Double.parseDouble(Utils.RemoveComma(getParam("cukaiparit"+(i+1)))));					
//	        				hcukai.setPengurangan(Double.parseDouble(Utils.RemoveComma(getParam("cukaipengurangan"+(i+1)))));			
//	        				hcukai.setPengecualian(Double.parseDouble(Utils.RemoveComma(getParam("cukaipengecualian"+(i+1)))));
//	        				hcukai.setJumlah(Double.parseDouble(Utils.RemoveComma(getParam("cukaijumlah"+(i+1)))));
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
	
//				SenaraiFailOrig = getICukai().CukaiSenaraiKemaskiniFail(idnegeri,iddaerah,idmukim,socTahun);
				SenaraiFailOrig = getICukai().senaraiHakmilik(idnegeri,iddaerah,idmukim,socTahun);
				template_name = PATH+"frmCukaiKemaskiniDataExcel2.jsp";
				
			}else if("simpanpenyatacukai".equals(submit)){	
				myLog.info("simpanpenyatacukai="+submit);
				Hashtable data = null;
				data = new Hashtable();
				String lebihan ="";
				String denda =""; 
				String tunggakan =""; 
				String taliair2 =""; 
				String taliparit2  ="";
				String cukai_kenabayar ="";				
				String[] lebihans = this.request.getParameterValues("txtlebihan");
				String[] dendas = this.request.getParameterValues("txtdenda");
				String[] tunggakans = this.request.getParameterValues("txttunggakan");
				String[] taliair2s = this.request.getParameterValues("cukaitaliair");
				String[] taliparit2s = this.request.getParameterValues("cukaiparit");
				String[] cukaiKenaBayars = this.request.getParameterValues("cukaikenabayar");
				String[] senaraiNoLots = this.request.getParameterValues("senaraiNolot");
				String[] senaraiNoHakmilikUploads = this.request.getParameterValues("senaraiNO_HAKMILIKUPLOAD");			
				String[] senaraiIdHakmilikCukais = this.request.getParameterValues("senaraiID_HAKMILIKCUKAI");			
				String[] senaraiNoHakmiliks = this.request.getParameterValues("senaraiNO_HAKMILIK");	
				String[] cukaiJumlahs = this.request.getParameterValues("cukaijumlah");			
				String[] cbsemaks = this.request.getParameterValues("cb");
				String[] cbsemaks_ = this.request.getParameterValues("cb_");
				bil = getParam("bil");
	 			
//	          	if(cbsemaks!=null){
	          		myLog.info("length:"+bil);		
	          		for (int i = 0; i < Integer.parseInt(bil); i++) { 
	          				myLog.info(i);
//	        			if(cbsemaks_[i].equals("true")){
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
	        				hakmilikBaru.setIdJenisHakmilik(hakmilik.getRujJenisHakmilik().getIdJenishakmilik()); //TBLHTPCUKAITEMP
	        				hakmilikBaru.setNoHakmilik(hakmilik.getNoHakmilik());			//TBLHTPCUKAITEMP
	        				cukai.setNoHakmilik(hakmilik.getRujJenisHakmilik().getKodJenisHakmilik()+hakmilik.getNoHakmilik()); //TBLHTPCUKAITEMP
	        				cukai.setCukaiKenaBayar(Double.parseDouble(Utils.RemoveComma(getParam("cukaikenabayarlama"+(i+1))))); //TBLHTPCUKAITEMP
	        				cukai.setCukaiTaliAir(Double.parseDouble(Utils.RemoveComma(getParam("cukaitaliair"+(i+1)))));				//TBLHTPCUKAITEMP
	        				cukai.setCukaiParit(Double.parseDouble(Utils.RemoveComma(getParam("cukaiparit"+(i+1)))));					//TBLHTPCUKAITEMP
	        				cukai.setCukailain(0.00);										//TBLHTPCUKAITEMP
	        				cukai.setTunggakkan(Double.parseDouble(Utils.RemoveComma(getParam("cukaitunggakan"+(i+1)))));				//TBLHTPCUKAITEMP
	        				cukai.setDenda(Double.parseDouble(Utils.RemoveComma(getParam("cukaidenda"+(i+1)))));						//TBLHTPCUKAITEMP
	        				cukai.setPengurangan(Double.parseDouble(Utils.RemoveComma(getParam("cukaipengurangan"+(i+1)))));			//TBLHTPCUKAITEMP
	        				cukai.setPengecualian(Double.parseDouble(Utils.RemoveComma(getParam("cukaipengecualian"+(i+1)))));		//TBLHTPCUKAITEMP
	        				cukai.setLebihan(0.00);											//TBLHTPCUKAITEMP
	        				cukai.setTotalcukai(Double.parseDouble(Utils.RemoveComma(getParam("cukaijumlah"+(i+1)))));			//TBLHTPCUKAITEMP
	        				cukai.setTahun(socTahun);										//TBLHTPCUKAITEMP
	        				hakmilikBaru.setHakmilikCTemp(cukai);
	        				hakmilikBaru.setIdMasuk(Long.parseLong(userId));				//TBLHTPCUKAITEMP
	        				
	        				//TBLHTPCUKAITERPERINCI
	        				HakmilikCukai hcukai = new HakmilikCukai();
	        				hcukai.setKodBayaranCukai(socTahun);
	        				hcukai.setCukaiTerkini(Double.parseDouble(Utils.RemoveComma(getParam("cukaikenabayarlama"+(i+1)))));
	        				hcukai.setTunggakan(Double.parseDouble(Utils.RemoveComma(getParam("cukaitunggakan"+(i+1)))));
	        				hcukai.setDenda(Double.parseDouble(Utils.RemoveComma(getParam("cukaidenda"+(i+1)))));
	        				hcukai.setCukaiTaliAir(Double.parseDouble(Utils.RemoveComma(getParam("cukaitaliair"+(i+1)))));				
	        				hcukai.setCukaiParit(Double.parseDouble(Utils.RemoveComma(getParam("cukaiparit"+(i+1)))));					
	        				hcukai.setPengurangan(Double.parseDouble(Utils.RemoveComma(getParam("cukaipengurangan"+(i+1)))));			
	        				hcukai.setPengecualian(Double.parseDouble(Utils.RemoveComma(getParam("cukaipengecualian"+(i+1)))));
	        				hcukai.setJumlah(Double.parseDouble(Utils.RemoveComma(getParam("cukaijumlah"+(i+1)))));
	        				hcukai.setIdHakmilikCukai(Long.parseLong(getParam("senaraiID_HAKMILIKCUKAI"+(i+1))));
	        		    	hakmilikBaru.setHakmilikCukai(hcukai);	 
	        		    	
	        				//getICukai().simpanCukaiHakmilikTemp(hakmilikBaru);
	        				//getICukai().simpanCukaiHakmilikTerperinci(hakmilikBaru);
	        				
//	        			}
	        		}
//	        	} 
	
				SenaraiFailOrig = getICukai().senaraiHakmilik(idnegeri,iddaerah,idmukim,socTahun);
				template_name = PATH+"frmCukaiKemaskiniDataExcel2.jsp";			

			// KEMASUKAN MANUAL CUKAI 
			}else if(submit.equals("manual")){	
				getListItem();
				getSenaraiTahun(session, action);			
				template_name = PATH+"FrmCukaiSenaraiTahunFail.jsp";

			}else if(submit.equals("createNew")){
				tahun = socTahun;
				this.context.put("manualNegeri",HTML.SelectNegeri("manualNegeri", "onChange=\"doChangeDaerahManual()\"") );
		    	this.context.put("manualDaerah", HTML.SelectDaerah("manualDaerah"));
		    	this.context.put("manualMukim", HTML.SelectMukim("manualMukim"));
		    	this.context.put("jenisLot",HTML.SelectLot("jenisLot"));
		    	this.context.put("JenisHakmilik",HTML.SelectJenisHakmilik("JenisHakmilik"));
		    	this.context.put("Tahun", tahun);	    		
		    	template_name = PATH+"new.jsp";
			
			}else if(submit.equals("doChangeDaerahManual")){			
				changeDaerahManual();
				template_name = PATH+"new.jsp";
			
			}else if(submit.equals("saveCukai")){
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
				myLog.info("senaraiFail");
				tahun = socTahun;
				getListItem();
				getSenaraiFail(session, action,tahun);
				this.context.put("Tahun", tahun);
				template_name = PATH+"frmCukaiSenaraiFailUploadExcel.jsp";
			
			}else if(submit.equals("searchHakmilik")){
				tahun = socTahun;
				searchHakmilik(session, action,tahun);
				template_name =PATH+"frmCukaiSenaraiFailUploadExcel_.jsp";
					
			}else{
				myLog.info("default");
				isCarian = getParam("carian");
			    String noLot = getParam("txtNoLot")==""?"":getParam("txtNoLot");
				if(isCarian.equals("ya")){
					myLog.info("default:isCarian");
					//SenaraiFailOrig = getICukai().senaraiHakmilik(idnegeri,iddaerah,idmukim,socTahun);
					SenaraiFailOrig = getICukai().senaraiHakmilik(idnegeri,iddaerah,idmukim,socTahun,noHakmilik,"",noLot);
					isCarian = "ya";	
					
				}
				this.context.put("carianNoLot", noLot);

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
			this.context.put("tahunparam", Integer.parseInt(socTahun));
			this.context.put("count", SenaraiFailOrig);
			this.context.put("carianNoHakmilik", noHakmilik);
			return template_name;		
		
			}catch(Exception e){
//				e.printStackTrace();
//				throw new Exception("[HTP CUKAI PELARASAN] SILA LOGIN SEMULA");
				throw new Exception(getIHTP().getErrorHTML(e.toString()));
			
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
	
	private ICukai getICukai(){
		if(iCukai==null){
			iCukai = new CukaiBean();
		}
		return iCukai;
		
	}
	
	private void salinCukai(String idnegeri,String iddaerah,String idmukim,String socTahun){
		getICukai().salinCukai(idnegeri,iddaerah,idmukim,socTahun);
		
	}
	  
	private IHtp getIHTP(){
		if(iHTP== null)
			iHTP = new HtpBean();
		return iHTP;
	}

	//KEMASUKAN MANUAL
	private void getListItem()throws Exception{
		String lblNegeri = HTML.SelectNegeri("lblNegeri", "onChange=\"doChangeDaerah()\"");
		String lblDaerah = HTML.SelectDaerah("lblDaerah");
		context.put("lblNegeri", lblNegeri);
		context.put("lblDaerah", lblDaerah);
		
		this.context.put("lblNegeri2", HTML.SelectNegeri("lblNegeri2", "onChange=\"doChangeDaerah1()\""));
		this.context.put("lblDaerah2", HTML.SelectDaerah("lblDaerah2"));
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
   		lblNegeri3 = HTML.SelectNegeri("manualNegeri", Utils.parseLong(idNegeri3), null, "onChange=\"doChangeDaerahManual()\" ");
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
		String lblNegeri2 = HTML.SelectNegeri("lblNegeri2", Utils.parseLong(idNegeri2), null, "onChange=\"doChangeDaerah1()\" ");
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
	private void searchHakmilik(HttpSession session,String action, String tahun)throws Exception{
		String lblNegericari = getParam("lblNegeri2");
		String lblDaerahcari = getParam("lblDaerah2");
		String lblMukimcari = getParam("lblMukim2");
		String noHakmilikcari = getParam("txtNoHakmilik").trim();
		Vector list = null;

		list = FrmCukaiSenaraiFailExcelUpload.CukaigetSenaraiFailExcel2(lblNegericari,lblDaerahcari,lblMukimcari,noHakmilikcari,tahun);
		setupPage2(session,action,list);
		changeDaerah();
		
	}


}
