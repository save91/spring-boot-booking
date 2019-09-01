package tosi.saverio.booking.controller;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import tosi.saverio.booking.domain.model.Booking;
import tosi.saverio.booking.domain.repository.BookingRepository;

import java.text.*;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BookingControllerTests {
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	@Autowired
	private WebTestClient webClient;

	@Autowired
	private BookingRepository bookingRepository;

	@Before
	public void beforeEachTest() {
		bookingRepository.deleteAll();
	}

	@Test
	public void should_return_all_bookings() {
		this.webClient.get().uri("/").exchange().expectStatus().isOk();
	}

	@Test
	public void the_slot_should_be_available() throws ParseException {
		Booking booking = new Booking();

		booking.setCourtId(2L);
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
		booking1.setCourtId(2L);
		booking1.setFrom(df.parse("2018-04-03 16:00"));
		booking1.setTo(df.parse("2018-04-03 18:00"));
		booking1.setUserId(1L);

		Booking booking2 = new Booking();
		booking2.setCourtId(2L);
		booking2.setFrom(df.parse("2018-04-03 18:00"));
		booking2.setTo(df.parse("2018-04-03 19:00"));
		booking2.setUserId(1L);

		Booking booking3 = new Booking();
		booking3.setCourtId(2L);
		booking3.setFrom(df.parse("2018-04-03 19:00"));
		booking3.setTo(df.parse("2018-04-03 21:00"));
		booking3.setUserId(1L);

		Booking booking4 = new Booking();
		booking4.setCourtId(2L);
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

	@Test
	public void it_should_fail_when_booking_slot_are_shorter_than_1h() throws ParseException {
		Booking booking = new Booking();
		booking.setCourtId(2L);
		booking.setFrom(df.parse("2018-04-06 17:00"));
		booking.setTo(df.parse("2018-04-06 17:59"));
		booking.setUserId(1L);

		this.webClient
				.post()
				.uri("/")
				.body(Mono.just(booking), Booking.class)
				.exchange().expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);

	}

	@Test
	public void it_should_fail_when_booking_slot_are_longer_than_3h() throws ParseException {
		Booking booking = new Booking();
		booking.setCourtId(2L);
		booking.setFrom(df.parse("2018-04-06 17:00"));
		booking.setTo(df.parse("2018-04-06 20:01"));
		booking.setUserId(1L);

		this.webClient
				.post()
				.uri("/")
				.body(Mono.just(booking), Booking.class)
				.exchange().expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);

	}


	@Test
	public void it_should_fail_when_booking_slot_time_start_before_9() throws ParseException {
		Booking booking = new Booking();
		booking.setCourtId(2L);
		booking.setFrom(df.parse("2018-04-06 8:59"));
		booking.setTo(df.parse("2018-04-06 9:59"));
		booking.setUserId(1L);

		this.webClient
				.post()
				.uri("/")
				.body(Mono.just(booking), Booking.class)
				.exchange().expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);

	}

	@Test
	public void it_should_fail_when_booking_slot_time_end_after_23() throws ParseException {
		Booking booking = new Booking();
		booking.setCourtId(2L);
		booking.setFrom(df.parse("2018-04-06 22:00"));
		booking.setTo(df.parse("2018-04-06 23:01"));
		booking.setUserId(1L);

		this.webClient
				.post()
				.uri("/")
				.body(Mono.just(booking), Booking.class)
				.exchange().expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);

	}

	@Test
	public void it_should_be_free_booking_when_booking_is_the_tenth() throws ParseException {
		for (int i = 1; i <= 20; i++) {
			Booking booking = new Booking();
			booking.setCourtId(2L);
			booking.setFrom(df.parse("2018-06-" + i + " 22:00"));
			booking.setTo(df.parse("2018-06-" + i + " 23:00"));
			booking.setUserId(1L);

			this.webClient
					.post()
					.uri("/")
					.body(Mono.just(booking), Booking.class)
					.exchange().expectStatus().isEqualTo(HttpStatus.CREATED);
		}

		List<Booking> bookings = bookingRepository.findByUserId(1L);

		for (int i = 0; i < 10; i++) {
			if(i == 9) {
				assertTrue(bookings.get(i).isFree());
			} else {
				assertFalse(bookings.get(i).isFree());
			}
		}
	}

}