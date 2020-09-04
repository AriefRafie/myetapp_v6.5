package ekptg.view.ppk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.velocity.VTemplate;

import org.apache.log4j.Logger;
import org.apache.velocity.Template;

import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.ppk.FrmPerintahSek8Data;
import ekptg.model.ppk.FrmPrmhnnSek8Notis;

//import ecourt.ws.ppk.ECourtPPKManager;

public class FrmIntegrasiDGCertPerintah extends VTemplate {
	static Logger myLogger = Logger.getLogger(FrmIntegrasiDGCertPerintah.class);
	
	// model
	FrmPerintahSek8Data logic = new FrmPerintahSek8Data();
	FrmPrmhnnSek8Notis modelNotis = new FrmPrmhnnSek8Notis();

	@Override
	public Template doTemplate() throws Exception {
		HttpSession session = this.request.getSession();
		ServletContext application=getServletContext(); //hide attachment
		
		Date now = new Date();
		String vm = "";
		String submit = request.getParameter("commandw") != null ? (String) request.getParameter("commandw") : (String) request.getParameter("command");
		myLogger.info("submit ::"+submit);
		String fFrom = request.getParameter("frmFrom") != null ? (String) request.getParameter("frmFrom") : "";
				
				String userId = "";
				String idPerbicaraan = getParam("id_perbicaraan");
				context.put("flagSimpan", "");
				context.put("NO_FAIL", "");
				context.put("id_perintah", "");
				context.put("id_fail", "");
				userId = (String) session.getAttribute("_ekptg_user_id");
				this.context.put("usid", userId);
				
				String NO_FAIL = request.getParameter("nofail");
				String id_perintah = request.getParameter("idperintah");
				String id_fail = request.getParameter("idfail");
				
				context.put("NO_FAIL", NO_FAIL);
				context.put("id_perintah", id_perintah);
				context.put("id_fail", id_fail);
				
				
			//	myLogger.info("skrin ::"+vm);
		
//nofail="+NO_FAIL+"&idfail="+idfail+"&idperbicaraan="+id_perbicaraan+"&flagVersion=popupPNB&command=sendDGCert
				
		if ("sendDGCertPerintah".equals(submit)) {
			//System.out.println("inside sendDGCert ################");
			
			Vector list = new Vector();
			Vector dataNotis = new Vector();
			Vector keputusanPermohonan = new Vector();
			Vector alamatTempatBicara = new Vector();
			
			String idFail = request.getParameter("idfail");
			String user = (String) session.getAttribute("_ekptg_user_id");
			
			String userlogin = (String) session.getAttribute("_portal_login");
			String username = (String) session.getAttribute("_portal_username");
			context.put("userlogin", userlogin);
			context.put("username", username);
            ///System.out.println("inside userlogin ################"+userlogin);
			//System.out.println("inside username ################"+username);
			String id = getParam("id_permohonan");
			userId = (String) session.getAttribute("_ekptg_user_id");
			String idPermohonan = getParam("idPermohonan");
			// get data keputusan permohonan
			keputusanPermohonan = modelNotis.getKeputusanPermohonan(idPermohonan);

			Hashtable kp = new Hashtable();
			String idkp = "";

			if (keputusanPermohonan.size() != 0) {
				kp = (Hashtable) keputusanPermohonan.get(0);
				idkp = kp.get("id_keputusanpermohonan").toString();
			}

			// get info pemohon
			modelNotis.setListSemak(idPermohonan, userId);
			list = modelNotis.getListSemak();
			
			String idSimati = "";
			String idStatus = "";
			String idPejabatJKPTG = "";
			String id_negeri = "";

			if (list.size() != 0) {
				Hashtable ls = (Hashtable) list.get(0);
				idSimati = ls.get("idSimati").toString();
				idStatus = ls.get("id_Status").toString();
				idPejabatJKPTG = ls.get("id_pejabatjkptg").toString();
				id_negeri = ls.get("pmidnegeri").toString();
			}
			
			if (id_negeri != "") {
				context.put("xidnegerix", id_negeri);
			}
			context.put("listSemak", list);
			
			
			// --data notis
			modelNotis.setListSemakWithData(idkp);
			dataNotis = modelNotis.getListSemakWithData();
			
			
			String bil = "";
			int _bil = 0;

			if (dataNotis.size() != 0) {
				Hashtable idn = (Hashtable) dataNotis.get(0);
				bil = idn.get("bil_bicara").toString();
				_bil = Integer.parseInt(bil) + 1;
			}

			if (_bil > 3) {
				context.put("alertBIL", "yes");
			} else {
				context.put("alertBIL", "");
			}
			context.put("NOalertBIL", bil);

			String idperbicaraan = "";
			String idpsk = "";
			String idNeg = "";
			String currentBil = "";
			String idpejabat = "";
			String idjenispejabat = "";
			String tempatBicara = "";
			String tarikh_bicara = "";
			String tarikh_notis = "";
			String masa_bicara = "";
			String jenis_masa_bicara = "";
			String jenisMasa = "";
			String peg_pengendali = "";
			String bil_bicara = "";

			Hashtable idn = new Hashtable();

			if (dataNotis.size() != 0) {
				idn = (Hashtable) dataNotis.get(0);
				idperbicaraan = idn.get("id_perbicaraan").toString();
				idpsk = idn.get("id_unitpsk").toString();
				idNeg = idn.get("id_negeribicara").toString();
				currentBil = idn.get("bil_bicara").toString();
				idpejabat = idn.get("id_pejabat").toString();
				tempatBicara = idn.get("tempat_bicara").toString();
				idjenispejabat = idn.get("id_jenispejabat").toString();
				tarikh_bicara = idn.get("tarikh_bicara").toString();
				tarikh_notis = idn.get("tarikh_notis").toString();
				masa_bicara = idn.get("masa_bicara").toString();
				jenis_masa_bicara = idn.get("jenis_masa_bicara").toString();
				peg_pengendali = idn.get("peg_pengendali").toString();
				bil_bicara = idn.get("bil_bicara").toString();
			}
			
			if(jenis_masa_bicara.equals("1")){
				jenisMasa = "PAGI";
			}else if(jenis_masa_bicara.equals("2")){
				jenisMasa = "TENGAHARI";
			}else if(jenis_masa_bicara.equals("3")){
				jenisMasa = "PETANG";
			}else{
				jenisMasa = "TIADA";
			}
			
			context.put("idn", idn);
			context.put("idperbicaraan", idperbicaraan);
			context.put("idpsk", idpsk);
			context.put("idNeg", idNeg);
			context.put("currentBil", currentBil);
			context.put("idpejabat", idpejabat);
			context.put("tempatBicara", tempatBicara);
			context.put("idjenispejabat", idjenispejabat);
			context.put("tarikh_bicara", tarikh_bicara);
			context.put("tarikh_notis", tarikh_notis);
			context.put("masa_bicara", masa_bicara);
			context.put("jenisMasa", jenisMasa);
			context.put("peg_pengendali", peg_pengendali);
			context.put("bil_bicara", bil_bicara);
			
			
			
			
			String idJKPTG = "";
			String alamat1 = "";
			String alamat2 = "";
			String alamat3 = "";
			String poskod = "";
			String negeri = "";

			if (idPejabatJKPTG != "") {

				alamatTempatBicara = getAlamatTempatBicaraIntegrasi(idperbicaraan);

				if (alamatTempatBicara.size() != 0) {

					Hashtable AB = (Hashtable) alamatTempatBicara.get(0);

					alamat1 = AB.get("alamat1").toString();
					alamat2 = AB.get("alamat2").toString();
					alamat3 = AB.get("alamat3").toString();
					poskod = AB.get("poskod").toString();
					negeri = AB.get("id_negeri").toString();
				}
			}

			context.put("poskod", poskod);
			context.put("alamat1", alamat1);
			context.put("alamat2", alamat2);
			context.put("alamat3", alamat3);
			

			if (negeri != "") {
				context.put("selectNegeri", HTML.SelectNegeri("socNegeri",
						Utils.parseLong(negeri),
						"class=disabled disabled style=width:305"));
			} else {
				context.put("selectNegeri", HTML.SelectNegeri("socNegeri",
						null, "class=disabled disabled style=width:305"));
			}
			
			if(idjenispejabat.equals("22")){
				if (idPejabatJKPTG != "") {
					context
						.put(
								"selectBicara",
								HTML
										.SelectTempatBicaraByPejabatJKPTG(
												idPejabatJKPTG,
												"socTempatBicara",
												Utils.parseLong(idPejabatJKPTG),
												null,
												"style=width:400 onChange=\"doChangeidTempatBicara();\" class=disabled disabled "));
			} else {
				context
						.put(
								"selectBicara",
								HTML
										.SelectTempatBicara("socTempatBicara",
												null, null,
												"style=width:400 onChange=\"doChangeidTempatBicara();\" class=disabled disabled "));
			}
			
			
			context.put("previousBil", _bil);
			
				if (idpejabat != "") {
					context.put("showBicara", HTML.SelectTempatBicara(
						"editTempatBicara", Utils.parseLong(idpejabat),
						null,
						"style=width:400 onChange=\"doChangeidTempatBicara();\" class=disabled disabled "));
				} else {
				context.put("showBicara", HTML.SelectTempatBicara(
						"editTempatBicara", null, null,
						"style=width:400 onChange=\"doChangeidTempatBicara();\" class=disabled disabled "));
				}
				// radio button
				context.put("checkP1", "checked");
				context.put("checkP2", "");
				context.put("checkP3", "");
			}
			if (idjenispejabat.equals("2")) {
				// dropdown pejabat tanah
				

				myLogger.info("idjenispejabat= "+idjenispejabat);
				myLogger.info("idPejabatJKPTG= "+idPejabatJKPTG);
				myLogger.info("idpejabat= "+idpejabat);
				if (idPejabatJKPTG != "") {
					if (idpejabat != "") {
						context
								.put(
										"selectBicara",
										HTML
												.SelectPejabatTanahByJKPTG(
														idPejabatJKPTG,
														"editTempatBicara",
														Utils
																.parseLong(idpejabat),
														null,
														"style=width:400 class=disabled disabled "));
					} else {
						context
								.put(
										"selectBicara",
										HTML
												.SelectPejabatTanahByJKPTG(
														idPejabatJKPTG,
														"editTempatBicara",
														null, null,
														"style=width:400 class=disabled disabled "));
					}
				} 
				else {
					if (idpejabat != "") {
						context.put("selectBicara", HTML.SelectPejabatTanah(
								"editTempatBicara", Utils.parseLong(idpejabat),
								null,
								"style=width:400 onChange=\"doChangeidTempatBicara();\" class=disabled disabled  "));
					} else {
						context.put("selectBicara", HTML.SelectPejabatTanah(
								"editTempatBicara", null, null,
								"style=width:400 onChange=\"doChangeidTempatBicara();\" class=disabled disabled "));
					}
				}
				// radio button
				context.put("checkP1", "");
				context.put("checkP2", "checked");
				context.put("checkP3", "");

			} else if (idjenispejabat.equals("0")) {

				// dropdown pejabat tanah
				context
						.put(
								"selectBicara",
								"<input type='text' name='editTempatBicara' style='text-transform:uppercase;' onBlur='this.value=this.value.toUpperCase()' size='52'  maxlength='60' value='"
										+ tempatBicara
										+ "' class='disabled' readonly>");

				// radio button
				context.put("checkP1", "");
				context.put("checkP2", "");
				context.put("checkP3", "checked");

			} else {
				// dropdown pejabat jkptg
				if (idPejabatJKPTG != "") {
					if (idpejabat != "") {
						context
								.put(
										"selectBicara",
										HTML
												.SelectTempatBicaraByPejabatJKPTG(
														idPejabatJKPTG,
														"editTempatBicara",
														Utils
																.parseLong(idpejabat),
														null,
														"style=width:400 class=disabled disabled "));
					} else {
						context
								.put(
										"selectBicara",
										HTML
												.SelectTempatBicaraByPejabatJKPTG(
														idPejabatJKPTG,
														"editTempatBicara",
														null, null,
														"style=width:400 class=disabled disabled "));
					}
				} else {
					if (idpejabat != "") {
						context.put("selectBicara", HTML.SelectTempatBicara(
								"editTempatBicara", Utils.parseLong(idpejabat),
								null,
								"style=width:400 class=disabled disabled  "));
					} else {
						context.put("selectBicara", HTML.SelectTempatBicara(
								"editTempatBicara", null, null,
								"style=width:400 class=disabled disabled  "));
					}
				}

				// radio button
				context.put("checkP1", "checked");
				context.put("checkP2", "");
				context.put("checkP3", "");
			}
			


			vm = "app/ppk/integrasi/DGCertPerintah.jsp";
			myLogger.info("vm ::"+vm);
			
			
		} else if ("sahTandatangan".equals(submit)) {
			
			String NOFAIL = request.getParameter("NO_FAIL");
			String idperintah = request.getParameter("id_perintah");
			String idfail = request.getParameter("id_fail");
		
			context.put("NO_FAIL", NOFAIL);
			context.put("idperintah", idperintah);
			context.put("id_fail", idfail);
			
			//aishah add untuk pindah dr table TEMP ke table real
			
			//modelNotis.insertPenghantaranNotisReal(idperbicaraan);
			
				vm = "app/ppk/integrasi/TandatanganSuccessPerintah.jsp";
			
			
		}
		else if ("verify".equals(submit)) {
			//System.out.println("inside sendDGCertPerintah ################");
			
			Vector list = new Vector();
			Vector dataNotis = new Vector();
			Vector keputusanPermohonan = new Vector();
			Vector alamatTempatBicara = new Vector();
			
			String idFail = request.getParameter("idfail");
			String user = (String) session.getAttribute("_ekptg_user_id");
			
			String userlogin = (String) session.getAttribute("_portal_login");
			String username = (String) session.getAttribute("_portal_username");
			context.put("userlogin", userlogin);
			context.put("username", username);
            ///System.out.println("inside userlogin ################"+userlogin);
			//System.out.println("inside username ################"+username);
			String idPermohonan = getParam("id_permohonan");
			
			userId = (String) session.getAttribute("_ekptg_user_id");
			
			// get data keputusan permohonan
			keputusanPermohonan = modelNotis.getKeputusanPermohonan(idPermohonan);

			Hashtable kp = new Hashtable();
			String idkp = "";

			if (keputusanPermohonan.size() != 0) {
				kp = (Hashtable) keputusanPermohonan.get(0);
				idkp = kp.get("id_keputusanpermohonan").toString();
			}

			// get info pemohon
			modelNotis.setListSemak(idPermohonan, userId);
			list = modelNotis.getListSemak();
			
			String idSimati = "";
			String idStatus = "";
			String idPejabatJKPTG = "";
			String id_negeri = "";

			if (list.size() != 0) {
				Hashtable ls = (Hashtable) list.get(0);
				idSimati = ls.get("idSimati").toString();
				idStatus = ls.get("id_Status").toString();
				idPejabatJKPTG = ls.get("id_pejabatjkptg").toString();
				id_negeri = ls.get("pmidnegeri").toString();
			}
			
			if (id_negeri != "") {
				context.put("xidnegerix", id_negeri);
			}
			context.put("listSemak", list);
			
			
			// --data notis
			modelNotis.setListSemakWithData(idkp);
			dataNotis = modelNotis.getListSemakWithData();
			
			
			String bil = "";
			int _bil = 0;

			if (dataNotis.size() != 0) {
				Hashtable idn = (Hashtable) dataNotis.get(0);
				bil = idn.get("bil_bicara").toString();
				_bil = Integer.parseInt(bil) + 1;
			}

			if (_bil > 3) {
				context.put("alertBIL", "yes");
			} else {
				context.put("alertBIL", "");
			}
			context.put("NOalertBIL", bil);

			String idPerintah="";
			String idperbicaraan = "";
			String idpsk = "";
			String idNeg = "";
			String currentBil = "";
			String idpejabat = "";
			String idjenispejabat = "";
			String tempatBicara = "";
			String tarikh_bicara = "";
			String tarikh_notis = "";
			String masa_bicara = "";
			String jenis_masa_bicara = "";
			String jenisMasa = "";
			String peg_pengendali = "";
			String bil_bicara = "";

			Hashtable idn = new Hashtable();

			if (dataNotis.size() != 0) {
				idn = (Hashtable) dataNotis.get(0);
				idperbicaraan = idn.get("id_perbicaraan").toString();
				idpsk = idn.get("id_unitpsk").toString();
				idNeg = idn.get("id_negeribicara").toString();
				currentBil = idn.get("bil_bicara").toString();
				idpejabat = idn.get("id_pejabat").toString();
				tempatBicara = idn.get("tempat_bicara").toString();
				idjenispejabat = idn.get("id_jenispejabat").toString();
				tarikh_bicara = idn.get("tarikh_bicara").toString();
				tarikh_notis = idn.get("tarikh_notis").toString();
				masa_bicara = idn.get("masa_bicara").toString();
				jenis_masa_bicara = idn.get("jenis_masa_bicara").toString();
				peg_pengendali = idn.get("peg_pengendali").toString();
				bil_bicara = idn.get("bil_bicara").toString();
			}
			
			if(jenis_masa_bicara.equals("1")){
				jenisMasa = "PAGI";
			}else if(jenis_masa_bicara.equals("2")){
				jenisMasa = "TENGAHARI";
			}else if(jenis_masa_bicara.equals("3")){
				jenisMasa = "PETANG";
			}else{
				jenisMasa = "TIADA";
			}
			
			context.put("idn", idn);
			context.put("idperbicaraan", idperbicaraan);
			context.put("idpsk", idpsk);
			context.put("idNeg", idNeg);
			context.put("currentBil", currentBil);
			context.put("idpejabat", idpejabat);
			context.put("tempatBicara", tempatBicara);
			context.put("idjenispejabat", idjenispejabat);
			context.put("tarikh_bicara", tarikh_bicara);
			context.put("tarikh_notis", tarikh_notis);
			context.put("masa_bicara", masa_bicara);
			context.put("jenisMasa", jenisMasa);
			context.put("peg_pengendali", peg_pengendali);
			context.put("bil_bicara", bil_bicara);
			
			
			
			
			String idJKPTG = "";
			String alamat1 = "";
			String alamat2 = "";
			String alamat3 = "";
			String poskod = "";
			String negeri = "";

			//bella cuba nasib.buat pandai.
			
			if (idperbicaraan != "") {

				alamatTempatBicara = getAlamatTempatBicaraIntegrasi(idperbicaraan);

				if (alamatTempatBicara.size() != 0) {

					Hashtable AB = (Hashtable) alamatTempatBicara.get(0);

					alamat1 = AB.get("alamat1").toString();
					alamat2 = AB.get("alamat2").toString();
					alamat3 = AB.get("alamat3").toString();
					poskod = AB.get("poskod").toString();
					negeri = AB.get("id_negeri").toString();
				}
			}

			context.put("poskod", poskod);
			context.put("alamat1", alamat1);
			context.put("alamat2", alamat2);
			context.put("alamat3", alamat3);
			if (negeri != "") {
				context.put("selectNegeri", HTML.SelectNegeri("socNegeri",
						Utils.parseLong(negeri),
						"class=disabled disabled style=width:305"));
			} else {
				context.put("selectNegeri", HTML.SelectNegeri("socNegeri",
						null, "class=disabled disabled style=width:305"));
			}
			
			if(idjenispejabat.equals("22")){
				if (idPejabatJKPTG != "") {
					context
						.put(
								"selectBicara",
								HTML
										.SelectTempatBicaraByPejabatJKPTG(
												idPejabatJKPTG,
												"socTempatBicara",
												Utils.parseLong(idPejabatJKPTG),
												null,
												"style=width:400 onChange=\"doChangeidTempatBicara();\" class=disabled disabled "));
			} else {
				context
						.put(
								"selectBicara",
								HTML
										.SelectTempatBicara("socTempatBicara",
												null, null,
												"style=width:400 onChange=\"doChangeidTempatBicara();\" class=disabled disabled "));
			}
			
			
			context.put("previousBil", _bil);
			
				if (idpejabat != "") {
					context.put("showBicara", HTML.SelectTempatBicara(
						"editTempatBicara", Utils.parseLong(idpejabat),
						null,
						"style=width:400 onChange=\"doChangeidTempatBicara();\" class=disabled disabled "));
				} else {
				context.put("showBicara", HTML.SelectTempatBicara(
						"editTempatBicara", null, null,
						"style=width:400 onChange=\"doChangeidTempatBicara();\" class=disabled disabled "));
				}
				// radio button
				context.put("checkP1", "checked");
				context.put("checkP2", "");
				context.put("checkP3", "");
			} else if (idjenispejabat.equals("2")) {
				// dropdown pejabat tanah
				

				myLogger.info("idjenispejabat= "+idjenispejabat);
				myLogger.info("idPejabatJKPTG= "+idPejabatJKPTG);
				myLogger.info("idpejabat= "+idpejabat);
				if (idPejabatJKPTG != "") {
					if (idpejabat != "") {
						context
								.put(
										"selectBicara",
										HTML
												.SelectPejabatTanahByJKPTG(
														idPejabatJKPTG,
														"editTempatBicara",
														Utils
																.parseLong(idpejabat),
														null,
														"style=width:400 class=disabled disabled "));
					} else {
						context
								.put(
										"selectBicara",
										HTML
												.SelectPejabatTanahByJKPTG(
														idPejabatJKPTG,
														"editTempatBicara",
														null, null,
														"style=width:400 class=disabled disabled "));
					}
				} 
				else {
					if (idpejabat != "") {
						context.put("selectBicara", HTML.SelectPejabatTanah(
								"editTempatBicara", Utils.parseLong(idpejabat),
								null,
								"style=width:400 onChange=\"doChangeidTempatBicara();\" class=disabled disabled  "));
					} else {
						context.put("selectBicara", HTML.SelectPejabatTanah(
								"editTempatBicara", null, null,
								"style=width:400 onChange=\"doChangeidTempatBicara();\" class=disabled disabled "));
					}
				}
				// radio button
				context.put("checkP1", "");
				context.put("checkP2", "checked");
				context.put("checkP3", "");

			} else if (idjenispejabat.equals("0")) {

				// dropdown pejabat tanah
				context
						.put(
								"selectBicara",
								"<input type='text' name='editTempatBicara' style='text-transform:uppercase;' onBlur='this.value=this.value.toUpperCase()' size='52'  maxlength='60' value='"
										+ tempatBicara
										+ "' class='disabled' readonly>");

				// radio button
				context.put("checkP1", "");
				context.put("checkP2", "");
				context.put("checkP3", "checked");

			} else {
				// dropdown pejabat jkptg
				if (idPejabatJKPTG != "") {
					if (idpejabat != "") {
						context
								.put(
										"selectBicara",
										HTML
												.SelectTempatBicaraByPejabatJKPTG(
														idPejabatJKPTG,
														"editTempatBicara",
														Utils
																.parseLong(idpejabat),
														null,
														"style=width:400 class=disabled disabled "));
					} else {
						context
								.put(
										"selectBicara",
										HTML
												.SelectTempatBicaraByPejabatJKPTG(
														idPejabatJKPTG,
														"editTempatBicara",
														null, null,
														"style=width:400 class=disabled disabled "));
					}
				} else {
					if (idpejabat != "") {
						context.put("selectBicara", HTML.SelectTempatBicara(
								"editTempatBicara", Utils.parseLong(idpejabat),
								null,
								"style=width:400 class=disabled disabled  "));
					} else {
						context.put("selectBicara", HTML.SelectTempatBicara(
								"editTempatBicara", null, null,
								"style=width:400 class=disabled disabled  "));
					}
				}

				// radio button
				context.put("checkP1", "checked");
				context.put("checkP2", "");
				context.put("checkP3", "");
			}
			
			//GET SIGNEDDATA
			String dataDahSign = logic.getSignedData(idPerintah);
			System.out.println("dataDahSign    "+dataDahSign);
			context.put("dataDahSign", dataDahSign);

			vm = "app/ppk/integrasi/verifyDGCertPerintah.jsp";
			myLogger.info("vm ::"+vm);
			
			
		}else if ("simpanTemp".equals(submit)) {
			
		//String flagSimpan = "";
			context.put("flagSimpan", "Y");
			String NOFAIL = request.getParameter("NO_FAIL");
			String idperintah = request.getParameter("id_perintah");
			
			System.out.println("idperintah::::: "+idperintah);
			String idfail = request.getParameter("id_fail");
			String signedText = request.getParameter("signedText");
			
			System.out.println("signedText::::: "+signedText);
			
			Db db = null;
			try {
				
				db = new Db();
				Connection con = db.getConnection();
				con.setAutoCommit(false);
				PreparedStatement ps = con.prepareStatement("UPDATE TBLPPKPERINTAH SET SIGNED_TEXT  = ? WHERE ID_PERINTAH = ? ");
	        	ps.setString(1, signedText);
	        	ps.setString(2, idperintah);       
	        	System.out.println("sql b4---");
	        	ps.executeUpdate();  
	        	System.out.println("sql--------UPDATE TBLPPKPERINTAH SET SIGNED_TEXT  = "+signedText+" WHERE ID_PERINTAH = "+idperintah+"");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (db != null)
					db.close();
			}

		
			context.put("NO_FAIL", NOFAIL);
			context.put("id_perintah", idperintah);
			context.put("id_fail", idfail);
//			
				vm = "app/ppk/integrasi/DGCertPerintah.jsp";
			
			
		}
		
		
		Template template = this.engine.getTemplate(vm);
		return template;
	}

