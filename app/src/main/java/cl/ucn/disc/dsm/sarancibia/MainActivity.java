/*
 * Copyright (c) 2021. Sebastián Arancibia-Arzic
 */

package cl.ucn.disc.dsm.sarancibia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cl.ucn.disc.dsm.sarancibia.databinding.ActivityMainBinding;
import cl.ucn.disc.dsm.sarancibia.model.NewsViewModel;

/**
 * The Main class.
 *
 * @author Sebastian Arancibia
 */
public final class MainActivity extends AppCompatActivity {

    /**
     * The Logger
     */
    private static final Logger log = LoggerFactory.getLogger(MainActivity.class);

    /**
     * The Bindings
     */
    private ActivityMainBinding binding;

    /**
     * The NewsViewModel
     */
    private NewsViewModel newsViewModel;

    /**
     * The NewsAdapter
     */
    private NewsAdapter newsAdapter;

    /**
     * @param savedInstanceState the instance.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflate the xml
        this.binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(this.binding.getRoot());

        // Set the toolbar
        this.setSupportActionBar(this.binding.toolbar);

        // Build the NewsViewModel
        this.newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);

        // What to do with swipe?
        this.binding.amSrlRefresh.setOnRefreshListener(() -> {

            log.debug("Refreshing the News...");
            this.newsViewModel.refresh();
        });

        // Instantiate the Adapter
        this.newsAdapter = new NewsAdapter();

        // Configure the RecyclerView
        {
            // 1. Layout
            this.binding.amRvListNews.setLayoutManager(new LinearLayoutManager(this));
            // 2. The Decoration
            this.binding.amRvListNews.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
            // 3. The Adapter
            this.binding.amRvListNews.setAdapter(this.newsAdapter);
        }

        // Observe the List of News
        this.newsViewModel.getNews().observe(this,news -> {

            log.debug("News: {}",news.size());

            // Notify the adapter with the list of news
            this.newsAdapter.setNews(news);

            // Hide the rotating circle.
            this.binding.amSrlRefresh.setRefreshing(false);

        });

    }

}