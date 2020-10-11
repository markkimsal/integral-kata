/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kata;

import java.util.ArrayList;
import java.util.List;
import kata.dao.TimelineMessage;
import kata.domains.ValidatesDomainLogic;
import kata.domains.timeline.TimelinePublishCommand;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

/**
 *
 * @author mark
 */
public class PublishingFeatureTest {
	
	protected User alice = new User("123456", "alice");
	
	@Test
	public void scenario_alicePublishesMessagesToHerPersonalTimeline() {
		String expected = "I love the weather today.";
		given_aliceHasPublished(expected);
		List<TimelineMessage> messages = when_userViewsTimeline(alice);
		
		assertEquals(expected, messages.get(0).getMessage());	
	}
	
	/**
	 * Could return null on failure
	 * 
	 * @param message
	 * @return 
	 */
	public TimelineMessage given_aliceHasPublished(String message) {
			TimelinePublishCommand publish = new TimelinePublishCommand(
				(Object source, String err) -> {
					fail("Failed to publish to alice's timeline");
			},
			alice,
			message
		);
		return publish.exec();
	}

	public ArrayList<TimelineMessage> when_userViewsTimeline(User u) {
		return TimelineMessage.loadForUser(u.getId());
	}
}
