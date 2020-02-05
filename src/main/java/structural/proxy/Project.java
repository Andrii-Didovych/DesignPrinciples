package structural.proxy;

public interface Project {
    public void run();
}

class RealProject implements Project {

    private String url;

    public RealProject(String url) {
        this.url = url;
        load();
    }

    public void load(){
        System.out.println("Loading project from url " + url + "...");
    }

    @Override
    public void run() {
        System.out.println("Running project " + url + "...");
    }
}

class ProxyProject implements Project {

    private String url;
    private RealProject realProject;

    public ProxyProject(String url) {
        this.url = url;
    }

    @Override
    public void run() {
        if (realProject == null) {
            realProject = new RealProject(url);
        }
        realProject.run();
    }
}

class ProjectRunner {

    public static void main(String[] args) {

        Project project = new ProxyProject("https://realroject");
        project.run();

    }
}
