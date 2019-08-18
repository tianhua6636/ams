package com.th.ams.service.impl;

import com.th.ams.dao.CityDao;
import com.th.ams.model.CityModel;
import com.th.ams.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : wangtianhua
 * @date : 2019/08/18 14:50
 * @description : DOTO
 */
@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private CityDao cityDao;

	@Override
	public List<CityModel> getCityList() {
		return cityDao.getCityList();
	}
}
