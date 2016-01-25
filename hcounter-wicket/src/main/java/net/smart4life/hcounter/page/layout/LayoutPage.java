package net.smart4life.hcounter.page.layout;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class LayoutPage extends WebPage {
	public static final String CONTENT_ID = "layoutContentPanel";

	private Component headerPanel;
	private Component footerPanel;

    public LayoutPage(){
    	this(null);
	}
    
    public LayoutPage(final PageParameters parameters){
    	super(parameters);
		add(headerPanel = new HeaderPanel("layoutHeaderPanel"));
		add(footerPanel = new FooterPanel("layoutFooterPanel"));
//		add(new Label(CONTENT_ID, "Put your content here"));
    }

	public Component getHeaderPanel() {
		return headerPanel;
	}

	public Component getFooterPanel() {
		return footerPanel;
	}
    
}
