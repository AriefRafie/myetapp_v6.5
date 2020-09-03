package ekptg.model.ppk;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.velocity.VTemplate;

import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import ekptg.model.RazTemplete;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.velocity.VTemplate;


public class FrmSemakanPPKModel  extends VTemplate{
	
	//extends razTemplete
	public RazTemplete RT = new RazTemplete();

	public Logger myLogger = Logger.getLogger(ekptg.model.ppk.FrmSemakanPPKModel.class);
	public HttpSession session;
	public String modul = "";
	
	//setting environtment untuk kegunaan controller, 
	//betujuan untuk kita mantain environment setting
	public void setFrmSemakanPPKModel(VelocityEngine engine, VelocityContext context, HttpServletRequest request, HttpServletResponse response,HttpSession session,String modul)
    {
        this.engine = engine;
        this.context = context;
        this.request = request;
        this.response = response;
        this.session = session;
        this.modul = modul;
        //kena call main environment dalam RazTempelete, untuk memastikan clases inherite each others
        RT.setEnvironmentRT(this.engine, this.context, this.request, this.response, this.session, this.modul);	
    }
	
	public String htmlSetupSkrin(String command, String currentTable, String currentSkrin, Db db) throws Exception
	{
		String html = "";
		List listSetupSkrin_1stlayer = listSetupSkrin(getParam("headerFailID_SEKSYEN"));
		if (listSetupSkrin_1stlayer.size() > 0 )
		{
				for (int j = 0; j < listSetupSkrin_1stlayer.size(); j++) {
					Map mapSetupSkrin = (Map) listSetupSkrin_1stlayer.get(j);
					String m_skrinName = (String) mapSetupSkrin.get("skrinName");
					String m_seqName = (String) mapSetupSkrin.get("seqName");
					String m_tableName = (String) mapSetupSkrin.get("tableName");
					String m_filterScript = (String) mapSetupSkrin.get("filterScript");
					String m_tajuk = (String) mapSetupSkrin.get("tajuk");
					String m_PK_FIELD = (String) mapSetupSkrin.get("PK_FIELD");
					//String m_ParentTable = (String) mapSetupSkrin.get("ParentTable");	
					String paramFieldFilter = (String) mapSetupSkrin.get("paramFieldFilter");
					String m_ParentSkrin = (String) mapSetupSkrin.get("ParentSkrin");	
					
					
					//if(currentTable.equals(m_ParentTable))
					if(currentSkrin.equals(m_ParentSkrin))
					{
						//1st layer, keluarkan skrin 1st layer
						if(command.equals("viewCrud") || command.equals("saveCrud"))
						{									
							myLogger.info("m_filterScript sebelum : "+m_filterScript+" paramFieldFilter >>>>>>>> "+paramFieldFilter);
							m_filterScript = m_filterScript.replace("{DUMMY_FK}", paramFieldFilter);	
							//myLogger.info("m_filterScript selepas : "+m_filterScript);
							html += RT.settingCRUDTapakAuto(m_skrinName,m_seqName,m_tableName,m_filterScript,m_tajuk,m_PK_FIELD,db);
						}
					}
				}					
		}
		return html;
	}
	
