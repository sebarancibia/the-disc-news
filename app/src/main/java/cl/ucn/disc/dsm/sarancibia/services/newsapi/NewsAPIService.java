/*
 * Copyright (c) 2021. Sebastián Arancibia-Arzic
 */

package cl.ucn.disc.dsm.sarancibia.services.newsapi;

import org.apache.commons.lang3.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cl.ucn.disc.dsm.sarancibia.model.News;
import cl.ucn.disc.dsm.sarancibia.model.newsapi.Article;
import cl.ucn.disc.dsm.sarancibia.model.newsapi.NewsAPIResponse;
import cl.ucn.disc.dsm.sarancibia.services.Contracts;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * The Service of NewsAPI
 *
 * @author Sebastian Arancibia
 */
public final class NewsAPIService implements Contracts {

    /**
     * The Logger.
     */
    private static final Logger log = LoggerFactory.getLogger(NewsAPIService.class);

    /**
     * The NewsAPI
     */
    private final NewsAPI newsAPI;

    /**
     * The current Zone
     */
    private static final ZoneId theZone = ZoneId.of("-4");

    /**
     * The Constructor: build the NewsAPI
     */
    public NewsAPIService() {

        this.newsAPI = new Retrofit.Builder()
                // The URL used connect
                .baseUrl(NewsAPI.BASE_URL)
                // JSON to POJO (Java) converter
                .addConverterFactory(GsonConverterFactory.create())
                // Build the retrofit
                .build()
                // Create the NewsAPI implementation
                .create(NewsAPI.class);
    }

    /**
     * Get the list of News
     *
     * @param size of the list
     * @return the List of News
     */
    @Override
    public List<News> retrieveNews(Integer size) {

        // The call the NewsAPI response.
        Call<NewsAPIResponse> theCall = this.newsAPI.getTopHealdlines(size, NewsAPI.Category.science);

        try{

            // Connect and get the response in synchrony way.
            Response<NewsAPIResponse> theResponse = theCall.execute();

            // ... if unsuccessful...
            if (!theResponse.isSuccessful()){
                // Not successful
                log.error("Can´t get the news" + theResponse.errorBody().string());
                throw new RuntimeException("Can´t get the News, response code : "+ theResponse.code());
            }

            // ...read the NewsAPIResponse from the body of the call (JSON)
            NewsAPIResponse newsAPIResponse = theResponse.body();

            // Nullity verification
            if (newsAPIResponse == null) {
                throw new RuntimeException("Body of NewsAPI was null");
            }

            // The result
            List<News> theNews = new ArrayList<>();

            // ... iterate over the list the Article
            for (Article article: newsAPIResponse.articles) {

                // TODO: Convert to transformer pattern
                News news = new News(
                        article.title,
                        article.source.name,
                        article.author,
                        article.url,
                        article.urlToImage,
                        article.description,
                        article.content,
                        parseDate(article.publishedAt)
                );

                // insert into the list of News
                theNews.add(news);
            }

            return theNews;

        }catch (IOException ex) {
            log.debug("Can´t get the news", ex);
            throw new RuntimeException("Can´t get the News",ex);
        }

    }

    /**
     * Convert the String 2021-06-22T22:27:00Z to ZoneDate
     * @param publishedAt used to convert.
     * @return the {@link ZonedDateTime}
     */
    private ZonedDateTime parseDate(String publishedAt) {

        return ZonedDateTime
                .parse(publishedAt)
                .withZoneSameInstant(theZone);

    }

    /**
     * Save one News to the System.
     *
     * @param news to save
     */
    @Override
    public void save(News news) {
        throw new NotImplementedException("Method not implemented!");
    }

    /**
     * The interface to NewsAPI.
     */
    public interface NewsAPI{

        /**
         * The base URL
         */
        String BASE_URL = "https://newsapi.org/v2/";

        /**
         * The API key
         */
        String API_KEY = "aadfe7079d9a442c8391a0f05c6f84ea";

        /**
         * Search through millions of articles from over 80,000 large and small news sources and blogs.
         *
         * This endpoint suits article discovery and analysis.
         * @return the Call with the {@link NewsAPIResponse}.
         */
        @Headers("x-Api-Key: " + API_KEY)
        @GET("everything")
        Call<NewsAPIResponse> getEverything();

        /**
         * https://newsapi.org/docs/endpoints/top-headlines
         *
         * @param pageSize The number of result to return per page (request). 20 is the default, 100 is the maximum.
         * @return the call to get NewsAPIResponse.
         */
        @Headers("x-Api-Key: " + API_KEY)
        @GET("top-headlines")
        Call<NewsAPIResponse> getTopHealdlines(@Query("pageSize") int pageSize);

        /**
         * https://newsapi.org/docs/endpoints/top-headlines
         */
        @Headers("x-Api-Key: " + API_KEY)
        @GET("top-headlines")
        Call<NewsAPIResponse> getTopHealdlines(@Query("pageSize") int pageSize,
                                               @Query("category") Category category);

        /**
         * The category you want to get headlines for.
         * Possible options:
         * business, entertainment, general, health, sciences, ports, technology
         */
        enum Category {
            business, entertainment, general, health, science, sports, technology
        }
    }
}
