package ekptg.helpers;

import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Hashtable;
import java.util.Vector;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import net.sf.ehcache.Element;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;

/*  
 * sample to call this method from jsp pages.
 $EkptgUtil.subString("hello world from azam",0,5)
 */
public class Utils extends EkptgCache implements Serializable {
	private static final long serialVersionUID = 3009746555818982094L;
	static Logger myLogger = Logger.getLogger(ekptg.helpers.Utils.class);

	public static String show() {
		return "ekptg.helpers.Utils";
	}

	public static String isNull(String txt) {
		if (txt == null)
			return "";
		else
			return txt;
	}

	public static String RemoveSymbol(String input) {
		if (input == null)
			return "";
		String regex = "(?<=\\d),(?=\\d)";
		return input.replaceAll(regex, "");
	}

	public static String RemoveComma(String input) {
		if (input == null)
			return "";
		return input.replaceAll(",", "");
	}

	public static String RemoveDash(String input) {
		if (input == null)
			return "";
		String regex = "-";
		return input.replaceAll(regex, "");
	}

	public static String escapeCharacter(String input) {
		if (input == null)
			return "";
		input = input.replaceAll("'", "''");
		return input;
	}

	public static String escapeJavaScript(String input) {
		// return "";
		return StringEscapeUtils.escapeJavaScript(input);
	}

	public static String unescapeJavaScript(String input) {
		return StringEscapeUtils.unescapeJavaScript(input);
	}

	public static String escapeSQL(String input) {
		return StringEscapeUtils.escapeSql(input);
	}

	public static String addSlashes(String input) {
		if (input == null)
			return "";
		// input = input.replaceAll("'","\\\\''");
		input = input.replaceAll("'", "\\XXX");
		return input;
	}

	public static Long parseLong(String input) {
		if (input == null || "".equals(input) || "null".equals(input)) {
			// myLogger.debug("input:"+input);
			return Long.parseLong("-1");
		} else
			return Long.parseLong(input);
	}

	public static int parseInt(String input) {
		if (input == null || "".equals(input) || "null".equals(input)) {
			// myLogger.debug("input:"+input);
			return Integer.parseInt("-1");
		} else
			return Integer.parseInt(input);
	}

	public static double parseDouble(String input) {
		if (input == null || "".equals(input) || "null".equals(input)) {
			// myLogger.debug("input:"+input);
			return Double.parseDouble("-1");
		} else
			return Double.parseDouble(input);
	}

	public static boolean checkIsAlive(String host) {
		InetSocketAddress address;
		boolean result;
		try {
			address = new InetSocketAddress(InetAddress.getByName(host), 80);
			System.out.println(address.toString());
			result = true;
		} catch (IOException x) {
			System.out.println(x);
			result = false;
		}
		return result;
	}

	public static boolean isFromOutside(String url) {
		if ("www.ekptg.gov.my".equals(url) || "ekptg.gov.my".equals(url)) {
			return true;
		} else {
			return false;
		}
	}

	public static String getFileNameWithoutExtenstion(String file) {
		int index = file.lastIndexOf('.');
		return file.substring(0, index);
	}

	public static String subString(String input, int Start, int Stop) {
		// $EkptgUtil.subString("hello world from azam",0,5)
		if (input == null || "".equals(input))
			return "";
		if (input.length() < Stop)
			return input;
		if (input.length() > Stop)
			; // Stop = input.length();
		return input.substring(Start, Stop) + "..";
	}

	public static boolean checkIfNumber(String in) {
		try {
			Integer.parseInt(in);
		} catch (NumberFormatException ex) {
			return false;
		}
		return true;
	}

	public static String formatLuas(float number) {
		try {
			return new DecimalFormat("#,###,###.00000").format(number);
		} catch (Exception localException) {
		}
		return Float.toString(number);
	}

	public static String formatLuas(Float number) {
		if (number == null)
			return "";
		try {
			return new DecimalFormat("#,###,###.00000").format(number);
		} catch (Exception localException) {
		}

		return Float.toString(number.floatValue());
	}

	public static String formatLuas(double number) {
		try {
			return new DecimalFormat("#,###,##0.00000").format(number);
		} catch (Exception localException) {
		}
		return Double.toString(number);
	}

	public static String formatLuas(Double number) {
		if (number == null)
			return "";
		try {
			return new DecimalFormat("#,###,##0.00000").format(number);
		} catch (Exception localException) {
		}
		return Double.toString(number.doubleValue());
	}

	public static String format1Decimal(Double number) {
		if (number == null)
			return "";
		try {
			return new DecimalFormat("#,###,##0.0").format(number);
		} catch (Exception localException) {
		}
		return Double.toString(number.doubleValue());
	}

	public static String format1Decimal(double number) {
		try {
			return new DecimalFormat("#,###,##0.0").format(number);
		} catch (Exception localException) {
		}
		return Double.toString(number);
	}

