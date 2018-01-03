import java.io.IOException;
import java.util.List;

import com.ibm.watson.developer_cloud.language_translator.v2.model.Translation;
import com.ibm.watson.developer_cloud.language_translator.v2.util.Language;
import com.ibm_watson.conversation.ConversationService;
import com.ibm_watson.languagetranslator.LanguageTranslatorService;
import com.ibm_watson.nlu.NLUService;
import com.ibm_watson.util.ConfigProperties;

public class MainApp {
	public static void main(String[] args) throws IOException {
		String text = "Watson is a question answering computer system capable of answering questions posed in natural language"
				+ " developed in IBM's DeepQA project by a research team led by principal investigator David Ferrucci. ";
		
		ConfigProperties configProperties= new ConfigProperties();
		
		//Natural Language Understanding Service
		NLUService nluService = new NLUService(configProperties.getProprties("nlu.username"), configProperties.getProprties("nlu.password"));
	
		System.out.println("Sentiment " + nluService.getSentiment(text));
		System.out.println("Emotions "+ nluService.detectEmotion(text) );
		System.out.println("Keywords "+ nluService.detectKeywords(text));
		
		//Conversation Service
		ConversationService conversationService =new ConversationService(configProperties.getProprties("conv.username"), configProperties.getProprties("conv.password"),configProperties.getProprties("conv.workspaceid"));

		List<String> response = conversationService.getResponse("Hi");
		System.out.println("Response from Watson:" +response);
		
		//Language Translator Service
		LanguageTranslatorService languageTranslatorService = new LanguageTranslatorService(configProperties.getProprties("lt.username"), configProperties.getProprties("lt.password"));
		
		List<Translation> translatedText = languageTranslatorService.getTranslatedLanguage(text, Language.ENGLISH, Language.GERMAN);
		
		System.out.println("Translated Text");
		System.out.println(translatedText);

		
	}

}
