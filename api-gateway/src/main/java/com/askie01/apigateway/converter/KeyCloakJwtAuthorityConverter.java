package com.askie01.apigateway.converter;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class KeyCloakJwtAuthorityConverter implements JwtAuthorityConverter {

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        final List<String> authorities = getAuthorities(jwt);
        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private List<String> getAuthorities(Jwt jwt) {
        return (List<String>) jwt.getClaimAsMap("realm_access").get("roles");
    }
}
