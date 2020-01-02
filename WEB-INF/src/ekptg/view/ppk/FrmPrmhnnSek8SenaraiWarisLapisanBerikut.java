/**
 *
 */
package ekptg.view.ppk;

import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.velocity.VTemplate;

import org.apache.velocity.Template;

import ekptg.helpers.HTML;
import ekptg.model.ppk.FrmPrmhnnSek8DaftarSek8Data;
import ekptg.model.ppk.FrmSenaraiFailPusakaKecilData;

/**
 * @author aida
 *
 */
public class FrmPrmhnnSek8SenaraiWarisLapisanBerikut extends VTemplate{
	private static final long serialVersionUID = 1L;

	public Template doTemplate() throws Exception
	{

		System.out.println(".:Senarai Waris Lapisan Berikut:.");

		HttpSession session = this.request.getSession();
		String vm = "app/ppk/frmPrmhnnSek8SenaraiWarisLapisanBerikut.jsp";
		String submit = getParam("command");
		String disability1 = "";
		String disability2 = "";
		String readability1 = "";
		String readability2 = "";

		Vector list = new Vector();

		list.clear();

		System.out.println("submit="+submit);
		System.out.println("idPermohonan="+getParam("idPermohonan"));
		System.out.println("idSimati="+getParam("idSimati"));

		if("Papar".equals(submit)){
			readability1 = " ";
			readability2 = "readonly";
			disability1 = "disabled";
			disability2 = "";
			vm = "app/ppk/frmPrmhnnSek8DaftarSek8.jsp";
			view_mode(session);
			list = FrmPrmhnnSek8DaftarSek8Data.getData();
			System.out.println(list.size());
  	    	this.context.put("View", list);

  	    	this.context.put("mode1", readability2);

			this.context.put("disableButton1", disability1);
			this.context.put("disableButton2", disability2);
			this.context.put("readOnlyText1", readability1);
			this.context.put("readOnlyText2", readability2);
			System.out.println("list daftarsek8: " +list);
			Hashtable h = (Hashtable) list.get(0);
			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(h.get("idnegeri").toString()),disability1));
			this.context.put("selectDaerah", HTML.SelectDaerah("socDaerah",Long.parseLong(h.get("idDaerah").toString()),disability1));
			System.out.println("app/ppk/frmPrmhnnSek8DaftarSek8.jsp");

		}
		else if("Tambah".equals(submit)){
			readability1 = " ";
			readability2 = "readonly";
			disability1 = "disabled";
			disability2 = "";
			vm = "app/ppk/frmPrmhnnSek8DaftarSek8.jsp";
			view_mode(session);
			list = FrmPrmhnnSek8DaftarSek8Data.getData();
			System.out.println(list.size());
  	    	this.context.put("View", list);

  	    	this.context.put("mode1", readability2);

			this.context.put("disableButton1", disability1);
			this.context.put("disableButton2", disability2);
			this.context.put("readOnlyText1", readability1);
			this.context.put("readOnlyText2", readability2);
			System.out.println("list daftarsek8: " +list);
			Hashtable h = (Hashtable) list.get(0);
			this.context.put("selectNegeri", HTML.SelectNegeri("socNegeri",Long.parseLong(h.get("idnegeri").toString()),disability1));
			this.context.put("selectDaerah", HTML.SelectDaerah("socDaerah",Long.parseLong(h.get("idDaerah").toString()),disability1));
			System.out.println("app/ppk/frmPrmhnnSek8DaftarSek8.jsp");

			}
			else{
			FrmSenaraiFailPusakaKecilData.setList();
			list = FrmSenaraiFailPusakaKecilData.getList();
			this.context.put("Senaraifail",list);
			this.context.put("selectNegeri",HTML.SelectNegeri("socNegeri"));
			}

		Template template = this.engine.getTemplate(vm);
		return template;
}
	private void view_mode(HttpSession session) throws Exception {
		String id = getParam("idPermohonan");
		FrmPrmhnnSek8DaftarSek8Data.setData(id);

	}
}