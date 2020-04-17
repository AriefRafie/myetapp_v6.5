package ekptg.view.pdt;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.pdt.FrmDaftarAktaData;
import ekptg.model.pdt.FrmKemaskiniAktaData;

public class PendaftaranAkta extends AjaxBasedModule {
	private static Logger myLogger = Logger.getLogger(PendaftaranAkta.class);
	
	FrmDaftarAktaData akta = new FrmDaftarAktaData();
	FrmKemaskiniAktaData kemaskiniAkta = new FrmKemaskiniAktaData();
	
	public String doTemplate2() throws Exception {

		String vm = "";
		String action = getParam("action");
		String hitbutton = getParam("hitbutton");
		String tabId = getParam("tabId");
		String idSeksyen = getParam("socSeksyen");
		String idFail = getParam("socNoFail");
		String checked = getParam("checked");
		this.context.put("checked",checked);
		String radButton = getParam("update");
		this.context.put("update",radButton);
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		

		if (tabId.equals("")) {
			tabId = "0";
		}

		String noAkta = "";
		String namaAkta = "";
		String tarikhKuatkuasa = "";
		String noWarta = "";
		String tarikhWarta = "";
		String catatan = "";
		String tarikhMansuh = "";
		
		Vector list = new Vector();
		Vector listSenaraiAkta = new Vector();
		list.clear();

		HttpSession session = this.request.getSession();
		
		if(action.equals("carian")){
			vm = "app/pdt/frmDaftarAkta.jsp";
			if (!"".equals(getParam("txtNoAktaC")))
				noAkta = getParam("txtNoAktaC");
			if (!"".equals(getParam("txtNamaAktaC")))
				namaAkta = getParam("txtNamaAktaC");
			if (!"".equals(getParam("txtTarikhKuatkuasaC")))
				tarikhKuatkuasa = getParam("txtTarikhKuatkuasaC");

			
			akta.setCarianPaparAkta(noAkta,namaAkta, tarikhKuatkuasa);
			
			listSenaraiAkta = akta.getCarianPaparAkta();
			
			prepareItemForDisplay(session, listSenaraiAkta, 10, "first");
			
			this.context.put("txtNoAktaC", noAkta);
			this.context.put("txtNamaAktaC", namaAkta);
			this.context.put("txtTarikhKuatkuasaC", tarikhKuatkuasa);
		}
		else
		if ("kembali".equals(action) || "batal".equals(action)) {

			vm = "app/pdt/frmDaftarAkta.jsp";

			akta.setCarianPaparAkta(noAkta,namaAkta,tarikhKuatkuasa);
			list = akta.getCarianPaparAkta();
			//prepareItemForDisplay(session, list, 10, "first");
			this.context.put("txtNoAktaC", noAkta);
			this.context.put("txtNamaAktaC", namaAkta);
			this.context.put("txtTarikhKuatkuasaC", tarikhKuatkuasa);

		} else
		// tambah Akta baru
		if ("tambah".equals(action)) {
			this.context.put("mode", "new");
			this.context.put("readOnly", "");
			this.context.put("disabled", "");
			vm = "app/pdt/frmKemaskiniAkta.jsp";

			this.context.put("idAkta", "");
			noAkta = getParam("txtNoAkta1");
			this.context.put("txtNoAkta", noAkta);
			namaAkta = getParam("txtNamaAkta1");
			this.context.put("txtNamaAkta", namaAkta);
			tarikhKuatkuasa = getParam("txtTarikhKuatkuasa1");
			this.context.put("txtTarikhKuatkuasa", tarikhKuatkuasa);
			tarikhMansuh = getParam("txtTarikhMansuh");
			this.context.put("txtTarikhMansuh", tarikhMansuh);

			if ("".equals(checked)) {
				checked = "terbuka";
			}
			if ("terbuka".equals(checked)) {
				this.context.put("radioChecked1", "checked");
				this.context.put("radioChecked2", "");
			} else if ("sulit".equals(checked)) {
				this.context.put("radioChecked1", "");
				this.context.put("radioChecked2", "checked");
			}

			this.context.put("selectSeksyen", HTML.SelectSeksyen("socSeksyen",Utils.parseLong(idSeksyen), ""));
			this.context.put("selectFail", HTML.SelectFail("socNoFail", Utils.parseLong(idFail), ""));

			catatan = getParam("txtCatatan");
			this.context.put("txtCatatan", catatan);
			noWarta = getParam("txtNoWarta");
			this.context.put("txtNoWarta", noWarta);
			tarikhWarta = getParam("txtTarikhWarta");
			this.context.put("txtTarikhWarta", tarikhWarta);
			this.context.put("txtTarikhDaftar", sdf.format(now));

		} // papar
		else if ("papar".equals(action)) {

			vm = "app/pdt/frmKemaskiniAkta.jsp";
			this.context.put("mode", "view");
			// update Akta baru
			if ("update".equals(hitbutton)) {

				if (tabId.equals("0")) {
					// update tab Akta
					String user_id = (String) session.getAttribute("_ekptg_user_id");
					Hashtable hAktaUpdate = new Hashtable();
					if (!getParam("idAkta").equals("")) {

						hAktaUpdate.put("idAkta", getParam("idAkta"));
						hAktaUpdate.put("idKemaskini", Integer.parseInt(user_id));
						hAktaUpdate.put("tarikhKemaskini",getParam("txtTarikhDaftar1"));
						hAktaUpdate.put("noAkta", Integer.parseInt(getParam("txtNoAkta1")));
						hAktaUpdate.put("namaAkta",getParam("txtNamaAkta1"));
						hAktaUpdate.put("tarikhKuatkusa",getParam("txtTarikhKuatkuasa"));
						hAktaUpdate.put("noWarta",getParam("txtNoWarta") == null|| getParam("txtNoWarta") == "" ? "": Integer.parseInt(getParam("txtNoWarta")));
						hAktaUpdate.put("tarikhMansuh",getParam("txtTarikhMansuh"));
						hAktaUpdate.put("tarikhWarta",getParam("txtTarikhWarta"));
						hAktaUpdate.put("catatan", getParam("txtCatatan"));
						hAktaUpdate.put("tarikhDaftar",getParam("txtTarikhDaftar"));
						hAktaUpdate.put("idFail",getParam("socNoFail") == null|| getParam("socNoFail") == "" ? "": Integer.parseInt(getParam("socNoFail")));
			
						// if user click untuk tukar taraf Akta
						if(radButton.equals("click")){
							//System.out.println("tukar taraf baru");
							if ("terbuka".equals(checked)) {
								this.context.put("radioChecked1", "checked");
								this.context.put("radioChecked2", "");
								this.context.put("checked","terbuka");
								hAktaUpdate.put("idKeselamatan","1");
								
							}else 
								if ("sulit".equals(checked)) {
									this.context.put("radioChecked1", "");
									this.context.put("radioChecked2", "checked");
									this.context.put("checked","sulit");
									hAktaUpdate.put("idKeselamatan","3");
								}
							this.context.put("update","");
						}else
							
						// if user click untuk maintain taraf Akta	
						if(radButton.equals("")){
							//System.out.println("maintain taraf lame");
							if(checked.equals("sulit"))
								hAktaUpdate.put("idKeselamatan","3");
							else
								hAktaUpdate.put("idKeselamatan","1");
						}
						
						hAktaUpdate.put("idSeksyen",getParam("socSeksyen") == null|| getParam("socSeksyen") == "" ? "": Integer.parseInt(getParam("socSeksyen")));
						kemaskiniAkta.updateAkta(hAktaUpdate);

						// papar semula selepas update Akta
						this.context.put("readOnly", "readonly");
						this.context.put("disabled", "disabled");
						view_modeAkta(session);
						list = akta.getPaparAkta();
						Hashtable hPapar = (Hashtable) list.get(0);
						this.context.put("idAkta", hPapar.get("idAkta").toString());
						this.context.put("txtNoAkta", hPapar.get("noAkta").toString());
						this.context.put("txtNamaAkta", hPapar.get("namaAkta").toString());
						this.context.put("txtTarikhKuatkuasa", hPapar.get("tarikhKuatkuasa").toString());
						this.context.put("txtTarikhMansuh", hPapar.get("tarikhMansuh").toString());
						this.context.put("txtNoWarta", hPapar.get("noWarta").toString());
						this.context.put("txtTarikhWarta", hPapar.get("tarikhWarta").toString());
						
						if (hPapar.get("sorTerbuka").toString().equals("1")) {
							this.context.put("radioChecked2", "");
							this.context.put("radioChecked1", "checked");

						} else {
							this.context.put("radioChecked1", "");
							this.context.put("radioChecked2", "checked");
							this.context.put("idPenggal", "");
						}
						this.context.put("selectSeksyen", HTML.SelectSeksyen("socSeksyen", Utils.parseLong(hPapar.get("idseksyen").toString()), "disabled"));
						this.context.put("selectFail", HTML.SelectFail("socNoFail", Utils.parseLong(hPapar.get("idFail").toString()), "disabled"));
						this.context.put("txtCatatan", hPapar.get("catatan").toString());
						this.context.put("txtTarikhDaftar", hPapar.get("tarikhDaftar").toString());
					}
				}else if (tabId.equals("1")) {
					// update tab penggal
					String user_id = (String) session.getAttribute("_ekptg_user_id");
					if (!getParam("idPenggal").equals("")) {
						Hashtable hPenggalUpdate = new Hashtable();
						hPenggalUpdate.put("idAkta", getParam("idAkta"));
						hPenggalUpdate.put("noPenggal",getParam("txtNoPenggal"));
						hPenggalUpdate.put("idPenggal", Integer.parseInt(getParam("idPenggal")));
						hPenggalUpdate.put("noPenggal", Integer.parseInt(getParam("txtNoPenggal")));
						hPenggalUpdate.put("tajukPenggal",getParam("txtTajukPenggal"));
						hPenggalUpdate.put("idKemaskini", Integer.parseInt(user_id));
						hPenggalUpdate.put("tarikhKemaskini", sdf.format(now));
						kemaskiniAkta.updatePenggal(hPenggalUpdate);

						// papar semula selepas update penggal
						vm = "app/pdt/frmKemaskiniAkta.jsp";
						this.context.put("readOnly2", "readonly");
						this.context.put("readOnly", "");
						this.context.put("mode", "new");
						akta.setSenaraiPenggal(Integer.parseInt(getParam("idAkta")));
						list = akta.getPaparPenggal();
						//prepareItemForDisplay(session, list, 10, "first");
						
						// clearkn value untuk simpan baru
						this.context.put("idPenggal", ""); 
						this.context.put("Penggal", list);
						this.context.put("txtNoPenggal", "");
						this.context.put("txtTajukPenggal", "");

					}
				} else if (tabId.equals("2")) {
					// update tab bahagian
					String user_id = (String) session.getAttribute("_ekptg_user_id");
					if (!getParam("idBahagian").equals("")) {
						Hashtable hBahagianUpdate = new Hashtable();
						hBahagianUpdate.put("idAkta", getParam("idAkta"));
						hBahagianUpdate.put("idBahagian",(getParam("idBahagian")));
						hBahagianUpdate.put("idAktaPenggal",getParam("socPenggal") == null|| getParam("socPenggal") == "" ? "": Integer.parseInt(getParam("socPenggal")));
						hBahagianUpdate.put("noBahagian", Integer.parseInt(getParam("txtNoBahagian")));
						hBahagianUpdate.put("tajukBahagian",getParam("txtTajukBahagian"));
						hBahagianUpdate.put("idKemaskini", Integer.parseInt(user_id));
						hBahagianUpdate.put("tarikhKemaskini", sdf.format(now));
						kemaskiniAkta.updateBahagian(hBahagianUpdate);

						// papar semula selepas update bahagian
						vm = "app/pdt/frmKemaskiniAkta.jsp";
						this.context.put("readOnly2", "readonly");
						this.context.put("readOnly", "");
						this.context.put("mode", "new");
						akta.setSenaraiBahagian(Integer.parseInt(getParam("idAkta")));
						list = akta.getPaparBahagian();
						//prepareItemForDisplay(session, list, 10, "first");
						
						// clearkn value untuk simpan baru
						this.context.put("idBahagian", ""); 
						this.context.put("selectPenggal", HTML.SelectPenggalAkta(Integer.parseInt(getParam("idAkta")),"socPenggal", null, null, null));
						this.context.put("txtNoBahagian", "");
						this.context.put("txtTajukBahagian", "");

					}
				}
				else if (tabId.equals("3")) {
					// update tab bab
					String user_id = (String) session
					.getAttribute("_ekptg_user_id");
					if (!getParam("idBab").equals("")) {
						Hashtable hBabUpdate = new Hashtable();
						hBabUpdate.put("idAkta", getParam("idAkta"));
						hBabUpdate.put("idBab",(getParam("idBab")));
						hBabUpdate.put("idAktaPenggal",getParam("socPenggal") == null|| getParam("socPenggalB") == "" ? "": Integer.parseInt(getParam("socPenggalB")));
						hBabUpdate.put("idAktaBahagian",getParam("socBahagian") == null|| getParam("socBahagian") == "" ? "": Integer.parseInt(getParam("socBahagian")));
						hBabUpdate.put("noBab", Integer.parseInt(getParam("txtNoBab")));
						hBabUpdate.put("tajukBab",getParam("txtTajukBab"));
						hBabUpdate.put("idKemaskini",user_id);
						hBabUpdate.put("tarikhKemaskini", sdf.format(now));
						kemaskiniAkta.updateBab(hBabUpdate);
						
						// papar semula selepas update bab
						vm = "app/pdt/frmKemaskiniAkta.jsp";
						this.context.put("readOnly2", "readonly");
						this.context.put("readOnly", "");
						this.context.put("mode", "new");
						akta.setSenaraiBab(Integer.parseInt(getParam("idAkta")));
						list = akta.getPaparBab();
						//prepareItemForDisplay(session, list, 10, "first");;
						
						// clearkn value untuk simpan baru
						this.context.put("idBab", ""); 
						this.context.put("selectPenggalB", HTML.SelectPenggalAkta(Integer.parseInt(getParam("idAkta")), "socPenggalB",null, null, null));
						this.context.put("selectBahagian", HTML.SelectBahagianAkta(Integer.parseInt(getParam("idAkta")), "socBahagian",null, null, null));
						this.context.put("txtNoBab", "");
						this.context.put("txtTajukBab", "");

					}
				}
				else if (tabId.equals("4")) {
					// update tab seksyen
					String user_id = (String) session.getAttribute("_ekptg_user_id");
					if (!getParam("idSeksyen").equals("")) {
						if (getParam("txtSeksyen") != "") {
							Hashtable hSeksyenUpdate = new Hashtable();
							hSeksyenUpdate.put("idAkta", getParam("idAkta"));
							hSeksyenUpdate.put("idSeksyen",(getParam("idSeksyen")));
							hSeksyenUpdate.put("idAktaPenggal",getParam("socPenggalS") == null|| getParam("socPenggalS") == "" ? "": Integer.parseInt(getParam("socPenggalS")));
							hSeksyenUpdate.put("idAktaBahagian",getParam("socBahagianS") == null|| getParam("socBahagianS") == "" ? "": Integer.parseInt(getParam("socBahagianS")));
							hSeksyenUpdate.put("idAktaBab",getParam("socBab") == null|| getParam("socBab") == "" ? "": Integer.parseInt(getParam("socBab")));
							hSeksyenUpdate.put("seksyen",getParam("txtSeksyen"));
							hSeksyenUpdate.put("idKemaskini", user_id);
							hSeksyenUpdate.put("tarikhKemaskini", sdf.format(now));
							kemaskiniAkta.updateSeksyen(hSeksyenUpdate);
							
							// papar semula selepas update seksyen
							vm = "app/pdt/frmKemaskiniAkta.jsp";
							this.context.put("readOnly2", "readonly");
							this.context.put("readOnly", "");
							this.context.put("mode", "new");
							akta.setSenaraiSeksyen(Integer.parseInt(getParam("idAkta")));
							list = akta.getPaparSeksyen();
							this.context.put("Seksyen", list);
							
							// clearkn value untuk simpan baru
							this.context.put("idSeksyen", ""); 
							this.context.put("selectPenggalS", HTML.SelectPenggalAkta(Integer.parseInt(getParam("idAkta")), "socPenggalS",null, null, null));
							this.context.put("selectBahagianS", HTML.SelectBahagianAkta(Integer.parseInt(getParam("idAkta")), "socBahagianS",null, null, null));
							this.context.put("selectBab", HTML.SelectBabAkta(Integer.parseInt(getParam("idAkta")), "socBab",null, null, null));				
							this.context.put("txtSeksyen","");
						}
					}
				}else if (tabId.equals("5")) {
					// update tab subseksyen
					String user_id = (String) session.getAttribute("_ekptg_user_id");
					if (!getParam("idSubSeksyen").equals("")) {
						if (getParam("txtSubseksyen") != "") {
							Hashtable hSubSeksyenUpdate = new Hashtable();
							hSubSeksyenUpdate.put("idAkta", getParam("idAkta"));
							hSubSeksyenUpdate.put("idSubSeksyen",(getParam("idSubSeksyen")));
							hSubSeksyenUpdate.put("idJadual",(getParam("idJadual")));
							hSubSeksyenUpdate.put("idAktaPenggal",getParam("socPenggalSS") == null|| getParam("socPenggalSS") == "" ? "": Integer.parseInt(getParam("socPenggalSS")));
							hSubSeksyenUpdate.put("idAktaBahagian",getParam("socBahagianSS") == null|| getParam("socBahagianSS") == "" ? "": Integer.parseInt(getParam("socBahagianSS")));
							hSubSeksyenUpdate.put("idAktaBab",getParam("socBabSS") == null|| getParam("socBabSS") == "" ? "": Integer.parseInt(getParam("socBabSS")));
							hSubSeksyenUpdate.put("idAktaSeksyen",getParam("socSeksyenSS") == null|| getParam("socSeksyenSS") == "" ? "": Integer.parseInt(getParam("socSeksyenSS")));
							hSubSeksyenUpdate.put("subSeksyen",getParam("txtSubseksyen"));
							hSubSeksyenUpdate.put("idKemaskini", user_id);
							hSubSeksyenUpdate.put("tarikhKemaskini", sdf.format(now));
							kemaskiniAkta.updateSubSeksyen(hSubSeksyenUpdate);
						
							// papar semula selepas update subseksyen
							vm = "app/pdt/frmKemaskiniAkta.jsp";
							this.context.put("readOnly2", "readonly");
							this.context.put("readOnly", "");
							this.context.put("mode", "new");
							akta.setSenaraiSubSeksyen(Integer.parseInt(getParam("idAkta")));
							list = akta.getPaparSubSeksyen();
							this.context.put("SubSeksyen", list);
							
							// clearkn value untuk simpan baru
							this.context.put("selectPenggalSS", HTML.SelectPenggalAkta(Integer.parseInt(getParam("idAkta")), "socPenggalSS",null, null, null));
							this.context.put("selectBahagianSS", HTML.SelectBahagianAkta(Integer.parseInt(getParam("idAkta")), "socBahagianSS",null, null, null));
							this.context.put("selectBabSS", HTML.SelectBabAkta(Integer.parseInt(getParam("idAkta")), "socBabSS",null, null, null));	
							this.context.put("selectSeksyenSS", HTML.SelectSeksyenAkta(Integer.parseInt(getParam("idAkta")), "socSeksyenSS",null, null, null));		
							this.context.put("txtSubseksyen","");
						}
					}
				}else if (tabId.equals("6")) {
					// update tab para
					String user_id = (String) session.getAttribute("_ekptg_user_id");
					if (!getParam("idPara").equals("")) {
						if (getParam("txtPara") != "") {
							Hashtable hParaUpdate = new Hashtable();
							hParaUpdate.put("idAkta", getParam("idAkta"));
							hParaUpdate.put("idJadual",(getParam("idJadual")));
							hParaUpdate.put("idPara",(getParam("idPara")));
							hParaUpdate.put("idAktaPenggal",getParam("socPenggalP") == null|| getParam("socPenggalP") == "" ? "": Integer.parseInt(getParam("socPenggalP")));
							hParaUpdate.put("idAktaBahagian",getParam("socBahagianP") == null|| getParam("socBahagianP") == "" ? "": Integer.parseInt(getParam("socBahagianP")));
							hParaUpdate.put("idAktaBab",getParam("socBabP") == null|| getParam("socBabP") == "" ? "": Integer.parseInt(getParam("socBabP")));
							hParaUpdate.put("idAktaSeksyen",getParam("socSeksyenP") == null|| getParam("socSeksyenP") == "" ? "": Integer.parseInt(getParam("socSeksyenP")));
							hParaUpdate.put("idAktaSubSeksyen",getParam("socSubSeksyen") == null|| getParam("socSubSeksyen") == "" ? "": Integer.parseInt(getParam("socSubSeksyen")));
							hParaUpdate.put("para",getParam("txtPara"));
							hParaUpdate.put("jadual",getParam("txtJadual"));
							hParaUpdate.put("idKemaskini", user_id);
							hParaUpdate.put("tarikhKemaskini", sdf.format(now));
							kemaskiniAkta.updatePara(hParaUpdate);
						
							// papar semula selepas update para
							vm = "app/pdt/frmKemaskiniAkta.jsp";
							this.context.put("readOnly2", "readonly");
							this.context.put("readOnly", "");
							this.context.put("mode", "new");
							akta.setSenaraiPara(Integer.parseInt(getParam("idAkta")));
							list = akta.getPaparPara();
							this.context.put("Para", list);
							
							// clearkn value untuk simpan baru
							this.context.put("selectPenggalP", HTML.SelectPenggalAkta(Integer.parseInt(getParam("idAkta")), "selectPenggalP",null, null, null));
							this.context.put("selectBahagianP", HTML.SelectBahagianAkta(Integer.parseInt(getParam("idAkta")), "selectBahagianP",null, null, null));
							this.context.put("selectBabP", HTML.SelectBabAkta(Integer.parseInt(getParam("idAkta")), "selectBabP",null, null, null));	
							this.context.put("selectSeksyenP", HTML.SelectSeksyenAkta(Integer.parseInt(getParam("idAkta")), "selectSeksyenP",null, null, null));		
							this.context.put("selectSubSeksyen", HTML.SelectSubSeksyenAkta(Integer.parseInt(getParam("idAkta")), "selectSubSeksyen",null, null, null));
							this.context.put("txtPara", "");
							this.context.put("txtJadual", "");
						}
					}
				}else if (tabId.equals("7")) {
					// update tab subpara
					String user_id = (String) session.getAttribute("_ekptg_user_id");
					if (!getParam("idSubPara").equals("")) {
						if (getParam("txtSubPara") != "") {
							Hashtable hSubParaUpdate = new Hashtable();
							hSubParaUpdate.put("idAkta", getParam("idAkta"));
							hSubParaUpdate.put("idSubPara",(getParam("idSubPara")));
							hSubParaUpdate.put("idJadual",(getParam("idJadual")));
							hSubParaUpdate.put("idPara",(getParam("idPara")));
							hSubParaUpdate.put("idAktaPenggal",getParam("socPenggalSP") == null|| getParam("socPenggalSP") == "" ? "": Integer.parseInt(getParam("socPenggalSP")));
							hSubParaUpdate.put("idAktaBahagian",getParam("socBahagianSP") == null|| getParam("socBahagianSP") == "" ? "": Integer.parseInt(getParam("socBahagianSP")));
							hSubParaUpdate.put("idAktaBab",getParam("socBabSP") == null|| getParam("socBabSP") == "" ? "": Integer.parseInt(getParam("socBabSP")));
							hSubParaUpdate.put("idAktaSeksyen",getParam("socSeksyenSP") == null|| getParam("socSeksyenSP") == "" ? "": Integer.parseInt(getParam("socSeksyenSP")));
							hSubParaUpdate.put("idAktaSubSeksyen",getParam("socSubSeksyenSP") == null|| getParam("socSubSeksyenSP") == "" ? "": Integer.parseInt(getParam("socSubSeksyenSP")));
							hSubParaUpdate.put("idAktaPara",getParam("socPara") == null|| getParam("socPara") == "" ? "": Integer.parseInt(getParam("socPara")));
							hSubParaUpdate.put("idJadual",getParam("socJadual") == null|| getParam("socJadual") == "" ? "": Integer.parseInt(getParam("socJadual")));
							hSubParaUpdate.put("subPara",getParam("txtSubPara"));
							hSubParaUpdate.put("idKemaskini", user_id);
							hSubParaUpdate.put("tarikhKemaskini", sdf.format(now));
							kemaskiniAkta.updateSubPara(hSubParaUpdate);
						
							// papar semula selepas update subpara
							vm = "app/pdt/frmKemaskiniAkta.jsp";
							this.context.put("readOnly2", "readonly");
							this.context.put("readOnly", "");
							this.context.put("mode", "new");
							akta.setSenaraiSubPara(Integer.parseInt(getParam("idAkta")));
							list = akta.getPaparSubPara();
							this.context.put("SubPara", list);
							
							// clearkn value untuk simpan baru
							this.context.put("selectPenggalSP", HTML.SelectPenggalAkta(Integer.parseInt(getParam("idAkta")), "selectPenggalSP",null, null, null));
							this.context.put("selectBahagianSP", HTML.SelectBahagianAkta(Integer.parseInt(getParam("idAkta")), "selectBahagianSP",null, null, null));
							this.context.put("selectBabSP", HTML.SelectBabAkta(Integer.parseInt(getParam("idAkta")), "selectBabSP",null, null, null));	
							this.context.put("selectSeksyenSP", HTML.SelectSeksyenAkta(Integer.parseInt(getParam("idAkta")), "selectSeksyenSP",null, null, null));		
							this.context.put("selectSubSeksyenSP", HTML.SelectSubSeksyenAkta(Integer.parseInt(getParam("idAkta")), "selectSubSeksyenSP",null, null, null));
							this.context.put("selectPara", HTML.SelectParaAkta(Integer.parseInt(getParam("idAkta")), "selectPara",null, null, null));
							this.context.put("selectJadual", HTML.SelectJadualAkta(Integer.parseInt(getParam("idAkta")), "selectJadual",null, null, null));
							this.context.put("txtSubPara", "");
						}
					}
				}else if (tabId.equals("8")) {
					// update tab jadualpara
					String user_id = (String) session.getAttribute("_ekptg_user_id");
					if (!getParam("idJadualPara").equals("")) {
						if (getParam("txtNamaJadual") != "") {
							Hashtable hJadualParaUpdate = new Hashtable();
							hJadualParaUpdate.put("idAkta", getParam("idAkta"));
							hJadualParaUpdate.put("idJadualPara",(getParam("idJadualPara")));
							hJadualParaUpdate.put("idJadual",(getParam("idJadual")));
							hJadualParaUpdate.put("idJadual",getParam("socJadualJP") == null|| getParam("socJadualJP") == "" ? "": Integer.parseInt(getParam("socJadualJP")));
							hJadualParaUpdate.put("para",getParam("txtParaJP"));
							hJadualParaUpdate.put("namaJadual",getParam("txtNamaJadual"));
							hJadualParaUpdate.put("idKemaskini", user_id);
							hJadualParaUpdate.put("tarikhKemaskini", sdf.format(now));
							kemaskiniAkta.updateJadualPara(hJadualParaUpdate);
						
							// papar semula selepas update jadualpara
							vm = "app/pdt/frmKemaskiniAkta.jsp";
							this.context.put("readOnly2", "readonly");
							this.context.put("readOnly", "");
							this.context.put("mode", "new");
							akta.setSenaraiJadualPara(Integer.parseInt(getParam("idAkta")));
							list = akta.getPaparJadualPara();
							this.context.put("JadualPara", list);
							
							// clearkn value untuk simpan baru
							this.context.put("selectJadualJP", HTML.SelectJadualAkta(Integer.parseInt(getParam("idAkta")), "selectJadualJP",null, null, null));
							this.context.put("txtParaJP", "");
							this.context.put("txtNamaJadual", "");
						}
					}
				}else if (tabId.equals("9")) {
					// update tab jadualsubpara
					String user_id = (String) session.getAttribute("_ekptg_user_id");
					if (!getParam("idJadualSubPara").equals("")) {
						if (getParam("txtSubParaJSP") != "") {
							Hashtable hJadualSubParaUpdate = new Hashtable();
							hJadualSubParaUpdate.put("idAkta", getParam("idAkta"));
							hJadualSubParaUpdate.put("idJadualSubPara",(getParam("idJadualSubPara")));
							hJadualSubParaUpdate.put("idJadual",getParam("socJadualJSP") == null|| getParam("socJadualJSP") == "" ? "": Integer.parseInt(getParam("socJadualJSP")));
							hJadualSubParaUpdate.put("idJadualPara",getParam("socParaJSP") == null|| getParam("socParaJSP") == "" ? "": Integer.parseInt(getParam("socParaJSP")));
							hJadualSubParaUpdate.put("subPara",getParam("txtSubParaJSP"));
							hJadualSubParaUpdate.put("idKemaskini", user_id);
							hJadualSubParaUpdate.put("tarikhKemaskini", sdf.format(now));
							kemaskiniAkta.updateJadualSubPara(hJadualSubParaUpdate);
						
							// papar semula selepas update jadualsubpara
							vm = "app/pdt/frmKemaskiniAkta.jsp";
							this.context.put("readOnly2", "readonly");
							this.context.put("readOnly", "");
							this.context.put("mode", "new");
							akta.setSenaraiJadualSubPara(Integer.parseInt(getParam("idAkta")));
							list = akta.getPaparJadualSubPara();
							this.context.put("JadualSubPara", list);
							
							// clearkn value untuk simpan baru
							this.context.put("selectJadualJSP", HTML.SelectJadualAkta(Integer.parseInt(getParam("idAkta")), "selectJadualJSP",null, null, null));//eda
							this.context.put("selectParaJSP", HTML.SelectParaJadualParaAkta(Integer.parseInt(getParam("idAkta")), "selectParaJSP",null, null, null));
							this.context.put("txtSubParaJSP", "");
						}
					}
				}else if (tabId.equals("10")) {
					// update tab jadualsubsubpara
					String user_id = (String) session.getAttribute("_ekptg_user_id");
					if (!getParam("idJadualSubSubPara").equals("")) {
						if (getParam("txtSubSubPara") != "") {
							Hashtable hJadualSubSubParaUpdate = new Hashtable();
							hJadualSubSubParaUpdate.put("idAkta", getParam("idAkta"));
							hJadualSubSubParaUpdate.put("idJadualSubSubPara",(getParam("idJadualSubSubPara")));
							hJadualSubSubParaUpdate.put("idJadual",getParam("socJadualJSSP") == null|| getParam("socJadualJSSP") == "" ? "": Integer.parseInt(getParam("socJadualJSSP")));
							hJadualSubSubParaUpdate.put("idJadualPara",getParam("socParaJSSP") == null|| getParam("socParaJSSP") == "" ? "": Integer.parseInt(getParam("socParaJSSP")));
							hJadualSubSubParaUpdate.put("idJadualSubPara",getParam("socSubParaJSSP") == null|| getParam("socSubParaJSSP") == "" ? "": Integer.parseInt(getParam("socSubParaJSSP")));
							hJadualSubSubParaUpdate.put("subSubPara",getParam("txtSubSubPara"));
							hJadualSubSubParaUpdate.put("idKemaskini", user_id);
							hJadualSubSubParaUpdate.put("tarikhKemaskini", sdf.format(now));
							kemaskiniAkta.updateJadualSubSubPara(hJadualSubSubParaUpdate);
						
							// papar semula selepas update jadualsubsubpara
							vm = "app/pdt/frmKemaskiniAkta.jsp";
							this.context.put("readOnly2", "readonly");
							this.context.put("readOnly", "");
							this.context.put("mode", "new");
							akta.setSenaraiJadualSubSubPara(Integer.parseInt(getParam("idAkta")));
							list = akta.getPaparJadualSubSubPara();
							this.context.put("JadualSubSubPara", list);
							
							// clearkn value untuk simpan baru
							this.context.put("selectJadualJSSP", HTML.SelectJadualAkta(Integer.parseInt(getParam("idAkta")), "selectJadualJSSP",null, null, null));
							this.context.put("selectParaJSSP", HTML.SelectParaJadualParaAkta(Integer.parseInt(getParam("idAkta")), "selectParaJSSP",null, null, null));
							this.context.put("selectSubParaJSSP", HTML.SelectSubParaJadualParaAkta(Integer.parseInt(getParam("idAkta")), "selectSubParaJSSP",null, null, null));
							this.context.put("txtSubSubPara", "");
						}
					}
				}
			}
			// papar next and previous data
			else if(("next".equals(hitbutton))|| ("previous".equals(hitbutton))){
				this.context.put("mode","new");
  	    		if(tabId.equals("1")){	
  	    			vm = "app/pdt/frmKemaskiniAkta.jsp";
  					akta.setSenaraiPenggal(Integer.parseInt(getParam("idAkta")));
  					list = akta.getPaparPenggal();
  					//prepareItemForDisplay(session, list, 10, hitbutton);
  					//System.out.println("penggal next previous");
  	    		}
  	    		else if(tabId.equals("2")){
  	    			vm = "app/pdt/frmKemaskiniAkta.jsp";
  					akta.setSenaraiBahagian(Integer.parseInt(getParam("idAkta")));
  					list = akta.getPaparBahagian();
  					//prepareItemForDisplay(session, list, 10, hitbutton);
  	    		}
  	    		else
  	    		{
			  	vm = "app/pdt/frmDaftarAkta.jsp";
				akta.setCarianPaparAkta(noAkta,namaAkta,tarikhKuatkuasa);
				list = akta.getPaparAkta();
  				//prepareItemForDisplay(session, list, 10, hitbutton);
  	    		}
  	    	}
			// papar Akta by id
			else if (tabId.equals("0")) {
				this.context.put("readOnly", "readonly");
				this.context.put("disabled", "disabled");
				view_modeAkta(session);
				list = akta.getPaparAkta();
				Hashtable hPapar = (Hashtable) list.get(0);
				this.context.put("idAkta", hPapar.get("idAkta").toString());
				this.context.put("txtNoAkta", hPapar.get("noAkta").toString());
				this.context.put("txtNamaAkta", hPapar.get("namaAkta").toString());
				this.context.put("txtTarikhKuatkuasa", hPapar.get("tarikhKuatkuasa").toString());
				this.context.put("txtTarikhMansuh", hPapar.get("tarikhMansuh").toString());
				this.context.put("txtNoWarta", hPapar.get("noWarta").toString());
				this.context.put("txtTarikhWarta", hPapar.get("tarikhWarta").toString());
				if (hPapar.get("sorTerbuka").toString().equals("1")) {
					this.context.put("radioChecked2", "");
					this.context.put("radioChecked1", "checked");

				} else {
					this.context.put("radioChecked1", "");
					this.context.put("radioChecked2", "checked");
				}
				
				this.context.put("idPenggal", ""); // clearkn value tok add penggal baru
				this.context.put("idBahagian", "");  // clearkn value tok add bahagian baru
				this.context.put("idBab", "");    // clearkn value tok add bab baru
				this.context.put("idSeksyen", ""); // clearkn value tok add seksyen baru
				this.context.put("idSubSeksyen", ""); // clearkn value tok add subseksyen baru
				this.context.put("idPara", ""); // clearkn value tok add para baru
				this.context.put("idJadual", ""); // clearkn value tok add jadual baru
				this.context.put("idSubPara", ""); // clearkn value tok add subpara baru
				this.context.put("idJadualPara", ""); // clearkn value tok add jadualpara baru
				this.context.put("idJadualSubPara", ""); // clearkn value tok add jadualsubpara baru
				this.context.put("idJadualSubSubPara", ""); // clearkn value tok add jadualsubsubpara baru

				this.context.put("selectSeksyen", HTML.SelectSeksyen("socSeksyen", Utils.parseLong(hPapar.get("idseksyen").toString()), "disabled"));
				this.context.put("selectFail", HTML.SelectFail("socNoFail",Utils.parseLong(hPapar.get("idFail").toString()),"disabled"));
				this.context.put("txtCatatan", hPapar.get("catatan").toString());
				this.context.put("txtTarikhDaftar", hPapar.get("tarikhDaftar").toString());
			}
			// papar penggal by id
			else if ("paparPenggal".equals(hitbutton)) {
				this.context.put("readOnly2", "readonly");
				this.context.put("readOnly", "readonly");
				this.context.put("mode", "view");

				view_modePenggal(session);
				list = akta.getPaparPenggalById();
				Hashtable hPenggal = (Hashtable) list.get(0);
				this.context.put("idPenggal", hPenggal.get("idPenggal").toString());
				this.context.put("txtNoAkta", hPenggal.get("noAkta").toString());
				this.context.put("txtNamaAkta", hPenggal.get("namaAkta").toString());
				this.context.put("txtNoPenggal", hPenggal.get("noPenggal").toString());
				this.context.put("txtTajukPenggal", hPenggal.get("tajukPenggal").toString());
			}
			// papar bahagian by id
			else if ("paparBahagian".equals(hitbutton)) {
				this.context.put("readOnly2", "readonly");
				this.context.put("readOnly", "readonly");
				this.context.put("disabled", "disabled");
				this.context.put("mode", "view");

				view_modeBahagian(session);
				list = akta.getPaparBahagianById();
				Hashtable hBahagian = (Hashtable) list.get(0);
				this.context.put("idPenggal", hBahagian.get("idPenggal").toString());
				this.context.put("idBahagian", hBahagian.get("idBahagian").toString());
				this.context.put("selectPenggal", HTML.SelectPenggalAkta(Integer.parseInt(hBahagian.get("idAkta").toString()), "socPenggal", Utils.parseLong(hBahagian.get("idPenggal").toString()), "disabled", null));
				this.context.put("txtNoBahagian", hBahagian.get("noBahagian").toString());
				this.context.put("txtTajukBahagian", hBahagian.get("tajukBahagian").toString());
			}
			// papar bab by id
			else if ("paparBab".equals(hitbutton)) {
				this.context.put("readOnly2", "readonly");
				this.context.put("readOnly", "readonly");
				this.context.put("disabled", "disabled");
				this.context.put("mode", "view");

				view_modeBab(session);
				list = akta.getPaparBabById();
				Hashtable hBab = (Hashtable) list.get(0);
				this.context.put("idBab", hBab.get("idBab").toString());
				this.context.put("idBahagian", hBab.get("idBahagian").toString());
				this.context.put("selectPenggalB", HTML.SelectPenggalAkta(Integer.parseInt(hBab.get("idAkta").toString()), "socPenggalB", Utils.parseLong(hBab.get("idPenggal").toString()), "disabled", null));
				this.context.put("selectBahagian", HTML.SelectBahagianAkta(Integer.parseInt(hBab.get("idAkta").toString()), "socBahagian", Utils.parseLong(hBab.get("idBahagian").toString()), "disabled", null));
				this.context.put("txtNoBab", hBab.get("noBab").toString());
				this.context.put("txtTajukBab", hBab.get("tajukBab").toString());
			}
			// papar seksyen by id
			else if ("paparSeksyen".equals(hitbutton)) {
				this.context.put("readOnly2", "readonly");
				this.context.put("readOnly", "readonly");
				this.context.put("disabled", "disabled");
				this.context.put("mode", "view");

				view_modeSeksyen(session);
				list = akta.getPaparSeksyenById();
				Hashtable hSeksyen = (Hashtable) list.get(0);
				this.context.put("idSeksyen", hSeksyen.get("idSeksyen").toString());
				this.context.put("idBab", hSeksyen.get("idBab").toString());
				this.context.put("idBahagian", hSeksyen.get("idBahagian").toString());
				this.context.put("selectPenggalS", HTML.SelectPenggalAkta(Integer.parseInt(hSeksyen.get("idAkta").toString()), "socPenggalS", Utils.parseLong(hSeksyen.get("idPenggal").toString()), "disabled", null));
				this.context.put("selectBahagianS", HTML.SelectBahagianAkta(Integer.parseInt(hSeksyen.get("idAkta").toString()), "socBahagianS", Utils.parseLong(hSeksyen.get("idBahagian").toString()), "disabled", null));
				this.context.put("selectBab", HTML.SelectBabAkta(Integer.parseInt(getParam("idAkta")), "socBab",Utils.parseLong(hSeksyen.get("idBab").toString()),"disabled", null));				
				this.context.put("txtSeksyen", hSeksyen.get("seksyen").toString());
			}
			// papar subseksyen by id
			else if ("paparSubSeksyen".equals(hitbutton)) {
				this.context.put("readOnly2", "readonly");
				this.context.put("readOnly", "readonly");
				this.context.put("disabled", "disabled");
				this.context.put("mode", "view");
				
				view_modeSubSeksyen(session);
				list = akta.getPaparSubSeksyenById();
				Hashtable hSubSeksyen = (Hashtable) list.get(0);
				this.context.put("idSubSeksyen", hSubSeksyen.get("idSubSeksyen").toString());
				this.context.put("idSeksyen", hSubSeksyen.get("idSeksyen").toString());
				this.context.put("idBab", hSubSeksyen.get("idBab").toString());
				this.context.put("idBahagian", hSubSeksyen.get("idBahagian").toString());
				this.context.put("selectPenggalSS", HTML.SelectPenggalAkta(Integer.parseInt(hSubSeksyen.get("idAkta").toString()), "socPenggalSS", Utils.parseLong(hSubSeksyen.get("idPenggal").toString()), "disabled", null));
				this.context.put("selectBahagianSS", HTML.SelectBahagianAkta(Integer.parseInt(hSubSeksyen.get("idAkta").toString()), "socBahagianSS", Utils.parseLong(hSubSeksyen.get("idBahagian").toString()), "disabled", null));
				this.context.put("selectBabSS", HTML.SelectBabAkta(Integer.parseInt(getParam("idAkta")), "socBabSS",Utils.parseLong(hSubSeksyen.get("idBab").toString()),"disabled", null));				
				this.context.put("selectSeksyenSS", HTML.SelectSeksyenAkta(Integer.parseInt(getParam("idAkta")), "socSeksyenSS",Utils.parseLong(hSubSeksyen.get("idSeksyen").toString()),"disabled", null));							
				this.context.put("txtSubseksyen", hSubSeksyen.get("subSeksyen").toString());
			}
			// papar para by id
			else if ("paparPara".equals(hitbutton)) {
				this.context.put("readOnly2", "readonly");
				this.context.put("readOnly", "readonly");
				this.context.put("disabled", "disabled");
				this.context.put("mode", "view");
	
				view_modePara(session);
				list = akta.getPaparParaById();
				Hashtable hPara = (Hashtable) list.get(0);
				this.context.put("idPara", hPara.get("idPara").toString());
				this.context.put("idSubSeksyen", hPara.get("idSubSeksyen").toString());
				this.context.put("idSeksyen", hPara.get("idSeksyen").toString());
				this.context.put("idBab", hPara.get("idBab").toString());
				this.context.put("idBahagian", hPara.get("idBahagian").toString());
				this.context.put("selectPenggalP", HTML.SelectPenggalAkta(Integer.parseInt(hPara.get("idAkta").toString()), "socPenggalP", Utils.parseLong(hPara.get("idPenggal").toString()), "disabled", null));
				this.context.put("selectBahagianP", HTML.SelectBahagianAkta(Integer.parseInt(hPara.get("idAkta").toString()), "socBahagianP", Utils.parseLong(hPara.get("idBahagian").toString()), "disabled", null));
				this.context.put("selectBabP", HTML.SelectBabAkta(Integer.parseInt(getParam("idAkta")), "socBabP",Utils.parseLong(hPara.get("idBab").toString()),"disabled", null));				
				this.context.put("selectSeksyenP", HTML.SelectSeksyenAkta(Integer.parseInt(getParam("idAkta")), "socSeksyenP",Utils.parseLong(hPara.get("idSeksyen").toString()),"disabled", null));							
				this.context.put("selectSubSeksyen", HTML.SelectSubSeksyenAkta(Integer.parseInt(getParam("idAkta")), "socSubSeksyen",Utils.parseLong(hPara.get("idSubSeksyen").toString()),"disabled", null));							
				this.context.put("txtPara", hPara.get("para").toString());
				this.context.put("txtJadual", hPara.get("jadual").toString());
			}
			// papar subpara by id
			else if ("paparSubPara".equals(hitbutton)) {
				this.context.put("readOnly2", "readonly");
				this.context.put("readOnly", "readonly");
				this.context.put("disabled", "disabled");
				this.context.put("mode", "view");
	
				view_modeSubPara(session);
				list = akta.getPaparSubParaById();
				Hashtable hSubPara = (Hashtable) list.get(0);
				this.context.put("idSubPara", hSubPara.get("idSubPara").toString());
				this.context.put("idPara", hSubPara.get("idPara").toString());
				this.context.put("idSubSeksyen", hSubPara.get("idSubSeksyen").toString());
				this.context.put("idSeksyen", hSubPara.get("idSeksyen").toString());
				this.context.put("idBab", hSubPara.get("idBab").toString());
				this.context.put("idBahagian", hSubPara.get("idBahagian").toString());
				this.context.put("selectPenggalSP", HTML.SelectPenggalAkta(Integer.parseInt(hSubPara.get("idAkta").toString()), "socPenggalSP", Utils.parseLong(hSubPara.get("idPenggal").toString()), "disabled", null));
				this.context.put("selectBahagianSP", HTML.SelectBahagianAkta(Integer.parseInt(hSubPara.get("idAkta").toString()), "socBahagianSP", Utils.parseLong(hSubPara.get("idBahagian").toString()), "disabled", null));
				this.context.put("selectBabSP", HTML.SelectBabAkta(Integer.parseInt(getParam("idAkta")), "socBabSP",Utils.parseLong(hSubPara.get("idBab").toString()),"disabled", null));				
				this.context.put("selectSeksyenSP", HTML.SelectSeksyenAkta(Integer.parseInt(getParam("idAkta")), "socSeksyenSP",Utils.parseLong(hSubPara.get("idSeksyen").toString()),"disabled", null));							
				this.context.put("selectSubSeksyenSP", HTML.SelectSubSeksyenAkta(Integer.parseInt(getParam("idAkta")), "socSubSeksyenSP",Utils.parseLong(hSubPara.get("idSubSeksyen").toString()),"disabled", null));		
				this.context.put("selectPara", HTML.SelectParaAkta(Integer.parseInt(getParam("idAkta")), "socPara",Utils.parseLong(hSubPara.get("idPara").toString()),"disabled", null));							
				this.context.put("selectJadual", HTML.SelectJadualAkta(Integer.parseInt(getParam("idAkta")), "socJadual",Utils.parseLong(hSubPara.get("idJadual").toString()),"disabled", null));							
				this.context.put("txtSubPara", hSubPara.get("subPara").toString());
			}
			// papar jadualpara by id
			else if ("paparJadualPara".equals(hitbutton)) {
				this.context.put("readOnly2", "readonly");
				this.context.put("readOnly", "readonly");
				this.context.put("disabled", "disabled");
				this.context.put("mode", "view");
	
				view_modeJadualPara(session);
				list = akta.getPaparJadualParaById();
				Hashtable hJadualPara = (Hashtable) list.get(0);
				this.context.put("idJadualPara", hJadualPara.get("idJadualPara").toString());
				this.context.put("idJadual", hJadualPara.get("idJadual").toString());
				this.context.put("selectJadualJP", HTML.SelectJadualAkta(Integer.parseInt(getParam("idAkta")), "socJadualJP",Utils.parseLong(hJadualPara.get("idJadual").toString()),"disabled", null));							
				this.context.put("txtNamaJadual", hJadualPara.get("namaJadual").toString());
				this.context.put("txtParaJP", hJadualPara.get("para").toString());
			}
			// papar jadualsubpara by id
			else if ("paparJadualSubPara".equals(hitbutton)) {
				this.context.put("readOnly2", "readonly");
				this.context.put("readOnly", "readonly");
				this.context.put("disabled", "disabled");
				this.context.put("mode", "view");
	
				view_modeJadualSubPara(session);
				list = akta.getPaparJadualSubParaById();
				Hashtable hJadualSubPara = (Hashtable) list.get(0);
				this.context.put("idJadualSubPara", hJadualSubPara.get("idJadualSubPara").toString());
				this.context.put("selectJadualJSP", HTML.SelectJadualAkta(Integer.parseInt(getParam("idAkta")), "socJadualJSP",Utils.parseLong(hJadualSubPara.get("idJadual").toString()),"disabled", null));							
				this.context.put("selectParaJSP", HTML.SelectParaJadualParaAkta(Integer.parseInt(getParam("idAkta")), "socParaJSP",Utils.parseLong(hJadualSubPara.get("idJadualPara").toString()),"disabled", null));							
				this.context.put("txtSubParaJSP", hJadualSubPara.get("subPara").toString());
			}
			// papar jadualsubsubpara by id
			else if ("paparJadualSubSubPara".equals(hitbutton)) {
				this.context.put("readOnly2", "readonly");
				this.context.put("readOnly", "readonly");
				this.context.put("disabled", "disabled");
				this.context.put("mode", "view");
	
				view_modeJadualSubSubPara(session);
				list = akta.getPaparJadualSubSubParaById();
				Hashtable hJadualSubSubPara = (Hashtable) list.get(0);
				this.context.put("idJadualSubSubPara", hJadualSubSubPara.get("idJadualSubSubPara").toString());
				this.context.put("selectJadualJSSP", HTML.SelectJadualAkta(Integer.parseInt(getParam("idAkta")), "socJadualJSSP",Utils.parseLong(hJadualSubSubPara.get("idJadual").toString()),"disabled", null));							
				this.context.put("selectParaJSSP", HTML.SelectParaJadualParaAkta(Integer.parseInt(getParam("idAkta")), "socParaJSSP",Utils.parseLong(hJadualSubSubPara.get("idJadualPara").toString()),"disabled", null));						
				this.context.put("selectSubParaJSSP", HTML.SelectSubParaJadualParaAkta(Integer.parseInt(getParam("idAkta")), "socSubParaJSSP",Utils.parseLong(hJadualSubSubPara.get("idJadualSubPara").toString()),"disabled", null));							
				this.context.put("txtSubSubPara", hJadualSubSubPara.get("subSubPara").toString());
			}
			// papar semua
			// papar semua senarai penggal
			else if (tabId.equals("1")) {
				vm = "app/pdt/frmKemaskiniAkta.jsp";
				this.context.put("readOnly2", "readonly");
				this.context.put("readOnly", "");
				this.context.put("mode", "new");
				akta.setSenaraiPenggal(Integer.parseInt(getParam("idAkta")));
				list = akta.getPaparPenggal();
				//prepareItemForDisplay(session, list, 10, "first");
				this.context.put("Penggal", list);
				this.context.put("txtNoPenggal", "");
				this.context.put("txtTajukPenggal", "");
			}
			// papar semua senarai bahagian
			else if (tabId.equals("2")) {
				vm = "app/pdt/frmKemaskiniAkta.jsp";
				this.context.put("readOnly2", "readonly");
				this.context.put("readOnly", "");
				this.context.put("mode", "new");

				akta.setSenaraiBahagian(Integer.parseInt(getParam("idAkta")));
				list = akta.getPaparBahagian();
				//prepareItemForDisplay(session, list, 10, "first");
				//this.context.put("Bahagian", list);
				this.context.put("selectPenggal", HTML.SelectPenggalAkta(Integer.parseInt(getParam("idAkta")), "socPenggal",null, null, null));
				this.context.put("txtNoBahagian", "");
				this.context.put("txtTajukBahagian", "");
			}
			// papar semua bab
			else if (tabId.equals("3")) {
				vm = "app/pdt/frmKemaskiniAkta.jsp";
				this.context.put("readOnly2", "readonly");
				this.context.put("readOnly", "");
				this.context.put("mode", "new");

				akta.setSenaraiBab(Integer.parseInt(getParam("idAkta")));
				list = akta.getPaparBab();
				//prepareItemForDisplay(session, list, 10, "first");
				///this.context.put("Bab", list);		
				this.context.put("selectPenggalB", HTML.SelectPenggalAkta(Integer.parseInt(getParam("idAkta")), "socPenggalB",null, null, null));
				this.context.put("selectBahagian", HTML.SelectBahagianAkta(Integer.parseInt(getParam("idAkta")), "socBahagian",null, null, null));
				this.context.put("txtNoBab", "");
				this.context.put("txtTajukBab", "");
			}
			// papar semua seksyen
			else if (tabId.equals("4")) {
				vm = "app/pdt/frmKemaskiniAkta.jsp";
				this.context.put("readOnly2", "readonly");
				this.context.put("readOnly", "");
				this.context.put("mode", "new");

				akta.setSenaraiSeksyen(Integer.parseInt(getParam("idAkta")));
				list = akta.getPaparSeksyen();
				this.context.put("Seksyen", list);
				
				this.context.put("selectPenggalS", HTML.SelectPenggalAkta(Integer.parseInt(getParam("idAkta")), "socPenggalS",null, null, null));
				this.context.put("selectBahagianS", HTML.SelectBahagianAkta(Integer.parseInt(getParam("idAkta")), "socBahagianS",null, null, null));
				this.context.put("selectBab", HTML.SelectBabAkta(Integer.parseInt(getParam("idAkta")), "socBab",null, null, null));				
				this.context.put("txtSeksyen", "");
			}
			// papar semua subseksyen
			else if (tabId.equals("5")) {
				vm = "app/pdt/frmKemaskiniAkta.jsp";
				this.context.put("readOnly2", "readonly");
				this.context.put("readOnly", "");
				this.context.put("mode", "new");

				akta.setSenaraiSubSeksyen(Integer.parseInt(getParam("idAkta")));
				list = akta.getPaparSubSeksyen();
				this.context.put("SubSeksyen", list);
				
				this.context.put("selectPenggalSS", HTML.SelectPenggalAkta(Integer.parseInt(getParam("idAkta")), "socPenggalSS",null, null, null));
				this.context.put("selectBahagianSS", HTML.SelectBahagianAkta(Integer.parseInt(getParam("idAkta")), "socBahagianSS",null, null, null));
				this.context.put("selectBabSS", HTML.SelectBabAkta(Integer.parseInt(getParam("idAkta")), "socBabSS",null, null, null));	
				this.context.put("selectSeksyenSS", HTML.SelectSeksyenAkta(Integer.parseInt(getParam("idAkta")), "socSeksyenSS",null, null, null));		
				this.context.put("txtSubseksyen", "");
			}
			// papar semua para
			else if (tabId.equals("6")) {
				vm = "app/pdt/frmKemaskiniAkta.jsp";
				this.context.put("readOnly2", "readonly");
				this.context.put("readOnly", "");
				this.context.put("mode", "new");

				akta.setSenaraiPara(Integer.parseInt(getParam("idAkta")));
				list = akta.getPaparPara();
				this.context.put("Para", list);
				
				this.context.put("selectPenggalP", HTML.SelectPenggalAkta(Integer.parseInt(getParam("idAkta")), "socPenggalP",null, null, null));
				this.context.put("selectBahagianP", HTML.SelectBahagianAkta(Integer.parseInt(getParam("idAkta")), "socBahagianP",null, null, null));
				this.context.put("selectBabP", HTML.SelectBabAkta(Integer.parseInt(getParam("idAkta")), "socBabP",null, null, null));	
				this.context.put("selectSeksyenP", HTML.SelectSeksyenAkta(Integer.parseInt(getParam("idAkta")), "socSeksyenP",null, null, null));		
				this.context.put("selectSubSeksyen", HTML.SelectSubSeksyenAkta(Integer.parseInt(getParam("idAkta")), "socSubSeksyen",null, null, null));		
				this.context.put("txtPara", "");
				this.context.put("txtJadual", "");
			}
			// papar semua subpara
			else if (tabId.equals("7")) {
				vm = "app/pdt/frmKemaskiniAkta.jsp";
				this.context.put("readOnly2", "readonly");
				this.context.put("readOnly", "");
				this.context.put("mode", "new");

				akta.setSenaraiSubPara(Integer.parseInt(getParam("idAkta")));
				list = akta.getPaparSubPara();
				this.context.put("SubPara", list);
				
				this.context.put("selectPenggalSP", HTML.SelectPenggalAkta(Integer.parseInt(getParam("idAkta")), "socPenggalSP",null, null, null));
				this.context.put("selectBahagianSP", HTML.SelectBahagianAkta(Integer.parseInt(getParam("idAkta")), "socBahagianSP",null, null, null));
				this.context.put("selectBabSP", HTML.SelectBabAkta(Integer.parseInt(getParam("idAkta")), "socBabSP",null, null, null));	
				this.context.put("selectSeksyenSP", HTML.SelectSeksyenAkta(Integer.parseInt(getParam("idAkta")), "socSeksyenSP",null, null, null));		
				this.context.put("selectSubSeksyenSP", HTML.SelectSubSeksyenAkta(Integer.parseInt(getParam("idAkta")), "socSubSeksyenSP",null, null, null));		
				this.context.put("selectPara", HTML.SelectParaAkta(Integer.parseInt(getParam("idAkta")), "socPara",null, null, null));
				this.context.put("selectJadual", HTML.SelectJadualAkta(Integer.parseInt(getParam("idAkta")), "socJadual",null, null, null));
				this.context.put("txtSubPara", "");
			}
			// papar semua jadual para
			else if (tabId.equals("8")) {
				vm = "app/pdt/frmKemaskiniAkta.jsp";
				this.context.put("readOnly2", "readonly");
				this.context.put("readOnly", "");
				this.context.put("mode", "new");

				akta.setSenaraiJadualPara(Integer.parseInt(getParam("idAkta")));
				list = akta.getPaparJadualPara();
				this.context.put("JadualPara", list);
				
				this.context.put("selectJadualJP", HTML.SelectJadualAkta(Integer.parseInt(getParam("idAkta")), "socJadualJP",null, null, null));
				this.context.put("txtParaJP", "");
				this.context.put("txtNamaJadual", "");
			}
			// papar semua jadual subpara
			else if (tabId.equals("9")) {
				vm = "app/pdt/frmKemaskiniAkta.jsp";
				this.context.put("readOnly2", "readonly");
				this.context.put("readOnly", "");
				this.context.put("mode", "new");

				akta.setSenaraiJadualSubPara(Integer.parseInt(getParam("idAkta")));
				list = akta.getPaparJadualSubPara();
				this.context.put("JadualSubPara", list);
				
				this.context.put("selectJadualJSP", HTML.SelectJadualAkta(Integer.parseInt(getParam("idAkta")), "socJadualJSP",null, null, null));
				this.context.put("selectParaJSP", HTML.SelectParaJadualParaAkta(Integer.parseInt(getParam("idAkta")), "socParaJSP",null, null, null));
				this.context.put("txtSubParaJSP", "");
			}
			// papar semua jadual subsubpara
			else if (tabId.equals("10")) {
				vm = "app/pdt/frmKemaskiniAkta.jsp";
				this.context.put("readOnly2", "readonly");
				this.context.put("readOnly", "");
				this.context.put("mode", "new");

				akta.setSenaraiJadualSubSubPara(Integer.parseInt(getParam("idAkta")));
				list = akta.getPaparJadualSubSubPara();
				this.context.put("JadualSubSubPara", list);
				
				this.context.put("selectJadualJSSP", HTML.SelectJadualAkta(Integer.parseInt(getParam("idAkta")), "socJadualJSSP",null, null, null));
				this.context.put("selectParaJSSP", HTML.SelectParaJadualParaAkta(Integer.parseInt(getParam("idAkta")), "socParaJSSP",null, null, null));
				this.context.put("selectSubParaJSSP", HTML.SelectSubParaJadualParaAkta(Integer.parseInt(getParam("idAkta")), "socSubParaJSSP",null, null, null));
				this.context.put("txtSubSubPara", "");
			}
			// kemaskini data
		} else if ("kemaskini".equals(action)) {

			vm = "app/pdt/frmKemaskiniAkta.jsp";
			this.context.put("readOnly", "");
			this.context.put("disabled", "");
			this.context.put("mode", "update");
			// kemaskini Akta
			if (tabId.equals("0")) {
				view_modeAkta(session);
				list = akta.getPaparAkta();
				Hashtable hPapar = (Hashtable) list.get(0);
				this.context.put("idAkta", hPapar.get("idAkta").toString());
				this.context.put("txtNoAkta1", hPapar.get("noAkta").toString());
				this.context.put("txtNamaAkta1", hPapar.get("namaAkta").toString());
				this.context.put("txtTarikhKuatkuasa", hPapar.get("tarikhKuatkuasa").toString());
				this.context.put("txtTarikhMansuh", hPapar.get("tarikhMansuh").toString());
				this.context.put("txtNoWarta", hPapar.get("noWarta").toString());
				this.context.put("txtTarikhWarta", hPapar.get("tarikhWarta").toString());
				if (hPapar.get("sorTerbuka").toString().equals("1")) {
					this.context.put("radioChecked2", "");
					this.context.put("radioChecked1", "checked");
					//this.context.put("checked","terbuka");
				} else {
					this.context.put("radioChecked1", "");
					this.context.put("radioChecked2", "checked");
					//this.context.put("checked","sulit");
				}
				this.context.put("selectSeksyen", HTML.SelectSeksyen("socSeksyen", Utils.parseLong(hPapar.get("idseksyen").toString()), null));
				this.context.put("selectFail", HTML.SelectFail("socNoFail", Utils.parseLong(hPapar.get("idFail").toString()),null));
				this.context.put("txtCatatan", hPapar.get("catatan").toString());
				this.context.put("txtTarikhDaftar", hPapar.get("tarikhDaftar").toString());
			}else
			// kemaskini penggal
			if (tabId.equals("1")) {
				view_modePenggal(session);
				list = akta.getPaparPenggalById();
				Hashtable hPenggal = (Hashtable) list.get(0);
				this.context.put("idPenggal", hPenggal.get("idPenggal").toString());
				this.context.put("txtNoAkta", hPenggal.get("noAkta").toString());
				this.context.put("txtNamaAkta", hPenggal.get("namaAkta").toString());
				this.context.put("txtNoPenggal", hPenggal.get("noPenggal").toString());
				this.context.put("txtTajukPenggal", hPenggal.get("tajukPenggal").toString());
			}else
			// kemaskini bahagian
			if (tabId.equals("2")) {
				view_modeBahagian(session);
				list = akta.getPaparBahagianById();
				Hashtable hBahagian = (Hashtable) list.get(0);
				this.context.put("idPenggal", hBahagian.get("idPenggal").toString());
				this.context.put("idBahagian", hBahagian.get("idBahagian").toString());
				this.context.put("selectPenggal", HTML.SelectPenggalAkta(Integer.parseInt(hBahagian.get("idAkta").toString()), "socPenggal", Utils.parseLong(hBahagian.get("idPenggal").toString()), null, null));
				this.context.put("txtNoBahagian", hBahagian.get("noBahagian").toString());
				this.context.put("txtTajukBahagian", hBahagian.get("tajukBahagian").toString());
			}else
			// kemaskini bab
			if (tabId.equals("3")) {
				view_modeBab(session);
				list = akta.getPaparBabById();
				Hashtable hBab = (Hashtable) list.get(0);
				this.context.put("idBab", hBab.get("idBab").toString());
				this.context.put("idBahagian", hBab.get("idBahagian").toString());
				this.context.put("selectPenggalB", HTML.SelectPenggalAkta(Integer.parseInt(hBab.get("idAkta").toString()), "socPenggalB", Utils.parseLong(hBab.get("idPenggal").toString()),null, null));
				this.context.put("selectBahagian", HTML.SelectBahagianAkta(Integer.parseInt(hBab.get("idAkta").toString()), "socBahagian", Utils.parseLong(hBab.get("idBahagian").toString()),null, null));
				this.context.put("txtNoBab", hBab.get("noBab").toString());
				this.context.put("txtTajukBab", hBab.get("tajukBab").toString());
			}else
		    // kemaskini seksyen
			if (tabId.equals("4")) {
				view_modeSeksyen(session);
				list = akta.getPaparSeksyenById();
				Hashtable hSeksyen = (Hashtable) list.get(0);
				this.context.put("idSeksyen", hSeksyen.get("idSeksyen").toString());
				this.context.put("idBab", hSeksyen.get("idBab").toString());
				this.context.put("idBahagian", hSeksyen.get("idBahagian").toString());
				this.context.put("selectPenggalS", HTML.SelectPenggalAkta(Integer.parseInt(hSeksyen.get("idAkta").toString()), "socPenggalS", Utils.parseLong(hSeksyen.get("idPenggal").toString()), null, null));
				this.context.put("selectBahagianS", HTML.SelectBahagianAkta(Integer.parseInt(hSeksyen.get("idAkta").toString()), "socBahagianS", Utils.parseLong(hSeksyen.get("idBahagian").toString()),null, null));
				this.context.put("selectBab", HTML.SelectBabAkta(Integer.parseInt(getParam("idAkta")), "socBab",Utils.parseLong(hSeksyen.get("idBab").toString()),null, null));				
				this.context.put("txtSeksyen", hSeksyen.get("seksyen").toString());
			 }else
			 // kemaskini subseksyen
			 if (tabId.equals("5")) {
				view_modeSubSeksyen(session);
				list = akta.getPaparSubSeksyenById();
				Hashtable hSubSeksyen = (Hashtable) list.get(0);
				this.context.put("idSubSeksyen", hSubSeksyen.get("idSubSeksyen").toString());
				this.context.put("idSeksyen", hSubSeksyen.get("idSeksyen").toString());
				this.context.put("idBab", hSubSeksyen.get("idBab").toString());
				this.context.put("idBahagian", hSubSeksyen.get("idBahagian").toString());
				this.context.put("selectPenggalSS", HTML.SelectPenggalAkta(Integer.parseInt(hSubSeksyen.get("idAkta").toString()), "socPenggalSS", Utils.parseLong(hSubSeksyen.get("idPenggal").toString()), null, null));
				this.context.put("selectBahagianSS", HTML.SelectBahagianAkta(Integer.parseInt(hSubSeksyen.get("idAkta").toString()), "socBahagianSS", Utils.parseLong(hSubSeksyen.get("idBahagian").toString()),null, null));
				this.context.put("selectBabSS", HTML.SelectBabAkta(Integer.parseInt(getParam("idAkta")), "socBabSS",Utils.parseLong(hSubSeksyen.get("idBab").toString()),null, null));				
				this.context.put("selectSeksyenSS", HTML.SelectSeksyenAkta(Integer.parseInt(getParam("idAkta")), "socSeksyenSS",Utils.parseLong(hSubSeksyen.get("idSeksyen").toString()),null, null));				
				this.context.put("txtSubseksyen", hSubSeksyen.get("subSeksyen").toString());
			 }else
			// kemaskini para
			 if (tabId.equals("6")) {
					view_modePara(session);
					list = akta.getPaparParaById();
					Hashtable hPara = (Hashtable) list.get(0);
					this.context.put("idPara", hPara.get("idPara").toString());
					this.context.put("idJadual", hPara.get("idJadual").toString());
					this.context.put("idSubSeksyen", hPara.get("idSubSeksyen").toString());
					this.context.put("idSeksyen", hPara.get("idSeksyen").toString());
					this.context.put("idBab", hPara.get("idBab").toString());
					this.context.put("idBahagian", hPara.get("idBahagian").toString());
					this.context.put("selectPenggalP", HTML.SelectPenggalAkta(Integer.parseInt(hPara.get("idAkta").toString()), "socPenggalP", Utils.parseLong(hPara.get("idPenggal").toString()), null, null));
					this.context.put("selectBahagianP", HTML.SelectBahagianAkta(Integer.parseInt(hPara.get("idAkta").toString()), "socBahagianP", Utils.parseLong(hPara.get("idBahagian").toString()),null, null));
					this.context.put("selectBabP", HTML.SelectBabAkta(Integer.parseInt(getParam("idAkta")), "socBabP",Utils.parseLong(hPara.get("idBab").toString()),null, null));				
					this.context.put("selectSeksyenP", HTML.SelectSeksyenAkta(Integer.parseInt(getParam("idAkta")), "socSeksyenP",Utils.parseLong(hPara.get("idSeksyen").toString()),null, null));				
					this.context.put("selectSubSeksyen", HTML.SelectSubSeksyenAkta(Integer.parseInt(getParam("idAkta")), "socSubSeksyen",Utils.parseLong(hPara.get("idSubSeksyen").toString()),null, null));				
					this.context.put("txtPara", hPara.get("para").toString());
					this.context.put("txtJadual", hPara.get("jadual").toString());
				 }else
			// kemaskini subpara
			 if (tabId.equals("7")) {
					view_modeSubPara(session);
					list = akta.getPaparSubParaById();
					Hashtable hSubPara = (Hashtable) list.get(0);
					this.context.put("idPara", hSubPara.get("idPara").toString());
					this.context.put("idJadual", hSubPara.get("idJadual").toString());
					this.context.put("idSubSeksyen", hSubPara.get("idSubSeksyen").toString());
					this.context.put("idSeksyen", hSubPara.get("idSeksyen").toString());
					this.context.put("idBab", hSubPara.get("idBab").toString());
					this.context.put("idBahagian", hSubPara.get("idBahagian").toString());
					this.context.put("selectPenggalSP", HTML.SelectPenggalAkta(Integer.parseInt(hSubPara.get("idAkta").toString()), "socPenggalSP", Utils.parseLong(hSubPara.get("idPenggal").toString()), null, null));
					this.context.put("selectBahagianSP", HTML.SelectBahagianAkta(Integer.parseInt(hSubPara.get("idAkta").toString()), "socBahagianSP", Utils.parseLong(hSubPara.get("idBahagian").toString()),null, null));
					this.context.put("selectBabSP", HTML.SelectBabAkta(Integer.parseInt(getParam("idAkta")), "socBabSP",Utils.parseLong(hSubPara.get("idBab").toString()),null, null));				
					this.context.put("selectSeksyenSP", HTML.SelectSeksyenAkta(Integer.parseInt(getParam("idAkta")), "socSeksyenSP",Utils.parseLong(hSubPara.get("idSeksyen").toString()),null, null));				
					this.context.put("selectSubSeksyenSP", HTML.SelectSubSeksyenAkta(Integer.parseInt(getParam("idAkta")), "socSubSeksyenSP",Utils.parseLong(hSubPara.get("idSubSeksyen").toString()),null, null));				
					this.context.put("selectPara", HTML.SelectParaAkta(Integer.parseInt(getParam("idAkta")), "socPara",Utils.parseLong(hSubPara.get("idPara").toString()),null, null));				
					this.context.put("selectJadual", HTML.SelectJadualAkta(Integer.parseInt(getParam("idAkta")), "socJadual",Utils.parseLong(hSubPara.get("idJadual").toString()),null, null));	
					this.context.put("txtSubPara", hSubPara.get("subPara").toString());
				 }else
			 // kemaskini jadualpara
			 if (tabId.equals("8")) {
				    view_modeJadualPara(session);
					list = akta.getPaparJadualParaById();
					Hashtable hJadualPara = (Hashtable) list.get(0);
					this.context.put("idJadualPara", hJadualPara.get("idJadualPara").toString());
					this.context.put("idJadual", hJadualPara.get("idJadual").toString());		
					this.context.put("selectJadualJP", HTML.SelectJadualAkta(Integer.parseInt(getParam("idAkta")), "socJadualJP",Utils.parseLong(hJadualPara.get("idJadual").toString()),null, null));	
					this.context.put("txtNamaJadual", hJadualPara.get("namaJadual").toString());
					this.context.put("txtParaJP", hJadualPara.get("para").toString());
				 }else
			 // kemaskini jadualsubpara
			 if (tabId.equals("9")) {
				    view_modeJadualSubPara(session);
					list = akta.getPaparJadualSubParaById();
					Hashtable hJadualSubPara = (Hashtable) list.get(0);
					this.context.put("idJadualSubPara", hJadualSubPara.get("idJadualSubPara").toString());		
					this.context.put("selectJadualJSP", HTML.SelectJadualAkta(Integer.parseInt(getParam("idAkta")), "socJadualJSP",Utils.parseLong(hJadualSubPara.get("idJadual").toString()),null, null));	
					this.context.put("selectParaJSP", HTML.SelectParaJadualParaAkta(Integer.parseInt(getParam("idAkta")), "socParaJSP",Utils.parseLong(hJadualSubPara.get("idJadualPara").toString()),null, null));							
					this.context.put("txtSubParaJSP", hJadualSubPara.get("subPara").toString());
				 }else
			 // kemaskini jadualsubsubpara
			 if (tabId.equals("10")) {
				    view_modeJadualSubSubPara(session);
					list = akta.getPaparJadualSubSubParaById();
					Hashtable hJadualSubSubPara = (Hashtable) list.get(0);
					this.context.put("idJadualSubSubPara", hJadualSubSubPara.get("idJadualSubSubPara").toString());		
					this.context.put("selectJadualJSSP", HTML.SelectJadualAkta(Integer.parseInt(getParam("idAkta")), "socJadualJSSP",Utils.parseLong(hJadualSubSubPara.get("idJadual").toString()),null, null));	
					this.context.put("selectParaJSSP", HTML.SelectParaJadualParaAkta(Integer.parseInt(getParam("idAkta")), "socParaJSSP",Utils.parseLong(hJadualSubSubPara.get("idJadualPara").toString()),null, null));							
					this.context.put("selectSubParaJSSP", HTML.SelectSubParaJadualParaAkta(Integer.parseInt(getParam("idAkta")), "socSubParaJSSP",Utils.parseLong(hJadualSubSubPara.get("idJadualSubPara").toString()),null, null));		
					this.context.put("txtSubSubPara", hJadualSubSubPara.get("subSubPara").toString());
				 }
		} else {
			// simpan data baru
			if ("simpan".equals(hitbutton)) {
				//System.out.println("hilda");
				String user_id = (String) session.getAttribute("_ekptg_user_id");

				// add new Akta
				if (tabId.equals("0")) {
					
					//uploadFiles();
					
					Hashtable hAddAkta = new Hashtable();
					hAddAkta.put("noAkta", getParam("txtNoAkta1"));
					hAddAkta.put("namaAkta", getParam("txtNamaAkta1"));
					hAddAkta.put("tarikhKuatkuasa",getParam("txtTarikhKuatkuasa"));
					hAddAkta.put("tarikhMansuh", getParam("txtTarikhMansuh"));
					hAddAkta.put("noWarta", getParam("txtNoWarta") == null|| getParam("txtNoWarta") == "" ? "" :getParam("txtNoWarta"));
					hAddAkta.put("tarikhWarta", getParam("txtTarikhWarta"));
				
					if(checked.equals("sulit"))	
						   hAddAkta.put("idKeselamatan","3");
					else
						hAddAkta.put("idKeselamatan","1");
					
					hAddAkta.put("catatan", getParam("txtCatatan"));
					hAddAkta.put("idFail", getParam("socNoFail") == null|| getParam("socNoFail") == "" ? "" : Integer.parseInt(getParam("socNoFail")));
					hAddAkta.put("idSeksyen", getParam("socSeksyen") == null|| getParam("socSeksyen") == "" ? "" : Integer.parseInt(getParam("socSeksyen")));
					hAddAkta.put("tarikhDaftar", getParam("txtTarikhDaftar"));
					hAddAkta.put("idMasuk", Integer.parseInt(user_id));
					hAddAkta.put("tarikhMasuk", getParam("txtTarikhDaftar"));
					akta.addAkta(hAddAkta);
					
					vm = "app/pdt/frmDaftarAkta.jsp";
					akta.setCarianPaparAkta(noAkta,namaAkta, tarikhKuatkuasa);
					list = akta.getCarianPaparAkta();
					//prepareItemForDisplay(session, list, 10, "first");
				}
				// add new penggal
				if (tabId.equals("1")) {
					Hashtable hAddPenggal = new Hashtable();
					if (getParam("idPenggal").equals("")) {
						if (getParam("txtNoPenggal") != "") {
							hAddPenggal.put("idAkta", getParam("idAkta"));
							hAddPenggal.put("noPenggal", Integer.parseInt(getParam("txtNoPenggal")));
							hAddPenggal.put("tajukPenggal",getParam("txtTajukPenggal"));
							hAddPenggal.put("idMasuk", Integer.parseInt(user_id));
							hAddPenggal.put("tarikhMasuk", sdf.format(now));
							akta.addPenggal(hAddPenggal);

							vm = "app/pdt/frmKemaskiniAkta.jsp";
							akta.setSenaraiPenggal(Integer.parseInt(getParam("idAkta")));
							list = akta.getPaparPenggal();
							//prepareItemForDisplay(session, list, 10, "first");
							///this.context.put("Penggal", list);
						}
					}
				}
				// add new bahagian
				if (tabId.equals("2")) {
					Hashtable hAddBahagian = new Hashtable();
					if (getParam("idBahagian").equals("")) {
						if (getParam("txtNoBahagian") != "") {
							hAddBahagian.put("idAkta", getParam("idAkta"));
							hAddBahagian.put("idAktaPenggal",getParam("socPenggalB") == null|| getParam("socPenggal") == "" ? "": Integer.parseInt(getParam("socPenggal")));
							hAddBahagian.put("noBahagian", Integer.parseInt(getParam("txtNoBahagian")));
							hAddBahagian.put("tajukBahagian",getParam("txtTajukBahagian"));
							hAddBahagian.put("idMasuk", user_id);
							hAddBahagian.put("tarikhMasuk", sdf.format(now));
							akta.addBahagian(hAddBahagian);

							vm = "app/pdt/frmKemaskiniAkta.jsp";
							akta.setSenaraiBahagian(Integer.parseInt(getParam("idAkta")));
							list = akta.getPaparBahagian();
							//prepareItemForDisplay(session, list, 10, "first");
						}
					}
				}
				// add new bab
				if (tabId.equals("3")) {
					Hashtable hAddBab = new Hashtable();
					if (getParam("idBab").equals("")) {
						if (getParam("txtNoBab") != "") {
							hAddBab.put("idAkta", getParam("idAkta"));
							hAddBab.put("idAktaPenggal",getParam("socPenggalB") == null|| getParam("socPenggalB") == "" ? "": Integer.parseInt(getParam("socPenggalB")));
							hAddBab.put("idAktaBahagian",getParam("socBahagian") == null|| getParam("socBahagian") == "" ? "": Integer.parseInt(getParam("socBahagian")));
							hAddBab.put("noBab", Integer.parseInt(getParam("txtNoBab")));
							hAddBab.put("tajukBab", getParam("txtTajukBab"));
							hAddBab.put("idMasuk", user_id);
							hAddBab.put("tarikhMasuk", sdf.format(now));
							akta.addBab(hAddBab);

							vm = "app/pdt/frmKemaskiniAkta.jsp";
							akta.setSenaraiBab(Integer.parseInt(getParam("idAkta")));
							list = akta.getPaparBab();
							//prepareItemForDisplay(session, list, 10, "first");
						}
					}
				}
				// add new seksyen
				if (tabId.equals("4")) {
					Hashtable hAddSeksyen = new Hashtable();
					if (getParam("idSeksyen").equals("")) {
						if (getParam("txtSeksyen") != "") {
							hAddSeksyen.put("idAkta", getParam("idAkta"));
							hAddSeksyen.put("idAktaPenggal",getParam("socPenggalS") == null|| getParam("socPenggalS") == "" ? "": Integer.parseInt(getParam("socPenggalS")));
							hAddSeksyen.put("idAktaBahagian",getParam("socBahagianS") == null|| getParam("socBahagianS") == "" ? "": Integer.parseInt(getParam("socBahagianS")));
							hAddSeksyen.put("idAktaBab",getParam("socBab") == null|| getParam("socBab") == "" ? "": Integer.parseInt(getParam("socBab")));
							hAddSeksyen.put("seksyen",getParam("txtSeksyen"));
							hAddSeksyen.put("idMasuk", user_id);
							hAddSeksyen.put("tarikhMasuk", sdf.format(now));
							akta.addSeksyen(hAddSeksyen);
							
							vm = "app/pdt/frmKemaskiniAkta.jsp";
							akta.setSenaraiSeksyen(Integer.parseInt(getParam("idAkta")));
							list = akta.getPaparSeksyen();
							this.context.put("Seksyen", list);
						}
					}
				}else
				// add new subseksyen
				if (tabId.equals("5")) {
					Hashtable hAddSubSeksyen = new Hashtable();
					if (getParam("idSubSeksyen").equals("")) {
						if (getParam("txtSubseksyen") != "") {
							hAddSubSeksyen.put("idAkta", getParam("idAkta"));
							hAddSubSeksyen.put("idAktaPenggal",getParam("socPenggalSS") == null|| getParam("socPenggalSS") == "" ? "": Integer.parseInt(getParam("socPenggalSS")));
							hAddSubSeksyen.put("idAktaBahagian",getParam("socBahagianSS") == null|| getParam("socBahagianSS") == "" ? "": Integer.parseInt(getParam("socBahagianSS")));
							hAddSubSeksyen.put("idAktaBab",getParam("socBabSS") == null|| getParam("socBabSS") == "" ? "": Integer.parseInt(getParam("socBabSS")));
							hAddSubSeksyen.put("idAktaSeksyen",getParam("socSeksyenSS") == null|| getParam("socSeksyenSS") == "" ? "": Integer.parseInt(getParam("socSeksyenSS")));
							hAddSubSeksyen.put("subSeksyen",getParam("txtSubseksyen"));
							hAddSubSeksyen.put("idMasuk", user_id);
							hAddSubSeksyen.put("tarikhMasuk", sdf.format(now));
							akta.addSubSeksyen(hAddSubSeksyen);
							
							vm = "app/pdt/frmKemaskiniAkta.jsp";
							akta.setSenaraiSubSeksyen(Integer.parseInt(getParam("idAkta")));
							list = akta.getPaparSubSeksyen();
							this.context.put("SubSeksyen", list);
						}
					}
				}else
				// add new para	
				if (tabId.equals("6")) {			
					Hashtable hAddPara = new Hashtable();
					if (getParam("idPapar").equals("")) {
						if (getParam("txtPara")!= "") {
							hAddPara.put("idAkta", getParam("idAkta"));
							hAddPara.put("idAktaPenggal",getParam("socPenggalP") == null|| getParam("socPenggalP") == "" ? "": Integer.parseInt(getParam("socPenggalP")));
							hAddPara.put("idAktaBahagian",getParam("socBahagianP") == null|| getParam("socBahagianP") == "" ? "": Integer.parseInt(getParam("socBahagianP")));
							hAddPara.put("idAktaBab",getParam("socBabP") == null|| getParam("socBabP") == "" ? "": Integer.parseInt(getParam("socBabP")));
							hAddPara.put("idAktaSeksyen",getParam("socSeksyenP") == null|| getParam("socSeksyenP") == "" ? "": Integer.parseInt(getParam("socSeksyenP")));
							hAddPara.put("idAktaSubSeksyen",getParam("socSubSeksyen") == null|| getParam("socSubSeksyen") == "" ? "": Integer.parseInt(getParam("socSubSeksyen")));
							hAddPara.put("para",getParam("txtPara"));
							hAddPara.put("jadual",getParam("txtJadual"));
							hAddPara.put("idMasuk", user_id);
							hAddPara.put("tarikhMasuk", sdf.format(now));
							akta.addPara(hAddPara);
							
							vm = "app/pdt/frmKemaskiniAkta.jsp";
							akta.setSenaraiPara(Integer.parseInt(getParam("idAkta")));
							list = akta.getPaparPara();
							this.context.put("Para", list);
						}
					}
				}else
					// add new subpara	
					if (tabId.equals("7")) {			
						Hashtable hAddSubPara = new Hashtable();
						if (getParam("idSubPapar").equals("")) {
							if (getParam("txtSubPara")!= "") {
								hAddSubPara.put("idAkta", getParam("idAkta"));
								hAddSubPara.put("idAktaPenggal",getParam("socPenggalSP") == null|| getParam("socPenggalSP") == "" ? "": Integer.parseInt(getParam("socPenggalSP")));
								hAddSubPara.put("idAktaBahagian",getParam("socBahagianSP") == null|| getParam("socBahagianSP") == "" ? "": Integer.parseInt(getParam("socBahagianSP")));
								hAddSubPara.put("idAktaBab",getParam("socBabSP") == null|| getParam("socBabSP") == "" ? "": Integer.parseInt(getParam("socBabSP")));
								hAddSubPara.put("idAktaSeksyen",getParam("socSeksyenSP") == null|| getParam("socSeksyenSP") == "" ? "": Integer.parseInt(getParam("socSeksyenSP")));
								hAddSubPara.put("idAktaSubSeksyen",getParam("socSubSeksyenSP") == null|| getParam("socSubSeksyenSP") == "" ? "": Integer.parseInt(getParam("socSubSeksyenSP")));
								hAddSubPara.put("idAktaPara",getParam("socPara") == null|| getParam("socPara") == "" ? "": Integer.parseInt(getParam("socPara")));
								hAddSubPara.put("idJadual",getParam("socJadual") == null|| getParam("socJadual") == "" ? "": Integer.parseInt(getParam("socJadual")));
								hAddSubPara.put("subPara",getParam("txtSubPara"));
								hAddSubPara.put("idMasuk", user_id);
								hAddSubPara.put("tarikhMasuk", sdf.format(now));
								akta.addSubPara(hAddSubPara);
								
								vm = "app/pdt/frmKemaskiniAkta.jsp";
								akta.setSenaraiSubPara(Integer.parseInt(getParam("idAkta")));
								list = akta.getPaparSubPara();
								this.context.put("SubPara", list);
							}
						}
					}else
				// add new jadualpara	
				if (tabId.equals("8")) {			
					Hashtable hAddJadualPara = new Hashtable();
					if (getParam("idJadualPara").equals("")) {
						if (getParam("txtNamaJadual")!= "") {
							hAddJadualPara.put("idAkta", getParam("idAkta"));
							hAddJadualPara.put("idJadual",getParam("socJadualJP") == null|| getParam("socJadualJP") == "" ? "": Integer.parseInt(getParam("socJadualJP")));
							hAddJadualPara.put("namaJadual",getParam("txtNamaJadual"));
							hAddJadualPara.put("para",getParam("txtParaJP"));
							//System.out.println(getParam("idAkta"));
							hAddJadualPara.put("idMasuk", user_id);
							hAddJadualPara.put("tarikhMasuk", sdf.format(now));
							hAddJadualPara.put("idKemaskini", user_id); // untuk update nama jadual dalam table tblptjadual
							hAddJadualPara.put("tarikhKemaskini", sdf.format(now)); // untuk update nama jadual dalam table tblptjadual
							akta.addJadualPara(hAddJadualPara);
							
							vm = "app/pdt/frmKemaskiniAkta.jsp";
							akta.setSenaraiJadualPara(Integer.parseInt(getParam("idAkta")));
							list = akta.getPaparJadualPara();
							this.context.put("JadualPara", list);
						}
					}
				}else
				// add new jadualsubpara	
				if (tabId.equals("9")) {			
					Hashtable hAddJadualSubPara = new Hashtable();
					if (getParam("idJadualSubPara").equals("")) {
						if (getParam("txtSubParaJSP")!= "") {
							hAddJadualSubPara.put("idAkta", getParam("idAkta"));
							hAddJadualSubPara.put("idJadual",getParam("socJadualJSP") == null|| getParam("socJadualJSP") == "" ? "": Integer.parseInt(getParam("socJadualJSP")));
							hAddJadualSubPara.put("idJadualPara",getParam("socParaJSP") == null|| getParam("socParaJSP") == "" ? "": Integer.parseInt(getParam("socParaJSP")));
							hAddJadualSubPara.put("subPara", getParam("txtSubParaJSP"));
							hAddJadualSubPara.put("idMasuk", user_id);
							hAddJadualSubPara.put("tarikhMasuk", sdf.format(now));
							akta.addJadualSubPara(hAddJadualSubPara);
							
							vm = "app/pdt/frmKemaskiniAkta.jsp";
							akta.setSenaraiJadualSubPara(Integer.parseInt(getParam("idAkta")));
							list = akta.getPaparJadualSubPara();
							this.context.put("JadualSubPara", list);
						}
					}
				}
				// add new jadualsubsubpara	
				if (tabId.equals("10")) {			
					Hashtable hAddJadualSubSubPara = new Hashtable();
					if (getParam("idJadualSubSubPara").equals("")) {
						if (getParam("txtSubSubPara")!= "") {
							hAddJadualSubSubPara.put("idAkta", getParam("idAkta"));
							hAddJadualSubSubPara.put("idJadual",getParam("socJadualJSSP") == null|| getParam("socJadualJSSP") == "" ? "": Integer.parseInt(getParam("socJadualJSSP")));
							hAddJadualSubSubPara.put("idJadualPara",getParam("socParaJSSP") == null|| getParam("socParaJSSP") == "" ? "": Integer.parseInt(getParam("socParaJSSP")));
							hAddJadualSubSubPara.put("idJadualSubPara",getParam("socSubParaJSSP") == null|| getParam("socSubParaJSSP") == "" ? "": Integer.parseInt(getParam("socSubParaJSSP")));
							hAddJadualSubSubPara.put("subSubPara", getParam("txtSubSubPara"));
							hAddJadualSubSubPara.put("idMasuk", user_id);
							hAddJadualSubSubPara.put("tarikhMasuk", sdf.format(now));
							akta.addJadualSubSubPara(hAddJadualSubSubPara);
							
							vm = "app/pdt/frmKemaskiniAkta.jsp";
							akta.setSenaraiJadualSubSubPara(Integer.parseInt(getParam("idAkta")));
							list = akta.getPaparJadualSubSubPara();
							this.context.put("JadualSubSubPara", list);
						}
					}
				}
			}
			else {
				if (tabId.equals("0")) {
					// papar semua Akta
					vm = "app/pdt/frmDaftarAkta.jsp";
					akta.setCarianPaparAkta(noAkta,namaAkta, tarikhKuatkuasa);
					listSenaraiAkta = akta.getCarianPaparAkta();
					//prepareItemForDisplay(session, listSenaraiAkta, 10, "first");
					this.context.put("txtNoAktaC", noAkta);
					this.context.put("txtNamaAktaC", namaAkta);
					this.context.put("txtTarikhKuatkuasaC", tarikhKuatkuasa);
				}
			}			
			action = "papar";
			this.context.put("action", action);
		}
		this.context.put("tabId", tabId);
		this.context.put("action", action);
		myLogger.debug("vm:"+vm);
		return vm;
	}

