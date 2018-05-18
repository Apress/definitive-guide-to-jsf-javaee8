package com.example.project.view.converter;

import javax.enterprise.inject.spi.CDI;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.example.project.model.BaseEntity;
import com.example.project.service.BaseEntityService;

@FacesConverter(forClass=BaseEntity.class, managed=true)
public class BaseEntityConverter implements Converter<BaseEntity> {

	// @Inject doesn't work due to https://github.com/javaserverfaces/mojarra/issues/4324
	private BaseEntityService baseEntityService = CDI.current().select(BaseEntityService.class).get();

	@Override
	public String getAsString(FacesContext context, UIComponent component, BaseEntity entity) {
		if (entity == null) {
			return "";
		}

		Long id = entity.getId();
		return (id != null) ? id.toString() : null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public BaseEntity getAsObject(FacesContext context, UIComponent component, String id) {
		if (id == null || id.isEmpty()) {
			return null;
		}

		Class<? extends BaseEntity> type = (Class<? extends BaseEntity>) component.getValueExpression("value").getType(context.getELContext());

		try {
			return baseEntityService.getById(type, Long.valueOf(id));
		}
		catch (NumberFormatException e) {
			throw new ConverterException(new FacesMessage(String.format("%s is not a valid entity ID", id)), e);
		}
	}

}
