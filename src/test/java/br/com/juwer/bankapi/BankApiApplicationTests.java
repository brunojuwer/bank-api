package br.com.juwer.bankapi;

import br.com.juwer.bankapi.domain.model.User;
import br.com.juwer.bankapi.domain.repository.UserRepository;
import br.com.juwer.bankapi.utils.DataBaseCleaner;
import br.com.juwer.bankapi.utils.ResourceUtil;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource("/application-test.yml")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BankApiApplicationTests {


	@Autowired
	private UserRepository userRepository;

	@Autowired
	private DataBaseCleaner dataBaseCleaner;

	@LocalServerPort
	private int port;

	private String jsonRegisterNewUser;
	private String jsonAuthenticateUser;

	@BeforeEach
	public void setUp(){
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.basePath = "/auth";
		RestAssured.port = port;

		jsonRegisterNewUser = ResourceUtil.getContentFromResource(
				"/json/newUserRegister.json");
		jsonAuthenticateUser = ResourceUtil.getContentFromResource(
				"/json/userAuthentication.json");

		dataBaseCleaner.clearTables();
		this.prepareData();
	}

	private void prepareData() {
		User user = new User();
		user.setEmail("bruno@email.com");
		user.setPassword("qwe@123");
		user.setFullName("Bruno Juwer");
		userRepository.save(user);
	}


	@Test
	public void mustReturnStatus_410_WhenTryRegister() {
		RestAssured
				.given()
					.body(jsonRegisterNewUser)
					.accept(ContentType.JSON)
					.contentType(ContentType.JSON)
				.when()
					.post("/register")
				.then()
					.statusCode(HttpStatus.GONE.value());
	}

	@Test
	public void mustReturnStatus_200_WhenTryGenerateNewToken() {
		RestAssured
				.given()
					.body(jsonAuthenticateUser)
					.accept(ContentType.JSON)
					.contentType(ContentType.JSON)
				.when()
					.post("/authenticate")
				.then()
					.body("token", Matchers.any(String.class))
					.body("username", Matchers.equalTo("bruno@email.com"))
					.body("userId", Matchers.equalTo(1))
					.statusCode(HttpStatus.OK.value());
	}


}
