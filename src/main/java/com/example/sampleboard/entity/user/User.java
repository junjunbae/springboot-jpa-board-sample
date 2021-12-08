package com.example.sampleboard.entity.user;

import com.example.sampleboard.entity.base.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PROTECTED) // 인자가 없는 기본 생성자 생성, PROTECTED로 보호
@Entity
@Table(name = "TB_USER")
@Getter
public class User extends BaseEntity implements UserDetails { // UserDetails는 스프링 시큐리티 사용에 있어 필요한 기본적인 요소들을 포함
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true)
    private String email;

    private String password;

    private String auth;

    private User(String email, String password, String auth) {
        this.email = email;
        this.password = password;
        this.auth = auth;
    }

    public static User userRegistration(String email, String password, String auth){
        return new User(email, password, auth);
    }

    // 사용자 권한 목록을 컬렉션 형태로 변환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        for( String role : auth.split(",")){
            roles.add(new SimpleGrantedAuthority(role));
        }
        return roles;
    }

    // 유저 email
    @Override
    public String getUsername() {
        return email;
    }

    // 계정만료여부
    @Override
    public boolean isAccountNonExpired() {
        return true; // 만료x
    }

    // 계정잠금여부
    @Override
    public boolean isAccountNonLocked() {
        return true; // 잠금x
    }

    // 패스워드만료여부
    @Override
    public boolean isCredentialsNonExpired() {
        return true; // 만료x
    }

    // 계정사용가능여부
    @Override
    public boolean isEnabled() {
        return true; // 사용가능
    }


}
