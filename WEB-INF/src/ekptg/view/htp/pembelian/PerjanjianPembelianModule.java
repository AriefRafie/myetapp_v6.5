package ekptg.view.htp.pembelian;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.action.AjaxModule;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.helpers.Paging;
import ekptg.helpers.Utils;
import ekptg.model.htp.FrmSemakan;
import ekptg.model.htp.HakmilikUrusan;
import ekptg.model.htp.entity.DrafPerjanjian;
import ekptg.model.htp.entity.HtpPerjanjian;
import ekptg.model.htp.entity.HtpPermohonan;
import ekptg.model.htp.entity.Peguam;
import ekptg.model.htp.entity.PerjanjianPembelian;
import ekptg.model.htp.entity.PerjanjianPindahMilik;
import ekptg.model.htp.entity.Permohonan;
import ekptg.model.htp.entity.PfdFail;
import ekptg.model.htp.entity.UploadPerjanjian;
import ekptg.model.htp.pembelian.IPembelian;
import ekptg.model.htp.pembelian.IPembelianDrafPerjanjian;
import ekptg.model.htp.pembelian.IPembelianPeguam;
import ekptg.model.htp.pembelian.IPerjanjianPembelian;
import ekptg.model.htp.pembelian.PembelianBean;
import ekptg.model.htp.pembelian.PembelianDrafPerjanjianBean;
import ekptg.model.htp.pembelian.PembelianPeguamBean;
import ekptg.model.htp.pembelian.PerjanjianBean;

