package projects;

import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class LoginInterceptor implements Interceptor {

	public void destroy() {

	}

	public void init() {

	}

	public String intercept(ActionInvocation invocation) throws Exception {
		Map<String, String> session=invocation.getInvocationContext().getSession();
		if(session.get("login")!="true"){
			return "login";
		}
		
		return invocation.invoke();
	}

}
