package net.smart4life.hcounter.page.authentication;

import net.smart4life.hcounter.page.layout.LayoutPage;

public class LoginPage extends LayoutPage {
	public LoginPage(){
		super();		
		add(new LoginPanel("loginContent"));
	}
}
