package cn.malt.code.pojo.vo;


import lombok.Builder;
import lombok.Data;

/**
 * 用户dto
 *
 * @author 蜡笔不小新Rm
 */
@Data
@Builder
public class UserVO {

    private Long id;

    private String username;

    private String email;

    private Integer age;

    private String ageDoc;

    private String rolesName;

    private String createTime;
}
