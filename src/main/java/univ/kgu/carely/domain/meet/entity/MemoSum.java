package univ.kgu.carely.domain.meet.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemoSum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memo_sum_id")
    private Long id;

    @Column(name = "comm_sum")
    private String commSum;

    @Column(name = "meal_sum")
    private String mealSum;

    @Column(name = "toilet_sum")
    private String toiletSum;

    @Column(name = "bath_sum")
    private String bathSum;

    @Column(name = "walk_sum")
    private String walkSum;

    // 연관관계 매핑

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memo_id", nullable = false, unique = true)
    private Memo memo;

}
