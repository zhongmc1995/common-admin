package com.zmc.utils;

import com.zmc.common.entity.Resource;
import com.zmc.common.vo.Menu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhongmc on 2017/6/30.
 */
public class MenuHelper {
    /**
     * 将资源转化为Menu
     * @param resources
     * @return
     */
    public static List<Menu> translateResourceToMenu(List<Resource> resources){
        List<Menu> menus = new ArrayList<Menu>();
        for (Resource r : resources){
            Menu menu = new Menu();
            menu.setId(r.getId());
            menu.setName(r.getName());
            menu.setType(r.getType());
            menu.setUrl(r.getUrl());
            menu.setParent_id(r.getParent_id());
            menu.setParent_ids(r.getParent_ids());
            menus.add(menu);
        }
        return menus;
    }

    /**
     * 菜单拼接
     * 1.将资源按照rank分类放在List数组中
     * 2.将数组从后往前遍历查找父菜单并拼接
     * @param resources
     * @return
     */
    public static List<Menu> buildMenuTree(List<Resource> resources){
        List<Menu> menus = translateResourceToMenu(resources);
        //找到最大的等级
        Integer maxRank = findMaxRank(menus);//-1
        if(maxRank > 0){
            //按等级归类菜单 arr
            List<Menu>[] arr = new List[maxRank];
            for (int i = 0;i<maxRank;i++){
                arr[i] = new ArrayList<Menu>();
            }
            //初始化
            for (Menu m : menus){
                Integer rank = m.getRank();
                arr[rank-1].add(m);
            }

            for (int i = arr.length-1;i>0;i--){
                List<Menu> list = arr[i];
                List<Menu> pre_list = arr[i-1];

                for (Menu m : list){

                    for (Menu pre_m : pre_list){
                        if(m.getParent_ids().contains(pre_m.getParent_ids()) && m.getParent_id()==pre_m.getId()){
                            pre_m.addChild(m);
                        }
                    }
                }
                arr[i] = null;// let gc work;
            }
            return arr[0];
        }else {
            return null;
        }

    }

    /**
     * 查找菜单的最大级数
     * @param menus
     * @return
     */
    public static Integer findMaxRank(List<Menu> menus){
        if (menus!=null&&menus.size()>0){
            Integer max = menus.get(0).getRank();
            for (int i = 1;i<menus.size();i++){
                if (max<menus.get(i).getRank()){
                    max = menus.get(i).getRank();
                }
            }
            return max;
        }
        return 0;
    }


}