	//sususan mengikut structure table
	public List listSetupSkrin(String ID_SEKSYEN)
	{
		List listSkrin = Collections.synchronizedList(new ArrayList());	
		//String ID_SEKSYEN = "";
		myLogger.info(">>>>>>>>>>>>>>>>>> listSetupSkrin ID_SEKSYEN :"+ID_SEKSYEN);
		if(ID_SEKSYEN.equals("2"))
		{
			listSkrin.add(mapSetupSkrinAuto("fail","TBLPFDFAIL_SEQ","TBLPFDFAIL",  "&ID_FAIL IN ( {DUMMY_FK})" ,"TABLE : TBLPFDFAIL","ID_FAIL","",getParam("ID_FAIL")));			
			listSkrin.add(mapSetupSkrinAuto("statusfail","TBLRUJSUBURUSANSTATUSFAIL_SEQ","TBLRUJSUBURUSANSTATUSFAIL","&ID_FAIL IN ( {DUMMY_FK}) ","TABLE : TBLRUJSUBURUSANSTATUSFAIL","ID_SUBURUSANSTATUSFAIL","fail",getParam("ID_FAIL")));			
			listSkrin.add(mapSetupSkrinAuto("permohonan","TBLPPKPERMOHONAN_SEQ","TBLPPKPERMOHONAN","&ID_FAIL IN ( {DUMMY_FK})","TABLE : TBLPPKPERMOHONAN","ID_PERMOHONAN","fail",getParam("ID_FAIL")));				
			listSkrin.add(mapSetupSkrinAuto("pemohon","TBLPPKPEMOHON_SEQ","TBLPPKPEMOHON","&ID_PEMOHON IN (SELECT ID_PEMOHON FROM TBLPPKPERMOHONAN WHERE ID_PERMOHONAN IN ( {DUMMY_FK})) ","TABLE : TBLPPKPEMOHON","ID_PEMOHON","permohonan",getParam("ID_PERMOHONAN")));			
			listSkrin.add(mapSetupSkrinAuto("semakanhantar","TBLSEMAKANHANTAR_SEQ","TBLSEMAKANHANTAR","&ID_PERMOHONAN IN ( {DUMMY_FK}) ","TABLE : TBLSEMAKANHANTAR","ID_SEMAKANHANTAR","permohonan",getParam("ID_PERMOHONAN")));			
			listSkrin.add(mapSetupSkrinAuto("peguampemohon","TBLPPKPEGUAMPEMOHON_SEQ","TBLPPKPEGUAMPEMOHON","&ID_PEMOHON  IN ( {DUMMY_FK}) ","TABLE : TBLPPKPEGUAMPEMOHON","ID_PEGUAMPEMOHON","pemohon",getParam("ID_PEMOHON")));	
			listSkrin.add(mapSetupSkrinAuto("permohonansimati","TBLPPKPERMOHONANSIMATI_SEQ","TBLPPKPERMOHONANSIMATI","&ID_PERMOHONAN IN ( {DUMMY_FK}) ","TABLE : TBLPPKPERMOHONANSIMATI","ID_PERMOHONANSIMATI","permohonan",getParam("ID_PERMOHONAN")));			
			listSkrin.add(mapSetupSkrinAuto("simati","TBLPPKSIMATI_SEQ","TBLPPKSIMATI","&ID_SIMATI IN (SELECT ID_SIMATI FROM TBLPPKPERMOHONANSIMATI WHERE ID_PERMOHONANSIMATI IN ( {DUMMY_FK}) ) ","TABLE : TBLPPKSIMATI","ID_SIMATI","permohonansimati",getParam("ID_PERMOHONANSIMATI")));	
			listSkrin.add(mapSetupSkrinAuto("bayaran","TBLPPKBAYARAN_SEQ","TBLPPKBAYARAN","&ID_PERMOHONAN IN ( {DUMMY_FK}) ","TABLE : TBLPPKBAYARAN","ID_BAYARAN","permohonan",getParam("ID_PERMOHONAN")));	
			listSkrin.add(mapSetupSkrinAuto("semakanhantar","TBLSEMAKANHANTAR_SEQ","TBLSEMAKANHANTAR","&ID_PERMOHONAN IN ( {DUMMY_FK}) ","TABLE : TBLSEMAKANHANTAR","ID_SEMAKANHANTAR","permohonan",getParam("ID_PERMOHONAN")));	
			listSkrin.add(mapSetupSkrinAuto("ob","TBLPPKOB_SEQ","TBLPPKOB","&ID_PERMOHONANSIMATI IN ( {DUMMY_FK}) ","TABLE : TBLPPKOB","ID_OB","permohonansimati",getParam("ID_PERMOHONANSIMATI")));	
			listSkrin.add(mapSetupSkrinAuto("obpermohonan","TBLPPKOBPERMOHONAN_SEQ","TBLPPKOBPERMOHONAN","&ID_PERMOHONANSIMATI IN ( {DUMMY_FK}) ","TABLE : TBLPPKOBPERMOHONAN","ID_OBPERMOHONAN","permohonansimati",getParam("ID_PERMOHONANSIMATI")));	
			listSkrin.add(mapSetupSkrinAuto("hubunganob","TBLPPKHUBUNGANOB_SEQ","TBLPPKHUBUNGANOB","&ID_OB IN ( {DUMMY_FK}) ","TABLE : TBLPPKHUBUNGANOB","ID_HUBUNGANOB","ob",getParam("ID_OB")));	
			listSkrin.add(mapSetupSkrinAuto("hubunganobprmhnn","TBLPPKHUBUNGANOBPERMOHONAN_SEQ","TBLPPKHUBUNGANOBPERMOHONAN","&ID_PERMOHONANSIMATI IN ( {DUMMY_FK}) ","TABLE : TBLPPKHUBUNGANOBPERMOHONAN","ID_HUBUNGANOBPERMOHONAN","permohonansimati",getParam("ID_PERMOHONANSIMATI")));	
			listSkrin.add(mapSetupSkrinAuto("hta","TBLPPKHTA_SEQ","TBLPPKHTA","&ID_PERMOHONANSIMATI IN ( {DUMMY_FK}) ","TABLE : TBLPPKHTA","ID_HTA","permohonansimati",getParam("ID_PERMOHONANSIMATI")));	
			listSkrin.add(mapSetupSkrinAuto("htapermohonan","TBLPPKHTAPERMOHONAN_SEQ","TBLPPKHTAPERMOHONAN","&ID_PERMOHONANSIMATI IN ( {DUMMY_FK}) ","TABLE : TBLPPKHTAPERMOHONAN","ID_HTAPERMOHONAN","permohonansimati",getParam("ID_PERMOHONANSIMATI")));				
			listSkrin.add(mapSetupSkrinAuto("ha","TBLPPKHA_SEQ","TBLPPKHA","&ID_PERMOHONANSIMATI IN ( {DUMMY_FK}) ","TABLE : TBLPPKHA","ID_HA","permohonansimati",getParam("ID_PERMOHONANSIMATI")));	
			listSkrin.add(mapSetupSkrinAuto("hapermohonan","TBLPPKHAPERMOHONAN_SEQ","TBLPPKHAPERMOHONAN","&ID_PERMOHONANSIMATI IN ( {DUMMY_FK}) ","TABLE : TBLPPKHAPERMOHONAN","ID_HAPERMOHONAN","permohonansimati",getParam("ID_PERMOHONANSIMATI")));	
			listSkrin.add(mapSetupSkrinAuto("pilihanhta","TBLPPKPILIHANHTA_SEQ","TBLPPKPILIHANHTA","&ID_PERMOHONANSIMATI IN ( {DUMMY_FK}) ","TABLE : TBLPPKPILIHANHTA","ID_PILIHANHTA","permohonansimati",getParam("ID_PERMOHONANSIMATI")));
			listSkrin.add(mapSetupSkrinAuto("obpilihanhta","TBLPPKOBPILIHANHTA_SEQ","TBLPPKOBPILIHANHTA","&ID_PILIHANHTA IN ( {DUMMY_FK}) ","TABLE : TBLPPKOBPILIHANHTA","ID_OBPILIHANHTA","pilihanhta",getParam("ID_PILIHANHTA")));	
			listSkrin.add(mapSetupSkrinAuto("pilihanha","TBLPPKPILIHANHA_SEQ","TBLPPKPILIHANHA","&ID_PERMOHONANSIMATI IN ( {DUMMY_FK}) ","TABLE : TBLPPKPILIHANHA","ID_PILIHANHA","permohonansimati",getParam("ID_PERMOHONANSIMATI")));	
			listSkrin.add(mapSetupSkrinAuto("obpilihanha","TBLPPKOBPILIHANHA_SEQ","TBLPPKOBPILIHANHA","&ID_PILIHANHA IN ( {DUMMY_FK}) ","TABLE : TBLPPKOBPILIHANHA","ID_OBPILIHANHA","pilihanha",getParam("ID_PILIHANHA")));			
			listSkrin.add(mapSetupSkrinAuto("keputusanpermohonan","TBLPPKKEPUTUSANPERMOHONAN_SEQ","TBLPPKKEPUTUSANPERMOHONAN","&ID_PERMOHONAN IN ( {DUMMY_FK}) ","TABLE : TBLPPKKEPUTUSANPERMOHONAN","ID_KEPUTUSANPERMOHONAN","permohonan",getParam("ID_PERMOHONAN")));			
			listSkrin.add(mapSetupSkrinAuto("perbicaraan","TBLPPKPERBICARAAN_SEQ","TBLPPKPERBICARAAN","&ID_KEPUTUSANPERMOHONAN IN ( {DUMMY_FK}) ","TABLE : TBLPPKPERBICARAAN","ID_PERBICARAAN","keputusanpermohonan",getParam("ID_KEPUTUSANPERMOHONAN")));	
			listSkrin.add(mapSetupSkrinAuto("notabicara","TBLPPKNOTABICARA_SEQ","TBLPPKNOTABICARA","&ID_PERBICARAAN IN ( {DUMMY_FK}) ","TABLE : TBLPPKNOTABICARA","ID_NOTABICARA","perbicaraan",getParam("ID_PERBICARAAN")));	
			
			//recheck balik.. 
			listSkrin.add(mapSetupSkrinAuto("notisperbicaraan","TBLPPKNOTISPERBICARAAN_SEQ","TBLPPKNOTISPERBICARAAN","&ID_NOTISOBMST IN ( {DUMMY_FK}) ","TABLE : TBLPPKNOTISPERBICARAAN","ID_NOTISPERBICARAAN","notisobmst",getParam("ID_NOTISOBMST")));	
			//listSkrin.add(mapSetupSkrinAuto("notisperbicaraan","TBLPPKNOTISPERBICARAAN_SEQ","TBLPPKNOTISPERBICARAAN","&ID_PERBICARAAN IN ( {DUMMY_FK}) ","TABLE : TBLPPKNOTISPERBICARAAN","ID_NOTISPERBICARAAN","perbicaraan",getParam("ID_PERBICARAAN")));	
			listSkrin.add(mapSetupSkrinAuto("notisobmst","TBLPPKNOTISOBMST_SEQ","TBLPPKNOTISOBMST","&ID_NOTISOBMST IN (SELECT ID_NOTISOBMST FROM TBLPPKNOTISPERBICARAAN WHERE ID_PERBICARAAN IN (SELECT ID_PERBICARAAN FROM TBLPPKPERBICARAAN WHERE ID_PERBICARAAN IN ( {DUMMY_FK}))) ","TABLE : TBLPPKNOTISOBMST","ID_NOTISOBMST","perbicaraan",getParam("ID_PERBICARAAN")));	
			
			
			listSkrin.add(mapSetupSkrinAuto("notisobdtl","TBLPPKNOTISOBDTL_SEQ","TBLPPKNOTISOBDTL","&ID_NOTISOBMST IN ( {DUMMY_FK}) ","TABLE : TBLPPKNOTISOBDTL","ID_NOTISOBDTL","notisobmst",getParam("ID_NOTISOBMST")));	
			
			/*
			listSkrin.add(mapSetupSkrinAuto("notisperbicaraantemp","TBLPPKNOTISPERBICARAAN_SEQ","TBLPPKNOTISPERBICARAAN","&ID_NOTISOBMST IN ( {DUMMY_FK}) ","TABLE : TBLPPKNOTISPERBICARAAN","ID_NOTISPERBICARAAN","notisobmsttemp",getParam("ID_NOTISOBMST")));	
			
			//listSkrin.add(mapSetupSkrinAuto("notisperbicaraantemp","TBLPPKNOTISPERBICARAAN_SEQ","TBLPPKNOTISPERBICARAAN_TEMP","&ID_PERBICARAAN IN ( {DUMMY_FK}) ","TABLE : TBLPPKNOTISPERBICARAAN_TEMP","ID_NOTISPERBICARAAN","perbicaraan",getParam("ID_PERBICARAAN")));	
			listSkrin.add(mapSetupSkrinAuto("notisobmsttemp","TBLPPKNOTISOBMST_SEQ","TBLPPKNOTISOBMST_TEMP","&ID_NOTISOBMST IN (SELECT ID_NOTISOBMST FROM TBLPPKNOTISPERBICARAAN_TEMP WHERE ID_PERBICARAAN IN (SELECT ID_PERBICARAAN FROM TBLPPKPERBICARAAN WHERE ID_PERBICARAAN IN ( {DUMMY_FK}))) ","TABLE : TBLPPKNOTISOBMST_TEMP","ID_NOTISOBMST","perbicaraan",getParam("ID_PERBICARAAN")));	
			listSkrin.add(mapSetupSkrinAuto("notisobdtltemp","TBLPPKNOTISOBDTL_SEQ","TBLPPKNOTISOBDTL_TEMP","&ID_NOTISOBMST IN ( {DUMMY_FK}) ","TABLE : TBLPPKNOTISOBDTL_TEMP","ID_NOTISOBDTL","notisobmsttemp",getParam("ID_NOTISOBMST")));	
			*/
			
			listSkrin.add(mapSetupSkrinAuto("perintah","TBLPPKPERINTAH_SEQ","TBLPPKPERINTAH","&ID_PERBICARAAN IN ( {DUMMY_FK}) ","TABLE : TBLPPKPERINTAH","ID_PERINTAH","perbicaraan",getParam("ID_PERBICARAAN")));	
			listSkrin.add(mapSetupSkrinAuto("penjaga","TBLPPKPENJAGA_SEQ","TBLPPKPENJAGA","&ID_OBMINOR IN ( {DUMMY_FK}) ","TABLE : TBLPPKPENJAGA","ID_PENJAGA","ob",getParam("ID_OB")));
			listSkrin.add(mapSetupSkrinAuto("bikehadiran","TBLPPKBIKEHADIRAN_SEQ","TBLPPKBIKEHADIRAN","&ID_PERBICARAAN IN ( {DUMMY_FK}) ","TABLE : TBLPPKBIKEHADIRAN","ID_BIKEHADIRAN","perbicaraan",getParam("ID_PERBICARAAN")));	
			listSkrin.add(mapSetupSkrinAuto("bisejarahmain","TBLPPKSEJARAHBIMAIN_SEQ","TBLPPKSEJARAHBIMAIN","&ID_PERBICARAAN IN ( {DUMMY_FK}) ","TABLE : TBLPPKSEJARAHBIMAIN","ID_SEJARAHBIMAIN","perbicaraan",getParam("ID_PERBICARAAN")));			
			listSkrin.add(mapSetupSkrinAuto("bisejarahsub","TBLPPKSEJARAHBISUB_SEQ","TBLPPKSEJARAHBISUB","&ID_SEJARAHBIMAIN IN ( {DUMMY_FK}) ","TABLE : TBLPPKSEJARAHBISUB","ID_SEJARAHBISUB","bisejarahmain",getParam("ID_SEJARAHBIMAIN")));
			listSkrin.add(mapSetupSkrinAuto("historynota","TBLPPKHISTORYJANANOTA_SEQ","TBLPPKHISTORYJANANOTA","&ID_PERINTAH IN ( {DUMMY_FK}) ","TABLE : TBLPPKHISTORYJANANOTA","ID_HISTORYJANANOTA","perintah",getParam("ID_PERINTAH")));	
			listSkrin.add(mapSetupSkrinAuto("perintahhtaobmst","TBLPPKPERINTAHHTAOBMST_SEQ","TBLPPKPERINTAHHTAOBMST","&ID_PERINTAH IN ( {DUMMY_FK}) ","TABLE : TBLPPKPERINTAHHTAOBMST","ID_PERINTAHHTAOBMST","perintah",getParam("ID_PERINTAH")));	
			listSkrin.add(mapSetupSkrinAuto("perintahhaobmst","TBLPPKPERINTAHHAOBMST_SEQ","TBLPPKPERINTAHHAOBMST","&ID_PERINTAH IN ( {DUMMY_FK}) ","TABLE : TBLPPKPERINTAHHAOBMST","ID_PERINTAHHAOBMST","perintah",getParam("ID_PERINTAH")));
			listSkrin.add(mapSetupSkrinAuto("perintahhtaobdtl","TBLPPKPERINTAHHTAOBDTL_SEQ","TBLPPKPERINTAHHTAOBDTL","&ID_PERINTAHHTAOBMST IN ( {DUMMY_FK}) ","TABLE : TBLPPKPERINTAHHTAOBDTL","ID_PERINTAHHTAOBDTL","perintahhtaobmst",getParam("ID_PERINTAHHTAOBMST")));	
			listSkrin.add(mapSetupSkrinAuto("perintahhaobdtl","TBLPPKPERINTAHHAOBDTL_SEQ","TBLPPKPERINTAHHAOBDTL","&ID_PERINTAHHAOBMST IN ( {DUMMY_FK}) ","TABLE : TBLPPKPERINTAHHAOBDTL","ID_PERINTAHHAOBDTL","perintahhaobmst",getParam("ID_PERINTAHHAOBMST")));	
			listSkrin.add(mapSetupSkrinAuto("dokumenhta","TBLPPKDOKUMENHTA_SEQ","TBLPPKDOKUMENHTA","&ID_HTA IN ( {DUMMY_FK}) ","TABLE : TBLPPKDOKUMENHTA","ID_DOKUMEN","hta",getParam("ID_HTA")));	
			listSkrin.add(mapSetupSkrinAuto("agihankemaskini","TBLEDITAGIHAN_SEQ","TBLEDITAGIHAN","&ID_FAIL IN ( {DUMMY_FK}) ","TABLE : TBLEDITAGIHAN","ID_EDITCOMMENT","fail",getParam("ID_FAIL")));
			listSkrin.add(mapSetupSkrinAuto("notifikasikemaskini","TBLEDITNOTIFIKASI_SEQ","TBLEDITNOTIFIKASI","&ID_FAIL IN ( {DUMMY_FK}) ","TABLE : TBLEDITNOTIFIKASI","ID_EDITNOTIFIKASI","fail",getParam("ID_FAIL")));
			listSkrin.add(mapSetupSkrinAuto("commentkemaskini","TBLEDITCOMMENT_SEQ","TBLEDITCOMMENT","&ID_FAIL IN ( {DUMMY_FK}) ","TABLE : TBLEDITCOMMENT","ID_EDITCOMMENT","fail",getParam("ID_FAIL")));
			listSkrin.add(mapSetupSkrinAuto("intppkperintah","INT_PPKPERINTAH_SEQ","INT_PPKPERINTAH","&NO_FAIL IN (SELECT NO_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( {DUMMY_FK})) ","TABLE : INT_PPKPERINTAH","ID_PPKPERINTAH","fail",getParam("ID_FAIL")));
			listSkrin.add(mapSetupSkrinAuto("intppkhmperintah","INT_PPKHAKMILIKPERINTAH_SEQ","INT_PPKHAKMILIKPERINTAH","&ID_PPKPERINTAH IN ({DUMMY_FK}) ","TABLE : INT_PPKHAKMILIKPERINTAH","ID_PPKHAKMILIKPERINTAH","intppkperintah",getParam("ID_PPKPERINTAH")));
			listSkrin.add(mapSetupSkrinAuto("intppkhta","INT_PPKHTA_SEQ","INT_PPKHTA","&ID_HTA IN ({DUMMY_FK}) ","TABLE : INT_PPKHTA","ID_PPKHTA","hta",getParam("ID_HTA")));
			listSkrin.add(mapSetupSkrinAuto("intppkborange","INT_PPKBORANGE_SEQ","INT_PPKBORANGE","&ID_PPKHAKMILIKPERINTAH IN ({DUMMY_FK}) ","TABLE : INT_PPKBORANGE","ID_PPKBORANGE","intppkhmperintah",getParam("ID_PPKHAKMILIKPERINTAH")));
			listSkrin.add(mapSetupSkrinAuto("intppkborangf","INT_PPKBORANGF_SEQ","INT_PPKBORANGF","&ID_PPKHAKMILIKPERINTAH IN ({DUMMY_FK}) ","TABLE : INT_PPKBORANGF","ID_PPKBORANGF","intppkhmperintah",getParam("ID_PPKHAKMILIKPERINTAH")));
			listSkrin.add(mapSetupSkrinAuto("intppkborangh","INT_PPKBORANGH_SEQ","INT_PPKBORANGH","&ID_PPKBORANGE IN ({DUMMY_FK}) ","TABLE : INT_PPKBORANGH","ID_PPKBORANGH","intppkborange",getParam("ID_PPKBORANGE")));
			/*
			listSkrin.add(mapSetupSkrinAuto("mtpermohonan","TBLINTMTPERMOHONAN_SEQ","TBLINTMTPERMOHONAN","&PETISYENNO IN (SELECT NO_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( {DUMMY_FK})) ","TABLE : TBLINTMTPERMOHONAN","ID_MTPERMOHONAN","fail",getParam("ID_FAIL")));			
			listSkrin.add(mapSetupSkrinAuto("mtkeputusan","TBLINTMTKEPUTUSAN_SEQ","TBLINTMTKEPUTUSAN","&IDKADBIRU IN (SELECT IDKADBIRU FROM TBLINTMTPERMOHONAN WHERE ID_MTPERMOHONAN IN ( {DUMMY_FK})) ","TABLE : TBLINTMTKEPUTUSAN","IDKEPUTUSAN","mtpermohonan",getParam("ID_MTPERMOHONAN")));
			listSkrin.add(mapSetupSkrinAuto("mtborangckaveat","TBLINTMTBRGCKAVEAT_SEQ","TBLINTMTBRGCKAVEAT","&IDKADBIRU IN (SELECT IDKADBIRU FROM TBLINTMTPERMOHONAN WHERE ID_MTPERMOHONAN IN ( {DUMMY_FK})) ","TABLE : TBLINTMTBRGCKAVEAT","IDBRGCADAKAVEAT","mtpermohonan",getParam("ID_MTPERMOHONAN")));
			listSkrin.add(mapSetupSkrinAuto("mtborangcfail","TBLINTMTBRGCFAIL_SEQ","TBLINTMTBRGCFAIL","&IDKADBIRU IN (SELECT IDKADBIRU FROM TBLINTMTPERMOHONAN WHERE ID_MTPERMOHONAN IN ( {DUMMY_FK})) ","TABLE : TBLINTMTBRGCFAIL","IDBRGCFAIL","mtpermohonan",getParam("ID_MTPERMOHONAN")));
			listSkrin.add(mapSetupSkrinAuto("mtlog","TBLINTMTLOG_SEQ","TBLINTMTLOG","&NO_FAIL IN (SELECT NO_FAIL FROM TBLPFDFAIL WHERE ID_FAIL IN ( {DUMMY_FK})) ","TABLE : TBLINTMTLOG","ID_LOG","fail",getParam("ID_FAIL")));
			*/
		}
		else if(ID_SEKSYEN.equals("1"))
		{
			listSkrin.add(mapSetupSkrinAuto("fail","TBLPFDFAIL_SEQ","TBLPFDFAIL",  "&ID_FAIL IN ( {DUMMY_FK})" ,"TABLE : TBLPFDFAIL","ID_FAIL","",getParam("ID_FAIL")));
			listSkrin.add(mapSetupSkrinAuto("permohonan","TBLPPTPERMOHONAN_SEQ","TBLPPTPERMOHONAN",  "&ID_FAIL IN ( {DUMMY_FK})" ,"TABLE : TBLPPTPERMOHONAN","ID_PERMOHONAN","fail",getParam("ID_FAIL")));
			listSkrin.add(mapSetupSkrinAuto("suburusanstatusppt","TBLRUJSUBURUSANSTATUSFAILPPT_SEQ","TBLRUJSUBURUSANSTATUSFAILPPT",  "&ID_FAIL IN ( {DUMMY_FK})" ,"TABLE : TBLRUJSUBURUSANSTATUSFAILPPT","ID_SUBURUSANSTATUSFAILPPT","fail",getParam("ID_FAIL")));
			listSkrin.add(mapSetupSkrinAuto("hakmilikpermohonan","TBLPPTHAKMILIK_SEQ","TBLPPTHAKMILIK",  "&ID_PERMOHONAN IN ( {DUMMY_FK}) " +
					//" AND (FLAG_PENARIKAN_KESELURUHAN IS NOT NULL or FLAG_PEMBATALAN_KESELURUHAN IS NOT NULL) " +
					"" ,"TABLE : TBLPPTHAKMILIK","ID_HAKMILIK","permohonan",getParam("ID_PERMOHONAN")));
			listSkrin.add(mapSetupSkrinAuto("hakmilikasal","TBLPPTHAKMILIKASAL_SEQ","TBLPPTHAKMILIKASAL",  "&ID_HAKMILIK IN ( {DUMMY_FK})" ,"TABLE : TBLPPTHAKMILIKASAL","ID_HAKMILIKASAL","hakmilikpermohonan",getParam("ID_HAKMILIK")));
			listSkrin.add(mapSetupSkrinAuto("dokumenppt","TBLPPTDOKUMEN_SEQ","TBLPPTDOKUMEN","&ID_PERMOHONAN IN ( {DUMMY_FK})" ,"TABLE : TBLPPTDOKUMEN","ID_DOKUMEN","permohonan",getParam("ID_PERMOHONAN")));
			listSkrin.add(mapSetupSkrinAuto("senaraisemak","TBLPPTSENARAISEMAK_SEQ","TBLPPTSENARAISEMAK","&ID_PERMOHONAN IN ( {DUMMY_FK})" ,"TABLE : TBLPPTSENARAISEMAK","ID_SENARAISEMAK","permohonan",getParam("ID_PERMOHONAN")));
			listSkrin.add(mapSetupSkrinAuto("ppttugas","TBLPPTTUGAS_SEQ","TBLPPTTUGAS","&ID_PERMOHONAN IN ( {DUMMY_FK})" ,"TABLE : TBLPPTTUGAS","ID_TUGAS","permohonan",getParam("ID_PERMOHONAN")));
			listSkrin.add(mapSetupSkrinAuto("tanahumum","TBLPPTTANAHUMUM_SEQ","TBLPPTTANAHUMUM","&ID_PERMOHONAN IN ( {DUMMY_FK})" ,"TABLE : TBLPPTTANAHUMUM","ID_TANAHUMUM","permohonan",getParam("ID_PERMOHONAN")));
			
			//BUKAN PENARIKAN BALIK
			listSkrin.add(mapSetupSkrinAuto("mmkpermohonan","TBLPPTMMK_SEQ","TBLPPTMMK","&ID_PERMOHONAN IN ( {DUMMY_FK}) AND ID_PENARIKANBALIK IS NULL " ,"TABLE : TBLPPTMMK (PERMOHONAN)","ID_MMK","permohonan",getParam("ID_PERMOHONAN")));
			listSkrin.add(mapSetupSkrinAuto("submmkpermohonan","TBLPPTSUBMMK_SEQ","TBLPPTSUBMMK","&ID_MMK IN ( {DUMMY_FK})" ,"TABLE : TBLPPTSUBMMK (PERMOHONAN)","ID_SUBMMK","mmkpermohonan",getParam("ID_MMK")));
			listSkrin.add(mapSetupSkrinAuto("mmkkeputusanpermohonan","TBLPPTMMKKEPUTUSAN_SEQ","TBLPPTMMKKEPUTUSAN","&ID_MMK IN ({DUMMY_FK})" ,"TABLE : TBLPPTMMKKEPUTUSAN (PERMOHONAN)","ID_MMKKEPUTUSAN","mmkpermohonan",getParam("ID_MMK")));
			listSkrin.add(mapSetupSkrinAuto("wartapermohonan","TBLPPTWARTA_SEQ","TBLPPTWARTA","&ID_PERMOHONAN IN ({DUMMY_FK}) AND ID_PENARIKANBALIK IS NULL" ,"TABLE : TBLPPTWARTA (PERMOHONAN)","ID_WARTA","permohonan",getParam("ID_PERMOHONAN")));
			
			listSkrin.add(mapSetupSkrinAuto("notisawamhakmilik","TBLPPTNOTISAWAMHAKMILIK_SEQ","TBLPPTNOTISAWAMHAKMILIK","&ID_HAKMILIK IN ({DUMMY_FK})" ,"TABLE : TBLPPTNOTISAWAMHAKMILIK","ID_NOTISAWAMHAKMILIK","hakmilikpermohonan",getParam("ID_HAKMILIK")));
			listSkrin.add(mapSetupSkrinAuto("notisawambyhakmilik","TBLPPTNOTISAWAM_SEQ","TBLPPTNOTISAWAM","&ID_NOTISAWAM IN (SELECT ID_NOTISAWAM FROM TBLPPTNOTISAWAMHAKMILIK WHERE ID_NOTISAWAMHAKMILIK IN ({DUMMY_FK})) AND ID_HAKMILIK IS NULL" ,"TABLE : TBLPPTNOTISAWAM (HAKMILIK)","ID_NOTISAWAM","notisawamhakmilik",getParam("ID_NOTISAWAMHAKMILIK")));
			listSkrin.add(mapSetupSkrinAuto("notisawambypermohonan","TBLPPTNOTISAWAM_SEQ","TBLPPTNOTISAWAM","&ID_PERMOHONAN IN ({DUMMY_FK}) AND ID_HAKMILIK IS NULL" ,"TABLE : TBLPPTNOTISAWAM (PERMOHONAN)","ID_NOTISAWAM","permohonan",getParam("ID_PERMOHONAN")));
			listSkrin.add(mapSetupSkrinAuto("hakmilikpb","TBLPPTHAKMILIKPB_SEQ","TBLPPTHAKMILIKPB","&ID_HAKMILIK IN ({DUMMY_FK}) " ,"TABLE : TBLPPTHAKMILIKPB","ID_HAKMILIKPB","hakmilikpermohonan",getParam("ID_HAKMILIK")));
			listSkrin.add(mapSetupSkrinAuto("pb","TBLPPTPIHAKBERKEPENTINGAN_SEQ","TBLPPTPIHAKBERKEPENTINGAN","&ID_PIHAKBERKEPENTINGAN IN (SELECT ID_PIHAKBERKEPENTINGAN FROM TBLPPTHAKMILIKPB WHERE ID_HAKMILIKPB IN ({DUMMY_FK})) " ,"TABLE : TBLPPTPIHAKBERKEPENTINGAN","ID_PIHAKBERKEPENTINGAN","hakmilikpb",getParam("ID_HAKMILIKPB")));
			listSkrin.add(mapSetupSkrinAuto("bahagianpb","TBLPPTBAHAGIANPB_SEQ","TBLPPTBAHAGIANPB","&ID_BAHAGIANPB IN (SELECT ID_BAHAGIANPB FROM TBLPPTHAKMILIKPB WHERE ID_HAKMILIKPB IN ({DUMMY_FK})) " ,"TABLE : TBLPPTBAHAGIANPB","ID_BAHAGIANPB","hakmilikpb",getParam("ID_HAKMILIKPB")));
			listSkrin.add(mapSetupSkrinAuto("bebanan","TBLPPTBEBANAN_SEQ","TBLPPTBEBANAN","&ID_HAKMILIK IN ({DUMMY_FK})" ,"TABLE : TBLPPTBEBANAN","ID_BEBANAN","hakmilikpermohonan",getParam("ID_HAKMILIK")));
			listSkrin.add(mapSetupSkrinAuto("tandakawasan","TBLPPTTANDAKAWASAN_SEQ","TBLPPTTANDAKAWASAN","&ID_PERMOHONAN IN ({DUMMY_FK})" ,"TABLE : TBLPPTTANDAKAWASAN","ID_TANDAKAWASAN","permohonan",getParam("ID_PERMOHONAN")));
			listSkrin.add(mapSetupSkrinAuto("ulasantechpermohonan","TBLPPTULASANTEKNIKAL_SEQ","TBLPPTULASANTEKNIKAL","&ID_PERMOHONAN IN ({DUMMY_FK}) AND ID_HAKMILIK IS NULL " ,"TABLE : TBLPPTULASANTEKNIKAL (PERMOHONAN)","ID_ULASANTEKNIKAL","permohonan",getParam("ID_PERMOHONAN")));
			listSkrin.add(mapSetupSkrinAuto("ulasantechhakmilik","TBLPPTULASANTEKNIKAL_SEQ","TBLPPTULASANTEKNIKAL","&ID_HAKMILIK IN ({DUMMY_FK})" ,"TABLE : TBLPPTULASANTEKNIKAL (HAKMILIK)","ID_ULASANTEKNIKAL","hakmilikpermohonan",getParam("ID_HAKMILIK")));
			listSkrin.add(mapSetupSkrinAuto("bangunan","TBLPPTBANGUNAN_SEQ","TBLPPTBANGUNAN","&ID_HAKMILIK IN ({DUMMY_FK})" ,"TABLE : TBLPPTBANGUNAN","ID_BANGUNAN","hakmilikpermohonan",getParam("ID_HAKMILIK")));
			listSkrin.add(mapSetupSkrinAuto("bangunanpb","TBLPPTBANGUNANPB_SEQ","TBLPPTBANGUNANPB","&ID_BANGUNAN IN ({DUMMY_FK})" ,"TABLE : TBLPPTBANGUNANPB","ID_BANGUNANPB","bangunan",getParam("ID_BANGUNAN")));
			listSkrin.add(mapSetupSkrinAuto("tanah","TBLPPTTANAH_SEQ","TBLPPTTANAH","&ID_HAKMILIK IN ({DUMMY_FK})" ,"TABLE : TBLPPTTANAH","ID_TANAH","hakmilikpermohonan",getParam("ID_HAKMILIK")));
			listSkrin.add(mapSetupSkrinAuto("borangehakmilik","TBLPPTBORANGEHAKMILIK_SEQ","TBLPPTBORANGEHAKMILIK","&ID_HAKMILIK IN ({DUMMY_FK})" ,"TABLE : TBLPPTBORANGEHAKMILIK","ID_BORANGEHAKMILIK","hakmilikpermohonan",getParam("ID_HAKMILIK")));
			listSkrin.add(mapSetupSkrinAuto("borange","TBLPPTBORANGE_SEQ","TBLPPTBORANGE","&ID_BORANGE IN (SELECT ID_BORANGE FROM TBLPPTBORANGEHAKMILIK WHERE ID_BORANGEHAKMILIK IN ({DUMMY_FK}))" ,"TABLE : TBLPPTBORANGE","ID_BORANGE","borangehakmilik",getParam("ID_BORANGEHAKMILIK")));
			listSkrin.add(mapSetupSkrinAuto("borangf","TBLPPTBORANGF_SEQ","TBLPPTBORANGF","&ID_HAKMILIKPB IN ({DUMMY_FK})" ,"TABLE : TBLPPTBORANGF","ID_BORANGF","hakmilikpb",getParam("ID_HAKMILIKPB")));
			listSkrin.add(mapSetupSkrinAuto("buktipenyampaian","TBLPPTBUKTIPENYAMPAIAN_SEQ","TBLPPTBUKTIPENYAMPAIAN","&ID_HAKMILIK IN ({DUMMY_FK})" ,"TABLE : TBLPPTBUKTIPENYAMPAIAN","ID_BUKTIPENYAMPAIAN","hakmilikpermohonan",getParam("ID_HAKMILIK")));
			listSkrin.add(mapSetupSkrinAuto("siasatanpermohonan","TBLPPTSIASATAN_SEQ","TBLPPTSIASATAN","&ID_HAKMILIK IN ({DUMMY_FK}) AND ID_PENARIKANBALIK IS NULL " ,"TABLE : TBLPPTSIASATAN (PERMOHONAN)","ID_SIASATAN","hakmilikpermohonan",getParam("ID_HAKMILIK")));
			listSkrin.add(mapSetupSkrinAuto("kehadiranpermohonan","TBLPPTKEHADIRAN_SEQ","TBLPPTKEHADIRAN","&ID_SIASATAN IN ({DUMMY_FK}) " ,"TABLE : TBLPPTKEHADIRAN (PERMOHONAN)","ID_KEHADIRAN","siasatanpermohonan",getParam("ID_SIASATAN")));
			listSkrin.add(mapSetupSkrinAuto("subsiasatanpermohonan","TBLPPTSUBSIASATAN_SEQ","TBLPPTSUBSIASATAN","&ID_SIASATAN IN ({DUMMY_FK}) " ,"TABLE : TBLPPTSUBSIASATAN (PERMOHONAN)","ID_SUBSIASATAN","siasatanpermohonan",getParam("ID_SIASATAN")));
			listSkrin.add(mapSetupSkrinAuto("award","TBLPPTAWARD_SEQ","TBLPPTAWARD","&ID_SIASATAN IN ({DUMMY_FK}) " ,"TABLE : TBLPPTAWARD","ID_AWARD","siasatanpermohonan",getParam("ID_SIASATAN")));
			listSkrin.add(mapSetupSkrinAuto("subaward","TBLPPTSUBAWARD_SEQ","TBLPPTSUBAWARD","&ID_AWARD IN ({DUMMY_FK}) " ,"TABLE : TBLPPTSUBAWARD","ID_SUBAWARD","award",getParam("ID_AWARD")));
			listSkrin.add(mapSetupSkrinAuto("borangg","TBLPPTBORANGG_SEQ","TBLPPTBORANGG","&ID_SIASATAN IN ({DUMMY_FK}) " ,"TABLE : TBLPPTBORANGG","ID_BORANGG","siasatanpermohonan",getParam("ID_SIASATAN")));
			listSkrin.add(mapSetupSkrinAuto("borangJ","TBLPPTBORANGJ_SEQ","TBLPPTBORANGJ","&ID_HAKMILIK IN ({DUMMY_FK}) " ,"TABLE : TBLPPTBORANGJ","ID_BORANGJ","hakmilikpermohonan",getParam("ID_HAKMILIK")));
			listSkrin.add(mapSetupSkrinAuto("borangh","TBLPPTBORANGH_SEQ","TBLPPTBORANGH","&ID_BORANGG IN ({DUMMY_FK}) " ,"TABLE : TBLPPTBORANGH","ID_BORANGH","borangg",getParam("ID_BORANGG")));
			listSkrin.add(mapSetupSkrinAuto("terimabayaran","TBLPPTTERIMABAYARAN_SEQ","TBLPPTTERIMABAYARAN","&ID_HAKMILIK IN ({DUMMY_FK})" ,"TABLE : TBLPPTTERIMABAYARAN","ID_TERIMABAYARAN","hakmilikpermohonan",getParam("ID_HAKMILIK")));
			listSkrin.add(mapSetupSkrinAuto("bayaran","TBLPPTBAYARAN_SEQ","TBLPPTBAYARAN","&ID_HAKMILIKPB IN ({DUMMY_FK})" ,"TABLE : TBLPPTBAYARAN","ID_BAYARAN","hakmilikpb",getParam("ID_HAKMILIKPB")));
			listSkrin.add(mapSetupSkrinAuto("afidavit","TBLPPTAFIDAVIT_SEQ","TBLPPTAFIDAVIT","&ID_AWARD IN ({DUMMY_FK}) " ,"TABLE : TBLPPTAFIDAVIT","ID_AFIDAVIT","award",getParam("ID_AWARD")));
			listSkrin.add(mapSetupSkrinAuto("hakmilikborangk","TBLPPTHAKMILIKBORANGK_SEQ","TBLPPTHAKMILIKBORANGK","&ID_HAKMILIK IN ({DUMMY_FK})" ,"TABLE : TBLPPTHAKMILIKBORANGK","ID_HAKMILIKBORANGK","hakmilikpermohonan",getParam("ID_HAKMILIK")));
			listSkrin.add(mapSetupSkrinAuto("borangk","TBLPPTBORANGK_SEQ","TBLPPTBORANGK","&ID_BORANGK IN (SELECT ID_BORANGK FROM  TBLPPTHAKMILIKBORANGK WHERE ID_HAKMILIKBORANGK IN ({DUMMY_FK}))" ,"TABLE : TBLPPTBORANGK","ID_BORANGK","hakmilikborangk",getParam("ID_HAKMILIKBORANGK")));
			listSkrin.add(mapSetupSkrinAuto("endosanborangk","TBLPPTENDOSANBORANGK_SEQ","TBLPPTENDOSANBORANGK","&ID_BORANGK IN ({DUMMY_FK}) " ,"TABLE : TBLPPTENDOSANBORANGK","ID_ENDOSANBORANGK","borangk",getParam("ID_BORANGK")));
			listSkrin.add(mapSetupSkrinAuto("borangL","TBLPPTBORANGL_SEQ","TBLPPTBORANGL","&ID_PERMOHONAN IN ( {DUMMY_FK})" ,"TABLE : TBLPPTBORANGL","ID_BORANGL","permohonan",getParam("ID_PERMOHONAN")));
			listSkrin.add(mapSetupSkrinAuto("pu","TBLPPTPERMINTAANUKUR_SEQ","TBLPPTPERMINTAANUKUR","&ID_HAKMILIK IN ({DUMMY_FK}) " ,"TABLE : TBLPPTPERMINTAANUKUR","ID_PERMINTAANUKUR","hakmilikpermohonan",getParam("ID_HAKMILIK")));
			listSkrin.add(mapSetupSkrinAuto("pelanpu","TBLPPTNOPELANPU_SEQ","TBLPPTNOPELANPU","&ID_PERMINTAANUKUR IN ({DUMMY_FK}) " ,"TABLE : TBLPPTNOPELANPU","ID_NOPELANPU","pu",getParam("ID_PERMINTAANUKUR")));
			listSkrin.add(mapSetupSkrinAuto("bantahanKJP","TBLPPTBANTAHAN_SEQ","TBLPPTBANTAHAN","&ID_HAKMILIK IN ({DUMMY_FK}) " ,"TABLE : TBLPPTBANTAHAN (KJP)","ID_BANTAHAN","hakmilikpermohonan",getParam("ID_HAKMILIK")));
			listSkrin.add(mapSetupSkrinAuto("bantahanPB","TBLPPTBANTAHAN_SEQ","TBLPPTBANTAHAN","&ID_HAKMILIKPB IN ({DUMMY_FK}) " ,"TABLE : TBLPPTBANTAHAN (PB)","ID_BANTAHAN","hakmilikpb",getParam("ID_HAKMILIKPB")));
			listSkrin.add(mapSetupSkrinAuto("borangOPB","TBLPPTBORANGO_SEQ","TBLPPTBORANGO","&ID_BANTAHAN IN ({DUMMY_FK}) " ,"TABLE : TBLPPTBORANGO (PB)","ID_BORANGO","bantahanPB",getParam("ID_BANTAHAN")));
			listSkrin.add(mapSetupSkrinAuto("borangOKJP","TBLPPTBORANGO_SEQ","TBLPPTBORANGO","&ID_BANTAHAN IN ({DUMMY_FK}) " ,"TABLE : TBLPPTBORANGO (KJP)","ID_BORANGO","bantahanKJP",getParam("ID_BANTAHAN")));
			listSkrin.add(mapSetupSkrinAuto("borangI","TBLPPTBORANGI_SEQ","TBLPPTBORANGI","&ID_PERMOHONAN IN ( {DUMMY_FK})" ,"TABLE : TBLPPTBORANGI","ID_BORANGI","permohonan",getParam("ID_PERMOHONAN")));
			listSkrin.add(mapSetupSkrinAuto("borangM","TBLPPTBORANGM_SEQ","TBLPPTBORANGM","&ID_HAKMILIKPB IN ({DUMMY_FK})" ,"TABLE : TBLPPTBORANGM","ID_BORANGM","hakmilikpb",getParam("ID_HAKMILIKPB")));
			listSkrin.add(mapSetupSkrinAuto("pelan","TBLPPTNOPELAN_SEQ","TBLPPTNOPELAN","&ID_PERMOHONAN IN ( {DUMMY_FK})" ,"TABLE : TBLPPTNOPELAN","ID_NOPELAN","permohonan",getParam("ID_PERMOHONAN")));
			listSkrin.add(mapSetupSkrinAuto("borangQ","TBLPPTBORANGQ_SEQ","TBLPPTBORANGQ","&ID_HAKMILIK IN ({DUMMY_FK}) " ,"TABLE : TBLPPTBORANGQ","ID_BORANGQ","hakmilikpermohonan",getParam("ID_HAKMILIK")));
			listSkrin.add(mapSetupSkrinAuto("buktipenyampaianPB","TBLPPTBUKTIPENYAMPAIANPB_SEQ","TBLPPTBUKTIPENYAMPAIANPB","&ID_PIHAKBERKEPENTINGAN IN ({DUMMY_FK})" ,"TABLE : TBLPPTBUKTIPENYAMPAIANPB","ID_BUKTIPENYAMPAIANPB","pb",getParam("ID_PIHAKBERKEPENTINGAN")));
			
			listSkrin.add(mapSetupSkrinAuto("pembatalan","TBLPPTPEMBATALAN_SEQ","TBLPPTPEMBATALAN","&ID_PERMOHONAN IN ( {DUMMY_FK})" ,"TABLE : TBLPPTPEMBATALAN","ID_PEMBATALAN","permohonan",getParam("ID_PERMOHONAN")));
			listSkrin.add(mapSetupSkrinAuto("pembatalanhakmilik","TBLPPTPEMBATALANHAKMILIK_SEQ","TBLPPTPEMBATALANHAKMILIK","&ID_PEMBATALAN IN ( {DUMMY_FK})" ,"TABLE : TBLPPTPEMBATALANHAKMILIK","ID_PEMBATALANHAKMILIK","pembatalan",getParam("ID_PEMBATALAN")));
			listSkrin.add(mapSetupSkrinAuto("penarikan","TBLPPTPENARIKANBALIK_SEQ","TBLPPTPENARIKANBALIK","&ID_PERMOHONAN IN ( {DUMMY_FK})" ,"TABLE : TBLPPTPENARIKANBALIK","ID_PENARIKANBALIK","permohonan",getParam("ID_PERMOHONAN")));
			listSkrin.add(mapSetupSkrinAuto("penarikanhakmilik","TBLPPTPENARIKANHAKMILIK_SEQ","TBLPPTPENARIKANHAKMILIK","&ID_PENARIKANBALIK IN ( {DUMMY_FK})" ,"TABLE : TBLPPTPENARIKANHAKMILIK","ID_PENARIKANHAKMILIK","penarikan",getParam("ID_PENARIKANBALIK")));
			
			listSkrin.add(mapSetupSkrinAuto("mmkpenarikan","TBLPPTMMK_SEQ","TBLPPTMMK","&ID_PENARIKANBALIK IN ( {DUMMY_FK})" ,"TABLE : TBLPPTMMK (PENARIKAN BALIK)","ID_MMK","penarikan",getParam("ID_PENARIKANBALIK")));
			listSkrin.add(mapSetupSkrinAuto("submmkpenarikan","TBLPPTSUBMMK_SEQ","TBLPPTSUBMMK","&ID_MMK IN ( {DUMMY_FK})" ,"TABLE : TBLPPTSUBMMK (PENARIKAN BALIK)","ID_SUBMMK","mmkpenarikan",getParam("ID_MMK")));
			listSkrin.add(mapSetupSkrinAuto("mmkkeputusanpenarikan","TBLPPTMMKKEPUTUSAN_SEQ","TBLPPTMMKKEPUTUSAN","&ID_MMK IN ({DUMMY_FK})" ,"TABLE : TBLPPTMMKKEPUTUSAN (PENARIKAN BALIK)","ID_MMKKEPUTUSAN","mmkpenarikan",getParam("ID_MMK")));
			listSkrin.add(mapSetupSkrinAuto("wartapenarikan","TBLPPTWARTA_SEQ","TBLPPTWARTA","&ID_PENARIKANBALIK IN ({DUMMY_FK}) " ,"TABLE : TBLPPTWARTA (PENARIKAN BALIK)","ID_WARTA","penarikan",getParam("ID_PENARIKANBALIK")));
			
			listSkrin.add(mapSetupSkrinAuto("statushakmilik","TBLRUJSUBURUSANSTATUSHAKMILIK_SEQ","TBLRUJSUBURUSANSTATUSHAKMILIK","&ID_HAKMILIK IN ( {DUMMY_FK})" ,"TABLE : TBLRUJSUBURUSANSTATUSHAKMILIK","ID_SUBURUSANSTATUSHAKMILIK","hakmilikpermohonan",getParam("ID_HAKMILIK")));
			
			
		
		}
		else if(ID_SEKSYEN.equals("3"))
		{
			listSkrin.add(mapSetupSkrinAuto("fail","TBLPFDFAIL_SEQ","TBLPFDFAIL",  "&ID_FAIL IN ( {DUMMY_FK})" ,"TABLE : TBLPFDFAIL","ID_FAIL","",getParam("ID_FAIL")));
		}
		else if(ID_SEKSYEN.equals("4"))
		{
			listSkrin.add(mapSetupSkrinAuto("fail","TBLPFDFAIL_SEQ","TBLPFDFAIL",  "&ID_FAIL IN ( {DUMMY_FK})" ,"TABLE : TBLPFDFAIL","ID_FAIL","",getParam("ID_FAIL")));
		}
			
		
		return listSkrin;
	}
		
	
	public Map mapSetupSkrinAuto(String skrinName, String seqName,String tableName,String filterScript, String tajuk, String PK_FIELD,String ParentSkrin,
			String paramFieldFilter)
	{
		Map hSetupSkrin = Collections.synchronizedMap(new HashMap());
		hSetupSkrin.put("skrinName",skrinName);
		hSetupSkrin.put("seqName",seqName);
		hSetupSkrin.put("tableName",tableName);
		hSetupSkrin.put("filterScript",filterScript);
		hSetupSkrin.put("tajuk",tajuk);
		hSetupSkrin.put("PK_FIELD",PK_FIELD);
		hSetupSkrin.put("ParentTable","");	
		hSetupSkrin.put("ParentSkrin",ParentSkrin);	
		hSetupSkrin.put("paramFieldFilter",paramFieldFilter);	
		return hSetupSkrin;		
	}
	
	
	
