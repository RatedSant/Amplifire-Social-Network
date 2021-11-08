package revature.model;

/*
 The sole purpose of this class is sending custom response messages to the Client
 */
public class ASNHttpResponse {
	
	private int status;
	private String message;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public ASNHttpResponse(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
}
