package com.ferdi.youcontribute.repository;

import com.ferdi.youcontribute.models.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface RepositoryRepository  extends PagingAndSortingRepository<Repository,Integer> {

    public List<Repository> findAll(); //findAll  override ettik.


    //Biz burada named queryler de yazabiliriz.Named query de metod adlandırmasına göre otomotik olrak sql querysine çevirebiliyor.
    //Mesela organizationa göre arama yapalım
  //  public Repository findByRepository(String organization);
    //Mesela Sıralayalım
    //public Repository findByRepositoryOrderByIdDesc(String organization);

    //Diğer yol ise @Query anatasyonu ile sorguyu kendimiz yazamak.
    // Burada Query içinde yazdıklarımız gerçek SQL ifadeleri değildir.Repository entity adı.
  //  @Query("Select * from Repository")
   // public List<Repository> findAllTest();

    // @Query anatasyonu ile sql sorguyu kendimiz yazamak.
    // Burada Query içerisine sql ifadeleri yazmak için.şeklinde kullanırız.Detaylı sorgularda  tercih edilebilir.
    //Bu sorgularda result olarak dönen değerler bazen çeşitli olabilir.Bu nedenle bunun için bir obje oluşturmak gerek.
    //@Query(value = "SELECT * FROM youcontribute.repository;", nativeQuery = true)
    //public List<Repository> findAllTest2();


    /*
    Spring boot da db ye bağlanırken  connection pool kullanır.->HikariPool

     */
}
