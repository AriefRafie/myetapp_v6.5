package ekptg.view.ppt;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.ppt.FrmHakmilikSementaraMaklumatPermohonanData;
import ekptg.model.ppt.FrmHakmilikSementaraSenaraiSetPerundinganData;
import ekptg.model.ppt.FrmHakmilikSementaraSetPerundinganData;

public class SementaraSetPerundingan extends AjaxBasedModule {

	@Override
	public String doTemplate2() throws Exception {
		
		String vm = "";
		String submit = getParam("command");
        String action = getParam("action");
        context.put("action",action);
        String mode = getParam("mode");
        String tarikhmohon = "";
    	String nofail = "";    
    	String cStatus = "0";
    	String id_fail = getParam("id_fail");
		context.put("idFail", id_fail);
		String id_permohonan = getParam("id_permohonan");
		context.put("idPermohonan",id_permohonan);
		String idSiasatan = getParam("id_siasatan");
		context.put("idSiasatan",idSiasatan);
		String idNegeri = getParam("socNegeri");
		String idHakmilik = getParam("id_hakmilik");
		context.put("idHakmilik",idHakmilik);
		String pb = getParam("socPB");
		String bank = getParam("socBank");
		String negeri = getParam("socNegeri");
		String bandar = getParam("socBandar");
		String id_kehadiran = getParam("id_kehadiran");
		context.put("idKehadiran",id_kehadiran);
		String id_turuthadir = getParam("idTurutHadir");
		context.put("idTurutHadir",id_turuthadir);
		String negeriTurut = getParam("socNegeriTurut");
		String bandarTurut = getParam("socBandarTurut");
		String kodNoPb = getParam("socKodNoPB");
		String kodJenisPb = getParam("socKodJenisPB");
		String pegawai = getParam("socPegawai");
	

    	FrmHakmilikSementaraSenaraiSetPerundinganData listSet = new FrmHakmilikSementaraSenaraiSetPerundinganData();
		FrmHakmilikSementaraMaklumatPermohonanData prmhnnMaster = new FrmHakmilikSementaraMaklumatPermohonanData();
		FrmHakmilikSementaraSetPerundinganData setRundingan = new FrmHakmilikSementaraSetPerundinganData();
    
    	Vector list = null;
    	Vector listAgensi = null;
    	Vector listRundingan = null;
    	Vector paparSet = null;
    	Vector listHakmilik = null;
    	Vector listPb = null;
    	Vector dataPB = null;
    	Vector paparPb = null;
    	Vector listTurutHadir = null;
    	Vector paparTurutHadir = null;
    	
    	HttpSession session = this.request.getSession();
    	
    	
    	if ("newSet".equals(action)){
    		
    		vm = "app/ppt/frmHakmilikSementaraSetPerundingan.jsp";
    	
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
    		
    		setRundingan.setSenaraiHakmilik(id_fail, id_permohonan);
			listHakmilik = setRundingan.getSenaraiHakmilik();
			context.put("SenaraiHakmilik", listHakmilik);
    		
			setRundingan.setRundingan(id_permohonan);
			listRundingan = setRundingan.getListRundingan();
			context.put("SenaraiRundingan",listRundingan);
			
			
    		
    		
    	}
    	else if ("tambahSet".equals(action)){
    		
    		vm = "app/ppt/frmHakmilikSementaraSetPerundingan.jsp";
    		context.put("mode","newSet");
    		context.put("readonly", "");
    		context.put("disabled", "");

    		context.put("SelectNegeri",HTML.SelectNegeri("socNegeri",null,"style=width:200px onChange=\"doChangeidNegeri();\""));
	     	context.put("SelectBandar",HTML.SelectBandar("socBandar",null,"style=width:200px"));
    		context.put("SelectPegawai",HTML.SelectPegawai("socPegawai", null, "style=width:200px"));
	     	context.put("BIL_RUNDING","");
    		context.put("BIL_RUNDING_SBLM","");
    		context.put("NO_RUNDING","");
    		context.put("ALAMAT1","");
    		context.put("ALAMAT2","");
    		context.put("ALAMAT3","");
    		context.put("POSKOD","");
    		context.put("TARIKH_RUNDING","");
    		context.put("MASA_RUNDINGAN","");
    		context.put("WAKTU_RUNDING", "0");
    		context.put("TEMPAT_RUNDING","");
    		context.put("TARIKH_AKHIR","");
    		context.put("ALASAN","");
    		context.put("STATUS_RUNDINGAN","0");
    		
    		

    	}
    	else if ("simpanSet".equals(action)){
    		
    		String siasatan = simpanSet(session);
    		context.put("idSiasatan", siasatan);
    		vm = "app/ppt/frmHakmilikSementaraSetPerundingan.jsp";

    		context.put("mode","paparSet");
    		context.put("readonly", "readonly");
    		context.put("disabled", "disabled");
    		
    		setRundingan.setDataRundingan(siasatan);
    		paparSet = setRundingan.getDataRundingan();
    		Hashtable h = (Hashtable)paparSet.get(0);
    		
    		context.put("SelectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(h.get("ID_NEGERI").toString()),"class=disabled disabled style=width:200px onChange=\"doChangeidNegeri();\""));
	     	context.put("SelectBandar",HTML.SelectBandar("socBandar",Utils.parseLong(h.get("ID_BANDAR").toString()),"class=disabled disabled style=width:200px"));
    		context.put("SelectPegawai",HTML.SelectPegawai("socPegawai", Utils.parseLong(h.get("ID_PEGAWAI_SIASATAN").toString()), "class=disabled disabled style=width:200px"));
	     	context.put("BIL_RUNDING",h.get("NO_KES"));
    		context.put("BIL_RUNDING_SBLM",h.get("NO_KES_SEBELUM"));
    		context.put("NO_RUNDING",h.get("NO_SIASATAN"));
    		context.put("ALAMAT1",h.get("ALAMAT1"));
    		context.put("ALAMAT2",h.get("ALAMAT2"));
    		context.put("ALAMAT3",h.get("ALAMAT3"));
    		context.put("POSKOD",h.get("POSKOD"));
    		context.put("TARIKH_RUNDING",h.get("TARIKH_SIASATAN"));
    		context.put("MASA_RUNDINGAN",h.get("MASA_SIASATAN"));
    		context.put("WAKTU_RUNDING", h.get("JENIS_WAKTU_SIASATAN"));
    		context.put("TEMPAT_RUNDING",h.get("TEMPAT"));
    		context.put("TARIKH_AKHIR",h.get("TARIKH_AKHIR_TAMPAL"));
    		context.put("ALASAN",h.get("ALASAN_TANGGUH"));
    		context.put("STATUS_RUNDINGAN",h.get("STATUS_SIASATAN"));
    		
    		setRundingan.setRundingan(id_permohonan);
			listRundingan = setRundingan.getListRundingan();
			context.put("SenaraiRundingan",listRundingan);
			
			setRundingan.setListPB(h.get("ID_HAKMILIK").toString());
			listPb = setRundingan.getListPb();
			context.put("SenaraiPb",listPb);
			
			setRundingan.setListTurutHadir(h.get("ID_HAKMILIK").toString());
			listTurutHadir = setRundingan.getListTurutHadir();
			context.put("SenaraiTurutHadir",listTurutHadir);
			
    	}
    	else if("paparSet".equals(action)){
    		
    		vm = "app/ppt/frmHakmilikSementaraSetPerundingan.jsp";

    		context.put("mode","paparSet");
    		context.put("readonly", "readonly");
    		context.put("disabled", "disabled");
    		
    		setRundingan.setDataRundingan(idSiasatan);
    		paparSet = setRundingan.getDataRundingan();
    		Hashtable h = (Hashtable)paparSet.get(0);
    		
    		context.put("SelectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(h.get("ID_NEGERI").toString()),"class=disabled disabled style=width:200px onChange=\"doChangeidNegeri();\""));
	     	context.put("SelectBandar",HTML.SelectBandar("socBandar",Utils.parseLong(h.get("ID_BANDAR").toString()),"class=disabled disabled style=width:200px"));
    		context.put("SelectPegawai",HTML.SelectPegawai("socPegawai", Utils.parseLong(h.get("ID_PEGAWAI_SIASATAN").toString()), "class=disabled disabled style=width:200px"));
	     	context.put("BIL_RUNDING",h.get("NO_KES"));
    		context.put("BIL_RUNDING_SBLM",h.get("NO_KES_SEBELUM"));
    		context.put("NO_RUNDING",h.get("NO_SIASATAN"));
    		context.put("ALAMAT1",h.get("ALAMAT1"));
    		context.put("ALAMAT2",h.get("ALAMAT2"));
    		context.put("ALAMAT3",h.get("ALAMAT3"));
    		context.put("POSKOD",h.get("POSKOD"));
    		context.put("TARIKH_RUNDING",h.get("TARIKH_SIASATAN"));
    		context.put("MASA_RUNDINGAN",h.get("MASA_SIASATAN"));
    		context.put("WAKTU_RUNDING", h.get("JENIS_WAKTU_SIASATAN"));
    		context.put("TEMPAT_RUNDING",h.get("TEMPAT"));
    		context.put("TARIKH_AKHIR",h.get("TARIKH_AKHIR_TAMPAL"));
    		context.put("ALASAN",h.get("ALASAN_TANGGUH"));
    		context.put("STATUS_RUNDINGAN",h.get("STATUS_SIASATAN"));
    		context.put("idHakmilik",h.get("ID_HAKMILIK"));
    		
