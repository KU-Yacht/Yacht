package site.yacht.backend.domain.application.domain;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public enum Region {

    KOREA, US, JAPAN, CHINA;

    private static final Map<String, Region> stringToEnum =
            Stream.of(values())
                    .collect(toMap(Object::toString, e -> e));

    public static Optional<Region> fromString(String source) {
        return Optional.ofNullable(stringToEnum.get(source));
    }
}
