package com.code.solvers.queue;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.context.Context;


import com.code.solvers.nlp.model.BotCompleteMessage;
//import com.code.pilots.dialogflow.model.response.DffUserData;
//import com.code.pilots.dialogflow.model.response.DialogFlowFulfillmentResponse;
import com.code.solvers.nlp.model.OriginalRequest;
import com.code.solvers.nlp.model.Response;
//import com.code.pilots.dialogflow.model.response.Result;
import com.code.solvers.jira.model.Assignee;
import com.code.solvers.jira.model.Fields;
import com.code.solvers.jira.model.Issuetype;
import com.code.solvers.jira.model.JiraRequestMessage;
import com.code.solvers.jira.model.JiraResponseMessage;
import com.code.solvers.jira.model.Project;
import com.code.solvers.model.AllUrls;
import com.code.solvers.model.BotServerIncomingMessage;
import com.code.solvers.model.BotServerOutgoingMessage;
import com.code.solvers.model.Channel;
import com.code.solvers.model.ChannelHistory;
import com.code.solvers.model.GroupMessages;
import com.code.solvers.model.RocketChatPostMessage;
import com.code.solvers.model.RocketChatReact;
import com.code.solvers.model.TickerData;
import com.code.solvers.model.rocket.GroupCreate;
import com.code.solvers.model.rocket.GroupCreateResponse;
import com.code.solvers.model.rocket.RocketIncomingMessage;
import com.code.solvers.starter.RocketAdapter;

@RestController
public class RocketAdapterQueueEndpoint {

	Logger logger = Logger.getLogger(RocketAdapterQueueEndpoint.class);
	
	@Autowired
	private RocketAdapterQueue botServerQueue;
	
	@Autowired
	private RocketAdapter adapter;
	
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private HtmlEmailBuilder emailBuilder;
	
	@Autowired
	private JavaMailSender mailSender;
	
	private RestTemplate template;
	
	private Map<String, String> USER_EMAIL_ID_STORE;
	private Map<String, RocketIncomingMessage> INC_TO_ROCKETMSG_STORE;
	
	public RocketAdapterQueueEndpoint(RestTemplateBuilder rtb) {
		template = rtb.setConnectTimeout(Duration.ofMillis(AllUrls.TIMEOUT_IN_MILLISECOND))
				.setReadTimeout(Duration.ofMillis(AllUrls.TIMEOUT_IN_MILLISECOND)).build();
		USER_EMAIL_ID_STORE = new HashMap<String, String>();
		INC_TO_ROCKETMSG_STORE = new HashMap<String, RocketIncomingMessage>();
	}
	
	@RequestMapping("/rocket/adapter/incoming/push")
	public void push(@RequestBody RocketIncomingMessage message) {
		
		logger.info("Message received.");
		
		try {
			botServerQueue.push(message);
			
			logger.info("Message pushed");
			
		} catch (Exception e) {
			logger.error("Error processing rocket adapter incoming message", e);
		}
	}
	
	@RequestMapping("/rocket/adapter/outgoing/push")
	public void pushToRocket(@RequestBody BotServerOutgoingMessage message) {
		
		logger.info("Message received from BOT server.");
		
		try {
			HttpEntity<RocketChatPostMessage> request = getRequest(message);
			
			Object postForObject = template.postForObject(AllUrls.ROCKET_POST_MESSAGE_ENDPOINT, request, Object.class);
			
			logger.info("Message pushed to rocket");
			
		} catch (Exception e) {
			logger.error("Error processing bot server outgoing message", e);
		}
	}
	
	@RequestMapping(value = "/rocket/adapter/group", method = RequestMethod.POST)
	public void reactToChat(@RequestBody RocketChatReact reactMessage) {
		logger.info("Request received to react to a chat.");
		try {
			HttpEntity<RocketChatReact> request= getRequest(reactMessage);
			template.postForObject(AllUrls.ROCKET_CHAT_REACT_ENDPOINT, request, Boolean.class);
		} catch(Exception e) {
			logger.error("Error while reacting to chat with details: " + reactMessage);
		}
	}
	
