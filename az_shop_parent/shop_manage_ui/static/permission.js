// 父窗口
var parent = window.parent;
// 客户端允许跨域传递cookie
axios.defaults.withCredentials = true;
axios.interceptors.response.use(function (response) {
    if(response.data.code == 500) {
        // 清空用户信息
        localStorage.removeItem("user");
        parent.location.href = "../login/login.html";
    }

    if(response.data.code == 403) { // 没有权限
        location.href = "../403.html";
    }
    return response;
},function (error) {

});