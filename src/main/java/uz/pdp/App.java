package uz.pdp;


import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class App {
    public static void main( String[] args ) throws IOException {


        String data = Files.readString(Path.of("src/resources/MOCK_DATA.json"));

        Type type = new TypeToken<List<Employee>>() {
        }.getType();
        List<Employee> employees = new GsonBuilder().create().fromJson(data, type);


//        Stream.of(1,34,2,3,-12,56,-1,37,-35,9,7,32,-45,-3)
//                        .sorted(Comparator.comparing(Math::abs)).forEach(System.out::println);


        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");


        //stream sorting
        employees.stream()
                .sorted(Comparator.comparing(Employee::getFirstName)
                        .thenComparing(Employee::getLastName)
                        .thenComparing(Employee::getGender)
                        .thenComparing(employee -> {
                            try {
                                return formatter.parse(employee.getBirthDate());
                            } catch (ParseException e) {
                                throw new RuntimeException(e);
                            }
                        }))
                .forEach(System.out::println);

        String collect = employees.stream()
                .collect(new ToStringCollector());
        System.out.println(collect);





    }
}
