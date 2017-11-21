export enum Role {
  GUEST, USER, ADMIN
}

export class User {
  username: String;
  password: String;
  email: String;

  constructor(username?: String, password?: String, email?: String) {
    this.username = username || "";
    this.password = password || "";
    this.email = email || "";
  }
}
