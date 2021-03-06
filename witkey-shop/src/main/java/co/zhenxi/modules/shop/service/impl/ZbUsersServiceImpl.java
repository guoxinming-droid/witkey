/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.service.impl;

import co.zhenxi.annotation.AnonymousAccess;
import co.zhenxi.modules.shop.domain.ZbUsers;
import co.zhenxi.common.service.impl.BaseServiceImpl;
import co.zhenxi.tools.domain.vo.EmailVo;
import co.zhenxi.tools.service.EmailConfigService;
import co.zhenxi.tools.utils.MD5Util;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import lombok.AllArgsConstructor;
import co.zhenxi.dozer.service.IGenerator;
import com.github.pagehelper.PageInfo;
import co.zhenxi.common.utils.QueryHelpPlus;
import co.zhenxi.utils.FileUtil;
import co.zhenxi.modules.shop.service.ZbUsersService;
import co.zhenxi.modules.shop.service.dto.ZbUsersDto;
import co.zhenxi.modules.shop.service.dto.ZbUsersQueryCriteria;
import co.zhenxi.modules.shop.service.mapper.ZbUsersMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
// 默认不使用缓存
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;
import java.util.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

/**
* @author guoke
* @date 2020-07-22
*/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "zbUsers")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbUsersServiceImpl extends BaseServiceImpl<ZbUsersMapper, ZbUsers> implements ZbUsersService {

    private final IGenerator generator;

    private final ZbUsersMapper zbUsersMapper;

    private final EmailConfigService emailConfigService;


    private final DefaultKaptcha defaultKaptcha;



    @Override
    //@Cacheable
    public Map<String, Object> queryAll(ZbUsersQueryCriteria criteria, Pageable pageable) {
        getPage(pageable);
        PageInfo<ZbUsers> page = new PageInfo<>(queryAll(criteria));
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getList(), ZbUsersDto.class));
        map.put("totalElements", page.getTotal());
        return map;
    }


    @Override
    //@Cacheable
    public List<ZbUsers> queryAll(ZbUsersQueryCriteria criteria){
        return baseMapper.selectList(QueryHelpPlus.getPredicate(ZbUsers.class, criteria));
    }


    @Override
    public void download(List<ZbUsersDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (ZbUsersDto zbUsers : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("用户名", zbUsers.getName());
            map.put("用户邮箱", zbUsers.getEmail());
            map.put("用户手机注册", zbUsers.getMobile());
            map.put("用户邮箱认证状态", zbUsers.getEmailStatus());
            map.put("用户密码", zbUsers.getPassword());
            map.put("支付密码", zbUsers.getAlternatePassword());
            map.put("随机码", zbUsers.getSalt());
            map.put("账户状态 ", zbUsers.getStatus());
            map.put("找回密码邮件过期时间", zbUsers.getOverdueDate());
            map.put("找回密码随机码", zbUsers.getValidationCode());
            map.put("重置密码邮件过期时间", zbUsers.getExpireDate());
            map.put("重置密码验证随机码", zbUsers.getResetPasswordCode());
            map.put("token", zbUsers.getRememberToken());
            map.put("最后一次登录时间", zbUsers.getLastLoginTime());
            map.put("注册来源", zbUsers.getSource());
            map.put("是否会员", zbUsers.getIsvip());
            map.put("创建时间", zbUsers.getCreatedAt());
            map.put("修改时间", zbUsers.getUpdatedAt());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    /**
     * 邮箱注册
     *
     * @param zbUsers
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<Object> loginByEmail(ZbUsers zbUsers) throws Exception {
        List<String> list = subUUID();
        String salt = list.get(0);
        String validationCode = list.get(1);
        String reSetPasswordCode = list.get(2);
        if(zbUsers!=null){
            zbUsers.setEmailStatus(0);
            zbUsers.setSalt(salt);
            zbUsers.setStatus(1);
            //来自PC注册
            zbUsers.setSource(1);
            zbUsers.setIsvip(0);
            zbUsers.setValidationCode(validationCode);
            zbUsers.setResetPasswordCode(reSetPasswordCode);
            zbUsers.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            zbUsers.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            //插入之后一概返回ID 带入超链接
            zbUsersMapper.insert(zbUsers);
            //sendEmail(zbUsers);
            //Thread.sleep(2000);
            return new ResponseEntity<>(HttpStatus.OK);

            //return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    private String sendEmail(ZbUsers zbUsers) {
        String text = defaultKaptcha.createText();
        EmailVo emailVo = new EmailVo();
        ArrayList<String> email = new ArrayList<>();
        email.add(zbUsers.getEmail());
        emailVo.setTos(email);
        emailVo.setSubject("邮箱验证");
        emailVo.setContent("<div style=\"height: auto;\n" +
                "\t\t\twidth: 820px;\n" +
                "\t\t\tmin-width: 820px;\n" +
                "\t\t\tmargin: 0 auto;\n" +
                "\t\t\tmargin-top: 20px;\n" +
                "            border: 1px solid #eee;\">\n" +
                "    <div style=\"padding: 10px;padding-bottom: 0px;\">\n" +
                "        <p style=\"margin-bottom: 10px;padding-bottom: 0px;\">尊敬的用户，您好：</p>\n" +
                "        <p style=\"text-indent: 2em; margin-bottom: 10px;\">您正在申请邮箱验证，您的验证码为：</p>\n" +
                "            <p style=\"text-align: center;\n" +
                "\t\t\tfont-family: Times New Roman;\n" +
                "\t\t\tfont-size: 22px;\n" +
                "\t\t\tcolor: #C60024;\n" +
                "\t\t\tpadding: 20px 0px;\n" +
                "\t\t\tmargin-bottom: 10px;\n" +
                "\t\t\tfont-weight: bold;\n" +
                "\t\t\tbackground: #ebebeb;\">"+text+"</p>\n" +
                "\n" +
                "\n" +
                "        <div class=\"foot-hr hr\" style=\"margin: 0 auto;\n" +
                "\t\t\tz-index: 111;\n" +
                "\t\t\twidth: 800px;\n" +
                "\t\t\tmargin-top: 30px;\n" +
                "\t\t\tborder-top: 1px solid #DA251D;\">\n" +
                "        </div>\n" +
                "\n" +
                "\n" +
                "    </div>\n" +
                "</div>");
        try {
            emailConfigService.send(emailVo, emailConfigService.find());
            //Thread.sleep(2000);
            return text;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<String> subUUID() {
        List<String> list =new ArrayList<>();
        for (int i = 4; i < 9; i++) {
            System.out.println(i);
            String s = UUID.randomUUID().toString();
            list.add (s.substring(s.length()-i,s.length()));
            i++;
        }
        return list;
    }

    /**
     * 手机注册
     *
     * @param zbUsers
     */
    @Override
    public ZbUsers loginByPhoneNum(ZbUsers zbUsers) {
        //先校验验证码
        List<String> list = subUUID();
        zbUsers.setSalt(list.get(0));
        zbUsers.setValidationCode(list.get(1));
        zbUsers.setResetPasswordCode(list.get(2));
        zbUsers.setEmailStatus(0);
        zbUsers.setStatus(1);
        zbUsers.setSource(1);
        zbUsers.setIsvip(0);
        zbUsers.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        zbUsers.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        zbUsersMapper.insert(zbUsers);

        return zbUsers;
    }

    /**
     * 查看是不是数据库得邮箱
     *
     * @param zbUsers
     */
    @Override
    public Map isEmail(ZbUsers zbUsers) {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>(1);
        stringObjectHashMap.put("flag",false);
        //phoneNum非空判断
        if(!"".equals(zbUsers.getEmail()) && zbUsers.getEmail().length()>0){
            ZbUsers zbUsers1 = zbUsersMapper.selectByEmail(zbUsers.getEmail());
            if(zbUsers1!=null && zbUsers.getEmail().equals(zbUsers1.getEmail())){
                stringObjectHashMap.put("flag",true);
            }
        }
        return stringObjectHashMap;
    }

    /**
     * 看看用户是不是vip
     *
     * @param uid
     * @return
     */
    @Override
    public ZbUsers isVIP(Integer uid) {
        return zbUsersMapper.selectById(uid);
    }

    @Override
    public ZbUsers selectByMobile(String mobile) {
        return zbUsersMapper.selectByMobile(mobile);
    }

    /**
     * @param id       用户id
     * @param password 更改的密码
     */
    @Override
    public void updatePassword(Integer id, String password) {
        ZbUsers zbUsers = new ZbUsers();
        zbUsers.setPassword(password);
        UpdateWrapper<ZbUsers> zbUsersUpdateWrapper = new UpdateWrapper<ZbUsers>().eq("zb_users.id",id);
        zbUsersMapper.update(zbUsers,zbUsersUpdateWrapper);
    }

    /**
     * 登录
     *
     * @param zbUsers
     */
    @Override
    public ZbUsers login(ZbUsers zbUsers) {
        String name = zbUsers.getName();
        String password = zbUsers.getPassword();
        //记住密码返回是加密后的 判断是否是加密过的密码
        if(password.length()==32){
            ZbUsers select = zbUsersMapper.select(name, password);
            if (select == null) {
                throw new RuntimeException("用户名或密码不正确");
            }
            return select;
        }else {
            String md5password = MD5Util.md5Encrypt32Lower(password);
            if (name == null || password == null) {
                throw new RuntimeException("用户名或密码不正确");
            }
            ZbUsers zbUsers1 = zbUsersMapper.select(name, md5password);

            if (zbUsers1 == null) {
                throw new RuntimeException("用户名或密码不正确");
            }
            return zbUsers1;
        }
    }

    @Override
    public Map<String, Object> sendEmailVCode(String email) {
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        if(email==null){
            linkedHashMap.put("flag",false);
            linkedHashMap.put("message","邮箱错误或为空");
            return linkedHashMap;
        }
        ZbUsers zbUsers = new ZbUsers();
        zbUsers.setEmail(email);
        String code = sendEmail(zbUsers);

        if(code == null) {
            linkedHashMap.put("flag",false);
            linkedHashMap.put("message","发送失败，请稍后再试");
            return linkedHashMap;
        }
        linkedHashMap.put("flag",true);
        linkedHashMap.put("message","发送成功");
        linkedHashMap.put("code",code);

        return linkedHashMap;
    }
}
