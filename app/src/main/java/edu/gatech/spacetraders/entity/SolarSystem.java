public class SolarSystem {
    private String name;
    private int techLvl;
    private int resLvl;

    public SolarSystem (String name, int techLvl, int reslvl) {
        this.name = name;
        this.techLvl = techLvl;
        this.resLvl = reslvl;
    }

    public String toString() {
        myReturn = "Name: " + this.name + " Tech Level: " + this.techLvl
                + " Resource Level: " this.resLvl;
        return myReturn;
    }
}