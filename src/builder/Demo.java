package builder;

import java.util.ArrayList;
import java.util.Collections;

class HtmlElement {

    public String name, text;
    public ArrayList<HtmlElement> elements = new ArrayList<>();
    private final int indentSize = 2;
    private final String newLine = System.lineSeparator();

    public HtmlElement() {
    }

    public HtmlElement(String name, String text) {
        this.name = name;
        this.text = text;
    }

    @Override
    public String toString() {
        return toStringImpl(0);
    }

    private String toStringImpl(int indent) {
        StringBuilder stringBuilder = new StringBuilder();
        String i = String.join("", Collections.nCopies(indent * indentSize, " "));
        stringBuilder.append(String.format("%s<%s>%s", i, name, newLine));
        if (text != null && !text.isEmpty()) {
            stringBuilder.append(String.join("", Collections.nCopies(indentSize * (indent + 1), "")))
                    .append(text)
                    .append(newLine);
        }

        for (HtmlElement htmlElement : elements) {
            stringBuilder.append(htmlElement.toStringImpl(indent + 1));
        }

        stringBuilder.append(String.format("%s<%s>%s", i, name, newLine));
        return stringBuilder.toString();
    }
}


class HtmlBuilder {
    private String rootName;
    private HtmlElement root = new HtmlElement();

    public HtmlBuilder(String rootName) {
        this.rootName = rootName;
        root.name = rootName;
    }

    public HtmlBuilder addChild(String childName, String childText) {
        HtmlElement element = new HtmlElement(childName, childText);
        root.elements.add(element);
        return this;
    }

    public void clear() {
        root = new HtmlElement();
        root.name = rootName;
    }

    @Override
    public String toString() {
        return root.toString();
    }
}

public class Demo {

    public static void main(String[] args) {
        System.out.println("String builder");
        String hello = "Hello";
        System.out.println("<p>" + hello + "</p>");

        String[] words = {"hello", "world"};

        final StringBuilder sb = new StringBuilder();
        sb.append("<ul>\n");
        for (String word : words) {
            sb.append(String.format("  <li>%s</li>\n", word));
        }
        sb.append("</ul>");
        System.out.println(sb);


        System.out.println("\nCustom builder");
        final HtmlBuilder builder = new HtmlBuilder("ul");
        builder
                .addChild("li", "hello")
                .addChild("li", "world");

        System.out.println(builder);


        final StringBuilder stringBuilder = new StringBuilder();
        //fluent interface
        stringBuilder.append("hello").append("world");
    }
}
