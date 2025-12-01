package com.askie01.apigateway.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;

public interface JwtAuthorityConverter extends Converter<Jwt, Collection<GrantedAuthority>> {

}
