package ma.yc.marjane.Auth;

import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import ma.yc.marjane.Models.GeneralAdminModel;
import ma.yc.marjane.Models.MarketAdminModel;
import ma.yc.marjane.Models.RayonAdminModel;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.naming.AuthenticationException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

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
            claims.put("role", generalAdmin.getRole());
            Date tokenCreatedTime = new Date();
            Date tokenValidity = new Date(tokenCreatedTime.getTime() + TimeUnit.MINUTES.toMillis(accessTokenValidity));
            return Jwts.builder()
                    .setClaims(claims)
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(tokenValidity)
                    .signWith(SignatureAlgorithm.HS256, secret_key)
                    .compact();

        }else if(admin instanceof RayonAdminModel) {
            RayonAdminModel rayonAdminModel = (RayonAdminModel)admin;
            Claims claims = Jwts.claims().setSubject(rayonAdminModel.getEmail());
            claims.put("fullaname", rayonAdminModel.getFullname());
            claims.put("roles", "RAYON_ADMIN");

            Date tokenCreatedTime = new Date();
            Date tokenValidity = new Date(tokenCreatedTime.getTime() + TimeUnit.MINUTES.toMillis(accessTokenValidity));

            return Jwts.builder()
                    .setClaims(claims)
                    .setExpiration(tokenValidity)
                    .signWith(SignatureAlgorithm.HS256, secret_key)
                    .compact();
        }else if(admin instanceof MarketAdminModel) {
            MarketAdminModel marketAdmin = (MarketAdminModel) admin;
            Claims claims = Jwts.claims().setSubject(marketAdmin.getEmail());
            claims.put("fullname", marketAdmin.getFullname());
            claims.put("roles", "MARKET_ADMIN");

            Date tokenCreatedTime = new Date();
            Date tokenValidity = new Date(tokenCreatedTime.getTime() + TimeUnit.MINUTES.toMillis(accessTokenValidity));

            return Jwts.builder()
                    .setClaims(claims)
                    .setExpiration(tokenValidity)
                    .signWith(SignatureAlgorithm.HS256, secret_key)
                    .compact();
        }else {
            throw new IllegalArgumentException("Unsupported admin type");
        }
    }

    public String getEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret_key).parseClaimsJws(token).getBody();
    }

    public String getRoles(String token)  {
        return extractClaim(token, claims -> claims.get("role", String.class));
    }
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String email = getEmail(token);
        return (email.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }
}
