package com.wannagetmask.repository;


import com.wannagetmask.domain.Target;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TargetRepositoryTests {

    @Autowired
    private TargetRepository targetRepository;

    @Test
    public void insetTargetTest() {
        targetRepository.insert(Target.builder().url("http://www.welkeepsmall.com/shop/shopbrand.html?type=X&xcode=023").domain("웰킵스").listTag("tb-center").soldoutTag("soldout").build());
        //        targetRepository.insert(Target.builder().url("https://www.coupang.com/np/search?q=%ED%83%90%EC%82%AC+%EB%A7%88%EC%8A%A4%ED%81%AC&brand=&offerCondition=&filter=&availableDeliveryFilter=&filterType=rocket%2Ccoupang_global&isPriceRange=false&priceRange=&minPrice=&maxPrice=&page=1&trcid=&traid=&filterSetByUser=true&channel=auto&backgroundColor=&component=&rating=0&sorter=scoreDesc&listSize=36&rocketAll=true").domain("쿠팡").listTag("search-product").soldoutTag("out-of-stock").build());
    }
}
