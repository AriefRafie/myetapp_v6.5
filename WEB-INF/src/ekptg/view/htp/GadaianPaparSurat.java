package ekptg.view.htp;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.velocity.VTemplate;

import org.apache.velocity.Template;

import ekptg.model.htp.FrmGadaianPeguamDOCData;
import ekptg.model.htp.FrmGadaianPeguamMOCData;
import ekptg.model.htp.FrmGadaianPeguamSOCData;
import ekptg.model.htp.FrmGadaianPeminjamDOCData;
import ekptg.model.htp.FrmGadaianPeminjamSOCData;

public class GadaianPaparSurat extends VTemplate {
	public Template doTemplate() throws Exception
    {
		HttpSession session = this.request.getSession();
    	System.out.println("GadaianSurat::");
    	String vm = "";
    	Vector list = new Vector();
    	list.clear();
    	String mode = getParam("mode");
		String setting = getParam("setting");
		int idPermohonan = Integer.parseInt(getParam("idPermohonan"));
		
		if("soc".equals(mode)){
			if("peguam".equals(setting)){
				vm = "app/htp/frmGadaianPeguamSOC.jsp";
				setPeguamSoc(session, idPermohonan);
				list = FrmGadaianPeguamSOCData.getListHeader();
				this.context.put("Header", list);
				list = FrmGadaianPeguamSOCData.getListContent();
				this.context.put("Content", list);
				list = FrmGadaianPeguamSOCData.getListHakmilik();
				this.context.put("Hakmilik", list);
				list = FrmGadaianPeguamSOCData.getListFooter();
				this.context.put("Footer", list);
			}else{
				vm = "app/htp/frmGadaianPeminjamSOC.jsp";
				setPeminjamSoc(session, idPermohonan);
				list = FrmGadaianPeminjamSOCData.getListHeader();
				this.context.put("Header", list);
				list = FrmGadaianPeminjamSOCData.getListContent();
				this.context.put("Content", list);
				list = FrmGadaianPeminjamSOCData.getListHakmilik();
				this.context.put("Hakmilik", list);
				list = FrmGadaianPeminjamSOCData.getListFooter();
				this.context.put("Footer", list);
			}
		}else if("doc".equals(mode)){
			if("peguam".equals(setting)){
				vm = "app/htp/frmGadaianPeguamDOC.jsp";
				setPeguamDoc(session, idPermohonan);
				list = FrmGadaianPeguamDOCData.getListHeader();
				this.context.put("Header", list);
				list = FrmGadaianPeguamDOCData.getListContent();
				this.context.put("Content", list);
				list = FrmGadaianPeguamDOCData.getListHakmilik();
				this.context.put("Hakmilik", list);
				list = FrmGadaianPeguamDOCData.getListFooter();
				this.context.put("Footer", list);
			}else{
				vm = "app/htp/frmGadaianPeminjamDOC.jsp";
				setPeminjamDoc(session, idPermohonan);
				list = FrmGadaianPeminjamDOCData.getListHeader();
				this.context.put("Header", list);
				list = FrmGadaianPeminjamDOCData.getListContent();
				this.context.put("Content", list);
				list = FrmGadaianPeminjamDOCData.getListHakmilik();
				this.context.put("Hakmilik", list);
				list = FrmGadaianPeminjamDOCData.getListFooter();
				this.context.put("Footer", list);
			}
		}else{
			if("peguam".equals(setting)){
				vm = "app/htp/frmGadaianPeguamMOC.jsp";
				setPeguamMoc(session, idPermohonan);
				list = FrmGadaianPeguamMOCData.getListHeader();
				this.context.put("Header", list);
				list = FrmGadaianPeguamMOCData.getListContent();
				this.context.put("Content", list);
				list = FrmGadaianPeguamMOCData.getListHakmilik();
				this.context.put("Hakmilik", list);
				list = FrmGadaianPeguamMOCData.getListFooter();
				this.context.put("Footer", list);
			}else{
				vm = "app/htp/frmGadaianPeminjamMOC.jsp";
			}
		}
		
		System.out.println("GadaianSurat::Surat");
    	Template template = this.engine.getTemplate(vm);
        return template;
    }
	
	//*** moc Peguam Controller
	public void setPeguamMoc(HttpSession session, int idPermohonan) throws Exception
	  {
		FrmGadaianPeguamMOCData.setListHeader(idPermohonan);
		FrmGadaianPeguamMOCData.setListContent(idPermohonan);
		FrmGadaianPeguamMOCData.setListHakmilik(idPermohonan);
		FrmGadaianPeguamMOCData.setListFooter(idPermohonan);
	  }
	
	//*** doc Peguam Controller
	public void setPeguamDoc(HttpSession session, int idPermohonan) throws Exception
	  {
		FrmGadaianPeguamDOCData.setListHeader(idPermohonan);
		FrmGadaianPeguamDOCData.setListContent(idPermohonan);
		FrmGadaianPeguamDOCData.setListHakmilik(idPermohonan);
		FrmGadaianPeguamDOCData.setListFooter(idPermohonan);
	  }
	
	//*** doc Peminjam Controller
	public void setPeminjamDoc(HttpSession session, int idPermohonan) throws Exception
	  {
		FrmGadaianPeminjamDOCData.setListHeader(idPermohonan);
		FrmGadaianPeminjamDOCData.setListContent(idPermohonan);
		FrmGadaianPeminjamDOCData.setListHakmilik(idPermohonan);
		FrmGadaianPeminjamDOCData.setListFooter(idPermohonan);
	  }
	
	//*** soc Peguam Controller
	public void setPeguamSoc(HttpSession session, int idPermohonan) throws Exception
	  {
		FrmGadaianPeguamSOCData.setListHeader(idPermohonan);
		FrmGadaianPeguamSOCData.setListContent(idPermohonan);
		FrmGadaianPeguamSOCData.setListHakmilik(idPermohonan);
		FrmGadaianPeguamSOCData.setListFooter(idPermohonan);
	  }
	
	//*** soc Peminjam Controller
	public void setPeminjamSoc(HttpSession session, int idPermohonan) throws Exception
	  {
		FrmGadaianPeminjamSOCData.setListHeader(idPermohonan);
		FrmGadaianPeminjamSOCData.setListContent(idPermohonan);
		FrmGadaianPeminjamSOCData.setListHakmilik(idPermohonan);
		FrmGadaianPeminjamSOCData.setListFooter(idPermohonan);
	  }
}
