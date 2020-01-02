package ekptg.view.ppt;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.ppt.FrmHakmilikSementaraMMKPenyediaanData;
import ekptg.model.ppt.FrmHakmilikSementaraMaklumatPermohonanData;
import ekptg.model.ppt.FrmHakmilikSementaraSenaraiMMKData;

public class SementaraMMK extends AjaxBasedModule{
	static Logger myLogger = Logger.getLogger(SementaraMMK.class);

	@Override
	public String doTemplate2() throws Exception {
		
		String vm = "";
		String submit = getParam("command");
		String action = getParam("action");
		String status = getParam("idStatus");
		
		myLogger.info("submit >> "+submit);
		myLogger.info("action >>"+action);
		myLogger.info("status >> "+status);
		
		context.put("idStatus", status);
		String tarikhmohon = "";
    	String nofail = "";    
    	String noJKPTG = "";
    	String cStatus = "0";
    	String id_fail = getParam("id_fail");
		context.put("idFail", id_fail);
		String id_permohonan = getParam("id_permohonan");
		context.put("idPermohonan",id_permohonan);
    	HttpSession session = this.request.getSession();
		String user_name = (String)session.getAttribute("_portal_username");


    	FrmHakmilikSementaraSenaraiMMKData listMMK = new FrmHakmilikSementaraSenaraiMMKData();
		FrmHakmilikSementaraMaklumatPermohonanData prmhnnMaster = new FrmHakmilikSementaraMaklumatPermohonanData();
		FrmHakmilikSementaraMMKPenyediaanData mmkData = new FrmHakmilikSementaraMMKPenyediaanData();
		
    	Vector list = null;
    	Vector listAgensi = null;
    	Vector butiran = null;
    	Vector butiranLot = null;
    	Vector sewaLot = null;
    	Vector noLot = null;
    	Vector semakPrmhnn = null;
    	Vector paparMMK = null;
    	Vector checkStatus = null;
        Vector checkIdPermohonan = null;
    	
    	if("newMMK".equals(action)){
    		
    			vm = "app/ppt/frmHakmilikSementaraMMKPenyediaan.jsp";
        
        		Vector getrecord_permohonan = prmhnnMaster.getRecord(id_permohonan);
    			Hashtable h = (Hashtable) getrecord_permohonan.get(0);
    			context.put("nama_kementerian",h.get("nama_kementerian"));
    			context.put("tarikh_terima",h.get("tarikh_terima"));
    			context.put("projek_negeri",h.get("projek_negeri"));
    			context.put("nama_daerah",h.get("nama_daerah"));
    			context.put("tarikh_kehendaki",h.get("tarikh_kehendaki"));
    			context.put("tujuan",h.get("tujuan"));
    			context.put("no_fail",h.get("no_fail"));
    			context.put("no_rujukan_surat",h.get("no_rujukan_surat"));
    			context.put("no_rujukan_ptd",h.get("no_rujukan_ptd"));
    			context.put("no_rujukan_ptg",h.get("no_rujukan_ptg"));
    			context.put("no_rujukan_upt",h.get("no_rujukan_upt"));
    			context.put("keterangan",h.get("keterangan"));
    			context.put("negeriMMK",h.get("id_negeri_projek"));
    			
    			String idAgensi = h.get("id_agensi").toString();
    			if(idAgensi!="")
    			{
    				int id_agensi = Integer.parseInt(idAgensi);
    				listAgensi = prmhnnMaster.getNamaAgensi(idAgensi);
    				Hashtable ag = (Hashtable) listAgensi.get(0);
    				context.put("idAgensi", ag.get("nama_agensi").toString()); 
    			}
    			else
    			{
    				context.put("idAgensi","-");
    			}
    			
    		
        		
    			mmkData.setButiranAsas(id_fail, id_permohonan);
    			butiran = mmkData.getButiranAsas();
    			Hashtable hB = (Hashtable)butiran.get(0);
    			
    			context.put("PEMOHON",hB.get("pemohon"));
    			context.put("TUJUAN",hB.get("tujuan"));
    			context.put("LOKASI", hB.get("lokasi"));
        		
    			mmkData.setButiranLot(id_fail, id_permohonan);
    			butiranLot = mmkData.getButiranLot();
    			Hashtable hL = (Hashtable)butiranLot.get(0);
    			
    			context.put("BILLOT",hL.get("bilLot"));
    			
    			mmkData.setSewaLot(id_fail, id_permohonan);
    			sewaLot = mmkData.getSewaLot();
    			Hashtable hS = (Hashtable)sewaLot.get(0);
    			
    			context.put("LUASSEWA",hS.get("sewaLot"));
    			
    			mmkData.setNoLot(id_fail, id_permohonan);
    			noLot = mmkData.getNoLot();
    			Hashtable hN;
    			String lot = "";
    			
    			if (noLot.size() > 1){
    				
    				for (int i = 0; i < noLot.size(); i++){
        				hN = (Hashtable)noLot.get(i);
        				if (lot.equals("")){
        					lot = hN.get("noLot").toString();
        				}
        				else{
            				lot = lot + ", " + hN.get("noLot");

        				}
        				
        			}
    				
    				context.put("NOLOT",lot);
    			}
    			else{
    				hN = (Hashtable)noLot.get(0);
    				context.put("NOLOT",hN.get("noLot"));
    			}
    			
    			mmkData.check_status(id_permohonan);
    			checkStatus = mmkData.getStatus();
    			Hashtable hC = (Hashtable)checkStatus.get(0);
    			context.put("idStatus", hC.get("status").toString());
    			
    			mmkData.check_idPermohonan(id_permohonan);
    			checkIdPermohonan = mmkData.getIdPermohonan();
    			
    			if(checkIdPermohonan.size() != 0){
    			
		    			if(Integer.parseInt(hC.get("status").toString()) == 132){
		    				
		    				mmkData.setPaparMMK(id_permohonan);
		    				paparMMK = mmkData.getPaparMMK();
		    				Hashtable hM = (Hashtable)paparMMK.get(0);
		        			
		    				context.put("mode","paparMMK");
		    				context.put("readonly","readonly");
		    				context.put("disabled","disabled");
		    				context.put("modeSemak","newMMK");
		    				context.put("readonlySemak","");
		    				context.put("disabledSemak","");
		    				context.put("modeKptsn","newMMK");
		    				context.put("readonlyKptsn","");
		    				context.put("disabledKptsn","");
		    				
		    				context.put("idMMK",hM.get("ID_MMK"));
		    				context.put("TUJUAN1",hM.get("TUJUAN"));
		    				context.put("LATARBELAKANG",hM.get("LATARBELAKANG"));
		    				context.put("ASAS_PERTIMBANGAN", hM.get("ASAS_PERTIMBANGAN"));
		    				context.put("KESIMPULAN",hM.get("KESIMPULAN"));
		    				context.put("SYOR",hM.get("SYOR"));
		    				context.put("KEDUDUKAN_LAPORAN_TNH", hM.get("KEDUDUKAN_LAPORAN_TNH"));
		    				context.put("PENGESAHAN_PERUNTUKAN",hM.get("PENGESAHAN_PERUNTUKAN"));
		    				context.put("PANDANGAN", hM.get("PANDANGAN"));
		    				context.put("IMPLIKASI",hM.get("IMPLIKASI"));
		    				context.put("PERIHALTANAH",hM.get("PERIHAL_TANAH"));
		    				context.put("PERIHAL_POHON",hM.get("PERIHAL_POHON"));
		    				context.put("ANGGARAN_KOS",hM.get("ANGGARAN_KOS"));
		    				context.put("PERAKUAN_PENTADBIR_TNH",hM.get("PERAKUAN_PENTADBIR_TNH"));
		    				context.put("NILAI_ATAS_TANAH",hM.get("NILAI_ATAS_TANAH"));
		    				context.put("PENGECUALIAN_UPAH_UKUR",hM.get("PENGECUALIAN_UPAH_UKUR"));
		    				context.put("HAL_LAIN",hM.get("HAL_LAIN"));
		    				context.put("JAWATANKUASA_TANAH",hM.get("JAWATANKUASA_TANAH"));
		    				context.put("NAMA_TUAN_TANAH", hM.get("NAMA_TUAN_TANAH"));
		    				context.put("TAJUK",hM.get("TAJUK"));
		    				context.put("ULASAN",hM.get("ULASAN"));
		    				context.put("ULASAN_JABTEKNIKAL",hM.get("ULASAN_JABTEKNIKAL"));
		    				context.put("ULASAN_PENGARAH",hM.get("ULASAN_PENGARAH"));
		    				context.put("KEPUTUSAN",hM.get("KEPUTUSAN"));
		    				context.put("BUTIR_ASAS",hM.get("BUTIR_ASAS"));
		    				context.put("KEADAAN_TANAH",hM.get("KEADAAN_TANAH"));
		    				context.put("BUTIR_TANAH",hM.get("BUTIR_TANAH"));
		    				context.put("KEMUDAHAN_ASAS",hM.get("KEMUDAHAN_ASAS"));
		    				context.put("PERAKUAN_SETIAUSAHA",hM.get("PERAKUAN_SETIAUSAHA"));
		    				context.put("PENUTUP",hM.get("PENUTUP"));
		    				
		    				context.put("NAMAPEGAWAI",hM.get("USER_NAME"));
		    				context.put("TARIKHSEMAKAN","");
		    				context.put("KEPUTUSANSEMAKAN","");
		    				context.put("ULASANSEMAKAN","");
		    				context.put("NORUJUKAN","");
		    				context.put("TARIKHHANTAR","");
		    				context.put("TARIKHKEPUTUSAN","");
		    				context.put("TARIKHTERIMA","");
		    				context.put("TARIKH","");
		    				context.put("STATUSKEPUTUSAN","");
		    				context.put("ULASANKEPUTUSAN","");
		    				
		
		    			}
		    			else if(Integer.parseInt(hC.get("status").toString()) == 133){
		    				
		    				mmkData.setPaparMMK(id_permohonan);
		    				paparMMK = mmkData.getPaparMMK();
		    				Hashtable hM = (Hashtable)paparMMK.get(0);
		    				
		    				context.put("mode","paparMMK");
		    				context.put("readonly","readonly");
		    				context.put("disabled","disabled");
		    				context.put("modeSemak","paparMMK");
		    				context.put("readonlySemak","readonly");
		    				context.put("disabledSemak","disabled");
		    				context.put("modeKptsn","newMMK");
		    				context.put("readonlyKptsn","");
		    				context.put("disabledKptsn","");
		    				
		    				context.put("idMMK",hM.get("ID_MMK"));
		    				context.put("TUJUAN1",hM.get("TUJUAN"));
		    				context.put("LATARBELAKANG",hM.get("LATARBELAKANG"));
		    				context.put("ASAS_PERTIMBANGAN", hM.get("ASAS_PERTIMBANGAN"));
		    				context.put("KESIMPULAN",hM.get("KESIMPULAN"));
		    				context.put("SYOR",hM.get("SYOR"));
		    				context.put("KEDUDUKAN_LAPORAN_TNH", hM.get("KEDUDUKAN_LAPORAN_TNH"));
		    				context.put("PENGESAHAN_PERUNTUKAN",hM.get("PENGESAHAN_PERUNTUKAN"));
		    				context.put("PANDANGAN", hM.get("PANDANGAN"));
		    				context.put("IMPLIKASI",hM.get("IMPLIKASI"));
		    				context.put("PERIHALTANAH",hM.get("PERIHAL_TANAH"));
		    				context.put("PERIHAL_POHON",hM.get("PERIHAL_POHON"));
		    				context.put("ANGGARAN_KOS",hM.get("ANGGARAN_KOS"));
		    				context.put("PERAKUAN_PENTADBIR_TNH",hM.get("PERAKUAN_PENTADBIR_TNH"));
		    				context.put("NILAI_ATAS_TANAH",hM.get("NILAI_ATAS_TANAH"));
		    				context.put("PENGECUALIAN_UPAH_UKUR",hM.get("PENGECUALIAN_UPAH_UKUR"));
		    				context.put("HAL_LAIN",hM.get("HAL_LAIN"));
		    				context.put("JAWATANKUASA_TANAH",hM.get("JAWATANKUASA_TANAH"));
		    				context.put("NAMA_TUAN_TANAH", hM.get("NAMA_TUAN_TANAH"));
		    				context.put("TAJUK",hM.get("TAJUK"));
		    				context.put("ULASAN",hM.get("ULASAN"));
		    				context.put("ULASAN_JABTEKNIKAL",hM.get("ULASAN_JABTEKNIKAL"));
		    				context.put("ULASAN_PENGARAH",hM.get("ULASAN_PENGARAH"));
		    				context.put("KEPUTUSAN",hM.get("KEPUTUSAN"));
		    				context.put("BUTIR_ASAS",hM.get("BUTIR_ASAS"));
		    				context.put("KEADAAN_TANAH",hM.get("KEADAAN_TANAH"));
		    				context.put("BUTIR_TANAH",hM.get("BUTIR_TANAH"));
		    				context.put("KEMUDAHAN_ASAS",hM.get("KEMUDAHAN_ASAS"));
		    				context.put("PERAKUAN_SETIAUSAHA",hM.get("PERAKUAN_SETIAUSAHA"));
		    				context.put("PENUTUP",hM.get("PENUTUP"));
		    				context.put("NAMAPEGAWAI",hM.get("USER_NAME"));
		    				context.put("TARIKHSEMAKAN",hM.get("TARIKH_SEMAK"));
		    				context.put("KEPUTUSANSEMAKAN",hM.get("STATUS_SEMAKAN"));
		    				context.put("ULASANSEMAKAN",hM.get("ULASAN"));
		    				context.put("NORUJUKAN","");
		    				context.put("TARIKHHANTAR","");
		    				context.put("TARIKHKEPUTUSAN","");
		    				context.put("TARIKHTERIMA","");
		    				context.put("TARIKH","");
		    				context.put("STATUSKEPUTUSAN","");
		    				context.put("ULASANKEPUTUSAN","");
		
		
		    			}
		    			else if(Integer.parseInt(hC.get("status").toString()) == 134){
		    				mmkData.setPaparMMK(id_permohonan);
		    				paparMMK = mmkData.getPaparMMK();
		    				Hashtable hM = (Hashtable)paparMMK.get(0);
		    				
		        			
		    				context.put("mode","paparMMK");
		    				context.put("readonly","readonly");
		    				context.put("disabled","disabled");
		    				context.put("modeSemak","paparMMK");
		    				context.put("readonlySemak","readonly");
		    				context.put("disabledSemak","disabled");
		    				context.put("modeKptsn","paparMMK");
		    				context.put("readonlyKptsn","readonly");
		    				context.put("disabledKptsn","disabled");
		    				
		    				context.put("idMMK",hM.get("ID_MMK"));
		    				context.put("TUJUAN1",hM.get("TUJUAN"));
		    				context.put("LATARBELAKANG",hM.get("LATARBELAKANG"));
		    				context.put("ASAS_PERTIMBANGAN", hM.get("ASAS_PERTIMBANGAN"));
		    				context.put("KESIMPULAN",hM.get("KESIMPULAN"));
		    				context.put("SYOR",hM.get("SYOR"));
		    				context.put("KEDUDUKAN_LAPORAN_TNH", hM.get("KEDUDUKAN_LAPORAN_TNH"));
		    				context.put("PENGESAHAN_PERUNTUKAN",hM.get("PENGESAHAN_PERUNTUKAN"));
		    				context.put("PANDANGAN", hM.get("PANDANGAN"));
		    				context.put("IMPLIKASI",hM.get("IMPLIKASI"));
		    				context.put("PERIHALTANAH",hM.get("PERIHAL_TANAH"));
		    				context.put("PERIHAL_POHON",hM.get("PERIHAL_POHON"));
		    				context.put("ANGGARAN_KOS",hM.get("ANGGARAN_KOS"));
		    				context.put("PERAKUAN_PENTADBIR_TNH",hM.get("PERAKUAN_PENTADBIR_TNH"));
		    				context.put("NILAI_ATAS_TANAH",hM.get("NILAI_ATAS_TANAH"));
		    				context.put("PENGECUALIAN_UPAH_UKUR",hM.get("PENGECUALIAN_UPAH_UKUR"));
		    				context.put("HAL_LAIN",hM.get("HAL_LAIN"));
		    				context.put("JAWATANKUASA_TANAH",hM.get("JAWATANKUASA_TANAH"));
		    				context.put("NAMA_TUAN_TANAH", hM.get("NAMA_TUAN_TANAH"));
		    				context.put("TAJUK",hM.get("TAJUK"));
		    				context.put("ULASAN",hM.get("ULASAN"));
		    				context.put("ULASAN_JABTEKNIKAL",hM.get("ULASAN_JABTEKNIKAL"));
		    				context.put("ULASAN_PENGARAH",hM.get("ULASAN_PENGARAH"));
		    				context.put("KEPUTUSAN",hM.get("KEPUTUSAN"));
		    				context.put("BUTIR_ASAS",hM.get("BUTIR_ASAS"));
		    				context.put("KEADAAN_TANAH",hM.get("KEADAAN_TANAH"));
		    				context.put("BUTIR_TANAH",hM.get("BUTIR_TANAH"));
		    				context.put("KEMUDAHAN_ASAS",hM.get("KEMUDAHAN_ASAS"));
		    				context.put("PERAKUAN_SETIAUSAHA",hM.get("PERAKUAN_SETIAUSAHA"));
		    				context.put("PENUTUP",hM.get("PENUTUP"));
		    				context.put("NAMAPEGAWAI",hM.get("USER_NAME"));
		    				context.put("TARIKHSEMAKAN",hM.get("TARIKH_SEMAK"));
		    				context.put("KEPUTUSANSEMAKAN",hM.get("STATUS_SEMAKAN"));
		    				context.put("ULASAN",hM.get("ULASAN"));
		    				context.put("idMMKKptsn",hM.get("ID_MMKKEPUTUSAN"));
		    				context.put("NORUJUKAN",hM.get("NO_RUJMMK"));
		    				context.put("TARIKHHANTAR",hM.get("TARIKH_HANTAR"));
		    				context.put("TARIKHKEPUTUSAN",hM.get("TARIKH_KEPUTUSAN"));
		    				context.put("TARIKHTERIMA",hM.get("TARIKH_TERIMA"));
		    				context.put("TARIKH",hM.get("TARIKH_MMK"));
		    				context.put("STATUSKEPUTUSAN",hM.get("STATUS_KEPUTUSAN"));
		    				context.put("ULASANKEPUTUSAN",hM.get("ULASAN_KEPUTUSAN"));
		    			}
		    			else{
		    				
		    				mmkData.setPaparMMK(id_permohonan);
		    				paparMMK = mmkData.getPaparMMK();
		    				Hashtable hM = (Hashtable)paparMMK.get(0);
		    				
		        			
		    				context.put("mode","paparMMK");
		    				context.put("readonly","readonly");
		    				context.put("disabled","disabled");
		    				context.put("modeSemak","paparMMK");
		    				context.put("readonlySemak","readonly");
		    				context.put("disabledSemak","disabled");
		    				context.put("modeKptsn","paparMMK");
		    				context.put("readonlyKptsn","readonly");
		    				context.put("disabledKptsn","disabled");
		    				
		    				context.put("idMMK",hM.get("ID_MMK"));
		    				context.put("TUJUAN1",hM.get("TUJUAN"));
		    				context.put("LATARBELAKANG",hM.get("LATARBELAKANG"));
		    				context.put("ASAS_PERTIMBANGAN", hM.get("ASAS_PERTIMBANGAN"));
		    				context.put("KESIMPULAN",hM.get("KESIMPULAN"));
		    				context.put("SYOR",hM.get("SYOR"));
		    				context.put("KEDUDUKAN_LAPORAN_TNH", hM.get("KEDUDUKAN_LAPORAN_TNH"));
		    				context.put("PENGESAHAN_PERUNTUKAN",hM.get("PENGESAHAN_PERUNTUKAN"));
		    				context.put("PANDANGAN", hM.get("PANDANGAN"));
		    				context.put("IMPLIKASI",hM.get("IMPLIKASI"));
		    				context.put("PERIHALTANAH",hM.get("PERIHAL_TANAH"));
		    				context.put("PERIHAL_POHON",hM.get("PERIHAL_POHON"));
		    				context.put("ANGGARAN_KOS",hM.get("ANGGARAN_KOS"));
		    				context.put("PERAKUAN_PENTADBIR_TNH",hM.get("PERAKUAN_PENTADBIR_TNH"));
		    				context.put("NILAI_ATAS_TANAH",hM.get("NILAI_ATAS_TANAH"));
		    				context.put("PENGECUALIAN_UPAH_UKUR",hM.get("PENGECUALIAN_UPAH_UKUR"));
		    				context.put("HAL_LAIN",hM.get("HAL_LAIN"));
		    				context.put("JAWATANKUASA_TANAH",hM.get("JAWATANKUASA_TANAH"));
		    				context.put("NAMA_TUAN_TANAH", hM.get("NAMA_TUAN_TANAH"));
		    				context.put("TAJUK",hM.get("TAJUK"));
		    				context.put("ULASAN",hM.get("ULASAN"));
		    				context.put("ULASAN_JABTEKNIKAL",hM.get("ULASAN_JABTEKNIKAL"));
		    				context.put("ULASAN_PENGARAH",hM.get("ULASAN_PENGARAH"));
		    				context.put("KEPUTUSAN",hM.get("KEPUTUSAN"));
		    				context.put("BUTIR_ASAS",hM.get("BUTIR_ASAS"));
		    				context.put("KEADAAN_TANAH",hM.get("KEADAAN_TANAH"));
		    				context.put("BUTIR_TANAH",hM.get("BUTIR_TANAH"));
		    				context.put("KEMUDAHAN_ASAS",hM.get("KEMUDAHAN_ASAS"));
		    				context.put("PERAKUAN_SETIAUSAHA",hM.get("PERAKUAN_SETIAUSAHA"));
		    				context.put("PENUTUP",hM.get("PENUTUP"));
		    				context.put("NAMAPEGAWAI",hM.get("USER_NAME"));
		    				context.put("TARIKHSEMAKAN",hM.get("TARIKH_SEMAK"));
		    				context.put("KEPUTUSANSEMAKAN",hM.get("STATUS_SEMAKAN"));
		    				context.put("ULASAN",hM.get("ULASAN"));
		    				context.put("idMMKKptsn",hM.get("ID_MMKKEPUTUSAN"));
		    				context.put("NORUJUKAN",hM.get("NO_RUJMMK"));
		    				context.put("TARIKHHANTAR",hM.get("TARIKH_HANTAR"));
		    				context.put("TARIKHKEPUTUSAN",hM.get("TARIKH_KEPUTUSAN"));
		    				context.put("TARIKHTERIMA",hM.get("TARIKH_TERIMA"));
		    				context.put("TARIKH",hM.get("TARIKH_MMK"));
		    				context.put("STATUSKEPUTUSAN",hM.get("STATUS_KEPUTUSAN"));
		    				context.put("ULASANKEPUTUSAN",hM.get("ULASAN_KEPUTUSAN"));
		    				
		    			}
    			}
    			else{
    				context.put("mode","newMMK");
    				context.put("readonly","");
    				context.put("disabled","");
    				context.put("modeSemak","newMMK");
    				context.put("readonlySemak","");
    				context.put("disabledSemak","");
    				context.put("modeKptsn","newMMK");
    				context.put("readonlyKptsn","");
    				context.put("disabledKptsn","");

    				context.put("TUJUAN1","");
    				context.put("LATARBELAKANG","");
    				context.put("ASAS_PERTIMBANGAN", "");
    				context.put("KESIMPULAN","");
    				context.put("SYOR","");
    				context.put("KEDUDUKAN_LAPORAN_TNH","");
    				context.put("PENGESAHAN_PERUNTUKAN","");
    				context.put("PANDANGAN","");
    				context.put("IMPLIKASI","");
    				context.put("PERIHALTANAH","");
    				context.put("PERIHAL_POHON","");
    				context.put("ANGGARAN_KOS","");
    				context.put("PERAKUAN_PENTADBIR_TNH","");
    				context.put("NILAI_ATAS_TANAH","");
    				context.put("PENGECUALIAN_UPAH_UKUR","");
    				context.put("HAL_LAIN","");
    				context.put("JAWATANKUASA_TANAH","");
    				context.put("NAMA_TUAN_TANAH", "");
    				context.put("TAJUK","");
    				context.put("ULASAN","");
    				context.put("ULASAN_JABTEKNIKAL","");
    				context.put("ULASAN_PENGARAH","");
    				context.put("KEPUTUSAN","");
    				context.put("BUTIR_ASAS","");
    				context.put("KEADAAN_TANAH","");
    				context.put("BUTIR_TANAH","");
    				context.put("KEMUDAHAN_ASAS","");
    				context.put("PERAKUAN_SETIAUSAHA","");
    				context.put("PENUTUP","");
    				context.put("NAMAPEGAWAI","");
    				context.put("TARIKHSEMAKAN","");
    				context.put("KEPUTUSANSEMAKAN","");
    				context.put("ULASANSEMAKAN","");
    				context.put("NORUJUKAN","");
    				context.put("TARIKHHANTAR","");
    				context.put("TARIKHKEPUTUSAN","");
    				context.put("TARIKHTERIMA","");
    				context.put("TARIKH","");
    				context.put("STATUSKEPUTUSAN","");
    				context.put("ULASANKEPUTUSAN","");
    				
    				
    			}
    			
	
    		
    	}
    	else if("tabSemakan".equals(action)){
    		
    		vm = "app/ppt/frmHakmilikSementaraMMKSemakan.jsp";
    		    		
    		Vector getrecord_permohonan = prmhnnMaster.getRecord(id_permohonan);
			Hashtable h = (Hashtable) getrecord_permohonan.get(0);
			context.put("nama_kementerian",h.get("nama_kementerian"));
			context.put("tarikh_terima",h.get("tarikh_terima"));
			context.put("projek_negeri",h.get("projek_negeri"));
			context.put("nama_daerah",h.get("nama_daerah"));
			context.put("tarikh_kehendaki",h.get("tarikh_kehendaki"));
			context.put("tujuan",h.get("tujuan"));
			context.put("no_fail",h.get("no_fail"));
			context.put("no_rujukan_surat",h.get("no_rujukan_surat"));
			context.put("no_rujukan_ptd",h.get("no_rujukan_ptd"));
			context.put("no_rujukan_ptg",h.get("no_rujukan_ptg"));
			context.put("no_rujukan_upt",h.get("no_rujukan_upt"));
			context.put("keterangan",h.get("keterangan"));
			
			
			String idAgensi = h.get("id_agensi").toString();
			if(idAgensi!="")
			{
				int id_agensi = Integer.parseInt(idAgensi);
				listAgensi = prmhnnMaster.getNamaAgensi(idAgensi);
				Hashtable ag = (Hashtable) listAgensi.get(0);
				context.put("idAgensi", ag.get("nama_agensi").toString()); 
			}
			else
			{
				context.put("idAgensi","-");
			}
			
			mmkData.check_status(id_permohonan);
			checkStatus = mmkData.getStatus();
			Hashtable hC = (Hashtable)checkStatus.get(0);
			context.put("idStatus", hC.get("status").toString());
			
			
			if(Integer.parseInt(hC.get("status").toString()) == 132){
				mmkData.setPaparMMK(id_permohonan);
				paparMMK = mmkData.getPaparMMK();
				Hashtable hM = (Hashtable)paparMMK.get(0);
				
				context.put("mode","paparMMK");
				context.put("readonly","readonly");
				context.put("disabled","disabled");
				context.put("modeSemak","newMMK");
				context.put("readonlySemak","");
				context.put("disabledSemak","");
				context.put("modeKptsn","newMMK");
				context.put("readonlyKptsn","");
				context.put("disabledKptsn","");
				
				context.put("idMMK",hM.get("ID_MMK"));
				context.put("TUJUAN1",hM.get("TUJUAN"));
				context.put("LATARBELAKANG",hM.get("LATARBELAKANG"));
				context.put("ASAS_PERTIMBANGAN", hM.get("ASAS_PERTIMBANGAN"));
				context.put("KESIMPULAN",hM.get("KESIMPULAN"));
				context.put("SYOR",hM.get("SYOR"));
				context.put("KEDUDUKAN_LAPORAN_TNH", hM.get("KEDUDUKAN_LAPORAN_TNH"));
				context.put("PENGESAHAN_PERUNTUKAN",hM.get("PENGESAHAN_PERUNTUKAN"));
				context.put("PANDANGAN", hM.get("PANDANGAN"));
				context.put("IMPLIKASI",hM.get("IMPLIKASI"));
				context.put("PERIHALTANAH",hM.get("PERIHAL_TANAH"));
				context.put("PERIHAL_POHON",hM.get("PERIHAL_POHON"));
				context.put("ANGGARAN_KOS",hM.get("ANGGARAN_KOS"));
				context.put("PERAKUAN_PENTADBIR_TNH",hM.get("PERAKUAN_PENTADBIR_TNH"));
				context.put("NILAI_ATAS_TANAH",hM.get("NILAI_ATAS_TANAH"));
				context.put("PENGECUALIAN_UPAH_UKUR",hM.get("PENGECUALIAN_UPAH_UKUR"));
				context.put("HAL_LAIN",hM.get("HAL_LAIN"));
				context.put("JAWATANKUASA_TANAH",hM.get("JAWATANKUASA_TANAH"));
				context.put("NAMA_TUAN_TANAH", hM.get("NAMA_TUAN_TANAH"));
				context.put("TAJUK",hM.get("TAJUK"));
				context.put("ULASAN",hM.get("ULASAN"));
				context.put("ULASAN_JABTEKNIKAL",hM.get("ULASAN_JABTEKNIKAL"));
				context.put("ULASAN_PENGARAH",hM.get("ULASAN_PENGARAH"));
				context.put("KEPUTUSAN",hM.get("KEPUTUSAN"));
				context.put("BUTIR_ASAS",hM.get("BUTIR_ASAS"));
				context.put("KEADAAN_TANAH",hM.get("KEADAAN_TANAH"));
				context.put("BUTIR_TANAH",hM.get("BUTIR_TANAH"));
				context.put("KEMUDAHAN_ASAS",hM.get("KEMUDAHAN_ASAS"));
				context.put("PERAKUAN_SETIAUSAHA",hM.get("PERAKUAN_SETIAUSAHA"));
				context.put("PENUTUP",hM.get("PENUTUP"));
				context.put("NAMAPEGAWAI",user_name);
				context.put("TARIKHSEMAKAN","");
				context.put("KEPUTUSANSEMAKAN","");
				context.put("ULASANSEMAKAN","");
				context.put("NORUJUKAN","");
				context.put("TARIKHHANTAR","");
				context.put("TARIKHKEPUTUSAN","");
				context.put("TARIKHTERIMA","");
				context.put("TARIKH","");
				context.put("STATUSKEPUTUSAN","");
				context.put("ULASANKEPUTUSAN","");
				

			}
			else if(Integer.parseInt(hC.get("status").toString()) == 133){
				mmkData.setPaparMMK(id_permohonan);
				paparMMK = mmkData.getPaparMMK();
				Hashtable hM = (Hashtable)paparMMK.get(0);
				
    			
				context.put("mode","paparMMK");
				context.put("readonly","readonly");
				context.put("disabled","disabled");
				context.put("modeSemak","paparMMK");
				context.put("readonlySemak","readonly");
				context.put("disabledSemak","disabled");
				context.put("modeKptsn","newMMK");
				context.put("readonlyKptsn","");
				context.put("disabledKptsn","");
				
				context.put("idMMK",hM.get("ID_MMK"));
				context.put("TUJUAN1",hM.get("TUJUAN"));
				context.put("LATARBELAKANG",hM.get("LATARBELAKANG"));
				context.put("ASAS_PERTIMBANGAN", hM.get("ASAS_PERTIMBANGAN"));
				context.put("KESIMPULAN",hM.get("KESIMPULAN"));
				context.put("SYOR",hM.get("SYOR"));
				context.put("KEDUDUKAN_LAPORAN_TNH", hM.get("KEDUDUKAN_LAPORAN_TNH"));
				context.put("PENGESAHAN_PERUNTUKAN",hM.get("PENGESAHAN_PERUNTUKAN"));
				context.put("PANDANGAN", hM.get("PANDANGAN"));
				context.put("IMPLIKASI",hM.get("IMPLIKASI"));
				context.put("PERIHALTANAH",hM.get("PERIHAL_TANAH"));
				context.put("PERIHAL_POHON",hM.get("PERIHAL_POHON"));
				context.put("ANGGARAN_KOS",hM.get("ANGGARAN_KOS"));
				context.put("PERAKUAN_PENTADBIR_TNH",hM.get("PERAKUAN_PENTADBIR_TNH"));
				context.put("NILAI_ATAS_TANAH",hM.get("NILAI_ATAS_TANAH"));
				context.put("PENGECUALIAN_UPAH_UKUR",hM.get("PENGECUALIAN_UPAH_UKUR"));
				context.put("HAL_LAIN",hM.get("HAL_LAIN"));
				context.put("JAWATANKUASA_TANAH",hM.get("JAWATANKUASA_TANAH"));
				context.put("NAMA_TUAN_TANAH", hM.get("NAMA_TUAN_TANAH"));
				context.put("TAJUK",hM.get("TAJUK"));
				context.put("ULASAN",hM.get("ULASAN"));
				context.put("ULASAN_JABTEKNIKAL",hM.get("ULASAN_JABTEKNIKAL"));
				context.put("ULASAN_PENGARAH",hM.get("ULASAN_PENGARAH"));
				context.put("KEPUTUSAN",hM.get("KEPUTUSAN"));
				context.put("BUTIR_ASAS",hM.get("BUTIR_ASAS"));
				context.put("KEADAAN_TANAH",hM.get("KEADAAN_TANAH"));
				context.put("BUTIR_TANAH",hM.get("BUTIR_TANAH"));
				context.put("KEMUDAHAN_ASAS",hM.get("KEMUDAHAN_ASAS"));
				context.put("PERAKUAN_SETIAUSAHA",hM.get("PERAKUAN_SETIAUSAHA"));
				context.put("PENUTUP",hM.get("PENUTUP"));
				context.put("NAMAPEGAWAI",hM.get("USER_NAME"));
				context.put("TARIKHSEMAKAN",hM.get("TARIKH_SEMAK"));
				context.put("KEPUTUSANSEMAKAN",hM.get("STATUS_SEMAKAN"));
				context.put("ULASANSEMAKAN",hM.get("ULASAN"));
				context.put("NORUJUKAN","");
				context.put("TARIKHHANTAR","");
				context.put("TARIKHKEPUTUSAN","");
				context.put("TARIKHTERIMA","");
				context.put("TARIKH","");
				context.put("STATUSKEPUTUSAN","");
				context.put("ULASANKEPUTUSAN","");


			}
			else if(Integer.parseInt(hC.get("status").toString()) == 134){
				mmkData.setPaparMMK(id_permohonan);
				paparMMK = mmkData.getPaparMMK();
				Hashtable hM = (Hashtable)paparMMK.get(0);
				
    			
				context.put("mode","paparMMK");
				context.put("readonly","readonly");
				context.put("disabled","disabled");
				context.put("modeSemak","paparMMK");
				context.put("readonlySemak","readonly");
				context.put("disabledSemak","disabled");
				context.put("modeKptsn","paparMMK");
				context.put("readonlyKptsn","readonly");
				context.put("disabledKptsn","disabled");
				
				context.put("idMMK",hM.get("ID_MMK"));
				context.put("TUJUAN1",hM.get("TUJUAN"));
				context.put("LATARBELAKANG",hM.get("LATARBELAKANG"));
				context.put("ASAS_PERTIMBANGAN", hM.get("ASAS_PERTIMBANGAN"));
				context.put("KESIMPULAN",hM.get("KESIMPULAN"));
				context.put("SYOR",hM.get("SYOR"));
				context.put("KEDUDUKAN_LAPORAN_TNH", hM.get("KEDUDUKAN_LAPORAN_TNH"));
				context.put("PENGESAHAN_PERUNTUKAN",hM.get("PENGESAHAN_PERUNTUKAN"));
				context.put("PANDANGAN", hM.get("PANDANGAN"));
				context.put("IMPLIKASI",hM.get("IMPLIKASI"));
				context.put("PERIHALTANAH",hM.get("PERIHAL_TANAH"));
				context.put("PERIHAL_POHON",hM.get("PERIHAL_POHON"));
				context.put("ANGGARAN_KOS",hM.get("ANGGARAN_KOS"));
				context.put("PERAKUAN_PENTADBIR_TNH",hM.get("PERAKUAN_PENTADBIR_TNH"));
				context.put("NILAI_ATAS_TANAH",hM.get("NILAI_ATAS_TANAH"));
				context.put("PENGECUALIAN_UPAH_UKUR",hM.get("PENGECUALIAN_UPAH_UKUR"));
				context.put("HAL_LAIN",hM.get("HAL_LAIN"));
				context.put("JAWATANKUASA_TANAH",hM.get("JAWATANKUASA_TANAH"));
				context.put("NAMA_TUAN_TANAH", hM.get("NAMA_TUAN_TANAH"));
				context.put("TAJUK",hM.get("TAJUK"));
				context.put("ULASAN",hM.get("ULASAN"));
				context.put("ULASAN_JABTEKNIKAL",hM.get("ULASAN_JABTEKNIKAL"));
				context.put("ULASAN_PENGARAH",hM.get("ULASAN_PENGARAH"));
				context.put("KEPUTUSAN",hM.get("KEPUTUSAN"));
				context.put("BUTIR_ASAS",hM.get("BUTIR_ASAS"));
				context.put("KEADAAN_TANAH",hM.get("KEADAAN_TANAH"));
				context.put("BUTIR_TANAH",hM.get("BUTIR_TANAH"));
				context.put("KEMUDAHAN_ASAS",hM.get("KEMUDAHAN_ASAS"));
				context.put("PERAKUAN_SETIAUSAHA",hM.get("PERAKUAN_SETIAUSAHA"));
				context.put("PENUTUP",hM.get("PENUTUP"));
				context.put("NAMAPEGAWAI",hM.get("USER_NAME"));
				context.put("TARIKHSEMAKAN",hM.get("TARIKH_SEMAK"));
				context.put("KEPUTUSANSEMAKAN",hM.get("STATUS_SEMAKAN"));
				context.put("ULASANSEMAKAN",hM.get("ULASAN"));
				context.put("idMMKKptsn",hM.get("ID_MMKKEPUTUSAN"));
				context.put("NORUJUKAN",hM.get("NO_RUJMMK"));
				context.put("TARIKHHANTAR",hM.get("TARIKH_HANTAR"));
				context.put("TARIKHKEPUTUSAN",hM.get("TARIKH_KEPUTUSAN"));
				context.put("TARIKHTERIMA",hM.get("TARIKH_TERIMA"));
				context.put("TARIKH",hM.get("TARIKH_MMK"));
				context.put("STATUSKEPUTUSAN",hM.get("STATUS_KEPUTUSAN"));
				context.put("ULASANKEPUTUSAN",hM.get("ULASAN_KEPUTUSAN"));
			}
			else{
				context.put("mode","newMMK");
				context.put("readonly","");
				context.put("disabled","");
				context.put("modeSemak","newMMK");
				context.put("readonlySemak","");
				context.put("disabledSemak","");
				context.put("modeKptsn","newMMK");
				context.put("readonlyKptsn","");
				context.put("disabledKptsn","");

				context.put("TUJUAN1","");
				context.put("LATARBELAKANG","");
				context.put("ASAS_PERTIMBANGAN", "");
				context.put("KESIMPULAN","");
				context.put("SYOR","");
				context.put("KEDUDUKAN_LAPORAN_TNH","");
				context.put("PENGESAHAN_PERUNTUKAN","");
				context.put("PANDANGAN","");
				context.put("IMPLIKASI","");
				context.put("PERIHALTANAH","");
				context.put("PERIHAL_POHON","");
				context.put("ANGGARAN_KOS","");
				context.put("PERAKUAN_PENTADBIR_TNH","");
				context.put("NILAI_ATAS_TANAH","");
				context.put("PENGECUALIAN_UPAH_UKUR","");
				context.put("HAL_LAIN","");
				context.put("JAWATANKUASA_TANAH","");
				context.put("NAMA_TUAN_TANAH", "");
				context.put("TAJUK","");
				context.put("ULASAN","");
				context.put("ULASAN_JABTEKNIKAL","");
				context.put("ULASAN_PENGARAH","");
				context.put("KEPUTUSAN","");
				context.put("BUTIR_ASAS","");
				context.put("KEADAAN_TANAH","");
				context.put("BUTIR_TANAH","");
				context.put("KEMUDAHAN_ASAS","");
				context.put("PERAKUAN_SETIAUSAHA","");
				context.put("PENUTUP","");
				context.put("NAMAPEGAWAI","");
				context.put("TARIKHSEMAKAN","");
				context.put("KEPUTUSANSEMAKAN","");
				context.put("ULASANSEMAKAN","");
				context.put("NORUJUKAN","");
				context.put("TARIKHHANTAR","");
				context.put("TARIKHKEPUTUSAN","");
				context.put("TARIKHTERIMA","");
				context.put("TARIKH","");
				context.put("STATUSKEPUTUSAN","");
				context.put("ULASANKEPUTUSAN","");
				
				
			}
    		
    	}
    	else if("tabKeputusan".equals(action)){
    		
    		vm = "app/ppt/frmHakmilikSementaraMMKKeputusan.jsp";
    	    		
    		Vector getrecord_permohonan = prmhnnMaster.getRecord(id_permohonan);
			Hashtable h = (Hashtable) getrecord_permohonan.get(0);
			context.put("nama_kementerian",h.get("nama_kementerian"));
			context.put("tarikh_terima",h.get("tarikh_terima"));
			context.put("projek_negeri",h.get("projek_negeri"));
			context.put("nama_daerah",h.get("nama_daerah"));
			context.put("tarikh_kehendaki",h.get("tarikh_kehendaki"));
			context.put("tujuan",h.get("tujuan"));
			context.put("no_fail",h.get("no_fail"));
			context.put("no_rujukan_surat",h.get("no_rujukan_surat"));
			context.put("no_rujukan_ptd",h.get("no_rujukan_ptd"));
			context.put("no_rujukan_ptg",h.get("no_rujukan_ptg"));
			context.put("no_rujukan_upt",h.get("no_rujukan_upt"));
			context.put("keterangan",h.get("keterangan"));
			
			
			String idAgensi = h.get("id_agensi").toString();
			if(idAgensi!="")
			{
				int id_agensi = Integer.parseInt(idAgensi);
				listAgensi = prmhnnMaster.getNamaAgensi(idAgensi);
				Hashtable ag = (Hashtable) listAgensi.get(0);
				context.put("idAgensi", ag.get("nama_agensi").toString()); 
			}
			else
			{
				context.put("idAgensi","-");
			}
			
			mmkData.check_status(id_permohonan);
			checkStatus = mmkData.getStatus();
			Hashtable hC = (Hashtable)checkStatus.get(0);
			context.put("idStatus", hC.get("status").toString());			
			
			
			
			if(Integer.parseInt(hC.get("status").toString()) == 132){
				mmkData.setPaparMMK(id_permohonan);
				paparMMK = mmkData.getPaparMMK();
				Hashtable hM = (Hashtable)paparMMK.get(0);
    			
				context.put("mode","paparMMK");
				context.put("readonly","readonly");
				context.put("disabled","disabled");
				context.put("modeSemak","newMMK");
				context.put("readonlySemak","");
				context.put("disabledSemak","");
				context.put("modeKptsn","newMMK");
				context.put("readonlyKptsn","");
				context.put("disabledKptsn","");
				
				context.put("idMMK",hM.get("ID_MMK"));
				context.put("TUJUAN1",hM.get("TUJUAN"));
				context.put("LATARBELAKANG",hM.get("LATARBELAKANG"));
				context.put("ASAS_PERTIMBANGAN", hM.get("ASAS_PERTIMBANGAN"));
				context.put("KESIMPULAN",hM.get("KESIMPULAN"));
				context.put("SYOR",hM.get("SYOR"));
				context.put("KEDUDUKAN_LAPORAN_TNH", hM.get("KEDUDUKAN_LAPORAN_TNH"));
				context.put("PENGESAHAN_PERUNTUKAN",hM.get("PENGESAHAN_PERUNTUKAN"));
				context.put("PANDANGAN", hM.get("PANDANGAN"));
				context.put("IMPLIKASI",hM.get("IMPLIKASI"));
				context.put("PERIHALTANAH",hM.get("PERIHAL_TANAH"));
				context.put("PERIHAL_POHON",hM.get("PERIHAL_POHON"));
				context.put("ANGGARAN_KOS",hM.get("ANGGARAN_KOS"));
				context.put("PERAKUAN_PENTADBIR_TNH",hM.get("PERAKUAN_PENTADBIR_TNH"));
				context.put("NILAI_ATAS_TANAH",hM.get("NILAI_ATAS_TANAH"));
				context.put("PENGECUALIAN_UPAH_UKUR",hM.get("PENGECUALIAN_UPAH_UKUR"));
				context.put("HAL_LAIN",hM.get("HAL_LAIN"));
				context.put("JAWATANKUASA_TANAH",hM.get("JAWATANKUASA_TANAH"));
				context.put("NAMA_TUAN_TANAH", hM.get("NAMA_TUAN_TANAH"));
				context.put("TAJUK",hM.get("TAJUK"));
				context.put("ULASAN",hM.get("ULASAN"));
				context.put("ULASAN_JABTEKNIKAL",hM.get("ULASAN_JABTEKNIKAL"));
				context.put("ULASAN_PENGARAH",hM.get("ULASAN_PENGARAH"));
				context.put("KEPUTUSAN",hM.get("KEPUTUSAN"));
				context.put("BUTIR_ASAS",hM.get("BUTIR_ASAS"));
				context.put("KEADAAN_TANAH",hM.get("KEADAAN_TANAH"));
				context.put("BUTIR_TANAH",hM.get("BUTIR_TANAH"));
				context.put("KEMUDAHAN_ASAS",hM.get("KEMUDAHAN_ASAS"));
				context.put("PERAKUAN_SETIAUSAHA",hM.get("PERAKUAN_SETIAUSAHA"));
				context.put("PENUTUP",hM.get("PENUTUP"));
				context.put("NAMAPEGAWAI","");
				context.put("TARIKHSEMAKAN","");
				context.put("KEPUTUSANSEMAKAN","");
				context.put("ULASANSEMAKAN","");
				context.put("NORUJUKAN","");
				context.put("TARIKHHANTAR","");
				context.put("TARIKHKEPUTUSAN","");
				context.put("TARIKHTERIMA","");
				context.put("TARIKH","");
				context.put("STATUSKEPUTUSAN","");
				context.put("ULASANKEPUTUSAN","");
				

			}
			else if(Integer.parseInt(hC.get("status").toString()) == 133){
				mmkData.setPaparMMK(id_permohonan);
				paparMMK = mmkData.getPaparMMK();
				Hashtable hM = (Hashtable)paparMMK.get(0);
			    			
				context.put("mode","paparMMK");
				context.put("readonly","readonly");
				context.put("disabled","disabled");
				context.put("modeSemak","paparMMK");
				context.put("readonlySemak","readonly");
				context.put("disabledSemak","disabled");
				context.put("modeKptsn","newMMK");
				context.put("readonlyKptsn","");
				context.put("disabledKptsn","");
				
				context.put("idMMK",hM.get("ID_MMK"));
				context.put("TUJUAN1",hM.get("TUJUAN"));
				context.put("LATARBELAKANG",hM.get("LATARBELAKANG"));
				context.put("ASAS_PERTIMBANGAN", hM.get("ASAS_PERTIMBANGAN"));
				context.put("KESIMPULAN",hM.get("KESIMPULAN"));
				context.put("SYOR",hM.get("SYOR"));
				context.put("KEDUDUKAN_LAPORAN_TNH", hM.get("KEDUDUKAN_LAPORAN_TNH"));
				context.put("PENGESAHAN_PERUNTUKAN",hM.get("PENGESAHAN_PERUNTUKAN"));
				context.put("PANDANGAN", hM.get("PANDANGAN"));
				context.put("IMPLIKASI",hM.get("IMPLIKASI"));
				context.put("PERIHALTANAH",hM.get("PERIHAL_TANAH"));
				context.put("PERIHAL_POHON",hM.get("PERIHAL_POHON"));
				context.put("ANGGARAN_KOS",hM.get("ANGGARAN_KOS"));
				context.put("PERAKUAN_PENTADBIR_TNH",hM.get("PERAKUAN_PENTADBIR_TNH"));
				context.put("NILAI_ATAS_TANAH",hM.get("NILAI_ATAS_TANAH"));
				context.put("PENGECUALIAN_UPAH_UKUR",hM.get("PENGECUALIAN_UPAH_UKUR"));
				context.put("HAL_LAIN",hM.get("HAL_LAIN"));
				context.put("JAWATANKUASA_TANAH",hM.get("JAWATANKUASA_TANAH"));
				context.put("NAMA_TUAN_TANAH", hM.get("NAMA_TUAN_TANAH"));
				context.put("TAJUK",hM.get("TAJUK"));
				context.put("ULASAN",hM.get("ULASAN"));
				context.put("ULASAN_JABTEKNIKAL",hM.get("ULASAN_JABTEKNIKAL"));
				context.put("ULASAN_PENGARAH",hM.get("ULASAN_PENGARAH"));
				context.put("KEPUTUSAN",hM.get("KEPUTUSAN"));
				context.put("BUTIR_ASAS",hM.get("BUTIR_ASAS"));
				context.put("KEADAAN_TANAH",hM.get("KEADAAN_TANAH"));
				context.put("BUTIR_TANAH",hM.get("BUTIR_TANAH"));
				context.put("KEMUDAHAN_ASAS",hM.get("KEMUDAHAN_ASAS"));
				context.put("PERAKUAN_SETIAUSAHA",hM.get("PERAKUAN_SETIAUSAHA"));
				context.put("PENUTUP",hM.get("PENUTUP"));
				context.put("NAMAPEGAWAI",hM.get("USER_NAME"));
				context.put("TARIKHSEMAKAN",hM.get("TARIKH_SEMAK"));
				context.put("KEPUTUSANSEMAKAN",hM.get("STATUS_SEMAKAN"));
				context.put("ULASANSEMAKAN",hM.get("ULASAN"));
				context.put("NORUJUKAN","");
				context.put("TARIKHHANTAR","");
				context.put("TARIKHKEPUTUSAN","");
				context.put("TARIKHTERIMA","");
				context.put("TARIKH","");
				context.put("STATUSKEPUTUSAN","");
				context.put("ULASANKEPUTUSAN","");


			}
			else if(Integer.parseInt(hC.get("status").toString()) == 134){
				mmkData.setPaparMMK(id_permohonan);
				paparMMK = mmkData.getPaparMMK();
				Hashtable hM = (Hashtable)paparMMK.get(0);
				
				context.put("mode","paparMMK");
				context.put("readonly","readonly");
				context.put("disabled","disabled");
				context.put("modeSemak","paparMMK");
				context.put("readonlySemak","readonly");
				context.put("disabledSemak","disabled");
				context.put("modeKptsn","paparMMK");
				context.put("readonlyKptsn","readonly");
				context.put("disabledKptsn","disabled");
				
				context.put("idMMK",hM.get("ID_MMK"));
				context.put("TUJUAN1",hM.get("TUJUAN"));
				context.put("LATARBELAKANG",hM.get("LATARBELAKANG"));
				context.put("ASAS_PERTIMBANGAN", hM.get("ASAS_PERTIMBANGAN"));
				context.put("KESIMPULAN",hM.get("KESIMPULAN"));
				context.put("SYOR",hM.get("SYOR"));
				context.put("KEDUDUKAN_LAPORAN_TNH", hM.get("KEDUDUKAN_LAPORAN_TNH"));
				context.put("PENGESAHAN_PERUNTUKAN",hM.get("PENGESAHAN_PERUNTUKAN"));
				context.put("PANDANGAN", hM.get("PANDANGAN"));
				context.put("IMPLIKASI",hM.get("IMPLIKASI"));
				context.put("PERIHALTANAH",hM.get("PERIHAL_TANAH"));
				context.put("PERIHAL_POHON",hM.get("PERIHAL_POHON"));
				context.put("ANGGARAN_KOS",hM.get("ANGGARAN_KOS"));
				context.put("PERAKUAN_PENTADBIR_TNH",hM.get("PERAKUAN_PENTADBIR_TNH"));
				context.put("NILAI_ATAS_TANAH",hM.get("NILAI_ATAS_TANAH"));
				context.put("PENGECUALIAN_UPAH_UKUR",hM.get("PENGECUALIAN_UPAH_UKUR"));
				context.put("HAL_LAIN",hM.get("HAL_LAIN"));
				context.put("JAWATANKUASA_TANAH",hM.get("JAWATANKUASA_TANAH"));
				context.put("NAMA_TUAN_TANAH", hM.get("NAMA_TUAN_TANAH"));
				context.put("TAJUK",hM.get("TAJUK"));
				context.put("ULASAN",hM.get("ULASAN"));
				context.put("ULASAN_JABTEKNIKAL",hM.get("ULASAN_JABTEKNIKAL"));
				context.put("ULASAN_PENGARAH",hM.get("ULASAN_PENGARAH"));
				context.put("KEPUTUSAN",hM.get("KEPUTUSAN"));
				context.put("BUTIR_ASAS",hM.get("BUTIR_ASAS"));
				context.put("KEADAAN_TANAH",hM.get("KEADAAN_TANAH"));
				context.put("BUTIR_TANAH",hM.get("BUTIR_TANAH"));
				context.put("KEMUDAHAN_ASAS",hM.get("KEMUDAHAN_ASAS"));
				context.put("PERAKUAN_SETIAUSAHA",hM.get("PERAKUAN_SETIAUSAHA"));
				context.put("PENUTUP",hM.get("PENUTUP"));
				context.put("NAMAPEGAWAI",hM.get("USER_NAME"));
				context.put("TARIKHSEMAKAN",hM.get("TARIKH_SEMAK"));
				context.put("KEPUTUSANSEMAKAN",hM.get("STATUS_SEMAKAN"));
				context.put("ULASANSEMAKAN",hM.get("ULASAN"));
				context.put("idMMKKptsn",hM.get("ID_MMKKEPUTUSAN"));
				context.put("NORUJUKAN",hM.get("NO_RUJMMK"));
				context.put("TARIKHHANTAR",hM.get("TARIKH_HANTAR"));
				context.put("TARIKHKEPUTUSAN",hM.get("TARIKH_KEPUTUSAN"));
				context.put("TARIKHTERIMA",hM.get("TARIKH_TERIMA"));
				context.put("TARIKH",hM.get("TARIKH_MMK"));
				context.put("STATUSKEPUTUSAN",hM.get("STATUS_KEPUTUSAN"));
				context.put("ULASANKEPUTUSAN",hM.get("ULASAN_KEPUTUSAN"));
			}
			else{
				context.put("mode","newMMK");
				context.put("readonly","");
				context.put("disabled","");
				context.put("modeSemak","newMMK");
				context.put("readonlySemak","");
				context.put("disabledSemak","");
				context.put("modeKptsn","newMMK");
				context.put("readonlyKptsn","");
				context.put("disabledKptsn","");

				context.put("TUJUAN1","");
				context.put("LATARBELAKANG","");
				context.put("ASAS_PERTIMBANGAN", "");
				context.put("KESIMPULAN","");
				context.put("SYOR","");
				context.put("KEDUDUKAN_LAPORAN_TNH","");
				context.put("PENGESAHAN_PERUNTUKAN","");
				context.put("PANDANGAN","");
				context.put("IMPLIKASI","");
				context.put("PERIHALTANAH","");
				context.put("PERIHAL_POHON","");
				context.put("ANGGARAN_KOS","");
				context.put("PERAKUAN_PENTADBIR_TNH","");
				context.put("NILAI_ATAS_TANAH","");
				context.put("PENGECUALIAN_UPAH_UKUR","");
				context.put("HAL_LAIN","");
				context.put("JAWATANKUASA_TANAH","");
				context.put("NAMA_TUAN_TANAH", "");
				context.put("TAJUK","");
				context.put("ULASAN","");
				context.put("ULASAN_JABTEKNIKAL","");
				context.put("ULASAN_PENGARAH","");
				context.put("KEPUTUSAN","");
				context.put("BUTIR_ASAS","");
				context.put("KEADAAN_TANAH","");
				context.put("BUTIR_TANAH","");
				context.put("KEMUDAHAN_ASAS","");
				context.put("PERAKUAN_SETIAUSAHA","");
				context.put("PENUTUP","");
				context.put("NAMAPEGAWAI","");
				context.put("TARIKHSEMAKAN","");
				context.put("KEPUTUSANSEMAKAN","");
				context.put("ULASANSEMAKAN","");
				context.put("NORUJUKAN","");
				context.put("TARIKHHANTAR","");
				context.put("TARIKHKEPUTUSAN","");
				context.put("TARIKHTERIMA","");
				context.put("TARIKH","");
				context.put("STATUSKEPUTUSAN","");
				context.put("ULASANKEPUTUSAN","");
				
				
			}

    		
    	}
    	else if ("simpanPenyediaan".equals(action)){
    		
    		String idMMK = addPenyediaan(session);
    		editStatusPenyediaan(session);
    		context.put("idMMK", idMMK);
    		
    		vm = "app/ppt/frmHakmilikSementaraMMKPenyediaan.jsp";
			context.put("mode","paparMMK");
    		context.put("readonly","readonly");
			context.put("disabled","disabled");
    		
    		
    		Vector getrecord_permohonan = prmhnnMaster.getRecord(id_permohonan);
			Hashtable h = (Hashtable) getrecord_permohonan.get(0);
			context.put("nama_kementerian",h.get("nama_kementerian"));
			context.put("tarikh_terima",h.get("tarikh_terima"));
			context.put("projek_negeri",h.get("projek_negeri"));
			context.put("nama_daerah",h.get("nama_daerah"));
			context.put("tarikh_kehendaki",h.get("tarikh_kehendaki"));
			context.put("tujuan",h.get("tujuan"));
			context.put("no_fail",h.get("no_fail"));
			context.put("no_rujukan_surat",h.get("no_rujukan_surat"));
			context.put("no_rujukan_ptd",h.get("no_rujukan_ptd"));
			context.put("no_rujukan_ptg",h.get("no_rujukan_ptg"));
			context.put("no_rujukan_upt",h.get("no_rujukan_upt"));
			context.put("keterangan",h.get("keterangan"));
			
			
			String idAgensi = h.get("id_agensi").toString();
			if(idAgensi!="")
			{
				int id_agensi = Integer.parseInt(idAgensi);
				listAgensi = prmhnnMaster.getNamaAgensi(idAgensi);
				Hashtable ag = (Hashtable) listAgensi.get(0);
				context.put("idAgensi", ag.get("nama_agensi").toString()); 
			}
			else
			{
				context.put("idAgensi","-");
			}
    		
			mmkData.setButiranAsas(id_fail, id_permohonan);
			butiran = mmkData.getButiranAsas();
			Hashtable hB = (Hashtable)butiran.get(0);
			
			context.put("PEMOHON",hB.get("pemohon"));
			context.put("TUJUAN",hB.get("tujuan"));
			context.put("LOKASI", hB.get("lokasi"));
    		
			mmkData.setButiranLot(id_fail, id_permohonan);
			butiranLot = mmkData.getButiranLot();
			Hashtable hL = (Hashtable)butiranLot.get(0);
			
			context.put("BILLOT",hL.get("bilLot"));
			
			mmkData.setSewaLot(id_fail, id_permohonan);
			sewaLot = mmkData.getSewaLot();
			Hashtable hS = (Hashtable)sewaLot.get(0);
			
			context.put("LUASSEWA",hS.get("sewaLot"));
			
			mmkData.setNoLot(id_fail, id_permohonan);
			noLot = mmkData.getNoLot();
			Hashtable hN;
			String lot = "";
			
			if (noLot.size() > 1){
				
				for (int i = 0; i < noLot.size(); i++){
    				hN = (Hashtable)noLot.get(i);
    				if (lot.equals("")){
    					lot = hN.get("noLot").toString();
    				}
    				else{
        				lot = lot + ", " + hN.get("noLot");

    				}
    				
    			}
				
				context.put("NOLOT",lot);
			}
			else{
				hN = (Hashtable)noLot.get(0);
				context.put("NOLOT",hN.get("noLot"));
			}
			
			context.put("TUJUAN1",getParam("txtTujuan"));
			context.put("LATARBELAKANG",getParam("txtLatarBelakang"));
			context.put("ASAS_PERTIMBANGAN", getParam("txtAsasPertimbangan"));
			context.put("KESIMPULAN",getParam("txtKesimpulan"));
			context.put("SYOR",getParam("txtSyor"));
			context.put("KEDUDUKAN_LAPORAN_TNH",getParam("txtKedudukanLaporanTanah"));
			context.put("PENGESAHAN_PERUNTUKAN",getParam("txtPengesahanPeruntukan"));
			context.put("PANDANGAN",getParam("txtPandangan"));
			context.put("IMPLIKASI",getParam("txtImplikasi"));
			context.put("PERIHALTANAH",getParam("txtPerihalTanah"));
			context.put("PERIHAL_POHON",getParam("txtPerihalPohon"));
			context.put("ANGGARAN_KOS",getParam("txtAnggaranKos"));
			context.put("PERAKUAN_PENTADBIR_TNH",getParam("txtPerakuanPentadbir"));
			context.put("NILAI_ATAS_TANAH",getParam("txtNilaianTanah"));
			context.put("PENGECUALIAN_UPAH_UKUR",getParam("txtPengecualianUpahUkur"));
			context.put("HAL_LAIN",getParam("txtHalLain"));
			context.put("JAWATANKUASA_TANAH",getParam("txtJawatankuasaTanah"));
			context.put("NAMA_TUAN_TANAH", getParam("txtNamaTuanTanah"));
			context.put("TAJUK",getParam("txtTajuk"));
			context.put("ULASAN",getParam("txtUlasan"));
			context.put("ULASAN_JABTEKNIKAL",getParam("txtUlasanJT"));
			context.put("ULASAN_PENGARAH",getParam("txtUlasanPengarah"));
			context.put("KEPUTUSAN",getParam("txtKeputusan"));
			context.put("BUTIR_ASAS",getParam("txtButirAsas"));
			context.put("KEADAAN_TANAH",getParam("txtKeadaanTanah"));
			context.put("BUTIR_TANAH",getParam("txtButirTanah"));
			context.put("KEMUDAHAN_ASAS",getParam("txtKemudahanAsas"));
			context.put("PERAKUAN_SETIAUSAHA",getParam("txtPerakuanSetiausaha"));
			context.put("PENUTUP",getParam("txtPenutup"));
			
			
    		
    	}
    	else if ("updatePenyediaan".equals(action)){
    		
    		updatePenyediaan(session);

    		vm = "app/ppt/frmHakmilikSementaraMMKPenyediaan.jsp";
			context.put("mode","paparMMK");
    		context.put("readonly","readonly");
			context.put("disabled","disabled");
    		
    		
    		Vector getrecord_permohonan = prmhnnMaster.getRecord(id_permohonan);
			Hashtable h = (Hashtable) getrecord_permohonan.get(0);
			context.put("nama_kementerian",h.get("nama_kementerian"));
			context.put("tarikh_terima",h.get("tarikh_terima"));
			context.put("projek_negeri",h.get("projek_negeri"));
			context.put("nama_daerah",h.get("nama_daerah"));
			context.put("tarikh_kehendaki",h.get("tarikh_kehendaki"));
			context.put("tujuan",h.get("tujuan"));
			context.put("no_fail",h.get("no_fail"));
			context.put("no_rujukan_surat",h.get("no_rujukan_surat"));
			context.put("no_rujukan_ptd",h.get("no_rujukan_ptd"));
			context.put("no_rujukan_ptg",h.get("no_rujukan_ptg"));
			context.put("no_rujukan_upt",h.get("no_rujukan_upt"));
			context.put("keterangan",h.get("keterangan"));
			
			
			String idAgensi = h.get("id_agensi").toString();
			if(idAgensi!="")
			{
				int id_agensi = Integer.parseInt(idAgensi);
				listAgensi = prmhnnMaster.getNamaAgensi(idAgensi);
				Hashtable ag = (Hashtable) listAgensi.get(0);
				context.put("idAgensi", ag.get("nama_agensi").toString()); 
			}
			else
			{
				context.put("idAgensi","-");
			}
    		
			mmkData.setButiranAsas(id_fail, id_permohonan);
			butiran = mmkData.getButiranAsas();
			Hashtable hB = (Hashtable)butiran.get(0);
			
			context.put("PEMOHON",hB.get("pemohon"));
			context.put("TUJUAN",hB.get("tujuan"));
			context.put("LOKASI", hB.get("lokasi"));
    		
			mmkData.setButiranLot(id_fail, id_permohonan);
			butiranLot = mmkData.getButiranLot();
			Hashtable hL = (Hashtable)butiranLot.get(0);
			
			context.put("BILLOT",hL.get("bilLot"));
			
			mmkData.setSewaLot(id_fail, id_permohonan);
			sewaLot = mmkData.getSewaLot();
			Hashtable hS = (Hashtable)sewaLot.get(0);
			
			context.put("LUASSEWA",hS.get("sewaLot"));
			
			mmkData.setNoLot(id_fail, id_permohonan);
			noLot = mmkData.getNoLot();
			Hashtable hN;
			String lot = "";
			
			if (noLot.size() > 1){
				
				for (int i = 0; i < noLot.size(); i++){
    				hN = (Hashtable)noLot.get(i);
    				if (lot.equals("")){
    					lot = hN.get("noLot").toString();
    				}
    				else{
        				lot = lot + ", " + hN.get("noLot");

    				}
    				
    			}
				
				context.put("NOLOT",lot);
			}
			else{
				hN = (Hashtable)noLot.get(0);
				context.put("NOLOT",hN.get("noLot"));
			}
			
			mmkData.setPaparMMK(id_permohonan);
			paparMMK = mmkData.getPaparMMK();
			Hashtable hM = (Hashtable)paparMMK.get(0);
			
			
			context.put("idMMK",hM.get("ID_MMK"));
			context.put("TUJUAN1",hM.get("TUJUAN"));
			context.put("LATARBELAKANG",hM.get("LATARBELAKANG"));
			context.put("ASAS_PERTIMBANGAN", hM.get("ASAS_PERTIMBANGAN"));
			context.put("KESIMPULAN",hM.get("KESIMPULAN"));
			context.put("SYOR",hM.get("SYOR"));
			context.put("KEDUDUKAN_LAPORAN_TNH", hM.get("KEDUDUKAN_LAPORAN_TNH"));
			context.put("PENGESAHAN_PERUNTUKAN",hM.get("PENGESAHAN_PERUNTUKAN"));
			context.put("PANDANGAN", hM.get("PANDANGAN"));
			context.put("IMPLIKASI",hM.get("IMPLIKASI"));
			context.put("PERIHALTANAH",hM.get("PERIHAL_TANAH"));
			context.put("PERIHAL_POHON",hM.get("PERIHAL_POHON"));
			context.put("ANGGARAN_KOS",hM.get("ANGGARAN_KOS"));
			context.put("PERAKUAN_PENTADBIR_TNH",hM.get("PERAKUAN_PENTADBIR_TNH"));
			context.put("NILAI_ATAS_TANAH",hM.get("NILAI_ATAS_TANAH"));
			context.put("PENGECUALIAN_UPAH_UKUR",hM.get("PENGECUALIAN_UPAH_UKUR"));
			context.put("HAL_LAIN",hM.get("HAL_LAIN"));
			context.put("JAWATANKUASA_TANAH",hM.get("JAWATANKUASA_TANAH"));
			context.put("NAMA_TUAN_TANAH", hM.get("NAMA_TUAN_TANAH"));
			context.put("TAJUK",hM.get("TAJUK"));
			context.put("ULASAN",hM.get("ULASAN"));
			context.put("ULASAN_JABTEKNIKAL",hM.get("ULASAN_JABTEKNIKAL"));
			context.put("ULASAN_PENGARAH",hM.get("ULASAN_PENGARAH"));
			context.put("KEPUTUSAN",hM.get("KEPUTUSAN"));
			context.put("BUTIR_ASAS",hM.get("BUTIR_ASAS"));
			context.put("KEADAAN_TANAH",hM.get("KEADAAN_TANAH"));
			context.put("BUTIR_TANAH",hM.get("BUTIR_TANAH"));
			context.put("KEMUDAHAN_ASAS",hM.get("KEMUDAHAN_ASAS"));
			context.put("PERAKUAN_SETIAUSAHA",hM.get("PERAKUAN_SETIAUSAHA"));
			context.put("PENUTUP",hM.get("PENUTUP"));
			
    		
    	}
    	else if ("kemaskiniPenyediaan".equals(action)){
    		

    		vm = "app/ppt/frmHakmilikSementaraMMKPenyediaan.jsp";
			context.put("mode","kemaskiniMMK");
    		context.put("readonly","");
			context.put("disabled","");
    		
    		
    		Vector getrecord_permohonan = prmhnnMaster.getRecord(id_permohonan);
			Hashtable h = (Hashtable) getrecord_permohonan.get(0);
			context.put("nama_kementerian",h.get("nama_kementerian"));
			context.put("tarikh_terima",h.get("tarikh_terima"));
			context.put("projek_negeri",h.get("projek_negeri"));
			context.put("nama_daerah",h.get("nama_daerah"));
			context.put("tarikh_kehendaki",h.get("tarikh_kehendaki"));
			context.put("tujuan",h.get("tujuan"));
			context.put("no_fail",h.get("no_fail"));
			context.put("no_rujukan_surat",h.get("no_rujukan_surat"));
			context.put("no_rujukan_ptd",h.get("no_rujukan_ptd"));
			context.put("no_rujukan_ptg",h.get("no_rujukan_ptg"));
			context.put("no_rujukan_upt",h.get("no_rujukan_upt"));
			context.put("keterangan",h.get("keterangan"));
			
			
			String idAgensi = h.get("id_agensi").toString();
			if(idAgensi!="")
			{
				int id_agensi = Integer.parseInt(idAgensi);
				listAgensi = prmhnnMaster.getNamaAgensi(idAgensi);
				Hashtable ag = (Hashtable) listAgensi.get(0);
				context.put("idAgensi", ag.get("nama_agensi").toString()); 
			}
			else
			{
				context.put("idAgensi","-");
			}
    		
			mmkData.setButiranAsas(id_fail, id_permohonan);
			butiran = mmkData.getButiranAsas();
			Hashtable hB = (Hashtable)butiran.get(0);
			
			context.put("PEMOHON",hB.get("pemohon"));
			context.put("TUJUAN",hB.get("tujuan"));
			context.put("LOKASI", hB.get("lokasi"));
    		
			mmkData.setButiranLot(id_fail, id_permohonan);
			butiranLot = mmkData.getButiranLot();
			Hashtable hL = (Hashtable)butiranLot.get(0);
			
			context.put("BILLOT",hL.get("bilLot"));
			
			mmkData.setSewaLot(id_fail, id_permohonan);
			sewaLot = mmkData.getSewaLot();
			Hashtable hS = (Hashtable)sewaLot.get(0);
			
			context.put("LUASSEWA",hS.get("sewaLot"));
			
			mmkData.setNoLot(id_fail, id_permohonan);
			noLot = mmkData.getNoLot();
			Hashtable hN;
			String lot = "";
			
			if (noLot.size() > 1){
				
				for (int i = 0; i < noLot.size(); i++){
    				hN = (Hashtable)noLot.get(i);
    				if (lot.equals("")){
    					lot = hN.get("noLot").toString();
    				}
    				else{
        				lot = lot + ", " + hN.get("noLot");

    				}
    				
    			}
				
				context.put("NOLOT",lot);
			}
			else{
				hN = (Hashtable)noLot.get(0);
				context.put("NOLOT",hN.get("noLot"));
			}
			
			
			mmkData.setPaparMMK(id_permohonan);
			paparMMK = mmkData.getPaparMMK();
			Hashtable hM = (Hashtable)paparMMK.get(0);
			
			
			context.put("idMMK",hM.get("ID_MMK"));
			context.put("TUJUAN1",hM.get("TUJUAN"));
			context.put("LATARBELAKANG",hM.get("LATARBELAKANG"));
			context.put("ASAS_PERTIMBANGAN", hM.get("ASAS_PERTIMBANGAN"));
			context.put("KESIMPULAN",hM.get("KESIMPULAN"));
			context.put("SYOR",hM.get("SYOR"));
			context.put("KEDUDUKAN_LAPORAN_TNH", hM.get("KEDUDUKAN_LAPORAN_TNH"));
			context.put("PENGESAHAN_PERUNTUKAN",hM.get("PENGESAHAN_PERUNTUKAN"));
			context.put("PANDANGAN", hM.get("PANDANGAN"));
			context.put("IMPLIKASI",hM.get("IMPLIKASI"));
			context.put("PERIHALTANAH",hM.get("PERIHAL_TANAH"));
			context.put("PERIHAL_POHON",hM.get("PERIHAL_POHON"));
			context.put("ANGGARAN_KOS",hM.get("ANGGARAN_KOS"));
			context.put("PERAKUAN_PENTADBIR_TNH",hM.get("PERAKUAN_PENTADBIR_TNH"));
			context.put("NILAI_ATAS_TANAH",hM.get("NILAI_ATAS_TANAH"));
			context.put("PENGECUALIAN_UPAH_UKUR",hM.get("PENGECUALIAN_UPAH_UKUR"));
			context.put("HAL_LAIN",hM.get("HAL_LAIN"));
			context.put("JAWATANKUASA_TANAH",hM.get("JAWATANKUASA_TANAH"));
			context.put("NAMA_TUAN_TANAH", hM.get("NAMA_TUAN_TANAH"));
			context.put("TAJUK",hM.get("TAJUK"));
			context.put("ULASAN",hM.get("ULASAN"));
			context.put("ULASAN_JABTEKNIKAL",hM.get("ULASAN_JABTEKNIKAL"));
			context.put("ULASAN_PENGARAH",hM.get("ULASAN_PENGARAH"));
			context.put("KEPUTUSAN",hM.get("KEPUTUSAN"));
			context.put("BUTIR_ASAS",hM.get("BUTIR_ASAS"));
			context.put("KEADAAN_TANAH",hM.get("KEADAAN_TANAH"));
			context.put("BUTIR_TANAH",hM.get("BUTIR_TANAH"));
			context.put("KEMUDAHAN_ASAS",hM.get("KEMUDAHAN_ASAS"));
			context.put("PERAKUAN_SETIAUSAHA",hM.get("PERAKUAN_SETIAUSAHA"));
			context.put("PENUTUP",hM.get("PENUTUP"));
    		
    	}
    	else if ("simpanSemakan".equals(action)){
    		
    		addSemakan(session);
    		editStatusSemakan(session);
    		
    		vm = "app/ppt/frmHakmilikSementaraMMKSemakan.jsp";
    		context.put("modeSemak","paparMMK");
    		context.put("readonlySemak","readonly");
			context.put("disabledSemak","disabled");
    		
    		
    		Vector getrecord_permohonan = prmhnnMaster.getRecord(id_permohonan);
			Hashtable h = (Hashtable) getrecord_permohonan.get(0);
			context.put("nama_kementerian",h.get("nama_kementerian"));
			context.put("tarikh_terima",h.get("tarikh_terima"));
			context.put("projek_negeri",h.get("projek_negeri"));
			context.put("nama_daerah",h.get("nama_daerah"));
			context.put("tarikh_kehendaki",h.get("tarikh_kehendaki"));
			context.put("tujuan",h.get("tujuan"));
			context.put("no_fail",h.get("no_fail"));
			context.put("no_rujukan_surat",h.get("no_rujukan_surat"));
			context.put("no_rujukan_ptd",h.get("no_rujukan_ptd"));
			context.put("no_rujukan_ptg",h.get("no_rujukan_ptg"));
			context.put("no_rujukan_upt",h.get("no_rujukan_upt"));
			context.put("keterangan",h.get("keterangan"));
			
			
			String idAgensi = h.get("id_agensi").toString();
			if(idAgensi!="")
			{
				int id_agensi = Integer.parseInt(idAgensi);
				listAgensi = prmhnnMaster.getNamaAgensi(idAgensi);
				Hashtable ag = (Hashtable) listAgensi.get(0);
				context.put("idAgensi", ag.get("nama_agensi").toString()); 
			}
			else
			{
				context.put("idAgensi","-");
			}
    		
			mmkData.setButiranAsas(id_fail, id_permohonan);
			butiran = mmkData.getButiranAsas();
			Hashtable hB = (Hashtable)butiran.get(0);
			
			context.put("PEMOHON",hB.get("pemohon"));
			context.put("TUJUAN",hB.get("tujuan"));
			context.put("LOKASI", hB.get("lokasi"));
    		
			mmkData.setButiranLot(id_fail, id_permohonan);
			butiranLot = mmkData.getButiranLot();
			Hashtable hL = (Hashtable)butiranLot.get(0);
			
			context.put("BILLOT",hL.get("bilLot"));
			
			mmkData.setSewaLot(id_fail, id_permohonan);
			sewaLot = mmkData.getSewaLot();
			Hashtable hS = (Hashtable)sewaLot.get(0);
			
			context.put("LUASSEWA",hS.get("sewaLot"));
			
			mmkData.setNoLot(id_fail, id_permohonan);
			noLot = mmkData.getNoLot();
			Hashtable hN;
			String lot = "";
			
			if (noLot.size() > 1){
				
				for (int i = 0; i < noLot.size(); i++){
    				hN = (Hashtable)noLot.get(i);
    				if (lot.equals("")){
    					lot = hN.get("noLot").toString();
    				}
    				else{
        				lot = lot + ", " + hN.get("noLot");

    				}
    				
    			}
				
				context.put("NOLOT",lot);
			}
			else{
				hN = (Hashtable)noLot.get(0);
				context.put("NOLOT",hN.get("noLot"));
			}
			
			context.put("NAMAPEGAWAI",getParam("txtNamaPegawai"));
			context.put("TARIKHSEMAKAN",getParam("txdTarikhSemakan"));
			context.put("KEPUTUSANSEMAKAN",getParam("socKeputusan"));
			context.put("ULASANSEMAKAN",getParam("txtUlasan"));
			
    		
    	}
    	else if ("updateSemakan".equals(action)){
    		
    		updateSemakan(session);

    		vm = "app/ppt/frmHakmilikSementaraMMKSemakan.jsp";
			context.put("modeSemak","paparMMK");
    		context.put("readonlySemak","readonly");
			context.put("disabledSemak","disabled");
    		
    		
    		Vector getrecord_permohonan = prmhnnMaster.getRecord(id_permohonan);
			Hashtable h = (Hashtable) getrecord_permohonan.get(0);
			context.put("nama_kementerian",h.get("nama_kementerian"));
			context.put("tarikh_terima",h.get("tarikh_terima"));
			context.put("projek_negeri",h.get("projek_negeri"));
			context.put("nama_daerah",h.get("nama_daerah"));
			context.put("tarikh_kehendaki",h.get("tarikh_kehendaki"));
			context.put("tujuan",h.get("tujuan"));
			context.put("no_fail",h.get("no_fail"));
			context.put("no_rujukan_surat",h.get("no_rujukan_surat"));
			context.put("no_rujukan_ptd",h.get("no_rujukan_ptd"));
			context.put("no_rujukan_ptg",h.get("no_rujukan_ptg"));
			context.put("no_rujukan_upt",h.get("no_rujukan_upt"));
			context.put("keterangan",h.get("keterangan"));
			
			
			String idAgensi = h.get("id_agensi").toString();
			if(idAgensi!="")
			{
				int id_agensi = Integer.parseInt(idAgensi);
				listAgensi = prmhnnMaster.getNamaAgensi(idAgensi);
				Hashtable ag = (Hashtable) listAgensi.get(0);
				context.put("idAgensi", ag.get("nama_agensi").toString()); 
			}
			else
			{
				context.put("idAgensi","-");
			}
    		
			mmkData.setButiranAsas(id_fail, id_permohonan);
			butiran = mmkData.getButiranAsas();
			Hashtable hB = (Hashtable)butiran.get(0);
			
			context.put("PEMOHON",hB.get("pemohon"));
			context.put("TUJUAN",hB.get("tujuan"));
			context.put("LOKASI", hB.get("lokasi"));
    		
			mmkData.setButiranLot(id_fail, id_permohonan);
			butiranLot = mmkData.getButiranLot();
			Hashtable hL = (Hashtable)butiranLot.get(0);
			
			context.put("BILLOT",hL.get("bilLot"));
			
			mmkData.setSewaLot(id_fail, id_permohonan);
			sewaLot = mmkData.getSewaLot();
			Hashtable hS = (Hashtable)sewaLot.get(0);
			
			context.put("LUASSEWA",hS.get("sewaLot"));
			
			mmkData.setNoLot(id_fail, id_permohonan);
			noLot = mmkData.getNoLot();
			Hashtable hN;
			String lot = "";
			
			if (noLot.size() > 1){
				
				for (int i = 0; i < noLot.size(); i++){
    				hN = (Hashtable)noLot.get(i);
    				if (lot.equals("")){
    					lot = hN.get("noLot").toString();
    				}
    				else{
        				lot = lot + ", " + hN.get("noLot");

    				}
    				
    			}
				
				context.put("NOLOT",lot);
			}
			else{
				hN = (Hashtable)noLot.get(0);
				context.put("NOLOT",hN.get("noLot"));
			}
			
			mmkData.setPaparMMK(id_permohonan);
			paparMMK = mmkData.getPaparMMK();
			Hashtable hM = (Hashtable)paparMMK.get(0);
			
			
			context.put("NAMAPEGAWAI",hM.get("USER_NAME"));
			context.put("TARIKHSEMAKAN",hM.get("TARIKH_SEMAK"));
			context.put("KEPUTUSANSEMAKAN",hM.get("STATUS_SEMAKAN"));
			context.put("ULASANSEMAKAN",hM.get("ULASAN"));
			
    		
    	}
    	else if ("kemaskiniSemakan".equals(action)){
    		

    		vm = "app/ppt/frmHakmilikSementaraMMKSemakan.jsp";
			context.put("modeSemak","kemaskiniMMK");
    		context.put("readonlySemak","");
			context.put("disabledSemak","");
    		
    		
    		Vector getrecord_permohonan = prmhnnMaster.getRecord(id_permohonan);
			Hashtable h = (Hashtable) getrecord_permohonan.get(0);
			context.put("nama_kementerian",h.get("nama_kementerian"));
			context.put("tarikh_terima",h.get("tarikh_terima"));
			context.put("projek_negeri",h.get("projek_negeri"));
			context.put("nama_daerah",h.get("nama_daerah"));
			context.put("tarikh_kehendaki",h.get("tarikh_kehendaki"));
			context.put("tujuan",h.get("tujuan"));
			context.put("no_fail",h.get("no_fail"));
			context.put("no_rujukan_surat",h.get("no_rujukan_surat"));
			context.put("no_rujukan_ptd",h.get("no_rujukan_ptd"));
			context.put("no_rujukan_ptg",h.get("no_rujukan_ptg"));
			context.put("no_rujukan_upt",h.get("no_rujukan_upt"));
			context.put("keterangan",h.get("keterangan"));
			
			
			String idAgensi = h.get("id_agensi").toString();
			if(idAgensi!="")
			{
				int id_agensi = Integer.parseInt(idAgensi);
				listAgensi = prmhnnMaster.getNamaAgensi(idAgensi);
				Hashtable ag = (Hashtable) listAgensi.get(0);
				context.put("idAgensi", ag.get("nama_agensi").toString()); 
			}
			else
			{
				context.put("idAgensi","-");
			}
    		
			mmkData.setButiranAsas(id_fail, id_permohonan);
			butiran = mmkData.getButiranAsas();
			Hashtable hB = (Hashtable)butiran.get(0);
			
			context.put("PEMOHON",hB.get("pemohon"));
			context.put("TUJUAN",hB.get("tujuan"));
			context.put("LOKASI", hB.get("lokasi"));
    		
			mmkData.setButiranLot(id_fail, id_permohonan);
			butiranLot = mmkData.getButiranLot();
			Hashtable hL = (Hashtable)butiranLot.get(0);
			
			context.put("BILLOT",hL.get("bilLot"));
			
			mmkData.setSewaLot(id_fail, id_permohonan);
			sewaLot = mmkData.getSewaLot();
			Hashtable hS = (Hashtable)sewaLot.get(0);
			
			context.put("LUASSEWA",hS.get("sewaLot"));
			
			mmkData.setNoLot(id_fail, id_permohonan);
			noLot = mmkData.getNoLot();
			Hashtable hN;
			String lot = "";
			
			if (noLot.size() > 1){
				
				for (int i = 0; i < noLot.size(); i++){
    				hN = (Hashtable)noLot.get(i);
    				if (lot.equals("")){
    					lot = hN.get("noLot").toString();
    				}
    				else{
        				lot = lot + ", " + hN.get("noLot");

    				}
    				
    			}
				
				context.put("NOLOT",lot);
			}
			else{
				hN = (Hashtable)noLot.get(0);
				context.put("NOLOT",hN.get("noLot"));
			}
			
			mmkData.setPaparMMK(id_permohonan);
			paparMMK = mmkData.getPaparMMK();
			Hashtable hM = (Hashtable)paparMMK.get(0);
			
			
			context.put("NAMAPEGAWAI",hM.get("USER_NAME"));
			context.put("TARIKHSEMAKAN",hM.get("TARIKH_SEMAK"));
			context.put("KEPUTUSANSEMAKAN",hM.get("STATUS_SEMAKAN"));
			context.put("ULASANSEMAKAN",hM.get("ULASAN"));
    		
    	}
    	else if ("batalSemakanNew".equals(action)){
    		
    		vm = "app/ppt/frmHakmilikSementaraMMKSemakan.jsp";
			context.put("modeSemak","newMMK");
    		context.put("readonlySemak","");
			context.put("disabledSemak","");
    		
    		
    		Vector getrecord_permohonan = prmhnnMaster.getRecord(id_permohonan);
			Hashtable h = (Hashtable) getrecord_permohonan.get(0);
			context.put("nama_kementerian",h.get("nama_kementerian"));
			context.put("tarikh_terima",h.get("tarikh_terima"));
			context.put("projek_negeri",h.get("projek_negeri"));
			context.put("nama_daerah",h.get("nama_daerah"));
			context.put("tarikh_kehendaki",h.get("tarikh_kehendaki"));
			context.put("tujuan",h.get("tujuan"));
			context.put("no_fail",h.get("no_fail"));
			context.put("no_rujukan_surat",h.get("no_rujukan_surat"));
			context.put("no_rujukan_ptd",h.get("no_rujukan_ptd"));
			context.put("no_rujukan_ptg",h.get("no_rujukan_ptg"));
			context.put("no_rujukan_upt",h.get("no_rujukan_upt"));
			context.put("keterangan",h.get("keterangan"));
			
			
			String idAgensi = h.get("id_agensi").toString();
			if(idAgensi!="")
			{
				int id_agensi = Integer.parseInt(idAgensi);
				listAgensi = prmhnnMaster.getNamaAgensi(idAgensi);
				Hashtable ag = (Hashtable) listAgensi.get(0);
				context.put("idAgensi", ag.get("nama_agensi").toString()); 
			}
			else
			{
				context.put("idAgensi","-");
			}
    		
			mmkData.setButiranAsas(id_fail, id_permohonan);
			butiran = mmkData.getButiranAsas();
			Hashtable hB = (Hashtable)butiran.get(0);
			
			context.put("PEMOHON",hB.get("pemohon"));
			context.put("TUJUAN",hB.get("tujuan"));
			context.put("LOKASI", hB.get("lokasi"));
    		
			mmkData.setButiranLot(id_fail, id_permohonan);
			butiranLot = mmkData.getButiranLot();
			Hashtable hL = (Hashtable)butiranLot.get(0);
			
			context.put("BILLOT",hL.get("bilLot"));
			
			mmkData.setSewaLot(id_fail, id_permohonan);
			sewaLot = mmkData.getSewaLot();
			Hashtable hS = (Hashtable)sewaLot.get(0);
			
			context.put("LUASSEWA",hS.get("sewaLot"));
			
			mmkData.setNoLot(id_fail, id_permohonan);
			noLot = mmkData.getNoLot();
			Hashtable hN;
			String lot = "";
			
			if (noLot.size() > 1){
				
				for (int i = 0; i < noLot.size(); i++){
    				hN = (Hashtable)noLot.get(i);
    				if (lot.equals("")){
    					lot = hN.get("noLot").toString();
    				}
    				else{
        				lot = lot + ", " + hN.get("noLot");

    				}
    				
    			}
				
				context.put("NOLOT",lot);
			}
			else{
				hN = (Hashtable)noLot.get(0);
				context.put("NOLOT",hN.get("noLot"));
			}
			
			
			context.put("NAMAPEGAWAI","");
			context.put("TARIKHSEMAKAN","");
			context.put("KEPUTUSANSEMAKAN","");
			context.put("ULASANSEMAKAN","");
    		
    	}
    	else if ("batalSemakanUpdate".equals(action)){
    		
    		vm = "app/ppt/frmHakmilikSementaraMMKSemakan.jsp";
			context.put("modeSemak","paparMMK");
    		context.put("readonlySemak","readonly");
			context.put("disabledSemak","disabled");
    		
    		
    		Vector getrecord_permohonan = prmhnnMaster.getRecord(id_permohonan);
			Hashtable h = (Hashtable) getrecord_permohonan.get(0);
			context.put("nama_kementerian",h.get("nama_kementerian"));
			context.put("tarikh_terima",h.get("tarikh_terima"));
			context.put("projek_negeri",h.get("projek_negeri"));
			context.put("nama_daerah",h.get("nama_daerah"));
			context.put("tarikh_kehendaki",h.get("tarikh_kehendaki"));
			context.put("tujuan",h.get("tujuan"));
			context.put("no_fail",h.get("no_fail"));
			context.put("no_rujukan_surat",h.get("no_rujukan_surat"));
			context.put("no_rujukan_ptd",h.get("no_rujukan_ptd"));
			context.put("no_rujukan_ptg",h.get("no_rujukan_ptg"));
			context.put("no_rujukan_upt",h.get("no_rujukan_upt"));
			context.put("keterangan",h.get("keterangan"));
			
			
			String idAgensi = h.get("id_agensi").toString();
			if(idAgensi!="")
			{
				int id_agensi = Integer.parseInt(idAgensi);
				listAgensi = prmhnnMaster.getNamaAgensi(idAgensi);
				Hashtable ag = (Hashtable) listAgensi.get(0);
				context.put("idAgensi", ag.get("nama_agensi").toString()); 
			}
			else
			{
				context.put("idAgensi","-");
			}
    		
			mmkData.setButiranAsas(id_fail, id_permohonan);
			butiran = mmkData.getButiranAsas();
			Hashtable hB = (Hashtable)butiran.get(0);
			
			context.put("PEMOHON",hB.get("pemohon"));
			context.put("TUJUAN",hB.get("tujuan"));
			context.put("LOKASI", hB.get("lokasi"));
    		
			mmkData.setButiranLot(id_fail, id_permohonan);
			butiranLot = mmkData.getButiranLot();
			Hashtable hL = (Hashtable)butiranLot.get(0);
			
			context.put("BILLOT",hL.get("bilLot"));
			
			mmkData.setSewaLot(id_fail, id_permohonan);
			sewaLot = mmkData.getSewaLot();
			Hashtable hS = (Hashtable)sewaLot.get(0);
			
			context.put("LUASSEWA",hS.get("sewaLot"));
			
			mmkData.setNoLot(id_fail, id_permohonan);
			noLot = mmkData.getNoLot();
			Hashtable hN;
			String lot = "";
			
			if (noLot.size() > 1){
				
				for (int i = 0; i < noLot.size(); i++){
    				hN = (Hashtable)noLot.get(i);
    				if (lot.equals("")){
    					lot = hN.get("noLot").toString();
    				}
    				else{
        				lot = lot + ", " + hN.get("noLot");

    				}
    				
    			}
				
				context.put("NOLOT",lot);
			}
			else{
				hN = (Hashtable)noLot.get(0);
				context.put("NOLOT",hN.get("noLot"));
			}
			
			mmkData.setPaparMMK(id_permohonan);
			paparMMK = mmkData.getPaparMMK();
			Hashtable hM = (Hashtable)paparMMK.get(0);
			
			
			context.put("NAMAPEGAWAI",hM.get("USER_NAME"));
			context.put("TARIKHSEMAKAN",hM.get("TARIKH_SEMAK"));
			context.put("KEPUTUSANSEMAKAN",hM.get("STATUS_SEMAKAN"));
			context.put("ULASANSEMAKAN",hM.get("ULASAN"));
    	}
    	else if ("simpanKeputusan".equals(action)){
    		
    		String idMMKKptsn = addKptsn(session);
    		editStatusKptsn(session);
    		
    		vm = "app/ppt/frmHakmilikSementaraMMKKeputusan.jsp";
    		context.put("modeKptsn","paparMMK");
    		context.put("readonlyKptsn","readonly");
			context.put("disabledKptsn","disabled");
    		
    		
    		Vector getrecord_permohonan = prmhnnMaster.getRecord(id_permohonan);
			Hashtable h = (Hashtable) getrecord_permohonan.get(0);
			context.put("nama_kementerian",h.get("nama_kementerian"));
			context.put("tarikh_terima",h.get("tarikh_terima"));
			context.put("projek_negeri",h.get("projek_negeri"));
			context.put("nama_daerah",h.get("nama_daerah"));
			context.put("tarikh_kehendaki",h.get("tarikh_kehendaki"));
			context.put("tujuan",h.get("tujuan"));
			context.put("no_fail",h.get("no_fail"));
			context.put("no_rujukan_surat",h.get("no_rujukan_surat"));
			context.put("no_rujukan_ptd",h.get("no_rujukan_ptd"));
			context.put("no_rujukan_ptg",h.get("no_rujukan_ptg"));
			context.put("no_rujukan_upt",h.get("no_rujukan_upt"));
			context.put("keterangan",h.get("keterangan"));
			
			
			String idAgensi = h.get("id_agensi").toString();
			if(idAgensi!="")
			{
				int id_agensi = Integer.parseInt(idAgensi);
				listAgensi = prmhnnMaster.getNamaAgensi(idAgensi);
				Hashtable ag = (Hashtable) listAgensi.get(0);
				context.put("idAgensi", ag.get("nama_agensi").toString()); 
			}
			else
			{
				context.put("idAgensi","-");
			}
    		
			mmkData.setButiranAsas(id_fail, id_permohonan);
			butiran = mmkData.getButiranAsas();
			Hashtable hB = (Hashtable)butiran.get(0);
			
			context.put("PEMOHON",hB.get("pemohon"));
			context.put("TUJUAN",hB.get("tujuan"));
			context.put("LOKASI", hB.get("lokasi"));
    		
			mmkData.setButiranLot(id_fail, id_permohonan);
			butiranLot = mmkData.getButiranLot();
			Hashtable hL = (Hashtable)butiranLot.get(0);
			
			context.put("BILLOT",hL.get("bilLot"));
			
			mmkData.setSewaLot(id_fail, id_permohonan);
			sewaLot = mmkData.getSewaLot();
			Hashtable hS = (Hashtable)sewaLot.get(0);
			
			context.put("LUASSEWA",hS.get("sewaLot"));
			
			mmkData.setNoLot(id_fail, id_permohonan);
			noLot = mmkData.getNoLot();
			Hashtable hN;
			String lot = "";
			
			if (noLot.size() > 1){
				
				for (int i = 0; i < noLot.size(); i++){
    				hN = (Hashtable)noLot.get(i);
    				if (lot.equals("")){
    					lot = hN.get("noLot").toString();
    				}
    				else{
        				lot = lot + ", " + hN.get("noLot");

    				}
    				
    			}
				
				context.put("NOLOT",lot);
			}
			else{
				hN = (Hashtable)noLot.get(0);
				context.put("NOLOT",hN.get("noLot"));
			}
			
			context.put("NORUJUKAN",getParam("txtNoRuj"));
			context.put("TARIKHHANTAR",getParam("txdTarikhHantar"));
			context.put("TARIKHKEPUTUSAN",getParam("txdTarikhKeputusan"));
			context.put("TARIKHTERIMA",getParam("txdTarikhTerima"));
			context.put("TARIKH",getParam("txdTarikhMMK"));
			context.put("STATUSKEPUTUSAN",getParam("sorKptsn"));
			context.put("ULASANKEPUTUSAN",getParam("txtUlasan"));
			
    		
    	}
    	else if ("updateKeputusan".equals(action)){
    		
    		updateKptsn(session);

    		vm = "app/ppt/frmHakmilikSementaraMMKKeputusan.jsp";
    		context.put("modeKptsn","paparMMK");
    		context.put("readonlyKptsn","readonly");
			context.put("disabledKptsn","disabled");
    		
    		
    		Vector getrecord_permohonan = prmhnnMaster.getRecord(id_permohonan);
			Hashtable h = (Hashtable) getrecord_permohonan.get(0);
			context.put("nama_kementerian",h.get("nama_kementerian"));
			context.put("tarikh_terima",h.get("tarikh_terima"));
			context.put("projek_negeri",h.get("projek_negeri"));
			context.put("nama_daerah",h.get("nama_daerah"));
			context.put("tarikh_kehendaki",h.get("tarikh_kehendaki"));
			context.put("tujuan",h.get("tujuan"));
			context.put("no_fail",h.get("no_fail"));
			context.put("no_rujukan_surat",h.get("no_rujukan_surat"));
			context.put("no_rujukan_ptd",h.get("no_rujukan_ptd"));
			context.put("no_rujukan_ptg",h.get("no_rujukan_ptg"));
			context.put("no_rujukan_upt",h.get("no_rujukan_upt"));
			context.put("keterangan",h.get("keterangan"));
			
			
			String idAgensi = h.get("id_agensi").toString();
			if(idAgensi!="")
			{
				int id_agensi = Integer.parseInt(idAgensi);
				listAgensi = prmhnnMaster.getNamaAgensi(idAgensi);
				Hashtable ag = (Hashtable) listAgensi.get(0);
				context.put("idAgensi", ag.get("nama_agensi").toString()); 
			}
			else
			{
				context.put("idAgensi","-");
			}
    		
			mmkData.setButiranAsas(id_fail, id_permohonan);
			butiran = mmkData.getButiranAsas();
			Hashtable hB = (Hashtable)butiran.get(0);
			
			context.put("PEMOHON",hB.get("pemohon"));
			context.put("TUJUAN",hB.get("tujuan"));
			context.put("LOKASI", hB.get("lokasi"));
    		
			mmkData.setButiranLot(id_fail, id_permohonan);
			butiranLot = mmkData.getButiranLot();
			Hashtable hL = (Hashtable)butiranLot.get(0);
			
			context.put("BILLOT",hL.get("bilLot"));
			
			mmkData.setSewaLot(id_fail, id_permohonan);
			sewaLot = mmkData.getSewaLot();
			Hashtable hS = (Hashtable)sewaLot.get(0);
			
			context.put("LUASSEWA",hS.get("sewaLot"));
			
			mmkData.setNoLot(id_fail, id_permohonan);
			noLot = mmkData.getNoLot();
			Hashtable hN;
			String lot = "";
			
			if (noLot.size() > 1){
				
				for (int i = 0; i < noLot.size(); i++){
    				hN = (Hashtable)noLot.get(i);
    				if (lot.equals("")){
    					lot = hN.get("noLot").toString();
    				}
    				else{
        				lot = lot + ", " + hN.get("noLot");

    				}
    				
    			}
				
				context.put("NOLOT",lot);
			}
			else{
				hN = (Hashtable)noLot.get(0);
				context.put("NOLOT",hN.get("noLot"));
			}
			
			mmkData.setPaparMMK(id_permohonan);
			paparMMK = mmkData.getPaparMMK();
			Hashtable hM = (Hashtable)paparMMK.get(0);
			
			
			context.put("idMMKKptsn",hM.get("ID_MMKKEPUTUSAN"));
			context.put("NORUJUKAN",hM.get("NO_RUJMMK"));
			context.put("TARIKHHANTAR",hM.get("TARIKH_HANTAR"));
			context.put("TARIKHKEPUTUSAN",hM.get("TARIKH_KEPUTUSAN"));
			context.put("TARIKHTERIMA",hM.get("TARIKH_TERIMA"));
			context.put("TARIKH",hM.get("TARIKH_MMK"));
			context.put("STATUSKEPUTUSAN",hM.get("STATUS_KEPUTUSAN"));
			context.put("ULASANKEPUTUSAN",hM.get("ULASAN_KEPUTUSAN"));
			
    		
    	}
    	else if ("kemaskiniKeputusan".equals(action)){
    		

    		vm = "app/ppt/frmHakmilikSementaraMMKKeputusan.jsp";
    		context.put("modeKptsn","kemaskiniMMK");
    		context.put("readonlyKptsn","");
			context.put("disabledKptsn","");
    		
    		
    		Vector getrecord_permohonan = prmhnnMaster.getRecord(id_permohonan);
			Hashtable h = (Hashtable) getrecord_permohonan.get(0);
			context.put("nama_kementerian",h.get("nama_kementerian"));
			context.put("tarikh_terima",h.get("tarikh_terima"));
			context.put("projek_negeri",h.get("projek_negeri"));
			context.put("nama_daerah",h.get("nama_daerah"));
			context.put("tarikh_kehendaki",h.get("tarikh_kehendaki"));
			context.put("tujuan",h.get("tujuan"));
			context.put("no_fail",h.get("no_fail"));
			context.put("no_rujukan_surat",h.get("no_rujukan_surat"));
			context.put("no_rujukan_ptd",h.get("no_rujukan_ptd"));
			context.put("no_rujukan_ptg",h.get("no_rujukan_ptg"));
			context.put("no_rujukan_upt",h.get("no_rujukan_upt"));
			context.put("keterangan",h.get("keterangan"));
			
			
			String idAgensi = h.get("id_agensi").toString();
			if(idAgensi!="")
			{
				int id_agensi = Integer.parseInt(idAgensi);
				listAgensi = prmhnnMaster.getNamaAgensi(idAgensi);
				Hashtable ag = (Hashtable) listAgensi.get(0);
				context.put("idAgensi", ag.get("nama_agensi").toString()); 
			}
			else
			{
				context.put("idAgensi","-");
			}
    		
			mmkData.setButiranAsas(id_fail, id_permohonan);
			butiran = mmkData.getButiranAsas();
			Hashtable hB = (Hashtable)butiran.get(0);
			
			context.put("PEMOHON",hB.get("pemohon"));
			context.put("TUJUAN",hB.get("tujuan"));
			context.put("LOKASI", hB.get("lokasi"));
    		
			mmkData.setButiranLot(id_fail, id_permohonan);
			butiranLot = mmkData.getButiranLot();
			Hashtable hL = (Hashtable)butiranLot.get(0);
			
			context.put("BILLOT",hL.get("bilLot"));
			
			mmkData.setSewaLot(id_fail, id_permohonan);
			sewaLot = mmkData.getSewaLot();
			Hashtable hS = (Hashtable)sewaLot.get(0);
			
			context.put("LUASSEWA",hS.get("sewaLot"));
			
			mmkData.setNoLot(id_fail, id_permohonan);
			noLot = mmkData.getNoLot();
			Hashtable hN;
			String lot = "";
			
			if (noLot.size() > 1){
				
				for (int i = 0; i < noLot.size(); i++){
    				hN = (Hashtable)noLot.get(i);
    				if (lot.equals("")){
    					lot = hN.get("noLot").toString();
    				}
    				else{
        				lot = lot + ", " + hN.get("noLot");

    				}
    				
    			}
				
				context.put("NOLOT",lot);
			}
			else{
				hN = (Hashtable)noLot.get(0);
				context.put("NOLOT",hN.get("noLot"));
			}
			
			mmkData.setPaparMMK(id_permohonan);
			paparMMK = mmkData.getPaparMMK();
			Hashtable hM = (Hashtable)paparMMK.get(0);
			
			
			context.put("idMMKKptsn",hM.get("ID_MMKKEPUTUSAN"));
			context.put("NORUJUKAN",hM.get("NO_RUJMMK"));
			context.put("TARIKHHANTAR",hM.get("TARIKH_HANTAR"));
			context.put("TARIKHKEPUTUSAN",hM.get("TARIKH_KEPUTUSAN"));
			context.put("TARIKHTERIMA",hM.get("TARIKH_TERIMA"));
			context.put("TARIKH",hM.get("TARIKH_MMK"));
			context.put("STATUSKEPUTUSAN",hM.get("STATUS_KEPUTUSAN"));
			context.put("ULASANKEPUTUSAN",hM.get("ULASAN_KEPUTUSAN"));
    		
    	}
    	else if ("batalKeputusanNew".equals(action)){
    		
    		vm = "app/ppt/frmHakmilikSementaraMMKKeputusan.jsp";
    		context.put("modeKptsn","newMMK");
    		context.put("readonlyKptsn","");
			context.put("disabledKptsn","");
    		
    		
    		Vector getrecord_permohonan = prmhnnMaster.getRecord(id_permohonan);
			Hashtable h = (Hashtable) getrecord_permohonan.get(0);
			context.put("nama_kementerian",h.get("nama_kementerian"));
			context.put("tarikh_terima",h.get("tarikh_terima"));
			context.put("projek_negeri",h.get("projek_negeri"));
			context.put("nama_daerah",h.get("nama_daerah"));
			context.put("tarikh_kehendaki",h.get("tarikh_kehendaki"));
			context.put("tujuan",h.get("tujuan"));
			context.put("no_fail",h.get("no_fail"));
			context.put("no_rujukan_surat",h.get("no_rujukan_surat"));
			context.put("no_rujukan_ptd",h.get("no_rujukan_ptd"));
			context.put("no_rujukan_ptg",h.get("no_rujukan_ptg"));
			context.put("no_rujukan_upt",h.get("no_rujukan_upt"));
			context.put("keterangan",h.get("keterangan"));
			
			
			String idAgensi = h.get("id_agensi").toString();
			if(idAgensi!="")
			{
				int id_agensi = Integer.parseInt(idAgensi);
				listAgensi = prmhnnMaster.getNamaAgensi(idAgensi);
				Hashtable ag = (Hashtable) listAgensi.get(0);
				context.put("idAgensi", ag.get("nama_agensi").toString()); 
			}
			else
			{
				context.put("idAgensi","-");
			}
    		
			mmkData.setButiranAsas(id_fail, id_permohonan);
			butiran = mmkData.getButiranAsas();
			Hashtable hB = (Hashtable)butiran.get(0);
			
			context.put("PEMOHON",hB.get("pemohon"));
			context.put("TUJUAN",hB.get("tujuan"));
			context.put("LOKASI", hB.get("lokasi"));
    		
			mmkData.setButiranLot(id_fail, id_permohonan);
			butiranLot = mmkData.getButiranLot();
			Hashtable hL = (Hashtable)butiranLot.get(0);
			
			context.put("BILLOT",hL.get("bilLot"));
			
			mmkData.setSewaLot(id_fail, id_permohonan);
			sewaLot = mmkData.getSewaLot();
			Hashtable hS = (Hashtable)sewaLot.get(0);
			
			context.put("LUASSEWA",hS.get("sewaLot"));
			
			mmkData.setNoLot(id_fail, id_permohonan);
			noLot = mmkData.getNoLot();
			Hashtable hN;
			String lot = "";
			
			if (noLot.size() > 1){
				
				for (int i = 0; i < noLot.size(); i++){
    				hN = (Hashtable)noLot.get(i);
    				if (lot.equals("")){
    					lot = hN.get("noLot").toString();
    				}
    				else{
        				lot = lot + ", " + hN.get("noLot");

    				}
    				
    			}
				
				context.put("NOLOT",lot);
			}
			else{
				hN = (Hashtable)noLot.get(0);
				context.put("NOLOT",hN.get("noLot"));
			}
			
			mmkData.setPaparMMK(id_permohonan);
			paparMMK = mmkData.getPaparMMK();
			Hashtable hM = (Hashtable)paparMMK.get(0);
			
			
			context.put("idMMKKptsn","");
			context.put("NORUJUKAN","");
			context.put("TARIKHHANTAR","");
			context.put("TARIKHKEPUTUSAN","");
			context.put("TARIKHTERIMA","");
			context.put("TARIKH","");
			context.put("STATUSKEPUTUSAN","");
			context.put("ULASANKEPUTUSAN","");
    	}
    	else if ("batalKeputusanUpdate".equals(action)){
    		
    		vm = "app/ppt/frmHakmilikSementaraMMKKeputusan.jsp";
    		context.put("modeKptsn","paparMMK");
    		context.put("readonlyKptsn","readonly");
			context.put("disabledKptsn","disabled");
    		
    		
    		Vector getrecord_permohonan = prmhnnMaster.getRecord(id_permohonan);
			Hashtable h = (Hashtable) getrecord_permohonan.get(0);
			context.put("nama_kementerian",h.get("nama_kementerian"));
			context.put("tarikh_terima",h.get("tarikh_terima"));
			context.put("projek_negeri",h.get("projek_negeri"));
			context.put("nama_daerah",h.get("nama_daerah"));
			context.put("tarikh_kehendaki",h.get("tarikh_kehendaki"));
			context.put("tujuan",h.get("tujuan"));
			context.put("no_fail",h.get("no_fail"));
			context.put("no_rujukan_surat",h.get("no_rujukan_surat"));
			context.put("no_rujukan_ptd",h.get("no_rujukan_ptd"));
			context.put("no_rujukan_ptg",h.get("no_rujukan_ptg"));
			context.put("no_rujukan_upt",h.get("no_rujukan_upt"));
			context.put("keterangan",h.get("keterangan"));
			
			
			String idAgensi = h.get("id_agensi").toString();
			if(idAgensi!="")
			{
				int id_agensi = Integer.parseInt(idAgensi);
				listAgensi = prmhnnMaster.getNamaAgensi(idAgensi);
				Hashtable ag = (Hashtable) listAgensi.get(0);
				context.put("idAgensi", ag.get("nama_agensi").toString()); 
			}
			else
			{
				context.put("idAgensi","-");
			}
    		
			mmkData.setButiranAsas(id_fail, id_permohonan);
			butiran = mmkData.getButiranAsas();
			Hashtable hB = (Hashtable)butiran.get(0);
			
			context.put("PEMOHON",hB.get("pemohon"));
			context.put("TUJUAN",hB.get("tujuan"));
			context.put("LOKASI", hB.get("lokasi"));
    		
			mmkData.setButiranLot(id_fail, id_permohonan);
			butiranLot = mmkData.getButiranLot();
			Hashtable hL = (Hashtable)butiranLot.get(0);
			
			context.put("BILLOT",hL.get("bilLot"));
			
			mmkData.setSewaLot(id_fail, id_permohonan);
			sewaLot = mmkData.getSewaLot();
			Hashtable hS = (Hashtable)sewaLot.get(0);
			
			context.put("LUASSEWA",hS.get("sewaLot"));
			
			mmkData.setNoLot(id_fail, id_permohonan);
			noLot = mmkData.getNoLot();
			Hashtable hN;
			String lot = "";
			
			if (noLot.size() > 1){
				
				for (int i = 0; i < noLot.size(); i++){
    				hN = (Hashtable)noLot.get(i);
    				if (lot.equals("")){
    					lot = hN.get("noLot").toString();
    				}
    				else{
        				lot = lot + ", " + hN.get("noLot");

    				}
    				
    			}
				
				context.put("NOLOT",lot);
			}
			else{
				hN = (Hashtable)noLot.get(0);
				context.put("NOLOT",hN.get("noLot"));
			}
			
			mmkData.setPaparMMK(id_permohonan);
			paparMMK = mmkData.getPaparMMK();
			Hashtable hM = (Hashtable)paparMMK.get(0);
			
			
			context.put("idMMKKptsn",hM.get("ID_MMKKEPUTUSAN"));
			context.put("NORUJUKAN",hM.get("NO_RUJMMK"));
			context.put("TARIKHHANTAR",hM.get("TARIKH_HANTAR"));
			context.put("TARIKHKEPUTUSAN",hM.get("TARIKH_KEPUTUSAN"));
			context.put("TARIKHTERIMA",hM.get("TARIKH_TERIMA"));
			context.put("TARIKH",hM.get("TARIKH_MMK"));
			context.put("STATUSKEPUTUSAN",hM.get("STATUS_KEPUTUSAN"));
			context.put("ULASANKEPUTUSAN",hM.get("ULASAN_KEPUTUSAN"));
    	}
    	else{
    		vm = "app/ppt/frmHakmilikSementaraSenaraiMMK.jsp";
    		
    		if (!"".equals(getParam("txdTarikh")));
    		tarikhmohon = getParam("txdTarikh");
    	
    		if (!"".equals(getParam("txtNoFail")));
    			nofail = getParam("txtNoFail");
    		
    		if(!"".equals(getParam("socStatus")))
    			cStatus = getParam("socStatus");
    		
    		listMMK.setCarianFail(nofail, tarikhmohon, cStatus);		
    		list = listMMK.getList();
    							
    	
    	    context.put("PermohonanList", list);
    	    context.put("list_size", list.size());
    	    context.put("CarianFail", nofail);  
    	    context.put("tarikhPermohonan", tarikhmohon);
    	    context.put("SelectStatus", HTML.SelectStatusPPT("socStatus",Utils.parseLong(cStatus),"style=width:130px"));
    	    setupPage(session,action,list);
    		
    	}
		
		
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
		this.context.put("PermohonanList",paging.getPage(page));
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
	
	private String addPenyediaan(HttpSession session) throws Exception{
		
		Hashtable h = new Hashtable();
	    
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String idPermohonan = getParam("id_permohonan");
		h.put("idPermohonan",idPermohonan);
		
		h.put("tujuan",getParam("txtTujuan"));
		h.put("latarBelakang",getParam("txtLatarBelakang"));
		h.put("asasPertimbangan",getParam("txtAsasPertimbangan"));
		h.put("kesimpulan",getParam("txtKesimpulan"));
		h.put("syor",getParam("txtSyor"));
		h.put("kedudukanLaporanTanah",getParam("txtKedudukanLaporanTanah"));
		h.put("pengesahanPeruntukan",getParam("txtPengesahanPeruntukan"));
		h.put("pandangan",getParam("txtPandangan"));
		h.put("implikasi",getParam("txtImplikasi"));
		h.put("perihalTanah",getParam("txtPerihalTanah"));
		h.put("perihalPohon",getParam("txtPerihalPohon"));
		h.put("anggaranKos",getParam("txtAnggaranKos"));
		h.put("perakuanPentadbir",getParam("txtPerakuanPentadbir"));
		h.put("nilaianTanah",getParam("txtNilaianTanah"));
		h.put("pengecualianUpahUkur",getParam("txtPengecualianUpahUkur"));
		h.put("halLain",getParam("txtHalLain"));
		h.put("jawatankuasaTanah",getParam("txtJawatankuasaTanah"));
		h.put("namaTuanTanah",getParam("txtNamaTuanTanah"));
		h.put("tajuk",getParam("txtTajuk"));
		h.put("ulasan",getParam("txtUlasan"));
		h.put("ulasanJT",getParam("txtUlasanJT"));
		h.put("ulasanPengarah",getParam("txtUlasanPengarah"));
		h.put("keputusan",getParam("txtKeputusan"));
		h.put("butirAsas",getParam("txtButirAsas"));
		h.put("keadaanTanah",getParam("txtKeadaanTanah"));
		h.put("butirTanah",getParam("txtButirTanah"));
		h.put("kemudahanAsas",getParam("txtKemudahanAsas"));
		h.put("perakuanSetiausaha",getParam("txtPerakuanSetiausaha"));
		h.put("penutup",getParam("txtPenutup"));
		h.put("id_Masuk",user_id);

		
		return FrmHakmilikSementaraMMKPenyediaanData.add(h);
	}
	
	private void updatePenyediaan(HttpSession session) throws Exception{
		
		Hashtable h = new Hashtable();
	    
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String idMMK = getParam("idMMK");
		h.put("idMMK",idMMK);
		h.put("tujuan",getParam("txtTujuan"));
		h.put("latarBelakang",getParam("txtLatarBelakang"));
		h.put("asasPertimbangan",getParam("txtAsasPertimbangan"));
		h.put("kesimpulan",getParam("txtKesimpulan"));
		h.put("syor",getParam("txtSyor"));
		h.put("kedudukanLaporanTanah",getParam("txtKedudukanLaporanTanah"));
		h.put("pengesahanPeruntukan",getParam("txtPengesahanPeruntukan"));
		h.put("pandangan",getParam("txtPandangan"));
		h.put("implikasi",getParam("txtImplikasi"));
		h.put("perihalTanah",getParam("txtPerihalTanah"));
		h.put("perihalPohon",getParam("txtPerihalPohon"));
		h.put("anggaranKos",getParam("txtAnggaranKos"));
		h.put("perakuanPentadbir",getParam("txtPerakuanPentadbir"));
		h.put("nilaianTanah",getParam("txtNilaianTanah"));
		h.put("pengecualianUpahUkur",getParam("txtPengecualianUpahUkur"));
		h.put("halLain",getParam("txtHalLain"));
		h.put("jawatankuasaTanah",getParam("txtJawatankuasaTanah"));
		h.put("namaTuanTanah",getParam("txtNamaTuanTanah"));
		h.put("tajuk",getParam("txtTajuk"));
		h.put("ulasan",getParam("txtUlasan"));
		h.put("ulasanJT",getParam("txtUlasanJT"));
		h.put("ulasanPengarah",getParam("txtUlasanPengarah"));
		h.put("keputusan",getParam("txtKeputusan"));
		h.put("butirAsas",getParam("txtButirAsas"));
		h.put("keadaanTanah",getParam("txtKeadaanTanah"));
		h.put("butirTanah",getParam("txtButirTanah"));
		h.put("kemudahanAsas",getParam("txtKemudahanAsas"));
		h.put("perakuanSetiausaha",getParam("txtPerakuanSetiausaha"));
		h.put("penutup",getParam("txtPenutup"));
		h.put("id_Kemaskini",user_id);

		
		FrmHakmilikSementaraMMKPenyediaanData.update(h);
	}
	private void editStatusPenyediaan(HttpSession session) throws Exception{
		
		
		String idPermohonan = getParam("id_permohonan");
		Hashtable h = null;
		h = new Hashtable();

		h.put("id_permohonan", idPermohonan);

		FrmHakmilikSementaraMMKPenyediaanData.editStatusPenyediaan(h);
	}
	private void addSemakan(HttpSession session) throws Exception{
		
		Hashtable h = new Hashtable();
	    
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String idMMK = getParam("idMMK");
		h.put("idMMK",idMMK);
		h.put("user_Id",user_id);
		h.put("tarikh_Semak",getParam("txdTarikhSemakan"));
		h.put("status_Semakan",getParam("socKeputusan"));
		h.put("ulasan",getParam("txtUlasan"));
		h.put("id_Kemaskini",user_id);

		
		FrmHakmilikSementaraMMKPenyediaanData.addSemak(h);
	}
	
	private void updateSemakan(HttpSession session) throws Exception{
		
		Hashtable h = new Hashtable();
	    
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String idMMK = getParam("idMMK");
		h.put("idMMK",idMMK);
		h.put("user_Id",user_id);
		h.put("tarikh_Semak",getParam("txdTarikhSemakan"));
		h.put("status_Semakan",getParam("socKeputusan"));
		h.put("ulasan",getParam("txtUlasan"));
		h.put("id_Kemaskini",user_id);

		
		FrmHakmilikSementaraMMKPenyediaanData.updateSemak(h);
	}
	private void editStatusSemakan(HttpSession session) throws Exception{
		
		
		String idPermohonan = getParam("id_permohonan");
		Hashtable h = null;
		h = new Hashtable();

		h.put("id_permohonan", idPermohonan);

		FrmHakmilikSementaraMMKPenyediaanData.editStatusSemak(h);
	}
	
	private String addKptsn(HttpSession session) throws Exception{
		
		Hashtable h = new Hashtable();
	    
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String idMMK = getParam("idMMK");
		h.put("idMMK",idMMK);
		h.put("no_rujukan",getParam("txtNoRuj"));
		h.put("tarikh_hantar",getParam("txdTarikhHantar"));
		h.put("tarikh_terima",getParam("txdTarikhTerima"));
		h.put("tarikh_keputusan",getParam("txdTarikhKeputusan"));
		h.put("tarikh_Mmk",getParam("txdTarikhMMK"));
		h.put("status_keputusan",getParam("sorKptsn"));
		h.put("ulasan",getParam("txtUlasan"));
		h.put("id_Masuk",user_id);

		
		return FrmHakmilikSementaraMMKPenyediaanData.addKeputusan(h);
	}
	
	private void updateKptsn(HttpSession session) throws Exception{
		
		Hashtable h = new Hashtable();
	    
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String idMMKKptsn = getParam("idMMKKptsn");
		String idMMK = getParam("idMMK");
		h.put("idMMK",idMMK);
		h.put("idMMKKptsn",idMMKKptsn);
		h.put("no_rujukan",getParam("txtNoRuj"));
		h.put("tarikh_hantar",getParam("txdTarikhHantar"));
		h.put("tarikh_terima",getParam("txdTarikhTerima"));
		h.put("tarikh_keputusan",getParam("txdTarikhKeputusan"));
		h.put("tarikh_Mmk",getParam("txdTarikhMMK"));
		h.put("status_keputusan",getParam("sorKptsn"));
		h.put("ulasan",getParam("txtUlasan"));
		h.put("id_Kemaskini",user_id);

		
		FrmHakmilikSementaraMMKPenyediaanData.updateKeputusan(h);
	}
	private void editStatusKptsn(HttpSession session) throws Exception{
		
		
		String idPermohonan = getParam("id_permohonan");
		Hashtable h = null;
		h = new Hashtable();

		h.put("id_permohonan", idPermohonan);

		FrmHakmilikSementaraMMKPenyediaanData.editStatusKeputusan(h);
	}
	
	

}
