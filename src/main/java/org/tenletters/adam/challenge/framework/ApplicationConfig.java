package org.tenletters.adam.challenge.framework;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.tenletters.adam.challenge.model.User;
import org.tenletters.adam.challenge.model.UserLink;

@Configuration
@ComponentScan("org.tenletters.adam.challenge")
@EnableWebMvc
public class ApplicationConfig {
	
	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory factory = new JedisConnectionFactory();
		factory.setUsePool(true);
		factory.setHostName("localhost");
		factory.setPort(6379);
		return factory;
	}
	
	@Bean(name="userTemplate")
	public RedisTemplate<String, User> redisUserTemplate() {
		RedisTemplate<String, User> template = new RedisTemplate<String, User>();
		template.setValueSerializer(new JacksonJsonRedisSerializer<User>(User.class));
		template.setKeySerializer(new StringRedisSerializer());
		template.setConnectionFactory(jedisConnectionFactory());
		return template;
	}
	
	@Bean(name="userLinkTemplate")
	public RedisTemplate<String, UserLink> redisUserLinkTemplate() {
		RedisTemplate<String, UserLink> template = new RedisTemplate<String, UserLink>();
		template.setValueSerializer(new JacksonJsonRedisSerializer<UserLink>(UserLink.class));
		template.setKeySerializer(new StringRedisSerializer());
		template.setConnectionFactory(jedisConnectionFactory());
		return template;
	}
}
