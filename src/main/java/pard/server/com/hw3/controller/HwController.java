package pard.server.com.hw3.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pard.server.com.hw3.dto.req.HwReq;
import pard.server.com.hw3.dto.res.HwRes;
import pard.server.com.hw3.service.HwService;


import java.util.List;

@RestController
@RequestMapping("/hw")
@RequiredArgsConstructor

public class HwController {
    private final HwService hwService;

    @PostMapping("") //저장할 숙제정보를 dto json으로 입력받아 서비스로 넘긴다.
    public String save(@RequestBody HwReq.HwInfo req) {
        hwService.save(req);
        return "저장완료!";
    }

    @GetMapping("orderByLevel") //난이도를 내림차순으로하여 숙제 title과 level 출력
    public List<HwRes.titleLevel> readTitleLevel() {//HwRes dto의 titleLevel 형식으로 리턴할건데
        return hwService.readTitleLevel(); //서비스의 readTitleLevel호출
    }

    @GetMapping("/{title}/status") //원하는 숙제의 완료 미완료 여부 확인
    public HwRes.statusDueDate getStatus(@PathVariable String title) { //숙제 제목을 입력받는다
        return hwService.getStatus(title); //서비스 호출, 숙제 제목 넘김
    }

    @GetMapping("incompleteHw") //미완료된 모든 숙제 출력
    public List<HwReq.HwTitle> readImCompleteHw() { //dto인 HwReq의 HwTitle 형태 리스트를 리턴한다.
        return hwService.readImCompleteHw(); //서비스 호출
    }

    @GetMapping("") //모든 숙제 리스트를 출력
    public List<HwRes.allInfo> findAll() {
        return hwService.findAll(); //서비스 호출
    }

    @PatchMapping("/{id}") //숙제의 아이디와 수정할 새로운 status만 필드로 갖는 json dto입력받음
    public String statusUpdate(@PathVariable Long id, @RequestBody HwReq.HwStatus req) {
        hwService.updateStatus(id, req); //서비스 호출
        return "수정완료!";
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id) {
        hwService.deleteById(id);
        return "삭제완료";
    }
}
