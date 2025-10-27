package cn.malt.code.mapstruct;

import cn.malt.code.pojo.dto.ProductDTO;
import cn.malt.code.pojo.vo.ProductVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * 产品映射结构
 *
 * @author 蜡笔不小新Rm
 */
@Mapper
public interface ProductMapStruct {

    ProductMapStruct INSTANCE = Mappers.getMapper(ProductMapStruct.class);

    /**
     * 字符串价格转数值
     * numberFormat 用于格式化数字
     * @param productVO 商品VO
     * @return 商品DTO
     */
    @Mapping(source = "priceStr", target = "price", numberFormat = "$#.00")
    @Mapping(source = "discountStr", target = "discount", numberFormat = "$#.00")
    ProductDTO toDTO(ProductVO productVO);

    /**
     * 数值价格转字符串
     * @param productDTO 商品DTO
     * @return 商品VO
     */
    @Mapping(source = "price", target = "priceStr", numberFormat = "$#,##0.00")
    @Mapping(source = "discount", target = "discountStr", numberFormat = "$#,##0.00")
    ProductVO toVO(ProductDTO productDTO);

}
