public class City {
    int idx;
    String name;
    int population;
    String codeCountry;

    public City(int idx, String name, int population, String codeCountry) {
        this.idx = idx;
        this.name = name;
        this.population = population;
        this.codeCountry = codeCountry;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getCodeCountry() {
        return codeCountry;
    }

    public void setCodeCountry(String codeCountry) {
        this.codeCountry = codeCountry;
    }

    @Override
    public String toString() {
        return "City{" +
                "idx=" + idx +
                ", name='" + name + '\'' +
                ", population=" + population +
                ", codeCountry='" + codeCountry + '\'' +
                '}'+"\n";
    }

    public City(String name, int population, String codeCountry) {
        this.name = name;
        this.population = population;
        this.codeCountry = codeCountry;
    }

}
