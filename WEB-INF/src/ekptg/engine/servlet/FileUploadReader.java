package ekptg.engine.servlet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;

import ekptg.intergration.FileUploadProperty;

public class FileUploadReader extends HttpServlet  {
	private static final long serialVersionUID = 6748857432950840322L;
    private static final String DESTINATION_DIR_PATH = "files";
    private static String realPath="G:/sniper/";

    /**
     * {@inheritDoc}
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        //realPath = getServletContext().getRealPath(DESTINATION_DIR_PATH) + "/";
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {

        PrintWriter writer = null;
        InputStream is = null;
        FileOutputStream fos = null;
        HttpSession ses = request.getSession();
        String userId =(String) ses.getAttribute("_ekptg_user_id");
        try {
            writer = response.getWriter();
        } catch (IOException ex) {
            //log(OctetStreamReader.class.getName() + "has thrown an exception: " + ex.getMessage());
        }

        String filename = request.getHeader("X-File-Name");
        String transaction = request.getParameter("param1");
        try {
            is = request.getInputStream();
            FileUploadProperty fileProperty = FileUploadProperty.getInstance();
            //prepare to upload data for online aduan
            if(transaction!= null && transaction.equals("aduan")){
            	realPath = fileProperty.getAduanFolder();//get aduan folder location
            }
            else if(transaction!= null && transaction.equals("aduanJawapan")){
            	realPath = fileProperty.getJawapanAduanLampiranFolder();
            }
            String newPath = realPath +userId+"/";
            System.out.println("upload path "+newPath);
            File toUp = new File(newPath);
            if(!toUp.exists()){
            	//fos = new FileOutputStream(toUp);
            	//IOUtils.copy(is, fos);
            	toUp.mkdir();
            }
            fos = new FileOutputStream(new File(newPath+filename));
            IOUtils.copy(is, fos);
            //changeFileName(realPath, filename, userId);
            response.setStatus(response.SC_OK);
            writer.print("{success: true}");
            fos.close();
        } catch (FileNotFoundException ex) {
            response.setStatus(response.SC_INTERNAL_SERVER_ERROR);
            writer.print("{success: false}");
            //log(OctetStreamReader.class.getName() + "has thrown an exception: " + ex.getMessage());
        } catch (IOException ex) {
            response.setStatus(response.SC_INTERNAL_SERVER_ERROR);
            writer.print("{success: false}");
            //log(OctetStreamReader.class.getName() + "has thrown an exception: " + ex.getMessage());
        }
        catch(Exception e){
        	e.printStackTrace();
        }
        finally {
            try {
                fos.close();
                is.close();
            } catch (IOException ignored) {
            }
        }

        writer.flush();
        writer.close();
    }
    private void changeFileName(String path,String fileName,String userId)throws Exception{
    	File oldFile = new File(path+fileName);
    	if(!oldFile.exists()){
    		throw new Exception("FILE NOT FOUND");
       	}
    	String newName = path+"/saiful"+"/"+fileName;
    	File newFile  = new File(newName);
    	System.out.println(oldFile.renameTo(newFile));
    }
}
