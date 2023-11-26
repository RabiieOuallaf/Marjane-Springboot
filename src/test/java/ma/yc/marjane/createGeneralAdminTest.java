package ma.yc.marjane;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.mockito.Mockito.when;

import ma.yc.marjane.Controllers.Implementation.Adminstration.GeneralAdminController;
import ma.yc.marjane.DTO.ProductDTO;
import ma.yc.marjane.Models.ProductModel;
import ma.yc.marjane.Services.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(GeneralAdminController.class)
@ExtendWith(MockitoExtension.class)
public class createGeneralAdminTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @InjectMocks
    private GeneralAdminController generalAdminController;

    @Test
    public void testCreate() throws Exception {
        ProductModel productModel = new ProductModel();

        ProductDTO mockedProductDTO = new ProductDTO();

        String response = "your JSON content representing the ProductModel";
        when(productService.create(productModel)).thenReturn(mockedProductDTO);

        mockMvc.perform(post("/your-endpoint/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(response))
                .andExpect(status().isOk())
                .andExpect(content().string("Product " + mockedProductDTO + "Created successfully"));
    }
}

