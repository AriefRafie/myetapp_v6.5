package ekptg.view.htp;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

public class frmGadaianSenaraiPP extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(frmGadaianSenaraiPP.class);
	private SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy");
	String command = "";
	String mode = "";
	Vector v = null;

	@Override
	public String doTemplate2() throws Exception {

		HttpSession session = request.getSession();
		this.command = getParam("command");
		this.mode = getParam("mode");
		
		log.info("command : " + command);
		log.info("mode : " + mode);
		
		
		String vm = "app/htp/frmGadaianSenaraiPP.jsp";
		// FrmGadaianA FGA = new FrmGadaianA();
		//String noFail = getParam("NoFail");
		String noFailPP = getParam("nofailpp");
		
		//log.info("noFail : " + noFail );
		log.info("noFailPP : " + noFailPP);

		if (command.equalsIgnoreCase("Search")) {
			vm = "app/htp/frmGadaianSenaraiPP.jsp";
//			CarianPP(noFail, noFailPP);
			CarianPP(noFailPP);
			Vector list = getList();
			context.put("SenaraiPP", list);
			context.put("carian", getParam("nofailpp"));
			
		}
		else{
			vm = "app/htp/frmGadaianSenaraiPP.jsp";
		}

		return vm;
	}

//	public void CarianPP(String noFail, String noFailPP) throws Exception {
	public void CarianPP(String noFailPP) throws Exception {

		Db db = null;
		String sql = "";
		SQLRenderer sqlr = null;
		Hashtable h = null;
		

		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			// boleh guna tapi sebab nama field peguam n nama pihak sama takut terkeliru
			/*
			sqlr = new SQLRenderer();	
			
			sqlr.add("htppohon.no_rujukan_lain");
			sqlr.add("pihak.nama");
			sqlr.add("peguam.nama");
			sqlr.add("mohon.tarikh_surat"); // tarikh surat kjp
			sqlr.add("mohon.tarikh_terima"); // tarikh permohonan
			sqlr.add("hakmilik.no_lot");			
			sqlr.add("daerah.nama_daerah");
			sqlr.add("negeri.nama_negeri");
			sqlr.add("mukim.nama_mukim");
			
			
			sqlr.add("peguam.id_permohonan", sqlr.unquote("mohon.id_permohonan"));
			sqlr.add("pihak.id_hakmilikurusan", sqlr.unquote("hakmilik.id_hakmilikurusan"));
			sqlr.add("hakmilik.id_permohonan", sqlr.unquote("mohon.id_permohonan"));
			sqlr.add("htppohon.id_permohonan", sqlr.unquote("mohon.id_permohonan"));
			sqlr.add("hakmilik.id_daerah", sqlr.unquote("daerah.id_daerah"));
			sqlr.add("hakmilik.id_negeri", sqlr.unquote("negeri.id_negeri"));
			sqlr.add("hakmilik.id_mukim", sqlr.unquote("mukim.id_mukim"));
			
			sqlr.add("htppohon.no_rujukan_lain", noFailPP);
			
			sql = sqlr.getSQLSelect("Tblhtppihakberkepentingan pihak, Tblhtppermohonan htppohon, Tblpermohonan mohon, Tblhtphakmilikurusan  hakmilik, Tblhtppeguam peguam, Tblrujdaerah daerah, Tblrujnegeri negeri, Tblrujmukim mukim");
			
			*/
			
			
			String select = "SELECT htppohon.no_rujukan_lain, pihak.nama as peminjam, peguam.nama as tetuan, mohon.tarikh_surat, mohon.tarikh_terima, ";
				   select += "hakmilik.no_lot, daerah.nama_daerah, negeri.nama_negeri, mukim.nama_mukim ";
				   
			String from = "FROM Tblhtppihakberkepentingan pihak, Tblhtppermohonan htppohon, Tblpermohonan mohon, Tblhtphakmilikurusan  hakmilik, ";
				   from += "Tblhtppeguam peguam, Tblrujdaerah daerah, Tblrujnegeri negeri, Tblrujmukim mukim ";
				   
			String where = "WHERE peguam.id_permohonan = mohon.id_permohonan  ";
			where += "AND pihak.id_hakmilikurusan = hakmilik.id_hakmilikurusan  ";
			where += "AND hakmilik.id_permohonan = mohon.id_permohonan ";
			where += "AND htppohon.id_permohonan = mohon.id_permohonan ";
			where += "AND hakmilik.id_daerah = daerah.id_daerah ";
			where += "AND hakmilik.id_negeri = negeri.id_negeri  ";
			where += "AND hakmilik.id_mukim = mukim.id_mukim ";
			where += "AND htppohon.no_rujukan_lain LIKE '%"+ noFailPP +"%' ";
			//where += "AND htppohon.no_rujukan_lain LIKE '%"+ noFailPP +"%' ";
			
			sql = select + from + where;
			
			log.info("frmGadaianSenaraiPP :: SQL : " + sql);
			
			ResultSet rs = stmt.executeQuery(sql);
			
			int bil = 1;
			h = new Hashtable();
			v = new Vector();
			
			while(rs.next()){
				h.put("bil", bil);
				h.put("peguam", rs.getString("tetuan"));
				h.put("peminjam", rs.getString("peminjam"));
				h.put("tarikhsuratKJP", formatter.format(rs.getDate("tarikh_surat")));
				h.put("tarikhPermohonan", formatter.format(rs.getDate("tarikh_terima")));
				h.put("nolot", rs.getString("no_lot"));
				h.put("daerah", rs.getString("nama_daerah"));
				h.put("mukim", rs.getString("nama_mukim"));
				h.put("negeri", rs.getString("nama_negeri"));
				h.put("noPP", rs.getString("no_rujukan_lain"));
				v.add(h);
				bil++;
				
			}
			
			log.info("Hashtable " + h);


		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			if (db != null) {
				db.close();
			}
		}
	}

	public Vector getList() {
		log.info("frmGadaianSenaraiPP:: getList : "+ v.size());
		return v;
	}

}
