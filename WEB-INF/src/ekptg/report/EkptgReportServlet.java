package ekptg.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.DbException;
import lebah.db.SQLRenderer;
import lebah.servlets.IServlet2;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.JRTextExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;
import oracle.sql.BLOB;

import org.apache.commons.lang.SystemUtils;
import org.apache.log4j.Logger;

import com.lowagie.text.pdf.PdfReader;

import ekptg.helpers.AuditTrail;
import ekptg.helpers.DB;

public abstract class EkptgReportServlet implements IServlet2 {
	static Logger myLogger = Logger.getLogger(ekptg.report.EkptgReportServlet.class);

	private String reportFileName;
	private Map<String, Object> parameters;
	private String SQL = null;
	private String folderName;
	private boolean setMaklumatPegawai;
	private boolean setMaklumatPermohonan;
	private boolean setMaklumatPemohon;
	private boolean setMaklumatSimati;
	private boolean setMaklumatPerbicaraan;
	private boolean setMaklumatHTA;
	private boolean setVersion;
	private boolean generateTextFile;

	private String doSaveVersion;
	private String borang;
	private String idfail;
	private String nofail;
	private String flag_batal;
	private String idmasuk;
	private String errorMsg;
	private String flag_keb_kemaskini;
	
	private String flagPNB;
	private String idperbicaraan;

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	private Vector<Hashtable<String,String>> beanMaklumatPegawai = null;
	private Vector<Hashtable<String,String>> beanMaklumatPermohonan = null;
	private Vector<Hashtable<String,String>> beanMaklumatPemohon = null;
	private Vector<Hashtable<String,String>> beanMaklumatSimati = null;
	private Vector<Hashtable<String,String>> beanMaklumatPerbicaraan = null;
	private Vector<Hashtable<String,String>> beanMaklumatHTA = null;
	
	String new_url = "";

	public abstract void doProcessing(HttpServletRequest request, HttpServletResponse response, ServletContext context, Map<String, Object> parameters)
		throws Exception;

