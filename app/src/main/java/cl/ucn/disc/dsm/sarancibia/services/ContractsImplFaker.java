/*
 * Copyright (c) 2021. Sebastián Arancibia-Arzic
 */

package cl.ucn.disc.dsm.sarancibia.services;

import java.util.ArrayList;
import java.util.Collection;
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

        // Check the list
        if (size > this.listNews.size()){
            throw new IndexOutOfBoundsException("Size > The current size");
        }

        // Return the last "size" inside of unmodifiable list
        return Collections.unmodifiableList(this.listNews.subList(this.listNews.size() - size, this.listNews.size()));

    }

    /**
     * Save one News to the System.
     *
     * @param news to save.
     */
    @Override
    public void save(final News news) {

        //  Nullity test
        if(news == null){
            throw new IllegalArgumentException("Need news != null");
        }

        // No duplicates allowed
        for (News n: this.listNews) {
            if (n != null && n.getId().equals(news.getId())){
                throw new IllegalArgumentException("News alredy in the list");
            }
        }

        // Insert into the end of the list
        this.listNews.add(news);

        // Sort the list by publishedAt
        Collections.sort(this.listNews, (a, b) -> b.getPublishedAt().compareTo(a.getPublishedAt()));

    }
}