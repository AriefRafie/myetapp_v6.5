package ekptg.fpx;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.portal.AjaxBasedModule;

import org.apache.log4j.Logger;

import ekptg.fpx.entity.DataFpx;
import ekptg.helpers.HTML;
import ekptg.helpers.Utils;

/*
 * @author 
 * Muhamad Syazreen bin Yahaya
 */

public class FrmFPXView extends AjaxBasedModule {

	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(FrmFPXView.class);

	// model
	FrmFPXModel model = new FrmFPXModel();
	IFpx fpxBean = new FpxProcessMessage();
	FpxProperty fpxProperty = FpxProperty.getInstance();

	@SuppressWarnings({ "unchecked" })
	@Override
	public String doTemplate2() throws Exception {

		HttpSession session = request.getSession();

		String vm = "";

		Vector dataFPX = new Vector();

		dataFPX.clear();

		// screen jsp
		String screenFPX = "app/fpx/frmSkrinFPX.jsp";

		// prevent duplicate when refresh page
		String doPost = (String) session.getAttribute("doPost");

		// anchor
		String ScreenLocation = getParam("ScreenLocation");
		String CursorPoint = getParam("CursorPoint");
		context.put("ScreenLocation", ScreenLocation);
		context.put("CursorPoint", CursorPoint);

		// user login id
		String id_user = (String) session.getAttribute("_ekptg_user_id");

		// step paging
		String stepaging = getParam("stepaging");
		if (stepaging != "") {
			context.put("stepaging", stepaging);
		} else {
			context.put("stepaging", "1");
		}

		// default
		context.put("mode", "");
		context.put("isEdit", "");
		context.put("jenisPembayar", "1");
		String showStep = "1";

		Date txnDate = new Date();
		DateFormat txDateFormat = new SimpleDateFormat("yyyyMMddHHmmssS");
		String txnStr = txDateFormat.format(txnDate);
		txnStr = txnStr.substring(0, 14);
		context.put("txnStr", txnStr);
		// main id

		String submit = getParam("command");
		myLogger.info("submit : " + submit);

		if ("goMaklumatBayaran".equals(submit)) {

			// screen validation
			showStep = "2";

			// reset value
			resetValueStep2();

			// get value
			getAndSetValueJenisBayaran();

			String submit2 = getParam("command2");
			myLogger.info("submit[2] : " + submit2);

			if ("onClickJenisPembayar".equals(submit2)) {

				// get and set value back
				getAndSetMaklumatPembayar(getParam("sorFlagJenisPembayar"),
						getParam("valueJenisBayaran"));

			}// close onClickJenisPembayar

			// screen
			vm = screenFPX;

		}// close goMaklumatBayaran

		else if ("goPengesahanBayaran".equals(submit)) {

			int port = Integer.parseInt(fpxProperty.getPort());
			int bufferChar;
			// Creating TCP/IP socket
			Socket socket = new Socket(fpxProperty.getHost(), port);
			InputStream inSocket = socket.getInputStream();
			OutputStream outSocket = socket.getOutputStream();

			// Creating string msg to send to FPX Plugin
			String txnSellerID = request.getParameter("sellerID");

			myLogger.info("Seller ID : " + txnSellerID);
			// System.out.println("Seller ID : " + txnSellerID);

			String txnSellerOrderNo = request.getParameter("TxnOrderNo");
			txnSellerOrderNo = fpxProperty.getTrasactionNo()
					+ getParam("txtNoPengenalan");
			String URLFPX = request.getParameter("url_FPX");

			myLogger.info("FPX url : " + URLFPX);

			String txnExchangeOrderNo = request.getParameter("TxnOrderNo");
			String tDate = request.getParameter("TxnDate");
			String txnAmount = request.getParameter("txtAmaun");
			String msgToPlugin = "message:request|message.type:AR|message.token:"
					+ request.getParameter("msgToken")
					+ "|message.orderno:"
					+ txnExchangeOrderNo
					+ "|message.ordercount:1|message.txntime:"
					+ tDate
					+ "|message.serialno:1|message.currency:MYR|message.amount:"
					+ txnAmount
					+ "|charge.type:AA|seller.orderno:"
					+ txnSellerOrderNo
					+ "|seller.id:"
					+ txnSellerID
					+ "|seller.bank:01|\n";
			context.put("msgToPlugin", msgToPlugin);
			myLogger.info("txnExchangeOrderNo " + txnExchangeOrderNo);
			myLogger.info("MSG TO FPX PLUGIN " + msgToPlugin);
			// Sending msg to FPX Plugin
			byte buffer[] = msgToPlugin.getBytes();
			outSocket.write(buffer);

			// Receiving response from FPX Plugin
			String msgFromPlugin = "";
			while ((bufferChar = inSocket.read()) != -1) {
				msgFromPlugin = msgFromPlugin + ((char) bufferChar);
			}
			context.put("msgFromPlugin", msgFromPlugin);
			// Closing Socket
			socket.close();

			// screen validation
			showStep = "3";

			// get value
			getAndSetValueMaklumatBayaran();

			// get data fpx
			getModelDataFPX("");

			if ("INDIVIDU".equals(URLFPX)) {
				// System.out.println(fpxProperty.getUrl_1());
				myLogger.info(fpxProperty.getUrl_1());
				context.put("plugin_url", fpxProperty.getUrl_1());
			} else if ("SYARIKAT".equals(URLFPX)) {
				myLogger.info(fpxProperty.getUrl_2());
				// System.out.println(fpxProperty.getUrl_2());
				context.put("plugin_url", fpxProperty.getUrl_2());
			}

			String submit2 = getParam("command2");
			myLogger.info("submit[2] : " + submit2);
			simpanPengesahan(session);
			if ("simpanPengesahan".equals(submit2)) {

				String result = "";
				if (doPost.equals("true")) {
					// simpan data pengesahan
					// result = simpanPengesahan(session);
				}

				context.put("id_datafpx", result);

				// SCREEN PENGESAHAN
				if (result != "") {

					// button cetak
					context.put("mode", "view");

					// get data fpx
					getModelDataFPX(result);

					// FIRST SCREEN
				} else {

					// screen validation
					showStep = "1";

					// reset value
					resetValue();

					// dropdown
					context.put("selectJenisBayaran", HTML
							.SelectJenisBayaranFPX("socJenisBayaran", null,
									null, "style=width:325px"));

				}

			}// close simpanPengesahan

			// screen
			vm = screenFPX;

		}// close goPengesahanBayaran

		else {

			// reset value
			resetValue();

			// dropdown
			context.put("selectJenisBayaran", HTML.SelectJenisBayaranFPX(
					"socJenisBayaran", null, null, "style=width:325px"));

			// list transaksi terdahulu
			ListHistory(id_user);

			// screen
			vm = screenFPX;

		}// close else

		context.put("showStep", showStep);
		return vm;

	}// close public template

