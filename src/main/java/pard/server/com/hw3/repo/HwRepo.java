package pard.server.com.hw3.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import pard.server.com.hw3.user.Hw;
import org.springframework.data.jpa.repository.Query;
//JpaRepository를 상속받으면 save(), findById, findAll(), delete() 등 기본적인
//CRUD 메서드를 따로 만들 필요 없다.

@Repository
public interface HwRepo extends JpaRepository<Hw, Long> {
    //관리 할 클래스가 Hw이고, Hw의 primary key값의 type은 Long type이다.

    //JPA
    //service에 getStatus메서드의 콜을 받아 숙제 제목과 일치하는 목록을 찾아 엔티티형태로 리턴
    Hw findByTitle(String title);


    //JPA
    List<Hw> findAllByOrderByLevelDesc(); //난이도 내림차순된 엔티티타입 리스트 리턴

    //JPQL: 아직 미완료인 과제들 목록을 리턴합니다.
    @Query("SELECT h FROM Hw h WHERE LOWER(h.status) = 'incomplete'")
    List<Hw> findIncompleteHw();

}
