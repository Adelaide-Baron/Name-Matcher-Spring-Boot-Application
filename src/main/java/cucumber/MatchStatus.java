package cucumber;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MatchStatus {
    MATCHED("MATCHED"),

    NOT_MATCHED("NOT_MATCHED");

    private final String value;
}