	// --- METHOD ---//

	@SuppressWarnings("unchecked")
	private void ListHistory(String id_user) throws Exception {

		System.out.println("id_user :" + id_user);

		Vector listHistory = new Vector();
		listHistory.clear();

		FpxBean.setListHistory(id_user);
		listHistory = FpxBean.getListHistory();
		context.put("listHistory", listHistory);
		context.put("listHistory_Size", listHistory.size());

	}// close ListDokumen

	private void getAndSetMaklumatPembayar(String sorFlagJenisPembayar,
			String valueJenisBayaran) throws Exception {

		context.put("jenisPembayar", sorFlagJenisPembayar);
		context.put("valueJenisBayaran", valueJenisBayaran);

		// context.put("txtNama", getParam("txtNama"));
		// context.put("txtNoPengenalan", getParam("txtNoPengenalan"));
		context.put("txtNoFail", getParam("txtNoFail"));
		context.put("txtAmaun", getParam("txtAmaun"));
		context.put("txtNoBil", getParam("txtNoBil"));
		context.put("txtCatatan", getParam("txtCatatan"));

	}// close getAndSetMaklumatPembayar

	private void resetValueStep2() throws Exception {

		context.put("txtNama", "");
		context.put("txtNoPengenalan", "");
		context.put("txtNoFail", "");
		context.put("txtAmaun", "");
		context.put("txtNoBil", "");
		context.put("txtCatatan", "");

	}// close resetValueStep2

