package com.ibm_watson.languagetranslator;

import java.util.List;

import com.ibm.watson.developer_cloud.language_translator.v2.LanguageTranslator;
import com.ibm.watson.developer_cloud.language_translator.v2.model.TranslateOptions;
import com.ibm.watson.developer_cloud.language_translator.v2.model.Translation;
import com.ibm.watson.developer_cloud.language_translator.v2.model.TranslationResult;
import com.ibm.watson.developer_cloud.language_translator.v2.util.Language;

public class LanguageTranslatorService {

	LanguageTranslator service;
	String fromLanguage = Language.ENGLISH, toLanguage = Language.SPANISH;

	public LanguageTranslatorService(String username, String password) {
		service = new LanguageTranslator(username, password);
	}

	public List<Translation> getTranslatedLanguage(String text, String fromLanguage, String toLanguage) {

		if (!fromLanguage.isEmpty())
			this.fromLanguage = fromLanguage;
		if (!toLanguage.isEmpty())
			this.toLanguage = toLanguage;

		TranslateOptions translateOptions = new TranslateOptions.Builder().addText(text).source(this.fromLanguage)
				.target(this.toLanguage).build();
		TranslationResult translationResult = service.translate(translateOptions).execute();

		
		return translationResult.getTranslations();

	}

}
