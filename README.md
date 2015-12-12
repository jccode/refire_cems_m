
Run server
==========

Run all servers (api & web)
```
gradle :server:bootRun
```

with -P "app" arguments, we can run specify server.

Run api server
```
gradle :server:bootRun -Papp=api
```

Run web server
```
gradle :server:bootRun -Papp=web
```

dist
====

dist all webapps to war
```
gradle :server:war
```

with -P "app" arguments, we can dist a specify server.
```
gradle :server:war -Papp=api
gradle :server:war -Papp=web
```

