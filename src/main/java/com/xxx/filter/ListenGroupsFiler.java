package com.xxx.filter;

import com.xxx.properties.ListenGroupsProperties;
import love.forte.simbot.api.message.containers.GroupContainer;
import love.forte.simbot.api.message.events.MsgGet;
import love.forte.simbot.core.intercept.FixedRangeGroupedListenerInterceptor;
import love.forte.simbot.intercept.InterceptionType;
import love.forte.simbot.listener.ListenerInterceptContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author mpj
 * @version V1.0
 * @date 2022/3/5 20:10
 * @since jdk1.8
 **/
@Component
@ConditionalOnClass(ListenGroupsProperties.class)
@EnableConfigurationProperties(ListenGroupsProperties.class)
public class ListenGroupsFiler extends FixedRangeGroupedListenerInterceptor {
	@Resource
	private ListenGroupsProperties listenGroupsProperties;

	private List<String> groups;

	@PostConstruct
	public void init() {
		this.groups = Arrays.asList(listenGroupsProperties.getGroups());
	}

	@NotNull
	@Override
	protected String[] getGroupRange() {
		return new String[]{"group1"};
	}

	@NotNull
	@Override
	protected InterceptionType doIntercept(@NotNull ListenerInterceptContext context, @Nullable String group) {
		MsgGet msgGet = context.getMsgGet();
		String code = "";
		if (msgGet instanceof GroupContainer) {
			GroupContainer groupContainer = (GroupContainer) msgGet;
			code = groupContainer.getGroupInfo().getGroupCode();
		}
		return groups.contains(code) ? InterceptionType.PASS : InterceptionType.INTERCEPT;
	}
}
