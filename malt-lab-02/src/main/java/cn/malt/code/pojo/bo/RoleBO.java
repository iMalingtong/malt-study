package cn.malt.code.pojo.bo;


import lombok.Builder;
import lombok.Data;

/**
 * bo角色
 *
 * @author 蜡笔不小新Rm
 */
@Data
@Builder
public class RoleBO {

    private Long id;

    private String code;

    private String name;

    private Integer status;
}
