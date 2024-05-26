package id.ac.ui.cs.advprog.admin.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoleTest {

    private List<Role> roles;

    @BeforeEach
    void setup() {
        this.roles = new ArrayList<>();

        Role roleUser = new Role(ERole.ROLE_USER);
        roleUser.setId(1L);
        this.roles.add(roleUser);

        Role roleAdmin = new Role(ERole.ROLE_ADMIN);
        roleAdmin.setId(2L);
        this.roles.add(roleAdmin);
    }

    @Test
    void testAttributes() {
        assertEquals(1L, roles.get(0).getId());
        assertEquals(ERole.ROLE_USER, roles.get(0).getName());

        assertEquals(2L, roles.get(1).getId());
        assertEquals(ERole.ROLE_ADMIN, roles.get(1).getName());
    }

    @Test
    void testSetName() {
        Role role = new Role(ERole.ROLE_USER);
        role.setName(ERole.ROLE_ADMIN);
        assertEquals(ERole.ROLE_ADMIN, role.getName());
    }
}
