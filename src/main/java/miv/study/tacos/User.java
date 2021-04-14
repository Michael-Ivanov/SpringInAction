package miv.study.tacos;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;

@Entity
@Table(name = "taco_user")
@Data
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // no need to map fields to table columns explicitly, because field name equals column name
    private String username;
    private String password;
    @Transient
    private String confirm;

    private String fullname;
    private String street;
    private String city;
    private String state;
    private String zip;

    // need explicit column mapping because Spring is looking for 'phone_number' column by default
    @Column(name = "phonenumber")
    private String phoneNumber;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }
    // todo: add custom deserializer https://stackoverflow.com/questions/36168010/spring-securitycan-not-construct-instance-of-org-springframework-security-core

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
