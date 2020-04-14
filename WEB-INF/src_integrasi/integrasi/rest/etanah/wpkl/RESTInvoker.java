package integrasi.rest.etanah.wpkl;

import integrasi.rest.etanah.wpkl.ppk.entities.Hakmilik;
import integrasi.rest.etanah.wpkl.ppk.entities.ParamForm;
import integrasi.rest.etanah.wpkl.ppk.entities.Perintah;
import integrasi.rest.etanah.wpkl.ppk.entities.ResponseForm;
import integrasi.rest.etanah.wpkl.ppt.entities.PermohonanForm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;


public class RESTInvoker {
	private final static String urlMaklumatHakmilik = "http://komkl1.ptgwp.gov.my/etanah-integration/mvc/myetapp/maklumat-hakmilik";
	private final static String urlMaklumatPerintah = "http://komkl1.ptgwp.gov.my/etanah-integration/mvc/myetapp/maklumat-perintah";
	private final static String urlUploadFile = "http://komkl1.ptgwp.gov.my/etanah-integration/mvc/myetapp/upload-file";
	private final static String urlMaklumatBorang = "http://komkl1.ptgwp.gov.my/etanah-integration/mvc/myetapp/maklumat-borang";
	
	private final static String kodTransaksiMaklumatHakmilik = "T1";
	private final static String kodTransaksiHantarBorangD = "T2-D";
	private final static String kodTransaksiHantarBorangK = "T2-K";
	private final static String kodTransaksiHantarPenarikanBalik = "T2-TB";
	private final static String kodTransaksiHantarPerintah = "T3";	
	
	private final static String kodAgensi = "AGENSI0003";
	private final static String kodCawangan = "JKPTG-MyeTaPP";
	private final static String token = "4ec3aa1a6efe4208b2defd86c261b761";	

