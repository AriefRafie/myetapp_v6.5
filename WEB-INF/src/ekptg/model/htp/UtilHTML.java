package ekptg.model.htp;

import java.util.Hashtable;
import java.util.Vector;

import lebah.portal.db.PrepareModule;

import org.apache.log4j.Logger;

import ekptg.helpers.DB;
import ekptg.model.entities.Tblrujagensi;
import ekptg.model.entities.Tblrujdaerah;
import ekptg.model.entities.Tblrujjenishakmilik;
import ekptg.model.entities.Tblrujjenistanah;
import ekptg.model.entities.Tblrujkementerian;
import ekptg.model.entities.Tblrujnegeri;
import ekptg.model.entities.Tblrujstatus;
import ekptg.model.entities.Tblrujsubkategori;
import ekptg.model.entities.Tblrujsuburusan;
import ekptg.model.entities.Tblrujurusan;
import ekptg.model.htp.cukai.CukaiBean;
import ekptg.model.htp.cukai.ICukai;
import ekptg.model.utils.FrmMappingUserPegawaiData;

public class UtilHTML {

	static Logger myLog = Logger.getLogger(ekptg.model.htp.UtilHTML.class);
	private ICukai iCukai = null;
	
	public static String SelectNegeri(String selectName) throws Exception {
		return SelectNegeri(selectName, null, null, null,null);
	}

	public static String SelectNegeri(String selectName, String jsFunction,String namaTable)
			throws Exception {
		return SelectNegeri(selectName, null, null, jsFunction,namaTable);
	}
    
	public static String SelectNegeri(String selectName, Long selectedValue)
			throws Exception {
		return SelectNegeri(selectName, selectedValue, null, null,null);
	}

	public static String SelectNegeri(String selectName, Long selectedValue,
			String disability) throws Exception {
		return SelectNegeri(selectName, selectedValue, disability, null,null);
	}

	public static String SelectNegeri(String selectName, Long selectedValue,
			String disability,String namaTable) throws Exception {
		return SelectNegeri(selectName, selectedValue, disability, null,namaTable);
	}
	
