package tests;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;

import main.model.Appointment;
import main.model.Center;
import main.model.User;
import main.service.AppointmentService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	private AppointmentService appointmentService;

	@Before
	public void setUp() throws Exception {
		appointmentService.AddAppointment(new Appointment(new Center("Center One", "Address One", "Description One", 7.8f), LocalDate.now(), LocalTime.now(), null));
	}
	
	@Test(expected = ObjectOptimisticLockingFailureException.class)
	public void testOptimisticLockingScenario() throws Throwable {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		Future<?> future1 = executor.submit(new Runnable() {
			
			@Override
			public void run() {
		        System.out.println("Startovan Thread 1");
				Optional<Appointment> appointmentToUpdate = appointmentService.getById(1L);// ocitan objekat sa id 1
				appointmentToUpdate.get().setUser(new User());// izmenjen ucitan objekat
				try { Thread.sleep(3000); } catch (InterruptedException e) {}// thread uspavan na 3 sekunde da bi drugi thread mogao da izvrsi istu operaciju
				appointmentService.scheduleFreeAppointment(appointmentToUpdate.get());// bacice ObjectOptimisticLockingFailureException
			}
		});
		executor.submit(new Runnable() {
			
			@Override
			public void run() {
		        System.out.println("Startovan Thread 2");
				Optional<Appointment> appointmentToUpdate = appointmentService.getById(1L);// ocitan isti objekat sa id 1 kao i iz prvog threada
				appointmentToUpdate.get().setUser(new User());// izmenjen ucitan objekat
				appointmentService.scheduleFreeAppointment(appointmentToUpdate.get());
			}
		});
		try {
		    future1.get(); // podize ExecutionException za bilo koji izuzetak iz prvog child threada
		} catch (ExecutionException e) {
		    System.out.println("Exception from thread " + e.getCause().getClass()); // u pitanju je bas ObjectOptimisticLockingFailureException
		    throw e.getCause();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		executor.shutdown();
	}
}
