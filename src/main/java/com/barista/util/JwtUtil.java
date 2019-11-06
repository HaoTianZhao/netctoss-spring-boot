package com.barista.util;

import org.eclipse.jdt.internal.compiler.batch.FileSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import javafx.application.Preloader;

/**
 * JWT生成Token
 *
 * @ClassName JwtUtil
 * @Author zhaoth
 * @Date 2019/9/6 11:41
 * @Version 1.0
 */
@ConfigurationProperties(prefix = "jwt")
@Component
public class JwtUtil {
    private Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    private String secret;
    private long expire;//token过期时间，单位秒
    private String header;


    /**
     * 以userName，生成jwt token
     *
     * @param userName 用户名
     * @return java.lang.String token
     * @author zhaoth
     */
    public String generateToken(String userName) {
        Date nowDate = new Date();
        //过期时间
        Date expireDate = new Date(nowDate.getTime() + expire * 1000);

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(userName)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Claims getClaimByToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            logger.debug("validate is token error ", e);
            return null;
        }
    }

    public String getChaimSubject(String token) {
        Claims claim = getClaimByToken(token);
        String userName = claim.getSubject();
        return userName;
    }

    /**
     * token是否过期
     *
     * @return true：过期
     */
    public boolean isTokenExpired(Date expiration) {
        return expiration.before(new Date());
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