	public static Hakmilik getMaklumatHakmilik(String idPengguna, String idHakmilik, String noResit) {
		Hakmilik hakmilik = null;
		try {
			Calendar cal = new GregorianCalendar();
			cal.setTime(new Date());
			String tarikhMasaMohon = String.valueOf(cal.get(Calendar.YEAR)) + "-" + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1) + "-" + new DecimalFormat("00").format(cal.get(Calendar.DATE))
					+ "T" + new DecimalFormat("00").format(cal.get(Calendar.HOUR_OF_DAY)) + ":" + new DecimalFormat("00").format(cal.get(Calendar.MINUTE)) + ":" + new DecimalFormat("00").format(cal.get(Calendar.SECOND));
			
			ObjectMapper mapper = new ObjectMapper();
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpPost request = new HttpPost(urlMaklumatHakmilik);
			request.addHeader("content-type", "application/json");
			
			ParamForm param = new ParamForm();
    		param.setKodAgensi(kodAgensi);
    		param.setKodCawangan(kodCawangan);
    		param.setToken(token);
    		param.setIdPengguna(idPengguna);
    		param.setKodTransaksi(kodTransaksiMaklumatHakmilik);
    		param.setTarikhMasaMohon(tarikhMasaMohon);    		
    		param.setIdHakmilik(idHakmilik);
    		param.setNoResit(noResit);    		
    		String jsonInString = mapper.writeValueAsString(param);
    		StringEntity params = new StringEntity(jsonInString);
    		request.setEntity(params);
    		
    		HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
            	InputStream instream = entity.getContent();
            	hakmilik = mapper.readValue(instream, Hakmilik.class);
            	instream.close();
            }
		} catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		return hakmilik;
	}

	public static ResponseForm hantarPerintah(Perintah perintah, String idPengguna) {
		ResponseForm rf = null;
		try {
			Calendar cal = new GregorianCalendar();
			cal.setTime(new Date());
			String tarikhMasaMohon = String.valueOf(cal.get(Calendar.YEAR)) + "-" + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1) + "-" + new DecimalFormat("00").format(cal.get(Calendar.DATE))
					+ "T" + new DecimalFormat("00").format(cal.get(Calendar.HOUR_OF_DAY)) + ":" + new DecimalFormat("00").format(cal.get(Calendar.MINUTE)) + ":" + new DecimalFormat("00").format(cal.get(Calendar.SECOND));
			
			ObjectMapper mapper = new ObjectMapper();
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpPost request = new HttpPost(urlMaklumatPerintah);
			request.addHeader("content-type", "application/json");
			
			perintah.setKodAgensi(kodAgensi);
			perintah.setKodCawangan(kodCawangan);
			perintah.setToken(token);
			perintah.setIdPengguna(idPengguna);
			perintah.setKodTransaksi(kodTransaksiHantarPerintah);
			perintah.setTarikhMasaMohon(tarikhMasaMohon);   
			String jsonInString = mapper.writeValueAsString(perintah);
    		StringEntity params = new StringEntity(jsonInString);
    		request.setEntity(params);
    		
    		HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
            	InputStream instream = entity.getContent();
            	rf = mapper.readValue(instream, ResponseForm.class);
            	instream.close();
            }
		} catch (Exception ex) {
            ex.printStackTrace();
        }
		return rf;		
	}
	
	public static ResponseForm hantarBorangD(PermohonanForm permohonan, Calendar cal, String idPengguna) {
		ResponseForm rf = null;
		try {
			String tarikhMasaMohon = String.valueOf(cal.get(Calendar.YEAR)) + "-" + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1) + "-" + new DecimalFormat("00").format(cal.get(Calendar.DATE))
					+ "T" + new DecimalFormat("00").format(cal.get(Calendar.HOUR_OF_DAY)) + ":" + new DecimalFormat("00").format(cal.get(Calendar.MINUTE)) + ":" + new DecimalFormat("00").format(cal.get(Calendar.SECOND));
			
			ObjectMapper mapper = new ObjectMapper();
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpPost request = new HttpPost(urlMaklumatBorang);
			request.addHeader("content-type", "application/json");
			
			permohonan.setKodAgensi(kodAgensi);
			permohonan.setKodCawangan(kodCawangan);
			permohonan.setToken(token);
			permohonan.setIdPengguna(idPengguna);
			permohonan.setKodTransaksi(kodTransaksiHantarBorangD);
			permohonan.setTarikhMasaMohon(tarikhMasaMohon);   
			String jsonInString = mapper.writeValueAsString(permohonan);
    		StringEntity params = new StringEntity(jsonInString);
    		request.setEntity(params);
    		
    		HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
            	InputStream instream = entity.getContent();
            	rf = mapper.readValue(instream, ResponseForm.class);
            	instream.close();
            }
		} catch (Exception ex) {
            ex.printStackTrace();
        }
		return rf;		
	}
	
	public static ResponseForm hantarBorangK(PermohonanForm permohonan, Calendar cal, String idPengguna) {
		ResponseForm rf = null;
		try {
			String tarikhMasaMohon = String.valueOf(cal.get(Calendar.YEAR)) + "-" + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1) + "-" + new DecimalFormat("00").format(cal.get(Calendar.DATE))
					+ "T" + new DecimalFormat("00").format(cal.get(Calendar.HOUR_OF_DAY)) + ":" + new DecimalFormat("00").format(cal.get(Calendar.MINUTE)) + ":" + new DecimalFormat("00").format(cal.get(Calendar.SECOND));
			
			ObjectMapper mapper = new ObjectMapper();
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpPost request = new HttpPost(urlMaklumatBorang);
			request.addHeader("content-type", "application/json");
			
			permohonan.setKodAgensi(kodAgensi);
			permohonan.setKodCawangan(kodCawangan);
			permohonan.setToken(token);
			permohonan.setIdPengguna(idPengguna);
			permohonan.setKodTransaksi(kodTransaksiHantarBorangK);
			permohonan.setTarikhMasaMohon(tarikhMasaMohon);   
			String jsonInString = mapper.writeValueAsString(permohonan);
    		StringEntity params = new StringEntity(jsonInString);
    		request.setEntity(params);
    		
    		HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
            	InputStream instream = entity.getContent();
            	rf = mapper.readValue(instream, ResponseForm.class);
            	instream.close();
            }
		} catch (Exception ex) {
            ex.printStackTrace();
        }
		return rf;		
	}
	
	public static ResponseForm hantarBorangPenarikanBalik(PermohonanForm permohonan, String idPengguna) {
		ResponseForm rf = null;
		try {
			Calendar cal = new GregorianCalendar();
			cal.setTime(new Date());
			String tarikhMasaMohon = String.valueOf(cal.get(Calendar.YEAR)) + "-" + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1) + "-" + new DecimalFormat("00").format(cal.get(Calendar.DATE))
					+ "T" + new DecimalFormat("00").format(cal.get(Calendar.HOUR_OF_DAY)) + ":" + new DecimalFormat("00").format(cal.get(Calendar.MINUTE)) + ":" + new DecimalFormat("00").format(cal.get(Calendar.SECOND));
			
			ObjectMapper mapper = new ObjectMapper();
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpPost request = new HttpPost(urlMaklumatBorang);
			request.addHeader("content-type", "application/json");
			
			permohonan.setKodAgensi(kodAgensi);
			permohonan.setKodCawangan(kodCawangan);
			permohonan.setToken(token);
			permohonan.setIdPengguna(idPengguna);
			permohonan.setKodTransaksi(kodTransaksiHantarPenarikanBalik);
			permohonan.setTarikhMasaMohon(tarikhMasaMohon);   
			String jsonInString = mapper.writeValueAsString(permohonan);
    		StringEntity params = new StringEntity(jsonInString);
    		request.setEntity(params);
    		
    		HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
            	InputStream instream = entity.getContent();
            	rf = mapper.readValue(instream, ResponseForm.class);
            	instream.close();
            }
		} catch (Exception ex) {
            ex.printStackTrace();
        }
		return rf;		
	}
	
	private static String convertStreamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}
