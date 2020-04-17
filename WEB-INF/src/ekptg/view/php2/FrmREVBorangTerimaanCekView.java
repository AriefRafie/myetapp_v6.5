package ekptg.view.php2;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;
import lebah.util.Util;

import org.apache.log4j.Logger;

import ekptg.helpers.HTML;

public class FrmREVBorangTerimaanCekView extends AjaxBasedModule{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	static Logger myLogger = Logger.getLogger(FrmREVBorangTerimaanCekView.class);	
	
	private final String vm="app/php2/FrmREVBorangTerimaanCek.jsp";
	String userId = null;
	String role = null;
	String user_negeri_login = null;
	
	HttpSession session = null;
	String action = null;	
	
	
		
	public String doTemplate2() throws Exception{
			
		session = request.getSession();
		userId = (String)session.getAttribute("_ekptg_user_id");
		context.put("pengguna_aktif",userId);		
		user_negeri_login = (String)session.getAttribute("_ekptg_user_negeri");
		context.put("id_negeri_user",user_negeri_login);		
		role = (String)session.getAttribute("myrole");
		String command = getParam("command");
		action = getParam("action");
		
		Boolean postDB = false;
		String doPost =  session.getAttribute("doPost").toString();
	    if (doPost.equals("true")) {
	        postDB = true;
	    }
		
		myLogger.info("COMMAND :"+command);
		myLogger.info("ROLE :"+role);
		context.put("role",role);

		String idBank = getParam("socBankC");
 		if (idBank == null || idBank.trim().length() == 0){
 			idBank = "99999";
 		}
 		
		context.put("txtNamaPenyewa","");
		context.put("txtTarikhTerima","");
		context.put("txtNoCek","");
		context.put("txtTarikhCek","");
		context.put("selectBank", HTML.selectBank("socBankC", Long.parseLong(idBank), ""));
		
	    //main_list(idBank);
	    
	     if(command.equals("cari"))
		{

	 		idBank = getParam("socBankC");
	 		if (idBank == null || idBank.trim().length() == 0){
	 			idBank = "99999";
	 		}
	 		
			context.put("txtNamaPenyewa",getParam("txtNamaPenyewa"));
			context.put("txtNoCek",getParam("txtNoCek"));
			context.put("txtTarikhCek",getParam("txtTarikhCek"));
			context.put("txtTarikhTerima",getParam("txtTarikhTerima"));
			main_list(idBank);
		}
	    else{
				main_list(idBank);
		}
		
		return vm;	
	}
	
	
	private void main_list(String idBank)throws Exception{
		/*Vector list = new Vector();
		list = list(getParam("txtTarikhTerima"), getParam("txtNamaPenyewa").toUpperCase(),getParam("txtNoCek").toUpperCase(), getParam("txtTarikhCek"), idBank);		
		setupPage(session,action,list);*/
		
		List<Hashtable> list = null;
		list = list(getParam("txtTarikhTerima"), getParam("txtNamaPenyewa").toUpperCase(),getParam("txtNoCek").toUpperCase(), getParam("txtTarikhCek"), idBank);		
		context.put("SenaraiFail",list);
		setupPage(session,action,list);
		
	}
	
	Vector list = null;
	public Vector list(String tarikh, String namaPenyewa, String noCek, String tarikhCek, String idBank)throws Exception {
		list = new Vector();
		Db db = null;
		list.clear();
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			sql = "SELECT B.TARIKH, C.NAMA, B.NO_RUJUKAN,B.TARIKH_CEK, E.NAMA_BANK, B.KREDIT FROM TBLPHPHASIL A,TBLPHPAKAUN B, TBLPHPPEMOHON C, TBLRUJBANK E " +
					"WHERE A.ID_HASIL = B.ID_HASIL(+) AND C.ID_PEMOHON = A.ID_PEMOHON(+) AND B.ID_BANK = E.ID_BANK(+) AND B.ID_CARABAYAR IN(2,5,6) ";
			
			if(!namaPenyewa.equals(""))
			{
			sql += " AND UPPER(C.NAMA) LIKE '%"+namaPenyewa.trim()+"%'";
			}
			
			if(!noCek.equals(""))
			{
			sql += " AND B.NO_RUJUKAN LIKE '%"+noCek.trim()+"%'";
			}
			
			if (idBank != null) {
				if (!idBank.trim().equals("") && !idBank.trim().equals("99999")) {
					sql += " AND UPPER(B.ID_BANK) = '" +  idBank.trim() + "'";
				}
			}
			
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yy");
			
			//txtTarikhTerima
			if (tarikh != null) {
				if (!tarikh.toString().trim().equals("")) {
					sql += " AND TO_CHAR(B.TARIKH,'dd-MON-YY') = '" + sdf1.format(sdf.parse(tarikh)).toUpperCase() +"'";
				}
			}
			
			//txtTarikhCek
			if (tarikhCek != null) {
				if (!tarikhCek.toString().trim().equals("")) {
					sql += " AND TO_CHAR(B.TARIKH_CEK,'dd-MON-YY') = '" + sdf1.format(sdf.parse(tarikhCek)).toUpperCase() +"'";
				}
			}
			
			sql+=" ORDER BY TO_DATE(B.TARIKH) ASC";
			myLogger.info("LIST:" + sql.toUpperCase());
			
			stmt.setFetchSize(10);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			int bil = 0;
			while (rs.next()) {
				bil = bil + 1;
				h = new Hashtable();
				h.put("bil", bil);
				h.put("tarikh", rs.getDate("TARIKH") == null ? "" : sdf.format(rs.getDate("TARIKH")));
				h.put("nama", rs.getString("NAMA") == null ? "" : rs.getString("NAMA"));
				h.put("noCek",rs.getString("NO_RUJUKAN") == null ? "" : rs.getString("NO_RUJUKAN"));
				h.put("tarikhCek",rs.getDate("TARIKH_CEK") == null ? "" : sdf.format(rs.getDate("TARIKH_CEK")));
				h.put("namaBank",rs.getString("NAMA_BANK") == null ? "" : rs.getString("NAMA_BANK"));
				h.put("kredit",rs.getString("KREDIT") == null ? "" : Util.formatDecimal(Double.valueOf(rs.getString("KREDIT"))));
				list.addElement(h);
			}
			return list;
		} finally {
			if (db != null)
				db.close();
		}

	}
}
