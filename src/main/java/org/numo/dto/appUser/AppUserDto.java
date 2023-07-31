package org.numo.dto.appUser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserDto {
    private Long id;
    private String name;
    private String username;
    private boolean actived;
    private Collection<String> roles;
}