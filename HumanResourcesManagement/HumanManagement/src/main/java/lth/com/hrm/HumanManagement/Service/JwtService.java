package lth.com.hrm.HumanManagement.Service;

import com.auth0.jwt.JWT;
import lth.com.hrm.HumanManagement.Entity.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

public class JwtService {
    private static final String Secret_key = "123";

    public String generateAccessToken(User user, Collection<SimpleGrantedAuthority> authorities){
        Algorithm algorithm = Algorithm.HMAC256(Secret_key.getBytes());

        return JWT.create()
                .withSubject(user.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 1000)) // 50 *60 *1000
                .withClaim("roles",authorities.stream().map(GrantedAuthority :: getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
    }
    public String generateRefreshToken(User user, Collection<SimpleGrantedAuthority> authorities){
        Algorithm algorithm = Algorithm.HMAC256(Secret_key.getBytes());

        return JWT.create()
                .withSubject(user.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + 70 *60 *1000))
                //.withClaim("roles",authorities.stream().map(GrantedAuthority :: getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
    }
}