	//disini, kita setting skrin carian, 
	//ongoing, sy akan setup mcm2 untuk templete ini bagi kegunaan semua
	public String HTMLSkrinCarian(Db db, String skrinName, String modul) throws Exception
	{ 
		String html = "";
		html += "<table width='100%' align='center' border='0' cellpadding='1' cellspacing='1'  class='classFade' > ";
		html += "<tr><td width='50%' valign=top>";
		html += RT.openHTMLTable();
		html += RT.setRowTextCarian(skrinName,//skrinName
				"No. Fail",//label field 
				"CARIAN_NO_FAIL",// nama & id field, sama kan dengan nama filed dalam DB
				100,//setting maxlength
				"",//default value
				"",//apa js yang dipanggil semasa onblur
				"",//jika field ini kan menggunakan datalist
				db //db object
				);
		
		
		
		html += RT.setRowSelectHC(skrinName,//skrinName
				true,//adakah paparan disekalikan dengan setup TR dan TD atau field semata2
				"edit",//mode : edit/view
				null,//set data
				"Nama Modul",//label field 
				"CARIAN_ID_SEKSYEN",// nama & id field, sama kan dengan nama filed dalam DB
				"HC_JENISMODUL",// nama list hard code
				false,// adakah field ini mandatory true=ya
				"select",// hidden/select/radio
				true,// display : diantara label & field
				true,//defaultUppercase pada value input
				"",//default value
				true,//adalah field ini untuk kegunaan carian
				"",//apa js yang dipanggil semasa onChange
				db //db object
				);
		
		html += RT.closeHTMLTable();	
		html += "</td><td width='50%' valign=top>";
		html += RT.openHTMLTable();
		html += RT.setRowTextCarian(skrinName,"No. Permohonan Online","CARIAN_NO_PERMOHONAN_ONLINE",100,"","","",db);
		html += RT.setRowTextCarian(skrinName,//skrinName
				"Id Fail",//label field 
				"CARIAN_ID_FAIL",// nama & id field, sama kan dengan nama filed dalam DB
				100,//setting maxlength
				"",//default value
				"",//apa js yang dipanggil semasa onblur
				"",//jika field ini kan menggunakan datalist
				db //db object
				);
		html += RT.closeHTMLTable();
		html += "</td></tr></table>";
		html += setupButtonCarian(skrinName);
		html += "";
		return html;
	}
	
	
	
