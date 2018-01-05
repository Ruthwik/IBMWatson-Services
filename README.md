# IBMWatson-Services

This project uses Java SDK provided by Watson Developer Cloud services to solve complex problems

Watson on the IBM Cloud allows you to integrate the world's most powerful AI into your application and store, train and manage your data in the most secure cloud..

Some of the IBM Watson Services 
  * [Conversation](conversation)
  * [Dialog](dialog)
  * [Discovery](discovery)
  * [Language Translator](language-translator)
  * [Natural Language Understanding](natural-language-understanding)
  * [Personality Insights](personality-insights)
  * [Visual Recognition](visual-recognition)
 
## Conversation

Watson Conversation is used wherever it is required to add conversational capability to the apps to engage with end-users on their platforms of choice

### Prerequisite

	* [Create a workspace](https://console.bluemix.net/docs/services/conversation/configure-workspace.html#configuring-a-conversation-workspace)
	* [Create intents](https://console.bluemix.net/docs/services/conversation/intents.html#defining-intents)
	* [Build a dialog](https://console.bluemix.net/docs/services/conversation/dialog-build.html#dialog-build)
	
	

```Java
	static Conversation service;
	//provide the workspaceid
	String workspaceId;

	public ConversationService(String username, String password, String workspaceId) {
		service = new Conversation(Conversation.VERSION_DATE_2017_05_26, username, password);
		this.workspaceId = workspaceId;
	}

	public List<String> getResponse(String text) {

		// Conversation 
		MessageOptions newMessageOptions = new MessageOptions.Builder().workspaceId(workspaceId)
				.input(new InputData.Builder(text).build()).build();

		MessageResponse response = service.message(newMessageOptions).execute();

		return response.getOutput().getText();

	}

```
### Output

The reponse from the conversation service is based on the configured intents,entities and dialog.

```Java
Response from Watson:[Welcome to Customer service., How can I help you?]
```

## Natural Language Understanding

Natural Language Understanding enables advanced text analysis through natural language processing. 
The service analyzes unstructured text to extract metadata such as entities, general concepts, keywords, categories, relations, sentiment, and emotion. 


```Java
	//extracts the keywords from the text
	public List<KeywordsResult> detectKeywords(String text) {

		KeywordsOptions keywords = new KeywordsOptions.Builder().build();

		Features features = new Features.Builder().keywords(keywords).build();

		AnalyzeOptions parameters = new AnalyzeOptions.Builder().text(text).features(features).build();

		AnalysisResults response = service.analyze(parameters).execute();

		List<KeywordsResult> keywordsResult = response.getKeywords();

		return keywordsResult;
	}

```
### Output

This a sample output for keywords of a text. The relevance number shows how much the keyword is relevant to the text.
```Java
Keywords [{
  "relevance": 0.99942,
  "text": "investigator David Ferrucci"
}, {
  "relevance": 0.758179,
  "text": "question answering"
}, {
  "relevance": 0.435151,
  "text": "natural language"
},
```

## Language Translator
The Watson Language Translator service provides domain-specific translation between languages.

Supported Languages:
English, Arabic, French, German, Italian, Japanese, Portuguese, Korean, and Spanish

### Input

Language: English
```Java
Watson is a question answering computer system capable of answering questions posed in natural language
developed in IBM's DeepQA project by a research team led by principal investigator David Ferrucci.
```

### Output

Language: German
```Java
Watson ist eine Frage beantwortet Computersystem kann der Beantwortung von Anfragen in natürlicher Sprache gestellt entwickelte DeepQA der IBM von einem Forschungsteam von Hauptprüfer David Ferrucci geführt

```

## References


	* [Java SDK](https://github.com/watson-developer-cloud/java-sdk) to use the IBM Watson service
	* [IBM Cloud docs](https://console.bluemix.net/docs/services)
