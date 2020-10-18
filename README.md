## 用户注册接口

* 1,请求相对路径

**"/user/registered"  ** 

* 2.请求方式
  **post**

* 3 请求参数
  1.username 用户名
  2.password 密码
  
* 4.返回值

  **成功返回例子**

  password均为空

  ```json
  {
    "code": "200",
    "message": {
    "upassword": " ",      
    "uid": 17,
    "uname": "5635975311"
    }
  }
  ```
  
  **错误返回例子**
  
  ```json
  {
    "code": "400",
    "message": "用户名重复"
}
  ```


## 用户登录接口
* 1.请求相对路径

  **/user/login**

* 2.请求方式
  **post**

* 3 请求参数
  1.username 用户名
  2.password 密码
  
* 4.返回值

  **成功返回例子**

  password均为空

  ```json
  {
      "code": "200",
      "message": {
          "upassword": " ",
          "uid": 17,
          "uname": "5635975311"
      }
  }
  ```
  
  **错误返回例子**
  
  ```json
  {
      "code": "400",
      "message": "用户名或密码错误"
}
  ```

## 图像识别接口
* 1.请求相对路径

  **get**

* 2.请求方式
  **post**

* 3 请求参数
  **1.file   要识别的图片**
  
* 4.返回值

  **成功返回例子**

  password均为空

  **特别说明 Audio为base64编码过的**
  
  ```json
  {
      "code": "200",
      "message": {
          "userFaceEntity": {
              "gender": 0,
              "age": 23,
              "smileMessage": "你似乎很开心，有什么好事发生吗",
            "message": "你是一位美丽的女性，我猜测你大概23岁。你似乎很开心，有什么好事发生吗"
          },
 "Audio":"UklGRlR/AABXQVZFZm10IBAAAAABAAEAgD4AAAB9AAACABAAZGF0YSx9AAD+////AQD//wAAAAAAAAIAAQADAAMABgAEAAYABQAGAAUABwAIAAgACQAAE......AAgACAAEAAgADAAIAAwACAAQAAwACAAIAAgADAAMAAgACAAIAAwABAAAAAAAAAAAAAAD/////AAAAAAAA//8AAP///v/9//7//v///////v8AAP///////wAA/////wAA/////wAAAAAAAAAAAAAAAAAAAAAAAAAA"
      }
  }
  ```

  **错误返回例子**
  
  ```json
  {
      "code": "400",
      "message": "用户未登录"
  }
  ```
  
    ```json
  {
      "code": "400",
      "message": "图片无法识别到人脸"
  }
  ```
  ```json
 {
    "code": "400",
    "message": "文件为空"
}
  ```
  
  
  ## 服务器出现错误的返回
   ```json
  {
    "code": "500",
    "message": "服务器内部错误"
  }
   ```
