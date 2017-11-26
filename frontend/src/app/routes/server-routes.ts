export class Routes {
  static HOME: String = '';
  static LOGIN: String = 'user/login';
  static REGISTER: String = 'user/register';
  static LOGOUT: String = 'user/logout';
  static ISSUES: String = 'issues';
  static VALIDATE_USERNAME: String = 'user/validate-username';
  static VALIDATE_EMAIL: String = 'user/validate-email';
}

export class Server {
  private static address: String = 'localhost';
  private static port: String = '8080';
  private static prefix: String = 'api';

  static routeTo(route: String) {
    return `http://${Server.address}:${Server.port}/${Server.prefix}/${route}`
  }
}


