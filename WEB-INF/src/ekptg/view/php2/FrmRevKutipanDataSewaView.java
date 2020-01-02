/**
 * 
 */
package ekptg.view.php2;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;
import ekptg.helpers.HTML;
import ekptg.model.php2.FrmRevKutipanDataSewaData;

/**
 * @author mohd faizal
 * 
 */
public class FrmRevKutipanDataSewaView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;

	FrmRevKutipanDataSewaData logic = new FrmRevKutipanDataSewaData();

	@Override
	public String doTemplate2() throws Exception {

		HttpSession session = this.request.getSession();

		Boolean postDB = false;
		String doPost = (String) session.getAttribute("doPost");
		if (doPost.equals("true")) {
			postDB = true;
		}

		String action = getParam("action"); 		
		String submit = getParam("command");
		String vm = "";
		String hitButton = getParam("hitButton");
		String step = getParam("step");
		String mode = getParam("mode");
		
		String idFail = getParam("idFail");
		String idHasil = getParam("idHasil");
		String idPemohon = getParam("idPemohon");
		
		Vector beanMaklumatPermohonan = null;
		Vector beanMaklumatPemohon = null;
		Vector beanMaklumatTanah = null;
		Vector beanMaklumatPerjanjian = null;
		
		String idUrusan = getParam("socUrusan");
		if (idUrusan == null || idUrusan.trim().length() == 0) {
			idUrusan = "99999";
		}
		String idSuburusan = getParam("socSuburusan");
		if (idSuburusan == null || idSuburusan.trim().length() == 0) {
			idSuburusan = "99999";
		}
		String idNegeri = getParam("socNegeri");
		if (idNegeri == null || idNegeri.trim().length() == 0) {
			idNegeri = "99999";
		}
		String idBandar = getParam("socBandar");
		if (idBandar == null || idBandar.trim().length() == 0) {
			idBandar = "99999";
		}
		String idNegeriTanah = getParam("socNegeriTanah");
		if (idNegeriTanah == null || idNegeriTanah.trim().length() == 0) {
			idNegeriTanah = "99999";
		}
		String idKementerian = getParam("socKementerian");
		if (idKementerian == null || idKementerian.trim().length() == 0) {
			idKementerian = "99999";
		}
		String idAgensi = getParam("socAgensi");
		if (idAgensi == null || idAgensi.trim().length() == 0) {
			idAgensi = "99999";
		}
		String idLuas = getParam("socLuas");
		if (idLuas == null || idLuas.trim().length() == 0){
			idLuas = "99999";
		}
		
		this.context.put("flagMsg", "");
		this.context.put("outputMsg", "");
		
		vm = "app/php2/frmREVKutipanDataSewa.jsp";
		
		//HITBUTTON
		if (postDB) {
			if ("daftarBaru".equals(hitButton)) {
				idFail = logic.daftarBaru(getParam("noFail"), idUrusan, idSuburusan, getParam("txtPerkara"), getParam("txtTujuan"), 
						getParam("txtNama"), getParam("txtNoPendaftaran"), getParam("txtAlamat1"), getParam("txtAlamat2"), getParam("txtAlamat3"),
						getParam("txtPoskod"), idBandar, idNegeri, getParam("txtEmel"), getParam("txtNoTel"), getParam("txtNoFaks"),							
						getParam("txtNoSiri"), getParam("txtTarikhMula"), getParam("txtTempoh"), getParam("txtTarikhTamat"), getParam("flagKelulusanDasar"), getParam("txtKadarSewa"), 
						getParam("txtCagaran"), getParam("modCajSewaan"),getParam("txtCatatan"), idNegeriTanah, idKementerian, idAgensi, getParam("txtMaklumatLot"), idLuas, getParam("txtLuas"),
						session);
				this.context.put("flagMsg", "Y");
				this.context.put("outputMsg", "FAIL BERJAYA DIDAFTARKAN");
			}
		}
		
		if ("papar".equals(step)) {
			
			this.context.put("mode", "view");
			this.context.put("readonly", "readonly");
			this.context.put("inputTextClass", "disabled");
			this.context.put("disabled", "disabled");
			
			// MAKLUMAT PERMOHONAN
			beanMaklumatPermohonan = new Vector();
			logic.setMaklumatPermohonan(idFail);
			beanMaklumatPermohonan = logic.getBeanMaklumatPermohonan();
			this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);
			if (logic.getBeanMaklumatPermohonan().size() != 0){
				Hashtable hashPermohonan = (Hashtable) logic.getBeanMaklumatPermohonan().get(0);
				idHasil = (String) hashPermohonan.get("idHasil");
				idUrusan = (String) hashPermohonan.get("idUrusan");
				idSuburusan = (String) hashPermohonan.get("idSuburusan");
			}
			
			// MAKLUMAT PEMOHON
			logic.setMaklumatPemohon(idFail);
			beanMaklumatPemohon = logic.getBeanMaklumatPemohon();
			this.context.put("BeanMaklumatPemohon", beanMaklumatPemohon);			
			if (logic.getBeanMaklumatPemohon().size() != 0){
				Hashtable hashPemohon = (Hashtable) logic.getBeanMaklumatPemohon().get(0);
				idPemohon = (String) hashPemohon.get("idPemohon");
				idNegeri = (String) hashPemohon.get("idNegeri");
				idBandar = (String) hashPemohon.get("idBandar");
			}
			
			// MAKLUMAT TANAH			
			beanMaklumatTanah = new Vector();
			logic.setMaklumatTanah(idFail);
			beanMaklumatTanah = logic.getBeanMaklumatTanah();
			if (logic.getBeanMaklumatTanah().size() != 0){
				Hashtable hashMaklumatTanah = (Hashtable) logic.getBeanMaklumatTanah().get(0);
				idNegeriTanah = (String) hashMaklumatTanah.get("idNegeri");
				idKementerian = (String) hashMaklumatTanah.get("idKementerian");
				idAgensi = (String) hashMaklumatTanah.get("idAgensi");
				idLuas = (String) hashMaklumatTanah.get("idLuas");
			}
			this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
			
			this.context.put("selectNegeriTanah", HTML.SelectNegeri("socNegeriTanah",Long.parseLong(idNegeriTanah), "disabled", " class=\"disabled\""));
			this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), "disabled", " class=\"disabled\""));
			this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Long.parseLong(idAgensi), "disabled", " class=\"disabled\""));
			
			//MAKLUMAT PERJANJIAN
			beanMaklumatPerjanjian = new Vector();
			logic.setMaklumatPerjanjian(idFail);
			beanMaklumatPerjanjian = logic.getBeanMaklumatPerjanjian();
	    	this.context.put("BeanMaklumatPerjanjian", beanMaklumatPerjanjian);
			
			this.context.put("selectUrusan", HTML.SelectUrusanPHPPenyewaan("socUrusan", Long.parseLong(idUrusan), "disabled", " class=\"disabled\""));
			this.context.put("selectSuburusan", HTML.SelectSuburusanByIdUrusan(idUrusan, "socSuburusan", Long.parseLong(idSuburusan), "disabled", " class=\"disabled\""));
			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), "disabled", " class=\"disabled\""));
			this.context.put("selectBandar", HTML.SelectBandarByNegeri(idNegeri, "socBandar", Long.parseLong(idBandar), "disabled", " class=\"disabled\""));
			
		} else {			
			
			this.context.put("mode", "new");
			this.context.put("readonly", "");
			this.context.put("inputTextClass", "");
			this.context.put("disabled", "");
			
			// MAKLUMAT PERMOHONAN
			beanMaklumatPermohonan = new Vector();
			Hashtable hashPermohonan = new Hashtable();
			hashPermohonan.put("noFail",getParam("noFail") == null ? "": getParam("noFail"));
			hashPermohonan.put("perkara", getParam("txtPerkara") == null ? "": getParam("txtPerkara"));
			hashPermohonan.put("tujuan", getParam("txtTujuan") == null ? "": getParam("txtTujuan"));
			beanMaklumatPermohonan.addElement(hashPermohonan);
			this.context.put("BeanMaklumatPermohonan", beanMaklumatPermohonan);

			// MAKLUMAT PEMOHON			
			beanMaklumatPemohon = new Vector();
			Hashtable hashPemohon = new Hashtable();
			hashPemohon.put("nama", getParam("txtNama") == null ? "": getParam("txtNama"));
			hashPemohon.put("noPendaftaran",getParam("txtNoPendaftaran") == null ? "": getParam("txtNoPendaftaran"));
			hashPemohon.put("alamat1", getParam("txtAlamat1") == null ? "": getParam("txtAlamat1"));
			hashPemohon.put("alamat2", getParam("txtAlamat2") == null ? "": getParam("txtAlamat2"));
			hashPemohon.put("alamat3", getParam("txtAlamat3") == null ? "": getParam("txtAlamat3"));
			hashPemohon.put("poskod", getParam("txtPoskod") == null ? "": getParam("txtPoskod"));
			hashPemohon.put("emel", getParam("txtEmel") == null ? "": getParam("txtEmel"));
			hashPemohon.put("noTel", getParam("txtNoTel") == null ? "": getParam("txtNoTel"));
			hashPemohon.put("noFaks", getParam("txtNoFaks") == null ? "": getParam("txtNoFaks"));
			beanMaklumatPemohon.addElement(hashPemohon);
			this.context.put("BeanMaklumatPemohon", beanMaklumatPemohon);
			
			// MAKLUMAT TANAH			
			beanMaklumatTanah = new Vector();
			Hashtable hashMaklumatTanah = new Hashtable();
			hashMaklumatTanah.put("maklumatLot", getParam("txtMaklumatLot") == null ? "": getParam("txtMaklumatLot"));
			hashMaklumatTanah.put("luas",getParam("txtLuas") == null ? "": getParam("txtLuas"));
			beanMaklumatTanah.addElement(hashMaklumatTanah);
			this.context.put("BeanMaklumatTanah", beanMaklumatTanah);
			
			this.context.put("selectNegeriTanah", HTML.SelectNegeri("socNegeriTanah",Long.parseLong(idNegeriTanah), "",""));
			this.context.put("selectKementerian",HTML.SelectKementerian("socKementerian", Long.parseLong(idKementerian), "", " onChange=\"doChangeKementerian();\""));
			this.context.put("selectAgensi",HTML.SelectAgensiByKementerian("socAgensi", idKementerian, Long.parseLong(idAgensi), "", ""));
			
			// MAKLUMAT PERJANJIAN			
			beanMaklumatPerjanjian = new Vector();
			Hashtable hashPerjanjian = new Hashtable();			
			hashPerjanjian.put("noSiri", getParam("txtNoSiri") == null ? "": getParam("txtNoSiri"));
			hashPerjanjian.put("flagKelulusanDasar", getParam("flagKelulusanDasar") == null ? "": getParam("flagKelulusanDasar"));
			hashPerjanjian.put("tarikhMula",getParam("txtTarikhMula") == null ? "": getParam("txtTarikhMula"));
			hashPerjanjian.put("tempoh", getParam("txtTempoh") == null ? "": getParam("txtTempoh"));
			hashPerjanjian.put("tarikhTamat", getParam("txtTarikhTamat") == null ? "": getParam("txtTarikhTamat"));
			hashPerjanjian.put("kadarSewa", getParam("txtKadarSewa") == null ? "": getParam("txtKadarSewa"));
			hashPerjanjian.put("cagaran", getParam("txtCagaran") == null ? "": getParam("txtCagaran"));
			hashPerjanjian.put("modCajSewaan", getParam("modCajSewaan") == null ? "": getParam("modCajSewaan"));
			beanMaklumatPerjanjian.addElement(hashPerjanjian);
			this.context.put("BeanMaklumatPerjanjian", beanMaklumatPerjanjian);
			
			this.context.put("selectUrusan", HTML.SelectUrusanPHPPenyewaan("socUrusan", Long.parseLong(idUrusan), "", " onChange=\"doChangeUrusan();\""));
			this.context.put("selectSuburusan", HTML.SelectSuburusanByIdUrusan(idUrusan, "socSuburusan", Long.parseLong(idSuburusan), "", " "));
			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(idNegeri), ""," onChange=\"doChangeNegeri();\""));
			this.context.put("selectBandar", HTML.SelectBandarByNegeri(idNegeri, "socBandar", Long.parseLong(idBandar), ""));
		}

		this.context.put("idLuas", idLuas);
		this.context.put("idFail", idFail);
		this.context.put("idHasil", idHasil);
		this.context.put("idPemohon", idPemohon);
				
		this.context.put("step", step);
		
		return vm;
	}

}
