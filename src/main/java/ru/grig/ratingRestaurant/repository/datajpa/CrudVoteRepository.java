package ru.grig.ratingRestaurant.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.grig.ratingRestaurant.model.User;
import ru.grig.ratingRestaurant.model.Vote;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {

    @Query("SELECT v FROM Vote v LEFT JOIN FETCH v.user LEFT JOIN FETCH v.restaurant WHERE v.id=:id AND v.user.id=:userId")
    public Vote get(@Param("id") int id, @Param("userId") int userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Vote v WHERE v.id=:id AND v.user.id=:userId")
    public int delete(@Param("id") int id, @Param("userId") int userId);

    @Query("SELECT v FROM Vote v LEFT JOIN FETCH v.user LEFT JOIN FETCH v.restaurant")
    List<Vote> getAll();

    @Query("SELECT v FROM Vote v LEFT JOIN FETCH v.user LEFT JOIN FETCH v.restaurant WHERE v.user.id=:userId")
    public List<Vote> getAllByUser(@Param("userId") int userId);

    @Query("SELECT v FROM Vote v LEFT JOIN FETCH v.user LEFT JOIN FETCH v.restaurant WHERE v.user.id=:userId AND v.voteDate=:date")
    public Vote getByDate(@Param("userId") int userId, @Param("date") LocalDate date);

//    @Override
    @Query("SELECT v FROM Vote v WHERE v.id=:id AND v.user.id=:userId")
    public Vote getOne(@Param("id") int id, @Param("userId") int userId);

//    @Transactional
//    public Vote save(int id, User user);

    @Transactional
    @Override
    <S extends Vote> S save(S entity);
}

