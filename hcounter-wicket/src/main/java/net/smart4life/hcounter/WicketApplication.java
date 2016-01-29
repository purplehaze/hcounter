package net.smart4life.hcounter;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import de.agilecoders.wicket.core.Bootstrap;
import net.smart4life.hcounter.config.JpaConfig;
import net.smart4life.hcounter.config.ServiceConfig;
import net.smart4life.hcounter.page.authentication.LoginPage;
import net.smart4life.hcounter.page.event.EventPage;
import net.smart4life.hcounter.page.user.UserPage;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the StartHCounter class.
 * 
 * @see net.smart4life.hcounter.StartHCounter#main(String[])
 */
public class WicketApplication extends WebApplication
{
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return HomePage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();
		
		Bootstrap.install(this);
		
	    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
	    //Scan package for annotated beans
	    ctx.scan(ServiceConfig.class.getPackage().getName(), JpaConfig.class.getPackage().getName());
	    ctx.refresh();

	    getComponentInstantiationListeners().add(new SpringComponentInjector(this, ctx));
	    
		mountPage("/user", UserPage.class);
		mountPage("/login", LoginPage.class);
		mountPage("/event", EventPage.class);
	}
}
