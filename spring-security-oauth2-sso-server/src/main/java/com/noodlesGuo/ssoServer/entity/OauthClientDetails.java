package com.noodlesGuo.ssoServer.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import java.io.Serializable;

/**
 * @Description  
 * @Author  De.Guo
 * @Date 2019-11-21 
 */

@Entity
@Table ( name ="oauth_client_details")
@Data
public class OauthClientDetails  implements Serializable {

	private static final long serialVersionUID =  2283589860793334842L;

	@Id
   	@Column(name = "client_id" )
	private String clientId;

   	@Column(name = "resource_ids" )
	private String resourceIds;

   	@Column(name = "client_secret" )
	private String clientSecret;

   	@Column(name = "scope" )
	private String scope;

   	@Column(name = "authorized_grant_types" )
	private String authorizedGrantTypes;

   	@Column(name = "web_server_redirect_uri" )
	private String webServerRedirectUri;

   	@Column(name = "authorities" )
	private String authorities;

   	@Column(name = "access_token_validity" )
	private Long accessTokenValidity;

   	@Column(name = "refresh_token_validity" )
	private Long refreshTokenValidity;

   	@Column(name = "additional_information" )
	private String additionalInformation;

   	@Column(name = "autoapprove" )
	private String autoapprove;

   	@Column(name = "web_client_logout_uri" )
	private String webClientLogoutUri;

	@Override
	public String toString() {
		return "TpApiConfig{" +
				"clientId='" + clientId + '\'' +
				"resourceIds='" + resourceIds + '\'' +
				"clientSecret='" + clientSecret + '\'' +
				"scope='" + scope + '\'' +
				"authorizedGrantTypes='" + authorizedGrantTypes + '\'' +
				"webServerRedirectUri='" + webServerRedirectUri + '\'' +
				"authorities='" + authorities + '\'' +
				"accessTokenValidity='" + accessTokenValidity + '\'' +
				"refreshTokenValidity='" + refreshTokenValidity + '\'' +
				"additionalInformation='" + additionalInformation + '\'' +
				"autoapprove='" + autoapprove + '\'' +
				"webClientLogoutUri='" + webClientLogoutUri + '\'' +
				'}';
	}

}
