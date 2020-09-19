package co.zhenxi.modules.shop.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @Author: Jia Hao Hao
 * @Date: 2020-09-15 16:47
 * @Description: ZbShopPackage
 **/
@Data
@TableName("zb_shop_package")
public class ZbShopPackage {


    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;


    private Integer shopId;

    private Integer packageId;

    private String privilegesPackage;

    private Integer uid;

    private String username;

    private Integer duration;

    private BigDecimal price;

    private Timestamp startTime;

    private Timestamp endTime;

    private Integer status;

    private Timestamp createdAt;

    private Timestamp updatedAt;

}
