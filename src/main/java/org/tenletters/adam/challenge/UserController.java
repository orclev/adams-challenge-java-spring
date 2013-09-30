package org.tenletters.adam.challenge;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tenletters.adam.challenge.model.User;
import org.tenletters.adam.challenge.model.UserLink;

@Controller
public class UserController {
	
	@Autowired
	@Qualifier("userTemplate")
	private RedisTemplate<String,User> userTemplate;
	
	@Autowired
	@Qualifier("userLinkTemplate")
	private RedisTemplate<String,UserLink> userLinkTemplate;
	
	@RequestMapping(value="/users", method = RequestMethod.GET
			, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Set<UserLink> getUsers() {
		return userLinkTemplate.opsForSet().members("users");
	}
	
	@RequestMapping(value="/user/{id}", method = RequestMethod.GET
			, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public User getUser(@PathVariable final String id) {
		return userTemplate.opsForValue().get("user:" + id);
	}
	
	@RequestMapping(value="/fillWithMockData", method = RequestMethod.GET)
	@ResponseBody
	public void fillWithMockData() {
		userLinkTemplate.delete("users");
		userLinkTemplate.boundSetOps("users").add(
				  new UserLink("dave","1200")
				, new UserLink("mike", "1201")
				, new UserLink("stubby", "1202")
				, new UserLink("ray", "1203"));
		userTemplate.delete("user:1200");
		userTemplate.delete("user:1201");
		userTemplate.delete("user:1202");
		userTemplate.delete("user:1203");
		Map<String,User> mockData = new HashMap<String,User>(4);
		mockData.put("user:1200", new User("dave", "1200", "foo"));
		mockData.put("user:1201", new User("mike", "1201", "bar"));
		mockData.put("user:1202", new User("stubby", "1202", "baz"));
		mockData.put("user:1203", new User("ray", "1203", "quux"));
		userTemplate.opsForValue().multiSet(mockData);
	}
}
