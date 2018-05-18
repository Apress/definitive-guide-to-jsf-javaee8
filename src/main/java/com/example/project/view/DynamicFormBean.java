package com.example.project.view;

import static java.util.Arrays.asList;
import static javax.faces.application.ResourceHandler.JSF_SCRIPT_LIBRARY_NAME;
import static javax.faces.application.ResourceHandler.JSF_SCRIPT_RESOURCE_NAME;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.component.UIOutput;
import javax.faces.component.behavior.AjaxBehavior;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlInputSecret;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlMessage;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;

import com.example.project.model.Field;
import com.example.project.model.Field.DynamicForm;
import com.example.project.service.FieldService;

@Named
@RequestScoped
public class DynamicFormBean implements DynamicForm {

	private transient UIForm form; // UIComponent instances MAY NOT be serialized!
	private Map<String, Object> values = new HashMap<>();

	@Inject
	private FieldService fieldService;

	public void populate(ComponentSystemEvent event) {
		form = (UIForm) event.getComponent();
		List<Field> fields = fieldService.list(form.getId());
		fields.forEach(field -> field.populate(this));
	}

	@Override
	public void createOutputLabel(Field field) {
		HtmlOutputLabel outputLabel = new HtmlOutputLabel();
		outputLabel.setId(field.getName() + "_l");
		outputLabel.setFor(field.getName());
		outputLabel.setValue(field.getLabel());
		form.getChildren().add(outputLabel);
	}

	@Override
	public void createInputText(Field field) {
		HtmlInputText inputText = new HtmlInputText();
		inputText.setId(field.getName());
		inputText.setLabel(field.getLabel());
		inputText.setValueExpression("value", createValueExpression(field));
		inputText.setRequired(field.isRequired());
		form.getChildren().add(inputText);
	}

	@Override
	public void createInputSecret(Field field) {
		HtmlInputSecret inputSecret = new HtmlInputSecret();
		inputSecret.setId(field.getName());
		inputSecret.setLabel(field.getLabel());
		inputSecret.setValueExpression("value", createValueExpression(field));
		inputSecret.setRequired(field.isRequired());
		form.getChildren().add(inputSecret);
	}

	@Override
	public void createCommandButton(Field field) {
		HtmlCommandButton commandButton = new HtmlCommandButton();
		commandButton.setId(field.getName());
		commandButton.setValue(field.getLabel());
		commandButton.setActionExpression(createMethodExpression(field));
		commandButton.addClientBehavior("action", createAjaxBehavior(field));
		form.getChildren().add(commandButton);
	}

	@Override
	public void createMessage(Field field) {
		HtmlMessage message = new HtmlMessage();
		message.setId(field.getName() + "_m");
		message.setFor(field.getName());
		form.getChildren().add(message);
	}

	private static ValueExpression createValueExpression(Field field) {
		FacesContext context = FacesContext.getCurrentInstance();
		return context.getApplication().getExpressionFactory().createValueExpression(context.getELContext(), "#{dynamicFormBean.values['" + field.getName() + "']}", Object.class);
	}

	private static MethodExpression createMethodExpression(Field field) {
		FacesContext context = FacesContext.getCurrentInstance();
		return context.getApplication().getExpressionFactory().createMethodExpression(context.getELContext(), "#{dynamicFormBean.submit}", Object.class, new Class[0]);
	}

	private static AjaxBehavior createAjaxBehavior(Field field) {
		FacesContext context = FacesContext.getCurrentInstance();
		AjaxBehavior ajax = (AjaxBehavior) context.getApplication().createBehavior(AjaxBehavior.BEHAVIOR_ID);
		ajax.setExecute(asList("@form"));
		ajax.setRender(asList("@messages"));

		UIOutput jsfjs = new UIOutput();
		jsfjs.setRendererType(context.getApplication().getResourceHandler().getRendererTypeForResourceName(JSF_SCRIPT_RESOURCE_NAME));
		jsfjs.getAttributes().put("name", JSF_SCRIPT_RESOURCE_NAME);
		jsfjs.getAttributes().put("library", JSF_SCRIPT_LIBRARY_NAME);
		context.getViewRoot().addComponentResource(context, jsfjs);

		return ajax;
	}

	public String submit() {
		FacesContext context = FacesContext.getCurrentInstance();
		UIComponent command = UIComponent.getCurrentComponent(context);
		return fieldService.submit(form.getId(), command.getId(), values, () -> context.addMessage(command.getClientId(context), new FacesMessage("Successfully submitted")));
	}

	public Map<String, Object> getValues() {
		return values;
	}

}
