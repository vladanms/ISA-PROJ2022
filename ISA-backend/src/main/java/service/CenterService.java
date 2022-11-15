package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repository.CenterRepository;

@Service
public class CenterService {

	@Autowired
	private CenterRepository centerRepository;
}
