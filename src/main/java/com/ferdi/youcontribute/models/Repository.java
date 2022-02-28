package com.ferdi.youcontribute.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Repository {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(strategy = "native", name = "native")
    private Integer id;

    private String organization;

    private String repository;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    @OneToMany(mappedBy ="repository" ,fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    //issue içinde bu classı ne olarak tanıyor->mappedBy
    //Bir repositoryden datayı çekerken , issueları çekmek istiyorsak  -> FetchType.EAGER ->Performans kaybına yol açabilir.Gerçekten ihtiyaç varsa kullanmalıyız.
    //Bir repositoryden datayı çekerken , issueları çekmek istemiyorsak  -> FetchType.LAZY -->Issuelar sürekli güncelleneceği için , repository çekildiğinde gelmesi mantıklı değil.
    //Herhangi bir datayı sildiğimiz de onunla ilgili related(ilişki datalara ne olacak?->Cascade ->Burada da Repositoryi sildiğimizde , Issueları bir anlamı olmayacağı için -> CascadeType.REMOVE
    @JsonBackReference
    private Set<Issue> issues;


    /*
    JsonBackReference -> Repositoryleri çektiğimizi düşünelim.Burada  Jackson: Repository classındaki alanları alıp , hepsini Stringe çevirmeye çalışacak.(Serilaze)
    (Serilaze işlemlerinde Jackson kullanılıyor)
    Ardından "issues" alanına geldiğinde tipinin Issue olduğunu görecek. Ve Issue classına geçecek.Ve Issue classının içindeki alanları teker teker serilaze(Stringe) çevirecek.
    Sonra Issue classında "Repository" alanının  görecek.Ve tekrar Repository classına gelecek.Sonra bu döngü gibi olacak.Ve stackOverFlow hatasını burada alacağız.
    Bunun engellemek için JsonBackReference ve JsonManagedReference  alanlarını kyllanamız gerek.

     */
}



/*
Set -->  Eklenen elemanları bir sıralama ile tutmaz yani bir index yapısı kurmaz.
Matematikteki kümeler gibi Set yapısı içerisine aynı elemandan iki tane eklemeye izin vermez.
 Ayrıca set yapısında null değere sahip tek bir eleman olabilir.
 */