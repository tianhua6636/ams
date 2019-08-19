package com.th.ams.dao;

import com.th.ams.model.CityModel;

import java.util.List;

/**
 * @author : wangtianhua
 * @date : 2019/08/18 14:43
 * @description : DOTO
 */
public interface CityDao {
	/**
	 * 功能描述: <br>
	 * 获取全部城市信息
	 * @author : wangtianhua
	 * @date : 2019/08/18 20:26
	 * @params : []
	 * @return : java.util.List<com.th.ams.model.CityModel>
	 */
	List<CityModel> getCityList();
}
