package restassureddemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.OrderDto;
import dto.PetDto;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import lombok.SneakyThrows;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RestAssuredExample {

    private static final String BASE_URL = "https://petstore.swagger.io/v2";
    private RequestSpecification requestSpecification;
    private PetDto responsePet;
    private OrderDto responseOrder;

    @BeforeClass
    public void setUp() {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .addHeader("Content-Type", "application/json")
                .build();
    }

    @Test
    @SneakyThrows
    public void createPet() {
        PetDto requestPet = PetDto
                .builder()
                .status("available")
                .name("Barsik")
                .build();

        String petId = RestAssured.given()   // данные для запроса
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

         Assert.assertEquals(requestPet.getName(), responsePet.getName());
         Assert.assertEquals(requestPet.getStatus(), responsePet.getStatus());
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

        Assert.assertEquals(requestOrder.getPetId(), responseOrder.getPetId());
        Assert.assertEquals(requestOrder.getQuantity(), responseOrder.getQuantity());
        Assert.assertEquals(requestOrder.getStatus(), responseOrder.getStatus());
    }
}
