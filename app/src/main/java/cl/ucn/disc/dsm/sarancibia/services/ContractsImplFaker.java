/*
 * Copyright (c) 2021. Sebastián Arancibia-Arzic
 */

package cl.ucn.disc.dsm.sarancibia.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cl.ucn.disc.dsm.sarancibia.model.News;

public final class ContractsImplFaker implements Contracts{

    /**
     * The List of News.
     */
    private final List<News> listNews = new ArrayList<>();

    /**
     * The constructor.
     */
    public ContractsImplFaker() {
        // Nothing here
    }

    /**
     * Get the list of News
     *
     * @param size of the list
     * @return the List of News
     */
    @Override
    public List<News> retrieveNews(Integer size) {

        // Preconditions
        if (size <= 0) {
            throw new IllegalArgumentException("Size cannot be zero or negative");
        }

        //Error! Warning!
        return Collections.unmodifiableList(this.listNews);

    }

    /**
     * Save one News to the Sustem.
     *
     * @param news to save
     */
    @Override
    public void save(News news) {

        for (News n : this.listNews){

            //No duplicates allowed
            if (n != null && n.getId().equals(news.getId())){
                throw new IllegalArgumentException("News already in the list");
            }

        }

        this.listNews.add(news);
    }
}
