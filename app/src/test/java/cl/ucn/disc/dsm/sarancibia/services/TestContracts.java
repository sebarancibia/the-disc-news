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
     * The Logger
     */
    private static final Logger log = LoggerFactory.getLogger(TestNews.class);

    /**
     * Testing the constructor.
     */
    @Test
    public void testConstuctor(){

        //Call the constructor
        Contracts contracts = new ContractsImplFaker();
        Assertions.assertNotNull(contracts,"Contracts null");

    }

    @Test
    public void testSave(){

        //The contracts implementation
        Contracts contracts = new ContractsImplFaker();

        // The Faker
        Faker faker = new Faker();

        // 1 News
        News news=
                new News(
                        faker.book().title(),
                        faker.book().publisher(),
                        faker.book().author(),
                        faker.internet().url(),
                        faker.internet().url(),
                        faker.book().genre(),
                        faker.dune().quote(),
                        //  FIXME: Check the current zone in Chile
                        ZonedDateTime.now(ZoneId.of("-3")));

        log.info("TheNews: {}." , toString(news));

        //  Save into the backend
        contracts.save(news);

        //  Fail to save, news null
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            contracts.save(null);
        });

        //  Verify the size of the list
        Assertions.assertEquals(1,contracts.retrieveNews(10).size());

        //  Fail to save, news repeat
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            contracts.save(news);
        });

    }

}