	public void doService(HttpServletRequest request, HttpServletResponse response, ServletContext context) throws IOException,
		ServletException {		
		//en azam 14082017		
		String url = new_url.toString();
		new_url = url.replace("10.19.144.82", "poc.myetapp.gov.my");
		
		// Security Check
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("_ekptg_user_id");
		myLogger.info("**************** check USER_ID SESSION : "+user_id);
		
		if(user_id==null){
			myLogger.info("**************** check USER ID MANUAL : "+(String)request.getAttribute("user_id"));
			user_id = (String)request.getAttribute("user_id");
		}
		
		myLogger.info("**************** check USER ID FINAL : "+user_id);
		this.idmasuk = user_id;
		if (user_id == null) {
			usernotvalid(response);
		} else if (this.errorMsg != null) {
			showErrors(response);
		} else {
			String contextPath = request.getContextPath();
			//myLogger.info("contextPath="+contextPath);
			Map<String, Object> parameters = new HashMap<String, Object>();
			if (this.parameters != null)
				parameters = this.parameters;

			String realPathReport = getReportPath(context,"reports");
			
			// parameters.put("BaseDir",new File(context.getRealPath("/img/")));
			parameters.put("BaseDir", context.getRealPath("/img/"));
			// Report folder
			// parameters.put("ReportDir",context.getRealPath("/reports/"));
			myLogger.info("146 :realPathReport="+realPathReport);
			parameters.put("ReportDir", realPathReport);
			// Get all parameters from query String
			String name = "";
			String value = "";
			Enumeration allparam = request.getParameterNames();
			for (; allparam.hasMoreElements();) {
				// Get the name of the request parameter
				name = (String) allparam.nextElement();
				// Get the value of the request parameter
				value = request.getParameter(name);
				// System.out.println(name +"="+value);
				parameters.put(name, value);
			
			}
			// Get all paramaters to global
			this.parameters = parameters;
			try {
				//myLogger.info("1");
				doProcessing(request, response, context, parameters);
				//myLogger.info("2");
				if (this.errorMsg != null) {
					showErrors(response);
					return;
				}
				//myLogger.info("3");
				//
				String reportFileName = this.reportFileName;
				String folderName = this.folderName;
				if (reportFileName == null || "".equals(reportFileName)) {
					fileNameEmpty(response);
					return;
			
				}
				// Versioning
				myLogger.debug("this.setVersion:" + this.setVersion);
				myLogger.debug("this.doSaveVersion:" + this.doSaveVersion);
				
				if(this.flag_keb_kemaskini!=null){
					
					if (this.setVersion) {
						
						if ("pindaanN".equals(this.doSaveVersion)) {
							doNoPindaanSaveVersioning(request, response, context, request.getQueryString());
							return;
				
						}else if ("pindaanP".equals(this.doSaveVersion)) {	
							doPindaanSaveVersioning(request, response, context, request.getQueryString());
							return;
							
						} else if ("view".equals(this.doSaveVersion)) {
							viewPDF(request, response, (String) parameters.get("idborang"));
							return;
						
						}else{
							askForVersionPindaan(request, response);
							return;
						
						}
					
					}
					
				}else{
					if (this.setVersion) {
						if ("yes".equals(this.doSaveVersion)) {
							doSaveVersioning(request, response, context, request.getQueryString());
							return;
						
						}else if ("hantarPNB".equals(this.doSaveVersion)) {
							hantarKePNB(request, response, context, request.getQueryString());
							return;
						
						}else if ("no".equals(this.doSaveVersion)) {
							// Ok-now we just print the report
						} else if ("view".equals(this.doSaveVersion)) {
							viewPDF(request, response, (String) parameters.get("idborang"));
							return;
						
						}else if ("viewPNB".equals(this.doSaveVersion)) {
							viewPDFPNB(request, response, (String) parameters.get("idborangpnb"));
							return;
						
						}else if ("popupPNB".equals(this.doSaveVersion)) {
							askForPNB(request, response);
							return;
						
						}else {
							askForVersion(request, response);
							return;
						
						}
	
					}
				
				}

				// Additional
				if (this.setMaklumatPegawai)
					doPegawai(request, response, context, parameters);
				if (this.setMaklumatPermohonan)
					doPermohonan(request, response, context, parameters);
				if (this.setMaklumatPemohon)
					doPemohon(request, response, context, parameters);
				if (this.setMaklumatSimati)
					doSimati(request, response, context, parameters);
				if (this.setMaklumatPerbicaraan)
					doPerbicaraan(request, response, context, parameters);
				if (this.setMaklumatHTA)
					doHTA(request, response, context, parameters);

				//myLogger.info("checking...");
				myLogger.debug("checking...\nbil dokumen="+parameters.get("bilDokumen"));
				if ("".equals(parameters.get("bilDokumen"))) {
					parameters.put("bilDokumen", "1");
				}

			} catch (Exception e) {
				// e.printStackTrace();
				doException(response, contextPath, e);
				return;
			
			}

			byte[] bytes = null;
			Db db = null;
			Statement statement = null;
			ResultSet resultSet = null;
			JRResultSetDataSource resultSetDataSource = null;
			Connection conn = null;
			String reportType;
			try {
				conn = new Db().getConnection();
				// String reportType = request.getParameter("reportType");
				reportType = (String) session.getAttribute("rFormat");
				JasperReport jasperReport = getCompiledReport(reportFileName, folderName, context);
				// Automatically set when no data section
				// jasperReport.setWhenNoDataType(jasperReport.WHEN_NO_DATA_TYPE_NO_DATA_SECTION);
				if (this.SQL != null) {
					statement = conn.createStatement();
					resultSet = statement.executeQuery(SQL);
					resultSetDataSource = new JRResultSetDataSource(resultSet);
			
				}

				if (isGenerateTextFile()) {
					createTEXTReport(request, response, parameters, jasperReport, conn, (String) parameters.get("id_simati"));
					return;
				
				}
				if ("HTML".equals(reportType)) {
					JasperPrint jasperPrint = null;
					if (resultSetDataSource != null) {
						jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, resultSetDataSource);
					} else {
						jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);
					}
					createHTMLReport(jasperPrint, request, response);
					
				} else if ("RTF".equals(reportType)) {
					// rtfConverter(response);
					createRTFReport(response, parameters, jasperReport, conn, request);
				} else if ("EXCEL".equals(reportType)) {
					// rtfConverter(response);
					createExcelReport(response, parameters, jasperReport, conn);
				}
				// Assume it is a PDF report
				// else if ("PDF".equals(reportType) || "".equals(reportType))
				else {
					if (resultSetDataSource != null) {
						myLogger.info("resultSetDataSource != null");
						createPDFReport(response, parameters, jasperReport, resultSetDataSource);
					} else {
						createPDFReport(response, parameters, jasperReport, conn);
					}
				}
			} catch (Exception e) {
				doException(response, contextPath, e);
				// return;
			} finally {
				try {
					if (resultSet != null)
						resultSet.close();
					if (statement != null)
						statement.close();
					if (conn != null)
						conn.close();
					if (db != null)
						db.close();
				} catch (SQLException xx) {}
			}
		}
	
	}

	public void setReportName(String s) {
		this.reportFileName = s;
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public void setParameters(Map<String,Object> params) {
		this.parameters = params;
	}

	public void setSQL(String sql) {
		this.SQL = sql;
	}

	public void flagMaklumatPegawai(boolean flagPegawai) {
		this.setMaklumatPegawai = flagPegawai;
	}

	public void flagMaklumatPermohonan(boolean flagPermohonan) {
		this.setMaklumatPermohonan = flagPermohonan;
	}

	public void flagMaklumatPemohon(boolean flagPemohon) {
		this.setMaklumatPemohon = flagPemohon;
	}

	public void flagMaklumatSimati(boolean flagSimati) {
		this.setMaklumatSimati = flagSimati;
	}

	public void flagMaklumatPerbicaraan(boolean flagPerbicaraan) {
		this.setMaklumatPerbicaraan = flagPerbicaraan;
	}

	public void flagMaklumatHTA(boolean flagHTA) {
		this.setMaklumatHTA = flagHTA;
	}

	public boolean isGenerateTextFile() {
		return generateTextFile;
	}

	public void setGenerateTextFile(boolean generateTextFile) {
		this.generateTextFile = generateTextFile;
	}

	private void createHTMLReport(JasperPrint jasperPrint, HttpServletRequest request, HttpServletResponse response) throws IOException,
		JRException {
		HttpSession session = request.getSession();
		Map imagesMap = new HashMap();
		request.getSession().setAttribute("IMAGES_MAP", imagesMap);
		response.setContentType("application/html");
		response.setHeader("Content-Disposition", "inline; filename=\"report.html\"");

		JRHtmlExporter exporter = new JRHtmlExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, response.getWriter());
		exporter.setParameter(JRHtmlExporterParameter.IMAGES_MAP, imagesMap);
		exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, "/" + session.getAttribute("_portal_appname") + "/servlets/image?image=");
		exporter.exportReport();
	
	}

	private void createRTFReport(HttpServletResponse response, Map parameters, JasperReport jasperReport, Connection conn,
		HttpServletRequest request) throws JRException, SQLException, IOException, ServletException {

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);

		response.setContentType("application/rtf");
		response.setHeader("Content-Disposition", "inline; filename=\"file.rtf\"");

		JRRtfExporter docxExporter = new JRRtfExporter();
		docxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);

		ServletOutputStream servletOutputStream = response.getOutputStream();
		docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);

		try {
			docxExporter.exportReport();
		} catch (JRException e) {
			throw new ServletException(e);
		} finally {
			if (servletOutputStream != null) {
				try {
					servletOutputStream.close();
				} catch (IOException ex) {}
			}
		}
	}

	/**
	 * Note : Create New untuk Cetak Excel
	 * @param response
	 * @param parameters
	 * @param jasperReport
	 * @param conn
	 * @throws JRException
	 * @throws SQLException
	 * @throws IOException
	 */
	private void createExcelReport(HttpServletResponse response, Map parameters, JasperReport jasperReport, Connection conn) 
		throws JRException,SQLException, IOException {
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);
		OutputStream ouputStream = response.getOutputStream();
		net.sf.jasperreports.engine.export.JExcelApiExporter exporter = null;
		try{
			exporter = new net.sf.jasperreports.engine.export.JExcelApiExporter();
			response.setContentType("application/xls");
			response.setHeader("Content-Disposition", "inline; filename=\"MyeTaPP.xls\"");
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
			exporter.exportReport();
			
		}catch (JRException e){
			e.printStackTrace();
		}
		ouputStream.flush();  
		ouputStream.close();
	
	}
	//	******** End Create New by zulfazdliabuas@gmail.com *********

	private void createTEXTReport(HttpServletRequest request, HttpServletResponse response, Map parameters, JasperReport jasperReport,
		Connection conn, String title) throws JRException, SQLException, IOException {
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);
		JRTextExporter exporterTxt = new JRTextExporter();
		exporterTxt.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);

		exporterTxt.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, request.getRealPath("/reports") + "/" + title + ".txt");
		exporterTxt.setParameter(JRTextExporterParameter.CHARACTER_WIDTH, new Integer(7));

		exporterTxt.setParameter(JRTextExporterParameter.CHARACTER_HEIGHT, new Integer(11));

		exporterTxt.exportReport();

		File f = new File(request.getRealPath("/reports") + "/" + title + ".txt");
		FileInputStream fin = new FileInputStream(f);
		ServletOutputStream outStream = response.getOutputStream();
		// SET THE MIME TYPE.
		response.setContentType("application/text");
		// set content dispostion to attachment in with file name.
		// case the open/save dialog needs to appear.
		response.setHeader("Content-Disposition", "attachment;filename=JPN_" + title + ".txt");

		byte[] buffer = new byte[1024];
		int n = 0;
		while ((n = fin.read(buffer)) != -1) {
			outStream.write(buffer, 0, n);
			System.out.println(buffer);
		}

		outStream.flush();
		fin.close();
		outStream.close();
	
	}

	private void createPDFReport(HttpServletResponse response, Map parameters, JasperReport jasperReport, Connection conn)
		throws JRException, SQLException, IOException {
		byte[] bytes = null;
		bytes = JasperRunManager.runReportToPdf(jasperReport, parameters, conn);

		if ((bytes != null) && (bytes.length > 0)) {
			response.setContentType("application/pdf");
			response.setContentLength(bytes.length);
			ServletOutputStream ouputStream = response.getOutputStream();
			ouputStream.write(bytes, 0, bytes.length);
			ouputStream.flush();
			ouputStream.close();
		
		} else
			emptyResponse(response);	
	}

	private void createPDFReport(HttpServletResponse response, Map parameters, JasperReport jasperReport
		, JRResultSetDataSource ds) throws JRException, SQLException, IOException {
		byte[] bytes = null;
		bytes = JasperRunManager.runReportToPdf(jasperReport, parameters, ds);

		if ((bytes != null) && (bytes.length > 0)) {
			response.setContentType("application/pdf");
			response.setContentLength(bytes.length);
			response.setHeader("Content-Disposition", "attachment; filename=eTapp.pdf");
			ServletOutputStream ouputStream = response.getOutputStream();
			ouputStream.write(bytes, 0, bytes.length);
			ouputStream.flush();
			ouputStream.close();
			
		} else
			emptyResponse(response);

	}

	private void usernotvalid(HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Ralat</title>");
		out.println("</head>");
		out.println("<body bgcolor=\"white\">");
		out.println("<span class=\"bold\">Maaf, sessi anda telah tamat.</span>");
		out.println("</body>");
		out.println("</html>");
		
	}

	private void rtfConverter(HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>RTF Converter</title>");
		out.println("</head>");
		out.println("<body bgcolor=\"white\">");
		out.println("<span class=\"bold\">RTF Converter In progress....</span>");
		out.println("</body>");
		out.println("</html>");
	
	}

	private void emptyResponse(HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Empty response.</title>");
		out.println("</head>");
		out.println("<body bgcolor=\"white\">");
		out.println("<span class=\"bold\">Empty response.</span>");
		out.println("</body>");
		out.println("</html>");
	
	}

	private void fileNameEmpty(HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"../default.css\" />");
		out.println("<div class=\"error\">");
		out.println("File Name Empty.");
		out.println("</div>");
		out.println("</html>");
	
	}

	private void showErrors(HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<center>" + this.getErrorMsg() + "</center>");
		out.println("</html>");
	
	}

	private void doException(HttpServletResponse response, String contextPath, Exception e) throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"" + contextPath + "/default.css\" />");
		out.println("<div class=\"error\">");
		out.println("JasperReports encountered this error :<br><br>");
		out.println("<pre>");
		// e.printStackTrace(out);
		out.println(e.getMessage());
		out.println("</pre>");
		out.println("</div>");
		out.close();
	
	}

	private JasperReport getCompiledReport(String fileName, String folderName, ServletContext context) throws JRException {
		File reportFile;
		String path;
		if (folderName != null) {
			// path = "/reports/" + folderName + "/" + fileName ;
			path = File.separator + "reports" + File.separator + folderName + File.separator + fileName;
		} else {
			// path = "/reports/" + fileName ;
			path = File.separator + "reports" + File.separator + fileName;
		}

		if (SystemUtils.IS_OS_LINUX) {
			path = path.replace("\\", "/");
		} else if (SystemUtils.IS_OS_WINDOWS) {
			path = path.replace("/", "\\");
		}
		String realPathJasper = getReportPath(context,path+".jasper");
		String realPathJrxml = getReportPath(context,path+".jrxml");

		reportFile = new File(realPathJasper);

		/**
		 *  If compiled file is not found, then
		 * // compile XML template
		 */	
		if (!reportFile.exists()) {
			JasperCompileManager.compileReportToFile(realPathJrxml);
		}
		/**
		 * Add By Azam add on 18 Nov,2009
		 * check if .jrxml > .jasper then compile again
		 */
		File jxml = new File(realPathJrxml);
		if (jxml.lastModified() > reportFile.lastModified()) {
			JasperCompileManager.compileReportToFile(realPathJrxml);
		}
		// Since it's in development, just ignore this part first, everytime we
		// run then
		// we should always recompile // it will be a litte bit slow in terms of
		// performance
		// JasperCompileManager.compileReportToFile(context.getRealPath(path+
		// ".jrxml"));
		myLogger.info("realPathJrxml:" + realPathJrxml);
		myLogger.info("realPathJasper:" + realPathJasper);
		myLogger.info("context.getContextPath():" + context.getContextPath());

		JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(reportFile.getPath());
		return jasperReport;
	
	}

	public void setIdPermohonan(String idfail, Map<String, String> parameters) {
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			sql = "Select id_permohonan from tblppkpermohonan where id_fail='" + idfail + "'";
			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				parameters.put("idpermohonan", rs.getString("id_permohonan"));
			} else {
				parameters.put("idpermohonan", "0");// this cannot be true.
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}

	}

	public void doPegawai(HttpServletRequest request
		, HttpServletResponse response
		, ServletContext context
		, Map<String, Object> parameters) throws Exception {
		Vector<Hashtable<String,String>> listPegawai = null;
		try {
			listPegawai = new Vector<Hashtable<String,String>>();
			setMaklumatPegawai(parameters);
			listPegawai = getBeanMaklumatPegawai();
			if (listPegawai.size() != 0) {
				Hashtable<String,String> h = (Hashtable<String,String>) listPegawai.get(0);
				parameters.put("namaPegawai", h.get("nama").toString());
				parameters.put("jawatan", h.get("jawatan").toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void doPermohonan(HttpServletRequest request
		, HttpServletResponse response
		, ServletContext context
		, Map<String, Object> parameters) throws Exception {
		Vector<Hashtable<String,String>> listPermohonan = null;
		try {

			listPermohonan = new Vector<Hashtable<String,String>>();
			setMaklumatPermohonan(parameters);
			listPermohonan = getBeanMaklumatPermohonan();

			if (listPermohonan.size() != 0) {
				Hashtable<String,String> h = (Hashtable<String,String>) listPermohonan.get(0);
				parameters.put("idPermohonan", h.get("idPermohonan").toString());
				parameters.put("idNegeriMhn", h.get("idNegeri").toString());
				parameters.put("idDaerahMhn", h.get("idDaerah").toString());
				parameters.put("jumHTATarikhMohon", h.get("jumHTATkhMhn").toString());
				parameters.put("jumHTATarikhMati", h.get("jumHTATkhMati").toString());
				parameters.put("jumHATarikhMohon", h.get("jumHATkhMhn").toString());
				parameters.put("jumHATarikhMati", h.get("jumHATkhMati").toString());
				parameters.put("jumHartaTarikhMohon", h.get("jumHartaTkhMhn").toString());
				parameters.put("jumHartaTarikhMati", h.get("jumHartaTkhMati").toString());
				parameters.put("tarikhMohon", h.get("tarikhMhn").toString());
				parameters.put("NoFail", h.get("noFail").toString());
				parameters.put("seksyen", h.get("seksyen").toString());

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void doPemohon(HttpServletRequest request
		, HttpServletResponse response
		, ServletContext context
		, Map<String,Object> parameters) throws Exception {
		Vector<Hashtable<String,String>> listPemohon = null;
		try {
			listPemohon = new Vector<Hashtable<String,String>>();
			setMaklumatPemohon(parameters);
			listPemohon = getBeanMaklumatPemohon();

			if (listPemohon.size() != 0) {
				Hashtable<String,String> h = (Hashtable<String,String>) listPemohon.get(0);
				parameters.put("idPermohonan", h.get("idPermohonan").toString());
				parameters.put("namaPemohon", h.get("namaPemohon").toString());
				parameters.put("singleLineAlamatPemohon", h.get("singleLineAlamatPemohon").toString());
				parameters.put("newLineAlamatPemohon", h.get("newLineAlamatPemohon").toString());
				parameters.put("no_kp_pemohon", h.get("no_kp_pemohon").toString());
				parameters.put("namaNegeriPemohon", h.get("namaNegeriPemohon").toString());
				parameters.put("namaBandarPemohon", h.get("namaBandarPemohon").toString());
				parameters.put("poskodPemohon", h.get("poskodPemohon").toString());
				parameters.put("noTel", h.get("noTel").toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	public void doSimati(HttpServletRequest request
		, HttpServletResponse response
		, ServletContext context
		, Map<String,Object> parameters) throws Exception {
		Vector<Hashtable<String,String>> listSimati = null;
		try {

			listSimati = new Vector<Hashtable<String,String>>();
			setMaklumatSimati(parameters);
			listSimati = getBeanMaklumatSimati();

			if (listSimati.size() != 0) {
				Hashtable<String,String> h = (Hashtable<String,String>) listSimati.get(0);
				parameters.put("idPermohonan", h.get("idPermohonan").toString());
				parameters.put("namaSimati", h.get("namaSimati").toString());
				parameters.put("singleLineAlamatSimati", h.get("singleLineAlamatSimati").toString());
				parameters.put("newLineAlamatSimati", h.get("newLineAlamatSimati").toString());
				parameters.put("no_kp_simati", h.get("no_kp_simati").toString());
				parameters.put("namaNegeriSimati", h.get("namaNegeriSimati").toString());
				parameters.put("namaBandarSimati", h.get("namaBandarSimati").toString());
				parameters.put("poskodSimati", h.get("poskodSimati").toString());
				parameters.put("noSijilMati", h.get("noSijilMati").toString());
				parameters.put("tarikhMati", h.get("tarikhMati").toString());
				parameters.put("waktuKematian", h.get("waktuKematian").toString());
				parameters.put("buktiKematian", h.get("buktiKematian").toString());

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	public void doPerbicaraan(HttpServletRequest request
		, HttpServletResponse response
		, ServletContext context
		, Map<String,Object> parameters) throws Exception {
		Vector<Hashtable<String,String>> listPerbicaraan = null;

		try {
			listPerbicaraan = new Vector<Hashtable<String,String>>();
			setMaklumatPerbicaraan(parameters);
			listPerbicaraan = getBeanMaklumatPerbicaraan();

			if (listPerbicaraan.size() != 0) {
				Hashtable<String,String> h = (Hashtable<String,String>) listPerbicaraan.get(0);
				parameters.put("idPermohonan", h.get("idPermohonan").toString());
				parameters.put("pegPengendali", h.get("pegPengendali").toString());
				parameters.put("singleLineAlamat", h.get("singleLineAlamat").toString());
				parameters.put("newLineAlamat", h.get("newLineAlamat").toString());
				parameters.put("namaNegeri", h.get("namaNegeri").toString());
				parameters.put("bandar", h.get("bandar").toString());
				parameters.put("poskod", h.get("poskod").toString());
				parameters.put("masaBicara", h.get("masaBicara").toString());

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void doHTA(HttpServletRequest request
		, HttpServletResponse response
		, ServletContext context
		, Map<String,Object> parameters) throws Exception {
		Vector<Hashtable<String,String>> listHTA = null;
		try {
			listHTA = new Vector<Hashtable<String,String>>();
			setMaklumatHTA(parameters);
			listHTA = getBeanMaklumatHTA();

			if (listHTA.size() != 0) {
				Hashtable<String,String> h = (Hashtable<String,String>) listHTA.get(0);
				parameters.put("idnegerijaagan", h.get("idnegerijaagan").toString());
				parameters.put("iddaerahjagaan", h.get("iddaerahjagaan").toString());

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void setMaklumatPegawai(Map<String,Object> parameters) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			beanMaklumatPegawai = new Vector<Hashtable<String,String>>();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("INITCAP(NAMA_PEGAWAI)AS NAMA_PEGAWAI");
			r.add("INITCAP(JAWATAN) AS JAWATAN");

			r.add("ID_UNITPSK", parameters.get("idPegawai").toString());

			sql = r.getSQLSelect("TBLPPKRUJUNIT");
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable<String,String> h;
			//int bil = 1;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable<String,String>();

				if (parameters.get("flagReport").toString().equals("S")) {
					h.put("nama", rs.getString("NAMA_PEGAWAI").toUpperCase());
					h.put("jawatan", rs.getString("JAWATAN"));
				} else {
					h.put("nama", rs.getString("NAMA_PEGAWAI").toUpperCase());
					h.put("jawatan", rs.getString("JAWATAN").toUpperCase());
				}

				beanMaklumatPegawai.addElement(h);
				//bil++;
				count++;
			}

		} finally {
			if (db != null)
				db.close();
		}

	}

	private void setMaklumatPermohonan(Map<String,Object> parameters) throws Exception {
		Db db = null;
		String sql = "";
		//System.out.println("No fail = " + parameters.get("NoFail"));
		try {
			db = new Db();
			beanMaklumatPermohonan = new Vector<Hashtable<String,String>>();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("A.ID_PERMOHONAN");
			r.add("INITCAP(C.NAMA_NEGERI) AS NAMA_NEGERI");
			r.add("INITCAP(D.NAMA_DAERAH) AS NAMA_DAERAH");
			r.add("A.JUMLAH_HTA_TARIKHMOHON");
			r.add("A.JUMLAH_HTA_TARIKHMATI");
			r.add("A.JUMLAH_HA_TARIKHMOHON");
			r.add("A.JUMLAH_HA_TARIKHMATI");
			r.add("A.JUMLAH_HARTA_TARIKHMOHON");
			r.add("A.JUMLAH_HARTA_TARIKHMATI");
			r.add("A.TARIKH_MOHON");
			r.add("B.NO_FAIL");
			r.add("A.SEKSYEN");

			r.add("B.ID_FAIL", r.unquote("A.ID_FAIL"));
			r.add("C.ID_NEGERI", r.unquote("A.ID_NEGERIMHN"));
			r.add("D.ID_DAERAH", r.unquote("A.ID_DAERAHMHN"));

			r.add("B.NO_FAIL", parameters.get("NoFail").toString());

			sql = r.getSQLSelect("TBLPPKPERMOHONAN A, TBLPFDFAIL B, TBLRUJNEGERI C,TBLRUJDAERAH D");
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable<String,String> h;
//			int bil = 1;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable<String,String>();

				h.put("idPermohonan", rs.getString("ID_PERMOHONAN"));
				h.put("idNegeri", rs.getString("NAMA_NEGERI"));
				h.put("idDaerah", rs.getString("NAMA_DAERAH"));
				h.put("jumHTATkhMhn", rs.getString("JUMLAH_HTA_TARIKHMOHON") == null ? "" : rs.getString("JUMLAH_HTA_TARIKHMOHON"));
				h.put("jumHTATkhMati", rs.getString("JUMLAH_HTA_TARIKHMATI") == null ? "" : rs.getString("JUMLAH_HTA_TARIKHMATI"));
				h.put("jumHATkhMhn", rs.getString("JUMLAH_HA_TARIKHMOHON") == null ? "" : rs.getString("JUMLAH_HA_TARIKHMOHON"));
				h.put("jumHATkhMati", rs.getString("JUMLAH_HA_TARIKHMATI") == null ? "" : rs.getString("JUMLAH_HA_TARIKHMATI"));
				h.put("jumHartaTkhMhn", rs.getString("JUMLAH_HARTA_TARIKHMOHON") == null ? "" : rs.getString("JUMLAH_HARTA_TARIKHMOHON"));
				h.put("jumHartaTkhMati", rs.getString("JUMLAH_HARTA_TARIKHMATI") == null ? "" : rs.getString("JUMLAH_HARTA_TARIKHMATI"));
				h.put("tarikhMhn", rs.getString("TARIKH_MOHON"));
				h.put("noFail", rs.getString("NO_FAIL"));
				h.put("seksyen", rs.getString("SEKSYEN"));
				beanMaklumatPermohonan.addElement(h);
//				bil++;
				count++;
				
			}

		} finally {
			if (db != null)
				db.close();
		}
		
	}

	private void setMaklumatPemohon(Map<String,Object> parameters) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			beanMaklumatPemohon = new Vector<Hashtable<String,String>>();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("B.ID_PERMOHONAN");
			r.add("A.NAMA_PEMOHON");
			r.add("A.ALAMAT1_SURAT");
			r.add("A.ALAMAT2_SURAT");
			r.add("A.ALAMAT3_SURAT");
			r.add("C.NAMA_NEGERI");
			r.add("D.KETERANGAN AS NAMA_BANDAR");
			r.add("A.POSKOD_SURAT");
			r.add("A.NO_TEL_SURAT");

			r.add("E.ID_FAIL", r.unquote("B.ID_FAIL"));
			r.add("B.ID_PEMOHON", r.unquote("A.ID_PEMOHON"));
			r.add("C.ID_NEGERI(+)", r.unquote("A.ID_NEGERISURAT"));
			r.add("D.ID_BANDAR(+)", r.unquote("A.ID_BANDARSURAT"));

			r.add("E.NO_FAIL", parameters.get("NoFail").toString());

			sql = r.getSQLSelect("TBLPPKPEMOHON A, TBLPPKPERMOHONAN B, TBLRUJNEGERI C,TBLRUJBANDAR D, TBLPFDFAIL E");
			myLogger.info(sql);
			ResultSet rs = stmt.executeQuery(sql);
			// System.out.println("sql pemohon == " + sql);

			Hashtable<String,String> h;
			//int bil = 1;
			Integer count = 0;
			String alamat1 = "";
			String alamat2 = "";
			String alamat3 = "";
			String poskod = "";
			String bandar = "";
			String negeri = "";
			while (rs.next()) {
				h = new Hashtable<String,String>();
				alamat1 = rs.getString("ALAMAT1_SURAT") == null ? "" : rs.getString("ALAMAT1_SURAT");
				alamat2 = rs.getString("ALAMAT2_SURAT") == null ? "" : rs.getString("ALAMAT2_SURAT");
				alamat3 = rs.getString("ALAMAT3_SURAT") == null ? "" : rs.getString("ALAMAT3_SURAT");
				poskod = rs.getString("POSKOD_SURAT") == null ? "" : rs.getString("POSKOD_SURAT");
				bandar = rs.getString("NAMA_BANDAR") == null ? "" : rs.getString("NAMA_BANDAR").toUpperCase();
				negeri = rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI");

				h.put("idPermohonan", rs.getString("ID_PERMOHONAN"));
				h.put("namaPemohon", rs.getString("NAMA_PEMOHON").toUpperCase());

				if (alamat3 != "") {
					h.put("singleLineAlamatPemohon", alamat1 + alamat2 + alamat3);
					h.put("newLineAlamatPemohon", alamat1 + "\n\n" + alamat2 + "\n\n" + alamat3 + "\n\n" + poskod + " " + bandar + "\n\n"
							+ negeri);
				} else {
					h.put("singleLineAlamatPemohon", alamat1 + alamat2);
					h.put("newLineAlamatPemohon", alamat1 + "\n\n" + alamat2 + "\n\n" + poskod + " " + bandar + "\n\n" + negeri);
				}
				h.put("no_kp_pemohon", noKpPemohon(rs.getString("ID_PERMOHONAN").toString()));
				h.put("namaNegeriPemohon", rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI"));
				h.put("namaBandarPemohon", rs.getString("NAMA_BANDAR") == null ? "" : rs.getString("NAMA_BANDAR").toUpperCase());
				h.put("poskodPemohon", rs.getString("POSKOD_SURAT") == null ? "" : rs.getString("POSKOD_SURAT"));
				h.put("noTel", rs.getString("NO_TEL_SURAT") == null ? "" : rs.getString("NO_TEL_SURAT"));
				beanMaklumatPemohon.addElement(h);
				//bil++;
				count++;
			}

		} finally {
			if (db != null)
				db.close();
		}
		
	}
// Semakan sehingga baris 1044, 21/01/2020
	private void setMaklumatSimati(Map<String,Object> parameters) throws Exception {
		Db db = null;
		String sql = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			db = new Db();
			beanMaklumatSimati = new Vector<Hashtable<String,String>>();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("B.ID_PERMOHONAN");
			r.add("A.NAMA_SIMATI");
			r.add("A.NO_SIJIL_MATI");
			r.add("A.TARIKH_MATI");
			r.add("A.WAKTU_KEMATIAN");
			r.add("G.KETERANGAN");
			r.add("A.ALAMAT_1");
			r.add("A.ALAMAT_2");
			r.add("A.ALAMAT_3");
			r.add("C.NAMA_NEGERI");
			r.add("D.KETERANGAN AS NAMA_BANDAR");
			r.add("A.POSKOD");

			r.add("E.ID_FAIL", r.unquote("B.ID_FAIL"));
			r.add("B.ID_PERMOHONAN", r.unquote("F.ID_PERMOHONAN"));
			r.add("A.ID_SIMATI", r.unquote("F.ID_SIMATI"));
			r.add("C.ID_NEGERI(+)", r.unquote("A.ID_NEGERI"));
			r.add("D.ID_BANDAR(+)", r.unquote("A.ID_BANDAR"));
			r.add("G.ID_BUKTIMATI", r.unquote("A.ID_BUKTIMATI"));

			r.add("E.NO_FAIL", parameters.get("NoFail").toString());

			sql = r.getSQLSelect("TBLPPKSIMATI A, TBLPPKPERMOHONAN B, TBLRUJNEGERI C,TBLRUJBANDAR D, TBLPFDFAIL E, TBLPPKPERMOHONANSIMATI F, TBLPPKRUJBUKTIMATI G");
			myLogger.debug(sql);
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable<String,String> h;
			//int bil = 1;
			Integer count = 0;

			String alamat1 = "";
			String alamat2 = "";
			String alamat3 = "";

			while (rs.next()) {
				h = new Hashtable<String,String>();
				alamat1 = rs.getString("ALAMAT_1") == null ? "" : rs.getString("ALAMAT_1").toUpperCase();
				alamat2 = rs.getString("ALAMAT_2") == null ? "" : rs.getString("ALAMAT_2").toUpperCase();
				alamat3 = rs.getString("ALAMAT_3") == null ? "" : rs.getString("ALAMAT_3").toUpperCase();

				h.put("idPermohonan", rs.getString("ID_PERMOHONAN"));
				h.put("namaSimati", rs.getString("NAMA_SIMATI").toUpperCase());
				h.put("singleLineAlamatSimati", alamat1 + alamat2 + "," + alamat3);
				h.put("newLineAlamatSimati", alamat1 + "\n" + alamat2 + "\n" + alamat3);
				h.put("no_kp_simati", noKpSimati(rs.getString("ID_PERMOHONAN")));
				h.put("namaNegeriSimati", rs.getString("NAMA_NEGERI") == null ? "" : rs.getString("NAMA_NEGERI").toUpperCase());
				h.put("namaBandarSimati", rs.getString("NAMA_BANDAR") == null ? "" : rs.getString("NAMA_BANDAR").toUpperCase());
				h.put("poskodSimati", rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD"));
				h.put("noSijilMati", rs.getString("NO_SIJIL_MATI"));
				h.put("tarikhMati", rs.getString("TARIKH_MATI") == null ? "" : sdf.format(rs.getDate("TARIKH_MATI")));
				h.put("waktuKematian", rs.getString("WAKTU_KEMATIAN") == null ? "" : rs.getString("WAKTU_KEMATIAN"));
				h.put("buktiKematian", rs.getString("KETERANGAN"));
				beanMaklumatSimati.addElement(h);
				//bil++;
				count++;
				
			}

		} finally {
			if (db != null)
				db.close();
		}
		
	}

	private void setMaklumatPerbicaraan(Map<String,Object> parameters) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			beanMaklumatPerbicaraan = new Vector<Hashtable<String,String>>();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();

			r.add("E.ID_PERMOHONAN");
			r.add("A.PEG_PENGENDALI");
			r.add("INITCAP(A.ALAMAT_BICARA1) AS ALAMAT_BICARA1");
			r.add("INITCAP(A.ALAMAT_BICARA2) AS ALAMAT_BICARA2");
			r.add("INITCAP(A.ALAMAT_BICARA3) AS ALAMAT_BICARA3");
			r.add("INITCAP(C.NAMA_NEGERI) AS NAMA_NEGERI");
			r.add("A.BANDAR");
			r.add("A.POSKOD");

			r.add("D.ID_FAIL", r.unquote("B.ID_FAIL"));
			r.add("B.ID_PERMOHONAN", r.unquote("E.ID_PERMOHONAN"));
			r.add("E.ID_KEPUTUSANPERMOHONAN", r.unquote("A.ID_KEPUTUSANPERMOHONAN"));
			r.add("C.ID_NEGERI", r.unquote("A.ID_NEGERIBICARA"));

			r.add("D.NO_FAIL", parameters.get("NoFail").toString());

			sql = r.getSQLSelect("TBLPPKPERBICARAAN A, TBLPPKPERMOHONAN B, TBLRUJNEGERI C,TBLPFDFAIL D, TBLPPKKEPUTUSANPERMOHONAN E");
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable<String,String> h;
			//int bil = 1;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable<String,String>();

				h.put("idPermohonan", rs.getString("ID_PERMOHONAN"));
				h.put("pegPengendali", rs.getString("PEG_PENGENDALI"));
				h.put("singleLineAlamat",
						rs.getString("ALAMAT_BICARA1") + "," + rs.getString("ALAMAT_BICARA2") + "," + rs.getString("ALAMAT_BICARA3"));
				h.put("newLineAlamat",
						rs.getString("ALAMAT_BICARA1") + "\n" + rs.getString("ALAMAT_BICARA2") + "\n" + rs.getString("ALAMAT_BICARA3"));
				h.put("namaNegeri", rs.getString("NAMA_NEGERI"));
				h.put("bandar", rs.getString("BANDAR"));
				h.put("poskod", rs.getString("POSKOD"));
				h.put("masaBicara", masaBicara(rs.getString("ID_PERMOHONAN")));
				beanMaklumatPerbicaraan.addElement(h);
				//bil++;
				count++;
			}

		} finally {
			if (db != null)
				db.close();
		}
		
	}

	private void setMaklumatHTA(Map<String,Object> parameters) throws Exception {
		Db db = null;
		String sql = "";

		try {
			db = new Db();
			beanMaklumatHTA = new Vector<Hashtable<String,String>>();
			sql = "SELECT"
					+ " J.ID_NEGERI AS idnegerijaagan , J.ID_DAERAH as iddaerahjagaan"
					+ " FROM TBLPFDFAIL A, TBLPPKPERMOHONAN B, TBLPPKPEMOHON P,TBLRUJBANDAR PPBANDAR, TBLRUJNEGERI PPNEGERI,TBLPPKPERMOHONANSIMATI C,"
					+ " TBLPPKSIMATI E, TBLRUJPEJABATURUSAN F, TBLRUJPEJABAT G, TBLRUJNEGERI H, TBLPPKHTA I, TBLRUJPEJABATURUSAN J" +

					" WHERE A.ID_FAIL=B.ID_FAIL" + " AND   B.ID_PERMOHONAN=C.ID_PERMOHONAN" + " AND   C.ID_SIMATI= E.ID_SIMATI"
					+ " AND I.ID_NEGERI  = F.ID_NEGERIURUS" + " AND I.ID_DAERAH = F.ID_DAERAHURUS"
					+ " AND F.ID_PEJABATJKPTG = G.ID_PEJABAT" + " AND G.ID_JENISPEJABAT = F.ID_JENISPEJABAT" + " AND F.ID_JENISPEJABAT = 3"
					+ " AND P.ID_PERMOHONAN= B.ID_PERMOHONAN" + " AND g.id_seksyen = 2" + " AND G.ID_DAERAH = F.ID_DAERAH"
					+ " AND PPBANDAR.ID_BANDAR = P.ID_BANDARSURAT" + " AND PPNEGERI.ID_NEGERI = P.ID_NEGERISURAT"
					+ " AND G.ID_NEGERI  =  H.ID_NEGERI" + " AND G.ID_PEJABAT = F.ID_PEJABATJKPTG"
					+ " AND C.ID_PERMOHONANSIMATI  = I.ID_PERMOHONANSIMATI" + " AND I.ID_NEGERI  = J.ID_NEGERIURUS"
					+ " AND I.ID_DAERAH = J.ID_DAERAHURUS" + " AND J.ID_JENISPEJABAT <> 3" + " AND E.ID_SIMATI = AAA.ID_SIMATI"
					+ " AND A.NO_FAIL = " + parameters.get("NoFail").toString() + " AND I.ID_HTA = " + parameters.get("idhta").toString();

			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);

			Hashtable<String,String> h;
			//int bil = 1;
			Integer count = 0;

			while (rs.next()) {
				h = new Hashtable<String,String>();
				h.put("idnegerijaagan", rs.getString("idnegerijaagan"));
				h.put("iddaerahjagaan", rs.getString("iddaerahjagaan"));
				beanMaklumatHTA.addElement(h);
				//bil++;
				count++;
			}

		} finally {
			if (db != null)
				db.close();
		}
		
	}

	private String noKpPemohon(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {

			db = new Db();
			sql = "SELECT"
					+ " CASE"
					+ " WHEN length(BBB.NO_KP2)<12 THEN  ''||rtrim(BBB.NO_KP2)||''"
					+ " WHEN length(rtrim(BBB.NO_KP2))=12 then substr(BBB.NO_KP2,1,6) || '-' || substr(BBB.NO_KP2,7,2) || '-' || substr(BBB.NO_KP2,9,4)"
					+ " ELSE substr(BBB.NO_KP2,1,6) || '-' || substr(BBB.NO_KP2,7,2) || '-' || substr(BBB.NO_KP2,9,4)  ||'  ('||TRIM(substr(BBB.NO_KP2,13,length(BBB.NO_KP2)))||')'"
					+ " END  AS no_kp_pemohon" + " FROM" + " (SELECT" + " CASE"
					+ " WHEN TBLPPKPEMOHON.NO_KP_BARU IS NULL AND TBLPPKPEMOHON.NO_KP_LAMA IS NOT NULL THEN  TBLPPKPEMOHON.NO_KP_LAMA"
					+ " WHEN TBLPPKPEMOHON.NO_KP_BARU IS NULL AND TBLPPKPEMOHON.NO_KP_LAMA IS NULL THEN  TBLPPKPEMOHON.NO_KP_LAIN"
					+ " WHEN TBLPPKPEMOHON.NO_KP_BARU IS NULL AND TBLPPKPEMOHON.NO_KP_LAIN IS NULL THEN  TBLPPKPEMOHON.NO_KP_LAMA"
					+ " ELSE TBLPPKPEMOHON.NO_KP_BARU" + " END || '' ||" + " CASE"
					+ " WHEN TBLPPKPEMOHON.NO_KP_BARU IS NOT NULL AND TBLPPKPEMOHON.NO_KP_LAMA IS NOT NULL THEN  TBLPPKPEMOHON.NO_KP_LAMA"
					+ " END || '' ||" + " CASE"
					+ " WHEN TBLPPKPEMOHON.NO_KP_BARU IS  NULL AND TBLPPKPEMOHON.NO_KP_LAMA IS NOT NULL THEN  TBLPPKPEMOHON.NO_KP_LAIN"
					+ " END AS NO_KP2 , ID_PEMOHON, ID_PERMOHONAN" + " FROM TBLPPKPEMOHON ) BBB,TBLPPKPEMOHON A"
					+ " WHERE A.ID_PEMOHON = BBB.ID_PEMOHON" + " AND BBB.ID_PERMOHONAN = " + idPermohonan;

			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("no_kp_pemohon").toString().toUpperCase();
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}

	}

	private String noKpSimati(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {

			db = new Db();
			sql = "SELECT"
					+ " CASE"
					+ " WHEN length(AAA.NO_KP1)<12 THEN  ''||rtrim(AAA.NO_KP1)||''"
					+ " WHEN length(rtrim(AAA.NO_KP1))=12 then substr(AAA.NO_KP1,1,6) || '-' || substr(AAA.NO_KP1,7,2) || '-' || substr(AAA.NO_KP1,9,4)"
					+ " ELSE substr(AAA.NO_KP1,1,6) || '-' || substr(AAA.NO_KP1,7,2) || '-' || substr(AAA.NO_KP1,9,4)  ||'  ('||TRIM(substr(AAA.NO_KP1,13,length(AAA.NO_KP1)))||')'"
					+ " END  AS no_kp_simati" + " FROM" + " (SELECT" + " CASE"
					+ " WHEN TBLPPKSIMATI.NO_KP_BARU IS NULL AND TBLPPKSIMATI.NO_KP_LAMA IS NOT NULL THEN  TBLPPKSIMATI.NO_KP_LAMA"
					+ " WHEN TBLPPKSIMATI.NO_KP_BARU IS NULL AND TBLPPKSIMATI.NO_KP_LAMA IS NULL THEN  TBLPPKSIMATI.NO_KP_LAIN"
					+ " WHEN TBLPPKSIMATI.NO_KP_BARU IS NULL AND TBLPPKSIMATI.NO_KP_LAIN IS NULL THEN  TBLPPKSIMATI.NO_KP_LAMA"
					+ " ELSE TBLPPKSIMATI.NO_KP_BARU" + " END || '' ||" + " CASE"
					+ " WHEN TBLPPKSIMATI.NO_KP_BARU IS NOT NULL AND TBLPPKSIMATI.NO_KP_LAMA IS NOT NULL THEN  TBLPPKSIMATI.NO_KP_LAMA"
					+ " END || '' ||" + " CASE"
					+ " WHEN TBLPPKSIMATI.NO_KP_BARU IS  NULL AND TBLPPKSIMATI.NO_KP_LAMA IS NOT NULL THEN  TBLPPKSIMATI.NO_KP_LAIN"
					+ " END AS NO_KP1 , ID_SIMATI" + " FROM TBLPPKSIMATI ) AAA, TBLPPKPERMOHONAN CCC, TBLPPKPERMOHONANSIMATI DDD"
					+ " WHERE CCC.ID_PERMOHONAN = " + idPermohonan + " AND CCC.ID_PERMOHONAN = DDD.ID_PERMOHONAN"
					+ " AND AAA.ID_SIMATI = DDD.ID_SIMATI";

			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("no_kp_simati").toString().toUpperCase();
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}

	}

	private String masaBicara(String idPermohonan) throws Exception {
		Db db = null;
		String sql = "";

		try {

			db = new Db();

			sql = "SELECT" + " DECODE(substr(A.MASA_BICARA,1,2),1,1,2,2,3,3,4,4,5,5,6,6,7,7,8,8,9,9,10,10,11,11,12,12,"
					+ " 13,1,14,2,15,3,16,4,17,5,18,6,19,7,20,8,21,9,22,10,23,11,24,12)" + " || ':'" + " || substr(A.masa_bicara,3,4)"
					+ " || ' '" + " || case when A.MASA_BICARA < 1200 then 'Pagi'"
					+ " when A.MASA_BICARA between 1200 and 1259 then 'Tengahari'"
					+ " when A.MASA_BICARA between 1300 and 1859 then 'Petang'" + " else 'Malam'" + " end  as MASA_BICARA"
					+ " FROM TBLPPKPERBICARAAN A, TBLPPKKEPUTUSANPERMOHONAN B, TBLPPKPERMOHONAN C"
					+ " WHERE A.ID_KEPUTUSANPERMOHONAN = B.ID_KEPUTUSANPERMOHONAN" + " AND B.ID_PERMOHONAN = C.ID_PERMOHONAN"
					+ " AND B.ID_PERMOHONAN = " + idPermohonan;

			Statement stmt = db.getStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getString("MASA_BICARA").toString().toUpperCase();
			} else {
				return "";
			}

		} finally {
			if (db != null)
				db.close();
		}

	}

	private Vector<Hashtable<String,String>> getBeanMaklumatPegawai() {
		return beanMaklumatPegawai;
	}

	public Vector<Hashtable<String,String>> getBeanMaklumatPermohonan() {
		return beanMaklumatPermohonan;
	}

	public Vector<Hashtable<String,String>> getBeanMaklumatPemohon() {
		return beanMaklumatPemohon;
	}

	public Vector<Hashtable<String,String>> getBeanMaklumatSimati() {
		return beanMaklumatSimati;
	}

	public Vector<Hashtable<String,String>> getBeanMaklumatPerbicaraan() {
		return beanMaklumatPerbicaraan;
	}

	public Vector<Hashtable<String,String>> getBeanMaklumatHTA() {
		return beanMaklumatHTA;
	}
	// Versioning Control
	public void doVersioning(String borang, String idfail, String nofail, String doSaveVersion, String flag_batal) {
		this.setVersion = true;
		this.borang = borang;
		this.idfail = idfail;
		this.nofail = nofail;
		this.flag_batal = flag_batal;
		this.doSaveVersion = doSaveVersion;
	
	}
	
	public void doHantarPNB(String borang, String idfail, String nofail, String idperbicaraan, String doSaveVersion) {
		this.setVersion = true;
		this.borang = borang;
		this.idfail = idfail;
		this.nofail = nofail;
		this.idperbicaraan = idperbicaraan;
		this.doSaveVersion = doSaveVersion;
	
	}
	// Versioning Control
	
	public void doVersioning(String borang, String idfail, String nofail, String doSaveVersion) {
		this.setVersion = true;
		this.borang = borang;
		this.idfail = idfail;
		this.nofail = nofail;
		this.doSaveVersion = doSaveVersion;
		
	}
	
	public void doVersioningPPK(String borang, String idfail, String nofail, String doSaveVersion,String flag_kemaskini) {
		this.setVersion = true;
		this.borang = borang;
		this.idfail = idfail;
		this.nofail = nofail;
		this.flag_keb_kemaskini = flag_kemaskini;
		this.doSaveVersion = doSaveVersion;
		
	}

	public void askForVersion(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {		
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("_ekptg_user_id");
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Versioning Control</title>");		
		out.println("</head>");
		out.println("<body bgcolor=\"white\">");
		out.println("<center>");
		out.println("No. Fail:" + this.nofail + "<br><br>");
		out.println("<hr>");
		String qS = request.getQueryString();
		out.println("<a href=" + new_url + "?idfail=" + this.idfail + "&user_id="+user_id+"&flagVersion=yes&flag_batal=Y&" + qS + ">Simpan History</a> |");
		out.println("<a href=" + new_url + "?idfail=" + this.idfail + "&user_id="+user_id+"&flagVersion=no&" + qS + ">Cetak</a><br>");
		
		
		if (hasVersionHistory(this.idfail, this.borang)) {
			doListing(request, response, this.idfail, this.borang);
		}

		// out.println("<input onClick=\" "+new_url+"?idfail="+this.idfail+"&flagVersion=yes \" type=button value=Simpan History>");
		// out.println("<input type=button value=Cetak></br>");
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
	
	}
	
	public void askForVersionPindaan(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Versioning Control</title>");		
		out.println("</head>");
		out.println("<body bgcolor=\"white\">");
		out.println("<center>");
		out.println("No Fail:" + this.nofail + "<br><br>");
		out.println("<hr>");
		String qS = request.getQueryString();
		/*out.println("<a href=" + new_url + "?idfail=" + this.idfail + "&flagVersion=yes&flag_batal=Y&" + qS + ">Simpan History</a> |");*/
				
		System.out.println("this.flag_keb_kemaskini==="+this.flag_keb_kemaskini);		
		if(this.flag_keb_kemaskini!=null)	{
						
			if((this.flag_keb_kemaskini).equals("yes")){
				System.out.println("::::::::8::::::::::");
				out.println("<a href=" + new_url + "?idfail=" + this.idfail + "&flagVersion=pindaanP&" + qS + ">Cetak</a><br>");
			}else{
			
				out.println("<a href=" + new_url + "?idfail=" + this.idfail + "&flagVersion=pindaanN&" + qS + ">Cetak</a><br>");
			}
		}else{

			out.println("<a href=" + new_url + "?idfail=" + this.idfail + "&flagVersion=BorangE&" + qS + ">Cetak</a><br>");
		}
		
			
			//out.println("<a href=" + new_url + "?idfail=" + this.idfail + "&flagVersion=yes&" + qS + ">Cetak</a><br>");
			out.println("<font color=red>*Nota:Setiap kali cetakan, id simpanan history atau no pindaan akan dijana.</font><br><br>");
			
			if (hasVersionHistory(this.idfail, this.borang)) {
				doListingPindaan(request, response, this.idfail, this.borang);
			}
	
	}
	
	
	public void libPNB(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
	response.setContentType("text/html");
	PrintWriter out = response.getWriter();
	out.println("<script type=\"text/javascript\" src=\"../library/js/SpryTabbedPanels.js\"></script>");
	out.println("<script type=\"text/javascript\" src=\"../library/js/ekptgTools.js\"></script>");
	out.println("<script type=\"text/javascript\" src=\"../img\"></script>");
	out.println("<script type=\"text/javascript\" src=\"../library/js/jquery-1.3.2.min.js\" ></script>");
	out.println("<script>var $jquery = jQuery.noConflict();</script>");
	out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"../css/SpryTabbedPanels.css\">");
	out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"../css/eTapp_PPK.css\">");
	}
	
	public void askForPNB(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		
		
		
		libPNB(request, response);
		
		out.println("<title>Hantar ke PNB</title>");		
		out.println("</head>");
		out.println("<body bgcolor=\"white\">");
		out.println("<center>");
		out.println("<span>No Fail:" + this.nofail + "</span><br>");
		out.println("<hr>");
		
		//aishahlatip
		if (hasRekodPNB(this.idfail, this.idperbicaraan)==true) {
		out.println("<p><span>Tandatangan Berjaya dikenalpasti</span></p><br>");
		}
		
		String qS = request.getQueryString();
		
		myLogger.info(" IDP ----------------------- "+this.idperbicaraan);
		String idSimati = getIdSimati(this.nofail);
		myLogger.info(" idSimati ----------------------- "+idSimati);
		if(!this.idperbicaraan.equals(""))
		{
			List listOBPerbicaraan = listOBPerbicaraan(this.idfail, this.idperbicaraan,this.borang,idSimati);
			System.out.println("listOBPerbicaraan.size()==="+listOBPerbicaraan.size());
			if(listOBPerbicaraan.size()>0)
			{
				//ada rekod penerima
				out.println("<a href=" + new_url + "?idfail=" + this.idfail + "&idperbicaraan="+ this.idperbicaraan+"&flagVersion=hantarPNB><font color='blue'><u>Hantar PNB</u></font></a> |");
			}
			else
			{
				//tiada rekod penerima
				out.println("<span><font color='red' class='blink'>Tiada Rekod Penerima</font></span> |");
	
			}
		}
		else
		{
			out.println("<span><font color='red' class='blink'>Tiada Rekod Perbicaraan</font></span> |");
		}
		out.println("<a href=" + new_url + "?idfail=" + this.idfail + "&flagVersion=no&idperbicaraan="+ this.idperbicaraan+"><font color='blue'><u>Cetak</u></font></a><br>");
		if (hasRekodPNB(this.idfail, this.idperbicaraan)) {
			doListingPNB(request, response, this.idfail, this.idperbicaraan);
		}
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");	
	}

	public boolean hasVersionHistory(String idfail, String borang) throws Exception {
		boolean output = false;
		String sql = "";
		Db db = null;
		try {
			db = new Db();
			sql = "Select count(*) as total from TBLPPKBORANG_HISTORY where id_fail ='" + idfail + "' and nama_borang='" + borang + "'";
			// myLogger.info(sql);
			ResultSet rs = db.getStatement().executeQuery(sql);
			if (rs.next()) {
				if (rs.getInt("total") > 0) {
					output = true;
				}
			}
		} catch (Exception e) {
			throw new Exception("error checking versioning :" + e.getMessage());
		} finally {
			if (db != null)
				db.close();
		}
		return output;
	
	}
	
	public boolean hasRekodPNB(String idfail, String idperbicaraan) throws Exception {
		boolean output = false;
		String sql = "";
		Db db = null;
		try {
			db = new Db();
			sql = "Select count(*) as total from TBLPPKBORANGPNB where id_fail ='" + idfail + "' and id_perbicaraan='" + idperbicaraan + "'";
			// myLogger.info(sql);
			ResultSet rs = db.getStatement().executeQuery(sql);
			if (rs.next()) {
				if (rs.getInt("total") > 0) {
					output = true;
				}
			}
		} catch (Exception e) {
			throw new Exception("error checking versioning :" + e.getMessage());
		} finally {
			if (db != null)
				db.close();
		}
		return output;
	
	}

	public void doListing(HttpServletRequest request, HttpServletResponse response, String idfail, String borang) throws IOException,
	Exception {
	PrintWriter out = response.getWriter();
	out.println("<hr>");
	out.println("Senarai </br>");

	String sql = "";
	String flag_kemaskini = "";
	Db db = null;
	try {
		db = new Db();
		sql = "Select id_borang,tarikh_masuk from TBLPPKBORANG_HISTORY where id_fail ='" + idfail + "' and nama_borang='" + borang
				+ "' order by tarikh_masuk desc";
		myLogger.info(sql);
		ResultSet rs = db.getStatement().executeQuery(sql);
		int x = 1;
		out.println("<table width='50%'   align='center'>");
		out.println("  <tr>");
		out.println(" <td width='2%' align='center' >Bil</td>");
		out.println(" <td width='1%' ></td>");
		out.println(" <td width='37%' align='left' >ID CETAKAN</a></td>");
		out.println(" <td width='60%' align='left'>Tarikh / Waktu Cetak</td>");
		out.println("  </tr>");

		while (rs.next()) {

			out.println("  <tr>");
			out.println(" <td width='2%' >" + x + "</td>");
			out.println(" <td width='1%' >:</td>");
			out.println(" <td width='30%' > <a href=" + new_url + "?idfail=" + idfail + "&ID_FAIL=" + idfail + "&flagVersion=view&flag_batal=Y&idborang="
					+ rs.getString("id_borang") + ">" + rs.getString("id_borang") + "</a></td>");
			out.println(" <td width='67%' >" + (rs.getString("tarikh_masuk") == null ? "" : rs.getString("tarikh_masuk")) + "</td>");
			out.println("  </tr>");

			// out.println(x+":"+rs.getString("id_borang")+"<br>");
			// out.println(x+" : <a href="+new_url+"?idfail="+idfail+"&flagVersion=view&idborang="+rs.getString("id_borang")+">"+rs.getString("id_borang")+"</a> <br>");
			x++;
		}
	} catch (Exception e) {
		throw new Exception("error getting listing:" + e.getMessage());
	} finally {
		if (db != null)
			db.close();
	}

}
	
	public void doListingPindaan(HttpServletRequest request, HttpServletResponse response, String idfail, String borang) 
		throws IOException,Exception {
		PrintWriter out = response.getWriter();
		out.println("<hr>");
		out.println("Senarai </br>");
	
		String sql = "";
		String flag_kemaskini = "";
		Db db = null;
		try {
			db = new Db();
			sql = "Select id_borang,tarikh_masuk,FLAG_KEB_KEMASKINI " +
				" from TBLPPKBORANG_HISTORY " +
				" where id_fail ='" + idfail + "' " +
				" and nama_borang='" + borang + "' " +
				" order by tarikh_masuk desc";
	//		myLogger.info(sql);
			ResultSet rs = db.getStatement().executeQuery(sql);
			int x = 1;
			out.println("<table width='70%'   align='center'>");
			out.println("  <tr>");
			out.println(" <td width='2%' align='center' >Bil</td>");
			out.println(" <td width='1%' ></td>");
			out.println(" <td width='37%' align='left' >ID CETAKAN</a></td>");
			out.println(" <td width='40%' align='left'>Tarikh / Waktu Cetak</td>");
			out.println(" <td width='20%' align='left'>Status</td>");
			out.println("  </tr>");
	
			while (rs.next()) {				
				if(rs.getString("FLAG_KEB_KEMASKINI")!= null){
					if(rs.getString("FLAG_KEB_KEMASKINI").equals("1")){
						flag_kemaskini = "Pindaan Fail";						
					}
				}else{
					flag_kemaskini = "-";
				}
			
				out.println("  <tr>");
				out.println(" <td width='2%' >" + x + "</td>");
				out.println(" <td width='1%' >:</td>");
				out.println(" <td width='37%' > <a href=" + new_url + "?idfail=" + idfail + "&flagVersion=view&flag_batal=Y&idborang="
						+ rs.getString("id_borang") + ">" + rs.getString("id_borang") + "</a></td>");
				out.println(" <td width='40%' >" + (rs.getString("tarikh_masuk") == null ? "" : rs.getString("tarikh_masuk")) + "</td>");
				out.println(" <td width='20%' >" + flag_kemaskini + "</td>");
				out.println("  </tr>");
	
				// out.println(x+":"+rs.getString("id_borang")+"<br>");
				// out.println(x+" : <a href="+new_url+"?idfail="+idfail+"&flagVersion=view&idborang="+rs.getString("id_borang")+">"+rs.getString("id_borang")+"</a> <br>");
				x++;
			}
			
		} catch (Exception e) {
			throw new Exception("error getting listing:" + e.getMessage());
		} finally {
			if (db != null)
				db.close();
		}
	
	}
	
	public void doListingPNB(HttpServletRequest request, HttpServletResponse response, String idfail, String idperbicaraan) throws IOException,
	Exception {
	PrintWriter out = response.getWriter();
	out.println("<fieldset>");
	out.println("<legend>Rekod Penghantaran Ke PNB</legend>");

	String sql = "";
	Db db = null;
	try {
		db = new Db();
		sql = " SELECT PNB.COUNT_PAGES,PNB.ID_BORANGPNB, PNB.ID_FAIL, PNB.ID_PERBICARAAN, PNB.ID_NEGERIUNIT, PNB.ID_UNIT, "+
				" PNB.NAMA_BORANG, PNB.FLAG_HANTARPNB, TO_CHAR(PNB.TARIKH_HANTARPNB,'DD/MM/YYYY') AS TARIKH_HANTARPNB, "+
				" PNB.FLAG_DOWNLOAD, TO_CHAR(PNB.TARIKH_DOWNLOAD,'DD/MM/YYYY') AS TARIKH_DOWNLOAD, PNB.NO_PNB, " +
				" COUNT(PNBOB.ID_BORANGPNBOB) AS JUMLAH_PENERIMA "+
				" FROM TBLPPKBORANGPNB PNB, TBLPPKBORANGPNBOB PNBOB WHERE PNB.ID_BORANGPNB IS NOT NULL  "+
				" AND PNB.ID_FAIL = '"+idfail+"' "+
				" AND PNB.ID_PERBICARAAN = '"+idperbicaraan+"' AND PNB.ID_BORANGPNB = PNBOB.ID_BORANGPNB(+) "+
				" GROUP BY PNB.COUNT_PAGES,PNB.ID_BORANGPNB, PNB.ID_FAIL, PNB.ID_PERBICARAAN, PNB.ID_NEGERIUNIT, "+
				" PNB.ID_UNIT, PNB.NAMA_BORANG, PNB.FLAG_HANTARPNB,PNB.TARIKH_HANTARPNB, "+
				" PNB.FLAG_DOWNLOAD,PNB.TARIKH_DOWNLOAD,PNB.NO_PNB "+
				" ORDER BY PNB.TARIKH_HANTARPNB DESC";
		
		
		
		
		myLogger.info(" doListingPNB : "+sql);
		ResultSet rs = db.getStatement().executeQuery(sql);
		int x = 1;
		out.println("<table width='100%' cellspacing='2' cellpadding='2'  align='center'>");
		out.println(" <tr class=\"table_header\" >");
		out.println(" <td  align='center' valign='top' width='3%' >Bil</td>");
		out.println(" <td  align='left' valign='top'>No. PNB</td>");
		out.println(" <td  align='left' valign='top'>Nama Borang</td>");
		out.println(" <td  align='center' valign='top'>Muka Surat</td>");
		out.println(" <td  align='center' valign='top'>Jumlah Penerima</td>");
		out.println(" <td  align='left' valign='top'>Status Hantar ke PNB</td>");
		out.println(" <td  align='left' valign='top'>Status Terima PNB</td>");
		out.println(" </tr>");

		while (rs.next()) {
			
			String rowCss = "";
			if ( (x % 2) == 0 )
			{
				rowCss = "row2";
			}
	        else
	        {
	        	rowCss = "row1";
	        }
			
			out.println(" <tr>");
			out.println(" <td class=\""+rowCss+"\" align=\"center\" valign=\"top\" >" + x + "</td>");
			out.println(" <td class=\""+rowCss+"\" align=\"left\" valign=\"top\">" + (rs.getString("NO_PNB") == null ? "" : rs.getString("NO_PNB")) + "</td>");
			
			out.println(" <td class=\""+rowCss+"\" align=\"left\" valign=\"top\" > <a href=" + new_url + "?idfail=" + idfail + "&flagVersion=viewPNB&idborangpnb="
					+ rs.getString("ID_BORANGPNB") + "><font color='blue'><u> " + (rs.getString("NAMA_BORANG") == null ? "" : rs.getString("NAMA_BORANG"))  + "</u></font></a></td>");
			out.println(" <td class=\""+rowCss+"\" align=\"center\" valign=\"top\">" + (rs.getString("COUNT_PAGES") == null ? 0 : rs.getInt("COUNT_PAGES")) + "</td>");
			
			out.println(" <td class=\""+rowCss+"\" align=\"center\" valign=\"top\">" + (rs.getString("JUMLAH_PENERIMA") == null ? 0 : rs.getInt("JUMLAH_PENERIMA")) + "</td>");
			out.println(" <td class=\""+rowCss+"\"  align=\"left\" valign=\"top\" >");
			if((rs.getString("FLAG_HANTARPNB") == null ? "" : rs.getString("FLAG_HANTARPNB")).equals("Y"))
			{
				out.println(" DIHANTAR PADA " + (rs.getString("TARIKH_HANTARPNB") == null ? "" : rs.getString("TARIKH_HANTARPNB")));
			}
			out.println(" </td>");
			out.println(" <td class=\""+rowCss+"\" align=\"left\" valign=\"top\" >");
			if((rs.getString("FLAG_DOWNLOAD") == null ? "" : rs.getString("FLAG_DOWNLOAD")).equals("Y"))
			{
				out.println(" DITERIMA PADA " + (rs.getString("TARIKH_DOWNLOAD") == null ? "" : rs.getString("TARIKH_DOWNLOAD")));
			}
			out.println(" </td>");
			
			out.println("  </tr>");
			
			List listPNBOB = listPNBOB(rs.getString("ID_BORANGPNB"));
			if(listPNBOB.size()>0)
			{
						
					out.println("<tr>");
					out.println("<td colspan='6'  align=\"left\" valign=\"top\" >" +
							"<table width='95%' align='center'><tr><td width='100%'>" +
							"<fieldset ><legend >Senarai Penerima</legend><table width='100%' cellspacing='2' cellpadding='2'  align='left'>");
					out.println(" <tr class=\"table_header\" >");
					out.println(" <td  align='center' valign='top' >Bil</td>");
					out.println(" <td  align='left' valign='top'>Nama</td>");
					out.println(" <td  align='left' valign='top'>Taraf</td>");
					out.println(" <td  align='left' valign='top'>Status & Tarikh Hantar & No Daftar Surat</td>");
					out.println(" <td  align='left' valign='top'>Status, Tarikh & Catatan Pemulangan</td>");
					out.println(" </tr>");
					int bil = 1;
					for(int i = 0; i < listPNBOB.size();i++)
					{
						
						String rowCssOB = "";
						if ( (bil % 2) == 0 )
						{
							rowCssOB = "row2";
						}
				        else
				        {
				        	rowCssOB = "row1";
				        }
						
						Map m = (Map) listPNBOB.get(i);					
						out.println("<tr>");
						out.println(" <td class=\""+rowCssOB+"\" align=\"center\" valign=\"top\" >" + bil + "</td>");
						out.println(" <td class=\""+rowCssOB+"\" align=\"left\" valign=\"top\">" + ((String) m.get("NAMA") == null ? "" : (String) m.get("NAMA"))  + "</td>");
						out.println(" <td class=\""+rowCssOB+"\" align=\"left\" valign=\"top\">" +((String) m.get("TARAF") == null ? "" : (String) m.get("TARAF")) + "</td>");
						
						/*
						out.println(" <td class=\""+rowCssOB+"\"  align=\"left\" valign=\"top\" >");
						//ALAMAT
						out.println(" </td>");
						*/
						out.println(" <td class=\""+rowCssOB+"\"  align=\"left\" valign=\"top\" >");
						if(((String) m.get("FLAG_HANTAROB") == null ? "" : (String) m.get("FLAG_HANTAROB")).equals("Y"))
						{
							out.println(" DIHANTAR PADA " + ((String) m.get("TARIKH_HANTAROB") == null ? "" : (String) m.get("TARIKH_HANTAROB")));
							out.println("<br>NO SURAT DAFTAR : " + ((String) m.get("NOSURAT_HANTAROB") == null ? "" : (String) m.get("NOSURAT_HANTAROB")));	
						}
						out.println(" </td>");
						out.println(" <td class=\""+rowCssOB+"\"  align=\"left\" valign=\"top\" >");
						if(((String) m.get("FLAG_RETURN") == null ? "" : (String) m.get("FLAG_RETURN")).equals("Y"))
						{
							out.println(" DIPULANG BALIK PADA " + ((String) m.get("TARIKH_RETURN") == null ? "" : (String) m.get("TARIKH_RETURN")));
							if(!((String) m.get("CATATAN_RETURN") == null ? "" : (String) m.get("CATATAN_RETURN")).equals(""))
							{
								out.println("<br>CATATAN : " + ((String) m.get("CATATAN_RETURN") == null ? "" : (String) m.get("CATATAN_RETURN")));	
							}
						}
						out.println(" </td>");
						out.println("</tr>");
						bil++;
					}
					out.println("</table></fieldset></td></tr></table></td>");
					out.println(" </tr>");
				
			}
			
			
			x++;
		}
		out.println("  </table>");
		
	} catch (Exception e) {
		throw new Exception("error getting listing pnb:" + e.getMessage());
	} finally {
		if (db != null)
			db.close();
	}
	
	out.println("</fieldset>");

}

	public synchronized void doSaveVersioning(HttpServletRequest request, HttpServletResponse response, ServletContext context,
		String queryString) throws IOException {
		// Save to BLOB
		// 1.get borang to generate
		myLogger.debug("foldername:" + this.folderName);
		myLogger.debug("filename:" + this.reportFileName);
		myLogger.debug("zz:" + queryString);
		myLogger.debug("xx:" + request.getQueryString());

		parameters = this.parameters;
		Connection conn = null;
		byte[] bytes = null;
		try {
			conn = new Db().getConnection();
			JasperReport jasperReport = getCompiledReport(reportFileName, folderName, context);
			bytes = JasperRunManager.runReportToPdf(jasperReport, parameters, conn);

			if ((bytes != null) && (bytes.length > 0)) {
				savePDF(bytes, this.idfail, this.borang, this.idmasuk);
				
				response.setContentType("text/html");
				String qS = request.getQueryString();
				PrintWriter out = response.getWriter();
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Versioning Control</title>");
				out.println("</head>");
				out.println("<body bgcolor=\"white\">");
				out.println("<center>");
				out.println("No Fail : " + this.nofail + " version saved<br><br>");
				out.println("<a href=" + new_url + "?idfail=" + this.idfail + "&flagVersion=no&" + qS + ">Cetak</a><br>");
				if (hasVersionHistory(this.idfail, this.borang)) {
					if ( !window.confirm("Adakah Anda Pasti ?") ){
						//document.${formName}.actionPenyewaan.value = "daftarBaru";
						return;
					}
					doListing(request, response, this.idfail, this.borang);
				}
				out.println("</center>");
				out.println("</body>");
				out.println("</html>");

			} else {

			}

		} catch (Exception e) {

		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException xx) { }
		}

	}
	
	public synchronized void doNoPindaanSaveVersioning(HttpServletRequest request, HttpServletResponse response, ServletContext context,
			String queryString) throws IOException {
			// Save to BLOB
			// 1.get borang to generate
			myLogger.debug("foldername xde pindaan:" + this.folderName);
			myLogger.debug("filename xde pindaan:" + this.reportFileName);
			myLogger.debug("zz:" + queryString);
			myLogger.debug("xx:" + request.getQueryString());

			parameters = this.parameters;
			Connection conn = null;
			byte[] bytes = null;
			try {
				conn = new Db().getConnection();
				JasperReport jasperReport = getCompiledReport(reportFileName, folderName, context);
				bytes = JasperRunManager.runReportToPdf(jasperReport, parameters, conn);

				if ((bytes != null) && (bytes.length > 0)) {
					savePDF(bytes, this.idfail, this.borang, this.idmasuk);
					
					response.setContentType("text/html");
					String qS = request.getQueryString();
					PrintWriter out = response.getWriter();
					out.println("<html>");
					out.println("<head>");
					out.println("<title>Versioning Control</title>");
					out.println("</head>");
					out.println("<body bgcolor=\"white\">");
					out.println("<center>");
					out.println("No Fail : " + this.nofail + " version saved<br><br>");
					out.println("<a href=" + new_url + "?idfail=" + this.idfail + "&flagVersion=no&" + qS + ">Cetak</a><br>");
					if (hasVersionHistory(this.idfail, this.borang)) {
						out.println("kat sini yati");
						doListingPindaan(request, response, this.idfail, this.borang);
					}
					out.println("</center>");
					out.println("</body>");
					out.println("</html>");

				} else {

				}

			} catch (Exception e) {

			} finally {
				try {
					if (conn != null)
						conn.close();
				} catch (SQLException xx) { }
			}

		}
	
	
	
	
	//open razman add new feature : attachment in bytes
	//kena convert dlu fail jesper kedalam byte
	public byte[] attachmentInbytes(String folderName, String reportFileName, ServletContext context, Map parameters) throws IOException {
		
			Connection conn = null;
			byte[] bytes = null;
			try {
				conn = new Db().getConnection();
				JasperReport jasperReport = getCompiledReport(reportFileName, folderName, context);
				bytes = JasperRunManager.runReportToPdf(jasperReport, parameters, conn);
			} catch (Exception e) {

			} finally {
				try {
					if (conn != null)
						conn.close();
				} catch (SQLException xx) { }
			}
			return bytes;
		}
	//close razman add new feature : attachment in bytes
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public synchronized void doPindaanSaveVersioning(HttpServletRequest request, HttpServletResponse response, ServletContext context,
			String queryString) throws IOException {
			// Save to BLOB
			// 1.get borang to generate
			myLogger.debug("foldername:" + this.folderName);
			myLogger.debug("filename:" + this.reportFileName);
			myLogger.debug("zz:" + queryString);
			myLogger.debug("xx:" + request.getQueryString());
			System.out.println("::::::::10::::::::::");
			parameters = this.parameters;
			Connection conn = null;
			byte[] bytes = null;
			try {
				conn = new Db().getConnection();
				//System.out.println("::::::::11::::::::::"+parameters);
				
				
				long idBorang = DB.getNextID("TBLPPKBORANG_HISTORY_SEQ");
				insertTBLPPKBORANG_HISTORY(idBorang,this.idfail, this.borang, this.idmasuk,request);

				
				JasperReport jasperReport = getCompiledReport(reportFileName, folderName, context);
				System.out.println("#################jasperReport##############");
				bytes = JasperRunManager.runReportToPdf(jasperReport, parameters, conn);
				System.out.println("#################bytes##############"+bytes);

				if ((bytes != null) && (bytes.length > 0)) {
					System.out.println("::::::::12::::::::::");
					savePDFpindaan(bytes, idBorang,request);
					response.setContentType("text/html");
					String qS = request.getQueryString();
					PrintWriter out = response.getWriter();
					out.println("<html>");
					out.println("<head>");
					out.println("<title>Versioning Control</title>");
					out.println("</head>");
					out.println("<body bgcolor=\"white\">");
					out.println("<center>");
					out.println("No Fail : " + this.nofail + " version saved<br><br>");
					out.println("<a href=" + new_url + "?idfail=" + this.idfail + "&flagVersion=pindaanP&" + qS + ">Cetak</a><br>");
					out.println("<font color=red>*Nota:Setiap kali cetakan, id simpanan history atau no pindaan akan dijana.</font><br><br>");
					/*out.println("<a href=" + new_url + "?idfail=" + this.idfail + "&flagVersion=no&" + qS + ">Cetak</a><br>");*/
					if (hasVersionHistory(this.idfail, this.borang)) {
						doListingPindaan(request, response, this.idfail, this.borang);
					}
					out.println("</center>");
					out.println("</body>");
					out.println("</html>");

				} else {

				}

			} catch (Exception e) {

			} finally {
				try {
					if (conn != null)
						conn.close();
				} catch (SQLException xx) { }
			}

		}
	
	
	public synchronized void hantarKePNB(HttpServletRequest request, HttpServletResponse response, ServletContext context,
			String queryString) throws Exception {
			// Save to BLOB
			// 1.get borang to generate
			myLogger.debug("foldername:" + this.folderName);
			myLogger.debug("filename:" + this.reportFileName);
			myLogger.debug("zz:" + queryString);
			myLogger.debug("xx:" + request.getQueryString());
			
			String id_pnb_prev = getPrevIdPNB(this.idfail,this.idperbicaraan);
			if(!id_pnb_prev.equals(""))
			{
				deletePNB(id_pnb_prev);
			}

			parameters = this.parameters;
			Connection conn = null;
			byte[] bytes = null;
			try {
				conn = new Db().getConnection();
				JasperReport jasperReport = getCompiledReport(reportFileName, folderName, context);
				bytes = JasperRunManager.runReportToPdf(jasperReport, parameters, conn);
				

				if ((bytes != null) && (bytes.length > 0)) {
					String namaBorang = savePDFPNB(bytes, this.idfail, this.idperbicaraan,this.idmasuk,reportFileName,request,this.borang );
					response.setContentType("text/html");
					String qS = request.getQueryString();
					PrintWriter out = response.getWriter();
					out.println("<html>");
					out.println("<head>");
					libPNB(request, response);
					out.println("<title>Hantar Ke PNB</title>");
					out.println("</head>");
					out.println("<body bgcolor=\"white\">");
					out.println("<center>");
					out.println("<span class='blink'> " + namaBorang + " berjaya dihantar! </span><br>");
					out.println("<hr>");
					//out.println("<a href=" + new_url + "?idfail=" + this.idfail + "&flagVersion=no&" + qS + ">Cetak</a><br>");
					out.println("<a href=" + new_url + "?idfail=" + this.idfail + "&idperbicaraan="+ this.idperbicaraan+"&flagVersion=hantarPNB><font color='blue'><u>Hantar PNB</u></font></a> |");
					out.println("<a href=" + new_url + "?idfail=" + this.idfail + "&flagVersion=no&idperbicaraan="+ this.idperbicaraan+"><font color='blue'><u>Cetak</u></font></a><br>");
					
					if (hasRekodPNB(this.idfail, this.idperbicaraan)) {
						doListingPNB(request, response, this.idfail, this.idperbicaraan);
					}
					out.println("</center>");
					out.println("</body>");
					out.println("</html>");

				} else {

				}

			} catch (Exception e) {

			} finally {
				try {
					if (conn != null)
						conn.close();
				} catch (SQLException xx) { }
			}

		}

	public synchronized void savePDF(byte[] pdf, String idfail, String borang, String idmasuk) {
		Db db = null;
		try {
			db = new Db();
			long idBorang = DB.getNextID("TBLPPKBORANG_HISTORY_SEQ");
			Connection con = db.getConnection();
			con.setAutoCommit(false);
			PreparedStatement ps = con.prepareStatement("insert into TBLPPKBORANG_HISTORY "
					+ "(id_borang,id_fail,nama_borang,content,tarikh_kemaskini,tarikh_masuk,id_masuk,id_kemaskini) "
					+ "values(?,?,?,?,sysdate,sysdate,?,?)");

			ps.setLong(1, idBorang);
			ps.setString(2, idfail);
			ps.setString(3, borang);
			ps.setBytes(4, pdf);
			ps.setString(5, idmasuk);
			ps.setString(6, idmasuk);

			ps.executeUpdate();
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}

	}// end very code post
	
	public synchronized String savePDFPNB(byte[] pdf, String idfail, String idperbicaraan,String userid,String reportFileName, HttpServletRequest request, String borang) {
		Db db = null;
		String namaBorang = "";
		try {
			
			
			Hashtable infoPerbicaraan = infoPerbicaraan(idfail, idperbicaraan);
			String NO_FAIL = (String)infoPerbicaraan.get("NO_FAIL");
			String TARIKH_BICARA = (String)infoPerbicaraan.get("TARIKH_BICARA");
			String BIL_BICARA = (String)infoPerbicaraan.get("BIL_BICARA");
			String ID_PEJABATJKPTG = (String)infoPerbicaraan.get("ID_PEJABATJKPTG");
			String ID_NEGERIMHN = (String)infoPerbicaraan.get("ID_NEGERIMHN");
			String NO_PNB_EXT = "PNB/"+NO_FAIL + "/" +TARIKH_BICARA+"/"+BIL_BICARA;
			Date now = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
			String tahun = formatter.format(now);
			String kod_negeri = getKodNegeri(ID_NEGERIMHN);
			String NO_PNB = "PNB/"+tahun + "/"+ kod_negeri + "/PPK/"
					+ String.format("%06d", getSeqNoPNB(tahun,ID_NEGERIMHN,"PPK"));
			namaBorang = reportFileName + "_" + NO_PNB_EXT;
			
			db = new Db();
			long idBorangpnb = DB.getNextID("TBLPPKBORANGPNB_SEQ");
			Connection con = db.getConnection();
			con.setAutoCommit(false);
			PreparedStatement ps = con.prepareStatement("insert into TBLPPKBORANGPNB "
					+ "(ID_BORANGPNB,ID_FAIL,ID_PERBICARAAN,ID_NEGERIUNIT,ID_UNIT," +
					" NO_PNB,NAMA_BORANG,CONTENT,FLAG_HANTARPNB,ID_HANTARPNB," +
					"TARIKH_HANTARPNB,JENIS_BORANG" +
					//",FLAG_DOWNLOAD,ID_DOWNLOAD,TARIKH_DOWNLOAD" +
					") "
					+ "values(?,?,?,?,?," +
					"?,?,?,?,?," +
					"sysdate,?)");
			
			ps.setLong(1, idBorangpnb);
			ps.setString(2, idfail);
			ps.setString(3, idperbicaraan);
			ps.setString(4, ID_NEGERIMHN);
			ps.setString(5, ID_PEJABATJKPTG);
			
			ps.setString(6, NO_PNB);
			ps.setString(7, namaBorang);
			ps.setBytes(8, pdf);
			ps.setString(9, "Y");
			ps.setString(10, userid);
			ps.setString(11, reportFileName);			

			ps.executeUpdate();
			con.commit();
			HttpSession session = request.getSession();
			//AuditTrail.logActivity("INS","TBLPPKBORANGPNB [ID_BORANGPNB : "+idBorangpnb+"] Inserted");
			AuditTrail.logActivity(null,session,"INS","TBLPPKBORANGPNB [ID_BORANGPNB : "+idBorangpnb+"] Inserted");
			
			PdfReader reader = new PdfReader(pdf);
			System.out.println(" pageCount hantar PNB : "+reader.getNumberOfPages());
			updateCOUNT_PAGES(idBorangpnb+"",reader.getNumberOfPages(),db); 
			
			String idSimati = getIdSimati(this.nofail);
			List listOBPerbicaraan = listOBPerbicaraan(idfail, idperbicaraan, borang,idSimati);
			for(int i = 0; i < listOBPerbicaraan.size();i++)
			{
				Map m = (Map) listOBPerbicaraan.get(i);
				
				String ID_PK = (String) m.get("ID_PK");
				String TABLENAME = (String) m.get("TABLENAME");
				String NAMA = (String) m.get("NAMA");
				String TARAF = (String) m.get("TARAF");
				String ALAMAT_1 = (String) m.get("ALAMAT_1");
				String ALAMAT_2 = (String) m.get("ALAMAT_2");
				String ALAMAT_3 = (String) m.get("ALAMAT_3");
				String POSKOD = (String) m.get("POSKOD");
				String BANDAR = (String) m.get("BANDAR");
				String NEGERI = (String) m.get("NEGERI");	
				
				insertPenerimaPNB("",idBorangpnb+"",ID_PK, TABLENAME, NAMA, TARAF, 
						ALAMAT_1, ALAMAT_2, ALAMAT_3, POSKOD, BANDAR, NEGERI,request);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}
		return namaBorang;

	}// end very code post
	
	private void savePDFpindaan(byte[] pdf, long idBorang ,HttpServletRequest request) throws Exception {
		Connection conn = null;
		Db db = null;
		Date now = new Date();
		String sql = "";
		String sql1 = "";
	
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			Statement stmt = db.getStatement();
			
			
			Connection con = db.getConnection();
			con.setAutoCommit(false);
			
			
			
			PreparedStatement pstmt = null;			
			String sqlQuery = 	"UPDATE TBLPPKBORANG_HISTORY SET "+
								"CONTENT = ? "+
								"WHERE ID_BORANG = ? ";
					
			pstmt = conn.prepareStatement(sqlQuery);
			pstmt.setBytes(1, pdf);
			pstmt.setLong(2, idBorang);
			
			
			
			pstmt.executeUpdate(); 	
			

			con.commit();
			HttpSession session = request.getSession();

			AuditTrail.logActivity(null,session,"UPD","TBLPPKPINDAAN [ID_BORANGPINDAAN : "+idBorang+"] Updated");
			

			conn.commit();
		} catch (SQLException se) {
			try {
				conn.rollback();
			} catch (SQLException se2) {
				throw new Exception("Rollback error:" + se2.getMessage());
			}
			se.printStackTrace();
			throw new Exception("Ralat Simpan Aduan:" + se.getMessage());
		} finally {
			if (conn != null)
				conn.close();
			if (db != null)
				db.close();
		}

	}


	
	public synchronized void insertTBLPPKBORANG_HISTORY(long idBorang, String idfail, String borang, String idmasuk,HttpServletRequest request) {
		Db db = null;
		String NO_PINDAAN = "";
		try {
			
	
		
			Date now = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
			String tahun = formatter.format(now);
			String seksyen = getSeksyen(idfail);
			
			
			String id_daerah = getID_DaerahMohon(idfail);
			
			NO_PINDAAN = String.format("%02d", getSeqNoPindaan(tahun,id_daerah,"PPK",seksyen))+"/"+tahun;
		
			db = new Db();

	
			Connection con = db.getConnection();
			con.setAutoCommit(false);
			PreparedStatement ps = con.prepareStatement("insert into TBLPPKBORANG_HISTORY"
					+ "(id_borang,id_fail,nama_borang,no_pindaan ,tarikh_kemaskini,tarikh_masuk,id_masuk,id_kemaskini, flag_keb_kemaskini) "
					+ "values(?,?,?,?,sysdate,sysdate,?,?,?)");

			ps.setLong(1, idBorang);
			ps.setString(2, idfail);
			ps.setString(3, borang);
			//ps.setBytes(4, pdf);
			ps.setString(4, NO_PINDAAN);
			ps.setString(5, idmasuk);
			ps.setString(6, idmasuk);
			ps.setString(7, "1");

			ps.executeUpdate();
			
		
			con.commit();
			HttpSession session = request.getSession();

			AuditTrail.logActivity(null,session,"INS","TBLPPKPINDAAN [ID_BORANGPINDAAN : "+idBorang+"] Inserted");
		
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}
	

	}// end 

	public synchronized void viewPDF(HttpServletRequest request, HttpServletResponse response, String id_borang) throws IOException {
		BLOB blob = null;
		byte[] pdfStream = null;
		String contextPath = request.getContextPath();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Connection con = db.getConnection();
			con.setAutoCommit(false);
			PreparedStatement ps = con.prepareStatement("SELECT content from " + "TBLPPKBORANG_HISTORY WHERE ID_BORANG=? FOR UPDATE");
			ps.setString(1, id_borang);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				pdfStream = rs.getBytes("content");
			}
			response.setContentType("application/pdf");
			response.setContentLength(pdfStream.length);
			ServletOutputStream ouputStream = response.getOutputStream();
			ouputStream.write(pdfStream, 0, pdfStream.length);
			ouputStream.flush();
			ouputStream.close();
			con.commit();

		} catch (Exception e) {
			doException(response, contextPath, e);
		} finally {
			if (db != null)
				db.close();
		}

	}// end very code post
	
	public void updateCOUNT_PAGES(String ID_BORANGPNB,Integer COUNT_PAGES, Db db) throws Exception {
		String sql = "";
		try {
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
				r.update("ID_BORANGPNB", ID_BORANGPNB);
				r.add("COUNT_PAGES", COUNT_PAGES);
				sql = r.getSQLUpdate("TBLPPKBORANGPNB");
			
			myLogger.info("updateCOUNT_PAGES : "+sql);				
			stmt.executeUpdate(sql);
			} 
		finally {
			//if (db != null)
			//	db.close();
		}
	}
	
	
	public synchronized void viewPDFPNB(HttpServletRequest request, HttpServletResponse response, String ID_BORANGPNB) throws IOException {
		BLOB blob = null;
		byte[] pdfStream = null;
		String contextPath = request.getContextPath();
		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Connection con = db.getConnection();
			con.setAutoCommit(false);
			PreparedStatement ps = con.prepareStatement("SELECT content from TBLPPKBORANGPNB WHERE ID_BORANGPNB=? FOR UPDATE");
			ps.setString(1, ID_BORANGPNB);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				pdfStream = rs.getBytes("content");
			}
			response.setContentType("application/pdf");
			response.setContentLength(pdfStream.length);
			ServletOutputStream ouputStream = response.getOutputStream();
			ouputStream.write(pdfStream, 0, pdfStream.length);
			ouputStream.flush();
			ouputStream.close();
			con.commit();

		} catch (Exception e) {
			doException(response, contextPath, e);
		} finally {
			if (db != null)
				db.close();
		}

	}// end very code post
	
	
	/**
	 * Fungsi mendapatkan nama context (folder fizikal applikasi)
	 * Dibuat Oleh	: Mohamad Rosli
	 * Dibuat Pada	: 27/01/2017
	 * Dikemaskini Oleh	: 
	 * Dikemaskini Pada :
	 * @return
	 */
	private String getAppContext(){
		String appContext ="myetapp";
		ResourceBundle rb = ResourceBundle.getBundle("file");
		appContext = rb.getString("context_name");
		myLogger.info("getAppContext="+appContext);
		return appContext;
		
	}
	/**
	 * Fungsi mendapatkan nama folder ( fizikal laporan) 
	 * dan nama context pada file.properties
	 * Dibuat Oleh	: Mohamad Rosli
	 * Dibuat Pada	: 27/01/2017
	 * Dikemaskini Oleh	: 
	 * Dikemaskini Pada :
	 * @return
	 */
	private String getReportPath(ServletContext context,String rtype){
		String realPathReport = context.getRealPath(File.separator + rtype + File.separator)
			.replace("johor" + File.separator, "")
			.replace("kedah" + File.separator, "")
			.replace("ekptgv3" + File.separator, "")
			.replace("kelantan" + File.separator, "")
			.replace("melaka" + File.separator, "")
			.replace("ns" + File.separator, "")
			.replace("pahang" + File.separator, "")
			.replace("penang" + File.separator, "")
			.replace("perak" + File.separator, "")
			.replace("perlis" + File.separator, "")
			.replace("selangor" + File.separator, "")
			.replace("terengganu" + File.separator, "")
			.replace("hq" + File.separator, "")
			.replace("ekptgv2" + File.separator, "")
			.replace("wp" + File.separator, "")
			.replace(getAppContext() + File.separator, "");
		//myLogger.info("realPathReport="+realPathReport);
		return realPathReport;
	
	}
	
	
	
	public Hashtable<String,String> infoPerbicaraan(String ID_FAIL, String ID_PERBICARAAN) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			db = new Db();
			stmt = db.getStatement();
			
			sql = " SELECT PB.ID_PERBICARAAN,F.ID_FAIL,F.NO_FAIL, PEJ.ID_PEJABATJKPTG, P.ID_NEGERIMHN, TRIM(TO_CHAR(PB.BIL_BICARA,'09')) AS BIL_BICARA, " +
					" TO_CHAR(PB.TARIKH_BICARA,'DD/MM/YYYY') AS TARIKH_BICARA, " +
					" TO_CHAR(PB.TARIKH_NOTIS,'DD/MM/YYYY') AS TARIKH_NOTIS," +
					" P.SEKSYEN "+ 
					" FROM TBLPPKPERBICARAAN PB, TBLPPKKEPUTUSANPERMOHONAN KP, TBLPPKPERMOHONAN P,  "+
					" TBLPFDFAIL F, TBLRUJPEJABATURUSAN PU, TBLRUJPEJABATJKPTG PEJ "+
					" WHERE F.ID_FAIL = P.ID_FAIL AND P.ID_PERMOHONAN = KP.ID_PERMOHONAN AND KP.ID_KEPUTUSANPERMOHONAN = PB.ID_KEPUTUSANPERMOHONAN "+
					" AND P.ID_DAERAHMHN = PU.ID_DAERAHURUS AND PU.ID_PEJABATJKPTG = PEJ.ID_PEJABATJKPTG AND PEJ.ID_SEKSYEN = 2 "+ 
					" AND PEJ.ID_JENISPEJABAT = 22 AND PU.ID_JENISPEJABAT = 22 "+
					" AND PB.ID_PERBICARAAN = '"+ID_PERBICARAAN+"' AND F.ID_FAIL = '"+ID_FAIL+"' ";
			myLogger.info(" infoPerbicaraan :" + sql.toUpperCase());
			rs = stmt.executeQuery(sql);
			Hashtable<String,String> h;
			h = new Hashtable<String,String>();
			
			while (rs.next()) {
				h.put("ID_FAIL",rs.getString("ID_FAIL") == null ? "" : rs.getString("ID_FAIL"));
				h.put("NO_FAIL",rs.getString("NO_FAIL") == null ? "" : rs.getString("NO_FAIL"));
				h.put("ID_PEJABATJKPTG",rs.getString("ID_PEJABATJKPTG") == null ? "" : rs.getString("ID_PEJABATJKPTG"));
				h.put("ID_PERBICARAAN",rs.getString("ID_PERBICARAAN") == null ? "" : rs.getString("ID_PERBICARAAN"));
				h.put("ID_NEGERIMHN",rs.getString("ID_NEGERIMHN") == null ? "" : rs.getString("ID_NEGERIMHN"));
				h.put("BIL_BICARA",rs.getString("BIL_BICARA") == null ? "" : rs.getString("BIL_BICARA"));
				h.put("TARIKH_BICARA",rs.getString("TARIKH_BICARA") == null ? "" : rs.getString("TARIKH_BICARA"));
				h.put("TARIKH_NOTIS",rs.getString("TARIKH_NOTIS") == null ? "" : rs.getString("TARIKH_NOTIS"));
				h.put("SEKSYEN",rs.getString("SEKSYEN") == null ? "" : rs.getString("SEKSYEN"));
			}
			return h;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
	}
	
	public String getNegeriMohon(String idFail) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			db = new Db();
			stmt = db.getStatement();
			String KOD_NEGERI="";
			sql = " SELECT B.KOD_NEGERI " +
					" FROM TBLPFDFAIL A,TBLRUJNEGERI B " +
					" WHERE A.ID_NEGERI = B.ID_NEGERI " +
					" AND A.ID_FAIL ='"+idFail+"' ";	
			
					//myLogger.info("  : getKodNegeri :" + sql.toUpperCase());
			
				rs = stmt.executeQuery(sql);				
				while (rs.next()) {				
					
					KOD_NEGERI = (rs.getString("KOD_NEGERI") == null ? "" : rs.getString("KOD_NEGERI"));
				}			
			return KOD_NEGERI;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
	}
	
	public String getID_DaerahMohon(String idFail) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			db = new Db();
			stmt = db.getStatement();
			String ID_NEGERI="";
			sql = " SELECT B.ID_DAERAHMHN AS ID_DAERAH" +
					" FROM TBLPFDFAIL A, TBLPPKPERMOHONAN B " +
					" WHERE A.ID_FAIL = B.ID_FAIL " +
					" AND A.ID_FAIL = '"+idFail+"' ";	
			
					//myLogger.info("  : getID_DAERAH :" + sql.toUpperCase());
			
				rs = stmt.executeQuery(sql);				
				while (rs.next()) {				
					
					ID_NEGERI = (rs.getString("ID_DAERAH") == null ? "" : rs.getString("ID_DAERAH"));
				}			
			return ID_NEGERI;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
	}
	
	public String getSeksyen(String idFail) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			db = new Db();
			stmt = db.getStatement();
			String SEKSYEN="";
			sql = " SELECT p.SEKSYEN " +
					" FROM tblppkpermohonan p " +
					" WHERE p.ID_FAIL = '"+idFail+"' ";	
			
				//	myLogger.info("  : getSeksyen :" + sql.toUpperCase());
			
				rs = stmt.executeQuery(sql);				
				while (rs.next()) {				
					
					SEKSYEN = (rs.getString("SEKSYEN") == null ? "" : rs.getString("SEKSYEN"));
				}			
			return SEKSYEN;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List listOBPerbicaraan(String ID_FAIL, String ID_PERBICARAAN, String borang, String idSimati)throws Exception {
		Db db = null;
		ResultSet rs = null;
		Statement stmt = null;
		List listOBPerbicaraan = null;
		String sql = "";
		String sqlOLD = "";
		
		try {
			db = new Db();
			stmt = db.getStatement(); 
			
		
			
			sql = " SELECT PENERIMA.TARAF, PENERIMA.ID_FAIL, PENERIMA.ID_PERBICARAAN, PENERIMA.ID_PK, PENERIMA.TABLENAME, "+
					"    			    PENERIMA.NAMA,PENERIMA.ALAMAT_1, PENERIMA.ALAMAT_2, PENERIMA.ALAMAT_3, PENERIMA.POSKOD,PENERIMA.BANDAR, PENERIMA.NEGERI, "+
					"    			    PNBMAIN.FLAG_HANTAROB, PNBMAIN.FLAG_RETURN, PNBMAIN.CATATAN_RETURN FROM "+
					"    			    (SELECT UPPER(TAR.KETERANGAN) AS TARAF,D.ID_FAIL, F.ID_PERBICARAAN,A.ID_OB AS ID_PK,'TBLPPKOB' AS TABLENAME,UPPER(NVL(A.NAMA_OB,' ')) AS NAMA, "+
					"    			         REPLACE(UPPER(REPLACE(TRIM(A.ALAMAT1_SURAT),'&','&#38;')),',') AS ALAMAT_1, "+
					"    			         REPLACE(UPPER(REPLACE(TRIM(A.ALAMAT2_SURAT),'&','&#38;')),',') AS ALAMAT_2, "+
					"    			         REPLACE(UPPER(REPLACE(TRIM(A.ALAMAT3_SURAT),'&','&#38;')),',') AS ALAMAT_3, "+
					"    			         UPPER(NVL(TBLRUJBANDAR.KETERANGAN,''))AS BANDAR, "+
					"    			         NVL(A.POSKOD_SURAT,'') AS POSKOD, "+
					"    			         UPPER(NVL(TBLRUJNEGERI.NAMA_NEGERI,'')) AS NEGERI "+
					"    			    FROM TBLPPKOB A, "+
					"    			         TBLPPKPERMOHONANSIMATI B, "+
					"    			         TBLPPKPERMOHONAN C, "+
					"    			         TBLPFDFAIL D, "+
					"    			         TBLRUJNEGERI, "+
					"    			         TBLPPKSIMATI H, "+
					"    			         TBLRUJBANDAR, "+
					"    			         TBLPPKKEPUTUSANPERMOHONAN E, "+
					"    			         TBLPPKPERBICARAAN F, "+
					"    			         TBLPPKPEMOHON G, TBLPPKRUJTARAFKPTG TAR ,"+
					"						 TBLPPKNOTISOBDTL obdtl, "+
					"						 TBLPPKNOTISOBMST obmst  "+
					"    			   WHERE A.ID_TARAFKPTG = TAR.ID_TARAFKPTG    "+
					"    			         AND G.ID_PEMOHON = C.ID_PEMOHON "+
					"    			         AND B.ID_PERMOHONAN =C.ID_PERMOHONAN "+
					"    			         AND B.ID_SIMATI = H.ID_SIMATI "+
					//"    			         AND B.ID_PERMOHONANSIMATI = A.ID_PERMOHONANSIMATI  "+
					"    			         AND H.ID_SIMATI = A.ID_SIMATI "+
					"    			         AND D.ID_FAIL = C.ID_FAIL "+
					"    			         AND C.ID_PERMOHONAN = E.ID_PERMOHONAN "+
					"    			         AND E.ID_KEPUTUSANPERMOHONAN = F.ID_KEPUTUSANPERMOHONAN "+
					" 						 AND H.ID_SIMATI = '"+idSimati+"' "+			
					//"    			         AND D.ID_FAIL = '"+ID_FAIL+"' "+
					"    			         AND F.ID_PERBICARAAN = '"+ID_PERBICARAAN+"' "+
					"    			         AND TBLRUJBANDAR.ID_BANDAR(+) = A.ID_BANDARSURAT "+
					"    			         AND TBLRUJNEGERI.ID_NEGERI(+) = A.ID_NEGERISURAT "+
					"    			         AND (A.UMUR IS NULL OR A.UMUR >= 18) "+
					"    			         AND (A.STATUS_HIDUP = 0 OR A.STATUS_HIDUP IS NULL)   "+ 
					"						 AND obdtl.ID_OB = a.ID_OB "+ 
			        "						 AND obmst.ID_NOTISOBMST = obdtl.ID_NOTISOBMST "+ 
			        "						 AND obmst.JENIS_SERAH = 5 "+ 
					" ) PENERIMA, "+
					"    			         (SELECT PNB.ID_FAIL, PNB.ID_PERBICARAAN, "+
					"    			         PNBOB.FLAG_HANTAROB, PNBOB.FLAG_RETURN, PNBOB.CATATAN_RETURN, PNBOB.ID_PK, PNBOB.TABLENAME  "+
					"    			         FROM TBLPPKBORANGPNBOB PNBOB, TBLPPKBORANGPNB PNB "+
					"    			         WHERE PNB.ID_BORANGPNB = PNBOB.ID_BORANGPNB "+
					//"    			         AND PNB.ID_FAIL = '"+ID_FAIL+"' "+
					"    			         AND PNB.ID_PERBICARAAN = '"+ID_PERBICARAAN+"') PNBMAIN "+
					"    			         WHERE PENERIMA.ID_FAIL = PNBMAIN.ID_FAIL(+) " +
					//" AND PENERIMA.ID_PERBICARAAN = PNBMAIN.ID_PERBICARAAN(+) "+
					"    			         AND PENERIMA.ID_PK = PNBMAIN.ID_PK(+) AND PENERIMA.TABLENAME = PNBMAIN.TABLENAME(+) ";

			myLogger.info(" V3: SQL listOBPerbicaraan :"+ sql);
			rs = stmt.executeQuery(sql);
			listOBPerbicaraan = Collections.synchronizedList(new ArrayList());
			Map h = null;
			int bil = 0;
			while (rs.next()) {
				
				h = Collections.synchronizedMap(new HashMap());
				bil++;
				h.put("BIL",bil);
				h.put("TARAF",rs.getString("TARAF") == null ? "" : rs.getString("TARAF"));
				h.put("ID_FAIL",ID_FAIL);
				h.put("ID_PERBICARAAN",ID_PERBICARAAN);
				h.put("ID_PK",rs.getString("ID_PK") == null ? "" : rs.getString("ID_PK"));
				h.put("TABLENAME",rs.getString("TABLENAME") == null ? "" : rs.getString("TABLENAME"));
				h.put("NAMA",rs.getString("NAMA") == null ? "" : rs.getString("NAMA"));
				h.put("ALAMAT_1",rs.getString("ALAMAT_1") == null ? "" : rs.getString("ALAMAT_1"));
				h.put("ALAMAT_2",rs.getString("ALAMAT_2") == null ? "" : rs.getString("ALAMAT_2"));
				h.put("ALAMAT_3",rs.getString("ALAMAT_3") == null ? "" : rs.getString("ALAMAT_3"));
				h.put("POSKOD",rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD"));
				h.put("BANDAR",rs.getString("BANDAR") == null ? "" : rs.getString("BANDAR"));
				h.put("NEGERI",rs.getString("NEGERI") == null ? "" : rs.getString("NEGERI"));
				h.put("FLAG_HANTAROB",rs.getString("FLAG_HANTAROB") == null ? "" : rs.getString("FLAG_HANTAROB"));
				h.put("FLAG_RETURN",rs.getString("FLAG_RETURN") == null ? "" : rs.getString("FLAG_RETURN"));
				h.put("CATATAN_RETURN",rs.getString("CATATAN_RETURN") == null ? "" : rs.getString("CATATAN_RETURN"));
				listOBPerbicaraan.add(h);
			}

		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}

		return listOBPerbicaraan;

	}
	
	
	public void deletePNB(String ID_BORANGPNB) throws Exception {
		Connection conn = null;
		Db db = null;
		String sql = "";
		
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);
			
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();	
				
			r.clear();
			r.add("ID_BORANGPNB",ID_BORANGPNB);
			sql = r.getSQLDelete("TBLPPKBORANGPNB");
			stmt.executeUpdate(sql);
			
			r.clear();
			r.add("ID_BORANGPNB",ID_BORANGPNB);
			sql = r.getSQLDelete("TBLPPKBORANGPNBOB");
			stmt.executeUpdate(sql);
			conn.commit();
		
	} 
	catch (SQLException se) { 
		myLogger.error(se);
    	try {
    		conn.rollback();
    	} catch (SQLException se2) {
    		throw new Exception("Rollback error:"+se2.getMessage());
    	}
    	throw new Exception("Ralat Pendaftaran Maklumat Bantahan:"+se.getMessage());
	}
	catch (Exception re) {
		throw re;
	}finally {
		if (db != null)
			db.close();
	}
}
	
	public String getPrevIdPNB(String ID_FAIL,String ID_PERBICARAAN) throws Exception {
		Db db = null;
		String sql = "";
		ResultSet rs = null;
		Statement stmt = null;
		try {
			db = new Db();
			stmt = db.getStatement();
			String ID_BORANGPNB="";
			sql = " SELECT ID_BORANGPNB FROM TBLPPKBORANGPNB "+
					" WHERE ID_FAIL = '"+ID_FAIL+"' AND ID_PERBICARAAN = '"+ID_PERBICARAAN+"' "+
					" AND NVL(FLAG_DOWNLOAD,' ') != 'Y' ";
					
				myLogger.info(" OT : getPrevIdPNB :" + sql.toUpperCase());
				rs = stmt.executeQuery(sql);
				
				int bil = 0;
				while (rs.next()) {				
					
					ID_BORANGPNB += (rs.getString("ID_BORANGPNB") == null ? "" : rs.getString("ID_BORANGPNB"));
				}
			
			return ID_BORANGPNB;
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (db != null)
				db.close();
		}
	}
	
	public static synchronized int getSeqNoPNB(String tahun,String id_negeri,String kod_modul)
			throws DbException {

		Db db = null;
		Connection conn = null;
		// File f = null;
		StringBuffer sb = new StringBuffer();
		int seqno = 0;
		try {
			db = new Db();
			conn = db.getConnection();
			conn.setAutoCommit(false);

			// f = new File();
			boolean found = false;

			sb.append("SELECT NO_TURUTAN FROM TBLRUJSEQESNOPNB WHERE ");
			sb.append(" TAHUN = '" + tahun + "'");
			sb.append(" AND ID_NEGERI = '" + id_negeri + "'");
			sb.append(" AND KOD_MODUL = '" + kod_modul + "'");

			ResultSet rs = db.getStatement().executeQuery(sb.toString());

			if (rs.next())
				found = true;
			
			myLogger.info("found :"+found);
			
			
			if (found) {
				// f.increaseSeqAduan(id_jenisaduan);
				increaseNoPNB(tahun,id_negeri,kod_modul);
			} else {
				// f.addNewAduan(id_jenisaduan);
				addNoPNB(tahun,id_negeri,kod_modul);
			}
			ResultSet rs2 = db.getStatement().executeQuery(sb.toString());
			if (rs2.next()) {

				seqno = rs2.getInt("NO_TURUTAN");

			}
			conn.commit();

		} catch (Exception ex) {
			try {
				conn.rollback();
			} catch (SQLException localSQLException1) {
			}
			throw new DbException(ex.getMessage() + ": " + sb.toString());
		} finally {
			if (db != null)
				db.close();
		}

		return seqno;
	}
		 public static void increaseNoPNB(String tahun,String id_negeri,String kod_modul) throws DbException {

				Db db = null;

				StringBuffer sb = new StringBuffer();
				sb.append("UPDATE TBLRUJSEQESNOPNB  SET ");
				sb.append("no_turutan = no_turutan + 1 ");
				sb.append(" WHERE ");
				sb.append(" TAHUN = '" + tahun + "'");
				sb.append(" AND id_negeri = '" + id_negeri + "'");
				sb.append(" AND kod_modul = '" + kod_modul + "'");


				try {
					db = new Db();
					try {
						db.getStatement().executeUpdate(sb.toString());
					} catch (SQLException x) {
						x.printStackTrace();
					}
				} catch (Exception ex) {
					throw new DbException(ex.getMessage() + ": " + sb.toString());
				} finally {
					if (db != null)
						db.close();
				}
			}
		 public static void addNoPNB(String tahun,String id_negeri,String kod_modul) throws DbException {

				Db db = null;
				StringBuffer sb = new StringBuffer();
				sb.append("INSERT INTO TBLRUJSEQESNOPNB (TAHUN,ID_NEGERI,KOD_MODUL,NO_TURUTAN)");
				sb.append(" VALUES (");
				sb.append("'" + tahun + "',");
				sb.append("'" + id_negeri + "',");
				sb.append("'" + kod_modul + "'");
				sb.append(",1)"); // initial value

				try {
					db = new Db();
					db.getStatement().executeUpdate(sb.toString());
				} catch (Exception ex) {
					throw new DbException(ex.getMessage() + ": " + sb.toString());
				} finally {
					if (db != null)
						db.close();
				}
			}
		 
		 
		 public static synchronized int getSeqNoPindaan(String tahun,String id_daerah,String kod_modul, String seksyen)
					throws DbException {

				Db db = null;
				Connection conn = null;
				// File f = null;
				StringBuffer sb = new StringBuffer();
				int seqno = 0;
				try {
					db = new Db();
					conn = db.getConnection();
					conn.setAutoCommit(false);

					// f = new File();
					boolean found = false;
				
					sb.append("SELECT NO_TURUTAN FROM TBLRUJSEQESNOPINDAAN WHERE ");
					sb.append(" TAHUN = '" + tahun + "'");
					sb.append(" AND ID_DAERAH = '" + id_daerah + "'");
					sb.append(" AND KOD_MODUL = '" + kod_modul + "'");
					sb.append(" AND SEKSYEN = '" + seksyen + "'");

					ResultSet rs = db.getStatement().executeQuery(sb.toString());

					if (rs.next())
						found = true;
					
					myLogger.info("found :"+found);
					
					
					if (found) {
						// f.increaseSeqAduan(id_jenisaduan);
						increaseNoPindaan(tahun,id_daerah,kod_modul,seksyen);
						myLogger.info("jumpe::: :");
					
					} else {
						// f.addNewAduan(id_jenisaduan);
						addNoPindaan(tahun,id_daerah,kod_modul,seksyen);
						myLogger.info("x de lagi jumpe::: :");
						
					}
					ResultSet rs2 = db.getStatement().executeQuery(sb.toString());
					if (rs2.next()) {

						seqno = rs2.getInt("NO_TURUTAN");
						
					}
					myLogger.info("seqno===="+seqno);
					conn.commit();

				} catch (Exception ex) {
					try {
						conn.rollback();
					} catch (SQLException localSQLException1) {
					}
					throw new DbException(ex.getMessage() + ": " + sb.toString());
				} finally {
					if (db != null)
						db.close();
				}

				return seqno;
			}
		 
		 
		 public static void increaseNoPindaan(String tahun,String id_daerah,String kod_modul, String seksyen) throws DbException {

				Db db = null;

				StringBuffer sb = new StringBuffer();
				sb.append("UPDATE TBLRUJSEQESNOPINDAAN  SET ");
				sb.append("no_turutan = no_turutan + 1 ");
				sb.append(" WHERE ");
				sb.append(" TAHUN = '" + tahun + "'");
				sb.append(" AND id_daerah = '" + id_daerah + "'");
				sb.append(" AND kod_modul = '" + kod_modul + "'");
				sb.append(" AND SEKSYEN = '" + seksyen + "'");


				try {
					db = new Db();
					try {
						db.getStatement().executeUpdate(sb.toString());
					} catch (SQLException x) {
						x.printStackTrace();
					}
				} catch (Exception ex) {
					throw new DbException(ex.getMessage() + ": " + sb.toString());
				} finally {
					if (db != null)
						db.close();
				}
			}
		 public static void addNoPindaan(String tahun,String id_daerah,String kod_modul, String seksyen) throws DbException {

				Db db = null;
				StringBuffer sb = new StringBuffer();
				sb.append("INSERT INTO TBLRUJSEQESNOPINDAAN (TAHUN,ID_DAERAH,KOD_MODUL,SEKSYEN,NO_TURUTAN)");
				sb.append(" VALUES (");
				sb.append("'" + tahun + "',");
				sb.append("'" + id_daerah + "',");
				sb.append("'" + kod_modul + "',");
				sb.append("'" + seksyen + "'");
				sb.append(",1)"); // initial value

				try {
					db = new Db();
					db.getStatement().executeUpdate(sb.toString());
				} catch (Exception ex) {
					throw new DbException(ex.getMessage() + ": " + sb.toString());
				} finally {
					if (db != null)
						db.close();
				}
			}
	
		 
		 public String getKodNegeri(String ID_NEGERI) throws Exception {
				Db db = null;
				String sql = "";
				ResultSet rs = null;
				Statement stmt = null;
				try {
					db = new Db();
					stmt = db.getStatement();
					String KOD_NEGERI="";
					sql = " SELECT KOD_NEGERI FROM TBLRUJNEGERI WHERE ID_NEGERI = '"+ID_NEGERI+"' ";	
					myLogger.info(" OT : getKodNegeri :" + sql.toUpperCase());
						rs = stmt.executeQuery(sql);				
						while (rs.next()) {				
							
							KOD_NEGERI = (rs.getString("KOD_NEGERI") == null ? "" : rs.getString("KOD_NEGERI"));
						}			
					return KOD_NEGERI;
				} finally {
					if (rs != null)
						rs.close();
					if (stmt != null)
						stmt.close();
					if (db != null)
						db.close();
				}
			}
		 
		 
		 public void insertPenerimaPNB(String ID_BORANGPNBOB,String ID_BORANGPNB, String ID_PK, String TABLENAME,String NAMA,String TARAF, 
				 String ALAMAT_1, String ALAMAT_2, String ALAMAT_3, String POSKOD, String BANDAR, String NEGERI,HttpServletRequest request) throws Exception {
				Connection conn = null;
				Db db = null;
				String sql = "";
				long idTT = 0;
				HttpSession session = request.getSession();
				try {
					db = new Db();
					conn = db.getConnection();
					conn.setAutoCommit(false);
					
					
					Statement stmt = db.getStatement();
					SQLRenderer r = new SQLRenderer();
					
					
					if(!ID_BORANGPNBOB.equals(""))
					{
						r.update("ID_BORANGPNBOB", ID_BORANGPNBOB);
					}
					else
					{
						idTT = DB.getNextID(db, "TBLPPKBORANGPNBOB_SEQ");
						r.add("ID_BORANGPNBOB", idTT);
					}
					r.add("ID_BORANGPNB", ID_BORANGPNB);
					r.add("ID_PK", ID_PK);
					r.add("TABLENAME", TABLENAME);					
					r.add("NAMA", NAMA);
					r.add("TARAF", TARAF);
					r.add("ALAMAT_1", ALAMAT_1);
					r.add("ALAMAT_2", ALAMAT_2);
					r.add("ALAMAT_3", ALAMAT_3);
					r.add("POSKOD", POSKOD);
					r.add("BANDAR", BANDAR);
					r.add("NEGERI", NEGERI);
					
					if(!ID_BORANGPNBOB.equals(""))
					{
						sql = r.getSQLUpdate("TBLPPKBORANGPNBOB");
					}
					else
					{
						sql = r.getSQLInsert("TBLPPKBORANGPNBOB");	
					}
					myLogger.info("insertPenerimaPNB : "+sql);				
					stmt.executeUpdate(sql);
					conn.commit();
					
					if(!ID_BORANGPNBOB.equals(""))
					{
						//AuditTrail.logActivity("UPD","TBLPPKBORANGPNBOB [ID_BORANGPNBOB : "+ID_BORANGPNBOB+"] Updated");	
						AuditTrail.logActivity(null,session,"UPD","TBLPPKBORANGPNBOB [ID_BORANGPNBOB : "+ID_BORANGPNBOB+"] Updated");
					}
					else
					{
						//AuditTrail.logActivity("INS","TBLPPKBORANGPNBOB [ID_BORANGPNBOB : "+idTT+"] Inserted");	
						AuditTrail.logActivity(null,session,"INS","TBLPPKBORANGPNBOB [ID_BORANGPNBOB : "+idTT+"] Inserted");
					}
					
					
					
				} 
				catch (SQLException se) { 
					myLogger.error(se);
			    	try {
			    		conn.rollback();
			    	} catch (SQLException se2) {
			    		throw new Exception("Rollback error:"+se2.getMessage());
			    	}
			    	throw new Exception("Ralat Pendaftaran :"+se.getMessage());
				}
				catch (Exception re) {
					throw re;
				}finally {
					if (db != null)
						db.close();
				}
			}

		 
		 public List listPNBOB(String ID_BORANGPNB)throws Exception {
				Db db = null;
				ResultSet rs = null;
				Statement stmt = null;
				List listPNBOB = null;
				String sql = "";
				try {
					db = new Db();
					stmt = db.getStatement();
					
					sql = " SELECT  " +
							" T.ID_BORANGPNBOB, T.ID_BORANGPNB, T.ID_PK, "+
							" T.TABLENAME, T.FLAG_HANTAROB, T.ID_HANTAROB,  "+
							" TO_CHAR(T.TARIKH_HANTAROB,'DD/MM/YYYY') AS TARIKH_HANTAROB, T.FLAG_RETURN, T.ID_RETURN,  "+
							" TO_CHAR(T.TARIKH_RETURN) AS TARIKH_RETURN, UPPER(T.CATATAN_RETURN) AS CATATAN_RETURN, T.NAMA,  "+
							" T.TARAF, T.ALAMAT_1, T.ALAMAT_2,  "+
							" T.ALAMAT_3, T.POSKOD, T.NEGERI,  "+
							" T.BANDAR, T.NOSURAT_HANTAROB  "+
							" FROM TBLPPKBORANGPNBOB T WHERE T.ID_BORANGPNB ='"+ID_BORANGPNB+"' " +
									" ORDER BY T.NAMA ";
					
					myLogger.info(" SQL : listPNBOB :"+ sql);			
					rs = stmt.executeQuery(sql);
					listPNBOB = Collections.synchronizedList(new ArrayList());
					Map h = null;
					int bil = 0;
					while (rs.next()) {
						h = Collections.synchronizedMap(new HashMap());
						h.put("ID_BORANGPNBOB",rs.getString("ID_BORANGPNBOB") == null ? "" : rs.getString("ID_BORANGPNBOB"));
						h.put("ID_BORANGPNB",rs.getString("ID_BORANGPNB") == null ? "" : rs.getString("ID_BORANGPNB"));
						h.put("ID_PK",rs.getString("ID_PK") == null ? "" : rs.getString("ID_PK"));
						h.put("TABLENAME",rs.getString("TABLENAME") == null ? "" : rs.getString("TABLENAME"));
						h.put("FLAG_HANTAROB",rs.getString("FLAG_HANTAROB") == null ? "" : rs.getString("FLAG_HANTAROB"));
						h.put("ID_HANTAROB",rs.getString("ID_HANTAROB") == null ? "" : rs.getString("ID_HANTAROB"));
						h.put("TARIKH_HANTAROB",rs.getString("TARIKH_HANTAROB") == null ? "" : rs.getString("TARIKH_HANTAROB"));
						h.put("FLAG_RETURN",rs.getString("FLAG_RETURN") == null ? "" : rs.getString("FLAG_RETURN"));
						h.put("TARIKH_RETURN",rs.getString("TARIKH_RETURN") == null ? "" : rs.getString("TARIKH_RETURN"));
						h.put("CATATAN_RETURN",rs.getString("CATATAN_RETURN") == null ? "" : rs.getString("CATATAN_RETURN"));
						h.put("NAMA",rs.getString("NAMA") == null ? "" : rs.getString("NAMA"));
						h.put("TARAF",rs.getString("TARAF") == null ? "" : rs.getString("TARAF"));
						h.put("ALAMAT_1",rs.getString("ALAMAT_1") == null ? "" : rs.getString("ALAMAT_1"));
						h.put("ALAMAT_2",rs.getString("ALAMAT_2") == null ? "" : rs.getString("ALAMAT_2"));
						h.put("ALAMAT_3",rs.getString("ALAMAT_3") == null ? "" : rs.getString("ALAMAT_3"));
						h.put("POSKOD",rs.getString("POSKOD") == null ? "" : rs.getString("POSKOD"));
						h.put("NEGERI",rs.getString("NEGERI") == null ? "" : rs.getString("NEGERI"));
						h.put("BANDAR",rs.getString("BANDAR") == null ? "" : rs.getString("BANDAR"));
						h.put("NOSURAT_HANTAROB",rs.getString("NOSURAT_HANTAROB") == null ? "" : rs.getString("NOSURAT_HANTAROB"));
						listPNBOB.add(h);
					}

				} finally {
					if (rs != null)
						rs.close();
					if (stmt != null)
						stmt.close();
					if (db != null)
						db.close();
				}
				return listPNBOB;
			}
			
		 public String getIdSimati( String no_fail) throws Exception {

				Db db = null;

				String sql = "";
				String idSimati = "";
				try {


					db = new Db();

					Statement stmt = db.getStatement();

					sql = "select s.ID_SIMATI " +
							" from tblpfdfail f, tblppkpermohonan p, tblppkpermohonansimati ps, tblppksimati s " +
							" where f.ID_FAIL =p.ID_FAIL and p.ID_PERMOHONAN = ps.ID_PERMOHONAN and ps.ID_SIMATI = s.ID_SIMATI " +
							" and f.no_FAIL = '"+no_fail+"'";

					ResultSet rs = stmt.executeQuery(sql);
					Vector list = new Vector();

					Hashtable h;
					

					while (rs.next()) {
						idSimati = rs.getString("ID_SIMATI");

					}
					return idSimati;
				} catch (Exception er) {
					myLogger.error(er);
					return idSimati;
				} finally {
					if (db != null)
						db.close();
				}
			}

		 
}