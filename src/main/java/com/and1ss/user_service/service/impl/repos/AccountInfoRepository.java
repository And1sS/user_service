package com.and1ss.user_service.service.impl.repos;

import com.and1ss.user_service.service.model.AccountInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("account_info")
public interface AccountInfoRepository extends JpaRepository<AccountInfo, UUID> {
    AccountInfo findAccountInfoByLogin(String login);
    AccountInfo findAccountInfoById(UUID id);
    List<AccountInfo> findAllByIdIn(List<UUID> uuids);
}