	@RequestMapping(value = "/rocket/adapter/participants", method = RequestMethod.GET)
	public ResponseEntity<Channel> getParticipants() {
		logger.info("Request received to get the participants.");
		ResponseEntity<Channel> response = null;
		try {
			HttpHeaders headers = getHeaders();
			HttpEntity<Void> request = new HttpEntity<>(headers);
			Map<String, String> params = new HashMap<>();
			params.put("room", "dailystandup");
			
			response = restTemplate.exchange(
					AllUrls.ROCKET_CHAT_PARTICIPANTS_ENDPOINT, 
					HttpMethod.GET, 
					request, 
					Channel.class,
					params);
			
		} catch(Exception e) {
			logger.error("Error while getting the participants.");
		}
		return response;
	}
	
	/**
	 * This will only work with public channel
	 * @return
	 */
	@RequestMapping(value = "/rocket/adapter/chat-history", method = RequestMethod.GET)
	public ResponseEntity<ChannelHistory> getChannelHistory() {
		logger.info("Request received to get the chat history.");
		ResponseEntity<ChannelHistory> response = null;
		try {
			HttpHeaders headers = getHeaders();
			HttpEntity<Void> request = new HttpEntity<>(headers);
			Map<String, String> params = new HashMap<>();
			params.put("room", "dailystandup");
			
			response = template.exchange(
					AllUrls.ROCKET_CHAT_HISOTRY_ENDPOINT, 
					HttpMethod.GET, 
					request, 
					ChannelHistory.class,
					params);
			
		} catch(Exception e) {
			logger.error("Error while getting the chat history.");
		}
		return response;
	}
	
	/**
	 * This will only work with private channel
	 * @return
	 */
	@RequestMapping(value = "/rocket/adapter/group-messages", method = RequestMethod.GET)
	public ResponseEntity<GroupMessages> getGroupMessages() {
		logger.info("Request received to get the group messages.");
		ResponseEntity<GroupMessages> response = null;
		try {
			HttpHeaders headers = getHeaders();
			HttpEntity<Void> request = new HttpEntity<>(headers);
			Map<String, String> params = new HashMap<>();
			params.put("room", "dailystandup");
			
			response = template.exchange(
					AllUrls.ROCKET_GROUP_MESSAGES_ENDPOINT, 
					HttpMethod.GET, 
					request, 
					GroupMessages.class,
					params);
			
		} catch(Exception e) {
			logger.error("Error while getting the group messages.");
		}
		return response;
	}
	
	@RequestMapping(value = "/rocket/adapter/jira", method = RequestMethod.GET)
	public ResponseEntity<JiraResponseMessage> createJiraIncident() {
		logger.info("Request received to create JIRA incident.");
		ResponseEntity<JiraResponseMessage> response = null;
		try {
			createJira("Demo tile", "Testing JIRA creation");
			
		} catch(Exception e) {
			logger.error("Error while creating JIRA incident.");
		}
		
		return response;
	}
	
	private HttpEntity<RocketChatPostMessage> getRequest(BotServerOutgoingMessage message){
		RocketIncomingMessage inMessage = message.getIncomingMessage().getRocketIncomingMessage();
        HttpHeaders headers = getHeaders();
		
		RocketChatPostMessage chatPostMessage = new RocketChatPostMessage();
		String resp = message.getResponseText();
		
		chatPostMessage.setText(resp);
		chatPostMessage.setRoomId(inMessage.getChannel_id());
		chatPostMessage.setChannel(inMessage.getChannel_name());
		
        return new HttpEntity<RocketChatPostMessage>(chatPostMessage, headers);
    }
	
	private HttpEntity<RocketChatReact> getRequest(RocketChatReact reactMessage) {
		HttpHeaders headers = getHeaders();
		return new HttpEntity<RocketChatReact>(reactMessage, headers);
	}
	
