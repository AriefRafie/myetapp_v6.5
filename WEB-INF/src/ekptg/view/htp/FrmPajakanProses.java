package ekptg.view.htp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.model.htp.FrmPajakanSemakanData;
import ekptg.model.htp.FrmSemakan;
import ekptg.model.htp.FrmSenaraiFailPajakanData;

/**
 *
 * @author Firzan.Fir
 */
public class FrmPajakanProses extends AjaxBasedModule {

    private static Logger log = Logger.getLogger(FrmPajakanProses.class);
    private Date now = new Date();
    private SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy");
    private static String disability = "disabled='disabled' class='disabled'";
    private static String classDis = "class='disabled'";
    private String submit;
    private String mode;
    private String vm;
    private String idFail;
    private String idPermohonan;
    private String idHakmilikUrusan;
    private Vector list;
    private String doChange;
    private String selectedTab;

    @Override
    public String doTemplate2() throws Exception {

        try {

            HttpSession session = this.request.getSession();
            this.submit = getParam("command");
            this.mode = getParam("mode");
            this.doChange = getParam("doChange");
            selectedTab = getParam("tabId");

            if (selectedTab.equalsIgnoreCase(null) || selectedTab.equalsIgnoreCase("")) {
                selectedTab = "0";
            }

            this.context.put("selectedTab", selectedTab);

            log.info("submit:" + submit);
            log.info("mode:" + mode);

            if(submit.equalsIgnoreCase("") || submit.equalsIgnoreCase(null) || submit.equalsIgnoreCase("carian")){

                vm = "app/htp/frmSenaraiFailPajakanAjax.jsp";

                list = new Vector();

                String key_cari = getParam("NamaFail");
                String keyNo_cari = getParam("NoFail");
                String Negeri = (getParam("socNegeri") == null ? "" == null : getParam("socNegeri").equals(""))? "0":getParam("socNegeri");;
                Long idNegeri = Long.parseLong(Negeri);
                ListFail(session, key_cari, keyNo_cari, idNegeri);
                list = FrmSenaraiFailPajakanData.getList();
                this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri", idNegeri, ""));
                this.context.put("Senaraifail", list);
                this.context.put("carian", getParam("NamaFail"));
                this.context.put("carianNoFail", getParam("NoFail"));
                this.context.put("Negeri", idNegeri);

            }else if (submit.equalsIgnoreCase("FailBaru")){

                vm = "app/htp/frmPajakanSemakanAjax.jsp";
                this.context.put("IdPermohonan", getParam("idPermohonan"));
                this.context.put("senaraiSemakan", FrmSemakan.getSenaraiSemakan("frmPajakanSemakan"));
                this.context.put("semakclass", new FrmSemakan());

                if (mode.equalsIgnoreCase("view")) {

                    idPermohonan = getParam("idPermohonan");
                    setFailBaru(session, idPermohonan);
                    ViewFailBaru(session, disability);

                } else if (mode.equalsIgnoreCase("kemaskini")) {

                    idPermohonan = getParam("idPermohonan");
                    setFailBaru(session, idPermohonan);
                    ViewFailBaru(session, disability);

                } else if (mode.equalsIgnoreCase("simpan")) {

                    idPermohonan = SimpanFailBaru(session);

                    String[] cbsemaks = this.request.getParameterValues("cbsemaks");
                    FrmSemakan frmSemak = new FrmSemakan();
                    frmSemak.semakanHapusByPermohonan(String.valueOf(idPermohonan));
                    if (cbsemaks != null) {
                        for (int i = 0; i < cbsemaks.length; i++) {
                            log.info("PembelianProcess:FailBaru::simpan::cbsemaks:::" + cbsemaks[i]);
                            FrmSemakan.semakanTambah(cbsemaks[i], String.valueOf(idPermohonan));
                        }
                    }

                    setFailBaru(session, idPermohonan);
                    ViewFailBaru(session, disability);
                    this.context.put("IdPermohonan", idPermohonan);

                } else if (mode.equalsIgnoreCase("baru")) {
                    
                    DataFailBaruBatal(session, disability);

                } else {
                    
                    DataFailBaru(session, "");
                }
                this.context.put("NoHakmilik", "");
                


            }else if(submit.equalsIgnoreCase("Hakmilik")){


            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return vm;

    }

    // Controller untuk Senarai Fail Pajakan
    public void ListFail(HttpSession session, String key_cari, String keyNo_cari, Long idNegeri) throws Exception {
        FrmSenaraiFailPajakanData.setList(key_cari, keyNo_cari, idNegeri);
    }

    public void setFailBaru(HttpSession session, String idPermohonan) throws Exception {
        FrmPajakanSemakanData.setSemak(idPermohonan);
    }

    private void ViewFailBaru(HttpSession session, String disability) throws Exception {

        Vector listVFB = new Vector();

        try {
            listVFB = FrmPajakanSemakanData.getSemak();
            this.context.put("Semak", listVFB);
            Hashtable h = (Hashtable) listVFB.get(0);
            this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri", Long.parseLong(h.get("idNegeri").toString()), disability));
            this.context.put("selectKementerian", HTML.SelectKementerian("socKementerian", Long.parseLong(h.get("idKementerian").toString()), "disabled"));
            this.context.put("selectAgensi", HTML.SelectAgensi("socAgensi", Long.parseLong(h.get("idAgensi").toString()), disability));
            this.context.put("selectUrusan", HTML.SelectUrusan("socSuburusan", Long.parseLong("3"), "disabled"));
            this.context.put("selectSuburusan", HTML.SelectSuburusanByIdUrusan("3", "socSuburusan", Long.parseLong(h.get("idSuburusan").toString()), "disabled"));
            this.context.put("selectJenisTanah", HTML.SelectJenisTanah("socJenisTanah", Long.parseLong(h.get("idJenistanah").toString()), disability));
            this.context.put("modeSoc", disability);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private String SimpanFailBaru(HttpSession session) throws Exception {
        String idPermohonan;
        if (getParam("idPermohonan") == "") {
            //fail baru
            Hashtable h = new Hashtable();
            h.put("idNegeri", Integer.parseInt(getParam("socNegeri")));
            h.put("idKementerian", Integer.parseInt(getParam("socKementerian")));
            h.put("idJenistanah", getParam("socJenisTanah"));
            h.put("idAgensi", Integer.parseInt(getParam("socAgensi")));
            h.put("Tajuk", getParam("txtTajuk"));
            h.put("NoFailKJP", getParam("txtNoFailKJP"));
            h.put("TarikhSurKJP", getParam("txdTarikhSurKJP"));
            h.put("NoFailLain", getParam("txtNoFailLain"));
            h.put("TarikhPermohonan", getParam("txdTarikhPermohonan"));
            log.info("FrmPajakanProcess::SimpanSemak:: h = " + h);
            idPermohonan = FrmPajakanSemakanData.simpan(h);

        } else {
            //kemaskini fail
            Hashtable h = new Hashtable();

            h.put("idFail", Integer.parseInt(getParam("idFail")));
            h.put("idPermohonan", Integer.parseInt(getParam("idPermohonan")));
            h.put("idJenistanah", getParam("socJenisTanah"));
            h.put("idAgensi", Integer.parseInt(getParam("socAgensi")));
            h.put("Tajuk", getParam("txtTajuk"));
            h.put("NoFailKJP", getParam("txtNoFailKJP"));
            h.put("TarikhSurKJP", getParam("txdTarikhSurKJP"));
            h.put("NoFailLain", getParam("txtNoFailLain"));
            h.put("TarikhPermohonan", getParam("txdTarikhPermohonan"));
            log.info("FrmPajakanProcess::SimpanSemak:: h = " + h);
            idPermohonan = FrmPajakanSemakanData.update(h);

        }

        return idPermohonan;
    }

    private void DataFailBaruBatal(HttpSession session, String disability) throws Exception {

        Vector temp = new Vector();
        try {
            this.context.put("Semak", temp);
            this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri", null, ""));
            this.context.put("selectKementerian", HTML.SelectKementerian("socKementerian", null, "", " onChange=\"doChangeKementerian();\" "));
            if ("doChangeKementerian".equals(submit)) {
                this.context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensi", "", null, disability));
            } else {
                this.context.put("selectAgensi", HTML.SelectAgensi("socAgensi"));
            }
            this.context.put("selectUrusan", HTML.SelectUrusan("socSuburusan", Long.parseLong("3"), "disabled"));
            this.context.put("selectSuburusan", HTML.SelectSuburusanByIdUrusan("3", "socSuburusan", Long.parseLong("7"), "disabled"));
            this.context.put("selectJenisTanah", HTML.SelectJenisTanah("socJenisTanah", null, disability));
            this.context.put("datenow", formatter.format(now));
            this.context.put("modeSoc", disability);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private void DataFailBaru(HttpSession session, String disability) throws Exception {
        
        try {

            if (this.doChange.equalsIgnoreCase("doChangeKementerian")) {
                //to be implemented
            } else {

                this.context.put("tajuk", "");
                this.context.put("idFail", "");
                this.context.put("noFail", "");
                this.context.put("tSurat", "");
                this.context.put("tPermohonan", "");
                this.context.put("noRujukanKJP", "");
                this.context.put("noRujukan", "");
                this.context.put("tAgihan", "");
                this.context.put("idAgensi", "");
                this.context.put("idSuburusan", "");

                this.context.put("Semak", "");
                this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri", 0L, ""));
                this.context.put("selectKementerian", HTML.SelectKementerian("socKementerian", 0L, "", " onChange=\"doChangeKementerian();\" "));
//                this.context.put("selectAgensi", HTML.SelectAgensi("socAgensi"));
                this.context.put("selectAgensi", HTML.SelectAgensiByKementerian("socAgensi", "", 0L, disability, ""));
                this.context.put("selectUrusan", HTML.SelectUrusan("socSuburusan", Long.parseLong("3"), disability));
                this.context.put("selectSuburusan", HTML.SelectSuburusanByIdUrusan("3", "socSuburusan", Long.parseLong("7"), disability));
                this.context.put("selectJenisTanah", HTML.SelectJenisTanah("socJenisTanah", 0L, disability));
                this.context.put("jenisFail", HTML.SelectTarafKeselamatan("socTarafKeselamatan"));
                this.context.put("datenow", formatter.format(now));
                this.context.put("modeSoc", disability);

            }






        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }

    //*** Fail Baru Controller
    private Vector tempDataBaru() {
        Vector temp = new Vector();
        Hashtable h;
        try {
            h = new Hashtable();
            h.put("idNegeri", getParam("socNegeri") == "" ? "0" : getParam("socNegeri"));
            h.put("idKementerian", getParam("socKementerian") == "" ? "0" : getParam("socKementerian"));
            h.put("idAgensi", getParam("socAgensi") == "" ? "0" : getParam("socAgensi"));
            h.put("idJenisTanah", getParam("socJenisTanah") == "" ? "0" : getParam("socJenisTanah"));
            h.put("tajuk", getParam("txtTajuk"));
            h.put("noRujukanKJP", getParam("txtNoFailKJP"));
            h.put("tSurat", getParam("txdTarikhSurKJP"));
            h.put("noRujukan", getParam("txtNoFailLain"));
            h.put("tPermohonan", getParam("txdTarikhPermohonan"));
            log.info("tempDataBaru::h=" + h);
            temp.addElement(h);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return temp;
    }


}
