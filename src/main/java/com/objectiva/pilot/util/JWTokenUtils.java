package com.objectiva.pilot.util;

import java.util.Date;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.objectiva.pilot.constants.PTConstants;


public class JWTokenUtils {
	
    public static final String SECRET = "jwt_secret";
    
    //generate JWT Token
    public static String getToken(String userId) {
        try {
            Date date = new Date(System.currentTimeMillis() + PTConstants.EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            return JWT.create()
                    .withAudience(userId)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            return null;
        }
    }
    
//    public String getToken(SysUser user) {
//        String token="";
//        token= JWT.create().withAudience(user.getId())
//                .sign(Algorithm.HMAC256(user.getPassword()));
//        return token;
//    }

}
