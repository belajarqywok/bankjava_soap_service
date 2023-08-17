package com.bankjava.api.Helpers;

import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import com.bankjava.api.Configurations.AppConfig;


public class Authentication extends AppConfig {

    // Secret Key For Access Token
    private String secretKeyForAccessToken;

    // Secret Key For Refresh Token
    private String secretKeyForRefreshToken;


    // Constructor
    public Authentication() {

        // Secret Key For Access Token
        this.secretKeyForAccessToken = this.getProperty(
            "SECRET_KEY.ACCESS_TOKEN");

        // Secret Key For Refresh Token
        this.secretKeyForRefreshToken = this.getProperty(
            "SECRET_KEY.REFRESH_TOKEN");
    }



    /**
     *  JWT Token Builder
     * 
     *  @param userId
     *  @return String
     */
    private String JwtTokenBuilder(
        String userId, Date expirationDate, String secretKey) {

        return Jwts.builder()
            // Set Header
            .setHeaderParam("type", "JWT")

            // Set Paylaad
            .setId(userId)
            .setExpiration(expirationDate)

            // Set Signature
            .signWith(
                SignatureAlgorithm.HS512,
                secretKey.getBytes()
            )
            .compact();
    }



    /**
     *  Access Token Builder
     * 
     *  @param userId
     *  @return
     */
    public String AccessTokenBuilder(String userId) {

        // Expired On 30 minutes
        Date expirationDate = new Date(
            System.currentTimeMillis() + (3600 / 2 * 1) * 1000
        );

        // Return JWT Token
        return this.JwtTokenBuilder(
            // User ID (UUID 4)
            userId,

            // Expiration Date
            expirationDate,
            
            // Secret Key
            this.secretKeyForAccessToken
        );
    }



    /**
     *  Refresh Token Builder
     * 
     *  @param userId
     *  @return
     */
    public String RefreshTokenBuilder(String userId) {
        
        // Expired On 3 days
        Date expirationDate = new Date(
            System.currentTimeMillis() + (3600 * 24 * 3) * 1000
        );

        // Return JWT Token
        return this.JwtTokenBuilder(
            // User ID (UUID 4)
            userId,

            // Expiration Date
            expirationDate,
            
            // Secret Key
            this.secretKeyForRefreshToken
        );
    }



    /**
     *  Access Token Validation
     * 
     *  @param accessToken
     *  @return boolean
     */
    public boolean ValidateAccessToken(String accessToken) {
        try {

            // JWT is valid
            Jwts.parser()
                .setSigningKey(this.secretKeyForAccessToken.getBytes())
                .parseClaimsJws(accessToken);

            return true;

        } catch (Exception e) {

            // JWT is Invalid
            return false;
        }
    }



    /**
     *  Refresh Token Validation
     * 
     *  @param refreshToken
     *  @return boolean
     */
    public boolean ValidateRefreshToken(String refreshToken) {
        try {

            // JWT is valid
            Jwts.parser()
                .setSigningKey(this.secretKeyForRefreshToken.getBytes())
                .parseClaimsJws(refreshToken);

            return true;

        } catch (Exception e) {

            // JWT is Invalid
            return false;
        }
    }

}
