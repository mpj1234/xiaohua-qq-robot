package com.xxx.client;

import com.dtflys.forest.annotation.Get;

/**
 * @author mpj
 * @version V1.0
 * @date 2022/3/4 21:32
 * @since jdk1.8
 **/
public interface LoveStoryClient {
	@Get("https://chp.shadiao.app/api.php")
	String getLoveStory();
}
