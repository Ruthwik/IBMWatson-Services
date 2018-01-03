package com.ibm_watson.conversation;

import java.util.List;

import com.ibm.watson.developer_cloud.conversation.v1.Conversation;
import com.ibm.watson.developer_cloud.conversation.v1.model.InputData;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageOptions;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

public class ConversationService {

	static Conversation service;

	String workspaceId;

	public ConversationService(String username, String password, String workspaceId) {
		service = new Conversation(Conversation.VERSION_DATE_2017_05_26, username, password);
		this.workspaceId = workspaceId;
	}

	public List<String> getResponse(String text) {

		// first message
		MessageOptions newMessageOptions = new MessageOptions.Builder().workspaceId(workspaceId)
				.input(new InputData.Builder(text).build()).build();

		MessageResponse response = service.message(newMessageOptions).execute();

		return response.getOutput().getText();

	}

}
