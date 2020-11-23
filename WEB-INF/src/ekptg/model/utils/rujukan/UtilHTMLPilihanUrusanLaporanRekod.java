package ekptg.model.utils.rujukan;

import java.util.Vector;

import ekptg.model.entities.Tblsemakan;
import ekptg.model.utils.IUtilHTMLPilihan;
//import org.apache.log4j.Logger;


public class UtilHTMLPilihanUrusanLaporanRekod implements IUtilHTMLPilihan{

	//static Logger myLog = Logger.getLogger(ekptg.model.htp.utiliti.UtilHTMLPilihanMT.class);
	
	public String Pilihan(String name) throws Exception {
		return Pilihan(name, null, null, null);		
	}

	public String Pilihan(String name, String jsFunction) throws Exception {
		return Pilihan(name, null, null, jsFunction);		
	}

	public String Pilihan(String name, String selectedValue,String disability) throws Exception {
		return Pilihan(name, selectedValue, disability, null);		
	}
	
	public String Pilihan(String name, String selectedValue,String disability, String jsFunction) 
		throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + name + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			String[] urusan = getUrusan(); 
//			#set ($list = ["BELUM ADA KEPUTUSAN","DILULUSKAN","TIDAK DILULUSKAN","DIBATALKAN/DITARIKBALIK"])
//			#set( $counter = 0 )
//				<option $!pk_ value="-1">SILA PILIH</option>
//				<!-- <option $!pk0 value="0">SEMUA STATUS</option> -->
//			#foreach ($i in $list)
//				#if ($!keputusan.status == $counter) 
//				<option selected value="0$counter">$i</option>
//				#else
//				<option value="0$counter">$i</option>
//				#end
//				#set ($counter = $counter+1)
//			#end
			sb.append("<option value=\"\">SILA PILIH</option>\n");
			//Vector<Tblsemakan> v = ekptg.model.htp.FrmSemakan.getSemakan(null,null);

			String f = null;
			String s = "";
			for (int i = 0; i < urusan.length; i++) {
				f = (String) urusan[i];
				if (String.valueOf(i).equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + i + ">"
						+ urusan[i]
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	
	}
	
	private String[] getUrusan() {
		String[] urusan = {"TANAH CEROBOH"
						,"TANAH DILUPUS"
						,"TANAH KOSONG"
						,"TANAH PAJAKAN"
						,"TANAH PELEPASAN"
						,"TANAH SEWA"
						,"TANAH TUKARGUNA"
						}; 
//		a. Tanah ceroboh  -
//		b. Tanah yang terlibat dengan sewa  -
//		c. Tanah yang telah dibuat tukarguna 
//		d. Tanah yang terlibat dengan pajakan -
//		e. Tanah yang telah dibuat pelepasan 
//		f. Tanah kosong -
//		g. Tanah yang telah dilupuskan
		return urusan;
		
	}

}