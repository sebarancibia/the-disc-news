/*
 * Copyright (c) 2021. Sebastián Arancibia-Arzic
 */

package cl.ucn.disc.dsm.sarancibia.model;

import net.openhft.hashing.LongHashFunction;

import org.threeten.bp.ZonedDateTime;

/**
 * The class.
 *
 * @author Sebastian Arancibia
 */
public class News {

    /**
     * Unique id.
     */
    private final Long id;

    /**
     *  The title.
     *  Restrictions:
     *  - not nulls.
     *  - size > 2
     */
    private final String title;

    /**
     * The source.
     */
    private final String source;

    /**
     * The author.
     */
    private final String author;

    /**
     * The Url.
     */
    private final String url;

    /**
     * The Url Image.
     */
    private final String urlImage;

    /**
     * The Description.
     */
    private final String description;

    /**
     * The Content.
     */
    private final String content;

    /**
     * The Date of Publish
     */
    private final ZonedDateTime publishedAt;

    /**
     * The Constructor.
     * @param title
     * @param source
     * @param author
     * @param url
     * @param urlImage
     * @param description
     * @param content
     * @param publishedAt
     */
    public News(
            final String title,
            final String source,
            final String author,
            final String url,
            final String urlImage,
            final String description,
            final String content,
            final ZonedDateTime publishedAt) {
        // FIXME: add the hash (title + source + author)




        //Title replace
        this.title= (title != null && title.length()>0) ? title : "No Title";

        // Source validation
        if (source == null){
            throw new IllegalArgumentException("Source was null");
        }
        if (source.length() <=4 ){
            throw new IllegalArgumentException("Source size <= 4");
        }
        this.source =source;

        //Author
        this.author = (author != null && author.length() > 0) ? author : "No Author";

        // Hash xx(title + source + author)
        this.id = LongHashFunction.xx().hashChars(
                this.getTitle() +  "|" + this.getSource() + "|" + this.getAuthor()
        );

        this.url = url;
        this.urlImage = urlImage;
        this.description = description;
        this.content = content;

        //PublishedAt validation
        if (publishedAt == null){
            throw new IllegalArgumentException("The PublishedAt needed");
        }
        this.publishedAt = publishedAt;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSource() {
        return source;
    }

    public String getAuthor() {
        return author;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public String getDescription() {
        return description;
    }

    public String getContent() {
        return content;
    }

    public ZonedDateTime getPublishedAt() {
        return publishedAt;
    }
}