	public static String SelectNegeri(String selectName, Long selectedValue,
			String disability, String jsFunction, String namaTable ) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=\"\">SILA PILIH NEGERI</option>\n");
			Vector<?> v = FrmUtilData.getNegeriDistinct(namaTable);
			Tblrujnegeri f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujnegeri) v.get(i);
				if (f.getIdNegeri().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdNegeri() + ">"
						+ f.getKodMampu() + " - " + f.getNamaNegeri()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
		
	}	
	//**SelectBaucer by arif */
	public static String SelectBaucer(int idPeringkatbayaran,String selectName,
			Long selectedValue, String disability, String jsFunction)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=\"\">SILA PILIH</option>\n");
			if (jsFunction != null)
				sb.append(jsFunction);
			Vector<?> v = FrmUtilData.getListBaucer(idPeringkatbayaran);  
			String s = "";  
			for (int i = 0; i < v.size(); i++) {  
				Hashtable<?, ?> h = (Hashtable<?, ?>)v.get(i);  
				if (h.get("idBaucer").equals(selectedValue)) {  
					s = "selected";  
				}else {  
					s = "";  
				}  
				sb.append("<option " + s + " value=" + h.get("idBaucer")+ ">"  
						+ h.get("namaDaerah") 
						//+ " - RM" + h.get("amaunBaucer")  
						+ "</option>\n");  
				}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
		
	}
	
	public static String SelectBaucer(String idPeringkatbayaran,String selectName,
			Long selectedValue, String disability, String jsFunction)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=\"\">SILA PILIH</option>\n");
			if (jsFunction != null)
				sb.append(jsFunction);
			Vector<?> v = FrmUtilData.getListBaucer(idPeringkatbayaran);  
			String s = "";  
			for (int i = 0; i < v.size(); i++) {  
				Hashtable<?, ?> h = (Hashtable<?, ?>)v.get(i);  
				if (h.get("idBaucer").equals(selectedValue)) {  
					s = "selected";  
				}else {  
					s = "";  
				}  
				sb.append("<option " + s + " value=" + h.get("idBaucer")+ ">"  
						+ h.get("namaDaerah") 
						//+ " - RM" + h.get("amaunBaucer")  
						+ "</option>\n");  
				}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
		
	}

	public String selectBaucer(String peringkatBayar,String idPeringkatbayaran,String selectName,
			Long selectedValue, String disability, String jsFunction)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=\"\">SILA PILIH</option>\n");
			if (jsFunction != null)
				sb.append(jsFunction);
			Vector<?> v = FrmUtilData.getListBaucer(peringkatBayar,idPeringkatbayaran);  
			String s = "";  
			for (int i = 0; i < v.size(); i++) {  
				Hashtable<?, ?> h = (Hashtable<?, ?>)v.get(i);  
				if (h.get("idBaucer").equals(selectedValue)) {  
					s = "selected";  
				}else {  
					s = "";  
				}  
				sb.append("<option " + s + " value=" + h.get("idBaucer")+ ">"  
						+ h.get("namaDaerah") 
						//+ " - RM" + h.get("amaunBaucer")  
						+ "</option>\n");  
				}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	}	
	
	public static String SelectGroup(String selectName, String selectedValue, String disability ) 
		throws Exception {
		return SelectGroup(selectName, selectedValue, disability, null);
	}

	public static String SelectGroup(String selectName, String selectedValue,
		String disability, String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=\"-1\">SILA PILIH</option>\n");
	
			Vector<Object> v = new Vector<Object>();
			v = lebah.portal.RenameGroupModule.getGroupNameList();
			String f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				//Vector vv = (Vector)v.get(i);
				f = ""+v.get(i);
				if (f.equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f + ">"
						+f
						+ "</option>\n");
			}
			sb.append("</select>");
			
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
		
	}

	public static String SelectModules(String selectName, String selectedValue, String disability, String groupName ) 
		throws Exception {
		return SelectModules(selectName, selectedValue, disability, null, groupName);
	}

	public static String SelectModules(String selectName, String selectedValue,
		String disability, String jsFunction,String groupName) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=\"-1\">SILA PILIH</option>\n");
			
			Vector<Object> moduleList1 = new Vector<Object>();
			moduleList1 = PrepareModule.getListOfModules();
		 
			//String f = null;
			String s = "";
			for (int i = 0; i < moduleList1.size(); i++) {
				lebah.portal.element.Module module = (lebah.portal.element.Module)moduleList1.get(i);
				if(groupName != null){
					if(groupName.equals(module.getGroupName())){
						if (module.getId().equals(selectedValue)) {
							s = "selected";
						} else {
							s = "";
						}
						sb.append("<option " + s + " value=" + module.getId() + ">"
								+module.getGroupName()+"==>"+module.getTitle()
								+ "</option>\n");
					}
				}else{
					if (module.getId().equals(selectedValue)) {
						s = "selected";
					} else {
						s = "";
					}
					sb.append("<option " + s + " value=" + module.getId() + ">"
							+module.getGroupName()+"==>"+module.getTitle()
							+ "</option>\n");
				}
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}		
		return sb.toString();
		
	}

	public static String SelectStatus(String selectName,
			Long selectedValue, String disability) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=\"\">SILA PILIH</option>\n");
			Vector<?> v = FrmUtilData.getStatusDistinct();
			Tblrujstatus f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujstatus) v.get(i);
				if (f.getIdStatus().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdStatus() + ">"
						+ f.getKeterangan() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}	
		return sb.toString();
		
	}
	
	public static String selectStatusByModule(String selectName,
		Long selectedValue, String disability, String idModule) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=''>SILA PILIH</option>\n");
			Vector<?> v = FrmUtilData.getStatusByUrusan(idModule);
			Tblrujstatus f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujstatus) v.get(i);
				if (f.getIdStatus().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdStatus() + ">"
						+ f.getKeterangan() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}	
		return sb.toString();
		
	}	
	//**SelectDaerahByCukai by arif */
	public static String SelectDaerahByCukai(int idPeringkatbayaran,String selectName,
			Long selectedValue, String disability, String jsFunction)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=\"-1\">SILA PILIH DAERAH</option>\n");
			if (jsFunction != null)
				sb.append(jsFunction);
			Vector<?> v = FrmUtilData.getListDaerah(idPeringkatbayaran);  
			String s = "";  
			for (int i = 0; i < v.size(); i++) {  
				Hashtable<?, ?> h = (Hashtable<?, ?>)v.get(i);  
				if (h.get("idDaerah").equals(selectedValue)) {  
					s = "selected";  
				}else {  
					s = "";  
				}  
				sb.append("<option " + s + " value=" + h.get("idDaerah")+ ">" 
						+ h.get("namaDaerah") 
						+ "</option>\n");  
				}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
		
	}

	public String SelectDaerahByCukai(String idPeringkatBayaran,String selectName,
			Long selectedValue, String disability, String jsFunction)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=\"\">SILA PILIH</option>\n");
			if (jsFunction != null)
				sb.append(jsFunction);
			//Vector<?> v = FrmUtilData.getListDaerah(idPeringkatbayaran);  
			Vector<Hashtable<String,String>> v = getICukai().getListDaerah(idPeringkatBayaran);  
			String s = "";  
			for (int i = 0; i < v.size(); i++) {  
				Hashtable<String,String> h = (Hashtable<String,String>)v.get(i);  
				if (h.get("idDaerah").equals(selectedValue)) {  
					s = "selected";  
				}else {  
					s = "";  
				}  
				sb.append("<option " + s + " value=" + h.get("idDaerah")+ ">" 
						+ h.get("namaDaerah") 
						+ "</option>\n");  
				}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	
	}
	
	public String SelectDaerahByCukai(String tahun,String idPeringkatBayaran,String selectName,
			Long selectedValue, String disability, String jsFunction)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=\"\">SILA PILIH</option>\n");
			if (jsFunction != null)
				sb.append(jsFunction);
			Vector<?> v = getICukai().getListDaerah(idPeringkatBayaran,tahun);  
			String s = "";  
			for (int i = 0; i < v.size(); i++) {  
				Hashtable<?, ?> h = (Hashtable<?, ?>)v.get(i);  
				if (h.get("idDaerah").equals(selectedValue)) {  
					s = "selected";  
				}else {  
					s = "";  
				}  
				sb.append("<option " + s + " value=" + h.get("idDaerah")+ ">" 
						+ h.get("namaDaerah") 
						+ "</option>\n");  
				}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	
	}
	
	public String SelectNegeriByCukai(String idPeringkatbayaran,String selectName,
			Long selectedValue, String disability, String jsFunction)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=\"\">SILA PILIH</option>\n");
			if (jsFunction != null)
				sb.append(jsFunction);
			Vector<?> v = getICukai().getListNegeri(idPeringkatbayaran);
			String s = "";  
			for (int i = 0; i < v.size(); i++) {  
				Hashtable<?, ?> h = (Hashtable<?, ?>)v.get(i);  
				if (h.get("idNegeri").equals(selectedValue)) {  
					s = "selected";  
				}else {  
					s = "";  
				}  
				sb.append("<option " + s + " value=" + h.get("idNegeri")+ ">" 
						+ h.get("nama") 
						+ "</option>\n");  
				}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
		
	}
	
	public String SelectNegeriByCukai(String tahun,String idPeringkatbayaran,String selectName,
			Long selectedValue, String disability, String jsFunction)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=\"\">SILA PILIH</option>\n");
			if (jsFunction != null)
				sb.append(jsFunction);
			Vector<?> v = getICukai().getListNegeri(idPeringkatbayaran,tahun);
			String s = "";  
			for (int i = 0; i < v.size(); i++) {  
				Hashtable<?, ?> h = (Hashtable<?, ?>)v.get(i);  
				if (h.get("idNegeri").equals(selectedValue)) {  
					s = "selected";  
				}else {  
					s = "";  
				}  
				sb.append("<option " + s + " value=" + h.get("idNegeri")+ ">" 
						+ h.get("nama") 
						+ "</option>\n");  
				}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	
	}	
	
	public static String SelectNegeriXKod(String selectName) throws Exception {
		return SelectNegeri(selectName, null, null, null,null);
	}

	public static String SelectNegeriXKod(String selectName, String jsFunction,String namaTable)
		throws Exception {
		return SelectNegeriXKod(selectName, null, null, jsFunction,namaTable);
	}
    
	public static String SelectNegeriXKod(String selectName, Long selectedValue)
		throws Exception {
		return SelectNegeriXKod(selectName, selectedValue, null, null,null);
	}

	public static String SelectNegeriXKod(String selectName, Long selectedValue,
		String disability) throws Exception {
		return SelectNegeriXKod(selectName, selectedValue, disability, null,null);
	}

	public static String SelectNegeriXKod(String selectName, Long selectedValue,
		String disability,String namaTable) throws Exception {
		return SelectNegeriXKod(selectName, selectedValue, disability, null,namaTable);
	}
	
	public static String SelectNegeriXKod(String selectName, Long selectedValue,
		String disability, String jsFunction, String namaTable ) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=\"-1\">SILA PILIH</option>\n");
			Vector<?> v = FrmUtilData.getNegeriDistinct(namaTable);
			Tblrujnegeri f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujnegeri) v.get(i);
				if (f.getIdNegeri().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdNegeri() + ">"
						+ f.getNamaNegeri()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	
	}
	
	public static String selectNegeriLaporan(String selectName) throws Exception {
		return selectNegeriLaporan(selectName, null, null, null,null);
	}

	public static String selectNegeriLaporan(String selectName, String jsFunction,String namaTable)
		throws Exception {
		return selectNegeriLaporan(selectName, null, null, jsFunction,namaTable);
	}
    
	public static String selectNegeriLaporan(String selectName, Long selectedValue)
		throws Exception {
		return selectNegeriLaporan(selectName, selectedValue, null, null,null);
	}

	public static String selectNegeriLaporan(String selectName, Long selectedValue,
		String disability) throws Exception {
		return selectNegeriLaporan(selectName, selectedValue, disability, null,null);
	}

	public static String selectNegeriLaporan(String selectName, Long selectedValue,
		String disability,String namaTable) throws Exception {
		return selectNegeriLaporan(selectName, selectedValue, disability, null,namaTable);
	}
	
	public static String selectNegeriLaporan(String selectName, Long selectedValue,
		String disability, String jsFunction, String namaTable ) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			String s_ = "";
			if(String.valueOf(selectedValue).equals("0"))
				s_ = "selected";

			sb.append("<option value=\"-1\">SILA PILIH</option>\n");
			sb.append("<option " + s_ + "  value=\"0\">SEMUA NEGERI</option>\n");

			Vector<?> v = FrmUtilData.getNegeriDistinct(namaTable);
			Tblrujnegeri f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujnegeri) v.get(i);
				if (f.getIdNegeri().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdNegeri() + ">"
						+ f.getNamaNegeri()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	
	}
	
	public static String SelectDaerahByUnitPPKXKod(String selectName, Long selectedValue, String disability, String jsFunction, String idPejabatJKPTG) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' ");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(">");
			sb.append("<option value=\"-1\">SILA PILIH</option>\n");
			Vector<?> v = DB.getDaerahByUnitPPK(idPejabatJKPTG);
			Hashtable<?, ?> h = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = (Hashtable<?, ?>) v.get(i);
				if (h.get("id").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.get("id") + ">"
						+ h.get("keterangan") + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}		
		return sb.toString();
	
	}
	// ** Daerah mengikut negeri */
	public static String SelectDaerahByNegeri(String idnegeri, String selectName)
		throws Exception {
		return SelectDaerahByNegeri(idnegeri, selectName, null, null, null);
	}

	public static String SelectDaerahByNegeri(String idnegeri,
		String selectName, String jsFunction) throws Exception {
		return SelectDaerahByNegeri(idnegeri, selectName, null, null,jsFunction);
	}

	public static String SelectDaerahByNegeri(String idnegeri,
		String selectName, Long selectedValue, String disability)
		throws Exception {
		return SelectDaerahByNegeri(idnegeri, selectName, selectedValue,disability, null);
	}

	public static String SelectDaerahByNegeri(String idnegeri,
			String selectName, Long selectedValue, String disability,
			String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=\"\">SILA PILIH</option>\n");
			Vector<?> v = FrmUtilData.getDaerahByNegeri(idnegeri);
			Tblrujdaerah f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujdaerah) v.get(i);
				if (f.getIdDaerah().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdDaerah() + ">"
						+ f.getNamaDaerah()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();

	}
	// ** Daerah mengikut negeri untuk Laporan*/
	public static String SelectDaerahLaporan(String idnegeri, String selectName)
		throws Exception {
		return SelectDaerahLaporan(idnegeri, selectName, null, null, null);
	}

	public static String SelectDaerahLaporan(String idnegeri,
		String selectName, String jsFunction) throws Exception {
		return SelectDaerahLaporan(idnegeri, selectName, null, null,jsFunction);
	}

	public static String SelectDaerahLaporan(String idnegeri,
		String selectName, Long selectedValue, String disability)
		throws Exception {
		return SelectDaerahLaporan(idnegeri, selectName, selectedValue,disability, null);
	}

	public static String SelectDaerahLaporan(String idnegeri,
			String selectName, Long selectedValue, String disability,
			String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			String s_ = "";
			if(String.valueOf(selectedValue).equals("0"))
				s_ = "selected";

			sb.append("<option value=\"-1\">SILA PILIH</option>\n");
			sb.append("<option " + s_ + "  value=\"0\">SEMUA DAERAH</option>\n");

			Vector<?> v = FrmUtilData.getDaerahByNegeri(idnegeri);
			Tblrujdaerah f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujdaerah) v.get(i);
				if (f.getIdDaerah().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdDaerah() + ">"
						+ f.getNamaDaerah()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();

	}
	
	public static String SelectDaerah(String selectName, Long selectedValue,
			String disability) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=\"\">SILA PILIH</option>\n");
			Vector<?> v = FrmUtilData.getDaerahByNegeri(null);
			Tblrujdaerah f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujdaerah) v.get(i);
				if (f.getIdDaerah().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdDaerah() + ">"
						+ f.getNamaDaerah()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();

	}	

	public static String SelectDaerahByNegeriSarawak(String idnegeri, String selectName)
		throws Exception {
		return SelectDaerahByNegeriSarawak(idnegeri, selectName, null, null, null);
	}

	public static String SelectDaerahByNegeriSarawak(String idnegeri,
		String selectName, String jsFunction) throws Exception {
		return SelectDaerahByNegeriSarawak(idnegeri, selectName, null, null,jsFunction);
	}

	public static String SelectDaerahByNegeriSarawak(String idnegeri,
		String selectName, Long selectedValue, String disability)
		throws Exception {
		return SelectDaerahByNegeriSarawak(idnegeri, selectName, selectedValue,disability, null);
	}
	
	public static String SelectDaerahByNegeriSarawak(String idnegeri,
			String selectName, Long selectedValue, String disability,
			String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=\"\">SILA PILIH</option>\n");
			Vector<?> v = FrmUtilData.getDaerahByNegeri(idnegeri);
			Tblrujdaerah f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujdaerah) v.get(i);
				if (f.getIdDaerah().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdDaerah() + ">"
						+ f.getNamaDaerah()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();

	}
	/**
	 * Created on 2009/12/14
	 * By Mohamad Rosli
	 * Display pegawai melalui selection
	 * */
	public static String SelectUsersByNegeri(String selectName, Long selectedValue,
			String disability, String idNegeri) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=\"0\">SILA PILIH</option>\n");
			Vector<?> v = FrmMappingUserPegawaiData.getSenaraiUsersByNegeri(idNegeri);
			Hashtable<?, ?> f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Hashtable<?, ?>) v.get(i);
				if (f.get("userid").equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.get("userid") + ">"
						+ f.get("username")
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();

	}	
	/**
	 * Created/Modified on 2009/12/22 by Mohamad Rosli
	 * Menghasilkan senarai SubUrusan dalam bentuk pilihan(Selection)
	 * @param idUrusan
	 * @param selectName
	 * @param selectedValue
	 * @param disability
	 * @param jsFunction
	 * @return String
	 * @throws Exception
	 */
	public static String SelectSuburusanByLogin(String login,
			String selectName, Long selectedValue, String disability,String jsFunction)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");

			sb.append("<option value=\"0\">Sila Pilih </option>\n");
			Vector<?> v = FrmUtilData.getSubUrusanByRole(login);
			Tblrujsuburusan f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujsuburusan) v.get(i);
				if (f.getIdSuburusan().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdSuburusan() + ">"
						+ f.getKodSuburusan() + " - " + f.getNamaSuburusan()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
		
	}
	/**
	 * Created/Modified on 2009/02/23 by Nor Hidayah
	 * Menghasilkan senarai SubUrusan Perletakhakan dalam bentuk pilihan(Selection)
	 * @param idUrusan
	 * @param selectName
	 * @param selectedValue
	 * @param disability
	 * @param jsFunction
	 * @return String
	 * @throws Exception
	 */
	//perletakhakan suburusan
	public static String SelectSuburusanPerletakhakan(
		String selectName, Long selectedValue, String idUrusan, String disability, String jsFunction){
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' ");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(">");
	
			sb.append("<option value=\"-1\">SILA PILIH</option>\n");
			Vector<?> v = DB.getSubUrusanByUrusan(idUrusan);
			Tblrujsuburusan h;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				h = new Tblrujsuburusan();
				h = (Tblrujsuburusan) v.get(i);
				if (h.getIdSuburusan().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + h.getIdSuburusan() + ">"
						+ h.getKodSuburusan() + " - " + h.getNamaSuburusan() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			
		}	
		return sb.toString();
		
	}	
	//selectsubkategori
	public static String SelectSubKategori(String selectName, Long selectedValue,
		String disability) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "' " + disability + "> ");
			sb.append("<option value=\"-1\">SILA PILIH</option>\n");
			Vector<?> v = FrmUtilData.getSubkategori();
			Tblrujsubkategori f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujsubkategori) v.get(i);
				if (f.getIdSubkategori().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdSubkategori() + ">"
						+ f.getKodSubkategori() + " - " + f.getKeterangan()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
		
	}

	public static String SelectUrusan(String selectName) throws Exception {
		return SelectUrusan(selectName, null, null);
	}

	public static String SelectUrusan(String selectName, Long selectedValue, String disability) 
		throws Exception {
		return SelectUrusan(selectName, selectedValue, disability, null);
	}

	public static String SelectUrusan(String selectName, Long selectedValue,
		String disability, String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {

			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");

			sb.append("<option value=\"\">SILA PILIH</option>\n");
			Vector<Tblrujurusan> v = (Vector<Tblrujurusan>)FrmUtilData.getUrusanPermohonanPerizapan("");
			Tblrujurusan f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujurusan) v.get(i);
				if (f.getIdUrusan().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdUrusan() + ">"
						+ f.getKodUrusan() + " - " + f.getNamaUrusan()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
		
	}
	/** 14/06/2010
	 Jenis HakMilik */
	public static String SelectJenisHakmilikMengikutNegeri(String selectName,String idNegeri)
		throws Exception {
		return SelectJenisHakmilikMengikutNegeri(selectName,idNegeri,null, null, null);
	}

	public static String SelectJenisHakmilikMengikutNegeri(
		String selectName,String idNegeri, Long selectedValue,String disability) throws Exception {
		return SelectJenisHakmilikMengikutNegeri(selectName,idNegeri,selectedValue, disability, null);
	}

	public static String SelectJenisHakmilikMengikutNegeri(String selectName,String idNegeri,
			Long selectedValue, String disability, String jsFunction) throws Exception{
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=\"\">SILA PILIH</option>\n");

			Vector<?> v = FrmUtilData.getJenisHakmilikMengikutNegeri(idNegeri);
			Tblrujjenishakmilik f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujjenishakmilik) v.get(i);
				if (f.getIdJenishakmilik().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdJenishakmilik()
						+ ">" + f.getKodJenisHakmilik() + " - "
						+ f.getKeterangan() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
		
	}
	
	/** 10/08/2010
		Jenis HakMilik */
	public String PilihJenisHakmilikMengikutNegeri(String selectName,String idNegeri)
		throws Exception {
		return PilihJenisHakmilikMengikutNegeri(selectName,idNegeri,null, null, null);
	}

	public String PilihJenisHakmilikMengikutNegeri(
		String selectName,String idNegeri, Long selectedValue,String disability) 
		throws Exception {
		return PilihJenisHakmilikMengikutNegeri(selectName,idNegeri,selectedValue, disability, null);
	}

	public String PilihJenisHakmilikMengikutNegeri(String selectName,String idNegeri,
		Long selectedValue, String disability, String jsFunction) throws Exception{
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=\"\">SILA PILIH</option>\n");

			Vector<?> v = FrmUtilData.getJenisHakmilikMengikutNegeri(idNegeri);
			Tblrujjenishakmilik f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujjenishakmilik) v.get(i);
				if (f.getIdJenishakmilik().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdJenishakmilik()
						+ ">" + f.getKodJenisHakmilik() + " - "
						+ f.getKeterangan() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
		
	}	
	/**
	 * Created/Modified on 6/07/2010 by Mohamad Rosli
	 * @param idUrusan
	 * @param selectName
	 * @param selectedValue
	 * @param disability
	 * @return
	 * @throws Exception
	 */
	public static String SelectSuburusanByIdUrusan(String idUrusan,String idSuburusan,String selectName, 
		Long selectedValue, String disability) throws Exception {
		return SelectSuburusanByIdUrusan(idUrusan,idSuburusan,selectName,selectedValue, disability,null);
	}
	/**
	 * Created/Modified on 06/07/2010 by Mohamad Rosli
	 * @param idUrusan
	 * @param selectName
	 * @param selectedValue
	 * @param disability
	 * @param jsFunction
	 * @return
	 * @throws Exception
	 */
	public static String SelectSuburusanByIdUrusan(String idUrusan,String idSuburusan,
			String selectName, Long selectedValue, String disability,String jsFunction)
			throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");

			sb.append("<option value=\"-1\">SILA PILIH </option>\n");
			Vector<Tblrujsuburusan> v = FrmUtilData.getSubUrusanByUrusan(idUrusan,idSuburusan);
			Tblrujsuburusan f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujsuburusan) v.get(i);
				if (f.getIdSuburusan().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdSuburusan() + ">"
						+ f.getKodSuburusan() + " - " + f.getNamaSuburusan()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	
	}	
	/**
	 * Dibuat Oleh : Mohamad Rosli
	 * Dibuat Pada : 28/01/2017
	 * Dikemaskini Oleh : Razman 
	 * Dikemaskini Pada : 28/01/2017 
	 * @param idUrusan
	 * @param selectName
	 * @param selectedValue
	 * @param disability
	 * @return
	 * @throws Exception
	 */
	public static String selectSuburusanLaporan(
		String idUrusan,String selectName, Long selectedValue,String disability) 
		throws Exception {
		return selectSuburusanLaporan(idUrusan, selectName, selectedValue,disability, null);
	}
	/**
	 * Dibuat Oleh : Mohamad Rosli
	 * Dibuat Pada : 28/01/2017
	 * Dikemaskini Oleh : Razman 
	 * Dikemaskini Pada : 28/01/2017 
	 * @param idUrusan
	 * @param selectName
	 * @param selectedValue
	 * @param disability
	 * @param jsFunction
	 * @return
	 * @throws Exception
	 */
	public static String selectSuburusanLaporan(
		String idUrusan,String selectName, Long selectedValue,String disability,String jsFunction) 
		throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			String s_ = "";
			if(String.valueOf(selectedValue).equals("0"))
				s_ = "selected";

			sb.append("<option value=\"-1\">SILA PILIH</option>\n");
			sb.append("<option " + s_ + "  value=\"0\">SEMUA PAJAKAN</option>\n");
			Vector<?> v = DB.getSubUrusanByUrusan(idUrusan);
			Tblrujsuburusan f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujsuburusan) v.get(i);
				if (f.getIdSuburusan().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdSuburusan() + ">"
						+ f.getNamaSuburusan()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	
	}	
	/**Senarai Jenis Tanah by Mohamad Rosli */
	public static String SelectJenisTanah(String selectName,String idJenisTanah) throws Exception {
		return SelectJenisTanah(selectName, null, null, null,idJenisTanah);
	}
	
	public static String SelectJenisTanah(
		String selectName, Long selectedValue,String disability,String idJenisTanah) throws Exception {
		return SelectJenisTanah(selectName, selectedValue, disability, null,idJenisTanah);
	}

	public static String SelectJenisTanah(String selectName, Long selectedValue,
		String disability, String jsFunction,String idJenisTanah) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=\"\">SILA PILIH</option>\n");
			Vector<Tblrujjenistanah> v = FrmUtilData.getJenisTanah(idJenisTanah);
			Tblrujjenistanah f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujjenistanah) v.get(i);
				if (f.getIdJenistanah().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdJenistanah() + ">"
						+ f.getKodJenistanah() + " - " + f.getKeterangan()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
		
	}	
	// Add by rosli on 16/08/2010
	public static String SelectKementerian(String selectName, String jsFunction)
		throws Exception {
		return SelectKementerian(selectName, null, null, jsFunction);
	}
	
	public static String SelectKementerian(String selectName,
		Long selectedValue, String disability, String jsFunction)
		throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=\"\">SILA PILIH</option>\n");
			Vector<Tblrujkementerian> v = FrmUtilData.getKementerian();
			Tblrujkementerian f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujkementerian) v.get(i);
				if (f.getIdKementerian().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdKementerian()
						+ ">" + f.getKodKementerian() + " - "
						+ f.getNamaKementerian() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}	
		return sb.toString();
	
	}	
	// Add by Mohamad Rosli on 24/01/2017
	public static String selectKementerianLaporan(String selectName, String jsFunction)
		throws Exception {
		return selectKementerianLaporan(selectName, null, null, jsFunction);
	}
	
	public static String selectKementerianLaporan(String selectName,
		Long selectedValue, String disability, String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			String s_ = "";
			if(String.valueOf(selectedValue).equals("0"))
				s_ = "selected";

			sb.append("<option value=\"-1\">SILA PILIH</option>\n");
			sb.append("<option " + s_ + "  value=\"0\">SEMUA KEMENTERIAN</option>\n");

			Vector<?> v = FrmUtilData.getKementerian();
			Tblrujkementerian f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujkementerian) v.get(i);
				if (f.getIdKementerian().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdKementerian()
						+ ">" 
						+ f.getNamaKementerian() + "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
	
	}
	
	public static String SelectAgensiLaporan(String selectName,
			String idKementerian, Long selectedValue, String disability,
			String jsFunction) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			String s_ = "";
			if(String.valueOf(selectedValue).equals("0"))
				s_ = "selected";

			sb.append("<option value=\"-1\">SILA PILIH</option>\n");
			sb.append("<option " + s_ + "  value=\"0\">SEMUA AGENSI</option>\n");

			Vector<Tblrujagensi> v = DB.getAgensiByKementerian(idKementerian);
			Tblrujagensi f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujagensi) v.get(i);
				if (f.getIdAgensi().equals(selectedValue))
					s = "selected";
				else 
					s = "";
				
				sb.append("<option " + s + " value=" + f.getIdAgensi() + ">"
						+ f.getNamaAgensi()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
		
	}

	private ICukai getICukai(){
		if(iCukai==null){
			iCukai = new CukaiBean();
		}
		return iCukai;
		
	}
	
	public static String SelectUrusanHTP(
		String selectName, Long selectedValue, String disability,String idUrusan) throws Exception {
		return SelectUrusanHTP(selectName, selectedValue, disability, null,idUrusan);
	}

	public static String SelectUrusanHTP(String selectName, Long selectedValue,
		String disability, String jsFunction, String idUrusan) throws Exception {
		StringBuffer sb = new StringBuffer("");
		try {

			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");

			sb.append("<option value=\"\">SILA PILIH</option>\n");
			Vector<Tblrujurusan> v = (Vector<Tblrujurusan>)FrmUtilData.getUrusanHTP(idUrusan);
			Tblrujurusan f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujurusan) v.get(i);
				if (f.getIdUrusan().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdUrusan() + ">"
						+ f.getKodUrusan() + " - " + f.getNamaUrusan()
						+ "</option>\n");
			}
			sb.append("</select>");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();
		
	}
	// Add by rosli on 29/06/2011 Paparan kementerian yang aktif
	public static String SelectKementerianAktif(String selectName, String jsFunction)
			throws Exception {
		return SelectKementerianAktif(selectName, null, null, jsFunction);
	}
	
	public static String SelectKementerianAktif(
		String selectName,Long selectedValue, String disability, String jsFunction)
		throws Exception {
		StringBuffer sb = new StringBuffer("");
		
		try {
			sb.append("<select name='" + selectName + "'");
			if (disability != null)
				sb.append(disability);
			if (jsFunction != null)
				sb.append(jsFunction);
			sb.append(" > ");
			sb.append("<option value=\"\">SILA PILIH</option>\n");
			Vector<Tblrujkementerian> v = FrmUtilData.getKementerianAktif();
			Tblrujkementerian f = null;
			String s = "";
			for (int i = 0; i < v.size(); i++) {
				f = (Tblrujkementerian) v.get(i);
				if (f.getIdKementerian().equals(selectedValue)) {
					s = "selected";
				} else {
					s = "";
				}
				sb.append("<option " + s + " value=" + f.getIdKementerian()
						+ ">" + f.getKodKementerian() + " - "
						+ f.getNamaKementerian() + "</option>\n");
			}
			sb.append("</select>");
			
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return sb.toString();

	}

	
}