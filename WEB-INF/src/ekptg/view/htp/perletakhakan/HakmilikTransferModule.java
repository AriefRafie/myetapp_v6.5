package ekptg.view.htp.perletakhakan;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.action.AjaxModule;
import ekptg.model.htp.FrmPerletakhakanMaklumatData;
import ekptg.model.htp.rekod.HakmilikBean;
import ekptg.model.htp.rekod.HakmilikInterface;

public class HakmilikTransferModule extends AjaxModule
{
  private String vm = "app/htp/perletakhakan/transferHakmilik/index.jsp";
  private HakmilikInterface hakmilikInterface;
  private HttpSession session = null;
  private String action = null;

  public String doAction()
    throws Exception
  {
    this.session = this.request.getSession();
    this.action = getParam("action");
    String command = getParam("command");
    if (command.equals("transferRecord")) {
      String idPermohonan = getParam("idPermohonan");
      getHakmilikBean().transferRecord(idPermohonan);
      getSelesaiList();
      this.vm = "app/htp/perletakhakan/transferHakmilik/index.jsp";
    }
    else
    {
      getSelesaiList();
      this.vm = "app/htp/perletakhakan/transferHakmilik/index.jsp";
    }

    return this.vm; }

  private void getSelesaiList() {
    String idFail = getParam("txtNoFail");
    Vector list = new FrmPerletakhakanMaklumatData().getFailSelesaiStatus(idFail);
    this.context.put("SenaraiFail", list);
    setupPage(this.session, this.action, list); }

  private HakmilikInterface getHakmilikBean() {
    if (this.hakmilikInterface == null)
      this.hakmilikInterface = new HakmilikBean();

    return this.hakmilikInterface;
  }
}