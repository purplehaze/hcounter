package net.smart4life.hcounter.page.user;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import net.smart4life.hcounter.page.layout.LayoutPage;

public class UserPage extends LayoutPage {
	


	public UserPage(final PageParameters parameters) {
		super(parameters);
		
		add(new UserPanel("userContent"));
//		getMenuPanel().setVisible(true);
	}

}