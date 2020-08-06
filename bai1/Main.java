import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost/week3";
        String user = "root";
        String password = "";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {

            if (conn != null) {
                System.out.println("Connected to the database");
            }
            //doc danh sach cac thanh pho tu DB
            List<City> listCity = new ArrayList<>();
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery("SELECT * FROM City");
            while (rs.next()) {
                City ct = new City(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4));
                listCity.add(ct);
                System.out.println("idx = " + rs.getInt(1) + " , name = " + rs.getString(2) +
                        " , dan so(trieu nguoi) = " + rs.getInt(3) + " , ma quoc gia = " + rs.getString(4));
            }
            System.out.println();
            //System.out.println(listCity.toString());

            //thanh pho dong dan nhat cua moi quoc gia
            HashSet<String> nameCtry = new HashSet<>();
            HashSet<String> codeCtry = new HashSet<>();
            for (City ct : listCity) {
                nameCtry.add(ct.getCodeCountry());
                codeCtry.add(ct.getCodeCountry());
            }
            for (String ct : nameCtry
            ) {
                City maxPopCountry = listCity.stream().filter(
                        ctOfCtry -> ctOfCtry.getCodeCountry().equals(ct)).max(
                        Comparator.comparing(City::getPopulation)).get();
                System.out.println("thanh pho dong dan nhat voi ma quoc gia: " + ct + ", la: " +
                        maxPopCountry.getName() + ", voi so dan : " + maxPopCountry.getPopulation());
            }
            System.out.println("----------------------------------------------------------------------------");
            //thanh pho dong dan nhat cua moi luc dia
            List<City> listCityOfConti = new ArrayList<>();
            ResultSet rs1 = state.executeQuery("SELECT City.name, City.population, Country.continent" +
                    " FROM City,Country" +
                    " WHERE City.codeCountry=Country.code");
            while (rs1.next()) {
                City ct = new City(rs1.getString(1), rs1.getInt(2), rs1.getString(3));
                listCityOfConti.add(ct);
                System.out.println("name = " + rs1.getString(1) +
                        " , dan so(trieu nguoi) = " + rs1.getInt(2) + " , chau luc = " + rs1.getString(3));
            }
            System.out.println();
            HashSet<String> nameOfConti = new HashSet<>();
            for (City ct : listCityOfConti) {
                nameOfConti.add(ct.getCodeCountry());
            }
            for (String ct : nameOfConti
            ) {
                City maxCityOfCconti = listCityOfConti.stream().filter(
                        ctOfCtry -> ctOfCtry.getCodeCountry().equals(ct)).max(
                        Comparator.comparing(City::getPopulation)).get();
                System.out.println("thanh pho dong dan nhat cua luc dia: " + ct + " , la: " +
                        maxCityOfCconti.getName() + ", voi so dan : " + maxCityOfCconti.getPopulation());
            }
            System.out.println();
            //thanh pho la thu do, dong dan nhat
            System.out.println("----------------------------------------------------------------------------");
            List<City> listCityIsCap = new ArrayList<>();
            List<String> listContiOfCityIsCap = new ArrayList<>();
            ResultSet rs2 = state.executeQuery(
                    "SELECT City.name, City.population, Country.code, Country.name, Country.continent" +
                            " FROM City,Country" +
                            " WHERE City.codeCountry=Country.code && City.idx=Country.capital");
            while (rs2.next()) {
                City ct = new City(rs2.getString(1), rs2.getInt(2), rs2.getString(3));
                listCityIsCap.add(ct);
                listContiOfCityIsCap.add(rs2.getString(4));
                System.out.println("name = " + rs2.getString(1) +
                        " , dan so(trieu nguoi) = " + rs2.getInt(2) + " , Quoc gia = " + rs2.getString(3));
            }
            City maxCityIsCap = listCityIsCap.stream().max(Comparator.comparing(City::getPopulation)).get();
            System.out.println("thanh pho la thu do, dong dan nhat la : " + maxCityIsCap.getName() +
                    " , so dan la : " + maxCityIsCap.getPopulation());
            //thanh pho la thu do, dong dan nhat cua moi luc dia
            System.out.println("----------------------------------------------------------------------------");
            List<Country> listCountry = new ArrayList<>();
            ResultSet rs3 = state.executeQuery("SELECT * FROM Country");
            while (rs3.next()) {
                Country conty = new Country(rs3.getString(1), rs3.getString(2), rs3.getString(3),
                        rs3.getDouble(4), rs3.getInt(5), rs3.getDouble(6), rs3.getInt(7));
                listCountry.add(conty);
            }
            for (String conti : nameOfConti) {
                List<City> lsCityOfConti = new ArrayList<>();
                for (Country ct:listCountry) {
                    for (City cty:listCity) {
                        if(ct.getContinent().equals(conti)&&ct.getCode().equals(cty.getCodeCountry())&&
                        ct.getCapital()==cty.getIdx()){
                            lsCityOfConti.add(cty);
                        }
                    }
                }
                City maxCtyIsCapOfConti = lsCityOfConti.stream().
                        max(Comparator.comparing(City::getPopulation)).get();
                System.out.println("thanh pho la thu do dong dan nhat cua luc dia "+conti+
                        " la "+maxCtyIsCapOfConti.getName()+" voi so dan : "+maxCtyIsCapOfConti.getPopulation());
            }
            System.out.println("----------------------------------------------------------------------------");
            //sap xep cac quoc gia theo so luong thanh pho giam dan
            List<Integer> numCityOfCountry = new ArrayList<>();
            int count=0;
            for (String conty:codeCtry) {
                count = (int)listCity.stream().filter(city -> city.getCodeCountry().equals(conty)).count();
                numCityOfCountry.add(count);
            }
            Map<String, Integer> sortConty = new HashMap<>();
            for(int i=0; i<nameCtry.size();i++){
                String[] arr;
                arr = nameCtry.toArray(String[]::new);
                sortConty.put(arr[i],numCityOfCountry.get(i));
            }
            List<String> nameCitySort = sortConty.entrySet().stream().
                    sorted((o1,o2)-> o2.getValue().compareTo(o1.getValue())).map(Map.Entry::getKey)
                    .collect(Collectors.toList());
            System.out.println("danh sach cac quoc gia co so thanh pho giam dan theo ma quoc gia");
            for (String cty:nameCitySort) {
                System.out.println(cty);
            }
            // xap xep cac quoc gia theo dan so giam dan, bo qua cac quoc gia co dan so = 0

            List<Country> popNotEqual0 = listCountry.stream().filter(city -> city.getPopulation()!=0).
                    sorted(Comparator.comparingInt(Country::getPopulation).reversed()).
                    collect(Collectors.toList());
            System.out.println("cac quoc gia theo dan so giam dan, bo qua cac quoc gia co dan so = 0");
            for (Country ct : popNotEqual0) {
                System.out.println(ct.getName());
            }
            conn.close();
        } catch (SQLException ex) {
            System.out.println("An error occurred. Maybe user/password is invalid");
            ex.printStackTrace();
        }
    }
}
