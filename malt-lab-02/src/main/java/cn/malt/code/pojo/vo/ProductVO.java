package cn.malt.code.pojo.vo;

import lombok.Builder;
import lombok.Data;

/**
 *
 * @author 蜡笔不小新Rm
 */
@Data
@Builder
public class ProductVO {

    private Long id;

    private String name;

    private String priceStr;

    private String discountStr;
}
