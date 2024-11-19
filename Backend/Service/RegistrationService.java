package com.tap.Registration.Service;

import com.tap.Registration.Entity.Registration;
import com.tap.Registration.Repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistrationService {
    @Autowired
    private RegistrationRepository repository;

    public Registration createRegistration(Registration registration) {
        return repository.save(registration);
    }

    public List<Registration> getAllRegistrations() {
        return repository.findAll();
    }

    public Optional<Registration> getRegistrationById(Long id) {
        return repository.findById(id);
    }

    public Registration updateRegistration(Long id, Registration updatedRegistration) {
        return repository.findById(id).map(existing -> {
            existing.setName(updatedRegistration.getName());
            existing.setEmail(updatedRegistration.getEmail());
            existing.setDateOfBirth(updatedRegistration.getDateOfBirth());
            return repository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Registration not found"));
    }

    public void deleteRegistration(Long id) {
        repository.deleteById(id);
    }
}
