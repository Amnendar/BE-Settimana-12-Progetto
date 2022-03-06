package it.be.gestionecatalogo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class AutoreControllerTest {

	@Autowired
    private MockMvc mockMvc;
	
	
	@Test 
    @WithMockUser(username = "admin", password = "admin", roles = "ADMIN") 
    public void listaAutoreUtentiAutenticati() throws Exception { 
        this.mockMvc.perform(get("/api/autori/")).andExpect(status().isAccepted()); 
    }

    
    @Test
    @WithAnonymousUser
    public void listaAutoriWhenUserIsAnonymous() throws Exception{
    	 this.mockMvc.perform(get("/api/autori")).andExpect(status().isUnauthorized()); 
    }
    
    
    @Test
    @WithMockUser(username="user", password ="user", roles = "USER")
    public void addAutoreWhenUserIsUser() throws Exception{
    	this.mockMvc.perform(post("/api/aggiungiautore")).andExpect(status().is4xxClientError());
    }
	
	
	
	
}
