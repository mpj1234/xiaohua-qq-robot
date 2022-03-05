package com.xxx.properties;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author mpj
 * @version V1.0
 * @date 2022/3/5 20:08
 * @since jdk1.8
 **/
@Data
@ConfigurationProperties(prefix = "listen.groups")
public class ListenGroupsProperties {
	private String[] groups;
}
