package com.interview.creditcard.controller;

import com.interview.creditcard.entity.CreditCardEntity;
import com.interview.creditcard.service.CreditCardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(value= CreditCardController.class)
class CreditCardControllerTest {
  @Autowired
    private MockMvc mockMvc;
   @MockBean
   private CreditCardService creditCardService;

   private static final String ADD_URI= "/credit-card/add";
    private static final String LIST_ALL_URI= "/credit-card/list";

    @BeforeEach
    void setUp() {

    }

    @Test
    void testCreateCreditCardValid() throws Exception {
        String VALID_FILE = "src/test/resources/CreditCardValidRequest.json";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(ADD_URI).accept(MediaType.APPLICATION_JSON).content(readFileAsString(VALID_FILE)).contentType(MediaType.APPLICATION_JSON);
        when(creditCardService.addCreditCard(any(CreditCardEntity.class))).thenReturn(any(CreditCardEntity.class));
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
         assertNotNull(response);
      assertEquals(HttpStatus.CREATED.value(),response.getStatus());
    }
    @Test
    void testCreateCreditCardBadCardNumber() throws Exception {
        String INVALID_CARD_NUMBER_FILE = "src/test/resources/CreditCardInValidRequestBadCardNumber.json";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(ADD_URI).accept(MediaType.APPLICATION_JSON).content(readFileAsString(INVALID_CARD_NUMBER_FILE)).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST.value(),response.getStatus());
    }
    @Test
    void testCreateCreditCardInvalidDate() throws Exception {
        String INVALID_DATE_FILE = "src/test/resources/CreditCardInValidRequestBadDate.json";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(ADD_URI).accept(MediaType.APPLICATION_JSON).content(readFileAsString(INVALID_DATE_FILE)).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST.value(),response.getStatus());
    }
    @Test
    void testCreateCreditCardInvalidCardType() throws Exception {
        String INVALID_CARD_TYPE_FILE = "src/test/resources/CreditCardInValidRequestBadCardType.json";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(ADD_URI).accept(MediaType.APPLICATION_JSON).content(readFileAsString(INVALID_CARD_TYPE_FILE)).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST.value(),response.getStatus());
    }

    @Test
    void testGetCreditCardListWithPagination() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(LIST_ALL_URI).accept(MediaType.APPLICATION_JSON).queryParam("page","1").queryParam("size","10");
        when(creditCardService.getAllCreditCards(PageRequest.of(1,10))).thenReturn(any(Page.class));
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertNotNull(response);

        assertEquals(HttpStatus.PARTIAL_CONTENT.value(),response.getStatus());
    }

    @Test
    void testGetCreditCardListWithoutPagination() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(LIST_ALL_URI).accept(MediaType.APPLICATION_JSON);
        when(creditCardService.getAllCreditCards()).thenReturn(new ArrayList<>());
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertNotNull(response);
        assertEquals(HttpStatus.OK.value(),response.getStatus());
    } @Test
    void testGetCreditCardListThrowsError() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(LIST_ALL_URI).accept(MediaType.APPLICATION_JSON);
        when(creditCardService.getAllCreditCards()).thenThrow(new NullPointerException("No Result Found"));
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(),response.getStatus());
    }


    public static String readFileAsString(String file)throws Exception
    {
        return new String(Files.readAllBytes(Paths.get(file)));
    }
}