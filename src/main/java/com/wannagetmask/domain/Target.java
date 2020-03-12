package com.wannagetmask.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Target {

    String url;
    String domain;
    String listTag;
    String soldoutTag;

}