	private void view_modeAkta(HttpSession session) throws Exception {
		int id = Integer.parseInt(getParam("idAkta"));
		akta.setPaparAktaById(id);
	}

	private void view_modePenggal(HttpSession session) throws Exception {
		int id = Integer.parseInt(getParam("idPenggal"));
		akta.setPaparPenggalById(id);
	}

	private void view_modeBahagian(HttpSession session) throws Exception {
		int id = Integer.parseInt(getParam("idBahagian"));
		akta.setPaparBahagianById(id);
	}
	private void view_modeBab(HttpSession session) throws Exception {
		int id = Integer.parseInt(getParam("idBab"));
		akta.setPaparBabById(id);
	}
	private void view_modeSeksyen(HttpSession session) throws Exception {
		int id = Integer.parseInt(getParam("idSeksyen"));
		akta.setPaparSeksyenById(id);
	}
	private void view_modeSubSeksyen(HttpSession session) throws Exception {
		int id = Integer.parseInt(getParam("idSubSeksyen"));
		akta.setPaparSubSeksyenById(id);
	}
	private void view_modePara(HttpSession session) throws Exception {
		int id = Integer.parseInt(getParam("idPara"));
		akta.setPaparParaById(id);
	}
	private void view_modeSubPara(HttpSession session) throws Exception {
		int id = Integer.parseInt(getParam("idSubPara"));
		akta.setPaparSubParaById(id);
	}
	private void view_modeJadualPara(HttpSession session) throws Exception {
		int id = Integer.parseInt(getParam("idJadualPara"));
		akta.setPaparJadualParaById(id);
	}
	private void view_modeJadualSubPara(HttpSession session) throws Exception {
		int id = Integer.parseInt(getParam("idJadualSubPara"));
		akta.setPaparJadualSubParaById(id);
	}
	private void view_modeJadualSubSubPara(HttpSession session) throws Exception {
		int id = Integer.parseInt(getParam("idJadualSubSubPara"));
		akta.setPaparJadualSubSubParaById(id);
	}
	 private void prepareItemForDisplay(HttpSession session, Vector objVector, int cntItemPage, String action)
	  {
	    int x;
	    int startno = 0;
	    if (action == null) action = "first";
	    if (session.getAttribute("_portal_startno") != null) startno = ((Integer)session.getAttribute("_portal_startno")).intValue();
	    if (action.equals("previous"))
	      if (startno == objVector.size()) {
	        x = startno % cntItemPage;
	        if (x > 0) {
	          startno -= x;
	          startno -= cntItemPage;
	        } else {
	          startno -= cntItemPage * 2;
	          if (startno < 0) startno = 0;
	        }
	      } else {
	        startno -= cntItemPage * 2;
	        if (startno < 0) startno = 0;
	      }
	    else if (action.equals("first"))
	      startno = 0;
	    	
	    else if (action.equals("last"))
	      x = cntItemPage;
	    else if (action.equals("back"))
	      if (startno == objVector.size()) {
	        x = startno % cntItemPage;
	        if (x == 0) x = cntItemPage;
	        startno -= x;
	      } else {
	        startno -= cntItemPage;
	        if (startno < 0) startno = 0;

	      }
	    
	    Vector moduleVector = new Vector();
	    int i = 0; int cnt = 0;
	    if (objVector.size() > 0) {
	      cnt = 0; for (i = startno; (cnt < cntItemPage) && (i < objVector.size()); ) {
	        moduleVector.addElement(objVector.elementAt(i));

	        ++i; ++cnt;
	      }

	    }

	    session.setAttribute("_portal_moduleVector", moduleVector);
	   
	    this.context.put("startnoInt", new Integer(startno));
	    //System.out.println("start no : "+new Integer(startno));
	    this.context.put("totalInt", new Integer(objVector.size()));
	    //System.out.println("total no : "+new Integer(objVector.size()));
	    
	   
	    startno = i;
	   
	    session.setAttribute("_portal_startno", new Integer(startno));
	    
	  }


