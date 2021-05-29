/*
 * Copyright (c) 2021. Sebastián Arancibia-Arzic
 */

package cl.ucn.disc.dsm.sarancibia.services;

import java.util.List;

import cl.ucn.disc.dsm.sarancibia.model.News;

public interface Contracts {

    /**
     * Get the list of News
     *
     * @param size of the list
     * @return the List of News
     */
    List<News> retrieveNews(Integer size);

    /**
     * Save one News to the System.
     * @param news to save
     */
    void save(News news);

}
