package com.invillia.acme.integration;

import com.invillia.acme.domain.commands.CreateStoreCommand;
import com.invillia.acme.domain.commands.UpdateStoreCommand;
import com.invillia.acme.domain.queries.StoreQuery;
import com.invillia.acme.domain.services.StoreService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StoreServiceTest {

    @Autowired
    private StoreService storeService;

    private UUID id;

    private final String NAME = "Americanas";
    private final String ADDRESS = "Center";

    @Before
    public void setup() {
        CreateStoreCommand command = new CreateStoreCommand();
        command.setName(NAME);
        command.setAddress(ADDRESS);

        id = storeService.create(command);
    }

    @Test
    public void testFindById() {
        StoreQuery store = storeService.find(id);

        Assert.assertEquals(store.getName(), NAME);
        Assert.assertEquals(store.getAddress(), ADDRESS);
    }

    @Test
    public void testList() {
        List<StoreQuery> stores = storeService.list(null, null);

        Assert.assertEquals(1, stores.size());

        stores = storeService.list(UUID.randomUUID().toString(), null);

        Assert.assertEquals(0, stores.size());

        stores = storeService.list(null, UUID.randomUUID().toString());

        Assert.assertEquals(0, stores.size());

        stores = storeService.list(UUID.randomUUID().toString(), UUID.randomUUID().toString());

        Assert.assertEquals(0, stores.size());

        stores = storeService.list(NAME, null);

        Assert.assertEquals(1, stores.size());

        stores = storeService.list(null, ADDRESS);

        Assert.assertEquals(1, stores.size());

        stores = storeService.list(NAME, ADDRESS);

        Assert.assertEquals(1, stores.size());
    }

    @Test
    public void testUpdateCommand() {
        final String NEW_NAME = "Walmart";
        final String NEW_ADDRESS = "River";

        UpdateStoreCommand command = new UpdateStoreCommand();
        command.setId(id);
        command.setName(NEW_NAME);
        command.setAddress(NEW_ADDRESS);

        storeService.update(command);

        StoreQuery query = storeService.find(id);

        Assert.assertEquals(query.getName(), NEW_NAME);
        Assert.assertEquals(query.getAddress(), NEW_ADDRESS);
    }
}
