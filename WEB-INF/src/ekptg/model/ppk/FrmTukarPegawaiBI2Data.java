package ekptg.model.ppk;

import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.pm.entity.ActivityEvent;
import lebah.pm.entity.UserActivityEvent;
import lebah.template.DbPersistence;

import org.apache.log4j.Logger;

import ekptg.engine.EmailProperty;
import ekptg.engine.EmailSender;
import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;
import ekptg.report.ppk.FrmPopupPilihPegawaiReportData;
import ekptg.model.ppk.BicaraInteraktifData;

public class FrmTukarPegawaiBI2Data {
	static Logger myLogger = Logger.getLogger(ekptg.model.ppk.FrmTukarPegawaiBI2Data.class);
	BicaraInteraktifData modelBI = new BicaraInteraktifData();
	
	public Map getDetailUsers(HttpSession session, String nama, String user_id, String id_negeri, Db db)throws Exception {
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		String sql = "";
		try {
			if(db != null)
			{
				db1 = db;
			}
			else
			{
				db1 = new Db();
			}
			stmt = db1.getStatement();	
			sql += " SELECT * FROM USERS U,USERS_INTERNAL UI WHERE U.USER_ID = UI.USER_ID " +
					//" AND NVL(UI.FLAG_AKTIF,' ') IN (' ','1') " +
					//" AND UI.ID_SEKSYEN = '2' " +
					" ";
			if(!user_id.equals(""))
			{
				sql += " AND U.USER_ID = '"+user_id+"' ";
			}
			else
			{
				if(!nama.equals(""))
				{
					sql += " AND UPPER(U.USER_NAME) = '"+nama.toUpperCase()+"' ";
				}
			}
			
			if(!id_negeri.equals(""))
			{
				sql += " AND UI.ID_NEGERI = '"+id_negeri+"' ";
			}
																			
			myLogger.info(" Tukar Pegawa Pengendali 2 : SQL getDetailUsers :"+ sql);			
			rs = stmt.executeQuery(sql);
			Map h = null;		
			while (rs.next()) {	
				h = Collections.synchronizedMap(new HashMap());		
				h.put("USER_ID",rs == null ? "" :rs.getString("USER_ID") == null ? "" : rs.getString("USER_ID"));
				h.put("USER_NAME",rs == null ? "" :rs.getString("USER_NAME") == null ? "" : rs.getString("USER_NAME"));
				h.put("ID_JAWATAN",rs == null ? "" :rs.getString("ID_JAWATAN") == null ? "" : rs.getString("ID_JAWATAN"));
				h.put("ID_NEGERI",rs == null ? "" :rs.getString("ID_NEGERI") == null ? "" : rs.getString("ID_NEGERI"));		
				h.put("ID_SEKSYEN",rs == null ? "" :rs.getString("ID_SEKSYEN") == null ? "" : rs.getString("ID_SEKSYEN"));		
			}
			return h;
		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
	}
	
	public String getDetailPegawaiList(HttpSession session, String username, Db db)throws Exception {
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
		Db db1 = null;
		ResultSet rs = null;
		Statement stmt = null;
		String sql = "";
		try {
			if(db != null)
			{
				db1 = db;
			}
			else
			{
				db1 = new Db();
			}
			stmt = db1.getStatement();			
			sql += " SELECT LIST_ID_UNITPSK || (SELECT ',' || (rtrim (xmlagg (xmlelement (e, ID_UNITPSK || ','  )).extract ('//text()'))|| 0) FROM TBLPPKRUJUNIT  WHERE USER_ID = '"+USER_ID_SYSTEM+"'  GROUP BY USER_ID) AS LIST_ID_UNITPSK FROM ( " +
				   " SELECT (rtrim (xmlagg (xmlelement (e, ID_UNITPSK || ','  )).extract ('//text()')) || 0) " +
				   " AS LIST_ID_UNITPSK FROM ( "+
				   " SELECT ID_UNITPSK,NAMA_PEGAWAI, UTL_MATCH.edit_distance(UPPER(NAMA_PEGAWAI),UPPER('"+username+"')) TOT_BEZA "+
				   " FROM TBLPPKRUJUNIT ) WHERE  TOT_BEZA < 4 ) ";			
			myLogger.info(" BICARA INTERAKTIF : SQL getDetailPegawaiList :"+ sql);			
			rs = stmt.executeQuery(sql);
			
			String LIST_ID_UNITPSK = "0";
			while (rs.next()) {	
				LIST_ID_UNITPSK = rs == null ? "" :rs.getString("LIST_ID_UNITPSK") == null ? "" : rs.getString("LIST_ID_UNITPSK");
			}
			return LIST_ID_UNITPSK;
		} finally {
			if (db == null)
			{
				db1.close();
			}
		}
	}
	
	public void hantarEmelTukarPegawai(HttpSession session,String ID_PERBICARAAN, String ID_TUKARPEGAWAI, String ID_PERMOHONAN, String ID_PERMOHONANSIMATI, 
			String FLAG_DAFTAR_TERUS,String ID_NEGERIPEGAWAIASAL, String ID_PEGAWAIASAL,String ID_NEGERIPEGAWAIBARU, String ID_PEGAWAIBARU,String STATUS_TUKARPEGAWAI,String FLAG_MULTiPLE, 
			String SELECTED_NAMA_NEGERI,String SELECTED_NAMA_PEGAWAI,List senaraiSelected,
			Db db) throws Exception {			
			myLogger.info("MASUK FUNCTION EMEL hantarEmelTukarPegawai");		
			
			EmailProperty pro = EmailProperty.getInstance();
			EmailSender email = EmailSender.getInstance();
			email.FROM = pro.getFrom();
			email.MULTIPLE_RECIEPIENT = new String[1];
			String subject = "";			
			String content = "";
			
			myLogger.info("STATUS_TUKARPEGAWAI : "+STATUS_TUKARPEGAWAI);
			myLogger.info("FLAG_DAFTAR_TERUS : "+FLAG_DAFTAR_TERUS);
			myLogger.info("ID_NEGERIPEGAWAIASAL : "+ID_NEGERIPEGAWAIASAL);
			myLogger.info("ID_PEGAWAIASAL : "+ID_PEGAWAIASAL);
			myLogger.info("ID_NEGERIPEGAWAIBARU : "+ID_NEGERIPEGAWAIBARU);
			myLogger.info("ID_PEGAWAIBARU : "+ID_PEGAWAIBARU);
			myLogger.info("FLAG_MULTiPLE : "+FLAG_MULTiPLE);
			myLogger.info("SELECTED_NAMA_NEGERI : "+SELECTED_NAMA_NEGERI);
			myLogger.info("SELECTED_NAMA_PEGAWAI : "+SELECTED_NAMA_PEGAWAI);
			
			Map viewPerbicaraan = null;
			Map viewTukarPegawai = null;
			if(FLAG_MULTiPLE.equals("N"))
			{
				viewPerbicaraan = modelBI.viewPerbicaraan(session,ID_PERBICARAAN,ID_PERMOHONAN,db);
				viewTukarPegawai = modelBI.getValueColumn(session,"",ID_PERBICARAAN,"tukarpegawai",ID_PERMOHONANSIMATI,"ID_TUKARPEGAWAI",ID_TUKARPEGAWAI,"TBLPPKTUKARPEGAWAI",db);
			}
			
			myLogger.info("hantarEmelTukarPegawai >>> viewPerbicaraan : "+viewPerbicaraan);
			myLogger.info("hantarEmelTukarPegawai >>> viewTukarPegawai : "+viewTukarPegawai);
			
			
			String NO_TUKARPEGAWAI = "";
			String INFO_TARIKH_MOHON = "";
			String INFO_TARIKH_KEPUTUSAN = "";
			String INFO_ID_PEGAWAIASAL = "";
			String INFO_ID_NEGERIPEGAWAIASAL = "";
			String INFO_ID_PEGAWAIBARU = "";
			String INFO_ID_NEGERIPEGAWAIBARU = "";
			String INFO_CATATAN_PEMOHON= "";
			String INFO_CATATAN_PELULUS= "";
			String INFO_ID_PELULUS= "";
			
			if(viewTukarPegawai!=null)
			{
				NO_TUKARPEGAWAI = (String)viewTukarPegawai.get("NO_TUKARPEGAWAI");
				
				INFO_TARIKH_MOHON = (String)viewTukarPegawai.get("TARIKH_MOHON");
				INFO_TARIKH_KEPUTUSAN = (String)viewTukarPegawai.get("TARIKH_KEPUTUSAN");
				INFO_ID_PEGAWAIASAL = (String)viewTukarPegawai.get("ID_PEGAWAIASAL");
				INFO_ID_NEGERIPEGAWAIASAL = (String)viewTukarPegawai.get("ID_NEGERIPEGAWAIASAL");
				INFO_ID_PEGAWAIBARU = (String)viewTukarPegawai.get("ID_PEGAWAIBARU");
				INFO_ID_NEGERIPEGAWAIBARU = (String)viewTukarPegawai.get("ID_NEGERIPEGAWAIBARU");
				INFO_CATATAN_PEMOHON = (String)viewTukarPegawai.get("CATATAN_PEMOHON");
				INFO_CATATAN_PELULUS = (String)viewTukarPegawai.get("CATATAN_PELULUS");
				INFO_ID_PELULUS = (String)viewTukarPegawai.get("ID_PELULUS");	
						
			}
			
			if(STATUS_TUKARPEGAWAI.equals("1") && FLAG_MULTiPLE.equals("Y"))
			{
				subject += " PERMOHONAN PERTUKARAN PEGAWAI PERBICARAAN (PERMOHONAN BARU)";
			}
			else if(STATUS_TUKARPEGAWAI.equals("2") && FLAG_MULTiPLE.equals("Y"))
			{
				subject += " KEPUTUSAN PERTUKARAN PEGAWAI PERBICARAAN (LULUS)";
			}
			else if( STATUS_TUKARPEGAWAI.equals("3") && FLAG_MULTiPLE.equals("Y"))
			{
				subject += " KEPUTUSAN PERTUKARAN PEGAWAI PERBICARAAN (TOLAK)";
			}
			else if(STATUS_TUKARPEGAWAI.equals("1") && FLAG_MULTiPLE.equals("N"))
			{
				subject += " PERMOHONAN PERTUKARAN PEGAWAI PERBICARAAN (PERMOHONAN BARU) : "+NO_TUKARPEGAWAI;
			}
			else if(STATUS_TUKARPEGAWAI.equals("2") && FLAG_MULTiPLE.equals("N"))
			{
				subject += " KEPUTUSAN PERTUKARAN PEGAWAI PERBICARAAN (LULUS) : "+NO_TUKARPEGAWAI;
			}
			else if(STATUS_TUKARPEGAWAI.equals("3") && FLAG_MULTiPLE.equals("N"))
			{
				subject += " KEPUTUSAN PERTUKARAN PEGAWAI PERBICARAAN (TOLAK) : "+NO_TUKARPEGAWAI;
			}			
			//subject += " (PENGUJIAN SISTEM MYETAPP) ";
			
			if(FLAG_MULTiPLE.equals("N"))
			{
				if(STATUS_TUKARPEGAWAI.equals("1"))
				{
					content += "Permohonan baru menukar pegawai perbicaraan. ";
				}
				else if(STATUS_TUKARPEGAWAI.equals("2"))
				{
					content += "Permohonan menukar pegawai perbicaraan kepada <b>"+SELECTED_NAMA_PEGAWAI+"</b> adalah lulus. ";
				}
				else if(STATUS_TUKARPEGAWAI.equals("2"))
				{
					content += "Permohonan menukar pegawai perbicaraan kepada <b>"+SELECTED_NAMA_PEGAWAI+"</b> adalah ditolak. ";
				}
				content += "Maklumat permohonan adalah seperti dibawah : <br><br>";
				
				
				if(viewPerbicaraan!=null)
				{	
					
					String NO_FAIL = (String)viewPerbicaraan.get("NO_FAIL");
					String NAMA_SIMATI = (String)viewPerbicaraan.get("NAMA_SIMATI");
					String NAMA_PEMOHON = (String)viewPerbicaraan.get("NAMA_PEMOHON");
					String WAKTU_BICARA = (String)viewPerbicaraan.get("TARIKH_BICARA") + " " + (String)viewPerbicaraan.get("MASA_BICARA");
					String BIL_BICARA = ((Integer)viewPerbicaraan.get("BIL_BICARA")).toString();
					content += "<div style=\"border-bottom: 1px solid black;\" >MAKLUMAT PERBICARAAN</div><br>";
					content += "<table width=\"100%\" align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"1\"   > ";
					content += "<tr><td width='1%'></td><td width='28%'></td><td width='1%'></td><td width='70%'></td></tr>";
					content += modelBI.setRowText(session,"","",ID_PERMOHONANSIMATI,"","view",null,"No. Fail","","","",ID_PERBICARAAN,"NO_FAIL","","text","Y","","",NO_FAIL,0,db);
					content += modelBI.setRowText(session,"","",ID_PERMOHONANSIMATI,"","view",null,"Nama Simati","","","",ID_PERBICARAAN,"NAMA_SIMATI","","text","Y","","",NAMA_SIMATI,0,db);
					content += modelBI.setRowText(session,"","",ID_PERMOHONANSIMATI,"","view",null,"Nama Pemohon","","","",ID_PERBICARAAN,"NAMA_PEMOHON","","text","Y","","",NAMA_PEMOHON,0,db);
					content += modelBI.setRowText(session,"","",ID_PERMOHONANSIMATI,"","view",null,"Waktu Bicara","","","",ID_PERBICARAAN,"WAKTU_BICARA","","text","Y","","",WAKTU_BICARA,0,db);
					content += modelBI.setRowText(session,"","",ID_PERMOHONANSIMATI,"","view",null,"Bil. Bicara","","","",ID_PERBICARAAN,"BIL_BICARA","","text","Y","","",BIL_BICARA,0,db);
					content += "</table>";	
					content += " <br> ";
					
				}
				if(viewTukarPegawai!=null)
				{
					content += "<div style=\"border-bottom: 1px solid black;\" >MAKLUMAT PERMOHONAN TUKAR PEGAWAI PERBICARAAN</div><br>";
					
					content += "<table width=\"100%\" align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"1\"   > ";
					content += "<tr><td width='1%'></td><td width='28%'></td><td width='1%'></td><td width='70%'></td></tr>";
					content += modelBI.setRowText(session,"","",ID_PERMOHONANSIMATI,"","view",null,"No. Permohonan Tukar Pegawai","","","",ID_PERBICARAAN,"NO_TUKARPEGAWAI","","text","Y","","",NO_TUKARPEGAWAI,0,db);
					content += modelBI.setRowTextTarikh(session,"",ID_PERMOHONANSIMATI,"","view",null,"Tarikh Permohonan Tukar Pegawai","","","",ID_PERBICARAAN,"TARIKH_MOHON","Y","text","Y","10","Y",INFO_TARIKH_MOHON,0,db);
					content += modelBI.setRowSelect(session,"",ID_PERMOHONANSIMATI,"","","view",null,"Pegawai Asal","","","",ID_PERBICARAAN,"ID_PEGAWAIASAL","Y","select","Y","TBLPPKRUJUNIT","ID_UNITPSK","","NAMA_PEGAWAI","","","","","","","","","","",INFO_ID_PEGAWAIASAL,0,db);
					content += modelBI.setRowSelect(session,"",ID_PERMOHONANSIMATI,"","","view",null,"Negeri Pegawai Ganti","","","",ID_PERBICARAAN,"ID_NEGERIPEGAWAIBARU","Y","select","Y","TBLRUJNEGERI","ID_NEGERI","KOD_NEGERI","NAMA_NEGERI","","","","","","","","","","",INFO_ID_NEGERIPEGAWAIBARU,0,db);//dynamic ajax call
					content += modelBI.setRowSelect(session,"",ID_PERMOHONANSIMATI,"","","view",null,"Pegawai Ganti","","","",ID_PERBICARAAN,"ID_PEGAWAIBARU","Y","select","Y","TBLPPKRUJUNIT","ID_UNITPSK","","NAMA_PEGAWAI","","","","","","","","","","",INFO_ID_PEGAWAIBARU,0,db);
					content += modelBI.setRowSelect(session,"",ID_PERMOHONANSIMATI,"","","view",null,"Status Tukar Pegawai","","","",ID_PERBICARAAN,"STATUS_TUKARPEGAWAI","Y","select","Y","","","","","","","","","","","","","","",STATUS_TUKARPEGAWAI,0,db);
					if(!INFO_CATATAN_PEMOHON.equals(""))
					{
						content += modelBI.setRowTextArea(session,"",ID_PERMOHONANSIMATI,"","view",null,"Catatan Pemohon","","","",ID_PERBICARAAN,"CATATAN_PEMOHON","","text","Y","4000","Y",INFO_CATATAN_PEMOHON,0,db);
					}
					if(!INFO_ID_PELULUS.equals(""))
					{
						content += modelBI.setRowSelect(session,"",ID_PERMOHONANSIMATI,"","","view",null,"Pegawai Pelulus","","","",ID_PERBICARAAN,"ID_PELULUS","Y","select","Y","USERS","USER_ID","","USER_NAME","","","","","","","","","","",INFO_ID_PELULUS,0,db);//dynamic ajax call
					}
					if(!INFO_TARIKH_KEPUTUSAN.equals(""))
					{
						content += modelBI.setRowTextTarikh(session,"",ID_PERMOHONANSIMATI,"","view",null,"Tarikh Kelulusan Tukar Pegawai","","","",ID_PERBICARAAN,"TARIKH_KEPUTUSAN","Y","text","Y","10","Y",INFO_TARIKH_KEPUTUSAN,0,db);
					}
					if(!INFO_CATATAN_PELULUS.equals(""))
					{
						content += modelBI.setRowTextArea(session,"",ID_PERMOHONANSIMATI,"","view",null,"Catatan Pelulus","","","",ID_PERBICARAAN,"CATATAN_PELULUS","","text","Y","4000","Y",INFO_CATATAN_PELULUS,0,db);
					}
					
					content += "</table>";
				}
							
			}
			else if(FLAG_MULTiPLE.equals("Y"))
			{
				if(senaraiSelected.size() > 0 )
				{
					if(STATUS_TUKARPEGAWAI.equals("1"))
					{
						content += "Permohonan menukar pegawai perbicaraan kepada <b>"+SELECTED_NAMA_PEGAWAI+"</b>. ";
					}
					else if(STATUS_TUKARPEGAWAI.equals("2"))
					{
						content += "Permohonan menukar pegawai perbicaraan kepada <b>"+SELECTED_NAMA_PEGAWAI+"</b> adalah lulus. ";
					}
					else if(STATUS_TUKARPEGAWAI.equals("3"))
					{
						content += "Permohonan menukar pegawai perbicaraan adalah ditolak. ";
					}
					content += "Senarai perbicaraan yang terlibat adalah seperti dibawah : <br><br>";
					
					content += "<table width=\"100%\" align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"1\"   > ";
					content += "<tr>" +
							"<td valign=\"top\" align=\"center\" width=\"5%\" style=\"border: 1px solid black;\" ><b>No.</b></td>" +
							"<td valign=\"top\" align=\"left\"  style=\"border: 1px solid black;\"><b>No Fail (Bil. Bicara)</b></td>" +
							"<td valign=\"top\" align=\"center\"  style=\"border: 1px solid black;\"><b>Tarikh Bicara</b></td>" +
							"<td valign=\"top\" align=\"left\"  style=\"border: 1px solid black;\"><b>Masa Bicara</b></td>" +
							"<td valign=\"top\" align=\"left\" style=\"border: 1px solid black;\"><b>Pegawai Asal</b></td>" +
							"<td valign=\"top\" align=\"left\"  style=\"border: 1px solid black;\"><b>Pegawai Ganti</b></td>" +
							"</tr> ";
					
					for (int i = 0; i < senaraiSelected.size(); i++) 
					{			
						Map itemSelected = (Map) senaraiSelected.get(i);
						String NO = (String) itemSelected.get("NO");
						String NO_FAIL = (String) itemSelected.get("NO_FAIL");
						String TARIKH_BICARA = (String) itemSelected.get("TARIKH_BICARA");
						String MASA_BICARA = (String) itemSelected.get("MASA_BICARA");
						String BIL_BICARA = (String) itemSelected.get("BIL_BICARA");
						String PEG_PENGENDALI = (String) itemSelected.get("PEG_PENGENDALI");
						String NAMA_PEGAWAI_BARU = (String) itemSelected.get("NAMA_PEGAWAI_BARU");
						
						content += "<tr>" +
								"<td valign=\"top\" align=\"center\" style=\"border: 1px solid black;\">"+NO+"</td>" +
								"<td valign=\"top\" align=\"left\" style=\"border: 1px solid black;\">"+NO_FAIL+" ("+BIL_BICARA+")</td>" +
								"<td valign=\"top\" align=\"center\" style=\"border: 1px solid black;\">"+TARIKH_BICARA +"</td>" +
								"<td valign=\"top\" align=\"left\" style=\"border: 1px solid black;\">"+MASA_BICARA+"</td>" +
								"<td valign=\"top\" align=\"left\" style=\"border: 1px solid black;\">"+PEG_PENGENDALI+"</td>";
						
						if(STATUS_TUKARPEGAWAI.equals("1"))
						{
							content += "<td valign=\"top\" align=\"left\" style=\"border: 1px solid black;\">"+SELECTED_NAMA_PEGAWAI+"</td>";
						}
						else if(STATUS_TUKARPEGAWAI.equals("2") || STATUS_TUKARPEGAWAI.equals("3"))
						{
							content += "<td valign=\"top\" align=\"left\" style=\"border: 1px solid black;\">"+NAMA_PEGAWAI_BARU+"</td>";
						}
						
						content += "</tr> ";
						
					}
					content += "</table>";
				}
			}
			
			
			
			content += "<br><br>Sila semak permohonan ini pada skrin 'Bicara Interaktif' untuk tindakan lanjut. Sekian, terima kasih.";
			content += "<br><br>Nota : Emel ini dijana oleh Sistem MyeTaPP dan tidak perlu dibalas.";
			
			
			List collectionSendTo = Collections.synchronizedList(new ArrayList());
			//Map h_collectionSendTo = Collections.synchronizedMap(new HashMap());
			//h_collectionSendTo.put("EMEL","etappsupport@jkptg.gov.my");
			collectionSendTo.add(modelBI.setHashEmel("etappsupport@jkptg.gov.my"));
			
			//capture reciepient			
			if(FLAG_MULTiPLE.equals("N"))
			{
				//single
				if(FLAG_DAFTAR_TERUS.equals("Y"))
				{
					if(STATUS_TUKARPEGAWAI.equals("2"))
					{
						//inform pegawai asal
						//myLogger.info("situation 1 >> INFO_ID_PEGAWAIBARU : "+INFO_ID_PEGAWAIBARU);
						//myLogger.info("situation 1 >> INFO_ID_PEGAWAIASAL : "+INFO_ID_PEGAWAIASAL);						
						//myLogger.info("situation 1 >>  emel pegawai baru : "+emelPegawaiPusaka(session,INFO_ID_PEGAWAIBARU,db));
						//myLogger.info("situation 1 >>  emel pegawai asal : "+emelPegawaiPusaka(session,INFO_ID_PEGAWAIASAL,db));
						String emelPegawaiBaru = modelBI.emelPegawaiPusaka(session,INFO_ID_PEGAWAIBARU,db);
						String emelPegawaiAsal = modelBI.emelPegawaiPusaka(session,INFO_ID_PEGAWAIASAL,db);
						if(!emelPegawaiBaru.equals(""))
						{
							collectionSendTo.add(modelBI.setHashEmel(emelPegawaiBaru));					
						}
						if(!emelPegawaiAsal.equals(""))
						{
							collectionSendTo.add(modelBI.setHashEmel(emelPegawaiAsal));					
						}
						
					}
				}
				else
				{
					if(STATUS_TUKARPEGAWAI.equals("1"))
					{
						myLogger.info("situation 2 >> INFO_ID_NEGERIPEGAWAIASAL : "+INFO_ID_NEGERIPEGAWAIASAL);
						myLogger.info("situation 2 >> INFO_ID_NEGERIPEGAWAIBARU : "+INFO_ID_NEGERIPEGAWAIBARU);						
						if(INFO_ID_NEGERIPEGAWAIASAL.equals(INFO_ID_NEGERIPEGAWAIBARU))
						{
							//kalo tidak melangkau negeri, inform kkp negeri
							List listKPP = modelBI.listKPP(session,INFO_ID_NEGERIPEGAWAIBARU,"NEGERI",db);
							if(listKPP.size() > 0)
							{
								//email.MULTIPLE_RECIEPIENT = new String[listKPP.size()];
								for(int i = 0; i < listKPP.size();i++)
								{
									Map m = (Map) listKPP.get(i);
									String EMEL = (String) m.get("EMEL");
									//email.MULTIPLE_RECIEPIENT[i] = EMEL;	
									if(!EMEL.equals(""))
									{
										collectionSendTo.add(modelBI.setHashEmel(EMEL));	
									}
								}
							}
						}
						else
						{
							//kalo melangkau negeri, inform kkp hq & pengarah
							List listKPP = modelBI.listKPP(session,"","HQ",db);
							if(listKPP.size() > 0)
							{
								//email.MULTIPLE_RECIEPIENT = new String[listKPP.size()];
								for(int i = 0; i < listKPP.size();i++)
								{
									Map m = (Map) listKPP.get(i);
									String EMEL = (String) m.get("EMEL");
									//email.MULTIPLE_RECIEPIENT[i] = EMEL;	
									if(!EMEL.equals(""))
									{
										collectionSendTo.add(modelBI.setHashEmel(EMEL));	
									}
								}
							}
						}
						
						String emelPegawaiBaru = modelBI.emelPegawaiPusaka(session,INFO_ID_PEGAWAIBARU,db);
						if(!emelPegawaiBaru.equals(""))
						{
							collectionSendTo.add(modelBI.setHashEmel(emelPegawaiBaru));					
						}
						
					}
					else if(STATUS_TUKARPEGAWAI.equals("2") || STATUS_TUKARPEGAWAI.equals("3"))
					{
						//inform pegawai asal & asal
						//myLogger.info("situation 3 >> INFO_ID_PEGAWAIBARU : "+INFO_ID_PEGAWAIBARU);
						//myLogger.info("situation 3 >> INFO_ID_PEGAWAIASAL : "+INFO_ID_PEGAWAIASAL);
						//myLogger.info("situation 3 >>  emel pegawai baru : "+emelPegawaiPusaka(session,INFO_ID_PEGAWAIBARU,db));
						//myLogger.info("situation 3 >>  emel pegawai asal : "+emelPegawaiPusaka(session,INFO_ID_PEGAWAIASAL,db));
						String emelPegawaiBaru = modelBI.emelPegawaiPusaka(session,INFO_ID_PEGAWAIBARU,db);
						String emelPegawaiAsal = modelBI.emelPegawaiPusaka(session,INFO_ID_PEGAWAIASAL,db);
						if(!emelPegawaiBaru.equals(""))
						{
							collectionSendTo.add(modelBI.setHashEmel(emelPegawaiBaru));					
						}
						if(!emelPegawaiAsal.equals(""))
						{
							collectionSendTo.add(modelBI.setHashEmel(emelPegawaiAsal));					
						}
					}
					
				}
			}
			else if(FLAG_MULTiPLE.equals("Y"))
			{
				//multiple
				if(FLAG_DAFTAR_TERUS.equals("Y"))
				{
					if(STATUS_TUKARPEGAWAI.equals("2"))
					{
						//myLogger.info("situation 4 >> ID_PEGAWAIBARU : "+ID_PEGAWAIBARU);
						//myLogger.info("situation 4 >> ID_PEGAWAIASAL : "+ID_PEGAWAIASAL);
						//inform pegawai baru
						//myLogger.info("situation 4 >>  emel pegawai baru : "+emelPegawaiPusaka(session,ID_PEGAWAIBARU,db));
						//myLogger.info("situation 4 >>  emel pegawai asal : "+emelPegawaiPusaka(session,ID_PEGAWAIASAL,db));
						String emelPegawaiBaru = modelBI.emelPegawaiPusaka(session,ID_PEGAWAIBARU,db);
						String emelPegawaiAsal = modelBI.emelPegawaiPusaka(session,ID_PEGAWAIASAL,db);
						if(!emelPegawaiBaru.equals(""))
						{
							collectionSendTo.add(modelBI.setHashEmel(emelPegawaiBaru));					
						}
						if(!emelPegawaiAsal.equals(""))
						{
							collectionSendTo.add(modelBI.setHashEmel(emelPegawaiAsal));					
						}
					}
				}
				else
				{
					if(STATUS_TUKARPEGAWAI.equals("1"))
					{
						//kalo tidak melangkau negeri, inform kkp negeri
						//kalo melangkau negeri, inform kkp hq & pengarah
						myLogger.info("situation 5 >> ID_NEGERIPEGAWAIASAL : "+ID_NEGERIPEGAWAIASAL);
						myLogger.info("situation 5 >> ID_NEGERIPEGAWAIBARU : "+ID_NEGERIPEGAWAIBARU);
						
						if(ID_NEGERIPEGAWAIASAL.equals(ID_NEGERIPEGAWAIBARU))
						{
							//kalo tidak melangkau negeri, inform kkp negeri
							List listKPP = modelBI.listKPP(session,ID_NEGERIPEGAWAIBARU,"NEGERI",db);
							if(listKPP.size() > 0)
							{
								for(int i = 0; i < listKPP.size();i++)
								{
									Map m = (Map) listKPP.get(i);
									String EMEL = (String) m.get("EMEL");
									if(!EMEL.equals(""))
									{
										collectionSendTo.add(modelBI.setHashEmel(EMEL));	
									}
								}
							}
						}
						else
						{
							//kalo melangkau negeri, inform kkp hq & pengarah
							List listKPP = modelBI.listKPP(session,"","HQ",db);
							if(listKPP.size() > 0)
							{
								for(int i = 0; i < listKPP.size();i++)
								{
									Map m = (Map) listKPP.get(i);
									String EMEL = (String) m.get("EMEL");
									if(!EMEL.equals(""))
									{
										collectionSendTo.add(modelBI.setHashEmel(EMEL));	
									}
								}
							}
						}	
						
						String emelPegawaiBaru = modelBI.emelPegawaiPusaka(session,ID_PEGAWAIBARU,db);
						if(!emelPegawaiBaru.equals(""))
						{
							collectionSendTo.add(modelBI.setHashEmel(emelPegawaiBaru));					
						}
						
					}
					else if(STATUS_TUKARPEGAWAI.equals("2") || STATUS_TUKARPEGAWAI.equals("3"))
					{
						//inform pegawai asal
						//myLogger.info("situation 6 >> ID_PEGAWAIBARU : "+ID_PEGAWAIBARU);
						//myLogger.info("situation 6 >> ID_PEGAWAIASAL : "+ID_PEGAWAIASAL);
						//myLogger.info("situation 6 >>  emel pegawai baru : "+emelPegawaiPusaka(session,ID_PEGAWAIBARU,db));
						//myLogger.info("situation 6 >>  emel pegawai asal : "+emelPegawaiPusaka(session,ID_PEGAWAIASAL,db));
						String emelPegawaiBaru = modelBI.emelPegawaiPusaka(session,ID_PEGAWAIBARU,db);
						String emelPegawaiAsal = modelBI.emelPegawaiPusaka(session,ID_PEGAWAIASAL,db);
						if(!emelPegawaiBaru.equals(""))
						{
							collectionSendTo.add(modelBI.setHashEmel(emelPegawaiBaru));					
						}
						if(!emelPegawaiAsal.equals(""))
						{
							collectionSendTo.add(modelBI.setHashEmel(emelPegawaiAsal));					
						}
					}					
				}
			}			
			email.MULTIPLE_RECIEPIENT = new String[collectionSendTo.size()];
			for(int i = 0; i < collectionSendTo.size();i++)
			{
				Map m = (Map) collectionSendTo.get(i);
				String EMEL = (String) m.get("EMEL");
				myLogger.info(">>>>>>>>>>>>>>>>>>> SENARAI PENERIMA EMEL "+EMEL);
				//SEMENTARA
				email.MULTIPLE_RECIEPIENT[i] = EMEL;
			}			
			
			email.TO_CC = new String[1]; 
			email.TO_CC[0] = "etappsupport@jkptg.gov.my"; 
						
			email.SUBJECT = subject;
			email.MESSAGE = content;
			//sementara
			email.sendEmail();		
		 }
}


/*
 * List fail tukar pegawai 2:
 * 1. ekptg.view.ppk.FrmTukarPegawaiBI2.java
 * 2. app/ppk/TukarPegawai2/FrmPrmhnnTukarPegawai2.jsp
 * 3. app/ppk/TukarPegawai2/historyTukarPegawai2.jsp
 * 4. app/ppk/TukarPegawai2/listTukarPegawai2.jsp
 * 5. ekptg.model.ppk.FrmTukarPegawaiBI2Data.java
 * */