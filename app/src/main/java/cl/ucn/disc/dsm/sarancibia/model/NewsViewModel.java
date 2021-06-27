/*
 * Copyright (c) 2021. Sebastián Arancibia-Arzic
 */

package cl.ucn.disc.dsm.sarancibia.model;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.concurrent.Executors;

import cl.ucn.disc.dsm.sarancibia.services.Contracts;
import cl.ucn.disc.dsm.sarancibia.services.ContractsImplFaker;
import cl.ucn.disc.dsm.sarancibia.services.newsapi.NewsAPIService;


/**
 * The NewViewModel
 *
 * @author Sebastian Arancibia
 */
public class NewsViewModel extends AndroidViewModel {

    /**
     * The Logger
     */
    private static final Logger log = LoggerFactory.getLogger(NewsViewModel.class);

    /**
     * The contract
     */
    private final Contracts contracts = new NewsAPIService(); //new ContractsImplFaker();


    /**
     * The List of News
     */
    private final MutableLiveData<List<News>> theNews;

    /**
     * The Constructor
     *
     * @param application to use
     */
    public NewsViewModel(Application application) {
        super(application);

        // TODO: call the new LiveData constructor
        this.theNews = new MutableLiveData<>();
    }

    /**
     * @return the list of news wrapped inside a liveData.
     */
     public LiveData<List<News>> getNews() {
        return this.theNews;
     }

    /**
     * Refresh (get) the News from the backend
     */
    public void refresh(){

        // Show message if theNews are empty
        if (this.theNews.getValue() == null || this.theNews.getValue().size() == 0){
            log.warn("No News, fetching from contracts...");
        }

        // Background thread
        {
            Executors.newSingleThreadExecutor().execute(() -> {

                List<News> news = this.contracts.retrieveNews(50);

                // Get thread
                new Handler(Looper.getMainLooper()).post(() ->{
                    this.theNews.setValue(news);
                });

            });
        }

    }
}
