package kata.domains.timeline;

import kata.User;
import kata.dao.TimelineMessage;
import kata.domains.ValidatesDomainLogic;

/**
 *
 * @author mark
 */
public class TimelinePublishCommand {
	
	private User user;
	private String message;
	private ValidatesDomainLogic callback;
	
	public TimelinePublishCommand (ValidatesDomainLogic cb, User user, String message) {
		this.user = user;
		this.message = message;
		this.callback = cb;
	}

	public TimelineMessage exec() {
		if (!this.validate()) {
			return null;
		}
		
		TimelineMessage tm = new TimelineMessage();
		tm.setUser(this.user);
		tm.setMessage(this.message);
		tm.save();
		return tm;
	}

	public boolean validate() {
		if (this.user == null) {
			this.callback.onValidationFailure(user, "User cannot be null");
			return false;
		}
		if (this.message.equals("")) {
			this.callback.onValidationFailure(user, "Message cannot be null");
			return false;
		}
		//TODO: rate limit
		//TODO: scan for banned content
		//TODO: other domain logic verification
		return true;
	}
}
