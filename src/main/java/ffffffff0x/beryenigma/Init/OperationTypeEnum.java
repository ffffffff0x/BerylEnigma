package ffffffff0x.beryenigma.Init;

/**
 * @author: RyuZUSUNC
 * @create: 2025/3/5 23:37
 **/
public enum OperationTypeEnum {
    ENCODE("encode"),
    DECODE("decode"),
    ENCRYPT("encrypt"),
    DECRYPT("decrypt"),
    CONFIRM("confirm");

    private final String name;

    OperationTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
