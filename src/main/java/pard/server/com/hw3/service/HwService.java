package pard.server.com.hw3.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import pard.server.com.hw3.dto.res.HwRes;
import pard.server.com.hw3.dto.req.HwReq;
import pard.server.com.hw3.user.Hw;
import pard.server.com.hw3.repo.HwRepo;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class HwService {
    private final HwRepo hwRepo;

    public void save(HwReq.HwInfo req) {
        Hw hw = Hw.builder()
                .title(req.getTitle())
                .status(req.getStatus())
                .dueDate(req.getDueDate())
                .level(req.getLevel())
                .build();
        hwRepo.save(hw);
    }

    public List<HwRes.titleLevel> readTitleLevel() {
        List<Hw> homework = hwRepo.findAllByOrderByLevelDesc(); //엔티티 타입리스트 homework를 리포의 메서드를 통해
        //내림차순 정렬이 완료된 리스트를 엔티티형식으로 받는다.

        return homework.stream().map(hw -> //받은 엔티티리스트를 dto리스트로 변환
                HwRes.titleLevel.builder()
                        .title(hw.getTitle()) //엔티티의 필요한 필드만 dto로 복사
                        .level(hw.getLevel())
                        .build()).toList();
    }

    public HwRes.statusDueDate getStatus(String title) { //필드로 status, duedate를 가지는 dto를 리턴하는데
        Hw hw = hwRepo.findByTitle(title); //리포지토리의 findByTitle을 호출하여 Entity를 리턴 받는다.
        return HwRes.statusDueDate.builder() //받은 엔티티를 dto형태로 변환 후 리턴
                .status(hw.getStatus())
                .dueDate(hw.getDueDate())
                .build();

    }

    public List<HwReq.HwTitle> readImCompleteHw(){ // dto인 HwReq의 HwTitle 형태의 리스트 리턴
        List<Hw> homework = hwRepo.findIncompleteHw(); //리포에서 엔티티타입 리스트를 받아서

        return homework.stream().map(hw -> // 리턴 타입에 알맞는 dto로 변환후 리턴
                HwReq.HwTitle.builder()
                        .title(hw.getTitle()) //숙제 title만 dto필드로 변환하면된다.
                        .build())
                .toList(); //변환된 DTO들을 리스트로 묶어서 리턴
    }

    public List<HwRes.allInfo> findAll() { //dto인 HwRes의 allInfo형태의 리스트를 리턴할거다
        List<HwRes.allInfo> resDtos = new ArrayList<>(); //어레이 선언

        hwRepo.findAll().forEach(hw -> { //리포에서 모든 숙게를 엔티티로 받아서
            HwRes.allInfo resDto = HwRes.allInfo.builder() //dto로 변환 후 리스트에 저장
                    .id(hw.getId()) //현재 프로그램에서 숙제의 아이디를 조회 할 수 있는 유일한 기능이다.
                    .title(hw.getTitle())
                    .level(hw.getLevel())
                    .dueDate(hw.getDueDate())
                    .status(hw.getStatus())
                    .build();
            resDtos.add(resDto); //리스트에 저장
        });
        return resDtos; //리스트 리턴
    }

    @Transactional //transactional 사용
    public void updateStatus(Long id, HwReq.HwStatus req) { //컨트롤러로 아이디, dto를 넘겨받음
        Hw hw = hwRepo.findById(id) //해당 아이디를 가진 숙제 엔티티를 리포로부터 찾음
                .orElseThrow(()-> new RuntimeException("Hw not found with the ID: " + id)); //없다면 rollback
        hw.updateStatus(req.getStatus()); //있다묜 해당 엔티티의 상태 업데이트 후 저장
    }

    public void deleteById(Long id) {
        hwRepo.deleteById(id); //JpaRepository로 손쉽게 삭제 가능..
    }
}
