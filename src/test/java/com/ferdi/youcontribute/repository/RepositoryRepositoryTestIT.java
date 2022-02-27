package com.ferdi.youcontribute.repository;

import com.ferdi.youcontribute.models.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.BDDAssertions.then;

@Disabled
@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(initializers = RepositoryRepositoryTestIT.Initilazer.class )  //Biz gerçek anlamda bir database konfigurasyon testi yapacağız.Bu nedenle mysql ile çalışmak içim bu konfigurasyon önemli.
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("it")
//it adında bir profile tanımladık.Ve çalıştırıken sadece "it" profile verirsek sadece burada ki testler çalışacak.
@Testcontainers //mysql'i container olarak kullanacağız.Bunu springe tanımladık.
public class RepositoryRepositoryTestIT {

    /*
    Integration testler , unit teste göre biraz daha yavaş çalışıyordur.Çünkü database teknolojilerini de ayağa kaldırıyoruz.
    TestContainers bize ne sağlıyor ?
    Bağımlı olduğumuz third party bir teknoloji varsa (Redis,Amazon web service,mysql vs .)  varsa , bunları bir container da ayağa kaldırmamızı sağlar ve orayı yönetir.
     Bazı test containerlar daha önce de tanımlanmışdır.Mesela Redis .Bazı containerlar ise kendimizi generate edebilriiz.->Örnek proje : https://github.com/testcontainers/testcontainers-java/blob/master/modules/mysql/src/test/java/org/testcontainers/junit/mysql/SimpleMySQLTest.java
       //  @BeforeEach -> Test yazarken , her test caseinden önce bi şey çalışsın istiyorsak , BeforeEach içerisine yazabiliirz.
       //  @BeforeEach -> Test yazarken , her test caseinden  sonra bi şey çalışsın istiyorsak , BeforeEach içerisine yazabiliirz.
       //   Mesela bir test yazdınız ve testden sonra dataların silinmesini istiyorsunuz.Bunu @AfterEach içerisin de yazabiliirz.
     */

    public static final DockerImageName MYSQL_57_IMAGE = DockerImageName.parse("mysql:5.7");//Docker image versiyonu

    @Container
    public static MySQLContainer<?> mysql = new MySQLContainer<>(MYSQL_57_IMAGE); //Test çalışmdan önce docker containerı ayağa kaldıracak.

    //Burada kritik nokta şu.Bu class ayağa kalktığında biz springe bu konfigurasyonları vermemiz gerek.
    // Ancak spring uygulama ayağa kalkmadan bu configleri ayarladığı için , bizim oraya girip tanıtmamız gerek.


    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    RepositoryRepository repositoryRepository;

    @Test
    public void it_should_find_all_repository()  {

        //given

        Repository repository1= Repository.builder().repository("repo1").organization("org1").build();
        Repository repository2= Repository.builder().repository("repo2").organization("org2").build();

        this.repositoryRepository.saveAll(Arrays.asList(repository1,repository2));
        this.testEntityManager.flush(); //tranaction çalışsın bitsin.Flush et.Sonraki sorgulara engel olma.


        //when

        List<Repository> repos= this.repositoryRepository.findAll();


        //then

        then(repos.size()).isEqualTo(2); //2 kayıt olduğunun validasyonu
        Repository repo1 =repos.get(0);
        then(repo1.getRepository()).isEqualTo("repo1"); //ilk indisdeki repository name kontrolü
        Repository repo2 =repos.get(1);
        then(repo2.getRepository()).isEqualTo("repo2"); //ikinci indisdeki repository name kontrolü

    }




    static class Initilazer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {

            TestPropertyValues.of("spring.datasource.url=" + mysql.getJdbcUrl(),
                    "spring.datasource.username" + mysql.getUsername(),
                    "spring.datasource.password" + mysql.getPassword())
                    .applyTo(applicationContext.getEnvironment());


            //Spring datasource u burada kendimiz override ettik.
            //Biz normalde Test de, H2 databaseini configini eklemişik.Ancak burada çalıştırdığımız testler de Mysql i kullanacak.

        }
    }


}