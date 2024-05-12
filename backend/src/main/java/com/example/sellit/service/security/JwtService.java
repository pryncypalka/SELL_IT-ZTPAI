package com.example.sellit.service.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "YNXMZog6hJMsYJcD5PkzGkC4Cy6zqnLyenbT2UCEA46D/hDO7zMuF2ngz2gjqutP8Q7KucGDjNB3n9j0Ad0E1ZcTOa2pRISQVsu+Kxq5PTCPrKUA8aFI4RM1drCz/H9lNTvWssoKubsmgiBH21GbBYM2zieUYCnGkjJoa29KV4jjf4O+gtOTAlg78ZfKWocI81I65UtwmyAH5/xAt+wCQc0ILGyWX11PDyzHWC1ufzzDNVAQQRQlqoso1+yg9qJqXAYTEcZpzaiJT+RdhY+RM65EoTHpcchuUiwo4ia2FHI8zXqHHtEbKSPNQhp9vNypnyQca8OHIvnLPc8C7OtRZRDGgnHCCIHi2nfUwzUx30LC3071fXjM++yHb6S7xWfpUVipiJUAQDJ/CVZtuLFNoj0PVcEozgijOIhq/5Xi/KnIdq74k9pvTCsi3s0N3XkwvjC8jP6Xex9jUewC+6FE8QugfsiL7sxcH2osTYvbvCEK9SVltXaqNH/kbTyolNd3/gnFbF8JzsPNUQ4geJnYTWdGc4YZKRp80eXqmo8ov17L7GzWl/IoQmxRONR8mx3QArEYkRYBAI5xHKM4UGa0+qE3yjU8xpUNVSDFkFb+/OmdEK6a9qC3bLdYvKIGp8wm9BuvWnoOKJM7EGJNaWSC9Xn3kjTTKVcmLjNTwBJw68UNlDLgEULVIstZWUuclM4FXPAvNYt7M1TJ0zCY1cbuRFUPjUNxyGApmjpYL9ffIKvFX7+riMn0f/MPtRYAYvdrj3VvtEZ6vGkoHVEx5Jz2pjDVnuSZzU1N08WVMroDImhumAAfXKXvA0xFGq1ToYHM0w1TkQA9boJirIJXj5YyJgMUxjexFx5NKqQhHDp5Nh936zx1z2IwHMiTM+rA9qBVBQON0HkAfPrHf5vXGAvu4+Ifgm6Gey85OnkYk8iqhhT5qGikvYjB5wYOjMhc7z7EO/EoQCy2NBYU58BBUhSpa8ivjetBEKX8qmwnfh3U36ms1OMhLURE62+UtYCGRWR9z5ZD8AgYnEfD0WBshy+o0ICZjrTrXhEbq4tYDdkPkd0+bTwnBA3DhF/CoIlXA6rouatcT1KLCoex7jNPdyPCeqvEdX/3h2hw9RCK4aa+Loy/6iUf51PxZnusHoGAEiKl12w12y0zHAO4VHEtmCQc8u8UG/GtnNaWcvv0pcfBMpr/swbh+RsLkYd6EQn+N52HnICSXqHTWD1kxE1lGQcOPhx9ak/vm20qFBx3bA5kK06PDQLQhl/5f5LEq1mGp7VkBuKXf7J+98PoJCgj3+lp+/GCXUAe93Mc5vYfPPymsYMEU+TDxA0NTccP8OvPy/b8oguk71MmRHcJGRzHxhlmD94LiBry/O2lhh2xi3WF8E0=";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(
          Map<String, Object> extraClaims,
          UserDetails userDetails
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