    		setRundingan.setRundingan(id_permohonan);
			listRundingan = setRundingan.getListRundingan();
			context.put("SenaraiRundingan",listRundingan);
			
			setRundingan.setListPB(h.get("ID_HAKMILIK").toString());
			listPb = setRundingan.getListPb();
			context.put("SenaraiPb",listPb);
			
			setRundingan.setListTurutHadir(h.get("ID_HAKMILIK").toString());
			listTurutHadir = setRundingan.getListTurutHadir();
			context.put("SenaraiTurutHadir",listTurutHadir);
    		
    	}
    	else if ("kemaskiniSet".equals(action)){
    		
    		vm = "app/ppt/frmHakmilikSementaraSetPerundingan.jsp";

    		context.put("mode","kemaskiniSet");
    		context.put("readonly", "");
    		context.put("disabled", "");
    		
    		setRundingan.setDataRundingan(idSiasatan);
    		paparSet = setRundingan.getDataRundingan();
    		Hashtable h = (Hashtable)paparSet.get(0);
    		
    		context.put("SelectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(h.get("ID_NEGERI").toString()),"style=width:200px onChange=\"doChangeidNegeriUpdate();\""));
	     	context.put("SelectBandar",HTML.SelectBandar("socBandar",Utils.parseLong(h.get("ID_BANDAR").toString()),"style=width:200px"));
    		context.put("SelectPegawai",HTML.SelectPegawai("socPegawai", Utils.parseLong(h.get("ID_PEGAWAI_SIASATAN").toString()), "style=width:200px"));
	     	context.put("BIL_RUNDING",h.get("NO_KES"));
    		context.put("BIL_RUNDING_SBLM",h.get("NO_KES_SEBELUM"));
    		context.put("NO_RUNDING",h.get("NO_SIASATAN"));
    		context.put("ALAMAT1",h.get("ALAMAT1"));
    		context.put("ALAMAT2",h.get("ALAMAT2"));
    		context.put("ALAMAT3",h.get("ALAMAT3"));
    		context.put("POSKOD",h.get("POSKOD"));
    		context.put("TARIKH_RUNDING",h.get("TARIKH_SIASATAN"));
    		context.put("MASA_RUNDINGAN",h.get("MASA_SIASATAN"));
    		context.put("WAKTU_RUNDING", h.get("JENIS_WAKTU_SIASATAN"));
    		context.put("TEMPAT_RUNDING",h.get("TEMPAT"));
    		context.put("TARIKH_AKHIR",h.get("TARIKH_AKHIR_TAMPAL"));
    		context.put("ALASAN",h.get("ALASAN_TANGGUH"));
    		context.put("STATUS_RUNDINGAN",h.get("STATUS_SIASATAN"));
    		
    		setRundingan.setRundingan(id_permohonan);
			listRundingan = setRundingan.getListRundingan();
			context.put("SenaraiRundingan",listRundingan);
    		
    	}
    	else if ("hapusSet".equals(action)){
    		
    		hapusSet(session);
    		
    		vm = "app/ppt/frmHakmilikSementaraSetPerundingan.jsp";

    		context.put("mode","newSet");
    		context.put("readonly", "");
    		context.put("disabled", "");
    		context.put("action","newSet");
    		
    		context.put("SelectNegeri",HTML.SelectNegeri("socNegeri",null,"style=width:200px onChange=\"doChangeidNegeri();\""));
	     	context.put("SelectBandar",HTML.SelectBandar("socBandar",null,"style=width:200px"));
    		context.put("SelectPegawai",HTML.SelectPegawai("socPegawai", null, "style=width:200px"));

	     	context.put("BIL_RUNDING","");
    		context.put("BIL_RUNDING_SBLM","");
    		context.put("NO_RUNDING","");
    		context.put("ALAMAT1","");
    		context.put("ALAMAT2","");
    		context.put("ALAMAT3","");
    		context.put("POSKOD","");
    		context.put("TARIKH_RUNDING","");
    		context.put("MASA_RUNDINGAN","");
    		context.put("WAKTU_RUNDING","");
    		context.put("TEMPAT_RUNDING","");
    		context.put("TARIKH_AKHIR","");
    		context.put("ALASAN","");
    		context.put("STATUS_RUNDINGAN","");
    		
    		setRundingan.setRundingan(id_permohonan);
			listRundingan = setRundingan.getListRundingan();
			context.put("SenaraiRundingan",listRundingan);
    		
    	}
    	else if ("updateSet".equals(action)){
    		
    		kemaskiniSet(session);
    		vm = "app/ppt/frmHakmilikSementaraSetPerundingan.jsp";

    		context.put("mode","paparSet");
    		context.put("readonly", "readonly");
    		context.put("disabled", "disabled");
    		
    		setRundingan.setDataRundingan(idSiasatan);
    		paparSet = setRundingan.getDataRundingan();
    		Hashtable h = (Hashtable)paparSet.get(0);
    		
    		context.put("SelectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(h.get("ID_NEGERI").toString()),"class=disabled disabled style=width:200px onChange=\"doChangeidNegeri();\""));
	     	context.put("SelectBandar",HTML.SelectBandar("socBandar",Utils.parseLong(h.get("ID_BANDAR").toString()),"class=disabled disabled style=width:200px"));
    		context.put("SelectPegawai",HTML.SelectPegawai("socPegawai", Utils.parseLong(h.get("ID_PEGAWAI_SIASATAN").toString()), "class=disabled disabled style=width:200px"));
	     	context.put("BIL_RUNDING",h.get("NO_KES"));
    		context.put("BIL_RUNDING_SBLM",h.get("NO_KES_SEBELUM"));
    		context.put("NO_RUNDING",h.get("NO_SIASATAN"));
    		context.put("ALAMAT1",h.get("ALAMAT1"));
    		context.put("ALAMAT2",h.get("ALAMAT2"));
    		context.put("ALAMAT3",h.get("ALAMAT3"));
    		context.put("POSKOD",h.get("POSKOD"));
    		context.put("TARIKH_RUNDING",h.get("TARIKH_SIASATAN"));
    		context.put("MASA_RUNDINGAN",h.get("MASA_SIASATAN"));
    		context.put("WAKTU_RUNDING", h.get("JENIS_WAKTU_SIASATAN"));
    		context.put("TEMPAT_RUNDING",h.get("TEMPAT"));
    		context.put("TARIKH_AKHIR",h.get("TARIKH_AKHIR_TAMPAL"));
    		context.put("ALASAN",h.get("ALASAN_TANGGUH"));
    		context.put("STATUS_RUNDINGAN",h.get("STATUS_SIASATAN"));
    		
    		setRundingan.setRundingan(id_permohonan);
			listRundingan = setRundingan.getListRundingan();
			context.put("SenaraiRundingan",listRundingan);
    		
    	}
    	else if("tambahKehadiran".equals(action)){
    		vm = "app/ppt/frmHakmilikSementaraSetPerundingan.jsp";
    		context.put("modePb","newPb");
    		context.put("readonlyPb", "");
    		context.put("disabledPb", "");
    		
    		setRundingan.setDataRundingan(idSiasatan);
    		paparSet = setRundingan.getDataRundingan();
    		Hashtable h = (Hashtable)paparSet.get(0);
    		
    		context.put("BIL_RUNDING",h.get("NO_KES"));
    		context.put("NO_RUNDING",h.get("NO_SIASATAN"));
    		context.put("TARIKH_RUNDING",h.get("TARIKH_SIASATAN"));
    		context.put("MASA_RUNDINGAN",h.get("MASA_SIASATAN"));
    		
    		  if(h.get("JENIS_WAKTU_SIASATAN").equals("1")){
    			  context.put("WAKTU_RUNDING", "PAGI");

	    	  }
    		  else if(h.get("JENIS_WAKTU_SIASATAN").equals("2")){
    			  context.put("WAKTU_RUNDING", "TENGAHARI");

	    	  }
    		  else if(h.get("JENIS_WAKTU_SIASATAN").equals("3")){
    			  context.put("WAKTU_RUNDING","PETANG");

	    	  }
    		  else if(h.get("JENIS_WAKTU_SIASATAN").equals("4")){
    			  context.put("WAKTU_RUNDING","MALAM");

	    	  }
	    	  
    		

    		if (h.get("STATUS_SIASATAN").equals("1")){
    			context.put("STATUS_RUNDINGAN","DALAM PROSES");
    		}
    		else if (h.get("STATUS_SIASATAN").equals("2")){
    			context.put("STATUS_RUNDINGAN","DITUNDA SEBELUM SIASATAN");
    		}
    		else if (h.get("STATUS_SIASATAN").equals("3")){
    			context.put("STATUS_RUNDINGAN","DIBATALKAN");
    		}
    		else if (h.get("STATUS_SIASATAN").equals("4")){
    			context.put("STATUS_RUNDINGAN","ULANG SIASATAN");
    		}
    		else if (h.get("STATUS_SIASATAN").equals("5")){
    			context.put("STATUS_RUNDINGAN","SAMBUNG SIASATAN");
    		}
    		else if (h.get("STATUS_SIASATAN").equals("6")){
    			context.put("STATUS_RUNDINGAN","SELESAI");
    		}
    		
    		context.put("SelectPB",HTML.SelectPB(idHakmilik, "socPB", null, null, "onChange=\"doChangePB();\""));
    		context.put("NAMA_BANK","");
    		context.put("NO_PB","");
    		context.put("LOT","");
    		context.put("NO_LOT","");
    		context.put("NO_PT","");
    		context.put("JENIS_PB", "");
    		context.put("ALAMAT1","");
    		context.put("ALAMAT2","");
    		context.put("ALAMAT3", "");
    		context.put("POSKOD","");
    		context.put("FLAG_HADIR","");
    		context.put("NO_AKAUN","");
    		context.put("NO_HANDPHONE","");
    		context.put("NO_TEL_RUMAH","");
    		context.put("SelectNegeri",HTML.SelectNegeri("socNegeri",null,null));
    		context.put("SelectBandar",HTML.SelectBandar("socBandar", null, null));
    		
    		setRundingan.setListPB(idHakmilik);
			listPb = setRundingan.getListPb();
			context.put("SenaraiPb",listPb);
			
			setRundingan.setListTurutHadir(idHakmilik);
			listTurutHadir = setRundingan.getListTurutHadir();
			context.put("SenaraiTurutHadir",listTurutHadir);
    		
    	}
    	else if("simpanKehadiran".equals(action)){
    		vm = "app/ppt/frmHakmilikSementaraSetPerundingan.jsp";
    		context.put("modePb","paparPb");
    		context.put("readonlyPb", "readonly");
    		context.put("disabledPb", "disabled");
    		context.put("readonlyChecked", "readonly");
    		context.put("disabledChecked", "disabled");
    		
    		
    		String idKehadiran = simpanPb(session);
    		context.put("idKehadiran",idKehadiran);
    		
    		setRundingan.setPaparPB(idKehadiran);
    		paparPb = setRundingan.getPaparPb();
    		Hashtable hP = (Hashtable)paparPb.get(0);
    		
    		context.put("SelectPB",HTML.SelectPB(idHakmilik, "socPB", Utils.parseLong(hP.get("ID_HAKMILIKPB").toString()), null, "class=disabled disabled"));

    		context.put("idHakmilikPb",hP.get("ID_HAKMILIKPB"));
    		context.put("NO_PB",hP.get("NO_PB"));
    		context.put("LOT",hP.get("LOT"));
    		context.put("NO_LOT",hP.get("NO_LOT"));
    		context.put("NO_PT",hP.get("NO_PT"));
    		context.put("JENIS_PB", hP.get("JENIS_PB"));
    		context.put("ALAMAT1",hP.get("ALAMAT1"));
    		context.put("ALAMAT2",hP.get("ALAMAT2"));
    		context.put("ALAMAT3", hP.get("ALAMAT3"));
    		context.put("POSKOD",hP.get("POSKOD"));
    		context.put("FLAG_HADIR",hP.get("FLAG_HADIR"));
    		context.put("NAMA_BANK",hP.get("NAMA_BANK"));
    		context.put("NO_AKAUN",hP.get("NO_AKAUN"));
    		context.put("NO_HANDPHONE",hP.get("NO_HANDPHONE"));
    		context.put("NO_TEL_RUMAH",hP.get("NO_TEL_RUMAH"));
    		context.put("SelectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(hP.get("ID_NEGERI").toString()),"class=disabled disabled"));
    		context.put("SelectBandar",HTML.SelectBandar("socBandar", Utils.parseLong(hP.get("ID_BANDAR").toString()), "class=disabled disabled"));
    		
    		setRundingan.setListPB(idHakmilik);
			listPb = setRundingan.getListPb();
			context.put("SenaraiPb",listPb);
    		
			setRundingan.setListTurutHadir(idHakmilik);
			listTurutHadir = setRundingan.getListTurutHadir();
			context.put("SenaraiTurutHadir",listTurutHadir);
    		
    	}
    	else if("paparKehadiran".equals(action)){
    		
    		vm = "app/ppt/frmHakmilikSementaraSetPerundingan.jsp";
    		context.put("modePb","paparPb");
    		context.put("readonlyPb", "readonly");
    		context.put("disabledPb", "disabled");
    		context.put("readonlyChecked", "readonly");
    		context.put("disabledChecked", "disabled");
    		
    		setRundingan.setDataRundingan(idSiasatan);
    		paparSet = setRundingan.getDataRundingan();
    		Hashtable h = (Hashtable)paparSet.get(0);
    		
    		context.put("BIL_RUNDING",h.get("NO_KES"));
    		context.put("NO_RUNDING",h.get("NO_SIASATAN"));
    		context.put("TARIKH_RUNDING",h.get("TARIKH_SIASATAN"));
    		context.put("MASA_RUNDINGAN",h.get("MASA_SIASATAN"));
    		
    		if(h.get("JENIS_WAKTU_SIASATAN").equals("1")){
  			  context.put("WAKTU_RUNDING", "PAGI");

	    	  }
  		   else if(h.get("JENIS_WAKTU_SIASATAN").equals("2")){
  			  context.put("WAKTU_RUNDING", "TENGAHARI");

	    	  }
  		   else if(h.get("JENIS_WAKTU_SIASATAN").equals("3")){
  			  context.put("WAKTU_RUNDING","PETANG");

	    	  }
  		   else if(h.get("JENIS_WAKTU_SIASATAN").equals("4")){
  			  context.put("WAKTU_RUNDING","MALAM");

	    	  }
	    	  

    		
    		if (h.get("STATUS_SIASATAN").equals("1")){
    			context.put("STATUS_RUNDINGAN","DALAM PROSES");
    		}
    		else if (h.get("STATUS_SIASATAN").equals("2")){
    			context.put("STATUS_RUNDINGAN","DITUNDA SEBELUM SIASATAN");
    		}
    		else if (h.get("STATUS_SIASATAN").equals("3")){
    			context.put("STATUS_RUNDINGAN","DIBATALKAN");
    		}
    		else if (h.get("STATUS_SIASATAN").equals("4")){
    			context.put("STATUS_RUNDINGAN","ULANG SIASATAN");
    		}
    		else if (h.get("STATUS_SIASATAN").equals("5")){
    			context.put("STATUS_RUNDINGAN","SAMBUNG SIASATAN");
    		}
    		else if (h.get("STATUS_SIASATAN").equals("6")){
    			context.put("STATUS_RUNDINGAN","SELESAI");
    		}
    		
    		setRundingan.setPaparPB(id_kehadiran);
    		paparPb = setRundingan.getPaparPb();
    		Hashtable hP = (Hashtable)paparPb.get(0);
    		
    		context.put("SelectPB",HTML.SelectPB(idHakmilik, "socPB", Utils.parseLong(hP.get("ID_HAKMILIKPB").toString()), null, "class=disabled disabled"));
    		context.put("NAMA_BANK",hP.get("NAMA_BANK"));
    		context.put("idHakmilikPb",hP.get("ID_HAKMILIKPB"));
    		context.put("NO_PB",hP.get("NO_PB"));
    		context.put("LOT",hP.get("LOT"));
    		context.put("NO_LOT",hP.get("NO_LOT"));
    		context.put("NO_PT",hP.get("NO_PT"));
    		context.put("JENIS_PB", hP.get("JENIS_PB"));
    		context.put("ALAMAT1",hP.get("ALAMAT1"));
    		context.put("ALAMAT2",hP.get("ALAMAT2"));
    		context.put("ALAMAT3", hP.get("ALAMAT3"));
    		context.put("POSKOD",hP.get("POSKOD"));
    		context.put("FLAG_HADIR",hP.get("FLAG_HADIR"));
    		context.put("NO_AKAUN",hP.get("NO_AKAUN"));
    		context.put("NO_HANDPHONE",hP.get("NO_HANDPHONE"));
    		context.put("NO_TEL_RUMAH",hP.get("NO_TEL_RUMAH"));
    		context.put("SelectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(hP.get("ID_NEGERI").toString()),"class=disabled disabled"));
    		context.put("SelectBandar",HTML.SelectBandar("socBandar", Utils.parseLong(hP.get("ID_BANDAR").toString()), "class=disabled disabled"));
    		
    		setRundingan.setListPB(idHakmilik);
			listPb = setRundingan.getListPb();
			context.put("SenaraiPb",listPb);
    		
			setRundingan.setListTurutHadir(idHakmilik);
			listTurutHadir = setRundingan.getListTurutHadir();
			context.put("SenaraiTurutHadir",listTurutHadir);
    	}
    	else if ("kemaskiniKehadiran".equals(action)){
    		vm = "app/ppt/frmHakmilikSementaraSetPerundingan.jsp";
    		context.put("modePb","kemaskiniPb");
    		context.put("readonlyPb", "");
    		context.put("disabledPb", "");
    		context.put("readonlyChecked", "readonly");
    		context.put("disabledChecked", "disabled");
    		
    		setRundingan.setPaparPB(id_kehadiran);
    		paparPb = setRundingan.getPaparPb();
    		Hashtable hP = (Hashtable)paparPb.get(0);
    		
    		context.put("SelectPB",HTML.SelectPB(idHakmilik, "socPB", Utils.parseLong(hP.get("ID_HAKMILIKPB").toString()), null, "class=disabled disabled"));
    		context.put("NAMA_BANK",hP.get("NAMA_BANK"));
    		context.put("idHakmilikPb",hP.get("ID_HAKMILIKPB"));
    		context.put("NO_PB",hP.get("NO_PB"));
    		context.put("LOT",hP.get("LOT"));
    		context.put("NO_LOT",hP.get("NO_LOT"));
    		context.put("NO_PT",hP.get("NO_PT"));
    		context.put("JENIS_PB", hP.get("JENIS_PB"));
    		context.put("ALAMAT1",hP.get("ALAMAT1"));
    		context.put("ALAMAT2",hP.get("ALAMAT2"));
    		context.put("ALAMAT3", hP.get("ALAMAT3"));
    		context.put("POSKOD",hP.get("POSKOD"));
    		context.put("FLAG_HADIR",hP.get("FLAG_HADIR"));
    		context.put("NO_AKAUN",hP.get("NO_AKAUN"));
    		context.put("NO_HANDPHONE",hP.get("NO_HANDPHONE"));
    		context.put("NO_TEL_RUMAH",hP.get("NO_TEL_RUMAH"));
    		context.put("SelectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(hP.get("ID_NEGERI").toString()),"class=disabled disabled"));
    		context.put("SelectBandar",HTML.SelectBandar("socBandar", Utils.parseLong(hP.get("ID_BANDAR").toString()), "class=disabled disabled"));
    		
    		setRundingan.setListPB(idHakmilik);
			listPb = setRundingan.getListPb();
			context.put("SenaraiPb",listPb);
			
			setRundingan.setListTurutHadir(idHakmilik);
			listTurutHadir = setRundingan.getListTurutHadir();
			context.put("SenaraiTurutHadir",listTurutHadir);
    	}
    	else if("updateKehadiran".equals(action)){
    		
    		kemaskiniPb(session);
    		
    		vm = "app/ppt/frmHakmilikSementaraSetPerundingan.jsp";
    		context.put("modePb","paparPb");
    		context.put("readonlyPb", "readonly");
    		context.put("disabledPb", "disabled");
    		context.put("readonlyChecked", "readonly");
    		context.put("disabledChecked", "disabled");
    		
    		setRundingan.setPaparPB(id_kehadiran);
    		paparPb = setRundingan.getPaparPb();
    		Hashtable hP = (Hashtable)paparPb.get(0);
    		
    		context.put("SelectPB",HTML.SelectPB(idHakmilik, "socPB", Utils.parseLong(hP.get("ID_HAKMILIKPB").toString()), null, "class=disabled disabled"));
    		context.put("NAMA_BANK",hP.get("NAMA_BANK"));
    		context.put("idHakmilikPb",hP.get("ID_HAKMILIKPB"));
    		context.put("NO_PB",hP.get("NO_PB"));
    		context.put("LOT",hP.get("LOT"));
    		context.put("NO_LOT",hP.get("NO_LOT"));
    		context.put("NO_PT",hP.get("NO_PT"));
    		context.put("JENIS_PB", hP.get("JENIS_PB"));
    		context.put("ALAMAT1",hP.get("ALAMAT1"));
    		context.put("ALAMAT2",hP.get("ALAMAT2"));
    		context.put("ALAMAT3", hP.get("ALAMAT3"));
    		context.put("POSKOD",hP.get("POSKOD"));
    		context.put("FLAG_HADIR",hP.get("FLAG_HADIR"));
    		context.put("NO_AKAUN",hP.get("NO_AKAUN"));
    		context.put("NO_HANDPHONE",hP.get("NO_HANDPHONE"));
    		context.put("NO_TEL_RUMAH",hP.get("NO_TEL_RUMAH"));
    		context.put("SelectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(hP.get("ID_NEGERI").toString()),"class=disabled disabled"));
    		context.put("SelectBandar",HTML.SelectBandar("socBandar", Utils.parseLong(hP.get("ID_BANDAR").toString()), "class=disabled disabled"));
    		
    		setRundingan.setListPB(idHakmilik);
			listPb = setRundingan.getListPb();
			context.put("SenaraiPb",listPb);
    		
			setRundingan.setListTurutHadir(idHakmilik);
			listTurutHadir = setRundingan.getListTurutHadir();
			context.put("SenaraiTurutHadir",listTurutHadir);
    		
    	}
    	else if ("hapusKehadiran".equals(action)){
    		
    		hapusKehadiran(session);
    		vm = "app/ppt/frmHakmilikSementaraSetPerundingan.jsp";
    		context.put("modePb","newPb");
    		context.put("readonlyPb", "");
    		context.put("disabledPb", "");
    		context.put("readonlyChecked", "");
    		context.put("disabledChecked", "");
    		
    		context.put("SelectPB",HTML.SelectPB(idHakmilik, "socPB", Utils.parseLong(pb), null, "onChange=\"doChangePB();\""));
    		
    		context.put("NO_PB","");
    		context.put("LOT","");
    		context.put("NO_LOT","");
    		context.put("NO_PT","");
    		context.put("JENIS_PB", "");
    		context.put("ALAMAT1","");
    		context.put("ALAMAT2","");
    		context.put("ALAMAT3", "");
    		context.put("POSKOD","");
    		context.put("NO_AKAUN",getParam("txtNoAkaun"));
    		context.put("NO_HANDPHONE","");
    		context.put("NO_TEL_RUMAH","");
    		context.put("SelectNegeri",HTML.SelectNegeri("socNegeri",null,null));
    		context.put("SelectBandar",HTML.SelectBandar("socBandar", null, null));
    	
    		setRundingan.setListPB(idHakmilik);
			listPb = setRundingan.getListPb();
			context.put("SenaraiPb",listPb);
			
			setRundingan.setListTurutHadir(idHakmilik);
			listTurutHadir = setRundingan.getListTurutHadir();
			context.put("SenaraiTurutHadir",listTurutHadir);
    		
    	}
    	else if ("tambahTurutHadir".equals(action)){
    		vm = "app/ppt/frmHakmilikSementaraSetPerundingan.jsp";
    		context.put("modeTurut", "newTurut");
    		context.put("readonlyTurut","");
    		context.put("disabledTurut","");
    		
    		setRundingan.setDataRundingan(idSiasatan);
    		paparSet = setRundingan.getDataRundingan();
    		Hashtable h = (Hashtable)paparSet.get(0);
    		
    		context.put("BIL_RUNDING",h.get("NO_KES"));
    		context.put("NO_RUNDING",h.get("NO_SIASATAN"));
    		context.put("TARIKH_RUNDING",h.get("TARIKH_SIASATAN"));
    		context.put("MASA_RUNDINGAN",h.get("MASA_SIASATAN"));
    		
    		if(h.get("JENIS_WAKTU_SIASATAN").equals("1")){
  			  context.put("WAKTU_RUNDING", "PAGI");

	    	  }
  		  else if(h.get("JENIS_WAKTU_SIASATAN").equals("2")){
  			  context.put("WAKTU_RUNDING", "TENGAHARI");

	    	  }
  		  else if(h.get("JENIS_WAKTU_SIASATAN").equals("3")){
  			  context.put("WAKTU_RUNDING","PETANG");

	    	  }
  		  else if(h.get("JENIS_WAKTU_SIASATAN").equals("4")){
  			  context.put("WAKTU_RUNDING","MALAM");

	    	  }
	    	  

    		if (h.get("STATUS_SIASATAN").equals("1")){
    			context.put("STATUS_RUNDINGAN","DALAM PROSES");
    		}
    		else if (h.get("STATUS_SIASATAN").equals("2")){
    			context.put("STATUS_RUNDINGAN","DITUNDA SEBELUM SIASATAN");
    		}
    		else if (h.get("STATUS_SIASATAN").equals("3")){
    			context.put("STATUS_RUNDINGAN","DIBATALKAN");
    		}
    		else if (h.get("STATUS_SIASATAN").equals("4")){
    			context.put("STATUS_RUNDINGAN","ULANG SIASATAN");
    		}
    		else if (h.get("STATUS_SIASATAN").equals("5")){
    			context.put("STATUS_RUNDINGAN","SAMBUNG SIASATAN");
    		}
    		else if (h.get("STATUS_SIASATAN").equals("6")){
    			context.put("STATUS_RUNDINGAN","SELESAI");
    		}
    		
    		context.put("idTurutHadir","");
    		context.put("NAMA_PBTURUT","");
    		context.put("NO_PBTURUT","");
    		context.put("ALAMAT1TURUT","");
    		context.put("ALAMAT2TURUT","");
    		context.put("ALAMAT3TURUT","");
    		context.put("POSKODTURUT","");
    		context.put("NO_HANDPHONETURUT","");
    		context.put("NO_TEL_RUMAHTURUT","");
    		context.put("NO_FAXTURUT","");
    		context.put("idHakmilikPb","");
    		context.put("idPB","");
    		context.put("SelectJenisNoPB",HTML.SelectJenisNoPb("socKodNoPB", null, null));
			context.put("SelectJenisPB",HTML.SelectJenisPb2("socKodJenisPB", null, null));
			context.put("SelectNegeriTurut",HTML.SelectNegeri("socNegeriTurut",null,"onChange=\"doChangeidNegeriTurut();\""));
	     	context.put("SelectBandarTurut",HTML.SelectBandar("socBandarTurut",null,null));
	     	
	     	setRundingan.setListTurutHadir(idHakmilik);
			listTurutHadir = setRundingan.getListTurutHadir();
			context.put("SenaraiTurutHadir",listTurutHadir);
    		
    	}
    	else if ("simpanTurutHadir".equals(action)){
    		
    		String idTurutHadir = simpanTurutHadir(session);
    		
    		vm = "app/ppt/frmHakmilikSementaraSetPerundingan.jsp";
    		context.put("modeTurut", "paparTurut");
    		context.put("readonlyTurut","readonly");
    		context.put("disabledTurut","disabled");
    		
    		setRundingan.setPaparTurutHadir(idTurutHadir);
    		paparTurutHadir = setRundingan.getPaparTurutHadir();
    		Hashtable hT = (Hashtable)paparTurutHadir.get(0);
    		
    		context.put("idTurutHadir",hT.get("ID_KEHADIRAN"));
    		context.put("NAMA_PBTURUT",hT.get("NAMA_PB"));
    		context.put("NO_PBTURUT",hT.get("NO_PB"));
    		context.put("ALAMAT1TURUT",hT.get("ALAMAT1"));
    		context.put("ALAMAT2TURUT",hT.get("ALAMAT2"));
    		context.put("ALAMAT3TURUT",hT.get("ALAMAT3"));
    		context.put("POSKODTURUT",hT.get("POSKOD"));
    		context.put("NO_HANDPHONETURUT",hT.get("NO_HANDPHONE"));
    		context.put("NO_TEL_RUMAHTURUT",hT.get("NO_TEL_RUMAH"));
    		context.put("NO_FAXTURUT",hT.get("NO_FAX"));
    		context.put("idHakmilikPb",hT.get("ID_HAKMILIKPB"));
    		context.put("idPB",hT.get("ID_PIHAKBERKEPENTINGAN"));

    		context.put("SelectJenisNoPB",HTML.SelectJenisNoPb("socKodNoPB",Utils.parseLong(hT.get("ID_JENISNOPB").toString()), "class=disabled disabled"));
			context.put("SelectJenisPB",HTML.SelectJenisPb2("socKodJenisPB", Utils.parseLong(hT.get("ID_JENISPB").toString()), "class=disabled disabled"));
			context.put("SelectNegeriTurut",HTML.SelectNegeri("socNegeriTurut",Utils.parseLong(hT.get("ID_NEGERI").toString()),"class=disabled disabled"));
	     	context.put("SelectBandarTurut",HTML.SelectBandar("socBandarTurut",Utils.parseLong(hT.get("ID_BANDAR").toString()),"class=disabled disabled"));
	     	
	     	setRundingan.setListTurutHadir(idHakmilik);
			listTurutHadir = setRundingan.getListTurutHadir();
			context.put("SenaraiTurutHadir",listTurutHadir);
    		
    		
    	}
    	else if ("kemaskiniTurutHadir".equals(action)){
    		
    		vm = "app/ppt/frmHakmilikSementaraSetPerundingan.jsp";
    		context.put("modeTurut", "kemaskiniTurut");
    		context.put("readonlyTurut","");
    		context.put("disabledTurut","");
    		
    		setRundingan.setPaparTurutHadir(id_turuthadir);
    		paparTurutHadir = setRundingan.getPaparTurutHadir();
    		Hashtable hT = (Hashtable)paparTurutHadir.get(0);
    		
    		context.put("idTurutHadir",hT.get("ID_KEHADIRAN"));
    		context.put("NAMA_PBTURUT",hT.get("NAMA_PB"));
    		context.put("NO_PBTURUT",hT.get("NO_PB"));
    		context.put("ALAMAT1TURUT",hT.get("ALAMAT1"));
    		context.put("ALAMAT2TURUT",hT.get("ALAMAT2"));
    		context.put("ALAMAT3TURUT",hT.get("ALAMAT3"));
    		context.put("POSKODTURUT",hT.get("POSKOD"));
    		context.put("NO_HANDPHONETURUT",hT.get("NO_HANDPHONE"));
    		context.put("NO_TEL_RUMAHTURUT",hT.get("NO_TEL_RUMAH"));
    		context.put("NO_FAXTURUT",hT.get("NO_FAX"));
    		context.put("idHakmilikPb",hT.get("ID_HAKMILIKPB"));
    		context.put("idPB",hT.get("ID_PIHAKBERKEPENTINGAN"));

    		context.put("SelectJenisNoPB",HTML.SelectJenisNoPb("socKodNoPB",Utils.parseLong(hT.get("ID_JENISNOPB").toString()), null));
			context.put("SelectJenisPB",HTML.SelectJenisPb2("socKodJenisPB", Utils.parseLong(hT.get("ID_JENISPB").toString()), null));
			context.put("SelectNegeriTurut",HTML.SelectNegeri("socNegeriTurut",Utils.parseLong(hT.get("ID_NEGERI").toString()),"onChange=\"doChangeidNegeriTurutUpdate();\""));
	     	context.put("SelectBandarTurut",HTML.SelectBandar("socBandarTurut",Utils.parseLong(hT.get("ID_BANDAR").toString()),null));
	     	
	     	setRundingan.setListTurutHadir(idHakmilik);
			listTurutHadir = setRundingan.getListTurutHadir();
			context.put("SenaraiTurutHadir",listTurutHadir);
    		
    		
    		
    	}
    	else if ("updateTurutHadir".equals(action)){
    		
    		kemaskiniTurutHadir(session);
    		
    		vm = "app/ppt/frmHakmilikSementaraSetPerundingan.jsp";
    		context.put("modeTurut", "paparTurut");
    		context.put("readonlyTurut","readonly");
    		context.put("disabledTurut","disabled");
    		
    		setRundingan.setPaparTurutHadir(id_turuthadir);
    		paparTurutHadir = setRundingan.getPaparTurutHadir();
    		Hashtable hT = (Hashtable)paparTurutHadir.get(0);
    		
    		context.put("idTurutHadir",hT.get("ID_KEHADIRAN"));
    		context.put("NAMA_PBTURUT",hT.get("NAMA_PB"));
    		context.put("NO_PBTURUT",hT.get("NO_PB"));
    		context.put("ALAMAT1TURUT",hT.get("ALAMAT1"));
    		context.put("ALAMAT2TURUT",hT.get("ALAMAT2"));
    		context.put("ALAMAT3TURUT",hT.get("ALAMAT3"));
    		context.put("POSKODTURUT",hT.get("POSKOD"));
    		context.put("NO_HANDPHONETURUT",hT.get("NO_HANDPHONE"));
    		context.put("NO_TEL_RUMAHTURUT",hT.get("NO_TEL_RUMAH"));
    		context.put("NO_FAXTURUT",hT.get("NO_FAX"));
    		context.put("idHakmilikPb",hT.get("ID_HAKMILIKPB"));
    		context.put("idPB",hT.get("ID_PIHAKBERKEPENTINGAN"));
    		
    		context.put("SelectJenisNoPB",HTML.SelectJenisNoPb("socKodNoPB",Utils.parseLong(hT.get("ID_JENISNOPB").toString()), "class=disabled disabled"));
			context.put("SelectJenisPB",HTML.SelectJenisPb2("socKodJenisPB", Utils.parseLong(hT.get("ID_JENISPB").toString()), "class=disabled disabled"));
			context.put("SelectNegeriTurut",HTML.SelectNegeri("socNegeriTurut",Utils.parseLong(hT.get("ID_NEGERI").toString()),"class=disabled disabled"));
	     	context.put("SelectBandarTurut",HTML.SelectBandar("socBandarTurut",Utils.parseLong(hT.get("ID_BANDAR").toString()),"class=disabled disabled"));
	     	
	     	setRundingan.setListTurutHadir(idHakmilik);
			listTurutHadir = setRundingan.getListTurutHadir();
			context.put("SenaraiTurutHadir",listTurutHadir);
    		
    		
    	}
    	else if ("paparTurutHadir".equals(action)){
    		
    		vm = "app/ppt/frmHakmilikSementaraSetPerundingan.jsp";
    		context.put("modeTurut", "paparTurut");
    		context.put("readonlyTurut","readonly");
    		context.put("disabledTurut","disabled");
    		
    		setRundingan.setPaparTurutHadir(id_turuthadir);
    		paparTurutHadir = setRundingan.getPaparTurutHadir();
    		Hashtable hT = (Hashtable)paparTurutHadir.get(0);
    		
    		context.put("idTurutHadir",hT.get("ID_KEHADIRAN"));
    		context.put("NAMA_PBTURUT",hT.get("NAMA_PB"));
    		context.put("NO_PBTURUT",hT.get("NO_PB"));
    		context.put("ALAMAT1TURUT",hT.get("ALAMAT1"));
    		context.put("ALAMAT2TURUT",hT.get("ALAMAT2"));
    		context.put("ALAMAT3TURUT",hT.get("ALAMAT3"));
    		context.put("POSKODTURUT",hT.get("POSKOD"));
    		context.put("NO_HANDPHONETURUT",hT.get("NO_HANDPHONE"));
    		context.put("NO_TEL_RUMAHTURUT",hT.get("NO_TEL_RUMAH"));
    		context.put("NO_FAXTURUT",hT.get("NO_FAX"));
    		context.put("idHakmilikPb",hT.get("ID_HAKMILIKPB"));
    		context.put("idPB",hT.get("ID_PIHAKBERKEPENTINGAN"));
    		
    		context.put("SelectJenisNoPB",HTML.SelectJenisNoPb("socKodNoPB",Utils.parseLong(hT.get("ID_JENISNOPB").toString()), "class=disabled disabled"));
			context.put("SelectJenisPB",HTML.SelectJenisPb2("socKodJenisPB", Utils.parseLong(hT.get("ID_JENISPB").toString()), "class=disabled disabled"));
			context.put("SelectNegeriTurut",HTML.SelectNegeri("socNegeriTurut",Utils.parseLong(hT.get("ID_NEGERI").toString()),"class=disabled disabled"));
	     	context.put("SelectBandarTurut",HTML.SelectBandar("socBandarTurut",Utils.parseLong(hT.get("ID_BANDAR").toString()),"class=disabled disabled"));
	     	
	     	setRundingan.setListTurutHadir(idHakmilik);
			listTurutHadir = setRundingan.getListTurutHadir();
			context.put("SenaraiTurutHadir",listTurutHadir);
    		
    	}
    	else if ("hapusTurutHadir".equals(action)){
    		
    		hapusTurutHadir(session);
    		
    		vm = "app/ppt/frmHakmilikSementaraSetPerundingan.jsp";
    		context.put("modeTurut", "newTurut");
    		context.put("readonlyTurut","");
    		context.put("disabledTurut","");
    		
    		context.put("idTurutHadir","");
    		context.put("NAMA_PBTURUT","");
    		context.put("NO_PBTURUT","");
    		context.put("ALAMAT1TURUT","");
    		context.put("ALAMAT2TURUT","");
    		context.put("ALAMAT3TURUT","");
    		context.put("POSKODTURUT","");
    		context.put("NO_HANDPHONETURUT","");
    		context.put("NO_TEL_RUMAHTURUT","");
    		context.put("NO_FAXTURUT","");
    		context.put("idHakmilikPb","");
    		context.put("idPB","");
    		context.put("SelectJenisNoPB",HTML.SelectJenisNoPb("socKodNoPB", null, null));
			context.put("SelectJenisPB",HTML.SelectJenisPb2("socKodJenisPB", null, null));
			context.put("SelectNegeriTurut",HTML.SelectNegeri("socNegeriTurut",null,"onChange=\"doChangeidNegeriTurut();\""));
	     	context.put("SelectBandarTurut",HTML.SelectBandar("socBandarTurut",null,null));
	     	
	     	setRundingan.setListTurutHadir(idHakmilik);
			listTurutHadir = setRundingan.getListTurutHadir();
			context.put("SenaraiTurutHadir",listTurutHadir);
    	}
    	else if ("doChangePB".equals(submit)){
    		
    		vm = "app/ppt/frmHakmilikSementaraSetPerundingan.jsp";
    		context.put("modePb","newPb");
    		context.put("readonlyPb", "");
    		context.put("disabledPb", "");
    		context.put("action","tambahKehadiran");
    		
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
    		
    		
    		setRundingan.setDataRundingan(idSiasatan);
    		paparSet = setRundingan.getDataRundingan();
    		Hashtable hR = (Hashtable)paparSet.get(0);
    		
    		context.put("BIL_RUNDING",hR.get("NO_KES"));
    		context.put("NO_RUNDING",hR.get("NO_SIASATAN"));
    		context.put("TARIKH_RUNDING",hR.get("TARIKH_SIASATAN"));
    		context.put("MASA_RUNDINGAN",hR.get("MASA_SIASATAN"));
    		context.put("WAKTU_RUNDING", hR.get("JENIS_WAKTU_SIASATAN"));

    		
    		if (hR.get("STATUS_SIASATAN").equals("1")){
    			context.put("STATUS_RUNDINGAN","DALAM PROSES");
    		}
    		else if (hR.get("STATUS_SIASATAN").equals("2")){
    			context.put("STATUS_RUNDINGAN","DITUNDA SEBELUM SIASATAN");
    		}
    		else if (hR.get("STATUS_SIASATAN").equals("3")){
    			context.put("STATUS_RUNDINGAN","DIBATALKAN");
    		}
    		else if (hR.get("STATUS_SIASATAN").equals("4")){
    			context.put("STATUS_RUNDINGAN","ULANG SIASATAN");
    		}
    		else if (hR.get("STATUS_SIASATAN").equals("5")){
    			context.put("STATUS_RUNDINGAN","SAMBUNG SIASATAN");
    		}
    		else if (hR.get("STATUS_SIASATAN").equals("6")){
    			context.put("STATUS_RUNDINGAN","SELESAI");
    		}
    		
    		if(hR.get("JENIS_WAKTU_SIASATAN").equals("1")){
    			  context.put("WAKTU_RUNDING", "PAGI");

  	    	}
    		else if(hR.get("JENIS_WAKTU_SIASATAN").equals("2")){
    			  context.put("WAKTU_RUNDING", "TENGAHARI");

  	    	}
    		else if(hR.get("JENIS_WAKTU_SIASATAN").equals("3")){
    			  context.put("WAKTU_RUNDING","PETANG");

  	    	}
    		else if(hR.get("JENIS_WAKTU_SIASATAN").equals("4")){
    			  context.put("WAKTU_RUNDING","MALAM");

  	    	}
    		
    		setRundingan.setDataPB(pb);
    		dataPB = setRundingan.getDataPb();
    		Hashtable hP = (Hashtable)dataPB.get(0);
    		
    		context.put("SelectPB",HTML.SelectPB(idHakmilik, "socPB", Utils.parseLong(pb), null, "onChange=\"doChangePB();\""));
    		context.put("NAMA_BANK",getParam("txtNamaBank"));    		
    		context.put("NO_PB",hP.get("NO_PB"));
    		context.put("LOT",hP.get("LOT"));
    		context.put("NO_LOT",hP.get("NO_LOT"));
    		context.put("NO_PT",hP.get("NO_PT"));
    		context.put("JENIS_PB", hP.get("JENIS_PB"));
    		context.put("ALAMAT1",hP.get("ALAMAT1"));
    		context.put("ALAMAT2",hP.get("ALAMAT2"));
    		context.put("ALAMAT3", hP.get("ALAMAT3"));
    		context.put("POSKOD", hP.get("POSKOD"));
    		context.put("NO_AKAUN",getParam("txtNoAkaun"));
    		context.put("NO_HANDPHONE",hP.get("NO_HANDPHONE"));
    		context.put("NO_TEL_RUMAH",hP.get("NO_TEL_RUMAH"));

    		context.put("SelectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(hP.get("ID_NEGERI").toString()),"class=disabled disabled"));
    		context.put("SelectBandar",HTML.SelectBandar("socBandar", Utils.parseLong(hP.get("ID_BANDAR").toString()), "class=disabled disabled"));
    		
    		setRundingan.setListPB(idHakmilik);
			listPb = setRundingan.getListPb();
			context.put("SenaraiPb",listPb);
			
			setRundingan.setListTurutHadir(idHakmilik);
			listTurutHadir = setRundingan.getListTurutHadir();
			context.put("SenaraiTurutHadir",listTurutHadir);
    	}
    	else if ("doChangeidNegeri".equals(submit)) {
    		
    		vm = "app/ppt/frmHakmilikSementaraSetPerundingan.jsp";
    		context.put("mode","newSet");
    		context.put("readonly", "");
    		context.put("disabled", "");
    		context.put("action","tambahSet");
    		
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
    		
    		
			context.put("SelectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri),"style=width:200px onChange=\"doChangeidNegeri();\""));
     		context.put("SelectBandar",HTML.SelectBandarByNegeri(idNegeri,"socBandar",null,"style=width:200px"));
    		context.put("SelectPegawai",HTML.SelectPegawai("socPegawai", Utils.parseLong(pegawai), "style=width:200px"));

     		context.put("BIL_RUNDING",getParam("txtBilRundingan"));
    		context.put("BIL_RUNDING_SBLM",getParam("txtBilRundinganSblm"));
    		context.put("NO_RUNDING",getParam("txtNoPerundingan"));
    		context.put("ALAMAT1",getParam("txtAlamat1"));
    		context.put("ALAMAT2",getParam("txtAlamat2"));
    		context.put("ALAMAT3",getParam("txtAlamat3"));
    		context.put("POSKOD",getParam("txtPoskod"));
    		context.put("TARIKH_RUNDING",getParam("txdTkhRundingan"));
    		context.put("MASA_RUNDINGAN",getParam("txtMasaRundingan"));
    		context.put("WAKTU_RUNDING", getParam("socWaktuRundingan"));
    		context.put("TEMPAT_RUNDING",getParam("txtTempat"));
    		context.put("TARIKH_AKHIR",getParam("txdTkhAkhirNotis"));
    		context.put("ALASAN",getParam("txtAlasan"));
    		context.put("STATUS_RUNDINGAN",getParam("socStatusRundingan"));
    		
    		setRundingan.setRundingan(id_permohonan);
			listRundingan = setRundingan.getListRundingan();
			context.put("SenaraiRundingan",listRundingan);

    	}
    	else if ("doChangeidNegeriUpdate".equals(submit)) {
    		
    		vm = "app/ppt/frmHakmilikSementaraSetPerundingan.jsp";
    		context.put("mode","kemaskiniSet");
    		context.put("readonly", "");
    		context.put("disabled", "");
    		context.put("action","kemaskiniSet");
    		
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
    		
    		
			context.put("SelectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri),"style=width:200px onChange=\"doChangeidNegeriUpdate();\""));
     		context.put("SelectBandar",HTML.SelectBandarByNegeri(idNegeri,"socBandar",null,"style=width:200px"));
    		context.put("SelectPegawai",HTML.SelectPegawai("socPegawai", Utils.parseLong(pegawai), "style=width:200px"));

     		context.put("BIL_RUNDING",getParam("txtBilRundingan"));
    		context.put("BIL_RUNDING_SBLM",getParam("txtBilRundinganSblm"));
    		context.put("NO_RUNDING",getParam("txtNoPerundingan"));
    		context.put("ALAMAT1",getParam("txtAlamat1"));
    		context.put("ALAMAT2",getParam("txtAlamat2"));
    		context.put("ALAMAT3",getParam("txtAlamat3"));
    		context.put("POSKOD",getParam("txtPoskod"));
    		context.put("TARIKH_RUNDING",getParam("txdTkhRundingan"));
    		context.put("MASA_RUNDINGAN",getParam("txtMasaRundingan"));
    		context.put("WAKTU_RUNDING", getParam("socWaktuRundingan"));
    		context.put("TEMPAT_RUNDING",getParam("txtTempat"));
    		context.put("TARIKH_AKHIR",getParam("txdTkhAkhirNotis"));
    		context.put("ALASAN",getParam("txtAlasan"));
    		context.put("STATUS_RUNDINGAN",getParam("socStatusRundingan"));
    		
    		setRundingan.setRundingan(id_permohonan);
			listRundingan = setRundingan.getListRundingan();
			context.put("SenaraiRundingan",listRundingan);

    	}
    	else if ("doChangeidNegeriTurut".equals(submit)) {
    		
    		vm = "app/ppt/frmHakmilikSementaraSetPerundingan.jsp";
    		context.put("modeTurut","newTurut");
    		context.put("readonlyTurut", "");
    		context.put("disabledTurut", "");
    		context.put("action","tambahTurutHadir");
    		
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
    		
    		setRundingan.setDataRundingan(idSiasatan);
    		paparSet = setRundingan.getDataRundingan();
    		Hashtable hR = (Hashtable)paparSet.get(0);
    		
    		context.put("BIL_RUNDING",hR.get("NO_KES"));
    		context.put("NO_RUNDING",hR.get("NO_SIASATAN"));
    		context.put("TARIKH_RUNDING",hR.get("TARIKH_SIASATAN"));
    		context.put("MASA_RUNDINGAN",hR.get("MASA_SIASATAN"));
    		
    		if(hR.get("JENIS_WAKTU_SIASATAN").equals("1")){
  			  context.put("WAKTU_RUNDING", "PAGI");

	    	  }
  		    else if(hR.get("JENIS_WAKTU_SIASATAN").equals("2")){
  			  context.put("WAKTU_RUNDING", "TENGAHARI");

	    	  }
  		    else if(hR.get("JENIS_WAKTU_SIASATAN").equals("3")){
  			  context.put("WAKTU_RUNDING","PETANG");

	    	  }
  		    else if(hR.get("JENIS_WAKTU_SIASATAN").equals("4")){
  			  context.put("WAKTU_RUNDING","MALAM");

	    	}

    		
    		if (hR.get("STATUS_SIASATAN").equals("1")){
    			context.put("STATUS_RUNDINGAN","DALAM PROSES");
    		}
    		else if (hR.get("STATUS_SIASATAN").equals("2")){
    			context.put("STATUS_RUNDINGAN","DITUNDA SEBELUM SIASATAN");
    		}
    		else if (hR.get("STATUS_SIASATAN").equals("3")){
    			context.put("STATUS_RUNDINGAN","DIBATALKAN");
    		}
    		else if (hR.get("STATUS_SIASATAN").equals("4")){
    			context.put("STATUS_RUNDINGAN","ULANG SIASATAN");
    		}
    		else if (hR.get("STATUS_SIASATAN").equals("5")){
    			context.put("STATUS_RUNDINGAN","SAMBUNG SIASATAN");
    		}
    		else if (hR.get("STATUS_SIASATAN").equals("6")){
    			context.put("STATUS_RUNDINGAN","SELESAI");
    		}
    		
    		
    		context.put("NAMA_PBTURUT",getParam("txtNamaPB"));
    		context.put("NO_PBTURUT",getParam("txtNoPB"));
    		context.put("ALAMAT1TURUT",getParam("txtAlamat4"));
    		context.put("ALAMAT2TURUT",getParam("txtAlamat5"));
    		context.put("ALAMAT3TURUT",getParam("txtAlamat6"));
    		context.put("POSKODTURUT",getParam("txtPoskod2"));
    		context.put("NO_HANDPHONETURUT",getParam("txtNoHp"));
    		context.put("NO_TEL_RUMAHTURUT",getParam("txtNoTelPjbt"));
    		context.put("NO_FAXTURUT",getParam("txtNoFaks"));
    		context.put("SelectJenisNoPB",HTML.SelectJenisNoPb("socKodNoPB", Utils.parseLong(kodNoPb), null));
			context.put("SelectJenisPB",HTML.SelectJenisPb2("socKodJenisPB", Utils.parseLong(kodJenisPb), null));
			context.put("SelectNegeriTurut",HTML.SelectNegeri("socNegeriTurut",Utils.parseLong(negeriTurut),"onChange=\"doChangeidNegeriTurut();\""));
	     	context.put("SelectBandarTurut",HTML.SelectBandarByNegeri(negeriTurut,"socBandarTurut",Utils.parseLong(bandarTurut),null));
	     	
	     	setRundingan.setListTurutHadir(idHakmilik);
			listTurutHadir = setRundingan.getListTurutHadir();
			context.put("SenaraiTurutHadir",listTurutHadir);

    	}
    	else if ("doChangeidNegeriTurutUpdate".equals(submit)) {
    		
    		vm = "app/ppt/frmHakmilikSementaraSetPerundingan.jsp";
    		context.put("modeTurut","kemaskiniTurut");
    		context.put("readonlyTurut", "");
    		context.put("disabledTurut", "");
    		context.put("action","kemaskiniTurutHadir");
    		
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
    		
    		setRundingan.setDataRundingan(idSiasatan);
    		paparSet = setRundingan.getDataRundingan();
    		Hashtable hR = (Hashtable)paparSet.get(0);
    		
    		context.put("BIL_RUNDING",hR.get("NO_KES"));
    		context.put("NO_RUNDING",hR.get("NO_SIASATAN"));
    		context.put("TARIKH_RUNDING",hR.get("TARIKH_SIASATAN"));
    		context.put("MASA_RUNDINGAN",hR.get("MASA_SIASATAN"));
    		context.put("WAKTU_RUNDING", hR.get("JENIS_WAKTU_SIASATAN"));

    		
    		if (hR.get("STATUS_SIASATAN").equals("1")){
    			context.put("STATUS_RUNDINGAN","DALAM PROSES");
    		}
    		else if (hR.get("STATUS_SIASATAN").equals("2")){
    			context.put("STATUS_RUNDINGAN","DITUNDA SEBELUM SIASATAN");
    		}
    		else if (hR.get("STATUS_SIASATAN").equals("3")){
    			context.put("STATUS_RUNDINGAN","DIBATALKAN");
    		}
    		else if (hR.get("STATUS_SIASATAN").equals("4")){
    			context.put("STATUS_RUNDINGAN","ULANG SIASATAN");
    		}
    		else if (hR.get("STATUS_SIASATAN").equals("5")){
    			context.put("STATUS_RUNDINGAN","SAMBUNG SIASATAN");
    		}
    		else if (hR.get("STATUS_SIASATAN").equals("6")){
    			context.put("STATUS_RUNDINGAN","SELESAI");
    		}
    		
    		if(hR.get("JENIS_WAKTU_SIASATAN").equals("1")){
    			  context.put("WAKTU_RUNDING", "PAGI");

  	    	}
    		else if(hR.get("JENIS_WAKTU_SIASATAN").equals("2")){
    			  context.put("WAKTU_RUNDING", "TENGAHARI");

  	    	}
    		else if(hR.get("JENIS_WAKTU_SIASATAN").equals("3")){
    			  context.put("WAKTU_RUNDING","PETANG");

  	    	}
    		else if(hR.get("JENIS_WAKTU_SIASATAN").equals("4")){
    			  context.put("WAKTU_RUNDING","MALAM");

  	    	}
    		context.put("NAMA_PBTURUT",getParam("txtNamaPB"));
    		context.put("NO_PBTURUT",getParam("txtNoPB"));
    		context.put("ALAMAT1TURUT",getParam("txtAlamat4"));
    		context.put("ALAMAT2TURUT",getParam("txtAlamat5"));
    		context.put("ALAMAT3TURUT",getParam("txtAlamat6"));
    		context.put("POSKODTURUT",getParam("txtPoskod2"));
    		context.put("NO_HANDPHONETURUT",getParam("txtNoHp"));
    		context.put("NO_TEL_RUMAHTURUT",getParam("txtNoTelPjbt"));
    		context.put("NO_FAXTURUT",getParam("txtNoFaks"));
    		context.put("SelectJenisNoPB",HTML.SelectJenisNoPb("socKodNoPB", Utils.parseLong(kodNoPb), null));
			context.put("SelectJenisPB",HTML.SelectJenisPb2("socKodJenisPB", Utils.parseLong(kodJenisPb), null));
			context.put("SelectNegeriTurut",HTML.SelectNegeri("socNegeriTurut",Utils.parseLong(negeriTurut),"onChange=\"doChangeidNegeriTurutUpdate();\""));
	     	context.put("SelectBandarTurut",HTML.SelectBandarByNegeri(negeriTurut,"socBandarTurut",Utils.parseLong(bandarTurut),null));
	     	
	     	setRundingan.setListTurutHadir(idHakmilik);
			listTurutHadir = setRundingan.getListTurutHadir();
			context.put("SenaraiTurutHadir",listTurutHadir);

    	}
    	else{
    		 	vm = "app/ppt/frmHakmilikSementaraSenaraiSetPerundingan.jsp";
    			
    			if (!"".equals(getParam("txdTarikh")));
    				tarikhmohon = getParam("txdTarikh");
    		
    			if (!"".equals(getParam("txtNoFail")));
    				nofail = getParam("txtNoFail");
    			
    			if(!"".equals(getParam("socStatus")));
    				cStatus = getParam("socStatus");
    			
    			listSet.setCarianFail(nofail, tarikhmohon, cStatus);		
    			list = listSet.getList();
    								
    		
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
	
	private String simpanSet(HttpSession session) throws Exception{
		
		Hashtable h = new Hashtable();
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String idPermohonan= getParam("id_permohonan");
		String idHakmilik = getParam("id_hakmilik");

		h.put("idPermohonan",idPermohonan);
		h.put("idHakmilik",idHakmilik);
		h.put("bilRundingan",getParam("txtBilRundingan"));
		h.put("bilRundinganSblm",getParam("txtBilRundinganSblm"));
		h.put("noPerundingan",getParam("txtNoPerundingan"));
		h.put("pegawaiPerundingan", getParam("socPegawai"));
		h.put("alamat1",getParam("txtAlamat1"));
		h.put("alamat2",getParam("txtAlamat2"));
		h.put("alamat3",getParam("txtAlamat3"));
		h.put("poskod",getParam("txtPoskod"));
		h.put("negeri",getParam("socNegeri"));
		h.put("bandar",getParam("socBandar"));
		h.put("statusRundingan",getParam("socStatusRundingan"));
		h.put("tarikhRundingan",getParam("txdTkhRundingan"));
		h.put("masaRundingan",getParam("txtMasaRundingan"));
		h.put("waktuRundingan",getParam("socWaktuRundingan"));
		h.put("tempat",getParam("txtTempat"));
		h.put("tarikhAkhirNotis",getParam("txdTkhAkhirNotis"));
		h.put("alasan",getParam("txtAlasan"));
		h.put("idMasuk",user_id);
		
		return FrmHakmilikSementaraSetPerundinganData.addSet(h);
		
	}
	
	private void kemaskiniSet(HttpSession session) throws Exception{
		
		Hashtable h = new Hashtable();
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String idSiasatan= getParam("id_siasatan");

		h.put("idSiasatan",idSiasatan);
		h.put("bilRundingan",getParam("txtBilRundingan"));
		h.put("bilRundinganSblm",getParam("txtBilRundinganSblm"));
		h.put("noPerundingan",getParam("txtNoPerundingan"));
		h.put("pegawaiPerundingan", getParam("socPegawai"));
		h.put("alamat1",getParam("txtAlamat1"));
		h.put("alamat2",getParam("txtAlamat2"));
		h.put("alamat3",getParam("txtAlamat3"));
		h.put("poskod",getParam("txtPoskod"));
		h.put("negeri",getParam("socNegeri"));
		h.put("bandar",getParam("socBandar"));
		h.put("statusRundingan",getParam("socStatusRundingan"));
		h.put("tarikhRundingan",getParam("txdTkhRundingan"));
		h.put("masaRundingan",getParam("txtMasaRundingan"));
		h.put("waktuRundingan",getParam("socWaktuRundingan"));
		h.put("tempat",getParam("txtTempat"));
		h.put("tarikhAkhirNotis",getParam("txdTkhAkhirNotis"));
		h.put("alasan",getParam("txtAlasan"));
		h.put("idKemaskini",user_id);
		
		FrmHakmilikSementaraSetPerundinganData.updateSet(h);
		
	}
	
	private void hapusSet(HttpSession session) throws Exception{
	    String id = getParam("id_siasatan");	    
	    FrmHakmilikSementaraSetPerundinganData.hapusSet(id);
	  }
	
	private String simpanPb(HttpSession session) throws Exception{
		
		Hashtable h = new Hashtable();
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		
		h.put("idSiasatan",getParam("id_siasatan"));
		h.put("idHakmilikPb",getParam("socPB"));
		h.put("flagHadir","1");
		h.put("namaBank",getParam("txtNamaBank"));
		h.put("noAkaun", getParam("txtNoAkaun"));
		h.put("idMasuk",user_id);
		
		return FrmHakmilikSementaraSetPerundinganData.addPb(h);
		
	}
	
	private void kemaskiniPb(HttpSession session) throws Exception{
		
		Hashtable h = new Hashtable();
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		
		h.put("idHakmilikPb",getParam("idHakmilikPb"));
		h.put("namaBank",getParam("txtNamaBank"));
		h.put("noAkaun", getParam("txtNoAkaun"));
		h.put("idKemaskini",user_id);
		
		FrmHakmilikSementaraSetPerundinganData.updatePb(h);
		
	}
	private void hapusKehadiran(HttpSession session) throws Exception{
	    String id = getParam("id_kehadiran");	    
	    FrmHakmilikSementaraSetPerundinganData.hapusKehadiran(id);
	  }
	
	private String simpanTurutHadir(HttpSession session) throws Exception{
		
		Hashtable h = new Hashtable();
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		
		h.put("idHakmilik",getParam("id_hakmilik"));
		h.put("idSiasatan",getParam("id_siasatan"));
		h.put("namaPB",getParam("txtNamaPB"));
		h.put("kodNoPB",getParam("socKodNoPB"));
		h.put("kodJenisPB",getParam("socKodJenisPB"));
		h.put("noPB",getParam("txtNoPB"));
		h.put("alamat4", getParam("txtAlamat4"));
		h.put("alamat5", getParam("txtAlamat5"));
		h.put("alamat6", getParam("txtAlamat6"));
		h.put("poskod", getParam("txtPoskod2"));
		h.put("negeri", getParam("socNegeriTurut"));
		h.put("bandar", getParam("socBandarTurut"));
		h.put("noTelPjbt", getParam("txtNoTelPjbt"));
		h.put("noHp", getParam("txtNoHp"));
		h.put("noFaks", getParam("txtNoFaks"));
		h.put("idMasuk",user_id);
		
		return FrmHakmilikSementaraSetPerundinganData.addTurutHadir(h);
		
	}
	
	private void kemaskiniTurutHadir(HttpSession session) throws Exception{
		
		Hashtable h = new Hashtable();
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		
		h.put("idHakmilikPb",getParam("idHakmilikPb"));
		h.put("idPB",getParam("idPB"));
		h.put("namaPB",getParam("txtNamaPB"));
		h.put("kodNoPB",getParam("socKodNoPB"));
		h.put("kodJenisPB",getParam("socKodJenisPB"));
		h.put("noPB",getParam("txtNoPB"));
		h.put("alamat4", getParam("txtAlamat4"));
		h.put("alamat5", getParam("txtAlamat5"));
		h.put("alamat6", getParam("txtAlamat6"));
		h.put("poskod", getParam("txtPoskod2"));
		h.put("negeri", getParam("socNegeriTurut"));
		h.put("bandar", getParam("socBandarTurut"));
		h.put("noTelPjbt", getParam("txtNoTelPjbt"));
		h.put("noHp", getParam("txtNoHp"));
		h.put("noFaks", getParam("txtNoFaks"));
		h.put("idKemaskini",user_id);
		
		FrmHakmilikSementaraSetPerundinganData.kemaskiniTurutHadir(h);
		
	}
	private void hapusTurutHadir(HttpSession session) throws Exception{
	    String id = getParam("idTurutHadir");	    
	    FrmHakmilikSementaraSetPerundinganData.hapusTurutHadir(id);
	  }
}
