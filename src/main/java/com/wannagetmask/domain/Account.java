package com.wannagetmask.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "accounts")
public class Account {

    @Id
    private String id;

    private String pw;

    private String domain;

    private Boolean onOff;

    public Account() {
        this.onOff = true;
    }
}
