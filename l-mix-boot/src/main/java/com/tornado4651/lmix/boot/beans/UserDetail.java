package com.tornado4651.lmix.boot.beans;

import com.tornado4651.lmix.boot.domain.Role;
import com.tornado4651.lmix.boot.domain.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
public class UserDetail implements UserDetails {
    private static final long serialVersionUID = 10L;
    private User user;
    private List<Role> roles;

    public UserDetail(User user, List<Role> roles){
        this.user = user;
        this.roles = roles;
    }

    public User getUser() {
        return user;
    }

    public List<Role> getRoles() {
        return roles;
    }

    @Override
//    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
//    @JsonIgnore
    public String getPassword() {
        return user.getPassword();
    }

    @Override
//    @JsonIgnore
    public String getUsername() {
        return user.getUsername();
    }

    @Override
//    @JsonIgnore
    public boolean isAccountNonExpired() {
        return user.getStatus()==1;
    }

    @Override
//    @JsonIgnore
    public boolean isAccountNonLocked() {
        return user.getStatus()==1;
    }

    @Override
//    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return user.getStatus()==1;
    }

    @Override
//    @JsonIgnore
    public boolean isEnabled() {
        return user.getStatus()==1;
    }
}
