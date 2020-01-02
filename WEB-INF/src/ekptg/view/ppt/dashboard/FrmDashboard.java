/**
 * 
 */
package ekptg.view.ppt.dashboard;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;
/*
import ekptg.model.ppt.FrmPembatalanInternalData;
import ekptg.model.ppt.FrmPermohonanUPTOnlineData;
import ekptg.model.ppt.MyInfoPPTData;
import ekptg.view.admin.Pengumuman;
*/
public class FrmDashboard extends AjaxBasedModule {
	static Logger myLogger = Logger.getLogger(FrmDashboard.class);
	String userId = null;
	String role = null;
	String user_negeri_login = null;
	//Pengumuman logic_pengumuman = new Pengumuman();
	//FrmPembatalanInternalData logic1 = new FrmPembatalanInternalData();
	//FrmPermohonanUPTOnlineData modelOnline = new FrmPermohonanUPTOnlineData();
	//MyInfoPPTData logic = new MyInfoPPTData();
	List listAgihan = null;
	List listFail = null;
	List listHakmilik = null;
	List listEndorsanK = null;
	List listPB = null;
	List listFailCaj= null; //penambahan yati
	List listFailSiasatan = null; //penambahan yati
	List listHakmilikSiasatan = null; //penambahan yati


	@Override
	public String doTemplate2() throws Exception {
		HttpSession session = this.request.getSession();
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String portal_role = (String) session.getAttribute("myrole");
		context.put("portal_role", portal_role);
		String command = getParam("command");
		myLogger.info("-----------------command :" + command);
		//System.out.println("---command :+command");

		
		Db db = null;
		try {
		db = new Db();
		
		Hashtable get_stat = null;
		Hashtable get_stat_bar = null;
		Vector list_memo_aktif = null;
		Hashtable notifikasi = null;

		Vector listing_online_penarikan = null;
		Vector listing_online_pembatalan = null;
		Vector listing_online_sek4 = null;
		Vector listing_online_sek8 = null;
		Vector listing_online_sementara = null;
		Vector listing_online_bantahan_pb = null;
		Vector listing_online_bantahan_agensi = null;

		listAgihan = Collections.synchronizedList(new ArrayList());

		Integer count_online_penarikan = 0;
		Integer count_online_pembatalan = 0;
		Integer count_online_sek4 = 0;
		Integer count_online_sek8 = 0;
		Integer count_online_sementara = 0;
		Integer count_online_bantahan_pb = 0;
		Integer count_online_bantahan_ag = 0;

		Vector listFailExpired = new Vector();
		Vector listPenarikanExpired = new Vector();
		Vector listPembatalanExpired = new Vector();

		listPembatalanExpired.clear();
		listPenarikanExpired.clear();
		listFailExpired.clear();

		role = (String) session.getAttribute("myrole");
		userId = (String) session.getAttribute("_ekptg_user_id");
		user_negeri_login = (String) session.getAttribute("_ekptg_user_negeri");
		context.put("user_negeri_login",user_negeri_login);

		myLogger.info("user_negeri_login-- "+user_negeri_login);
		

		
		
		notifikasi = notifikasi(role, user_negeri_login, "", "", userId, "NO",db);
		context.put("check_notifikasi_aduan", notifikasi.get("JUMLAH_ADUAN"));
		context.put("check_notifikasi_inbox", notifikasi.get("INBOX"));
		/*
		 * count_online_bantahan_ag = count_online_bantahan_ag(session,db);
		 * this.context.put("count_online_bantahan_ag",
		 * count_online_bantahan_ag);
		 * 
		 * count_online_bantahan_pb = count_online_bantahan_pb(session,db);
		 * this.context.put("count_online_bantahan_pb",
		 * count_online_bantahan_pb);
		 * 
		 * count_online_penarikan = count_online_penarikan(session,db);
		 * this.context.put("count_online_penarikan", count_online_penarikan);
		 * 
		 * count_online_pembatalan = count_online_pembatalan(session,db);
		 * this.context.put("count_online_pembatalan",count_online_pembatalan);
		 * 
		 * count_online_sek4 = count_online_sek4(session,db);
		 * this.context.put("count_online_sek4", count_online_sek4);
		 * 
		 * count_online_sek8 = count_online_sek8(session,db);
		 * this.context.put("count_online_sek8",count_online_sek8);
		 * 
		 * count_online_sementara = count_online_sementara(session,db);
		 * this.context.put("count_online_sementara", count_online_sementara);
		 */
		
		if (command.equals("getMainStat")) {
		get_stat = stat_fail_ppt(session, role, userId,db);
		String negeri_sever = (String) get_stat.get("NAMA_NEGERI");
		String sek4 = (String) get_stat.get("SEK_4");
		String sek8 = (String) get_stat.get("SEK_8");
		String sekSementara = (String) get_stat.get("SEK_SEMENTARA");
		Integer jumlah_keseluruhan = Integer.parseInt(sek4)
				+ Integer.parseInt(sek8) + Integer.parseInt(sekSementara);
		// String fail_hapus = (String)get_stat.get("JUMLAH_FAIL_HAPUS");
		// String fail_selesai = (String)get_stat.get("JUMLAH_SELESAI");
		// String fail_xselesai = (String)get_stat.get("JUMLAH_XSELESAI");
		context.put("negeri_sever", negeri_sever);
		context.put("jumlah_keseluruhan", jumlah_keseluruhan);
		context.put("sek4", sek4);
		context.put("sek8", sek8);
		context.put("sekSementara", sekSementara);
		// context.put("fail_hapus", fail_hapus);
		// context.put("fail_selesai", fail_selesai);
		// context.put("fail_xselesai", fail_xselesai);
		// carianFail(session,"dashboard","");
		return "app/ppt/dashboard/div_MainStats.jsp";
		}
		
		else if(command.equals("getStatsFailHakmilik"))
		{
			get_stat_bar = stat_bar(session, role, userId,db);
			String DITERIMA = (String) get_stat_bar.get("DITERIMA");
			String DISELESAI = (String) get_stat_bar.get("DISELESAI");
			String LOT_DITERIMA = (String) get_stat_bar.get("LOT_DITERIMA");
			String LOT_DISELESAI = (String) get_stat_bar.get("LOT_DISELESAI");

			context.put("DITERIMA", DITERIMA);
			context.put("DISELESAI", DISELESAI);
			context.put("LOT_DITERIMA", LOT_DITERIMA);
			context.put("LOT_DISELESAI", LOT_DISELESAI);

			return "app/ppt/dashboard/div_cartaMain.jsp";
		}
		else if(command.equals("getLewatEndorsanK"))
		{
			context.put("getListEndorsan", 1);
			listHakmilik = ListHakmilik(session, "endorsanK", "",db);
			this.context.put("listHakmilik", listHakmilik);
			return "app/ppt/dashboard/div_endorsanLewat.jsp";
		} //penambahan yati
		else if(command.equals("getLewatCaj"))
		{
			context.put("getListCaj", 1);
			listFailCaj = ListFailCaj(session, "cajLewat", "",db);
			this.context.put("listFailCaj", listFailCaj);
			return "app/ppt/dashboard/div_cajLewat.jsp";
			
		}
		else if (command.equals("getTabDashboard")) {
			return getTabDashboard(session, role, user_negeri_login, "", "",
					userId, "NO", "1", "1",db);
		} else if (command.equals("getMainOnline")) {
			return getMainOnline(session,db);
		} else if (command.equals("getAgihan")) {
			return getListAgihan(session,db);
		} else if (command.equals("getOnline4")) {
			
			return getOnline4(session,db);
		} else if (command.equals("getOnline4_close")) {
		
			return getOnline4_close(session,db);
		} else if (command.equals("getOnline8")) {
			return getOnline8(session,db);
		} else if (command.equals("getOnline8_close")) {
			return getOnline8_close(session,db);
		} else if (command.equals("getOnlineSementara")) {
			return getOnlineSementara(session,db);
		} else if (command.equals("getOnlineSementara_close")) {
			return getOnlineSementara_close(session,db);
		} else if (command.equals("getOnlineBantahanPB")) {
			return getOnlineBantahanPB(session,db);
		} else if (command.equals("getOnlineBantahanPB_close")) {
			return getOnlineBantahanPB_close(session,db);
		} else if (command.equals("getOnlineBantahanAG")) {
			return getOnlineBantahanAG(session,db);
		} else if (command.equals("getOnlineBantahanAG_close")) {
			return getOnlineBantahanAG_close(session,db);
		} else if (command.equals("getOnlinePenarikan")) {
			return getOnlinePenarikan(session,db);
		} else if (command.equals("getOnlinePenarikan_close")) {
			return getOnlinePenarikan_close(session,db);
		} else if (command.equals("getOnlinePembatalan")) {
			return getOnlinePembatalan(session,db);
		} else if (command.equals("getOnlinePembatalan_close")) {
			return getOnlinePembatalan_close(session,db);
		}

		else if (command.equals("getAgihan1")) {
			return getAgihan1(session,db);
		} else if (command.equals("getAgihan1_close")) {
			return getAgihan1_close(session,db);
		} else if (command.equals("getAgihan2")) {
			return getAgihan2(session,db);
		} else if (command.equals("getAgihan2_close")) {
			return getAgihan2_close(session,db);
		} else if (command.equals("getAgihan3")) {
			return getAgihan3(session,db);
		} else if (command.equals("getAgihan3_close")) {
			return getAgihan3_close(session,db);
		} else if (command.equals("getAgihan4")) {
			return getAgihan4(session,db);
		} else if (command.equals("getAgihan4_close")) {
			return getAgihan4_close(session,db);
		} else if (command.equals("getAgihan5")) {
			return getAgihan5(session,db);
		} else if (command.equals("getAgihan5_close")) {
			return getAgihan5_close(session,db);
		} else if (command.equals("getAgihan6")) {
			return getAgihan6(session,db);
		} else if (command.equals("getAgihan6_close")) {
			return getAgihan6_close(session,db);
		} else if (command.equals("getAgihan7")) {
			return getAgihan7(session,db);
		} else if (command.equals("getAgihan7_close")) {
			return getAgihan7_close(session,db);
		} else if (command.equals("getAgihan8")) {
			return getAgihan8(session,db);
		} else if (command.equals("getAgihan8_close")) {
			return getAgihan8_close(session,db);
		} else if (command.equals("getAgihan9")) {
			return getAgihan9(session,db);
		} else if (command.equals("getAgihan9_close")) {
			return getAgihan9_close(session,db);
		}else if (command.equals("getAgihan10")) {
				return getAgihan10(session,db);
		} else if (command.equals("getAgihan10_close")) {
				return getAgihan10_close(session,db);
		}
		/*
		 * else if(command.equals("getCarianMain")) { return
		 * getCarianMain(session,db); }
		 */
		else if (command.equals("carianFail")) {
			return carianFail(session,db);
		}
		else if (command.equals("carianFailCaj")) {
			return carianFailCaj(session,db);
		}
		 else if (command.equals("carianHakmilik")) {
			return carianHakmilik(session,db);
		} else if (command.equals("carianPB")) {
			return carianPB(session,db);
		} else if (command.equals("carianFail_close")) {
			return carianFail_close(session,db);
		} else if (command.equals("carianHakmilik_close")) {
			return carianHakmilik_close(session,db);
		} else if (command.equals("carianPB_close")) {
			return carianPB_close(session,db);
		}
		
		else if (command.equals("carianFailSiasatan")) { //penamnbahan yati
			return carianFailSiasatan(session,db);
		}
		else if (command.equals("carianHakmilikSiasatan")) {
			return carianHakmilikSiasatan(session,db);
		}
		 else if (command.equals("carianFailSiasatan_close")) {
			return carianFailSiasatan_close(session,db);
		} else if (command.equals("carianHakmilikSiasatan_close")) {
			return carianHakmilikSiasatan_close(session,db);
		}
		else if (command.equals("getListFail")) {
			return getListFail(session,db);
		} else if (command.equals("getListFail_close")) {
			return getListFail_close(session,db);
		}

		else if (command.equals("getListHakmilik")) {
			return getListHakmilik(session,db);
		}
		else if (command.equals("getListHakmilik_close")) {
			return getListHakmilik_close(session,db);
		}

		else if (command.equals("getListEndorsanK")) {
			
			return getListEndorsanK(session,db);
		}

		else if (command.equals("getListEndorsanK_close")) {
			
			return getListEndorsanK_close(session,db);
		} //penambahan yati
		
		else if (command.equals("getListCaj")) {
			return getListCaj(session,db);
		}
		else if (command.equals("getListCaj_close")) {
			return getListCaj_close(session,db);
		} //tutup penambahan yati
		
		else if (command.equals("getListPB")) {
			return getListPB(session,db);
		} else if (command.equals("getListPB_close")) {
			return getListPB_close(session,db);
		}
		//penambahan yati
		else if (command.equals("getListFailSiasatan")) {
			return getListFailSiasatan(session,db);
		} else if (command.equals("getListFailSiasatan_close")) {
			return getListFailSiasatan_close(session,db);
		}
		else if (command.equals("getListHakmilikSiasatan")) {
			return getListHakmilikSiasatan(session,db);
		}
		else if (command.equals("getListHakmilikSiasatan_close")) {
			return getListHakmilikSiasatan_close(session,db);
		}

		context.put("defaultTab", "0");
		list_memo_aktif = getMemo("", "Aktif", "1", "0",db);
		context.put("list_memo_aktif", list_memo_aktif);
		
		}
		finally {			
		if (db != null)
		db.close();
		}

		String vm = "app/ppt/dashboard/dashboard.jsp";

		return vm;
	}
	
	Vector list_memo = null;
    public Vector getMemo(String id_memo,String status_mesej, String internalType, String onlineType,Db db) throws Exception {
          //updatePapar();
          list_memo = new Vector();
          list_memo.clear();
        //  Db db = null;
          String sql = "";
          String sql_update = "";
          try {
              //  db = new Db();
                Statement stmt = db.getStatement();
                SQLRenderer r = new SQLRenderer();
                
          sql = " SELECT A.ID_MEMO," +
          " A.MESEJ," +
          // "X.TXT AS MESEJ," +
          "A.STATUS_MESEJ," +
          "A.SUBMESEJ," +
          "A.FLAG_MESEJ," +
          "A.FLAG_ROLE_ONLINE," +
          "A.FLAG_ROLE_INTERNAL," +
          " A.TURUTAN_MESEJ," +
          
          " TO_CHAR(A.TARIKH_MULA_PAPAR,'dd/mm/yyyy') AS TARIKH_MULA_PAPAR_DATE," +
          " TO_CHAR(A.TARIKH_MULA_PAPAR,'hh') AS TARIKH_MULA_PAPAR_HOUR," +
          " TO_CHAR(A.TARIKH_MULA_PAPAR,'mi') AS TARIKH_MULA_PAPAR_MINUTE," +
          " TO_CHAR(A.TARIKH_MULA_PAPAR,'am') AS TARIKH_MULA_PAPAR_AMPM," +             
          " TO_CHAR(A.TARIKH_MULA_PAPAR,'dd/mm/yyyy hh:mi am') AS TARIKH_MULA_PAPAR," + 
          
          " TO_CHAR(A.TARIKH_AKHIR_PAPAR,'dd/mm/yyyy') AS TARIKH_AKHIR_PAPAR_DATE," +
          " TO_CHAR(A.TARIKH_AKHIR_PAPAR,'hh') AS TARIKH_AKHIR_PAPAR_HOUR," +
          " TO_CHAR(A.TARIKH_AKHIR_PAPAR,'mi') AS TARIKH_AKHIR_PAPAR_MINUTE," +
          " TO_CHAR(A.TARIKH_AKHIR_PAPAR,'am') AS TARIKH_AKHIR_PAPAR_AMPM," +
          " TO_CHAR(A.TARIKH_AKHIR_PAPAR,'dd/mm/yyyy hh:mi am') AS TARIKH_AKHIR_PAPAR " +      
          
          //" FROM TBLMEMO A,XTBLMEMO X WHERE A.ID_MEMO = X.IDX AND A.ID_MEMO IS NOT NULL ";
          " FROM TBLMEMO A WHERE  A.ID_MEMO IS NOT NULL ";
                
          if(!id_memo.equals("") && !id_memo.equals(null))
          {
                sql += " AND A.ID_MEMO = '"+id_memo+"'";  
          }
          
          if(!internalType.equals("") && !internalType.equals(null) && !internalType.equals("0"))
          {
                sql += " AND A.FLAG_ROLE_INTERNAL = '"+internalType+"'";
          }

          if(!onlineType.equals("") && !onlineType.equals(null) && !onlineType.equals("0"))
          {
                sql += " AND A.FLAG_ROLE_ONLINE = '"+onlineType+"'";
          }
          
          if(!status_mesej.equals("") && !status_mesej.equals(null))
          {
                sql += " AND A.STATUS_MESEJ= '"+status_mesej+"'";     
          }
                sql +=  " ORDER BY A.TURUTAN_MESEJ ASC";              
                //myLogger.info("LIST MEMO :"+sql);
                int bil = 0;
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                      bil = bil + 1;
                      Hashtable h = new Hashtable();
                      h.put("BIL", bil);
                      h.put("id_memo", rs.getString("ID_MEMO"));                  
                      
                      if (rs.getString("MESEJ") == null) {
                            h.put("mesej", "");
                      } else {
                            h.put("mesej", rs.getString("MESEJ"));
                      }
                      
                      if (rs.getString("SUBMESEJ") == null) {
                            h.put("submesej", "");
                      } else {
                            h.put("submesej", rs.getString("submesej"));
                      }
                      
                      if (rs.getString("FLAG_MESEJ") == null) {
                            h.put("flag_mesej", "");
                      } else {
                            h.put("flag_mesej", rs.getString("flag_mesej"));
                      }
                      
                      if (rs.getString("TURUTAN_MESEJ") == null) {
                            h.put("turutan_mesej", "");
                      } else {
                            h.put("turutan_mesej", rs.getString("TURUTAN_MESEJ"));
                      }
                      
                      if (rs.getString("STATUS_MESEJ") == null) {
                            h.put("status_mesej", "");
                      } else {
                            h.put("status_mesej", rs.getString("STATUS_MESEJ"));
                      }
                      
                      if (rs.getString("FLAG_ROLE_INTERNAL") == null) {
                          h.put("flag_role_internal", "");
                      } else {
                          h.put("flag_role_internal", rs.getString("FLAG_ROLE_INTERNAL"));
                      }
                      
                      if (rs.getString("FLAG_ROLE_ONLINE") == null) {
                          h.put("flag_role_online", "");
                      } else {
                          h.put("flag_role_online", rs.getString("FLAG_ROLE_ONLINE"));
                      }
                      
                      if (rs.getString("TARIKH_MULA_PAPAR") == null) {
                            h.put("tarikh_mula_papar_full", "");
                      } else {
                            h.put("tarikh_mula_papar_full", rs.getString("TARIKH_MULA_PAPAR"));
                      }
                      
                      if (rs.getString("TARIKH_MULA_PAPAR_DATE") == null) {
                            h.put("tarikh_mula_papar", "");
                      } else {
                            h.put("tarikh_mula_papar", rs.getString("TARIKH_MULA_PAPAR_DATE"));
                      }
                      
                      if (rs.getString("TARIKH_MULA_PAPAR_HOUR") == null) {
                            h.put("tarikh_mula_papar_hour", "");
                      } else {
                            h.put("tarikh_mula_papar_hour", rs.getString("TARIKH_MULA_PAPAR_HOUR"));
                      }
                      
                      if (rs.getString("TARIKH_MULA_PAPAR_MINUTE") == null) {
                            h.put("tarikh_mula_papar_minute", "");
                      } else {
                            h.put("tarikh_mula_papar_minute", rs.getString("TARIKH_MULA_PAPAR_MINUTE"));
                      }
                      
                      if (rs.getString("TARIKH_MULA_PAPAR_AMPM") == null) {
                            h.put("tarikh_mula_papar_ampm", "");
                      } else {
                            h.put("tarikh_mula_papar_ampm", rs.getString("TARIKH_MULA_PAPAR_AMPM").toUpperCase());
                      }
                      
                      if (rs.getString("TARIKH_AKHIR_PAPAR") == null) {
                            h.put("tarikh_akhir_papar_full", "");
                      } else {
                            h.put("tarikh_akhir_papar_full", rs.getString("TARIKH_AKHIR_PAPAR"));
                      }
                      
                      if (rs.getString("TARIKH_AKHIR_PAPAR_DATE") == null) {
                            h.put("tarikh_akhir_papar", "");
                      } else {
                            h.put("tarikh_akhir_papar", rs.getString("TARIKH_AKHIR_PAPAR_DATE"));
                      }
                      
                      if (rs.getString("TARIKH_AKHIR_PAPAR_HOUR") == null) {
                            h.put("tarikh_akhir_papar_hour", "");
                      } else {
                            h.put("tarikh_akhir_papar_hour", rs.getString("TARIKH_AKHIR_PAPAR_HOUR"));
                      }
                      
                      if (rs.getString("TARIKH_AKHIR_PAPAR_MINUTE") == null) {
                            h.put("tarikh_akhir_papar_minute", "");
                      } else {
                            h.put("tarikh_akhir_papar_minute", rs.getString("TARIKH_AKHIR_PAPAR_MINUTE"));
                      }
                      
                      if (rs.getString("TARIKH_AKHIR_PAPAR_AMPM") == null) {
                            h.put("tarikh_akhir_papar_ampm", "");
                      } else {
                            h.put("tarikh_akhir_papar_ampm", rs.getString("TARIKH_AKHIR_PAPAR_AMPM").toUpperCase());
                      }
                      list_memo.addElement(h);
                }
                return list_memo;
          } finally {
             //   if (db != null)
               //       db.close();
          }
    }


	private String getListFail(HttpSession session,Db db) throws Exception {
		if (!getParam("div_getListFail_open").equals("Y")) {
			this.context.put("div_getListFail_open", "Y");
			listFail = ListFail(session, "fail", getParam("search"),db);
			this.context.put("listFail", listFail);
			this.context.put("search", getParam("search"));
		} else {
			this.context.put("div_getListFail_open", "N");
		}
		return "app/ppt/dashboard/div_listFail.jsp";
	}

	private String getListFail_close(HttpSession session,Db db) throws Exception {
		this.context.put("div_getListFail_open", "N");
		return "app/ppt/dashboard/div_listFail.jsp";
	}

	private String getListHakmilik(HttpSession session,Db db) throws Exception {
		if (!getParam("div_getListHakmilik_open").equals("Y")) {
			this.context.put("div_getListHakmilik_open", "Y");
			listHakmilik = ListHakmilik(session, "hakmilik", getParam("search"),db);
			this.context.put("listHakmilik", listHakmilik);
			this.context.put("search", getParam("search"));
		} else {
			this.context.put("div_getListHakmilik_open", "N");
		}
		return "app/ppt/dashboard/div_listHakmilik.jsp";
	}
	private void setCloseDiv() {

	}
	
	private String getListEndorsanK(HttpSession session,Db db) throws Exception {
		//System.out.println("xxx"+getParam("div_getListEndorsanK_open"));
		//System.out.println("www"+request.getParameter("test"));

		 if (!getParam("div_getListEndorsanK_open").equals("Y")) {
		
			setCloseDiv();
			listEndorsanK = ListHakmilik(session, "endorsanK", "",db);
			
			this.context.put("listEndorsanK", listEndorsanK);
			this.context.put("div_getListEndorsanK_open", "Y");
			//this.context.put("divVisible", "visible");
			// this.context.put("search",getParam("search"));
		}
		else {
			
			this.context.put("div_getListEndorsanK_open", "N");
		}
	
		return "app/ppt/dashboard/div_listEndorsanK.jsp";
	}

	private String getListEndorsanK_close(HttpSession session,Db db) throws Exception {
	
		this.context.put("div_getListEndorsanK_open", "N");
		
		return "app/ppt/dashboard/div_listEndorsanK.jsp";
	}

	private String getListHakmilik_close(HttpSession session,Db db) throws Exception {
		this.context.put("div_getListHakmilik_open", "N");
		return "app/ppt/dashboard/div_listHakmilik.jsp";
	}
	//penambahan yati 15/3/2017
	private String getListFailSiasatan(HttpSession session,Db db) throws Exception {
		if (!getParam("div_getListFailSiasatan_open").equals("Y")) {
			this.context.put("div_getListFailSiasatan_open", "Y");
			listFailSiasatan = ListFailSiasatan(session, "failSiasatan", getParam("searchsiasatan"),db);
			this.context.put("listFailSiasatan", listFailSiasatan);
			this.context.put("searchsiasatan", getParam("searchsiasatan"));
		} else {
			this.context.put("div_getListFailSiasatan_open", "N");
		}
		return "app/ppt/dashboard/div_listFailSiasatan.jsp";                
	}

	private String getListFailSiasatan_close(HttpSession session,Db db) throws Exception {
		this.context.put("div_getListFailSiasatan_open", "N");
		return "app/ppt/dashboard/div_listFailSiasatan.jsp";
	}

	private String getListHakmilikSiasatan(HttpSession session,Db db) throws Exception {
		if (!getParam("div_getListHakmilikSiasatan_open").equals("Y")) {
			this.context.put("div_getListHakmilikSiasatan_open", "Y");
			listHakmilikSiasatan = ListHakmilikSiasatan(session, "hakmilikSiasatan", getParam("searchsiasatan"),db);
			this.context.put("listHakmilikSiasatan", listHakmilikSiasatan);
			this.context.put("searchsiasatan", getParam("searchsiasatan"));
		} else {
			this.context.put("div_getListHakmilikSiasatan_open", "N");
		}
		return "app/ppt/dashboard/div_listHakmilikSiasatan.jsp";
	}
	private String getListHakmilikSiasatan_close(HttpSession session,Db db) throws Exception {
		this.context.put("div_getListHakmilikSiasatan_open", "N");
		return "app/ppt/dashboard/div_listHakmilikSiasatan.jsp";
	}


	
	/*PENAMBAHAN YATI*/
	private String getListCaj(HttpSession session,Db db) throws Exception {
		if (!getParam("div_getListCaj_open").equals("Y")) {
			//System.out.println("div_getListCaj_open");
			setCloseDiv();
			this.context.put("div_getListCaj_open", "Y");
			listFailCaj = ListFailCaj(session, "cajLewat", "",db);
			this.context.put("listFailCaj", listFailCaj);
			// this.context.put("search",getParam("search"));
			
			this.context.put("divVisible", "visible");
			
		} else {
			//System.out.println("ELSE N");
			this.context.put("div_getListCaj_open", "N");
		}
		return "app/ppt/dashboard/div_listCaj.jsp";
	}

	private String getListCaj_close(HttpSession session,Db db) throws Exception {
		this.context.put("div_getListCaj_open", "N");
		return "app/ppt/dashboard/div_listCaj.jsp";
	}
	//tutup

	private String getListPB(HttpSession session,Db db) throws Exception {
		if (!getParam("div_getListPB_open").equals("Y")) {
			this.context.put("div_getListPB_open", "Y");
			listPB = ListPB(session, "pb", getParam("search"),db);
			this.context.put("listPB", listPB);
			this.context.put("search", getParam("search"));
		} else {
			this.context.put("div_getListPB_open", "N");
		}
		return "app/ppt/dashboard/div_listPB.jsp";
	}

	private String getListPB_close(HttpSession session,Db db) throws Exception {
		this.context.put("div_getListPB_open", "N");
		return "app/ppt/dashboard/div_listPB.jsp";
	}

	private String carianFail(HttpSession session,Db db) throws Exception {

		this.context.put("div_carianFail_open", "Y");
		listFail = ListFail(session, "fail", getParam("search"),db);
		this.context.put("listFail", listFail);

		return "app/ppt/dashboard/div_carianFail.jsp";
	}
	//penambahan yati
	private String carianFailCaj(HttpSession session,Db db) throws Exception {

		this.context.put("div_carianFailCaj_open", "Y");
		listFailCaj = ListFailCaj(session, "failcaj", getParam("search"),db);
		this.context.put("listFailCaj", listFailCaj);

		return "app/ppt/dashboard/div_carianFailCaj.jsp";
	}
