package com.code.solvers.queue;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
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


import com.code.solvers.nlp.model.Response;
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
						.setReadTimeout(Duration.ofMillis(AllUrls.TIMEOUT_IN_MILLISECOND))
						.build();
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
	
	/**
	 * End point to be called by chat client to kick start the flow
	 * @param message
	 */
	@RequestMapping("/rocket/adapter/incoming/mom")
	public ResponseEntity<String> generateMOM(@RequestBody RocketIncomingMessage message) {
		
		logger.info("Message received from chat client.");
		
		try {
			botServerQueue.push(message);
			
			logger.info("Message pushed");
			
			/*RocketChatReact reactMsg = new RocketChatReact();
			reactMsg.setEmoji(":smile:");
			reactMsg.setMessageId(message.getMessage_id());
			reactMsg.setShouldReact(true);
			
			reactToChat(reactMsg);*/
			ResponseEntity<String> response = getSummary("");
			return postMessageToChat(response.getBody(), message);
			
		} catch (Exception e) {
			logger.error("Error processing rocket adapter incoming message", e);
		}
		
		return new ResponseEntity<String>(HttpStatus.OK);
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
			template.postForObject(AllUrls.ROCKET_CHAT_REACT_ENDPOINT, request, String.class);
			
			
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
	
	private ResponseEntity<String> postMessageToChat(String msg, RocketIncomingMessage inMessage) {
		RocketChatPostMessage chatPostMessage = new RocketChatPostMessage();
		chatPostMessage.setChannel("#dailystandup");
		chatPostMessage.setRoomId(inMessage.getChannel_id());
		chatPostMessage.setText(msg);
		
		try {
			HttpHeaders headers = getHeaders();
			HttpEntity<RocketChatPostMessage> request = new HttpEntity<>(chatPostMessage, headers);
			
			ResponseEntity<String> response = template.exchange(
					AllUrls.ROCKET_POST_MESSAGE_ENDPOINT, 
					HttpMethod.POST, 
					request, 
					String.class);
			
			logger.info("Message pushed to rocket");
			
			return response;
			
		} catch (Exception e) {
			logger.error("Error processing outgoing message", e);
		}
		
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	private ResponseEntity<String> getSummary(String content) {
		logger.info("Requesting NLP engine to get the summary.");
		try {
			HttpHeaders headers = getCommonHeaders(null);
			HttpEntity<RocketChatPostMessage> request = new HttpEntity<>(headers);
			Map<String, String> params = new HashMap<>();
			
			content = "Jules: Hey kids! How you boys doin’?\r\n"
					+ "Jules: (Speaking to the guy laying on the couch) Hey, keep chillin’. You know who we are? We’re associates of your business partner Marsellus Wallace. You do remember your business partner don’t you? Let me take a wild guess here. You’re Brett, right?\r\n"
					+ "Brett: Yeah.\r\n"
					+ "Jules: I thought so. You remember your business partner Marsellus Wallace, don’t you, Brett?\r\n"
					+ "Brett: Yeah, yeah, I remember him.\r\n"
					+ "Jules: Good. Looks like me an Vincent caught you boys at breakfast. Sorry about that. Whatcha havin’?\r\n"
					+ "Brett: Hamburgers.";
			params.put("input", content);
			
			ResponseEntity<String> response = template.exchange(
					AllUrls.NLP_SUMMARY_ENDPOINT, 
					HttpMethod.POST, 
					request, 
					String.class,
					params);
			
			return response;
			
		} catch (Exception e) {
			logger.error("Error processing NLP engine to get the summary", e);
		}
		
		return new ResponseEntity<String>(HttpStatus.OK);
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
	
	private HttpHeaders getCommonHeaders(HttpHeaders headers) {
		if (headers == null) {
			headers = new HttpHeaders();
		}
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		return headers;
	}
	
	private HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers = getCommonHeaders(headers);
		headers.add("X-Auth-Token", adapter.getLoginData().getAuthToken());
		headers.add("X-User-Id", adapter.getLoginData().getUserId());
		
		return headers;
	}

	private void invokeJiraWebhook(Response webhookInput) {
		String jiraKey = webhookInput.getIssue().getKey();
		String jiraStatus = webhookInput.getIssue().getFields().getStatus().getName();
		logger.info("Received Jira webhook call for :" + jiraKey);
		
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
		sb.append("\r\n*Ticket Link: * https://thegreatsolvers.atlassian.net/browse/" + jiraKey);
		sb.append("\r\n*Status: *" + jiraStatus);
		
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
		
		Issuetype issueType = new Issuetype();
		issueType.setName("Task");
		
		Project project = new Project();
		project.setKey("FIRST");
		
		Fields fields = new Fields();
		fields.setProject(project);
		fields.setIssuetype(issueType);
		fields.setSummary(title);
		fields.setDescription(desc);
		Assignee assignee = new Assignee();
		assignee.setName("admin");
		
		jiraMessage.setFields(fields);
		
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
	
}
