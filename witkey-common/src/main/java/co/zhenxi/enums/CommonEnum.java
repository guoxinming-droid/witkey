/**
 * Copyright (C) 2018-2020
 * All rights reserved, Designed By www.yixiang.co

 */
package co.zhenxi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author gxm
 * 通用枚举
 */
@Getter
@AllArgsConstructor
public enum CommonEnum {

	DEL_STATUS_0(0,"未删除"),
	DEL_STATUS_1(1,"已删除"),

	SHOW_STATUS_0(0,"未显示"),
	SHOW_STATUS_1(1,"显示");


	private Integer value;
	private String desc;


}
