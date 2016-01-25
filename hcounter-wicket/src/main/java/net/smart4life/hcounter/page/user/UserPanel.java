package net.smart4life.hcounter.page.user;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.basic.MultiLineLabel;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.value.ValueMap;

import net.smart4life.hcounter.datamodel.entity.User;
import net.smart4life.hcounter.service.user.UserService;

public class UserPanel extends Panel {

	@SpringBean
	private UserService userService;
	
	public UserPanel(String id) {
		super(id);
		

		// Add comment form
        add(new UserForm("userForm"));
        // Add commentListView of existing comments
//        add(new PropertyListView<User>("users", userService.findAll()) {
//            @Override
//            public void populateItem(final ListItem<User> listItem) {
//                listItem.add(new Label("id"));
//                listItem.add(new MultiLineLabel("loginname"));
//            }
//        }).setVersioned(false);
        
        add(new PropertyListView<User>("users", new UserListModel()) {
          @Override
          public void populateItem(final ListItem<User> listItem) {
              listItem.add(new Label("id"));
              listItem.add(new MultiLineLabel("loginname"));
          }       	
        }).setVersioned(false);
	}
	

	class UserListModel extends LoadableDetachableModel<List<User>> {
		@Override
		protected List<User> load() {
			return userService.findAll();
		}
	}
	
	class UserForm extends Form<ValueMap> {
		public UserForm(final String id) {
			// Construct form with no validation listener
			super(id, new CompoundPropertyModel<ValueMap>(new ValueMap()));
			// this is just to make the unit test happy
			setMarkupId("userForm");
			// Add text entry widget
			add(new TextField<String>("loginname").setType(String.class));
			// Add simple automated spam prevention measure.
//			add(new TextField<String>("comment").setType(String.class));
		}

		/**
		 * Show the resulting valid edit
		 */
		@Override
		public final void onSubmit() {
			ValueMap values = getModelObject();
			// check if the honey pot is filled
//			if (StringUtils.isNotBlank((String) values.get("comment"))) {
//				error("Caught a spammer!!!");
//				return;
//			}
			// Construct a copy of the edited comment
			User user = new User();
			// Set date of comment to add
			user.setLoginname((String) values.get("loginname"));
			userService.insert(user);
			// Clear out the text component
			values.put("loginname", "");
		}

		/**
		 * Clears the comments.
		 */
		public void clear() {
//			userList.clear();
		}
	}

}
