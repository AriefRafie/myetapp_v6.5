package ekptg.model.ppk.harta;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.util.DateUtil;

import org.apache.log4j.Logger;

import ekptg.helpers.EkptgCache;
import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.integrasi.FrmModelNilaianHartaTakAlih;
import ekptg.model.ppk.FrmHeaderPpk;
import ekptg.model.ppk.FrmPermohonanHTAData;
import ekptg.model.ppk.FrmPrmhnnSek8DaftarSek8InternalData;
import ekptg.model.ppk.FrmPrmhnnSek8InternalData;


public class HTABean extends EkptgCache implements IMaklumatHarta {
	
	private static Logger myLog = Logger.getLogger(ekptg.model.ppk.harta.HTABean.class);
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	FrmPrmhnnSek8DaftarSek8InternalData logic_A = null; 
	FrmHeaderPpk mainheader = null;

	Vector senaraiHTA = null;
	String READMODE = "readmode";
	String READMODED = "disabled";
	String YES = "yes";
	Date date = new Date();
	String currentDate = dateFormat.format(date);
	
	String txtNoHakmilikHtaam = "";
	String idSimati = "";
	String txtNoPTHtaam = "";
	String txtNilaiTarikhMohonHtaa = "";
	String txtNilaiTarikhMatiHtaam =  "";
	String socKategoriTanahHtaam =  "";
	String socJenisHakmilikHtaam =  "";
	String socStatusPemilikanHtaam =  "";
	String txtLuasHMpHtaam = "";
	String txtLuasAsalHtaam = "";
	String txtNoPajakan = "";
	String socJenisTanahHtaam = "";
	String txtBahagianSimati1 = "";
	String txtBahagianSimati2 = "";
	String txtTanggunganHtaam = "";
	String socJenisLuasHtaam = "";
	String txtCatatanHtaam = "";
	String txtNoPersHtaam = "";
	String FLAG_DAFTAR = "";
	String txtSekatan = "";
	String txtSyaratNyata = "";
	
	String idhtaamid = "";
	String idPermohonanSimati = "";
	String userID = "0";

