package cn.malt.code.pojo.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 *
 * @author 蜡笔不小新Rm
 */
@Data
@Builder
public class ProductDTO {

    private Long id;

    private String name;

    private BigDecimal price;  // 数值类型

    private BigDecimal discount;

}
