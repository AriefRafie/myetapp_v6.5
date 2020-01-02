package ekptg.view.pdt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Iterator;
import java.util.List;

import lebah.db.Db;
import lebah.portal.AjaxBasedModule;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FrmTRMUploadExcel extends AjaxBasedModule {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6848013933614299880L;

	@Override
	public String doTemplate2() throws Exception {
		String vm = "";
		
		uploadFile("");
		vm = "app/pdt/trm/frmUploadExcel.jsp";
		return vm;
	}

	public void uploadFile(String idAkta) throws Exception {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {
			List items = upload.parseRequest(request);
			Iterator itr = items.iterator();
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				if ((!(item.isFormField())) && (item.getName() != null) && (!("".equals(item.getName())))) {
					//saveBlob(idAkta, item);
				}
			}
		}
	}

	public void saveBlob(String idAkta, FileItem item) throws Exception {
		Db db = null;
		try {
			db = new Db();
			Connection con = db.getConnection();
			con.setAutoCommit(false);
			PreparedStatement ps = con.prepareStatement("UPDATE tblpdtakta " + "SET Content=?,jenis_mime=? WHERE id_akta=?");
			ps.setBinaryStream(1, item.getInputStream(), (int) item.getSize());
			ps.setString(2, item.getName());
			ps.setString(3, idAkta);
			ps.executeUpdate();
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (db != null)
				db.close();
		}
	}
}