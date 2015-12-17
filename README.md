#### 1. Tomcat 서버를 시작할 때 웹 애플리케이션이 초기화하는 과정을 설명하라.
* 

#### 2. Tomcat 서버를 시작한 후 http://localhost:8080으로 접근시 호출 순서 및 흐름을 설명하라.
* src/main/java.next.WebServerLauncher.java 에 main method를 실행한다
* 톰캣이 실행되고 톰캣은 디폴트로 설정된 webapp/index.jsp를 실행시킨다.
* index.jsp에서 response.sendRedirect(“/list.next”)  구문을 통해 redirection이 이루어진다.
* - redirection에 의해 맵핑정보를 찾게 되는데, 맵핑 정보는 아래의 경로에 존재한다.
    - src/main/java/core/mvc/RequestMapping.java
    - mappings.put(“/list .next”, new ListController());
    - 이를 통해 ListController가 생성된다.
* ListController에서 DAO를 통해 쿼리를 보낸다.
    - ListController.java에서 questionDao의 finAllByPage() 메소드가 실행이되고 아래의 쿼리문을 통해 데이터베이스에서 목록을 불러온다. 
*  SELECT한 DB내용을 List<Question>에 넣고 mav(ModelAndView)를 생성한다.

* 이 때 mav에 할당된 jstlView는 View인터페이스를 구현하면서, render라는 메소드가 있다. 이 render라는 메소드에는  response.sendRedirect(viewName.substring(DEFAULT_REDIRECT_PREFIX.length()));와 같은 redirect 기능이 있는데. 이 기능은 jstlView(“list.jsp”)가 list.jsp로 이동하게 한다.

* 이 때  mav.addObject("questions", questions); 를 통해서 questions 리스트가 함께 전달된다.

* 전달된 question Object는  아래의 구문에 의해 화면에 보여지게 된다.

#### 9. ListController와 ShowController가 멀티 쓰레드 상황에서 문제가 발생하는 이유에 대해 설명하라.

*   멀티 쓰레드 상황에서는 클라이언트를 위해 쓰레드가 하나의 인스턴스를 공유하는 방식으로 동작한다. 따라서 로컬변수의 경우 쓰레드별로 할당된 스택을 가지고 있지만,  인스턴스의 변수의 경우 힙 메모리에 올라가므로 모든 쓰레드가 공유하게 된다. 때문에 이에 대한 동기화 처리가 없으면 멀티쓰레드 상황에서 문제가 발생한다. 따라서 메소드 내에서 초기화함으로써 문제를 해결할 수 있다.
