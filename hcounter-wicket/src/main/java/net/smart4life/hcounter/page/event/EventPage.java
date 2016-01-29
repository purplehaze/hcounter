package net.smart4life.hcounter.page.event;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.value.ValueMap;

import net.smart4life.hcounter.datamodel.entity.User;
import net.smart4life.hcounter.page.layout.LayoutPage;
import net.smart4life.hcounter.service.windowsevent.WindowsEventService;

public class EventPage extends LayoutPage {

	@SpringBean
	private WindowsEventService eventService;
	
	public EventPage(final PageParameters parameters) {
		super(parameters);
		
		add(new LoadEventsForm("loadEventsForm"));
		
		add(new EventPanel("eventContent"));
	}
	
	class LoadEventsForm extends Form<ValueMap> {
		public LoadEventsForm(final String id) {
			// Construct form with no validation listener
			super(id, new CompoundPropertyModel<ValueMap>(new ValueMap()));
			// this is just to make the unit test happy
			setMarkupId("loadEventsForm");
			add(new Button("loadEventsBtn"){
				@Override
				public void onSubmit() {
					eventService.loadEventsIntoDb(null);
				}
			}.setDefaultFormProcessing(false));
		}

		/**
		 * Clears the comments.
		 */
		public void clear() {
//			userList.clear();
		}
	}

}