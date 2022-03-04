package com.xxx.client;

import com.dtflys.forest.annotation.Get;
import com.dtflys.forest.annotation.Query;

import java.util.Map;

/**
 * @author mpj
 * @version V1.0
 * @date 2022/2/23 13:39
 * @since jdk1.8
 **/
public interface WeatherClient {
 @Get("https://wis.qq.com/weather/common?source=pc&weather_type=observe%7Cforecast_24h&province={0}&city={1}")
 Map<String, Object> getWeather(@Query String province, @Query String city);
}
