export class Routes {
    static HOME: string                 = '';
    static LOGIN: string                = 'user/login';
    static REGISTER: string             = 'user/register';
    static LOGOUT: string               = 'user/logout';
    static ISSUES: string               = 'issues';
    static VALIDATE_USERNAME: string    = 'user/validate-username';
    static VALIDATE_EMAIL: string       = 'user/validate-email';

    static TRAINING_LIST: string        = "training/list";
    static TRAINING_LIST_REGISTERED: string    = "training/listRegistered";
    static TRAINING_LIST_UNREGISTERED: string  = "training/listUnregistered";
    static TRAINING_REGISTER: string    = "training/register";
    static TRAINING_UNREGISTER: string  = "training/unregister";

    static SEMESTER_LIST: string        = "semester/list";
    static SEMESTER_REGISTER: string    = "semester/register";
    static SEMESTER_UNREGISTER: string  = "semester/unregister";
    static SEMESTER_CURRENT: string     = "semester/current";
}

export class Server {
  private static address: string        = 'localhost';
  private static port: string           = '8080';
  private static prefix: string         = 'api';

  static routeTo(route: string) {
    return `http://${Server.address}:${Server.port}/${Server.prefix}/${route}`
  }
}


