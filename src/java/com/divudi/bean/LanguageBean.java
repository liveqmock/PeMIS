/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.divudi.bean;

import java.io.Serializable;
import java.util.Locale;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;

/**
 *
 * @author Buddhika
 */
@ManagedBean
@SessionScoped
public class LanguageBean implements Serializable {

    public LanguageBean() {
    }
    private static final long serialVersionUID = 1L;
    Boolean inSinhala;
    Boolean inTamil;
    Boolean inEnglish;

    public Boolean getInSinhala() {
        if (getLanguage().equals("si")) {
            inSinhala = Boolean.TRUE;
        } else {
            inSinhala = Boolean.FALSE;
        }
        return inSinhala;
    }

    public void setInSinhala(Boolean inSinhala) {
        this.inSinhala = inSinhala;
    }

    public Boolean getInTamil() {
        if (getLanguage().equals("ta")) {
            inTamil = Boolean.TRUE;
        } else {
            inTamil = Boolean.FALSE;
        }
        return inTamil;
    }

    public Boolean getInEnglish() {
        if (getLanguage().equals("en")) {
            inEnglish = Boolean.TRUE;
        } else {
            inEnglish = Boolean.FALSE;
        }
        return inEnglish;
    }

    public void changeToSinhala() {
        setLanguage("si");
    }

    public void changeToTamil() {
        setLanguage("ta");
    }

    public void changeToEnglish() {
        setLanguage("en");
    }
    private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    public Locale getLocale() {
        return locale;
    }

    public String getLanguage() {
        return locale.getLanguage();
    }

    public void setLanguage(String language) {
        locale = new Locale(language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }
}