	private HttpHeaders getHeadersCommanForAll() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}
	
	private HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
        
		headers.add("X-Auth-Token", adapter.getLoginData().getAuthToken());
		headers.add("X-User-Id", adapter.getLoginData().getUserId());
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		return headers;
	}

	private void invokeJiraWebhook(Response webhookInput) {
		String jiraKey = webhookInput.getIssue().getKey();
		String jiraStatus = webhookInput.getIssue().getFields().getStatus().getName();
		logger.info("Received Jira webhook call for :"+jiraKey);
		
		RocketIncomingMessage rocketMsg = INC_TO_ROCKETMSG_STORE.get(jiraKey.toUpperCase());
		if(rocketMsg == null) {
			return;
		}
		
		BotServerIncomingMessage im = new BotServerIncomingMessage();
		im.setRocketIncomingMessage(rocketMsg);
		BotServerOutgoingMessage om = new BotServerOutgoingMessage();
		
		StringBuilder sb = new StringBuilder();
		if(jiraStatus != null && !jiraStatus.equalsIgnoreCase("To Do"))  {
			sb.append("Hey, there is an update on the ticket raised for you. :-) \r\n\r\n");
		}
		
		sb.append("*Ticket: * "+jiraKey);
		sb.append("\r\n*Ticket Link: * https://greatgoblin.atlassian.net/browse/"+jiraKey);
		sb.append("\r\n*Status: *"+jiraStatus);
		
		om.setResponseText(sb.toString());
		om.setIncomingMessage(im);
		
		try {
			pushToRocket(om);
		} catch(Exception e) {
			logger.error("Error occurred while pushing jira update to rocket",e);
		}
		
	}
	
	private String send(String subject, String toEmail, String templateName, String userId) {
		
		try {
			MimeMessage mail = mailSender.createMimeMessage();	
            MimeMessageHelper messageHelper = new MimeMessageHelper(mail, true);
	        messageHelper.setFrom("Credit Suisse Team<solversthegreat@gmail.com>");
	        messageHelper.setTo(toEmail);
	        messageHelper.setSubject(subject);
	        Context context = new Context();
	        // need to populate context for replacing placeholder with value in template
	        messageHelper.setText(emailBuilder.build(templateName, context), true);
		    mailSender.send(mail);
		    
		    if(userId != null) {
		    	USER_EMAIL_ID_STORE.put(userId, toEmail);
		    }
		    return "Email sent successfully";
		    
		} catch(Exception e) {
			logger.error("Error sending email :"+e);
		}
		
		return "Sorry. Couldn't send the email right now. I will try again back after sometime.";
	}
	
	private HttpEntity<JiraResponseMessage> createJira(String title, String desc) {
		JiraRequestMessage jiraMessage = new JiraRequestMessage();
		
		Issuetype it = new Issuetype();
		it.setName("Task");
		
		Project p = new Project();
		p.setKey("FIRST");
		
		Fields f = new Fields();
		f.setProject(p);
		f.setIssuetype(it);
		f.setSummary(title);
		f.setDescription(desc);
		Assignee a = new Assignee();
		a.setName("admin");
		
		jiraMessage.setFields(f);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<JiraRequestMessage> httpReq = new HttpEntity<JiraRequestMessage>(jiraMessage, headers);
		
		try {
			HttpEntity<JiraResponseMessage> resp = restTemplate.postForEntity(AllUrls.JIRA_LOCAL_CREATE_ISSUE_ENDPOINT, httpReq, JiraResponseMessage.class);
			return resp;
			
		} catch(Exception e) {
			logger.error("Error creating jira :", e);
		}
		
		return new HttpEntity<JiraResponseMessage>(new JiraResponseMessage());
	}
	  
	 
	 /* private HttpEntity<DialogFlowFulfillmentResponse> createInc(DffUserData data,
	 * Result result, String userId, DialogFlowQueryResponse webhookInput) {
	 * 
	 * Map<String, String> pm = result.getParameters(); String incTitle =
	 * pm.get("inctitle"); String incDescription = pm.get("incdescription");
	 * 
	 * HttpEntity<DialogFlowFulfillmentResponse> response;
	 * HttpEntity<JiraResponseMessage> jiraResp = createJira(incTitle,
	 * incDescription);
	 * 
	 * 
	 * String link = ""; String msg = "";
	 * 
	 * if(jiraResp.getBody() != null) { String jiraKey =
	 * jiraResp.getBody().getKey(); if(jiraKey != null) { link =
	 * "https://thegreatsolvers.atlassian.net/browse/"+jiraResp.getBody().getKey(); msg
	 * = "A Ticket for you has been created. Here is the link \n. "
	 * +link+"\n. Please quote this ticket number for any communication regarding this discussion."
	 * ; Context c = new Context(); c.setVariable("inckey", jiraKey);
	 * c.setVariable("inclink", link);
	 * 
	 * INC_TO_ROCKETMSG_STORE.put(jiraKey.toUpperCase(),
	 * webhookInput.getOriginalRequest().getData().getIncomingMessage());
	 * 
	 * send("Alphabot : Ticket "+jiraKey+" has been created for you",
	 * "grt.goblin@gmail.com", "incraised", null, c); } else { msg =
	 * "Failed to create ticket. :-("; } } else { msg =
	 * "Failed to create ticket. :-("; }
	 * 
	 * logger.info(msg);
	 * 
	 * data.setUserData(jiraResp); DialogFlowFulfillmentResponse r = new
	 * DialogFlowFulfillmentResponse(); r.setDisplayText(msg); r.setSpeech(msg);
	 * r.setData(data);
	 * 
	 * response = new HttpEntity<DialogFlowFulfillmentResponse>(r,
	 * jiraResp.getHeaders()); return response; }
	 * 
	 * private HttpEntity<GroupCreateResponse> createGroup(Response webhookInput) {
	 * 
	 * HttpHeaders headers = new HttpHeaders();
	 * headers.setContentType(MediaType.APPLICATION_JSON); OriginalRequest
	 * originalRequest = webhookInput.getOriginalRequest();
	 * logger.info("Request received to create a group.");
	 * 
	 * if(originalRequest != null) {
	 * if(originalRequest.getSource().contains("telegram") ||
	 * originalRequest.getSource().contains("slack")) { logger.
	 * info("Create group is not supported for client other than rocket chat.");
	 * return new HttpEntity<GroupCreateResponse>(headers); } }
	 * 
	 * try { HttpEntity<GroupCreate> request = getGroupCreateRequest(webhookInput);
	 * GroupCreateResponse createGroupResponse =
	 * template.postForObject(AllUrls.ROCKET_CREATE_GROUP_ENDPOINT, request,
	 * GroupCreateResponse.class); HttpEntity<GroupCreateResponse> resp = new
	 * HttpEntity<GroupCreateResponse>(createGroupResponse, headers); return resp;
	 * 
	 * } catch (Exception e) { logger.
	 * error("Error processing while creating new group, using object from dialogflow"
	 * + webhookInput); return new HttpEntity<GroupCreateResponse>(headers); } }
	 * 
	 * private HttpEntity<GroupCreate> getGroupCreateRequest(Response dfObject) {
	 * HttpHeaders headers = new HttpHeaders(); headers.add("X-Auth-Token",
	 * adapter.getLoginData().getAuthToken()); headers.add("X-User-Id",
	 * adapter.getLoginData().getUserId());
	 * headers.setContentType(MediaType.APPLICATION_JSON);
	 * 
	 * Map<String, String> parameters = dfObject.getResult().getParameters();
	 * List<String> groupChatUses = new ArrayList<String>();
	 * 
	 * for(Entry<String, String> e : parameters.entrySet()) {
	 * if(e.getKey().contains("userIdForGroupChat")) {
	 * groupChatUses.add(e.getValue()); } } GroupCreate gc = new GroupCreate();
	 * gc.setReadOnly("false");
	 * gc.setName("CustomerSupport"+System.currentTimeMillis());
	 * gc.setMembers(groupChatUses);
	 * 
	 * return new HttpEntity<GroupCreate>(gc, headers); }
	 *
	 */
	
}
