/**
* Copyright (C) 2018-2020
* All rights reserved, Designed By 臻希
* 注意：
* 本软件为臻希开发研制
*/
package co.zhenxi.modules.shop.rest;

import co.zhenxi.annotation.AnonymousAccess;
import co.zhenxi.dozer.service.IGenerator;
import co.zhenxi.logging.aop.log.Log;
import co.zhenxi.modules.shop.domain.ZbFinancial;
import co.zhenxi.modules.shop.domain.ZbOrder;
import co.zhenxi.modules.shop.domain.ZbSubOrder;
import co.zhenxi.modules.shop.service.*;
import co.zhenxi.modules.shop.service.dto.ZbCashoutQueryCriteria;
import co.zhenxi.modules.shop.service.dto.ZbFinancialDto;
import co.zhenxi.modules.shop.service.dto.ZbFinancialQueryCriteria;
import co.zhenxi.utils.DateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
* @author Guoxm
* @date 2020-07-16
*/
@AllArgsConstructor
@Api(tags = "财务流水管理")
@RestController
@RequestMapping("/api/zbFinancial")
public class ZbFinancialController {

    private final ZbFinancialService zbFinancialService;
    private final IGenerator generator;
    private final ZbCashoutService zbCashoutService;
    private final ZbOrderService zbOrderService;
    private final ZbSubOrderService zbSubOrderService;
    private final ZbUserDetailService zbUserDetailService;


    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','zbFinancial:list')")
    public void download(HttpServletResponse response, ZbFinancialQueryCriteria criteria) throws IOException {
        zbFinancialService.download(generator.convert(zbFinancialService.queryAll(criteria), ZbFinancialDto.class), response);
    }

