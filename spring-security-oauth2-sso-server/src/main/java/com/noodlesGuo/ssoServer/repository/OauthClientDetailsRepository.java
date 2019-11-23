package com.noodlesGuo.ssoServer.repository;

import com.noodlesGuo.ssoServer.entity.OauthClientDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OauthClientDetailsRepository extends JpaRepository<OauthClientDetails,String> {
}
