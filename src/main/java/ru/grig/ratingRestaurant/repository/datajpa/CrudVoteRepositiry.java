package ru.grig.ratingRestaurant.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.grig.ratingRestaurant.model.Vote;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudVoteRepositiry extends JpaRepository<Vote, Integer> {

    Vote findByIdAndIdUser(int id, int userId);

    @Transactional
    @Modifying
//    @Query("DELETE FROM Vote v WHERE v.id=:id AND v.idUser=:userId")
    int deleteByIdAndIdUser(int id, int userId);

    List<Vote> findAllByIdUser(int userId);
}
