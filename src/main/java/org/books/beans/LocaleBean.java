package org.books.beans;

import java.io.Serializable;
import java.util.Locale;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author AWy
 */
@Named("localeBean")
@SessionScoped
public class LocaleBean implements Serializable {

    public Locale getLocale() {
	return FacesContext.getCurrentInstance().getViewRoot().getLocale();
    }

    public void setGerman() {
	changeLocale(Locale.GERMAN);
    }

    public void setEnglish() {
	changeLocale(Locale.ENGLISH);
    }

    public boolean isShowGerman() {
	return !Locale.GERMAN.equals(getLocale());
    }

    public boolean isShowEnglish() {
	return !Locale.ENGLISH.equals(getLocale());
    }

    private void changeLocale(Locale locale) {
	FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }
}
