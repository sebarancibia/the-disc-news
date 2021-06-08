/*
 * Copyright (c) 2021. Sebastián Arancibia-Arzic
 */

package cl.ucn.disc.dsm.sarancibia.model;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import cl.ucn.disc.dsm.sarancibia.services.Contracts;
import cl.ucn.disc.dsm.sarancibia.services.ContractsImplFaker;


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
     * The constract
     */
    private final Contracts contracts = new ContractsImplFaker();


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

        // Get the news from the backend
        // FIXME: 10 is good number?
        this.theNews.setValue(this.contracts.retrieveNews(10));

    }
}
