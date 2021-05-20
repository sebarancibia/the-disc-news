# The DISC News

The DISC News is a android application 

## Domain Model

```
@startuml

package externals* #ffcccc {
    
    package org.threeten.bp {

       class ZoneDateTime {
            ...
        } 

    }      

    package net.openhft.hashing{
        class longHashFunction{
            ...
        }
    }

}

package cl.ucn.disc.dsm.sarancibia {

    package model #ccffcc {
    
        class News <<entity>> {
            - id: Long
            - title: String
            - source: String
            - author: String
            - url: String
            - irlImage: String
            - desciption: String
            - content: String
            + News(...)
            + getId(); String
            + getTitle(): String
            + getSource(): String
            + getAuthor(): String
            + getUrl(): String
            + getUrlImage(): String
            + getDescription(): String
            + getContent(): String
        }    
        
        News *--> "1" ZoneDateTime : - publishedAt
        News ..> longHashFunction : <<use>>
    }

    package services #ccccff {

        interface Contracts <<interface>> {
        
            + retrieveNews(size: Integer) : List<News>
            + save(news: News) : void

        }
        Contracts ..> News : <<use>>

    }



}
@enduml
```

## License
[MIT](https://choosealicense.com/licenses/mit/)