package com.xxx.client;

import com.dtflys.forest.annotation.Get;
import com.dtflys.forest.annotation.Request;
import com.xxx.Pojo.ChickenSoup;

/**
 * @author mpj
 * @version V1.0
 * @date 2022/3/4 21:53
 * @since jdk1.8
 **/
public interface ChickenSoupClient {
	@Request(
			url = "https://www.iamwawa.cn/home/dujitang/ajax",
			type = "get",
			headers = {
					"accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
					"user-agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.99 Safari/537.36"
			}
	)
	ChickenSoup getChickenSoup();
}
