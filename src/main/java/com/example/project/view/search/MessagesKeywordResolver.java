package com.example.project.view.search;

import java.util.HashSet;
import java.util.Set;

import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.component.UIMessage;
import javax.faces.component.search.SearchExpressionContext;
import javax.faces.component.search.SearchKeywordContext;
import javax.faces.component.search.SearchKeywordResolver;
import javax.faces.component.visit.VisitContext;
import javax.faces.component.visit.VisitResult;
import javax.faces.context.FacesContext;

public class MessagesKeywordResolver extends SearchKeywordResolver {

    @Override
    public boolean isResolverForKeyword
        (SearchExpressionContext context, String keyword)
    {
        return "messages".equals(keyword);
    }

    @Override
    public void resolve
        (SearchKeywordContext context, UIComponent base, String keyword)
    {
        UIComponent form = base.getNamingContainer();
        while (!(form instanceof UIForm) && form != null) {
            form = form.getNamingContainer();
        }

        if (form != null) {
            Set<String> messageClientIds = new HashSet<>();
            VisitContext visitContext = VisitContext.createVisitContext
                (context.getSearchExpressionContext().getFacesContext());

            form.visitTree(visitContext, (visit, child) -> {
                if (child instanceof UIMessage) {
                    messageClientIds.add(child.getClientId());
                }
                return VisitResult.ACCEPT;
            });
            
            if (!messageClientIds.isEmpty()) {
                context.invokeContextCallback(new UIMessage() {
                    @Override
                    public String getClientId(FacesContext context) {
                        return String.join(" ", messageClientIds);
                    }
                });
            }
        }

        context.setKeywordResolved(true);
    }
}
