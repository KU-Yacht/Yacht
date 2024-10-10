package site.yacht.backend.domain.user.dto;

import site.yacht.backend.domain.authentication.dto.TokenDto;
import site.yacht.backend.domain.user.domain.User;

public record LoginInformation(TokenDto tokenDto, User user) {
}
