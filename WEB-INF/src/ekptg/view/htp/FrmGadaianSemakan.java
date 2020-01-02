package ekptg.view.htp;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.velocity.VTemplate;

import org.apache.velocity.Template;

import ekptg.helpers.DB;

public class FrmGadaianSemakan extends VTemplate {

    @Override
	public Template doTemplate() throws Exception

    {
    	HttpSession session = this.request.getSession();
		Date now = new Date();
		SimpleDateFormat Format =  new SimpleDateFormat("dd/MM/yyyy");
		String vm = "";
		Vector Semak = new Vector();
		Semak.clear();
		String action = getParam("command");
		if("view".equals(action)){
			vm = "app/htp/frmGadaianSemakan.jsp";
			view_mode(session);
//			Semak = FrmSenaraiFailGadaianData.getSemak();
		    this.context.put("Semak", Semak);
		}else if("seterus".equals(action)){
			vm = "app/htp/frmGadaianPenHamilik.jsp";
			seterus(session);
//			this.context.put("sekarang", Format.format(now));
		}
        Vector listNegeri = DB.getNegeri();
	    this.context.put("SenaraiNegeri", listNegeri);
	    Vector listKementerian = DB.getKementerian();
	    this.context.put("SenaraiKementerian", listKementerian);
	    Vector listAgensi = DB.getAgensi();
	    this.context.put("SenaraiAgensi", listAgensi);
	    Vector listUrusan = getUrusan();
	    this.context.put("SenaraiUrusan", listUrusan);
        Template template = this.engine.getTemplate(vm);

        return template;

    }

    public static Vector getUrusan() throws Exception {
	    Db db = null;
	    String sql = "";
	    try {
	      db = new Db();
	      Statement stmt = db.getStatement();
	      SQLRenderer r = new SQLRenderer();
	      r.add("id_Suburusan");
	      r.add("kod_Suburusan");
	      r.add("nama_Suburusan");
	      r.add("id_Urusan");
	      r.set("id_Urusan",Integer.parseInt("108"));
	      sql = r.getSQLSelect("tblrujsuburusan", "kod_Suburusan");
	      
	      ResultSet rs = stmt.executeQuery(sql);
	      Vector v = new Vector();
	      while (rs.next()) {
	        Hashtable h = new Hashtable();
	        h.put("id", rs.getInt("id_Suburusan"));
	        h.put("kod", rs.getString("kod_Suburusan"));
	        h.put("nama", rs.getString("nama_Suburusan"));
	        v.addElement(h);
	      }
	      System.out.println(v.size());
	      return v;
	    }
	    finally {
	      if (db != null) db.close();
	    }
	  }

    private void view_mode(HttpSession session) throws Exception {
	    int id = Integer.parseInt(getParam("id_permohonan"));
//	    FrmSenaraiFailGadaianData.setSemak(id);
	  }
    private void seterus(HttpSession session) throws Exception {
	    int id = Integer.parseInt(getParam("id_permohonan"));
//	    FrmSenaraiFailGadaianData.setPenHakmilik(id);
	  }
}
