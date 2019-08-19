package com.th.ams.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author : wangtianhua
 * @date : 2019/08/15 1:59
 * @description : DOTO
 */
@Data
@ToString
public class LicenseModel implements Serializable {

	private String id;
	// 项目id
	private String projectId;
	// 公司名称
	private String companyName;
	// 机器码
	private List<Integer> serverCodes;
	// SN 号
	private String sn;
	// 状态码 （1-4）
	private String state;
	// 状态码说明
	private String stateDescription;
	// 授权类型 （0临时授权，1正式授权）
	private String type;
	// 授权类型说明
	private String typeDescription;
	// 是否购买技术服务
	private Boolean techSupportEnabled;
	// 技术服务类型 （8*5 工作日5天每天8小时，24*7 24小时服务）
	private String techSupportType;
	// 功能过期时间 //featureEndDate
	private Long featureExpireDate;
	// 令牌过期时间
	private Long tokenExpireDate;
	// 授权客户（商户）数量
	private Integer tenantCount;
	// 实时并发数
	private Integer portalTerminalConcurrency;
	// 员工的类型 （0:用户类型，1:终端类型）
	private String userType;
	// 员工用户数量
	private Integer userCount;
	// 员工终端数量
	private Integer userTerminalCount;
	// 是都启用用户名认证（portal）
	private Boolean portalUserEnabled;
	// 是否启用企业微信扫码认证（portal）
	private Boolean weChatWorkPortalEnabled;
	// 是否启用钉钉扫码认证（portal）
	private Boolean dingTalkPortalEnabled;
	// 商业访客授权方式 （1:商业访客终端数量，0:商业访客AP数量）
	private String businessVisitorGrantType;
	// 商业访客终端数量
	private Integer simpleVisitorTerminalCount;
	// 商业访客AP数量
	private Integer apCount;
	// 是否启用一键登录
	private Boolean oneKeyPortalEnabled;
	// 是否启用短信认证
	private Boolean smsPortalEnabled;
	// 是否启用微信认证
	private Boolean weixinPortalEnabled;
	// 是否Facebook认证
	private Boolean facebookPortalEnabled;
	//是否启用邮箱认证
	private Boolean emailPortalEnabled;
	// 企业访客数量
	private Integer enhancedVisitorTerminalCount;
	// 是否启用访客申请
	private Boolean visitorSelfHelpEnabled;
	// 是否启用协助扫码
	private Boolean qrCodeGuestEnabled;
	// 是否启用外部认证
	private Boolean externalPortalEnabled;
	// 外部认证方式 （0:外部认证终端数量，1:外部认证AP数量 ）
	private String externalAuthType;
	// 外部认证终端数量
	private Integer externalAuthTerminalCount;
	// 外部认证终端数量
	private Integer externalAuthApCount;
	// 是否启用APP认证
	private Boolean appAuthEnabled;
	// APP认证终端数量
	private Integer appAuthTerminalCount;
	// 是否启用802.1x认证
	private Boolean auth8021xEnabled;
	// 是否启用IP分配策略
	private Boolean ipAssignmentEnabled;
	// 是否启用高级策略
	private Boolean advancedPoliciesEnabled;
	// 是否启用报表
	private Boolean reportEnabled;
	// (无线)认证客户机数量
	private Integer wlanAccessServerCount;
	// (双因素)认证客户机数量
	private Integer strongAccessServerCount;
	// 短信令牌数量
	private Integer smsTokenCount;
	// 手机令牌数量
	private Integer mobileTokenCount;
	// 硬件令牌数量
	private Integer hardwareTokenCount;
	// 是否启用双机热备
	private Boolean haEnabled;
	// 是否启用审计联动
	private Boolean auditEnabled;
	// 是否启用 TACACS+
	private Boolean tacacsEnabled;
	// 其它信息（HTML格式）
	private String otherInfo;
	//
	private String techSupportTypeDescription;
	//
	private String techSupportState;
	//
	private String techSupportStateDescription;
	//
	private Long techSupportExpireDate;
	//
	private Long techSupportUpdateTime;
	// 创建时间
	private Long createTime;
	// 更新时间
	private Long updateTime;
	//
	private Long techSupportExpiredDate;
	//
	private Long featureExpiredDate;
	//
	private Long tokenExpiredDate;

	// 公司地址
	//private String companyAddress;
	// 技术服务到期时间
	//private Long serviceEndDate;
}
