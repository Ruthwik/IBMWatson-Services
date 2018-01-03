package com.ibm_watson.nlu;

import java.util.List;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.DocumentSentimentResults;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EmotionOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EmotionResult;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.EmotionScores;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.Features;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.KeywordsOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.KeywordsResult;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.SentimentOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.SentimentResult;

public class NLUService {

	private static NaturalLanguageUnderstanding service;

	public NLUService() {
		super();
	}

	public NLUService(String username, String password) {
		service = new NaturalLanguageUnderstanding(NaturalLanguageUnderstanding.VERSION_DATE_2017_02_27, username,
				password);
	}

	public DocumentSentimentResults getSentiment(String text) {
		SentimentOptions sentiment = new SentimentOptions.Builder().build();

		Features features = new Features.Builder().sentiment(sentiment).build();

		AnalyzeOptions parameters = new AnalyzeOptions.Builder().text(text).features(features).build();

		AnalysisResults response = service.analyze(parameters).execute();

		SentimentResult sentimentResult = response.getSentiment();

		return sentimentResult.getDocument();

	}

	public EmotionScores detectEmotion(String text) {

		EmotionOptions emotion = new EmotionOptions.Builder().build();

		Features features = new Features.Builder().emotion(emotion).build();
		AnalyzeOptions parameters = new AnalyzeOptions.Builder().text(text).features(features).build();

		AnalysisResults response = service.analyze(parameters).execute();

		EmotionResult emotionResult = response.getEmotion();
		EmotionScores emotionScores = emotionResult.getDocument().getEmotion();

		return emotionScores;
	}

	public List<KeywordsResult> detectKeywords(String text) {

		KeywordsOptions keywords = new KeywordsOptions.Builder().build();

		Features features = new Features.Builder().keywords(keywords).build();

		AnalyzeOptions parameters = new AnalyzeOptions.Builder().text(text).features(features).build();

		AnalysisResults response = service.analyze(parameters).execute();

		List<KeywordsResult> keywordsResult = response.getKeywords();

		return keywordsResult;
	}

}