		// UPLOAD FILE
//		private void uploadFiles() throws Exception {
//			DiskFileItemFactory factory = new DiskFileItemFactory();
//			ServletFileUpload upload = new ServletFileUpload(factory);
//		    boolean isMultipart = ServletFileUpload.isMultipartContent(request);
//		    Enumeration allparam = request.getParameterNames();
//			String name="";String value="";
//			for (; allparam.hasMoreElements(); ) {
//			// Get the name of the request parameter
//			name = (String)allparam.nextElement();
//			// Get the value of the request parameter
//			value = request.getParameter(name);
//			//System.out.println(name +"="+value);W
//			    //log.info(name +"="+value);
//			}
//		    List items = upload.parseRequest(request);
//		    Iterator itr = items.iterator();
//		    while (itr.hasNext()) {
//			FileItem item = (FileItem)itr.next();
//			    if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
//				saveData(item);
//			    }
//		    }
//		 }
//		 
//		private void saveData(FileItem item,Hashtable h) throws Exception {
//		 Db db = null;
//		 try {
//			 db = new Db();
//			 Connection con = db.getConnection();
//			 con.setAutoCommit(false);
//			 PreparedStatement ps = con.prepareStatement("insert into TBLPDTAKTA " +
//					"(id_Gambar,id_hakmilik,perihal_imej,ringkasan,content,jenis_mime," +
//					"nama_fail,tarikh_kemaskini) " +
//					"values(?,?,?,?,?,?,?,sysdate)");
//			 //log.info("insert into TBLHTPGAMBAR " +
//			 //"(id_Gambar,id_hakmilik,perihal_imej,ringkasan,content,jenis_mime,nama_fail,tarikh_kemaskini) " +
//			 //"values(?,?,?,?,?,?,?,sysdate)");
//
//			 ps.setLong(1, idGambar);
//			 ps.setString(2, getParam("idHakmilik"));
//			 ps.setString(3, getParam("txtPerihalImej").toUpperCase());
//			 ps.setString(4, getParam("txtRingkas").toUpperCase());
//			 ps.setBinaryStream(5,item.getInputStream(),(int)item.getSize());
//			 ps.setString(6,item.getName());
//			 ps.setString(7,item.getContentType());
//			 ps.executeUpdate();
//			 con.commit();
//		 } finally {
//			      if (db != null) db.close();
//		 }
//		}
	 
}
