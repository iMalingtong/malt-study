package cn.malt.code.pojo.bo;


import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * 用户bo
 *
 * @author 蜡笔不小新Rm
 */
@Data
@Builder
public class UserBO {

    private Long id;

    private String username;

    private String email;

    private Integer age;

    private Date createTime;
}
