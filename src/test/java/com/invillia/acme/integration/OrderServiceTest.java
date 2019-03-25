package com.invillia.acme.integration;

import com.invillia.acme.domain.commands.CreateOrderCommand;
import com.invillia.acme.domain.commands.CreateStoreCommand;
import com.invillia.acme.domain.commands.OrderItemCommand;
import com.invillia.acme.domain.entity.OrderStatus;
import com.invillia.acme.domain.queries.OrderQuery;
import com.invillia.acme.domain.services.OrderService;
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
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private StoreService storeService;

    @Autowired
    private OrderService orderService;

    private UUID storeId;
    private UUID id;

    private final String ADDRESS = "Center";
    private final String DESCRIPTION = "SHOE";
    private final BigDecimal PRICE = new BigDecimal(10);

    @Before
    public void setup() {
        CreateStoreCommand storeCommand = new CreateStoreCommand();
        storeCommand.setName("name");
        storeCommand.setAddress("address");

        storeId = storeService.create(storeCommand);

        CreateOrderCommand command = new CreateOrderCommand();
        command.setAddress(ADDRESS);
        command.setStoreId(storeId);
        command.setItems(new ArrayList<>());

        OrderItemCommand item = new OrderItemCommand();
        item.setDescription(DESCRIPTION);
        item.setPrice(PRICE);

        command.getItems().add(item);

        id = orderService.create(command);
    }

    @Test
    public void testCreate() {
        OrderQuery order = orderService.find(id);

        Assert.assertEquals(order.getAddress(), ADDRESS);
        Assert.assertEquals(order.getStatus(), OrderStatus.PAYMENT_PENDING.toString());
    }

    @Test
    public void TestFind() {
        List<OrderQuery> orders = orderService.list();

        Assert.assertEquals(1, orders.size());
    }
}
