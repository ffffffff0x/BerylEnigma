package ffffffff0x.beryenigma.Kit.Utils.TextFormatter;

import javafx.scene.control.TextFormatter;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

/**
 * @author: RyuZUSUNC
 * @create: 2022/8/11 21:32
 **/
public class IntegerFilter implements UnaryOperator<TextFormatter.Change> {
    private final static Pattern DIGIT_PATTERN = Pattern.compile("\\d*");

    @Override
    public TextFormatter.Change apply(TextFormatter.Change aT) {
        return DIGIT_PATTERN.matcher(aT.getText()).matches() ? aT : null;
    }
}
