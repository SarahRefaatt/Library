package com.example.demo;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.entity.Patron;
import com.example.demo.exception.NoSuchElementException;
import com.example.demo.repository.PatronRepository;
import com.example.demo.service.PatronService;

public class PatronServiceTest {

    @Mock
    private PatronRepository patronRepository;

    @InjectMocks
    private PatronService patronService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddPatron() {
        Patron patron = new Patron();
        patron.setName("John Doe");
        patron.setContactInfo("123-456-7890");

        patronService.addPatron(patron);

        verify(patronRepository).save(patron);
    }

    @Test
    void testGetPatrons() {
        List<Patron> patrons = new ArrayList<>();
        patrons.add(new Patron("Alice", "alice@example.com"));
        patrons.add(new Patron("Bob", "bob@example.com"));

        doReturn(patrons).when(patronRepository).findAll();

        List<Patron> result = patronService.getPatrons();

        assertThat(result).isEqualTo(patrons);
    }

    @Test
    void testGetPatronById() {
        Patron patron = new Patron("Alice", "alice@example.com");
        patron.setId(1L);

        doReturn(Optional.of(patron)).when(patronRepository).findById(1L);

        Patron result = patronService.getPatronById(1L);

        assertThat(result).isEqualTo(patron);
    }

    @Test
    void testGetPatronByIdNotFound() {
        doReturn(Optional.empty()).when(patronRepository).findById(1L);

        assertThrows(NoSuchElementException.class, () -> patronService.getPatronById(1L));
    }

    @Test
    void testUpdatePatron() {
        Patron existingPatron = new Patron("Alice", "alice@example.com");
        existingPatron.setId(1L);

        Patron updatedPatron = new Patron("Alice Smith", "alice.smith@example.com");
        updatedPatron.setId(1L);

        doReturn(Optional.of(existingPatron)).when(patronRepository).findById(1L);
        doReturn(updatedPatron).when(patronRepository).save(existingPatron);

        Patron result = patronService.updatePatron(updatedPatron);

        assertThat(result).isEqualTo(updatedPatron);
        verify(patronRepository).save(existingPatron);
    }

    @Test
    void testDeletePatron() {
        doNothing().when(patronRepository).deleteById(1L);

        patronService.deletePatron(1L);

        verify(patronRepository).deleteById(1L);
    }
}
