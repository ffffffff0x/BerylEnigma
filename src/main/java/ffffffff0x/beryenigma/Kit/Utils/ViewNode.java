package ffffffff0x.beryenigma.Kit.Utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ViewNode {
    String name() default "";
    String folderPath() default "Root/PlugIn/";
    String fxmlName() default "";
    boolean isVisible() default true;
}
