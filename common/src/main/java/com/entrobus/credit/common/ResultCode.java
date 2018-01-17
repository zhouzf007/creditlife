package com.entrobus.credit.common;

public interface ResultCode {

    int OK = 200;//[GET]服务器成功返回用户请求的数据，该操作是幂等的（Idempotent）。
    int CREATED = 201;//[POST/PUT/PATCH]：用户新建或修改数据成功。
    int ACCEPTED = 202;//[*]表示一个请求已经进入后台排队（异步任务）
    int NO_CONTENT = 204;//[DELETE]：用户删除数据成功。
    int INVALID_REQUEST = 400;//[POST/PUT/PATCH]：用户发出的请求有错误，服务器没有进行新建或修改数据的操作，该操作是幂等的。
    int UNAUTHORIZED = 401;//[*]：表示用户没有权限（令牌、用户名、密码错误）。
    int FORBIDDEN = 403;//[*] 表示用户得到授权（与401错误相对），但是访问是被禁止的。
    int NOT_FOUND = 404;//[*]：用户发出的请求针对的是不存在的记录，服务器没有进行操作，该操作是幂等的。
    int NOT_ACCEPTABLE = 406;//[GET]：用户请求的格式不可得（比如用户请求JSON格式，但是只有XML格式）。
    int GONE = 410;//[GET]：用户请求的资源被永久删除，且不会再得到的。
    int UNPROCESABLE_ENTITY = 422;//[POST/PUT/PATCH] 当创建一个对象时，发生一个验证错误。
    int INTERNAL_SERVER_ERROR = 500;//[*]：服务器发生错误，用户将无法判断发出的请求是否成功。

}
