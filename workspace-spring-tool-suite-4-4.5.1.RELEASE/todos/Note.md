
### NOTE

#### Spring boot
- boot 는 Spring 을 사용하기 쉽게 미리 infrastructure 등을 세팅해주는 것임. (ex: tomcat 등)
- 마법처럼 자동으로 해주는 것이 많음.
- IoC, DI, AOP 를 굉장히 편하게 해줌.
- 관심사의 분리를 위해 AOP!
- @Profile, 자동으로 Component Scan, @Primary 등 annotation 기반으로 처리하는 것들이 많음. 이게 길어지면 메타 annotation 을 많이 사용함.
- Spring Security 는 enterprise 앱 용으로 규모가 엄청나게 크기때문에 간단한 인증에 사용은 잘 권하지 않음.
- spring intializer 를 통해 프로젝트 init

#### Java EE
- Java EE 의 스펙 구조도(diagram) 한번 보기
- Java EE 와 Spring 은 서로 보완하는 존재. Spring 에서 Java EE 의 스펙이 구현된 것도 있음.
- Spring 은 초기에 Java EE 를 더 구조적으로 layered 하게 쓰기위해 만들어졌음.

#### Others
- 상속보다 interface, composition 등을 추천함
- reflection 기능이 성능을 저하시킨다는 얘기도 있으나 요새는 해당되지 않음
- session 구현 방법: sticky session - load balancer, sticky session clustering, session db   
- 요청이 없어도 클라이언트에 정보 업데이트 시켜주기: polling, long-polling, websocket, *event-stream*: html5 에 있는 스펙이라 별도의 javascript 코드가 필요없음(브라우저가 html5 스펙을 구현했다면).
- 그런데 event-stream 을 thread 당 유저 커넥션 하나로 물게하면 tomcat 등 오픈소스 커넥션을 초과할것임(최대 커넥션 250). 그래서 reactive 기능(비동기, non-blocking i/o)을 써야함.
- 이 강의 과정이 대부분의 애플리케이션을 커버할 것임. 그런데 html5 가 되면서 새로 들어온 기능들 (websocket 등) 은 커버하지 않았음.
- spring.io, spring one 컨퍼런스 추천. 특히 spring.io 는 open source 커뮤니티에서 주최하는 거라 영양가가 있음.


### TODO
- logout 기능
- message converter type hierarchy 뒤져보기
- type hierarchy 기능 좋음
- 처음부터 혼자서 다 다시 복기해보기
