package fileAction;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;


public class DownloadAction extends  ActionSupport{
	private String inputPath;
	private InputStream inputStream;
	public void setInputPath(String value) {
		inputPath=value;

	}
	
	public String getFileName(){
		String f= new File(inputPath).getName();
		return "filename="+f;
	}

	public String execute() throws Exception {
		File f=new File(inputPath);
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("application/octet");
		response.setContentLength((int) f.length());
		System.out.println(f.length());
		response.setHeader("Content-Disposition","attachment;"+getFileName());
		FileInputStream fileIn=new FileInputStream(f);
		OutputStream out = response.getOutputStream();
		byte[] buffer = new byte[4096];
		int bytesRead = fileIn.read(buffer);
		while (bytesRead >= 0) {
		  if (bytesRead > 0)
		    out.write(buffer, 0, bytesRead);
		    bytesRead = fileIn.read(buffer);
		}
		out.flush();
		out.close();
		fileIn.close();
		return null;
	}

}
