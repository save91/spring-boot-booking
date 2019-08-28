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
import tosi.saverio.booking.domain.model.Booking;

import java.text.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BookingControllerTests {
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	@Autowired
	private WebTestClient webClient;

	@Test
	public void should_return_all_bookings() {
		this.webClient.get().uri("/").exchange().expectStatus().isOk();
	}

	@Test
	public void the_slot_should_be_available() throws ParseException {
		Booking booking = new Booking();

		booking.setCourtId(1L);
		booking.setFrom(df.parse("2018-04-04 18:00"));
		booking.setTo(df.parse("2018-04-04 19:00"));
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

	@Test
	public void the_slot_should_be_unavailable() throws ParseException {
		Booking booking1 = new Booking();
		booking1.setCourtId(1L);
		booking1.setFrom(df.parse("2018-04-03 16:00"));
		booking1.setTo(df.parse("2018-04-03 18:00"));
		booking1.setUserId(1L);

		Booking booking2 = new Booking();
		booking2.setCourtId(1L);
		booking2.setFrom(df.parse("2018-04-03 18:00"));
		booking2.setTo(df.parse("2018-04-03 19:00"));
		booking2.setUserId(1L);

		Booking booking3 = new Booking();
		booking3.setCourtId(1L);
		booking3.setFrom(df.parse("2018-04-03 19:00"));
		booking3.setTo(df.parse("2018-04-03 21:00"));
		booking3.setUserId(1L);

		Booking booking4 = new Booking();
		booking4.setCourtId(1L);
		booking4.setFrom(df.parse("2018-04-03 17:00"));
		booking4.setTo(df.parse("2018-04-03 20:00"));
		booking4.setUserId(1L);

		this.webClient
				.post()
				.uri("/")
				.body(Mono.just(booking1), Booking.class)
				.exchange().expectStatus().isEqualTo(HttpStatus.CREATED);

		this.webClient
				.post()
				.uri("/")
				.body(Mono.just(booking2), Booking.class)
				.exchange().expectStatus().isEqualTo(HttpStatus.CREATED);

		this.webClient
				.post()
				.uri("/")
				.body(Mono.just(booking3), Booking.class)
				.exchange().expectStatus().isEqualTo(HttpStatus.CREATED);

		this.webClient
				.post()
				.uri("/")
				.body(Mono.just(booking4), Booking.class)
				.exchange().expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);

	}

}