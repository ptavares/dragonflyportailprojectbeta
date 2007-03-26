package fileAction;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;

public class DownloadAction2 implements Action {
	private String inputPath;
    public void setInputPath(String value) {
        inputPath = value;
    }

    public InputStream getInputStream() throws Exception {
        return new FileInputStream(new File(inputPath));
    }
    
    public String getF(){
    	return "filename="+(new File(inputPath)).getName();
    }

	public String execute() throws Exception {
		HttpServletResponse response=ServletActionContext.getResponse();
		System.out.println(getF());
		response.setHeader("Content-Disposition","attachment;"+getF()+";");
		return SUCCESS;
	}

}
