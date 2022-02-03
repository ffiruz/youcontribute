package com.ferdi.youcontribute.controller;

import com.ferdi.youcontribute.controller.requests.CreateRepositoryRequest;
import com.ferdi.youcontribute.controller.resources.RepositoryResource;
import com.ferdi.youcontribute.service.RepositoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/repositories")
public class RepositoryController {
    //http üzerinden bu class ile konuşacağız.
    //post işleminde postmapping veya request mapping kullanabiliriz.Postmapping bir RequestMappingdir.Sadece RequestMapping de typeını veririz.

    //Spring de bir classı inject etmenin birden fazla yolu vardır.
    //Spring de iki çeşit dependency injection yöntemi var.
    //1.Autowired  -> Field Injection ->Spring çok güzel Reflection yapar.
    // Ne demek Reflection? Şöyle-> Java da class içerisindeki private alanlara biz kodsal anlamda erişemeyiz.Reflection kullnarak bunlara erişim sağlıyoruz.
    //İşte biz @Autowired diyerek , spring bu bağımlılığın ne olduğunu bulabiliyor.Biz buna reflection diyoruz.
    /*
    @Autowired
    private  RepositoryService repositoryServiceAutowired ;
    */

    //2.Constructor Injection
    private final RepositoryService repositoryService ;
    public RepositoryController(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    //Peki Field Injection mı ? Constructor Injection mı ?
    //Genel de constructor injecction tercih edilir.Çünkü test yazarken daha kullanışlı oluyor.
    //Çünkü biz test yazarken mockito kullanıyoruz.Mockito da @InjectMocks denen bir kavram var.Burada bir reflection var.E bir de @Autowired ı kullanırsak bir reflection daha olacak.Ve double reflection.
    //Bu nedenle constructor injection daha faydalı oluyor.




    /*
        Bir tane create metodu oluşturacağız ve bu post işlemi olacak.
        Ancak bu asenkron olacak.Yani requesti aldıktan sonra , request alındı diye bir response dönmeli.
        Bunun için hangi http türü olması gerekir ? Ve nasıl bir metod olabilir ?
        1.Fluxlar  kullanılabilir.
        2.Temel olarak da @ResponseStatus(HttpStatus.CREATED)
     */

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody CreateRepositoryRequest request)
    {
        this.repositoryService.create(request.getUrl());

    }

    @GetMapping
    public List<RepositoryResource> list()
    {
      return  RepositoryResource.createFor(this.repositoryService.list());  //model to dto
    }



}