	public static String format2Decimal(Double number) {
		if (number == null)
			return "";
		try {
			return new DecimalFormat("#,###,##0.00").format(number);
		} catch (Exception localException) {
		}
		return Double.toString(number.doubleValue());
	}

	public static String format2Decimal(double number) {
		try {
			return new DecimalFormat("#,###,##0.00").format(number);
		} catch (Exception localException) {
		}
		return Double.toString(number);
	}

	public static String formatLuasHM(double number) {
		try {
			return new DecimalFormat("#,###,##0.0000").format(number);
		} catch (Exception localException) {
		}
		return Double.toString(number);
	}

	public static String formatLuasHM(Double number) {
		if (number == null)
			return "";
		try {
			return new DecimalFormat("#,###,##0.0000").format(number);
		} catch (Exception localException) {
		}
		return Double.toString(number.doubleValue());
	} // Portal Usage

	public static String formatAnyDecimal(Double number) {
		if (number == null)
			return "";
		try {
			return new DecimalFormat("#,###,##0.##").format(number);
		} catch (Exception localException) {
		}
		return Double.toString(number.doubleValue());
	}
	
	public static String formatNumberWithoutDecimal(int number) {
		try {
			return new DecimalFormat("#,###,###,##0").format(number);
		} catch (Exception localException) {
		}
		return Integer.toString(number);
	}

	public static String formatAnyDecimal(double number) {
		try {
			return new DecimalFormat("#,###,##0.##").format(number);
		} catch (Exception localException) {
		}
		return Double.toString(number);
	}

	public String isPage(String CurrentPage, String pageToCompare) {
		if (CurrentPage == "")
			CurrentPage = "utama";
		if (CurrentPage.equals(pageToCompare))
			return "class=current";
		else
			return "";
	}

	public boolean isFileExist(String filename) {
		java.io.File f = new java.io.File(filename);
		if (f.exists()) {
			return true;
		} else {
			return false;
		}
	}	

	public static String digitLastFormatted(String output, int digits) {
		// int digits = 4;
		output = output.trim();
		while (output.length() < digits)
			output = "0" + output;
		return output;
	}

