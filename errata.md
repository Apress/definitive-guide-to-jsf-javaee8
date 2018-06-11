# Errata for *The Definitive Guide to JSF in Java EE 8*
***

On **4. Form Components. SelectItem Tags section. Country code example, view code snippet** [wrong position of the closing tag]:
From the [link](https://www.safaribooksonline.com/library/view/the-definitive-guide/9781484233870/A454457_1_En_4_Chapter.html)
Original code snippet

```xhtml
<h:selectOneMenu id="countryCode" value="#{bean.countryCode}">
    <f:selectItem itemValue="#{null}" itemLabel="-- select one --" />
    <f:selectItems value="#{bean.availableCountries}" var="country">
        itemValue="#{country.code}" itemLabel="#{country.name}"
    </f:selectItems>
</h:selectOneMenu>
```

Corrections
```xhtml
<h:selectOneMenu id="countryCode" value="#{bean.countryCode}">
    <f:selectItem itemValue="#{null}" itemLabel="-- select one --" />
    <f:selectItems value="#{bean.availableCountries}" var="country"
        itemValue="#{country.code}" itemLabel="#{country.name}"/>    
</h:selectOneMenu>
```
***
