package com.action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

import com.bo.UserBo;

import com.form.LoginForm;
import com.model.UserDetails;

public class LoginAction extends org.springframework.web.struts.ActionSupport {
	private final static String SUCCESS = "success";
    private final static String ERROR = "error";
    public ActionForward execute(ActionMapping mapping,
    		ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {
    	
    	
    			
    			LoginForm userForm = (LoginForm)form;
        
        		UserBo userBo =(UserBo) getWebApplicationContext().getBean("userBo");
        	 
        		
        		UserDetails user = new UserDetails();
        	 
        		//copy customerform to model
        		//BeanUtils.copyProperties(user, userForm);
        		
        		user.setuserName(userForm.getUserName());
        		user.setPassword(userForm.getPassword());
        		
        		System.out.println("UserBo in action class: " + userBo);
        	 
        		if(userBo.checkUser(user)== true)
        		{
        			return mapping.findForward(SUCCESS);
        		}
        		
        		return mapping.findForward(ERROR);
     
    }
}
