package ekptg.view.ppk;

import integrasi.utils.IntLogManager;
//import integrasi.ws.mt.MTManager;
import integrasi.ws.mt.MTManager;
import integrasi.ws.mt.MTManagerReg;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import lebah.db.Db;
import lebah.db.SQLRenderer;
import lebah.portal.velocity.VTemplate;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.velocity.Template;

//import sun.misc.BASE64Encoder;

//import com.sun.xml.internal.messaging.saaj.util.Base64;

import com.Ostermiller.util.Base64;

import ekptg.helpers.DB;
import ekptg.helpers.Utils;
import ekptg.model.ppk.FrmPrmhnnSek8KeputusanPermohonanInternalData;
import integrasi.ws.mt.MTManagerReg;

public class FrmNilaianJPPH extends VTemplate {
	/**
	 * 
	 */
	SkrinPopupUploadDokumen logic_F = null;
	private static final long serialVersionUID = 1L;
	static Logger myLogger = Logger.getLogger(FrmNilaianJPPH.class);
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public Template doTemplate() throws Exception {
		logic_F = new SkrinPopupUploadDokumen();
		MTManagerReg im = null;
		
		HttpSession session = this.request.getSession();

		String user = (String) session.getAttribute("_portal_login");
		String usid = (String) session.getAttribute("_ekptg_user_id");
		this.context.put("usid", usid);

		String vm = "";
		String submit = request.getParameter("command");
		String fFrom = request.getParameter("frmFrom") != null ? (String) request.getParameter("frmFrom") : "";
		String idPermohonanSimati = getParam("idPermohonanSimati");
		String sendI = "tiada";
		String isExistPetioner = "";
		myLogger.info("submit = "+submit);
		
		if ("borangPermohonan".equals(submit)) {
			String idhta = request.getParameter("idhta");
			
			Hashtable permohonanJPPH = getPermohonan(idPermohonanSimati, idhta);
			String noHakmilik = (String) permohonanJPPH.get("noHakmilik");
			String alamat1 = (String) permohonanJPPH.get("alamat1");
			String alamat2 = (String) permohonanJPPH.get("alamat2");
			String poskodhta = (String) permohonanJPPH.get("poskodhta");
			String baSimati = (String) permohonanJPPH.get("baSimati");
			String bbSimati = (String) permohonanJPPH.get("bbSimati");
			String noPt = (String) permohonanJPPH.get("noPt");
			String idkategori = (String) permohonanJPPH.get("idkategori");
			String luas = (String) permohonanJPPH.get("luas");
			String luasHMP = (String) permohonanJPPH.get("luasHMP");
			String idjenisPB = (String) permohonanJPPH.get("idjenisPB");
			String catatan = (String) permohonanJPPH.get("catatan");
			String tanggungan = (String) permohonanJPPH.get("tanggungan");
			String nilaihtaTM = (String) permohonanJPPH.get("nilaihtaTM");
			String nilaihtaTMA = (String) permohonanJPPH.get("nilaihtaTMA");
			String jenisHM = (String) permohonanJPPH.get("jenisHM");
			String idnegeri = (String) permohonanJPPH.get("idnegeri");
			String idMukim = (String) permohonanJPPH.get("idMukim");
			String bandarhta = (String) permohonanJPPH.get("bandarhta");
			
			context.put("noHakmilik", noHakmilik);
			context.put("alamat1", alamat1);
			context.put("alamat2", alamat2); 
			context.put("poskodhta", poskodhta);
			context.put("baSimati", baSimati);
			context.put("bbSimati", bbSimati);
			context.put("noPt", noPt);
			context.put("idkategori", idkategori);
			context.put("luas", luas);
			context.put("luasHMP", luasHMP);
			context.put("idjenisPB", idjenisPB);
			context.put("catatan", catatan);
			context.put("tanggungan", tanggungan);
			context.put("nilaihtaTM", nilaihtaTM);
			context.put("nilaihtaTMA", nilaihtaTMA);
			context.put("jenisHM", jenisHM);
			context.put("idnegeri", idnegeri);
			context.put("idMukim", idMukim);
			context.put("bandarhta", bandarhta);
			
			vm = "app/ppk/frmNilaianJPPH.jsp";
			
		}
		
else if ("hantarPermohonanPetioner".equals(submit)) {
			
			String transactionID = "";
			Calendar cal = new GregorianCalendar();
			cal.setTime(new Date());
			transactionID = Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.YEAR)), 4) + Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.MONTH) + 1), 2)
					+ Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.DATE)), 2) + Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.HOUR_OF_DAY)), 2)
					+ Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.MINUTE)), 2) + Utils.digitLastFormatted(String.valueOf(cal.get(Calendar.SECOND)), 2);


			String tarikhMati = request.getParameter("tarikhMati");
			String DDate = tarikhMati.substring(0, 10);

			String[] parts = DDate.split("-");
			String part1 = parts[0];
			String part2 = parts[1];
			String part3 = parts[2];
			DDate = part3 + "/" + part2 + "/" + part1;

			String tarikhJanaBorangB = request.getParameter("tarikhJanaBorangB");
			String BDate = "";
			if(tarikhJanaBorangB != ""){
			BDate = tarikhJanaBorangB.substring(0, 10);

			String[] Bparts = BDate.split("-");
			String Bpart1 = Bparts[0];
			String Bpart2 = Bparts[1];
			String Bpart3 = Bparts[2];
			BDate = Bpart3 + "/" + Bpart2 + "/" + Bpart1;
			}
			
