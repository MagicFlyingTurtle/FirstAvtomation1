package restassureddemo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.OrderDto;
import dto.PetDto;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import lombok.SneakyThrows;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RestAssuredExample {

    private static final String BASE_URL = "https://petstore.swagger.io/v2";
    private RequestSpecification requestSpecification;
    private PetDto responsePet;
    private PetDto requestPet;
    private OrderDto responseOrder;
    String petId;

    @BeforeClass
    public void setUp() {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .addHeader("Content-Type", "application/json")
                .build();
    }

    @BeforeMethod
    public void createPetId() throws JsonProcessingException {
         requestPet = PetDto
                .builder()
                .status("available")
                .name("Barsik")
                .build();

         petId = RestAssured.given()   // данные для запроса
                .spec(requestSpecification)
                .body(new ObjectMapper().writeValueAsString(requestPet))  //mapper from jackson
                .when()  //выполнение запроса
                .post("/pet")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath()
                .getString("id"); //id - поле 1 уровеня, если 2 уровень category.id

        JsonPath jsonResponsePet = RestAssured
                .given()
                .spec(requestSpecification)
                .when()
                .get("/pet/" + petId)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath();

        //from json to java object
        responsePet = new ObjectMapper().readValue(jsonResponsePet.prettify(), PetDto.class);
    }

    @Test
    public void checkPet() {
        assertThat(responsePet)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(requestPet);
    }

    @Test
    @SneakyThrows
    public void createPetOrder() {
        OrderDto requestOrder = OrderDto
                .builder()
                .petId(responsePet.getId())
                .quantity(15)
                .status("delivered")
                .build();

        String orderId = RestAssured.given()
                .spec(requestSpecification)
                .body(new ObjectMapper().writeValueAsString(requestOrder))
                .when()
                .post("/store/order")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath()
                .getString("id");

        JsonPath jsonResponseOrder = RestAssured
                .given()
                .spec(requestSpecification)
                .when()
                .get("/store/order/" + orderId)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath();

        responseOrder = new ObjectMapper().readValue(jsonResponseOrder.prettify(), OrderDto.class);

       assertThat(responseOrder)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(requestOrder);
    }
}
