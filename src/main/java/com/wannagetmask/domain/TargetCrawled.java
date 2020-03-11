package com.wannagetmask.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TargetCrawled {

    String prodCode;
    String prodName;
    String url;

}
