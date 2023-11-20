package ma.yc.marjane.Auth;

import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import ma.yc.marjane.Models.GeneralAdminModel;
import ma.yc.marjane.Models.MarketAdminModel;
import ma.yc.marjane.Models.RayonAdminModel;
import org.springframework.stereotype.Component;

import javax.naming.AuthenticationException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {
    private final String secret_key = "mysecretkey";
    private long accessTokenValidity = 60*60*1000;

    private final JwtParser jwtParser;

    private String TOKEN_HEADER = "AUTHORIZATION";
    private String TOKEN_PREFIX = "Bearer";

    public JwtUtil() {
        this.jwtParser = Jwts.parser().setSigningKey(secret_key);
    }

    public String createToken(Object admin) {
        if(admin instanceof GeneralAdminModel){
            GeneralAdminModel generalAdmin = (GeneralAdminModel)admin;
            Claims claims = Jwts.claims().setSubject(generalAdmin.getEmail());
            claims.put("fullName", generalAdmin.getFullname());
            Date tokenCreatedTime = new Date();
            Date tokenValidity = new Date(tokenCreatedTime.getTime() + TimeUnit.MINUTES.toMillis(accessTokenValidity));

            return Jwts.builder()
                    .setClaims(claims)
                    .setExpiration(tokenValidity)
                    .signWith(SignatureAlgorithm.ES256, secret_key)
                    .compact();
        }else if(admin instanceof RayonAdminModel) {
            RayonAdminModel rayonAdminModel = (RayonAdminModel)admin;
            Claims claims = Jwts.claims().setSubject(rayonAdminModel.getEmail());
            claims.put("fullaname", rayonAdminModel.getFullname());
            Date tokenCreatedTime = new Date();
            Date tokenValidity = new Date(tokenCreatedTime.getTime() + TimeUnit.MINUTES.toMillis(accessTokenValidity));

            return Jwts.builder()
                    .setClaims(claims)
                    .setExpiration(tokenValidity)
                    .signWith(SignatureAlgorithm.ES256, secret_key)
                    .compact();
        }else if(admin instanceof MarketAdminModel) {
            MarketAdminModel marketAdmin = (MarketAdminModel) admin;
            Claims claims = Jwts.claims().setSubject(marketAdmin.getEmail());
            claims.put("fullname", marketAdmin.getFullname());
            Date tokenCreatedTime = new Date();
            Date tokenValidity = new Date(tokenCreatedTime.getTime() + TimeUnit.MINUTES.toMillis(accessTokenValidity));

            return Jwts.builder()
                    .setClaims(claims)
                    .setExpiration(tokenValidity)
                    .signWith(SignatureAlgorithm.ES256, secret_key)
                    .compact();
        }else {
            throw new IllegalArgumentException("Unsupported admin type");
        }
    }
    private Claims parseJwtClaims(String token) {
        return jwtParser.parseClaimsJws(token).getBody();
    }

    private Claims resolveClaim(HttpServletRequest  req) {
        try {
            String token = resolveToken(req);
            if(token != null) {
                return parseJwtClaims(token)
            }
            return null;
        } catch (ExpiredJwtException e) {
            req.setAttribute("expired", e.getMessage());
            throw e;
        } catch (Exception e) {
            req.setAttribute("Invalid", e.getMessage());
            throw e;
        }
    }

    private String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader(TOKEN_HEADER);
        if(bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)){
            return bearerToken.substring(TOKEN_PREFIX.length());
        };
        return null;
    }

    private boolean validateClaims(Claims claims) throws AuthenticationException {
        try {
            return claims.getExpiration().after(new Date());
        } catch(Exception e) {
            throw e;
        }
    }

    public String getEmail(Claims claims) {
        return claims.getSubject();
    }

    public List<String> getRoles(Claims claims)  {
        return (List<String>) claims.get("roles");
    }
}
