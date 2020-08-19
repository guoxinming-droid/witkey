/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.mapper;

import co.zhenxi.common.mapper.CoreMapper;
import co.zhenxi.modules.shop.domain.ZbMessageTemplate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
* @author guoke
* @date 2020-08-05
*/
@Repository
@Mapper
public interface ZbMessageTemplateMapper extends CoreMapper<ZbMessageTemplate> {

    @Update("UPDATE zb_message_template set  is_open = ${isOpen},updated_at = NOW() WHERE id = ${id}")
    Boolean changeIsOpen(Integer id, Integer isOpen);
    @Update("UPDATE zb_message_template set  is_on_site = ${isOnSite},updated_at = NOW() WHERE id = ${id}")
    Boolean changeIsOnSite(Integer id, Integer isOnSite);
    @Update("UPDATE zb_message_template set  is_send_email = ${isSendEmail},updated_at = NOW() WHERE id = ${id}")
    Boolean changeIsSendEmail(Integer id, Integer isSendEmail);
    @Update("UPDATE zb_message_template set  is_send_mobile = ${isSendMobile},updated_at = NOW() WHERE id = ${id}")
    Boolean changeIsSendMobile(Integer id, Integer isSendMobile);
}
