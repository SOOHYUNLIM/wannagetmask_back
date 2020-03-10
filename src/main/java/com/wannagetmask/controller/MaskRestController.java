package com.wannagetmask.controller;

import com.wannagetmask.domain.Account;
import com.wannagetmask.domain.Market;
import com.wannagetmask.domain.Option;
import com.wannagetmask.util.JsoupUtil;
import com.wannagetmask.util.SeleniumUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.openqa.selenium.support.ui.Select;

import java.net.URLDecoder;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
public class MaskRestController {

    private final SeleniumUtil seleniumUtil = SeleniumUtil.getChrome();

    @GetMapping("/intoMarket/{url}")
        public ResponseEntity<List<Option>> intoMarket(@PathVariable String url) {
        String rink = URLDecoder.decode(url);
        Select select = seleniumUtil.intoNaverShopping(rink);
        List<Option> result = select.getOptions().stream().map(option->Option.builder().id(option.getAttribute("value")).text(option.getText()).build()).collect(Collectors.toList());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/registerMarket")
    public ResponseEntity<String> registerMarket(@RequestBody Market market) {

        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    @PostMapping("/registerAccount")
    public ResponseEntity<String> registerAccount(@RequestBody Account account) {

        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }
}
