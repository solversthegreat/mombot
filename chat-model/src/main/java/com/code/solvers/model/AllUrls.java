package com.code.solvers.model;

public class AllUrls {

	public static final int TIMEOUT_IN_MILLISECOND = 20000;
	
	public static final String ROCKET_LOGIN_ENDPOINT = "https://hack2022.rocket.chat/api/v1/login";
	public static final String ROCKET_POST_MESSAGE_ENDPOINT = "http://hack2022.rocket.chat/api/v1/chat.postMessage";
	public static final String ROCKET_CREATE_GROUP_ENDPOINT = "http://hack2022.rocket.chat/api/v1/groups.create";
	public static final String ROCKET_CHAT_REACT_ENDPOINT = "http://hack2022.rocket.chat/api/v1/chat.react";
	public static final String ROCKET_CHAT_PARTICIPANTS_ENDPOINT = "https://hack2022.rocket.chat/api/v1/channels.members?roomName={room}";
	public static final String ROCKET_CHAT_HISOTRY_ENDPOINT = "https://hack2022.rocket.chat/api/v1/channels.history?roomName={room}";
	public static final String ROCKET_GROUP_MESSAGES_ENDPOINT = "https://hack2022.rocket.chat/api/v1/groups.messages?roomName={room}";
	public static final String DIALOGFLOW_QUERY_ENDPOINT = "https://api.dialogflow.com/v1/query?v=20170712";
	public static String BOT_SERVER_TO_ROCKET_PUSH = "http://rocket-chat/rocket/adapter/outgoing/push";
	public static String BOT_SERVER_TO_DIALOG_FLOW = "http://dialogflow-connector/dialogflow/connector/incoming/push";
	public static String JIRA_CREATE_ISSUE_ENDPOINT = "https://thegreatsolvers.atlassian.net/rest/api/3/issue";
	public static String JIRA_LOCAL_CREATE_ISSUE_ENDPOINT = "http://jira-connector/jira/connector/incident";
	public static String ROCKET_TO_TWILIO_SMS = "http://twilio-connector/twilio/adapter/incoming/otp/sms";
	public static String ROCKET_TO_TWILIO_CALL = "http://twilio-connector/twilio/adapter/incoming/otp/call";
	public static String ROCKET_TO_NEWS_HEADLINES = "http://elastic-connector/elastic/news/headlines";
	public static String ROCKET_TO_BOT_SERVER_INCOMING = "http://bot-server/bot/server/incoming/push";
	
}

