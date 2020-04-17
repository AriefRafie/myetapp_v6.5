/**
 * 
 */
package ekptg.view.ppk;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;
import ekptg.helpers.Utils;
import ekptg.model.ppk.FrmSenaraiPermohonanSeksyen8Data;


/**
 * 
 *
 */

public class FrmSenaraiKiv extends AjaxBasedModule {
	static Logger myLogger = Logger.getLogger(FrmSenaraiPermohonanSeksyen8.class);

	FrmSenaraiPermohonanSeksyen8Data logic = null;
	//Vector list = null;
	List list = null;

	@Override
	public String doTemplate2() throws Exception {
		logic = new FrmSenaraiPermohonanSeksyen8Data();
		HttpSession session = this.request.getSession();
		String userId = (String)session.getAttribute("_ekptg_user_id");
		
		String vm = ""; 
		String flagAdvSearch = getParam("flagAdvSearch");
		String action = getParam("action"); // Second Level
		//vm = "app/ppk/frmSenaraiKiv.jsp";
	
		
		String USER_NEGERI = (String) session.getAttribute("_ekptg_user_negeri");
		this.context.put("USER_NEGERI",USER_NEGERI);
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
		this.context.put("USER_ID_SYSTEM",USER_ID_SYSTEM);
		boolean checkJawatanKPP = false;
		checkJawatanKPP = getJawatanKPP(session,USER_ID_SYSTEM);
		this.context.put("jawatanKpp",checkJawatanKPP);
		
		list = Collections.synchronizedList(new ArrayList());
		
		String command = getParam("command");		
		
		this.context.put("myIdSimati", "");
		this.context.put("noFail", "");
		this.context.put("socPegawai", "");
		this.context.put("txtTarikhMatang", "");
		this.context.put("txtTarikhPerintah", "");
		this.context.put("seksyen", "");
		
		
		
		String myIdSimati = "";
		String noFail = "";
		String socPegawai = "";
		String txtTarikhMatang = "";
		String txtTarikhPerintah = "";
		String seksyen = "";
		String selseksyen = "";
		String selseksyen8 = "";
		String selseksyen17 = "";
		
		
		
	
		if ("carian".equals(command)) {
		
			
			 myIdSimati = getParam("myIdSimati");
			 noFail = getParam("noFail");
			 socPegawai = getParam("socPegawai");
			 txtTarikhMatang = getParam("txtTarikhMatang");
			 txtTarikhPerintah = getParam("txtTarikhPerintah");
			 seksyen = getParam("seksyen");
			 
			 if(seksyen.equals("0")){
				 selseksyen = "selected=selected";
			 }else if(seksyen.equals("8")){
				 selseksyen8 = "selected=selected";
			 }else if(seksyen.equals("17")){
				 selseksyen17 = "selected=selected";
			 }
				
			 
			 
			 context.put("selectPegawai", HTML.SelectPegawaiPengendali(
						"socPegawai", Utils.parseLong(socPegawai), "style=width:305"));
			 
		 	 this.context.put("myIdSimati", myIdSimati.trim());
			 this.context.put("noFail", noFail.trim());
			 this.context.put("txtTarikhMatang", txtTarikhMatang);
			 this.context.put("txtTarikhPerintah", txtTarikhPerintah);
			 this.context.put("seksyen", seksyen);
			 
			 
			 this.context.put("selseksyen", selseksyen);
			 this.context.put("selseksyen8", selseksyen8);
			 this.context.put("selseksyen17", selseksyen17);
			 
			 list = getListBorangB(userId,noFail.trim(),myIdSimati.trim(),socPegawai,txtTarikhMatang,txtTarikhPerintah,seksyen,session);		
			 this.context.put("SenaraiFail", list);		
			
			 setupPage(session,action,list);
			
			
			vm = "app/ppk/frmSenaraiKiv.jsp";
		}else if ("printKIV".equals(command)) {
			System.out.println("ccccccccccccccccccccccccccccccc");
			 myIdSimati = getParam("myIdSimati");
			 noFail = getParam("noFail");
			 socPegawai = getParam("socPegawai");
			 txtTarikhMatang = getParam("txtTarikhMatang");
			 txtTarikhPerintah = getParam("txtTarikhPerintah");
			 seksyen = getParam("seksyen");
				
			 list = getListBorangB(userId,noFail.trim(),myIdSimati.trim(),socPegawai,txtTarikhMatang,txtTarikhPerintah,seksyen,session);		
			 this.context.put("SenaraiFail", list);		
			
			 setupPage(session,action,list);
			 
			 vm = "app/ppk/printKIV.jsp";
			
		}else{ 
	
			System.out.println("11111111111");
			list = getListBorangB(userId,noFail.trim(),myIdSimati.trim(),socPegawai,txtTarikhMatang,txtTarikhPerintah,seksyen,session);		
			this.context.put("SenaraiFail", list);		
			
			this.context.put("selectPegawai", HTML.SelectPegawaiPengendali(
					"socPegawai", null, "style=width:305"));
			
			this.context.put("myIdSimati", myIdSimati.trim());
			this.context.put("noFail", noFail.trim());
			
			setupPage(session,action,list);
			
			vm = "app/ppk/frmSenaraiKiv.jsp";
		}
		
		
		/*myLogger.info("Panggil dari jsp");
		myLogger.info("List@*@*@*@* = "+list);
		myLogger.info("Session = "+session);
		myLogger.info("Action = "+action);*/

		
		return vm;
	}
	