	public String setupButtonCarian(String skrinName) throws Exception {
		
		String html = "<table width='100%' align='center' border='0' cellpadding='1' cellspacing='1' style='margin-top:5px' id=\"button"+skrinName+"\"  >";
		html += "<tr>";
		html += "<tr><td width='100%'  align='center'  style='border-top: 1px solid #000;'>";
		html += " <input type=\"button\" id=\"cmdCari"+skrinName+"\" name=\"cmdCari"+skrinName+"\" value=\"Cari\" onClick=\"if(checkSkrinCarian('"+skrinName+"') == true){"+RT.ajaxCallFunction("carianSenaraiUtama","div_carianUtama",  skrinName,"")+"}\" >";
		html += " <input type=\"button\" id=\"cmdReset"+skrinName+"\" name=\"cmdReset"+skrinName+"\" value=\"Reset\" onClick=\"resetSkrinCarian('"+skrinName+"');\" >";
		html +=	"</td>";
		html += "</tr></table>";		
		return html;
	}
	
	
	
	
	public String queryByTABLElist(Db db,String skrinName,String namaList,String TABLE_NAME, String filter, String cacheID) throws Exception
	{
		String sql = " SELECT * FROM "+TABLE_NAME+" WHERE ROWNUM < 1 ";		
		List columnNameFromQuery = RT.returnColumnNameFromQuery(sql, namaList, true, false, true, cacheID, db);
		
		sql = " SELECT ";
		if (columnNameFromQuery.size() != 0) {
			for (int i = 0; i < columnNameFromQuery.size(); i++) {
				Map map_column_name = (Map) columnNameFromQuery.get(i);
				String COLUMN_NAME = (String) map_column_name.get("COLUMN_NAME");
				String COLUMN_TYPE = (String) map_column_name.get("COLUMN_TYPE");
				
				//if(COLUMN_NAME.contains("TARIKH") && COLUMN_TYPE.equals("DATE"))
				//{
					//sql += " TO_CHAR("+COLUMN_NAME+",'DD/MM/YYYY') AS "+COLUMN_NAME+" ";
				//}
				//else
				//{
					sql += " "+COLUMN_NAME+" ";
				//}
				
				
				if((i+1) < columnNameFromQuery.size())
				{
					sql += ", ";
				}
			}
			
		}
		sql += " FROM "+TABLE_NAME+" ";
		
		if(!filter.equals(""))
		{
			sql += " WHERE "+filter;
		}
		
		myLogger.info("queryByTABLE ::: "+sql);
		
		return sql;
	}
	
