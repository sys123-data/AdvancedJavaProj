package comx.repository;

import comx.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PassRepository  extends JpaRepository<Login,Integer> {
    @Modifying
    @Query("UPDATE Login l SET l.password = :newPassword WHERE l.username = :username")
    public Integer updatePassword(@Param("username") String username, @Param("newPassword") String newPassword);

}
