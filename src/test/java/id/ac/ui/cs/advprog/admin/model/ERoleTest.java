package id.ac.ui.cs.advprog.admin.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ERoleTest {

    @Test
    public void testEnumValues() {
        ERole[] roles = ERole.values();
        assertEquals(2, roles.length);

        assertTrue(roles[0] == ERole.ROLE_USER);
        assertTrue(roles[1] == ERole.ROLE_ADMIN);
    }

    @Test
    public void testEnumValueOf() {
        assertEquals(ERole.ROLE_USER, ERole.valueOf("ROLE_USER"));
        assertEquals(ERole.ROLE_ADMIN, ERole.valueOf("ROLE_ADMIN"));
    }
}
