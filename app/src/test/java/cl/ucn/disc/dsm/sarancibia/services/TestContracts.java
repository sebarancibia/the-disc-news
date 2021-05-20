/*
 * Copyright (c) 2021. Sebastián Arancibia-Arzic
 */

package cl.ucn.disc.dsm.sarancibia.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

import cl.ucn.disc.dsm.sarancibia.model.News;

/**
 * The class.
 *
 * @author Sebastian Arancibia
 */
public final class TestContracts {

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

        // 1 News
        News news=
        new News(
                "The Title",
                "The Source",
                "The Author",
                "The Url",
                "The Image Url",
                "The Description",
                "The Content",
                ZonedDateTime.now(ZoneId.of("-3")));

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
