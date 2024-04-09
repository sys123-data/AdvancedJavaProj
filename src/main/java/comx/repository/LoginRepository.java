package comx.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import comx.entity.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, Integer>{

    @Query("select l from Login l where l.username = :username")
    public Optional<Login> findUserByName(@Param("username") String username);
    @Query("SELECT l FROM Login l WHERE (l.role IS NULL OR l.role = false) AND l.username LIKE %:email%")
    List<Login> findNonAdminUsersWithEmailContaining(@Param("email") String email);

}

