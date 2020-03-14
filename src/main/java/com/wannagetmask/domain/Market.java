package com.wannagetmask.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "markets")
public class Market {

    @Id
    String title;

    String url;

    String time;

    Option option;

    Boolean onOff;

    public Market () {
        this.onOff = true;
    }
}
