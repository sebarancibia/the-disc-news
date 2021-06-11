/*
 * Copyright (c) 2021. Sebastián Arancibia-Arzic
 */

package cl.ucn.disc.dsm.sarancibia;


import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cl.ucn.disc.dsm.sarancibia.databinding.RowNewsBinding;
import cl.ucn.disc.dsm.sarancibia.model.News;

/**
 * The Adapter of News
 *
 * @author Sebastian Arancibia
 */
public final class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder>{

    /**
     * The DataSource
     */
    private List<News> theNews = new ArrayList<>();

    /**
     * The Constructor
     */
    public NewsAdapter(){
        // Nothing here
    }

    /**
     * Update th datasource
     *
     * @param news to use as data source
     */
    public void setNews(final List<News> news){
        this.theNews = news;

        // Notify the RecyclerView
        this.notifyDataSetChanged();
    }

    /**
     * Called when the recyclerView need a fresh row of NewsViewHolder
     *
     * @param parent The ViewGroup where the new will be added
     * @param viewType The view of type
     * @return the NewsViewHolder ready for action
     */
    @Override
    public NewsViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        // Call the constructor inflating the layout
        return new NewsViewHolder(
            RowNewsBinding.inflate(
                    LayoutInflater.from(parent.getContext())
            )
        );
    }

    /**
     * Called by RecyclerView when need to display some data at specific position
     * @param holder to use to set the GUI data
     * @param position of the dataset to show
     */
    @Override
    public void onBindViewHolder(final NewsAdapter.NewsViewHolder holder, final int position) {
        // Bind the ViewHolder + News at certain position
        holder.bind(this.theNews.get(position));
    }

    /**
     *
     * @return the size of the dataset
     */
    @Override
    public int getItemCount() {
        return this.theNews.size();
    }

    /**
     * The ViewHolder implementation of {@link cl.ucn.disc.dsm.sarancibia.model.News}
     */
    public static class NewsViewHolder extends RecyclerView.ViewHolder {

        /**
         * The GUI of News
         */
        private final RowNewsBinding rowNewsBinding;

        /**
         * @param rowNewsBinding the layout to use
         */
        public NewsViewHolder(final RowNewsBinding rowNewsBinding) {
            super(rowNewsBinding.getRoot());
            this.rowNewsBinding = rowNewsBinding;
        }

        /**
         *
         * @param news to use
         */
        public void bind(final News news){

            // Bind the title
            this.rowNewsBinding.rnTvTitle.setText(news.getTitle());

            // Bind the author
            this.rowNewsBinding.rnRvAuthor.setText(news.getAuthor());

        }
    }

}
