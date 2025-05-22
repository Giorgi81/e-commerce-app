package com.order.order.mapper;

import com.order.order.dto.OrderLineRequestDTO;
import com.order.order.dto.OrderLineResponseDTO;
import com.order.order.entity.OrderLine;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderLineMapper {

    OrderLine toOrderLine(OrderLineRequestDTO orderLineRequestDTO);

    OrderLineResponseDTO toOrderLineResponseDTO(OrderLine order);

}
