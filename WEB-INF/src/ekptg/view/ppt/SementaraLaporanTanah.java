package ekptg.view.ppt;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.ppt.FrmHakmilikSementaraLaporanTanahData;
import ekptg.model.ppt.FrmHakmilikSementaraMaklumatPermohonanData;
import ekptg.model.ppt.FrmHakmilikSementaraSenaraiLaporanTanahData;

public class SementaraLaporanTanah extends AjaxBasedModule{
	static Logger myLogger = Logger.getLogger(SementaraLaporanTanah.class);
	
	public String doTemplate2() throws Exception

    { 
		String vm = "";
		String submit = getParam("command");
        String action = getParam("action");
        String mode = getParam("mode");
        
        myLogger.info("submit :: "+submit);
        myLogger.info("action :: "+action);
        myLogger.info("mode :: "+mode);
        
        String tarikhmohon = "";
    	String nofail = "";    
    	String noJKPTG = "";
    	String cStatus = "0";
    	String id_fail = getParam("id_fail");
		context.put("idFail", id_fail);
		String id_permohonan = getParam("id_permohonan");
		context.put("idPermohonan",id_permohonan);
		String idHakmilik = "";
		context.put("idHakmilik",idHakmilik);
    	HttpSession session = this.request.getSession();
    	context.put("mode",mode);
    	
		FrmHakmilikSementaraSenaraiLaporanTanahData listSenarai = new FrmHakmilikSementaraSenaraiLaporanTanahData();
		FrmHakmilikSementaraMaklumatPermohonanData prmhnnMaster = new FrmHakmilikSementaraMaklumatPermohonanData();
		FrmHakmilikSementaraLaporanTanahData laporanTanah = new FrmHakmilikSementaraLaporanTanahData();
    	
    	Vector list = null;
    	Vector listAgensi = null;
    	Vector maklumatTanah = null;
    	Vector maklumatLaporan = null;
    	Vector checkIdPermohonan = null;
		
    	
    	if("newLaporan".equals(action)){
    		
			vm = "app/ppt/frmHakmilikSementaraLaporanTanah.jsp";

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
			
			laporanTanah.check_idPermohonan(id_permohonan);
			checkIdPermohonan = laporanTanah.getIdPermohonan();
			context.put("existLaporan",checkIdPermohonan.size());
			
			if(checkIdPermohonan.size() == 0){
				
				context.put("mode","newLaporan");
				context.put("readonly","");
				context.put("disabled","");
				
				idHakmilik = getParam("socHakmilik");

				int idPermohonan = Integer.parseInt(id_permohonan);
				
				context.put("SelectNoHakmilik",HTML.SelectHakmilikSementara(id_permohonan, "socHakmilik",null,"style=width:135px"," onChange=\"doChangeHakmilik();\""));
				
				context.put("NO_LOT","");
				context.put("LUAS_LOT","");
				context.put("UNIT_LUAS_LOT","");
				context.put("NAMA_MUKIM","");
				context.put("LUAS_SEWA","");
				context.put("UNIT_LUAS_SEWA","");
				context.put("KATEGORI_TANAH","");
				context.put("NO_PELAN","");
				context.put("NO_SYIT","");
				context.put("NAMA_PEGAWAI",session.getAttribute("_portal_username"));
				context.put("TARIKH_LAPOR", "");
				
				context.put("TARIKH_MULA_CHART","");
				context.put("TARIKH_AKHIR_CHART","");
				context.put("TARIKH_LAWATAN","");
				context.put("PERIHAL_SYIT","");
				context.put("FLAG_JENIS_TANAH","");
				context.put("LOKASI_TANAH","");
				context.put("FLAG_LUAR_SIMPANAN","");
				context.put("FLAG_DLM_SIMPANAN","");
				context.put("FLAG_LUAR_MAJLIS","");
				context.put("FLAG_DLM_MAJLIS","");
				context.put("RUPABUMI_SELURUH_LOT","");
				context.put("RUPABUMI_KWSN_TERLIBAT","");
				context.put("LOT_SELURUH_LOT","");
				context.put("LOT_JENIS_TANAMAN","");
				context.put("LOT_KEADAAN_TANAMAN","");
				context.put("LOT_KWSN_TERLIBAT","");
				
			}
			else{
				
				context.put("mode","paparLaporan");
				context.put("readonly","readonly");
				context.put("disabled","disabled");
				
				laporanTanah.setMaklumatLaporan(id_permohonan);
				maklumatLaporan = laporanTanah.getMaklumatLaporan();
				Hashtable hM = (Hashtable)maklumatLaporan.get(0);

				context.put("idHakmilik",hM.get("ID_HAKMILIK").toString());
				context.put("idTanahUmum",hM.get("ID_TANAHUMUM").toString());

				context.put("SelectNoHakmilik",HTML.SelectHakmilikSementara(id_permohonan, "socHakmilik",Utils.parseLong(hM.get("ID_HAKMILIK").toString()),"style=width:135px class=disabled disabled"," onChange=\"doChangeHakmilik();\""));
				
				context.put("NO_LOT",hM.get("NO_LOT"));
				context.put("LUAS_LOT",hM.get("LUAS_LOT"));
				context.put("UNIT_LUAS_LOT",hM.get("UNIT_LUAS_LOT"));
				context.put("NAMA_MUKIM",hM.get("NAMA_MUKIM"));
				context.put("LUAS_SEWA",hM.get("LUAS_SEWA"));
				context.put("UNIT_LUAS_SEWA",hM.get("UNIT_LUAS_SEWA"));
				context.put("KATEGORI_TANAH",hM.get("KATEGORI_TANAH"));
				context.put("NO_PELAN",hM.get("NO_PELAN"));
				context.put("NO_SYIT",hM.get("NO_SYIT"));
				context.put("NAMA_PEGAWAI",hM.get("USER_NAME"));
				context.put("TARIKH_LAPOR", hM.get("TARIKH_LAPOR"));
				
				context.put("TARIKH_MULA_CHART",hM.get("TARIKH_MULA_CHART"));
				context.put("TARIKH_AKHIR_CHART",hM.get("TARIKH_AKHIR_CHART"));
				context.put("TARIKH_LAWATAN",hM.get("TARIKH_LAWATAN"));
				context.put("PERIHAL_SYIT",hM.get("PERIHAL_SYIT"));
				context.put("FLAG_JENIS_TANAH",hM.get("FLAG_JENIS_TANAH"));
				context.put("LOKASI_TANAH",hM.get("LOKASI_TANAH"));
				context.put("FLAG_LUAR_SIMPANAN",hM.get("FLAG_LUAR_SIMPANAN"));
				context.put("FLAG_DLM_SIMPANAN",hM.get("FLAG_DLM_SIMPANAN"));
				context.put("FLAG_LUAR_MAJLIS",hM.get("FLAG_LUAR_MAJLIS"));
				context.put("FLAG_DLM_MAJLIS",hM.get("FLAG_DLM_MAJLIS"));
				context.put("RUPABUMI_SELURUH_LOT",hM.get("RUPABUMI_SELURUH_LOT"));
				context.put("RUPABUMI_KWSN_TERLIBAT",hM.get("RUPABUMI_KWSN_TERLIBAT"));
				context.put("LOT_SELURUH_LOT",hM.get("LOT_SELURUH_LOT"));
				context.put("LOT_JENIS_TANAMAN",hM.get("LOT_JENIS_TANAMAN"));
				context.put("LOT_KEADAAN_TANAMAN",hM.get("LOT_KEADAAN_TANAMAN"));
				context.put("LOT_KWSN_TERLIBAT",hM.get("LOT_KWSN_TERLIBAT"));
				
				
			}
			
    	}
    	else if ("addLaporanTanah".equals(action)){
    		
    		String idTanahUmum = addLaporanTanah(session);
    		edit_status_laporanTanah147(session);
    		
    		context.put("mode","paparLaporan");
			context.put("readonly","readonly");
			context.put("disabled","disabled");
    		
    		vm = "app/ppt/frmHakmilikSementaraLaporanTanah.jsp";
    		
    		

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
			
			idHakmilik = getParam("socHakmilik");
			
			context.put("SelectNoHakmilik",HTML.SelectHakmilikSementara(id_permohonan, "socHakmilik",Utils.parseLong(idHakmilik),null, "style=width:135px class=disabled disabled onChange=\"doChangeHakmilik();\""));
    		
    		laporanTanah.setMaklumatHakmilik(idHakmilik);
			maklumatTanah = laporanTanah.getList();
			Hashtable h2 = (Hashtable)maklumatTanah.get(0);
			
			context.put("NO_LOT",h2.get("NO_LOT"));
			context.put("LUAS_LOT",h2.get("LUAS_LOT"));
			context.put("UNIT_LUAS_LOT",h2.get("UNIT_LUAS_LOT"));
			context.put("NAMA_MUKIM",h2.get("NAMA_MUKIM"));
			context.put("LUAS_SEWA",h2.get("LUAS_SEWA"));
			context.put("UNIT_LUAS_SEWA",h2.get("UNIT_LUAS_SEWA"));
			context.put("KATEGORI_TANAH",h2.get("KATEGORI_TANAH"));
			context.put("NO_PELAN",h2.get("NO_PELAN"));
			context.put("NO_SYIT",h2.get("NO_SYIT"));
			context.put("NAMA_PEGAWAI",session.getAttribute("_portal_username"));
			context.put("idHakmilik",h2.get("ID_HAKMILIK"));
			context.put("TARIKH_LAPOR", getParam("txdTarikhLaporan"));
			
			context.put("TARIKH_MULA_CHART",getParam("txdTkhMulaChart"));
			context.put("TARIKH_AKHIR_CHART",getParam("txdTkhAkhirChart"));
			context.put("TARIKH_LAWATAN",getParam("txdTkhLawatTapak"));
			context.put("PERIHAL_SYIT",getParam("txtBilSyitPiawai"));
			context.put("FLAG_JENIS_TANAH",getParam("sorJenisTanah"));
			context.put("LOKASI_TANAH",getParam("txtLokasiTnh"));
			context.put("FLAG_LUAR_SIMPANAN",getParam("sbcLuarKwsnSmpnMelayu"));
			context.put("FLAG_DLM_SIMPANAN",getParam("sbcDlmKwsnSmpnMelayu"));
			context.put("FLAG_LUAR_MAJLIS",getParam("sbcLuarKwsnMajlisDaerah"));
			context.put("FLAG_DLM_MAJLIS",getParam("sbcDlmKwsnMajlisDaerah"));
			context.put("RUPABUMI_SELURUH_LOT",getParam("txtRupabumiSeluruhLot"));
			context.put("RUPABUMI_KWSN_TERLIBAT",getParam("txtRupabumiKwsTerlibat"));
			context.put("LOT_SELURUH_LOT",getParam("txtKeadaanLotSeluruh"));
			context.put("LOT_JENIS_TANAMAN",getParam("txtKeadaanLotJnsTanaman"));
			context.put("LOT_KEADAAN_TANAMAN",getParam("txtKeadaanLotTanaman"));
			context.put("LOT_KWSN_TERLIBAT",getParam("txtKeadaanLotKwsTerlibat"));
			context.put("idTanahUmum",idTanahUmum);

    	}
    	else if ("kemaskiniLaporanTanah".equals(action)){
    		
    		vm = "app/ppt/frmHakmilikSementaraLaporanTanah.jsp";
    		
    		context.put("mode","kemaskiniLaporan");
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
			
			laporanTanah.setMaklumatLaporan(id_permohonan);
			maklumatLaporan = laporanTanah.getMaklumatLaporan();
			Hashtable hM = (Hashtable)maklumatLaporan.get(0);
					
			context.put("idHakmilik",hM.get("ID_HAKMILIK").toString());
			context.put("idTanahUmum",hM.get("ID_TANAHUMUM").toString());
			context.put("SelectNoHakmilik",HTML.SelectHakmilikSementara(id_permohonan, "socHakmilik",Utils.parseLong(hM.get("ID_HAKMILIK").toString()),"style=width:135px class=disabled disabled"," onChange=\"doChangeHakmilik();\""));
			
			context.put("NO_LOT",hM.get("NO_LOT"));
			context.put("LUAS_LOT",hM.get("LUAS_LOT"));
			context.put("UNIT_LUAS_LOT",hM.get("UNIT_LUAS_LOT"));
			context.put("NAMA_MUKIM",hM.get("NAMA_MUKIM"));
			context.put("LUAS_SEWA",hM.get("LUAS_SEWA"));
			context.put("UNIT_LUAS_SEWA",hM.get("UNIT_LUAS_SEWA"));
			context.put("KATEGORI_TANAH",hM.get("KATEGORI_TANAH"));
			context.put("NO_PELAN",hM.get("NO_PELAN"));
			context.put("NO_SYIT",hM.get("NO_SYIT"));
			context.put("NAMA_PEGAWAI",hM.get("USER_NAME"));
			context.put("TARIKH_LAPOR", hM.get("TARIKH_LAPOR"));
			
			context.put("TARIKH_MULA_CHART",hM.get("TARIKH_MULA_CHART"));
			context.put("TARIKH_AKHIR_CHART",hM.get("TARIKH_AKHIR_CHART"));
			context.put("TARIKH_LAWATAN",hM.get("TARIKH_LAWATAN"));
			context.put("PERIHAL_SYIT",hM.get("PERIHAL_SYIT"));
			context.put("FLAG_JENIS_TANAH",hM.get("FLAG_JENIS_TANAH"));
			context.put("LOKASI_TANAH",hM.get("LOKASI_TANAH"));
			context.put("FLAG_LUAR_SIMPANAN",hM.get("FLAG_LUAR_SIMPANAN"));
			context.put("FLAG_DLM_SIMPANAN",hM.get("FLAG_DLM_SIMPANAN"));
			context.put("FLAG_LUAR_MAJLIS",hM.get("FLAG_LUAR_MAJLIS"));
			context.put("FLAG_DLM_MAJLIS",hM.get("FLAG_DLM_MAJLIS"));
			context.put("RUPABUMI_SELURUH_LOT",hM.get("RUPABUMI_SELURUH_LOT"));
			context.put("RUPABUMI_KWSN_TERLIBAT",hM.get("RUPABUMI_KWSN_TERLIBAT"));
			context.put("LOT_SELURUH_LOT",hM.get("LOT_SELURUH_LOT"));
			context.put("LOT_JENIS_TANAMAN",hM.get("LOT_JENIS_TANAMAN"));
			context.put("LOT_KEADAAN_TANAMAN",hM.get("LOT_KEADAAN_TANAMAN"));
			context.put("LOT_KWSN_TERLIBAT",hM.get("LOT_KWSN_TERLIBAT"));
    		
    	}
    	else if ("updateLaporanTanah".equals(action)){
    		
    		updateLaporanTanah(session);
    		
    		vm = "app/ppt/frmHakmilikSementaraLaporanTanah.jsp";
    		
    		context.put("mode","newLaporan");
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
			
			laporanTanah.setMaklumatLaporan(id_permohonan);
			maklumatLaporan = laporanTanah.getMaklumatLaporan();
			Hashtable hM = (Hashtable)maklumatLaporan.get(0);
					
			context.put("idHakmilik",hM.get("ID_HAKMILIK").toString());
			context.put("idTanahUmum",hM.get("ID_TANAHUMUM").toString());
			context.put("SelectNoHakmilik",HTML.SelectHakmilikSementara(id_permohonan, "socHakmilik",Utils.parseLong(hM.get("ID_HAKMILIK").toString()),"style=width:135px class=disabled disabled"," onChange=\"doChangeHakmilik();\""));
			
			context.put("NO_LOT",hM.get("NO_LOT"));
			context.put("LUAS_LOT",hM.get("LUAS_LOT"));
			context.put("UNIT_LUAS_LOT",hM.get("UNIT_LUAS_LOT"));
			context.put("NAMA_MUKIM",hM.get("NAMA_MUKIM"));
			context.put("LUAS_SEWA",hM.get("LUAS_SEWA"));
			context.put("UNIT_LUAS_SEWA",hM.get("UNIT_LUAS_SEWA"));
			context.put("KATEGORI_TANAH",hM.get("KATEGORI_TANAH"));
			context.put("NO_PELAN",hM.get("NO_PELAN"));
			context.put("NO_SYIT",hM.get("NO_SYIT"));
			context.put("NAMA_PEGAWAI",hM.get("USER_NAME"));
			context.put("TARIKH_LAPOR", hM.get("TARIKH_LAPOR"));
			
			context.put("TARIKH_MULA_CHART",hM.get("TARIKH_MULA_CHART"));
			context.put("TARIKH_AKHIR_CHART",hM.get("TARIKH_AKHIR_CHART"));
			context.put("TARIKH_LAWATAN",hM.get("TARIKH_LAWATAN"));
			context.put("PERIHAL_SYIT",hM.get("PERIHAL_SYIT"));
			context.put("FLAG_JENIS_TANAH",hM.get("FLAG_JENIS_TANAH"));
			context.put("LOKASI_TANAH",hM.get("LOKASI_TANAH"));
			context.put("FLAG_LUAR_SIMPANAN",hM.get("FLAG_LUAR_SIMPANAN"));
			context.put("FLAG_DLM_SIMPANAN",hM.get("FLAG_DLM_SIMPANAN"));
			context.put("FLAG_LUAR_MAJLIS",hM.get("FLAG_LUAR_MAJLIS"));
			context.put("FLAG_DLM_MAJLIS",hM.get("FLAG_DLM_MAJLIS"));
			context.put("RUPABUMI_SELURUH_LOT",hM.get("RUPABUMI_SELURUH_LOT"));
			context.put("RUPABUMI_KWSN_TERLIBAT",hM.get("RUPABUMI_KWSN_TERLIBAT"));
			context.put("LOT_SELURUH_LOT",hM.get("LOT_SELURUH_LOT"));
			context.put("LOT_JENIS_TANAMAN",hM.get("LOT_JENIS_TANAMAN"));
			context.put("LOT_KEADAAN_TANAMAN",hM.get("LOT_KEADAAN_TANAMAN"));
			context.put("LOT_KWSN_TERLIBAT",hM.get("LOT_KWSN_TERLIBAT"));
			

    	}
    	else if("hapusLaporanTanah".equals(action)){
    		
    		deleteLaporanTanah(session);
    		edit_status_laporanTanah149(session);
    		
    		vm = "app/ppt/frmHakmilikSementaraSenaraiLaporanTanah.jsp";
			
			if (!"".equals(getParam("txdTarikh")));
			tarikhmohon = getParam("txdTarikh");
		
			if (!"".equals(getParam("txtNoFail")));
				nofail = getParam("txtNoFail");
				
			if (!"".equals(getParam("txtNoRujJKPTGNegeri")));
				noJKPTG = getParam("txtNoRujJKPTGNegeri");
			
			if(!"".equals(getParam("socStatus")))
				cStatus = getParam("socStatus");
			
			listSenarai.setCarianFail(nofail, tarikhmohon, cStatus, noJKPTG);		
			list = listSenarai.getList();
								
		
		    context.put("PermohonanList", list);
		    context.put("list_size", list.size());
		    context.put("CarianFail", nofail);  
		    context.put("CarianNoJKPTG",noJKPTG );
		    context.put("tarikhPermohonan", tarikhmohon);
		    context.put("SelectStatus", HTML.SelectStatusPPT("socStatus",Utils.parseLong(cStatus),"style=width:130px"));
		    setupPage(session,action,list);
		    
    	}
    	else if ("doChangeHakmilik".equals(submit)){
    		
    		vm = "app/ppt/frmHakmilikSementaraLaporanTanah.jsp";
    		
    		context.put("mode","newLaporan");
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
			
			laporanTanah.check_idPermohonan(id_permohonan);
			checkIdPermohonan = laporanTanah.getIdPermohonan();
			context.put("existLaporan",checkIdPermohonan.size());
			
			idHakmilik = getParam("socHakmilik");

			
			context.put("SelectNoHakmilik",HTML.SelectHakmilikSementara(id_permohonan, "socHakmilik",Utils.parseLong(idHakmilik),"style=width:135px"," onChange=\"doChangeHakmilik();\""));
			    		
    		laporanTanah.setMaklumatHakmilik(idHakmilik);
			maklumatTanah = laporanTanah.getList();
			Hashtable h2 = (Hashtable)maklumatTanah.get(0);
			
			context.put("NO_LOT",h2.get("NO_LOT"));
			context.put("LUAS_LOT",h2.get("LUAS_LOT"));
			context.put("UNIT_LUAS_LOT",h2.get("UNIT_LUAS_LOT"));
			context.put("NAMA_MUKIM",h2.get("NAMA_MUKIM"));
			context.put("LUAS_SEWA",h2.get("LUAS_SEWA"));
			context.put("UNIT_LUAS_SEWA",h2.get("UNIT_LUAS_SEWA"));
			context.put("KATEGORI_TANAH",h2.get("KATEGORI_TANAH"));
			context.put("NO_PELAN",h2.get("NO_PELAN"));
			context.put("NO_SYIT",h2.get("NO_SYIT"));
			context.put("NAMA_PEGAWAI",session.getAttribute("_portal_username"));
			context.put("idHakmilik",h2.get("ID_HAKMILIK"));
			context.put("TARIKH_LAPOR", getParam("txdTarikhLaporan"));

			context.put("TARIKH_MULA_CHART",getParam("txdTkhMulaChart"));
			context.put("TARIKH_AKHIR_CHART",getParam("txdTkhAkhirChart"));
			context.put("TARIKH_LAWATAN",getParam("txdTkhLawatTapak"));
			context.put("PERIHAL_SYIT",getParam("txtBilSyitPiawai"));
			context.put("FLAG_JENIS_TANAH",getParam("sorJenisTanah"));
			context.put("LOKASI_TANAH",getParam("txtLokasiTnh"));
			context.put("FLAG_LUAR_SIMPANAN",getParam("sbcLuarKwsnSmpnMelayu"));
			context.put("FLAG_DLM_SIMPANAN",getParam("sbcDlmKwsnSmpnMelayu"));
			context.put("FLAG_LUAR_MAJLIS",getParam("sbcLuarKwsnMajlisDaerah"));
			context.put("FLAG_DLM_MAJLIS",getParam("sbcDlmKwsnMajlisDaerah"));
			context.put("RUPABUMI_SELURUH_LOT",getParam("txtRupabumiSeluruhLot"));
			context.put("RUPABUMI_KWSN_TERLIBAT",getParam("txtRupabumiKwsTerlibat"));
			context.put("LOT_SELURUH_LOT",getParam("txtKeadaanLotSeluruh"));
			context.put("LOT_JENIS_TANAMAN",getParam("txtKeadaanLotJnsTanaman"));
			context.put("LOT_KEADAAN_TANAMAN",getParam("txtKeadaanLotTanaman"));
			context.put("LOT_KWSN_TERLIBAT",getParam("txtKeadaanLotKwsTerlibat"));
			
    	}
    	else{
			vm = "app/ppt/frmHakmilikSementaraSenaraiLaporanTanah.jsp";
			
			if (!"".equals(getParam("txdTarikh")));
				tarikhmohon = getParam("txdTarikh");
		
			if (!"".equals(getParam("txtNoFail")));
				nofail = getParam("txtNoFail");
				
			if (!"".equals(getParam("txtNoRujJKPTGNegeri")));
				noJKPTG = getParam("txtNoRujJKPTGNegeri");
			
			if(!"".equals(getParam("socStatus")))
				cStatus = getParam("socStatus");
			
			listSenarai.setCarianFail(nofail, tarikhmohon, cStatus, noJKPTG);		
			list = listSenarai.getList();
								
		
		    context.put("PermohonanList", list);
		    context.put("list_size", list.size());
		    context.put("CarianFail", nofail);  
		    context.put("CarianNoJKPTG",noJKPTG );
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
	private String addLaporanTanah(HttpSession session) throws Exception{
		
		Hashtable h = new Hashtable();
	    
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String idHakmilik = getParam("idHakmilik");
		String idPermohonan= getParam("id_permohonan");
		
		h.put("idHakmilik",idHakmilik);
		h.put("idPermohonan",idPermohonan);
		h.put("tkhMulaChart",getParam("txdTkhMulaChart"));
		h.put("tkhAkhirChart",getParam("txdTkhAkhirChart"));
		h.put("tkhLawatTapak",getParam("txdTkhLawatTapak"));
		h.put("id_Masuk",user_id);
		h.put("bilSyit",getParam("txtBilSyitPiawai"));
		h.put("lokasiTanah",getParam("txtLokasiTnh"));
		h.put("flag_Jenis_Tanah",getParam("sorJenisTanah"));
		h.put("flag_Luar_Simpanan",getParam("sbcLuarKwsnSmpnMelayu"));
		h.put("flag_Dlm_Simpanan",getParam("sbcDlmKwsnSmpnMelayu"));
		h.put("flag_Luar_Majlis",getParam("sbcLuarKwsnMajlisDaerah"));
		h.put("flag_Dlm_Majlis",getParam("sbcDlmKwsnMajlisDaerah"));
		h.put("rupabumi_Seluruh_Lot",getParam("txtRupabumiSeluruhLot"));
		h.put("rupabumi_Kwsn_Terlibat",getParam("txtRupabumiKwsTerlibat"));
		h.put("lot_Seluruh_Lot",getParam("txtKeadaanLotSeluruh"));
		h.put("lot_Jenis_Tanaman",getParam("txtKeadaanLotJnsTanaman"));
		h.put("lot_Keadaan_Tanaman",getParam("txtKeadaanLotTanaman"));
		h.put("lot_Kwsn_Terlibat",getParam("txtKeadaanLotKwsTerlibat"));
		h.put("nama_Pelapor",getParam("txtNamaPegawai"));
		h.put("tkhLapor",getParam("txdTarikhLaporan"));
		
		
		return FrmHakmilikSementaraLaporanTanahData.addLaporanTanah(h);
		
		 
		
	}
	
	private void updateLaporanTanah(HttpSession session) throws Exception{
		
		Hashtable h = new Hashtable();
	    
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String idHakmilik = getParam("idHakmilik");
		String idPermohonan= getParam("id_permohonan");
		String idTanahUmum = getParam("idTanahUmum");
		
		h.put("idTanahUmum",idTanahUmum);
		h.put("idHakmilik",idHakmilik);
		h.put("idPermohonan",idPermohonan);
		h.put("tkhMulaChart",getParam("txdTkhMulaChart"));
		h.put("tkhAkhirChart",getParam("txdTkhAkhirChart"));
		h.put("tkhLawatTapak",getParam("txdTkhLawatTapak"));
		h.put("id_Kemaskini",user_id);
		h.put("bilSyit",getParam("txtBilSyitPiawai"));
		h.put("lokasiTanah",getParam("txtLokasiTnh"));
		h.put("flag_Jenis_Tanah",getParam("sorJenisTanah"));
		h.put("flag_Luar_Simpanan",getParam("sbcLuarKwsnSmpnMelayu"));
		h.put("flag_Dlm_Simpanan",getParam("sbcDlmKwsnSmpnMelayu"));
		h.put("flag_Luar_Majlis",getParam("sbcLuarKwsnMajlisDaerah"));
		h.put("flag_Dlm_Majlis",getParam("sbcDlmKwsnMajlisDaerah"));
		h.put("rupabumi_Seluruh_Lot",getParam("txtRupabumiSeluruhLot"));
		h.put("rupabumi_Kwsn_Terlibat",getParam("txtRupabumiKwsTerlibat"));
		h.put("lot_Seluruh_Lot",getParam("txtKeadaanLotSeluruh"));
		h.put("lot_Jenis_Tanaman",getParam("txtKeadaanLotJnsTanaman"));
		h.put("lot_Keadaan_Tanaman",getParam("txtKeadaanLotTanaman"));
		h.put("lot_Kwsn_Terlibat",getParam("txtKeadaanLotKwsTerlibat"));
		h.put("nama_Pelapor",getParam("txtNamaPegawai"));
		h.put("tkhLapor",getParam("txdTarikhLaporan"));
		
		FrmHakmilikSementaraLaporanTanahData.updateLaporanTanah(h);
		
		
	}
	
	private void edit_status_laporanTanah147(HttpSession session) throws Exception {
		String id_permohonan = getParam("id_permohonan");
	    
	    Hashtable h = null;
	    h = new Hashtable();

	    h.put("id_permohonan", id_permohonan);

	    FrmHakmilikSementaraLaporanTanahData.edit_status147(h);
	}
	
	private void edit_status_laporanTanah149(HttpSession session) throws Exception {
		String id_permohonan = getParam("id_permohonan");
	    
	    Hashtable h = null;
	    h = new Hashtable();

	    h.put("id_permohonan", id_permohonan);

	    FrmHakmilikSementaraLaporanTanahData.edit_status149(h);
	}
	
	
	private void deleteLaporanTanah(HttpSession session) throws Exception{
	    String id = getParam("idTanahUmum");	    
	    FrmHakmilikSementaraLaporanTanahData.deleteLaporanTanah(id);
	  }
	
}