public class PerjanjianPembelianModule extends AjaxModule {
	private static Logger myLog = Logger.getLogger(PerjanjianPembelianModule.class);
	private static final String PATH="app/htp/pembelian/perjanjian/";
	private String vm = PATH+"perjanjian.jsp";
	private String userID = null;
	private String selectedTab="0";
	private IPembelianPeguam iPeguam = null;
	private IPembelian iPembelian = null;
	private IPembelianDrafPerjanjian iDrafPerjanjian = null;
	private IPerjanjianPembelian iPerjanjianPembelian = null;
	private PfdFail fail = null;
	private Permohonan permohonan = null;
	private HtpPermohonan htpPermohonan = null;
	private HakmilikUrusan urusan = null;
	private Peguam peguam = null;
	private DrafPerjanjian drafPerjanjian = null;
	private PerjanjianPembelian perjanjian = null;
	private HtpPerjanjian htpPerjanjian = null;
	private PerjanjianPindahMilik pindahMilik = null;
	private UploadPerjanjian uPerjanjian = null;
	
	
	@Override
	public String doAction() throws Exception {
		HttpSession session = request.getSession();
		String command = getParam("command");
		String action = getParam("action");
		String idDokumenPerjanjian = getParam("idDokumenPerjanjian");
		String idPermohonan = getParam("idPermohonan");
		
		if(command.equals("addDraft")){
			System.out.println("==addDraft==");
			getPermohonanInfo();
			context.put("draft", drafPerjanjian);
			context.put("drafMode", "save");
			vm = PATH+"draft.jsp";
			 
		}
		else if(command.equals("simpanDraft")){
			System.out.println("==simpanDraft==");
			getDrafValues();
			getIDrafPerjanjian().saveDrafPerjanjian(drafPerjanjian);
			getPermohonanInfo();
			Vector<DrafPerjanjian> v = getIDrafPerjanjian().getDrafList(getParam("idPermohonan"));
			context.put("drafts", v);
			selectedTab="0";
			vm = PATH+"perjanjian.jsp";
			 
		}
		else if(command.equals("updateDraft")){
			System.out.println("==updateDraft==");
			getDrafValues();
			getIDrafPerjanjian().updateDrafPerjanjian(drafPerjanjian);
			Vector<DrafPerjanjian> v = getIDrafPerjanjian().getDrafList(getParam("idPermohonan"));
			context.put("drafts", v);
			selectedTab="0";
			vm = PATH+"perjanjian.jsp";
		}
		else if(command.equals("paparDraft")){
			System.out.println("==paparDraft==");
			getPermohonanInfo();
			drafPerjanjian = getIDrafPerjanjian().getDrafPerjanjian(getParam("idDraf"));
			context.put("draft", drafPerjanjian);
			context.put("drafMode", "edit");
			vm = PATH+"draft.jsp";
			 
		}
		else if(command.equals("deleteDraft")){
			System.out.println("==deleteDraft==");
			getPermohonanInfo();
			getIDrafPerjanjian().deleteDrafPerjanjian(getParam("idDraf"));
			Vector<DrafPerjanjian> v = getIDrafPerjanjian().getDrafList(getParam("idPermohonan"));
			context.put("drafts", v);
			selectedTab="0";
			vm = PATH+"perjanjian.jsp";
		}
		else if(command.equals("kembaliDraft")){
			System.out.println("==kembaliDraft==");
			Vector<DrafPerjanjian> v = getIDrafPerjanjian().getDrafList(getParam("idPermohonan"));
			context.put("drafts", v);
			selectedTab="0";
			vm = PATH+"perjanjian.jsp";
		}
		else if(command.equals("Peguam")){
			System.out.println("====Peguam====");
			context.remove("peguam");
			getPermohonanInfo();
			getPeguamValues();
			String peguamMode = "edit";
			String mode =" class=disabled readonly";
			peguam = getIPembelianPeguam().findByIdPermohonan(getParam("idPermohonan"));
			if(peguam == null){
				peguamMode = "new";
				mode ="";
				context.put("selectBNegeri", HTML.SelectNegeri("selectBNegeri"," onChange=\"doChangeNegeriPeguam()\" "));
				context.put("selectBDaerah", HTML.SelectDaerah("selectBDaerah"));
			
			}else{
			//context.put("selectBNegeri", HTML.SelectNegeri("selectBNegeri",peguam.getIdNegeri(),"disabled", " onChange=\"doChangeNegeriPeguam()\" "));
			//context.put("selectBDaerah", HTML.SelectDaerahByNegeri(String.valueOf(peguam.getIdNegeri()), "selectBDaerah", peguam.getIdDaerah(), "disabled"));
			context.put("selectBNegeri", peguam.getNegeri().getNamaNegeri());
			context.put("selectBDaerah", peguam.getDaerah().getNamaDaerah());
			
			}
			context.put("mode", mode);
			context.put("peguamMode", peguamMode);
			context.put("peguam", peguam);
			selectedTab="4";
			vm = PATH+"perjanjian.jsp";	
		}
		else if(command.equals("doChangeNegeriPeguam")){
			System.out.println("====doChangeNegeriPeguam====");
			getPermohonanInfo();
			getPeguamValues();
			getdoChange();
			String mode =" readonly";
			peguam = getIPembelianPeguam().findByIdPermohonan(getParam("idPermohonan"));
			if(peguam == null){
				context.put("peguamMode", "new");
			}else{
				context.put("peguamMode", "update");
			}
			selectedTab="4";
			vm = PATH+"perjanjian.jsp";
		}
		else if(command.equals("savePeguam")){
			System.out.println("==savePeguam==");
			getPermohonanInfo();
			getPeguamValues();
			getdoChange();
			peguam = getIPembelianPeguam().simpanPeguam(peguam);
			peguam = getIPembelianPeguam().findByIdPermohonan(getParam("idPermohonan"));
			//context.put("selectBNegeri", HTML.SelectNegeri("selectBNegeri",peguam.getIdNegeri(),"disabled", " onChange=\"doChangeNegeriPeguam()\" "));
			//context.put("selectBDaerah", HTML.SelectDaerahByNegeri(String.valueOf(peguam.getIdNegeri()), "selectBDaerah", peguam.getIdDaerah(), "disabled"));
			context.put("selectBNegeri", peguam.getNegeri().getNamaNegeri());
			context.put("selectBDaerah", peguam.getDaerah().getNamaDaerah());
			context.put("mode", " class=disabled readonly");
			context.put("peguamMode", "kemaskini");
			context.put("peguam", peguam);
			selectedTab="4";
			vm = PATH+"perjanjian.jsp";
		}
		else if(command.equals("kemaskiniPeguam")){
			System.out.println("==kemaskiniPeguam==");
			getPermohonanInfo();
			peguam = getIPembelianPeguam().findByIdPermohonan(getParam("idPermohonan"));
			context.put("selectBNegeri", HTML.SelectNegeri("selectBNegeri",peguam.getIdNegeri() ,"", "onChange=\"doChangeNegeriPeguam()\" "));
			context.put("selectBDaerah", HTML.SelectDaerahByNegeri(String.valueOf(peguam.getIdNegeri()), "selectBDaerah", peguam.getIdDaerah(), ""));
			context.put("mode", "");
			context.put("peguamMode", "update");
			context.put("peguam", peguam);
			selectedTab="4";
			vm = PATH+"perjanjian.jsp";
			
		}
		else if(command.equals("updatePeguam")){
			System.out.println("==updatePeguam==");
			getPermohonanInfo();
			getPeguamValues();
			peguam = getIPembelianPeguam().updatePeguam(peguam);
			//context.put("selectBNegeri", HTML.SelectNegeri("selectBNegeri",peguam.getIdNegeri() ,"disabled", " onChange=\"doChangeNegeriPeguam()\" "));
		//	context.put("selectBDaerah", HTML.SelectDaerahByNegeri(String.valueOf(peguam.getIdNegeri()), "selectBDaerah", peguam.getIdDaerah(), "disabled"));
			peguam = getIPembelianPeguam().findByIdPermohonan(getParam("idPermohonan"));
			context.put("selectBNegeri", peguam.getNegeri().getNamaNegeri());
			context.put("selectBDaerah", peguam.getDaerah().getNamaDaerah());
			context.put("mode", " class=disabled readonly");
			context.put("peguamMode", "kemaskini");
			context.put("peguam", peguam);
			selectedTab="4";
			vm = PATH+"perjanjian.jsp";
		}
		else if(command.equals("pembelianview")){
			System.out.println("==pembelianview==");
			getPermohonanInfo();
			String perjanjianMode = "edit";
			String mode =" readonly class=\"disabled\"";
			perjanjian = getIPerjanjianPembelian().getPerjanjianByPermohonan(getParam("idPermohonan"));
			if(perjanjian == null){
				perjanjianMode  = "new";
				mode ="";
			}
			context.put("mode", mode);
			context.put("perjanjianMode", perjanjianMode);
			context.put("perjanjian", perjanjian);
			selectedTab="1";
			vm = PATH+"perjanjian.jsp";
		}
		else if(command.equals("simpanPerjanjian")){
			System.out.println("==simpanPerjanjian==");
			getPermohonanInfo();
			getPerjanjianValues();
			perjanjian = getIPerjanjianPembelian().simpanPerjanjian(perjanjian);
			context.put("mode", " readonly class=\"disabled\"");
			context.put("perjanjianMode", " edit");
			context.put("perjanjian", perjanjian);
			selectedTab="1";
			vm = PATH+"perjanjian.jsp";
		}
		else if(command.equals("kemaskiniPerjanjian")){
			System.out.println("==kemaskiniPerjanjian==");
			getPermohonanInfo();
			String perjanjianMode = "update";
			String mode =" ";
			perjanjian = getIPerjanjianPembelian().getPerjanjianByPermohonan(getParam("idPermohonan"));
			context.put("mode", mode);
			context.put("perjanjianMode", perjanjianMode);
			context.put("perjanjian", perjanjian);
			selectedTab="1";
			vm = PATH+"perjanjian.jsp";
		}
		else if(command.equals("updatePerjanjian")){
			System.out.println("==updatePerjanjian==");
			getPermohonanInfo();
			getPerjanjianValues();
			perjanjian = getIPerjanjianPembelian().updatePerjanjian(perjanjian);
			context.put("mode", " readonly class=\"disabled\"");
			context.put("perjanjianMode", " edit");
			context.put("perjanjian", perjanjian);
			selectedTab="1";
			vm = PATH+"perjanjian.jsp";
		}
		else if(command.equals("senaraisemakperjanjian")){
			System.out.println("==senaraisemakperjanjian==");
			getPermohonanInfo();
			selectedTab="2";
			vm = PATH+"senaraiSemakPerjanjian.jsp";
			getSemakanPerjanjian();
			context.put("mode", " disabled");
			context.put("semakMode", " ");
			context.put("tajukSemakan", getParam("tajuk"));
			
		}
		else if(command.equals("senaraisemakperjanjiankemaskini")){
			System.out.println("==senaraisemakperjanjiankemaskini==");
			getPermohonanInfo();
			selectedTab="2";
			vm = PATH+"senaraiSemakPerjanjian.jsp";
			getSemakanPerjanjian();
			context.put("mode", " ");
			context.put("semakMode", "update");
		}
		else if(command.equals("senaraisemakperjanjiansimpan")){
			System.out.println("==senaraisemakperjanjiansimpan==");
			getPermohonanInfo();
			simpanSenaraiSemakFail(String.valueOf(htpPermohonan.getPermohonan().getIdPermohonan()));
			selectedTab="2";
			vm = PATH+"senaraiSemakPerjanjian.jsp";
			getSemakanPerjanjian();
			context.put("mode", " disabled");
			context.put("semakMode", "");
		}
		else if(command.equals("senaraisemakpmilik")){
			System.out.println("==senaraisemakpmilik==");
			getPermohonanInfo();
			selectedTab="2";
			vm = PATH+"senaraiSemakPindahmilik.jsp";
			getSemakanPindahMilik();
			context.put("mode", " disabled");
			context.put("semakMode", " ");
			context.put("tajukSemakan", getParam("tajuk"));
			
		}
		else if(command.equals("senaraisemakpmilikkemaskini")){
			System.out.println("==senaraisemakpmilikkemaskini==");
			getPermohonanInfo();
			selectedTab="2";
			vm = PATH+"senaraiSemakPindahmilik.jsp";
			getSemakanPindahMilik();
			context.put("mode", " ");
			context.put("semakMode", "update");
		}
		else if(command.equals("senaraisemakpmiliksimpan")){
			System.out.println("==senaraisemakpmiliksimpan==");
			getPermohonanInfo();
			simpanSenaraiSemakFail(String.valueOf(htpPermohonan.getPermohonan().getIdPermohonan()));
			selectedTab="2";
			vm = PATH+"senaraiSemakPindahmilik.jsp";
			getSemakanPindahMilik();
			context.put("mode", " disabled");
			context.put("semakMode", "");
		}
		else if(command.equals("senaraisemakview")){
			System.out.println("==senaraisemakview==");
			idPermohonan = getParam("idPermohonan");
			getPermohonanInfo();
			getSemakanPindahMilik();
			getSemakanPerjanjian();
			getPindahMilikValues();
			
			System.out.println("==senaraisemakview perjanjian=="+idPermohonan);
			pindahMilik = getIPerjanjianPembelian().getPerjanjianPermohonan(idPermohonan);

			
			if(pindahMilik == null){
				System.out.println("NewPerjanjian");
				selectedTab="2";
				vm = PATH+"perjanjian.jsp";
				getPindahMilikValues();
				getSemakanPindahMilik();
				getSemakanPerjanjian();
				context.put("semakMode", "new");
				context.put("pindahMilik", pindahMilik);
				context.put("uPerjanjian", "");
				context.put("lampiran", 1);
				context.put("mode", "");
			
			}else{
				perjanjian = getIPerjanjianPembelian().getPerjanjianByPermohonan(getParam("idPermohonan"));
				if(perjanjian==null){
					context.put("buttonPerjanjian", "tiada");
				}else{
					context.put("buttonPerjanjian", "ada");
				}
				Vector uPerjanjian = getIPerjanjianPembelian().getPerjanjianAttach(pindahMilik.getIdDokumenPerjanjian());
				System.out.println("Existing Perjanjian");
				System.out.println("==2==");
				selectedTab="2";
				vm = PATH+"perjanjian.jsp";
//				getPindahMilikValues();
				getSemakanPindahMilik();
				getSemakanPerjanjian();
				
				context.put("mode", " readonly class=\"disabled\"");
				context.put("semakMode", "");
				context.put("pindahMilik", pindahMilik);
				context.put("uPerjanjian", uPerjanjian);
				context.put("lampiran", "");
		}
			}
		else if(command.equals("kemaskiniSemak")){
			System.out.println("==kemaskiniSemak==");
			getPermohonanInfo();
			getPindahMilikValues();
			getSemakanPerjanjian();
			selectedTab="2";
			vm = PATH+"perjanjian.jsp";
			getSemakanPindahMilik();
			context.put("mode", " ");
			context.put("semakMode", "update");
		}
		else if(command.equals("simpanPemilik")){
			System.out.println("==simpanPemilikk==");
			
			getPermohonanInfo();	
			getPindahMilikValues();
			getSemakanPerjanjian();
			getSemakanPindahMilik();
			selectedTab="2";
			vm = PATH+"perjanjian.jsp";	
			simpanSenaraiSemakFail(String.valueOf(htpPermohonan.getPermohonan().getIdPermohonan()));
			getIPerjanjianPembelian().simpanPerjanjian(pindahMilik);
			pindahMilik = getIPerjanjianPembelian().getPerjanjianPermohonan(String.valueOf(htpPermohonan.getPermohonan().getIdPermohonan()));	
			context.put("buttonPerjanjian", "ada");
			context.put("pindahMilik", pindahMilik);
			getPindahMilikValues();
//			downloadPerjanjian(String.valueOf(htpPermohonan.getPermohonan().getIdPermohonan()), session);
			context.put("mode", " readonly class=\"disabled\"");
			context.put("semakMode", "");
		}	
		else if(command.equals("hapusDokumenPerjanjian")){
			String id = getParam("idDokumenPerjanjianAttach");
			String idP = getParam("idDokumenPerjanjian");
			System.out.println("==hapusDokumenPerjanjian=="+id);
			getIPerjanjianPembelian().hapusPerjanjian(id);
			context.put("hapus",1);
			getPermohonanInfo();
			getPindahMilikValues();
			getSemakanPerjanjian();
			getSemakanPindahMilik();		
			pindahMilik = getIPerjanjianPembelian().getPerjanjianPermohonan(String.valueOf(htpPermohonan.getPermohonan().getIdPermohonan()));
			Vector uPerjanjian = getIPerjanjianPembelian().getPerjanjianAttach(idP);
			context.put("pindahMilik", pindahMilik);
			context.put("uPerjanjian", uPerjanjian);
			context.put("mode", " readonly class=\"disabled\"");
			context.put("semakMode", "");
			selectedTab="2";
			vm = PATH+"perjanjian.jsp";
			
		}
		else if(command.equals("updatePemilik")){
			System.out.println("==updatePemilik==");
			getPermohonanInfo();	
			getPindahMilikValues();
			getSemakanPerjanjian();
			selectedTab="2";
			vm = PATH+"perjanjian.jsp";
			getSemakanPindahMilik();
			pindahMilik = getIPerjanjianPembelian().updatePerjanjian(pindahMilik);
			context.put("pindahMilik", pindahMilik);
			//downloadPerjanjian(String.valueOf(htpPermohonan.getPermohonan().getIdPermohonan()), session);
			simpanSenaraiSemakFail(String.valueOf(htpPermohonan.getPermohonan().getIdPermohonan()));
			context.put("mode", " readonly class=\"disabled\"");
			context.put("semakMode", "");
			
		}else if(command.equals("tambahAttachFail")){
			idPermohonan = getParam("idPermohonan");
			System.out.println("==tambahAttachFail=="+idPermohonan);
			pindahMilik = getIPerjanjianPembelian().getPerjanjianPermohonan(idPermohonan);
			context.put("hapus",0);
			context.put("pindahMilik", pindahMilik);
			selectedTab="2";	
			vm = PATH+"PerjanjianAttch.jsp";
			getPindahMilikValues();
		
		}else if(command.equals("AttchPemilik")){
			idPermohonan = getParam("idPermohonan");
			System.out.println("==AttchPemilik==");
			downloadPerjanjian(idDokumenPerjanjian, session);
			context.put("hapus",0);
			context.put("berjaya", 1);
			getPermohonanInfo();
			getSemakanPindahMilik();
			getSemakanPerjanjian();
			getPindahMilikValues();
			pindahMilik = getIPerjanjianPembelian().getPerjanjianPermohonan(idPermohonan);
			
			if(pindahMilik == null){

				selectedTab="2";
				vm = PATH+"perjanjian.jsp";
				getPindahMilikValues();
				getSemakanPindahMilik();
				getSemakanPerjanjian();
				context.put("semakMode", "new");
				context.put("pindahMilik", "");
				context.put("uPerjanjian", "");
				context.put("lampiran", 1);
			
			}else{
				Vector uPerjanjian = getIPerjanjianPembelian().getPerjanjianAttach(pindahMilik.getIdDokumenPerjanjian());

				System.out.println("==2==");
				selectedTab="2";
//				getPindahMilikValues();
				getSemakanPindahMilik();
				getSemakanPerjanjian();
				context.put("mode", " readonly class=\"disabled\"");
				context.put("semakMode", "");
				context.put("pindahMilik", pindahMilik);
				context.put("uPerjanjian", uPerjanjian);
				context.put("lampiran", "");
			vm = PATH+"PerjanjianAttch.jsp";
		}}
		else if(command.equals("detail")){
			System.out.println("==detail==");
			getPermohonanInfo();
			Vector<DrafPerjanjian> v = getIDrafPerjanjian().getDrafList(getParam("idPermohonan"));
			context.put("drafts", v);
			selectedTab="0";
			vm = PATH+"perjanjian.jsp";
		}
		else if(command.equals("pindahMilik")){
			System.out.println("==pindahMilik==");
			getPermohonanInfo();
			Vector pindahMilik = getIPerjanjianPembelian().getPindahMilikByPermohonan(getParam("idPermohonan"));
			Vector<HakmilikUrusan> hakmiliks = getIPembelian().getHakmilikList(getParam("idPermohonan"));
			String pindahMode ="";
			String mode =" readonly class=\"disabled\"";
			if(pindahMilik.isEmpty()){
				pindahMode = "new";
				mode ="";
				generatePerjanjianPindahMilik(hakmiliks,getParam("idPermohonan"));
				pindahMilik = getIPerjanjianPembelian().getPindahMilikByPermohonan(getParam("idPermohonan"));
				//pindahMilik = getIPerjanjianPembelian().getPindahMilikByPermohonan(getParam("idPermohonan"));
			}
			//context.put("hakmiliks", pindahMilik);
			context.put("mode", mode);
			context.put("pindahMode", pindahMode);
			context.put("pindahMilik", pindahMilik);
			selectedTab="3";
			vm = PATH+"perjanjian.jsp";
		}
		else if(command.equals("simpanPindahMilik")){
			System.out.println("==simpanPindahMilik==");
			getPermohonanInfo();
			getPindahMilikValues();
			pindahMilik = getIPerjanjianPembelian().simpanPindahMilik(pindahMilik);
			context.put("pindahMilik", pindahMilik);
			context.put("mode", " readonly class=\"disabled\"");
			context.put("pindahMode", "");
			selectedTab="3";
			vm = PATH+"perjanjian.jsp";
		}
		else if(command.equals("updatePindahMilik")){
			System.out.println("==updatePindahMilik==");
			getPermohonanInfo();
			getPindahMilikValues();
			getIPerjanjianPembelian().updatePindahMilik(pindahMilik);
			Vector pindahMilik = getIPerjanjianPembelian().getPindahMilikByPermohonan(getParam("idPermohonan"));
			context.put("pindahMilik", pindahMilik);
			context.put("mode", " readonly class=\"disabled\"");
			context.put("pindahMode", "");
			selectedTab="3";
			vm = PATH+"perjanjian.jsp";
		}
		else if(command.equals("viewPindahMilik")){
			System.out.println("==viewPindahMilik==");
			getPermohonanInfo();
			pindahMilik = getIPerjanjianPembelian().getPindahMilik(getParam("idPindahMilik"));
			context.put("pindahMilik", pindahMilik);
			context.put("mode", "");
			context.put("pindahMode", "update");
			selectedTab="3";
			vm = PATH+"updatePindahMilik.jsp";
		}
		else if(command.equals("searchFail")){
			System.out.println("==searchFail==");
			String noFail = getParam("noFail");
			String carian = getParam("carian");
			String idNegeri = getParam("socNegeri") == "" ? "0" : getParam("socNegeri");
			
			Vector<?> v = getIPembelian().findFail(carian, noFail, idNegeri);
			context.put("lists", v);
			context.put("socNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri)));
			setupPage(session,action, v);
			vm = PATH+"index.jsp";
		}
		else{
			String noFail = getParam("noFail");
			String carian = getParam("carian");
			String idNegeri = getParam("socNegeri") == "" ? "0" : getParam("socNegeri");
			
			Vector<?> v = getIPembelian().findFail(carian, noFail, idNegeri);
			context.put("lists", v);
			context.put("socNegeri",HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri)));
			setupPage(session,action, v);
			//context.put("socNegeri",HTML.SelectNegeri("socNegeri"));
			selectedTab="0";
			vm = PATH+"index.jsp";
		}
		context.put("page","5");
		context.put("selectedTab", selectedTab);
		return vm;
	}
	private void generatePerjanjianPindahMilik(Vector<HakmilikUrusan> hakmiliks, String idPermohonan) {
		if(!hakmiliks.isEmpty()){
			for(HakmilikUrusan urusan : hakmiliks){
				pindahMilik = new PerjanjianPindahMilik();
				pindahMilik.setIdPermohonan(idPermohonan);
				pindahMilik.setHakmilikUrusan(urusan);
				getIPerjanjianPembelian().simpanPindahMilik(pindahMilik);
			}
		}
		
	}
	private void getdoChange() throws Exception {
		String selectNegeri = getParam("selectBNegeri");
		if(selectNegeri.equals(""))
			selectNegeri="0";
		String selectDaerah = getParam("selectBDaerah");
		if(selectDaerah.equals(""))
			selectDaerah="0";
		context.put("selectBNegeri", HTML.SelectNegeri("selectBNegeri", Long.parseLong(selectNegeri),"","onChange=\"doChangeNegeriPeguam()\" "));
		context.put("selectBDaerah", HTML.SelectDaerahByNegeri(selectNegeri, "selectBDaerah"));
		
	}
	private void getPermohonanInfo()throws Exception{
		String idPermohonan = getParam("idPermohonan");
		String idHtpPermohonan = getParam("idHtpPermohonan");
		htpPermohonan = getIPembelian().findPermohonan(idPermohonan,idHtpPermohonan);
		context.put("htpPermohonan", htpPermohonan);
	}
	private void getPeguamValues() throws Exception{
		String idPermohonan = getParam("idPermohonan");
		String idPeguam = getParam("idPeguam");
		String namaPeguam = getParam("txtNamaPeguam");
		String kodPeguam = getParam("txtKodPeguam");
		String alamat1 = getParam("txtAlamat1");
		String alamat2 =getParam("txtAlamat2");
		String alamat3 =getParam("txtAlamat3");
		String poskod =getParam("txtPoskod");
		String tel =getParam("txtNoTelefon");
		String fax = getParam("txtNoFax");
		String selectNegeri = getParam("selectBNegeri")==""?"0":getParam("selectBNegeri");
		String selectDaerah = getParam("selectBDaerah")==""?"0":getParam("selectBDaerah");
		peguam = new Peguam();
		permohonan = new Permohonan();
		permohonan.setIdPermohonan(idPermohonan);
		peguam.setIdpeguam(idPeguam);
		peguam.setNama(namaPeguam);
		peguam.setNoRujukan(kodPeguam);
		peguam.setAlamat1(alamat1);
		peguam.setAlamat2(alamat2);
		peguam.setAlamat3(alamat3);
		peguam.setPoskod(poskod);
		peguam.setNoTel(tel);
		peguam.setNoFax(fax);
		peguam.setIdNegeri(selectNegeri);
		peguam.setIdDaerah(selectDaerah);
		peguam.setPermohonan(permohonan);
		context.put("peguam", peguam);
	}
	private void getDrafValues(){
		String idPermohonan = getParam("idPermohonan");
		String tarikhTerima = getParam("txdTarikhTerima");
		String tarikhHantar = getParam("txdTarikhHantar");
		String ulasan = getParam("txtcatatan");
		String idDraf = getParam("idDraf");
		
		drafPerjanjian = new DrafPerjanjian();
		drafPerjanjian.setIdDrafPerjanjian(idDraf);
		drafPerjanjian.setTarikhHantar(tarikhHantar);
		drafPerjanjian.setTarikhTerima(tarikhTerima);
		drafPerjanjian.setUlasan(ulasan);
		//drafPerjanjian.setIdMasuk(null);
		permohonan = new Permohonan();
		permohonan.setIdPermohonan(idPermohonan);
		drafPerjanjian.setPermohonan(permohonan);
	}
	private void getPerjanjianValues(){
		String idPermohonan = getParam("idPermohonan");
		String idPerjanjian = getParam("idPerjanjian");
		String idPerjanjianPembelian = getParam("id_perjanjianpembelian");
		String noKontrak = getParam("txtNoKontrak");
		String tarikhPerjanjian= getParam("txtTarikhPerjanjian");
		String jumlahHakmilik = getParam("txtjumlahHakmilik");
		String unitBangunan = "0";
		if(!getParam("txtbilUnitBangunan").equals("")||getParam("txtbilUnitBangunan")!=null){
			unitBangunan = getParam("txtbilUnitBangunan");
		}
		String nilaiTanah = Utils.RemoveSymbol(getParam("txtnilaiTanah"));
		String hargaTambahan = Utils.RemoveSymbol(getParam("txtHargaTambahan"));
		String nilaiBangunan = Utils.RemoveSymbol(getParam("txtnilaiBangunan"));
		String hargaBeli = Utils.RemoveSymbol(getParam("txtHargaBeli"));
		
		permohonan = new Permohonan();
		permohonan.setIdPermohonan(idPermohonan);
		
		htpPerjanjian = new HtpPerjanjian();
		htpPerjanjian.setPermohonan(permohonan);
		htpPerjanjian.setTarikhPerjanjian(tarikhPerjanjian);
		htpPerjanjian.setNoRujukanPerjanjian(noKontrak);
		htpPerjanjian.setIdPerjanjian(idPerjanjian);
		
		perjanjian = new PerjanjianPembelian();
		perjanjian.setIdPerjanjianPembelian(idPerjanjianPembelian);
		perjanjian.setBilHakmilikBeli(jumlahHakmilik);
		perjanjian.setNilaiTanah(nilaiTanah);
		perjanjian.setHargaTambahan(hargaTambahan);
		perjanjian.setHargaBeli(hargaBeli);
		perjanjian.setNilaiBangunan(nilaiBangunan);
		perjanjian.setBilUnitBeli(Integer.parseInt(unitBangunan));
		perjanjian.setTarikhBorang14A("");
		perjanjian.setHtpPerjanjian(htpPerjanjian);
		
	}
	private String getParamasInteger(String string) {
		// TODO Auto-generated method stub
		return null;
	}
	private void getPindahMilikValues(){
		String idPerjanjianPindahMilik = getParam("idPindahMilik");
		String tarikhTerima = getParam("tarikhTerimaKJP");
		String tarikhHantar = getParam("tarikhHantarKJP");
		String tarikhTandatangan = getParam("tarikhTandatanganPTP");
		String idPermohonan = getParam("idPermohonan");
		String idDokumenPerjanjian = getParam("idDokumenPerjanjian");
		System.out.println("getPindahMilikValues==" +idPermohonan);
		pindahMilik = new PerjanjianPindahMilik();
		permohonan = new Permohonan();
		
		permohonan.setIdPermohonan(idPermohonan);
		pindahMilik.setIdPindahMilik(idPerjanjianPindahMilik);
		pindahMilik.setIdPermohonan(idPermohonan);
		pindahMilik.setTarikhHantar(tarikhHantar);
		pindahMilik.setTarikhTerima(tarikhTerima);
		pindahMilik.setTarikhTandatangan(tarikhTandatangan);
		pindahMilik.setIdDokumenPerjanjian(idDokumenPerjanjian);
		context.put("pindahMilik", pindahMilik);
		
	}
	private IPembelian getIPembelian(){
		if (iPembelian==null){
			iPembelian=new PembelianBean();
		}
		return iPembelian;
	} 
	private IPembelianPeguam getIPembelianPeguam(){
		if(iPeguam == null)
			iPeguam = new PembelianPeguamBean();
		return iPeguam;
	}
	private IPembelianDrafPerjanjian getIDrafPerjanjian(){
		if(iDrafPerjanjian == null)
			iDrafPerjanjian = new PembelianDrafPerjanjianBean();
		return iDrafPerjanjian;
	}
	private IPerjanjianPembelian getIPerjanjianPembelian(){
		if(iPerjanjianPembelian == null)
			iPerjanjianPembelian = new PerjanjianBean();
		return iPerjanjianPembelian;
	}
	
	private void getSemakanPindahMilik()throws Exception{
		context.put("semakclass", new FrmSemakan());
		Vector semakList = FrmSemakan.getSenaraiSemakan("frmBeliPindahMilik");
		context.put("semakDraf", semakList);
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
	
	private void getSemakanPerjanjian()throws Exception{
		context.put("semakclass", new FrmSemakan());
		Vector semakList = FrmSemakan.getSenaraiSemakan("frmBeliPerjanjian");
		context.put("semakPerjanjian", semakList);
	}
	
    private void downloadPerjanjian(String idDokumenPerjanjian,HttpSession session) throws Exception {//
		System.out.println("=======Download file perjanjian==========" +idDokumenPerjanjian);
		uploadPerjanjianBaru(idDokumenPerjanjian);
	}
    
   @SuppressWarnings("unchecked")
private void uploadPerjanjianBaru(String idDokumenPerjanjian) throws Exception {
	    DiskFileItemFactory factory = new DiskFileItemFactory();
	    ServletFileUpload upload = new ServletFileUpload(factory);
	    List items = upload.parseRequest(request);
	    Iterator itr = items.iterator();
	    while (itr.hasNext()) {
	      FileItem item = (FileItem)itr.next();
	      myLog.info("ContentType :" + item.getContentType());
	      if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
	    	  saveData(item,idDokumenPerjanjian);
	      }
	    }
	  }
 private void saveData(FileItem item,String idDokumenPerjanjian) throws Exception {
	 	HttpSession session = request.getSession();		
  		Db db = null;
//		SimpleDateFormat sdfSource = new SimpleDateFormat("dd/mm/yyyy");
//	    Date TT = sdfSource.parse(getParam("txdTarikhTerimaDraf"));
//	    Date TH = sdfSource.parse(getParam("txdTarikhTerimaDraf"));
//	    java.sql.Date TH2 = new java.sql.Date(TH.getTime());
//	    java.sql.Date TT2 = new java.sql.Date(TT.getTime());

        try {
        	
        	long id_dokumenperjanjianattch = DB.getNextID("TBLHTPDOKUMENPERATTACH_SEQ");	        	
        	db = new Db();			      

        	Connection con = db.getConnection();
        	con.setAutoCommit(false);
        	SQLRenderer r = new SQLRenderer();
        	System.out.println("1 "+idDokumenPerjanjian+" 2 "+getParam("idPermohonan")+" 3 "+item.getName()+" 4 " +item.getContentType());
        	PreparedStatement ps = con.prepareStatement("insert into TBLHTPDOKUMENPERJANJIANATTACH " +
        			"(id_dokumenperjanjianattach,id_dokumenperjanjian,nama_Fail,mimetype,content_derafperjanjian) " +
        			"values(?,?,?,?,?)");
        	ps.setLong(1, id_dokumenperjanjianattch);
        	ps.setNString(2,idDokumenPerjanjian);
        	ps.setString(3,item.getName());
        	ps.setString(4,item.getContentType());
        	ps.setBinaryStream(5,item.getInputStream(),(int)item.getSize());   
        	System.out.println("==savedata==" +ps);
        	ps.executeUpdate();
            con.commit();  
            
        	
	    } finally {
		      if (db != null) db.close();
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
