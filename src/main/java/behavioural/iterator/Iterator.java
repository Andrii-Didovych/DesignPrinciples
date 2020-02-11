package behavioural.iterator;

public interface Iterator {

    public boolean hasNext();
    public String next();
}

interface Collection {
    Iterator getIterator();
}

class JavDeveloper implements Collection {

    private String name;
    private String[] skills;

    public JavDeveloper(String name, String[] skills) {
        this.name = name;
        this.skills = skills;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getSkills() {
        return skills;
    }

    public void setSkills(String[] skills) {
        this.skills = skills;
    }

    @Override
    public Iterator getIterator() {
        return new SkillIterator();
    }

    private class SkillIterator implements Iterator {

        int index;

        @Override
        public boolean hasNext() {
            if (index < skills.length) {
                return true;
            }
            return false;
        }

        @Override
        public String next() {
            return skills[index++];
        }
    }
}

class DeveloperRunner {

    public static void main(String[] args) {
        String[] skills = {"Java", "Spring", "Maven"};

        JavDeveloper javDeveloper = new JavDeveloper("Andrew", skills);

        Iterator iterator = javDeveloper.getIterator();

        System.out.println("Dev" + javDeveloper.getName());
        System.out.println("Skills");

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}