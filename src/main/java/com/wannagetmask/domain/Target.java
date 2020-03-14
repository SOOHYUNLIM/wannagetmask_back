package com.wannagetmask.domain;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Target {

    String url;
    String domain;
    String listTag;
    String soldoutTag;

}
