/*
 * Copyright (c) 2021. Sebastián Arancibia-Arzic
 */

package cl.ucn.disc.dsm.sarancibia.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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

}
