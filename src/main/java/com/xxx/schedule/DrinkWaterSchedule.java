package com.xxx.schedule;

import love.forte.simbot.api.sender.BotSender;
import love.forte.simbot.bot.BotManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author mpj
 * @version V1.0
 * @date 2022/2/23 15:05
 * @since jdk1.8
 **/
@Component
public class DrinkWaterSchedule {
	@Resource
	private BotManager botManager;

	/**
	 * 每一分钟提醒一次: 0 0/1 * * * ?
	 * 每一小时提醒一次: 0 0 0/1 * * ?
	 */
	@Scheduled(cron = "0 0 8,9,10,11,13,14,15,16,17,18,20,21 * * ?")
	public void drinkWater() {
		BotSender sender = botManager.getDefaultBot().getSender();
		sender.SENDER.sendGroupMsg("779746363", "兄弟们该喝水了！！！");
		sender.SENDER.sendGroupMsg("639075263", "兄弟们该喝水了！！！");
	}
}
