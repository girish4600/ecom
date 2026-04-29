package org.gsk.order.service;

import lombok.extern.slf4j.Slf4j;
import org.gsk.order.conversion.OrderMapper;
import org.gsk.order.handler.BusinessException;
import org.gsk.order.kafka.NotificationProducer;
import org.gsk.order.kafka.model.OrderNotificationRequest;
import org.gsk.order.model.OrderRequest;
import org.gsk.order.model.PaymentMethod;
import org.gsk.order.model.customer.CustomerResponse;
import org.gsk.order.model.payment.PaymentRequest;
import org.gsk.order.model.product.ProductPurchaseRequest;
import org.gsk.order.orderline.model.OrderLineRequest;
import org.gsk.order.orderline.service.OrderLineService;
import org.gsk.order.outer_api.CustomerService;
import org.gsk.order.outer_api.PaymentService;
import org.gsk.order.outer_api.ProductService;
import org.gsk.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private OrderRepository repository;
    @Autowired
    private OrderLineService orderLineServiceImpl;

    @Autowired
    private OrderMapper mapper;

    @Autowired
    private NotificationProducer notificationProducer;

    @Override
    public String placeOrder(OrderRequest orderRequest) {
        //check the customer (openFeign) -->  customer microservice
        log.info("getCustomer from customer-service fot ID  :: {}", orderRequest.customerId());
        var customer = customerService.findById(orderRequest.customerId())
                .orElseThrow(() -> new BusinessException(String.format("Unable to Create Order :: Customer not found :: %d", orderRequest.customerId())));

        //purchase the products -->  product microservice
        log.info("======================== Calling purchaseProduct ========================");
        var purchaseList = productService.purchaseProduct(orderRequest.purchaseRequest());
        log.info("======================== completed purchaseProduct ========================");

        //persis order
        var order = repository.save(mapper.dtoToOrderEntity(orderRequest));
        //persist order lines
        log.info("======================== save orderLines ========================");
        for (ProductPurchaseRequest purchaseRequest : orderRequest.purchaseRequest()) {
            orderLineServiceImpl.saveOrderLine(new OrderLineRequest(
                    null,
                    order.getId(),
                    purchaseRequest.productId(),
                    purchaseRequest.quantity(),
                    order
            ));
        }
        log.info("======================== save orderLines completed ========================");
        var paymentRequest = new PaymentRequest(
                orderRequest.amount(),
                orderRequest.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );
        //start payment process
        log.info("======================== Calling payment creation ========================");
        paymentService.createPayment(paymentRequest);
        log.info("======================== payment creation completed ========================");
        //send order confirmation -->  notification microservice
        notificationProducer.sendNotification(
                new OrderNotificationRequest( order.getId(),
                        orderRequest.reference(),
                        orderRequest.paymentMethod(),
                        orderRequest.amount(),
                        orderRequest.customerId())

        );
        return order.getId().toString();
    }

    @Override
    public String getCustomer(Integer customerId) {
        log.info("getCustomer from customer-service fot ID   :: " + customerId);
        var customer = customerService.findById(customerId);
        return customer.toString();
    }
}
