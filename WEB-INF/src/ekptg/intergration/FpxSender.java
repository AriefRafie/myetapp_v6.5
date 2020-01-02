package ekptg.intergration;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.text.DecimalFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ekptg.fpx.FpxProcessMessage;
import ekptg.fpx.FpxProperty;
import ekptg.fpx.FpxUtility;
import ekptg.fpx.IFpx;
import ekptg.fpx.entity.DataFpx;

public class FpxSender extends HttpServlet  {
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		doGet(req, res);
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		try{
			System.out.println("****INDIRECT message servlet*****");
			FpxProperty fpxProperty = FpxProperty.getInstance();
			String fpxPluginIP = fpxProperty.getHost();
			System.out.println("host "+fpxPluginIP);
			int fpxPluginPort = Integer.parseInt(fpxProperty.getPort());
			System.out.println(fpxPluginPort);
			String resp=null; 
			String strmesgFromFpx=null;
			String msgFromPlugin=null;		
			String msgResult[];	
			String txnResult="";
			int bufferChar;
			
			strmesgFromFpx = req.getParameter("mesgFromFpx");	
			strmesgFromFpx = "message:response|response.string:" + strmesgFromFpx + "\n";
			System.out.println(strmesgFromFpx);
			// Creating TCP/IP socket
			Socket socket = new Socket(fpxPluginIP, fpxPluginPort);
			InputStream inSocket = socket.getInputStream();
			OutputStream outSocket = socket.getOutputStream();
			
			byte buffer[] = strmesgFromFpx.getBytes();
			outSocket.write(buffer);
		
			msgFromPlugin = "";
			while ((bufferChar = inSocket.read()) != -1) 
			{
				msgFromPlugin = msgFromPlugin + ((char)bufferChar) ;
			}
			socket.close();
			
			
			String receiptResult[];	
			String txnReceiptResult="";
			
	  		receiptResult = (msgFromPlugin.
					replaceAll("message.type:", "").
					replaceAll("message.token:", "").
					replaceAll("message.orderno:", "").
					replaceAll("message.ordercount:", "").
					replaceAll("message.txnTime:", "").
					replaceAll("message.fpxTransactionId:", "").
					replaceAll("message.serialno:", "").
					replaceAll("message.currency:", "").
					replaceAll("message.amount:", "").
					replaceAll("charge.type:", "").
					replaceAll("seller.orderno:", "").
					replaceAll("seller.bank:", "").
					replaceAll("buyer.name:", "").	
					replaceAll("buyer.id:", "").
					replaceAll("buyer.bank:", "").
					replaceAll("buyer.bankbranch:", "").
					replaceAll("buyer.accno:", "").
					replaceAll("buyer.iban:", "").
					replaceAll("buyer.name:", "").	
					replaceAll("maker.name:", "").
					replaceAll("debit.authcode:", "").
					replaceAll("debit.authno:", "").
					replaceAll("credit.authcode:", "").
					replaceAll("credit.authno:", "").
					replaceAll(" ", "")).
					split("\\|");
		
		for (int i=0; i < receiptResult.length; i++) 
		{
			txnReceiptResult = txnReceiptResult + receiptResult[receiptResult.length-i-1]+"\n";
		}
		
		String status = "";
		String noTransaksi ="";
		if(receiptResult != null && receiptResult.length >10){
			noTransaksi = receiptResult[11];
			System.out.println();
			if ( (receiptResult[20].equals("00")) && (receiptResult[22].equals("00")) )
			{
				status ="SUCCESSFUL";
				
			}
			else if (receiptResult[20].equals("99"))
			{
				status ="PENDING FOR AUTHORIZER TO APPROVE";
			}
			else if (!(receiptResult[20].equals("00")) || !(receiptResult[20].equals("")) || !(receiptResult[20].equals("99")))
			{
				status ="UNSUCCESSFUL";
			}
		}else{
			status =receiptResult[20];
		}
		DecimalFormat nf = new DecimalFormat("0.00");
		IFpx fpxBean = new FpxProcessMessage();
		DataFpx fpx = new DataFpx();
		fpx.setNoTransaksi(noTransaksi);
		fpx.setStatus(status);
		fpx.setIndirectMessage(status);
		fpx = fpxBean.doProcess(fpx);
		//res.sendRedirect("fpxIndirect.jsp");
		req.setAttribute("STATUS", status);
		req.setAttribute("IDFPX", fpx.getId());
		req.setAttribute("NILAI_WORD", FpxUtility.generateWordEncodeURL(fpx.getAmount()).toUpperCase());
		req.setAttribute("NO_TRANSAKSI", fpx.getNoTransaksi());
		req.setAttribute("AMOUNT", nf.format(fpx.getAmount()));
		req.setAttribute("JENIS_BAYARAN", fpx.getJenisBayaran());
		req.setAttribute("TARIKH_TRANSAKSI",fpx.getTransactionDate().toString());
		RequestDispatcher forw = req.getRequestDispatcher("fpxIndirect.jsp");
		forw.forward(req, res);
		
		}catch(Exception e){
			e.printStackTrace();
		}
		


	}
}