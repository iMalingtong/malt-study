package cn.malt.code.demo;


import cn.malt.code.mapstruct.UserMapStruct;
import cn.malt.code.pojo.bo.UserBO;
import cn.malt.code.pojo.vo.UserVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 集合映射
 * MapStruct
 *
 * @author 蜡笔不小新Rm
 */
public class MapStructDemo04 {

    /**
     * 集合映射
     * MapStruct 和 BeanUtils耗时对比
     *
     * @param args 参数
     */
    public static void main(String[] args) {

        UserBO xiaoXinBO = UserBO.builder()
                .id(1L)
                .username("小新")
                .email("xiaoXin.com")
                .age(3)
                .createTime(new Date())
                .build();

        UserBO fenJianBO = UserBO.builder()
                .id(2L)
                .username("风间")
                .email("fenJian.com")
                .age(18)
                .createTime(new Date())
                .build();

        List<UserBO> userBOList = new ArrayList<>();
        userBOList.add(xiaoXinBO);
        userBOList.add(fenJianBO);


        // BO =》 VO
        // 测试代码执行所需时间，单位（秒）
        long mStart = System.currentTimeMillis();
        List<UserVO> userVOList = UserMapStruct.INSTANCE.toVOList(userBOList);
        System.out.println("（MapStruct）耗时：" + (System.currentTimeMillis() - mStart) + "ms");
        userVOList.stream().forEach(System.out::println);
    }
}
