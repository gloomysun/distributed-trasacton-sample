package com.ly.user.repository;

import com.ly.user.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query(value = "UPDATE tb_message SET status=?2 WHERE id=?1", nativeQuery = true)
    @Modifying
    void updateStatus(Long messageId, String status);

    List<Message> findByStatusAndLastSendTimeLessThanAndIsDead(String status, Date needTime, boolean isDead);

    @Query(value = "UPDATE tb_message SET is_dead=1 WHERE id=?1", nativeQuery = true)
    @Modifying
    void markMessageDead(Long id);
}
