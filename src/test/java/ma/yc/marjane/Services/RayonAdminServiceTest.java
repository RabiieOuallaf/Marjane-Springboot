package ma.yc.marjane.Services;

import ma.yc.marjane.DTO.RayonAdminDTO;
import ma.yc.marjane.Mappers.RayonAdminMapper;
import ma.yc.marjane.Models.RayonAdminModel;
import ma.yc.marjane.Repositories.RayonAdminRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
 public class RayonAdminServiceTest {

    @Mock
    private RayonAdminRepository rayonAdminRepository;
    @Mock
    private RayonAdminMapper rayonAdminMapper;
    @InjectMocks
    private RayonAdminService rayonAdminService;

    @Test
    public void createRayonAdmin_whenAdminAlreadyExists_shouldLogErrorAndReturnNull() {
        RayonAdminModel rayonAdminModel = new RayonAdminModel();
        rayonAdminModel.setEmail("existing@email.com");
        Mockito.when(rayonAdminRepository.findByEmail(rayonAdminModel.getEmail())).thenReturn(Optional.of(rayonAdminModel));

        RayonAdminDTO rayonAdminDTO = rayonAdminService.create(rayonAdminModel);

        Assertions.assertNull(rayonAdminDTO);
        Mockito.verify(rayonAdminRepository, Mockito.times(0)).save(ArgumentMatchers.any());
        Mockito.verify(rayonAdminMapper, Mockito.times(0)).toDTO(ArgumentMatchers.any());
    }

    @Test
    public void createRayonAdmin_whenAdminDoesNotExist_shouldSaveAdminAndReturnDTO() {
        RayonAdminModel rayonAdminModel = new RayonAdminModel();
        rayonAdminModel.setEmail("new@email.com");
        RayonAdminModel savedRayonAdminModel = new RayonAdminModel();
        savedRayonAdminModel.setId(1);
        Mockito.when(rayonAdminRepository.findByEmail(rayonAdminModel.getEmail())).thenReturn(Optional.empty());
        Mockito.when(rayonAdminRepository.save(rayonAdminModel)).thenReturn(savedRayonAdminModel);

        RayonAdminDTO rayonAdminDTO = rayonAdminService.create(rayonAdminModel);

        Assertions.assertNotNull(rayonAdminDTO);
        Mockito.verify(rayonAdminRepository, Mockito.times(1)).save(rayonAdminModel);
        Mockito.verify(rayonAdminMapper, Mockito.times(1)).toDTO(savedRayonAdminModel);
    }
}

