# 기간 롤링 appender 만들기

rotor 어펜더는 특정 크기가 되면 다음 로그파일이 만들어지는데, 
rolling 어펜더는 날짜별, 주별, 월별로 로그파일을 생성하는 appender이다. 

handler에서 설정할 것이므로 handler namespace에 :require를 추가하자.

```clojure
(ns myapp.handler
  (:require ...
            [taoensso.timbre.appenders.rotor :as rotor]
            [taoensso.timbre.appenders.rolling :as rolling]
```

rolling appender는 make-rolling-appender함수로 만들 수 있다.
기본 설정은 (:enabled? true, :min-level nil, :pattern :daily)인데 
함수에 map을 주면 기본 설정을 바꿀 수 있다. 

handler/init 함수에서 rolling appender를 만들자.

```clojure     	  
  (timbre/set-config!
    [:appenders :rolling]
    (rolling/make-rolling-appender
      {:min-level :info}))
```

로그 파일의 위치는 :shared-append-config에 설정하자.
```clojure
  (timbre/set-config!
    [:shared-appender-config :rolling :path]
    "logs/rolling/hipstr.log")
```

로그파일이 있는 디렉토리는 미리 만들어 두어야 한다. timbre는 자동으로 디렉토리를 만들지 않는다.

다 되었다.