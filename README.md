
# Minimalist Todo App

**Minimalist Todo App** yapılacak işlemlerinizi kolay bir şekilde yönetmek için geliştirilmiştir. Basitçe üye olabilir, yapılacak işlerinizi kaydedebilir daha sonra silebilir ya da tamamlandı olarak işaretleyebilirsiniz. 

## Modeller Arasındaki İlişki (Entity Relationship)

![UML Diagram](https://farukgenc.com/minimaltodoapp/uml.PNG)

## Modeller (Entity)

* User
* Task

Olmak üzere 2 adet model vardır. *User ve Task* modelleri arasında **ManyToOne** ve **OneToMany** bir ilişki bulunmaktadır. 


## Nasıl kullanabilirim ?

JPA/Hibernate ile geliştirilmiş Spring Boot projemizde herhangi bir tablo oluşturmanıza gerek yok. **Yanlızca bir veritabanı oluşturup** "application.properties" dosyasında DataSource kısmının altındaki ilgili bilgileri düzenlemelisiniz.

Projeyi isterseniz herhangi bir IDE yardımıyla çalıştırabilirsiniz istersenizde consol üzerinden çalıştırabilirsinz. Konsol üzerinden kolayca çalıştırmak için;

**minimal-todo-app** dizinine geçip aşağıdaki komutu çalıştırın.
```
mvn clean install
```
BUILD SUCCESS ifadesinin ardından /target klasörü altında todo-app-0.0.1.jar dosyası oluşacak. Bu dizine girip aşağıdaki komutu çalıştırın.
```
java -jar todo-app-0.0.1.jar
```

Artık proje ```localhost:8080``` adresinden ulaşılabilir durumda. :tada:
İlgili endpointlere [swagger-ui](http://localhost:8080/swagger-ui.html) üzerinden ulaşabilirsiniz. 

Backend projesinden bağımsız angular projesini çalıştırmak için **minimal-todo-app-web-ui** dizinine geçip aşağıdaki komutu çalıştırın.
```
npm install
```
Ardından ilgili paketler yüklenip *node_modules* dizini oluştuktan sonra aynı dizin üzerinde kalıp ;
```
ng serve 
```
ya da 
```
npm run ng serve
```
komutlarını çalıştırarak angular projesini ayağa kaldırabilirsiniz.

Artık fronent projesi```localhost:4200``` adresinden ulaşılabilir durumda. :tada:

## Uygulamaları Docker ile nasıl ayağa kaldırabilirm?

- Bütün uygulamaları (postgres, angular ve spring boot) docker imagelerini üreterek container olarak
ayağa kaldırabiliriz.

```bash
docker-compose up
```

- Container'ları durdurmak ve tekrar güncelleyerek kullanabilmek için de aşağıdaki script i calıştırırız.

```bash
docker-compose down
```

## Kullanılan Teknolojiler

 - Spring Boot
 - Spring Security + JWT
 - JPA/Hibernate
 - Angular 9 & TypeScript
 - PostgreSQL
 - Docker
 
## Ekran Görüntüleri

![desc](https://farukgenc.com/minimaltodoapp/homepage.png)

------------


![desc](https://farukgenc.com/minimaltodoapp/registrationpage.png)


------------


![desc](https://farukgenc.com/minimaltodoapp/loginpage.png)


------------


![desc](https://farukgenc.com/minimaltodoapp/dashboard.png)


------------


![desc](https://farukgenc.com/minimaltodoapp/input_validation.png)


------------


![desc](https://farukgenc.com/minimaltodoapp/backend_validation.png)

------------

*Sevgiler* :purple_heart:
