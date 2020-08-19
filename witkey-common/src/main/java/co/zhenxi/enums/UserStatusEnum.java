package co.zhenxi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum UserStatusEnum {


    ERROR(0,"支付失败"),
    SUCCESS(0,"支付成功");


    private Integer value;
    private String desc;

    public static UserStatusEnum intKey(int value) {
        return Stream.of(UserStatusEnum.values())
                .filter(p -> p.value == value)
                .findAny()
                .orElse(null);
    }

    public static UserStatusEnum value(int value) {
        return Stream.of(UserStatusEnum.values())
                .filter(p -> p.value == value)
                .findAny()
                .orElse(null);
    }

}
