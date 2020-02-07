package behavioural.strategy;

import java.util.List;
import java.util.function.Supplier;

public enum OutputFormat {
    WARKDOWN,HTML
}

interface ListStrategy {
    default void start(StringBuilder sb){}
    void addListItem(StringBuilder sb, String item);
    default void end(StringBuilder sb){}
}

class MarkdoenStrategy implements ListStrategy {

    @Override
    public void addListItem(StringBuilder sb, String item) {
        sb.append("*").append(item).append(System.lineSeparator());
    }
}

class HtmlListStrategy implements ListStrategy {

    @Override
    public void start(StringBuilder sb) {
        sb.append("<ul>").append(System.lineSeparator());
    }

    @Override
    public void addListItem(StringBuilder sb, String item) {
        sb.append("  <li>")
                .append(item)
                .append("</li>")
                .append(System.lineSeparator());
    }

    @Override
    public void end(StringBuilder sb) {
        sb.append("</ul>").append(System.lineSeparator());
    }
}
//compile time
class CompileTextProcessor<LS extends ListStrategy> {

    private StringBuilder sb = new StringBuilder();
    private ListStrategy listStrategy;

    public CompileTextProcessor(Supplier<? extends LS> ctor) {
        listStrategy = ctor.get();
    }

    public void appendList(List<String> items) {
        listStrategy.start(sb);
        for (String item : items) {
            listStrategy.addListItem(sb, item);
        }
        listStrategy.end(sb);
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}

//runtime
class RuntimeTextProcessor {

    private StringBuilder sb = new StringBuilder();
    private ListStrategy listStrategy;

    public RuntimeTextProcessor(OutputFormat format) {
        setOutputFormat(format);
    }

    public void setOutputFormat(OutputFormat format) {
        switch (format) {
            case WARKDOWN:
                listStrategy = new MarkdoenStrategy();
                break;
            case HTML:
                listStrategy = new HtmlListStrategy();
                break;
        }
    }

    public void appendList(List<String> items) {
        listStrategy.start(sb);
        for (String item : items) {
            listStrategy.addListItem(sb, item);
        }
        listStrategy.end(sb);
    }

    public void clear() {
        sb.setLength(0);
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}

class Demo {

    public static void main(String[] args) {

        //runtime
        final RuntimeTextProcessor tp = new RuntimeTextProcessor(OutputFormat.WARKDOWN);
        tp.appendList(List.of("liberte", "egalitr", "fraternite"));
        System.out.println(tp);

        tp.clear();

        tp.setOutputFormat(OutputFormat.HTML);
        tp.appendList(List.of("liberte", "egalitr", "fraternite"));
        System.out.println(tp);

        //compile
        CompileTextProcessor<MarkdoenStrategy> tp1 = new CompileTextProcessor<>(MarkdoenStrategy::new);
        tp1.appendList(List.of("alpha", "beta", "gamma"));
        System.out.println(tp1);

        CompileTextProcessor<HtmlListStrategy> tp2 = new CompileTextProcessor<>(HtmlListStrategy::new);
        tp2.appendList(List.of("alpha", "beta", "gamma"));
        System.out.println(tp2);
    }
}