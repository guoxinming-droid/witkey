/**
 * Copyright (C) 2018-2020
 * All rights reserved, Designed By www.yixiang.co

 */
package co.zhenxi.tools.service;

import co.zhenxi.common.service.BaseService;
import co.zhenxi.tools.domain.EmailConfig;
import co.zhenxi.tools.domain.vo.EmailVo;
import org.springframework.scheduling.annotation.Async;

/**
* @author hupeng
* @date 2020-05-13
*/
public interface EmailConfigService  extends BaseService<EmailConfig> {
    /**
     * 更新邮件配置
     * @param emailConfig 邮件配置
     * @param old 旧的配置
     * @return EmailConfig
     */
    void update(EmailConfig emailConfig, EmailConfig old);

    /**
     * 查询配置
     * @return EmailConfig 邮件配置
     */
    EmailConfig find();

    /**
     * 发送邮件
     * @param emailVo 邮件发送的内容
     * @param emailConfig 邮件配置
     * @throws Exception /
     */
    @Async
    void send(EmailVo emailVo, EmailConfig emailConfig) throws Exception;
}
