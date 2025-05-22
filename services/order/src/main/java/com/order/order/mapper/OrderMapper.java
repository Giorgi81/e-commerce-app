package com.order.order.mapper;

import com.order.order.dto.OrderRequestDTO;
import com.order.order.dto.OrderResponseDTO;
import com.order.order.entity.Orders;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Orders toOrder(OrderRequestDTO orderRequestDTO);

    OrderResponseDTO toOrderResponseDTO(Orders order);

}
