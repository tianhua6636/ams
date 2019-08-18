package com.th.ams.base;

import com.th.ams.AmsApplicationTests;
import com.th.ams.model.CityModel;
import com.th.ams.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;
import java.util.List;

/**
 * @author : wangtianhua
 * @date : 2019/08/18 14:59
 * @description : DOTO
 */
public class CityTest extends AmsApplicationTests {

	@Autowired
	private CityService cityService;

	@Override
	public void contextLoads() {
		List<CityModel> list = cityService.getCityList();
		Iterator iterator = list.iterator();
		while (iterator.hasNext()){
			System.out.println(iterator.next());
		}

	}
}
