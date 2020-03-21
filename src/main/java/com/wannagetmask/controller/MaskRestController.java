package com.wannagetmask.controller;

import com.wannagetmask.domain.Account;
import com.wannagetmask.domain.Market;
import com.wannagetmask.domain.Option;
import com.wannagetmask.domain.Target;
import com.wannagetmask.repository.AccountRepository;
import com.wannagetmask.repository.MarketRepository;
import com.wannagetmask.repository.TargetRepository;
import com.wannagetmask.util.CustomMessage;
import com.wannagetmask.util.SeleniumUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

@Slf4j
@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
public class MaskRestController {

    private final SeleniumUtil seleniumUtil = SeleniumUtil.getChrome();
    private final AccountRepository accountRepository;
    private final MarketRepository marketRepository;
    private final TargetRepository targetRepository;


    @Qualifier("targetList")
    @Autowired
    private List<Target> targetList;

        // 옵션 반환
        @GetMapping("/intoMarket/{url}")
        public ResponseEntity<Map<String, List<Option>>> intoMarket(@PathVariable String url) {
            String rink = URLDecoder.decode(url);
            Map<String, List<Option>> result = null;
            HttpStatus status = HttpStatus.OK;
            try{
                result = seleniumUtil.intoNaverShopping(rink);
            } catch (Exception e) {
                status = HttpStatus.RESET_CONTENT;
            }
            return new ResponseEntity<>(result, status);
    }

    @PostMapping("/registerMarket")
    public ResponseEntity<Boolean> registerMarket(@RequestBody Market market) {
        Boolean result = true;
        //DB에 넣을 것
        try{
            marketRepository.insert(market);
        } catch (Exception e) {
        //이미 등록되었을 시(PK 중복 시)
            result = false;
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/getMarkets/{page}")
    public ResponseEntity<Page> getMarkets(@PathVariable Integer page) {
        Page<Market> result = marketRepository.findAll(PageRequest.of(page, 10));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/registerAccount")
    public ResponseEntity<CustomMessage> registerAccount(@RequestBody Account account) {
        CustomMessage result = seleniumUtil.loginNaver(account.getId(), account.getPw()) ? CustomMessage.SUCCESS : CustomMessage.FAIL;
        if(result.equals(CustomMessage.SUCCESS)) {
            try{
                accountRepository.insert(account);
            } catch (Exception e) {
                result = CustomMessage.OVERLAP;
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/registerTargetCrawled")
    public ResponseEntity<String> registerTargetCrawled(@RequestBody Target target) {

        targetRepository.insert(target);
        // 리스트에 넣고
        targetList.add(target);

        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    @GetMapping("/getTargetList/{pno}")
    public ResponseEntity<Page<Target>> targetListUp(@PathVariable Integer pno) {
        PageRequest page = PageRequest.of(pno-1,10);
        return new ResponseEntity<>(targetRepository.findAll(page),HttpStatus.OK);
    }

}
