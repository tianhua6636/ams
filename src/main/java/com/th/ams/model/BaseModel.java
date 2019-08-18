package com.th.ams.model;

import lombok.*;

import java.io.Serializable;

/**
 * @author : wangtianhua
 * @date : 2019/08/18 13:02
 * @description : DOTO
 */
@Data
@NonNull
@NoArgsConstructor
@AllArgsConstructor
public class BaseModel implements Serializable {
	private static final long serialVersionUID = -3555993657643679263L;

	private String createTime;
	private String updateTime;
	private Integer pageNumber;
	private Integer pageSize;

}
