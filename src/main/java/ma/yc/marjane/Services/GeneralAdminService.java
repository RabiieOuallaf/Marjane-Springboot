package ma.yc.marjane.Services;

import lombok.extern.slf4j.Slf4j;
import ma.yc.marjane.DTO.GeneralAdminDTO;
import ma.yc.marjane.Mappers.GeneralAdminMapper;
import ma.yc.marjane.Models.GeneralAdminModel;
import ma.yc.marjane.Repositories.GeneralAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GeneralAdminService {
    @Autowired
    GeneralAdminRepository generalAdminRepository;
    /*
     Find general admin by email
     */
    public GeneralAdminDTO read(String email) {
        GeneralAdminModel generalAdminModel= generalAdminRepository.readByEmail(email);
        if(generalAdminModel != null) {
            GeneralAdminDTO generalAdminDTO = GeneralAdminMapper.generalAdminMapper.toDTO(generalAdminModel);
            return generalAdminDTO;
        }else {
            log.warn("No general admin found");
            return null;
        }

    }
}
