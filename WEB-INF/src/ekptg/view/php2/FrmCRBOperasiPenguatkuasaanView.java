package ekptg.view.php2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import ekptg.helpers.DB;
import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.php2.FrmCRBHeaderData;
import ekptg.model.php2.FrmCRBOperasiPenguatkuasaanData;

public class FrmCRBOperasiPenguatkuasaanView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;

	FrmCRBHeaderData logicHeader = new FrmCRBHeaderData();
	FrmCRBOperasiPenguatkuasaanData logic = new FrmCRBOperasiPenguatkuasaanData();

	Utils utils = new Utils();

	public String doTemplate2() throws Exception {

		HttpSession session = this.request.getSession();

		Boolean postDB = false;
		String doPost = (String) session.getAttribute("doPost");
		if (doPost.equals("true")) {
			postDB = true;
		}

		// GET DEFAULT PARAM
		String action = getParam("action"); // * ACTION NI HANYA UTK SETUP
											// PAGING SHJ
		String vm = "";
		String submit = getParam("command");
		String hitButton = getParam("hitButton");
		String actionCRB = getParam("actionCRB");

		String selectedTabUpper = getParam("selectedTabUpper").toString();
		if (selectedTabUpper == null || "".equals(selectedTabUpper)) {
			selectedTabUpper = "0";
		}
		String selectedTabLower = getParam("selectedTabLower").toString();
		if (selectedTabLower == null || "".equals(selectedTabLower)) {
			selectedTabLower = "0";
		}

		// GET ID PARAM
		String idFail = getParam("idFail");
		String idPermohonan = getParam("idPermohonan");
		String idStatus = getParam("idStatus");
		String idUlasanTeknikal = getParam("idUlasanTeknikal");
		String idLaporanTanah = getParam("idLaporanTanah");
		String idNegeriTanah = getParam("idNegeriTanah");
		String idPenceroboh = getParam("idPenceroboh");
		String idPegawaiLaporanTanah = getParam("idPegawaiLaporanTanah");
		String idDokumen = getParam("idDokumen");
		String flagPopup = getParam("flagPopup");
		String modePopup = getParam("modePopup");
		String idKementerianTanah = getParam("idKementerianTanah");
		String idAgensiTanah = getParam("idAgensiTanah");
		String flagStatus = getParam("flagStatus");
		String flagAktif = getParam("flagAktif");
		String flagSuratKe = getParam("idSuratKe");		
		String idHakmilik = "";

		this.context.put("completed", false);

		// VECTOR
		Vector beanHeader = null;
		Vector beanLaporanOperasi = null;
		Vector senaraiImejan = null;
		Vector beanMaklumatImejan = null;
		Vector beanMaklumatKehadiran = null;
		Vector senaraiKehadiran = null;
		Vector beanMaklumatSuratPenghargaan = null;
		Vector senaraiSuratPenghargaan = null;
		Vector beanMaklumatPejabat = null;
		Vector beanMaklumatLaporanOperasi = null;
		Vector senaraiLaporanOperasi = null;

		boolean flagOpenDetail = false;
		String status = "";

		String step = getParam("step");

		vm = "app/php2/frmCRBOperasiPenguatkuasaan.jsp";

		// GET DROPDOWN PARAM
		String idKementerian = getParam("socKementerian");
		if (idKementerian == null || idKementerian.trim().length() == 0) {
			idKementerian = "99999";
		}
		String idAgensi = getParam("socAgensi");
		if (idAgensi == null || idAgensi.trim().length() == 0) {
			idAgensi = "99999";
		}
		String idNegeri = getParam("socNegeri");
		if (idNegeri == null || idNegeri.trim().length() == 0) {
			idNegeri = "99999";
		}
		String idPejabat = getParam("socPejabat");
		if (idPejabat == null || idPejabat.trim().length() == 0) {
			idPejabat = "99999";
		}

		if (postDB) {
			if ("simpanLO".equals(hitButton)) {
				idLaporanTanah = logic.simpanLO(idPermohonan,
						getParam("txtTarikhOperasi"), getParam("txtLaporan"),
						getParam("txtNamaPelaporOperasi"),
						getParam("txtJawatanLO"), getParam("txtCatatanLO"),
						getParam("txtTarikhTamatOperasi"),
						Utils.RemoveSymbol(getParam("txtKosOperasi")),
						logicHeader.getIdHakmilikPermohonanByIdFail(idFail), getParam("txtMasaDari"), 
						getParam("txtMasaHingga"), getParam("txtLaporanSemasa"), getParam("txtTempat"), getParam("txtPenceroboh"), 
						getParam("txtLaporanSelepas"), 
						session);
				this.context.put("idLaporanTanah", idLaporanTanah);
			}

			if ("simpanKemaskiniLO".equals(hitButton)) {
				logic.updateLaporanOperasi(idLaporanTanah,
						getParam("txtTarikhOperasi"), getParam("txtLaporan"),
						getParam("txtNamaPelaporOperasi"),
						getParam("txtJawatanLO"), getParam("txtCatatanLO"),
						getParam("txtTarikhTamatOperasi"),
						Utils.RemoveSymbol(getParam("txtKosOperasi")),getParam("txtMasaDari"), 
						getParam("txtMasaHingga"), getParam("txtLaporanSemasa"), getParam("txtLaporanSelepas"), 
						getParam("txtTempat"), getParam("txtPenceroboh"), 
						session);
			}
			if ("simpanDokumen".equals(hitButton)) {
				uploadFiles(idLaporanTanah, idPermohonan, session);
			}
			if ("simpanKemaskiniDokumen".equals(hitButton)) {
				logic.simpanKemaskiniDokumen(idDokumen,
						getParam("txtNamaImej"), getParam("txtCatatanImej"),
						session);
			}
			if ("hapusDokumen".equals(hitButton)) {
				logic.hapusDokumen(idDokumen, session);
			}
			if ("simpanKehadiran".equals(hitButton)) {

				String listNama[] = this.request.getParameterValues("txtNama");
				String listAgensi[] = this.request
						.getParameterValues("txtAgensi");
				String listJawatan[] = this.request
						.getParameterValues("txtJawatan");

				if (listNama != null) {
					for (int i = 0; i < listNama.length; i++) {
						if (!listNama[i].equals("")) {
							logic.saveKehadiran(i, idLaporanTanah, listNama[i],
									listAgensi[i], listJawatan[i],
									getParam("flagPengerusi"), session);
						}
					}
				}
			}
			if ("simpanKemaskiniKehadiran".equals(hitButton)) {
				logic.updateKehadiran(idLaporanTanah, idPegawaiLaporanTanah,
						getParam("txtNama"), getParam("txtAgensi"),
						getParam("txtJawatan"), getParam("socPengerusi"),
						session);
			}
			if ("hapusKehadiran".equals(hitButton)) {
				logic.hapusKehadiran(idPegawaiLaporanTanah, session);
			}

			if ("simpanMaklumatSuratPenghargaan".equals(hitButton)) {
				idUlasanTeknikal = logic.simpanMaklumatSuratPenghargaan(
						idPermohonan, idPejabat, idNegeri,
						getParam("txtTarikhHantar"), getParam("txtJangkaMasa"),
						getParam("txtTarikhJangkaTerima"),
						getParam("idSuratKe"), idKementerianTanah,
						idAgensiTanah, session);
			}
			if ("simpanMaklumatUlanganSuratPenghargaan".equals(hitButton)) {
				idUlasanTeknikal = logic.simpanMaklumatUlangaSuratPenghargaan(
						idUlasanTeknikal, idPermohonan, idPejabat, idNegeri,
						getParam("txtTarikhHantar"), getParam("txtJangkaMasa"),
						getParam("txtTarikhJangkaTerima"),
						getParam("idSuratKe"), idKementerianTanah,
						idAgensiTanah, session);
			}
			if ("simpanKemaskiniMaklumatSuratPenghargaan".equals(hitButton)) {
				logic.simpanKemaskiniMaklumaSuratPenghargaan(idUlasanTeknikal,
						idPejabat, idNegeri, getParam("txtTarikhHantar"),
						getParam("txtJangkaMasa"),
						getParam("txtTarikhJangkaTerima"), flagStatus,
						getParam("txtTarikhTerima"),
						getParam("txtTarikhSurat"), getParam("txtNoRujukan"),
						getParam("txtUlasan"), getParam("idSuratKe"),
						idKementerianTanah, idAgensiTanah,
						getParam("txtNamaPegawai"), getParam("txtJawatan"),
						session);
			}
			if ("hapusMaklumatKJPKJT".equals(hitButton)) {
				logic.hapusMaklumatKJPKJT(idUlasanTeknikal, session);
			}
			if ("hapusLO".equals(hitButton)) {
				logic.hapusMaklumatLO(idLaporanTanah, session);
			}

			if ("doSelesaiPermohonan".equals(hitButton)) {
				//System.out.println("cek status dalam selesai =" + idStatus);
				logicHeader.doSelesaiPermohonan(idFail, idPermohonan, idStatus,
						getParam("tarikhSelesai"), getParam("txtSebab"),
						session);
				step = "";
			}
			if ("doBatalPermohonan".equals(hitButton)) {
				logicHeader.doBatalPermohonan(idFail, idPermohonan, idStatus,
						getParam("tarikhBatal"), getParam("txtSebab"), session);
				step = "";
			}
		}

		// HEADER
		beanHeader = new Vector();
		logicHeader.setMaklumatPermohonan(idFail, session);
		beanHeader = logicHeader.getBeanMaklumatPermohonan();
		this.context.put("BeanHeader", beanHeader);

		if (beanHeader.size() != 0) {
			Hashtable hashHeader = (Hashtable) logicHeader.getBeanMaklumatPermohonan().get(0);
			idFail = (String) hashHeader.get("idFail");
			idPermohonan = (String) hashHeader.get("idPermohonan");
			idStatus = (String) hashHeader.get("idStatus");
			status = (String) hashHeader.get("status");
			idNegeriTanah = (String) hashHeader.get("idNegeriTanah");
			idKementerianTanah = (String) hashHeader.get("idKementerianTanah");
			idAgensiTanah = (String) hashHeader.get("idAgensiTanah");
			idHakmilik = (String) hashHeader.get("idHakmilik");
		}

		// GET FLAG OPEN DETAIL
		flagOpenDetail = logicHeader.getFlagOpenDetail(idStatus, 7); // 7 = LAPORAN OPERASI

		vm = "app/php2/frmCRBSenaraiOperasiPenguatkuasaan.jsp";

		if ("0".equals(selectedTabUpper)) {

			if ("newLaporanOperasi".equals(actionCRB)) {

				if ("new".equals(modePopup)) {
					this.context.put("readonly", "");
					this.context.put("inputTextClass", "");
					this.context.put("disabled", "");

					// MAKLUMAT LAPORAN OPERASI
					beanMaklumatLaporanOperasi = new Vector();
					Hashtable hashLO = new Hashtable();
					hashLO.put("tarikhOperasi", getParam("txtTarikhOperasi") == null ? "" : getParam("txtTarikhOperasi"));
					hashLO.put("tarikhTamatOperasi", getParam("txtTarikhTamatOperasi") == null ? "" : getParam("txtTarikhTamatOperasi"));
					hashLO.put("laporan", getParam("txtLaporan") == null ? "" : getParam("txtLaporan"));
					hashLO.put("catatan", getParam("txtCatatanLO") == null ? "" : getParam("txtCatatanLO"));
					hashLO.put("kosOperasi", getParam("txtKosOperasi") == null ? "" : getParam("txtKosOperasi"));
					hashLO.put("namaPegawaiOperasi", getParam("txtNamaPelaporOperasi") == null ? "" : getParam("txtNamaPelaporOperasi"));
					hashLO.put("jawatanLO", getParam("txtJawatanLO") == null ? "" : getParam("txtJawatanLO"));
					
					hashLO.put("masaDari", getParam("txtMasaDari") == null ? "" : getParam("txtMasaDari"));
					hashLO.put("masaHingga", getParam("txtMasaHingga") == null ? "" : getParam("txtMasaHingga"));
					hashLO.put("semasaOperasi", getParam("txtLaporanSemasa") == null ? "" : getParam("txtLaporanSemasa"));
					hashLO.put("selepasOperasi", getParam("txtLaporanSelepas") == null ? "" : getParam("txtLaporanSelepas"));
					hashLO.put("tempat", getParam("txtTempat") == null ? "" : getParam("txtTempat"));
					hashLO.put("penceroboh", getParam("txtPenceroboh") == null ? "" : getParam("txtPenceroboh"));
					
					beanMaklumatLaporanOperasi.addElement(hashLO);
					this.context.put("BeanMaklumatLaporanOperasi", beanMaklumatLaporanOperasi);
				}

			} else if ("viewLaporanOperasi".equals(actionCRB)) {

				if ("0".equals(selectedTabLower)) {
					// MAKLUMAT LAPORAN OPERASI
					beanMaklumatLaporanOperasi = new Vector();
					logic.setMaklumatLaporanOperasi(idLaporanTanah);
					beanMaklumatLaporanOperasi = logic.getBeanMaklumatLaporanOperasi();
					this.context.put("BeanMaklumatLaporanOperasi", beanMaklumatLaporanOperasi);

					if ("view".equals(modePopup)) {
						this.context.put("readonly", "readonly");
						this.context.put("inputTextClass", "disabled");
						this.context.put("disabled", "disabled");
					} else {
						this.context.put("readonly", "");
						this.context.put("inputTextClass", "");
						this.context.put("disabled", "");
					}
				}

				if ("1".equals(selectedTabLower)) {
					if ("update".equals(modePopup)) {

						this.context.put("readonlyPopup", "");
						this.context.put("inputTextClassPopup", "");

						beanMaklumatKehadiran = new Vector();
						logic.setMaklumatKehadiran(idPegawaiLaporanTanah);
						beanMaklumatKehadiran = logic.getBeanMaklumatKehadiran();
						this.context.put("BeanMaklumatKehadiran", beanMaklumatKehadiran);

					} else {

						this.context.put("readonlyPopup", "readonly");
						this.context.put("inputTextClassPopup", "disabled");

						beanMaklumatKehadiran = new Vector();
						logic.setMaklumatKehadiran(idPegawaiLaporanTanah);
						beanMaklumatKehadiran = logic.getBeanMaklumatKehadiran();
						this.context.put("BeanMaklumatKehadiran", beanMaklumatKehadiran);
					}

					// SENARAI KEHADIRAN
					logic.setSenaraiKehadiran(idLaporanTanah);
					senaraiKehadiran = logic.getListKehadiran();
					this.context.put("SenaraiKehadiran", senaraiKehadiran);
				}

				if ("2".equals(selectedTabLower)) {

					// OPEN POPUP DOKUMEN
					if ("openPopupDokumen".equals(flagPopup)) {

						if ("new".equals(modePopup)) {

							this.context.put("readonlyPopup", "");
							this.context.put("inputTextClassPopup", "");
							
							beanMaklumatImejan = new Vector();
							Hashtable hashMaklumatImejan = new Hashtable();
							hashMaklumatImejan.put("namaImej", "");
							hashMaklumatImejan.put("catatanImej", "");
							beanMaklumatImejan.addElement(hashMaklumatImejan);
							this.context.put("BeanMaklumatImejan", beanMaklumatImejan);

						} else if ("update".equals(modePopup)) {

							this.context.put("readonlyPopup", "");
							this.context.put("inputTextClassPopup", "");

							// MAKLUMAT DOKUMEN
							beanMaklumatImejan = new Vector();
							logic.setMaklumatImej(idDokumen);
							beanMaklumatImejan = logic.getBeanMaklumatImejan();
							Hashtable hashMaklumatImejanDB = (Hashtable) logic.getBeanMaklumatImejan().get(0);
							Hashtable hashMaklumatImejan = new Hashtable();
							beanMaklumatImejan.addElement(hashMaklumatImejan);
							this.context.put("BeanMaklumatImejan", beanMaklumatImejan);

						} else if ("view".equals(modePopup)) {

							this.context.put("readonlyPopup", "readonly");
							this.context.put("inputTextClassPopup", "disabled");

							// MAKLUMAT DOKUMEN
							beanMaklumatImejan = new Vector();
							logic.setMaklumatImej(idDokumen);
							beanMaklumatImejan = logic.getBeanMaklumatImejan();
							this.context.put("BeanMaklumatImejan", beanMaklumatImejan);
						}
					}

					// SENARAI IMEJAN
					senaraiImejan = new Vector();
					logic.setSenaraiImejan(idLaporanTanah);
					senaraiImejan = logic.getListImejan();
					this.context.put("SenaraiImejan", senaraiImejan);
				}
			}

			// SENARAI LAWATAN OPERASI
			logic.setSenaraiLaporanOperasi(idPermohonan);
			senaraiLaporanOperasi = logic.getListLaporanOperasi();
			this.context.put("SenaraiLaporanOperasi", senaraiLaporanOperasi);
		}

		if ("1".equals(selectedTabUpper)) {
			// OPEN POPUP DETAIL MAKLUMAT SURAT PENGHARGAAN
			if ("openSuratPenghargaan".equals(flagPopup)) {

				if ("new".equals(modePopup)) {
					this.context.put("readonlyPopup", "");
					this.context.put("inputTextClassPopup", "");
					this.context.put("disabled", "");
					this.context.put("idSuratKe", flagSuratKe);
					this.context.put("readonlyS", "");
					this.context.put("inputTextClassS", "");

					this.context.put("selectNegeri", HTML.SelectNegeri( "socNegeri", Long.parseLong(idNegeri), "", " onChange=\"doChangeNegeri();\""));
					this.context.put("selectPejabat", HTML.SelectPejabatByIdNegeriAndJenisPejabat("socPejabat", Long.parseLong(idPejabat),
									"", " onChange=\"doChangePejabat();\"", idNegeri, "2"));

					if ("doChangeSuratKe".equals(submit) || "doChangeNegeri".equals(submit)) {
						idPejabat = "99999";
					}
					
					if ("".equals(submit)) {

						beanMaklumatSuratPenghargaan = new Vector();
						Hashtable hashMaklumatNotis = new Hashtable();
						hashMaklumatNotis.put("tarikhHantar", "");
						hashMaklumatNotis.put("jangkamasa", "");
						hashMaklumatNotis.put("tarikhJangkaTerima", "");
						beanMaklumatSuratPenghargaan.addElement(hashMaklumatNotis);
						this.context.put("BeanMaklumatSuratPenghargaan", beanMaklumatSuratPenghargaan);

						idNegeri = "99999";
						idPejabat = "99999";

					} else {

						beanMaklumatSuratPenghargaan = new Vector();
						Hashtable hashMaklumatNotis = new Hashtable();
						hashMaklumatNotis.put("tarikhHantar", getParam("txtTarikhHantar"));
						hashMaklumatNotis.put("jangkamasa", getParam("txtJangkaMasa"));
						hashMaklumatNotis.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));
						beanMaklumatSuratPenghargaan.addElement(hashMaklumatNotis);
						this.context.put("BeanMaklumatSuratPenghargaan", beanMaklumatSuratPenghargaan);
					}

					if ("99999".equals(idNegeri) && !"".equals(idNegeriTanah)) {
						idNegeri = idNegeriTanah;
					}

					if ("PTD".equals(flagSuratKe)) {
						this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri", Long.parseLong(idNegeri), "", " onChange=\"doChangeNegeri();\""));
						this.context.put("selectPejabat", HTML.SelectPejabatByIdNegeriAndJenisPejabat("socPejabat", Long.parseLong(idPejabat), "",
										" onChange=\"doChangePejabat();\"", idNegeri, "2"));

						beanMaklumatPejabat = new Vector();
						logic.setMaklumatPejabat(idPejabat);
						beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
						this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);
						
					} else if ("JKPTG".equals(flagSuratKe)) {
						this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri", Long.parseLong(idNegeri), "", " onChange=\"doChangeNegeri();\""));
						this.context.put("selectPejabat", HTML.SelectPejabatKPTGByIdNegeriIdSeksyen("socPejabat", Long.parseLong(idPejabat),
										""," onChange=\"doChangePejabat();\"", idNegeri, "4"));

						beanMaklumatPejabat = new Vector();
						logic.setMaklumatPejabatJKPTG(idPejabat);
						beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
						this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);
					}

				} else if ("newUlangan".equals(modePopup)) {
					this.context.put("readonlyPopup", "");
					this.context.put("inputTextClassPopup", "");
					this.context.put("disabled", "");
					this.context.put("readonlyS", "readonly");
					this.context.put("inputTextClassS", "disabled");

					if ("".equals(submit)) {

						beanMaklumatSuratPenghargaan = new Vector();
						Hashtable hashMaklumatNotis = new Hashtable();
						hashMaklumatNotis.put("tarikhHantar", "");
						hashMaklumatNotis.put("jangkamasa", "");
						hashMaklumatNotis.put("tarikhJangkaTerima", "");
						beanMaklumatSuratPenghargaan.addElement(hashMaklumatNotis);
						this.context.put("BeanMaklumatSuratPenghargaan", beanMaklumatSuratPenghargaan);

					} else {

						beanMaklumatSuratPenghargaan = new Vector();
						Hashtable hashMaklumatNotis = new Hashtable();
						hashMaklumatNotis.put("tarikhHantar", getParam("txtTarikhHantar"));
						hashMaklumatNotis.put("jangkamasa", getParam("txtJangkaMasa"));
						hashMaklumatNotis.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));
						beanMaklumatSuratPenghargaan.addElement(hashMaklumatNotis);
						this.context.put("BeanMaklumatSuratPenghargaan", beanMaklumatSuratPenghargaan);
					}

					if ("99999".equals(idNegeri) && !"".equals(idNegeriTanah)) {
						idNegeri = idNegeriTanah;
					}
					this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri", Long.parseLong(idNegeri), "disabled", " class=\"disabled\""));

					if ("PTD".equals(flagSuratKe)) {
						this.context.put("selectPejabat", HTML.SelectPejabatByIdNegeriAndJenisPejabat("socPejabat", Long.parseLong(idPejabat), "disabled",
										" class=\"disabled\"", idNegeri, "2"));

						beanMaklumatPejabat = new Vector();
						logic.setMaklumatPejabat(idPejabat);
						beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
						this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);
						
					} else if ("JKPTG".equals(flagSuratKe)) {
						this.context.put("selectPejabat", HTML.SelectPejabatKPTGByIdNegeriIdSeksyen("socPejabat", Long.parseLong(idPejabat),
										"disabled"," class=\"disabled\"", idNegeri, "4"));

						beanMaklumatPejabat = new Vector();
						logic.setMaklumatPejabatJKPTG(idPejabat);
						beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
						this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);
					}

				} else if ("view".equals(modePopup)) {
					this.context.put("readonlyPopup", "readonly");
					this.context.put("inputTextClassPopup", "disabled");
					this.context.put("disabled", "disabled");
					this.context.put("readonlyS", "readonly");
					this.context.put("inputTextClassS", "disabled");

					beanMaklumatSuratPenghargaan = new Vector();
					logic.setMaklumatKJT(idUlasanTeknikal);
					beanMaklumatSuratPenghargaan = logic.getBeanMaklumatKJT();
					this.context.put("BeanMaklumatSuratPenghargaan", beanMaklumatSuratPenghargaan);

					if (beanMaklumatSuratPenghargaan.size() != 0) {
						Hashtable hashMaklumatKJT = (Hashtable) logic.getBeanMaklumatKJT().get(0);
						idNegeri = (String) hashMaklumatKJT.get("idNegeri");
						idPejabat = (String) hashMaklumatKJT.get("idPejabat");
						flagStatus = (String) hashMaklumatKJT.get("flagStatus");
						flagAktif = (String) hashMaklumatKJT.get("flagAktif");
						flagSuratKe = (String) hashMaklumatKJT.get("flagKJP");
					}
					this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri", Long.parseLong(idNegeri), "disabled", " class=\"disabled\""));
					if ("PTD".equals(flagSuratKe)) {
						this.context.put("selectPejabat", HTML.SelectPejabatByIdNegeriAndJenisPejabat("socPejabat", Long.parseLong(idPejabat), "disabled",
										" class=\"disabled\"", idNegeri, "2"));
						beanMaklumatPejabat = new Vector();
						logic.setMaklumatPejabat(idPejabat);
						beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
						this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);
						
					} else if ("JKPTG".equals(flagSuratKe)) {
						this.context.put("selectPejabat", HTML.SelectPejabatKPTGByIdNegeriIdSeksyen("socPejabat", Long.parseLong(idPejabat),
										"disabled"," class=\"disabled\"", idNegeri, "4"));
						beanMaklumatPejabat = new Vector();
						logic.setMaklumatPejabatJKPTG(idPejabat);
						beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
						this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);
					}

				} else if ("update".equals(modePopup)) {
					this.context.put("readonlyPopup", "");
					this.context.put("inputTextClassPopup", "");
					this.context.put("disabled", "");

					if ("doChangeSuratKe".equals(submit) || "doChangeNegeri".equals(submit)) {
						idPejabat = "99999";
					}

					beanMaklumatSuratPenghargaan = new Vector();
					Hashtable hashMaklumatNotis = new Hashtable();
					hashMaklumatNotis.put("tarikhHantar", getParam("txtTarikhHantar"));
					hashMaklumatNotis.put("jangkamasa", getParam("txtJangkaMasa"));
					hashMaklumatNotis.put("tarikhJangkaTerima", getParam("txtTarikhJangkaTerima"));
					hashMaklumatNotis.put("tarikhTerima", getParam("txtTarikhTerima"));
					hashMaklumatNotis.put("tarikhSurat", getParam("txtTarikhSurat"));
					hashMaklumatNotis.put("noRujukan", getParam("txtNoRujukanSurat"));
					hashMaklumatNotis.put("ulasan", getParam("txtUlasan"));
					hashMaklumatNotis.put("namaPegawai", getParam("txtNamaPegawai"));
					hashMaklumatNotis.put("jawatan", getParam("txtJawatan"));
					beanMaklumatSuratPenghargaan.addElement(hashMaklumatNotis);
					this.context.put("BeanMaklumatSuratPenghargaan", beanMaklumatSuratPenghargaan);

					if ("1".equals(flagStatus)) {
						this.context.put("readonlyS", "");
						this.context.put("inputTextClassS", "");

						if ("99999".equals(idNegeri) && !"".equals(idNegeriTanah)) {
							idNegeri = idNegeriTanah;
						}

						if ("PTD".equals(flagSuratKe)) {
							this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri", Long.parseLong(idNegeri), "", " onChange=\"doChangeNegeri();\""));
							this.context.put("selectPejabat", HTML.SelectPejabatByIdNegeriAndJenisPejabat("socPejabat", Long.parseLong(idPejabat), "",
											" onChange=\"doChangePejabat();\"", idNegeri, "2"));

							beanMaklumatPejabat = new Vector();
							logic.setMaklumatPejabat(idPejabat);
							beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
							this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);
							
						} else if ("JKPTG".equals(flagSuratKe)) {
							this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri", Long.parseLong(idNegeri), "", " onChange=\"doChangeNegeri();\""));
							this.context.put("selectPejabat", HTML.SelectPejabatKPTGByIdNegeriIdSeksyen("socPejabat", Long.parseLong(idPejabat), "",
											" onChange=\"doChangePejabat();\"", idNegeri, "4"));

							beanMaklumatPejabat = new Vector();
							logic.setMaklumatPejabatJKPTG(idPejabat);
							beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
							this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);
						}

					} else {
						this.context.put("readonlyS", "readonly");
						this.context.put("inputTextClassS", "disabled");

						if ("99999".equals(idNegeri) && !"".equals(idNegeriTanah)) {
							idNegeri = idNegeriTanah;
						}

						if ("PTD".equals(flagSuratKe)) {
							this.context.put("selectPejabat", HTML.SelectPejabatByIdNegeriAndJenisPejabat("socPejabat",
											Long.parseLong(idPejabat), "disabled", " class=\"disabled\"", idNegeri, "2"));
							beanMaklumatPejabat = new Vector();
							logic.setMaklumatPejabat(idPejabat);
							beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
							this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);
						} else if ("JKPTG".equals(flagSuratKe)) {
							this.context.put("selectPejabat", HTML.SelectPejabatKPTGByIdNegeriIdSeksyen("socPejabat", Long.parseLong(idPejabat),
											"disabled"," class=\"disabled\"", idNegeri, "4"));
							beanMaklumatPejabat = new Vector();
							logic.setMaklumatPejabatJKPTG(idPejabat);
							beanMaklumatPejabat = logic.getBeanMaklumatPejabat();
							this.context.put("BeanMaklumatPejabat", beanMaklumatPejabat);
						}
					}
				}
			}

			// DOKUMEN SURAT PENGHARGAAN
			senaraiSuratPenghargaan = new Vector();
			logic.setSenaraiSuratPenghargaan(idPermohonan);
			senaraiSuratPenghargaan = logic.getListSuratPenghargaan();
			this.context.put("SenaraiSuratPenghargaan", senaraiSuratPenghargaan);
		}

		if ("selesaiPermohonan".equals(step)) {
			vm = "app/php2/frmSelesaiPermohonan.jsp";
		}
		if ("batalPermohonan".equals(step)) {
			vm = "app/php2/frmBatalPermohonan.jsp";
		}

		// SET DEFAULT PARAM
		this.context.put("flagPopup", flagPopup);
		this.context.put("modePopup", modePopup);
		this.context.put("selectedTabUpper", selectedTabUpper);
		this.context.put("selectedTabLower", selectedTabLower);

		// SET ID PARAM
		this.context.put("idFail", idFail);
		this.context.put("idPermohonan", idPermohonan);
		this.context.put("idStatus", idStatus);
		this.context.put("idLaporanTanah", idLaporanTanah);
		this.context.put("idPegawaiLaporanTanah", idPegawaiLaporanTanah);
		this.context.put("idUlasanTeknikal", idUlasanTeknikal);
		this.context.put("idDokumen", idDokumen);
		this.context.put("idKementerianTanah", idKementerianTanah);
		this.context.put("idAgensiTanah", idAgensiTanah);
		this.context.put("idDokumen", idDokumen);
		this.context.put("flagStatus", flagStatus);
		this.context.put("flagAktif", flagAktif);
		this.context.put("idSuratKe", flagSuratKe);
		this.context.put("actionCRB", actionCRB);

		this.context.put("flagOpenDetail", flagOpenDetail);
		this.context.put("status", status.toUpperCase());

		return vm;
	}

	// UPLOAD FILE
	private void uploadFiles(String idLaporanTanah, String idPermohonan, HttpSession session) throws Exception {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		
		if (isMultipart != false) {
			List items = upload.parseRequest(request);
			Iterator itr = items.iterator();
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				if ((!(item.isFormField())) && (item.getName() != null)
						&& (!("".equals(item.getName())))) {
					saveData(item, idLaporanTanah, idPermohonan, session);
				}
			}
		}
	}

	private void saveData(FileItem item, String idLaporanTanah, String idPermohonan, HttpSession session) throws Exception {

		Db db = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");  
		try {
			db = new Db();

			// TBLPHPDOKUMEN
			long idDokumen = DB.getNextID("TBLPHPDOKUMEN_SEQ");
			Connection con = db.getConnection();
			con.setAutoCommit(false);
			PreparedStatement ps = con
					.prepareStatement("INSERT INTO TBLPHPDOKUMEN "
							+ "(ID_DOKUMEN,NAMA_DOKUMEN,CATATAN,ID_MASUK,TARIKH_MASUK,CONTENT,JENIS_MIME,NAMA_FAIL,ID_LAPORANTANAH,FLAG_DOKUMEN,ID_PERMOHONAN,JENIS_IMEJ) "
							+ "VALUES(?,?,?,?,SYSDATE,?,?,?,?,?,?,?)");
			ps.setLong(1, idDokumen);
			ps.setString(2, getParam("namaImej"));
			ps.setString(3, getParam("catatanImej"));
			ps.setString(4, userId);
			ps.setBinaryStream(5, item.getInputStream(), (int) item.getSize());
			ps.setString(6, item.getContentType());
			ps.setString(7, item.getName());
			ps.setString(8, idLaporanTanah);
			ps.setString(9, "I");
			ps.setString(10, idPermohonan);
			ps.setString(11, getParam("jenisImej"));
			ps.executeUpdate();

			con.commit();
			
		} finally {
			if (db != null)
				db.close();
		}
		
		this.context.put("completed", true);
	}
}