	String socNegeriHtaamUp = "0";
	String socDaerahHtaamUp = "0";
	String idHarta = "0";
	
	
	public void getMaklumtHarta(org.apache.velocity.VelocityContext context
		,HttpSession session
		,Hashtable hParam
		,String mode
		,String bolehsimpan
		,int idnegeri
		,FrmPrmhnnSek8InternalData logic_internal
			) throws Exception {
		
		//int idnegerii = 0;
		String add_new_harta = "";
		String buttonhtaam = "";
		String tambahharta = "";
		String kembaliharta = "";
		int negeri = 0;
		int daerah = 0;
		int mukim = 0;
		String readmodenegeri = "";
		String readmodedaerah = "";
		String readmodemukim = "";
		String show_simpan_add_htaam = "";
		String show_batal_add_htaam = "";
		String show_kemaskini_htaam = "";
		String show_button = "";
		String show_htaa_add_table = "";
		String show_save_update_htaam = "";
		String show_batal_update_htaam = "";
		String show_hapus_htaam = "";
		String show_kembali_htaam = "";
		String show_htaa_update_table = "";
		String listnegeri = "";
		Vector listDaerahbyNegeri = null;
		Vector listMukimbyDaerah = null;
		
		//25/08/2017
		FrmPermohonanHTAData permohonanHarta = new FrmPermohonanHTAData();
		logic_A = new FrmPrmhnnSek8DaftarSek8InternalData();		
		//senaraiHTA = new Vector();
		Vector beanMaklumatPelan = null;
		Vector listnegeribydaerah = null;
		Vector listmukim = null;
		Vector v = null;
		Vector listHTAid = null;
		
		Hashtable hashHTAid = null;
		
		//PARAM
		String mati = String.valueOf(hParam.get("id_Permohonansimati"));
		String matiHeader = String.valueOf(hParam.get("id_permohonansimati_atheader"));
		String idDokumen = String.valueOf(hParam.get("idDokumen"));
		String selectedHartaTakAlih = String.valueOf(hParam.get("selectedHartaTakAlih"));
		String idhtaam = String.valueOf(hParam.get("idhtaam"));
		String upload = String.valueOf(hParam.get("upload"));
		String idPermohonan = String.valueOf(hParam.get("idPermohonan"));
		//MAKLUMAT TANAH
		txtNoHakmilikHtaam = String.valueOf(hParam.get("txtNoHakmilikHtaam"));
		 idSimati = String.valueOf(hParam.get("idSimati"));
		 txtNoPTHtaam = String.valueOf(hParam.get("txtNoPTHtaam"));
		 txtNilaiTarikhMohonHtaa = String.valueOf(hParam.get("txtNilaiTarikhMohonHtaa"));
		 txtNilaiTarikhMatiHtaam = String.valueOf(hParam.get("txtNilaiTarikhMatiHtaam"));
		 socKategoriTanahHtaam = String.valueOf(hParam.get("socKategoriTanahHtaam"));
		 socJenisHakmilikHtaam = String.valueOf(hParam.get("socJenisHakmilikHtaam"));
		 socStatusPemilikanHtaam = String.valueOf(hParam.get("socStatusPemilikanHtaam"));
		 txtLuasHMpHtaam = String.valueOf(hParam.get("txtLuasHMpHtaam"));
		 txtLuasAsalHtaam = String.valueOf(hParam.get("txtLuasAsalHtaam"));
		 txtNoPajakan = String.valueOf(hParam.get("txtNoPajakan"));
		 socJenisTanahHtaam = String.valueOf(hParam.get("socJenisTanahHtaam"));
		 txtBahagianSimati1 = String.valueOf(hParam.get("txtBahagianSimati1"));
		 txtBahagianSimati2 = String.valueOf(hParam.get("txtBahagianSimati2"));
		 txtTanggunganHtaam = String.valueOf(hParam.get("txtTanggunganHtaam"));
		 socJenisLuasHtaam = String.valueOf(hParam.get("socJenisLuasHtaam"));
		 txtCatatanHtaam = String.valueOf(hParam.get("txtCatatanHtaam"));
		 txtNoPersHtaam = String.valueOf(hParam.get("txtNoPersHtaam"));
		 FLAG_DAFTAR = String.valueOf(hParam.get("FLAG_DAFTAR"));
		 txtSekatan = String.valueOf(hParam.get("txtSekatan"));
		 txtSyaratNyata = String.valueOf(hParam.get("txtSyaratNyata"));
		//SOC
		String socNegeriHtaam = String.valueOf(hParam.get("socNegeriHtaam"));
		String socDaerahHtaam = String.valueOf(hParam.get("socDaerahHtaam"));
		String socMukimHtaam = String.valueOf(hParam.get("socMukimHtaam"));
		
		socNegeriHtaamUp = String.valueOf(hParam.get("socNegeriHtaamUp"));
		socDaerahHtaamUp = String.valueOf(hParam.get("socDaerahHtaamUp"));
		idhtaamid = String.valueOf(hParam.get("idhtaamid"));
		String idPelan = String.valueOf(hParam.get("idPelan"));
		userID = String.valueOf(session.getAttribute("_ekptg_user_id"));	
		myLog.info("mode="+mode);
		if ("Htaamview".equals(mode)) {
			//IL
				//String mati = getParam("id_Permohonansimati");
//			String mati = getParam("id_permohonansimati_atheader");
			idPermohonanSimati = matiHeader;
			if (matiHeader.length() == 0) {
				idPermohonanSimati = mati;
			}//IL				
			//String idDokumen = getParam("idDokumen");
			//String selectedHartaTakAlih = getParam("selectedHartaTakAlih");	
			//logic_internal.setDataHTA(mati);
			//listHTA = logic_internal.getDataHTA();
			senaraiHTA = permohonanHarta.getDataHTA(idPermohonanSimati);
			//this.context.put("id_Permohonansimati", mati);//IL
			//this.context.put("listHTA", listHTA);
			tambahharta = "yes";
			kembaliharta = "yes";
			//this.context.put("selectedHartaTakAlih", selectedHartaTakAlih);//IL
			
		}else if ("add_new".equals(mode)) {
			//IL
//			String mati = getParam("id_Permohonansimati");
//			if (mati.length() == 0) {
//				mati = getParam("id_permohonansimati_atheader");
//			}
			idPermohonanSimati = mati;
			if (mati.length() == 0) {
				idPermohonanSimati = matiHeader;
			}
//			String idDokumen = getParam("idDokumen");
			//IL
			senaraiHTA = permohonanHarta.getDataHTA(idPermohonanSimati);

			//IL start
			/*//MAKLUMAT PELAN beanMaklumatPelan = new Vector();
			 * logic_internal.setMaklumatPelan(idDokumen); beanMaklumatPelan
			 * = logic_internal.getBeanMaklumatPelan();
			 * 
			 * // MAKLUMAT PELAN Hashtable hashPelan = new Hashtable();
			 * hashPelan.put("namaPelan",getParam("txtNamaPelan") == null ?
			 * "": getParam("txtNamaPelan"));
			 * beanMaklumatPelan.addElement(hashPelan);
			 * this.context.put("BeanMaklumatPelan", beanMaklumatPelan);
			 */	
			//IL end
			readmodenegeri = READMODE;
			readmodedaerah = READMODE;
			readmodemukim = READMODE;
			show_simpan_add_htaam = YES;
			show_batal_add_htaam = YES;
			show_kemaskini_htaam = YES;
			show_button = YES;
			show_htaa_add_table = YES;		
			add_new_harta = YES;
			buttonhtaam = "Tambah";
			
		}else if ("masukkan".equals(mode)) {
//			String idhtaam = getParam("idhtaam");//IL
			if (bolehsimpan.equals("yes")) {
				Hashtable gParam = new Hashtable();
				gParam.put("socNegeriHtaam",socNegeriHtaam);
				addHtaam(session,gParam,logic_internal);
			}
			//IL
			if (upload.equals("simpanUpload")) {
				//addHtaam(session);
//				uploadFiles(session);
				// mode = "";
			}
			//end IL

			//String id = idPermohonan;
			idPermohonanSimati = mati;
			//start IL
			if (mati.length() == 0) {
				idPermohonanSimati = matiHeader;
			}
//			String idDokumen = getParam("idDokumen");
			//end IL
			senaraiHTA = permohonanHarta.getDataHTA(idPermohonanSimati);

			tambahharta = YES;
			kembaliharta = YES;
			context.put("command", "");//IL
			context.put("mode", "");//IL
			context.put("action", "");//IL
			logic_A.updateDataNilai(idPermohonan, idPermohonanSimati, (String) session.getAttribute("_ekptg_user_id"));
			// String send =
			// this.request.getRequestURI()+"?_portal_module=FrmPrmhnnSek8Internal";;
			// this.response.sendRedirect(send);
			
		} else if ("negerichange".equals(mode)) {
			idPermohonanSimati = mati;
//			String idDokumen = getParam("idDokumen");//IL
			negeri = Integer.parseInt(socNegeriHtaam);
			senaraiHTA = permohonanHarta.getDataHTA(mati);

//			context.put("BeanMaklumatPelan", beanMaklumatPelan);//IL
			listnegeribydaerah = logic_A.getListDaerahbyNegeri(negeri);
//			context.put("listDaerahbyNegeri", listnegeribydaerah);
			context.put("noHakmilik", txtNoHakmilikHtaam);
			context.put("idSimati",idSimati);
			context.put("nopt",txtNoPTHtaam);
			context.put("nilai_Hta_memohon",txtNilaiTarikhMohonHtaa);
			context.put("nilai_Hta_mati",txtNilaiTarikhMatiHtaam);
			context.put("kategori",socKategoriTanahHtaam);
			context.put("jenishakmilik",socJenisHakmilikHtaam);
			context.put("pemilikan",socStatusPemilikanHtaam);
			context.put("luashmp",txtLuasHMpHtaam);
			context.put("luasasal",txtLuasAsalHtaam);
			context.put("nopajakan",txtNoPajakan);
			context.put("jenistanah",socJenisTanahHtaam);
			context.put("basimati",txtBahagianSimati1);
			context.put("bbsimati",txtBahagianSimati2);
			context.put("tanggungan",txtTanggunganHtaam);
			context.put("jenisluas",socJenisLuasHtaam);
			context.put("catatan",txtCatatanHtaam);
			context.put("noperserahan",txtNoPersHtaam);
//			negeri = idnegeri;
			context.put("FLAG_DAFTAR", "FLAG_DAFTAR");
			show_simpan_add_htaam = YES;
			show_batal_add_htaam = YES;
			//show_kemaskini_htaam = YES;
			show_button = YES;
			show_kembali_htaam = YES;
			show_htaa_add_table = YES;		
			// ADD BY PEJE TAMBAH FIELD SEKATAN & SYARAT NYATA
			context.put("sekatan", txtSekatan);
			context.put("syaratNyata",txtSyaratNyata);
			
			if (socNegeriHtaam != ""
				&& socNegeriHtaam != "0") {
				Vector s3 = logic_A.getListBandarByNegeri(negeri);
				context.put("listBandarSuratbyNegeri", s3);
			} else {
				context.put("listBandarSuratbyNegeri", "");
			}
			
		} else if ("daerahchange".equals(mode)) {
//			String mati = getParam("id_Permohonansimati");
//			String idDokumen = getParam("idDokumen");//IL
			idPermohonanSimati = mati;
			myLog.info("socNegeriHtaam="+socNegeriHtaam+",socDaerahHtaam="+socDaerahHtaam);
			daerah = Integer.parseInt(socDaerahHtaam);
			negeri = Integer.parseInt(socNegeriHtaam);
			senaraiHTA = permohonanHarta.getDataHTA(mati);

			//IL start
			// //MAKLUMAT PELAN
			// beanMaklumatPelan = new Vector();
			// logic_internal.setMaklumatPelan(idDokumen);
			// beanMaklumatPelan = logic_internal.getBeanMaklumatPelan();
			//
			// Hashtable hashPelan = new Hashtable();
			// hashPelan.put("namaPelan",getParam("txtNamaPelan") == null ?
			// "": getParam("txtNamaPelan"));
			// beanMaklumatPelan.addElement(hashPelan);
//			this.context.put("BeanMaklumatPelan", beanMaklumatPelan);
			//IL end
//			listnegeribydaerah = logic_A.getListDaerahbyNegeri(idnegerii);
//			context.put("listDaerahbyNegeri", listnegeribydaerah);
			listnegeribydaerah = logic_A.getListDaerahbyNegeri(negeri);
			//25/08/2017 By Mohamad Rosli
			context.put("senaraiDaerahbyNegeri", HTML.SelectDaerahByNegeri(socNegeriHtaam, "socDaerahHtaam",Utils.parseLong(socDaerahHtaam),"", "onChange=\"doChanges2()\""));
			listmukim = logic_A.getListMukimbyDaerah(daerah);
//			context.put("listMukimbyDaerah", listmukim);
			listMukimbyDaerah = listmukim;

			//25/08/2017 By Mohamad Rosli
			context.put("senaraiMukimbyDaerah", HTML.SelectMukimByDaerah(socDaerahHtaam, "socMukimHtaam", Utils.parseLong(socMukimHtaam) ,""));
			setValuesTanah(context);

			show_simpan_add_htaam = YES;
			show_batal_add_htaam = YES;
			show_button = YES;
			show_kembali_htaam = YES;
			//negeri = idnegeri;
			//daerah=iddaerah;
			show_htaa_add_table = YES;		

		}else if ("checkWujudLot".equals(mode)) {
//			String idDokumen = getParam("idDokumen");//IL
//			String mati = getParam("id_Permohonansimati");
			Hashtable getLot = null;
			FrmHeaderPpk mainheader = null;
			mainheader = new FrmHeaderPpk();
			idPermohonanSimati = mati;
			
			getLot = mainheader.getWujudLot(mati,txtNoPTHtaam
					,socNegeriHtaam,socDaerahHtaam,socMukimHtaam
					,socJenisHakmilikHtaam,txtNoHakmilikHtaam,txtNoPTHtaam);
			context.put("nopt",txtNoPTHtaam);
			if (String.valueOf(getLot.get("ID_HTA")) != null) {
				setValuesTanah(context,getLot);
				if (!getLot.get("ID_NEGERI").toString().equals("")) {
					negeri = Integer.parseInt((String) getLot.get("ID_NEGERI"));
					listnegeribydaerah = logic_A.getListDaerahbyNegeri(negeri);
//					context.put("listDaerahbyNegeri", listnegeribydaerah);
					listDaerahbyNegeri = listnegeribydaerah;
//					negeri = idnegeri;
					
				} else {
//					this.context.put("listDaerahbyNegeri", "");
					negeri = 0;
				}
				if (!getLot.get("ID_DAERAH").toString().equals("")) {
					daerah = Integer.parseInt((String) getLot.get("ID_DAERAH"));
					listmukim = logic_A.getListMukimbyDaerah(daerah);
//					this.context.put("listMukimbyDaerah", listmukim);
					listMukimbyDaerah = listmukim;
//					daerah=iddaerah;
					
				} else {
					//this.context.put("listMukimbyDaerah", "");
					daerah=0;
				}
				if (!getLot.get("ID_MUKIM").toString().equals("")) {
					mukim = Integer.parseInt((String) getLot.get("ID_MUKIM"));
//					mukim = idmukim;
							
				} else {
					mukim = 0;
				}
				context.put("CheckWujudLot", "Y");
				
			} else {
				if (!socNegeriHtaam.equals("") || !socNegeriHtaam.equals("0")) {
					negeri = Integer.parseInt(socNegeriHtaam);
					listnegeribydaerah = logic_A.getListDaerahbyNegeri(negeri);
//					this.context.put("listDaerahbyNegeri", listnegeribydaerah);
					listDaerahbyNegeri = listnegeribydaerah;
//					negeri = idnegeri;
					
				} else {
//					this.context.put("listDaerahbyNegeri", "");
					negeri = 0;
				}
				if (!socDaerahHtaam.equals("")) {
					daerah = Integer.parseInt(socDaerahHtaam);
					listmukim = logic_A.getListMukimbyDaerah(daerah);
//					this.context.put("listMukimbyDaerah", listmukim);
					listMukimbyDaerah = listmukim;
//					daerah=iddaerah;
					
				} else {
//					this.context.put("listMukimbyDaerah", "");
					daerah=0;
					
				}
				if (!socMukimHtaam.equals("") || !socMukimHtaam.equals("0")) {
					mukim = Integer.parseInt(socMukimHtaam);
//					mukim = idmukim;
					
				} else {
					mukim = 0;
				}
				setValuesTanah(context);
				context.put("CheckWujudLot", "T");
			}
			senaraiHTA = permohonanHarta.getDataHTA(mati);
			show_simpan_add_htaam = YES;
			show_batal_add_htaam = YES;
			show_button = YES;
			show_kembali_htaam = YES;
			show_htaa_add_table = YES;		

		}else if ("negerichangeup".equals(mode)) {
//			v = new Vector();
//			String mati = getParam("id_Permohonansimati");
//			String idDokumen = getParam("idDokumen");//IL
//			this.context.put("listMukimbyDaerah", "");
			if (socNegeriHtaamUp != "" 
				&& socNegeriHtaamUp != "0") {
				negeri = Integer.parseInt(socNegeriHtaamUp);
				listnegeribydaerah = logic_A.getListDaerahbyNegeri(idnegeri);
//				this.context.put("listDaerahbyNegeri", listnegeribydaerah);
				listDaerahbyNegeri = listnegeribydaerah;
//				negeri = idnegeri;
				context.put("bandar", "");
				
			} else {
//				this.context.put("listDaerahbyNegeri", "");
				negeri = 0;
				context.put("bandar", "");
			}
			if (socNegeriHtaamUp != ""
				&& socNegeriHtaamUp != "0") {
				Vector s3 = logic_A.getListBandarByNegeri(Integer.parseInt(socNegeriHtaamUp));
				context.put("listBandarSuratbyNegeri", s3);					
			} else {
				context.put("listBandarSuratbyNegeri", "");				
			}				
			senaraiHTA = permohonanHarta.getDataHTA(mati);

			//IL start
			// //MAKLUMAT PELAN
			// beanMaklumatPelan = new Vector();
			// logic_internal.setMaklumatPelan(idDokumen);
			// beanMaklumatPelan = logic_internal.getBeanMaklumatPelan();
			//
			// Hashtable hashPelan = new Hashtable();
			// hashPelan.put("namaPelan",getParam("txtNamaPelan") == null ?
			// "": getParam("txtNamaPelan"));
			// beanMaklumatPelan.addElement(hashPelan);
			//
			// this.context.put("BeanMaklumatPelan", beanMaklumatPelan);
			//IL end
			Hashtable h = new Hashtable();
			h.put("idhta", idhtaamid);
			if (socNegeriHtaamUp != "" 
				&& socNegeriHtaamUp != "0") {
				negeri = Integer.parseInt(socNegeriHtaamUp);
				h.put("negeri", negeri);
			} else {
				h.put("negeri", 0);
			}			

			//int idnegeri = Integer.parseInt(getParam("socNegeriHtaamUp"));
			//this.context.put("negeriup", idnegeri);
			//listBandarSuratbyNegeri = logic_A.getListBandarByNegeri(idnegeri);
			//listnegeribydaerah = logic_A.getListDaerahbyNegeri(idnegeri);
			//this.context.put("listDaerahbyNegeri", listnegeribydaerah);
			//this.context.put("listBandarSuratbyNegeri", listBandarSuratbyNegeri);
			//Hashtable h = new Hashtable();
			setValuesTanaHash(h,hParam );
			v = new Vector();
			v.addElement(h);
//			context.put("listHTAid", v);
			listHTAid = v;
			show_save_update_htaam = YES;
			show_batal_update_htaam = YES;
			show_hapus_htaam = YES;
			show_kembali_htaam = YES;
			show_htaa_update_table = YES;
			show_button = YES;
			tambahharta = YES;
			//this.context.put("negeriup", idnegeri);	
			
		} else if ("daerahchangeup".equals(mode)) {
			v = new Vector();
//			String mati = getParam("id_Permohonansimati");
//			String idDokumen = getParam("idDokumen");//IL				
			senaraiHTA = permohonanHarta.getDataHTA(mati);

			//IL start
			/* //MAKLUMAT PELAN beanMaklumatPelan = new Vector();
			 * logic_internal.setMaklumatPelan(idDokumen); beanMaklumatPelan
			 * = logic_internal.getBeanMaklumatPelan();
			 * 
			 * Hashtable hashPelan = new Hashtable();
			 * hashPelan.put("namaPelan",getParam("txtNamaPelan") == null ?
			 * "": getParam("txtNamaPelan"));
			 * beanMaklumatPelan.addElement(hashPelan);
			 */
			// this.context.put("BeanMaklumatPelan", beanMaklumatPelan);
			//IL end
			negeri = Integer.parseInt(socNegeriHtaamUp);
			daerah = Integer.parseInt(socDaerahHtaamUp);
			listnegeribydaerah = logic_A.getListDaerahbyNegeri(idnegeri);
//			this.context.put("listDaerahbyNegeri", listnegeribydaerah);
			listDaerahbyNegeri = listnegeribydaerah;
			listmukim = logic_A.getListMukimbyDaerah(daerah);
//			this.context.put("listMukimbyDaerah", listmukim);
			listMukimbyDaerah = listmukim;
			
			if (socNegeriHtaamUp != "" 
				&& socNegeriHtaamUp != "0") {
				Vector s3 = logic_A.getListBandarByNegeri(negeri);
				context.put("listBandarSuratbyNegeri", s3);				
			} else {
				context.put("listBandarSuratbyNegeri", "");
			}				
			Hashtable h = new Hashtable();
			setValuesTanaHash(h,hParam);
			//PARAM daerahchangeup
			h.put("alamathta1", String.valueOf(hParam.get("txtAlamat1Htaam")));
			h.put("bandar", String.valueOf(hParam.get("txtBandarHartaHtaamX2")));	
			h.put("daerah", daerah);

			v.addElement(h);
			listHTAid = v;			
//			context.put("listHTAid", v);
			show_save_update_htaam = YES;
			show_batal_update_htaam = YES;
			show_hapus_htaam = YES;
			show_kembali_htaam = YES;
			show_htaa_update_table = YES;
			show_button = YES;
			tambahharta = "yes";
			
		} else if ("getHtaam".equals(mode)) {
//			String idhtaam = getParam("idhtaam");
//			String idDokumen = getParam("idDokumen");//IL
//			String mati = getParam("id_Permohonansimati");
			idPermohonanSimati = mati;
			idHarta = idhtaam;
//			String idPelan = getParam("idPelan");//IL
//			logic_internal.setDataHTAbyIdHtaam(idhtaam,mati);
//			listHTAid = logic_internal.getDataHTAbyIdHtaam();
			senaraiHTA = permohonanHarta.getDataHTA(mati);
			//IL start
			// MAKLUMAT PELAN
			// beanMaklumatPelanUp = new Vector();
			// logic_internal.setMaklumatPelanUp(idDokumen);
			// beanMaklumatPelanUp =
			// logic_internal.getBeanMaklumatPelanUp();

			// Hashtable hashPelan = new Hashtable();
			// hashPelan.put("namaPelanUp",getParam("txtNamaPelanUp") ==
			// null ? "": getParam("txtNamaPelanUp"));
			// beanMaklumatPelanUp.addElement(hashPelan);
			//IL end

//			Hashtable k = (Hashtable) senaraiHTA.get(0);
			Hashtable k = permohonanHarta.getDataHTAbyIdHtaam(idHarta, mati);
			if (k.get("negeri").toString() != "" && k.get("negeri").toString() != "0") {
				Vector s3 = logic_A.getListBandarByNegeri(Integer.parseInt(k.get("negeri").toString()));
				context.put("listBandarSuratbyNegeri", s3);
			} else {
				context.put("listBandarSuratbyNegeri", "");
			}		
//			this.context.put("idhtaam", idhtaam);
			show_kemaskini_htaam = YES;
			show_hapus_htaam = YES;
			show_kembali_htaam = YES;
			show_button = YES;
			show_htaa_update_table = YES;
			tambahharta = YES;
			readmodenegeri = READMODED;
			readmodedaerah = READMODED;
			readmodemukim = READMODED;
			context.put("readmode", "disabled");
			
		} else if ("getHtaamStatus".equals(mode)) {
			// Tukar Status
//			String mati = getParam("id_Permohonansimati");
			senaraiHTA = permohonanHarta.getDataHTA(mati);
			idPermohonanSimati = mati;

			tambahharta = YES;
			kembaliharta = YES;
//			String id = getParam("idPermohonan");
			//String id_sub = getParam("id_Suburusanstatusfail");
			//PARAM
			String id_Fail = String.valueOf(hParam.get("id_Fail"));

			if (bolehsimpan.equals("yes")) {
				// :::SUB
				// ID_STATUS : 9
				// ID_SUBURUSAN : 342
				// logic_A.htaamstatus(id, (String)
				// session.getAttribute("_ekptg_user_id"), id_sub, id_Fail);
				logic_A.kemaskiniSubUrusanStatusFail(session, idPermohonan,(String) session.getAttribute("_ekptg_user_id"),"9", "342", id_Fail);
			}
			/*String idhtaam = Integer.parseInt(getParam("idhtaamid")); int
			 * mati = Integer.parseInt(getParam("id_Permohonansimati"));
			 * String id = Integer.parseInt(getParam("idPermohonan"));
			 * String id_sub = Integer.parseInt(getParam("id_Suburusanstatusfail")); String id_Fail
			 * = Integer.parseInt(getParam("id_Fail"));
			 * 
			 * if (bolehsimpan.equals("yes")) { logic_A.htaamstatus(id,
			 * (String) session .getAttribute("_ekptg_user_id"), id_sub,
			 * id_Fail); } logic_internal.setDataHTAbyIdHtaam(idhtaam);
			 * listHTAid = logic_internal.getDataHTAbyIdHtaam();
			 * logic_internal.setDataHTA(mati); listHTA =
			 * logic_internal.getDataHTA(); this.context.put("listHTA",
			 * listHTA); this.context.put("idhtaam", idhtaam);
			 * this.context.put("listHTAid", listHTAid);
			 * this.context.put("show_kemaskini_htaam", "yes");
			 * this.context.put("show_hapus_htaam", "yes");
			 * this.context.put("show_kembali_htaam", "yes");
			 * this.context.put("show_button", "yes");
			 * this.context.put("show_htaa_update_table", "yes");
			 * this.context.put("tambahharta", "yes");
			 * this.context.put("readmodenegeri", "disabled");
			 * this.context.put("readmodedaerah", "disabled");
			 * this.context.put("readmodemukim", "disabled");
			 * this.context.put("readmode", "disabled");
			 */
		} else if ("hapusHtaam".equals(mode)) {
//			String idhtaam = getParam("idhtaamid");
			idPermohonanSimati = mati;
			logic_internal.deleteHtaamInternal(idDokumen, idhtaam, mati);
			senaraiHTA = permohonanHarta.getDataHTA(mati);
			tambahharta = "yes";
			kembaliharta = "yes";
			
		} else if ("kemaskiniHtaam".equals(mode)) {
//			String flag_tukar_jenis_hta = getParam("flag_tukar_jenis_hta");
			idPermohonanSimati = mati;
			//PARAM
			String flag_tukar_jenis_hta = String.valueOf(hParam.get("flag_tukar_jenis_hta"));
			if (flag_tukar_jenis_hta.equals("ADA")) {
				if (bolehsimpan.equals("yes")) {
//					String idhtaam = getParam("idhtaam");//IL
					if (String.valueOf(hParam.get("nama_skrin")).equals("tiadahakmilik")) {
						updateHtaamX(hParam,logic_internal);
					} else {
						updateHtaam(hParam,logic_internal);
//						uploadFiles(session);//IL
					}
				}
			}

//			String idhtaam = getParam("idhtaam");	//IL			
			//String idhtaam = getParam("idhtaamid");
			senaraiHTA = permohonanHarta.getDataHTA(mati);
//			logic_internal.setDataHTAbyIdHtaam(idhtaam,getParam("id_Permohonansimati"));
//			listHTAid = logic_internal.getDataHTAbyIdHtaam();
//			Hashtable b = (Hashtable) listHTAid.get(0);
			Hashtable b = permohonanHarta.getDataHTAbyIdHtaam(idhtaamid, mati);
			String nn = b.get("negeri").toString();
			String dd = b.get("daerah").toString();
			if (nn != "" && nn != "0") {
				int idn = Integer.parseInt(nn);
				listnegeribydaerah = logic_A.getListDaerahbyNegeri(idn);
//				this.context.put("listDaerahbyNegeri", listnegeribydaerah);
				listDaerahbyNegeri = listnegeribydaerah;
				
			}
			if (dd != "" && dd != "0") {
				int idd = Integer.parseInt(dd);
				listmukim = logic_A.getListMukimbyDaerah(idd);
//				this.context.put("listMukimbyDaerah", listmukim);
				listMukimbyDaerah = listmukim;
			}
//			this.context.put("idhtaam", idhtaam);
//			this.context.put("listHTAid", listHTAid);
			show_save_update_htaam = YES;
			show_batal_update_htaam = YES;
			show_hapus_htaam = YES;
			show_kembali_htaam = YES;
			show_button = YES;
			show_htaa_update_table = YES;
			tambahharta = YES;
			
		} else if ("simpanHtaam".equals(mode)) {
//			String idhtaam = getParam("idhtaam");//IL
			if (bolehsimpan.equals("yes")) {
//				updateHtaam(session);
				updateHtaam(hParam,logic_internal);
			}
			//IL start
			if (upload.equals("simpanUpload")) {
//				updateHtaam(session);					
				updateHtaam(hParam,logic_internal);
				if(idDokumen == ""){ 
//					uploadFilesA(idhtaam,session);
				}
				else{
//					uploadFilesUpdate(session, Long.parseLong(idDokumen));
				}
			}
			//IL end
			//String idhtaam = getParam("idhtaamid");
			//PARAM
			String id_bandarhta = String.valueOf(hParam.get("id_bandarhta")); 
//			logic_internal.setDataHTAbyIdHtaam(idhtaam,getParam("id_Permohonansimati"));
//			listHTAid = logic_internal.getDataHTAbyIdHtaam();
//			this.context.put("listHTAid", listHTAid);
			
			senaraiHTA = permohonanHarta.getDataHTA(mati);

			//IL start
			// MAKLUMAT PELAN
			// beanMaklumatPelan = new Vector();
			// logic_internal.setMaklumatPelanUp(idDokumen);
			// beanMaklumatPelanUp =
			// logic_internal.getBeanMaklumatPelanUp();
			// Hashtable hashPelan = new Hashtable();
			// hashPelan.put("namaPelanUp",getParam("txtNamaPelanUp") ==
			// null ? "": getParam("txtNamaPelanUp"));
			// beanMaklumatPelanUp.addElement(hashPelan);
//			this.context.put("BeanMaklumatPelanUp", beanMaklumatPelanUp);
			//IL end
			show_kemaskini_htaam = YES;
			show_hapus_htaam = YES;
			show_kembali_htaam = YES;
			show_button = YES;
			show_htaa_update_table = YES;
			tambahharta = YES;
			readmodenegeri = READMODED;
			readmodedaerah = READMODED;
			readmodemukim = READMODED;
			
			context.put("readmode", "disabled");
//			String id = getParam("idPermohonan");
			if (bolehsimpan.equals("yes")) {
				logic_A.updateDataNilai(idPermohonan, mati, (String) session.getAttribute("_ekptg_user_id"));
			}
		}
		//IL
		idPermohonanSimati = mati;
		if (mati.length() == 0) {
			idPermohonanSimati = matiHeader;
		}
		//IL
		context.put("selectedTabatas", 1);
		context.put("selectedTabtengah", 0);
		context.put("selectedTabbawah", 0);
		context.put("selectedTabtepi", 0);
//		this.context.put("id_Permohonansimati", mati);//IL
		context.put("DATEUTIL", new DateUtil());
//		String id = getParam("idPermohonan");
		Vector list = logic_A.setData(idPermohonan, (String) session.getAttribute("_ekptg_user_id"));
		context.put("View", list);
		//IL start
//		String selectedHartaTakAlih = getParam("selectedHartaTakAlih");
		// PARAM
		String load = String.valueOf(hParam.get("load"));
		FrmModelNilaianHartaTakAlih modelNilaianHartaTakAlih = new FrmModelNilaianHartaTakAlih();//IL
		if(load.isEmpty()){
			if(list.size() > 0){
				Hashtable hash = (Hashtable) list.get(0);
				String idSimati = (String) hash.get("idSimati");
				Vector vecDataHTA = modelNilaianHartaTakAlih.setListDataHTA(idSimati);
				if(vecDataHTA.size() > 0){
					for(int i=0; i < vecDataHTA.size(); i++){
						Hashtable hashHTA = (Hashtable) vecDataHTA.get(i);
						String idHTA = (String) hashHTA.get("idhta");
						if(i == 0){
								selectedHartaTakAlih = idHTA;
						} else {
							selectedHartaTakAlih = selectedHartaTakAlih +","+idHTA;
						}
					}
				}				
				Vector vecDataHTAX = modelNilaianHartaTakAlih.setListDataHTAX(idSimati);
				if(vecDataHTAX.size() > 0){
					for(int i=0; i < vecDataHTAX.size(); i++){
						Hashtable hashHTAX = (Hashtable) vecDataHTAX.get(i);
						String idHTA = (String) hashHTAX.get("idhta");
						if(i == 0){
							if(!selectedHartaTakAlih.isEmpty()){
								selectedHartaTakAlih = selectedHartaTakAlih +","+idHTA;
							} else {
								selectedHartaTakAlih = idHTA;
							}								
						} else {
							selectedHartaTakAlih = selectedHartaTakAlih +","+idHTA;
						}
					}
				}
			}
		}
		//MULA 25/08/2017
		context.put("add_new_harta",add_new_harta);
		context.put("buttonhtaam",buttonhtaam);
		context.put("tambahharta",tambahharta);
		context.put("kembaliharta",kembaliharta);
		context.put("negeri",negeri);
		context.put("daerah",daerah);
		context.put("mukim",mukim);
		context.put("readmodenegeri",readmodenegeri);
		context.put("readmodedaerah",readmodedaerah);
		context.put("readmodemukim",readmodemukim);
		context.put("show_simpan_add_htaam",show_simpan_add_htaam);
		context.put("show_batal_add_htaam",show_batal_add_htaam);
		context.put("show_kemaskini_htaam",show_kemaskini_htaam);
		context.put("show_button",show_button);
		context.put("show_htaa_add_table",show_htaa_add_table);
		context.put("show_save_update_htaam",show_save_update_htaam);
		context.put("show_batal_update_htaam",show_batal_update_htaam);
		context.put("show_hapus_htaam",show_hapus_htaam);
		context.put("show_kembali_htaam",show_kembali_htaam);
		context.put("show_htaa_update_table",show_htaa_update_table);
		
		//
		context.put("id_Permohonansimati", idPermohonanSimati);//IL
		context.put("idhtaam", idhtaam);
		context.put("idDokumen", idDokumen);
		context.put("idPelan", idPelan);//IL
		context.put("idPermohonan", idPermohonan);//IL

		context.put("selectedHartaTakAlih", selectedHartaTakAlih);			
		
		//SENARAI
		context.put("listHTA", senaraiHTA);
		//context.put("listDaerahbyNegeri",listDaerahbyNegeri);
		context.put("listDaerahbyNegeri",listnegeribydaerah);
		context.put("listMukimbyDaerah",listMukimbyDaerah);
		context.put("listnegeri",listnegeri);
		context.put("listHTAid",listHTAid);
		//2017
		context.put("hashHTAid",hashHTAid);

		//TAMAT 25/08/2017
		//this.context.put("selectedHartaTakAlih", selectedHartaTakAlih);			
		//IL end 
		mainheader = new FrmHeaderPpk();
		headerppk_baru(session, idPermohonan, "Y", "", "T",context);
		logic_A.setDataFail(idPermohonan);
		Vector listFail = logic_A.getDataFail();
		context.put("ViewFail", listFail);
		//String mati = getParam("id_Permohonansimati");comment by aishahlatip
		logic_A.updateDataNilai(idPermohonan, mati, (String) session.getAttribute("_ekptg_user_id"));
	
	}
	

