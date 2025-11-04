# JPA

Andmete hoidmiseks võite kasutada PostgreSQL või HSQL andmebaasi. HSQL on oluliselt kiirem ja soovitatav.

Kui soovite HSQL baasis olevat infot nt IDEA vahenditega vaadata, peate käivitama HSQL andmebaasi serveri. See asub klassis HsqlServer.java.

IDEA-st käivitatud HSQL serveriga ühendumiseks:

Database sakk → + → Data Source → HSQLDB

Täitke aknas:
- Driver: HSQLDB (Remote)
- Host: localhost
- Database: db1


## Ülesanded

1. Lisage `PersonDao`-sse meetod, mis võimaldab isikut sisestada. Kasutage JPA vahendeid.

   Sisestage kaks isikut.

   Testimiseks on paketis main-klass Main.java.

   Muuhulgas peab tegema järgmist:

   - Salvestatava objekti klass peab olema märgitud annotatsiooniga @Entity  
   - Klassil peab olema väli, mis on märgistatud @Id  
   - Id väljale lisage @GeneratedValue  
   - Määrake, kust paketist olemeid otsida  
   - Enne salvestamist alustage transaktsioon ja pärast lõpetage (commit)  

   JPA puhul on AutoCommit vaikimisi väljas, seega tuleb transaktsiooni piirid ise määrata.

   Selleks on vaja Spring konfiguratsioonis määrata @EnableTransactionManagement  
   ja salvestaval meetodil kasutada @Transactional.


2. Lisage meetod, mis tagastab kõik isikud.


3. Lisage meetod, mis tagastab isiku nime järgi.

   Kui kasutate konstruktsiooni `query.getResultStream().findFirst()`, lisage meetodile @Transactional.


4. Täiendage esimeses punktis tehtud salvestamise meetodit, et võimaldada loetud ja muudetud isiku salvestamist.

   Olemasoleva objekti salvestamiseks kasutage `em.merge()`.

   Kontrollige, kas muudatused jõuavad andmebaasi.


5. Lisage isikule seos aadressiga.

   Isikul võib olla üks aadress ja sama aadress võib kuuluda mitmele isikule (@OneToOne).

   Kontrollige:
   - kas isiku salvestamisel salvestatakse ka aadress  
   - kas isiku lugemisel loetakse aadress välja  

   Alamelementide salvestamiseks on vajalik seose atribuut *cascade*.


6. Lisage võimalus, et isikule saaks lisada telefone (@OneToMany).

   Alamelementide salvestamiseks on vajalik seose atribuut *cascade*.

   Lisage isikule mõned telefonid ja uurige, kuidas andmed välja näevad andmebaasis.


7. Muutke isiku ja telefonide seost nii, et telefonid ei oleks eraldi olemid.

   Telefon märgitakse @Embeddable ja seos on @ElementCollection.

   Uurige, kuidas andmed välja näevad andmebaasis.


8. Laadige andmebaasi skeem failist `schema.sql`:

```
var populator = new ResourceDatabasePopulator(
        new ClassPathResource("schema.sql"));

DatabasePopulatorUtils.execute(populator, dataSource);
```

   `hibernate.hbm2ddl.auto` väärtuseks määrake `validate`.

   Lisage vastavad annotatsioonid (@Table, @Column), et JPA teaks, kuidas isikuid, telefone ja aadresse olemasoleva skeemiga andmebaasi salvestada.

   Alguses näidatakse tõenäoliselt palju vigu skeemi ja olemi klasside erinevuste kohta.

   Kaks lähenemist:

   **a)** Muuta olemi klasse veateadete järgi. Miinus: näete ainult vigu, mitte seda, mis on juba korrektne.  

   **b)** Määrata ajutiselt `hibernate.hbm2ddl.auto = create` ja vaadata, millised tabelid luuakse. Seejärel muuta olemeid nii, et genereeritud tabelid vastaksid schema.sql skeemile. Kui vastavus saavutatud, muuta väärtus `create` ära. Vajalik võib olla HSQL käsk:

   ```
   DROP SCHEMA PUBLIC CASCADE
   ```


9. Määrake jada (sequence) ühekordselt.

   Tehke abstraktne klass BaseEntity annotatsiooniga @MappedSuperclass.

   Lisage BaseEntity-le väli id ja vajalikud annotatsioonid (@Id, @GeneratedValue, @SequenceGenerator).

   Pärige teised olemi klassid sellest.


## Infoks

@PersistenceContext private EntityManager em;  
Ütleb, et siia süstitakse EntityManager. @Autowired ei ole piisav, sest EntityManager peab olema seotud andmebaasiga ja iga kasutaja peab saama uue koopia.

@Entity  
Ütleb, et seda klassi saab salvestada andmebaasi.

@Id  
Väli, mida kasutatakse id-na.

@GeneratedValue  
Andmebaas paneb väärtuse ise.

@SequenceGenerator(name = "my_seq", sequenceName = "<järjendi nimi>", allocationSize = 1)  
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")  
Id väärtused võetakse <järjendi nimi> nimelisest sequence’ist.

@OneToOne(cascade = CascadeType.ALL)  
@JoinColumn(name = "<välisvõtme veerg>")  
Väli viitab seosele teises tabelis. CascadeType.ALL salvestab ka sõltuva objekti.

@ElementCollection  
@CollectionTable(  
name = "<alamkirjete tabeli nimi>",  
joinColumns = @JoinColumn(name = "<alamtabeli välisvõtme väli>", referencedColumnName = "<peatabeli id väli>")  
)  
Määrab, et alamkirjed asuvad eraldi tabelis ja seos on määratud antud väljadega.

@Embeddable  
Objekt salvestatakse eraldi tabelisse ja sellel puudub id. Eksisteerib ainult koos seda sisaldava objektiga.

factory.setPackagesToScan("model")  
Määrab, kust otsida @Entity ja @Embeddable klasse.

properties.put("hibernate.hbm2ddl.auto", "update")  
Uuendab olemasolevat skeemi või loob uue.

properties.put("hibernate.show_sql", true)  
Näitab SQL päringuid.

@Transactional  
Meetodi alguses alustatakse transaktsioon ja lõpus lõpetatakse see (commit). Ühendus hoitakse avatuna meetodi kestel.

@Table(name = "<tabeli nimi>")  
Määrab tabeli nime.

@Column(name = "<välja nimi>")  
Määrab veeru nime.


**Lahendused:**  
https://youtu.be/xjaQt45WbbQ
