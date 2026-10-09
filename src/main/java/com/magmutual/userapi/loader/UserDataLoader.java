package com.magmutual.userapi.loader;

import com.magmutual.userapi.model.User;
import com.magmutual.userapi.repository.UserRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDate;

@Component
public class UserDataLoader implements CommandLineRunner {

    private final UserRepository userRepository;

    public UserDataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        var resource = new ClassPathResource("users.csv");
          try (
                Reader reader = new InputStreamReader(resource.getInputStream());
                CSVParser csvParser = CSVFormat.DEFAULT.builder().setHeader().setSkipHeaderRecord(true).build().parse(reader)
        ) {

            for (CSVRecord record : csvParser) {

                User user = new User();

                user.setId(Long.parseLong(record.get("id")));
                user.setFirstname(record.get("firstname"));
                user.setLastname(record.get("lastname"));
                user.setEmail(record.get("email"));
                 user.setProfession(record.get("profession"));
                user.setDateCreated(
                        LocalDate.parse(record.get("dateCreated"))
                  );
                user.setCountry(record.get("country"));
                user.setCity(record.get("city"));

                  userRepository.save(user);
            }
        }


        System.out.println(
                "Loaded " + userRepository.count() + " users"
        );
    }

}