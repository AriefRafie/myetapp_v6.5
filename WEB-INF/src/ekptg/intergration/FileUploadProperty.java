package ekptg.intergration;

import java.util.ResourceBundle;

public class FileUploadProperty {
	private String aduanFolder;
	private String jawapanAduanFolder;
	private volatile static FileUploadProperty singleton;
	private ResourceBundle rb = null;
	private FileUploadProperty(){
		rb = ResourceBundle.getBundle("file");
	}
	public String getAduanFolder() {
		aduanFolder = rb.getString("ADUAN_LAMPIRAN_FOLDER");
		return aduanFolder;
	}
	public String getIntegrasiJUPEMFolder() {
		aduanFolder = rb.getString("INTEGRASI_JUPEM_FOLDER");
		return aduanFolder;
	}
	public String getJawapanAduanLampiranFolder(){
		jawapanAduanFolder = rb.getString("ADUAN_JAWAPAN_FOLDER");
		return jawapanAduanFolder;
	}
	public static FileUploadProperty getInstance(){
		if(singleton == null){
			synchronized(FileUploadProperty.class){
				if(singleton == null){
					singleton = new FileUploadProperty(); 
				}
			}
		}
		return singleton;
	}
	
}
