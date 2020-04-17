package ekptg.intergration;

import java.io.File;
import java.io.FileInputStream;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import lebah.db.Db;

public class FileUploadUtility implements IUploadUtilityObserver{
	@Override
	public void deleteUploadFiles(IFileUploadUtilityProperty prop)throws Exception{
		FileUpload up = prop.getUploadInfo();
		String path = up.getUploadPath();
		String loginName = up.getLoginName();
		String type = up.getProsesType();
		
		if(type.equals("aduan")){
			String dirName = path + loginName;
			deleteFiles(dirName);
		}
		else if(type.equals("intergration")){
			String dirName = path + loginName;
			deleteFiles(dirName);
		}
		else if(type.equals("jawapanAduan")){
			String dirName = path + loginName;
			deleteFiles(dirName);
		}
		
	}
	private void deleteFiles(String dirName){
		try{
			File dir = new File(dirName);
			if(dir.isDirectory()){
				File files [] = dir.listFiles();
				if(files.length > 0){
					for(File f: files){
						System.out.println(f.delete());//delete file
					}
				}
			}
		}
		catch(Exception ioe){
			ioe.printStackTrace();
		}
	}
	@Override
	public void storeUploadFile(IFileUploadUtilityProperty prop) throws Exception{
		FileUpload up = prop.getUploadInfo();
		String tableName = up.getUploadTableName();
		String fieldName = up.getUploadFieldName();
		String fieldPK = up.getPkField();
		long pkValue = up.getPkValue();
		String fieldFK = up.getFkField();
		long fkValue = up.getFkValue();
		String mimeField = up.getMimeField();
		if(mimeField == null)
			mimeField = "MIME_TYPE";
		String path = up.getUploadPath();
		String loginName = up.getLoginName();
		
		Db db = null;
		try{
			db = new Db();
			Connection conn = db.getConnection();
			conn.setAutoCommit(false);
			System.out.println(path+loginName);
			File dir = new File(path+loginName);
			String sql = "INSERT INTO "+tableName+"("+fieldFK+","+fieldName+","+mimeField+",NAMA_FAIL)"+
			"VALUES(?,?,?,?)";
			
			PreparedStatement prep = conn.prepareStatement(sql);
			if(dir.isDirectory()){
				File files[] = dir.listFiles();
				System.out.println("total file to upload :"+files.length);
				for(File file : files){
					FileNameMap fileNameMap = URLConnection.getFileNameMap();
					//String mimeType = new MimetypesFileTypeMap().getContentType(file);
					String mimeType = fileNameMap.getContentTypeFor(file.getName());
					String fileName = file.getName();
					System.out.println("file name "+fileName);
					FileInputStream input = new FileInputStream(file);
					prep.setLong(1, fkValue);
					prep.setBinaryStream(2, input, file.length());
					prep.setString(3, mimeType);
					prep.setString(4, fileName);
					prep.addBatch();
				}
				int count[] = prep.executeBatch();
				conn.commit();
			}
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			db.close();
		}
		
	}

}
