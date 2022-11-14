package service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import dto.CenterDTO;
import model.Center;

public interface AppointmentService {
	Collection<Center> findAll();

	Center findOne(Long id);

	Center create(Center center) throws Exception;

	Center update(Center center) throws Exception;

	Center delete(Long id);

	Center updateCenter(CenterDTO centerDTO, long id) throws Exception;

	ArrayList<Center> searchCenters(Optional<String> text);
}
