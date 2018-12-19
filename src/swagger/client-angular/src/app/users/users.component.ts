import {Component, OnInit} from '@angular/core';

import {DefaultService} from '../api/api/default.service';
import {User} from '../api/model/user';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {
  users: User[];
  newuser: User;
  title = 'List of users';

  constructor(private api: DefaultService) {}

  ngOnInit() {
    this.reload();
  }

  reload(): void {
    this.newuser = <User>{
      firstname: '',
      lastname: '',
      email: '',
      department: '',
      phone: [],
      homepage: ''
    };
    this.getUsers();
  }

  getUsers() {
    this.api.readUsers()
      .subscribe(u => this.users = u);
  }

  create(): void {
    this.api.createUser(this.newuser)
      .subscribe(x => this.reload());
  }
}
