package com.ferdi.youcontribute.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ferdi.youcontribute.controller.requests.CreateRepositoryRequest;
import com.ferdi.youcontribute.models.Repository;
import com.ferdi.youcontribute.service.RepositoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)     //JUNIT5 -->Çalıştırdığımız testi Springin özellikleri ile extends ediyoruz.
@WebMvcTest(controllers = RepositoryController.class)
//Spring'e hangi classı test edeceğini söylüyoruz.(Muhatap olduğumuz classın sadece bağımlılıkları ile vs uğraş diyoruz.)
@AutoConfigureMockMvc(addFilters = false) //Gelen requestlerin filterBean ile durumunu false yapıyoruz.
public class RepositoryControllerTest {

    @Autowired
    private ObjectMapper objectMapper;  //map lemeyi sağlar.

    @Autowired
    private MockMvc mockMvc;

    @MockBean  //RepositoryController ın bağımlılığı RepositoryService olduğu için mockladık.
    //MockBean->Ben bir classı mockluyorum.Aynı zamn da gidip springin contexine de koyuyorum.(IOC)
    RepositoryService repositoryService;

    @Test  //Listelemeyi test edeceğiz.Bunun içierisinde RepositoryService bağımlılığımız var.
    public void it_should_list_repositories() throws Exception {
        Repository repository = Repository.builder().id(1).organization("Project Test Organization").repository("Project Test Repository").build();
        given(this.repositoryService.list()).willReturn(Collections.singletonList(repository));  //RepositoryService içerisindeki list metodu çağrıldığıdan ne döneceğini belirtiyoruz.Collections.singletonList ->List döner

        //Şimdi asıl işi yapacağımız kısım.

        this.mockMvc.perform(get("/repositories")).andDo(print()).andExpect(status().isOk())    //GetReequesti göndereceğimiz kısım ->perform ile sağlıyoruz.
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Project Test Repository"))
                .andExpect(jsonPath("$[0].organization").value("Project Test Organization"));
        //Dönen bir liste olduğu için her bir indisi karşılaştırdık.
        //Burada "/repositories" bir get isteği attığımız da print olarak yaz ve beklenen status "ok".
        // Ve dönen content içerisinde de  beklediğimiz alan bir json değer.({"name":"Project Test Repository","organization":"Project Test Organization"})
        //RepositoryResource  içinde ->  name ve organization alanlarına göre eşleştirme yapıyor.
        //Production oratamında karşılaşabilecek sorunlar öncesi burası bizim için ciddi bir deneme ortamı.
    }

    @Test
    public void it_should_create_repository() throws Exception {

        //given
        //Burada create metodunu test edeceğiz..Bunun içierisinde RepositoryService bağımlılığımız var.Burada RepositoryService içerisindeki create metodunu mocklayacağız.
        //create metodu "void" olduğu için 'bir değer döndürmediği için' given yerine  doNothing.when yapısını kullancağız.
        String organization = "github";
        String name = "contribute";
        CreateRepositoryRequest createRepositoryRequest = CreateRepositoryRequest.builder().organization(organization).repository(name).build();

        doNothing().when(this.repositoryService).create(name, organization); //crate metdonu çağırırsan hiç bir şey yapma.Void çünkü.


        //when

        this.mockMvc.perform(post("/repositories").content(objectMapper.writeValueAsBytes(createRepositoryRequest))  //requestimizi byte olarak , objectmapper ile parametre olarak verdik.Content type ve accepti de JSON olarak set ettik.
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)) //Burada bir post isteği gönderdik.Ve parametre olarak hazırladığımız requesti gönderdi.Json tipinde.
                .andDo(print()).andExpect(status().isCreated());


        //then


    }

}

/*
 pojo-tester? -> modelleri test etiğimizi bir librarty. -> https://www.pojo.pl/#what-is-pojo-tester
POJO-TESTER is a java testing library, which makes your pojo-method tests much easier.
You can test your pojo against equals, hashCode, toString, getters, setters and even constructors.

 */