	public String queryByTABLEMap(Db db,String skrinName,String TABLE_NAME, String PK_field, String PK_value, String cacheID) throws Exception
	{
		String sql = " SELECT * FROM "+TABLE_NAME+" ";		
		List columnNameFromQuery = RT.returnColumnNameFromQuery(sql, TABLE_NAME+"map", true, false, true, cacheID, db);
		
		sql = " SELECT ";
		if (columnNameFromQuery.size() != 0) {
			for (int i = 0; i < columnNameFromQuery.size(); i++) {
				Map map_column_name = (Map) columnNameFromQuery.get(i);
				String COLUMN_NAME = (String) map_column_name.get("COLUMN_NAME");
				String COLUMN_TYPE = (String) map_column_name.get("COLUMN_TYPE");
				sql += " "+COLUMN_NAME+" ";
								
				if((i+1) < columnNameFromQuery.size())
				{
					sql += ", ";
				}
			}
			
		}
		sql += " FROM "+TABLE_NAME+" ";
		
		if(!PK_field.equals(""))
		{
			sql += " WHERE "+PK_field+" = '"+PK_value+"' ";
		}
		
		myLogger.info("queryByTABLEMap ::: "+sql);
		
		return sql;
	}
	
	//setting custom query untuk  carian
	//disini boleh accept parameter carian
	//tolong hati2 dengan query, make sure table2 are properly indexed
	public String querySenaraiFail(Db db,String skrinName) throws Exception
	{
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");
		
		//jika melibatkan carian
		//untuk parameter carian, tolong tambah 'CARIAN_' didepan. untuk mengelak duplicate parameter dari main rekod
		
		String CARIAN_ID_FAIL = getParam(skrinName+"CARIAN_ID_FAIL");
		String WCCARIAN_ID_FAIL = getParam("WC"+skrinName+"CARIAN_ID_FAIL");		
		String CARIAN_NO_FAIL = getParam(skrinName+"CARIAN_NO_FAIL");
		String WCCARIAN_NO_FAIL = getParam("WC"+skrinName+"CARIAN_NO_FAIL");
		String CARIAN_NO_PERMOHONAN_ONLINE = getParam(skrinName+"CARIAN_NO_PERMOHONAN_ONLINE");
		String WCCARIAN_NO_PERMOHONAN_ONLINE = getParam("WC"+skrinName+"CARIAN_NO_PERMOHONAN_ONLINE");
		String CARIAN_ID_SEKSYEN = getParam(skrinName+"CARIAN_ID_SEKSYEN");
		
		
		String sql = " SELECT F.ID_FAIL, F.NO_FAIL, F.ID_SEKSYEN, F.TARIKH_MASUK FROM TBLPFDFAIL F WHERE F.ID_FAIL IS NOT NULL  ";
		
		if(!CARIAN_ID_SEKSYEN.equals(""))
		{
			sql += " AND F.ID_SEKSYEN = '"+CARIAN_ID_SEKSYEN+"' ";
		}
		else
		{
			sql += " AND F.ID_SEKSYEN IN (1,2,3,4) ";
		}
		
		if(!CARIAN_NO_FAIL.equals(""))
		{
			if(WCCARIAN_NO_FAIL.equals("1"))
			{
				sql += " AND UPPER(F.NO_FAIL) = '"+CARIAN_NO_FAIL.toUpperCase().trim()+"' ";
			}
			else if(WCCARIAN_NO_FAIL.equals("2"))
			{
				sql += " AND UPPER(F.NO_FAIL) LIKE '%"+CARIAN_NO_FAIL.toUpperCase().trim()+"%' ";
			}			
		}
		
		
		if(!CARIAN_ID_FAIL.equals(""))
		{
			if(WCCARIAN_ID_FAIL.equals("1"))
			{
				sql += " AND UPPER(F.ID_FAIL) = '"+CARIAN_ID_FAIL.toUpperCase().trim()+"' ";
			}
			else if(WCCARIAN_NO_FAIL.equals("2"))
			{
				sql += " AND UPPER(F.ID_FAIL) LIKE '%"+CARIAN_ID_FAIL.toUpperCase().trim()+"%' ";
			}			
		}
		
		
		if(!CARIAN_NO_PERMOHONAN_ONLINE.equals(""))
		{
			sql += " AND F.ID_FAIL IN (SELECT P.ID_FAIL FROM TBLPPKPERMOHONAN P WHERE P.ID_PERMOHONAN IS NOT NULL ";
			if(!CARIAN_NO_PERMOHONAN_ONLINE.equals(""))
			{	
				if(WCCARIAN_NO_PERMOHONAN_ONLINE.equals("1"))
				{
					sql += " AND UPPER(P.NO_PERMOHONAN_ONLINE) = '"+CARIAN_NO_PERMOHONAN_ONLINE.toUpperCase().trim()+"' ";
				}
				else if(WCCARIAN_NO_PERMOHONAN_ONLINE.equals("2"))
				{
					sql += " AND UPPER(P.NO_PERMOHONAN_ONLINE) LIKE '%"+CARIAN_NO_PERMOHONAN_ONLINE.toUpperCase().trim()+"%' ";
				}
			}
			sql += " ) ";
		}
		
		return sql;
	}
	
	
	
