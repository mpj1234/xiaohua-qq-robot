package com.xxx.listen;

import com.dtflys.forest.utils.StringUtils;
import com.xxx.Pojo.AIChat;
import com.xxx.Pojo.ChickenSoup;
import com.xxx.client.AIClient;
import com.xxx.client.ChickenSoupClient;
import com.xxx.client.LoveStoryClient;
import com.xxx.client.WeatherClient;
import love.forte.simbot.annotation.Filter;
import love.forte.simbot.annotation.ListenGroup;
import love.forte.simbot.annotation.OnGroup;
import love.forte.simbot.api.message.events.GroupMsg;
import love.forte.simbot.api.sender.MsgSender;
import love.forte.simbot.filter.MatchType;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
import java.util.Objects;

/**
 * @author mpj
 * @version V1.0
 * @date 2022/2/23 13:34
 * @since jdk1.8
 **/
@Component
@ListenGroup("group1")
public class MyGroupListen {

	@OnGroup
	@Filter(value = "时间", matchType = MatchType.EQUALS)
	public void groupDatePrint(GroupMsg groupMsg, MsgSender sender) {
		LocalDate localDate = LocalDate.now();
		LocalTime localTime = LocalTime.now();
		String printStr = "当前时间：" + localDate + " " + localTime;
		sender.SENDER.sendGroupMsg(groupMsg, printStr);
	}

	@Resource(description = "weatherClient")
	private WeatherClient weatherClient;
	final int WEATHER_FORMAT_LENGTH = 3;

	@OnGroup
	@Filter(value = "天气", matchType = MatchType.REGEX_FIND)
	public void getWeather(GroupMsg groupMsg, MsgSender sender) {
		String text = groupMsg.getText();
		if (StringUtils.isNotEmpty(text)) {
			String[] args = text.split(" ");
			if (args.length == WEATHER_FORMAT_LENGTH) {
				String province = args[1];
				String city = args[2];
				System.out.println(this.weatherClient);
				Map<String, Object> weather = this.weatherClient.getWeather(province, city);
				Map data = (Map) weather.get("data");
				Map forecast_24h = (Map) data.get("forecast_24h");
				Map today = (Map) forecast_24h.get("0");
				String result = "今天是" + today.get("day_weather") + "，最高温度" + today.get("max_degree") + "℃，最低温度" + today.get("min_degree") + "℃";
				sender.SENDER.sendGroupMsg(groupMsg, result);
			}
		}
	}

	@Resource
	private AIClient aiClient;

	@OnGroup
	@Filter(value = "ai|AI", matchType = MatchType.REGEX_FIND)
	public void getAIChat(GroupMsg groupMsg, MsgSender sender) {
		String text = groupMsg.getText();
		if (StringUtils.isNotEmpty(text)) {
			String substring = text.replace("ai", "");
			substring = substring.replace("AIClient", "");
			System.out.println(substring);
			String trim = substring.trim();
			AIChat aiChat = this.aiClient.getAIChat(trim);
			String content = aiChat.getContent();
			sender.SENDER.sendGroupMsg(groupMsg, content);
		}
	}

	@Resource
	private LoveStoryClient loveStoryClient;

	@OnGroup
	@Filter(value = "爱你", matchType = MatchType.REGEX_FIND)
	public void getLoveStory(GroupMsg groupMsg, MsgSender sender) {
		String result = loveStoryClient.getLoveStory();
		if (StringUtils.isNotEmpty(result)) {
			sender.SENDER.sendGroupMsg(groupMsg, result);
			return;
		}
		sender.SENDER.sendGroupMsg(groupMsg, "没有找到情话!");
	}

	@Resource
	private ChickenSoupClient chickenSoupClient;

	@OnGroup
	@Filter(value = "鸡汤", matchType = MatchType.REGEX_FIND)
	public void getChickenSoupClient(GroupMsg groupMsg, MsgSender sender) {
		ChickenSoup chickenSoup = chickenSoupClient.getChickenSoup();
		if (Objects.nonNull(chickenSoup)) {
			if (StringUtils.isNotEmpty(chickenSoup.getData())) {
				sender.SENDER.sendGroupMsg(groupMsg, chickenSoup.getData());
				return;
			}
		}
		sender.SENDER.sendGroupMsg(groupMsg, "没有查到鸡·汤!");
	}
}
