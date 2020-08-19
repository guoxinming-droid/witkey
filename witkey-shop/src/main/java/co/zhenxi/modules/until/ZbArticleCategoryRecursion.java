package co.zhenxi.modules.until;

import co.zhenxi.modules.shop.service.dto.ZbArticleCategoryDto;

import java.util.ArrayList;
import java.util.List;

public class ZbArticleCategoryRecursion {

    static List<ZbArticleCategoryDto> childArticle = new ArrayList<ZbArticleCategoryDto>();

    public static List<ZbArticleCategoryDto> treeMenuList(List<ZbArticleCategoryDto> menuList, int pid) {
        for (ZbArticleCategoryDto mu : menuList) {
            //遍历出父id等于参数的id，add进子节点集合
            if (Integer.valueOf(mu.getPid()) == pid) {
                //递归遍历下一级
                treeMenuList(menuList, Integer.valueOf(mu.getId()));
                childArticle.add(mu);
            }
        }
        return childArticle;
    }
}
