/*
 * Copyright (c) 2021. Sebastián Arancibia-Arzic
 */

package cl.ucn.disc.dsm.sarancibia.services;

import java.util.ArrayList;
import java.util.List;

import cl.ucn.disc.dsm.sarancibia.model.News;

public final class ContractsImplFaker implements Contracts{


    /**
     * Get the list of News
     *
     * @param size of the list
     * @return the List of News
     */
    @Override
    public List<News> retrievNews(Integer size) {
        return new ArrayList<>();
    }

    /**
     * Save one News to the Sustem.
     *
     * @param news to save
     */
    @Override
    public void save(News news) {

    }
}
