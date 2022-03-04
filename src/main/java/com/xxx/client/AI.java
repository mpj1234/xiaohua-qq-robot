package com.xxx.client;

import com.dtflys.forest.annotation.BaseRequest;
import com.dtflys.forest.annotation.Get;
import com.dtflys.forest.annotation.Query;
import com.xxx.Pojo.AIChat;

/**
 * @author mpj
 * @version V1.0
 * @date 2022/2/23 14:04
 * @since jdk1.8
 **/
@BaseRequest(baseURL = "http://api.qingyunke.com/api.php?key=free&appid=0&")
public interface AI {
	@Get("msg={0}")
	AIChat getAIChat(@Query("msg") String msg);
}
