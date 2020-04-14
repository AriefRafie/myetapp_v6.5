package integrasi.rest.etanah.wpkl;

import integrasi.rest.etanah.wpkl.entities.Hakmilik;
import integrasi.rest.etanah.wpkl.entities.ParamForm;
import integrasi.rest.etanah.wpkl.entities.Perintah;
import integrasi.rest.etanah.wpkl.entities.PermohonanDForm;
import integrasi.rest.etanah.wpkl.entities.PermohonanKForm;
import integrasi.rest.etanah.wpkl.entities.PermohonanPDForm;
import integrasi.rest.etanah.wpkl.entities.ResponseForm;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FilenameUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class RESTInvoker {
	//url sit
	private final static String urlMaklumatHakmilik = "http://komkl1.ptgwp.gov.my/etanah-integration/mvc/myetapp/maklumat-hakmilik";
	private final static String urlMaklumatPerintah = "http://komkl1.ptgwp.gov.my/etanah-integration/mvc/myetapp/maklumat-perintah";
	private final static String urlUploadFile = "http://komkl1.ptgwp.gov.my/etanah-integration/mvc/myetapp/upload-file";
	private final static String urlMaklumatBorang = "http://komkl1.ptgwp.gov.my/etanah-integration/mvc/myetapp/maklumat-borang";
	
	//url live
//	private final static String urlMaklumatHakmilik = "http://komkl.ptgwp.gov.my/etanah-integration/mvc/myetapp/maklumat-hakmilik";
//	private final static String urlMaklumatPerintah = "http://komkl.ptgwp.gov.my/etanah-integration/mvc/myetapp/maklumat-perintah";
//	private final static String urlUploadFile = "http://komkl.ptgwp.gov.my/etanah-integration/mvc/myetapp/upload-file";
//	private final static String urlMaklumatBorang = "http://komkl.ptgwp.gov.my/etanah-integration/mvc/myetapp/maklumat-borang ";
	
	private final static String kodTransaksiMaklumatHakmilik = "T1";
	private final static String kodTransaksiHantarBorangD = "T2-D";
	private final static String kodTransaksiHantarBorangK = "T2-K";
	private final static String kodTransaksiHantarPenarikanBalik = "T2-TB";
	private final static String kodTransaksiHantarPerintah = "T3";	
	
//sit 	
	private final static String kodAgensi = "AGENSI0003";
	private final static String kodCawangan = "JKPTG-MyeTaPP";
	private final static String token = "4ec3aa1a6efe4208b2defd86c261b761";
	
	//live
//	private final static String kodAgensi = "AGENSI0003";
//	private final static String kodCawangan = "JKPTG-MyeTaPP";
//	private final static String token = "e5b67e01c623487ca014571a9f145e26";	

	public static Hakmilik getMaklumatHakmilik(String idPengguna, String idHakmilik, String noResit) throws JsonParseException, JsonMappingException, IOException {
		Hakmilik hakmilik = null;
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
		
		return hakmilik;
	}

	public static ResponseForm hantarPerintah(Perintah perintah, String idPengguna) throws JsonParseException, JsonMappingException, IOException {
		ResponseForm rf = null;
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
		return rf;		
	}
	
	public static ResponseForm hantarBorangD(String idPermohonan, PermohonanDForm permohonan, Calendar cal, String idPengguna) {
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
			String kodTransaksiNew = kodTransaksiHantarBorangD + "/" + idPermohonan;
			permohonan.setKodTransaksi(kodTransaksiNew);
			permohonan.setTarikhMasaMohon(tarikhMasaMohon);   
			String jsonInString = mapper.writeValueAsString(permohonan);
			System.out.println("jsonInString hantarBorangD : " + jsonInString);
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
	
	public static ResponseForm hantarBorangK(String idPermohonan, PermohonanKForm permohonan, Calendar cal, String idPengguna) {
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
			String kodTransaksiNew = kodTransaksiHantarBorangK + "/" + idPermohonan;
			permohonan.setKodTransaksi(kodTransaksiNew);
			permohonan.setTarikhMasaMohon(tarikhMasaMohon);   
			String jsonInString = mapper.writeValueAsString(permohonan);
			System.out.println("jsonInString hantarBorangK : " + jsonInString);
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
	
	public static ResponseForm hantarBorangPenarikanBalik(String idPermohonan, PermohonanPDForm permohonan, Calendar cal, String idPengguna) {
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
			String kodTransaksiNew = kodTransaksiHantarPenarikanBalik + "/" + idPermohonan;
			permohonan.setKodTransaksi(kodTransaksiNew);
			permohonan.setTarikhMasaMohon(tarikhMasaMohon);   
			String jsonInString = mapper.writeValueAsString(permohonan);
			System.out.println("jsonInString hantarBorangPenarikanBalik : " + jsonInString);
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
	
	public static String uploadFile(FileItem item) {
		String responseString = null;
		try {
			ResourceBundle rb = ResourceBundle.getBundle("file");
			String path = rb.getString("generalpath") + rb.getString("integrationpath") + "etanahkl/";
			
			File tempFile = null;
			String fileType = FilenameUtils.getExtension(item.getName());
			if ("LOCAL".equals(rb.getString("APP_DIR"))) {
				tempFile = File.createTempFile("tmp", fileType);
			} else {
				tempFile = File.createTempFile("tmp", fileType, new File(path));
			}

			item.write(tempFile);
			responseString = uploadFileToEtanah(tempFile);

		} catch (Exception ex) {
            ex.printStackTrace();
        }
		return responseString;		
	}
	
	private static String uploadFileToEtanah(File name) throws IOException {
 
		String FileUUID = "";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpPost httppost = new HttpPost(urlUploadFile);

			FileBody bin = new FileBody(name);
			HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("bin", bin).build();
			httppost.setEntity(reqEntity);
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					FileUUID = EntityUtils.toString(resEntity);
				}
				EntityUtils.consume(resEntity);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				response.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			httpclient.close();
		}
		return FileUUID;
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
