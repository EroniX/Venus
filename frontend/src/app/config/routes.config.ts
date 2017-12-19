export class Routes {
    static HOME: 							              string = '';
    static USER_LOGIN: 						          string = 'user/login';
    static USER_FIND_ME:			              string = 'user/find-me';
    static USER_FIND_BY_ID:			            string = 'user/find-by-id';
    static USER_REGISTER: 					        string = 'user/register';
    static USER_LOGOUT: 					          string = 'user/logout';
    static VALIDATE_USERNAME:				        string = 'user/validate-username';
    static VALIDATE_EMAIL: 					        string = 'user/validate-email';
                                                  
    static TRAINING_FIND_ALL: 		          string = "training/find-all";
    static TRAINING_REGISTER: 			        string = "training/register";
    static TRAINING_UNREGISTER: 		        string = "training/unregister";
                                                  
    static SEMESTER_FIND_ALL: 		          string = "semester/find-all";
    static SEMESTER_REGISTER: 			        string = "semester/register";
    static SEMESTER_UNREGISTER: 		        string = "semester/unregister";
    static SEMESTER_FIND_CURRENT:           string = "semester/find-current";
                                                  
    static SUBJECT_FIND_ALL: 			          string = "subject/find-all";
                     
    static COURSE_FIND_BY_ID:               string = "course/find-by-id";
    static COURSE_FIND_BY_SUBJECT_ID: 	    string = "course/find-by-subject-id";
    static COURSE_CREATE: 				          string = "course/create";

    static USER_COURSE_CREATE: 				      string = "user-course/create";
    static USER_COURSE_DELETE: 			        string = "user-course/delete";
    static USER_COURSE_FIND_BY_COURSE_ID:   string = "user-course/find-by-course-id"
    static USER_COURSE_MARK: 				        string = "user-course/mark";
}

export class Server {
  private static address: 					        string = 'localhost';
  private static port: 						          string = '8080';
  private static prefix: 					          string = 'api';

  static routeTo(route: string) {
    return `http://${Server.address}:${Server.port}/${Server.prefix}/${route}`
  }
}


