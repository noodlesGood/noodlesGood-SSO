package com.noodlesGuo.ssoServer.service;

import com.noodlesGuo.ssoServer.entity.OauthClientDetails;
import com.noodlesGuo.ssoServer.repository.OauthClientDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OauthClientDetailServiceImpl {
    @Autowired
    private OauthClientDetailsRepository repository;

    public List<OauthClientDetails> getList(){
        List<OauthClientDetails> detailList = repository.findAll();
        return detailList;
    }
}
