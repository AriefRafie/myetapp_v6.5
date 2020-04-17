package ekptg.fpx;

import java.util.Calendar;
import java.util.ResourceBundle;

public class FpxProperty {
	private String host;
	private String port;
	private final String BASENAME = "fpxgateway";
	private volatile static FpxProperty singleton;
	private ResourceBundle rb = null;
	private String url_1;
	private String url_2;
	private String adminName;
	private String adminPhone;
	private String adminMel;

	private FpxProperty() {
		rb = ResourceBundle.getBundle(BASENAME);
	}

	public static FpxProperty getInstance() {
		if (singleton == null) {
			synchronized (FpxProperty.class) {
				if (singleton == null) {
					singleton = new FpxProperty();
				}
			}
		}
		return singleton;
	}

	public String getHost() {
		host = rb.getString("plugin_host");
		if (host == null)
			host = "127.0.0.1";
		return host;
	}

	public String getPort() {
		port = rb.getString("plugin_port");
		if (port == null)
			port = "6666";
		return port;
	}

	public String getUrl_1() {
		url_1 = rb.getString("plugin_url_1");
		return url_1;
	}

	public String getUrl_2() {
		url_2 = rb.getString("plugin_url_2");
		return url_2;
	}

	public String getAdminName() {
		adminName = rb.getString("admin_name");
		return adminName;
	}

	public String getAdminPhone() {
		adminPhone = rb.getString("admin_phone");
		return adminPhone;
	}

	public String getAdminMel() {
		adminMel = rb.getString("admin_emel");
		return adminMel;
	}

	public String getTrasactionNo() {
		Calendar cal = Calendar.getInstance();

		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);

		return String.valueOf(year) + String.valueOf(month)
				+ String.valueOf(day) + String.valueOf(hour)
				+ String.valueOf(minute) + "-";
	}

}