	public String queryHeaderFail(Db db,String skrinName, String ID_SEKSYEN) throws Exception
	{
		String ID_FAIL = getParam("ID_FAIL");		
		String sql = "";
		if(ID_SEKSYEN.equals("2"))
		{
			sql = "SELECT DISTINCT F.ID_SEKSYEN,F.NO_FAIL, SM.NAMA_SIMATI, PM.NAMA_PEMOHON, TO_CHAR(P.TARIKH_MOHON,'DD/MM/YYYY') AS TARIKH_MOHON, " +
					//" P.TARIKH_MOHON AS TM1, " +
					" ST.KETERANGAN AS STATUS, P.SEKSYEN, P.ID_STATUS, "+
					" PSM.ID_PERMOHONANSIMATI, F.ID_FAIL, " +
					" TO_CHAR(F.TARIKH_DAFTAR_FAIL,'DD/MM/YYYY') AS TARIKH_DAFTAR_FAIL," +
					" F.FLAG_JENIS_FAIL, "+ 
					" P.ID_PERMOHONAN, SM.ID_SIMATI, "+
					" PEND.PENDAFTAR AS PENDAFTAR_FAIL  "+
					" FROM  "+
					" TBLPFDFAIL F, TBLPPKPERMOHONAN P, TBLPPKPEMOHON PM, TBLPPKPERMOHONANSIMATI PSM, TBLPPKSIMATI SM, TBLRUJSTATUS ST, "+
					" (SELECT SUTF.ID_FAIL, UU.USER_NAME AS PENDAFTAR, UU.USER_LOGIN FROM USERS UU, TBLRUJSUBURUSANSTATUSFAIL SUTF  "+
					" WHERE UU.USER_ID = SUTF.ID_MASUK AND SUTF.ID_SUBURUSANSTATUS IN (340, 430)) PEND "+				
					" WHERE F.ID_FAIL = P.ID_FAIL AND P.ID_PEMOHON = PM.ID_PEMOHON AND F.ID_FAIL = PEND.ID_FAIL(+) "+
					" AND P.ID_PERMOHONAN = PSM.ID_PERMOHONAN AND SM.ID_SIMATI = PSM.ID_SIMATI AND P.ID_STATUS = ST.ID_STATUS "+
					" AND F.ID_FAIL = '"+ID_FAIL+"' ";
		}
		else if(ID_SEKSYEN.equals("1"))
		{
			sql = " SELECT F.NO_FAIL, P.NO_PERMOHONAN_ONLINE, P.NO_RUJUKAN_PTG, P.NO_RUJUKAN_UPT, P.NO_RUJUKAN_PTD, P.NO_PERMOHONAN, "+
					" TO_CHAR(P.TARIKH_PERMOHONAN,'DD/MM/YYYY') AS TARIKH_PERMOHONAN, TO_CHAR(P.TARIKH_PERMOHONAN_KJP,'DD/MM/YYYY') AS TARIKH_PERMOHONAN_KJP, "+ 
					" F.ID_SEKSYEN, P.ID_STATUS, S.KETERANGAN AS STATUS "+
					" FROM TBLPFDFAIL F, TBLPPTPERMOHONAN P, TBLRUJSTATUS S WHERE  "+
					" F.ID_FAIL = P.ID_FAIL AND P.ID_STATUS = S.ID_STATUS "+
					" AND F.ID_FAIL = '"+ID_FAIL+"'  ";
		}
		else if(ID_SEKSYEN.equals("3"))
		{
			//bleh custom mengikut modul pun header
			sql = " SELECT F.NO_FAIL FROM TBLPFDFAIL F WHERE F.ID_FAIL = '"+ID_FAIL+"'  ";
		}
		else if(ID_SEKSYEN.equals("4"))
		{
			//bleh custom mengikut modul pun header
			sql = " SELECT F.NO_FAIL FROM TBLPFDFAIL F WHERE F.ID_FAIL = '"+ID_FAIL+"'  ";
		}
		return sql;
	}
	
	
	
	
	
