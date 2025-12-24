package pard.server.com.hw3.user;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Hw {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "status")
    private String status;

    @Column(name = "dueDate")
    private String dueDate;

    @Column(name = "level")
    private int level;

    public void updateStatus(String newStatus) { //patchMapping의 업데이트는 꼭 메소드로 관리하기
        this.status = newStatus;
    }
}
