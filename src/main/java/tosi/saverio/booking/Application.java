package tosi.saverio.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import tosi.saverio.booking.domain.model.Court;
import tosi.saverio.booking.domain.model.User;
import tosi.saverio.booking.domain.repository.CourtRepository;
import tosi.saverio.booking.domain.repository.UserRepository;

@SpringBootApplication
public class Application implements CommandLineRunner{
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CourtRepository courtRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		createUser();
		createCourt();
	}

	private void createUser() {
		User user = new User();
		user.setName("Saverio Tosi");
		user.setPhone("+39 327 2983 988");
		user.setEmail("saverio.tosi@flowing.it");

		userRepository.save(user);
	}

	private void createCourt() {
		Court court = new Court();
		court.setName("Tennis club");

		courtRepository.save(court);
	}
}