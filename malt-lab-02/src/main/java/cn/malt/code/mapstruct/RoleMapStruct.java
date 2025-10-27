package cn.malt.code.mapstruct;

import cn.malt.code.pojo.bo.RoleBO;
import cn.malt.code.pojo.vo.RoleVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * 角色映射结构
 *
 * @author 蜡笔不小新Rm
 */
@Mapper
public interface RoleMapStruct {

    RoleMapStruct INSTANCE = Mappers.getMapper(RoleMapStruct.class);

    /**
     * 角色vo转角色bo
     *
     * @param roleVO 角色vo
     * @return 角色bo
     */
    @Mapping(source = "roleId", target = "id")
    @Mapping(source = "roleCode", target = "code")
    @Mapping(source = "roleName", target = "name")
    RoleBO toRoleBO(RoleVO roleVO);

    /**
     * 角色bo转角色vo
     *
     * @param roleBO 角色bo
     * @return 角色vo
     */
    @Mapping(source = "id", target = "roleId")
    @Mapping(source = "code", target = "roleCode")
    @Mapping(source = "name", target = "roleName")
    RoleVO toRoleVO(RoleBO roleBO);


}
