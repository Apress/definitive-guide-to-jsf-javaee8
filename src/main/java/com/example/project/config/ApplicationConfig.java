package com.example.project.config;

import javax.faces.annotation.FacesConfig;
import javax.faces.application.ViewVisitOption;
import javax.faces.context.FacesContext;
import javax.faces.webapp.FacesServlet;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.example.project.model.ButtonField;
import com.example.project.model.Field;
import com.example.project.model.Group;
import com.example.project.model.Message;
import com.example.project.model.PasswordField;
import com.example.project.model.Product;
import com.example.project.model.TextField;
import com.example.project.service.FieldService;
import com.example.project.service.MessageService;
import com.example.project.service.ProductService;
import com.example.project.service.UserService;
import com.example.project.view.search.MessagesKeywordResolver;

@FacesConfig
@WebListener
public class ApplicationConfig implements ServletContextListener {

	@Inject
	private ProductService productService;

	@Inject
	private MessageService messageService;

	@Inject
	private FieldService fieldService;

	@Inject
	private UserService userService;

	@Override
	public void contextInitialized(ServletContextEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		registerMessagesKeywordResolver(facesContext);
		addExtensionLessMappings(event.getServletContext(), facesContext);
		createTestProducts();
		createTestMessages();
		createTestFields();
		createTestUsers();
	}

	private void registerMessagesKeywordResolver(FacesContext context) {
		context.getApplication().addSearchKeywordResolver(new MessagesKeywordResolver());
	}

	private void addExtensionLessMappings(ServletContext servletContext, FacesContext facesContext) {
		servletContext
			.getServletRegistrations().values().stream()
			.filter(servlet -> servlet.getClassName().equals(FacesServlet.class.getName()))
			.findAny()
			.ifPresent(facesServlet -> facesContext
				.getApplication()
				.getViewHandler()
				.getViews(facesContext, "/", ViewVisitOption.RETURN_AS_MINIMAL_IMPLICIT_OUTCOME)
				.forEach(view -> facesServlet.addMapping(view))
		);
	}

	private void createTestProducts() {
		productService.create(Product.create("One", "The first product"));
		productService.create(Product.create("Two", "The second product"));
		productService.create(Product.create("Three", "The third product"));
	}

	private void createTestMessages() {
		Message message1 = Message.create("Lorem ipsum", null);
		Message message2 = Message.create("Dolor sit amet", message1);
		Message message3 = Message.create("Consectetur adipiscing elit", message1);
		Message message4 = Message.create("Phasellus magna mauris", message3);
		Message message5 = Message.create("Mattis vitae consectetur sit amet", message4);
		Message message6 = Message.create("Quisque vitae odio vitae", null);
		Message message7 = Message.create("Tellus tincidunt molestie in quis metus", message6);
		Message message8 = Message.create("Sed nec quam vel turpis vestibulum", message6);
		Message message9 = Message.create("Faucibus et a quam", message6);

		messageService.create(message1);
		messageService.create(message2);
		messageService.create(message3);
		messageService.create(message4);
		messageService.create(message5);
		messageService.create(message6);
		messageService.create(message7);
		messageService.create(message8);
		messageService.create(message9);
	}

	private void createTestFields() {
		fieldService.create(Field.create(1, TextField.class, "loginForm", "email", "Email Address", true));
		fieldService.create(Field.create(2, PasswordField.class, "loginForm", "password", "Password", true));
		fieldService.create(Field.create(3, ButtonField.class, "loginForm", "login", "Log In"));
	}

	private void createTestUsers() {
		userService.register("admin@example.com", "passw0rd", Group.ADMIN);
		userService.register("user@example.com", "passw0rd");
	}

}
