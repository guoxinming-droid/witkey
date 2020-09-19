package co.zhenxi.modules.pcshop.rest;

import co.zhenxi.annotation.AnonymousAccess;
import co.zhenxi.logging.aop.log.Log;
import co.zhenxi.modules.shop.domain.ZbUsers;
import co.zhenxi.modules.shop.service.ZbUsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Jia Hao Hao
 * @Date: 2020-09-01 09:02
 * @Description: SignUpController
 **/
@AllArgsConstructor
@Api(tags = "首页")
@RestController
@RequestMapping("/api/signUp")
public class SignUpController {

    private final ZbUsersService zbUsersService;




    @Log("登录")
    @ApiOperation("登录")
    //@PreAuthorize("@el.check('admin','zbAd:del')")
    @AnonymousAccess
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody ZbUsers zbUsers, @RequestParam("state") String state,HttpServletRequest request) throws Exception {
        ZbUsers zbUsers1 = zbUsersService.login(zbUsers);
        HttpSession session = request.getSession();
        session.setAttribute("users_in_the_session",zbUsers1);
        session.setAttribute("state",state);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Log("退出")
    @ApiOperation("退出")
    //@PreAuthorize("@el.check('admin','zbAd:del')")
    @AnonymousAccess
    @GetMapping("/logout")
    public ResponseEntity<Object> logout(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        session.invalidate();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Log("记住密码")
    @ApiOperation("记住密码")
    //@PreAuthorize("@el.check('admin','zbAd:del')")
    @AnonymousAccess
    @GetMapping("/remember")
    public ResponseEntity<Object> remember(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        String state = (String)session.getAttribute("state");
        if(!"true".equals(state)){
            return null;
        }

        HashMap<String, Object> map = new HashMap<>(2);
        ZbUsers zbUsers = (ZbUsers) session.getAttribute("users_in_the_session");
        map.put("name",zbUsers.getName());
        map.put("password",zbUsers.getPassword());
        return new ResponseEntity<>(map,HttpStatus.OK);
    }

    @Log("获取用户名")
    @ApiOperation("获取用户名")
    //@PreAuthorize("@el.check('admin','zbAd:del')")
    @AnonymousAccess
    @PostMapping("/getUserName")
    public ResponseEntity<Object> getUserName(Integer uid, HttpServletRequest request) throws Exception {
        if(uid == null){
            HttpSession session = request.getSession();
            ZbUsers user = (ZbUsers)session.getAttribute("users_in_the_session");
            return new ResponseEntity<>(user.getName(),HttpStatus.OK);
        }
        return new ResponseEntity<>(zbUsersService.getById(uid).getName(),HttpStatus.OK);
    }

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
