package cn.malt.code.demo;


import cn.malt.code.mapstruct.RoleMapStruct;
import cn.malt.code.pojo.bo.RoleBO;
import cn.malt.code.pojo.vo.RoleVO;
import org.springframework.beans.BeanUtils;

/**
 * 字段名不一致映射
 * MapStruct 和 BeanUtils耗时对比
 *
 * @author 蜡笔不小新Rm
 */
public class MapStructDemo02 {

    /**
     * 字段名不一致映射
     * MapStruct 和 BeanUtils耗时对比
     *
     * @param args 参数
     */
    public static void main(String[] args) {

        RoleBO xiaoXinBO = RoleBO.builder()
                .id(1L)
                .code("xiaoXin")
                .name("小新")
                .status(1)
                .build();

        // BO =》 VO
        // 测试代码执行所需时间，单位（秒）
        long mStart = System.currentTimeMillis();
        RoleVO mapVO = RoleMapStruct.INSTANCE.toRoleVO(xiaoXinBO);
        System.out.println("（MapStruct）耗时：" + (System.currentTimeMillis() - mStart) + "ms");
        System.out.println("（MapStruct）：" + mapVO.toString());


        // 字段名称不一样，无法转换，需手动编写转换方法   （不推荐使用）
        // Apache Commons BeanUtils 字段名不一样可以转换
        // BeanUtils.copyProperty(target, "name", BeanUtils.getProperty(source, "userName"));
        // 感兴趣的可以自己了解下，这里主要演示MapStruct和BeanUtils的耗时对比
        RoleVO beanVO = RoleVO.builder().build();
        long bStart = System.currentTimeMillis();
        BeanUtils.copyProperties(xiaoXinBO, beanVO);
        System.out.println("（BeanUtils）耗时：" + (System.currentTimeMillis() - bStart) + "ms");
        System.out.println("（BeanUtils）：" + beanVO.toString());

    }
}
