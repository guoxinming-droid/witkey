package co.zhenxi.modules.shop.service.impl;

import co.zhenxi.common.service.impl.BaseServiceImpl;
import co.zhenxi.dozer.service.IGenerator;
import co.zhenxi.modules.shop.domain.ZbDistrict;
import co.zhenxi.modules.shop.service.ZbDistrictService;
import co.zhenxi.modules.shop.service.mapper.ZbDistrictMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Jia Hao Hao
 * @Date: 2020-08-28 16:30
 * @Description: ZbDistrictServiceIml
 **/
@Service
@AllArgsConstructor
//@CacheConfig(cacheNames = "ZbDistrict")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ZbDistrictServiceIml extends BaseServiceImpl<ZbDistrictMapper, ZbDistrict> implements ZbDistrictService {
    private final ZbDistrictMapper zbDistrictMapper;
    @Autowired
    private final RedisTemplate redisTemplate;

    private final IGenerator generator;

    @Override
    public List getDistrict() {
        List district1 = (List)redisTemplate.opsForList().range("district",0,-1);
        if(district1 != null){
            return district1;
        }
        List<ZbDistrict> district = zbDistrictMapper.getDistrict();
        redisTemplate.opsForList().rightPush("district",district);
        redisTemplate.persist("district");
        return district;
    }

    @Override
    public Map<String,Object> getDistrictById(Integer pid, Integer size) {
        PageHelper.startPage(0,size);
        Page<ZbDistrict> page = zbDistrictMapper.getAllByPid(pid);
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("content", generator.convert(page.getResult(), ZbDistrict.class));
        map.put("totalElements", page.getTotal());
        return map;
    }
}