    @GetMapping
    @Log("查询财务流水")
    @ApiOperation("查询财务流水")
    @PreAuthorize("@el.check('admin','zbFinancial:list')")
    public ResponseEntity<Object> getZbFinancials(ZbFinancialQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbFinancialService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增财务流水")
    @ApiOperation("新增财务流水")
    @PreAuthorize("@el.check('admin','zbFinancial:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ZbFinancial resources){
        return new ResponseEntity<>(zbFinancialService.save(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改财务流水")
    @ApiOperation("修改财务流水")
    @PreAuthorize("@el.check('admin','zbFinancial:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ZbFinancial resources){
        zbFinancialService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除财务流水")
    @ApiOperation("删除财务流水")
    @PreAuthorize("@el.check('admin','zbFinancial:del')")
    @DeleteMapping
    public ResponseEntity<Object> deleteAll(@RequestBody Integer[] ids) {
        Arrays.asList(ids).forEach(id->{
            zbFinancialService.removeById(id);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }



    @GetMapping("/financeRecharge")
    @Log("查询充值记录")
    @ApiOperation("查询充值记录")
    //@PreAuthorize("@el.check('admin','financeRecharge:list')")
    @AnonymousAccess
    public ResponseEntity<Object> financeRecharge(ZbFinancialQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbFinancialService.financeRecharge(criteria,pageable),HttpStatus.OK);
    }


    @GetMapping("/financeWithdraw")
    @Log("查询提现记录")
    @ApiOperation("查询提现记录")
    //@PreAuthorize("@el.check('admin','zbCashout:list')")
    @AnonymousAccess
    public ResponseEntity<Object> financeWithdraw(ZbCashoutQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(zbCashoutService.financeWithdraw(criteria,pageable),HttpStatus.OK);
    }

    @GetMapping("/financeProfit")
    @Log("查询利润统计")
    @ApiOperation("查询利润统计")
    //@PreAuthorize("@el.check('admin','zbCashout:list')")
    @AnonymousAccess
    public ResponseEntity<Object> financeProfit(String start, String end,  String from, Pageable pageable){
        return new ResponseEntity<>(zbOrderService.financeProfit(start,end,from,pageable),HttpStatus.OK);
    }


    @GetMapping("/financeStatement")
    @Log("查询网站收支")
    @ApiOperation("查询网站收支")
    //@PreAuthorize("@el.check('admin','zbCashout:list')")
    @AnonymousAccess
    public ResponseEntity<Object> financeStatement(){
        Map map = new HashMap();
        String startTime = DateUtils.parseDateToStr("yyyy-MM-dd",getServerStartDate(-7));
        String endTime = DateUtils.parseDateToStr("yyyy-MM-dd",getServerStartDate(-1));
        //收支表
        List<ZbFinancial> zbFinancialList = zbFinancialService.financeStatement(startTime,endTime);
        List<ZbOrder> zbOrderList = zbOrderService.financeStatement(startTime,endTime);
        List<ZbSubOrder> zbSubOrderList = zbSubOrderService.financeStatement(startTime,endTime);
        map.put("zbFinancialList",zbFinancialList);
        map.put("zbOrderList",zbOrderList);
        map.put("zbSubOrderList",zbSubOrderList);
        return  new ResponseEntity<>(map,HttpStatus.OK);
    }

    public  Date getServerStartDate(Integer lDate) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar c = new GregorianCalendar();
        java.util.Date date = new java.util.Date();
        c.setTime(date);
        c.add(Calendar.SECOND, lDate*24*3600);
        java.util.Date dateBefore = c.getTime();//两种把Calendar转化成Long类型的方法（毫秒）
        return  dateBefore;
    }


    @GetMapping("/rechargeList")
    @Log("查询利润统计")
    @ApiOperation("查询利润统计")
    //@PreAuthorize("@el.check('admin','zbCashout:list')")
    @AnonymousAccess
    public ResponseEntity<Object> rechargeList(String code ,String userName,String startTime,String endTime, Pageable pageable){
        return new ResponseEntity<>(zbFinancialService.rechargeList(code,userName,startTime,endTime,pageable),HttpStatus.OK);

    }




    @PutMapping("/confirmRechargeOrder")
    @Log("确认充值")
    @ApiOperation("确认充值")
    //@PreAuthorize("@el.check('admin','zbFinancial:edit')")
    @AnonymousAccess
    public ResponseEntity<Object> confirmRechargeOrder(@RequestParam("code") String code ){
        ZbOrder  zbOrder  = zbOrderService.getByCode(code);
        zbOrder.setStatus(1);
        zbOrderService.updateById(zbOrder);
        ZbFinancial zbFinancial = new ZbFinancial();
        zbFinancial.setAction(3);
        zbFinancial.setPayType(1);
        zbFinancial.setCash(BigDecimal.valueOf(zbOrder.getCash()));
        zbFinancial.setUid(zbOrder.getUid());
        zbFinancial.setCreatedAt(Timestamp.valueOf(DateUtils.getTime()));
        create(zbFinancial);
        Integer uid = zbOrder.getUid();
        BigDecimal balances = BigDecimal.valueOf(zbOrder.getCash());
        zbUserDetailService.updateUserDetail(uid,balances);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




    @GetMapping("/cashoutList")
    @Log("查询利润统计")
    @ApiOperation("查询利润统计")
    //@PreAuthorize("@el.check('admin','zbCashout:list')")
    @AnonymousAccess
    public ResponseEntity<Object> cashoutList(String cashoutType ,String userName,String startTime,String endTime, Pageable pageable){
        return new ResponseEntity<>(zbCashoutService.cashoutList(cashoutType,userName,startTime,endTime,pageable),HttpStatus.OK);

    }

    @GetMapping("/financeList")
    @Log("查询网站流水")
    @ApiOperation("查询网站流水")
    //@PreAuthorize("@el.check('admin','zbCashout:list')")
    @AnonymousAccess
    public ResponseEntity<Object> financeList(Integer actions ,String startTime,String endTime, Pageable pageable){
        return new ResponseEntity<>(zbFinancialService.financeList(actions,startTime,endTime,pageable),HttpStatus.OK);
    }

    @GetMapping("/userFinance")
    @Log("查询用户流水")
    @ApiOperation("查询用户流水")
    //@PreAuthorize("@el.check('admin','zbCashout:list')")
    @AnonymousAccess
    public ResponseEntity<Object> userFinance(String userName ,Integer action,String startTime,String endTime, Pageable pageable){
        return new ResponseEntity<>(zbFinancialService.userFinance(userName,action,startTime,endTime,pageable),HttpStatus.OK);
    }


}
