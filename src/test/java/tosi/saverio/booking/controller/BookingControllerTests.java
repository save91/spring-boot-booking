package tosi.saverio.booking.controller;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import tosi.saverio.booking.model.entity.Booking;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BookingControllerTests {

	@Autowired
	private WebTestClient webClient;

	@Test
	public void should_return_all_bookings() {
		this.webClient.get().uri("/").exchange().expectStatus().isOk();
	}

	@Test
	public void should_create_booking() {
		Booking booking = new Booking();

		booking.setCourtId(1L);
		booking.setFrom(new Date(1522771200000L)); 	// "2018-04-03 18:00"
		booking.setTo(new Date(1522774800000L));	// "2018-04-03 19:00"
		booking.setUserId(1L);

		this.webClient
				.get()
				.uri("/")
				.exchange().expectStatus().isEqualTo(HttpStatus.OK)
				.expectBodyList(Booking.class).hasSize(0);

		this.webClient
				.post()
				.uri("/")
				.body(Mono.just(booking), Booking.class)
				.exchange().expectStatus().isEqualTo(HttpStatus.CREATED);

		this.webClient
				.get()
				.uri("/")
				.exchange().expectStatus().isEqualTo(HttpStatus.OK)
				.expectBodyList(Booking.class).hasSize(1);
	}

}