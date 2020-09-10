package co.zhenxi.modules.shop.service.impl;

import co.zhenxi.common.service.impl.BaseServiceImpl;
import co.zhenxi.modules.shop.domain.ZbNav;
import co.zhenxi.modules.shop.service.ZbNavService;
import co.zhenxi.modules.shop.service.mapper.ZbNavMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: Jia Hao Hao
 * @Date: 2020-08-31 14:34
 * @Description: ZbNavServiceImpl
 **/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "zbnav")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbNavServiceImpl extends BaseServiceImpl<ZbNavMapper, ZbNav> implements ZbNavService {

    private final ZbNavMapper zbNavMapper;
    /**
     * 查询全部
     *
     * @return List<ZbNav>
     */
    @Override
    public List<ZbNav> queryAll() {
        return zbNavMapper.selectList(null);
    }
}
