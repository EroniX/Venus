export class Routes {
    static HOME: 							string = '';
    static USER_LOGIN: 						string = 'user/login';
    static USER_GET: 						string = 'user/get';
    static USER_REGISTER: 					string = 'user/register';
    static USER_LOGOUT: 					string = 'user/logout';
    static VALIDATE_USERNAME:				string = 'user/validate-username';
    static VALIDATE_EMAIL: 					string = 'user/validate-email';
                                                  
    static TRAINING_LIST: 					string = "training/list";
    static TRAINING_REGISTER: 				string = "training/register";
    static TRAINING_UNREGISTER: 			string = "training/unregister";
                                                  
    static SEMESTER_LIST: 					string = "semester/list";
    static SEMESTER_REGISTER: 				string = "semester/register";
    static SEMESTER_UNREGISTER: 			string = "semester/unregister";
    static SEMESTER_CURRENT: 				string = "semester/current";
                                                  
    static SUBJECT_LIST: 					string = "subject/list";
                                                  
    static COURSE_LIST: 					string = "course/list";
    static COURSE_CREATE: 				string = "course/create";
    static COURSE_REGISTER: 				string = "course/register";
    static COURSE_UNREGISTER: 				string = "course/unregister";
}

export class Server {
  private static address: 					string = 'localhost';
  private static port: 						string = '8080';
  private static prefix: 					string = 'api';

  static routeTo(route: string) {
    return `http://${Server.address}:${Server.port}/${Server.prefix}/${route}`
  }
}