	//method untuk susun column dalam senarai
	//dalam case ni tak pakai	
	public List listColumnForSenaraiFail(String skrinName
			//,String skrinName
			)throws Exception {
		List listColumnForSenarai = null;
		//myLogger.info("listColumnForList namaList : "+namaList);
		listColumnForSenarai = Collections.synchronizedList(new ArrayList());
		//setting nama column yang kita nak display dari query
		listColumnForSenarai.add(RT.getColumnForSenarai(skrinName, 
				"ID_FAIL", //COLUMN NAME
				//"{ID_FAIL}",//display
				0,//width
				"ID_FAIL", //LABEL
				"left", // ALIGN
				"NUMBER", // DATATYPE
				"papar('{ID_FAIL}','{ID_SEKSYEN}','"+skrinName+"')",//ni tempat letak script, incase untuk custom display >> function ONCLIK LINK mesti diletakkan dalam index app skrin ini
				""
				));	
		//jika param2 untuk onclick script ni akan ditarik dari maklumat list, buat mcm ni {}
		//makesure column yang kita nak panggil ni ada dalam query list kita
		
		
		listColumnForSenarai.add(RT.getColumnForSenarai(skrinName, "NO_FAIL",
				//"{NO_FAIL}",
				0,"NO_FAIL", "left", 
				"VARCHAR2","",""));
		listColumnForSenarai.add(RT.getColumnForSenarai(skrinName, "ID_SEKSYEN",
				//"{NO_FAIL}",
				0,"ID_SEKSYEN", "left", 
				"NUMBER","",""));
		
		listColumnForSenarai.add(RT.getColumnForSenarai(skrinName, "TARIKH_MASUK",
				//"{NO_FAIL}",
				0,"TARIKH_MASUK", "left", 
				"DATE","","","yyyy-MM-dd HH:mm:ss.S"));
		
		return listColumnForSenarai;
	}
	
