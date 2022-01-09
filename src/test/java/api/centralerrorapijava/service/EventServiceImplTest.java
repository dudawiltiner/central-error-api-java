package api.centralerrorapijava.service;

import api.centralerrorapijava.model.Event;
import api.centralerrorapijava.model.LevelError;
import api.centralerrorapijava.repository.EventRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EventServiceImplTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventServiceImpl eventServiceImpl;

    @Test
    public void testFindAll(){
        System.out.println(" -----------------------------------------------------");
        System.out.println("Verficando o funcionamento do método FindAll");
        System.out.println(" -----------------------------------------------------");
        mockFindAllRepository();
        Pageable pageable = PageRequest.of(0, 2);
        List<Event> eventList = eventServiceImpl.findAll(pageable);
        System.out.println(" - O retorno não deve ser null");
        assertNotNull(eventList);
        System.out.println(" - O tamanho do array deve ser de 2(paginação escolhida)");
        assertEquals(2, eventList.size());
    }

    @Test
    public void testFindById(){
        System.out.println(" -----------------------------------------------------");
        System.out.println("Verficando o funcionamento do método FindId");
        System.out.println(" -----------------------------------------------------");
        mockFindIdRepository();
        Event event = eventServiceImpl.findById(1L).orElse(null);
        System.out.println(" - O retorno não deve ser null");
        assertNotNull(event);
        System.out.println(" - O id do evento deve ser o ID filtrado");
        Long id = 1L;
        assertEquals(id,   event.getId());
    }

    @Test
    public void testFindByLevelName(){
        System.out.println(" -----------------------------------------------------");
        System.out.println("Verficando o funcionamento do método FindByLevelName");
        System.out.println(" -----------------------------------------------------");
        mockFindByLevelRepository();
        Pageable pageable = PageRequest.of(0, 2);
        List<Event> eventList = eventServiceImpl.findByLevelName("error", pageable);
        System.out.println(" - O retorno não deve ser null");
        assertNotNull(eventList);
        System.out.println(" - O nível de erro(level) de cada evento deve ser o levelName filtrado");
        assertEquals("error", eventList.get(0).getLevel().getLevelName());
    }

    @Test
    public void testFindByEventDescription(){
        System.out.println(" -----------------------------------------------------");
        System.out.println("Verficando o funcionamento do método FindByEventDescription");
        System.out.println(" -----------------------------------------------------");
        mockFindByDescRepository();
        Pageable pageable = PageRequest.of(0, 2);
        List<Event> eventList = eventServiceImpl.findByEventDescription("descricao1", pageable);
        System.out.println(" - O retorno não deve ser null");
        assertNotNull(eventList);
        System.out.println(" - A descrição de cada evento deve ser a descrição filtrado");
        assertEquals("descricao1", eventList.get(0).getEventDescription());
    }

    @Test
    public void testFindByOrigin(){
        System.out.println(" -----------------------------------------------------");
        System.out.println("Verficando o funcionamento do método FindByOrigin");
        System.out.println(" -----------------------------------------------------");
        mockFindByOriginRepository();
        Pageable pageable = PageRequest.of(0, 2);
        List<Event> eventList = eventServiceImpl.findByOrigin("sistema2", pageable);
        System.out.println(" - O retorno não deve ser null");
        assertNotNull(eventList);
        System.out.println(" - A origem de cada evento deve ser a origem filtrada");
        assertEquals("sistema2", eventList.get(0).getOrigin());
    }

    @Test
    public void testFindByQuantity(){
        System.out.println(" -----------------------------------------------------");
        System.out.println("Verficando o funcionamento do método FindByQuantity");
        System.out.println(" -----------------------------------------------------");
        mockFindByQuantRepository();
        Pageable pageable = PageRequest.of(0, 2);
        List<Event> eventList = eventServiceImpl.findByQuantity(2, pageable);
        System.out.println(" - O retorno não deve ser null");
        assertNotNull(eventList);
        System.out.println(" - A quantidade de errors por tipo de cada evento deve ser a quantidade filtrada");
        Integer quant = 2;
        assertEquals(quant, eventList.get(0).getLevel().getQuantity());
    }

    public void mockFindAllRepository(){
        Page<Event> page = mockPageEvents();
        Pageable pageable = PageRequest.of(0, 2);
        when(this.eventRepository.findAll(pageable)).thenReturn(page);
    }

    public void mockFindIdRepository(){
        Page<Event> page = mockPageEvents();
        Event event = page.getContent().get(0);
        when(this.eventRepository.findById(1L)).thenReturn(Optional.of(event));
    }

    public void mockFindByLevelRepository(){
        Page<Event> page = mockPageEvents();
        Pageable pageable = PageRequest.of(0, 2);
        List<Event> eventList = page.getContent();
        List<Event> newList = new ArrayList<>();
        newList.add(eventList.get(0));
        when(this.eventRepository.findByLevelName("error", pageable)).thenReturn(new PageImpl<>(newList));
    }

    public void mockFindByDescRepository(){
        Page<Event> page = mockPageEvents();
        Pageable pageable = PageRequest.of(0, 2);
        List<Event> eventList = page.getContent();
        List<Event> newList = new ArrayList<>();
        newList.add(eventList.get(0));
        when(this.eventRepository.findByEventDescription("descricao1", pageable)).thenReturn(new PageImpl<>(newList));
    }

    public void mockFindByOriginRepository(){
        Page<Event> page = mockPageEvents();
        Pageable pageable = PageRequest.of(0, 2);
        List<Event> eventList = page.getContent();
        List<Event> newList = new ArrayList<>();
        newList.add(eventList.get(0));
        when(this.eventRepository.findByOrigin("sistema2", pageable)).thenReturn(new PageImpl<>(newList));
    }


    public void mockFindByQuantRepository(){
        Page<Event> page = mockPageEvents();
        Pageable pageable = PageRequest.of(0, 2);
        List<Event> eventList = page.getContent();
        List<Event> newList = new ArrayList<>();
        newList.add(eventList.get(0));
        when(this.eventRepository.findByQuantity(2, pageable)).thenReturn(new PageImpl<>(newList));
    }

    public Page<Event> mockPageEvents(){
        //Salvando um primeiro nível de erro
        LevelError error1 = new LevelError();
        error1.setId(1L);
        error1.setLevelName("error");
        error1.setQuantity(2);

        //Salvando um segundo nível de erro
        LevelError error2 = new LevelError();
        error2.setId(3L);
        error2.setLevelName("info");
        error2.setQuantity(3);

        List<Event> eventList = new ArrayList<>();

        //Colocando um primeiro evento na Page
        Event event1 = new Event();
        event1.setId(1L);
        event1.setLevel(error1);
        event1.setEventDescription("descricao1");
        event1.setEventDate("02-03-2021");
        event1.setOrigin("sistema1");
        eventList.add(event1);

        //Colocando um segundo evento na Page
        Event event2 = new Event();
        event2.setId(2L);
        event2.setLevel(error2);
        event2.setEventDescription("descricao2");
        event2.setEventDate("04-03-2021");
        event1.setOrigin("sistema2");
        eventList.add(event2);

        return new PageImpl<>(eventList);
    }

}