	public static Vector getAlamatTempatBicaraIntegrasi(String idBicara)
			throws Exception {

		Db db = null;

		String sql = "";

		try {

			db = new Db();

			Statement stmt = db.getStatement();

			sql = "SELECT ALAMAT_BICARA1 AS ALAMAT1, ALAMAT_BICARA2 AS ALAMAT2, ALAMAT_BICARA3 AS ALAMAT3, POSKOD, ID_NEGERIBICARA AS ID_NEGERI, " +
					" ID_PEJABAT, ID_JENISPEJABAT FROM TBLPPKPERBICARAAN WHERE ID_PERBICARAAN = '" + idBicara + "'";
			myLogger.info("getAlamatTempatBicara::::: "+sql);
			ResultSet rs = stmt.executeQuery(sql);
			myLogger.info("getAlamatTempatBicara::::: "+sql);
			Vector list = new Vector();

			Hashtable h;

			while (rs.next()) {
				h = new Hashtable();

				h.put("alamat1", rs.getString("alamat1") == null ? "" : rs
						.getString("alamat1"));
				h.put("alamat2", rs.getString("alamat2") == null ? "" : rs
						.getString("alamat2"));
				h.put("alamat3", rs.getString("alamat3") == null ? "" : rs
						.getString("alamat3"));
				h.put("poskod", rs.getString("poskod") == null ? "" : rs
						.getString("poskod"));
				h.put("id_negeri", rs.getString("id_negeri") == null ? "" : rs
						.getString("id_negeri"));

				list.addElement(h);

			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}
		// Get alamat tempat bicara
	}
	
}

/**List fail-fail Tandatangan Digital di Perintah:
 * 1.	FrmPerintahSek8.java
 * 2.	FrmPerintahMaklumatPerintahSek8.jsp
 * 3.	tindakanPegawaiPerintahSek8.jsp
 * 4.	FrmIntegrasiDGCertPerintah.java
 * 5.	DGCertPerintah.jsp
 * 6.	FrmPerintahSek8Data.java
 * 7.	TandatanganSuccessPerintah.jsp
 * 8.	verifyDGCertPerintah.jsp
**/
