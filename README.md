#### 1. Tomcat 서버를 시작할 때 웹 애플리케이션이 초기화하는 과정을 설명하라.
- WebServerLauncher를 통해 톰캣 서버가 실행된다.

- 서버가 실행되고 나서 서블릿 컨테이너는 listner로 등록된 객체인  ContextLoaderListener에게 이벤트가 발생했음을 알려준다. 현재는 @WebLisner annotation을 이용해서 그 정보를 전달하고 있다.

- 이후 서블릿 클래스가 초기화 된다.
    - DispatcherServlet클래스에서 RequestMapping을 초기화해준다.
    - RequestMapping에서는 요청 url을 해당하는 컨트롤러를 생성하여 매핑시키는 작업을 한다.

#### 2. Tomcat 서버를 시작한 후 http://localhost:8080으로 접근시 호출 순서 및 흐름을 설명하라.
* src/main/java.next.WebServerLauncher.java 에 main method를 실행한다
* - 톰캣이 실행되고 톰캣은 디폴트로 설정된 webapp/index.jsp를 실행시킨다.
- index.jsp에서 response.sendRedirect(“/list.next”)  구문을 통해 redirection이 이루어진다.
- redirection에 의해 맵핑정보를 찾게 되는데, 맵핑 정보는 아래의 경로에 존재한다.
    - src/main/java/core/mvc/RequestMapping.java
    - mappings.put(“/list .next”, new ListController());

    - 이를 통해 ListController가 생성된다.
- ListController에서 DAO를 통해 쿼리를 보낸다.

    - ListController.java에서 questionDao의 finAllByPage() 메소드가 실행이되고 아래의 쿼리문을 통해 데이터베이스에서 목록을 불러온다.

#### 9. ListController와 ShowController가 멀티 쓰레드 상황에서 문제가 발생하는 이유에 대해 설명하라.
* 멀티 쓰레드 상황에서는 클라이언트를 위해 쓰레드가 하나의 인스턴스를 공유하는 방식으로 동작한다. 따라서 로컬변수의 경우 쓰레드별로 할당된 스택을 가지고 있지만,  인스턴스의 변수의 경우 힙 메모리에 올라가므로 모든 쓰레드가 공유하게 된다. 때문에 이에 대한 동기화 처리가 없으면 멀티쓰레드 상황에서 문제가 발생한다. 따라서 메소드 내에서 초기화함으로써 문제를 해결할 수 있다.
