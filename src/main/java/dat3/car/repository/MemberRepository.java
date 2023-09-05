package dat3.car.repository;

import dat3.car.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,String> {
  boolean existsByEmail(String email);
    @Query("SELECT DISTINCT m FROM Member m WHERE m.reservations IS NOT EMPTY")
    List<Member> findDistinctMemberByReservationsIsNotNull();


}