//tutup
	//penambahan yati
	private String carianFailSiasatan(HttpSession session,Db db) throws Exception {

		this.context.put("div_carianFailSiasatan_open", "Y");
		listFailSiasatan = ListFailSiasatan(session, "failSiasatan", getParam("searchsiasatan"),db);
		this.context.put("listFailSiasatan", listFailSiasatan);

		return "app/ppt/dashboard/div_carianFailSiasatan.jsp";
	}
	private String carianHakmilik(HttpSession session,Db db) throws Exception {
		this.context.put("div_carianHakmilik_open", "Y");
		listHakmilik = ListHakmilik(session, "hakmilik", getParam("search"),db);
		this.context.put("listHakmilik", listHakmilik);

		return "app/ppt/dashboard/div_carianHakmilik.jsp";
	}
	//penambahan yati
	private String carianHakmilikSiasatan(HttpSession session,Db db) throws Exception {
		this.context.put("div_carianHakmilikSiasatan_open", "Y");
		listHakmilikSiasatan = ListHakmilikSiasatan(session, "hakmilikSiasatan", getParam("searchsiasatan"),db);
		this.context.put("listHakmilikSiasatan", listHakmilikSiasatan);

		return "app/ppt/dashboard/div_carianHakmilikSiasatan.jsp";
	}

	private String carianPB(HttpSession session,Db db) throws Exception {
		this.context.put("div_carianPB_open", "Y");
		listPB = ListPB(session, "pb", getParam("search"),db);
		this.context.put("listPB", listPB);

		return "app/ppt/dashboard/div_carianPB.jsp";
	}

	private String carianFail_close(HttpSession session,Db db) throws Exception {
		this.context.put("div_carianFail_open", "N");
		return "app/ppt/dashboard/div_carianFail.jsp";
	}
	//penambahan yati
	private String carianFailSiasatan_close(HttpSession session,Db db) throws Exception {
		this.context.put("div_carianFailSiasatan_open", "N");
		return "app/ppt/dashboard/div_carianFailSiasatan.jsp";
	}

	private String carianHakmilik_close(HttpSession session,Db db) throws Exception {
		this.context.put("div_carianHakmilik_open", "N");
		return "app/ppt/dashboard/div_carianHakmilik.jsp";
	}
	
	private String carianHakmilikSiasatan_close(HttpSession session,Db db) throws Exception {
		this.context.put("div_carianHakmilikSiasatan_open", "N");
		return "app/ppt/dashboard/div_carianHakmilikSiasatan.jsp";
	}

	private String carianPB_close(HttpSession session,Db db) throws Exception {
		this.context.put("div_carianPB_open", "N");
		return "app/ppt/dashboard/div_carianPB.jsp";
	}

	private List ListHakmilik(HttpSession session, String type, String search,Db db)
			throws Exception {
		listHakmilik = carianFail(session, type, search,db);
		this.context.put("listHakmilik", listHakmilik);
		return listHakmilik;
	}
	private List ListHakmilikSiasatan(HttpSession session, String type, String searchsiasatan,Db db)
			throws Exception {
		listHakmilikSiasatan = carianFailSiasatan(session, type, searchsiasatan,db);
		this.context.put("listHakmilikSiastan", listHakmilikSiasatan);
		return listHakmilikSiasatan;
	}


	private List ListPB(HttpSession session, String type, String search,Db db)
			throws Exception {
		listPB = carianFail(session, type, search,db);
		this.context.put("listPB", listPB);
		return listPB;
	}

	private List ListFail(HttpSession session, String type, String search,Db db)
			throws Exception {
		listFail = carianFail(session, type, search,db);
		this.context.put("listFail", listFail);
		return listFail;
	}
	
	private List ListFailSiasatan(HttpSession session, String type, String searchsiasatan,Db db)
			throws Exception {
		listFailSiasatan = carianFailSiasatan(session, type, searchsiasatan,db);
		this.context.put("listFailSiasatan", listFailSiasatan);
		return listFailSiasatan;
	}
	
	
	//penambahan yati
	private List ListFailCaj(HttpSession session, String type, String search,Db db)
			throws Exception {
		listFailCaj = carianFailCaj(session, type, search,db);
		this.context.put("listFailCaj", listFailCaj);
		return listFailCaj;

	}

	private List ListAgihan(HttpSession session, String type,Db db) throws Exception {
		listAgihan = carianFail(session, type, "",db);
		this.context.put("listAgihan", listAgihan);
		return listAgihan;
	}

	private String getMainOnline(HttpSession session,Db db) throws Exception {
		count_online_bantahan_ag = count_online_bantahan_ag(session,db);
		this.context.put("count_online_bantahan_ag", count_online_bantahan_ag);

		count_online_bantahan_pb = count_online_bantahan_pb(session,db);
		this.context.put("count_online_bantahan_pb", count_online_bantahan_pb);

		count_online_penarikan = count_online_penarikan(session,db);
		this.context.put("count_online_penarikan", count_online_penarikan);

		count_online_pembatalan = count_online_pembatalan(session,db);
		this.context.put("count_online_pembatalan", count_online_pembatalan);

		count_online_sek4 = count_online_sek4(session,db);
		this.context.put("count_online_sek4", count_online_sek4);

		count_online_sek8 = count_online_sek8(session,db);
		this.context.put("count_online_sek8", count_online_sek8);

		count_online_sementara = count_online_sementara(session,db);
		this.context.put("count_online_sementara", count_online_sementara);
		return "app/ppt/dashboard/div_getMainOnline.jsp";
	}

	private String getListAgihan(HttpSession session,Db db) throws Exception {
		ListAgihan(session, "agihan",db);
		return "app/ppt/dashboard/div_getAgihan.jsp";
	}

	private String getCarianMain(HttpSession session,Db db) throws Exception {
		this.context.put("search", "");
		return "app/ppt/dashboard/div_carianMain.jsp";
	}

	private String getAgihan1(HttpSession session,Db db) throws Exception {
		if (!getParam("div_getAgihan1_open").equals("Y")) {
			listAgihan = ListAgihan(session, "agihan",db);
			this.context.put("listAgihan", listAgihan);
			this.context.put("div_getAgihan1_open", "Y");
			this.context.put("divVisible", "visible");
		} else {
			this.context.put("div_getAgihan1_open", "N");
		}
		return "app/ppt/dashboard/div_getAgihan1.jsp";
	}

	private String getAgihan1_close(HttpSession session,Db db) throws Exception {
		this.context.put("div_getAgihan1_open", "N");
		return "app/ppt/dashboard/div_getAgihan1.jsp";
	}

	private String getAgihan2(HttpSession session,Db db) throws Exception {
		if (!getParam("div_getAgihan2_open").equals("Y")) {
			listAgihan = ListAgihan(session, "agihan",db);
			this.context.put("listAgihan", listAgihan);
			this.context.put("div_getAgihan2_open", "Y");
			this.context.put("divVisible", "visible");
		} else {
			this.context.put("div_getAgihan2_open", "N");
		}
		return "app/ppt/dashboard/div_getAgihan2.jsp";
	}

	private String getAgihan2_close(HttpSession session,Db db) throws Exception {
		this.context.put("div_getAgihan2_open", "N");
		return "app/ppt/dashboard/div_getAgihan2.jsp";
	}

	private String getAgihan3(HttpSession session,Db db) throws Exception {
		if (!getParam("div_getAgihan3_open").equals("Y")) {
			listAgihan = ListAgihan(session, "agihan",db);
			this.context.put("listAgihan", listAgihan);
			this.context.put("div_getAgihan3_open", "Y");
			this.context.put("divVisible", "visible");
		} else {
			this.context.put("div_getAgihan3_open", "N");
		}
		return "app/ppt/dashboard/div_getAgihan3.jsp";
	}

	private String getAgihan3_close(HttpSession session,Db db) throws Exception {
		this.context.put("div_getAgihan3_open", "N");
		return "app/ppt/dashboard/div_getAgihan3.jsp";
	}

	private String getAgihan4(HttpSession session,Db db) throws Exception {
		if (!getParam("div_getAgihan4_open").equals("Y")) {
			listAgihan = ListAgihan(session, "agihan",db);
			this.context.put("listAgihan", listAgihan);
			this.context.put("div_getAgihan4_open", "Y");
			this.context.put("divVisible", "visible");
		} else {
			this.context.put("div_getAgihan4_open", "N");
		}
		return "app/ppt/dashboard/div_getAgihan4.jsp";
	}

	private String getAgihan4_close(HttpSession session,Db db) throws Exception {
		this.context.put("div_getAgihan4_open", "N");
		return "app/ppt/dashboard/div_getAgihan4.jsp";
	}
	
	
	
	private String getAgihan10(HttpSession session,Db db) throws Exception {
		if (!getParam("div_getAgihan10_open").equals("Y")) {
			listAgihan = ListAgihan(session, "agihan",db);
			this.context.put("listAgihan", listAgihan);
			this.context.put("div_getAgihan10_open", "Y");
			this.context.put("divVisible", "visible");
		} else {
			this.context.put("div_getAgihan10_open", "N");
		}
		return "app/ppt/dashboard/div_getAgihan10.jsp";
	}

	private String getAgihan10_close(HttpSession session,Db db) throws Exception {
		this.context.put("div_getAgihan10_open", "N");
		return "app/ppt/dashboard/div_getAgihan10.jsp";
	}

	private String getAgihan5(HttpSession session,Db db) throws Exception {
		if (!getParam("div_getAgihan5_open").equals("Y")) {
			listAgihan = ListAgihan(session, "agihan",db);
			this.context.put("listAgihan", listAgihan);
			this.context.put("div_getAgihan5_open", "Y");
			this.context.put("divVisible", "visible");
		} else {
			this.context.put("div_getAgihan5_open", "N");
		}
		return "app/ppt/dashboard/div_getAgihan5.jsp";
	}

	private String getAgihan5_close(HttpSession session,Db db) throws Exception {
		this.context.put("div_getAgihan5_open", "N");
		return "app/ppt/dashboard/div_getAgihan5.jsp";
	}

	private String getAgihan6(HttpSession session,Db db) throws Exception {
		if (!getParam("div_getAgihan6_open").equals("Y")) {
			listAgihan = ListAgihan(session, "agihan",db);
			this.context.put("listAgihan", listAgihan);
			this.context.put("div_getAgihan6_open", "Y");
			this.context.put("divVisible", "visible");
		} else {
			this.context.put("div_getAgihan6_open", "N");
		}
		return "app/ppt/dashboard/div_getAgihan6.jsp";
	}

	private String getAgihan6_close(HttpSession session,Db db) throws Exception {
		this.context.put("div_getAgihan6_open", "N");
		return "app/ppt/dashboard/div_getAgihan6.jsp";
	}

	private String getAgihan7(HttpSession session,Db db) throws Exception {
		if (!getParam("div_getAgihan7_open").equals("Y")) {
			listAgihan = ListAgihan(session, "agihan",db);
			this.context.put("listAgihan", listAgihan);
			this.context.put("div_getAgihan7_open", "Y");
			this.context.put("divVisible", "visible");
		} else {
			this.context.put("div_getAgihan7_open", "N");
		}
		return "app/ppt/dashboard/div_getAgihan7.jsp";
	}

	private String getAgihan7_close(HttpSession session,Db db) throws Exception {
		this.context.put("div_getAgihan7_open", "N");
		return "app/ppt/dashboard/div_getAgihan7.jsp";
	}

	private String getAgihan8(HttpSession session,Db db) throws Exception {
		if (!getParam("div_getAgihan8_open").equals("Y")) {
			listAgihan = ListAgihan(session, "agihan",db);
			this.context.put("listAgihan", listAgihan);
			this.context.put("div_getAgihan8_open", "Y");
			this.context.put("divVisible", "visible");
		} else {
			this.context.put("div_getAgihan8_open", "N");
		}
		return "app/ppt/dashboard/div_getAgihan8.jsp";
	}

	private String getAgihan8_close(HttpSession session,Db db) throws Exception {
		this.context.put("div_getAgihan8_open", "N");
		return "app/ppt/dashboard/div_getAgihan8.jsp";
	}

	private String getAgihan9(HttpSession session,Db db) throws Exception {
		if (!getParam("div_getAgihan9_open").equals("Y")) {
			listAgihan = ListAgihan(session, "agihan",db);
			this.context.put("listAgihan", listAgihan);
			this.context.put("div_getAgihan9_open", "Y");
			this.context.put("divVisible", "visible");
		} else {
			this.context.put("div_getAgihan9_open", "N");
		}
		return "app/ppt/dashboard/div_getAgihan9.jsp";
	}

	private String getAgihan9_close(HttpSession session,Db db) throws Exception {
		this.context.put("div_getAgihan9_open", "N");
		return "app/ppt/dashboard/div_getAgihan9.jsp";
	}


	private String getOnline4(HttpSession session,Db db) throws Exception {
		//System.out.println("xxx"+getParam("div_getOnline4_open"));
		//System.out.println("www"+getParam("try"));
		if (!getParam("div_getOnline4_open").equals("Y")){
			
			setCloseDiv();
			
			listing_online_sek4 = listing_online_sek4(session,db);
			// prepareItemForDisplay(session, listing_online_sek4, 50, "first");
			this.context.put("listing_online_sek4", listing_online_sek4);
			this.context.put("div_getOnline4_open", "Y");
			this.context.put("divVisible", "visible");
		} else {
			
			this.context.put("div_getOnline4_open", "N");
		}
		
		return "app/ppt/dashboard/div_getOnline4.jsp";
	}

	private String getOnline4_close(HttpSession session,Db db) throws Exception {
		
		this.context.put("div_getOnline4_open", "N");
		return "app/ppt/dashboard/div_getOnline4.jsp";
	}

	private String getOnline8(HttpSession session,Db db) throws Exception {
		if (!getParam("div_getOnline8_open").equals("Y")) {
			setCloseDiv();
			listing_online_sek8 = listing_online_sek8(session,db);
			// prepareItemForDisplay(session, listing_online_sek8, 50, "first");
			this.context.put("listing_online_sek8", listing_online_sek8);
			this.context.put("div_getOnline8_open", "Y");
			this.context.put("divVisible", "visible");
		} else {
			
			this.context.put("div_getOnline8_open", "N");
		}
		return "app/ppt/dashboard/div_getOnline8.jsp";
	}

	private String getOnline8_close(HttpSession session,Db db) throws Exception {
		this.context.put("div_getOnline8_open", "N");
		return "app/ppt/dashboard/div_getOnline8.jsp";
	}

	private String getOnlineSementara(HttpSession session,Db db) throws Exception {
		if (!getParam("div_getOnlineSementara_open").equals("Y")) {
			setCloseDiv();
			listing_online_sementara = listing_online_sementara(session,db);
			// prepareItemForDisplay(session, listing_online_sementara, 50,
			// "first");
			this.context.put("listing_online_sementara",
					listing_online_sementara);
			this.context.put("div_getOnlineSementara_open", "Y");
			this.context.put("divVisible", "visible");
		} else {
			this.context.put("div_getOnlineSementara_open", "N");
		}
		return "app/ppt/dashboard/div_getOnlineSementara.jsp";
	}

	private String getOnlineSementara_close(HttpSession session,Db db) throws Exception {
		this.context.put("div_getOnlineSementara_open", "N");
		return "app/ppt/dashboard/div_getOnlineSementara.jsp";
	}

	private String getOnlineBantahanPB(HttpSession session,Db db) throws Exception {
		if (!getParam("div_getOnlineBantahanPB_open").equals("Y")) {
			listing_online_bantahan_pb = listing_online_bantahan_pb(session,db);
			// prepareItemForDisplay(session, listing_online_sementara, 50,
			// "first");
			this.context.put("listing_online_bantahan_pb",
					listing_online_bantahan_pb);
			this.context.put("div_getOnlineBantahanPB_open", "Y");
			this.context.put("divVisible", "visible");
		} else {
			this.context.put("div_getOnlineBantahanPB_open", "N");
		}
		return "app/ppt/dashboard/div_getOnlineBantahanPB.jsp";
	}

	private String getOnlineBantahanPB_close(HttpSession session,Db db)
			throws Exception {
		this.context.put("div_getOnlineBantahanPB_open", "N");
		return "app/ppt/dashboard/div_getOnlineBantahanPB.jsp";
	}

	private String getOnlineBantahanAG(HttpSession session,Db db) throws Exception {
		if (!getParam("div_getOnlineBantahanAG_open").equals("Y")) {
			listing_online_bantahan_agensi = listing_online_bantahan_agensi(session,db);
			// prepareItemForDisplay(session, listing_online_sementara, 50,
			// "first");
			this.context.put("listing_online_bantahan_agensi",
					listing_online_bantahan_agensi);
			this.context.put("div_getOnlineBantahanAG_open", "Y");
			this.context.put("divVisible", "visible");
		} else {
			this.context.put("div_getOnlineBantahanAG_open", "N");
		}
		return "app/ppt/dashboard/div_getOnlineBantahanAG.jsp";
	}

	private String getOnlineBantahanAG_close(HttpSession session,Db db)	throws Exception {
		this.context.put("div_getOnlineBantahanAG_open", "N");
		return "app/ppt/dashboard/div_getOnlineBantahanAG.jsp";
	}

	private String getOnlinePenarikan(HttpSession session,Db db) throws Exception {
		if (!getParam("div_getOnlinePenarikan_open").equals("Y")) {
			listing_online_penarikan = listing_online_penarikan(session,db);
			// prepareItemForDisplay(session, listing_online_sementara, 50,
			// "first");
			this.context.put("listing_online_penarikan",
					listing_online_penarikan);
			this.context.put("div_getOnlinePenarikan_open", "Y");
			this.context.put("divVisible", "visible");
		} else {
			this.context.put("div_getOnlinePenarikan_open", "N");
		}
		return "app/ppt/dashboard/div_getOnlinePenarikan.jsp";
	}

	private String getOnlinePenarikan_close(HttpSession session, Db db)
			throws Exception {
		this.context.put("div_getOnlinePenarikan_open", "N");
		return "app/ppt/dashboard/div_getOnlinePenarikan.jsp";
	}

	private String getOnlinePembatalan(HttpSession session,Db db) throws Exception {
		if (!getParam("div_getOnlinePembatalan_open").equals("Y")) {
			listing_online_pembatalan = listing_online_pembatalan(session,db);
			// prepareItemForDisplay(session, listing_online_sementara, 50,
			// "first");
			this.context.put("listing_online_pembatalan",
					listing_online_pembatalan);
			this.context.put("div_getOnlinePembatalan_open", "Y");
			this.context.put("divVisible", "visible");
		} else {
			this.context.put("div_getOnlinePembatalan_open", "N");
		}
		return "app/ppt/dashboard/div_getOnlinePembatalan.jsp";
	}

	private String getOnlinePembatalan_close(HttpSession session,Db db)
			throws Exception {
		this.context.put("div_getOnlinePembatalan_open", "N");
		return "app/ppt/dashboard/div_getOnlinePembatalan.jsp";
	}
	//yati--

	private String getTabDashboard(HttpSession session, String role,
			String id_negeri_user, String id_esaduan, String flag_notifikasi,
			String user_terima, String notread, String fail_selesai,
			String fail_xselesai,Db db) throws Exception {
		
		
		Vector list_memo_aktif = null;
		list_memo_aktif = getMemo("", "Aktif", "1", "0",db);
		context.put("list_memo_aktif", list_memo_aktif);
		/*
		context.put("fail_selesai", fail_selesai);
		context.put("fail_xselesai", fail_xselesai);

		Hashtable get_stat_bar = null;
		get_stat_bar = stat_bar(session, role, userId,db);
		String DITERIMA = (String) get_stat_bar.get("DITERIMA");
		String DISELESAI = (String) get_stat_bar.get("DISELESAI");
		String LOT_DITERIMA = (String) get_stat_bar.get("LOT_DITERIMA");
		String LOT_DISELESAI = (String) get_stat_bar.get("LOT_DISELESAI");

		context.put("DITERIMA", DITERIMA);
		context.put("DISELESAI", DISELESAI);
		context.put("LOT_DITERIMA", LOT_DITERIMA);
		context.put("LOT_DISELESAI", LOT_DISELESAI);

		context.put("getListEndorsan", 1);

		listHakmilik = ListHakmilik(session, "endorsanK", "",db);
		this.context.put("listHakmilik", listHakmilik);
		*/
		return "app/ppt/dashboard/dashboard_tab.jsp";
	}

	Integer count_online_sementara = 0;

	@SuppressWarnings("unchecked")
	public Integer count_online_sementara(HttpSession session,Db db) throws Exception {
		//Db db = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String negeriUser = (String) session.getAttribute("_ekptg_user_negeri");
		String sql = "";
		try {
			//db = new Db();
			Statement stmt = db.getStatement();
			sql = " SELECT "
					+ " COUNT(*) AS TOTAL "
					+ " FROM TBLPPTPERMOHONAN P,TBLPFDFAIL F,TBLRUJSTATUS S,USERS U,USERS_INTERNAL UI, TBLPPTMMK MK "
					+ " WHERE P.ID_FAIL = F.ID_FAIL"
					+ " AND S.ID_STATUS = P.ID_STATUS(+) "
					+ " AND P.ID_MASUK = U.USER_ID(+) "
					+ " AND P.ID_PERMOHONAN = MK.ID_PERMOHONAN(+)"
					+ " AND F.ID_SUBURUSAN IN (53) "
					+ " AND P.ID_STATUS IN (138) "
					+ " AND U.USER_ID = UI.USER_ID(+) ";
			sql = sql + " AND P.FLAG_JENISPERMOHONAN = '1' ";
			if (!negeriUser.equals("16") && !negeriUser.isEmpty()) {
				if (negeriUser.equals("14")) {
					sql += "AND F.ID_NEGERI in (14,15,16) ";
				} else {
					sql += "AND F.ID_NEGERI = '" + negeriUser + "'";
				}
			}
			sql = sql + " ORDER BY P.ID_PERMOHONAN DESC";
			myLogger.debug("FAIL ONLINE PERMOHONAN listing_online_sementara:"
					+ sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				count_online_sementara = rs.getInt("TOTAL");
			}
			return count_online_sementara;
		} finally {
			//if (db != null)
				//db.close();
		}
	}

	Vector listing_online_sementara = null;

	@SuppressWarnings("unchecked")
	public Vector listing_online_sementara(HttpSession session, Db db)throws Exception {
		listing_online_sementara = new Vector();
		//Db db = null;
		listing_online_sementara.clear();
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String negeriUser = (String) session.getAttribute("_ekptg_user_negeri");
		String sql = "";
		try {
			//db = new Db();
			Statement stmt = db.getStatement();
			sql = " SELECT "
					+ " F.ID_SUBURUSAN,P.NO_PERMOHONAN_ONLINE,P.TUJUAN,P.FLAG_JENISPERMOHONAN,F.NO_FAIL AS NO_JKPTG,P.NO_RUJUKAN_PTG,P.NO_RUJUKAN_UPT,P.NO_RUJUKAN_PTD,TO_CHAR(P.TARIKH_PERMOHONAN,'DD.MM.YYYY') AS TARIKH_PERMOHONAN,S.KETERANGAN AS STATUS, "
					+ " U.USER_NAME,P.ID_PERMOHONAN,P.ID_STATUS,P.ID_FAIL,UI.ID_NEGERI AS NEGERI_USER, P.FLAG_SEGERA, P.FLAG_SEMAK, MK.FLAG_SEMAKAN_PENGARAH, P.FLAG_STATUS_ONLINE "
					+ " FROM TBLPPTPERMOHONAN P,TBLPFDFAIL F,TBLRUJSTATUS S,USERS U,USERS_INTERNAL UI, TBLPPTMMK MK "
					+ " WHERE P.ID_FAIL = F.ID_FAIL"
					+ " AND S.ID_STATUS = P.ID_STATUS(+) "
					+ " AND P.ID_MASUK = U.USER_ID(+) "
					+ " AND P.ID_PERMOHONAN = MK.ID_PERMOHONAN(+)"
					+ " AND F.ID_SUBURUSAN IN (53) "
					+ " AND P.ID_STATUS IN (138) "
					+ " AND U.USER_ID = UI.USER_ID(+) ";
			sql = sql + " AND P.FLAG_JENISPERMOHONAN = '1' ";
			if (!negeriUser.equals("16") && !negeriUser.isEmpty()) {
				if (negeriUser.equals("14")) {
					sql += "AND F.ID_NEGERI in (14,15,16) ";
				} else {
					sql += "AND F.ID_NEGERI = '" + negeriUser + "'";
				}
			}
			sql = sql + " ORDER BY P.ID_PERMOHONAN DESC";
			myLogger.debug("FAIL ONLINE PERMOHONAN listing_online_sementara:"
					+ sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("FLAG_STATUS_ONLINE",
						rs.getString("FLAG_STATUS_ONLINE") == null ? "" : rs
								.getString("FLAG_STATUS_ONLINE"));
				h.put("FLAG_SEMAKAN_PENGARAH",
						rs.getString("FLAG_SEMAKAN_PENGARAH") == null ? "" : rs
								.getString("FLAG_SEMAKAN_PENGARAH"));
				h.put("FLAG_SEMAK", rs.getString("FLAG_SEMAK") == null ? ""
						: rs.getString("FLAG_SEMAK"));
				h.put("FLAG_SEGERA", rs.getString("FLAG_SEGERA") == null ? ""
						: rs.getString("FLAG_SEGERA"));
				h.put("ID_SUBURUSAN", rs.getString("ID_SUBURUSAN") == null ? ""
						: rs.getString("ID_SUBURUSAN"));
				h.put("FLAG_JENISPERMOHONAN",
						rs.getString("FLAG_JENISPERMOHONAN") == null ? "" : rs
								.getString("FLAG_JENISPERMOHONAN"));
				h.put("ID_STATUS",
						rs.getString("ID_STATUS") == null ? "" : rs
								.getString("ID_STATUS"));
				h.put("NO_JKPTG",
						rs.getString("NO_JKPTG") == null ? "" : rs
								.getString("NO_JKPTG"));
				h.put("NO_RUJUKAN_PTG",
						rs.getString("NO_RUJUKAN_PTG") == null ? "" : rs
								.getString("NO_RUJUKAN_PTG"));
				h.put("NO_RUJUKAN_PTD",
						rs.getString("NO_RUJUKAN_PTD") == null ? "" : rs
								.getString("NO_RUJUKAN_PTD"));
				h.put("NO_RUJUKAN_UPT",
						rs.getString("NO_RUJUKAN_UPT") == null ? "" : rs
								.getString("NO_RUJUKAN_UPT"));
				h.put("TARIKH_PERMOHONAN",
						rs.getString("TARIKH_PERMOHONAN") == null ? "" : rs
								.getString("TARIKH_PERMOHONAN"));
				h.put("STATUS",
						rs.getString("STATUS") == null ? "" : rs
								.getString("STATUS"));
				h.put("USER_NAME",
						rs.getString("USER_NAME") == null ? "" : rs
								.getString("USER_NAME"));
				h.put("ID_PERMOHONAN",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				h.put("ID_FAIL",
						rs.getString("ID_FAIL") == null ? "" : rs
								.getString("ID_FAIL"));
				h.put("NEGERI_USER", rs.getString("NEGERI_USER") == null ? ""
						: rs.getString("NEGERI_USER"));
				h.put("NO_PERMOHONAN_ONLINE",
						rs.getString("NO_PERMOHONAN_ONLINE") == null ? "" : rs
								.getString("NO_PERMOHONAN_ONLINE"));
				if (rs.getString("ID_SUBURUSAN") != null) {
					if (rs.getString("ID_SUBURUSAN").equals("51")) {
						h.put("URUSAN", "PENGAMBILAN SEKSYEN 4");
					} else if (rs.getString("ID_SUBURUSAN").equals("52")) {
						h.put("URUSAN", "PENGAMBILAN SEKSYEN 8");
					} else if (rs.getString("ID_SUBURUSAN").equals("53")) {
						h.put("URUSAN", "PENGAMBILAN SEMENTARA");
					} else {
						h.put("URUSAN", "");
					}
				} else {
					h.put("URUSAN", "");
				}
				listing_online_sementara.addElement(h);
			}
			return listing_online_sementara;
		} finally {
			//if (db != null)
				//db.close();
		}
	}

	Integer count_online_sek8 = 0;

	@SuppressWarnings("unchecked")
	public Integer count_online_sek8(HttpSession session,Db db) throws Exception {

		//Db db = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String negeriUser = (String) session.getAttribute("_ekptg_user_negeri");
		String sql = "";
		try {
			//db = new Db();
			Statement stmt = db.getStatement();
			sql = " SELECT "
					+ " COUNT(*) AS TOTAL "
					+ " FROM TBLPPTPERMOHONAN P,TBLPFDFAIL F,TBLRUJSTATUS S,USERS U,USERS_INTERNAL UI, TBLPPTMMK MK "
					+ " WHERE P.ID_FAIL = F.ID_FAIL"
					+ " AND S.ID_STATUS = P.ID_STATUS(+) "
					+ " AND P.ID_MASUK = U.USER_ID(+) "
					+ " AND P.ID_PERMOHONAN = MK.ID_PERMOHONAN(+)"
					+ " AND F.ID_SUBURUSAN IN (52) "
					+ " AND P.ID_STATUS IN (138) "
					+ " AND U.USER_ID = UI.USER_ID(+) ";
			sql = sql + " AND P.FLAG_JENISPERMOHONAN = '1' ";
			if (!negeriUser.equals("16") && !negeriUser.isEmpty()) {
				if (negeriUser.equals("14")) {
					sql += "AND F.ID_NEGERI in (14,15,16) ";
				} else {
					sql += "AND F.ID_NEGERI = '" + negeriUser + "'";
				}
			}
			sql = sql + " ORDER BY P.ID_PERMOHONAN DESC";
			myLogger.debug("FAIL ONLINE PERMOHONAN listing_online_sek8:" + sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				count_online_sek8 = rs.getInt("TOTAL");
			}
			return count_online_sek8;
		} finally {
			//if (db != null)
				//db.close();
		}
	}

	Vector listing_online_sek8 = null;

	@SuppressWarnings("unchecked")
	public Vector listing_online_sek8(HttpSession session,Db db) throws Exception {
		listing_online_sek8 = new Vector();
		//Db db = null;
		listing_online_sek8.clear();
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String negeriUser = (String) session.getAttribute("_ekptg_user_negeri");
		String sql = "";
		try {
			//db = new Db();
			Statement stmt = db.getStatement();
			sql = " SELECT "
					+ " F.ID_SUBURUSAN,P.NO_PERMOHONAN_ONLINE,P.TUJUAN,P.FLAG_JENISPERMOHONAN,F.NO_FAIL AS NO_JKPTG,P.NO_RUJUKAN_PTG,P.NO_RUJUKAN_UPT,P.NO_RUJUKAN_PTD,TO_CHAR(P.TARIKH_PERMOHONAN_KJP,'DD.MM.YYYY') AS TARIKH_PERMOHONAN,S.KETERANGAN AS STATUS, "
					+ " U.USER_NAME,P.ID_PERMOHONAN,P.ID_STATUS,P.ID_FAIL,UI.ID_NEGERI AS NEGERI_USER, P.FLAG_SEGERA, P.FLAG_SEMAK, MK.FLAG_SEMAKAN_PENGARAH, P.FLAG_STATUS_ONLINE "
					+ " FROM TBLPPTPERMOHONAN P,TBLPFDFAIL F,TBLRUJSTATUS S,USERS U,USERS_INTERNAL UI, TBLPPTMMK MK "
					+ " WHERE P.ID_FAIL = F.ID_FAIL"
					+ " AND S.ID_STATUS = P.ID_STATUS(+) "
					+ " AND P.ID_MASUK = U.USER_ID(+) "
					+ " AND P.ID_PERMOHONAN = MK.ID_PERMOHONAN(+)"
					+ " AND F.ID_SUBURUSAN IN (52) "
					+ " AND P.ID_STATUS IN (138) "
					+ " AND U.USER_ID = UI.USER_ID(+) ";

			sql = sql + " AND P.FLAG_JENISPERMOHONAN = '1' ";
			if (!negeriUser.equals("16") && !negeriUser.isEmpty()) {
				if (negeriUser.equals("14")) {
					sql += "AND F.ID_NEGERI in (14,15,16) ";
				} else {
					sql += "AND F.ID_NEGERI = '" + negeriUser + "'";
				}
			}
			sql = sql + " ORDER BY P.ID_PERMOHONAN DESC";
			myLogger.debug("FAIL ONLINE PERMOHONAN listing_online_sek8:" + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("FLAG_STATUS_ONLINE",
						rs.getString("FLAG_STATUS_ONLINE") == null ? "" : rs
								.getString("FLAG_STATUS_ONLINE"));
				h.put("FLAG_SEMAKAN_PENGARAH",
						rs.getString("FLAG_SEMAKAN_PENGARAH") == null ? "" : rs
								.getString("FLAG_SEMAKAN_PENGARAH"));
				h.put("FLAG_SEMAK", rs.getString("FLAG_SEMAK") == null ? ""
						: rs.getString("FLAG_SEMAK"));
				h.put("FLAG_SEGERA", rs.getString("FLAG_SEGERA") == null ? ""
						: rs.getString("FLAG_SEGERA"));
				h.put("ID_SUBURUSAN", rs.getString("ID_SUBURUSAN") == null ? ""
						: rs.getString("ID_SUBURUSAN"));
				h.put("FLAG_JENISPERMOHONAN",
						rs.getString("FLAG_JENISPERMOHONAN") == null ? "" : rs
								.getString("FLAG_JENISPERMOHONAN"));
				h.put("ID_STATUS",
						rs.getString("ID_STATUS") == null ? "" : rs
								.getString("ID_STATUS"));
				h.put("NO_JKPTG",
						rs.getString("NO_JKPTG") == null ? "" : rs
								.getString("NO_JKPTG"));
				h.put("NO_RUJUKAN_PTG",
						rs.getString("NO_RUJUKAN_PTG") == null ? "" : rs
								.getString("NO_RUJUKAN_PTG"));
				h.put("NO_RUJUKAN_PTD",
						rs.getString("NO_RUJUKAN_PTD") == null ? "" : rs
								.getString("NO_RUJUKAN_PTD"));
				h.put("NO_RUJUKAN_UPT",
						rs.getString("NO_RUJUKAN_UPT") == null ? "" : rs
								.getString("NO_RUJUKAN_UPT"));
				h.put("TARIKH_PERMOHONAN",
						rs.getString("TARIKH_PERMOHONAN") == null ? "" : rs
								.getString("TARIKH_PERMOHONAN"));
				h.put("STATUS",
						rs.getString("STATUS") == null ? "" : rs
								.getString("STATUS"));
				h.put("USER_NAME",
						rs.getString("USER_NAME") == null ? "" : rs
								.getString("USER_NAME"));
				h.put("ID_PERMOHONAN",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				h.put("ID_FAIL",
						rs.getString("ID_FAIL") == null ? "" : rs
								.getString("ID_FAIL"));
				h.put("NEGERI_USER", rs.getString("NEGERI_USER") == null ? ""
						: rs.getString("NEGERI_USER"));
				h.put("NO_PERMOHONAN_ONLINE",
						rs.getString("NO_PERMOHONAN_ONLINE") == null ? "" : rs
								.getString("NO_PERMOHONAN_ONLINE"));
				if (rs.getString("ID_SUBURUSAN") != null) {
					if (rs.getString("ID_SUBURUSAN").equals("51")) {
						h.put("URUSAN", "PENGAMBILAN SEKSYEN 4");
					} else if (rs.getString("ID_SUBURUSAN").equals("52")) {
						h.put("URUSAN", "PENGAMBILAN SEKSYEN 8");
					} else if (rs.getString("ID_SUBURUSAN").equals("53")) {
						h.put("URUSAN", "PENGAMBILAN SEMENTARA");
					} else {
						h.put("URUSAN", "");
					}
				} else {
					h.put("URUSAN", "");
				}
				listing_online_sek8.addElement(h);
			}
			return listing_online_sek8;
		} finally {
			//if (db != null)
				//db.close();
		}
	}

	Integer count_online_sek4 = null;

	@SuppressWarnings("unchecked")
	public Integer count_online_sek4(HttpSession session,Db db) throws Exception {

		//Db db = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String negeriUser = (String) session.getAttribute("_ekptg_user_negeri");
		String sql = "";
		try {
			//db = new Db();
			Statement stmt = db.getStatement();
			sql = " SELECT "
					+ " COUNT(*) AS TOTAL "
					+ " FROM TBLPPTPERMOHONAN P,TBLPFDFAIL F,TBLRUJSTATUS S,USERS U,USERS_INTERNAL UI, TBLPPTMMK MK "
					+ " WHERE P.ID_FAIL = F.ID_FAIL"
					+ " AND S.ID_STATUS = P.ID_STATUS(+) "
					+ " AND P.ID_MASUK = U.USER_ID(+) "
					+ " AND P.ID_PERMOHONAN = MK.ID_PERMOHONAN(+)"
					+ " AND F.ID_SUBURUSAN IN (51) "
					+ " AND P.ID_STATUS IN (138) "
					+ " AND U.USER_ID = UI.USER_ID(+) ";
			sql = sql + " AND P.FLAG_JENISPERMOHONAN = '1' ";
			if (!negeriUser.equals("16") && !negeriUser.isEmpty()) {
				if (negeriUser.equals("14")) {
					sql += "AND F.ID_NEGERI in (14,15,16) ";
				} else {
					sql += "AND F.ID_NEGERI = '" + negeriUser + "'";
				}
			}
			sql = sql + " ORDER BY P.ID_PERMOHONAN DESC";
			myLogger.debug("FAIL ONLINE PERMOHONAN listing_online_sek4:" + sql);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				count_online_sek4 = rs.getInt("TOTAL");
			}
			return count_online_sek4;
		} finally {
			//if (db != null)
				//db.close();
		}
	}

	Vector listing_online_sek4 = null;

	@SuppressWarnings("unchecked")
	public Vector listing_online_sek4(HttpSession session,Db db) throws Exception {
		listing_online_sek4 = new Vector();
		//Db db = null;
		listing_online_sek4.clear();
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String negeriUser = (String) session.getAttribute("_ekptg_user_negeri");
		String sql = "";
		try {
			//db = new Db();
			Statement stmt = db.getStatement();
			sql = " SELECT "
					+ " F.ID_SUBURUSAN,P.NO_PERMOHONAN_ONLINE,P.TUJUAN,P.FLAG_JENISPERMOHONAN,F.NO_FAIL AS NO_JKPTG,P.NO_RUJUKAN_PTG,P.NO_RUJUKAN_UPT,P.NO_RUJUKAN_PTD,TO_CHAR(P.TARIKH_PERMOHONAN_KJP,'DD.MM.YYYY') AS TARIKH_PERMOHONAN," 
					+ " S.KETERANGAN AS STATUS, "
					+ " U.USER_NAME,P.ID_PERMOHONAN,P.ID_STATUS,P.ID_FAIL,UI.ID_NEGERI AS NEGERI_USER, P.FLAG_SEGERA, P.FLAG_SEMAK, MK.FLAG_SEMAKAN_PENGARAH, P.FLAG_STATUS_ONLINE "
					+ " FROM TBLPPTPERMOHONAN P,TBLPFDFAIL F,TBLRUJSTATUS S,USERS U,USERS_INTERNAL UI, TBLPPTMMK MK "
					+ " WHERE P.ID_FAIL = F.ID_FAIL"
					+ " AND S.ID_STATUS = P.ID_STATUS(+) "
					+ " AND P.ID_MASUK = U.USER_ID(+) "
					+ " AND P.ID_PERMOHONAN = MK.ID_PERMOHONAN(+)"
					+ " AND F.ID_SUBURUSAN IN (51) "
					+ " AND P.ID_STATUS IN (138) "
					+ " AND U.USER_ID = UI.USER_ID(+) ";
			sql = sql + " AND P.FLAG_JENISPERMOHONAN = '1' ";
			if (!negeriUser.equals("16") && !negeriUser.isEmpty()) {
				if (negeriUser.equals("14")) {
					sql += "AND F.ID_NEGERI in (14,15,16) ";
				} else {
					sql += "AND F.ID_NEGERI = '" + negeriUser + "'";
				}
			}
			sql = sql + " ORDER BY P.ID_PERMOHONAN DESC";
			myLogger.debug("FAIL ONLINE PERMOHONAN listing_online_sek4:" + sql);
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();
				h.put("FLAG_STATUS_ONLINE",
						rs.getString("FLAG_STATUS_ONLINE") == null ? "" : rs
								.getString("FLAG_STATUS_ONLINE"));
				h.put("FLAG_SEMAKAN_PENGARAH",
						rs.getString("FLAG_SEMAKAN_PENGARAH") == null ? "" : rs
								.getString("FLAG_SEMAKAN_PENGARAH"));
				h.put("FLAG_SEMAK", rs.getString("FLAG_SEMAK") == null ? ""
						: rs.getString("FLAG_SEMAK"));
				h.put("FLAG_SEGERA", rs.getString("FLAG_SEGERA") == null ? ""
						: rs.getString("FLAG_SEGERA"));
				h.put("ID_SUBURUSAN", rs.getString("ID_SUBURUSAN") == null ? ""
						: rs.getString("ID_SUBURUSAN"));
				h.put("FLAG_JENISPERMOHONAN",
						rs.getString("FLAG_JENISPERMOHONAN") == null ? "" : rs
								.getString("FLAG_JENISPERMOHONAN"));
				h.put("ID_STATUS",
						rs.getString("ID_STATUS") == null ? "" : rs
								.getString("ID_STATUS"));
				h.put("NO_JKPTG",
						rs.getString("NO_JKPTG") == null ? "" : rs
								.getString("NO_JKPTG"));
				h.put("NO_RUJUKAN_PTG",
						rs.getString("NO_RUJUKAN_PTG") == null ? "" : rs
								.getString("NO_RUJUKAN_PTG"));
				h.put("NO_RUJUKAN_PTD",
						rs.getString("NO_RUJUKAN_PTD") == null ? "" : rs
								.getString("NO_RUJUKAN_PTD"));
				h.put("NO_RUJUKAN_UPT",
						rs.getString("NO_RUJUKAN_UPT") == null ? "" : rs
								.getString("NO_RUJUKAN_UPT"));
				h.put("TARIKH_PERMOHONAN",
						rs.getString("TARIKH_PERMOHONAN") == null ? "" : rs
								.getString("TARIKH_PERMOHONAN"));
				h.put("STATUS",
						rs.getString("STATUS") == null ? "" : rs
								.getString("STATUS"));
				h.put("USER_NAME",
						rs.getString("USER_NAME") == null ? "" : rs
								.getString("USER_NAME"));
				h.put("ID_PERMOHONAN",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				h.put("ID_FAIL",
						rs.getString("ID_FAIL") == null ? "" : rs
								.getString("ID_FAIL"));
				h.put("NEGERI_USER", rs.getString("NEGERI_USER") == null ? ""
						: rs.getString("NEGERI_USER"));
				h.put("NO_PERMOHONAN_ONLINE",
						rs.getString("NO_PERMOHONAN_ONLINE") == null ? "" : rs
								.getString("NO_PERMOHONAN_ONLINE"));
				if (rs.getString("ID_SUBURUSAN") != null) {
					if (rs.getString("ID_SUBURUSAN").equals("51")) {
						h.put("URUSAN", "PENGAMBILAN SEKSYEN 4");
					} else if (rs.getString("ID_SUBURUSAN").equals("52")) {
						h.put("URUSAN", "PENGAMBILAN SEKSYEN 8");
					} else if (rs.getString("ID_SUBURUSAN").equals("53")) {
						h.put("URUSAN", "PENGAMBILAN SEMENTARA");
					} else {
						h.put("URUSAN", "");
					}
				} else {
					h.put("URUSAN", "");
				}
				listing_online_sek4.addElement(h);
			}
			return listing_online_sek4;
		} finally {
			//if (db != null)
				//db.close();
		}
	}

	Integer count_online_pembatalan = null;

	@SuppressWarnings("unchecked")
	public Integer count_online_pembatalan(HttpSession session,Db db) throws Exception {

		//Db db = null;

		String userId = (String) session.getAttribute("_ekptg_user_id");
		String negeriUser = (String) session.getAttribute("_ekptg_user_negeri");
		String sql = "";
		try {
			//db = new Db();
			Statement stmt = db.getStatement();

			sql = " SELECT "
					+ " COUNT(*) AS TOTAL "
					+ " FROM TBLPPTPERMOHONAN P,TBLPFDFAIL F,TBLRUJSTATUS S,USERS U,USERS_INTERNAL UI, TBLPPTMMK MK "
					+ " WHERE P.ID_FAIL = F.ID_FAIL"
					+ " AND S.ID_STATUS = P.ID_STATUS(+) "
					+ " AND P.ID_MASUK = U.USER_ID(+) "
					+ " AND P.ID_PERMOHONAN = MK.ID_PERMOHONAN(+)"
					+ " AND F.ID_SUBURUSAN IN (51,52,53) "
					+ " AND P.ID_STATUS NOT IN (8,999) "
					+ " AND U.USER_ID = UI.USER_ID(+) ";
			sql = sql
					+ " AND  P.ID_PERMOHONAN IN (SELECT P.ID_PERMOHONAN "
					+ " FROM TBLPPTHAKMILIK H,TBLPPTPERMOHONAN P,TBLPFDFAIL F,TBLPPTPEMBATALAN PNB,TBLPPTPEMBATALANHAKMILIK PH "
					+ " WHERE F.ID_FAIL = P.ID_FAIL AND PH.ID_HAKMILIK = H.ID_HAKMILIK AND PH.ID_PEMBATALAN = PNB.ID_PEMBATALAN "
					+ " AND H.ID_PERMOHONAN = P.ID_PERMOHONAN "
					+ " AND PNB.FLAG_ONLINE = '2'" + " )";

			if (!negeriUser.equals("16") && !negeriUser.isEmpty()) {
				if (negeriUser.equals("14")) {
					sql += "AND F.ID_NEGERI in (14,15,16) ";
				} else {
					sql += "AND F.ID_NEGERI = '" + negeriUser + "'";
				}
			}
			sql = sql + " ORDER BY P.ID_PERMOHONAN DESC";
			myLogger.debug("FAIL ONLINE PEMBATALAN:" + sql);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				count_online_pembatalan = rs.getInt("TOTAL");
			}
			return count_online_pembatalan;
		} finally {
			//if (db != null)
				//db.close();
		}
	}

	Vector listing_online_pembatalan = null;

	@SuppressWarnings("unchecked")
	public Vector listing_online_pembatalan(HttpSession session,Db db)throws Exception {
		listing_online_pembatalan = new Vector();
		//Db db = null;
		listing_online_pembatalan.clear();

		String userId = (String) session.getAttribute("_ekptg_user_id");
		String negeriUser = (String) session.getAttribute("_ekptg_user_negeri");
		String sql = "";
		try {
			//db = new Db();
			Statement stmt = db.getStatement();

			sql = " SELECT "
					+ " F.ID_SUBURUSAN,P.TUJUAN,P.FLAG_JENISPERMOHONAN,F.NO_FAIL AS NO_JKPTG,P.NO_RUJUKAN_PTG,P.NO_RUJUKAN_UPT,P.NO_RUJUKAN_PTD,TO_CHAR(P.TARIKH_PERMOHONAN,'DD.MM.YYYY') AS TARIKH_PERMOHONAN,S.KETERANGAN AS STATUS, "
					+ " U.USER_NAME,P.ID_PERMOHONAN,P.ID_STATUS,P.ID_FAIL,UI.ID_NEGERI AS NEGERI_USER, P.FLAG_SEGERA, P.FLAG_SEMAK, MK.FLAG_SEMAKAN_PENGARAH "
					+ " FROM TBLPPTPERMOHONAN P,TBLPFDFAIL F,TBLRUJSTATUS S,USERS U,USERS_INTERNAL UI, TBLPPTMMK MK "
					+ " WHERE P.ID_FAIL = F.ID_FAIL"
					+ " AND S.ID_STATUS = P.ID_STATUS(+) "
					+ " AND P.ID_MASUK = U.USER_ID(+) "
					+ " AND P.ID_PERMOHONAN = MK.ID_PERMOHONAN(+)"
					+ " AND F.ID_SUBURUSAN IN (51,52,53) "
					+ " AND P.ID_STATUS NOT IN (8,999) "
					+ " AND U.USER_ID = UI.USER_ID(+) ";

			sql = sql
					+ " AND  P.ID_PERMOHONAN IN (SELECT P.ID_PERMOHONAN "
					+ " FROM TBLPPTHAKMILIK H,TBLPPTPERMOHONAN P,TBLPFDFAIL F,TBLPPTPEMBATALAN PNB,TBLPPTPEMBATALANHAKMILIK PH "
					+ " WHERE F.ID_FAIL = P.ID_FAIL AND PH.ID_HAKMILIK = H.ID_HAKMILIK AND PH.ID_PEMBATALAN = PNB.ID_PEMBATALAN "
					+

					" AND H.ID_PERMOHONAN = P.ID_PERMOHONAN " +

					" AND PNB.FLAG_ONLINE = '2'" +

					" )";

			if (!negeriUser.equals("16") && !negeriUser.isEmpty()) {
				if (negeriUser.equals("14")) {
					sql += "AND F.ID_NEGERI in (14,15,16) ";
				} else {
					sql += "AND F.ID_NEGERI = '" + negeriUser + "'";
				}
			}

			sql = sql + " ORDER BY P.ID_PERMOHONAN DESC";

			myLogger.debug("FAIL ONLINE PEMBATALAN:" + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();

				h.put("FLAG_SEMAKAN_PENGARAH",
						rs.getString("FLAG_SEMAKAN_PENGARAH") == null ? "" : rs
								.getString("FLAG_SEMAKAN_PENGARAH"));
				h.put("FLAG_SEMAK", rs.getString("FLAG_SEMAK") == null ? ""
						: rs.getString("FLAG_SEMAK"));
				h.put("FLAG_SEGERA", rs.getString("FLAG_SEGERA") == null ? ""
						: rs.getString("FLAG_SEGERA"));
				h.put("ID_SUBURUSAN", rs.getString("ID_SUBURUSAN") == null ? ""
						: rs.getString("ID_SUBURUSAN"));
				h.put("FLAG_JENISPERMOHONAN",
						rs.getString("FLAG_JENISPERMOHONAN") == null ? "" : rs
								.getString("FLAG_JENISPERMOHONAN"));
				h.put("ID_STATUS",
						rs.getString("ID_STATUS") == null ? "" : rs
								.getString("ID_STATUS"));
				h.put("NO_JKPTG",
						rs.getString("NO_JKPTG") == null ? "" : rs
								.getString("NO_JKPTG"));
				h.put("NO_RUJUKAN_PTG",
						rs.getString("NO_RUJUKAN_PTG") == null ? "" : rs
								.getString("NO_RUJUKAN_PTG"));
				h.put("NO_RUJUKAN_PTD",
						rs.getString("NO_RUJUKAN_PTD") == null ? "" : rs
								.getString("NO_RUJUKAN_PTD"));
				h.put("NO_RUJUKAN_UPT",
						rs.getString("NO_RUJUKAN_UPT") == null ? "" : rs
								.getString("NO_RUJUKAN_UPT"));
				h.put("TARIKH_PERMOHONAN",
						rs.getString("TARIKH_PERMOHONAN") == null ? "" : rs
								.getString("TARIKH_PERMOHONAN"));
				h.put("STATUS",
						rs.getString("STATUS") == null ? "" : rs
								.getString("STATUS"));
				h.put("USER_NAME",
						rs.getString("USER_NAME") == null ? "" : rs
								.getString("USER_NAME"));
				h.put("ID_PERMOHONAN",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				h.put("ID_FAIL",
						rs.getString("ID_FAIL") == null ? "" : rs
								.getString("ID_FAIL"));
				h.put("NEGERI_USER", rs.getString("NEGERI_USER") == null ? ""
						: rs.getString("NEGERI_USER"));

				if (rs.getString("ID_SUBURUSAN") != null) {

					if (rs.getString("ID_SUBURUSAN").equals("51")) {
						h.put("URUSAN", "PENGAMBILAN SEKSYEN 4");
					} else if (rs.getString("ID_SUBURUSAN").equals("52")) {
						h.put("URUSAN", "PENGAMBILAN SEKSYEN 8");
					} else if (rs.getString("ID_SUBURUSAN").equals("53")) {
						h.put("URUSAN", "PENGAMBILAN SEMENTARA");
					} else {
						h.put("URUSAN", "");
					}

				} else {
					h.put("URUSAN", "");
				}

				listing_online_pembatalan.addElement(h);
			}
			return listing_online_pembatalan;
		} finally {
			//if (db != null)
				//db.close();
		}
	}

	Integer count_online_penarikan = 0;

	@SuppressWarnings("unchecked")
	public Integer count_online_penarikan(HttpSession session,Db db) throws Exception {

		//Db db = null;
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String negeriUser = (String) session.getAttribute("_ekptg_user_negeri");
		String sql = "";
		try {
			//db = new Db();
			Statement stmt = db.getStatement();

			sql = " SELECT "
					+ " COUNT(*) AS TOTAL "
					+ " FROM TBLPPTPERMOHONAN P,TBLPFDFAIL F,TBLRUJSTATUS S,USERS U,USERS_INTERNAL UI, TBLPPTMMK MK "
					+ " WHERE P.ID_FAIL = F.ID_FAIL"
					+ " AND S.ID_STATUS = P.ID_STATUS(+) "
					+ " AND P.ID_MASUK = U.USER_ID(+) "
					+ " AND P.ID_PERMOHONAN = MK.ID_PERMOHONAN(+)"
					+ " AND F.ID_SUBURUSAN IN (51,52,53) "
					+ " AND P.ID_STATUS NOT IN (8,999) "
					+ " AND U.USER_ID = UI.USER_ID(+) ";

			sql = sql
					+ " AND  P.ID_PERMOHONAN IN (SELECT P.ID_PERMOHONAN "
					+ " FROM TBLPPTHAKMILIK H,TBLPPTPERMOHONAN P,TBLPFDFAIL F,TBLPPTPENARIKANBALIK PNB,TBLPPTPENARIKANHAKMILIK PH "
					+ " WHERE F.ID_FAIL = P.ID_FAIL AND PH.ID_HAKMILIK = H.ID_HAKMILIK AND PH.ID_PENARIKANBALIK = PNB.ID_PENARIKANBALIK "
					+

					" AND H.ID_PERMOHONAN = P.ID_PERMOHONAN " +

					" AND PNB.FLAG_ONLINE = '2'" +

					" )";

			if (!negeriUser.equals("16") && !negeriUser.isEmpty()) {
				if (negeriUser.equals("14")) {
					sql += "AND F.ID_NEGERI in (14,15,16) ";
				} else {
					sql += "AND F.ID_NEGERI = '" + negeriUser + "'";
				}
			}

			sql = sql + " ORDER BY P.ID_PERMOHONAN DESC";

			myLogger.debug("FAIL ONLINE PENARIKAN:" + sql);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				count_online_penarikan = rs.getInt("TOTAL");

			}
			return count_online_penarikan;
		} finally {
			//if (db != null)
				//db.close();
		}
	}

	Vector listing_online_penarikan = null;

	@SuppressWarnings("unchecked")
	public Vector listing_online_penarikan(HttpSession session, Db db) throws Exception {
		listing_online_penarikan = new Vector();
		//Db db = null;
		listing_online_penarikan.clear();

		String userId = (String) session.getAttribute("_ekptg_user_id");
		String negeriUser = (String) session.getAttribute("_ekptg_user_negeri");
		String sql = "";
		try {
			//db = new Db();
			Statement stmt = db.getStatement();

			sql = " SELECT "
					+ " F.ID_SUBURUSAN,P.TUJUAN,P.FLAG_JENISPERMOHONAN,F.NO_FAIL AS NO_JKPTG,P.NO_RUJUKAN_PTG,P.NO_RUJUKAN_UPT,P.NO_RUJUKAN_PTD,TO_CHAR(P.TARIKH_PERMOHONAN,'DD.MM.YYYY') AS TARIKH_PERMOHONAN,S.KETERANGAN AS STATUS, "
					+ " U.USER_NAME,P.ID_PERMOHONAN,P.ID_STATUS,P.ID_FAIL,UI.ID_NEGERI AS NEGERI_USER, P.FLAG_SEGERA, P.FLAG_SEMAK, MK.FLAG_SEMAKAN_PENGARAH "
					+ " FROM TBLPPTPERMOHONAN P,TBLPFDFAIL F,TBLRUJSTATUS S,USERS U,USERS_INTERNAL UI, TBLPPTMMK MK "
					+ " WHERE P.ID_FAIL = F.ID_FAIL"
					+ " AND S.ID_STATUS = P.ID_STATUS(+) "
					+ " AND P.ID_MASUK = U.USER_ID(+) "
					+ " AND P.ID_PERMOHONAN = MK.ID_PERMOHONAN(+)"
					+ " AND F.ID_SUBURUSAN IN (51,52,53) "
					+ " AND P.ID_STATUS NOT IN (8,999) "
					+ " AND U.USER_ID = UI.USER_ID(+) ";

			sql = sql
					+ " AND  P.ID_PERMOHONAN IN (SELECT P.ID_PERMOHONAN "
					+ " FROM TBLPPTHAKMILIK H,TBLPPTPERMOHONAN P,TBLPFDFAIL F,TBLPPTPENARIKANBALIK PNB,TBLPPTPENARIKANHAKMILIK PH "
					+ " WHERE F.ID_FAIL = P.ID_FAIL AND PH.ID_HAKMILIK = H.ID_HAKMILIK AND PH.ID_PENARIKANBALIK = PNB.ID_PENARIKANBALIK "
					+

					" AND H.ID_PERMOHONAN = P.ID_PERMOHONAN " +

					" AND PNB.FLAG_ONLINE = '2'" +

					" )";

			if (!negeriUser.equals("16") && !negeriUser.isEmpty()) {
				if (negeriUser.equals("14")) {
					sql += "AND F.ID_NEGERI in (14,15,16) ";
				} else {
					sql += "AND F.ID_NEGERI = '" + negeriUser + "'";
				}
			}

			sql = sql + " ORDER BY P.ID_PERMOHONAN DESC";

			myLogger.debug("FAIL ONLINE PENARIKAN:" + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();

				h.put("FLAG_SEMAKAN_PENGARAH",
						rs.getString("FLAG_SEMAKAN_PENGARAH") == null ? "" : rs
								.getString("FLAG_SEMAKAN_PENGARAH"));
				h.put("FLAG_SEMAK", rs.getString("FLAG_SEMAK") == null ? ""
						: rs.getString("FLAG_SEMAK"));
				h.put("FLAG_SEGERA", rs.getString("FLAG_SEGERA") == null ? ""
						: rs.getString("FLAG_SEGERA"));
				h.put("ID_SUBURUSAN", rs.getString("ID_SUBURUSAN") == null ? ""
						: rs.getString("ID_SUBURUSAN"));
				h.put("FLAG_JENISPERMOHONAN",
						rs.getString("FLAG_JENISPERMOHONAN") == null ? "" : rs
								.getString("FLAG_JENISPERMOHONAN"));
				h.put("ID_STATUS",
						rs.getString("ID_STATUS") == null ? "" : rs
								.getString("ID_STATUS"));
				h.put("NO_JKPTG",
						rs.getString("NO_JKPTG") == null ? "" : rs
								.getString("NO_JKPTG"));
				h.put("NO_RUJUKAN_PTG",
						rs.getString("NO_RUJUKAN_PTG") == null ? "" : rs
								.getString("NO_RUJUKAN_PTG"));
				h.put("NO_RUJUKAN_PTD",
						rs.getString("NO_RUJUKAN_PTD") == null ? "" : rs
								.getString("NO_RUJUKAN_PTD"));
				h.put("NO_RUJUKAN_UPT",
						rs.getString("NO_RUJUKAN_UPT") == null ? "" : rs
								.getString("NO_RUJUKAN_UPT"));
				h.put("TARIKH_PERMOHONAN",
						rs.getString("TARIKH_PERMOHONAN") == null ? "" : rs
								.getString("TARIKH_PERMOHONAN"));
				h.put("STATUS",
						rs.getString("STATUS") == null ? "" : rs
								.getString("STATUS"));
				h.put("USER_NAME",
						rs.getString("USER_NAME") == null ? "" : rs
								.getString("USER_NAME"));
				h.put("ID_PERMOHONAN",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				h.put("ID_FAIL",
						rs.getString("ID_FAIL") == null ? "" : rs
								.getString("ID_FAIL"));
				h.put("NEGERI_USER", rs.getString("NEGERI_USER") == null ? ""
						: rs.getString("NEGERI_USER"));

				if (rs.getString("ID_SUBURUSAN") != null) {

					if (rs.getString("ID_SUBURUSAN").equals("51")) {
						h.put("URUSAN", "PENGAMBILAN SEKSYEN 4");
					} else if (rs.getString("ID_SUBURUSAN").equals("52")) {
						h.put("URUSAN", "PENGAMBILAN SEKSYEN 8");
					} else if (rs.getString("ID_SUBURUSAN").equals("53")) {
						h.put("URUSAN", "PENGAMBILAN SEMENTARA");
					} else {
						h.put("URUSAN", "");
					}

				} else {
					h.put("URUSAN", "");
				}

				listing_online_penarikan.addElement(h);
			}
			return listing_online_penarikan;
		} finally {
			//if (db != null)
				//db.close();
		}
	}

	Integer count_online_bantahan_pb = null;

	@SuppressWarnings("unchecked")
	public Integer count_online_bantahan_pb(HttpSession session,Db db)
			throws Exception {

		//Db db = null;

		String userId = (String) session.getAttribute("_ekptg_user_id");
		String negeriUser = (String) session.getAttribute("_ekptg_user_negeri");
		String sql = "";
		try {
			//db = new Db();
			Statement stmt = db.getStatement();

			sql = " SELECT "
					+ " COUNT(*) AS TOTAL "
					+ " FROM TBLPPTPERMOHONAN P,TBLPFDFAIL F,TBLRUJSTATUS S,USERS U,USERS_INTERNAL UI, TBLPPTMMK MK "
					+ " WHERE P.ID_FAIL = F.ID_FAIL"
					+ " AND S.ID_STATUS = P.ID_STATUS(+) "
					+ " AND P.ID_MASUK = U.USER_ID(+) "
					+ " AND P.ID_PERMOHONAN = MK.ID_PERMOHONAN(+)"
					+ " AND F.ID_SUBURUSAN IN (51,52,53) "
					+ " AND P.ID_STATUS NOT IN (8,999) "
					+ " AND U.USER_ID = UI.USER_ID(+) ";
			sql = sql
					+ " AND  P.ID_PERMOHONAN IN (SELECT P.ID_PERMOHONAN "
					+ " FROM TBLPPTHAKMILIK H,TBLPPTHAKMILIKPB HPB,TBLPPTPERMOHONAN P,TBLPFDFAIL F,TBLPPTBANTAHAN BB,TBLPPTBORANGO O "
					+ " WHERE F.ID_FAIL = P.ID_FAIL AND BB.ID_BANTAHAN = O.ID_BANTAHAN(+) AND O.ID_BORANGO IS NULL "
					+ " AND HPB.ID_HAKMILIK = H.ID_HAKMILIK AND H.ID_PERMOHONAN = P.ID_PERMOHONAN "
					+ " AND BB.FLAG_ONLINE = '2'"
					+ "AND BB.ID_HAKMILIKPB = HPB.ID_HAKMILIKPB )";
			if (!negeriUser.equals("16") && !negeriUser.isEmpty()) {
				if (negeriUser.equals("14")) {
					sql += "AND F.ID_NEGERI in (14,15,16) ";
				} else {
					sql += "AND F.ID_NEGERI = '" + negeriUser + "'";
				}
			}
			sql = sql + " ORDER BY P.ID_PERMOHONAN DESC";
			myLogger.debug("FAIL ONLINE BANTAHAN PB:" + sql);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				count_online_bantahan_pb = rs.getInt("TOTAL");
			}
			return count_online_bantahan_pb;
		} finally {
			//if (db != null)
				//db.close();
		}
	}

	Vector listing_online_bantahan_pb = null;

	@SuppressWarnings("unchecked")
	public Vector listing_online_bantahan_pb(HttpSession session,Db db) throws Exception {
		listing_online_bantahan_pb = new Vector();
		//Db db = null;
		listing_online_bantahan_pb.clear();

		String userId = (String) session.getAttribute("_ekptg_user_id");
		String negeriUser = (String) session.getAttribute("_ekptg_user_negeri");
		String sql = "";
		try {
			//db = new Db();
			Statement stmt = db.getStatement();

			sql = " SELECT "
					+ " F.ID_SUBURUSAN,P.TUJUAN,P.FLAG_JENISPERMOHONAN,F.NO_FAIL AS NO_JKPTG,P.NO_RUJUKAN_PTG,P.NO_RUJUKAN_UPT,P.NO_RUJUKAN_PTD,TO_CHAR(P.TARIKH_PERMOHONAN,'DD.MM.YYYY') AS TARIKH_PERMOHONAN,S.KETERANGAN AS STATUS, "
					+ " U.USER_NAME,P.ID_PERMOHONAN,P.ID_STATUS,P.ID_FAIL,UI.ID_NEGERI AS NEGERI_USER, P.FLAG_SEGERA, P.FLAG_SEMAK, MK.FLAG_SEMAKAN_PENGARAH "
					+ " FROM TBLPPTPERMOHONAN P,TBLPFDFAIL F,TBLRUJSTATUS S,USERS U,USERS_INTERNAL UI, TBLPPTMMK MK "
					+ " WHERE P.ID_FAIL = F.ID_FAIL"
					+ " AND S.ID_STATUS = P.ID_STATUS(+) "
					+ " AND P.ID_MASUK = U.USER_ID(+) "
					+ " AND P.ID_PERMOHONAN = MK.ID_PERMOHONAN(+)"
					+ " AND F.ID_SUBURUSAN IN (51,52,53) "
					+ " AND P.ID_STATUS NOT IN (8,999) "
					+ " AND U.USER_ID = UI.USER_ID(+) ";

			sql = sql
					+ " AND  P.ID_PERMOHONAN IN (SELECT P.ID_PERMOHONAN "
					+ " FROM TBLPPTHAKMILIK H,TBLPPTHAKMILIKPB HPB,TBLPPTPERMOHONAN P,TBLPFDFAIL F,TBLPPTBANTAHAN BB,TBLPPTBORANGO O "
					+ " WHERE F.ID_FAIL = P.ID_FAIL AND BB.ID_BANTAHAN = O.ID_BANTAHAN(+) AND O.ID_BORANGO IS NULL "
					+ " AND HPB.ID_HAKMILIK = H.ID_HAKMILIK AND H.ID_PERMOHONAN = P.ID_PERMOHONAN "
					+ " AND BB.FLAG_ONLINE = '2'"
					+ "AND BB.ID_HAKMILIKPB = HPB.ID_HAKMILIKPB )";

			if (!negeriUser.equals("16") && !negeriUser.isEmpty()) {
				if (negeriUser.equals("14")) {
					sql += "AND F.ID_NEGERI in (14,15,16) ";
				} else {
					sql += "AND F.ID_NEGERI = '" + negeriUser + "'";
				}
			}

			sql = sql + " ORDER BY P.ID_PERMOHONAN DESC";

			myLogger.debug("FAIL ONLINE BANTAHAN PB:" + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();

				h.put("FLAG_SEMAKAN_PENGARAH",
						rs.getString("FLAG_SEMAKAN_PENGARAH") == null ? "" : rs
								.getString("FLAG_SEMAKAN_PENGARAH"));
				h.put("FLAG_SEMAK", rs.getString("FLAG_SEMAK") == null ? ""
						: rs.getString("FLAG_SEMAK"));
				h.put("FLAG_SEGERA", rs.getString("FLAG_SEGERA") == null ? ""
						: rs.getString("FLAG_SEGERA"));
				h.put("ID_SUBURUSAN", rs.getString("ID_SUBURUSAN") == null ? ""
						: rs.getString("ID_SUBURUSAN"));
				h.put("FLAG_JENISPERMOHONAN",
						rs.getString("FLAG_JENISPERMOHONAN") == null ? "" : rs
								.getString("FLAG_JENISPERMOHONAN"));
				h.put("ID_STATUS",
						rs.getString("ID_STATUS") == null ? "" : rs
								.getString("ID_STATUS"));
				h.put("NO_JKPTG",
						rs.getString("NO_JKPTG") == null ? "" : rs
								.getString("NO_JKPTG"));
				h.put("NO_RUJUKAN_PTG",
						rs.getString("NO_RUJUKAN_PTG") == null ? "" : rs
								.getString("NO_RUJUKAN_PTG"));
				h.put("NO_RUJUKAN_PTD",
						rs.getString("NO_RUJUKAN_PTD") == null ? "" : rs
								.getString("NO_RUJUKAN_PTD"));
				h.put("NO_RUJUKAN_UPT",
						rs.getString("NO_RUJUKAN_UPT") == null ? "" : rs
								.getString("NO_RUJUKAN_UPT"));
				h.put("TARIKH_PERMOHONAN",
						rs.getString("TARIKH_PERMOHONAN") == null ? "" : rs
								.getString("TARIKH_PERMOHONAN"));
				h.put("STATUS",
						rs.getString("STATUS") == null ? "" : rs
								.getString("STATUS"));
				h.put("USER_NAME",
						rs.getString("USER_NAME") == null ? "" : rs
								.getString("USER_NAME"));
				h.put("ID_PERMOHONAN",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				h.put("ID_FAIL",
						rs.getString("ID_FAIL") == null ? "" : rs
								.getString("ID_FAIL"));
				h.put("NEGERI_USER", rs.getString("NEGERI_USER") == null ? ""
						: rs.getString("NEGERI_USER"));

				if (rs.getString("ID_SUBURUSAN") != null) {

					if (rs.getString("ID_SUBURUSAN").equals("51")) {
						h.put("URUSAN", "PENGAMBILAN SEKSYEN 4");
					} else if (rs.getString("ID_SUBURUSAN").equals("52")) {
						h.put("URUSAN", "PENGAMBILAN SEKSYEN 8");
					} else if (rs.getString("ID_SUBURUSAN").equals("53")) {
						h.put("URUSAN", "PENGAMBILAN SEMENTARA");
					} else {
						h.put("URUSAN", "");
					}

				} else {
					h.put("URUSAN", "");
				}

				listing_online_bantahan_pb.addElement(h);
			}
			return listing_online_bantahan_pb;
		} finally {
			//if (db != null)
				//db.close();
		}
	}

	Integer count_online_bantahan_ag = null;

	@SuppressWarnings("unchecked")
	public Integer count_online_bantahan_ag(HttpSession session,Db db)throws Exception {

		//Db db = null;

		String userId = (String) session.getAttribute("_ekptg_user_id");
		String negeriUser = (String) session.getAttribute("_ekptg_user_negeri");
		String sql = "";
		try {
			//db = new Db();
			Statement stmt = db.getStatement();

			sql = " SELECT "
					+ " COUNT(*) AS TOTAL "
					+ " FROM TBLPPTPERMOHONAN P,TBLPFDFAIL F,TBLRUJSTATUS S,USERS U,USERS_INTERNAL UI, TBLPPTMMK MK "
					+ " WHERE P.ID_FAIL = F.ID_FAIL"
					+ " AND S.ID_STATUS = P.ID_STATUS(+) "
					+ " AND P.ID_MASUK = U.USER_ID(+) "
					+ " AND P.ID_PERMOHONAN = MK.ID_PERMOHONAN(+)"
					+ " AND F.ID_SUBURUSAN IN (51,52,53) "
					+ " AND P.ID_STATUS NOT IN (8,999) "
					+ " AND U.USER_ID = UI.USER_ID(+) ";

			sql = sql
					+ " AND P.ID_PERMOHONAN IN "
					+ " (SELECT P.ID_PERMOHONAN FROM TBLPPTHAKMILIK H,TBLPPTPERMOHONAN P,TBLPFDFAIL F,TBLPPTBANTAHAN BB,TBLPPTBORANGO O "
					+ " WHERE F.ID_FAIL = P.ID_FAIL AND H.ID_HAKMILIK = BB.ID_HAKMILIK AND BB.ID_BANTAHAN = O.ID_BANTAHAN(+) AND O.ID_BORANGO is null "
					+

					" AND BB.FLAG_ONLINE = '2'" +

					"AND P.ID_PERMOHONAN = H.ID_PERMOHONAN) ";

			if (!negeriUser.equals("16") && !negeriUser.isEmpty()) {
				if (negeriUser.equals("14")) {
					sql += "AND F.ID_NEGERI in (14,15,16) ";
				} else {
					sql += "AND F.ID_NEGERI = '" + negeriUser + "'";
				}
			}

			sql = sql + " ORDER BY P.ID_PERMOHONAN DESC";

			myLogger.debug("FAIL ONLINE BANTAHAN AGENSI:" + sql);

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				count_online_bantahan_ag = rs.getInt("TOTAL");
			}
			return count_online_bantahan_ag;
		} finally {
			//if (db != null)
				//db.close();
		}
	}

	Vector listing_online_bantahan_agensi = null;

	@SuppressWarnings("unchecked")
	public Vector listing_online_bantahan_agensi(HttpSession session,Db db)throws Exception {
		listing_online_bantahan_agensi = new Vector();
		//Db db = null;
		listing_online_bantahan_agensi.clear();

		String userId = (String) session.getAttribute("_ekptg_user_id");
		String negeriUser = (String) session.getAttribute("_ekptg_user_negeri");
		String sql = "";
		try {
			//db = new Db();
			Statement stmt = db.getStatement();

			sql = " SELECT "
					+ " F.ID_SUBURUSAN,P.TUJUAN,P.FLAG_JENISPERMOHONAN,F.NO_FAIL AS NO_JKPTG,P.NO_RUJUKAN_PTG,P.NO_RUJUKAN_UPT,P.NO_RUJUKAN_PTD,TO_CHAR(P.TARIKH_PERMOHONAN,'DD.MM.YYYY') AS TARIKH_PERMOHONAN,S.KETERANGAN AS STATUS, "
					+ " U.USER_NAME,P.ID_PERMOHONAN,P.ID_STATUS,P.ID_FAIL,UI.ID_NEGERI AS NEGERI_USER, P.FLAG_SEGERA, P.FLAG_SEMAK, MK.FLAG_SEMAKAN_PENGARAH "
					+ " FROM TBLPPTPERMOHONAN P,TBLPFDFAIL F,TBLRUJSTATUS S,USERS U,USERS_INTERNAL UI, TBLPPTMMK MK "
					+ " WHERE P.ID_FAIL = F.ID_FAIL"
					+ " AND S.ID_STATUS = P.ID_STATUS(+) "
					+ " AND P.ID_MASUK = U.USER_ID(+) "
					+ " AND P.ID_PERMOHONAN = MK.ID_PERMOHONAN(+)"
					+ " AND F.ID_SUBURUSAN IN (51,52,53) "
					+ " AND P.ID_STATUS NOT IN (8,999) "
					+ " AND U.USER_ID = UI.USER_ID(+) ";

			sql = sql
					+ " AND P.ID_PERMOHONAN IN "
					+ " (SELECT P.ID_PERMOHONAN FROM TBLPPTHAKMILIK H,TBLPPTPERMOHONAN P,TBLPFDFAIL F,TBLPPTBANTAHAN BB,TBLPPTBORANGO O "
					+ " WHERE F.ID_FAIL = P.ID_FAIL AND H.ID_HAKMILIK = BB.ID_HAKMILIK AND BB.ID_BANTAHAN = O.ID_BANTAHAN(+) AND O.ID_BORANGO is null "
					+

					" AND BB.FLAG_ONLINE = '2'" +

					"AND P.ID_PERMOHONAN = H.ID_PERMOHONAN) ";

			if (!negeriUser.equals("16") && !negeriUser.isEmpty()) {
				if (negeriUser.equals("14")) {
					sql += "AND F.ID_NEGERI in (14,15,16) ";
				} else {
					sql += "AND F.ID_NEGERI = '" + negeriUser + "'";
				}
			}

			sql = sql + " ORDER BY P.ID_PERMOHONAN DESC";

			myLogger.debug("FAIL ONLINE BANTAHAN AGENSI:" + sql);

			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			while (rs.next()) {
				h = new Hashtable();

				h.put("FLAG_SEMAKAN_PENGARAH",
						rs.getString("FLAG_SEMAKAN_PENGARAH") == null ? "" : rs
								.getString("FLAG_SEMAKAN_PENGARAH"));
				h.put("FLAG_SEMAK", rs.getString("FLAG_SEMAK") == null ? ""
						: rs.getString("FLAG_SEMAK"));
				h.put("FLAG_SEGERA", rs.getString("FLAG_SEGERA") == null ? ""
						: rs.getString("FLAG_SEGERA"));
				h.put("ID_SUBURUSAN", rs.getString("ID_SUBURUSAN") == null ? ""
						: rs.getString("ID_SUBURUSAN"));
				h.put("FLAG_JENISPERMOHONAN",
						rs.getString("FLAG_JENISPERMOHONAN") == null ? "" : rs
								.getString("FLAG_JENISPERMOHONAN"));
				h.put("ID_STATUS",
						rs.getString("ID_STATUS") == null ? "" : rs
								.getString("ID_STATUS"));
				h.put("NO_JKPTG",
						rs.getString("NO_JKPTG") == null ? "" : rs
								.getString("NO_JKPTG"));
				h.put("NO_RUJUKAN_PTG",
						rs.getString("NO_RUJUKAN_PTG") == null ? "" : rs
								.getString("NO_RUJUKAN_PTG"));
				h.put("NO_RUJUKAN_PTD",
						rs.getString("NO_RUJUKAN_PTD") == null ? "" : rs
								.getString("NO_RUJUKAN_PTD"));
				h.put("NO_RUJUKAN_UPT",
						rs.getString("NO_RUJUKAN_UPT") == null ? "" : rs
								.getString("NO_RUJUKAN_UPT"));
				h.put("TARIKH_PERMOHONAN",
						rs.getString("TARIKH_PERMOHONAN") == null ? "" : rs
								.getString("TARIKH_PERMOHONAN"));
				h.put("STATUS",
						rs.getString("STATUS") == null ? "" : rs
								.getString("STATUS"));
				h.put("USER_NAME",
						rs.getString("USER_NAME") == null ? "" : rs
								.getString("USER_NAME"));
				h.put("ID_PERMOHONAN",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				h.put("ID_FAIL",
						rs.getString("ID_FAIL") == null ? "" : rs
								.getString("ID_FAIL"));
				h.put("NEGERI_USER", rs.getString("NEGERI_USER") == null ? ""
						: rs.getString("NEGERI_USER"));

				if (rs.getString("ID_SUBURUSAN") != null) {

					if (rs.getString("ID_SUBURUSAN").equals("51")) {
						h.put("URUSAN", "PENGAMBILAN SEKSYEN 4");
					} else if (rs.getString("ID_SUBURUSAN").equals("52")) {
						h.put("URUSAN", "PENGAMBILAN SEKSYEN 8");
					} else if (rs.getString("ID_SUBURUSAN").equals("53")) {
						h.put("URUSAN", "PENGAMBILAN SEMENTARA");
					} else {
						h.put("URUSAN", "");
					}

				} else {
					h.put("URUSAN", "");
				}

				listing_online_bantahan_agensi.addElement(h);
			}
			return listing_online_bantahan_agensi;
		} finally {
			//if (db != null)
				//db.close();
		}
	}

	public Hashtable stat_bar(HttpSession session, String role, String user_id,Db db) throws Exception {
		//Db db = null;
		String sql = "";
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String negeriUser = (String) session.getAttribute("_ekptg_user_negeri");
		try {

			//db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			/*
			sql = " SELECT TO_CHAR ( "
					+ " ( SELECT COUNT (DISTINCT PP.ID_PERMOHONAN) FROM TBLRUJSUBURUSANSTATUSFAILPPT SHM, "
					+ " TBLPPTPERMOHONAN PP, TBLPFDFAIL F, TBLRUJSTATUS ST, TBLRUJSUBURUSANSTATUS SST "
					+ " WHERE SHM.ID_FAIL = F.ID_FAIL AND PP.TARIKH_PERMOHONAN IS NOT NULL           "
					+ " AND PP.ID_FAIL = F.ID_FAIL AND SHM.ID_SUBURUSANSTATUS = SST.ID_SUBURUSANSTATUS "
					+ " AND SST.ID_STATUS = ST.ID_STATUS AND F.ID_SUBURUSAN = '52'   ";
			if (!negeriUser.equals("16") && !negeriUser.isEmpty()) {
				if (negeriUser.equals("14")) {
					sql += "AND F.ID_NEGERI in (14,15,16) ";
				} else {
					sql += "AND F.ID_NEGERI = '" + negeriUser + "'";
				}
			}
			sql += " )             "
					+ " ) AS DITERIMA, "
					+ " TO_CHAR (( SELECT COUNT (DISTINCT PX.ID_PERMOHONAN) FROM TBLRUJSUBURUSANSTATUSFAILPPT SHM, "
					+ " TBLPPTPERMOHONAN PX, TBLPFDFAIL F, TBLRUJSTATUS ST, TBLRUJSUBURUSANSTATUS SST "
					+ " WHERE SHM.ID_FAIL = F.ID_FAIL AND PX.TARIKH_PERMOHONAN IS NOT NULL              "
					+ " AND PX.ID_FAIL = F.ID_FAIL AND SHM.ID_SUBURUSANSTATUS = SST.ID_SUBURUSANSTATUS "
					+ " AND SST.ID_STATUS = ST.ID_STATUS AND F.ID_SUBURUSAN = '52' "
					+ " AND F.ID_NEGERI = '10' AND (SELECT COUNT (DISTINCT HMK.ID_HAKMILIK) "
					+ " FROM TBLRUJSUBURUSANSTATUSFAILPPT SHM, TBLPPTHAKMILIK HMK, "
					+ " TBLPPTPERMOHONAN PP, TBLPFDFAIL F, TBLRUJSTATUS ST, "
					+ " TBLRUJSUBURUSANSTATUS SST WHERE SHM.ID_FAIL = F.ID_FAIL "
					+ " AND HMK.ID_PERMOHONAN = PP.ID_PERMOHONAN AND PP.TARIKH_PERMOHONAN IS NOT NULL     "
					+ " AND PP.ID_FAIL = F.ID_FAIL AND PP.ID_PERMOHONAN = PX.ID_PERMOHONAN "
					+ " AND SHM.ID_SUBURUSANSTATUS = SST.ID_SUBURUSANSTATUS  "
					+ " AND SST.ID_STATUS = ST.ID_STATUS AND F.ID_SUBURUSAN = '52'     ";
			if (!negeriUser.equals("16") && !negeriUser.isEmpty()) {
				if (negeriUser.equals("14")) {
					sql += "AND F.ID_NEGERI in (14,15,16) ";
				} else {
					sql += "AND F.ID_NEGERI = '" + negeriUser + "'";
				}
			}
			sql += " ) > 0 AND (SELECT COUNT (DISTINCT HMK.ID_HAKMILIK) "
					+ " FROM TBLRUJSUBURUSANSTATUSFAILPPT SHM, TBLPPTHAKMILIK HMK, TBLPPTPERMOHONAN PP, "
					+ " TBLPFDFAIL F, TBLRUJSTATUS ST, TBLRUJSUBURUSANSTATUS SST "
					+ " WHERE SHM.ID_FAIL = F.ID_FAIL AND HMK.ID_PERMOHONAN = PP.ID_PERMOHONAN "
					+ " AND PP.TARIKH_PERMOHONAN IS NOT NULL AND PP.ID_FAIL = F.ID_FAIL "
					+ " AND PP.ID_PERMOHONAN = PX.ID_PERMOHONAN AND SHM.ID_SUBURUSANSTATUS = SST.ID_SUBURUSANSTATUS "
					+ " AND SST.ID_STATUS = ST.ID_STATUS AND F.ID_SUBURUSAN = '52' ";
			if (!negeriUser.equals("16") && !negeriUser.isEmpty()) {
				if (negeriUser.equals("14")) {
					sql += "AND F.ID_NEGERI in (14,15,16) ";
				} else {
					sql += "AND F.ID_NEGERI = '" + negeriUser + "'";
				}
			}
			sql += " ) = (SELECT COUNT (DISTINCT HMK.ID_HAKMILIK) FROM TBLRUJSUBURUSANSTATUSHAKMILIK SHMK, "
					+ " TBLPPTHAKMILIK HMK, TBLPPTPERMOHONAN PP, TBLPFDFAIL F, TBLRUJSTATUS STK, "
					+ " TBLRUJSUBURUSANSTATUS SSTK WHERE SHMK.ID_HAKMILIK = HMK.ID_HAKMILIK "
					+ " AND HMK.ID_PERMOHONAN = PP.ID_PERMOHONAN AND PP.ID_PERMOHONAN = PX.ID_PERMOHONAN "
					+ " AND PP.TARIKH_PERMOHONAN IS NOT NULL AND PP.ID_FAIL = F.ID_FAIL "
					+ " AND SHMK.ID_SUBURUSANSTATUS = SSTK.ID_SUBURUSANSTATUS "
					+ " AND SSTK.ID_STATUS = STK.ID_STATUS AND STK.ID_STATUS IN (1610242, 1610233) "
					+ " AND F.ID_SUBURUSAN = '52'  ";
			if (!negeriUser.equals("16") && !negeriUser.isEmpty()) {
				if (negeriUser.equals("14")) {
					sql += "AND F.ID_NEGERI in (14,15,16) ";
				} else {
					sql += "AND F.ID_NEGERI = '" + negeriUser + "'";
				}
			}
			sql += " )                "
					+ " ) "
					+ " ) AS DISELESAI, "
					+ " TO_CHAR ((SELECT COUNT (DISTINCT HMK.ID_HAKMILIK) FROM TBLRUJSUBURUSANSTATUSFAILPPT SHM, "
					+ " TBLPPTHAKMILIK HMK, TBLPPTPERMOHONAN PP, TBLPFDFAIL F, TBLRUJSTATUS ST, "
					+ " TBLRUJSUBURUSANSTATUS SST WHERE SHM.ID_FAIL = F.ID_FAIL "
					+ " AND HMK.ID_PERMOHONAN = PP.ID_PERMOHONAN AND PP.TARIKH_PERMOHONAN IS NOT NULL "
					+ " AND PP.ID_FAIL = F.ID_FAIL AND SHM.ID_SUBURUSANSTATUS = SST.ID_SUBURUSANSTATUS "
					+ " AND SST.ID_STATUS = ST.ID_STATUS AND F.ID_SUBURUSAN = '52'       ";
			if (!negeriUser.equals("16") && !negeriUser.isEmpty()) {
				if (negeriUser.equals("14")) {
					sql += "AND F.ID_NEGERI in (14,15,16) ";
				} else {
					sql += "AND F.ID_NEGERI = '" + negeriUser + "'";
				}
			}
			sql += " ) "
					+ " ) AS LOT_DITERIMA, "
					+ " TO_CHAR ((SELECT COUNT (DISTINCT HMK.ID_HAKMILIK) FROM TBLRUJSUBURUSANSTATUSHAKMILIK SHMK, "
					+ " TBLPPTHAKMILIK HMK, TBLPPTPERMOHONAN PP, TBLPFDFAIL F, TBLRUJSTATUS STK, "
					+ " TBLRUJSUBURUSANSTATUS SSTK WHERE SHMK.ID_HAKMILIK = HMK.ID_HAKMILIK "
					+ " AND HMK.ID_PERMOHONAN = PP.ID_PERMOHONAN AND PP.TARIKH_PERMOHONAN IS NOT NULL "
					+ " AND PP.ID_FAIL = F.ID_FAIL AND SHMK.ID_SUBURUSANSTATUS = SSTK.ID_SUBURUSANSTATUS "
					+ " AND SSTK.ID_STATUS = STK.ID_STATUS AND STK.ID_STATUS IN (1610242, 1610233) "
					+ " AND F.ID_SUBURUSAN = '52' ";
			if (!negeriUser.equals("16") && !negeriUser.isEmpty()) {
				if (negeriUser.equals("14")) {
					sql += "AND F.ID_NEGERI in (14,15,16) ";
				} else {
					sql += "AND F.ID_NEGERI = '" + negeriUser + "'";
				}
			}
			sql += " ) " + " ) AS LOT_DISELESAI " + " FROM DUAL ";
			*/
			
			
			sql = " SELECT "+
					//"  --MAIN.ID_NEGERI,NEG.NAMA_NEGERI, "+
					"  COUNT(DISTINCT MAIN.ID_FAIL) AS DITERIMA, COUNT(CASE WHEN MAIN.HM_DITERIMA = MAIN.HM_SELESAI AND MAIN.HM_DITERIMA > 0 THEN MAIN.ID_FAIL END) AS DISELESAI, "+
					"  SUM(DISTINCT MAIN.HM_DITERIMA) AS LOT_DITERIMA, "+
					"  SUM(DISTINCT MAIN.HM_SELESAI) AS LOT_DISELESAI "+
					"  FROM "+
					"  (           "+                              
					"  SELECT F.ID_FAIL,F.ID_NEGERI, "+
					"  COUNT(DISTINCT (CASE WHEN HM.ID_HAKMILIK IS NOT NULL THEN HM.ID_HAKMILIK END)) AS HM_DITERIMA, "+
					"  COUNT(CASE WHEN SSTK.ID_STATUS IN (1610242, 1610233) THEN HM.ID_HAKMILIK END) AS HM_SELESAI "+
					"  FROM TBLPFDFAIL F, TBLPPTPERMOHONAN P, TBLPPTHAKMILIK HM, TBLRUJSUBURUSANSTATUSHAKMILIK SHMK, TBLRUJSUBURUSANSTATUS SSTK "+
					"  WHERE F.ID_FAIL = P.ID_FAIL "+
					"  AND P.TARIKH_PERMOHONAN IS NOT NULL "+
					"  AND F.ID_SUBURUSAN = '52' ";
					if (!negeriUser.equals("16") && !negeriUser.isEmpty()) {
						/*if (negeriUser.equals("14")) {
							sql += "AND F.ID_NEGERI in (14,15,16) ";
						} else {
							sql += "AND F.ID_NEGERI = '" + negeriUser + "'";
						}*/
						sql += "  AND F.ID_NEGERI = '" + negeriUser + "' ";
					}
					
					sql += "  AND P.ID_PERMOHONAN = HM.ID_PERMOHONAN "+
					"  AND HM.ID_HAKMILIK = SHMK.ID_HAKMILIK(+) "+
					"  AND SHMK.ID_SUBURUSANSTATUS = SSTK.ID_SUBURUSANSTATUS(+) "+
					"  GROUP BY F.ID_FAIL,F.ID_NEGERI         "+                            
					"  ) MAIN, TBLRUJNEGERI NEG "+
					"  WHERE MAIN.ID_NEGERI = NEG.ID_NEGERI ";
					//"  --GROUP BY MAIN.ID_NEGERI, NEG.NAMA_NEGERI ";
			
			
			myLogger.info(" STAT BAR :::::::::::::::::" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				if (rs.getString("DITERIMA") == null) {
					h.put("DITERIMA", "");
				} else {
					h.put("DITERIMA", rs.getString("DITERIMA"));
				}
				if (rs.getString("LOT_DITERIMA") == null) {
					h.put("LOT_DITERIMA", "");
				} else {
					h.put("LOT_DITERIMA", rs.getString("LOT_DITERIMA"));
				}
				if (rs.getString("DISELESAI") == null) {
					h.put("DISELESAI", "");
				} else {
					h.put("DISELESAI", rs.getString("DISELESAI"));
				}
				if (rs.getString("LOT_DISELESAI") == null) {
					h.put("LOT_DISELESAI", "");
				} else {
					h.put("LOT_DISELESAI", rs.getString("LOT_DISELESAI")
							.toUpperCase());
				}
			}
			return h;
		} finally {
			//if (db != null)
				//db.close();
		}
	}

	public Hashtable stat_fail_ppt(HttpSession session, String role,
			String user_id,Db db) throws Exception {
		//Db db = null;
		String sql = "";
		String userId = (String) session.getAttribute("_ekptg_user_id");
		String negeriUser = (String) session.getAttribute("_ekptg_user_negeri");
		try {

			//db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			
			/*
			sql = " SELECT ";

			sql += "(";
			sql += " SELECT COUNT(*) AS SEK_4 FROM TBLPPTPERMOHONAN P,TBLPFDFAIL F "
					+ " WHERE P.ID_FAIL = F.ID_FAIL AND ID_SUBURUSAN IN (51) ";
			if (!negeriUser.equals("16") && !negeriUser.isEmpty()) {
				if (negeriUser.equals("14")) {
					sql += "AND F.ID_NEGERI in (14,15,16) ";
				} else {
					sql += "AND F.ID_NEGERI = '" + negeriUser + "'";
				}
			}
			sql += ") AS SEK_4,";

			sql += "(";
			sql += " SELECT COUNT(*) AS SEK_8 FROM TBLPPTPERMOHONAN P,TBLPFDFAIL F "
					+ " WHERE P.ID_FAIL = F.ID_FAIL AND ID_SUBURUSAN IN (52) ";
			if (!negeriUser.equals("16") && !negeriUser.isEmpty()) {
				if (negeriUser.equals("14")) {
					sql += "AND F.ID_NEGERI in (14,15,16) ";
				} else {
					sql += "AND F.ID_NEGERI = '" + negeriUser + "'";
				}
			}
			sql += ") AS SEK_8,";

			sql += "(";
			sql += " SELECT COUNT(*) AS SEK_SEMENTARA FROM TBLPPTPERMOHONAN P,TBLPFDFAIL F "
					+ " WHERE P.ID_FAIL = F.ID_FAIL AND ID_SUBURUSAN IN (53) ";
			if (!negeriUser.equals("16") && !negeriUser.isEmpty()) {
				if (negeriUser.equals("14")) {
					sql += "AND F.ID_NEGERI in (14,15,16) ";
				} else {
					sql += "AND F.ID_NEGERI = '" + negeriUser + "'";
				}
			}
			sql += ") AS SEK_SEMENTARA,";

			sql += " (SELECT UPPER(NAMA_NEGERI) FROM TBLRUJNEGERI WHERE ID_NEGERI = '"
					+ negeriUser + "') AS NAMA_NEGERI ";

			sql += " FROM DUAL";
			*/
			
			// razman revamp query
			
			
			sql = " SELECT NAMA_NEGERI,MAIN.SEK_4,MAIN.SEK_8,MAIN.SEK_SEMENTARA FROM TBLRUJNEGERI NEG, "+
					" (SELECT  COUNT(CASE WHEN ID_SUBURUSAN = 51 THEN 1 END) AS SEK_4,   "+
					" COUNT(CASE WHEN ID_SUBURUSAN = 52 THEN 1 END) AS SEK_8,  COUNT(CASE WHEN ID_SUBURUSAN = 53 THEN 1 END) AS SEK_SEMENTARA "+
					" FROM TBLPPTPERMOHONAN P, TBLPFDFAIL F, TBLRUJNEGERI N  " +
					" WHERE P.ID_FAIL = F.ID_FAIL ";
			
					if (!negeriUser.equals("16") && !negeriUser.isEmpty()) {
						sql += "AND F.ID_NEGERI = '" + negeriUser + "'";
					}
			
					sql += " AND F.ID_NEGERI = N.ID_NEGERI) MAIN ";
					
					sql += " WHERE NEG.ID_NEGERI = '" + negeriUser + "'";
					
			
			
			
			
			myLogger.info(" stat_fail_ppt :" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				if (rs.getString("SEK_4") == null) {
					h.put("SEK_4", "");
				} else {
					h.put("SEK_4", rs.getString("SEK_4"));
				}
				if (rs.getString("SEK_8") == null) {
					h.put("SEK_8", "");
				} else {
					h.put("SEK_8", rs.getString("SEK_8"));
				}
				if (rs.getString("SEK_SEMENTARA") == null) {
					h.put("SEK_SEMENTARA", "");
				} else {
					h.put("SEK_SEMENTARA", rs.getString("SEK_SEMENTARA"));
				}
				if (rs.getString("NAMA_NEGERI") == null) {
					h.put("NAMA_NEGERI", "");
				} else {
					h.put("NAMA_NEGERI", rs.getString("NAMA_NEGERI")
							.toUpperCase());
				}
			}
			return h;
		} finally {
			//if (db != null)
				//db.close();
		}
	}

	public Hashtable stat_fail(String role, String user_id,Db db) throws Exception {

		//Db db = null;
		String sql = "";
		Hashtable get_negeri = kod_negeri(db);
		String kod_negeri = (String) get_negeri.get("KOD_NEGERI");
		try {
			//db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "";

			if (!kod_negeri.equals("16")) {
				sql += " SELECT (SELECT INITCAP(U.NAMA_PEJABAT) FROM TBLRUJPEJABATJKPTG U, USERS_INTERNAL UR WHERE U.ID_PEJABATJKPTG = UR.ID_PEJABATJKPTG AND UR.USER_ID = '"
						+ user_id + "' ) AS NAMA_NEGERI_SERVER,  ";
			} else {
				sql += " SELECT (SELECT INITCAP(N.NAMA_NEGERI) FROM TBLLOOKUPSTATE S,TBLRUJNEGERI N WHERE S.KOD_NEGERI = N.KOD_NEGERI ) AS NAMA_NEGERI_SERVER,  ";
			}

			sql += " (SELECT TO_CHAR(COUNT(*),'999,999,999') FROM TBLPPKPERMOHONAN P,TBLPFDFAIL F,TBLRUJNEGERI N WHERE P.ID_FAIL = F.ID_FAIL AND P.ID_NEGERIMHN = N.ID_NEGERI ";

			if (!kod_negeri.equals("16")) {
				// sql += " AND N.KOD_NEGERI = '"+kod_negeri+"' ";
				sql += "AND P.ID_DAERAHMHN in ( select distinct u.id_daerahurus from"
						+ " TBLRUJPEJABATURUSAN u, users_internal ur "
						+ " where u.id_pejabatjkptg=ur.id_pejabatjkptg and ur.user_id='"
						+ user_id + "')";
			}
			sql += " ) AS JUMLAH_KESELURUHAN,  ";

			sql += " (SELECT TO_CHAR(COUNT(*),'999,999,999') FROM TBLPPKPERMOHONAN P,TBLPFDFAIL F,TBLRUJNEGERI N WHERE P.ID_FAIL = F.ID_FAIL AND P.ID_NEGERIMHN = N.ID_NEGERI ";
			if (!kod_negeri.equals("16")) {
				// sql += " AND N.KOD_NEGERI = '"+kod_negeri+"' ";
				sql += "AND P.ID_DAERAHMHN in ( select distinct u.id_daerahurus from"
						+ " TBLRUJPEJABATURUSAN u, users_internal ur "
						+ " where u.id_pejabatjkptg=ur.id_pejabatjkptg and ur.user_id='"
						+ user_id + "')";
			}
			sql += " AND SEKSYEN = '8' AND P.ID_STATUS <> '999') AS JUMLAH_KESELURUHAN_SEK8,  ";

			sql += " (SELECT TO_CHAR(COUNT(*),'999,999,999') FROM TBLPPKPERMOHONAN P,TBLPFDFAIL F,TBLRUJNEGERI N WHERE P.ID_FAIL = F.ID_FAIL AND P.ID_NEGERIMHN = N.ID_NEGERI ";
			if (!kod_negeri.equals("16")) {
				// sql += " AND N.KOD_NEGERI = '"+kod_negeri+"' ";

				sql += "AND P.ID_DAERAHMHN in ( select distinct u.id_daerahurus from"
						+ " TBLRUJPEJABATURUSAN u, users_internal ur "
						+ " where u.id_pejabatjkptg=ur.id_pejabatjkptg and ur.user_id='"
						+ user_id + "')";
			}
			sql += " AND SEKSYEN = '17' AND P.ID_STATUS <> '999') AS JUMLAH_KESELURUHAN_SEK17,  ";

			sql += "(SELECT COUNT(*) FROM TBLPPKPERMOHONAN P,TBLPFDFAIL F,TBLRUJNEGERI N WHERE P.ID_FAIL = F.ID_FAIL AND P.ID_NEGERIMHN = N.ID_NEGERI ";
			if (!kod_negeri.equals("16")) {
				// sql += " AND N.KOD_NEGERI = '"+kod_negeri+"' ";
				sql += "AND P.ID_DAERAHMHN in ( select distinct u.id_daerahurus from"
						+ " TBLRUJPEJABATURUSAN u, users_internal ur "
						+ " where u.id_pejabatjkptg=ur.id_pejabatjkptg and ur.user_id='"
						+ user_id + "')";
			}
			sql += " AND P.ID_STATUS = '21' AND P.ID_STATUS <> '999' AND P.SEKSYEN IN (8,17)) AS JUMLAH_SELESAI,  ";

			sql += " (SELECT COUNT(*) FROM TBLPPKPERMOHONAN P,TBLPFDFAIL F,TBLRUJNEGERI N WHERE P.ID_FAIL = F.ID_FAIL AND P.ID_NEGERIMHN = N.ID_NEGERI ";
			if (!kod_negeri.equals("16")) {
				// sql += " AND N.KOD_NEGERI = '"+kod_negeri+"' ";
				sql += "AND P.ID_DAERAHMHN in ( select distinct u.id_daerahurus from"
						+ " TBLRUJPEJABATURUSAN u, users_internal ur "
						+ " where u.id_pejabatjkptg=ur.id_pejabatjkptg and ur.user_id='"
						+ user_id + "')";
			}
			sql += " AND P.ID_STATUS <> '21' AND P.ID_STATUS <> '999' AND P.SEKSYEN IN (8,17)) AS JUMLAH_XSELESAI, ";

			sql += " (SELECT TO_CHAR(COUNT(*),'999,999,999') FROM TBLPPKPERMOHONAN P,TBLPFDFAIL F,TBLRUJNEGERI N WHERE P.ID_FAIL = F.ID_FAIL AND P.ID_NEGERIMHN = N.ID_NEGERI ";
			if (!kod_negeri.equals("16")) {
				// sql += " AND N.KOD_NEGERI = '"+kod_negeri+"' ";
				sql += " AND P.ID_DAERAHMHN in ( select distinct u.id_daerahurus from"
						+ " TBLRUJPEJABATURUSAN u, users_internal ur "
						+ " where u.id_pejabatjkptg=ur.id_pejabatjkptg and ur.user_id='"
						+ user_id + "')";
			}
			sql += " AND P.ID_STATUS = '999') AS JUMLAH_FAIL_HAPUS  " +

			"FROM DUAL ";

			myLogger.info(" STATISTIK :" + sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				if (rs.getString("NAMA_NEGERI_SERVER") == null) {
					h.put("NAMA_NEGERI_SERVER", "");
				} else {
					h.put("NAMA_NEGERI_SERVER",
							rs.getString("NAMA_NEGERI_SERVER"));
				}
				if (rs.getString("JUMLAH_KESELURUHAN") == null) {
					h.put("JUMLAH_KESELURUHAN", "");
				} else {
					h.put("JUMLAH_KESELURUHAN",
							rs.getString("JUMLAH_KESELURUHAN"));
				}
				if (rs.getString("JUMLAH_KESELURUHAN_SEK8") == null) {
					h.put("JUMLAH_KESELURUHAN_SEK8", "");
				} else {
					h.put("JUMLAH_KESELURUHAN_SEK8",
							rs.getString("JUMLAH_KESELURUHAN_SEK8"));
				}
				if (rs.getString("JUMLAH_KESELURUHAN_SEK17") == null) {
					h.put("JUMLAH_KESELURUHAN_SEK17", "");
				} else {
					h.put("JUMLAH_KESELURUHAN_SEK17",
							rs.getString("JUMLAH_KESELURUHAN_SEK17"));
				}
				if (rs.getString("JUMLAH_FAIL_HAPUS") == null) {
					h.put("JUMLAH_FAIL_HAPUS", "");
				} else {
					h.put("JUMLAH_FAIL_HAPUS",
							rs.getString("JUMLAH_FAIL_HAPUS"));
				}
				if (rs.getString("JUMLAH_SELESAI") == null) {
					h.put("JUMLAH_SELESAI", "");
				} else {
					h.put("JUMLAH_SELESAI", rs.getString("JUMLAH_SELESAI"));
				}
				if (rs.getString("JUMLAH_XSELESAI") == null) {
					h.put("JUMLAH_XSELESAI", "");
				} else {
					h.put("JUMLAH_XSELESAI", rs.getString("JUMLAH_XSELESAI"));
				}
			}
			return h;
		} finally {
			//if (db != null)
				//db.close();
		}
	}

	public Hashtable kod_negeri(Db db) throws Exception {

		//Db db = null;
		String sql = "";
		try {
			//db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = "SELECT KOD_NEGERI FROM TBLLOOKUPSTATE S ";
			myLogger.info(" KOD_NEGERI :" + sql.toUpperCase());

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				if (rs.getString("KOD_NEGERI") == null) {
					h.put("KOD_NEGERI", "");
				} else {
					h.put("KOD_NEGERI", rs.getString("KOD_NEGERI"));
				}

			}
			return h;
		} finally {
			//if (db != null)
				//db.close();
		}
	}

	public Hashtable notifikasi_borangB(String role, String id_negeri_user,
			String id_esaduan, String flag_notifikasi, String user_terima,
			String notread,Db db) throws Exception {

		//Db db = null;
		String sql = "";
		try {
			//db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql += " SELECT ";

			sql += " (SELECT COUNT(*) AS TOTAL_FAIL  "
					+ " FROM TBLPPKPERMOHONAN PPP, TBLPFDFAIL FFF "
					+ " WHERE PPP.ID_FAIL = FFF.ID_FAIL AND "
					+ " (SELECT COUNT(*) FROM TBLPPKPERMOHONAN A, TBLPFDFAIL FF, TBLRUJSTATUS S, TBLPPKSIMATI P, TBLPPKPEMOHON PM, "
					+ " TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D "
					+ " WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR "
					+ " WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"
					+ user_terima
					+ "') "
					+ " AND ST.ID_STATUS = S.ID_STATUS(+) AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+) "
					+ " AND A.ID_PEMOHON = PM.ID_PEMOHON(+) AND A.ID_FAIL = FF.ID_FAIL(+) AND A.ID_DAERAHMHN = D.ID_DAERAH "
					+ " AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN AND P.ID_SIMATI = MS.ID_SIMATI "
					+ " AND A.ID_STATUS <> '999' "
					+ " AND S.ID_STATUS = '14' AND FFF.ID_FAIL = FF.ID_FAIL) = 0 AND "
					+ " ( "
					+ " SELECT COUNT(*) FROM TBLPPKPERMOHONAN A, TBLPFDFAIL F, TBLRUJSTATUS S, TBLPPKSIMATI P, TBLPPKPEMOHON PM, "
					+ " TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D "
					+ " WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR "
					+ " WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"
					+ user_terima
					+ "')  "
					+ " AND ST.ID_STATUS = S.ID_STATUS(+) AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+) "
					+ " AND A.ID_PEMOHON = PM.ID_PEMOHON(+) AND A.ID_FAIL = F.ID_FAIL(+) AND A.ID_DAERAHMHN = D.ID_DAERAH "
					+ " AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN AND P.ID_SIMATI = MS.ID_SIMATI "
					+ " AND A.ID_STATUS <> '999' "
					+ " AND S.ID_STATUS = '170' AND F.ID_FAIL = FFF.ID_FAIL "
					+ " ) > 0 "
					+ " AND "
					+ " (TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'), 'DD/MM/YYYY') -  "
					+ " (SELECT MAX(TO_DATE(TO_CHAR(STA.TARIKH_MASUK,'DD/MM/YYYY'),'DD/MM/YYYY')) FROM TBLPPKPERMOHONAN A, TBLPFDFAIL FF, TBLRUJSTATUS S, TBLPPKSIMATI P, TBLPPKPEMOHON PM, "
					+ " TBLPPKPERMOHONANSIMATI MS,TBLRUJSUBURUSANSTATUS ST, TBLRUJSUBURUSANSTATUSFAIL STA, TBLRUJDAERAH D "
					+ " WHERE D.ID_DAERAH IN ( SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, USERS_INTERNAL UR "
					+ " WHERE U.ID_PEJABATJKPTG=UR.ID_PEJABATJKPTG AND UR.USER_ID='"
					+ user_terima
					+ "') "
					+ " AND ST.ID_STATUS = S.ID_STATUS(+) AND STA.ID_SUBURUSANSTATUS = ST.ID_SUBURUSANSTATUS(+) "
					+ " AND A.ID_PEMOHON = PM.ID_PEMOHON(+) AND A.ID_FAIL = FF.ID_FAIL(+) AND A.ID_DAERAHMHN = D.ID_DAERAH "
					+ " AND A.ID_PERMOHONAN = MS.ID_PERMOHONAN AND A.ID_PERMOHONAN = STA.ID_PERMOHONAN AND P.ID_SIMATI = MS.ID_SIMATI "
					+ " AND A.ID_STATUS <> '999' "
					+ " AND S.ID_STATUS = '170' "
					+ " AND  FFF.ID_FAIL = FF.ID_FAIL))>30 ) AS TOTAL_BORANGB ";
			sql += " FROM DUAL";

			myLogger.info("****************8--------------- LIST NOTIFICATION DASHBOARD LIST BORANG B:"
					+ sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h = null;

			while (rs.next()) {
				h = new Hashtable();
				h.put("TOTAL_BORANGB",
						rs.getString("TOTAL_BORANGB") == null ? "" : rs
								.getInt("TOTAL_BORANGB"));
			}
			return h;
		} finally {
			//if (db != null)
				//db.close();
		}
	}

	public Hashtable notifikasi(String role, String id_negeri_user,
			String id_esaduan, String flag_notifikasi, String user_terima,
			String notread,Db db) throws Exception {

		//Db db = null;
		String sql = "";
		try {
			//db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			sql += " SELECT ( ";
			sql += "  SELECT COUNT(*) FROM TBLESADUAN A,TBLRUJSUMBERESADUAN S,TBLRUJJENISESADUAN J,USERS U,TBLRUJSTATUSESADUAN ST,USERS_INTERNAL UI, "
					+ " TBLRUJSEKSYEN SK,TBLESNOTIFIKASI X,TBLRUJNEGERI N,TBLRUJPEJABATJKPTG PEJ,TBLRUJDAERAH D,TBLRUJJENISMODULESADUAN JM "
					+ " WHERE A.ID_SUMBERADUAN = S.ID_SUMBERADUAN(+) "
					+ " AND A.ID_JENISADUAN = J.ID_JENISADUAN(+) "
					+ " AND A.ID_JENISMODULESADUAN = JM.ID_JENISMODULESADUAN(+) "
					+ " AND A.USER_ID = U.USER_ID "
					+ " AND X.ID_ESADUAN = A.ID_ESADUAN "
					+ " AND A.ID_STATUS = ST.ID_STATUSESADUAN(+) "
					+ " AND U.USER_ID = UI.USER_ID "
					+ " AND UI.ID_SEKSYEN = SK.ID_SEKSYEN(+) "
					+ " AND UI.ID_NEGERI = N.ID_NEGERI(+) "
					+ " AND UI.ID_PEJABATJKPTG = PEJ.ID_PEJABATJKPTG(+) "
					+ " AND UI.ID_DAERAH = D.ID_DAERAH(+)"
					+ " AND A.ID_STATUS NOT IN ('16125') AND A.ID_STATUS  IS NOT NULL   ";
			sql += " AND X.ID_ESNOTIFIKASI is not null ";
			if (!id_esaduan.equals("")) {
				sql += " AND X.ID_ESADUAN = '" + id_esaduan + "' ";
			}
			if (!id_negeri_user.equals("") && !role.equals("adminsuper_es")) {
				sql += " AND A.ID_NEGERI_PENGADU = '" + id_negeri_user + "' ";
			}
			if (!user_terima.equals("")) {
				sql += " AND X.ID_USER_NOTIFIKASI_TERIMA = '" + user_terima
						+ "' ";
			}
			if (!flag_notifikasi.equals("")) {
				sql += " AND X.FLAG_NOTIFIKASI = '" + flag_notifikasi + "'";
			}
			if (!notread.equals("")) {
				sql += " AND X.FLAG_READ = '" + notread + "'";
			}
			sql += " ) AS JUMLAH_ADUAN,";

			sql += " (SELECT COUNT(*) as notification"
					+ " FROM TBLMAININBOX A,TBLUSERSINBOX B,TBLINBOXNOTIFIKASI C "
					+ " WHERE A.ID_MAININBOX = B.ID_MAININBOX AND C.FLAG_READ = 'NO' AND A.FLAG_AKTIF = 'Y' AND A.ID_MAININBOX = C.ID_MAININBOX "
					+ " AND B.USER_ID = '"
					+ user_terima
					+ "' AND C.ID_USER_NOTIFIKASI_TERIMA = B.USER_ID) AS INBOX ";

			sql += " FROM DUAL";

			myLogger.info("--------------- LIST NOTIFICATION PPT DASHBOARD LIST:"
					+ sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h = null;

			while (rs.next()) {
				h = new Hashtable();
				h.put("JUMLAH_ADUAN", rs.getString("JUMLAH_ADUAN") == null ? ""
						: rs.getInt("JUMLAH_ADUAN"));
				h.put("INBOX",
						rs.getString("INBOX") == null ? "" : rs.getInt("INBOX"));

			}
			return h;
		} finally {
			//if (db != null)
				//db.close();
		}

	}

	Integer count_aduan = null;

	public Integer getListNotifikasi_main_list(String role,
			String id_negeri_user, String id_esaduan, String flag_notifikasi,
			String user_terima, String notread,Db db) throws Exception {

		//Db db = null;
		String sql = "";
		try {
			//db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = " SELECT COUNT(*) as notification FROM TBLESADUAN A,TBLRUJSUMBERESADUAN S,TBLRUJJENISESADUAN J,USERS U,TBLRUJSTATUSESADUAN ST,USERS_INTERNAL UI, "
					+ " TBLRUJSEKSYEN SK,TBLESNOTIFIKASI X,TBLRUJNEGERI N,TBLRUJPEJABATJKPTG PEJ,TBLRUJDAERAH D,TBLRUJJENISMODULESADUAN JM "
					+ " WHERE A.ID_SUMBERADUAN = S.ID_SUMBERADUAN(+) "
					+ " AND A.ID_JENISADUAN = J.ID_JENISADUAN(+) "
					+ " AND A.ID_JENISMODULESADUAN = JM.ID_JENISMODULESADUAN(+) "
					+ " AND A.USER_ID = U.USER_ID "
					+ " AND X.ID_ESADUAN = A.ID_ESADUAN "
					+ " AND A.ID_STATUS = ST.ID_STATUSESADUAN(+) "
					+ " AND U.USER_ID = UI.USER_ID "
					+ " AND UI.ID_SEKSYEN = SK.ID_SEKSYEN(+) "
					+ " AND UI.ID_NEGERI = N.ID_NEGERI(+) "
					+ " AND UI.ID_PEJABATJKPTG = PEJ.ID_PEJABATJKPTG(+) "
					+ " AND UI.ID_DAERAH = D.ID_DAERAH(+)"
					+ " AND A.ID_STATUS NOT IN ('16125') AND A.ID_STATUS  IS NOT NULL   ";

			sql += " AND X.ID_ESNOTIFIKASI is not null ";
			if (!id_esaduan.equals("")) {
				sql += " AND X.ID_ESADUAN = '" + id_esaduan + "' ";
			}
			if (!id_negeri_user.equals("") && !role.equals("adminsuper_es")) {
				sql += " AND A.ID_NEGERI_PENGADU = '" + id_negeri_user + "' ";
			}
			if (!user_terima.equals("")) {
				sql += " AND X.ID_USER_NOTIFIKASI_TERIMA = '" + user_terima
						+ "' ";
			}
			if (!flag_notifikasi.equals("")) {
				sql += " AND X.FLAG_NOTIFIKASI = '" + flag_notifikasi + "'";
			}
			if (!notread.equals("")) {
				sql += " AND X.FLAG_READ = '" + notread + "'";
			}

			// myLogger.info("LIST NOTIFICATION DASHBOARD LIST"+sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {

				count_aduan = rs.getInt("notification");

			}
			return count_aduan;
		} finally {
			//if (db != null)
				//db.close();
		}
	}

	Integer count_inbox = null;

	public Integer getListNotifikasi_inbox(String userId,Db db) throws Exception {
		//Db db = null;
		String sql = "";
		try {
			//db = new Db();
			Statement stmt = db.getStatement();
			sql = ""
					+ " SELECT COUNT(*) as notification"
					+ " FROM TBLMAININBOX A,TBLUSERSINBOX B,TBLINBOXNOTIFIKASI C "
					+ " WHERE A.ID_MAININBOX = B.ID_MAININBOX AND C.FLAG_READ = 'NO' AND A.FLAG_AKTIF = 'Y' AND A.ID_MAININBOX = C.ID_MAININBOX "
					+ " AND B.USER_ID = '" + userId
					+ "' AND C.ID_USER_NOTIFIKASI_TERIMA = B.USER_ID ";
			sql += "  ";
			myLogger.info("SQL COUNT LIST MAIN INBOX XX :" + sql.toUpperCase());
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				count_inbox = rs.getInt("notification");
			}
			return count_inbox;
		} finally {
			//if (db != null)
				//db.close();
		}
	}

	private void prepareItemForDisplay(HttpSession session, Vector objVector,
			int cntItemPage, String nav) {

		// start number
		int startno = 0;
		if (nav == null)
			nav = "first";
		if (session.getAttribute("_portal_startno") != null)
			startno = ((Integer) session.getAttribute("_portal_startno"))
					.intValue();
		if (nav.equals("previous")) {
			if (startno == objVector.size()) {
				int x = startno % cntItemPage;
				if (x > 0) {
					startno = startno - x;
					startno = startno - cntItemPage;
				} else {
					startno = startno - (cntItemPage * 2);
					if (startno < 0)
						startno = 0;
				}
			} else {
				startno = startno - (cntItemPage * 2);
				if (startno < 0)
					startno = 0;
			}
		} else if (nav.equals("first")) {
			startno = 0;
		} else if (nav.equals("last")) {
			int x = cntItemPage;
		} else if (nav.equals("back")) {
			if (startno == objVector.size()) {
				int x = startno % cntItemPage;
				if (x == 0)
					x = cntItemPage;
				startno = startno - x;
			} else {
				startno = startno - cntItemPage;
				if (startno < 0)
					startno = 0;
			}

		}

		Vector moduleVector = new Vector();
		int i = 0, cnt = 0;
		if (objVector.size() > 0) {
			for (cnt = 0, i = startno; cnt < cntItemPage
					&& i < objVector.size(); i++, cnt++) {
				moduleVector.addElement(objVector.elementAt(i));
			}
		}

		session.setAttribute("_portal_moduleVector", moduleVector);
		context.put("startnoInt", new Integer(startno));
		context.put("totalInt", new Integer(objVector.size()));

		startno = i;
		// set the next start number
		session.setAttribute("_portal_startno", new Integer(startno));
	}

	@SuppressWarnings("unchecked")
	public List carianFail(HttpSession session, String type, String search, Db db)throws Exception {

		String userId = (String) session.getAttribute("_ekptg_user_id");
		String negeriUser = (String) session.getAttribute("_ekptg_user_negeri");

		//Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		SimpleDateFormat sdf = null;

		List senaraiFail = null;

		String sql = "";
		Integer count = 0;

		try {

			//db = new Db();
			sdf = new SimpleDateFormat("dd/MM/yyyy");
			stmt = db.getStatement();

			sql = " SELECT DISTINCT";
			
					/*
					+ " (SELECT COUNT(DISTINCT HMX.ID_PEGAWAI) FROM TBLPPTHAKMILIK HMX "
					+ " WHERE HMX.ID_PEGAWAI IS NOT NULL AND HMX.ID_PERMOHONAN = P.ID_PERMOHONAN)AS BIL_PEGAWAI_BERTUGAS, "
					+ " (SELECT COUNT(DISTINCT AGHM.ID_AGIHANHM) "
					+ " FROM TBLPPTHAKMILIK HMX, TBLPPTAGIHANHM AGHM "
					+ " WHERE AGHM.ID_HAKMILIK = HMX.ID_HAKMILIK "
					+ " AND HMX.ID_PERMOHONAN = P.ID_PERMOHONAN "
					+ " AND AGHM.BARIS = '2' )AS BIL_PEGAWAI_BERTUGAS_BARU, "*/
				sql += " F.ID_SUBURUSAN,P.TUJUAN,P.FLAG_JENISPERMOHONAN,F.NO_FAIL AS NO_JKPTG,P.NO_RUJUKAN_PTG,P.NO_RUJUKAN_UPT,P.NO_RUJUKAN_PTD,P.NO_PERMOHONAN_ONLINE,TO_CHAR(P.TARIKH_PERMOHONAN,'DD.MM.YYYY') AS TARIKH_PERMOHONAN,S.KETERANGAN AS STATUS, "
					+ " U.USER_NAME,P.ID_PERMOHONAN,P.ID_STATUS,P.ID_FAIL,UI.ID_NEGERI AS NEGERI_USER, P.FLAG_SEGERA, P.FLAG_SEMAK, MK.FLAG_SEMAKAN_PENGARAH,P.TARIKH_MASUK AS TARIKH_MASUK_P "
					+ " ,MK2.FLAG_MMK AS FLAG_MMK_PENARIKAN, MKK.STATUS_KEPUTUSAN,";
					

			if (type.equals("hakmilik") || type.equals("endorsanK")) {
				// add field
				sql += " UPPER(PHM.NO_HAKMILIK) AS NO_HAKMILIK,UPPER(PHM.NO_LOT) AS NO_LOT,UPPER(JHM.KOD_JENIS_HAKMILIK) AS KOD_HAKMILIK,UPPER(JHM.KETERANGAN) AS JENIS_HAKMILIK, ";
			}
			
			if (type.equals("pb")) {
				// add field
				sql += " UPPER(PHM.NO_HAKMILIK) AS NO_HAKMILIK,UPPER(PHM.NO_LOT) AS NO_LOT,UPPER(JHM.KOD_JENIS_HAKMILIK) AS KOD_HAKMILIK,UPPER(JHM.KETERANGAN) AS JENIS_HAKMILIK, ";
				sql += " UPPER(PB.NAMA_PB) AS NAMA_PB,UPPER(PB.NO_PB) AS NO_PB,UPPER(HPB.NO_AKAUN) AS NO_AKAUN, ";
			}

			sql += " JK.NAMA_KEMENTERIAN "
					+ " FROM TBLPPTPERMOHONAN P,TBLPFDFAIL F,TBLRUJSTATUS S,USERS U,USERS_INTERNAL UI, TBLPPTMMK MK "
					+ " ,TBLPPTPENARIKANBALIK PB, TBLPPTMMK MK2, TBLPPTMMKKEPUTUSAN MKK,";

			if (type.equals("hakmilik") || type.equals("endorsanK")) {
				// add table
				//myLogger.debug("------------ masuk 1xxx");
				sql += " TBLPPTHAKMILIK PHM,TBLRUJJENISHAKMILIK JHM,";
			}

			if (type.equals("pb")) {
				// add table
				sql += " TBLPPTHAKMILIK PHM,TBLRUJJENISHAKMILIK JHM,TBLPPTHAKMILIKPB HPB,TBLPPTPIHAKBERKEPENTINGAN PB,";

			}
			
			sql += " TBLRUJKEMENTERIAN JK " + " WHERE P.ID_FAIL = F.ID_FAIL"
					+ " AND JK.ID_KEMENTERIAN(+) = F.ID_KEMENTERIAN "
					+ " AND S.ID_STATUS = P.ID_STATUS(+) "
					+ " AND P.ID_MASUK = U.USER_ID(+) "
					+ " AND P.ID_PERMOHONAN = MK.ID_PERMOHONAN(+)"
					+ " AND PB.ID_PERMOHONAN(+) = P.ID_PERMOHONAN"
					+ " AND MK2.ID_PENARIKANBALIK(+) = PB.ID_PENARIKANBALIK "
					+ " AND MK.ID_MMK = MKK.ID_MMK(+) "
					+ " AND F.ID_SUBURUSAN IN (51,52,53) "
					+ " AND P.ID_STATUS NOT IN (8,999) "
					+ " AND U.USER_ID = UI.USER_ID(+) ";

			if (type.equals("hakmilik") || type.equals("endorsanK")) {
				// add condition
				sql += " AND P.ID_PERMOHONAN = PHM.ID_PERMOHONAN AND PHM.ID_JENISHAKMILIK = JHM.ID_JENISHAKMILIK(+) ";
			}
				
			if (type.equals("pb")) {
				// add condition
				sql += " AND P.ID_PERMOHONAN = PHM.ID_PERMOHONAN AND PHM.ID_JENISHAKMILIK = JHM.ID_JENISHAKMILIK(+) ";
				sql += " AND PHM.ID_HAKMILIK = HPB.ID_HAKMILIK AND HPB.ID_PIHAKBERKEPENTINGAN = PB.ID_PIHAKBERKEPENTINGAN ";
			}

			if (type.equals("agihan")) {
				sql += " AND P.ID_STATUS IN (11,127,16,148,132,133,74,26,1612198) ";
			}
			// myLogger.debug("------------ masuk 1");
			if (type.equals("fail")) {
				 //myLogger.debug("------------ masuk 2");
				if (search != null) {
					// myLogger.debug("------------ masuk 3");
					if (!search.trim().equals("")) {
						// myLogger.debug("------------ masuk 4");
						sql += " AND (" + " UPPER(f.no_fail) LIKE '%"
								+ search.toUpperCase().trim() + "%' "
								+ " OR UPPER(P.NO_RUJUKAN_PTG) LIKE '%"
								+ search.toUpperCase().trim() + "%' "
								+ " OR UPPER(P.NO_RUJUKAN_PTD) LIKE '%"
								+ search.toUpperCase().trim() + "%' "
								+ " OR UPPER(P.NO_PERMOHONAN_ONLINE) LIKE '%"
								+ search.toUpperCase().trim() + "%' "
								+ " OR UPPER(P.NO_RUJUKAN_UPT) LIKE '%"
								+ search.toUpperCase().trim() + "%' ";
						sql += " OR  UPPER(P.TUJUAN)  LIKE UPPER('%' ||'"
								+ search.toUpperCase().trim() + "'|| '%')";
						sql += " OR  UPPER(JK.NAMA_KEMENTERIAN)  LIKE UPPER('%' ||'"
								+ search.toUpperCase().trim()
								+ "'|| '%')"
								+ ")  ";
					}
				}
			}
			
			
			if (type.equals("hakmilik")) {
				
				if (search != null) {
					if (!search.trim().equals("")) {
						sql += " AND (";
						sql += " trim(UPPER(PHM.NO_HAKMILIK)) LIKE '%"
								+ search.toUpperCase().trim() + "%' ";
						sql += " OR trim(UPPER(PHM.NO_LOT)) LIKE '%"
								+ search.toUpperCase().trim() + "%' ";
						sql += " OR trim(UPPER(JHM.KOD_JENIS_HAKMILIK)) LIKE '%"
								+ search.toUpperCase().trim() + "%' ";
						// sql
						// +=" OR trim(UPPER(JHM.KETERANGAN)) LIKE '%"+search.toUpperCase().trim()+"%' ";
						sql += " OR UPPER(f.no_fail) LIKE '%"
								+ search.toUpperCase().trim() + "%' ";
						sql += " OR UPPER(P.NO_RUJUKAN_PTG) LIKE '%"
								+ search.toUpperCase().trim() + "%' ";
						sql += " OR UPPER(P.NO_RUJUKAN_PTD) LIKE '%"								
								+ search.toUpperCase().trim() + "%' ";
						sql += " OR UPPER(P.NO_PERMOHONAN_ONLINE) LIKE '%"
						+ search.toUpperCase().trim() + "%' ";
						sql += " OR UPPER(P.NO_RUJUKAN_UPT) LIKE '%"
								+ search.toUpperCase().trim() + "%' ";
						sql += " OR  UPPER(P.TUJUAN)  LIKE UPPER('%' ||'"
								+ search.toUpperCase().trim() + "'|| '%')";
						sql += " OR  UPPER(JK.NAMA_KEMENTERIAN)  LIKE UPPER('%' ||'"
								+ search.toUpperCase().trim() + "'|| '%')";

						sql += ")  ";
					}
				}
			}

		
			 if (type.equals("endorsanK")) {

				sql += " AND PHM.ID_HAKMILIK IN (";
				
				sql += " SELECT ID_HAKMILIK FROM ( "+
						" SELECT DISTINCT HM1.ID_HAKMILIK, MAX(EBK1.TARIKH_CATATAN) AS TARIKH_HANTAR_ENDORSAN  "+
						" FROM TBLPPTHAKMILIK HM1,TBLPPTBORANGK BK1,TBLPPTHAKMILIKBORANGK HBK1,TBLPPTENDOSANBORANGK EBK1, "+
						" TBLRUJSUBURUSANSTATUSHAKMILIK SHMK1, TBLRUJSUBURUSANSTATUS SSTK1  "+
						" WHERE HBK1.ID_HAKMILIK = HM1.ID_HAKMILIK "+
						" AND HM1.ID_HAKMILIK = SHMK1.ID_HAKMILIK "+
						" AND SHMK1.ID_SUBURUSANSTATUS = SSTK1.ID_SUBURUSANSTATUS "+
						" AND SSTK1.ID_STATUS NOT IN (1610242, 1610233) "+
						" AND HBK1.ID_BORANGK = BK1.ID_BORANGK "+
						" AND EBK1.ID_BORANGK = BK1.ID_BORANGK ";
						if (!negeriUser.equals("16") && !negeriUser.isEmpty()) {
							
								sql += "AND HM1.ID_NEGERI = '" + negeriUser + "'";
							
						}
						sql += " AND EBK1.TARIKH_CATATAN IS NOT NULL "+
						" AND (EBK1.TARIKH_TERIMA IS NULL OR EBK1.TARIKH_TERIMA = '') "+
						" AND TO_DATE (SYSDATE) - TO_DATE (EBK1.TARIKH_CATATAN) > 14 "+
						" AND NVL(HM1.FLAG_PEMBATALAN_KESELURUHAN, 0) <> 'Y' "+
						" AND NVL(HM1.FLAG_PENARIKAN_KESELURUHAN, 0) <> 'Y' "+
						" GROUP BY HM1.ID_HAKMILIK "+
						" ) ";
				
				

				sql += ")  ";

			}

			if (type.equals("pb")) {
				if (search != null) {
					if (!search.trim().equals("")) {
						sql += " AND (";
						sql += " trim(UPPER(PHM.NO_HAKMILIK)) LIKE '%"
								+ search.toUpperCase().trim() + "%' ";
						sql += " OR trim(UPPER(PHM.NO_LOT)) LIKE '%"
								+ search.toUpperCase().trim() + "%' ";
						sql += " OR trim(UPPER(JHM.KOD_JENIS_HAKMILIK)) LIKE '%"
								+ search.toUpperCase().trim() + "%' ";
						sql += " OR UPPER(f.no_fail) LIKE '%"
								+ search.toUpperCase().trim() + "%' ";
						sql += " OR UPPER(P.NO_RUJUKAN_PTG) LIKE '%"
								+ search.toUpperCase().trim() + "%' ";
						sql += " OR UPPER(P.NO_RUJUKAN_PTD) LIKE '%"
								+ search.toUpperCase().trim() + "%' ";
						sql += " OR UPPER(P.NO_PERMOHONAN_ONLINE) LIKE '%"
								+ search.toUpperCase().trim() + "%' ";
						sql += " OR UPPER(P.NO_RUJUKAN_UPT) LIKE '%"
								+ search.toUpperCase().trim() + "%' ";
						sql += " OR  UPPER(P.TUJUAN)  LIKE UPPER('%' ||'"
								+ search.toUpperCase().trim() + "'|| '%')";
						sql += " OR  UPPER(JK.NAMA_KEMENTERIAN)  LIKE UPPER('%' ||'"
								+ search.toUpperCase().trim() + "'|| '%')";
						sql += " OR UPPER(PB.NAMA_PB)  LIKE UPPER('%' ||'"
								+ search.trim() + "'|| '%')";
						sql += " OR UPPER(PB.NO_PB)  LIKE ('%' ||'"
								+ search.trim() + "'|| '%') ";
						sql += " OR UPPER(HPB.NO_AKAUN)  LIKE ('%' ||'"
								+ search.trim() + "'|| '%') ";
						sql += ")  ";

					}
				}
			}

			if (!negeriUser.equals("16") && !negeriUser.isEmpty()) {
				if (negeriUser.equals("14")) {
					sql += " AND F.ID_NEGERI in (14,15,16) ";
				} else {
					sql += " AND F.ID_NEGERI = '" + negeriUser + "'";
				}
			}

			sql = sql + " ORDER BY " + " CASE nvl(p.flag_semak,0)"
					+ " WHEN '1' THEN 1" + " END asc,"
					+ " CASE nvl(p.id_status,0)" + " WHEN 16 THEN 1"
					+ " END asc," + " CASE nvl(mk.flag_semakan_pengarah,0)"
					+ " WHEN '1' THEN 1" + " END asc,"
					+ " CASE nvl(FLAG_MMK_PENARIKAN,0)" + " WHEN '1' THEN 1"
					+ " END asc," + "" + "p.tarikh_masuk DESC";

			myLogger.debug("TYPE :"
					+ type
					+ "---------------------------------- FAIL TUGASAN PPT DASHBOARD :"
					+ sql);
			stmt.setFetchSize(10);
			rs = stmt.executeQuery(sql);

			int bil = 1;
			senaraiFail = Collections.synchronizedList(new ArrayList());
			Map h = null;

			while (rs.next()) {

				h = Collections.synchronizedMap(new HashMap());
				h.put("bil", bil);

				if (type.equals("hakmilik") || type.equals("endorsanK")) {
					h.put("NO_HAKMILIK",
							rs.getString("NO_HAKMILIK") == null ? "" : rs
									.getString("NO_HAKMILIK"));
					h.put("NO_LOT",
							rs.getString("NO_LOT") == null ? "" : rs
									.getString("NO_LOT"));
					h.put("KOD_HAKMILIK",
							rs.getString("KOD_HAKMILIK") == null ? "" : rs
									.getString("KOD_HAKMILIK"));
					h.put("JENIS_HAKMILIK",
							rs.getString("JENIS_HAKMILIK") == null ? "" : rs
									.getString("JENIS_HAKMILIK"));
				}

				if (type.equals("pb")) {
					h.put("NO_HAKMILIK",
							rs.getString("NO_HAKMILIK") == null ? "" : rs
									.getString("NO_HAKMILIK"));
					h.put("NO_LOT",
							rs.getString("NO_LOT") == null ? "" : rs
									.getString("NO_LOT"));
					h.put("KOD_HAKMILIK",
							rs.getString("KOD_HAKMILIK") == null ? "" : rs
									.getString("KOD_HAKMILIK"));
					h.put("JENIS_HAKMILIK",
							rs.getString("JENIS_HAKMILIK") == null ? "" : rs
									.getString("JENIS_HAKMILIK"));
					h.put("NAMA_PB",
							rs.getString("NAMA_PB") == null ? "" : rs
									.getString("NAMA_PB"));
					h.put("NO_PB",
							rs.getString("NO_PB") == null ? "" : rs
									.getString("NO_PB"));
					h.put("NO_AKAUN", rs.getString("NO_AKAUN") == null ? ""
							: rs.getString("NO_AKAUN"));
				}
				/*
				h.put("BIL_PEGAWAI_BERTUGAS_BARU", rs
						.getString("BIL_PEGAWAI_BERTUGAS_BARU") == null ? ""
						: rs.getString("BIL_PEGAWAI_BERTUGAS_BARU"));
						*/
				h.put("NAMA_KEMENTERIAN",
						rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs
								.getString("NAMA_KEMENTERIAN"));
				/*
				h.put("BIL_PEGAWAI_BERTUGAS",
						rs.getString("BIL_PEGAWAI_BERTUGAS") == null ? "" : rs
								.getString("BIL_PEGAWAI_BERTUGAS"));
								*/
				h.put("STATUS_KEPUTUSAN",
						rs.getString("STATUS_KEPUTUSAN") == null ? "" : rs
								.getString("STATUS_KEPUTUSAN"));
				h.put("FLAG_MMK_PENARIKAN",
						rs.getString("FLAG_MMK_PENARIKAN") == null ? "" : rs
								.getString("FLAG_MMK_PENARIKAN"));
				h.put("FLAG_SEMAKAN_PENGARAH",
						rs.getString("FLAG_SEMAKAN_PENGARAH") == null ? "" : rs
								.getString("FLAG_SEMAKAN_PENGARAH"));
				h.put("FLAG_SEMAK", rs.getString("FLAG_SEMAK") == null ? ""
						: rs.getString("FLAG_SEMAK"));
				h.put("FLAG_SEGERA", rs.getString("FLAG_SEGERA") == null ? ""
						: rs.getString("FLAG_SEGERA"));
				h.put("ID_SUBURUSAN", rs.getString("ID_SUBURUSAN") == null ? ""
						: rs.getString("ID_SUBURUSAN"));
				h.put("FLAG_JENISPERMOHONAN",
						rs.getString("FLAG_JENISPERMOHONAN") == null ? "" : rs
								.getString("FLAG_JENISPERMOHONAN"));
				h.put("ID_STATUS",
						rs.getString("ID_STATUS") == null ? "" : rs
								.getString("ID_STATUS"));
				h.put("NO_JKPTG",
						rs.getString("NO_JKPTG") == null ? "" : rs
								.getString("NO_JKPTG"));
				h.put("NO_RUJUKAN_PTG",
						rs.getString("NO_RUJUKAN_PTG") == null ? "" : rs
								.getString("NO_RUJUKAN_PTG"));
				h.put("NO_RUJUKAN_PTD",
						rs.getString("NO_RUJUKAN_PTD") == null ? "" : rs
								.getString("NO_RUJUKAN_PTD"));
				h.put("NO_PERMOHONAN_ONLINE",
						rs.getString("NO_PERMOHONAN_ONLINE") == null ? "" : rs
								.getString("NO_PERMOHONAN_ONLINE"));
				
				
				h.put("NO_RUJUKAN_UPT",
						rs.getString("NO_RUJUKAN_UPT") == null ? "" : rs
								.getString("NO_RUJUKAN_UPT"));
				h.put("TARIKH_PERMOHONAN",
						rs.getString("TARIKH_PERMOHONAN") == null ? "" : rs
								.getString("TARIKH_PERMOHONAN"));
				h.put("STATUS",
						rs.getString("STATUS") == null ? "" : rs
								.getString("STATUS"));
				h.put("USER_NAME",
						rs.getString("USER_NAME") == null ? "" : rs
								.getString("USER_NAME"));
				h.put("ID_PERMOHONAN",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				h.put("ID_FAIL",
						rs.getString("ID_FAIL") == null ? "" : rs
								.getString("ID_FAIL"));
				h.put("NEGERI_USER", rs.getString("NEGERI_USER") == null ? ""
						: rs.getString("NEGERI_USER"));
				h.put("TUJUAN",
						rs.getString("TUJUAN") == null ? "" : rs
								.getString("TUJUAN"));

				if (rs.getString("ID_SUBURUSAN") != null) {

					if (rs.getString("ID_SUBURUSAN").equals("51")) {
						h.put("URUSAN", "PENGAMBILAN SEKSYEN 4");
					} else if (rs.getString("ID_SUBURUSAN").equals("52")) {
						h.put("URUSAN", "PENGAMBILAN SEKSYEN 8");
					} else if (rs.getString("ID_SUBURUSAN").equals("53")) {
						h.put("URUSAN", "PENGAMBILAN SEMENTARA");
					} else {
						h.put("URUSAN", "");
					}

				} else {
					h.put("URUSAN", "");
				}
				

				senaraiFail.add(h);
				bil++;
				count++;
				
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			//if (db != null)
				//db.close();
		}

		return senaraiFail;

	}
	
	//penambahan yati
	
	@SuppressWarnings("unchecked")
	public List carianFailCaj(HttpSession session, String type, String search, Db db)throws Exception {

		userId = (String) session.getAttribute("_ekptg_user_id");
		String negeriUser = (String) session.getAttribute("_ekptg_user_negeri");

		//Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		SimpleDateFormat sdf = null;

		List senaraiFailCaj = null;

		String sql = "";
		Integer count = 0;

		try {

			//db = new Db();
			sdf = new SimpleDateFormat("dd/MM/yyyy");
			stmt = db.getStatement();

			sql += "SELECT DISTINCT F.ID_FAIL,F.NO_FAIL, P.ID_PERMOHONAN, F.ID_SUBURUSAN,P.TARIKH_PERMOHONAN, " +
					"P.FLAG_JENISPERMOHONAN, P.FLAG_SEGERA, P.TUJUAN,JK.NAMA_KEMENTERIAN,(COUNT(HM.ID_HAKMILIK)) AS JUMLAH_HAKMILIK_LEWAT " +
					"FROM TBLPFDFAIL F, TBLPPTPERMOHONAN P, TBLPPTHAKMILIK HM, TBLPPTSIASATAN S, TBLPPTBORANGG G, TBLPPTBORANGH H, " +
					"TBLPPTTERIMABAYARAN TB , TBLRUJKEMENTERIAN JK " +
					"WHERE F.ID_FAIL = P.ID_FAIL " +
					"AND P.ID_PERMOHONAN = HM.ID_PERMOHONAN " +
					"AND S.ID_HAKMILIK = HM.ID_HAKMILIK " +
					"AND S.STATUS_SIASATAN = '6' " +
					"AND S.ID_SIASATAN = G.ID_SIASATAN " +
					"AND G.ID_BORANGG = H.ID_BORANGH " +
					"AND HM.ID_HAKMILIK = TB.ID_HAKMILIK " +
					"AND F.ID_KEMENTERIAN = JK.ID_KEMENTERIAN ";
					
					if (!negeriUser.equals("16") && !negeriUser.isEmpty()) {
						if (negeriUser.equals("14")) {
							sql += " AND F.ID_NEGERI in (14,15,16) ";
						} else {
							sql += " AND F.ID_NEGERI = '" + negeriUser + "'";
						}
					}
					sql += "AND ((CASE WHEN TB.ID_TERIMABAYARAN IS NULL THEN SYSDATE ELSE TB.TARIKH_BAYARAN END) - H.TARIKH_HANTAR)  > 90 " +
					"GROUP BY F.ID_FAIL,F.NO_FAIL,P.ID_PERMOHONAN, F.ID_SUBURUSAN,P.TARIKH_PERMOHONAN, P.FLAG_JENISPERMOHONAN, P.FLAG_SEGERA,P.TUJUAN, JK.NAMA_KEMENTERIAN  ";
			
			myLogger.debug("TYPE :"
					+ type
					+ "---------------------------------- FAIL TUGASAN PPT DASHBOARD :"
					+ sql);
			
			//System.out.println(" SQL USER LIST FROM MODEL :"+sql);
			rs = stmt.executeQuery(sql);
			
			
			Hashtable h;
			int bil = 1;
			senaraiFailCaj = Collections.synchronizedList(new ArrayList());
			while (rs.next()) {
				h = new Hashtable();
				h.put("bil", bil);
				h.put("no_fail", rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
				h.put("id_permohonan", rs.getString("ID_PERMOHONAN") == null ? "" : rs.getString("ID_PERMOHONAN"));
				h.put("tujuan", rs.getString("TUJUAN") == null ? "" : rs.getString("TUJUAN"));
				h.put("jum_lewat", rs.getString("JUMLAH_HAKMILIK_LEWAT") == null ? "" : rs.getString("JUMLAH_HAKMILIK_LEWAT"));
				h.put("nama_kementerian", rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs.getString("NAMA_KEMENTERIAN"));
				h.put("id_fail", rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("id_suburusan", rs.getString("ID_SUBURUSAN") == null ? "" : rs.getString("ID_SUBURUSAN"));
				h.put("flag_segera", rs.getString("FLAG_SEGERA") == null ? "" : rs.getString("FLAG_SEGERA"));
				h.put("flag_jenispermohonan", rs.getString("FLAG_JENISPERMOHONAN") == null ? "" : rs.getString("FLAG_JENISPERMOHONAN"));
				h.put("tarikh_permohonan", rs.getString("TARIKH_PERMOHONAN") == null ? "" : rs.getString("TARIKH_PERMOHONAN"));
				
				senaraiFailCaj.add(h);
				bil++;		
			
			}
			
					//System.out.println(" SENARAI USER LIST FROM MODEL :"+senarai_user);
			return senaraiFailCaj;
		}catch (Exception re) {
			throw re;
		} finally {
			if (db != null)
				db.close();
		}
	}//tutup 
	
	//yati
	@SuppressWarnings("unchecked")
	public List carianFailSiasatan(HttpSession session, String type, String searchsiasatan, Db db)throws Exception {

		String userId = (String) session.getAttribute("_ekptg_user_id");
		String negeriUser = (String) session.getAttribute("_ekptg_user_negeri");

		//Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		SimpleDateFormat sdf = null;

		List senaraiFailSiasatan = null;

		String sql = "";
		Integer count = 0;

		try {

			//db = new Db();
			sdf = new SimpleDateFormat("dd/MM/yyyy");
			stmt = db.getStatement();

			sql = " SELECT DISTINCT";
			
					/*
					+ " (SELECT COUNT(DISTINCT HMX.ID_PEGAWAI) FROM TBLPPTHAKMILIK HMX "
					+ " WHERE HMX.ID_PEGAWAI IS NOT NULL AND HMX.ID_PERMOHONAN = P.ID_PERMOHONAN)AS BIL_PEGAWAI_BERTUGAS, "
					+ " (SELECT COUNT(DISTINCT AGHM.ID_AGIHANHM) "
					+ " FROM TBLPPTHAKMILIK HMX, TBLPPTAGIHANHM AGHM "
					+ " WHERE AGHM.ID_HAKMILIK = HMX.ID_HAKMILIK "
					+ " AND HMX.ID_PERMOHONAN = P.ID_PERMOHONAN "
					+ " AND AGHM.BARIS = '2' )AS BIL_PEGAWAI_BERTUGAS_BARU, "*/
				sql += " F.ID_SUBURUSAN,P.TUJUAN,P.FLAG_JENISPERMOHONAN,F.NO_FAIL AS NO_JKPTG,P.NO_RUJUKAN_PTG,P.NO_RUJUKAN_UPT,P.NO_RUJUKAN_PTD,P.NO_PERMOHONAN_ONLINE,TO_CHAR(P.TARIKH_PERMOHONAN,'DD.MM.YYYY') AS TARIKH_PERMOHONAN,S.KETERANGAN AS STATUS, "
					+ " U.USER_NAME,P.ID_PERMOHONAN,P.ID_STATUS,P.ID_FAIL,UI.ID_NEGERI AS NEGERI_USER, P.FLAG_SEGERA, P.FLAG_SEMAK, MK.FLAG_SEMAKAN_PENGARAH,P.TARIKH_MASUK AS TARIKH_MASUK_P "
					+ " ,MK2.FLAG_MMK AS FLAG_MMK_PENARIKAN, MKK.STATUS_KEPUTUSAN,";
					

			if (type.equals("hakmilikSiasatan")) {
				// add field
				sql += " UPPER(PHM.NO_HAKMILIK) AS NO_HAKMILIK,UPPER(PHM.NO_LOT) AS NO_LOT,UPPER(JHM.KOD_JENIS_HAKMILIK) AS KOD_HAKMILIK,UPPER(JHM.KETERANGAN) AS JENIS_HAKMILIK, ";
			}
			if (type.equals("failSiasatan") || type.equals("hakmilikSiasatan")) {
				// add field
				//myLogger.debug("------------ masuk 2");
				sql += " ST.ID_SIASATAN,ST.NO_SIASATAN,PHM.ID_HAKMILIK, ";
			}


			sql += " JK.NAMA_KEMENTERIAN "
					+ " FROM TBLPPTPERMOHONAN P,TBLPFDFAIL F,TBLRUJSTATUS S,USERS U,USERS_INTERNAL UI, TBLPPTMMK MK "
					+ " ,TBLPPTPENARIKANBALIK PB, TBLPPTMMK MK2, TBLPPTMMKKEPUTUSAN MKK,";

			if (type.equals("hakmilikSiasatan")) {
				// add table
				//myLogger.debug("------------ masuk 1xxx");
				sql += " TBLRUJJENISHAKMILIK JHM,";
			}
	
			if (type.equals("failSiasatan") || type.equals("hakmilikSiasatan")) {
				// myLogger.debug("------------ masuk 2xxx");
				// add table
				sql += " TBLPPTSIASATAN ST, TBLPPTHAKMILIK PHM,";

			}

			sql += " TBLRUJKEMENTERIAN JK " + " WHERE P.ID_FAIL = F.ID_FAIL"
					+ " AND JK.ID_KEMENTERIAN(+) = F.ID_KEMENTERIAN "
					+ " AND S.ID_STATUS = P.ID_STATUS(+) "
					+ " AND P.ID_MASUK = U.USER_ID(+) "
					+ " AND P.ID_PERMOHONAN = MK.ID_PERMOHONAN(+)"
					+ " AND PB.ID_PERMOHONAN(+) = P.ID_PERMOHONAN"
					+ " AND ST.ID_HAKMILIK = PHM.ID_HAKMILIK(+) "
					+ " AND MK2.ID_PENARIKANBALIK(+) = PB.ID_PENARIKANBALIK "
					+ " AND MK2.ID_MMK = MKK.ID_MMK(+) "
					+ " AND F.ID_SUBURUSAN IN (51,52,53) "
					+ " AND P.ID_STATUS NOT IN (8,999) "
					+ " AND U.USER_ID = UI.USER_ID(+) ";

			
			if (type.equals("failSiasatan")) {
				// add condition
				sql += " AND P.ID_PERMOHONAN = ST.ID_PERMOHONAN";
			}
			if (type.equals("hakmilikSiasatan")) {
				// add condition
				sql += " AND P.ID_PERMOHONAN = ST.ID_PERMOHONAN AND P.ID_PERMOHONAN = PHM.ID_PERMOHONAN AND PHM.ID_JENISHAKMILIK = JHM.ID_JENISHAKMILIK(+)";
			}


			if (type.equals("agihan")) {
				sql += " AND P.ID_STATUS IN (11,127,16,148,132,133,74,26,1612198) ";
			}
	
			//penambahan yati
			else if (type.equals("hakmilikSiasatan")) {
				// myLogger.debug("------------ masuk 5aaaa");
				if (searchsiasatan != null) {
					// myLogger.debug("------------ masuk 3");
					if (!searchsiasatan.trim().equals("")) {
						// myLogger.debug("------------ masuk 4");
						sql += " AND (" + " UPPER(ST.NO_SIASATAN) LIKE '%"
								+ searchsiasatan.toUpperCase().trim() + "%' "
								+ ")";
						
					}
				}
			}
			//TUTUP YATI
			
			//penambahan yati
			else if (type.equals("failSiasatan")) {
				// myLogger.debug("------------ masuk 5bbbb");
				if (searchsiasatan != null) {
					//myLogger.debug("------------ masuk 3bbbb");
					if (!searchsiasatan.trim().equals("")) {
						//myLogger.debug("------------ masuk 4xxx");
						sql += " AND (" + " UPPER(ST.NO_SIASATAN) LIKE '%"
								+ searchsiasatan.toUpperCase().trim() + "%' "
								+ ")";
						
					}
				}
			}
			//TUTUP YATI


			if (!negeriUser.equals("16") && !negeriUser.isEmpty()) {
				if (negeriUser.equals("14")) {
					sql += " AND F.ID_NEGERI in (14,15,16) ";
				} else {
					sql += " AND F.ID_NEGERI = '" + negeriUser + "'";
				}
			}

			sql = sql + " ORDER BY " + " CASE nvl(p.flag_semak,0)"
					+ " WHEN '1' THEN 1" + " END asc,"
					+ " CASE nvl(p.id_status,0)" + " WHEN 16 THEN 1"
					+ " END asc," + " CASE nvl(mk.flag_semakan_pengarah,0)"
					+ " WHEN '1' THEN 1" + " END asc,"
					+ " CASE nvl(FLAG_MMK_PENARIKAN,0)" + " WHEN '1' THEN 1"
					+ " END asc," + "" + "p.tarikh_masuk DESC";

			myLogger.debug("TYPE SIASATAN:"
					+ type
					+ "---------------------------------- FAIL TUGASAN PPT DASHBOARD SIASATAN:"
					+ sql);
			stmt.setFetchSize(10);
			rs = stmt.executeQuery(sql);

			int bil = 1;
			senaraiFailSiasatan = Collections.synchronizedList(new ArrayList());
			Map h = null;

			while (rs.next()) {

				h = Collections.synchronizedMap(new HashMap());
				h.put("bil", bil);
				if (type.equals("failSiasatan") ) {
					h.put("NO_SIASATAN",
							rs.getString("NO_SIASATAN") == null ? "" : rs
									.getString("NO_SIASATAN"));
					h.put("ID_HAKMILIK",
							rs.getString("ID_HAKMILIK") == null ? "" : rs
									.getString("ID_HAKMILIK"));
					h.put("ID_SIASATAN",
							rs.getString("ID_SIASATAN") == null ? "" : rs
									.getString("ID_SIASATAN"));
					
				}
				if (type.equals("hakmilikSiasatan") ) {
					
					h.put("ID_SIASATAN",
							rs.getString("ID_SIASATAN") == null ? "" : rs
									.getString("ID_SIASATAN"));
					
					h.put("NO_SIASATAN",
							rs.getString("NO_SIASATAN") == null ? "" : rs
									.getString("NO_SIASATAN"));
					h.put("NO_HAKMILIK",
							rs.getString("NO_HAKMILIK") == null ? "" : rs
									.getString("NO_HAKMILIK"));
					h.put("ID_HAKMILIK",
							rs.getString("ID_HAKMILIK") == null ? "" : rs
									.getString("ID_HAKMILIK"));
					h.put("NO_LOT",
							rs.getString("NO_LOT") == null ? "" : rs
									.getString("NO_LOT"));
					h.put("KOD_HAKMILIK",
							rs.getString("KOD_HAKMILIK") == null ? "" : rs
									.getString("KOD_HAKMILIK"));
					h.put("JENIS_HAKMILIK",
							rs.getString("JENIS_HAKMILIK") == null ? "" : rs
									.getString("JENIS_HAKMILIK"));
				}

				/*
				h.put("BIL_PEGAWAI_BERTUGAS_BARU", rs
						.getString("BIL_PEGAWAI_BERTUGAS_BARU") == null ? ""
						: rs.getString("BIL_PEGAWAI_BERTUGAS_BARU"));
						*/
				h.put("NAMA_KEMENTERIAN",
						rs.getString("NAMA_KEMENTERIAN") == null ? "" : rs
								.getString("NAMA_KEMENTERIAN"));
				/*
				h.put("BIL_PEGAWAI_BERTUGAS",
						rs.getString("BIL_PEGAWAI_BERTUGAS") == null ? "" : rs
								.getString("BIL_PEGAWAI_BERTUGAS"));
								*/
				h.put("STATUS_KEPUTUSAN",
						rs.getString("STATUS_KEPUTUSAN") == null ? "" : rs
								.getString("STATUS_KEPUTUSAN"));
				h.put("FLAG_MMK_PENARIKAN",
						rs.getString("FLAG_MMK_PENARIKAN") == null ? "" : rs
								.getString("FLAG_MMK_PENARIKAN"));
				h.put("FLAG_SEMAKAN_PENGARAH",
						rs.getString("FLAG_SEMAKAN_PENGARAH") == null ? "" : rs
								.getString("FLAG_SEMAKAN_PENGARAH"));
				h.put("FLAG_SEMAK", rs.getString("FLAG_SEMAK") == null ? ""
						: rs.getString("FLAG_SEMAK"));
				h.put("FLAG_SEGERA", rs.getString("FLAG_SEGERA") == null ? ""
						: rs.getString("FLAG_SEGERA"));
				h.put("ID_SUBURUSAN", rs.getString("ID_SUBURUSAN") == null ? ""
						: rs.getString("ID_SUBURUSAN"));
				h.put("FLAG_JENISPERMOHONAN",
						rs.getString("FLAG_JENISPERMOHONAN") == null ? "" : rs
								.getString("FLAG_JENISPERMOHONAN"));
				h.put("ID_STATUS",
						rs.getString("ID_STATUS") == null ? "" : rs
								.getString("ID_STATUS"));
				h.put("NO_JKPTG",
						rs.getString("NO_JKPTG") == null ? "" : rs
								.getString("NO_JKPTG"));
				h.put("NO_RUJUKAN_PTG",
						rs.getString("NO_RUJUKAN_PTG") == null ? "" : rs
								.getString("NO_RUJUKAN_PTG"));
				h.put("NO_RUJUKAN_PTD",
						rs.getString("NO_RUJUKAN_PTD") == null ? "" : rs
								.getString("NO_RUJUKAN_PTD"));
				h.put("NO_PERMOHONAN_ONLINE",
						rs.getString("NO_PERMOHONAN_ONLINE") == null ? "" : rs
								.getString("NO_PERMOHONAN_ONLINE"));
				
				
				h.put("NO_RUJUKAN_UPT",
						rs.getString("NO_RUJUKAN_UPT") == null ? "" : rs
								.getString("NO_RUJUKAN_UPT"));
				h.put("TARIKH_PERMOHONAN",
						rs.getString("TARIKH_PERMOHONAN") == null ? "" : rs
								.getString("TARIKH_PERMOHONAN"));
				h.put("STATUS",
						rs.getString("STATUS") == null ? "" : rs
								.getString("STATUS"));
				h.put("USER_NAME",
						rs.getString("USER_NAME") == null ? "" : rs
								.getString("USER_NAME"));
				h.put("ID_PERMOHONAN",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				h.put("ID_FAIL",
						rs.getString("ID_FAIL") == null ? "" : rs
								.getString("ID_FAIL"));
				h.put("NEGERI_USER", rs.getString("NEGERI_USER") == null ? ""
						: rs.getString("NEGERI_USER"));
				h.put("TUJUAN",
						rs.getString("TUJUAN") == null ? "" : rs
								.getString("TUJUAN"));

				if (rs.getString("ID_SUBURUSAN") != null) {

					if (rs.getString("ID_SUBURUSAN").equals("51")) {
						h.put("URUSAN", "PENGAMBILAN SEKSYEN 4");
					} else if (rs.getString("ID_SUBURUSAN").equals("52")) {
						h.put("URUSAN", "PENGAMBILAN SEKSYEN 8");
					} else if (rs.getString("ID_SUBURUSAN").equals("53")) {
						h.put("URUSAN", "PENGAMBILAN SEMENTARA");
					} else {
						h.put("URUSAN", "");
					}

				} else {
					h.put("URUSAN", "");
				}
				

				senaraiFailSiasatan.add(h);
				bil++;
				count++;
				
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			//if (db != null)
				//db.close();
		}

		return senaraiFailSiasatan;

	}
	

}
