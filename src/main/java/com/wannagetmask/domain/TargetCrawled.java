package com.wannagetmask.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TargetCrawled {

    String prodCode;
    String url;

}
