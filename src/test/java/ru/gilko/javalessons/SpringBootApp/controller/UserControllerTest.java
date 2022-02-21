package ru.gilko.javalessons.SpringBootApp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import ru.gilko.javalessons.SpringBootApp.repository.UserRepository;

import java.io.IOException;
import java.nio.file.Paths;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @Sql(value = {"/sql/util/clear_db.sql"}) // как нормально настроить пути? classpath
    @Sql(value = {"/sql/util/clear_db.sql"},
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    //    @Sql(value = {"src/test/resources/sql/util/clear_db.sql"},
    void postTest() throws IOException, Exception {
        String dataPath = "/resources/test_data/user_controller/tests/post/";

        String testData = objectMapper.readValue(Paths.get(dataPath + "valid.json").toFile(), String.class);
        // чтение из файла
        mockMvc.perform(post("/users")
                .content(testData)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void list() {
    }

    @Test
    void getOne() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}