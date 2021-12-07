package com.login.cafenow_kakaologin_stafflogin.domain.kakao_login.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.login.cafenow_kakaologin_stafflogin.common.model.AbstractEntity;
import com.login.cafenow_kakaologin_stafflogin.domain.kakao_login.model.enumType.AdminRoleType;
import com.login.cafenow_kakaologin_stafflogin.domain.kakao_login.model.enumType.UserRole;
import com.login.cafenow_kakaologin_stafflogin.domain.staff.model.Staff;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static javax.persistence.EnumType.STRING;

@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "admin")
public class Admin extends AbstractEntity implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long adminIdx;

    @Column(nullable = false, unique = true, length = 30)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(length = 100)
    private String password;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 100)
    private String provider;

    @Column(length = 500)
    private String profile_image_url;

    @Column(length = 500)
    private String thumbnail_image_url;

    /*
        이메일 유효 여부
        turn : 유효한 이메일
        false : 이메일이 다른 카카오계정에 사용돼 만료
     */
    private Boolean is_email_valid;

    /*
        이메일 인증 여부
        turn : 인증된 이메일
        false : 인증되지 않은 이메일
     */
    private Boolean is_email_verified;

    @Enumerated(STRING) @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "role_admin", joinColumns = @JoinColumn(name = "adminIdx"))
    @Builder.Default
    private List<AdminRoleType> roles = new ArrayList<>();

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // List<Role> 형태를 Stream을 사용하여 roles 원소의 값을 String으로 바꿔주는 Enum.name()을 이용하여 List<String>형태로 변환(GrantedAuthority의 생성자는 String 타입을 받기 때문)
        List<String> rolesConvertString = this.roles.stream().map(Enum::name).collect(Collectors.toList());
        return rolesConvertString.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    public String getRole() {
        return getAuthorities().iterator().next().toString();
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Builder.Default
    @OneToMany(
            mappedBy = "admin",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true
    )
    private List<Staff> staffs = new ArrayList<>();

    // Store에서 파일 처리 위함
    public void addStaff(Staff staff) {
        this.staffs.add(staff);

        // 게시글에 파일이 저장되어있지 않은 경우
        if(staff.getAdmin() != this)
            // 파일 저장
            staff.setAdmin(this);
    }
}