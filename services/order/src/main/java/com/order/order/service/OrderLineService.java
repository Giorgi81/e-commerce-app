package com.order.order.service;

import com.order.order.dto.OrderLineRequestDTO;
import com.order.order.dto.OrderLineResponseDTO;
import com.order.order.entity.OrderLine;
import com.order.order.mapper.OrderLineMapper;
import com.order.order.repository.OrderLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper orderLineMapper;


    public OrderLineResponseDTO saveOrderLine(OrderLineRequestDTO orderLineRequestDTO) {
        OrderLine orderLine = orderLineMapper.toOrderLine(orderLineRequestDTO);

        return orderLineMapper.toOrderLineResponseDTO(orderLine);
    }

    public List<OrderLineResponseDTO> findOrderLineByOrderId(Long id) {
        return orderLineRepository.findOrderLineByOrderId(id)
                .stream().map(orderLineMapper::toOrderLineResponseDTO).collect(Collectors.toList());
    }
}
