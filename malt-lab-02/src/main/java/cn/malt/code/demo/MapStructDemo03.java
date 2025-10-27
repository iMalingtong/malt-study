package cn.malt.code.demo;


import cn.malt.code.mapstruct.UserRoleMapStruct;
import cn.malt.code.pojo.bo.RoleBO;
import cn.malt.code.pojo.bo.UserBO;
import cn.malt.code.pojo.dto.UserRoleDto;

/**
 * 多个源对象映射到一个目标对象
 * MapStruct
 *
 * @author 蜡笔不小新Rm
 */
public class MapStructDemo03 {

    /**
     * 多个源对象映射到一个目标对象
     *
     * @param args 参数
     */
    public static void main(String[] args) {

        RoleBO xiaoXinRoleBO = RoleBO.builder()
                .id(1L)
                .code("xiaoXin")
                .name("小新管理员")
                .status(1)
                .build();

        UserBO xiaoXinUserBO = UserBO.builder()
                .id(1L)
                .username("xiaoXin")
                .email("xiaoXin.com")
                .age(3)
                .build();

        // 测试代码执行所需时间，单位（秒）
        long mStart = System.currentTimeMillis();
        UserRoleDto mapVO = UserRoleMapStruct.INSTANCE.toUserRoleDto(xiaoXinUserBO, xiaoXinRoleBO);
        System.out.println("（MapStruct）耗时：" + (System.currentTimeMillis() - mStart) + "ms");
        System.out.println("（MapStruct）：" + mapVO.toString());
    }
}
