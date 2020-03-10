package com.wannagetmask.controller;

import com.wannagetmask.domain.Account;
import com.wannagetmask.domain.Market;
import com.wannagetmask.domain.Option;
import com.wannagetmask.util.SeleniumUtil;
import static com.wannagetmask.util.TargetUtil.*;

import com.wannagetmask.util.TargetUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.support.ui.Select;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
public class MaskRestController {

    private final SeleniumUtil seleniumUtil = SeleniumUtil.getChrome();

    // 옵션 반환
    @GetMapping("/intoMarket/{url}")
        public ResponseEntity<List<Option>> intoMarket(@PathVariable String url) {
        String rink = URLDecoder.decode(url);
        List<Option> options = null;
        HttpStatus status = HttpStatus.OK;
        try{
            Select select = seleniumUtil.intoNaverShopping(rink);
            options = select.getOptions().stream().map(option->Option.builder().id(option.getAttribute("value")).text(option.getText()).build()).collect(Collectors.toList());
        } catch (Exception e) {
            status = HttpStatus.RESET_CONTENT;
        }
        return new ResponseEntity<>(options, status);
    }

    @PostMapping("/registerMarket")
    public ResponseEntity<Boolean> registerMarket(@RequestBody Market market) {
        Boolean result = true;
        //DB에 넣을 것
        try{

        } catch (Exception e) {
        //이미 등록되었을 시(PK 중복 시)
            result = false;
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/registerAccount")
    public ResponseEntity<String> registerAccount(@RequestBody Account account) {

        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    @PostMapping("/registerTargetCrawled")
    public ResponseEntity<String> registerTargetCrawled(@RequestBody String prodPage) {


        return new ResponseEntity<>("Success", HttpStatus.OK);
    }







}