	public String getTabID(String tab_title, String user_role) throws Exception {

		String key = "getTabID" + tab_title + user_role;
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (String) cachedObject.getObjectValue();
		} else {
			// Fetch from DB
			String output = "";
			String sql = "";
			Db db = null;
			try {
				db = new Db();
				if ("root".equals(user_role)) {
					sql = "Select TAB_ID FROM tab_user where " + "tab_title='"
							+ escapeSQL(tab_title) + "' and user_login='admin'";
				} else {
					sql = "Select TAB_ID FROM tab_template where "
							+ "tab_title='" + escapeSQL(tab_title)
							+ "' and user_login='" + user_role + "'";
				}
				// myLogger.info(sql);
				ResultSet rs = db.getStatement().executeQuery(sql);
				if (rs.next()) {
					output = rs.getString("TAB_ID");
				}
			} catch (Exception e) {
				throw new Exception("error getting tab id :" + e.getMessage());
			} finally {
				if (db != null)
					db.close();
			}
			// myLogger.debug("put "+ output +" into cache");
			myCache.put(new Element(key, output));
			return output;
		}
	}

	public boolean isSimatiMuslim(String id_simati) throws Exception {

		boolean output = false;
		String sql = "";
		Db db = null;
		try {
			db = new Db();
			sql = "Select jenis_agama from TBLPPKSimati Where id_simati='"
					+ id_simati + "' ";
			// myLogger.info(sql);
			ResultSet rs = db.getStatement().executeQuery(sql);
			if (rs.next()) {
				if ("1".equals(rs.getString("jenis_agama"))) {
					output = true;
				}

			}
		} catch (Exception e) {
			throw new Exception("error getting agama simati :" + e.getMessage());
		} finally {
			if (db != null)
				db.close();
		}
		return output;
	}

	public boolean isSimatiNonMuslim(String id_simati) throws Exception {
		boolean output = true;
		String sql = "";
		Db db = null;
		try {
			db = new Db();
			sql = "select NVL(jenis_agama,0) jenis_agama from tblppksimati where id_simati='"+ id_simati + "' ";
			// myLogger.info(sql);
			ResultSet rs = db.getStatement().executeQuery(sql);
			if (rs.next()) {
				if ("1".equals(rs.getString("jenis_agama"))) {
					output = false;
				}

			}
		} catch (Exception e) {
			throw new Exception("error getting agama simati :" + e.getMessage());
		} finally {
			if (db != null)
				db.close();
		}
		return output;
	}

	public Hashtable getCatatan(String id_permohonan) throws Exception {

		Db db = null;
		String sql = "";
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			sql = " SELECT CATATAN_MAKLUMAT_TAMBAHAN FROM TBLPPKPERMOHONAN  "
					+ " WHERE ID_PERMOHONAN = '" + id_permohonan + "'";

			ResultSet rs = stmt.executeQuery(sql);

			Hashtable h;
			h = new Hashtable();
			while (rs.next()) {
				if (rs.getString("CATATAN_MAKLUMAT_TAMBAHAN") == null) {
					h.put("CATATAN_MAKLUMAT_TAMBAHAN", "");
				} else {
					h.put("CATATAN_MAKLUMAT_TAMBAHAN",
							rs.getString("CATATAN_MAKLUMAT_TAMBAHAN"));
				}
			}
			return h;
		} finally {
			if (db != null)
				db.close();
		}
	}

	public Vector getSenaraiMaklumatTambahan(String id_permohonan)
			throws Exception {
		String key = "getSenaraiMaklumatTambahan" + id_permohonan;
		Element cachedObject = myCache.get(key);
		if (cachedObject != null) {
			return (Vector) cachedObject.getObjectValue();
		} else {
			Db db = null;
			String sql = "";
			Vector lists = null;
			try {
				db = new Db();
				lists = new Vector();
				Statement stmt = db.getStatement();
				sql = "select b.CATATAN,a.id_semakan,a.perihal,"
						+ "CASE WHEN b.id_semakan IS NULL THEN '0' "
						+ "ELSE '1' "
						+ "END AS flag from tblsemakan a left outer join TBLPPKSEMAKANTAMBAHAN b "
						+ "on a.id_semakan = b.id_semakan and b.id_permohonan='"
						+ id_permohonan + "' "
						+ "where a.id_parent=121 order by a.id_semakan ";

				myLogger.info("SQL maklumat tambahan :" + sql.toUpperCase());

				ResultSet rs = stmt.executeQuery(sql);
				Hashtable h;
				int bil = 1;

				while (rs.next()) {
					h = new Hashtable();
					h.put("id_semakan", rs.getString("id_semakan"));
					h.put("perihal", rs.getString("perihal"));
					h.put("flag", rs.getString("flag"));
					h.put("catatan",
							rs.getString("CATATAN") == null ? "" : rs
									.getString("CATATAN"));
					lists.addElement(h);
					bil++;
				}
				// return list;
			} finally {
				if (db != null)
					db.close();
			}
			myCache.put(new Element(key, lists));
			return lists;
		}
	}

	public static long wordcount(String line) {
		long numWords = 0;
		int index = 0;
		boolean prevWhiteSpace = true;
		while (index < line.length()) {
			char c = line.charAt(index++);
			boolean currWhiteSpace = Character.isWhitespace(c);
			if (prevWhiteSpace && !currWhiteSpace) {
				numWords++;
			}
			prevWhiteSpace = currWhiteSpace;
		}
		return numWords;
	}

	public static String PrintSpace(String input, int length) {
		String space = "";
		for (int x = 0; x < (length - input.length()); x++) {
			space = space + " ";
		}
		return input + space;
	}

	public static String getIpAddressByHost(String hostname) {
		try {
			java.net.InetAddress inetAdd = java.net.InetAddress
					.getByName(hostname);
			return inetAdd.getHostAddress();
		} catch (java.net.UnknownHostException uhe) {
			return "-";
		}

	}

	/* remove leading whitespace */
	public static String ltrim(String source) {
		return source.replaceAll("^\\s+", "");
	}

	/* remove trailing whitespace */
	public static String rtrim(String source) {
		return source.replaceAll("\\s+$", "");
	}

	/* replace multiple whitespaces between words with single blank */
	public static String itrim(String source) {
		return source.replaceAll("\\b\\s{2,}\\b", " ");
	}

	/* remove all superfluous whitespaces in source string */
	public static String trim(String source) {
		return itrim(ltrim(rtrim(source)));
	}

	public static String lrtrim(String source) {
		return ltrim(rtrim(source));
	}

	public static String NiceStateName(String txt) {
		if (txt == null)
			return "";
		return txt.replace("WILAYAH PERSEKUTUAN ", "");
	}

	public static void main(String args[]) throws Exception {
		// Utils x = new Utils();
		// System.out.println(x.PrintSpace("test",10));
		// String xx = x.getTabID("'My Info'","adminppk");
		// System.out.println(wordcount("ajae testing hdhsad    adsadh  dsad"));

		// Map x = new HashMap();
		// x.put("Test",3);
		//
		// if (x.get("Test").equals(3)) {
		// myLogger.info("ok = 3");
		// } else {
		// myLogger.info("takok");
		// }
		// myLogger.debug(x.get("Test").getClass());
		// Integer y = x.get("Test");
		// myLogger.info("y="+y);

		// myLogger.debug(NiceStateName("WILAYAH PERSEKUTUAN PUTRAJAYA"));
		double d = 1234567891234567.0;
		long x = (long) d;
		// myLogger.debug(d);
		// myLogger.debug(x);

	}

	
}
