package de.jan.boot.client;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class LanguageBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
	
	private String lang = "de";
	
	private Map<String, String> langs;

	@PostConstruct
	public void init() {
		Map<String,String> test = new HashMap<String, String>();
		test.put("Deutsch", "de");
		test.put("English", "en");
		test.put("Polski", "pl");
		langs = test;
	}
	
	public Locale getLocale() {
		return locale;
	}

	public String getLanguage() {
		return locale.getLanguage();
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public Map<String, String> getLangs() {
		return langs;
	}

	public void setLangs(Map<String, String> langs) {
		this.langs = langs;
	}

	public void onLanguageChange() {
		changeLanguage(this.lang);
	}
	
	public void changeLanguage(String language) {
		locale = new Locale(language);
		FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
	}
}