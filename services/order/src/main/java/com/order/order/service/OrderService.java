package com.order.order.service;

import com.order.order.config.CustomerClient;
import com.order.order.config.PaymentClient;
import com.order.order.config.ProductClient;
import com.order.order.dto.OrderConfirmationDTO;
import com.order.order.dto.OrderLineRequestDTO;
import com.order.order.dto.OrderRequestDTO;
import com.order.order.dto.OrderResponseDTO;
import com.order.order.dto.PaymentRequestDTO;
import com.order.order.dto.PurchaseRequestDTO;
import com.order.order.dto.PurchaseResponseDTO;
import com.order.order.entity.Orders;
import com.order.order.exception.BusinessException;
import com.order.order.mapper.OrderMapper;
import com.order.order.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper orderMapper;
    private final OrderLineService orderLineService;
    private final OrderKafkaProducer orderKafkaProducer;
    private final PaymentClient paymentClient;


    public Long createOrder(OrderRequestDTO orderRequestDTO) {

         var customer = this.customerClient.getCustomerById(orderRequestDTO.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order:: No customer exists with the provided ID"));

        List<PurchaseResponseDTO> purchasedProducts = this.productClient.getPurchases(orderRequestDTO.products());

         Orders order = orderRepository.save(orderMapper.toOrder(orderRequestDTO));

         for(PurchaseRequestDTO purchaseRequestDTO: orderRequestDTO.products()){
             orderLineService.saveOrderLine(
                     new OrderLineRequestDTO(
                             null,
                             order.getId(),
                             purchaseRequestDTO.productId(),
                             purchaseRequestDTO.quantity()


                     )
             );
         }

         paymentClient.createPayment(new PaymentRequestDTO(
                 orderRequestDTO.amount(),
                 orderRequestDTO.paymentMethod(),
                 order.getId(),
                 order.getReference(),
                 customer
         ));
         orderKafkaProducer.send(
                 new OrderConfirmationDTO(
                     orderRequestDTO.reference(),
                         orderRequestDTO.amount(),
                         orderRequestDTO.paymentMethod(),
                         customer,
                         purchasedProducts

                 )
         );

         return order.getId();
    }

    public Page<OrderResponseDTO> getOrders(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Orders> orders = orderRepository.findAll(pageable);
        return orders.map(orderMapper::toOrderResponseDTO);

    }

    public OrderResponseDTO getOrder(Long id) {
        return orderRepository
                .findById(id)
                .map(orderMapper::toOrderResponseDTO)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
    }
}
