package site.yacht.backend.domain.user.domain;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Converter
public class RoleConverter implements AttributeConverter<Set<Role>, String> {

    @Override
    public String convertToDatabaseColumn(Set<Role> roles) {
        if (roles == null || roles.isEmpty()) {
            return null;
        }

        return roles.stream()
                .map(Role::name)
                .collect(Collectors.joining(","));
    }

    @Override
    public Set<Role> convertToEntityAttribute(String notifications) {
        if (notifications == null || notifications.isEmpty()) {
            return new HashSet<>();
        }

        return Arrays.stream(notifications.split(","))
                .map(Role::valueOf)
                .collect(Collectors.toSet());
    }
}
