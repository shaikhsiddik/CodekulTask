package www.square.codekul.Model;

public class ResourceList
{
    String name,color,pantone;
    int year;

    public ResourceList(){

    }

    public ResourceList(String name, String color, String pantone, int year) {
        this.name = name;
        this.color = color;
        this.pantone = pantone;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPantone() {
        return pantone;
    }

    public void setPantone(String pantone) {
        this.pantone = pantone;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
