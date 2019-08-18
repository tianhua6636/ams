package com.th.ams.base;

import com.th.ams.AmsApplicationTests;
import com.th.ams.model.CityModel;
import org.springframework.stereotype.Service;

/**
 * @author : wangtianhua
 * @date : 2019/08/18 14:30
 * @description : DOTO
 */
@Service
public class lombokTest extends AmsApplicationTests {
	@Override
	public void contextLoads() {
		CityModel city = new CityModel();
		city.setId(123456);
		city.setPageNumber(10);
		city.setCityCode(null);
		System.out.println(city.toString());
	}
}
