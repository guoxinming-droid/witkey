package co.zhenxi.modules.shop.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: Jia Hao Hao
 * @Date: 2020-08-28 16:23
 * @Description: ZbDistrict
 **/
@Data
@TableName("zb_district")
public class ZbDistrict implements Serializable {
    /** id  */
    @TableId
    private int id;

    /** 夫id  */
    private int uPid;

    /** 地区名称  */
    private String name;

    /** 地区类型  */
    private int type;

    /** 排序  */
    private int disPlayOrder;

    /** 地区拼音  */
    private String spelling;

    /** 子地区集合  */
    private List list;

    public void copy(ZbConfig source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