	private void headerppk_baru(HttpSession session, String id_permohonan,
			String flag_permohonan, String id_fail, String flag_fail
			,org.apache.velocity.VelocityContext context)
			throws Exception {
		// hashtable maklumat header
		context.put("headerppk", mainheader.getHeaderData(session, id_permohonan, flag_permohonan, id_fail, flag_fail));
		Vector list_sub = null;
		list_sub = mainheader.carianFail(id_permohonan, flag_permohonan, id_fail, flag_fail);

		context.put("list_sub_header", list_sub);
		context.put("flag_jenis_vm", "vtemplate");
	
	}

	private void updateHtaam(Hashtable hParam,FrmPrmhnnSek8InternalData logic_internal) throws Exception {
		System.out.println("-------Read Here----");
		Vector v = new Vector();
		Hashtable h = new Hashtable();
		setValuesTanaHash(h,hParam);
			//h.put("alamat1", String.valueOf(hParam.get("txtAlamat1Htaam1")));
			//h.put("alamat2", String.valueOf(hParam.get("txtAlamat2Htaam")));
			//h.put("alamat3", String.valueOf(hParam.get("txtAlamat3Htaam")));
			//h.put("poskod", String.valueOf(hParam.get("txtAlamatPoskodHtaam")));
// ASAL
//		if (getParam("socKategoriTanahHtaamUp") != "") {
//			h.put("kategori", Integer.parseInt(getParam("socKategoriTanahHtaamUp")));
//		} else {
//			h.put("kategori", 0);
//		}
//ASAL
//		if (getParam("socJenisHakmilikHtaamUp") != "") {
//			h.put("jenishakmilik", Integer.parseInt(getParam("socJenisHakmilikHtaamUp")));
//		} else {
//			h.put("jenishakmilik", 0);
//		}		
			//h.put("bandar", "");
			//h.put("basimati", String.valueOf(hParam.get("txtBahagianSimati1Up")));
			//h.put("bbsimati", String.valueOf(hParam.get("txtBahagianSimati2Up")));
			//h.put("jenisluas", String.valueOf(hParam.get("socJenisLuasHtaamUpd")));
			//h.put("sekatan", txtSekatan);
			//h.put("syaratNyata", txtSyaratNyata);
		if (socNegeriHtaamUp != "") {
			h.put("negeri", Integer.parseInt(socNegeriHtaamUp));
		} else {
			h.put("negeri", 0);
		}		
		if (socDaerahHtaamUp != "") {
			h.put("daerah", Integer.parseInt(socDaerahHtaamUp));
		} else {
			h.put("daerah", 0);
		}
		//PARAM
		if (String.valueOf(hParam.get("socMukimHtaamUp")) != "") {
			h.put("mukim", Integer.parseInt(String.valueOf(hParam.get("socMukimHtaamUp"))));
		} else {
			h.put("mukim", 0);
		}		
		//PARAM
		if (String.valueOf(hParam.get("txtBahagianSimati1Up")) != "") {
			h.put("basimati", String.valueOf(hParam.get("txtBahagianSimati1Up")));
		} else {
			h.put("basimati", "0");
		}
		//PARAM
		if (String.valueOf(hParam.get("txtBahagianSimati2Up")) != "") {
			h.put("bbsimati", String.valueOf(hParam.get("txtBahagianSimati2Up")));
		} else {
			h.put("bbsimati", "0");
		}		
		if (String.valueOf(hParam.get("socJenisLuasHtaamUpd")) != "") {
			h.put("jenisluas", Integer.parseInt(String.valueOf(hParam.get("socJenisLuasHtaamUpd"))));
		} else {
			h.put("jenisluas", 0);
		}
		// ADD BY PEJE - TAMBAH FIELD SEKATAN & SYARAT NYATA
		h.put("sekatan", String.valueOf(hParam.get("txtSekatan")) == null ? "" : String.valueOf(hParam.get("txtSekatan")));
		h.put("syaratNyata", String.valueOf(hParam.get("txtSyaratNyata")) == null ? "" : String.valueOf(hParam.get("txtSyaratNyata")));
		h.put("id_Permohonansimati", idPermohonanSimati);
		
		// ADD BY SALNIZAM - TAMBAH FIELD ALAMAT
		//PARAM
		h.put("alamat_hta1", String.valueOf(hParam.get("txtAlamat1Htaam1")));
		h.put("alamat_hta2", String.valueOf(hParam.get("txtAlamat2Htaam")));
		h.put("alamat_hta3", String.valueOf(hParam.get("txtAlamat3Htaam")));
		h.put("poskod", String.valueOf(hParam.get("txtAlamatPoskodHtaam")));
		if (String.valueOf(hParam.get("txtBandarHartaHtaamX2")) != "") {
			h.put("id_bandarhta", Integer.parseInt(String.valueOf(hParam.get("txtBandarHartaHtaamX2"))));
		} else {
			h.put("id_bandarhta", 0);
		}
		h.put("id_Kemaskini", userID);
		h.put("tarikh_Kemaskini", currentDate);
		// fungsi baru untuk penambahbaikkan...boleh kemaskini jenis HTATH
		String radioHtaamViewX_update = String.valueOf(hParam.get("radioHtaamViewX_update"));
		String no_flag = "1";
		if (radioHtaamViewX_update.equals("1")) {
			no_flag = "1";
		}
		if (radioHtaamViewX_update.equals("2")) {
			no_flag = "2";
		}
		if (radioHtaamViewX_update.equals("3")) {
			no_flag = "3";
		}
		h.put("flag", no_flag);

		String flag_tukar_jenis_hta = String.valueOf(hParam.get("flag_tukar_jenis_hta"));
		if (flag_tukar_jenis_hta.equals("ADA")) {
			h.put("jenis_Hta", "Y");
		} else if (flag_tukar_jenis_hta.equals("TIADA")) {
			h.put("jenis_Hta", "T");
			h.put("flag", no_flag);
		} else {
			h.put("jenis_Hta", "Y");
			h.put("flag", "");
		}
		//h.put("id_bandarhta", getParam("txtBandarHartaHtaamX2"));
		//h.put("noHakmilik", getParam("txtAlamat2Htaam"));
	//	h.put("noHakmilik", getParam("txtAlamat3Htaam"));
		//h.put("noHakmilik", getParam("txtAlamatBandarHtaam"));
		//h.put("noHakmilik", getParam("txtAlamatPoskodHtaam"));
	//	h.put("noHakmilik", getParam("socNegeriHtaamUp2"));		
		//h.put("nilai_Hta_mati", getParam("txtAlamat1Htaam1"));

		logic_internal.updateHtaam(h);
		
	}
	
