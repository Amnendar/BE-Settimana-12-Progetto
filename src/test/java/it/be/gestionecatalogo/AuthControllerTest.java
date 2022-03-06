package it.be.gestionecatalogo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

	@Autowired
	private MockMvc mock;
	
	@Test
	@WithAnonymousUser
	public void listaAutoriWhenUtenteIsAnonymous() throws Exception{
	this.mock.perform(get("/api/autore")).andExpect(status().isUnauthorized());
		
	}
	
	@Test
    @WithAnonymousUser
    public void testLoginPubblica() throws Exception {
        this.mock.perform(get("/auth/login")).andExpect(status().isMethodNotAllowed());
    }
	
	@Test
    public void utenteRealeGetTokenAndAuthentication() throws Exception {
        String username = "user";
        String password = "user";
        String body = "{\"username\":\"" + username + "\", \"password\":\"" 
                      + password + "\"}";
        MvcResult result = mock.perform(MockMvcRequestBuilders.post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk()).andReturn();
        String response = result.getResponse().getContentAsString();
        
        String token = response.split(",")[0];
        token = token.substring(10, token.length() - 1);
        mock.perform(MockMvcRequestBuilders.get("/api/autore")
            .header("Authorization", "Bearer " + token))
            .andExpect(status().isOk());
    }
	
	
}
