package cn.malt.code.mapstruct;

import cn.malt.code.pojo.bo.RoleBO;
import cn.malt.code.pojo.bo.UserBO;
import cn.malt.code.pojo.dto.UserRoleDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * 用户角色映射结构
 *
 * @author 蜡笔不小新Rm
 */
@Mapper
public interface UserRoleMapStruct {

    UserRoleMapStruct INSTANCE =Mappers.getMapper(UserRoleMapStruct.class);

    @Mapping(source = "userBO.id", target = "userId")
    @Mapping(source = "userBO.username", target = "userName")
    @Mapping(source = "roleBO.name", target = "roleName")
    UserRoleDto toUserRoleDto(UserBO userBO, RoleBO roleBO);

}
