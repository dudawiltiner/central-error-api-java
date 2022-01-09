package api.centralerrorapijava.service;

import api.centralerrorapijava.model.LevelError;
import api.centralerrorapijava.repository.LevelErrorRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LevelErrorServiceImplTest {

    @Mock
    private LevelErrorRepository levelErrorRepository;

    @InjectMocks
    private LevelErrorServiceImpl levelErrorService;

    @Test
    public void testFindAll(){
        System.out.println(" -----------------------------------------------------");
        System.out.println("Verficando o funcionamento do método FindAll");
        System.out.println(" -----------------------------------------------------");
        mockFindAllRepository();
        Pageable pageable = PageRequest.of(0, 2);
        List<LevelError> errorList = levelErrorService.findAll();
        System.out.println(" - O retorno não deve ser null");
        assertNotNull(errorList);
        System.out.println(" - O tamanho do array deve ser de 3(3 tipos de erro)");
        assertEquals(3, errorList.size());
    }

    public void mockFindAllRepository(){
        List<LevelError> errorList = mockErrorList();
        when(this.levelErrorRepository.findAll()).thenReturn(errorList);
    }


    public List<LevelError> mockErrorList(){
        List<LevelError> errorList = new ArrayList<>();

        //Salvando um primeiro nível de erro
        LevelError error1 = new LevelError();
        error1.setId(1L);
        error1.setLevelName("error");
        error1.setQuantity(2);
        errorList.add(error1);

        //Salvando um segundo nível de erro
        LevelError error2 = new LevelError();
        error2.setId(2L);
        error2.setLevelName("warning");
        error2.setQuantity(1);
        errorList.add(error2);

        //Salvando um terceiro nível de erro
        LevelError error3 = new LevelError();
        error3.setId(3L);
        error3.setLevelName("info");
        error3.setQuantity(3);
        errorList.add(error3);

        return errorList;
    }
}
