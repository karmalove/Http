# Http
一个网络请求封装类
## 调用方法
``` java
Http.setHttpRequest(address,new HttpCallbackListener(){
    @overrid
    public void onFinsh(String response){
    //在这里根据返回内容执行具体的逻辑
    }
    @overrid
    public void onError(Exception e){
    //这里对异常情况进行处理
    }
});
```
