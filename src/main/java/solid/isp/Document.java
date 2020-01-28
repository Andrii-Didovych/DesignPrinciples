package solid.isp;

/*
Отже, даний принцип означає, що занадто «товсті» інтерфейси необхідно розділяти на менші та специфічні, щоб їх клієнти
знали лише про ті методи, що необхідні для них у роботі. Як результат, при зміні певного функціоналу, незмінними мають
лишитися ті класи, що не використовують його. Тобто виконання цього принципу допомагає системі залишатися гнучкою при
 внесенні до неї змін та лишатися простою для рефакторингу.
 */

public class Document {}

interface Machine {
    void print(Document document);
    void fax(Document document) throws Exception;
    void scan(Document document);
}

class MultiFuntionalPrinter implements Machine {

    @Override
    public void print(Document document) {
        //
    }

    @Override
    public void fax(Document document) {
        //
    }

    @Override
    public void scan(Document document) {
        //
    }
}

class OldFachionedPrinter implements Machine {

    @Override
    public void print(Document document) {
        //
    }

    @Override
    public void fax(Document document) throws Exception {
        throw new Exception();
    }

    @Override
    public void scan(Document document) {
        //
    }
}

// YAGNI = You Ain't Going to Need It

interface Printer {
    void print(Document document);
}

interface Scanner {
    void scan(Document document);
}

class JustAPrinter implements Printer {

    @Override
    public void print(Document document) {
        System.out.println("Print documents");
    }
}

class JustAScanner implements Scanner {

    @Override
    public void scan(Document document) {
        System.out.println("Scan document");
    }
}

class PhotoCopier implements Printer, Scanner {

    private Printer printer;
    private Scanner scanner;

    public PhotoCopier(Printer printer, Scanner scanner) {
        this.printer = printer;
        this.scanner = scanner;
    }

    @Override
    public void print(Document document) {
        printer.print(document);
    }

    @Override
    public void scan(Document document) {
        scanner.scan(document);
    }
}

interface MultiFunctionDevice extends Printer, Scanner {}

class MultiFunctionDeviceImpl implements MultiFunctionDevice {

    private Printer printer;
    private Scanner scanner;

    public MultiFunctionDeviceImpl(Printer printer, Scanner scanner) {
        this.printer = printer;
        this.scanner = scanner;
    }

    @Override
    public void print(Document document) {
        printer.print(document);
    }

    @Override
    public void scan(Document document) {
        scanner.scan(document);
    }
}

class Demo {
    public static void main(String[] args) {
        Document document = new Document();

        JustAScanner justAScanner = new JustAScanner();
        justAScanner.scan(document);

        JustAPrinter justAPrinter = new JustAPrinter();
        justAPrinter.print(document);

        MultiFunctionDeviceImpl multiFunctionDevice = new MultiFunctionDeviceImpl(new JustAPrinter(), new JustAScanner());
        multiFunctionDevice.scan(document);
        multiFunctionDevice.print(document);

        PhotoCopier photoCopier = new PhotoCopier(new JustAPrinter(), new JustAScanner());
        photoCopier.scan(document);
        photoCopier.print(document);
    }
}