	private void updateHtaamX(Hashtable hParam,FrmPrmhnnSek8InternalData logic_internal) throws Exception {
		System.out.println("-------Read Here----");
		Vector v = new Vector();
		Hashtable h = new Hashtable();
		setValuesTanaHash(h,hParam);
			//h.put("noHakmilik", String.valueOf(hParam.get("txtNoHakmilikHtaamUp")));
			//h.put("alamat1", String.valueOf(hParam.get("txtAlamat1Htaam1")));
			//h.put("alamat2", String.valueOf(hParam.get("txtAlamat2Htaam")));
			//h.put("alamat3", String.valueOf(hParam.get("txtAlamat3Htaam")));
			//h.put("poskod", String.valueOf(hParam.get("txtAlamatPoskodHtaam")));
		//h.put("idSimati", idSimati);
			//h.put("idhta", String.valueOf(hParam.get("id_htaam")));
			//h.put("nopt", String.valueOf(hParam.get("txtNoPTHtaamUp")));
			//h.put("nilai_Hta_memohon", String.valueOf(hParam.get("txtNilaiTarikhMohonHt")));
			//h.put("nilai_Hta_mati", String.valueOf(hParam.get("txtNilaiTarikhMatiHtaamUpd")));
			//h.put("kategori", String.valueOf(hParam.get("socKategoriTanahHtaamUp")));
			//h.put("jenishakmilik", String.valueOf(hParam.get("socJenisHakmilikHtaamUp")));
			//h.put("pemilikan", String.valueOf(hParam.get("socStatusPemilikanHtaamUpd")));
			//h.put("daerah", "");
			//h.put("mukim", "");
			//h.put("bandar", "");
			//h.put("luashmp", String.valueOf(hParam.get("txtLuasHMpHtaamUpd")));
			//h.put("luasasal", String.valueOf(hParam.get("txtLuasAsalHtaamUpd")));
			//h.put("nopajakan", String.valueOf(hParam.get("txtNoPajakanUp")));
			//h.put("jenistanah", String.valueOf(hParam.get("socJenisTanahHtaamUpd")));
			//h.put("basimati", String.valueOf(hParam.get("txtBahagianSimati1Up")));
			//h.put("bbsimati", String.valueOf(hParam.get("txtBahagianSimati2Up")));
			//h.put("tanggungan", String.valueOf(hParam.get("txtTanggunganHtaamUp")));
			//h.put("jenisluas", String.valueOf(hParam.get("socJenisLuasHtaamUpd")));
			//h.put("catatan", String.valueOf(hParam.get("txtCatatanHt")));
			//h.put("noperserahan", String.valueOf(hParam.get("txtNoPersHtaamUp")));
		//h.put("FLAG_DAFTAR", FLAG_DAFTAR);
			//h.put("sekatan", txtSekatan);
			//h.put("syaratNyata", txtSyaratNyata);
		//PARAM
		h.put("idhta", idhtaamid);
		h.put("nopt", String.valueOf(hParam.get("txtNoPTHtaamX")));
		h.put("nilai_Hta_memohon", String.valueOf(hParam.get("txtNilaiTarikhMohonHtaaX")));
		h.put("nilai_Hta_mati", String.valueOf(hParam.get("txtNilaiTarikhMatiHtaamX")));
		if (String.valueOf(hParam.get("socKategoriTanahHtaamX")) != "") {
			h.put("kategori", Integer.parseInt(String.valueOf(hParam.get("socKategoriTanahHtaamX"))));
		} else {
			h.put("kategori", 0);
		}
		if (String.valueOf(hParam.get("socJenisHakmilikHtaamX")) != "") {
			h.put("jenishakmilik", Integer.parseInt(String.valueOf(hParam.get("socJenisHakmilikHtaamX"))));
		} else {
			h.put("jenishakmilik", 0);
		}		
		h.put("pemilikan", String.valueOf(hParam.get("socStatusPemilikanHtaamX")));	
		if (String.valueOf(hParam.get("socNegeriHtaamX")) != "") {
			h.put("negeri", Integer.parseInt(String.valueOf(hParam.get("socNegeriHtaamX"))));
		} else {
			h.put("negeri", 0);
		}
		if (String.valueOf(hParam.get("socDaerahHtaamX")) != "") {
			h.put("daerah", Integer.parseInt(String.valueOf(hParam.get("socDaerahHtaamX"))));
		} else {
			h.put("daerah", 0);
		}
		if (String.valueOf(hParam.get("socMukimHtaamX")) != "") {
			h.put("mukim", Integer.parseInt(String.valueOf(hParam.get("socMukimHtaamX"))));
		} else {
			h.put("mukim", 0);
		}
		h.put("luashmp", String.valueOf(hParam.get("txtLuasHMpHtaamX")));
		h.put("luasasal", String.valueOf(hParam.get("txtLuasAsalHtaamX")));
		h.put("nopajakan", String.valueOf(hParam.get("txtNoPajakanX")));	
		h.put("jenistanah", String.valueOf(hParam.get("socJenisTanahHtaamX")));
		if (String.valueOf(hParam.get("txtBahagianSimati1X"))!= "") {
			h.put("basimati", String.valueOf(hParam.get("txtBahagianSimati1X")));
		} else {
			h.put("basimati", "0");
		}
		if (String.valueOf(hParam.get("txtBahagianSimati2X")) != "") {
			h.put("bbsimati", String.valueOf(hParam.get("txtBahagianSimati2X")));
		} else {
			h.put("bbsimati", "0");
		}
		h.put("tanggungan", String.valueOf(hParam.get("txtTanggunganHtaamX")));
		if (String.valueOf(hParam.get("socJenisLuasHtaamX")) != "") {
			h.put("jenisluas", Integer.parseInt(String.valueOf(hParam.get("socJenisLuasHtaamX"))));
		} else {
			h.put("jenisluas", 0);
		}
		h.put("catatan", String.valueOf(hParam.get("txtCatatanHtaamX")));
		h.put("noperserahan", String.valueOf(hParam.get("txtNoPersHtaamX")));
		h.put("FLAG_DAFTAR", FLAG_DAFTAR);		
		
		h.put("id_Permohonansimati", idPermohonanSimati);
		
		h.put("namapemaju", String.valueOf(hParam.get("txtNamaPemajuHtaamX")));
		h.put("alamatpemaju1", String.valueOf(hParam.get("txtAlamatPemaju1HtaamX")));
		h.put("alamatpemaju2", String.valueOf(hParam.get("txtAlamatPemaju2HtaamX")));
		h.put("alamatpemaju3", String.valueOf(hParam.get("txtAlamatPemaju3HtaamX")));
		h.put("poskodpemaju", String.valueOf(hParam.get("txtPoskodPemaju1HtaamX")));
		if (String.valueOf(hParam.get("socNegeriPemajuHtaamX")).equals("")) {
			h.put("negeripemaju", 0);
		} else {
			h.put("negeripemaju", Integer.parseInt(String.valueOf(hParam.get("socNegeriPemajuHtaamX"))));
		}
		if (String.valueOf(hParam.get("txtBandarPemaju1HtaamX")).equals("")) {
			h.put("bandarpemaju", 0);
		} else {
			h.put("bandarpemaju", Integer.parseInt(String.valueOf(hParam.get("txtBandarPemaju1HtaamX"))));
		}
	
		h.put("alamathta1", String.valueOf(hParam.get("txtAlamatHarta1HtaamX")));
		h.put("alamathta2", String.valueOf(hParam.get("txtAlamatHarta2HtaamX")));
		h.put("alamathta3", String.valueOf(hParam.get("txtAlamatHarta3HtaamX")));
		h.put("poskodhta", String.valueOf(hParam.get("txtPoskodHartaHtaamX")));
		if (String.valueOf(hParam.get("txtBandarHartaHtaamX")).equals("")) {
			h.put("bandarhta", 0);
		} else {
			h.put("bandarhta", Integer.parseInt(String.valueOf(hParam.get("txtBandarHartaHtaamX"))));
		}
		h.put("noperjanjian", String.valueOf(hParam.get("txtNoPerjanjianHtaamX")));
		h.put("tarikhperjanjian", String.valueOf(hParam.get("txtTarikhPerjanjianHtaamX")));
		h.put("namarancangan", String.valueOf(hParam.get("txtNamaRancanganHtaamX")));
		h.put("noroh", String.valueOf(hParam.get("txtNoRohHtaamX")));

		h.put("nolot", String.valueOf(hParam.get("txtLotIdHtaamX")));
		h.put("nocagaran", String.valueOf(hParam.get("txtNoCagaranX")));
		h.put("jeniskepentingan", String.valueOf(hParam.get("txtJenisKepentinganX")));
		h.put("id_Kemaskini", userID);
		h.put("tarikh_Kemaskini", currentDate);

		// /fungsi baru untuk penambahbaikkan...boleh kemaskini jenis HTATH
		String radioHtaamViewX_update = String.valueOf(hParam.get("radioHtaamViewX_update"));
		String no_flag = "1";
		if (radioHtaamViewX_update.equals("1")) {
			no_flag = "1";
		}
		if (radioHtaamViewX_update.equals("2")) {
			no_flag = "2";
		}
		if (radioHtaamViewX_update.equals("3")) {
			no_flag = "3";
		}
		h.put("flag", no_flag);

		String flag_tukar_jenis_hta = String.valueOf(hParam.get("flag_tukar_jenis_hta"));
		if (flag_tukar_jenis_hta.equals("ADA")) {
			h.put("jenis_Hta", "Y");

		} else if (flag_tukar_jenis_hta.equals("TIADA")) {
			h.put("jenis_Hta", "T");
			h.put("flag", no_flag);
		} else {
			h.put("jenis_Hta", "T");
		}
		logic_internal.updateHtaamX(h);

	}
	private void addHtaam(HttpSession session
		,Hashtable hParam
		,FrmPrmhnnSek8InternalData logic_internal
		) throws Exception {
		System.out.println("-------Read Here8----");
		Hashtable h = new Hashtable();

		h.put("FLAG_DAFTAR", hParam.get("FLAG_DAFTAR"));
		h.put("noHakmilik", hParam.get("txtNoHakmilikHtaam"));
		// ADD BY SALNIZAM - TAMBAH FIELD ALAMAT
		h.put("alamat_hta1", hParam.get("txtAlamat1Htaam1"));
		h.put("alamat_hta2", hParam.get("txtAlamat2Htaam"));
		h.put("alamat_hta3", hParam.get("txtAlamat3Htaam"));
		h.put("poskod", hParam.get("txtAlamatPoskodHtaam"));
		//if (getParam("txtBandarHartaHtaamX2") != "") {
			h.put("id_bandarhta", hParam.get("txtBandarHartaHtaamX2"));
		//} else {
		//	h.put("id_bandarhta", "99999");
		//}
		
		//h.put("bandar_hta", getParam("txtBandarHartaHtaamX2"));
		h.put("idSimati", hParam.get("idSimati"));

		//IL start
		//h.put("id_Permohonansimati", getParam("id_Permohonansimati"));

		String mati = String.valueOf(hParam.get("id_permohonansimati_atheader"));
		if (mati.length() == 0) {
			mati =  String.valueOf(hParam.get("id_Permohonansimati"));
		}

		h.put("id_Permohonansimati", mati);
		//IL end

		// int mati = Integer.parseInt(getParam("id_Permohonansimati"));
		h.put("nopt", hParam.get("txtNoPTHtaam"));
		
		/*
		 * if (getParam("txtNilaiTarikhMohonHtaa") != "") {
		 * h.put("nilai_Hta_memohon", Double
		 * .parseDouble(getParam("txtNilaiTarikhMohonHtaa"))); } else {
		 * h.put("nilai_Hta_memohon", 0.0); }
		 */

		h.put("nilai_Hta_memohon", hParam.get("txtNilaiTarikhMohonHtaa"));
		/*
		 * if (getParam("txtNilaiTarikhMatiHtaam") != "") {
		 * h.put("nilai_Hta_mati", Double
		 * .parseDouble(getParam("txtNilaiTarikhMatiHtaam"))); } else {
		 * h.put("nilai_Hta_mati", 0.0); }
		 */

		h.put("nilai_Hta_mati", hParam.get("txtNilaiTarikhMatiHtaam"));

		if (hParam.get("socKategoriTanahHtaam") != "") {
			h.put("kategori", Integer
					.parseInt(String.valueOf(hParam.get("socKategoriTanahHtaam"))));
		} else {
			h.put("kategori", 0);
		}

		if (hParam.get("socJenisHakmilikHtaam") != "") {
			h.put("jenishakmilik", Integer
					.parseInt(String.valueOf(hParam.get("socJenisHakmilikHtaam"))));
		} else {
			h.put("jenishakmilik", 0);
		}

		h.put("pemilikan", hParam.get("socStatusPemilikanHtaam"));

		if (hParam.get("socNegeriHtaam") != "") {
			h.put("negeri", Integer.parseInt(String.valueOf(hParam.get("socNegeriHtaam"))));
		} else {
			h.put("negeri", 0);
		}
	
		if (hParam.get("socDaerahHtaam") != "") {
			h.put("daerah", Integer.parseInt(String.valueOf(hParam.get("socDaerahHtaam"))));
		} else {
			h.put("daerah", 0);
		}

		if (hParam.get("socMukimHtaam") != "") {
			h.put("mukim", Integer.parseInt(String.valueOf(hParam.get("socMukimHtaam"))));
		} else {
			h.put("mukim", 0);
		}

		h.put("luashmp", hParam.get("txtLuasHMpHtaam"));
		h.put("luasasal", hParam.get("txtLuasAsalHtaam"));
		h.put("nopajakan", hParam.get("txtNoPajakan"));
		h.put("jenistanah", hParam.get("socJenisTanahHtaam"));

		if (hParam.get("txtBahagianSimati1") != "") {
			h.put("basimati", hParam.get("txtBahagianSimati1"));
		} else {
			h.put("basimati", "0");
		}

		if (hParam.get("txtBahagianSimati2") != "") {
			h.put("bbsimati", hParam.get("txtBahagianSimati2"));
		} else {
			h.put("bbsimati", "0");
		}

		if (hParam.get("socJenisLuasHtaam") != "") {
			h.put("jenisluas", Integer.parseInt(String.valueOf(hParam.get("socJenisLuasHtaam"))));
		} else {
			h.put("jenisluas", 0);
		}

		h.put("tanggungan", hParam.get("txtTanggunganHtaam"));

		h.put("catatan", hParam.get("txtCatatanHtaam"));
		h.put("noperserahan", hParam.get("txtNoPersHtaam"));

		h.put("id_Masuk", session.getAttribute("_ekptg_user_id"));
		h.put("tarikh_Masuk", currentDate);

		// ADD BY PEJE - TAMBAH FIELD SEKATAN & SYARAT NYATA
		h.put("sekatan", hParam.get("txtSekatan") == null ? ""
				: hParam.get("txtSekatan"));
		h.put("syaratNyata", hParam.get("txtSyaratNyata") == null ? ""
				: hParam.get("txtSyaratNyata"));

		//logic_internal.addHtaam(h);//IL comment
		String idHta = logic_internal.addHtaamUpload(h);//IL
		session.setAttribute("idHtaSession", idHta);//IL
		System.out.println("-------Read Here8j ends----");
		
	}
	private void setValuesTanah(org.apache.velocity.VelocityContext context){
		context.put("noHakmilik", txtNoHakmilikHtaam);
		context.put("idSimati",idSimati);
		context.put("nopt",txtNoPTHtaam);
		context.put("nilai_Hta_memohon",txtNilaiTarikhMohonHtaa);
		context.put("nilai_Hta_mati",txtNilaiTarikhMatiHtaam);
		context.put("kategori",socKategoriTanahHtaam);
		context.put("jenishakmilik",socJenisHakmilikHtaam);
		context.put("pemilikan",socStatusPemilikanHtaam);
		context.put("luashmp",txtLuasHMpHtaam);
		context.put("luasasal",txtLuasAsalHtaam);
		context.put("nopajakan",txtNoPajakan);
		context.put("jenistanah",socJenisTanahHtaam);
		context.put("basimati",txtBahagianSimati1);
		context.put("bbsimati",txtBahagianSimati2);
		context.put("tanggungan",txtTanggunganHtaam);
		context.put("jenisluas",socJenisLuasHtaam);
		context.put("catatan",txtCatatanHtaam);
		context.put("noperserahan",txtNoPersHtaam);
		context.put("FLAG_DAFTAR", FLAG_DAFTAR);
		context.put("txtSekatan", txtSekatan);
		context.put("txtSyaratNyata", txtSyaratNyata);
				
	}