//			String BDate = tarikhJanaBorangB.substring(0, 10);
//
//			String[] Bparts = BDate.split("-");
//			String Bpart1 = Bparts[0];
//			String Bpart2 = Bparts[1];
//			String Bpart3 = Bparts[2];
//			BDate = Bpart3 + "/" + Bpart2 + "/" + Bpart1;

			Db db = null;
			String sql = "";

			db = new Db();
			Statement stmt = db.getStatement();
			SQLRenderer r = new SQLRenderer();
			
			String id_Permohonan = request.getParameter("idPermohonan");
			String noFail = request.getParameter("noPetisyen");
			context.put("noPetisyen", request.getParameter("noPetisyen"));
			context.put("namaSimati", request.getParameter("namaSimati"));
			context.put("namaLainSimati", request.getParameter("namaSimatiLain"));
			context.put("MyIDSimati", request.getParameter("noKPSimatiBaru"));
			context.put("ICLamaSimati", request.getParameter("noKPSimatiLama"));
			context.put("ICLainSimati", request.getParameter("noKPSimatiLain"));
			context.put("tarikhMati", request.getParameter("tarikhMati"));
			context.put("namaPemohon", request.getParameter("namaPemohon"));
			context.put("MyIDPemohon", request.getParameter("noKPPemohon"));
			context.put("hubungan", request.getParameter("hubSimatiPemohon"));
			context.put("tarikhJanaBorangB", request.getParameter("tarikhJanaBorangB"));
			context.put("tarikhHantarBorangB", request.getParameter("tarikhHantarBorangB"));
			context.put("kodPejabat", request.getParameter("kodPejabat"));
			context.put("namaPejabat", request.getParameter("namaPejabat"));
			context.put("idnegeri", request.getParameter("idnegeri"));
			context.put("jeniskp", request.getParameter("jeniskp"));
			//context.put("namaDokumen", request.getParameter("namaDokumen"));
			context.put("docContent", request.getParameter("docContent"));
			

			if (!"".equals(fFrom)) {
				context.put("fFrom", "ya");
			} else {
				context.put("fFrom", "tidak");
			}

			String no_fail = request.getParameter("noPetisyen");
			String blueCardId = "";
			// aishah add 26072017
			String sqlGetBlueCardId = "SELECT IDKADBIRU FROM TBLINTMTPERMOHONAN WHERE FLAG_AKTIF = 'Y' AND  PETISYENNO = '" + no_fail + "'";

			ResultSet rsKadBiru = stmt.executeQuery(sqlGetBlueCardId);
			if (rsKadBiru.next()) {
				if (rsKadBiru.getString("IDKADBIRU") != null) {
					blueCardId = rsKadBiru.getString("IDKADBIRU");
				}
			}		

			// aishah start integration ecourt
			MTManager manager = new MTManager("MT");

			
		}

		Template template = this.engine.getTemplate(vm);
		return template;
			
		

		}
		
		public Hashtable<String,String> getPermohonan(String idPermohonanSimati, String idhta) {
		Db db = null;
		String sql = "";
		Hashtable<String,String> permohonanJPPH = new Hashtable<String,String>();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			db = new Db();
			Statement stmt = db.getStatement();
			
			sql = "SELECT H.FLAG_DAFTAR AS flag_daftar,"
					+ " H.ID_HTA AS idhta,"					
					+ " H.NO_HAKMILIK AS noHakmilik,"
					+ " H.ID_SIMATI AS idSimati,"
					+ " H.NO_PT AS noPt,"
					+ " H.NILAI_HTA_TARIKHMOHON AS nilaihtaTM,"
					+ " H.NILAI_HTA_TARIKHMATI AS nilaihtaTMA,"
					+ " H.ID_KATEGORI AS idkategori,"
					+ " H.ID_JENISHM AS jenisHM,"
					+ " H.ID_JENISPB AS idjenisPB,"
					+ " H.ID_NEGERI AS idnegeri,"
					+ " H.ID_DAERAH as idDaerah,"
					+ " H.ID_LUAS AS idLuas,"
					+ " H.ID_MUKIM AS idMukim,"
					+ " H.LUAS_HMP AS luasHMP,"
					+ " H.LUAS AS luas,"
					+ " H.NO_CAGARAN AS noCagaran,"
					+ " H.NO_PAJAKAN AS no_pajakan,"
					+ " H.JENIS_TNH AS jenisTNH,"
					+ " H.BA_SIMATI AS baSimati,"
					+ " H.BB_SIMATI AS bbSimati,"
					+ " H.JENIS_HTA AS jenisHta,"
					+ " H.TANGGUNGAN AS tanggungan,"
					+ " H.ALAMAT_HTA1 AS alamat1,"
					+ " H.ALAMAT_HTA2 AS alamat2,"
					+ " H.ALAMAT_HTA3 AS alamat3,"
					+ " H.BANDAR_HTA AS bandarhta,"
					+ " H.POSKOD_HTA AS poskodhta,"
					+ " H.ID_BANDARHTA AS idBandarhta,"		
					+ " H.NO_PERSERAHAN AS noPerserahan,"  
					+ " H.CATATAN AS catatan,"
					+ " H.STATUS_PEMILIKAN AS status_pemilikan,"
					+ " DOKUMEN.ID_DOKUMEN AS idDokumen,"
					+ " DOKUMEN.NAMA_DOKUMEN AS namaDokumen,"
					+ " H.SEKATAN AS sekatan,"
					+ " H.SYARAT_NYATA AS syaratNyata"
					+ " FROM TBLPPKHTA HP,"
					+ " TBLPPKHTAPERMOHONAN H,"
					+ " TBLPPKSIMATI S,"
					+ " TBLPPKUPLOADPELAN PELAN,"
					+ " TBLPPKDOKUMEN DOKUMEN"
					+ " WHERE H.ID_SIMATI = S.ID_SIMATI"
					+ " AND HP.ID_HTA = H.ID_HTA"
					+ " AND PELAN.ID_HTA (+) = H.ID_HTA"
					+ " AND DOKUMEN.ID_DOKUMEN (+)= PELAN.ID_DOKUMEN"
					+ " AND H.ID_PERMOHONANSIMATI = '"+idPermohonanSimati+"'"
					+ " AND H.JENIS_HTA = 'Y'"
					+ " AND H.ID_HTA = '"+idhta+"'";
			myLogger.info("SQL STATEMENT - NILAIAN JPPH : " + sql);

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				permohonanJPPH.put(
						"noHakmilik",
						rs.getString("noHakmilik") == null ? "" : rs
								.getString("noHakmilik"));
				permohonanJPPH.put(
						"alamat1",
						rs.getString("alamat1") == null ? "" : rs
								.getString("alamat1"));
				permohonanJPPH.put(
						"alamat2",
						rs.getString("alamat2") == null ? "" : rs
								.getString("alamat2"));
				permohonanJPPH.put(
						"poskodhta",
						rs.getString("poskodhta") == null ? "" : rs
								.getString("poskodhta"));
				permohonanJPPH.put(
						"baSimati",
						rs.getString("baSimati") == null ? "" : rs
								.getString("baSimati"));
				permohonanJPPH.put(
						"bbSimati",
						rs.getString("bbSimati") == null ? "" : rs
								.getString("bbSimati"));
				permohonanJPPH.put(
						"noPt",
						rs.getString("noPt") == null ? "" : rs
								.getString("noPt"));
				permohonanJPPH.put(
						"idkategori",
						rs.getString("idkategori") == null ? "" : rs
								.getString("idkategori"));
				permohonanJPPH.put(
						"luas",
						rs.getString("luas") == null ? "" : rs
								.getString("luas"));
				permohonanJPPH.put(
						"luasHMP",
						rs.getString("luasHMP") == null ? "" : rs
								.getString("luasHMP"));
				
				permohonanJPPH.put(
						"idjenisPB",
						rs.getString("idjenisPB") == null ? "" : rs
								.getString("idjenisPB"));
				permohonanJPPH.put(
						"catatan",
						rs.getString("catatan") == null ? "" : rs
								.getString("catatan"));
				permohonanJPPH.put(
						"tanggungan",
						rs.getString("tanggungan") == null ? "" : rs
								.getString("tanggungan"));
				permohonanJPPH.put(
						"nilaihtaTM",
						rs.getString("nilaihtaTM") == null ? "" : rs
								.getString("nilaihtaTM"));
				permohonanJPPH.put(
						"nilaihtaTMA",
						rs.getString("nilaihtaTMA") == null ? "" : rs
								.getString("nilaihtaTMA"));
				permohonanJPPH.put(
						"jenisHM",
						rs.getString("jenisHM") == null ? "" : rs
								.getString("jenisHM"));
				permohonanJPPH.put(
						"idnegeri",
						rs.getString("idnegeri") == null ? "" : rs
								.getString("idnegeri"));
				permohonanJPPH.put(
						"idMukim",
						rs.getString("idMukim") == null ? "" : rs
								.getString("idMukim"));
				permohonanJPPH.put(
						"bandarhta",
						rs.getString("bandarhta") == null ? "" : rs
								.getString("bandarhta"));
				
				permohonanJPPH.put(
						"namaPemohon",
						rs.getString("namaPemohon") == null ? "" : rs
								.getString("namaPemohon"));
				permohonanJPPH.put(
						"noKPBaruPemohon",
						rs.getString("noKPBaruPemohon") == null ? "" : rs
								.getString("noKPBaruPemohon"));
				permohonanJPPH.put(
						"noKPLamaPemohon",
						rs.getString("noKPLamaPemohon") == null ? "" : rs
								.getString("noKPLamaPemohon"));
				permohonanJPPH.put(
						"noKPLainPemohon",
						rs.getString("noKPLainPemohon") == null ? "" : rs
								.getString("noKPLainPemohon"));
				permohonanJPPH.put(
						"jenisKPPemohon",
						rs.getString("jenisKPPemohon") == null ? "" : rs
								.getString("jenisKPPemohon"));
				permohonanJPPH.put(
						"umurPemohon",
						rs.getString("umurPemohon") == null ? "" : rs
								.getString("umurPemohon"));
				permohonanJPPH.put(
						"jantinaPemohon",
						rs.getString("jantinaPemohon") == null ? "" : rs
								.getString("jantinaPemohon"));
				permohonanJPPH.put(
						"alamat1",
						rs.getString("alamat1") == null ? "" : rs
								.getString("alamat1"));
				permohonanJPPH.put(
						"alamat2",
						rs.getString("alamat2") == null ? "" : rs
								.getString("alamat2"));
				permohonanJPPH.put(
						"alamat3",
						rs.getString("alamat3") == null ? "" : rs
								.getString("alamat3"));
				permohonanJPPH.put(
						"poskod",
						rs.getString("poskod") == null ? "" : rs
								.getString("poskod"));
				permohonanJPPH.put(
						"idBandar",
						rs.getString("idBandar") == null ? "" : rs
								.getString("idBandar"));
				permohonanJPPH.put(
						"idNegeriPemohon",
						rs.getString("idNegeriPemohon") == null ? "" : rs
								.getString("idNegeriPemohon"));
				permohonanJPPH.put(
						"hubSimatiPemohon",
						rs.getString("hubSimatiPemohon") == null ? "" : rs
								.getString("hubSimatiPemohon"));
				permohonanJPPH.put(
						"tarikhJanaBorangB",
						rs.getString("tarikhJanaBorangB") == null ? "" : rs
								.getString("tarikhJanaBorangB"));
				permohonanJPPH.put(
						"kodPejabat",
						rs.getString("kodPejabat") == null ? "" : rs
								.getString("kodPejabat"));
				permohonanJPPH.put(
						"jenisTransaksi",
						rs.getString("jenisTransaksi") == null ? "" : rs
								.getString("jenisTransaksi"));
				permohonanJPPH.put(
						"ID_HUBSIMATIPEMOHON",
						rs.getString("ID_HUBSIMATIPEMOHON") == null ? "" : rs
								.getString("ID_HUBSIMATIPEMOHON"));
				permohonanJPPH.put(
						"idnegeriPemohon",
						rs.getString("idnegeriPemohon") == null ? "" : rs
								.getString("idnegeriPemohon"));
				permohonanJPPH.put(
						"WAKTU_KEMATIAN",
						rs.getString("WAKTU_KEMATIAN") == null ? "" : rs
								.getString("WAKTU_KEMATIAN"));
				permohonanJPPH.put(
						"JENIS_WAKTU_MATI",
						rs.getString("JENIS_WAKTU_MATI") == null ? "" : rs
								.getString("JENIS_WAKTU_MATI"));
				permohonanJPPH.put(
						"idPermohonan",
						rs.getString("ID_PERMOHONAN") == null ? "" : rs
								.getString("ID_PERMOHONAN"));
				permohonanJPPH.put(
						"idSimati",
						rs.getString("idSimati") == null ? "" : rs
								.getString("idSimati"));
				permohonanJPPH.put(
						"jumlahharta",
						rs.getString("jumlahharta") == null ? "" : rs
								.getString("jumlahharta"));
				
				permohonanJPPH.put(
						"namaDokumen",
						rs.getString("NAMA_DOKUMEN") == null ? "" : rs
								.getString("NAMA_DOKUMEN"));
				
				Blob  b = rs.getBlob("KANDUNGAN");
				InputStream is = b.getBinaryStream();
				 byte [] b2 = IOUtils.toByteArray(is);
					String content = Base64.encodeToString(b2);
					myLogger.info("*****KANDUNGAN*****");
				
					permohonanJPPH.put("docContent", content);
				
				
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}
		return permohonanJPPH;
	}
}