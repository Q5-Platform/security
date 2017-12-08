/**
 * Created by houcunlu on 2017/8/23.
 */

    // 本地
//var security = "http://127.0.0.1:8090/security/db_security_";  // 权限地址
//var ysscaleBackUrl = "http://127.0.0.1:8090/security/ysscale/web";       // 后台

// var security = "http://127.0.0.1:8090/demo/security/db_security_";  // 权限地址

var security = "http://127.0.0.1:8090/security/security/db_security_";


// var powerUrl = "http://127.0.0.1:8899/power";       // 后台


var File_Upload_Url = "http://127.0.0.1:8080/fileService/file/uploadFile";             // 图片 上传 地址
var File_Download_Url = "http://127.0.0.1:8080/fileService/file/downloadFile?path=";  // 图片 访问 地址

/**
 *  权限
 */
    // 登陆
var securityLogin = "/admin/login";

   //  加载菜单
var  loadAdminMenu = "/resource/loadAdminMenu";

//  验证资源
var  verifyRight = "/resource/verifyRight";


//   权限用户列表
var  findUserPage = "/admin/findUserPage";


//   权限用户信息修改
var  updateUserById = "/admin/updateUserById";


//   权限删除用户
var  deleteUserById = "/admin/deleteUserById";


//   修改用户状态
var  updateEnableByUserId = "/admin/updateEnableByUserId";

//   添加用户
var  addUser = "/admin/addUser";

//   角色列表
var  findRolePage = "/role/findRolePage";

//   添加或修改权限
var saveOrUpdateRole = "/role/saveOrUpdateRole"

//   删除权限
var deleteRoleById = "/role/deleteRoleById"

//   资源列表
var findResourcePage = "/resource/findResourcePage"

//   资源下的接口/功能
var findResourceRightPage = "/resource/findResourceRightPage"

//   资源修改排序
var updateOrderNumById = "/resource/updateOrderNumById"

//   添加或修改资源
var saveOrUpdateResource = "/resource/saveOrUpdateResource"


//   删除资源
var deleteResourceById = "/resource/deleteResourceById"


//   获取权限下的管理员
var getUserByRoleIdPage = "/userRole/getUserByRoleIdPage"


//   获取除拥有该权限用户的所有用户
var loadUserNoHaveUserPage = "/userRole/loadUserNoHaveUserPage"


//   添加权限用户
var addUserRole = "/userRole/addUserRole"


//   删除权限用户
var deleteUser = "/userRole/deleteUser"


//   加载全部资源树
var loadResourceTree = "/roleResource/loadResourceTree"


//   加载角色的资源id
var getResourceByRoleId = "/roleResource/getResourceByRoleId"


//   保存角色的资源
var saveRoleResource = "/roleResource/saveRoleResource"


