package com.beststar.book.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    // 머스테치 스타터 덕분에 컨트롤러에서 문자열을 반환할 때
    // 앞의 경로와 뒤의 파일 확장자는 자동으로 지정됨
    // 앞의 경로는 src/main/resources/template로
    // 뒤의 파일 확장자는 .mustache가 붙는 것
    // 즉, 여기선 "index"를 반환하므로
    // src/main/resources/templates/index.mustache로 전환되어
    // View Resolver가 처리하게 됨
    // * View Resolver는 URL 요청의 결과를 전달할 타입과 값을 지정하는 관리자 격으로 볼 수 있음
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