	@SuppressWarnings({ "unchecked", "static-access" })
	private void getModelDataFPX(String id_datafpx) throws Exception {

		Vector dataFPX = new Vector();
		dataFPX.clear();

		model.setDataFPX(id_datafpx);
		dataFPX = model.getDataFPX();

		context.put("dataFPX", dataFPX);

	}// close getModelDataFPX

	@SuppressWarnings({ "unchecked", "static-access" })
	private void simpanPengesahan(HttpSession session) throws Exception {

		String id_user = (String) session.getAttribute("_ekptg_user_id");

		String txnSellerOrderNo = fpxProperty.getTrasactionNo();
		txnSellerOrderNo = txnSellerOrderNo + getParam("txtNoPengenalan");

		DataFpx fpx = new DataFpx();
		fpx.setNama(getParam("txtNama"));
		fpx.setNoKp(getParam("txtNoPengenalan"));
		fpx.setType(getParam("sorFlagJenisPembayar"));
		fpx.setNoBil(getParam("txtNoBil"));
		fpx.setIdJenisBayaran(getParam("valueJenisBayaran"));
		fpx.setNoFail(getParam("txtNoFail"));
		fpx.setAmount(Double.parseDouble(Utils
				.RemoveSymbol(getParam("txtAmaun"))));
		fpx.setCatatan(getParam("txtCatatan"));
		fpx.setTarikhMasuk(getParam("txtSysdate"));
		fpx.setIdMasuk(id_user);
		fpx.setNoTransaksi(txnSellerOrderNo);
		fpx.setStatus("SUBMITTED");
		fpxBean.doProcess(fpx);

	}// close simpanPengesahan

	private void resetValue() throws Exception {

		context.put("id_datafpx", "");
		context.put("txtAmaun", "");
		context.put("valueJenisBayaran", "");
		context.put("txtjenisBayaran", "");
		context.put("txtNoFail", "");
		context.put("txtAmaun", "");
		context.put("txtCatatan", "");
		context.put("txtSysdate", "");

	}// close resetValue

	private void getAndSetValueJenisBayaran() throws Exception {

		context.put("valueJenisBayaran", getParam("socJenisBayaran"));

	}// close getValueMaklumatBayaran

	@SuppressWarnings({ "unchecked", "static-access" })
	private void getAndSetValueMaklumatBayaran() throws Exception {

		Vector jenisBayaran = new Vector();
		jenisBayaran.clear();

		String id_jenisbayaran = getParam("valueJenisBayaran");

		String jenis_bayaran = "";
		model.setDataJenisBayaran(id_jenisbayaran);
		jenisBayaran = model.getDataJenisBayaran();
		if (jenisBayaran.size() != 0) {
			Hashtable jb = (Hashtable) jenisBayaran.get(0);
			jenis_bayaran = (String) jb.get("jenis_bayaran");
		}

		String txtJenisPembayar = "";
		if (getParam("sorFlagJenisPembayar").equals("2")) {
			txtJenisPembayar = "SYARIKAT";
		} else {
			txtJenisPembayar = "INDIVIDU";
		}

		context.put("txtJenisPembayar", txtJenisPembayar);
		context.put("txtNama", getParam("txtNama"));
		context.put("txtNoPengenalan", getParam("txtNoPengenalan"));
		context.put("txtNoBil", getParam("txtNoBil"));
		context.put("jenisPembayar", getParam("sorFlagJenisPembayar"));

		context.put("txtjenisBayaran", jenis_bayaran);
		context.put("valueJenisBayaran", id_jenisbayaran);
		context.put("txtNoFail", getParam("txtNoFail"));
		context.put("txtAmaun", getParam("txtAmaun"));
		context.put("txtCatatan", getParam("txtCatatan"));
		context.put("txtSysdate",
				lebah.util.Util.getDateTime(new Date(), "dd/MM/yyyy"));

	}// close getValueMaklumatBayaran

}// close class
