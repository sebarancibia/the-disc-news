/*
 * Copyright (c) 2021. Sebastián Arancibia-Arzic
 */

package cl.ucn.disc.dsm.sarancibia.services;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;
import cl.ucn.disc.dsm.sarancibia.BaseTest;
import cl.ucn.disc.dsm.sarancibia.model.News;
import cl.ucn.disc.dsm.sarancibia.model.TestNews;


/**
 * The class.
 *
 * @author Sebastian Arancibia
 */
public final class TestContracts extends BaseTest {

    /**
     * The logger
     */
    private static final Logger log = LoggerFactory.getLogger(TestNews.class);

    /**
     * Testing the constructor.
     */
    public void testConstructor(){

        // Call the constructor
        Contracts contracts = new ContractsImplFaker();
        Assertions.assertNotNull(contracts, "Contracts null!");

    }

    /**
     * Testing the save.
     */
    @Test
    public void testSave(){

        log.debug("Testing the Save ..");

        //  The Contracts implementation
        Contracts contracts = new ContractsImplFaker();

        //  Fail to save, news null
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            contracts.save(null);
        });

        //  The Faker
        Faker faker = new Faker();

        final int N = 20;

        //  Add 20 news to the backend.
        for (int i = 0; i < N; i++) {

            //  1 News
            News news = new News(
                    faker.book().title(),
                    faker.book().publisher(),
                    faker.book().author(),
                    faker.internet().url(),
                    faker.internet().url(),
                    faker.book().genre(),
                    faker.dune().quote(),
                    ZonedDateTime.now(ZoneId.of("-3"))
            );

            log.info("News: {}: {}.", i, toString(news));

            //  Save into the backend
            contracts.save(news);

        }

        //  Verify the size of the list
        Assertions.assertEquals(10, contracts.retrieveNews(10).size());
        Assertions.assertEquals(1, contracts.retrieveNews(1).size());

        //  The size > the real size
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            Assertions.assertEquals(100, contracts.retrieveNews(100).size());
        });

        //  Negative or zero size
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            contracts.retrieveNews(-100);
            contracts.retrieveNews(0);
        });

        // Test duplicates
        News news = contracts.retrieveNews(1).get(0);
        Assertions.assertNotNull(news);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
           contracts.save(news);
        });

        //  Fail to save, news repeat
        /*
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            contracts.save(news);
        });
        */

        log.debug("Done.");
    }
}