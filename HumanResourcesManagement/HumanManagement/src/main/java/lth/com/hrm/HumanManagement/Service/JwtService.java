package lth.com.hrm.HumanManagement.Service;

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
