package fr.ulco.dealhunter.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleEntity implements GrantedAuthority {

    public static final String USER = "USER";
    public static final String ADMIN = "ADMIN";

    private String authority;
}