	Vector getListKiv = new Vector();
	public Vector getListBorangB(String userId,String noFail,String myIdSimati, 
			String socPegawai,String txtTarikhMatang,String txtTarikhPerintah,String seksyen, HttpSession session) throws Exception {
		Db db = null;
		getListKiv.clear();
		String sql = "";
		boolean checkJawatanKPP = false;
		SimpleDateFormat Format = new SimpleDateFormat("dd/MM/yyyy");
		
		
		String USER_NEGERI = (String) session.getAttribute("_ekptg_user_negeri");
		this.context.put("USER_NEGERI",USER_NEGERI);
		String USER_ID_SYSTEM = (String)session.getAttribute("_ekptg_user_id");	
		this.context.put("USER_ID_SYSTEM",USER_ID_SYSTEM);
		
		checkJawatanKPP = getJawatanKPP(session,USER_ID_SYSTEM);
		this.context.put("jawatanKpp",checkJawatanKPP);
System.out.println("checkJawatanKPP==="+checkJawatanKPP);
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = " SELECT DISTINCT TO_CHAR(TO_DATE(PP.TARIKH_MOHON,'DD/MM/YYYY'),'DD/MM/YYYY') AS TARIKH_MOHON," +
					"SSM.ID_PERMOHONANSIMATI,M.NAMA_SIMATI,PP.SEKSYEN,SSM.ID_SIMATI,PP.ID_PERMOHONAN," +
					"FFF.ID_FAIL,FFF.NO_FAIL," +
					" TO_DATE (TO_CHAR(ord.date_kiv, 'DD/MM/YYYY')    , 'DD/MM/YYYY')          AS DATE_KIV ," +
					" TO_DATE( TO_CHAR(ord.TARIKH_PERINTAH, 'DD/MM/YYYY') , 'DD/MM/YYYY') AS tarikh_perintah, " +           
                     "  pr.PEG_PENGENDALI,ORD.CATATAN_KIV";
			sql += "  " +
					" FROM TBLPPKSIMATI M,TBLPPKPERMOHONANSIMATI SSM,TBLPFDFAIL FFF,TBLPPKPERBICARAAN PR,TBLPPKKEPUTUSANPERMOHONAN KP,TBLPPKPERMOHONAN PP,TBLPPKPERINTAH ORD "+
			" WHERE SSM.ID_SIMATI = M.ID_SIMATI AND SSM.ID_PERMOHONAN = PP.ID_PERMOHONAN AND FFF.ID_FAIL = PP.ID_FAIL AND PR.ID_KEPUTUSANPERMOHONAN = KP.ID_KEPUTUSANPERMOHONAN AND ORD.ID_PERBICARAAN = PR.ID_PERBICARAAN "+
				" AND KP.ID_PERMOHONAN = PP.ID_PERMOHONAN  "+
				" AND ORD.CHECK_KIV = '1' "+
				" AND TO_DATE(ORD.DATE_KIV) < SYSDATE ";
				
				if(checkJawatanKPP==false){
					sql +=" AND PP.ID_DAERAHMHN IN "+
						" (SELECT DISTINCT U.ID_DAERAHURUS FROM TBLRUJPEJABATURUSAN U, "+
						" USERS_INTERNAL UR "+
						" WHERE U.ID_PEJABATJKPTG = UR.ID_PEJABATJKPTG AND UR.USER_ID = '"+userId+"' ";
				}else{
					
					sql +=" AND pp.ID_NEGERIMHN IN "+
							" (SELECT DISTINCT u.ID_NEGERIURUS FROM TBLRUJPEJABATURUSAN U, "+
							" USERS_INTERNAL UR "+
							" WHERE U.ID_PEJABATJKPTG = UR.ID_PEJABATJKPTG AND UR.USER_ID = '"+userId+"' ";
					
				}
				 sql += " UNION ";   
				
				 if(checkJawatanKPP==false){
				sql +=" SELECT DISTINCT PBU_U.ID_DAERAHURUS  "+ 
					" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
					" WHERE ID_STATUS = 2  "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
					" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
					" AND PBU.ID_PEMOHON = "+userId+"  ";
				 }else{
					 sql +=" SELECT DISTINCT pbu_u.ID_NEGERIURUS  "+ 
								" FROM TBLPERMOHONANBANTUUNIT PBU,TBLRUJPEJABATURUSAN PBU_U "+ 
								" WHERE ID_STATUS = 2  "+ 
								" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') >= TO_DATE(TO_CHAR(PBU.TARIKH_MULA,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
								" AND TO_DATE(TO_CHAR(SYSDATE,'DD/MM/YYYY'),'DD/MM/YYYY') <= TO_DATE(TO_CHAR(PBU.TARIKH_AKHIR,'DD/MM/YYYY'),'DD/MM/YYYY') "+ 
								" AND PBU.ID_UNIT = PBU_U.ID_PEJABATJKPTG  "+ 
								" AND PBU.ID_PEMOHON = "+userId+"  ";
				 }
				
				sql += " ) ";
		
		
			
			//dapat flag
			boolean setLimit = true;
			
			//noFail
			if (noFail != null) {
				if (!noFail.trim().equals("")) {
					setLimit = false;
					sql = sql + " AND ( UPPER(FFF.NO_FAIL) LIKE '%' ||'"
							+ noFail.trim().toUpperCase() + "'|| '%' ) " ;
				}
			}
			
			if (myIdSimati != null) {
				if (!myIdSimati.trim().equals("")) {
					setLimit = false;
					
						sql = sql + " AND ( UPPER(M.NO_KP_BARU) LIKE '%' ||'" + myIdSimati.toUpperCase() + "'|| '%' ";
				
					
						sql = sql + " OR UPPER(M.NO_KP_LAMA) LIKE '%' ||'" + myIdSimati.toUpperCase() + "'|| '%' ";
					
					
						sql = sql + " OR UPPER(M.NO_KP_LAIN) LIKE '%' ||'" + myIdSimati.toUpperCase() + "'|| '%' )";
					
				}
			}
			
			if (socPegawai != null) {
				if (!socPegawai.trim().equals("")) {
					setLimit = false;
					sql = sql + " AND (pr.ID_UNITPSK ='"+ socPegawai+"' ) " ;
				}
			}
			
			if (txtTarikhMatang != null) {
				if (!txtTarikhMatang.trim().equals("")) {
					setLimit = false;
					sql = sql + " AND (TO_CHAR(ord.date_kiv, 'DD/MM/YYYY') = '"+txtTarikhMatang+"' ) " ;
				}
			}
			
			if (txtTarikhPerintah != null) {
				if (!txtTarikhPerintah.trim().equals("")) {
					setLimit = false;
					sql = sql + " AND (TO_CHAR(ord.TARIKH_PERINTAH, 'DD/MM/YYYY') = '"+txtTarikhPerintah+"' ) " ;
				}
			}
			
			
				if (!seksyen.trim().equals("0")) {
					setLimit = false;
					if ((!seksyen.trim().equals("8")) || (!seksyen.trim().equals("17"))) {
					
					sql = sql + " AND (PP.SEKSYEN = '"+seksyen+ "') " ;
					}
				}
			
			
			//if (setLimit) {	//RESERVED BY AZAM
			//	sql = sql + " AND ROWNUM <= 50 ORDER BY TO_DATE(DATE_KIV) ASC";
			//	}
			
			myLogger.info("GET LIST FAIL KIV"+sql);
			
			ResultSet rs = stmt.executeQuery(sql);
			Hashtable h;
			int bil = 0;
			while (rs.next()){
				h = new Hashtable();
				
				bil++;
				h.put("BIL",bil);
				
				h.put("ID_SIMATI", rs.getString("ID_SIMATI")==null?"":rs.getString("ID_SIMATI"));
				h.put("ID_PERMOHONAN", rs.getString("ID_PERMOHONAN")==null?"":rs.getString("ID_PERMOHONAN"));
				h.put("ID_FAIL", rs.getString("ID_FAIL")==null?"":rs.getString("ID_FAIL"));
				h.put("NO_FAIL", rs.getString("NO_FAIL")==null?"":rs.getString("NO_FAIL"));
				h.put("SEKSYEN", rs.getString("SEKSYEN")==null?"":rs.getString("SEKSYEN"));
				h.put("NAMA_SIMATI", rs.getString("NAMA_SIMATI")==null?"":rs.getString("NAMA_SIMATI"));
				h.put("ID_PERMOHONANSIMATI", rs.getString("ID_PERMOHONANSIMATI")==null?"":rs.getString("ID_PERMOHONANSIMATI"));
				h.put("TARIKH_MOHON", rs.getString("TARIKH_MOHON")==null?"":rs.getString("TARIKH_MOHON"));
				h.put("NAMA_SIMATI", rs.getString("NAMA_SIMATI")==null?"":rs.getString("NAMA_SIMATI"));
				//h.put("DATE_KIV", rs.getString("DATE_KIV")==null?"":rs.getString("DATE_KIV"));
				
				h.put("DATE_KIV", rs.getDate("DATE_KIV")==null?"":Format.format(rs.getDate("DATE_KIV")));
				h.put("tarikh_perintah", rs.getDate("tarikh_perintah")==null?"":Format.format(rs.getDate("tarikh_perintah")));
				h.put("PEG_PENGENDALI", rs.getString("PEG_PENGENDALI")==null?"":rs.getString("PEG_PENGENDALI"));
				h.put("CATATAN_KIV", rs.getString("CATATAN_KIV")==null?"":rs.getString("CATATAN_KIV"));
				
				getListKiv.addElement(h);
			}
			return getListKiv;
		}finally {
			if(db != null) db.close();
		}
		}
	
	public boolean getJawatanKPP(HttpSession session, String USER_ID) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			db = new Db();
			stmt = db.getStatement();
			boolean JawatanKpp=false;
			sql = " SELECT UI.ID_SEKSYEN,U.USER_ID,U.USER_LOGIN,INITCAP(U.USER_NAME) AS USER_NAME_INIT,U.USER_NAME,U.USER_ROLE,S.NAMA_SEKSYEN," +
					" PEJ.NAMA_PEJABAT,N.KOD_NEGERI,N.NAMA_NEGERI,UI.ID_NEGERI,UI.ID_PEJABATJKPTG,D.NAMA_DAERAH,UI.EMEL,PEJ.NO_TEL," +
					" J.KETERANGAN AS NAMA_JAWATAN , J.SHORTNAME_JAWATAN KOD_JWTN" +
					" FROM USERS U,USERS_INTERNAL UI,TBLRUJSEKSYEN S,TBLRUJJAWATAN J,TBLRUJNEGERI N,TBLRUJPEJABATJKPTG PEJ,TBLRUJDAERAH D " +
					" WHERE U.USER_ID = UI.USER_ID AND UI.ID_JAWATAN = J.ID_JAWATAN(+) " +
					" AND UI.ID_SEKSYEN = S.ID_SEKSYEN(+) " +
					" AND UI.ID_NEGERI = N.ID_NEGERI(+) " +
					" AND UI.ID_PEJABATJKPTG = PEJ.ID_PEJABATJKPTG(+) " +
					" AND UI.ID_DAERAH = D.ID_DAERAH(+) AND J.ID_JAWATAN = 5 " +
					" AND U.USER_ID = "+USER_ID;
			myLogger.info(" OT : getKodNegeri :" + sql.toUpperCase());
			
				rs = stmt.executeQuery(sql);				
				while (rs.next()) {				
					
					JawatanKpp = true;
				}			
			return JawatanKpp;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
	}
	



}
