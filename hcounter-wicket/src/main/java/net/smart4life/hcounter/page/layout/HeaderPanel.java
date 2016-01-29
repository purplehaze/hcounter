package net.smart4life.hcounter.page.layout;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import net.smart4life.hcounter.page.authentication.LoginPage;
import net.smart4life.hcounter.page.event.EventPage;
import net.smart4life.hcounter.page.user.UserPage;

public class HeaderPanel extends Panel {
	
	private Link<String> userLink;
	private Link<String> eventLink;
	private Link<String> loginLink;

	public HeaderPanel(String id) {
		super(id);
		
		add(userLink = new Link<String>("userLink"){
			@Override
			public void onClick() {
				PageParameters pageParameters = new PageParameters();
				pageParameters.add("foo", "foo");
				pageParameters.add("bar", "bar");
				setResponsePage(UserPage.class);
			}
		});
		add(eventLink = new Link<String>("eventLink"){
			@Override
			public void onClick() {
				setResponsePage(EventPage.class);
			}
		});
		add(loginLink = new Link<String>("loginLink"){
			@Override
			public void onClick() {
				PageParameters pageParameters = new PageParameters();
				pageParameters.add("foo", "foo");
				pageParameters.add("bar", "bar");
				setResponsePage(LoginPage.class);
			}
		});
	}
}
