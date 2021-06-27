/*
 * Copyright (c) 2021. Sebastián Arancibia-Arzic
 */

package cl.ucn.disc.dsm.sarancibia.services;

import com.github.javafaker.Faker;

import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cl.ucn.disc.dsm.sarancibia.model.News;

/**
 * The class
 *
 * @author Sebastian Arancibia
 */
public final class ContractsImplFaker implements Contracts{

    /**
     * The List of News.
     */
    private final List<News> listNews = new ArrayList<>();

    /**
     * The constructor.
     */
    public ContractsImplFaker() {

        int N = 20;

        // Generate test data
        Faker faker = new Faker();

        for (int i = 0; i < N; i++) {

            // Test: valid data
            News news =
                    new News(
                            faker.book().title(),
                            faker.book().publisher(),
                            faker.book().author(),
                            faker.internet().url(),
                            "https://www.originalcup.com/7447-large_default/neon-mask-nightmare.jpg",
                            faker.book().genre(),
                            faker.dune().quote(),
                            ZonedDateTime.now(ZoneId.of("-3")));

            this.save((news));

        }
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