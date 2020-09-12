package co.zhenxi.modules.shop.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author: Jia Hao Hao
 * @Date: 2020-09-11 16:11
 * @Description: ZbEmployLocal
 **/
@Data
@TableName("zb_employ_local")
public class ZbEmployLocal {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer employId;

    private Integer uid;

    private Integer localId;
}
