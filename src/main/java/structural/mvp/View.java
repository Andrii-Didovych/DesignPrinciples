package structural.mvp;


interface Model {
    void load();
}

interface Presenter {
    void processClick();
}

public interface View {
    void click();
    void message();

}
class Repository implements Model {


    @Override
    public void load() {
        System.out.println("Data was loaded");
    }
}

class Mediator implements Presenter {

    private final Model model;
    private final View view;

    Mediator(View view) {
        this.view = view;
        this.model = new Repository();
    }

    @Override
    public void processClick() {
        view.message();
        model.load();
    }
}

class UserInterface implements View {

    private Presenter presenter;

    @Override
    public void click() {
        presenter = new Mediator(this);
        presenter.processClick();
    }

    @Override
    public void message() {
        System.out.println("Right button");
    }
}


class Demo {

    public static void main(String[] args) {
        View view = new UserInterface();
        view.click();
    }

}





