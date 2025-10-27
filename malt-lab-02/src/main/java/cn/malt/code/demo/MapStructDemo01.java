package cn.malt.code.demo;


import cn.malt.code.mapstruct.UserMapStruct;
import cn.malt.code.pojo.bo.UserBO;
import cn.malt.code.pojo.vo.UserVO;
import org.springframework.beans.BeanUtils;

/**
 * 字段名一致映射
 * MapStruct 和 BeanUtils耗时对比
 *
 * @author 蜡笔不小新Rm
 */
public class MapStructDemo01 {

    /**
     * 字段名一致映射
     * MapStruct 和 BeanUtils耗时对比
     *
     * @param args 参数
     */
    public static void main(String[] args) {

        UserVO xiaoXinVO = UserVO.builder()
                .id(1L)
                .username("小新")
                .email("<EMAIL>")
                .age(18)
                .build();

        // VO =》 BO
        // 测试代码执行所需时间，单位（秒）
        long mStart = System.currentTimeMillis();
        UserBO mapBo = UserMapStruct.INSTANCE.toBO(xiaoXinVO);
        System.out.println("（MapStruct）耗时：" + (System.currentTimeMillis() - mStart) + "ms");
        System.out.println("（MapStruct）：" + mapBo.toString());


        UserBO beanBo = UserBO.builder().build();
        long bStart = System.currentTimeMillis();
        BeanUtils.copyProperties(xiaoXinVO, beanBo);
        System.out.println("（BeanUtils）耗时：" + (System.currentTimeMillis() - bStart) + "ms");
        System.out.println("（BeanUtils）：" + mapBo.toString());

    }
}
