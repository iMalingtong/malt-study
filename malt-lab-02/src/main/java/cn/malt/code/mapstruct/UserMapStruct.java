package cn.malt.code.mapstruct;


import cn.malt.code.pojo.bo.UserBO;
import cn.malt.code.pojo.vo.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

/**
 *
 * @author 蜡笔不小新Rm
 */
@Mapper
public interface UserMapStruct {

    UserMapStruct INSTANCE = Mappers.getMapper(UserMapStruct.class);

    UserBO toBO(UserVO userVO);

    @Mapping(source = "age", target = "ageDoc", qualifiedByName = "ageToDesc")
    @Mapping(source = "createTime", target = "createTime", dateFormat = "yyyy-MM-dd HH:mm")
    UserVO toVO(UserBO userBO);

    List<UserVO> toVOList(List<UserBO> userBOs);

    Set<UserVO> toVOSet(Set<UserBO> userBOs);

    @Named("ageToDesc")
    default String ageToDesc(Integer age) {
        if (age < 18) {
            return "未成年人";
        } else if (age < 60) {
            return "成年人";
        } else {
            return "老年人";
        }
    }
}