	private void setValuesTanah(org.apache.velocity.VelocityContext context
		,Hashtable getLot){
		context.put("noHakmilik", getLot.get("NO_HAKMILIK"));
		context.put("idSimati", getLot.get("ID_SIMATI"));
		context.put("nilai_Hta_memohon", getLot.get("NILAI_HTA_TARIKHMOHON"));
		context.put("nilai_Hta_mati", getLot.get("NILAI_HTA_TARIKHMATI"));
		context.put("kategori", getLot.get("ID_KATEGORI"));
		context.put("jenishakmilik", getLot.get("ID_JENISHM"));
		context.put("pemilikan", getLot.get("STATUS_PEMILIKAN"));
		context.put("luashmp", getLot.get("LUAS_HMP"));
		context.put("luasasal", getLot.get("LUAS"));
		context.put("nopajakan", getLot.get("NO_PAJAKAN"));
		context.put("jenistanah", getLot.get("JENIS_TNH"));
		context.put("basimati", getLot.get("BA_SIMATI"));
		context.put("bbsimati", getLot.get("BB_SIMATI"));
		context.put("tanggungan", getLot.get("TANGGUNGAN"));
		context.put("jenisluas", getLot.get("ID_LUAS"));
		context.put("catatan", getLot.get("CATATAN"));
		context.put("noperserahan", getLot.get("NO_PERSERAHAN"));
		context.put("FLAG_DAFTAR", getLot.get("FLAG_DAFTAR"));
		
	}
		