	//method untuk susun column dalam senarai
		//dalam case ni tak pakai	
		public List listColumnForSenaraiCRUD(String div,String skrinName,String tableName,
				//String namaList,
				String PK_FIELD, String filterDB, String param)throws Exception {
			List listColumnForSenarai = null;
			//myLogger.info("listColumnForList namaList : "+namaList);
			listColumnForSenarai = Collections.synchronizedList(new ArrayList());
			
			listColumnForSenarai.add(RT.getColumnForSenarai(skrinName,PK_FIELD,0,"PK : "+PK_FIELD, "left","VARCHAR2","",""));
			
			if(tableName.equals("TBLPFDFAIL"))
			{
				listColumnForSenarai.add(RT.getColumnForSenarai(skrinName,"NO_FAIL",0,"NO_FAIL", "left","VARCHAR2","",""));
				listColumnForSenarai.add(RT.getColumnForSenarai(skrinName,"ID_SEKSYEN",0,"ID_SEKSYEN", "left","VARCHAR2","",""));
				listColumnForSenarai.add(RT.getColumnForSenarai(skrinName,"ID_URUSAN",0,"ID_URUSAN", "left","VARCHAR2","",""));
				listColumnForSenarai.add(RT.getColumnForSenarai(skrinName,"ID_SUBURUSAN",0,"ID_SUBURUSAN", "left","VARCHAR2","",""));
			}
			else if(tableName.equals("TBLPPKPERMOHONAN"))
			{
				listColumnForSenarai.add(RT.getColumnForSenarai(skrinName,"ID_FAIL",0,"ID_FAIL", "left","VARCHAR2","",""));
				listColumnForSenarai.add(RT.getColumnForSenarai(skrinName,"TARIKH_MOHON",0,"TARIKH_MOHON", "left","DATE","","","dd/MM/yyyy"));
				listColumnForSenarai.add(RT.getColumnForSenarai(skrinName,"NO_PERMOHONAN_ONLINE",0,"NO_PERMOHONAN_ONLINE", "left","VARCHAR2","",""));
				listColumnForSenarai.add(RT.getColumnForSenarai(skrinName,"ID_NEGERIMHN",0,"ID_NEGERIMHN", "left","VARCHAR2","",""));
				listColumnForSenarai.add(RT.getColumnForSenarai(skrinName,"ID_DAERAHMHN",0,"ID_DAERAHMHN", "left","VARCHAR2","",""));
			}
			else if(tableName.equals("TBLRUJSUBURUSANSTATUSFAIL"))
			{
				listColumnForSenarai.add(RT.getColumnForSenarai(skrinName,"ID_FAIL",0,"ID_FAIL", "left","VARCHAR2","",""));
				listColumnForSenarai.add(RT.getColumnForSenarai(skrinName,"ID_PERMOHONAN",0,"ID_PERMOHONAN", "left","VARCHAR2","",""));
				listColumnForSenarai.add(RT.getColumnForSenarai(skrinName,"ID_SUBURUSANSTATUS",0,"ID_SUBURUSANSTATUS", "left","VARCHAR2","",""));
				listColumnForSenarai.add(RT.getColumnForSenarai(skrinName,"AKTIF",0,"AKTIF", "left","VARCHAR2","",""));
			}
			else if(tableName.equals("TBLPPKKEPUTUSANPERMOHONAN"))
			{
				listColumnForSenarai.add(RT.getColumnForSenarai(skrinName,"ID_PERMOHONAN",0,"ID_PERMOHONAN", "left","VARCHAR2","",""));
				listColumnForSenarai.add(RT.getColumnForSenarai(skrinName,"KEPUTUSAN_PERMOHONAN",0,"KEPUTUSAN_PERMOHONAN", "left","VARCHAR2","",""));
			}
			else if(tableName.equals("TBLPPKOB") || tableName.equals("TBLPPKOBPERMOHONAN"))
			{
				listColumnForSenarai.add(RT.getColumnForSenarai(skrinName,"NAMA_OB",0,"NAMA_OB", "left","VARCHAR2","",""));
				listColumnForSenarai.add(RT.getColumnForSenarai(skrinName,"NO_KP_BARU",0,"NO_KP_BARU", "left","VARCHAR2","",""));
			}
			else if(tableName.equals("TBLPPKHTA") || tableName.equals("TBLPPKHTAPERMOHONAN"))
			{
				listColumnForSenarai.add(RT.getColumnForSenarai(skrinName,"NO_HAKMILIK",0,"NO_HAKMILIK", "left","VARCHAR2","",""));
				listColumnForSenarai.add(RT.getColumnForSenarai(skrinName,"NO_PT",0,"NO_PT", "left","VARCHAR2","",""));
			}
			else if(tableName.equals("TBLPPKHA") || tableName.equals("TBLPPKHAPERMOHONAN"))
			{
				listColumnForSenarai.add(RT.getColumnForSenarai(skrinName,"JENAMA",0,"JENAMA", "left","VARCHAR2","",""));
				listColumnForSenarai.add(RT.getColumnForSenarai(skrinName,"NO_DAFTAR",0,"NO_DAFTAR", "left","VARCHAR2","",""));
				listColumnForSenarai.add(RT.getColumnForSenarai(skrinName,"NO_SIJIL",0,"NO_SIJIL", "left","VARCHAR2","",""));
			}
			else if(tableName.equals("TBLPPKPERBICARAAN"))
			{
				listColumnForSenarai.add(RT.getColumnForSenarai(skrinName,"BIL_BICARA",0,"BIL_BICARA", "center","VARCHAR2","",""));
				listColumnForSenarai.add(RT.getColumnForSenarai(skrinName,"TARIKH_BICARA",0,"TARIKH_BICARA", "center","DATE","","","dd/MM/yyyy"));
				listColumnForSenarai.add(RT.getColumnForSenarai(skrinName,"PEG_PENGENDALI",0,"PEG_PENGENDALI", "left","VARCHAR2","",""));
			}
			
			
			String htmlCustom = "<a href=\"javascript:setingTrDivTR('divId"+skrinName+"','divView"+skrinName+"{"+PK_FIELD+"}');doDivAjaxCall"+RT.getFormName()+"('divView"+skrinName+"{"+PK_FIELD+"}','viewCrud','&div=divView"+skrinName+"&skrinName="+skrinName+"&"+PK_FIELD+"={"+PK_FIELD+"}&pkField="+PK_FIELD+"&filterDB="+filterDB+"&tableName="+tableName+param+"')\"><img title=\"Kemaskini\" src=\"../img/edit.gif\" border=\"0\"></a>";	   
			htmlCustom += "<a class=\"buttonHapus\" href=\"javascript:if(confirm('Data akan dipadam. Adakah Anda Pasti?')){doDivAjaxCall"+RT.getFormName()+"('"+div+"','deleteCrud','&div="+div+"&skrinName="+skrinName+"&"+PK_FIELD+"={"+PK_FIELD+"}&pkField="+PK_FIELD+"&filterDB="+filterDB+"&tableName="+tableName+param+"&scrolPosition='+getPageLocation());}\"><img title=\"Hapus Maklumat Perubahan\" src=\"../img/delete.gif\" border=\"0\"></a>";	   
			
			
			listColumnForSenarai.add(RT.getColumnForSenarai(skrinName,"",10,"Tindakan", "center",
					//namaList,
					"VARCHAR2","",htmlCustom));
			return listColumnForSenarai;
		}
	

	//[STANDARD]
	//boleh custome mengikut keperluan
	public void defaultContextPut()
	{
		//segala context.put yang digunapakai dalam class ni mesti di initiate
		
		
	}

	

}
