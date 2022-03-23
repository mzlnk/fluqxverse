# Welcome to Fluqxverse!
[![Licence: MIT](https://img.shields.io/badge/Licence-MIT-blue.svg)](https://shields.io/)
[![Current Era: Alcyone](https://img.shields.io/badge/Current_Era-Alcyone-blue.svg)](https://shields.io/)
[![Open Source](https://badges.frapsoft.com/os/v2/open-source.svg?v=103)](https://github.com/ellerbrock/open-source-badges/)

<p>
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white"/>
  <img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"/>
  <img src="https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white"/>
  <img src="https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=spring-security&logoColor=white"/>
  <img src="https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB"/>
  <img src="https://img.shields.io/badge/TypeScript-007ACC?style=for-the-badge&logo=typescript&logoColor=white"/>
  <img src="https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white"/>
  <img src="https://img.shields.io/badge/IntelliJ_IDEA-3E66E2?style=for-the-badge&logo=intellij-idea&logoColor=white"/>
</p>

## About 🔍
Fluqxverse is my personal project developed in free time and it is focused on building consistent and reliable microservice architecture based on JVM using latest technologies and approaches.

You will be able to find here topics such as:
- Spring Boot
- Spring JPA
- Spring Security
- Spring Cloud
- Spring Streams
- JWT auth
- CQRS
- message queues
- API documentation (Swagger, OpenAPI)
- Kafka
- and lots more :D

## Development 🛠
The whole development will be divided into longer timespans called Eras. We will try to focus on particular topics in each Era - they can be related to creating new microservice in the universe or enhancing the existing ones with new features. At the end, the whole progress will be checked out to a new branch `era/<era-name>`, so you can easily track how our universe is growing ;)

**🚧 Note 🚧**<br />
Current development status is highly initial - can be considered as a very early Proof of Concept - so contents of the project can change a lot ;)

### Current Era 🌗
Name: **Alcyone**<br />
Started: **2022.03.22**<br />
Finished: **TBA**<br />
Focusing on the following topics: 📎<br />
  - create initial version of a service responsible for authentication management **[authn]**
  - create initial version of a service responsible for authorization management **[authz]**
  - create initial version of a service responsible for identity brokering with 3rd party identity providers (e.g. Google, GitHub) **[identity-broker]**
  - provide basic communication between services

### Past Eras 🌑
none

## Universe overview 🌏
Here you can check out what services are present in the universe and how they are connected to each other in high level overview terms.

[image here]

### Components ⚙️

Below is a list of the components that make up the universe:
- microservices:
  - [AuthN](/authn)
  - [AuthZ](/authz)
  - [Identity Broker](/identity-broker)
- shared libraries:
  - [Auth Security Spring Boot Starter](/shared/auth-security-spring-boot-starter)

## Credits

This project is under MIT licence so feel free to use it for your personal purposes ;)

Created by Marcin Zielonka
