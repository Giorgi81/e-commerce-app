package com.customer.customer_service.mapper;


import com.customer.customer_service.dto.CustomerRequestDTO;
import com.customer.customer_service.dto.CustomerResponseDTO;
import com.customer.customer_service.entity.Customer;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Service;

@Mapper(componentModel = "spring")
public interface CustomerMapper {


    Customer toCustomer(CustomerRequestDTO customerRequestDTO);

    CustomerResponseDTO toCustomerResponseDTO(Customer customer);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void fromCustomerToDTO(CustomerRequestDTO customerRequestDTO, @MappingTarget Customer customer);


}