	private void setValuesTanaHash(Hashtable h,Hashtable hParam){
//		txtNoHakmilikHtaamUp = String.valueOf(hParam.get("txtNoHakmilikHtaamUp"));
//		txtAlamat1Htaam1 = String.valueOf(hParam.get("txtAlamat1Htaam1"));
//		txtAlamat2Htaam = String.valueOf(hParam.get("txtAlamat2Htaam"));
//		txtAlamat3Htaam = String.valueOf(hParam.get("txtAlamat3Htaam"));
//		txtAlamatPoskodHtaam = String.valueOf(hParam.get("txtAlamatPoskodHtaam"));
//		id_htaam = String.valueOf(hParam.get("id_htaam"));
//		txtNoPTHtaamUp = String.valueOf(hParam.get("txtNoPTHtaamUp"));
//		txtAlamatPoskodHtaam = String.valueOf(hParam.get("txtAlamatPoskodHtaam"));
//		txtNilaiTarikhMohonHt = String.valueOf(hParam.get("txtNilaiTarikhMohonHt"));
//		txtNilaiTarikhMatiHtaamUpd = String.valueOf(hParam.get("txtNilaiTarikhMatiHtaamUpd"));
//		socKategoriTanahHtaamUp = String.valueOf(hParam.get("socKategoriTanahHtaamUp"));
//		socJenisHakmilikHtaamUp = String.valueOf(hParam.get("socJenisHakmilikHtaamUp"));
//		socStatusPemilikanHtaamUpd = String.valueOf(hParam.get("socStatusPemilikanHtaamUpd"));
//		txtLuasHMpHtaamUpd = String.valueOf(hParam.get("txtLuasHMpHtaamUpd"));
//		txtLuasAsalHtaamUpd = String.valueOf(hParam.get("txtLuasAsalHtaamUpd"));
//		txtNoPajakanUp = String.valueOf(hParam.get("txtNoPajakanUp"));
//		socJenisTanahHtaamUpd = String.valueOf(hParam.get("socJenisTanahHtaamUpd"));
//		txtBahagianSimati1Up = String.valueOf(hParam.get("txtBahagianSimati1Up"));
//		txtBahagianSimati2Up = String.valueOf(hParam.get("txtBahagianSimati2Up"));
//		txtTanggunganHtaamUp = String.valueOf(hParam.get("txtTanggunganHtaamUp"));
//		socJenisLuasHtaamUpd = String.valueOf(hParam.get("socJenisLuasHtaamUpd"));
//		txtCatatanHt = String.valueOf(hParam.get("txtCatatanHt"));
//		txtNoPersHtaamUp = String.valueOf(hParam.get("txtNoPersHtaamUp"));
		//PARAM
		h.put("noHakmilik", String.valueOf(hParam.get("txtNoHakmilikHtaamUp")));
		h.put("alamat1", String.valueOf(hParam.get("txtAlamat1Htaam1")));
		h.put("alamat2", String.valueOf(hParam.get("txtAlamat2Htaam")));
		h.put("alamat3", String.valueOf(hParam.get("txtAlamat3Htaam")));
		h.put("poskod", String.valueOf(hParam.get("txtAlamatPoskodHtaam")));
		h.put("idSimati", idSimati);
		h.put("idhta", String.valueOf(hParam.get("id_htaam")));
		h.put("nopt", String.valueOf(hParam.get("txtNoPTHtaamUp")));
		h.put("nilai_Hta_memohon", String.valueOf(hParam.get("txtNilaiTarikhMohonHt")));
		h.put("nilai_Hta_mati", String.valueOf(hParam.get("txtNilaiTarikhMatiHtaamUpd")));
		h.put("kategori", String.valueOf(hParam.get("socKategoriTanahHtaamUp")));
		h.put("jenishakmilik", String.valueOf(hParam.get("socJenisHakmilikHtaamUp")));
		h.put("pemilikan", String.valueOf(hParam.get("socStatusPemilikanHtaamUpd")));
		h.put("daerah", "");
		h.put("mukim", "");
		h.put("bandar", "");
		h.put("luashmp", String.valueOf(hParam.get("txtLuasHMpHtaamUpd")));
		h.put("luasasal", String.valueOf(hParam.get("txtLuasAsalHtaamUpd")));
		h.put("nopajakan", String.valueOf(hParam.get("txtNoPajakanUp")));
		h.put("jenistanah", String.valueOf(hParam.get("socJenisTanahHtaamUpd")));
		h.put("basimati", String.valueOf(hParam.get("txtBahagianSimati1Up")));
		h.put("bbsimati", String.valueOf(hParam.get("txtBahagianSimati2Up")));
		h.put("tanggungan", String.valueOf(hParam.get("txtTanggunganHtaamUp")));
		h.put("jenisluas", String.valueOf(hParam.get("socJenisLuasHtaamUpd")));
		h.put("catatan", String.valueOf(hParam.get("txtCatatanHt")));
		h.put("noperserahan", String.valueOf(hParam.get("txtNoPersHtaamUp")));
		h.put("FLAG_DAFTAR", FLAG_DAFTAR);
		// ADD BY PEJE TAMBAH FIELD SEKATAN & SYARAT NYATA
		h.put("sekatan", txtSekatan);
		h.put("syaratNyata", txtSyaratNyata);
				
	}
	private Hashtable setValuesTanaHashX(Hashtable hi){
		Hashtable h = new Hashtable();
		h.put("noHakmilik", hi.get("txtNoHakmilikHtaamUp"));
		h.put("alamat1", hi.get("txtAlamat1Htaam1"));
		h.put("alamat2", hi.get("txtAlamat2Htaam"));
		h.put("alamat3", hi.get("txtAlamat3Htaam"));
		h.put("poskod", hi.get("txtAlamatPoskodHtaam"));
		h.put("idSimati", hi.get("idSimati"));
		h.put("idhta", hi.get("id_htaam"));
		h.put("nopt", hi.get("txtNoPTHtaamUp"));
		h.put("nilai_Hta_memohon", hi.get("txtNilaiTarikhMohonHt"));
		h.put("nilai_Hta_mati", hi.get("txtNilaiTarikhMatiHtaamUpd"));
		//h.put("alamatHta1", getParam("txtAlamat1Htaam1"));				
		h.put("kategori", hi.get("socKategoriTanahHtaamUp"));
		h.put("jenishakmilik", hi.get("socJenisHakmilikHtaamUp"));
		h.put("pemilikan", hi.get("socStatusPemilikanHtaamUpd"));
		h.put("daerah", "");
		h.put("mukim", "");
		h.put("bandar", "");
		h.put("luashmp", hi.get("txtLuasHMpHtaamUpd"));
		h.put("luasasal", hi.get("txtLuasAsalHtaamUpd"));
		h.put("nopajakan", hi.get("txtNoPajakanUp"));
		h.put("jenistanah", hi.get("socJenisTanahHtaamUpd"));
		h.put("basimati", hi.get("txtBahagianSimati1Up"));
		h.put("bbsimati", hi.get("txtBahagianSimati2Up"));
		h.put("tanggungan", hi.get("txtTanggunganHtaamUp"));
		h.put("jenisluas", hi.get("socJenisLuasHtaamUpd"));
		h.put("catatan", hi.get("txtCatatanHt"));
		h.put("noperserahan", hi.get("txtNoPersHtaamUp"));
		h.put("FLAG_DAFTAR", hi.get("FLAG_DAFTAR"));
		// ADD BY PEJE TAMBAH FIELD SEKATAN & SYARAT NYATA
		h.put("sekatan", hi.get("txtSekatan"));
		h.put("syaratNyata", hi.get("txtSyaratNyata"));
		return h;
		
	}
	
	
}
