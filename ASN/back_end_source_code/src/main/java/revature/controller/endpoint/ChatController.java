package revature.controller.endpoint;

import java.io.IOException;

import java.util.HashMap;

import java.util.Map;
import java.util.Set;

import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
@CrossOrigin(origins={"http://localhost:4200", "http://127.0.0.1:4200", "http://34.125.179.241:9015", "http://35.236.194.104:8080"})

public class ChatController extends TextWebSocketHandler {
	
	Map<String, WebSocketSession> sessions;
	
	public ChatController() {
		sessions = new HashMap<String, WebSocketSession>();
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {		
		sessions.put(session.getId(), session);		
		updateSession(sessions.size());
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {		
		sessions.remove(session.getId());
		updateSession(sessions.size());
	}		
	

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws InterruptedException, IOException {		
		
		String payload = message.getPayload();
		JSONObject jsonObject = new JSONObject(payload);			
		broadcastMessage(jsonObject);			
	}
	
	public void updateSession(int connected) {		
		Set<String> keys = sessions.keySet();
		for(String key: keys) {
			WebSocketSession s = sessions.get(key);
			try {
				JSONObject j = new JSONObject();
				j.put("action", "update");
				j.put("connected", connected);
				s.sendMessage(new TextMessage( j.toString() ));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}				
	}
	
	public void broadcastMessage(JSONObject payload) {
		Set<String> keys = sessions.keySet();
		for(String key: keys) {
			WebSocketSession s = sessions.get(key);
			try {				
				s.sendMessage(new TextMessage( payload.toString() ));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}