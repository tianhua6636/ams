package com.th.ams.model;


import lombok.*;

/**
 * @author : wangtianhua
 * @date : 2019/08/18 13:01
 * @description : DOTO
 */
@Data
@NonNull
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class CityModel extends BaseModel{
	private static final long serialVersionUID = -2124213217525364829L;

	private Integer id;
	private String name;
	private Integer parentId;
	private String shortName;
	private Integer levelType;
	private String cityCode;
	private String zipCode;
	private String longitude;
	private String latitude;
	private String pinyin;
	private Integer status;
}
