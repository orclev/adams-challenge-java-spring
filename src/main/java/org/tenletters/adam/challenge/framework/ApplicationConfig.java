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
	
	private <T> RedisTemplate<String,T> getRedisTemplate(final Class<T> type) {
		RedisTemplate<String, T> template = new RedisTemplate<String, T>();
		template.setValueSerializer(new JacksonJsonRedisSerializer<T>(type));
		template.setKeySerializer(new StringRedisSerializer());
		template.setConnectionFactory(jedisConnectionFactory());
		return template;
	}
	
	@Bean(name="userTemplate")
	public RedisTemplate<String, User> redisUserTemplate() {
		return getRedisTemplate(User.class);
	}
	
	@Bean(name="userLinkTemplate")
	public RedisTemplate<String, UserLink> redisUserLinkTemplate() {
		return getRedisTemplate(UserLink.class);
	}
}
