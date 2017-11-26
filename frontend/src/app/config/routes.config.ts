export class Routes {
  static HOME: string = '';
  static LOGIN: string = 'user/login';
  static REGISTER: string = 'user/register';
  static LOGOUT: string = 'user/logout';
  static ISSUES: string = 'issues';
  static VALIDATE_USERNAME: string = 'user/validate-username';
  static VALIDATE_EMAIL: string = 'user/validate-email';
}

export class Server {
  private static address: string = 'localhost';
  private static port: string = '8080';
  private static prefix: string = 'api';

  static routeTo(route: string) {
    return `http://${Server.address}:${Server.port}/${Server.prefix}/${route}`
  }
}


