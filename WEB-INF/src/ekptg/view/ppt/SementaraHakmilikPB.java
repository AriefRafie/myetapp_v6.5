package ekptg.view.ppt;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.ppt.FrmHakmilikSementaraMaklumatHakmilikPBData;
import ekptg.model.ppt.FrmHakmilikSementaraMaklumatPermohonanData;
import ekptg.model.ppt.FrmHakmilikSementaraSenaraiData;
import ekptg.model.ppt.FrmHakmilikSementaraSenaraiHakmilikData;

public class SementaraHakmilikPB extends AjaxBasedModule{

	@Override
	public String doTemplate2() throws Exception {
		
		String vm = "";
		String action = getParam("action");
		context.put("action",action);
		String submit = getParam("command");
		String tarikhmohon = "";
    	String nofail = "";    
    	String noJKPTG = "";
    	String cStatus = "0";
    	String id_fail = getParam("id_fail");
		context.put("id_fail", getParam("id_fail"));
		String id_permohonan = getParam("id_permohonan");
		context.put("id_permohonan", getParam("id_permohonan"));
		String idHakmilik = getParam("idHakmilik");
		context.put("idHakmilik", getParam("idHakmilik"));
		String idPB = getParam("idPB");
		context.put("idPB", getParam("idPB"));
    	HttpSession session = this.request.getSession();
    	

		FrmHakmilikSementaraMaklumatPermohonanData prmhnnMaster = new FrmHakmilikSementaraMaklumatPermohonanData();
		FrmHakmilikSementaraSenaraiData listSenarai = new FrmHakmilikSementaraSenaraiData();
		FrmHakmilikSementaraSenaraiHakmilikData senaraiHakmilik = new FrmHakmilikSementaraSenaraiHakmilikData();
		FrmHakmilikSementaraMaklumatHakmilikPBData hakmilikPB = new FrmHakmilikSementaraMaklumatHakmilikPBData();
		
		Vector list = null;
		Vector listAgensi = null;
		Vector listHakmilik = null;
		
		
		if ("viewHakmilik".equals(action)){
			
			vm = "app/ppt/frmHakmilikSementaraSenaraiHakmilik.jsp";

			
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
			
			senaraiHakmilik.setSenaraiHakmilik(id_fail, id_permohonan);
			listHakmilik = senaraiHakmilik.getSenaraiHakmilik();
			context.put("SenaraiHakmilik", listHakmilik);
			setupPage(session,action,listHakmilik);
			

			
		}
		else if ("viewMaklumatHakmilik".equals(action)){
			
			vm = "app/ppt/frmHakmilikSementaraMaklumatHakmilikPB.jsp";
			
			context.put("modeHakmilik","paparHakmilik");
			context.put("actionPB","");
			context.put("readonlyHakmilik","readonly");
			context.put("disabledHakmilik","disabled");
			
			
			Vector getrecord_permohonan = prmhnnMaster.getRecord(id_permohonan);
			Hashtable h1 = (Hashtable) getrecord_permohonan.get(0);
			context.put("no_fail",h1.get("no_fail"));
			context.put("no_rujukan_upt",h1.get("no_rujukan_upt"));
			
		
			
			Vector getHakmilik = hakmilikPB.getHakmilik(idHakmilik);
			Hashtable h2 = (Hashtable)getHakmilik.get(0);
			
			String jenisHakmilik = (String)h2.get("ID_JENISHAKMILIK");
			String idNegeri = (String)h2.get("ID_NEGERI");
			String idDaerah = (String) h2.get("ID_DAERAH");
			String idMukim = (String)h2.get("ID_MUKIM");
			String idLot = (String)h2.get("ID_LOT");
			String idUnitLuasLot = (String) h2.get("ID_UNITLUASLOT");
			String idKategoriTanah = (String)h2.get("ID_KATEGORITANAH");
			String idUnitLuasSewa = (String)h2.get("ID_UNITLUAS");
			
			context.put("NO_HAKMILIK",h2.get("NO_HAKMILIK"));
		    context.put("TARIKH_DAFTAR",h2.get("TARIKH_DAFTAR"));
			context.put("NO_LOT",h2.get("NO_LOT"));  
			context.put("LUAS_LOT",h2.get("LUAS_LOT"));   
			context.put("NO_SYIT",h2.get("NO_SYIT"));  
			context.put("NO_PELAN",h2.get("NO_PELAN"));  
			context.put("SEKATAN_KEPENTINGAN",h2.get("SEKATAN_KEPENTINGAN"));  
			context.put("TARIKH_MULA",h2.get("TARIKH_MULA"));  
			context.put("TARIKH_AKHIR",h2.get("TARIKH_AKHIR"));  
			context.put("LUAS_SEWA",h2.get("LUAS_SEWA"));  
			context.put("SYARAT_NYATA",h2.get("SYARAT_NYATA"));
			context.put("SYARAT_KHAS",h2.get("SYARAT_KHAS"));
			context.put("TEMPOH_PENDUDUKAN", h2.get("TEMPOH_PENDUDUKAN"));
			context.put("UNIT_TEMPOH", h2.get("UNIT_TEMPOH"));
			context.put("SelectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri),"style=width:200px class=disabled disabled onChange=\"doChangeidNegeri();\""));
	     	context.put("SelectDaerah",HTML.SelectDaerah("socDaerah",Utils.parseLong(idDaerah),null,"style=width:200px class=disabled disabled onChange=\"doChangeidMukim();\""));
	     	context.put("SelectBandar",HTML.SelectMukim("socBandar",Utils.parseLong(idMukim),"style=width:200px class=disabled disabled"));
	    	context.put("SelectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong(jenisHakmilik),"style=width:auto class=disabled disabled class=autoselect"));
	    	context.put("SelectKategoriTanah", HTML.SelectKategori("socKategoriTanah",Utils.parseLong(idKategoriTanah),"style=width:100px class=disabled disabled"));
			context.put("SelectLuasAmbil",HTML.SelectLuas("socLuasAmbil",Utils.parseLong(idUnitLuasSewa),"style=width:90px class=disabled disabled"));
			context.put("SelectLuasLot",HTML.SelectLuas("socLuasLot",Utils.parseLong(idUnitLuasLot),"style=width:90px class=disabled disabled"));
			context.put("SelectLot", HTML.SelectLot("socLot",Utils.parseLong(idLot),"style=width:90px class=disabled disabled"));
			
			Vector getPB = hakmilikPB.getSenaraiPB(idHakmilik);
			context.put("SenaraiPB", getPB);
			setupPage(session,action,getPB);
			
		}
		
		else if ("kemaskiniHakmilik".equals(action)){
			
			vm = "app/ppt/frmHakmilikSementaraMaklumatHakmilikPB.jsp";
			context.put("modeHakmilik","kemaskiniHakmilik");
			context.put("actionPB","");
			context.put("readonlyHakmilik","");
			context.put("disabledHakmilik","");
			
			
			Vector getrecord_permohonan = prmhnnMaster.getRecord(id_permohonan);
			Hashtable h1 = (Hashtable) getrecord_permohonan.get(0);
			context.put("no_fail",h1.get("no_fail"));
			context.put("no_rujukan_upt",h1.get("no_rujukan_upt"));
						
			Vector getHakmilik = hakmilikPB.getHakmilik(idHakmilik);
			Hashtable h2 = (Hashtable)getHakmilik.get(0);
			
			String jenisHakmilik = (String)h2.get("ID_JENISHAKMILIK");
			String idNegeri = (String)h2.get("ID_NEGERI");
			String idDaerah = (String) h2.get("ID_DAERAH");
			String idMukim = (String)h2.get("ID_MUKIM");
			String idLot = (String)h2.get("ID_LOT");
			String idUnitLuasLot = (String) h2.get("ID_UNITLUASLOT");
			String idKategoriTanah = (String)h2.get("ID_KATEGORITANAH");
			String idUnitLuasSewa = (String)h2.get("ID_UNITLUAS");
			
			  
			context.put("NO_HAKMILIK",h2.get("NO_HAKMILIK"));
		    context.put("TARIKH_DAFTAR",h2.get("TARIKH_DAFTAR"));
			context.put("NO_LOT",h2.get("NO_LOT"));  
			context.put("LUAS_LOT",h2.get("LUAS_LOT"));   
			context.put("NO_SYIT",h2.get("NO_SYIT"));  
			context.put("NO_PELAN",h2.get("NO_PELAN"));  
			context.put("SEKATAN_KEPENTINGAN",h2.get("SEKATAN_KEPENTINGAN"));  
			context.put("TARIKH_MULA",h2.get("TARIKH_MULA"));  
			context.put("TARIKH_AKHIR",h2.get("TARIKH_AKHIR"));  
			context.put("LUAS_SEWA",h2.get("LUAS_SEWA"));  
			context.put("SYARAT_NYATA",h2.get("SYARAT_NYATA"));
			context.put("SYARAT_KHAS",h2.get("SYARAT_KHAS"));
			context.put("TEMPOH_PENDUDUKAN", h2.get("TEMPOH_PENDUDUKAN"));
			context.put("UNIT_TEMPOH", h2.get("UNIT_TEMPOH"));
			context.put("idNegeri", idNegeri);
			context.put("idDaerah",idDaerah);
			context.put("idBandar",idMukim);
			context.put("SelectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri),"style=width:200px class=disabled disabled onChange=\"doChangeidNegeri();\""));
	     	context.put("SelectDaerah",HTML.SelectDaerah("socDaerah",Utils.parseLong(idDaerah),null,"style=width:200px class=disabled disabled onChange=\"doChangeidMukim();\""));
	     	context.put("SelectBandar",HTML.SelectMukim("socBandar",Utils.parseLong(idMukim),"style=width:200px class=disabled disabled"));
	    	context.put("SelectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong(jenisHakmilik),"style=width:200px class=autoselect"));
	    	context.put("SelectKategoriTanah", HTML.SelectKategori("socKategoriTanah",Utils.parseLong(idKategoriTanah),"style=width:100px"));
			context.put("SelectLuasAmbil",HTML.SelectLuas("socLuasAmbil",Utils.parseLong(idUnitLuasSewa),"style=width:90px"));
			context.put("SelectLuasLot",HTML.SelectLuas("socLuasLot",Utils.parseLong(idUnitLuasLot),"style=width:90px"));
			context.put("SelectLot", HTML.SelectLot("socLot",Utils.parseLong(idLot),"style=width:90px"));
			
			Vector getPB = hakmilikPB.getSenaraiPB(idHakmilik);
			context.put("SenaraiPB", getPB);
			setupPage(session,action,getPB);
			
			
		}
		else if ("tambahHakmilik".equals(action)){
			vm = "app/ppt/frmHakmilikSementaraMaklumatHakmilikPB.jsp";
			
			context.put("modeHakmilik","newHakmilik");
			context.put("actionPB","");
			context.put("readonlyHakmilik","");
			context.put("disabledHakmilik","");
			
			Vector getrecord_permohonan = prmhnnMaster.getRecord(id_permohonan);
			Hashtable h1 = (Hashtable) getrecord_permohonan.get(0);
			context.put("no_fail",h1.get("no_fail"));
			context.put("no_rujukan_upt",h1.get("no_rujukan_upt"));
			
			String idDaerah = h1.get("id_daerah").toString();
			context.put("idNegeri", h1.get("id_negeri"));
			context.put("idDaerah",h1.get("id_daerah"));
        	context.put("SelectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(h1.get("id_negeri").toString()),"style=width:200px class=disabled disabled onChange=\"doChangeidNegeri();\""));
     		context.put("SelectDaerah",HTML.SelectDaerah("socDaerah",Utils.parseLong(h1.get("id_daerah").toString()),null,"style=width:200px class=disabled disabled onChange=\"doChangeidMukim();\""));
     		context.put("SelectBandar",HTML.SelectMukimByDaerah(idDaerah,"socBandar",null,"style=width:200px"));
    		context.put("SelectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik",null,"style=width:200px"));
    		context.put("SelectKategoriTanah", HTML.SelectKategori("socKategoriTanah",null,"style=width:100px"));
			context.put("SelectLuasAmbil",HTML.SelectLuas("socLuasAmbil",null,"style=width:90px"));
			context.put("SelectLuasLot",HTML.SelectLuas("socLuasLot",null,"style=width:90px"));
			context.put("SelectLot", HTML.SelectLot("socLot",null,"style=width:90px"));
			context.put("NO_HAKMILIK","");
			context.put("NO_LOT","");
			context.put("NO_PT","");
			context.put("LUAS_LOT","");
			context.put("LUAS_SEWA","");
			context.put("NO_SYIT","");
			context.put("NO_PELAN","");
			context.put("TARIKH_DAFTAR","");
			context.put("SYARAT_NYATA","");
			context.put("SYARAT_KHAS","");
			context.put("SEKATAN_KEPENTINGAN","");
			context.put("TEMPOH_PENDUDUKAN","");
			context.put("UNIT_TEMPOH","");
			context.put("TARIKH_MULA","");
			context.put("TARIKH_AKHIR","");
		
		}
		else if("simpanHakmilik".equals(action)){
			
			addHakmilik(session);
			vm = "app/ppt/frmHakmilikSementaraSenaraiHakmilik.jsp";
			
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
			
			senaraiHakmilik.setSenaraiHakmilik(id_fail, id_permohonan);
			listHakmilik = senaraiHakmilik.getSenaraiHakmilik();
			context.put("SenaraiHakmilik", listHakmilik);
			setupPage(session,action,listHakmilik);
			
			
		}
		else if("updateHakmilik".equals(action)){
			
			updateHakmilik(session);
			
			vm = "app/ppt/frmHakmilikSementaraMaklumatHakmilikPB.jsp";
			
			context.put("modeHakmilik","paparHakmilik");
			context.put("actionPB","");
			context.put("readonlyHakmilik","readonly");
			context.put("disabledHakmilik","disabled");
			
			
			Vector getrecord_permohonan = prmhnnMaster.getRecord(id_permohonan);
			Hashtable h1 = (Hashtable) getrecord_permohonan.get(0);
			context.put("no_fail",h1.get("no_fail"));
			context.put("no_rujukan_upt",h1.get("no_rujukan_upt"));
			
			
			Vector getHakmilik = hakmilikPB.getHakmilik(idHakmilik);
			Hashtable h2 = (Hashtable)getHakmilik.get(0);
			
			String jenisHakmilik = (String)h2.get("ID_JENISHAKMILIK");
			String idNegeri = (String)h2.get("ID_NEGERI");
			String idDaerah = (String) h2.get("ID_DAERAH");
			String idMukim = (String)h2.get("ID_MUKIM");
			String idLot = (String)h2.get("ID_LOT");
			String idUnitLuasLot = (String) h2.get("ID_UNITLUASLOT");
			String idKategoriTanah = (String)h2.get("ID_KATEGORITANAH");
			String idUnitLuasSewa = (String)h2.get("ID_UNITLUAS");
			
			  
			context.put("NO_HAKMILIK",h2.get("NO_HAKMILIK"));
		    context.put("TARIKH_DAFTAR",h2.get("TARIKH_DAFTAR"));
			context.put("NO_LOT",h2.get("NO_LOT"));  
			context.put("LUAS_LOT",h2.get("LUAS_LOT"));   
			context.put("NO_SYIT",h2.get("NO_SYIT"));  
			context.put("NO_PELAN",h2.get("NO_PELAN"));  
			context.put("SEKATAN_KEPENTINGAN",h2.get("SEKATAN_KEPENTINGAN"));  
			context.put("TARIKH_MULA",h2.get("TARIKH_MULA"));  
			context.put("TARIKH_AKHIR",h2.get("TARIKH_AKHIR"));  
			context.put("LUAS_SEWA",h2.get("LUAS_SEWA"));  
			context.put("SYARAT_NYATA",h2.get("SYARAT_NYATA")); 
			context.put("SYARAT_KHAS",h2.get("SYARAT_KHAS"));
			context.put("TEMPOH_PENDUDUKAN", h2.get("TEMPOH_PENDUDUKAN"));
			context.put("UNIT_TEMPOH", h2.get("UNIT_TEMPOH"));
			context.put("SelectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri),"style=width:200px class=disabled disabled onChange=\"doChangeidNegeri();\""));
	     	context.put("SelectDaerah",HTML.SelectDaerah("socDaerah",Utils.parseLong(idDaerah),null,"style=width:200px class=disabled disabled onChange=\"doChangeidMukim();\""));
	     	context.put("SelectBandar",HTML.SelectMukim("socBandar",Utils.parseLong(idMukim),"style=width:200px class=disabled disabled"));
	    	context.put("SelectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong(jenisHakmilik),"style=width:200px class=disabled disabled class=autoselect"));
	    	context.put("SelectKategoriTanah", HTML.SelectKategori("socKategoriTanah",Utils.parseLong(idKategoriTanah),"style=width:100px class=disabled disabled"));
			context.put("SelectLuasAmbil",HTML.SelectLuas("socLuasAmbil",Utils.parseLong(idUnitLuasSewa),"style=width:90px class=disabled disabled"));
			context.put("SelectLuasLot",HTML.SelectLuas("socLuasLot",Utils.parseLong(idUnitLuasLot),"style=width:90px class=disabled disabled"));
			context.put("SelectLot", HTML.SelectLot("socLot",Utils.parseLong(idLot),"style=width:90px class=disabled disabled"));
			

			Vector getPB = hakmilikPB.getSenaraiPB(idHakmilik);
			context.put("SenaraiPB", getPB);
			setupPage(session,action,getPB);
			
			
		}
		else if ("addPB".equals(action)){
			
			vm = "app/ppt/frmHakmilikSementaraMaklumatPB.jsp";
			
			context.put("modePB", "newPB");
			context.put("readonlyPB","");
			context.put("disabledPB","");

			
			Vector getrecord_permohonan = prmhnnMaster.getRecord(id_permohonan);
			Hashtable h1 = (Hashtable) getrecord_permohonan.get(0);
			context.put("no_fail",h1.get("no_fail"));
			context.put("no_rujukan_upt",h1.get("no_rujukan_upt"));
			
			context.put("NAMA_PB","");
			context.put("NO_PB","");
			context.put("SYER_ATAS","");
			context.put("SYER_BAWAH","");
			context.put("ALAMAT1","");
			context.put("ALAMAT2","");
			context.put("ALAMAT3","");
			context.put("POSKOD","");
			context.put("NO_TEL_RUMAH","");
			context.put("NO_HP","");
			context.put("NO_FAX","");
			
			context.put("SelectJenisNoPB",HTML.SelectJenisNoPb("socKodNoPB", null, "style=width:200px"));
			context.put("SelectJenisPB",HTML.SelectJenisPb("socKodJenisPB", null, "style=width:200px"));
			context.put("SelectBangsa",HTML.SelectBangsa("socBangsa", null, "style=width:200px"));
			context.put("SelectWarganegara", HTML.SelectWarganegara("socWarganegara", null, "style=width:200px"));
			context.put("SelectNegeri",HTML.SelectNegeri("socNegeri",null,"style=width:200px onChange=\"doChangeidNegeriPB();\""));
	     	context.put("SelectBandar",HTML.SelectBandar("socBandar",null,"style=width:200px"));
		}
		else if("simpanPB".equals(action)){
			
			addPB(session);
			vm = "app/ppt/frmHakmilikSementaraMaklumatHakmilikPB.jsp";
			
			context.put("modeHakmilik","paparHakmilik");
			context.put("actionPB","");
			context.put("readonlyHakmilik","readonly");
			context.put("disabledHakmilik","disabled");
			
			Vector getrecord_permohonan = prmhnnMaster.getRecord(id_permohonan);
			Hashtable h1 = (Hashtable) getrecord_permohonan.get(0);
			context.put("no_fail",h1.get("no_fail"));
			context.put("no_rujukan_upt",h1.get("no_rujukan_upt"));
		
			Vector getHakmilik = hakmilikPB.getHakmilik(idHakmilik);
			Hashtable h2 = (Hashtable)getHakmilik.get(0);
			
			String jenisHakmilik = (String)h2.get("ID_JENISHAKMILIK");
			String idNegeri = (String)h2.get("ID_NEGERI");
			String idDaerah = (String) h2.get("ID_DAERAH");
			String idMukim = (String)h2.get("ID_MUKIM");
			String idLot = (String)h2.get("ID_LOT");
			String idUnitLuasLot = (String) h2.get("ID_UNITLUASLOT");
			String idKategoriTanah = (String)h2.get("ID_KATEGORITANAH");
			String idUnitLuasSewa = (String)h2.get("ID_UNITLUAS");
			
			  
			context.put("NO_HAKMILIK",h2.get("NO_HAKMILIK"));
		    context.put("TARIKH_DAFTAR",h2.get("TARIKH_DAFTAR"));
			context.put("NO_LOT",h2.get("NO_LOT"));  
			context.put("LUAS_LOT",h2.get("LUAS_LOT"));   
			context.put("NO_SYIT",h2.get("NO_SYIT"));  
			context.put("NO_PELAN",h2.get("NO_PELAN"));  
			context.put("SEKATAN_KEPENTINGAN",h2.get("SEKATAN_KEPENTINGAN"));  
			context.put("TARIKH_MULA",h2.get("TARIKH_MULA"));  
			context.put("TARIKH_AKHIR",h2.get("TARIKH_AKHIR"));  
			context.put("LUAS_SEWA",h2.get("LUAS_SEWA"));  
			context.put("SYARAT_NYATA",h2.get("SYARAT_NYATA"));  
			context.put("SYARAT_KHAS",h2.get("SYARAT_KHAS"));
			context.put("TEMPOH_PENDUDUKAN", h2.get("TEMPOH_PENDUDUKAN"));
			context.put("UNIT_TEMPOH", h2.get("UNIT_TEMPOH"));
			context.put("SelectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri),"style=width:200px class=disabled disabled onChange=\"doChangeidNegeri();\""));
	     	context.put("SelectDaerah",HTML.SelectDaerah("socDaerah",Utils.parseLong(idDaerah),null,"style=width:200px class=disabled disabled onChange=\"doChangeidMukim();\""));
	     	context.put("SelectBandar",HTML.SelectMukim("socBandar",Utils.parseLong(idMukim),"style=width:200px class=disabled disabled"));
	    	context.put("SelectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong(jenisHakmilik),"style=width:200px class=disabled disabled class=autoselect"));
	    	context.put("SelectKategoriTanah", HTML.SelectKategori("socKategoriTanah",Utils.parseLong(idKategoriTanah),"style=width:100px class=disabled disabled"));
			context.put("SelectLuasAmbil",HTML.SelectLuas("socLuasAmbil",Utils.parseLong(idUnitLuasSewa),"style=width:90px class=disabled disabled"));
			context.put("SelectLuasLot",HTML.SelectLuas("socLuasLot",Utils.parseLong(idUnitLuasLot),"style=width:90px class=disabled disabled"));
			context.put("SelectLot", HTML.SelectLot("socLot",Utils.parseLong(idLot),"style=width:90px class=disabled disabled"));
			

			Vector getPB = hakmilikPB.getSenaraiPB(idHakmilik);
			context.put("SenaraiPB", getPB);
			setupPage(session,action,getPB);
			
			
		}
		else if ("viewMaklumatPB".equals(action)){
			vm = "app/ppt/frmHakmilikSementaraMaklumatPB.jsp";
			
			context.put("modePB", "paparPB");
			context.put("readonlyPB","readonly");
			context.put("disabledPB","disabled");
			
			Vector getrecord_permohonan = prmhnnMaster.getRecord(id_permohonan);
			Hashtable h1 = (Hashtable) getrecord_permohonan.get(0);
			context.put("no_fail",h1.get("no_fail"));
			context.put("no_rujukan_upt",h1.get("no_rujukan_upt"));
			
			Vector getPB = hakmilikPB.getPihakBerkepentingan(idHakmilik, idPB);
			Hashtable h2 = (Hashtable)getPB.get(0);
			
			context.put("NAMA_PB",h2.get("NAMA_PB"));
			context.put("NO_PB",h2.get("NO_PB"));
			context.put("SYER_ATAS",h2.get("SYER_ATAS"));
			context.put("SYER_BAWAH",h2.get("SYER_BAWAH"));
			context.put("ALAMAT1",h2.get("ALAMAT1"));
			context.put("ALAMAT2",h2.get("ALAMAT2"));
			context.put("ALAMAT3",h2.get("ALAMAT3"));
			context.put("POSKOD",h2.get("POSKOD"));
			context.put("NO_TEL_RUMAH",h2.get("NO_TEL_RUMAH"));
			context.put("NO_HP",h2.get("NO_HP"));
			context.put("NO_FAX",h2.get("NO_FAX"));
			
			context.put("SelectJenisNoPB",HTML.SelectJenisNoPb("socKodNoPB", Utils.parseLong(h2.get("ID_JENISNOPB").toString()), "style=width:200px class=disabled disabled"));
			context.put("SelectJenisPB",HTML.SelectJenisPb("socKodJenisPB", Utils.parseLong(h2.get("ID_JENISPB").toString()), "style=width:200px class=disabled disabled"));
			context.put("SelectBangsa",HTML.SelectBangsa("socBangsa", Utils.parseLong(h2.get("ID_BANGSA").toString()), "style=width:200px class=disabled disabled"));
			context.put("SelectWarganegara", HTML.SelectWarganegara("socWarganegara", Utils.parseLong(h2.get("ID_WARGANEGARA").toString()), "style=width:200px class=disabled disabled"));
			context.put("SelectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(h2.get("ID_NEGERI").toString()),"style=width:200px class=disabled disabled onChange=\"doChangeidNegeriPB();\""));
	     	context.put("SelectBandar",HTML.SelectBandar("socBandar",Utils.parseLong(h2.get("ID_BANDAR").toString()),"style=width:200px class=disabled disabled"));
		}
		else if ("kemaskiniPB".equals(action)){
			
			vm = "app/ppt/frmHakmilikSementaraMaklumatPB.jsp";
			
			context.put("modePB", "kemaskiniPB");
			context.put("readonlyPB","");
			context.put("disabledPB","");

			
			Vector getrecord_permohonan = prmhnnMaster.getRecord(id_permohonan);
			Hashtable h1 = (Hashtable) getrecord_permohonan.get(0);
			context.put("no_fail",h1.get("no_fail"));
			context.put("no_rujukan_upt",h1.get("no_rujukan_upt"));
		
			
			Vector getPB = hakmilikPB.getPihakBerkepentingan(idHakmilik, idPB);
			Hashtable h2 = (Hashtable)getPB.get(0);
			
			String idNegeri = h2.get("ID_NEGERI").toString();
			context.put("NAMA_PB",h2.get("NAMA_PB"));
			context.put("NO_PB",h2.get("NO_PB"));
			context.put("SYER_ATAS",h2.get("SYER_ATAS"));
			context.put("SYER_BAWAH",h2.get("SYER_BAWAH"));
			context.put("ALAMAT1",h2.get("ALAMAT1"));
			context.put("ALAMAT2",h2.get("ALAMAT2"));
			context.put("ALAMAT3",h2.get("ALAMAT3"));
			context.put("POSKOD",h2.get("POSKOD"));
			context.put("NO_TEL_RUMAH",h2.get("NO_TEL_RUMAH"));
			context.put("NO_HP",h2.get("NO_HP"));
			context.put("NO_FAX",h2.get("NO_FAX"));
			
			context.put("SelectJenisNoPB",HTML.SelectJenisNoPb("socKodNoPB", Utils.parseLong(h2.get("ID_JENISNOPB").toString()), "style=width:200px"));
			context.put("SelectJenisPB",HTML.SelectJenisPb("socKodJenisPB", Utils.parseLong(h2.get("ID_JENISPB").toString()), "style=width:200px"));
			context.put("SelectBangsa",HTML.SelectBangsa("socBangsa", Utils.parseLong(h2.get("ID_BANGSA").toString()), "style=width:200px"));
			context.put("SelectWarganegara", HTML.SelectWarganegara("socWarganegara", Utils.parseLong(h2.get("ID_WARGANEGARA").toString()), "style=width:200px"));
			context.put("SelectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(h2.get("ID_NEGERI").toString()),"style=width:200px onChange=\"doChangeidNegeriUpdatePB();\""));
	     	context.put("SelectBandar",HTML.SelectBandarByNegeri(idNegeri,"socBandar",Utils.parseLong(h2.get("ID_BANDAR").toString()),"style=width:200px"));
		}
		else if ("updatePB".equals(action)){
			
			updatePB(session);
			
			vm = "app/ppt/frmHakmilikSementaraMaklumatPB.jsp";
			
			context.put("modePB", "paparPB");
			context.put("readonlyPB","readonly");
			context.put("disabledPB","disabled");
			
			Vector getrecord_permohonan = prmhnnMaster.getRecord(id_permohonan);
			Hashtable h1 = (Hashtable) getrecord_permohonan.get(0);
			context.put("no_fail",h1.get("no_fail"));
			context.put("no_rujukan_upt",h1.get("no_rujukan_upt"));
			
			
			
			Vector getPB = hakmilikPB.getPihakBerkepentingan(idHakmilik, idPB);
			Hashtable h2 = (Hashtable)getPB.get(0);
			
			context.put("NAMA_PB",h2.get("NAMA_PB"));
			context.put("NO_PB",h2.get("NO_PB"));
			context.put("SYER_ATAS",h2.get("SYER_ATAS"));
			context.put("SYER_BAWAH",h2.get("SYER_BAWAH"));
			context.put("ALAMAT1",h2.get("ALAMAT1"));
			context.put("ALAMAT2",h2.get("ALAMAT2"));
			context.put("ALAMAT3",h2.get("ALAMAT3"));
			context.put("POSKOD",h2.get("POSKOD"));
			context.put("NO_TEL_RUMAH",h2.get("NO_TEL_RUMAH"));
			context.put("NO_HP",h2.get("NO_HP"));
			context.put("NO_FAX",h2.get("NO_FAX"));
			
			context.put("SelectJenisNoPB",HTML.SelectJenisNoPb("socKodNoPB", Utils.parseLong(h2.get("ID_JENISNOPB").toString()), "style=width:200px class=disabled disabled"));
			context.put("SelectJenisPB",HTML.SelectJenisPb("socKodJenisPB", Utils.parseLong(h2.get("ID_JENISPB").toString()), "style=width:200px class=disabled disabled"));
			context.put("SelectBangsa",HTML.SelectBangsa("socBangsa", Utils.parseLong(h2.get("ID_BANGSA").toString()), "style=width:200px class=disabled disabled"));
			context.put("SelectWarganegara", HTML.SelectWarganegara("socWarganegara", Utils.parseLong(h2.get("ID_WARGANEGARA").toString()), "style=width:200px class=disabled disabled"));
			context.put("SelectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(h2.get("ID_NEGERI").toString()),"style=width:200px class=disabled disabled onChange=\"doChangeidNegeriPB();\""));
	     	context.put("SelectBandar",HTML.SelectBandar("socBandar",Utils.parseLong(h2.get("ID_BANDAR").toString()),"style=width:200px class=disabled disabled"));
		}
		else if ("doChangeidNegeri".equals(submit)) {
			vm = "app/ppt/frmHakmilikSementaraMaklumatHakmilikPB.jsp";
			
			context.put("modeHakmilik","newHakmilik");
			context.put("modePB","");
			context.put("readonlyHakmilik","");
			context.put("disabledHakmilik","");
		
			
			Vector getrecord_permohonan = prmhnnMaster.getRecord(id_permohonan);
			Hashtable h1 = (Hashtable) getrecord_permohonan.get(0);
			context.put("no_fail",h1.get("no_fail"));
			context.put("no_rujukan_upt",h1.get("no_rujukan_upt"));
			
			String idBandar = getParam("socBandar");
    		String idNegeri = getParam("socNegeri");
    		String idJenisHakmilik = getParam("socJenisHakmilik");
    		String idKategoriTanah = getParam("socKategoriTanah");
    		String idLuasAmbil = getParam("socLuasAmbil");
    		String idLuasLot = getParam("socLuasLot");
    		String idLot = getParam("socLot");
    		String idPT = getParam("socPT");
    		
    		context.put("SelectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri),"style=width:300px onChange=\"doChangeidNegeri();\""));
     		context.put("SelectDaerah",HTML.SelectDaerahByNegeri(idNegeri,"socDaerah",null,null,"style=width:300px onChange=\"doChangeidMukim();\""));
     		context.put("SelectBandar",HTML.SelectMukim("socBandar",Utils.parseLong(idBandar),"style=width:300px"));
    		context.put("SelectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong(idJenisHakmilik),"style=width:200px class=autoselect"));
    		context.put("SelectKategoriTanah", HTML.SelectKategori("socKategoriTanah",Utils.parseLong(idKategoriTanah),"style=width:100px"));
			context.put("SelectLuasAmbil",HTML.SelectLuas("socLuasAmbil",Utils.parseLong(idLuasAmbil),"style=width:90px"));
			context.put("SelectLuasLot",HTML.SelectLuas("socLuasLot",Utils.parseLong(idLuasLot),"style=width:90px"));
			context.put("SelectLot", HTML.SelectLot("socLot",Utils.parseLong(idLot)," style=width:90px"));
			context.put("SelectPT", HTML.SelectLot("socPT",Utils.parseLong(idPT),"class=disabled disabled style=width:90px"));
			context.put("NO_HAKMILIK",getParam("txtNoHakmilik"));
			context.put("NO_LOT",getParam("txtNoLot"));
			context.put("NO_PT",getParam("txtNoPT"));
			context.put("LUAS_LOT",getParam("txtLuasLot"));
			context.put("LUAS_SEWA",getParam("txtLuasAmbil"));
			context.put("NO_SYIT",getParam("txtNoLembaranPiawai"));
			context.put("NO_PELAN",getParam("txtNoPA"));
			context.put("TARIKH_DAFTAR",getParam("txdTkhdaftar"));
			context.put("SYARAT_NYATA",getParam("txtSyaratNyata"));
			context.put("SYARAT_KHAS",getParam("txtSyaratKhas"));
			context.put("SEKATAN_KEPENTINGAN",getParam("txtSekatanKepentingan"));
			context.put("TEMPOH_PENDUDUKAN",getParam("txtTempoh"));
			context.put("TARIKH_MULA",getParam("txdTkhMula"));
			context.put("TARIKH_AKHIR",getParam("txdTkhAkhir"));
			



		 }
		else if ("doChangeidMukim".equals(submit)) {
			
			vm = "app/ppt/frmHakmilikSementaraMaklumatHakmilikPB.jsp";
			
			context.put("modeHakmilik","newHakmilik");
			context.put("modePB","");
			context.put("readonlyHakmilik","");
			context.put("disabledHakmilik","");
			
			
			Vector getrecord_permohonan = prmhnnMaster.getRecord(id_permohonan);
			Hashtable h1 = (Hashtable) getrecord_permohonan.get(0);
			context.put("no_fail",h1.get("no_fail"));
			context.put("no_rujukan_upt",h1.get("no_rujukan_upt"));
			
			String idBandar = getParam("socBandar");
			String idDaerah = getParam("socDaerah");
    		String idNegeri = getParam("socNegeri");
    		String idJenisHakmilik = getParam("socJenisHakmilik");
    		String idKategoriTanah = getParam("socKategoriTanah");
    		String idLuasAmbil = getParam("socLuasAmbil");
    		String idLuasLot = getParam("socLuasLot");
    		String idLot = getParam("socLot");
    		String idPT = getParam("socPT");
    		
    		context.put("SelectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri),"style=width:200px onChange=\"doChangeidNegeri();\""));
     		context.put("SelectDaerah",HTML.SelectDaerahByNegeri(idNegeri,"socDaerah",Utils.parseLong(idDaerah),null,"style=width:200px onChange=\"doChangeidMukim();\""));
     		context.put("SelectBandar",HTML.SelectMukimByDaerah(idDaerah,"socBandar",null,"style=width:200px"));
    		context.put("SelectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong(idJenisHakmilik),"style=width:200px class=autoselect"));
    		context.put("SelectKategoriTanah", HTML.SelectKategori("socKategoriTanah",Utils.parseLong(idKategoriTanah),"style=width:100px"));
			context.put("SelectLuasAmbil",HTML.SelectLuas("socLuasAmbil",Utils.parseLong(idLuasAmbil),"style=width:90px"));
			context.put("SelectLuasLot",HTML.SelectLuas("socLuasLot",Utils.parseLong(idLuasLot),"style=width:90px"));
			context.put("SelectLot", HTML.SelectLot("socLot",Utils.parseLong(idLot)," style=width:90px"));
			context.put("SelectPT", HTML.SelectLot("socPT",Utils.parseLong(idPT),"class=disabled disabled style=width:90px"));
			context.put("NO_HAKMILIK",getParam("txtNoHakmilik"));
			context.put("NO_LOT",getParam("txtNoLot"));
			context.put("NO_PT",getParam("txtNoPT"));
			context.put("LUAS_LOT",getParam("txtLuasLot"));
			context.put("LUAS_SEWA",getParam("txtLuasAmbil"));
			context.put("NO_SYIT",getParam("txtNoLembaranPiawai"));
			context.put("NO_PELAN",getParam("txtNoPA"));
			context.put("TARIKH_DAFTAR",getParam("txdTkhdaftar"));
			context.put("SYARAT_NYATA",getParam("txtSyaratNyata"));
			context.put("SYARAT_KHAS",getParam("txtSyaratKhas"));
			context.put("SEKATAN_KEPENTINGAN",getParam("txtSekatanKepentingan"));
			context.put("TEMPOH_PENDUDUKAN",getParam("txtTempoh"));
			context.put("TARIKH_MULA",getParam("txdTkhMula"));
			context.put("TARIKH_AKHIR",getParam("txdTkhAkhir"));
		}
		else if ("deleteMaklumatTanah".equals(action)) {
  	      
    		deleteMaklumatTanah(session);
  	      	
    		vm = "app/ppt/frmHakmilikSementaraSenaraiHakmilik.jsp";
			
			
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
			
			senaraiHakmilik.setSenaraiHakmilik(id_fail, id_permohonan);
			listHakmilik = senaraiHakmilik.getSenaraiHakmilik();
			context.put("SenaraiHakmilik", listHakmilik);
			setupPage(session,action,listHakmilik);
			
		}
		else if ("deleteMaklumatPB".equals(action)) {
			deleteMaklumatPB(session);
			
			vm = "app/ppt/frmHakmilikSementaraMaklumatHakmilikPB.jsp";
			
			context.put("modeHakmilik","paparHakmilik");
			context.put("actionPB","");
			context.put("readonlyHakmilik","readonly");
			context.put("disabledHakmilik","disabled");
			
			
			Vector getrecord_permohonan = prmhnnMaster.getRecord(id_permohonan);
			Hashtable h1 = (Hashtable) getrecord_permohonan.get(0);
			context.put("no_fail",h1.get("no_fail"));
			context.put("no_rujukan_upt",h1.get("no_rujukan_upt"));
			
			Vector getHakmilik = hakmilikPB.getHakmilik(idHakmilik);
			Hashtable h2 = (Hashtable)getHakmilik.get(0);
			
			String jenisHakmilik = (String)h2.get("ID_JENISHAKMILIK");
			String idNegeri = (String)h2.get("ID_NEGERI");
			String idDaerah = (String) h2.get("ID_DAERAH");
			String idMukim = (String)h2.get("ID_MUKIM");
			String idLot = (String)h2.get("ID_LOT");
			String idUnitLuasLot = (String) h2.get("ID_UNITLUASLOT");
			String idKategoriTanah = (String)h2.get("ID_KATEGORITANAH");
			String idUnitLuasSewa = (String)h2.get("ID_UNITLUAS");
			
			  
			context.put("NO_HAKMILIK",h2.get("NO_HAKMILIK"));
		    context.put("TARIKH_DAFTAR",h2.get("TARIKH_DAFTAR"));
			context.put("NO_LOT",h2.get("NO_LOT"));  
			context.put("LUAS_LOT",h2.get("LUAS_LOT"));   
			context.put("NO_SYIT",h2.get("NO_SYIT"));  
			context.put("NO_PELAN",h2.get("NO_PELAN"));  
			context.put("SEKATAN_KEPENTINGAN",h2.get("SEKATAN_KEPENTINGAN"));  
			context.put("TARIKH_MULA",h2.get("TARIKH_MULA"));  
			context.put("TARIKH_AKHIR",h2.get("TARIKH_AKHIR"));  
			context.put("LUAS_SEWA",h2.get("LUAS_SEWA"));  
			context.put("SYARAT_NYATA",h2.get("SYARAT_NYATA"));  
			context.put("SYARAT_KHAS",h2.get("SYARAT_KHAS")); 
			context.put("TEMPOH_PENDUDUKAN", h2.get("TEMPOH_PENDUDUKAN"));
			context.put("UNIT_TEMPOH", h2.get("UNIT_TEMPOH"));
			context.put("SelectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri),"style=width:200px tabindex=4 class=disabled disabled onChange=\"doChangeidNegeri();\""));
	     	context.put("SelectDaerah",HTML.SelectDaerah("socDaerah",Utils.parseLong(idDaerah),null,"style=width:200px tabindex=5 class=disabled disabled onChange=\"doChangeidMukim();\""));
	     	context.put("SelectBandar",HTML.SelectMukim("socBandar",Utils.parseLong(idMukim),"style=width:200px tabindex=6 class=disabled disabled"));
	    	context.put("SelectJenisHakmilik",HTML.SelectJenisHakmilik("socJenisHakmilik",Utils.parseLong(jenisHakmilik),"style=width:200px class=disabled disabled class=autoselect tabindex=1"));
	    	context.put("SelectKategoriTanah", HTML.SelectKategori("socKategoriTanah",Utils.parseLong(idKategoriTanah),"style=width:100px tabindex=12 class=disabled disabled"));
			context.put("SelectLuasAmbil",HTML.SelectLuas("socLuasAmbil",Utils.parseLong(idUnitLuasSewa),"style=width:90px class=disabled disabled"));
			context.put("SelectLuasLot",HTML.SelectLuas("socLuasLot",Utils.parseLong(idUnitLuasLot),"style=width:90px tabindex=11 class=disabled disabled"));
			context.put("SelectLot", HTML.SelectLot("socLot",Utils.parseLong(idLot),"style=width:90px tabindex=8 class=disabled disabled"));
			

			Vector getPB = hakmilikPB.getSenaraiPB(idHakmilik);
			context.put("SenaraiPB", getPB);
			setupPage(session,action,getPB);
			
		}
		else if ("doChangeidNegeriPB".equals(submit)) {
			
			vm = "app/ppt/frmHakmilikSementaraMaklumatPB.jsp";
			context.put("modePB", "newPB");
			context.put("readonlyPB","");
			context.put("disabledPB","");

			
			Vector getrecord_permohonan = prmhnnMaster.getRecord(id_permohonan);
			Hashtable h1 = (Hashtable) getrecord_permohonan.get(0);
			context.put("no_fail",h1.get("no_fail"));
			context.put("no_rujukan_upt",h1.get("no_rujukan_upt"));
			
			String idNegeri = getParam("socNegeri");
			String jenisPB = getParam("socKodJenisPB");
			String jenisNoPB = getParam("socKodNoPB");
			String bangsa = getParam("socBangsa");
			String warganegara = getParam("socWarganegara");
			
			context.put("NAMA_PB",getParam("txtNama"));
			context.put("NO_PB",getParam("txtNoPB"));
			context.put("SYER_ATAS",getParam("txtBahagianAtas"));
			context.put("SYER_BAWAH",getParam("txtBahagianBwh"));
			context.put("ALAMAT1",getParam("txtAlamat1"));
			context.put("ALAMAT2",getParam("txtAlamat2"));
			context.put("ALAMAT3",getParam("txtAlamat3"));
			context.put("POSKOD",getParam("txtPoskod"));
			context.put("NO_TEL_RUMAH",getParam("txtNoTel"));
			context.put("NO_HP",getParam("txtNoTelBimbit"));
			context.put("NO_FAX",getParam("txtNoFaks"));
			
			context.put("SelectJenisNoPB",HTML.SelectJenisNoPb("socKodNoPB", Utils.parseLong(jenisNoPB), "style=width:200px"));
			context.put("SelectJenisPB",HTML.SelectJenisPb("socKodJenisPB", Utils.parseLong(jenisPB), "style=width:200px"));
			context.put("SelectBangsa",HTML.SelectBangsa("socBangsa", Utils.parseLong(bangsa), "style=width:200px"));
			context.put("SelectWarganegara", HTML.SelectWarganegara("socWarganegara", Utils.parseLong(warganegara), "style=width:200px"));
			context.put("SelectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri),"style=width:200px onChange=\"doChangeidNegeriPB();\""));
     		context.put("SelectBandar",HTML.SelectBandarByNegeri(idNegeri,"socBandar",null,"style=width:200px"));

			
		}
		else if ("doChangeidNegeriUpdatePB".equals(submit)) {
			
			vm = "app/ppt/frmHakmilikSementaraMaklumatPB.jsp";
			
			context.put("modePB", "kemaskiniPB");
			context.put("readonlyPB","");
			context.put("disabledPB","");
			
			
			Vector getrecord_permohonan = prmhnnMaster.getRecord(id_permohonan);
			Hashtable h1 = (Hashtable) getrecord_permohonan.get(0);
			context.put("no_fail",h1.get("no_fail"));
			context.put("no_rujukan_upt",h1.get("no_rujukan_upt"));
			
		
			String idNegeri = getParam("socNegeri");
			
			Vector getPB = hakmilikPB.getPihakBerkepentingan(idHakmilik, idPB);
			Hashtable h2 = (Hashtable)getPB.get(0);
			
			context.put("NAMA_PB",h2.get("NAMA_PB"));
			context.put("NO_PB",h2.get("NO_PB"));
			context.put("SYER_ATAS",h2.get("SYER_ATAS"));
			context.put("SYER_BAWAH",h2.get("SYER_BAWAH"));
			context.put("ALAMAT1",h2.get("ALAMAT1"));
			context.put("ALAMAT2",h2.get("ALAMAT2"));
			context.put("ALAMAT3",h2.get("ALAMAT3"));
			context.put("POSKOD",h2.get("POSKOD"));
			context.put("NO_TEL_RUMAH",h2.get("NO_TEL_RUMAH"));
			context.put("NO_HP",h2.get("NO_HP"));
			context.put("NO_FAX",h2.get("NO_FAX"));
			
			context.put("SelectJenisNoPB",HTML.SelectJenisNoPb("socKodNoPB", Utils.parseLong(h2.get("ID_JENISNOPB").toString()), "style=width:200px"));
			context.put("SelectJenisPB",HTML.SelectJenisPb("socKodJenisPB", Utils.parseLong(h2.get("ID_JENISPB").toString()), "style=width:200px"));
			context.put("SelectBangsa",HTML.SelectBangsa("socBangsa", Utils.parseLong(h2.get("ID_BANGSA").toString()), "style=width:200px"));
			context.put("SelectWarganegara", HTML.SelectWarganegara("socWarganegara", Utils.parseLong(h2.get("ID_WARGANEGARA").toString()), "style=width:200px"));
			context.put("SelectNegeri",HTML.SelectNegeri("socNegeri",Utils.parseLong(idNegeri),"style=width:200px onChange=\"doChangeidNegeriUpdatePB();\""));
     		context.put("SelectBandar",HTML.SelectBandarByNegeri(idNegeri,"socBandar",null,"style=width:200px"));

		}
		else{
			vm = "app/ppt/frmHakmilikSementaraSenarai.jsp";
			
			if (!"".equals(getParam("txdTarikh")));
				tarikhmohon = getParam("txdTarikh");
			
			if (!"".equals(getParam("txtNoFail")));
				nofail = getParam("txtNoFail");
				
			if(!"".equals(getParam("socStatus")))
				cStatus = getParam("socStatus");
				
			listSenarai.setCarianFail(nofail, tarikhmohon, cStatus);		
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
	
	private void addHakmilik(HttpSession session) throws Exception{
		
	    Hashtable h = new Hashtable();
	    
		String user_id = (String)session.getAttribute("_ekptg_user_id");
    	h.put("id_permohonan", getParam("id_permohonan"));
	    h.put("idJenisHakmilik",getParam("socJenisHakmilik"));
	    h.put("noHakmilik",getParam("txtNoHakmilik"));
	    h.put("tkhDaftar",getParam("txdTkhdaftar"));
	    h.put("idNegeri",getParam("idNegeri"));
	    h.put("idDaerah",getParam("idDaerah"));
	    h.put("idMukim",getParam("socBandar"));
	    h.put("syaratNyata",getParam("txtSyaratNyata"));
	    h.put("syaratKhas",getParam("txtSyaratKhas"));
	    h.put("idLot",getParam("socLot"));
	    h.put("noLot",getParam("txtNoLot"));
	    h.put("idPT",getParam("socPT"));
	    h.put("noPT",getParam("txtNoPT"));
	    h.put("luasLot",getParam("txtLuasLot"));
	    h.put("idLuasLot",getParam("socLuasLot"));
	    h.put("idKategoriTanah",getParam("socKategoriTanah"));
	    h.put("noPiawai",getParam("txtNoLembaranPiawai"));
	    h.put("noPA",getParam("txtNoPA"));
	    h.put("luasSewa",getParam("txtLuasAmbil"));
	    h.put("idLuasSewa",getParam("socLuasAmbil"));
	    h.put("sekatan",getParam("txtSekatanKepentingan"));
	    h.put("tempoh",getParam("txtTempoh"));
	    h.put("unitTempoh",getParam("socUnitTempoh"));
	    h.put("tkhMula",getParam("txdTkhMula"));
	    h.put("tkhAkhir",getParam("txdTkhAkhir"));
 	    h.put("id_Masuk",user_id);

	    
	    FrmHakmilikSementaraMaklumatHakmilikPBData.addHakmilik(h);
	    
	}
	private void updateHakmilik(HttpSession session) throws Exception{
		
	    Hashtable h = new Hashtable();
	    
	    String idHakmilik = getParam("idHakmilik");
	    
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		h.put("idHakmilik",idHakmilik);
    	h.put("id_permohonan", getParam("id_permohonan"));
	    h.put("idJenisHakmilik",getParam("socJenisHakmilik"));
	    h.put("noHakmilik",getParam("txtNoHakmilik"));
	    h.put("tkhDaftar",getParam("txdTkhdaftar"));
	    h.put("idNegeri",getParam("idNegeri"));
	    h.put("idDaerah",getParam("idDaerah"));
	    h.put("idMukim",getParam("idBandar"));
	    h.put("syaratNyata",getParam("txtSyaratNyata"));
	    h.put("syaratKhas",getParam("txtSyaratKhas"));
	    h.put("idLot",getParam("socLot"));
	    h.put("noLot",getParam("txtNoLot"));
	    h.put("idPT",getParam("socPT"));
	    h.put("noPT",getParam("txtNoPT"));
	    h.put("luasLot",getParam("txtLuasLot"));
	    h.put("idLuasLot",getParam("socLuasLot"));
	    h.put("idKategoriTanah",getParam("socKategoriTanah"));
	    h.put("noPiawai",getParam("txtNoLembaranPiawai"));
	    h.put("noPA",getParam("txtNoPA"));
	    h.put("luasSewa",getParam("txtLuasAmbil"));
	    h.put("idLuasSewa",getParam("socLuasAmbil"));
	    h.put("sekatan",getParam("txtSekatanKepentingan"));
	    h.put("tempoh",getParam("txtTempoh"));
	    h.put("unitTempoh",getParam("socUnitTempoh"));
	    h.put("tkhMula",getParam("txdTkhMula"));
	    h.put("tkhAkhir",getParam("txdTkhAkhir"));
 	    h.put("id_Kemaskini",user_id);

	    
	    FrmHakmilikSementaraMaklumatHakmilikPBData.updateHakmilik(h);
	    
	}
	private void deleteMaklumatTanah(HttpSession session) throws Exception{
	    String id = getParam("idHakmilik");	    
	    FrmHakmilikSementaraMaklumatHakmilikPBData.deleteMaklumatTanah(id);
	  }
	private void addPB(HttpSession session) throws Exception{
		
		Hashtable h = new Hashtable();
	    
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String idHakmilik = getParam("idHakmilik");
		h.put("idHakmilik",idHakmilik);
		h.put("namaPB",getParam("txtNama"));
		h.put("kodJenisPB",getParam("socKodJenisPB"));
		h.put("kodNoPB",getParam("socKodNoPB"));
		h.put("noPB",getParam("txtNoPB"));
		h.put("bangsa",getParam("socBangsa"));
		h.put("warganegara",getParam("socWarganegara"));
		h.put("bhgnAtas",getParam("txtBahagianAtas"));
		h.put("bhgnBwh",getParam("txtBahagianBwh"));
		h.put("alamat1",getParam("txtAlamat1"));
		h.put("alamat2",getParam("txtAlamat2"));
		h.put("alamat3",getParam("txtAlamat3"));
		h.put("poskod",getParam("txtPoskod"));
		h.put("negeri",getParam("socNegeri"));
		h.put("bandar",getParam("socBandar"));
		h.put("noTel",getParam("txtNoTel"));
		h.put("noHP", getParam("txtNoTelBimbit"));
		h.put("noFaks",getParam("txtNoFaks"));
		h.put("id_Masuk",user_id);

		
		 FrmHakmilikSementaraMaklumatHakmilikPBData.addPB(h);
	}
	private void updatePB(HttpSession session) throws Exception{
		
		Hashtable h = new Hashtable();
	    
		String user_id = (String)session.getAttribute("_ekptg_user_id");
		String idPB = getParam("idPB");
		h.put("idPB",idPB);
		h.put("namaPB",getParam("txtNama"));
		h.put("kodJenisPB",getParam("socKodJenisPB"));
		h.put("kodNoPB",getParam("socKodNoPB"));
		h.put("noPB",getParam("txtNoPB"));
		h.put("bangsa",getParam("socBangsa"));
		h.put("warganegara",getParam("socWarganegara"));
		h.put("bhgnAtas",getParam("txtBahagianAtas"));
		h.put("bhgnBwh",getParam("txtBahagianBwh"));
		h.put("alamat1",getParam("txtAlamat1"));
		h.put("alamat2",getParam("txtAlamat2"));
		h.put("alamat3",getParam("txtAlamat3"));
		h.put("poskod",getParam("txtPoskod"));
		h.put("negeri",getParam("socNegeri"));
		h.put("bandar",getParam("socBandar"));
		h.put("noTel",getParam("txtNoTel"));
		h.put("noHP", getParam("txtNoTelBimbit"));
		h.put("noFaks",getParam("txtNoFaks"));
		h.put("id_Kemaskini",user_id);

		
		 FrmHakmilikSementaraMaklumatHakmilikPBData.updatePB(h);
	}
	
	private void deleteMaklumatPB(HttpSession session) throws Exception{
	    String idPB = getParam("idPB");
	    FrmHakmilikSementaraMaklumatHakmilikPBData.deleteMaklumatPB(idPB);
	  }
}
