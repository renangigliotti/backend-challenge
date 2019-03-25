package com.invillia.acme.integration;

import com.invillia.acme.domain.commands.*;
import com.invillia.acme.domain.entity.OrderStatus;
import com.invillia.acme.domain.exceptions.PaymentPendingException;
import com.invillia.acme.domain.exceptions.PaymentRequiredException;
import com.invillia.acme.domain.queries.OrderQuery;
import com.invillia.acme.domain.services.OrderService;
import com.invillia.acme.domain.services.PaymentService;
import com.invillia.acme.domain.services.StoreService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PaymentServiceTest {

    @Autowired
    private StoreService storeService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private PaymentService paymentService;

    private UUID storeId;
    private UUID orderId;

    private final String CREDITCARD = "9625481265489632";
    private final String ADDRESS = "Center";
    private final String DESCRIPTION = "SHOE";
    private final BigDecimal PRICE = new BigDecimal(10);

    @Before
    public void setup() {
        CreateStoreCommand storeCommand = new CreateStoreCommand();
        storeCommand.setName("name");
        storeCommand.setAddress("address");

        storeId = storeService.create(storeCommand);

        CreateOrderCommand orderCommand = new CreateOrderCommand();
        orderCommand.setAddress(ADDRESS);
        orderCommand.setStoreId(storeId);
        orderCommand.setItems(new ArrayList<>());

        OrderItemCommand item = new OrderItemCommand();
        item.setDescription(DESCRIPTION);
        item.setPrice(PRICE);

        orderCommand.getItems().add(item);

        orderId = orderService.create(orderCommand);
    }

    @Test(expected = PaymentRequiredException.class)
    public void testRefundOrderPaymentRequired() {
        CreateOrderRefundCommand command = new CreateOrderRefundCommand();
        command.setOrderId(orderId);

        paymentService.refund(command);
    }

    @Test(expected = PaymentPendingException.class)
    public void testRefundOrderPaymentPending() {
        CreatePaymentCommand command = new CreatePaymentCommand();
        command.setOrderId(orderId);
        command.setCreditCardNumber(CREDITCARD);
        paymentService.create(command);

        CreateOrderRefundCommand refundCommand = new CreateOrderRefundCommand();
        refundCommand.setOrderId(orderId);

        paymentService.refund(refundCommand);
    }

    @Test
    public void testRefundCompleted() {
        CreatePaymentCommand command = new CreatePaymentCommand();
        command.setOrderId(orderId);
        command.setCreditCardNumber(CREDITCARD);
        UUID id = paymentService.create(command);

        paymentService.complete(id);

        CreateOrderRefundCommand refundCommand = new CreateOrderRefundCommand();
        refundCommand.setOrderId(orderId);

        paymentService.refund(refundCommand);

        OrderQuery order = orderService.find(orderId);

        Assert.assertEquals(order.getStatus(), OrderStatus.REFUNDED.toString());
    }
}
