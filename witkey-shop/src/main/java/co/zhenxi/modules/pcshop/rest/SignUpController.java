package co.zhenxi.modules.pcshop.rest;

import co.zhenxi.annotation.AnonymousAccess;
import co.zhenxi.logging.aop.log.Log;
import co.zhenxi.modules.shop.domain.ZbUsers;
import co.zhenxi.modules.shop.service.ZbUsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author: Jia Hao Hao
 * @Date: 2020-09-01 09:02
 * @Description: SignUpController
 **/
@AllArgsConstructor
@Api(tags = "首页")
@RestController
@RequestMapping("/api/homePage")
public class SignUpController {

    private final ZbUsersService zbUsersService;

    @Log("邮箱注册")
    @ApiOperation("邮箱注册")
    //@PreAuthorize("@el.check('admin','zbAd:del')")
    @AnonymousAccess
    @PostMapping("/loginByEmail")
    public ResponseEntity<Object> loginByEmail(@RequestBody ZbUsers zbUsers) throws Exception {
        return zbUsersService.loginByEmail(zbUsers);
    }

    @Log("手机号注册")
    @ApiOperation("手机号注册")
    //@PreAuthorize("@el.check('admin','zbAd:del')")
    @AnonymousAccess
    @PostMapping("/loginByPhoneNum")
    public ResponseEntity<Object> loginByPhoneNum(@RequestBody ZbUsers zbUsers) throws Exception {
        zbUsersService.loginByPhoneNum(zbUsers);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Log("邮箱是否正确")
    @ApiOperation("邮箱是否正确")
    //@PreAuthorize("@el.check('admin','zbAd:del')")
    @AnonymousAccess
    @PostMapping("/isEmail")
    public Map isEmail(@RequestBody ZbUsers zbUsers) throws Exception {
        return zbUsersService.isEmail(zbUsers);
    }

    @Log("更新密码")
    @ApiOperation("更新密码")
    //@PreAuthorize("@el.check('admin','zbAd:del')")
    @AnonymousAccess
    @PostMapping("/updatePassword")
    public ResponseEntity<Object> updatePassword(@RequestBody ZbUsers zbUsers) throws Exception {
        zbUsersService.updatePassword(zbUsers.getId(),zbUsers.getPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
