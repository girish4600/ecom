package org.gsk.order.orderline.service;

import lombok.extern.slf4j.Slf4j;
import org.gsk.order.orderline.conversion.OrderLineMapper;
import org.gsk.order.orderline.model.OrderLineRequest;
import org.gsk.order.orderline.repository.OrderLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderLineServiceImpl implements OrderLineService {

    @Autowired
    private OrderLineRepository lineRepository;

    @Autowired
    private OrderLineMapper mapper;

    @Override
    public Integer saveOrderLine(OrderLineRequest orderLineRequest) {
        log.info("Inside orderLineService :: ");
        return lineRepository.save(mapper.entityToDTO(orderLineRequest)).getId();
    }
}
