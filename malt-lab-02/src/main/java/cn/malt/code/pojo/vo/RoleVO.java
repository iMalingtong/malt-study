package cn.malt.code.pojo.vo;


import lombok.Builder;
import lombok.Data;

/**
 * 角色vo
 *
 * @author 蜡笔不小新Rm
 */
@Data
@Builder
public class RoleVO {

    private Long roleId;

    private String roleCode;

    private String roleName;

    private Integer roleStatus;
}
