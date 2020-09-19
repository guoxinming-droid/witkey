package co.zhenxi.modules.shop.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author: Jia Hao Hao
 * @Date: 2020-09-15 14:48
 * @Description: ZbPackAdvice
 **/
@Data
@Getter
@Setter
public class ZbPackAdvice extends ZbPackage {

    private List<ZbPrivileges> zbPrivilegesList;
}
